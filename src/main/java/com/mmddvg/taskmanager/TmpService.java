package com.mmddvg.taskmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class TmpService {
    private final MyBean bean ;

    @Autowired
    private Environment env;
    @Autowired
    TmpService(MyBean bean ){
    this.bean=bean;
    }

    public String tell(){

        return  bean.sayHello() + this.env.getProperty("name");
    }
}
