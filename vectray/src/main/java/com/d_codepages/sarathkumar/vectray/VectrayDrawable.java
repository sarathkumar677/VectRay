package com.d_codepages.sarathkumar.vectray;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Sarath Kumar on 27-01-2016.
 */

@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface VectrayDrawable {
    String Icon();
    int Color();
    int Dp();
 }
