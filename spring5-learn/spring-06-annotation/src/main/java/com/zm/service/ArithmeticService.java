package com.zm.service;

import org.springframework.stereotype.Service;

/**
 * @Auther: zhangmo
 * @Email : zhangmowx@163.com
 * @Date: 2020/3/3 09:25
 * @Description:
 */
@Service
public class ArithmeticService {

    public int addition(int a,int b){
        int c = a+b;
        System.out.println("计算结果为"+c);
        return c;
    }

    public int division(int a,int b){
        int c = a/b;
        System.out.println("计算结果为"+c);
        return c;
    }

}
