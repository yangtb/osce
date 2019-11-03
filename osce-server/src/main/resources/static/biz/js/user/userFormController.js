/**
 * 用户表单
 */
layui.config({
    base: basePath + '/layui/build/js/'
}).use(['form', 'layer', 'table', 'jquery', 'common', "upload"], function () {
    var $ = layui.$,
        form = layui.form,
        common = layui.common,
        layer = layui.layer,
        upload = layui.upload;

    //自定义验证规则
    form.verify({
        username: [/^[A-Za-z0-9_]{3,32}$/, '必须由字母、数字、下划线组成且至少3个字符'],
        pass : [/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[\s\S]{8,16}$/, '至少包含1个大写字母，1个小写字母和1个数字的8-16位密码']
    });

    //监听指定开关
    form.on('switch(userEnabledSwitch)', function (data) {
        if (this.checked == false) {
            layer.tips('温馨提示：状态置为无效，用户将无法登陆', data.othis)
        }
    });

    //监听提交
    form.on('submit(addUser)', function (data) {
        if (!data.field.sex) {
            $('#sex').focus();
            layer.msg("请选择性别");
            return false;
        }
        var roles = new Array();

        var role = $('input[name="role"]:checked').val();
        console.log($('input[name="role"]:checked').val())
        if (role) {
            roles.push($('input[name="role"]:checked').val());
        }

        /*$("input[name='role']:checked").each(function () {
            roles.push($(this).val());
        });*/
        if (roles.length == 0) {
            layer.msg("请选择用户角色");
            return false;
        }
        data.field.roles = roles;

        // 判断2次密码是不是一致
        if ($("#clearPassword").val() != $("#clearPassword2").val()) {
            layer.msg("两次输入密码不一致，请重新输入");
            return false;
        }

        var url = basePath + "/pf/r/user/" + formType;
        layer.load(2);
        $.ajax({
            url: url,
            type: 'post',
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify(data.field),
            success: function (data) {
                layer.closeAll('loading');
                if (data.code != 0) {
                    layer.msg(data.msg);
                    return false;
                } else {
                    layer.msg("保存成功");
                    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                    parent.layer.close(index); //再执行关闭
                    //刷新父页面table
                    if (formType == 'edit') {
                        parent.layui.common.refreshCurrentPage();
                    } else {
                        parent.layui.table.reload('userTableId', {
                            height: 'full-68'
                        });
                    }
                    return true;
                }
            },
            error: function () {
                layer.closeAll('loading');
                layer.msg("保存失败");
                return false;
            }
        });
        return false;
    });

    // 上传头像
    upload.render({
        elem: '#LAY_avatarUpload'
        , url: basePath + '/upload'
        , field: 'file'
        , accept: 'images' //普通文件
        , exts: 'jpg|png|bmp|jpeg'
        , before: function (obj) {
            layer.msg('正在上传图片', {
                icon: 16,
                shade: 0.01,
                time: false
            });
        }
        , done: function (res) {
            if (res.code != '0') {
                layer.tips(res.msg, '#LAY_avatarUpload', {
                    tips: [1, '#FF5722'],
                    time: 5000
                });
                return;
            }
            $('#LAY_avatarSrc').val(res.data.path);
            layer.closeAll();
        }
        , error: function () {
            layer.closeAll();
        }
    });

    $('#reviewPhoto').on('click', function () {
        var i = $("#LAY_avatarSrc").val();
        if (!i) {
            layer.tips("请先上传头像", '#reviewPhoto', {
                tips: [1, '#FF5722']
            });
            return;
        }
        layui.layer.photos({
            photos: {
                title: "查看头像",
                data: [{
                    src: i
                }]
            },
            shade: .01,
            closeBtn: 1,
            anim: 5
        })
    });
});

