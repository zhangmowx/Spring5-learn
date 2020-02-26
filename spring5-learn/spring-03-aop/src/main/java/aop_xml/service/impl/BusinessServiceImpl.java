package aop_xml.service.impl;

import aop_xml.service.BusinessService;

/**
 * @Auther: zhangmo
 * @Email : zhangmowx@163.com
 * @Date: 2020/2/26 09:46
 * @Description:
 */
public class BusinessServiceImpl implements BusinessService {


    @Override
    public void beforeFunc() {
        System.out.println("BusinessServiceImpl beforeFunc");
    }

    @Override
    public String afterReturnFunc() {
        System.out.println("BusinessServiceImpl afterReturnFunc");
        return "Hello AOP";
    }

    @Override
    public void afterThrowFunc() throws  Exception{
        System.out.println("BusinessServiceImpl afterThrowFunc");
        int i=1/0;
    }

    @Override
    public void afterFunc() {
        System.out.println("BusinessServiceImpl afterFunc");
    }

    @Override
    public void aroundFunc() {
        System.out.println("BusinessServiceImpl AroundFunc");
    }

    @Override
    public String adviceFunc(String name, int age) {
        System.out.println("BusinessServiceImpl adviceFunc 参数name："+name +",age:"+age);
        return null;
    }
}
