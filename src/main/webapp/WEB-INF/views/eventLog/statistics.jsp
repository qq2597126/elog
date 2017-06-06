<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>${systemName}-事件信息统计</title>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport"/>
    <%@ include file="/jsp/common/meta.jsp"%>
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
                <h4>事件信息统计</h4>
            </div>
        </div>
        <!--数据列表部分-->
        <div class="row">
            <div class="lists">
                <div class="tableList col-md-12" style="overflow: auto;">
                    <!--分页部分-->
                    <div id="dtGridContainer_2_1_2" class="dt-grid-container"></div>
                    <div id="dtGridToolBarContainer_2_1_2" class="dt-grid-toolbar-container"></div>
                </div>
            </div>
        </div>
        <form id="commonForm" method="post">
          <input type="hidden" name="id" id="editId"/>
          <input type="hidden" name="appId" id="appId"/>
          <input type="hidden" name="nowPage" id="commonNowPage"/>
          <input type="hidden" name="pageSize" id="commonPageSize"/>
       </form>
    </div>
</div>
<script type="text/javascript">
  var nowPage   = '${requestScope.nowPage}';
  var pageSize  = '${requestScope.pageSize}';
  var appId = '${requestScope.appId }';
</script>
<script>
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
        },
        //动态渲染DOM
        renderDOM: function () {
            //配置dtgrid
            var dtGrid = function () {
                //初始化dtgrid
                var dtGridColumns_2_1_2 = [
                 	{ id: 'appId', title: 'APP标识', type: 'string', columnClass: 'text-center' }
                    ,{ id: 'eventKey', title: '事件标识', type: 'string', columnClass: 'text-center' }
                    ,{ id: 'createTime', title: '操作时间', type: 'string', columnClass: 'text-center' }
                    ,{ id: 'count', title: '统计个数',type: 'string', columnClass: 'text-center' }
                    
                ];
                var dtGridOption_2_1_2 = {
                    lang: 'zh-cn',
                    ajaxLoad: true,
                    loadURL: baseUrl + '/admin/eventlog/getStatisticsLog',
                    columns: dtGridColumns_2_1_2,
                    gridContainer: 'dtGridContainer_2_1_2',
                   /*  toolbarContainer: 'dtGridToolBarContainer_2_1_2',
                    tools: '',
                    pageSize: 15,
                    pageSizeLimit: [15, 25, 50] */
                };
                grid = $.fn.DtGrid.init(dtGridOption_2_1_2);
                grid.parameters = new Object();
                grid.parameters.appId = appId;
                grid.parameters.nowPage = nowPage;
                grid.parameters.pageSize = pageSize;
                grid.load();
            };
            dtGrid();
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