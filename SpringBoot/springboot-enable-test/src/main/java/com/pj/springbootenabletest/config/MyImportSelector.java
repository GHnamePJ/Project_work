package com.pj.springbootenabletest.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author : Pj-Xk
 * @date : 2022-10-10 16:25
 **/
public class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"com.pj.springbootenabletest.domain.User"};
    }
}
