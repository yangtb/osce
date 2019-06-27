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
        var basePath = '${basePath!}';
        var sq = '${sq!}';
        var idPlan = '${idPlan!}';
    </script>

    <style>
        .layui-input{
            height: 30px;
        }
        .div-assistant {
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
                            <label style="margin-left: 20px; margin-right: 10px; font-weight: bold">已分配考官</label>
                        </div>
                    </div>
                </form>
                <div id="allAssistant" style="margin: 20px 5px 10px 0px; border: 1px solid #dddddd; height: 371px">
                    <div id="manager" class="div-sp" style="text-align: center; ">
                        <div style="text-align: center; font-weight: bold; padding-top: 10px; padding-bottom: 10px;">
                            <span>主考官</span>
                        </div>

                    </div>
                    <div id="assistant" class="div-sp" style="text-align: center; ">
                        <div style="text-align: center; font-weight: bold; padding-top: 10px; padding-bottom: 10px;">
                            <span>考官</span>
                        </div>

                    </div>
                    <div id="remote" class="div-sp" style="text-align: center; ">
                        <div style="text-align: center; font-weight: bold; padding-top: 10px; padding-bottom: 10px;">
                            <span>中控考官</span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-col-xs10">
                <form class="layui-form">
                    <div class="layui-inline" style="text-align: left">
                        <label style="margin-left: 10px; margin-right: 10px;">姓名</label>
                        <div class="layui-input-inline">
                            <input type="text" name="keywords" class="layui-input" autocomplete="off"
                                   placeholder="请输入姓名" style="height: 30px">
                        </div>
                    </div>

                    <div class="layui-inline">
                        <div class="layui-input-inline">
                            <button type="button" class="layui-btn layui-btn-sm" lay-submit lay-filter="assistantSearchFilter">
                                <i class="iconfont icon-query"></i> 查询
                            </button>
                            <button type="reset" class="layui-btn layui-btn-sm layui-btn-primary">
                                <i class="iconfont icon-reset"></i> 重置
                            </button>
                        </div>
                    </div>
                </form>
                <table id="assistantTable" lay-filter="assistantTableFilter">
                </table>
            </div>
        </div>

</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/biz/js/biz/plan/manage/selectAssistant.js"></script>

<script type="text/html" id="sexTpl">
    {{#  if(d.sex == 1){ }}
    男
    {{#  } }}
    {{#  if(d.sex == 2){ }}
    <assistantan style="color: #F581B1;">女</assistantan>
    {{#  } }}
    {{#  if(d.sex == ''){ }}
        未知
    {{#  } }}
</script>

<script type="text/html" id="idUserManagerTpl">
    <input type="checkbox" name="idUserManager" lay-skin="primary" value="{{d.userId}}-{{d.realName}}"
           lay-filter="idUserManagerCheckFilter">
</script>
<script type="text/html" id="idUserAssistantTpl">
    <input type="checkbox" name="idUserAssistant" lay-skin="primary" value="{{d.userId}}-{{d.realName}}"
           lay-filter="idUserAssistantCheckFilter">
</script>
<script type="text/html" id="idUserRemoteTpl">
    <input type="checkbox" name="idUserRemote" lay-skin="primary" value="{{d.userId}}-{{d.realName}}"
           lay-filter="idUserRemoteCheckFilter">
</script>

</body>
</html>