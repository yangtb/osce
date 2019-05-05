<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>${formType}菜单</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${contextPath}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath}/biz/css/common.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/biz/iconfont/iconfont.css">

    <script>
        var basePath = '${basePath}';
        var formType = '${formType}';
    </script>
</head>

<body class="body-my">

<div class="wrapper-content">
    <form class="layui-form" id="menuform">
        <div hidden>
            <input name="id" hidden>
        </div>
        <div class="layui-form-item form-item-my">
            <div class="layui-inline">
                <label class="layui-form-label">名称<i class="iconfont icon-required" style="color: #f03f2d"></i></label>
                <div class="layui-input-inline">
                    <input type="text" name="name" data-fill="" lay-verify="required" lay-vertype="tips" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">级别<i class="iconfont icon-required" style="color: #f03f2d"></i></label>
                <div class="layui-input-inline">
                    <select name="level" id="level" lay-verify="required" lay-vertype="tips">
                        <option value="">请选择</option>
                        <option value="1">一级</option>
                        <option value="2">二级</option>
                        <option value="3">三级</option>
                    </select>
                </div>
            </div>
        </div>

        <div class="layui-form-item form-item-my">
            <div class="layui-inline">
                <label class="layui-form-label">编码<i class="iconfont icon-required" style="color: #f03f2d"></i></label>
                <div class="layui-input-inline">
                    <input type="text" name="code" lay-verify="required" lay-vertype="tips" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">父编码</label>
                <div class="layui-input-inline">
                    <input type="text" name="parentCode" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
        </div>

        <div class="layui-form-item form-item-my">
            <div class="layui-inline">
                <label class="layui-form-label">权限url</label>
                <div class="layui-input-inline">
                    <input type="text" name="functionUrl" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">菜单位置</label>
                <div class="layui-input-inline">
                    <select name="position">
                        <option value="">未选择</option>
                        <option value="left">左侧</option>
                        <option value="top">顶部</option>
                    </select>
                </div>
            </div>
        </div>

        <div class="layui-form-item form-item-my">
            <div class="layui-inline">
                <label class="layui-form-label">资源类别<i class="iconfont icon-required" style="color: #f03f2d"></i></label>
                <div class="layui-input-inline">
                    <select name="functionType" lay-verify="required" lay-vertype="tips">
                        <option value="1" selected>菜单权限功能</option>
                        <option value="2">按钮、链接功能权限资源</option>
                        <option value="3">接口功能权限资源</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">状态</label>
                <div class="layui-input-block">
                    <input type="checkbox" checked="" name="status" lay-skin="switch"
                           lay-filter="menuDisableSwitch" value="enabled" lay-text="有效|无效">
                </div>
            </div>
        </div>

        <div class="layui-form-item form-item-my5">
            <div class="layui-inline">
                <label class="layui-form-label">图标类型</label>
                <div class="layui-input-inline">
                    <select name="iconType" lay-verify="required">
                        <option value="0">无</option>
                        <option value="1">iconfont矢量图标</option>
                        <option value="2">url图标</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">图标</label>
                <div class="layui-input-inline">
                    <input type="text" name="iconSource" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>

        <div class="layui-form-item form-item-my5">
            <div class="layui-inline">
                <label class="layui-form-label">排序<i class="iconfont icon-required" style="color: #f03f2d"></i></label>
                <div class="layui-input-inline">
                    <input type="text" name="sortNum" lay-verify="required|sort" lay-vertype="tips" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">所属平台<i class="iconfont icon-required" style="color: #f03f2d"></i></label>
                <div class="layui-input-inline">
                    <select name="platformType" lay-verify="required" lay-vertype="tips">
                        <option value="pfprod">管理平台</option>
                    </select>
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="addMenu">
                    <i class="iconfont icon-save-copy"></i> 保存
                </button>
                <#if formType == 'add'>
                <button type="reset" class="layui-btn layui-btn-danger">
                    <i class="iconfont icon-reset"></i> 重新填写
                </button>
                </#if>
            </div>
        </div>
    </form>
</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/biz/js/user/menu/menuFormController.js"></script>
<script src="${contextPath}/common/js/jquery.min.js"></script>
<script src="${contextPath}/common/js/jquery.formautofill.js"></script>

<script>
    function fullForm(data) {
        $(document).ready(function(){
            $("#menuform").autofill(data);
            layui.use('form',function(){
                layui.form.render();
            });

        });
    }
</script>

</body>
</html>