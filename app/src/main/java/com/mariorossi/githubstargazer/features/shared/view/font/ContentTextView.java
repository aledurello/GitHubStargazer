package com.mariorossi.githubstargazer.features.shared.view.font;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

import com.mariorossi.githubstargazer.features.shared.utils.Helper;

public class ContentTextView extends TextView {
    public ContentTextView(Context context) {
        super(context);
        init();
    }

    public ContentTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ContentTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ContentTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        setTypeface(Helper.getFontContent());
    }
}
