package com.pxjy.elog.service;

import java.util.List;

import com.pxjy.common.paginator.IPage;
import com.pxjy.elog.domain.bo.EventInfoBo;
import com.pxjy.elog.domain.param.EventInfoParam;

/**
 * 事件信息 业务接口
 * @author cg
 * @date 2017-05-25
 */
public interface IEventInfoService {

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
	 * @param param
	 * @return IPage<SchoolBo>
	 * @author: cg
	 * @time: 2017-05-25
	 */
	public IPage<EventInfoBo> findListByPage(EventInfoParam param);
	
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
	/**
	 * 根据APPID进行查询
	 */
	public List<EventInfoBo> findEventInfoByAppId(EventInfoBo eventInfoBo);
	/**
	 * 批量插入
	 */
	public void doAddList(List<EventInfoBo>  eventInfoBos);
}
