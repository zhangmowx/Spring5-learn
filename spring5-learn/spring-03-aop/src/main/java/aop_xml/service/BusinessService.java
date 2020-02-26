package aop_xml.service;

/**
 * @Auther: zhangmo
 * @Email : zhangmowx@163.com
 * @Date: 2020/2/26 09:45
 * @Description:
 */
public interface BusinessService {

    void beforeFunc();

    String afterReturnFunc();

    void afterThrowFunc()throws Exception;

    void afterFunc();

    void aroundFunc();

    String adviceFunc(String name,int age);
}
