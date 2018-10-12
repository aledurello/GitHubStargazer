package com.mariorossi.githubstargazer.features.shared.utils;

import android.graphics.Typeface;

import com.github.nitrico.fontbinder.FontBinder;

public class Helper {

    public static Typeface getFontTitle() {
        return FontBinder.get("rounded");
    }

    public static Typeface getFontContent() {
        return FontBinder.get("neue");
    }
}
