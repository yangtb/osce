<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>${formType}用户</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${contextPath}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath}/biz/css/common.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/biz/iconfont/iconfont.css">
    <link rel="stylesheet" href="${contextPath}/layui/expand/css/formSelects-v4.css"></script>

    <script>
        var formType = '${formType}';
        var publicKey = '${publicKey}';
        var basePath = '${basePath}';
        var contextPath = '${contextPath}';
    </script>

</head>

<body>
<div class="wrapper-content">
    <form class="layui-form" id="userform">
        <div hidden>
            <input name="user_id" hidden>
        </div>
        <div class="layui-form-item form-item-my">
            <div class="layui-inline">
                <label class="layui-form-label">登录账号<i class="iconfont icon-required"
                                                       style="color: #f03f2d"></i></label>
                <div class="layui-input-inline">
                    <input type="text" name="user_name" lay-verify="required|username" lay-vertype="tips" autocomplete="off"
                           class="layui-input <#if (formType == 'edit')>layui-disabled</#if>"
                           <#if (formType == 'edit')>disabled</#if>>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">所属机构<i class="iconfont icon-required"
                                                       style="color: #f03f2d"></i></label>
                <div class="layui-input-inline">
                    <select name="idOrg" lay-verify="required" lay-vertype="tips">
                        <option value="">请选择</option>
                        <#list allOrg as element>
                            <option value="${element.idOrg}" <#if (formType=='add' && userOrgId==element.idOrg)>selected</#if>>${element.name}</option>
                        </#list>
                    </select>
                </div>
            </div>
        </div>
        <#if (formType == 'add')>
            <div class="layui-form-item form-item-my">
                <div class="layui-inline">
                    <label class="layui-form-label">登录密码<i class="iconfont icon-required"
                                                           style="color: #f03f2d"></i></label>
                    <div class="layui-input-inline">
                        <input type="hidden" name="password" id="encryptPassword">
                        <input type="password" id="clearPassword" lay-verify="required|pass" lay-vertype="tips" autocomplete="off" placeholder="请输入密码"
                               class="layui-input">
                    </div>
                    <div class="layui-form-mid layui-word-aux">8-16位，至少包含1个大写字母、小写字母、数字</div>
                </div>
            </div>
            <div class="layui-form-item form-item-my">
                <div class="layui-inline">
                    <label class="layui-form-label">确认密码<i class="iconfont icon-required"
                                                           style="color: #f03f2d"></i></label>
                    <div class="layui-input-inline">
                        <input type="password" id="clearPassword2" lay-verify="required|pass" lay-vertype="tips" autocomplete="off" placeholder="请确认密码"
                               class="layui-input">
                    </div>
                    <div class="layui-form-mid layui-word-aux">2次输入密码必须一致</div>
                </div>
            </div>
        </#if>

        <div class="layui-form-item form-item-my">
            <div class="layui-inline">
                <label class="layui-form-label">邮箱<i class="iconfont icon-required"
                                                     style="color: #f03f2d"></i></label>
                <div class="layui-input-inline">
                    <input type="text" name="email" lay-verify="required|email" lay-vertype="tips"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">真实姓名<i class="iconfont icon-required"
                                                       style="color: #f03f2d"></i></label>
                <div class="layui-input-inline">
                    <input type="text" name="real_name" lay-verify="required" lay-vertype="tips"
                           autocomplete="off" class="layui-input">
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
                <label class="layui-form-label">联系手机<i class="iconfont icon-required"
                                                       style="color: #f03f2d"></i></label>
                <div class="layui-input-inline">
                    <input type="text" name="phone_no" lay-verify="required|phone" lay-vertype="tips"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>

        <div class="layui-form-item form-item-my">
            <div class="layui-inline">
                <label class="layui-form-label">状态</label>
                <div class="layui-input-block">
                    <input type="checkbox" checked="" name="enabled" lay-skin="switch"
                           lay-filter="userEnabledSwitch" value="true" lay-text="活动|锁定">
                </div>
            </div>

        </div>

        <div class="layui-form-item form-item-my5">
            <label class="layui-form-label">用户角色</label>
            <div class="layui-input-block">
                <#list roles as role>
                    <#if (role.level != 0)>
                        <input type="checkbox" name="role" value="${role.roleId}"
                               lay-skin="primary" title="${role.name}"
                               <#if (role.checked == true)>checked</#if>
                    </#if>
                </#list>
            </div>
        </div>

        <div class="layui-form-item" style="padding-top: 5px">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="addUser">
                    <i class="iconfont icon-save-copy"></i> 保存
                </button>
                <#if(formType == 'add')>
                    <button type="reset" class="layui-btn layui-btn-danger">
                        <i class="iconfont icon-reset"></i> 重新填写
                    </button>
                </#if>
            </div>
        </div>
    </form>
</div>

<script src="${contextPath}/layui/plugins/layui/layui.all.js"></script>
<script src="${contextPath}/common/js/jquery.min.js"></script>
<script src="${contextPath}/common/js/jquery.jcryption.3.1.0.js"></script>
<script src="${contextPath}/common/js/jquery.formautofill.js"></script>
<script src="${contextPath}/biz/js/user/userFormController.js"></script>
<script src="${contextPath}/biz/js/user/register/registerController.js"></script>
<script>
    #if(${formType} == 'edit')
    function fullForm(data) {
        $(document).ready(function () {
            $("#userform").autofill(data);
            layui.use('form', function () {
                layui.form.render();
            });
        });
    };
    #end

    $(function () {
        var registerController = new RegisterController();
        registerController.init(publicKey);
    });
</script>

</body>
</html>