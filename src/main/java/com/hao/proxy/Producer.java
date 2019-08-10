package com.hao.proxy;

/**
 * @Describe com.hao.proxy
 * @Auther wenhao chen
 * @CreateDate 2019/8/6
 * @Version 1.0
 *
 * 代理类，销售&售后
 */
public class Producer implements Iproducer{

    public void saleProduct(Float money){
        System.out.println("卖出产品，得钱："+money);
    }

    public void afterService(Float money){
        System.out.println("提供售后服务，得钱“"+money);
    }
}
