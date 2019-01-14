package y530.retrofitdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import y530.retrofitdemo.network.model.FeedbackModel;
import y530.retrofitdemo.network.subscribe.FeedbackSubscribe;
import y530.retrofitdemo.network.utils.OnSuccessAndFaultListener;
import y530.retrofitdemo.network.utils.OnSuccessAndFaultSub;

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


        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //FeedbackSubscribe.getShowList(new OnSuccessAndFaultSub(onSuccessAndFaultListener));
                FeedbackSubscribe.gridcommodity(new OnSuccessAndFaultSub<FeedbackModel>(onSuccessAndFaultListener));
            }
        });
    }
}
