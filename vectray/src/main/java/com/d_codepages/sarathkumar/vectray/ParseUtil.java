package com.d_codepages.sarathkumar.vectray;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Sarath Kumar on 10/24/2015.
 */
public class ParseUtil {

    public static  List<String> descriptor(String data)
    {
        List<String> descriptions = new ArrayList<String>();
        String pattern = "\\{([^*^\\s*$]+)\\}";
        Pattern pt = Pattern.compile(pattern);
        Matcher mat = pt.matcher(data);
        while (mat.find())
        {
            for (int i  = 0; i < mat.groupCount() ; i++)
            {
                 descriptions.add(mat.group(i).replace("{", "").replace("}", ""));
            }
        }
        return descriptions;
    }
}
