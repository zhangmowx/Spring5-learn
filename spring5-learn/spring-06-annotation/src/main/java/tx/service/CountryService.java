package tx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tx.dao.CountryDao;

/**
 * @Auther: zhangmo
 * @Email : zhangmowx@163.com
 * @Date: 2020/3/3 10:46
 * @Description:
 */
@Service
public class CountryService {

    @Autowired
    private CountryDao countryDao;
    @Transactional
    public void saveCountry(){
        countryDao.saveCountry();
        int i = 1/0;
    }

}
