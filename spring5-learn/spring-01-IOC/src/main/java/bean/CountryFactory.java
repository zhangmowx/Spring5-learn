package bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * @Auther: zhangmo
 * @Email : zhangmowx@163.com
 * @Date: 2020/2/22 15:13
 * @Description:
 */
public class CountryFactory implements FactoryBean<Country> {
    @Override
    public Country getObject() throws Exception {
        return new Country();
    }

    @Override
    public Class<?> getObjectType() {
        return Country.class;
    }

    /**
     * 设置bean对象Scope。默认是单例模式
     */
//    @Override
//    public boolean isSingleton() {
//        return false;
//    }
}
