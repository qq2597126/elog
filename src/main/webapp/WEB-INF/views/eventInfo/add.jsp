<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>${systemName}-事件信息</title>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport"/>
    <%@ include file="/jsp/common/meta.jsp"%>
    <script language="javascript">
        function checkForm(){
            return true;
        }
        function cancleClick(){
            self.location = baseUrl + '/admin/eventInfo/onList';    
        }
        $(function(){
        	//填充下拉列表
        	$.ajax({
        		url:baseUrl + '/admin/appInfo/getAll',
        		type:"post",
        		dataType:"json",
        		success:function(msg){
        			if(msg.status=="1"){
        				var da = msg.data;
        				if(da!=null){
        					for (var i = 0; i < da.length; i++) {
	        					$("#appId").append('<option value="'+da[i].appId+'">APP_NAME：'+da[i].appName+'&nbsp;&nbsp;&nbsp;APP_ID：'+da[i].appId+'</option>');
							}
        				}
        			}else{
        				alert(msg.errorMessage);
        			}
        			//设置默认值
        			 var val = '${requestScope.eventInfo.appId}';
        			 console.info(val);
     				$("#appId").val(val);
        		}
        	});
        })
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
                 <h4 class="panel-title">事件信息添加</h4>
             </div>
             <div class="panel-body panel-form">
                  <form data-parsley-validate="true" name="schoolForm" id="schoolForm" method="post" action="${base}/admin/eventInfo/add" onsubmit="return checkForm();" class="form-horizontal form-bordered" data-validate="parsley">
                    <div class="form-group">
                        <label class="control-label col-md-4 col-sm-4 ui-sortable" for="eventInfo.appId">APP标识：</label>
                        <div class="col-md-6 col-sm-6 ui-sortable">
<%--                             <input name="appId" maxlength="64" data-parsley-required="true" value="${requestScope.eventInfo.appId }" type="text" id="appId" ltype="text" class="form-control parsley-validated"/>
 --%>                   	<select id="appId" name="appId" class="form-control parsley-validated">
 							</select>     
 						</div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-4 col-sm-4 ui-sortable" for="name"><span style="color: red">*</span>事件标识：</label>
                        <div class="col-md-6 col-sm-6 ui-sortable">
                            <input name="eventKey" maxlength="64" data-parsley-required="true" type="text" id="eventKey" ltype="text" class="form-control parsley-validated"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-4 col-sm-4 ui-sortable" for="name"><span style="color: red">*</span>事件名称：</label>
                        <div class="col-md-6 col-sm-6 ui-sortable">
                            <input name="eventName" maxlength="64" data-parsley-required="true" type="text" id="eventName" ltype="text" class="form-control parsley-validated"/>
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