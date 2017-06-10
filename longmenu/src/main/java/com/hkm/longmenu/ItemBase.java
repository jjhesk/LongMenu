package com.hkm.longmenu;

import android.os.Bundle;

/**
 * Created by hesk on 10/6/2017.
 */

public class ItemBase {
    private final int ResIdCompanyLogo;
    private final String menuDisplayName;
    private Bundle theExtraData;


    public ItemBase(int logoId, String menuName) {
        ResIdCompanyLogo = logoId;
        menuDisplayName = menuName;
    }

    public final int getResIdCompanyLogo() {
        return ResIdCompanyLogo;
    }

    public final Bundle getTheExtraData() {
        return theExtraData;
    }

    public final String getMenuDisplayName() {
        return menuDisplayName;
    }

    protected void setExtra(Bundle data) {
        theExtraData = data;
    }
}
