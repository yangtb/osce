<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>${formType}SP</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${contextPath}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath}/biz/css/common.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/biz/iconfont/iconfont.css">
    <link rel="stylesheet" href="${contextPath}/layui/build/js/treeSelect.js"></script>

    <script>
        var formType = '${formType!}';
        var basePath = '${basePath!}';
        var contextPath = '${contextPath!}';
    </script>

</head>

<body>
<div class="wrapper-content">
    <form class="layui-form layui-form-pane" id="spForm">
        <div hidden>
            <input name="userId" hidden>
        </div>
        <div class="layui-form-item form-item-my">
            <div class="layui-inline">
                <label class="layui-form-label">真实姓名<i class="iconfont icon-required"
                                                       style="color: #f03f2d"></i></label>
                <div class="layui-input-inline">
                    <input type="text" name="realName" lay-verify="required" lay-vertype="tips"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">所属机构<i class="iconfont icon-required"
                                                       style="color: #f03f2d"></i></label>
                <div class="layui-input-inline">
                    <div class="layui-input-inline">
                        <input id="idOrg" name="idOrg" type="text" lay-filter="orgTree" class="layui-input">
                    </div>

                </div>
            </div>
        </div>

        <div class="layui-form-item form-item-my">
            <div class="layui-inline">
                <label class="layui-form-label">性别<i class="iconfont icon-required"
                                                     style="color: #f03f2d"></i></label>
                <div class="layui-input-inline">
                    <select name="sex" id="sex" lay-verify="required" lay-vertype="tips">
                        <option value="">请选择</option>
                        <option value="1">男</option>
                        <option value="2">女</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">身份证号<i class="iconfont icon-required"
                                                       style="color: #f03f2d"></i></label>
                <div class="layui-input-inline">
                    <input type="text" name="idcard" lay-verify="required|identity" lay-vertype="tips"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>

        <div class="layui-form-item form-item-my">
            <div class="layui-inline">
                <label class="layui-form-label">联系电话<i class="iconfont icon-required"
                                                       style="color: #f03f2d"></i></label>
                <div class="layui-input-inline">
                    <input type="text" name="phoneNo" lay-verify="required|phone" lay-vertype="tips"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">邮箱<i class="iconfont icon-required"
                                                     style="color: #f03f2d"></i></label>
                <div class="layui-input-inline">
                    <input type="text" name="email" lay-verify="required|email" lay-vertype="tips"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item form-item-my" style="width: 625px">
            <table class="layui-table" lay-size="sm">
                <tbody>
                <#if allSpTag?? && (allSpTag?size > 0)>
                    <#list allSpTag as spTag>
                        <tr>
                            <td>${spTag.descript!}</td>
                            <td><input type="text" name="spTag-${spTag.idSpTag!}" class="layui-input btn-sm-my1"
                                       autocomplete="off"></td>
                        </tr>
                    </#list>
                </#if>
                </tbody>
            </table>
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
                    <input type="text" name="operator" autocomplete="off" class="layui-input layui-disabled"
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

        <div class="layui-form-item" style="padding-top: 5px">
            <div class="layui-input-block">
                <button class="layui-btn layui-btn-normal" type="button" id="addTag">
                    <i class="iconfont icon-biaoqian"></i> SP标签定义
                </button>
                <button class="layui-btn" lay-submit="" lay-filter="addUser">
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

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/common/js/jquery.min.js"></script>
<script src="${contextPath}/common/js/jquery.formautofill.js"></script>
<script src="${contextPath}/biz/js/biz/training/structure/sp/spFormController.js"></script>
<script>
    <#if (formType == 'edit')>
    function fullForm(data) {
        $(document).ready(function () {
            $("#spForm").autofill(data);
            layui.use(['form', 'jquery', 'treeSelect'], function () {
                layui.form.render();
                var treeSelect= layui.treeSelect
                treeSelect.render({
                    elem: '#idOrg',
                    data: basePath + '/pf/r/org/tree/select',
                    type: 'post',
                    placeholder: '请选择机构',
                    // 加载完成后的回调函数
                    success: function (d) {
                        treeSelect.checkNode('orgTree', data.idOrg);
                    }
                });
            });
        });
    };
    </#if>
</script>

</body>
</html>