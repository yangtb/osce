<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>添加学员</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${contextPath}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath}/biz/css/common.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/biz/iconfont/iconfont.css">

    <script>
        var basePath = '${basePath}';
    </script>

    <style>
        .layui-input{
            height: 30px;
        }
    </style>
</head>

<body class="body-my">


<div class="wrapper-content">
    <form class="layui-form">
        <div class="layui-inline" style="text-align: left">
            <label style="margin-right: 10px">班级</label>
            <div class="layui-input-inline">
                <input id="idDepart" name="idDepart" type="text"
                       lay-filter="departTree" class="layui-input" style="height: 30px"/>
            </div>
        </div>
        <div class="layui-inline" style="text-align: left">
            <label style="margin-left: 10px; margin-right: 10px;">姓名</label>
            <div class="layui-input-inline">
                <input type="text" name="realName" class="layui-input" autocomplete="off"
                       placeholder="请输入姓名" style="height: 30px">
            </div>
        </div>

        <div class="layui-inline">
            <div class="layui-input-inline">
                <button type="button" class="layui-btn layui-btn-sm" lay-submit lay-filter="selectStudentSearchFilter">
                    <i class="iconfont icon-query"></i> 查询
                </button>
                <button type="reset" class="layui-btn layui-btn-sm layui-btn-primary">
                    <i class="iconfont icon-reset"></i> 重置
                </button>
            </div>
        </div>
    </form>

    <table id="selectStudentTable" lay-filter="selectStudentTableFilter">
    </table>

    <div class="layui-row" style="text-align: right">
        <button type="button" class="layui-btn layui-btn-sm" id="selectStudent">
            <i class="iconfont icon-query"></i> 确定
        </button>
    </div>
</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/biz/js/biz/monitor/addStudentController.js"></script>

<script type="text/html" id="sexTpl">
    {{#  if(d.sex == 1){ }}
    男
    {{#  } }}
    {{#  if(d.sex == 2){ }}
    <span style="color: #F581B1;">女</span>
    {{#  } }}
    {{#  if(d.sex == ''){ }}
        未知
    {{#  } }}
</script>

</body>
</html>