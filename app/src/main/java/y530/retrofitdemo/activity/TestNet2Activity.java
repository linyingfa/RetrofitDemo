package y530.retrofitdemo.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import y530.retrofitdemo.R;
import y530.retrofitdemo.module.reponse.LoginResponse;
import y530.retrofitdemo.module.reponse.MeiZi;
import y530.retrofitdemo.module.request.LoginRequest;
import y530.retrofitdemo.net.RetrofitHelper;
import y530.retrofitdemo.network2.net.common.BasicResponse;
import y530.retrofitdemo.network2.net.common.Constants;
import y530.retrofitdemo.network2.net.common.DefaultObserver;
import y530.retrofitdemo.network2.net.common.ProgressUtils;
import y530.retrofitdemo.network2.net.download.DownloadListener;
import y530.retrofitdemo.network2.net.download.DownloadUtils;
import y530.retrofitdemo.network2.utils.FileUtils;
import y530.retrofitdemo.network2.utils.LogUtils;
import y530.retrofitdemo.network2.utils.RxUtil;
import y530.retrofitdemo.network2.utils.ToastUtils;


public class TestNet2Activity extends BaseActivity {
    private Button btn;
    ProgressBar progressBar;
    TextView mTvPercent;
    private DownloadUtils downloadUtils;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main2;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        mTvPercent = (TextView) findViewById(R.id.tv_percent);
        btn = (Button) findViewById(R.id.btn_download);
        downloadUtils = new DownloadUtils();
    }

    /**
     * Post请求
     */
    public void login(View view) {
        LoginRequest loginRequest = new LoginRequest(this);
        loginRequest.setUserId("123456");
        loginRequest.setPassword("123123");
        Map<String, Object> params = new HashMap<>();
        RetrofitHelper.getApiService()
                .login(params)
                .compose(RxUtil.<LoginResponse>rxSchedulerHelper(this))
                .subscribe(new DefaultObserver<LoginResponse>() {
                    @Override
                    public void onSuccess(LoginResponse response) {
                        ToastUtils.show("登录成功");
                    }
                });
    }

    /**
     * Get请求
     *
     * @param view
     */
    public void getData(View view) {
        RetrofitHelper.getApiService()
                .getMezi()
                .compose(RxUtil.<List<MeiZi>>rxSchedulerHelper(this))
                .subscribe(new DefaultObserver<List<MeiZi>>() {
                    @Override
                    public void onSuccess(List<MeiZi> response) {
                        showToast("请求成功，妹子个数为" + response.size());
                    }
                });
    }

    /**
     * 单文件上传 方法一
     */
    public void uploadFile1(View view) {
        //文件路径
        File file = getFile();
        RequestBody fileBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("phone", "12345678901")
                .addFormDataPart("password", "123123")
                .addFormDataPart("uploadFile", file.getName(), fileBody);
        List<MultipartBody.Part> parts = builder.build().parts();
        RetrofitHelper.getApiService()
                .uploadFiles(parts)
                .subscribeOn(Schedulers.io())
                .compose(this.<BasicResponse>bindToLifecycle())
                .compose(ProgressUtils.<BasicResponse>applyProgressBar(this, "上传文件..."))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<BasicResponse>() {
                    @Override
                    public void onSuccess(BasicResponse response) {
                        ToastUtils.show("文件上传成功");
                    }
                });
    }

    /**
     * 单文件上传 方法二
     */
    public void uploadFile2(View view) {
        File file = getFile();
        //  图片参数
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part fileBody = MultipartBody.Part.createFormData("uploadFile", file.getName(), requestFile);
        //  手机号参数
        RequestBody phoneBody = RequestBody.create(MediaType.parse("multipart/form-data"), "12345678909");
        //  密码参数
        RequestBody pswBody = RequestBody.create(MediaType.parse("multipart/form-data"), "123123");

        RetrofitHelper.getApiService()
                .uploadFiles(phoneBody, pswBody, fileBody)
                .subscribeOn(Schedulers.io())
                .compose(this.<BasicResponse>bindToLifecycle())
                .compose(ProgressUtils.<BasicResponse>applyProgressBar(this, "上传文件..."))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<BasicResponse>() {
                    @Override
                    public void onSuccess(BasicResponse response) {
                        ToastUtils.show("文件上传成功");
                    }
                });
    }

    /**
     * 下载文件
     *
     * @param view
     */
    public void download(View view) {
        btn.setClickable(false);
        downloadUtils.download(Constants.DOWNLOAD_URL, new DownloadListener() {
            @Override
            public void onProgress(int progress) {
                LogUtils.e("--------下载进度：" + progress);
                Log.e("onProgress", "是否在主线程中运行:" + String.valueOf(Looper.getMainLooper() == Looper.myLooper()));
                progressBar.setProgress(progress);
                mTvPercent.setText(String.valueOf(progress) + "%");
            }

            @Override
            public void onSuccess(ResponseBody responseBody) {  //  运行在子线程
                saveFile(responseBody);
                Log.e("onSuccess", "是否在主线程中运行:" + String.valueOf(Looper.getMainLooper() == Looper.myLooper()));
            }

            @Override
            public void onFail(String message) {
                btn.setClickable(true);
                ToastUtils.show("文件下载失败,失败原因：" + message);
                Log.e("onFail", "是否在主线程中运行:" + String.valueOf(Looper.getMainLooper() == Looper.myLooper()));
            }

            @Override
            public void onComplete() {  //  运行在主线程中
                ToastUtils.show("文件下载成功");
                btn.setClickable(true);
            }
        });
    }

    /**
     * 取消下载
     *
     * @param view
     */
    public void cancelDownload(View view) {
        if (downloadUtils != null) {
            downloadUtils.cancelDownload();
            btn.setClickable(true);
        }
    }

    private void saveFile(ResponseBody body) {
        String fileName = "oitsme.apk";
        String fileStoreDir = Environment.getExternalStorageDirectory().getAbsolutePath();
        try {
            InputStream is = body.byteStream();
            File file = new File(fileStoreDir + "/" + fileName);
            FileOutputStream fos = new FileOutputStream(file);
            BufferedInputStream bis = new BufferedInputStream(is);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = bis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
                fos.flush();
            }
            fos.close();
            bis.close();
            is.close();
            installApk(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void installApk(File file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file),
                "application/vnd.android.package-archive");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private File getFile() {
        String fileStoreDir = Environment.getExternalStorageDirectory().getAbsolutePath();
        String filePath = fileStoreDir + "/test/test.txt";
        FileUtils.createOrExistsFile(filePath);
        //文件路径
        return new File(filePath);
    }
}
