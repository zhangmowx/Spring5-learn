package com.zm.filter;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

/**
 * @Auther: zhangmo
 * @Email : zhangmowx@163.com
 * @Date: 2020/2/28 10:55
 * @Description:
 */
public class MyTypeFilter implements TypeFilter {

    /**
     * MetadataReader metadataReader:获取当前正在扫描的类的信息
     * MetadataReaderFactory metadataReaderFactory,获取带其他任何类的信息
     *
     */
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();//获取当前类的注解信息
        ClassMetadata classMetadata = metadataReader.getClassMetadata();//获取当前扫描的类信息
        Resource resource = metadataReader.getResource();//获取当前扫描的资源信息
        String name = classMetadata.getClassName();//获取类的名字
        if(name.contains("er")) {
            return true;//如果类的名字中带有"er",则符合过滤的要求
        }
        return false;
    }
}
