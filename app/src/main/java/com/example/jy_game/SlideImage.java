package com.example.jy_game;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

import androidx.annotation.Nullable;

public class SlideImage extends androidx.appcompat.widget.AppCompatImageView {
    public SlideImage(Context context) {
        super(context);
    }

    public SlideImage(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SlideImage(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private static final String TAG = "SlideImage";

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float startx = 0;
        float starty = 0;
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                startx = event.getX();
                starty = event.getY();


                Log.d(TAG, "onTouchEvent: " + startx + "  y=" + starty);
                break;
            case MotionEvent.ACTION_MOVE:
                float endx = event.getX();
                float endy = event.getY();

                Log.d(TAG, "onTouchEvent: " + endx + "  y=" + endy);
                break;
        }
        return super.onTouchEvent(event);

    }
}
