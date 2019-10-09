package com.zbcn.combootjpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name="t_jpa_user")//自定义表名
public class User implements Serializable {

	private static final long serialVersionUID = 2954803246419778193L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * 编码
	 */
	private String code;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 状态 1启用 0 停用
	 */
	private String status;

	/**
	 * 创建时间
	 */
	@CreatedDate //自动创建
	private Date gmtCreate;

	/**
	 * 修改时间
	 */
	@LastModifiedDate //有修改时 会自动时间
	private Date gmtModified;

}
