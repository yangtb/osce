<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>分配SP</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${contextPath}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath}/biz/css/common.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/biz/iconfont/iconfont.css">

    <script>
        var basePath = '${basePath}';
        var sq = '${sq!}';
        var idPlan = '${idPlan!}';
    </script>

    <style>
        .layui-input{
            height: 30px;
        }
        .div-sp {
            margin: 5px 0px 0px 5px;
        }
    </style>
</head>

<body class="body-my">


<div class="wrapper-content">
        <div class="layui-row">
            <div class="layui-col-xs2">
                <form class="layui-form">
                    <div class="layui-inline" style="text-align: left">
                        <div class="layui-input-inline">
                            <label style="margin-left: 20px; margin-right: 10px; font-weight: bold">分配SP列表</label>
                        </div>
                    </div>
                </form>
                <div id="sp-ed" style="margin: 20px 5px 10px 0px; border: 1px solid #dddddd; height: 371px">
                    <#--<div class="div-sp">
                        <button type="button" class="layui-btn layui-btn-sm">
                            <span class="spRealName" id="spRealName-1" data-id="1">张发三</span> <i class="layui-icon">&#x1007;</i>
                        </button>
                    </div>
                    <div class="div-sp">
                        <button type="button" class="layui-btn layui-btn-sm">
                            <span class="spRealName" id="spRealName-2" data-id="2">张发三</span> <i class="layui-icon">&#x1007;</i>
                        </button>
                    </div>
                    <div class="div-sp">
                        <button type="button" class="layui-btn layui-btn-sm">
                            <span class="spRealName" id="spRealName-3" data-id="3">张发三</span> <i class="layui-icon">&#x1007;</i>
                        </button>
                    </div>-->
                </div>

            </div>
            <div class="layui-col-xs10">
                <form class="layui-form">
                    <div class="layui-inline" style="text-align: left">
                        <#--<div class="layui-input-inline">
                            <select>
                                <option value="1">sp标签</option>
                            </select>
                        </div>-->
                    </div>
                    <div class="layui-inline" style="text-align: left">
                        <label style="margin-left: 10px; margin-right: 10px;">姓名</label>
                        <div class="layui-input-inline">
                            <input type="text" name="keywords" class="layui-input" autocomplete="off"
                                   placeholder="请输入姓名" style="height: 30px">
                        </div>
                    </div>

                    <div class="layui-inline">
                        <div class="layui-input-inline">
                            <button type="button" class="layui-btn layui-btn-sm" lay-submit lay-filter="spSearchFilter">
                                <i class="iconfont icon-query"></i> 查询
                            </button>
                            <button type="reset" class="layui-btn layui-btn-sm layui-btn-primary">
                                <i class="iconfont icon-reset"></i> 重置
                            </button>
                        </div>
                    </div>
                </form>
                <table id="spTable" lay-filter="spTableFilter">
                </table>
            </div>
        </div>

</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/biz/js/biz/plan/manage/selectSp.js"></script>

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

<script type="text/html" id="checkSpTpl">
    <input type="checkbox" name="fgActive" lay-skin="primary" value="{{d.userId}}-{{d.realName}}" lay-filter="spCheckFilter">
</script>

</body>
</html>