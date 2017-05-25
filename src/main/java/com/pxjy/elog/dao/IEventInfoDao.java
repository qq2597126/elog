package com.pxjy.elog.dao;

import org.apache.ibatis.annotations.Param;

import com.pxjy.common.paginator.IPage;
import com.pxjy.common.paginator.PageRequest;
import com.pxjy.elog.domain.bo.EventInfoBo;
import com.pxjy.elog.domain.param.EventInfoParam;

/**
 * 事件信息 Dao 接口
 * @author cg
 * @since 2017-05-25
 */
public interface IEventInfoDao {
		
	/**
	 * 根据编号查询事件信息信息
	 * @param id
	 * @return EventInfoBo
	 * @author: cg
	 * @time: 2017-05-25
	 */
	public EventInfoBo findEventInfoById(Integer id);

	/**
	 * 分页查询事件信息列表
	 * @param eventInfoParam
	 * @param pageRequest
	 * @return IPage<EventInfoBo>
	 * @author: cg
	 * @time: 2017-05-25
	 */
	public IPage<EventInfoBo> findListByPage(@Param("param")EventInfoParam eventInfoParam, PageRequest pageRequest);
	
	/**
	 * 添加事件信息
	 * @param eventInfoBo void
	 * @author: cg
	 * @time: 2017-05-25
	 */
	public void doAddEventInfo(EventInfoBo eventInfoBo);
		
	/**
	 * 编辑事件信息
	 * @param eventInfoBo void
	 * @author: cg
	 * @time: 2017-05-25
	 */
	public void doEditEventInfo(EventInfoBo eventInfoBo);
	
	/**
	 * 根据编号删除事件信息
	 * @param id void
	 * @author: cg
	 * @time: 2017-05-25
	 */
	public void doDelEventInfo(Integer id);
	
}
