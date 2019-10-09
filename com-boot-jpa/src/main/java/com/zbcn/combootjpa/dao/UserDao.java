package com.zbcn.combootjpa.dao;

import com.zbcn.combootjpa.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

/**
 * 资源类
 * 这里直接继承了PagingAndSortingRepository，其本身实现了分页功能，
 * 还可以按需要继承CrudRepository或者JpaRepository等。
 * 而且，占位符为：?+具体的参数索引值
 */
public interface UserDao extends PagingAndSortingRepository<User, Long> {

	Optional<User> findById(Long id);

	//使用自动命名规则进行查询服务
	List<User> findByCodeAndName(String code, String name);

	@Query(value = "select * from t_jpa_user where code = ?1", nativeQuery = true)
	List<User> queryByCode(String code);
	//分页
	Page<User> findByCode(String code, Pageable pageable);

}
