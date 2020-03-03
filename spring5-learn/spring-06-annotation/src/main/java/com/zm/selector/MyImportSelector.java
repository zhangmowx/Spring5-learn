package com.zm.selector;

import com.zm.pojo.Employee;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.MultiValueMap;

import java.util.Map;
import java.util.Set;

/**
 * @Auther: zhangmo
 * @Email : zhangmowx@163.com
 * @Date: 2020/2/28 13:44
 * @Description:
 */
public class MyImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

        //将Employee类注入
        return new String[]{Employee.class.getName()};
    }
}
