package com.hkm.longmenu;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by hesk on 4/11/2015.
 */
public class menuitem<activityClass extends Activity> {
    private final int ResIdCompanyLogo;
    private Class<activityClass> classIntent;
    private final String menuDisplayName;
    private Bundle theExtraData;

    public menuitem(int logoId, String menuName) {
        ResIdCompanyLogo = logoId;
        menuDisplayName = menuName;
    }

    public menuitem(int logoId, String menuName, Class<activityClass> intentClass) {
        ResIdCompanyLogo = logoId;
        menuDisplayName = menuName;
        classIntent = intentClass;
    }

    public menuitem(int logoId, String menuName, Class<activityClass> intentClass, Bundle extras) {
        ResIdCompanyLogo = logoId;
        menuDisplayName = menuName;
        classIntent = intentClass;
        theExtraData = extras;
    }

    public int getResIdCompanyLogo() {
        return ResIdCompanyLogo;
    }

    public Class<activityClass> getClassIntent() {
        return classIntent;
    }

    public Bundle getTheExtraData() {
        return theExtraData;
    }

    public String getMenuDisplayName() {
        return menuDisplayName;
    }
}
