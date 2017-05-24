package com.pxjy.elog.service.impl;

import javax.annotation.Resource;

import com.pxjy.common.paginator.IPage;
import com.pxjy.common.paginator.PageRequest;
import com.pxjy.elog.dao.IAppInfoDao;
import com.pxjy.elog.domain.bo.AppInfoBo;
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
		// TODO Auto-generated method stub
		appInfoDao.doAddAppInfo(appInfoBo);
	}

	@Override
	public void doEditAppInfo(AppInfoBo appInfoBo) {
		// TODO Auto-generated method stub
		appInfoDao.doEditAppInfo(appInfoBo);
	}

	@Override
	public void doDelAppInfo(Integer id) {
		// TODO Auto-generated method stub
		appInfoDao.doDelAppInfo(id);
	}

}
