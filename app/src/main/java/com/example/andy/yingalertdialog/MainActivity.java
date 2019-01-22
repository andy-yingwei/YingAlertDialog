package com.example.andy.yingalertdialog;

import android.app.Activity;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.view.View;

import com.example.yingalertdialoglibrary.YingAlertDialog;

public class MainActivity extends Activity implements View.OnClickListener {

    private int i = -1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.basic_test).setOnClickListener(this);
        findViewById(R.id.under_text_test).setOnClickListener(this);
        findViewById(R.id.error_text_test).setOnClickListener(this);
        findViewById(R.id.success_text_test).setOnClickListener(this);
        findViewById(R.id.warning_confirm_test).setOnClickListener(this);
        findViewById(R.id.warning_cancel_test).setOnClickListener(this);
        findViewById(R.id.custom_img_test).setOnClickListener(this);
        findViewById(R.id.progress_dialog).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.basic_test:
                // default title "Here's a message!"
                YingAlertDialog sd = new YingAlertDialog(this);
                sd.setCancelable(true);
                sd.setCanceledOnTouchOutside(true);
                sd.show();
                break;
            case R.id.under_text_test:
                new YingAlertDialog(this)
                        .setContentText("这里是需要提示的信息！")
                        .show();
                break;
            case R.id.error_text_test:
                new YingAlertDialog(this, YingAlertDialog.ERROR_TYPE)
                        .setTitleText("错 误")
                        .setContentText("这里是需要提示的错误信息!")
                        .show();
                break;
            case R.id.success_text_test:
                new YingAlertDialog(this, YingAlertDialog.SUCCESS_TYPE)
                        .setTitleText("完 成!")
                        .setContentText("您提交的操作已完成!")
                        .show();
                break;
            case R.id.warning_confirm_test:
                new YingAlertDialog(this, YingAlertDialog.WARNING_TYPE)
                        .setTitleText("您确定吗?")
                        .setContentText("点击按钮将删除您所选择的数据!")
                        .setConfirmText("确认删除")
                        .setConfirmClickListener(new YingAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(YingAlertDialog sDialog) {
                                // reuse previous dialog instance
                                sDialog.setTitleText("删除成功!")
                                        .setContentText("您所选择的数据已删除成功!")
                                        .setConfirmText("确 认")
                                        .setConfirmClickListener(null)
                                        .changeAlertType(YingAlertDialog.SUCCESS_TYPE);
                            }
                        })
                        .show();
                break;
            case R.id.warning_cancel_test:
                new YingAlertDialog(this, YingAlertDialog.WARNING_TYPE)
                        .setTitleText("您确定吗?")
                        .setContentText("点击按钮将删除您所选择的数据!")
                        .setCancelText("取消操作")
                        .setConfirmText("确认删除")
                        .showCancelButton(true)
                        .setCancelClickListener(new YingAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(YingAlertDialog sDialog) {
                                // reuse previous dialog instance, keep widget user state, reset them if you need
                                sDialog.setTitleText("操作取消!")
                                        .setContentText("您所选择的数据已取消操作 :)")
                                        .setConfirmText("确 认")
                                        .showCancelButton(false)
                                        .setCancelClickListener(null)
                                        .setConfirmClickListener(null)
                                        .changeAlertType(YingAlertDialog.ERROR_TYPE);

                                // or you can new a YingAlertDialog to show
                               /* sDialog.dismiss();
                                new YingAlertDialog(SampleActivity.this, YingAlertDialog.ERROR_TYPE)
                                        .setTitleText("Cancelled!")
                                        .setContentText("Your imaginary file is safe :)")
                                        .setConfirmText("OK")
                                        .show();*/
                            }
                        })
                        .setConfirmClickListener(new YingAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(YingAlertDialog sDialog) {
                                sDialog.setTitleText("删除成功!")
                                        .setContentText("您所选择的数据已删除成功!")
                                        .setConfirmText("确 认")
                                        .showCancelButton(false)
                                        .setCancelClickListener(null)
                                        .setConfirmClickListener(null)
                                        .changeAlertType(YingAlertDialog.SUCCESS_TYPE);
                            }
                        })
                        .show();
                break;
            case R.id.custom_img_test:
                new YingAlertDialog(this, YingAlertDialog.CUSTOM_IMAGE_TYPE)
                        .setTitleText("标题!")
                        .setContentText("这里是提示信息.")
                        .setCustomImage(R.drawable.custom_img)
                        .show();
                break;
            case R.id.progress_dialog:
                final YingAlertDialog pDialog = new YingAlertDialog(this, YingAlertDialog.PROGRESS_TYPE)
                        .setTitleText("加载中...");
                pDialog.show();
                pDialog.setCancelable(false);
                new CountDownTimer(800 * 7, 800) {
                    public void onTick(long millisUntilFinished) {
                        // you can change the progress bar color by ProgressHelper every 800 millis
                        i++;
                        switch (i){
                            case 0:
                                pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.blue_btn_bg_color));
                                break;
                            case 1:
                                pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.material_deep_teal_50));
                                break;
                            case 2:
                                pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.success_stroke_color));
                                break;
                            case 3:
                                pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.material_deep_teal_20));
                                break;
                            case 4:
                                pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.material_blue_grey_80));
                                break;
                            case 5:
                                pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.warning_stroke_color));
                                break;
                            case 6:
                                pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.success_stroke_color));
                                break;
                        }
                    }

                    public void onFinish() {
                        i = -1;
                        pDialog.setTitleText("已完成!")
                                .setConfirmText("确 认")
                                .changeAlertType(YingAlertDialog.SUCCESS_TYPE);
                    }
                }.start();
                break;
        }
    }
}
