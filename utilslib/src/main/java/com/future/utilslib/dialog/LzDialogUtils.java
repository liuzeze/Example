package com.future.utilslib.dialog;


import android.content.Context;
import android.view.View;

/**
 * 对话框弹出帮助类，汇集了多种对话框的简单调用
 * Created by 刘泽 on 2018/7/13
 */
public class LzDialogUtils {

    private static LzAlertDialog mSaAlertDialog;


    public static LzAlertDialog alertTitletDialog(Context context,
                                                  String title,
                                                  String positiveBtnStr,
                                                  View.OnClickListener positiveClickListener,
                                                  String negativeBtnStr,
                                                  View.OnClickListener negativeClickListener) {
        if (mSaAlertDialog != null) {
            mSaAlertDialog.dismiss();
        }

        mSaAlertDialog = new LzAlertDialog(context).builder()
                .setTitle(title)
                .setPositiveButton(positiveBtnStr,
                        positiveClickListener != null ? positiveClickListener : new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        })
                .setNegativeButton(negativeBtnStr, negativeClickListener != null ? negativeClickListener : new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .show();
        return mSaAlertDialog;
    }

    public static LzAlertDialog alertContentDialog(Context context,
                                                   String content,
                                                   String positiveBtnStr,
                                                   View.OnClickListener positiveClickListener,
                                                   String negativeBtnStr,
                                                   View.OnClickListener negativeClickListener) {
        if (mSaAlertDialog != null) {
            mSaAlertDialog.dismiss();
        }

        mSaAlertDialog = new LzAlertDialog(context).builder()
                .setMsg(content)
                .setPositiveButton(positiveBtnStr,
                        positiveClickListener != null ? positiveClickListener : new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        })
                .setNegativeButton(negativeBtnStr, negativeClickListener != null ? negativeClickListener : new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .show();
        return mSaAlertDialog;
    }


    public static LzAlertDialog alertConfirmDialog(Context context,
                                                   String title,
                                                   String content,
                                                   String positiveBtnStr,
                                                   View.OnClickListener positiveClickListener,
                                                   String negativeBtnStr,
                                                   View.OnClickListener negativeClickListener
    ) {

        if (mSaAlertDialog != null) {
            mSaAlertDialog.dismiss();
        }

        mSaAlertDialog = new LzAlertDialog(context).builder()
                .setTitle(title)
                .setMsg(content)
                .setPositiveButton(positiveBtnStr,
                        positiveClickListener != null ? positiveClickListener : new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        })
                .setNegativeButton(negativeBtnStr, negativeClickListener != null ? negativeClickListener : new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .show();

        return mSaAlertDialog;
    }

    public static LzAlertDialog alertConfirmDialog(Context context,
                                                   String title,
                                                   String content,
                                                   String comfirmText,
                                                   View.OnClickListener confirmClickListener) {

        if (mSaAlertDialog != null) {
            mSaAlertDialog.dismiss();
        }

        mSaAlertDialog = new LzAlertDialog(context).builder()
                .setTitle(title)
                .setMsg(content)
                .setConfirmButton(comfirmText, confirmClickListener != null ? confirmClickListener : new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .show();

        return mSaAlertDialog;
    }

    public static LzAlertDialog alertTimeDialog(Context context,
                                                String title,
                                                String content,
                                                int time) {

        if (mSaAlertDialog != null) {
            mSaAlertDialog.dismiss();
        }

        mSaAlertDialog = new LzAlertDialog(context).builder()
                .setAutoDismissTime(time)
                .setTitle(title)
                .setMsg(content)
                .setCancelable(false)
                .show();

        return mSaAlertDialog;
    }


    public static LzAlertDialog alertViewDialog(Context context, View view, String pos, View.OnClickListener positiveClickListener,
                                                String neg, View.OnClickListener negativeClickListener) {

        if (mSaAlertDialog != null) {
            mSaAlertDialog.dismiss();
        }

        mSaAlertDialog = new LzAlertDialog(context).builder()
                .setContentView(view)
                .setPositiveButton(pos,
                        positiveClickListener != null ? positiveClickListener : new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        })
                .setNegativeButton(neg, negativeClickListener != null ? negativeClickListener : new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .show();

        return mSaAlertDialog;
    }

    public static LzAlertDialog alertViewDialog(Context context, View view) {

        if (mSaAlertDialog != null) {
            mSaAlertDialog.dismiss();
        }

        mSaAlertDialog = new LzAlertDialog(context).builder()
                .setContentView(view)
                .show();

        return mSaAlertDialog;
    }

    public static LzBottomDialog alertBottomDialog(Context context, String title, View view, String pos, View.OnClickListener positiveClickListener,
                                                   String neg, View.OnClickListener negativeClickListener) {

        LzBottomDialog mSaAlertDialog = new LzBottomDialog(context).builder()
                .setTitle(title)
                .setContentView(view)
                .setPositiveButton(pos,
                        positiveClickListener != null ? positiveClickListener :
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                    }
                                })
                .setNegativeButton(neg,
                        negativeClickListener != null ? negativeClickListener : new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        })
                .show();


        return mSaAlertDialog;
    }

}
