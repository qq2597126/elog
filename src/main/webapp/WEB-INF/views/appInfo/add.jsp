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
<body>
    <div class="col-md-6 ui-sortable">
        <!-- begin panel -->
        <div class="panel panel-inverse">
             <div class="panel-heading">
                 <h4 class="panel-title">APP信息添加</h4>
             </div>
             <div class="panel-body panel-form">
                  <form data-parsley-validate="true" name="schoolForm" id="schoolForm" method="post" action="${base}/admin/appInfo/add" onsubmit="return checkForm();" class="form-horizontal form-bordered" data-validate="parsley">
                    <div class="form-group">
                        <label class="control-label col-md-4 col-sm-4 ui-sortable" for="name"><span style="color: red">*</span>APP_ID：</label>
                        <div class="col-md-6 col-sm-6 ui-sortable">
                            <input name="appId" maxlength="64" data-parsley-required="true" type="text" id="appId" ltype="text" class="form-control parsley-validated"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-4 col-sm-4 ui-sortable" for="name"><span style="color: red">*</span>APP_KEY：</label>
                        <div class="col-md-6 col-sm-6 ui-sortable">
                            <input name="appKey" maxlength="64" data-parsley-required="true" type="text" id="appKey" ltype="text" class="form-control parsley-validated"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-4 col-sm-4 ui-sortable" for="name"><span style="color: red">*</span>APP_NAME：</label>
                        <div class="col-md-6 col-sm-6 ui-sortable">
                            <input name="appName" maxlength="64" data-parsley-required="true" type="text" id="appName" ltype="text" class="form-control parsley-validated"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-4 col-sm-4 ui-sortable" for="name"><span style="color: red">*</span>CREATE_USER：</label>
                        <div class="col-md-6 col-sm-6 ui-sortable">
                            <input name="createUser" maxlength="64" data-parsley-required="true" type="text" id="createUser" ltype="text" class="form-control parsley-validated"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-4 col-sm-4 ui-sortable" for="name"><span style="color: red">*</span>1有效，0无效：</label>
                        <div class="col-md-6 col-sm-6 ui-sortable">
                            <input name="status" maxlength="64" data-parsley-required="true" type="text" id="status" ltype="text" class="form-control parsley-validated"/>
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
    <!-- ================== BEGIN PAGE LEVEL JS ================== -->
    <script src="${base}/resources/common/script/parsley/dist/parsley.js"></script>
    <!-- ================== END PAGE LEVEL JS ================== -->
</body>
</html>