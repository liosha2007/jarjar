/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tonicsystems.jarjar.util;

import java.util.regex.Pattern;
import javax.annotation.Nonnull;

/**
 *
 * @author shevek
 */
public class ClassNameUtils {

    private static final Pattern ARRAY_FOR_NAME_PATTERN
            = Pattern.compile("\\[L[\\p{javaJavaIdentifierPart}\\.]+?;");

    /**
     * Returns true if the given string looks like a Java array name.
     *
     * @param value
     * @return
     */
    // also used by KeepProcessor
    public static boolean isArrayForName(String value) {
        // Type type = Type.getType(value);
        // type.getSort() == ARRAY;
        // type.getElementType();
        return ARRAY_FOR_NAME_PATTERN.matcher(value).matches();
    }

    // TODO: use this for package remapping too?
    /**
     * Returns true if the String looks like a Java type name.
     */
    public static boolean isForName(@Nonnull String value) {
        if (value.equals(""))
            return false;
        for (int i = 0, len = value.length(); i < len; i++) {
            char c = value.charAt(i);
            if (c != '.' && !Character.isJavaIdentifierPart(c))
                return false;
        }
        return true;
    }

    public static boolean isClass(@Nonnull String name) {
        return hasExtension(name, ".class");
    }

    public static boolean hasExtension(@Nonnull String name, @Nonnull String ext) {
        if (name.length() < ext.length())
            return false;
        String actual = name.substring(name.length() - ext.length());
        return actual.equalsIgnoreCase(ext);
    }
}
