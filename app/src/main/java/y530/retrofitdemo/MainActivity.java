package y530.retrofitdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import rx_activity_result2.RxActivityResult;
import y530.retrofitdemo.activity.TestNet2Activity;
import y530.retrofitdemo.network.utils.OnSuccessAndFaultListener;

public class MainActivity extends AppCompatActivity {

    OnSuccessAndFaultListener onSuccessAndFaultListener = new OnSuccessAndFaultListener() {
        @Override
        public void onSuccess(String result) {
            Log.i("result", result);
        }


        @Override
        public void onFault(String errorMsg) {
            Log.i("result", errorMsg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //FeedbackSubscribe.getShowList(new OnSuccessAndFaultSub(onSuccessAndFaultListener));
        // FeedbackSubscribe.gridcommodity(new OnSuccessAndFaultSub<FeedbackModel>(onSuccessAndFaultListener));
        findViewById(R.id.test).setOnClickListener(v -> {
       /*     RxActivityResult.on(this)
                    .startIntent(new Intent(this, TestNet2Activity.class))//请求result
                    .map(result -> result.data())//对result的处理，转换为intent
                    .subscribe(intent -> {
                        //处理数据结果
                    });
*/
            Intent intent = new Intent(this, TestNet2Activity.class);
            this.startActivity(intent);
        });
    }
}
