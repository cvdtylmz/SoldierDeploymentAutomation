package com.cevdet.soldierdeploymentautomation.helper;

public class HelperFunction {

    public static boolean isStringEmpty(String text) {
        if (text == null) return true;
        else return text.trim().isEmpty();
    }
}
