package com.future.electronicmusic.logic;

import com.future.utilslib.BuildConfig;
import com.future.utilslib.utils.LzInitUtil;
import com.future.utilslib.utils.LzSPUtils;



public final class FeUrl {
    public static String BASE_URl = BuildConfig.API_URL;

    static {
        if (BuildConfig.DEBUG) {
            try {
                boolean s = LzSPUtils.getBoolean(LzInitUtil.APPBASEURL);
                if (s) {
                    BASE_URl = BuildConfig.API_ONLINE_URL;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
