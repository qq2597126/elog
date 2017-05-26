package com.pxjy.elog.dao;

import org.apache.ibatis.annotations.Param;

import com.pxjy.common.paginator.IPage;
import com.pxjy.common.paginator.PageRequest;
import com.pxjy.elog.domain.bo.AppInfoBo;
import com.pxjy.elog.domain.param.AppInfoParam;

/**
 * APP信息 Dao 接口
 * @author cg
 * @since 2017-05-24
 */
public interface IAppInfoDao {
		
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
	 * @param appInfoParam
	 * @param pageRequest
	 * @return IPage<AppInfoBo>
	 * @author: cg
	 * @time: 2017-05-24
	 */
	public IPage<AppInfoBo> findListByPage(@Param("param")AppInfoParam appInfoParam, PageRequest pageRequest);
	
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
	 * 根据APPID进行查询
	 */
	public AppInfoBo findAppInfoByAppId(AppInfoBo appInfoBo);
	
}
