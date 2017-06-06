<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>${systemName}-事件信息查询</title>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport"/>
    <%@ include file="/jsp/common/meta.jsp"%>
    <!-- 日期包 datetimepicker-->
    <link rel="stylesheet" href="${base}/resources/common/script/datetimepicker/jquery.datetimepicker.css">
    <script src="${base}/resources/common/script/datetimepicker/jquery.datetimepicker.full.min.js"></script>
    
    <!--dtgrid资源包引入-->
    <link rel="stylesheet" href="${base}/resources/common/plugins/DTgrid/jquery.dtGrid.min.css">
    <link rel="stylesheet" href="${base}/resources/assets/css/demo.css">
    <script src="${base}/resources/common/plugins/DTgrid/jquery.dtGrid.min.js"></script>
    <script src="${base}/resources/common/plugins/DTgrid/i18n/zh-cn.js"></script>
</head>
<body>
<!-- 加载器 -->
<div id="page-loader" class="fade in"><span class="spinner"></span></div>

<!-- 页面容器--start -->
<div id="page-container" class="fade page-without-sidebar">
    <!-- 页面中间内容部分--start -->
    <div id="content" class="content">
        <!-- 列表标题条 -->
        <div class="row">
            <div class="rowTitle col-md-12 col-sm-12">
                <h4>事件信息查询</h4>
            </div>
        </div>
        <!--数据列表部分-->
        <div class="row">
            <div class="lists">
              <div class="buttons col-md-2 col-sm-12 col-xs-12">
                    
                </div>
                <div class="query col-md-10 col-sm-12 col-xs-12 text-right">
                  <div class="form-group inlineBlock">
                      <label>APPID:</label>
                      <input id="appId" type="text" class="formList" name="appId" placeholder="请输入APP标示" value="${requestScope.userId }"/>
                  </div>
                  <div class="form-group inlineBlock">
                      <label>用户标示:</label>
                      <input id="userId" type="text" class="formList" name="userId" placeholder="请输入用户标示" value="${requestScope.userId }"/>
                  </div>
                  <div class="form-group inlineBlock">
                      <label>开始操作时间:</label>
                      <input id="beginTime" type="text" class="formList" name="beginTime" placeholder="开始时间"/>
                  </div>
                  <div class="form-group inlineBlock">
                      <label>结束操作时间:</label>
                      <input id="endTime" type="text" class="formList" name="endTime" placeholder="结束时间"/>
                  </div>
                  <div class="form-group inlineBlock">
                      <button class="queryBtn btn btn-success" type="button">查询</button>
                  </div>
                </div>
                <div class="tableList col-md-12" style="overflow: auto;">
                    <!--分页部分-->
                    <div id="dtGridContainer_2_1_2" class="dt-grid-container"></div>
                    <div id="dtGridToolBarContainer_2_1_2" class="dt-grid-toolbar-container"></div>
                </div>
            </div>
        </div>
        <form id="commonForm" method="post">
          <input type="hidden" name="appId" id="appId"/>
          <input type="hidden" name="userId" id="userId"/>
          <input type="hidden" name="beginTime" id="beginTime"/>
          <input type="hidden" name="endTime" id="endTime"/>
          <input type="hidden" name="nowPage" id="commonNowPage"/>
          <input type="hidden" name="pageSize" id="commonPageSize"/>
       </form>
    </div>
</div>
<script type="text/javascript">
  var nowPage   = '${requestScope.nowPage}';
  var pageSize  = '${requestScope.pageSize}';
  var userId = '${requestScope.userId }';
  var beginTime = '${requestScope.beginTime}';
  var endTime='${requestScope.endTime}';
  var appId='${requestScope.appId}';
</script>
<script>
  	//日期插件begin
     //调用dateTimePicker
    $('#beginTime').datetimepicker({
        format: 'Y-m-d H:i:s',
        hours12: false,
        lang:'ch',
        step: 5,
        todayButton:true
    });
    $('#endTime').datetimepicker({
        format: 'Y-m-d H:i:s',
        hours12: false,
        lang:'ch',
        step: 5,
        todayButton:true
    });
    //dateTimePicker汉化
    $.datetimepicker.setLocale('ch');

  //日期插件end

  var grid;
    $(function () {
        App.init();
        $("#dtGridContainer_2_1_2").on("click",".edit",function(){
            var rowinfo = adminList.getOppRowInfo($(this).attr("data-value"));
            console.log(rowinfo);
        });
    });
    function AdminList() {
        this.init();
    }
    //重写原型对象
    AdminList.prototype = {
        grid: "",
        //初始化页面
        init: function () {
            this.renderDOM();
            $(".queryBtn").click(function(){
                adminList.search();
            });
            //添加回车事件
            $(".query").on("keydown","input",function(e){
              if(e && e.keyCode==13){ // enter 键
                adminList.search();
              }
            });
        },
        //动态渲染DOM
        renderDOM: function () {
            //配置dtgrid
            var dtGrid = function () {
                //初始化dtgrid
                var dtGridColumns_2_1_2 = [
                  	 { id: 'userId', title: '操作人ID', type: 'string', columnClass: 'text-center' }
                    ,{ id: 'appBuild', title: 'APP版本号', type: 'string', columnClass:'text-center'}
                    ,{ id: 'eventKey', title: '事件标示', type: 'string', columnClass: 'text-center' }
                    ,{ id: 'osVersion', title: '版本号', type: 'string', columnClass: 'text-center' }
                    ,{ id: 'createTime', title: '操作时间',type: 'string',columnClass: 'text-center' }
                    ];
                var dtGridOption_2_1_2 = {
                    lang: 'zh-cn',
                    ajaxLoad: true,
                    loadURL: baseUrl + '/admin/eventlog/getLog',
                    columns: dtGridColumns_2_1_2,
                    gridContainer: 'dtGridContainer_2_1_2',
                    toolbarContainer: 'dtGridToolBarContainer_2_1_2',
                    tools: '',
                    pageSize: 15,
                    pageSizeLimit: [15, 25, 50]
                };
                grid = $.fn.DtGrid.init(dtGridOption_2_1_2);
                grid.parameters = new Object();
                grid.parameters.appId=appId;
                grid.parameters.userId = userId;
                grid.parameters.beginTime=beginTime;
                grid.parameters.endTime=endTime;
                grid.parameters.nowPage = nowPage;
                grid.parameters.pageSize = pageSize;
                grid.load();
            };
            dtGrid();
        },
        search: function() {
            grid.parameters = new Object();
            grid.parameters.userId = $('input[name="userId"]').val();
            grid.parameters.appId = $('input[name="appId"]').val();
            grid.parameters.beginTime = $('input[name="beginTime"]').val();
            grid.parameters.endTime = $('input[name="endTime"]').val();
            grid.refresh(true);
        },
        getOppRowInfo: function(num) {
            var data = grid.exhibitDatas;
            return data[num];
        }
    };
    //执行构造函数
    var adminList = new AdminList();
</script>
<!-- ================== BEGIN PAGE LEVEL JS ================ -->
<script src="${base}/resources/common/script/parsley/dist/parsley.js"></script>
<!-- ================== END PAGE LEVEL JS ================== -->
</body>
</html>