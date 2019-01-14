package y530.retrofitdemo.module.request;


import y530.retrofitdemo.network2.utils.SharedPreferencesHelper;
import y530.retrofitdemo.network2.utils.Utils;

/**
 * Created by zhpan on 2017/10/25.
 * Description:
 */

public class BasicRequest {
    public String token = (String) SharedPreferencesHelper.get(Utils.getContext(), "token", "");

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
