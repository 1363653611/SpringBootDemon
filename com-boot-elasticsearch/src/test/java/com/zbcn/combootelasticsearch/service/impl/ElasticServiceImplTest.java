package com.zbcn.combootelasticsearch.service.impl;

import com.zbcn.combootelasticsearch.entity.DocBean;
import com.zbcn.combootelasticsearch.service.IElasticService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ElasticServiceImplTest {

    @Autowired
    private IElasticService elasticService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createIndex() {
        elasticService.createIndex();
    }

    @Test
    void deleteIndex() {
    }

    @Test
    void save() {
    }

    @Test
    void saveAll() {
        List<DocBean> list = Lists.newArrayList();
        list.add(new DocBean(1L,"XX0193","XX8064","我看看",1));
        list.add(new DocBean(2L,"XX0210","XX7475","写入汉字",1));
        list.add(new DocBean(3L,"XX0257","XX8097","我的天哪，不能有错呀",1));
        elasticService.saveAll(list);
    }

    @Test
    void findAll() {
        Iterator<DocBean> all = elasticService.findAll();
        while (all.hasNext()){
            DocBean next = all.next();
            System.out.println(next);
        }
    }

    @Test
    void findByContent() {
        Page<DocBean> result = elasticService.findByContent("我看看");
        result.get().forEach(docBean -> {
            System.out.println(docBean);
        });

    }

    @Test
    void findByFirstCode() {
        Page<DocBean> result = elasticService.findByFirstCode("XX0193");
        result.get().forEach(docBean -> {
            System.out.println(docBean);
        });
    }

    @Test
    void findBySecondCode() {
        Page<DocBean> result = elasticService.findBySecondCode("XX8064");
        result.get().forEach(docBean -> {
            System.out.println(docBean);
        });
    }

    @Test
    void query() {
    }
}