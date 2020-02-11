package com.zbcn.combootelasticsearch.controller;

import com.zbcn.combootelasticsearch.entity.DocBean;
import com.zbcn.combootelasticsearch.service.IElasticService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/elastic")
public class ElasticController {

	@Autowired
	private IElasticService elasticService;

	@GetMapping("/init")
	public void init(){
		elasticService.createIndex();
		List<DocBean> list =new ArrayList<>();
		list.add(new DocBean(1L,"XX0193","XX8064","我看看",1));
		list.add(new DocBean(2L,"XX0210","XX7475","写入汉字",1));
		list.add(new DocBean(3L,"XX0257","XX8097","我的天哪，不能有错呀",1));
		elasticService.saveAll(list);

	}

	@GetMapping("/all")
	public Iterator<DocBean> all(){
		return elasticService.findAll();
	}

}
