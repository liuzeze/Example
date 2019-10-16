package com.future.utilslib.http;

import com.future.utilslib.dialog.LoadDialog;
import com.future.utilslib.utils.LzActivityTool;
import com.lz.httplib.observer.JsonParseTransformer;

import java.lang.reflect.Type;
import java.util.List;

import io.reactivex.ObservableTransformer;

/**
 * @author : liuze
 * @e-mail : 835052259@qq.com
 * @date : 2019/10/2-9:58
 * @desc : 修改内容
 * @version: 1.0
 */
public class Transformer {


    public static ObservableTransformer<String, String> switchSchedulersStr() {
        LoadDialog dialog = new LoadDialog(LzActivityTool.currentActivity());
        return JsonParseTransformer.switchSchedulersType(String.class, dialog.getDialog());
    }

    public static <T> ObservableTransformer<String, T> switchSchedulersObj(final Class<T> type) {
        LoadDialog dialog = new LoadDialog(LzActivityTool.currentActivity());
        return JsonParseTransformer.switchSchedulersObj(type, dialog.getDialog());
    }


    public static <T> ObservableTransformer<String, List<T>> switchSchedulersArray(final Class<T> clazz) {
        LoadDialog dialog = new LoadDialog(LzActivityTool.currentActivity());
        return JsonParseTransformer.switchSchedulersArray(clazz, dialog.getDialog());
    }

    public static <T> ObservableTransformer<String, T> switchSchedulersType(final Type type) {
        LoadDialog dialog = new LoadDialog(LzActivityTool.currentActivity());
        return JsonParseTransformer.switchSchedulersType(type, dialog.getDialog());
    }


    public static <T> ObservableTransformer<String, T> switchSchedulersNoBase(final Class<T> clazz) {
        LoadDialog dialog = new LoadDialog(LzActivityTool.currentActivity());
        return JsonParseTransformer.switchSchedulersNoBase(clazz, dialog.getDialog());
    }


    public static <T> ObservableTransformer<T, T> switchThreadDialog() {
        LoadDialog dialog = new LoadDialog(LzActivityTool.currentActivity());
        return JsonParseTransformer.<T>switchThread(dialog.getDialog());
    }

    public static <T> ObservableTransformer<T, T> switchThread() {
        return JsonParseTransformer.<T>switchThread(null);
    }

}
