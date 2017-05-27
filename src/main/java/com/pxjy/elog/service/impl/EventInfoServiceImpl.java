package com.pxjy.elog.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.pxjy.common.paginator.IPage;
import com.pxjy.common.paginator.PageRequest;
import com.pxjy.elog.dao.IEventInfoDao;
import com.pxjy.elog.domain.bo.EventInfoBo;
import com.pxjy.elog.domain.param.EventInfoParam;
import com.pxjy.elog.service.IEventInfoService;

/**
 * 事件信息 业务接口 IEventInfoService 实现类
 * @author cg
 * @date 2017-05-25
 */
public class EventInfoServiceImpl implements IEventInfoService {

	@Resource
	private IEventInfoDao eventInfoDao;

	@Override
	public EventInfoBo findEventInfoById(Integer id) {
		// TODO Auto-generated method stub
		return eventInfoDao.findEventInfoById(id);
	}

	@Override
	public IPage<EventInfoBo> findListByPage(EventInfoParam param) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(param.getPageNumber(), param.getPageSize());
		return eventInfoDao.findListByPage(param, pageRequest);
	}

	@Override
	public void doAddEventInfo(EventInfoBo eventInfoBo) {
		// TODO Auto-generated method stub
		eventInfoDao.doAddEventInfo(eventInfoBo);
	}

	@Override
	public void doEditEventInfo(EventInfoBo eventInfoBo) {
		// TODO Auto-generated method stub
		eventInfoDao.doEditEventInfo(eventInfoBo);
	}

	@Override
	public void doDelEventInfo(Integer id) {
		// TODO Auto-generated method stub
		eventInfoDao.doDelEventInfo(id);
	}
	/**
	 * 根据APPID进行查询
	 */
	@Override
	public List<EventInfoBo> findEventInfoByAppId(EventInfoBo eventInfoBo) {
		return eventInfoDao.findEventInfoByAppId(eventInfoBo);
	}

	@Override
	public void doAddList(List<EventInfoBo> eventInfoBos) {
		//批量插入
		eventInfoDao.doAddList(eventInfoBos);
	}

}
