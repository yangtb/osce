<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>${formType}学员</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${contextPath}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath}/biz/css/common.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/biz/iconfont/iconfont.css">
    <link rel="stylesheet" href="${contextPath}/layui/expand/css/formSelects-v4.css"></script>

    <script>
        var formType = '${formType!}';
        var publicKey = '${publicKey!}';
        var basePath = '${basePath!}';
        var contextPath = '${contextPath!}';
    </script>

</head>

<body>
<div class="wrapper-content">
    <form class="layui-form" id="userform">
        <div hidden>
            <input name="userId" hidden>
            <input name="idStudentDepart" hidden>
        </div>
        <div class="layui-form-item form-item-my">
            <div class="layui-inline">
                <label class="layui-form-label">学届<i class="iconfont icon-required"
                                                       style="color: #f03f2d"></i></label>
                <div class="layui-input-inline">
                    <select name="idGrade" lay-verify="required">
                        <#if allGrade?? && (allGrade?size > 0)>
                            <#list allGrade as grade >
                                <option value="${grade.idGrade!}" <#if grade.fgActive='1'>selected</#if>>${grade.naGrade!}</option>
                            </#list>
                        </#if>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">班级<i class="iconfont icon-required"
                                                       style="color: #f03f2d"></i></label>
                <div class="layui-input-inline">
                    <input id="idDepart" name="idDepart" type="text" lay-filter="departTree" class="layui-input"/>
                </div>
            </div>
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
                    <input type="text" id="idOrg" name="idOrg" lay-filter="orgTree" class="layui-input"/>
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
                <label class="layui-form-label">联系手机<i class="iconfont icon-required"
                                                       style="color: #f03f2d"></i></label>
                <div class="layui-input-inline">
                    <input type="text" name="phoneNo" lay-verify="required|phone" lay-vertype="tips"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">邮箱</label>
                <div class="layui-input-inline">
                    <input type="text" name="email" lay-vertype="tips"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>

        <div class="layui-form-item form-item-my">
            <label class="layui-form-label">头像</label>
            <div class="layui-input-inline">
                <input name="photoAddr"
                       id="LAY_avatarSrc" placeholder="图片地址" class="layui-input">
            </div>
            <div class="layui-input-inline layui-btn-container" style="width: auto;">
                <button type="button" class="layui-btn layui-btn-primary" id="LAY_avatarUpload">
                    <i class="layui-icon">&#xe67c;</i>上传图片
                </button>
                <button type="button" class="layui-btn layui-btn-primary" id="reviewPhoto">查看图片</button>
            </div>
        </div>

        <div class="layui-form-item form-item-my">
            <div class="layui-inline">
                <label class="layui-form-label">学号<i class="iconfont icon-required"
                                                     style="color: #f03f2d"></i></label>
                <div class="layui-input-inline">
                    <input type="text" name="userCd" lay-verify="required" lay-vertype="tips"
                           placeholder="" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>

        <#if (formType == 'add')>
            <div class="layui-form-item form-item-my">
                <div class="layui-inline">
                    <label class="layui-form-label">默认密码</label>
                    <div class="layui-input-inline">
                        <input type="checkbox" checked="" name="enabled" lay-skin="switch"
                               lay-filter="userEnabledSwitch" value="true" lay-text="是|否">
                    </div>
                    <div class="layui-form-mid layui-word-aux">默认密码为身份证后6位</div>
                </div>
            </div>

            <div class="layui-form-item form-item-my">
                <div class="layui-inline">
                    <label class="layui-form-label">登录密码</label>
                    <div class="layui-input-inline">
                        <input type="hidden" name="password" id="encryptPassword">
                        <input type="password" id="clearPassword" lay-verify="passMy" autocomplete="off" placeholder="请输入密码"
                               class="layui-input layui-disabled" disabled>
                    </div>
                    <div class="layui-form-mid layui-word-aux">8-16位，至少包含1个大写字母、小写字母、数字</div>
                </div>
            </div>
            <div class="layui-form-item form-item-my">
                <div class="layui-inline">
                    <label class="layui-form-label">确认密码</label>
                    <div class="layui-input-inline">
                        <input type="password" id="clearPassword2" lay-verify="passMy" autocomplete="off" placeholder="请确认密码"
                               class="layui-input layui-disabled" disabled>
                    </div>
                    <div class="layui-form-mid layui-word-aux">2次输入密码必须一致</div>
                </div>
            </div>
        </#if>

        <div class="layui-form-item" style="padding-top: 5px">
            <div class="layui-input-block">
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

<script src="${contextPath}/layui/plugins/layui/layui.all.js"></script>
<script src="${contextPath}/common/js/jquery.min.js"></script>
<script src="${contextPath}/common/js/jquery.jcryption.3.1.0.js"></script>
<script src="${contextPath}/common/js/jquery.formautofill.js"></script>
<script src="${contextPath}/biz/js/biz/training/structure/student/studentFormController.js"></script>
<script src="${contextPath}/biz/js/user/register/registerController.js"></script>
<script>
    <#if (formType == 'edit')>
    function fullForm(data) {
        $(document).ready(function () {
            $("#userform").autofill(data);
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

                treeSelect.render({
                    elem: '#idDepart',
                    data: basePath + '/pf/r/dept/tree/select',
                    type: 'post',
                    placeholder: '请选择班级',
                    click: function(d){
                    },
                    // 加载完成后的回调函数
                    success: function (d) {
                        treeSelect.checkNode('departTree', data.idDepart);
                    }
                });
            });
        });
    };
    </#if>

    $(function () {
        var registerController = new RegisterController();
        registerController.init(publicKey);
    });
</script>

</body>
</html>