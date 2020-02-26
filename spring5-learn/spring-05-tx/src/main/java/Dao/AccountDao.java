package Dao;

/**
 * @Auther: zhangmo
 * @Email : zhangmowx@163.com
 * @Date: 2020/2/26 21:04
 * @Description:
 */
public interface AccountDao {

    void transOut(Long outid,float money);
    void  tranIn(Long inid,float money);
}
