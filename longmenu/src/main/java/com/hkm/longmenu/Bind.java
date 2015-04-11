package com.hkm.longmenu;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by hesk on 4/11/2015.
 */
public class Bind {
    public static final int UNSET = -1;
    public static final float UNSETF = -1f;

    /**
     * the default pattern in the background
     */
    public static class Pattern {
        public static final int DEFAULTPATTERN = R.drawable.patterniuucu;
        public static final int DEFAULTBLACK = R.drawable.patternback;
        public static final int GREEN = R.drawable.patterngreen;
        public static final int COLORED = R.drawable.patterncolored;
        public static final int STAMP = R.drawable.patternstamps;
        public static final int SUSHI = R.drawable.patternsushi;
        public static final int LATAX = R.drawable.patternthi;

    }

    private int ResIdCompanyLogo;
    private ArrayList<menuitem> listMenu = new ArrayList<>();
    private int componentWidth, item_height = 50;
    private int scaleType;
    private Context context;
    private float imagePortion = 0.75f;
    private float textPortion = 0.25f;
    private int ResLayoutItem = UNSET;
    private int backgroundpattern = Pattern.DEFAULTPATTERN;
    private float icon_padding = UNSETF;
    private boolean withSeparator = false;

    public Bind(int width_dp_unit, Context context) {
        this.context = context;
        componentWidth = ViewUtils.dipToPixels(context, width_dp_unit);
    }

    public float[] getPortions() {
        return new float[]{imagePortion, textPortion};
    }

    public void setWithSeparator(boolean b) {
        withSeparator = b;
    }

    public boolean isEnabledSeparator() {
        return withSeparator;
    }

    /**
     * @param portion_image has to be less than 1f
     * @param portion_text  has to be less than 1f and less than portion_image
     */
    public void setPortions(float portion_image, float portion_text) throws Exception {
        if ((portion_image + portion_text) == 1.f) {
            imagePortion = portion_image;
            textPortion = portion_text;
        } else throw new Exception("calculation of set portion does not match");
    }

    public void setPattern(int ResIdDrawable) {
        backgroundpattern = ResIdDrawable;
    }

    public int getPattern() {
        return backgroundpattern;
    }

    public void setIconPadding(float dp_unit) {
        icon_padding = dp_unit;
    }

    public float getIconPadding() {
        return icon_padding == UNSETF ? ViewUtils.dipToPixels(context, 10f) : ViewUtils.dipToPixels(context, icon_padding);
    }

    public void setItemHeight(int dp_unit) {
        item_height = ViewUtils.dipToPixels(context, dp_unit);
    }

    public int getItem_height() {
        return item_height;
    }

    public void setCustomResId(int layout) {
        ResLayoutItem = layout;
    }

    public int getResLayoutItem() {
        return ResLayoutItem;
    }

    public void setScaleType(int e) {
        scaleType = e;
    }

    public int getWidth() {
        return componentWidth;
    }

    public void setResIdCompanyLogo(int resid) {
        ResIdCompanyLogo = resid;
    }

    public int getResIdCompanyLogo() {
        return ResIdCompanyLogo;
    }

    public void resetMenu() {
        listMenu.clear();
    }

    public void setAddListMenu(menuitem item) {
        listMenu.add(item);
    }

    public ArrayList<menuitem> getListMenu() {
        return listMenu;
    }
}
