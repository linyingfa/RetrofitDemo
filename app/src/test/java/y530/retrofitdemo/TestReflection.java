package y530.retrofitdemo;

import android.util.Log;

import org.junit.Test;

import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/1/15.
 */

public class TestReflection {


    public static class Table extends Room {
        @Override
        public void show() {//覆盖的是父类Room的show方法
            super.show();
        }
    }

    public static class Room implements B {
        @Override
        public void show() {

        }
    }

    public static final class InnerClass {
    }


    public interface InnerInteface {
    }

    public interface B {
        void show();
    }

    public static class House<Q> {
    }

    public static class Particle<POSITION, MOMENTUM> {
    }

    @Test
    public void main() {

        //例子, 这个例子表明类的参数类型跟传进去的类型没有关系，泛型参数只是`占位符`,例如: E ,K V  U Q...等等，只是一个占位符
        //[E]       public class ArrayList<E>
        List<Table> tableList = new ArrayList<Table>();
        System.out.println(Arrays.toString(tableList.getClass().getTypeParameters()));
        //[K, V]    public class HashMap<K,V>
        Map<Room, Table> maps = new HashMap<Room, Table>();
        System.out.println(Arrays.toString(maps.getClass().getTypeParameters()));
        //[Q]
        House<Room> house = new House<Room>();
        System.out.println(Arrays.toString(house.getClass().getTypeParameters()));
        //[POSITION, MOMENTUM]
        Particle<Long, Double> particle = new Particle<Long, Double>();
        System.out.println(Arrays.toString(particle.getClass().getTypeParameters()));
    }

    @Test
    public void Main2() {
        //这个例子表明编译过程中并没有根据参数生成新的类型
        Class c1 = new ArrayList<Integer>().getClass();
        Class c2 = new ArrayList<String>().getClass();
        System.out.print(c1 == c2);//public class ArrayList<E>
    }

    /**
     * 获取所传类类型的所有继承的接口列表
     *
     * @param clazz
     * @return
     */
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


    /**
     * 基本类周边信息获取 , 获取普通函数的父类Class对象
     *
     * @throws ClassNotFoundException
     */
    @Test
    public void main3() throws ClassNotFoundException {
        String TAG = "TAG";
        //TODO 一、引入
        //TODO 1、类的生命周期
        //TODO 2、获取类类型
        //1、泛型隐藏填充类型默认填充为无界通配符？
        Class<?> class1 = Table.class;
        Class<Table> class2 = Table.class;
        Class class3 = Table.class;
        //2、获取类类型的方法
        Table table = new Table();
        Class a = table.getClass();//方法一：
        Class b = Table.class;//方法二：
//        Class c = Class.forName("y530.retrofitdemo.TestReflection.Table");//方法三：
//        Class d = getClass().getClassLoader().loadClass("y530.retrofitdemo.TestReflection.Table");  //方法四：（不建议使用）
        //TODO  二、基本类类型周边信息获取
        //TODO 1、类名，包名获取
        Class<?> class11 = Table.class;
        Package package1 = class11.getPackage();
        System.out.println(("TAG" + "完整的类名：" + class11.getName()));
        System.out.println(("TAG" + "仅获取类名：" + class11.getSimpleName()));
        System.out.println(("TAG" + "包名：" + package1.getName()));
        //todo （2）、获取超类Class对象
        Class<?> class22 = Table.class;
        Class<?> parentClass = class22.getSuperclass();
        //获取普通函数的父类Class对象
        System.out.println(TAG + "父类：" + parentClass.getName());
        //todo (3)、获取类所直接继承的接口的Class对象
        Class<?> class33 = Table.class;
        Class<?>[] interfaces = class33.getInterfaces();
        for (Class interItem : interfaces) {
            System.out.println((TAG + "Table继承的接口：" + interItem.getName()));
        }
        //（4）、综合提升：获取某个类类型的所有接口
        getAllInterface(Table.class);
        //（5）、获取类的访问修饰符
        Class<?> clazz = InnerClass.class;
        int modifiers = clazz.getModifiers();
        String retval = Modifier.toString(modifiers);
        boolean isFinal = Modifier.isFinal(modifiers);
        System.out.println("InnerClass的定义修饰符:" + retval);
        System.out.println("is Final:" + isFinal);
        //（6）、获取接口的访问修饰符
        //使用
        Class<?> clazz2 = InnerInteface.class;
        int modifiers2 = clazz2.getModifiers();
        String retval2 = Modifier.toString(modifiers2);
        boolean isInteface = Modifier.isInterface(modifiers2);
        Log.d(TAG, "InnerClass的定义修饰符:" + retval2);
        Log.d(TAG, "isInteface:" + isInteface);
    }

    //Point泛型类的实现
    public static class Point<T> {

        private T x, y;

        public T getX() {
            return x;
        }

        public void setX(T x) {
            this.x = x;
        }

        public T getY() {
            return y;
        }

        public void setY(T y) {
            this.y = y;
        }
    }

    //PointImpl类的实现
    public static class PointImpl extends Point<Integer> {

    }

    public interface PointInterface<T, U> {
    }

    public static class PointImpl2 extends Point<Integer> implements PointInterface<String, Double> {
    }

    /**
     * 泛型相关周边信息获取
     *
     * @throws ClassCastException
     */
    @Test
    public void main4() throws ClassCastException {
        //TODO 一、获取泛型超类和接口的相信信息   泛型获取的都有这个单词Generic
        //TODO 1、获取泛型超类相信信息
        Class<?> clazz = PointImpl.class;//获取PointImpl对象
        Type type = clazz.getGenericSuperclass();//获取PointImpl的父类
        if (type instanceof ParameterizedType) {//该父类是否是泛型
            ParameterizedType parameterizedType = (ParameterizedType) type;
            //返回表示此类型实际类型参数的 Type 对象的数组，获取父类泛型参数，实际的类类型
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            for (Type parameterArgType : actualTypeArguments) {
                //类类型
                Class parameterArgClass = (Class) parameterArgType;
                System.out.println("填充类型为：" + parameterArgClass.getName());
            }
            //返回 Type 对象，表示声明此类型的类或接口。当前泛型表达式的类或者接口的Class对象
            //该父类可能是类，可能是接口，
            Type type1 = parameterizedType.getRawType();
            Class class22 = (Class) type1;
            System.out.println("PointImpl的父类类型为：" + class22.getName());
        }
        //（1）、获取泛型超类
        //通过clazz.getGenericSuperclass()获取PointImpl.class的超类。
        // 由于我们知道PointImpl.class的父类是泛型，(不能通过普通getSuperclass（）来获取，这个是获取不是泛型的超类)
        // 所以我们只能使用clazz.getGenericSuperclass()来获取
        //public final class Class<T> implements Serializable, AnnotatedElement, GenericDeclaration, Type
        Class<?> clazz2 = PointImpl.class;//获取PointImpl对象
        Type type2 = clazz2.getGenericSuperclass();//获取PointImpl的父类（Type）
        // ParameterizedType：这就是上面我们代码中用到的，他代表的是一个泛型类型，比如Point，它就是一个泛型类型。
        //获得PointImpl.class的父类，而它的父类是Point，这明显是一个泛型类型，所以它对应的类型就是ParameterizedType；
        //TODO 2、获取所继承泛型接口的相关信息
        //...........
        //todo 总结

        /**
         *
         public Type getGenericSuperclass();//获取泛型超类的Type

         public Type[] getGenericInterfaces(); //获取泛型接口的方法

         Type[] getActualTypeArguments();//获取填充泛型变量的真实参数列表

         Type getRawType();  //返回声明此当前泛型表达式的类或接口的Class对象


         String  getName();   //就是得到当前泛型变量的名称；

         Type[]  getBounds(); //返回表示此类型变量上边界的 Type 对象的数组。如果没有上边界，则默认返回Object；


         Type getGenericComponentType(); //当前数组类型所对应的Type


         Type[] getUpperBounds();   //获取通配符的上边界对象列表

         Type[] getLowerBounds();  //获取通配符的下边界对象列表
         */



        /**
         * 获取构造函数
         */
        ////获取public类型的构造函数
        //        Constructor<?>[] getConstructors ();
        //        Constructor<T> getConstructor (Class < ? >...parameterTypes);
        ////获取所有类型的构造函数
        //        Constructor<?>[] getDeclaredConstructors ();
        //        Constructor<T> getDeclaredConstructor (Class < ? >...parameterTypes)
        //
        ///*
        //* 获取对应类的实例
        //*/
        //        public T newInstance (Object...args);
        //
        ///**
        // * 获取参数类型
        // */
        ////用于解析一般函数
        //        Class<?>[] getParameterTypes ();
        ////用于解析泛型对象
        //        Type[] getGenericParameterTypes ();
        //
        ///**
        // * 获取访问修饰符
        // */
        //        int getModifiers ()
        //
        ///**
        // * 获取声明Constructor的类的Class对象；
        // */
        //        Class<T> getDeclaringClass ()

        /**
         * 获取Field对象
         */
        ////仅能获取声明类型为public的成员变量
        //        Field[] getFields();
        //        Field getField(String name);
        ////可以获取全部的成员变量
        //        Field[] getDeclaredFields();
        //        Field getDeclaredField(String name)

        ///**
        // * get,set方法系列
        // */
        ///*//当获取或设置指定类对象中某变量的值
        //        void set(Object object, Object value)
        //        Object get(Object object)
        //
        ////设置与获取int类型的值
        //        void setInt(Object object, int value)
        //        int getInt(Object object)
        ////设置与获取double类型的值
        //        void setDouble(Object object, double value)
        //        double getDouble(Object object)
        ////设置与获取float类型的值
        //        void setFloat(Object object, float value)
        //        float getFloat(Object object)
        ////设置与获取bool类型的值
        //        void setBoolean(Object object, boolean value)
        //        boolean getBoolean(Object object)
        ////设置与获取short类型的值
        //        void setShort(Object object, short value)
        //        short getShort(Object object)
        ////设置与获取long类型的值
        //        void setLong(Object object, long value)
        //        long getLong(Object object)
        ////设置与获取byte类型的值
        //        void setByte(Object object, byte value)
        //        byte getByte(Object object)
        ////设置与获取char类型的值
        //        void setChar(Object object, char value)
        //        char getChar(Object object)
        //
        //*//**
        // * 这个函数用于判断当前field是否为枚举常量；
        // *//*
        //        boolean isEnumConstant()*/

        /**
         * 获取指定类中的成员函数Method对象
         */

            ////获取声明为public的成员函数
            //        Method[] getMethods()
            //        Method getMethod(String name, Class<?>... parameterTypes)
            ////获取所有的成员函数
            //        Method[] getDeclaredMethods()
            //        Method getDeclaredMethod(String name, Class<?>... parameterTypes)
            ///*
            //* 执行某个方法
            //*/
            //        Object invoke(Object receiver, Object... args)
            //
            ///**
            // * 获取参数类型
            // */
            //        Class<?>[] getParameterTypes()
            //        Type[] getGenericParameterTypes()
            //
            ///**
            // * 获取返回值类型
            // */
            //        Class<?> getReturnType()
            //        Type getGenericReturnType()

    }


}
