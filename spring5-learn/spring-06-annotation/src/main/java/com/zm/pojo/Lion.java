package com.zm.pojo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Auther: zhangmo
 * @Email : zhangmowx@163.com
 * @Date: 2020/2/28 14:51
 * @Description:
 */
public class Lion {

    public Lion(){}

    @PostConstruct
    public void initmethod(){
        System.out.println("Lion...initmethod");
    }
    @PreDestroy
    public void destroymethod(){
        System.out.println("Lion...initmethod");
    }
}
