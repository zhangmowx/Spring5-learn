package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import service.AccountService;
import Dao.AccountDao;

/**
 * @Auther: zhangmo
 * @Email : zhangmowx@163.com
 * @Date: 2020/2/26 21:04
 * @Description:
 */
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao dao;

    @Override
    public void tran(Long outid, Long inid, float money) {
        dao.transOut(outid,money);
        int i = 1/0; //模拟抛出异常
        dao.tranIn(inid,money);
    }
}
