package com.putaoteng.task7.test;

import com.putaoteng.task7.utils.other.AliStorage;
import com.putaoteng.task7.utils.other.QiniuStorage;
import com.putaoteng.task7.utils.other.Storage;
import com.putaoteng.task7.utils.other.Transfer;
import com.qiniu.common.QiniuException;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AliStorageTest {
    private QiniuStorage qiniuStorage;
    private AliStorage aliStorage;

    @Test
    public void uploadFileTest(){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("application-context.xml");

       qiniuStorage = (QiniuStorage) context.getBean("qiniuStorage");
       aliStorage = (AliStorage) context.getBean("aliStorage");


    }
}
