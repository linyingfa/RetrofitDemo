package y530.retrofitdemo.network2.net.common;

import retrofit2.Retrofit;

/**
 * Created by zhpan on 2017/4/1.
 * 构建初始化Retrofit的工具类IdeaApi
 */

public class IdeaApi {
    public static <T> T getApiService(Class<T> cls, String baseUrl) {
        // Retrofit.Builder.build();
        Retrofit retrofit = RetrofitUtils.getRetrofitBuilder(baseUrl).build();
        return retrofit.create(cls);
    }
}
