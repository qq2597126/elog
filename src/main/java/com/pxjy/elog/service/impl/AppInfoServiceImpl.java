package com.pxjy.elog.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.pxjy.common.lang.StringUtil;
import com.pxjy.common.paginator.IPage;
import com.pxjy.common.paginator.PageRequest;
import com.pxjy.elog.dao.IAppInfoDao;
import com.pxjy.elog.dao.IEventInfoDao;
import com.pxjy.elog.domain.bo.AppInfoBo;
import com.pxjy.elog.domain.bo.EventInfoBo;
import com.pxjy.elog.domain.param.AppInfoParam;
import com.pxjy.elog.service.IAppInfoService;

/**
 * APP信息 业务接口 IAppInfoService 实现类
 * @author cg
 * @date 2017-05-24
 */
public class AppInfoServiceImpl implements IAppInfoService {

	@Resource
	private IAppInfoDao appInfoDao;
	@Resource
	private IEventInfoDao eventInfoDao;
	@Override
	public AppInfoBo findAppInfoById(Integer id) {
		// TODO Auto-generated method stub
		return appInfoDao.findAppInfoById(id);
	}

	@Override
	public IPage<AppInfoBo> findListByPage(AppInfoParam param) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(param.getPageNumber(), param.getPageSize());
		return appInfoDao.findListByPage(param, pageRequest);
	}

	@Override
	public void doAddAppInfo(AppInfoBo appInfoBo) {
		//初始化数据
		//设置APPID
		appInfoBo.setAppKey(StringUtil.random32Str());
		appInfoBo.setStatus(1);
		appInfoDao.doAddAppInfo(appInfoBo);
	}

	@Override
	public void doEditAppInfo(AppInfoBo appInfoBo) {
		appInfoDao.doEditAppInfo(appInfoBo);
	}

	@Override
	public void doDelAppInfo(Integer id) {
		//根据ID进行查询
		AppInfoBo appInfoBo = appInfoDao.findAppInfoById(id);
		if(appInfoBo!=null){
			//更新数据
			AppInfoBo aib  = new AppInfoBo();
			aib.setId(id);
			aib.setStatus(0);
			appInfoDao.doEditAppInfo(aib);
			//删除事件
			EventInfoBo eventInfoBo = new EventInfoBo();
			eventInfoBo.setAppId(appInfoBo.getAppId());
			eventInfoDao.delEventInfoBoByAppId(eventInfoBo);
		}
		
	}

	@Override
	public AppInfoBo findAppinfoByAppId(AppInfoBo appInfoBo) {
		return appInfoDao.findAppInfoByAppId(appInfoBo);
	}
	/**
	 * 查询全部
	 */
	@Override
	public List<AppInfoBo> findAll() {
		return appInfoDao.findAll();
	}

}
