package com.hkm.longmenu;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by hesk on 4/11/2015.
 */
public class MAitem<activityClass extends Activity> extends ItemBase {

    private Class<activityClass> classIntent;

    public MAitem(int logoId, String menuName) {
        super(logoId, menuName);
    }

    public MAitem(int logoId, String menuName, Class<activityClass> intentClass) {
        this(logoId, menuName);
        classIntent = intentClass;
    }

    public MAitem(int logoId, String menuName, Class<activityClass> intentClass, Bundle extras) {
        this(logoId, menuName);
        setExtra(extras);
        classIntent = intentClass;
    }


    public Class<activityClass> getClassIntent() {
        return classIntent;
    }


}
