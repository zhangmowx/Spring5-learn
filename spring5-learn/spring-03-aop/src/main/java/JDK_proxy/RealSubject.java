package JDK_proxy;

/**
 * @Auther: zhangmo
 * @Email : zhangmowx@163.com
 * @Date: 2020/2/23 17:23
 * @Description:
 */
public class RealSubject implements  Subject{
    @Override
    public void doWork() {
        System.out.println("RealSubject类的doWork方法");
    }
}
