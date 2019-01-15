package y530.retrofitdemo.net;


import y530.retrofitdemo.network2.net.common.Constants;
import y530.retrofitdemo.network2.net.common.IdeaApi;

//定义一个RetrofitHelper 类，通过IdeaApi来获取IdeaApiService的实例。
public class RetrofitHelper {

    //存放所有API的方法
    private static IdeaApiService mIdeaApiService;

    public static IdeaApiService getApiService() {
        if (mIdeaApiService == null)
            mIdeaApiService = IdeaApi.getApiService(IdeaApiService.class, Constants.API_SERVER_URL);
        return mIdeaApiService;
    }
}
