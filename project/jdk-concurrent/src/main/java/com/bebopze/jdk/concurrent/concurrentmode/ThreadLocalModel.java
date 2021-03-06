package com.bebopze.jdk.concurrent.concurrentmode;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * 3.3 线程本地存储模式：没有共享，就没有伤害
 *
 * @author bebopze
 * @date 2019/6/16
 */
public class ThreadLocalModel {

    public static void main(String[] args) {

        test_1();

        test__ThreadLocal();

        // 在线程池中，正确使用 ThreadLocal  ===> try{}finally{}方案，手动释放资源
        test__2();

        test__InheritableThreadLocal();
    }


    // ------------------------------------------------------------------------------------

    private static final ThreadLocal<String> token = new ThreadLocal();
    private static final ThreadLocal<String> user = new ThreadLocal();

    private static void test_1() {

        token.set("111");
        user.set("ed");

        System.out.println(token.get());

        token.remove();
    }


    private static void test__InheritableThreadLocal() {

        InheritableThreadLocal<String> threadLocal = new InheritableThreadLocal();

        String val = "value";


        threadLocal.set(val);

        String v = threadLocal.get();

        threadLocal.remove();
    }

    /**
     * 在线程池中，正确使用 ThreadLocal  ===> try{}finally{}方案，手动释放资源
     */
    private static void test__2() {

//        ExecutorService es;
//        ThreadLocal<Object> tl;
//        es.execute(() -> {
//            Object obj = new Object();
//            //ThreadLocal 增加变量
//            tl.set(obj);
//            try {
//                // 省略业务逻辑代码
//            } finally {
//                // 手动清理 ThreadLocal
//                tl.remove();
//            }
//        });

    }

//    class Thread {
//        // 内部持有 ThreadLocalMap
//        ThreadLocal.ThreadLocalMap threadLocals;
//    }

//    class ThreadLocal<T> {
//        public T get() {
//            // 首先获取线程持有的
//            //ThreadLocalMap
//            ThreadLocalMap map = java.lang.Thread.currentThread().threadLocals;
//            // 在 ThreadLocalMap 中
//            // 查找变量
//            ThreadLocalMap.Entry e = map.getEntry(this);
//            return e.value;
//        }
//
//        static class ThreadLocalMap {
//            // 内部是数组而不是 Map
//            Entry[] table;
//
//            // 根据 ThreadLocal 查找 Entry
//            Entry getEntry(ThreadLocal key) {
//                // 省略查找逻辑
//            }
//
//            //Entry 定义
//            static class Entry extends
//                    WeakReference<ThreadLocal> {
//                Object value;
//            }
//        }
//    }


    private static void test__ThreadLocal() {

        // 不同线程执行下面代码
        // 返回的 df 是不同的
        DateFormat df = SafeDateFormat.get();
    }


    static class SafeDateFormat {
        // 定义 ThreadLocal 变量
        static final ThreadLocal<DateFormat> tl =
                ThreadLocal.withInitial(
                        () -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                );

        static DateFormat get() {
            return tl.get();
        }
    }
}
