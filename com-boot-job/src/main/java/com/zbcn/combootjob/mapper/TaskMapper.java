package com.zbcn.combootjob.mapper;

import com.zbcn.combootjob.entity.TaskDO;
import com.zbcn.combootjob.entity.vo.TaskVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 任务类mapper
 */
public interface TaskMapper {

	TaskDO get(Long id);

	List<TaskDO> list();

	List<TaskVO> listTaskVoByDesc(@Param("desc") String desc);

	int save(TaskDO task);

	int update(TaskDO task);

	int remove(Long id);

	int removeBatch(Long[] ids);
}
