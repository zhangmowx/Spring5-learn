package JDK_proxy;

import org.omg.CORBA.portable.InvokeHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @Auther: zhangmo
 * @Email : zhangmowx@163.com
 * @Date: 2020/2/23 17:24
 * @Description:
 */
public class JDKDynamicProxy implements InvocationHandler {

    private Object target;

    public JDKDynamicProxy(Object target) {
        this.target = target;
    }

    /**
     * 获取被代理接口实例对象
     * @param <T>
     * @return
     */
    public <T> T getProxy() {
        System.out.println("getProxy方法执行");
        return (T) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("invoke方法执行");
        System.out.println(proxy.getClass());
        System.out.println(method.getName());
        System.out.println(Arrays.toString(args));
        Object invoke = method.invoke(target, args);
        return invoke;
    }
}
