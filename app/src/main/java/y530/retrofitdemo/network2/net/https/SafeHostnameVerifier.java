package y530.retrofitdemo.network2.net.https;



import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import y530.retrofitdemo.network2.net.common.Constants;


/**
 * Created by zhpan on 2018/3/21.
 */

public class SafeHostnameVerifier implements HostnameVerifier {
    @Override
    public boolean verify(String hostname, SSLSession session) {
        if (Constants.IP.equals(hostname)) {//校验hostname是否正确，如果正确则建立连接
            return true;
        }
        return false;
    }
}
