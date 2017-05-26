<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>${systemName}-APP信息</title>
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
                <h4>APP信息管理</h4>
            </div>
        </div>
        <!--数据列表部分-->
        <div class="row">
            <div class="lists">
              <div class="buttons col-md-2 col-sm-12 col-xs-12">
                    <button class="btn btn-info" type="button" onclick="add()">
                        <i class="fa fa-plus"></i>
                        <span>新增</span>
                    </button>
                </div>
                <div class="query col-md-10 col-sm-12 col-xs-12 text-right">
                  <div class="form-group inlineBlock">
                      <label>APP标示</label>
                      <input id="appInfo.appId" type="text" class="formList" name="appInfo.appId" placeholder="请输入APP标示" value="${requestScope.appId }"/>
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
                  {
                        id: 'operation', title: '操作', type: 'string', columnClass: 'text-center',columnStyle: 'width: 140px;',
                        resolution: function (value, record, column, grid, dataNo, columnNo) {
                            var content = '';
                            content += '<a class="edit btn btn-xs btn-warning" data-toggle="modal" data-value="'+dataNo+'" onclick="edit('+record.id+')"><i class="fa fa-edit"></i>编辑</a>&nbsp;';
                            content += '<a class="edit btn btn-xs btn-danger" data-toggle="modal" data-value="'+dataNo+'" onclick="del('+record.id+')"><i class="fa fa-edit"></i>删除</a>&nbsp;';
                          
                            grid.parameters = new Object();
                            grid.parameters.nowPage = '';
                            grid.parameters.pageSize = '';
                            grid.parameters.appId = $("input[name='appInfo.appId']").val();
                            return content;
                        }
                    }
                    ,{ id: 'createUser', title: '创建人', type: 'string', columnClass: 'text-center' }
                    ,{ id: 'createTime', title: '创建时间', type: 'date', format:'yyyy-MM-dd hh:mm:ss', otype:'time_stamp_ms', columnClass: 'text-center' }
                    ,{ id: 'appId', title: 'APP标示', type: 'string', columnClass: 'text-center' }
                    ,{ id: 'appName', title: 'APP显示名称', type: 'string', columnClass: 'text-center' }
                    ,{ id: 'appKey', title: 'APP验签加密字符串', type: 'string', columnClass: 'text-center' }
                    ,{ id: 'sendTime', title: '间隔发送', type: 'string', columnClass: 'text-center',
                    	resolution: function (value, record, column, grid, dataNo, columnNo) {
                            if(record.sendType=='0'){
                            	return "-";
                            }
                            return value;
                        }	
                    }
                    ,{ id: 'sendType', title: '发送类型', type: 'string', columnClass: 'text-center',
                    	resolution: function (value, record, column, grid, dataNo, columnNo) {
                            if(value=='0'){
                            	return "启动时发送";
                            }else if(value=='1'){
                            	return "间隔发送";
                            }
                            return value;
                        }
                    }
                    ,{ id: 'status', title: '状态', type: 'string', columnClass: 'text-center', 
                    	resolution: function (value, record, column, grid, dataNo, columnNo) {
                            if(value=='0'){
                            	return "无效";
                            }else if(value=='1'){
                            	return "有效";
                            }
                            return value;
                        }
                    }
                ];
                var dtGridOption_2_1_2 = {
                    lang: 'zh-cn',
                    ajaxLoad: true,
                    loadURL: baseUrl + '/admin/appInfo/list4ajax',
                    columns: dtGridColumns_2_1_2,
                    gridContainer: 'dtGridContainer_2_1_2',
                    toolbarContainer: 'dtGridToolBarContainer_2_1_2',
                    tools: '',
                    pageSize: 15,
                    pageSizeLimit: [15, 25, 50]
                };
                grid = $.fn.DtGrid.init(dtGridOption_2_1_2);
                grid.parameters = new Object();
                grid.parameters.appId = appId;
                grid.parameters.nowPage = nowPage;
                grid.parameters.pageSize = pageSize;
                grid.load();
            };
            dtGrid();
        },
        search: function() {
            grid.parameters = new Object();
            grid.parameters.appId = $('input[name="appInfo.appId"]').val();
            grid.refresh(true);
        },
        getOppRowInfo: function(num) {
            var data = grid.exhibitDatas;
            return data[num];
        }
    };
    //执行构造函数
    var adminList = new AdminList();
    
    function add(){
      window.location = baseUrl + "/admin/appInfo/onAdd";
    }
    function edit(id){
      var actionUrl = baseUrl + "/admin/appInfo/onEdit";
      $("#editId").val(id);
      $("#appId").val($("input[name='appInfo.appId']").val());
      $("#commonNowPage").val(grid.pager.nowPage);
      $("#commonPageSize").val(grid.pager.pageSize);
      $("#commonForm").attr("action",actionUrl);
      $("#commonForm").submit();
    }
    function del(id){
      if (confirm('确定删除?')){
        var actionUrl = baseUrl + "/admin/appInfo/doDel";
          $("#editId").val(id);
          $("#appId").val($("input[name='appInfo.appId']").val());
          $("#commonNowPage").val(grid.pager.nowPage);
          $("#commonPageSize").val(grid.pager.pageSize);
          $("#commonForm").attr("action",actionUrl);
          $("#commonForm").submit();
        }
    }
</script>
<!-- ================== BEGIN PAGE LEVEL JS ================ -->
<script src="${base}/resources/common/script/parsley/dist/parsley.js"></script>
<!-- ================== END PAGE LEVEL JS ================== -->
</body>
</html>