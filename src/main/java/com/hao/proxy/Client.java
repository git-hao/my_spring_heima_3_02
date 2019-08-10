package com.hao.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Describe com.hao.proxy
 * @Auther wenhao chen
 * @CreateDate 2019/8/6
 * @Version 1.0
 *
 * 消费者类，花钱买货
 */
public class Client {
    public static void  main(String[] args){
        final Producer pro = new Producer();

        /**
         * 动态代理：
         *      特点：使用时创建，加载
         *      作用：不需修改源码，对方法增强
         *      分类：
         *          1，基于接口
         *          2，基于子类
         *
         * 基于接口：
         *      涉及的类：Proxy
         *      提供者：JDK官方
         * 创建代理对象：
         *      Proxy类中的newProxyInstance方法
         * 创建代理对象的要求：
         *      被代理的类最少实现一个接口，没有则不能使用
         * newProxyInstance参数：
         *      ClassLoader:类加载器
         *          加载代理对象字节码，和被代理对象使用相同的类加载器，固定写法
         *          Proxy.newProxyInstance(pro.getClass().getClassLoader())
         *      Class[]:字节码数组
         *          让代理对象和被代理对象有相同的方法，固定写法
         *          Proxy.newProxyInstance(pro.getClass().getClassLoader(),pro.getClass().getInterfaces())
         *      InvocationHandler；
         *          提供增强的代码，让我们写如何代理，一般是写一个该接口的实现类，通常情况下是要给匿名内部类（非必须）
         *
         */
        Iproducer proxyProducer = (Iproducer) Proxy.newProxyInstance(pro.getClass().getClassLoader(), pro.getClass().getInterfaces(),
                new InvocationHandler() {
                    /**
                     * 执行被代理对象的任何接口方法，都会经过该方法
                     * @param proxy ：代理对象的引用，一般不用
                     * @param method：当前执行的方法
                     * @param args：当前执行方法所需的参数
                     * @return     ：和被代理对象方法有相同的返回值
                     * @throws Throwable
                     *
                     * 匿名内部类只能访问final型变量外部成员变量
                     */
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //提供增强代码，使Producer得钱只有80%
                        //1，获取方法执行的参数
                        Float moeny = (Float) args[0];
                        //2，判断当前方法是否是saleProduct
                        if ("saleProduct".equals(method.getName())){
                            Object result = method.invoke(pro,moeny*0.8f);
                            return result;
                        }
                        return null;
                    }
                });
        proxyProducer.saleProduct(100f);
    }

}
