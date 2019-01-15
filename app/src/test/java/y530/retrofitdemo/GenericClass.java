package y530.retrofitdemo;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/1/15.
 * 针对1的GenericClass<T>，运行时通过Class.getTypeParameters()方法得到的数组可以获取那个“T”；
 * 同理，2的T、3的java.lang.String与T、4的T与U都可以获得。
 * 源码文本里写的是什么运行时就能得到什么；
 * 像是T、U等在运行时的实际类型是获取不到的。
 */

public class GenericClass<T> {                // 1
    private List<T> list;                     // 2
    private Map<String, T> map;               // 3

    public <U> U genericMethod(Map<T, U> m) { // 4
        return null;
    }

    public GenericClass() {
        System.out.println(GenericClass.class.getTypeParameters());
    }
}
