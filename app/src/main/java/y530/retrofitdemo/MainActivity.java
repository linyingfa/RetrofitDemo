package y530.retrofitdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import java.util.ArrayList;
import java.util.List;

import rx_activity_result2.RxActivityResult;
import y530.retrofitdemo.activity.TestNet2Activity;
import y530.retrofitdemo.network.model.FeedbackModel;
import y530.retrofitdemo.network.subscribe.FeedbackSubscribe;
import y530.retrofitdemo.network.utils.OnSuccessAndFaultListener;
import y530.retrofitdemo.network.utils.OnSuccessAndFaultSub;
import y530.retrofitdemo.testRetrpfot.A;
import y530.retrofitdemo.testRetrpfot.D;

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

    //局部内部类
    A<FeedbackModel> feedbackModelA = new A<FeedbackModel>() {
        @Override
        public void show() {
            super.show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //FeedbackSubscribe.getShowList(new OnSuccessAndFaultSub(onSuccessAndFaultListener));
        // new HoCallback<CommodityModel>()
        FeedbackSubscribe.gridcommodity(new OnSuccessAndFaultSub<FeedbackModel>(onSuccessAndFaultListener));
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

        //局部内部类
//        A<FeedbackModel> feedbackModelA = new A<FeedbackModel>() {
//            @Override
//            public void show() {
//                super.show();
//            }
//        };

        //y530.retrofitdemo.MainActivity$2
//        String name = feedbackModelA.getClass().getName();


   /*     new A<FeedbackModel>() {//匿名内部类
            @Override
            public void show() {
                super.show();
            }
        };*/
//        A<FeedbackModel> feedbackModelA1 = new A<>();
        List<Class<?>> classList = getSuperClass(MainActivity.class);
        int b = 1;
    }

    /**
     *  获取当前类的所有父类
     * @param clazz
     * @return
     */
    public static List<Class<?>> getSuperClass(Class<?> clazz) {
        List<Class<?>> clazzs = new ArrayList<Class<?>>();
        Class<?> suCl = clazz.getSuperclass();
        while (suCl != null) {
            System.out.println(suCl.getName());
            clazzs.add(suCl);
            suCl = suCl.getSuperclass();
        }
        return clazzs;
    }

    //获取所传类类型的所有继承的接口列表
    public Class<?>[] getAllInterface(Class<?> clazz) {

        //获取自身的所有接口
        Class<?>[] interSelf = clazz.getInterfaces();
        //递规调用getAllInterface获取超类的所有接口
        Class<?> superClazz = clazz.getSuperclass();
        Class<?>[] interParent = null;
        if (null != superClazz) {
            interParent = getAllInterface(superClazz);
        }

        //返回值
        if (interParent == null && interSelf != null) {
            return interSelf;
        } else if (interParent == null && interSelf == null) {
            return null;
        } else if (interParent != null && interSelf == null) {
            return interParent;
        } else {
            final int length = interParent.length + interSelf.length;
            Class<?>[] result = new Class[length];
            System.arraycopy(interSelf, 0, result, 0, interSelf.length);
            System.arraycopy(interParent, 0, result, interSelf.length, interParent.length);
            return result;
        }
    }
}
