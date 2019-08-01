package com.future.utilslib.utils;


/**
 * 常用单位转换的辅助类
 */
public class LzDp2Px {
    private LzDp2Px() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }


    /**
     * 单位转换: dp -> px
     *
     * @param dp
     * @return
     */
    public static int dp2px(float dp) {
        return (int) (LzResUtils.getDensity() * dp + 0.5);
    }

    /**
     * 单位转换: sp -> px
     *
     * @param sp
     * @return
     */
    public static int sp2px(float sp) {
        return (int) (LzResUtils.getFontDensity() * sp + 0.5);
    }

    /**
     * 单位转换:px -> dp
     *
     * @param px
     * @return
     */
    public static int px2dp(float px) {
        return (int) (px / LzResUtils.getDensity() + 0.5);
    }

    /**
     * 单位转换:px -> sp
     *
     * @param px
     * @return
     */
    public static int px2sp(float px) {
        return (int) (px / LzResUtils.getFontDensity() + 0.5);
    }


}
