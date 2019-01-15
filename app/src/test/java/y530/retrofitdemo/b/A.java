package y530.retrofitdemo.b;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by Administrator on 2019/1/15.
 */

public  class A<T> extends B<T> {

    public A() {

        Type type = this.getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType) type;
            Type typeToken = pType.getActualTypeArguments()[0];
            Type type1 = pType.getRawType();
            System.out.println(typeToken); //eat
            System.out.println(type1); //eat

        }
    }
}
