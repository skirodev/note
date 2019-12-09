package com.junzijian.demo.jdk8.lambda;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.HashMap;
import java.util.function.*;

/**
 * Lambda
 *
 * @author junzijian
 * @date 2019/12/9
 * -
 * @link 从入门到入土：Lambda完整学习指南  https://mp.weixin.qq.com/s/ogcRRWBE8CtOY5l6kcQvhg
 */
public class Lumbda {

    public static void main(String[] args) {

        // JDK8 Lumbda语法
        testJdk8Lumbda();

        // Java 内置四大核心函数式接口
        testJdkFunctionInterface();

        // 自定义 函数式接口
        testMyFunctionInterfaceLumbda();

        // 方法引用
        testMethodReference();

        // 构造器引用
        testConstructorReference();

        //
        testCollectionFunction();
    }

    private static void testCollectionFunction() {

        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "我");
        map.put(2, "拒绝");
        map.put(3, "996");

        map.forEach((key, value) -> System.out.println(key + "=" + value));
        map.merge(1, "和你", (v1, v2) -> v1 + v2);
        map.forEach((key, value) -> System.out.println(key + "=" + value));
    }


    /**
     * 方法引用
     * -
     * - 当要传递给Lambda体内的操作，已经有实现的方法了，就可以使用方法引用了！
     * -
     * - 方法引用使用的前提条件是什么呢？   // 参数列表 + 返回值 完全一致
     * -
     * -   方法引用 所引用的方法的参数列表 必须要和 函数式接口中抽象方法的参数列表相同（完全一致）。  // 参数列表 完全一致
     * -   方法引用 所引用的方法的的返回值 必须要和 函数式接口中抽象方法的返回值相同（完全一致）。    // 返回值  完全一致
     * -
     * -
     * - 方法引用一般有三种格式：
     * -
     * -   实例对象名::实例方法名
     * -   类名::静态方法名
     * -   类名::实例方法名
     * -
     * -
     * - 注意：
     * -
     * -  2和3的区别：
     * -
     * -    若Lambda 的参数列表的第一个参数，是实例方法的调用者，第二个参数(或无参)是实例方法的参数时
     * -
     * -    格式：类名::实例方法名
     * -
     * -
     * - 方法引用：使用操作符 “::” 将方法名和对象或类的名字分隔开来。
     */
    private static void testMethodReference() {

        // ------对象::实例方法
        System.out.println("------------------------对象::实例方法");

        PrintStream ps = System.out;

        Consumer<String> con = (str) -> ps.println(str);
        con.accept("Hello World！");

        out("=====================");

        // 对象::实例方法
        Consumer<String> con2 = ps::println;
        con2.accept("Hello Java8！");


        // ------类::静态方法
        System.out.println("------------------------类::静态方法");

        BiFunction<Double, Double, Double> fun = (x, y) -> Math.max(x, y);
        out(fun.apply(1.5, 22.2));

        out("=====================");

        // 类::静态方法
        BiFunction<Double, Double, Double> fun2 = Math::max;
        out(fun2.apply(1.2, 1.5));


        // ------类::实例方法
        System.out.println("------------------------类::实例方法");

        /**
         * -  若Lambda 的参数列表的第一个参数，是实例方法的调用者，第二个参数(或无参)是实例方法的参数时
         *
         *    (x, y) -> x.equals(y)   ->  String::equals      // 格式： 类名::实例方法名
         *
         *
         *     x: 是第一个参数             ->    是 调用者(实例对象) 自身
         *
         *     y: 第二个参数(或无参)       ->    是 实例对象x 所调用的方法 equals 的参数
         *
         */
        BiPredicate<String, String> biPredicate = (x, y) -> x.equals(y);
        out(biPredicate.test("a", "b"));

        out("=====================");

        // 类::实例方法
        BiPredicate<String, String> biPredicate2 = String::equals;
        out(biPredicate2.test("a", "b"));

    }

    /**
     * - 构造器引用
     * -
     * - 构造器使用的前提是什么？       // 参数列表 一致！！！
     * -
     * -    构造器参数列表 要与 接口中抽象方法的参数列表 一致！        // 参数列表 一致！！！
     * -
     * - 语法格式：  类名::new
     */
    private static void testConstructorReference() {


    }


    /**
     * JDK8 Lumbda语法
     */
    private static void testJdk8Lumbda() {

        // 实现函数接口(FunctionalInterface)
        Runnable runnable = () -> System.out.println("语法格式一：无参，无返回值，Lambda 体只需一条语句");
        // 执行函数接口
        runnable.run();


        // 实现
        Consumer<String> consumer1 = (x) -> System.out.println("语法格式二：Lambda 需要一个参数：" + x);
        // 调用
        consumer1.accept("x");


        // impl
        Consumer<String> consumer2 = x -> System.out.println("语法格式三：Lambda 只需要一个参数时，参数的小括号可以省略：" + x);
        // invoke
        consumer2.accept("x");


        // impl
        Comparator<Integer> comparator1 = (x, y) -> {
            System.out.println("语法格式四：Lambda 需要两个参数，并且有返回值");
            return Integer.compare(x, y);
        };
        // invoke
        int result4 = comparator1.compare(1, 2);


        // 语法格式五：当 Lambda 体只有一条语句时，return 与 大括号 可以省略
        Comparator<Integer> comparator2 = (x, y) -> Integer.compare(x, y);
        int result5 = comparator2.compare(1, 2);


        // 语法格式六：Lambda 表达式的参数列表的数据类型可以省略不写，因为JVM编译器通过上下文推断出，数据类型，即“类型推断”。
        Comparator<Integer> comparator = (Integer x, Integer y) -> { // Integer 类型可以省略
            System.out.println("函数接口的实现");
            return Integer.compare(x, y);
        };
        int result6_1 = comparator.compare(1, 2);

        LongBinaryOperator longBinaryOperator1 = (long x, long y) -> x + y;
        long result6_2 = longBinaryOperator1.applyAsLong(1, 2);

        LongBinaryOperator longBinaryOperator2 = (x, y) -> x + y;  // 类型推断
        long result6_3 = longBinaryOperator2.applyAsLong(1, 2);


        System.out.println("-----------------------------------");
    }


    /**
     * Java 内置四大核心函数式接口
     */
    private static void testJdkFunctionInterface() {

        /**
         * Consumer<T> 消费型接口
         *
         * void accept(T t);
         */
        Consumer<String> consumer = x -> System.out.println("Consumer accept : " + x);
        consumer.accept("消费型接口");


        /**
         * Supplier<T> 供给型接口
         *
         * T get();
         */
        Supplier<String> supplier = () -> "Supplier get";
        String supplierResult = supplier.get();
        out(supplierResult);


        /**
         * Function<T, R> 函数型接口
         *
         * R apply(T t);
         */
        Function<Integer, String> function = (Integer x) -> "Function apply : " + x;
        String applyResult = function.apply(123);
        out(applyResult);


        /**
         * Predicate<T> 断定型接口
         *
         * boolean test(T t);
         */
        Predicate<String> predicate = (String x) -> x.contains("Predicate");
        boolean predicateTestResult = predicate.test("Predicate test");
        out(predicateTestResult);


        System.out.println("-----------------------------------");
    }


    /**
     * 自定义函数式接口
     */
    private static void testMyFunctionInterfaceLumbda() {

        // 完整写法
        MyFunctionInterface myFunctionInterface1 = (String name) -> {
            return "hello : " + name;
        };
        System.out.println(myFunctionInterface1.sayHello("二狗 二狗 听到请回答。。。。。"));


        // 简化：省略 -> 参数类型、参数括号、return、方法体大括号
        MyFunctionInterface myFunctionInterface2 = name -> "hello : " + name;
        System.out.println(myFunctionInterface2.sayHello("二狗子 出来玩撒。。。。。"));


        System.out.println("-----------------------------------");
    }


    private static void out(Object x) {
        System.out.println(x);
    }
}
