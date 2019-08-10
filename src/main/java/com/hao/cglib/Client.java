package com.hao.cglib;

import com.hao.proxy.Iproducer;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

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
    public static void  main(String[] args) {
        final Producer pro = new Producer();
        /**
         * 动态代理：
         *      特点：使用时创建，加载
         *      作用：不需修改源码，对方法增强
         *      分类：
         *          1，基于接口
         *          2，基于子类
         *
         * 基于子类：
         *      涉及的类：Enhancer
         *      提供者：第三方cglib库
         * 创建代理对象：
         *      使用Enhancer类的create方法
         * 创建代理对象的要求：
         *      被代理的类不能是final类
         * create方法参数：
         *      Class:字节码
         *          指定被代理对象的字节码
         *          Enhancer.create(pro.getClass());
         *      Callback:提供增强的代码
         *          一般使用该接口的子接口实现类：
         *          MethodInterceptor
         *          public interface MethodInterceptor extends Callback
         *
         */

        Producer cglibPro = (Producer) Enhancer.create(pro.getClass(), new MethodInterceptor() {
            /**
             * 执行任何被代理类的方法，都会经过该方法
             * @param proxy：代理对象的引用，一般不用
             * @param method：当前执行的方法
             * @param args：当前执行方法所需的参数
             *      上三个参数，和基于接口动态代理中invoke方法的参数一致
             * @param methodProxy：当前执行方法的代理对象
             * @return
             * @throws Throwable
             */
            public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
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
        cglibPro.saleProduct(200f);

    }
}
