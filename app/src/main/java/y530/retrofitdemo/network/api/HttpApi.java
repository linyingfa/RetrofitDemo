package y530.retrofitdemo.network.api;

import io.reactivex.Observable;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * 存放所有的Api
 */

public interface HttpApi {

    @POST("app/user/insert-user-feedback.htm")
    Observable<ResponseBody> postfeedback(@QueryMap Map<String, String> map);

    @POST("app/commodity/grid-commodity-data.htm")
    Observable<ResponseBody> gridcommodity(@QueryMap Map<String, String> map);

}
