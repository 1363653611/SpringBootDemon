package com.zbcn.combootelasticsearch.repository;

import com.zbcn.combootelasticsearch.entity.DocBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ElasticRepository extends ElasticsearchRepository<DocBean,Long> {

	//默认的注释
	@Query("{\"match\" : { \"content\" : \"?0\"}}")
	Page<DocBean> findByContent(String content, Pageable pageable);

	@Query("{\"match\" : { \"firstCode.keyword\" : \"?0\"}}")
	Page<DocBean> findByFirstCode(String firstCode, Pageable pageable);

	@Query("{\"bool\" : {\"must\" : {\"match\" : {\"secondCode\" : \"?0\"}}}}")
	Page<DocBean> findBySecondCode(String secondCode, Pageable pageable);

}
