package y530.retrofitdemo.testRetrpfot;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by Administrator on 2019/1/15.
 */

public class A<T> extends B<String> {

    public T data;

    public void setData(T data) {
        this.data = data;
    }

    public A() {
        show();
    }

    public void show() {
        //y530.retrofitdemo.MainActivity$2
        //y530.retrofitdemo.MainActivity$3
        //y530.retrofitdemo.testRetrpfot.A   A<FeedbackModel> feedbackModelA1 = new A<>();
        Class<?> cc = getClass();
        //y530.retrofitdemo.testRetrpfot.A<y530.retrofitdemo.network.model.FeedbackModel>
        //y530.retrofitdemo.testRetrpfot.A<y530.retrofitdemo.network.model.FeedbackModel>
        //y530.retrofitdemo.testRetrpfot.B<java.lang.String>   A<FeedbackModel> feedbackModelA1 = new A<>();
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType) type;
            Type typeToken = pType.getActualTypeArguments()[0];
            System.out.println(typeToken); //eat

        }
    }
}
