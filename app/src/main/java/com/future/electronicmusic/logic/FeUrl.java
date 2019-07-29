package com.future.electronicmusic.logic;

import com.future.utilslib.BuildConfig;
import com.future.utilslib.utils.LzInitUtil;
import com.future.utilslib.utils.LzSPUtils;

/**
 * Created by Administrator on 2018/5/2 0002.
 */

public final class FeUrl {
    public static String BASE_URl = BuildConfig.API_URL;

    static {
        if (BuildConfig.DEBUG) {
            try {
                boolean s = LzSPUtils.getBoolean(LzInitUtil.APPBASEYRL);
                if (s) {
                    BASE_URl = BuildConfig.API_ONLINE_URL;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
