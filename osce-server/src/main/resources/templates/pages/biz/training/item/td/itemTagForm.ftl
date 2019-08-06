<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>题库管理tab</title>
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
    <div class="layui-tab layui-col-xs12" lay-filter="tagTabFilter" style="margin: 0px">
        <ul class="layui-tab-title">
            <li class="layui-this">题集定义</li>
            <#if formType == 'edit'>
                <li>题目定义</li>
            </#if>
        </ul>
        <div class="layui-tab-content" style="margin: 0px;">
            <div class="layui-tab-item layui-show">
                <form class="layui-form" id="itemForm">
                    <div hidden>
                        <input name="idItemStore" id="idItemStore" hidden>
                    </div>

                    <div class="layui-form-item form-item-my">
                        <div class="layui-inline">
                            <label class="layui-form-label">题集名称<i class="iconfont icon-required"
                                                                   style="color: #f03f2d"></i></label>
                            <div class="layui-input-inline" style="width: 514px;">
                                <input type="text" name="naItemStore" lay-verify="required|naItemStore" lay-vertype="tips"
                                       autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">

                        </div>
                    </div>

                    <div class="layui-form-item form-item-my">
                        <div class="layui-inline">
                            <label class="layui-form-label">描述</label>
                            <div class="layui-input-inline" style="width: 514px;">
                                <textarea name="desItemStore" class="layui-textarea"
                                          autocomplete="off" lay-verify="commonLength255" lay-vertype="tips"/></textarea>
                            </div>
                        </div>
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
                            <button class="layui-btn" lay-submit="" lay-filter="addItem">
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
            <#if formType == 'edit'>
                <div class="layui-tab-item">
                    <iframe id="itemTag" class='layui-col-xs12' frameborder="0" src=""></iframe>
                </div>
            </#if>
        </div>
    </div>

</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/biz/js/biz/training/item/td/itemTabController.js"></script>
<script src="${contextPath}/common/js/jquery.min.js"></script>
<script src="${contextPath}/common/js/jquery.formautofill.js"></script>

<script>
    function fullForm(data) {
        $(document).ready(function(){
            $("#itemForm").autofill(data);
            layui.use('form',function(){
                layui.form.render();
            });

        });
    }
</script>

</body>
</html>