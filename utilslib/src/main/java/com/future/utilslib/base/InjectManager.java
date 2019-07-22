package com.future.utilslib.base;

import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;
import com.future.utilslib.R;
import com.future.utilslib.view.toolbar.TitleToolbar;

/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2019-04-03       创建class
 */
public class InjectManager {

    /**
     * Activity注册
     *
     * @param target
     */
    public static void getLayoutId(Activity target) {
        Class<? extends Activity> aClass = target.getClass();
        initInjectLayout(target, aClass);
    }

    private static void initInjectLayout(Activity target, Class<? extends Activity> aClass) {
        InjectLayout injectLayout = aClass.getAnnotation(InjectLayout.class);
        if (injectLayout != null) {
            int value = injectLayout.layoutId();
            if (injectLayout.isShowActTitle()) {
                View root = target.getLayoutInflater().inflate(R.layout.layout_root, null);
                LinearLayout linearLayout = root.findViewById(R.id.container);
                TitleToolbar titleToolbar = root.findViewById(R.id.common_toolbar);
                titleToolbar.setTitle(injectLayout.titleName());
                titleToolbar.setBackVisible(injectLayout.actBackIcon());
                target.getLayoutInflater().inflate(value, linearLayout);
                target.setContentView(root);
            } else {
                target.setContentView(value);
            }
        }
    }


}
