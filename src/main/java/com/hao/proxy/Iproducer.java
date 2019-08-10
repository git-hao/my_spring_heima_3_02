package com.hao.proxy;

/**
 * @Describe com.hao.proxy
 * @Auther wenhao chen
 * @CreateDate 2019/8/6
 * @Version 1.0
 *
 * 厂家类，要求代理商必须有销售和售后两个方法
 */
public interface Iproducer {

    public void saleProduct(Float money);

    public void afterService(Float money);
}
