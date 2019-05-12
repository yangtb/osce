<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>房间信息tab</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${contextPath}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath}/biz/css/common.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/biz/iconfont/iconfont.css">

    <script>
        var basePath = '${basePath}';
        var contextPath = '${contextPath}';
        var formType = '${formType!}';
    </script>
</head>

<body class="body-my">
<div>
    <div class="layui-tab layui-col-xs12" lay-filter="tagTabFilter" style="margin: 5px">
        <ul class="layui-tab-title">
            <li class="layui-this">房间定义</li>
                <li>固定设备</li>
        </ul>
        <div class="layui-tab-content" style="margin: 0px;">
            <div class="layui-tab-item layui-show">
                <form class="layui-form" id="roomForm">
                    <div hidden>
                        <input id="idRoom" name="idRoom" hidden>
                    </div>
                    <div class="layui-form-item form-item-my">
                        <div class="layui-inline">
                            <label class="layui-form-label">房间<i class="iconfont icon-required"
                                                                   style="color: #f03f2d"></i></label>
                            <div class="layui-input-inline" style="width: 514px;">
                                <input type="text" name="naRoom" lay-verify="required|naRoom" lay-vertype="tips"
                                       autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">

                        </div>
                    </div>

                    <div class="layui-form-item form-item-my">
                        <div class="layui-inline">
                            <label class="layui-form-label">部门描述</label>
                            <div class="layui-input-inline" style="width: 514px;">
                                <textarea name="desRoom" class="layui-textarea"
                                          autocomplete="off" lay-verify="desRoom" lay-vertype="tips"/></textarea>
                            </div>
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">布局图</label>
                        <div class="layui-input-inline" style="vertical-align: middle;width: 317px;">
                            <input id="picRoom" name="picRoom" placeholder="请上传文件" autocomplete="off"
                                   class="layui-input layui-disabled" disabled>
                        </div>
                        <button type="button" class="layui-btn layui-btn-primary" id="test3">
                            <i class="layui-icon" style="color: #009688; font-weight: bold">&#xe608;</i>上传
                        </button>
                        <button type="button" class="layui-btn layui-btn-primary" id="preview">
                            <i class="iconfont icon-look1" style="color: #009688; font-weight: bold"></i> 预览
                        </button>
                    </div>

                    <div class="layui-form-item form-item-my">
                        <div class="layui-inline">
                            <label class="layui-form-label">激活状态</label>
                            <div class="layui-input-inline">
                                <input type="checkbox" checked="" name="fgActive" lay-skin="switch"
                                       lay-filter="fgActiveSwitch" value="1" lay-text="激活|停用">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">排序</label>
                            <div class="layui-input-inline">
                                <input type="text" name="sort" id="sort" autocomplete="off" class="layui-input"
                                       lay-verify="sort" lay-vertype="tips">
                            </div>
                        </div>
                    </div>

                    <div class="layui-form-item form-item-my">
                        <div class="layui-inline">
                            <label class="layui-form-label">创建人</label>
                            <div class="layui-input-inline">
                                <input type="text" name="creator" autocomplete="off" class="layui-input layui-disabled"
                                       disabled>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">创建时间</label>
                            <div class="layui-input-inline">
                                <input type="text" name="gmtCreate" autocomplete="off" class="layui-input layui-disabled"
                                       disabled>
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item form-item-my">
                        <div class="layui-inline">
                            <label class="layui-form-label">修改人</label>
                            <div class="layui-input-inline">
                                <input type="text" name="operator" autocomplete="off" class="layui-input layui-disabled"
                                       disabled>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">修改时间</label>
                            <div class="layui-input-inline">
                                <input type="text" name="gmtModify" autocomplete="off" class="layui-input layui-disabled"
                                       disabled>
                            </div>
                        </div>
                    </div>

                    <div class="layui-form-item" style="padding-top: 5px">
                        <div class="layui-input-block">
                            <button class="layui-btn" lay-submit="" lay-filter="addRoom">
                                <i class="iconfont icon-save-copy"></i> 保存
                            </button>
                            <#if (formType == 'add')>
                                <button type="reset" class="layui-btn layui-btn-danger">
                                    <i class="iconfont icon-reset"></i> 重新填写
                                </button>
                            </#if>
                        </div>
                    </div>

                </form>
            </div>
                <div class="layui-tab-item">
                    <iframe id="deviceTag" class='layui-col-xs12' frameborder="0" src=""></iframe>
                </div>
        </div>
    </div>

</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/biz/js/biz/training/res/room/roomTabController.js"></script>
<script src="${contextPath}/common/js/jquery.min.js"></script>
<script src="${contextPath}/common/js/jquery.formautofill.js"></script>

<script>
    function fullForm(data) {
        $(document).ready(function(){
            $("#roomForm").autofill(data);
            layui.use('form',function(){
                layui.form.render();
            });

        });
    }
</script>

</body>
</html>