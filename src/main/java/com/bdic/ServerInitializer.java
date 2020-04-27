package com.bdic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Component
public class ServerInitializer implements ApplicationRunner {
    @Autowired
    private QARepository repository;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        Iterator<QA> byQuestion = repository.findByQuestion("test sql").iterator();
        if (!byQuestion.hasNext()) {
            QA qa = new QA();
            qa.setQuestion("test sql");
            qa.setAnswer("test sql successful!");
            repository.save(qa);
            System.out.println("initial database entry");
        }
        System.out.println("entry already exist");
    }
}
