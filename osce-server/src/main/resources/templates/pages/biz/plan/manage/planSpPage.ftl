<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>标准化病人选</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${contextPath}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath}/biz/css/common.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/biz/iconfont/iconfont.css">

    <script>
        var basePath = '${basePath}';
        var idPlan = '${idPlan!}';
        var tagSize = ${allSpTag?size};
    </script>
</head>

<body>

<div class="wrapper-content">

    <div class="layui-row layui-col-space10">
        <div class="layui-col-md6">
            <div class="layui-card" style="height: 300px; border: 1px solid #e9e9e9">
                <div class="layui-card-header">
                    <span style="float: left; font-weight: bold">考试-上午/下午</span>
                    <button type="button" class="layui-btn layui-btn-normal layui-btn-sm" style="float: right; margin-top: 5px;">查看标准化重病人</button>
                </div>
                <div class="layui-card-body">
                    <table id="test1" lay-filter="test1Filter">
                    </table>
                </div>
            </div>
        </div>
        <div class="layui-col-md6">
            <div class="layui-card" style="height: 300px; border: 1px solid #e9e9e9">
                <div class="layui-card-header">
                    <span style="float: left; font-weight: bold">过滤方式</span>
                    <button id="querySp" type="button" class="layui-btn layui-btn-sm"
                            style="float: right; margin-top: 5px;">查询</button>
                </div>
                <div class="layui-card-body">
                    <div style="height: 240px; overflow:scroll; overflow-x: hidden;">

                        <table style="width:100%;background-color:#fff;color:#666;height: auto;border-collapse:separate; border-spacing:2px;">
                            <tbody>
                            <#if allSpTag?? && (allSpTag?size > 0)>
                                <#list allSpTag as spTag>
                                    <tr>
                                        <td>•&nbsp;<span id="tagName-${spTag.idSpTag!}">${spTag.descript!}</span></td>
                                        <td>
                                            <select id="type-${spTag_index}" data-idSpTag="${spTag.idSpTag!}">
                                                <option value="1">且</option>
                                                <option value="2">或</option>
                                            </select>
                                        </td>
                                        <td>
                                            <select id="condition-${spTag.idSpTag!}">
                                                <option value="1">等于</option>
                                                <option value="2">包含</option>
                                            </select>
                                        </td>
                                        <td>
                                            <input type="text" id="spTag-${spTag.idSpTag!}" class="layui-input"
                                                   style="height: 24px; width: 150px;"
                                                   autocomplete="off">
                                        </td>
                                    </tr>
                                </#list>
                            </#if>
                            </tbody>
                        </table>

                    </div>
                </div>
            </div>
        </div>
    </div>


    <div class="layui-row layui-col-space10">
        <div class="layui-col-md6">
            <div class="layui-card" style="height: 300px; border: 1px solid #e9e9e9">
                <div class="layui-card-header">
                    <span style="float: left; font-weight: bold">查询结果</span>
                    <button id="addSpCache" type="button" class="layui-btn layui-btn-sm"
                            style="float: right; margin-top: 5px;">添加</button>
                </div>
                <div class="layui-card-body">
                    <table id="test2" lay-filter="test2Filter">
                    </table>
                </div>
            </div>
        </div>
        <div class="layui-col-md6">
            <div class="layui-card" style="height: 300px; border: 1px solid #e9e9e9">
                <div class="layui-card-header">
                    <span style="float: left; font-weight: bold">计划SP分配</span>
                    <button id="delSpCache" type="button" class="layui-btn layui-btn-danger layui-btn-sm"
                            style="float: right; margin-top: 5px;">删除</button>
                </div>
                <div class="layui-card-body">
                    <table id="test3" lay-filter="test3Filter">
                    </table>
                </div>
            </div>
        </div>
    </div>

</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/biz/js/biz/plan/manage/planSpPage.js"></script>

</body>

</html>