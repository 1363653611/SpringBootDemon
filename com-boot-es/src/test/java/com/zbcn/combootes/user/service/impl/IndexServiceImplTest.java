package com.zbcn.combootes.user.service.impl;

import com.zbcn.combootes.user.service.IndexService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class IndexServiceImplTest {

    @Autowired
    IndexService indexService;

    @Test
    void createIndex() {
        indexService.createIndex();
    }

    @Test
    void deleteIndex() {
        indexService.deleteIndex();
    }
}