package com.zm.pojo;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @Auther: zhangmo
 * @Email : zhangmowx@163.com
 * @Date: 2020/2/28 14:45
 * @Description:
 */
public class Student implements InitializingBean, DisposableBean {

    @Override
    public void destroy() throws Exception {
        System.out.println("销毁destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("初始化init");
    }
}
