package com.hkm.longmenu;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

/**
 * Created by hesk on 10/6/2017.
 */

public class MFitem<actClass extends Fragment> extends ItemBase {
    private Class<actClass> classIntent;

    public MFitem(int logoId, String menuName) {
        super(logoId, menuName);
    }

    public MFitem(int logoId, String menuName, Class<actClass> intentClass) {
        this(logoId, menuName);
        classIntent = intentClass;
    }

    public MFitem(int logoId, String menuName, Class<actClass> intentClass, Bundle extras) {
        this(logoId, menuName);
        setExtra(extras);
        classIntent = intentClass;
    }

    public Class<actClass> getClassIntent() {
        return classIntent;
    }
}
