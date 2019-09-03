layui.config({
    base: basePath + '/layui/build/js/'
}).use(['form', 'layer', 'treeSelect', 'jquery', 'common'], function () {
    var $ = layui.$,
        form = layui.form,
        common = layui.common,
        layer = layui.layer,
        treeSelect = layui.treeSelect;

    if (formType == 'add') {
        treeSelect.render({
            elem: '#idOrg',
            data: basePath + '/pf/r/org/tree/select',
            type: 'post',
            placeholder: '请选择机构',
            click: function (d) {
                $("#idOrg").val(d.current.id);
            },
            success: function (d) {
                if (formType == 'add') {
                    $("#idOrg").val(idOrg);
                    treeSelect.checkNode('orgTree', idOrg);
                }
            }
        });
        form.render();
    }

    $("#addTag").on('click', function () {
        common.open('标签管理', basePath + '/pf/p/sp/tag/form', 490, 500);
    });

    //监听提交
    form.on('submit(addUser)', function (data) {
        data.field.idOrg = $("#idOrg").val();
        if (!data.field.idOrg) {
            $('#idOrg').focus();
            common.errorMsg("请选择机构");
            return false;
        }
        console.log(data.field)
        var reqData = new Array(),
            userSpObj = data.field;

        for (var i in userSpObj) {
            if (i.indexOf("spTag-") != -1) {
                var userSpData = {
                    idSpTag2: i.substring(6, i.length),
                    value: userSpObj[i]
                }
                reqData.push(userSpData);
            }
        }

        var bizData = {
            registerInfo: data.field,
            userSpList: reqData
        }

        console.log(bizData)

        var url = basePath + "/pf/r/sp/" + formType;
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
                        parent.layui.table.reload('spTableId', {
                            height: 'full-60'
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

