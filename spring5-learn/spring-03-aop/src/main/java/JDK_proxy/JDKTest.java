package JDK_proxy;

/**
 * @Auther: zhangmo
 * @Email : zhangmowx@163.com
 * @Date: 2020/2/23 17:27
 * @Description:
 */
public class JDKTest {

    public static void main(String[] args) {

        // 保存生成的代理类的字节码文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        // jdk动态代理测试
        Subject subject = new JDKDynamicProxy(new RealSubject()).getProxy();
        subject.doWork();
        System.out.println(1234);
        System.out.println(1234);
    }
}
