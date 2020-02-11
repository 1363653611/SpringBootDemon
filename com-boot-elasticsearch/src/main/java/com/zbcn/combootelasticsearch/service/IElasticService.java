package com.zbcn.combootelasticsearch.service;

import com.zbcn.combootelasticsearch.entity.DocBean;
import org.springframework.data.domain.Page;

import java.util.Iterator;
import java.util.List;

public interface IElasticService {
	void createIndex();

	void deleteIndex(String index);

	void save(DocBean docBean);

	void saveAll(List<DocBean> list);

	Iterator<DocBean> findAll();

	Page<DocBean> findByContent(String content);

	Page<DocBean> findByFirstCode(String firstCode);

	Page<DocBean> findBySecondCode(String secondCode);

	Page<DocBean> query(String key);

}
