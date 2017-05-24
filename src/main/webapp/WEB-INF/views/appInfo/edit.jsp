<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>${systemName}-APP信息</title>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport"/>
    <%@ include file="/jsp/common/meta.jsp"%>
    <script language="javascript">
        function checkForm(){
            return true;
        }
        function cancleClick(){
            self.location = baseUrl + '/admin/appInfo/onList';    
        }
    </script>
    <style type="text/css">
        body{ font-size:12px;}
            .l-table-edit {}
            .l-table-edit-td{ padding:4px;}
            .l-button-submit,.l-button-reset{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
            .l-verify-tip{ left:230px; top:120px;}
    </style>
</head>
</head>
<body>
    <div class="col-md-6 ui-sortable">
        <!-- begin panel -->
        <div class="panel panel-inverse">
             <div class="panel-heading">
                 <h4 class="panel-title">APP信息修改</h4>
             </div>
             <div class="panel-body panel-form">
                  <form data-parsley-validate="true" name="schoolForm" id="schoolForm" method="post" action="${base}/admin/appInfo/edit" onsubmit="return checkForm();" class="form-horizontal form-bordered" data-validate="parsley">
                    <input type="hidden" id="id" name="id" value="${requestScope.appInfo.id }"/>
                    <input type="hidden" name="nowPage" id="commonNowPage" value="${requestScope.nowPage}"/>
                    <input type="hidden" name="pageSize" id="commonPageSize" value="${requestScope.pageSize}"/>
                    <div class="form-group">
                        <label class="control-label col-md-4 col-sm-4 ui-sortable" for="appInfo.appId">APP_ID：</label>
                        <div class="col-md-6 col-sm-6 ui-sortable">
                            <input name="appId" maxlength="64" data-parsley-required="true" value="${requestScope.appInfo.appId }" type="text" id="appId" ltype="text" class="form-control parsley-validated"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-4 col-sm-4 ui-sortable" for="appInfo.appKey">APP_KEY：</label>
                        <div class="col-md-6 col-sm-6 ui-sortable">
                            <input name="appKey" maxlength="64" data-parsley-required="true" value="${requestScope.appInfo.appKey }" type="text" id="appKey" ltype="text" class="form-control parsley-validated"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-4 col-sm-4 ui-sortable" for="appInfo.appName">APP_NAME：</label>
                        <div class="col-md-6 col-sm-6 ui-sortable">
                            <input name="appName" maxlength="64" data-parsley-required="true" value="${requestScope.appInfo.appName }" type="text" id="appName" ltype="text" class="form-control parsley-validated"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-4 col-sm-4 ui-sortable" for="appInfo.createUser">CREATE_USER：</label>
                        <div class="col-md-6 col-sm-6 ui-sortable">
                            <input name="createUser" maxlength="64" data-parsley-required="true" value="${requestScope.appInfo.createUser }" type="text" id="createUser" ltype="text" class="form-control parsley-validated"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-4 col-sm-4 ui-sortable" for="appInfo.status">1有效，0无效：</label>
                        <div class="col-md-6 col-sm-6 ui-sortable">
                            <input name="status" maxlength="64" data-parsley-required="true" value="${requestScope.appInfo.status }" type="text" id="status" ltype="text" class="form-control parsley-validated"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-4 col-sm-4 ui-sortable"></label>
                        <div class="col-md-6 col-sm-6 ui-sortable">
                            <input type="submit" value="保存" id="Button1" class="btn btn-primary" /> 
                            &nbsp;&nbsp;
                            <input type="button" value="取消" class="btn btn-primary" onclick="javascript:cancleClick();"/>
                        </div>
                    </div>
               </form>
            </div>
        </div>
        <!-- end panel -->
    </div>
    <!-- ================== BEGIN PAGE LEVEL JS ================== jiaoyan -->
    <script src="${base}/resources/common/script/parsley/dist/parsley.js"></script>
    <!-- ================== END PAGE LEVEL JS ================== -->
</body>
</html>