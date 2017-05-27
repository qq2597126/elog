package com.pxjy.elog.service;

import java.util.List;

import com.pxjy.common.paginator.IPage;
import com.pxjy.elog.domain.bo.AppInfoBo;
import com.pxjy.elog.domain.param.AppInfoParam;

/**
 * APP信息 业务接口
 * @author cg
 * @date 2017-05-24
 */
public interface IAppInfoService {

	/**
	 * 根据编号查询APP信息信息
	 * @param id
	 * @return AppInfoBo
	 * @author: cg
	 * @time: 2017-05-24
	 */
	public AppInfoBo findAppInfoById(Integer id);

	/**
	 * 分页查询APP信息列表
	 * @param param
	 * @return IPage<SchoolBo>
	 * @author: cg
	 * @time: 2017-05-24
	 */
	public IPage<AppInfoBo> findListByPage(AppInfoParam param);
	
	/**
	 * 添加APP信息
	 * @param appInfoBo void
	 * @author: cg
	 * @time: 2017-05-24
	 */
	public void doAddAppInfo(AppInfoBo appInfoBo);
	
	/**
	 * 编辑APP信息
	 * @param appInfoBo void
	 * @author: cg
	 * @time: 2017-05-24
	 */
	public void doEditAppInfo(AppInfoBo appInfoBo);
	
	/**
	 * 根据编号删除APP信息
	 * @param id void
	 * @author: cg
	 * @time: 2017-05-24
	 */
	public void doDelAppInfo(Integer id);

	/**
	 * 根据ID进行查询
	 */
	public AppInfoBo findAppinfoByAppId(AppInfoBo appInfoBo);
	/**
	 * 查询全部
	 */
	public List<AppInfoBo> findAll();
}
