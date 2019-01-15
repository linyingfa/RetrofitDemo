package y530.retrofitdemo;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;



/**
 * Created by Administrator on 2019/1/15.
 */

public class Student<T> extends Person<String> {

    public Student() {
        Person2<T> person2 = new Person2();
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType) type;
            Type typeToken = pType.getActualTypeArguments()[0];
            System.out.println(typeToken); //eat
        }
    }


    public static class Person2<T> {
        public Person2() {
            Type type = getClass().getGenericSuperclass();
            if (type instanceof ParameterizedType) {
                ParameterizedType pType = (ParameterizedType) type;
                Type typeToken = pType.getActualTypeArguments()[0];
                System.out.println(typeToken); //eat
            }
        }
    }

}
