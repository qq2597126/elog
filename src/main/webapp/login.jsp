<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>${systemName}</title>
    <link rel="shortcut icon" type=image/x-icon href="${base}/resources/assets/images/favicon.ico">
    <link rel="stylesheet" href="${base }/resources/common/script/bootstrap/css/bootstrap.min.css">
    <script src="${base }/resources/common/script/jquery-1.11.3.min.js"></script>
    <!--[if !IE]>-->
    <link rel="stylesheet" href="${base }/resources/assets/css/common.css">
    <link rel="stylesheet" href="${base }/resources/assets/css/account/login.css">
    <!--<![endif]-->
    
    <script>
		$(function (){
			$("#loginName").focus();
		});
	</script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="layout">
            <!--logo部分-->
            <div class="logo">
                <a href="#">
                    <img src="${base }/resources/assets/images/logo.png" alt="朴新教育">
                </a>
                <div class="title">
                    <img src="${base }/resources/assets/images/title.png" alt="朴新系统管理平台">
                </div>
            </div>
            <!--表单部分-->
            <div class="form-group">
            	<form id="loginform" action="${base }/login/login" method="post">
	                <div class="username">
	                    <input type="text" name="loginName" id="loginName" placeholder="用户名/邮箱" value="admin@pxjy.com">
	                    <span class="errInfo userErr"></span>
	                </div>
	                <div class="password">
	                    <input type="password" id="password" name="password" placeholder="密码" value="admin">
	                    <span class="errInfo pwErr" style="color:white;">${errorStr}</span>
	                </div>
	                <div class="signUp">
	                    <input type="submit" value="登录">
	                </div>
                </form>
            </div>
            <div class="footer">
                    <p>Copyright&copy;&nbsp;${companyName}</p>
                    <p>All right reserved</p>
            </div>
        </div>
    </div>
</div>
<script src="${base }/resources/common/script/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>