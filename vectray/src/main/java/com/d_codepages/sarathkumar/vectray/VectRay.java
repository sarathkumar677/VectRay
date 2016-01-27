package com.d_codepages.sarathkumar.vectray;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;


public class VectRay {
    public static final String FONTAWSOME = "fontawesome-webfont.ttf";
    private Object target;
    private Context context;
    private String pck_name;
    private TextView IconTextView;
    private List<String> descriptions;
    private HashMap<String, String> Custom_fontsets = new HashMap<>();

    public VectRay(Context con, String package_name) {
        context = con;
        pck_name = package_name;
        annotationProcess();
    }

    public void LoadCustomTypeface(String Key, String TypeFace) {
        Custom_fontsets.put(Key, TypeFace);
    }

    private void annotationProcess() {
        Field[] fields = context.getClass().getFields();
        for (Field field : fields) {
            VectrayDrawable vDrawble = field.getAnnotation(VectrayDrawable.class);
            if (vDrawble != null) {
                if (field.getType() == Drawable.class || field.getType() == IconDrawable.class) {
                    IconDrawable icn = new IconDrawable(context, get_icon(vDrawble.Icon()));
                    icn.color(vDrawble.Color());
                    icn.sizeDp(vDrawble.Dp());
                    target = context;
                    try {
                        field.set(target, icn);
                    } catch (IllegalAccessException e) {
                        throw new AssertionError(e);
                    }

                }
            }
        }


    }

    public void initIcons(ViewGroup view) {
        for (int i = view.getChildCount() - 1; i >= 0; i--) {
            View child = view.getChildAt(i);
            if (child instanceof ViewGroup) {
                initIcons((ViewGroup) child);
            } else {
                if (child != null) {
                    if (child instanceof TextView) {
                        IconTextView = (TextView) child;
                        String data = IconTextView.getText().toString().trim();
                        Icon icon = get_icon(data);
                        if (icon != null) {
                            SetString(icon.getString());
                            TypeFaceSet(icon.getTypeface());
                            if (icon.isAnimated()) {
                                IconTextView.startAnimation(icon.getAnimation());
                            }
                        }

                    }
                }

            }
        }
    }

    private void SetString(String string) {
        IconTextView.setText(string);
    }

    private void TypeFaceSet(Typeface typeface) {
        IconTextView.setTypeface(typeface);
    }

    public Icon get_icon(String data) {
        Icon icon = new Icon();

        if (!data.isEmpty() && data.startsWith("{")) {
            descriptions = ParseUtil.descriptor(data);
            if (!descriptions.isEmpty() && descriptions.size() > 1) {
                switch (descriptions.get(0)) {
                    case "fa":
                        icon.setTypeface(getTypeface(VectRay.FONTAWSOME));
                        break;
                    default:
                        String key = descriptions.get(0);
                        if (Custom_fontsets.containsKey(key)) {
                            String type = Custom_fontsets.get(key);
                            if (!type.isEmpty() || type.equals("")) {
                                icon.setTypeface(getTypeface(type));
                            }
                        }
                }
                String key_to_string = descriptions.get(1);
                if (!key_to_string.isEmpty()) {
                    icon.setString(context.getString(get_string_id(key_to_string)));
                }
                if (descriptions.size() > 2) {
                    switch (descriptions.get(2)) {
                        case "spin":
                            icon.setIsAnimated(true);
                            icon.setAnimation(AnimationUtils.loadAnimation(context, R.anim.rotate));

                    }
                }
                return icon;

            }

        }

        return null;
    }

    private int get_string_id(String key) {
        try {
            Class res = Class.forName(pck_name + ".R$string");
            Field field = res.getField(key);
            return field.getInt(null);
        } catch (Exception e) {
            Log.e("Vectray", "Failure to get String id Check String.xml.", e);
            return 0;
        }

    }

    private Typeface getTypeface(String font) {
        AssetManager assetManager = context.getAssets();
        return Typeface.createFromAsset(assetManager, font);
    }
}
