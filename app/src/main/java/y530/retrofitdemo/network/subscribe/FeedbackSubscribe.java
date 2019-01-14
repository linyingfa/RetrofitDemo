package y530.retrofitdemo.network.subscribe;


import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import y530.retrofitdemo.network.utils.RetrofitFactory;


public class FeedbackSubscribe {

    /**
     * 获取视频列表页面
     */
    public static void getShowList(DisposableObserver<ResponseBody> subscriber) {
        Map<String, String> map = new HashMap<>();
        map.put("feedbackConten", "哈哈哈哈哈哈哈");
        Observable observable = RetrofitFactory.getInstance().getHttpApi().postfeedback(map);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    public static void gridcommodity(DisposableObserver<ResponseBody> subscriber) {
        Map<String, String> map = new HashMap<>();
        map.put("city", "广州市");
        Observable observable = RetrofitFactory.getInstance().getHttpApi().gridcommodity(map);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }
}
