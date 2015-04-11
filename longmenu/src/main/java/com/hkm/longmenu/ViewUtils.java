package com.hkm.longmenu;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.Map;

/**
 * Created by hesk on 4/11/2015.
 */

public class ViewUtils {
   /* public static T getItemAt(Map<?, T> frameDescriptors, int x, int y) {
        T returnValue = null;

        for (T item : frameDescriptors.values()) {
            if (item.frame.contains((int) x, (int) y)) {
                returnValue = item;
            }

        }
        return returnValue;
    }*/

    public static Point getScreenSize(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }

    public static float dipToPixels(Context context, float dipValue) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics);
    }
    public static int dipToPixels(Context context, int dipValue) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics);
    }

    private View prepareSpace(int dp, Context c) {
        LinearLayout.LayoutParams separator = new LinearLayout.LayoutParams(dp, ViewGroup.LayoutParams.MATCH_PARENT);
        //border set
        View borderVertical = new View(c);
        borderVertical.setLayoutParams(separator);
        return borderVertical;
    }
}

