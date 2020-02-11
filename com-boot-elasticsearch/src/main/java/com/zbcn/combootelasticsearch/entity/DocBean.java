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
@Document(indexName = "zbcn",type = "_doc", shards = 1, replicas = 0)
public class DocBean {

	@Id
	private Long id;

	@Field(type = FieldType.Keyword)
	private String fistCode;

	@Field(type = FieldType.Keyword)
	private String secondCode;

	@Field(type = FieldType.Text, analyzer = "ik_max_word")
	private String content;

	@Field(type = FieldType.Integer)
	private Integer type;
}
