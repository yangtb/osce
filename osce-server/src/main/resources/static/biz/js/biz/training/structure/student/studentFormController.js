layui.config({
    base: basePath + '/layui/build/js/'
}).use(['form', 'layer', 'table', 'jquery', 'treeSelect', 'common'], function () {
    var $ = layui.$,
        form = layui.form,
        common = layui.common,
        layer = layui.layer,
        treeSelect = layui.treeSelect;

    if (formType == 'add') {
        treeSelect.render({
            // 选择器
            elem: '#idOrg',
            // 数据
            data: basePath + '/pf/r/org/tree/select',
            // 异步加载方式：get/post，默认get
            type: 'post',
            // 占位符
            placeholder: '请选择机构',
            // 是否开启搜索功能：true/false，默认false
            //search: true,
            // 点击回调
            click: function (d) {
                $("#idOrg").val(d.current.id)
            },
            // 加载完成后的回调函数
            success: function (d) {
                //console.log(d);
//                选中节点，根据id筛选
                //  treeSelect.checkNode('orgTree', 1);

//                获取zTree对象，可以调用zTree方法
//                var treeObj = treeSelect.zTree('tree');
//                console.log(treeObj);

//                刷新树结构
//                treeSelect.refresh();
            }
        });

        treeSelect.render({
            elem: '#idDepart',
            data: basePath + '/pf/r/dept/tree/select',
            type: 'post',
            placeholder: '请选择班级',
            click: function (d) {
                $("#idDepart").val(d.current.id)
            },
            // 加载完成后的回调函数
            success: function (d) {

            }
        });
    }

    //自定义验证规则
    form.verify({
        passMy: function (value) {
            if (!value.match(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[\s\S]{8,16}$/)) {
                return '至少包含1个大写字母，1个小写字母和1个数字的8-16位密码';
            }
        }
    });

    //监听指定开关
    form.on('switch(userEnabledSwitch)', function (data) {
        if (this.checked == false) {
            layer.tips('温馨提示：请填写密码', '#clearPassword');
            $('#clearPassword').removeClass("layui-disabled");
            $('#clearPassword').removeAttr("disabled", "true");
            $('#clearPassword2').removeClass("layui-disabled");
            $('#clearPassword2').removeAttr("disabled", "true");
            $('#clearPassword').focus();
        } else {
            $('#clearPassword').addClass("layui-disabled");
            $('#clearPassword').attr("disabled", "true");
            $('#clearPassword2').addClass("layui-disabled");
            $('#clearPassword2').attr("disabled", "true");
        }
    });

    //监听提交
    form.on('submit(addUser)', function (data) {
        if (!data.field.idDepart) {
            $('#idDepart').focus();
            common.errorMsg("请选择班级");
            return false;
        }
        if (!data.field.idOrg) {
            $('#idOrg').focus();
            common.errorMsg("请选择机构");
            return false;
        }
        if (!data.field.sex) {
            $('#sex').focus();
            common.errorMsg("请选择性别");
            return false;
        }

        // 判断2次密码是不是一致
        if ($("#clearPassword").val() && $("#clearPassword2").val()) {
            if ($("#clearPassword").val() != $("#clearPassword2").val()) {
                common.errorMsg("两次输入密码不一致，请重新输入");
                return false;
            }
        }

        var bizData = {
            idStudentDepart: data.field.idStudentDepart,
            idUser: data.field.userId,
            idDepart: data.field.idDepart,
            idGrade: data.field.idGrade,
            registerInfo: data.field
        }

        var url = basePath + "/pf/r/student/" + formType;
        layer.load(2);
        $.ajax({
            url: url,
            type: 'post',
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify(bizData),
            success: function (data) {
                layer.closeAll('loading');
                if (data.code != 0) {
                    common.errorMsg(data.msg);
                    return false;
                } else {
                    common.sucMsg("保存成功");
                    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                    parent.layer.close(index); //再执行关闭
                    //刷新父页面table
                    if (formType == 'edit') {
                        parent.layui.common.refreshCurrentPage();
                    } else {
                        parent.layui.table.reload('studentTableId', {
                            height: 'full-110'
                        });
                    }
                    return true;
                }
            },
            error: function () {
                layer.closeAll('loading');
                common.errorMsg("保存失败");
                return false;
            }
        });
        return false;
    });
});

