<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>考场监控</title>
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
        .right-label {
            color: #656565;
        }

        .left-label {
            display:inline-block; width:80px;
        }
    </style>
</head>

<body>

<div class="wrapper-content" style="margin-top: -10px;">

    <div class="layui-tab layui-tab-brief" lay-filter="monitorFilter">
        <ul class="layui-tab-title">
            <li class="layui-this">站点监控</li>
            <li>学员管控</li>
            <li class="layui-nav-item layui-hide-xs" style="float: right;" lay-unselect>
                <a href="javascript:;" layadmin-event="fullscreen">
                    <i class="layui-icon layui-icon-screen-full"></i>
                </a>
            </li>
        </ul>
        <div class="layui-tab-content">

            <div class="layui-tab-item layui-show">

                <#--指示条区域-->
                <div class="layui-row">
                    <div class="layui-col-xs3">
                        <button type="button" class="layui-btn layui-btn-sm layui-btn-warm layui-btn-fluid" style="border-radius:0px;">叫号待认证</button>
                    </div>
                    <div class="layui-col-xs3">
                        <button type="button" class="layui-btn layui-btn-sm layui-btn-fluid" style="background-color: #5FB878; border-radius:0px;">叫号已认证</button>
                    </div>
                    <div class="layui-col-xs3">
                        <button type="button" class="layui-btn layui-btn-sm layui-btn-normal layui-btn-fluid" style="border-radius:0px;">考试考核</button>
                    </div>
                    <div class="layui-col-xs3">
                        <button type="button" class="layui-btn layui-btn-sm layui-btn-fluid" style="background-color: #c2c2c2; border-radius:0px;">空闲</button>
                    </div>
                </div>

                <#--轮播区域-->
                <div class="layui-carousel" id="test1" lay-filter="test1">
                    <div carousel-item="" id="monitorCard">

                    </div>
                </div>
            </div>


            <div class="layui-tab-item" style="margin-top: -20px;">
                <div class="layui-tab layui-tab-brief" lay-filter="stuTabFilter">
                    <ul class="layui-tab-title">
                        <li class="layui-this">待考学员</li>
                        <li>场内学员</li>
                        <li>结束学员</li>
                    </ul>
                    <div class="layui-tab-content">
                        <div class="layui-tab-item layui-show">
                            <table class="layui-hide" id="toBeExaminedTable" lay-filter="toBeExaminedTableFilter"></table>

                            </table>
                        </div>
                        <div class="layui-tab-item">
                            <table class="layui-hide" id="onSiteTable" lay-filter="onSiteTableFilter"></table>

                            </table>
                        </div>
                        <div class="layui-tab-item">
                            <table class="layui-hide" id="endTable" lay-filter="endTableFilter"></table>

                            </table>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>

</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/biz/js/biz/monitor/areaMonitorController.js"></script>

<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm layui-btn-normal" id="addStudent" lay-event="addStudent"><i class="layui-icon">&#xe654;</i>添加学员</button>
        <button class="layui-btn layui-btn-sm layui-btn-danger" id="delStudent" lay-event="delStudent"><i class="layui-icon">&#xe640;</i>删除学员</button>
    </div>
</script>

<script type="text/html" id="endBar">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm layui-btn-normal" id="recoveryTest" lay-event="recoveryTest"><i class="iconfont icon-huifu"></i> 恢复考试</button>
    </div>
</script>

<script type="text/html" id="endStatusTpl">
    {{#  if(d.endStatus == 1){ }}
    <button type="button" class="layui-btn layui-btn-xs" style="background-color: #5FB878">正常结束</button>
    {{#  } else { }}
    <button type="button" class="layui-btn layui-btn-xs layui-btn-danger">缺考</button>
    {{#  } }}
</script>

<script type="text/html" id="toBeExaminedBar">
        <i class="iconfont icon-shangyi" style="color: #35aefc; cursor: pointer;"></i>
        <i class="iconfont icon-xiayi" style="color: #3170d1; cursor: pointer;"></i>
        <i class="iconfont icon-zhiding" style="color: #3ca2d6; cursor: pointer;"></i>
</script>

</body>

</html>