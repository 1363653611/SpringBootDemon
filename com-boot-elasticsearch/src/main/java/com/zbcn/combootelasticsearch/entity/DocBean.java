package com.zbcn.combootelasticsearch.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@NoArgsConstructor
@AllArgsConstructor
//其中indexName代表ES索引名称，type代表文档名称。
@Document(indexName = "zbcn",type = "_doc", shards = 1, replicas = 0)
public class DocBean {

	@Id
	private Long id;

	@Field(type = FieldType.Keyword)
	private String fistCode;

	//index=true代表是否开启索引，即该字段数据是否能被搜索到,默认为true
	@Field(index = true, type = FieldType.Keyword)
	private String secondCode;
	//analyzer="ik_max_word"代表搜索的时候是如何分词匹配，ik_max_word使用IK分词器最细颗粒度分词查
	//searchAnalyzer = "ik_max_word"搜索分词的类型
	@Field(type = FieldType.Text, analyzer = "ik_max_word")
	private String content;

	@Field(type = FieldType.Integer)
	private Integer type;
}
