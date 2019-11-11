layui.config({
    base: basePath + '/layui/plugins/'
}).extend({
    index: 'lib/index', //主入口模块
}).use(['index', 'form', 'layer', 'treeSelect', 'jquery', 'common'], function () {
    var $ = layui.$,
        form = layui.form,
        common = layui.common,
        layer = layui.layer,
        treeSelect = layui.treeSelect;

    $(document).ready(function () {
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
        } else {
            var spBizData = {
                userId : userId
            }
            $.ajax({
                url: basePath + '/pf/r/sp/select',
                type: 'post',
                dataType: 'json',
                contentType: "application/json",
                data: JSON.stringify(spBizData),
                success: function (data) {
                    layer.closeAll('loading');
                    if (data.code != 0) {
                        layer.msg(data.msg);
                        return false;
                    } else {
                        // 获取sp
                        getTagValue(data.data);
                        return true;
                    }
                },
                error: function () {
                    layer.closeAll('loading');
                    layer.msg("查询失败");
                    return false;
                }
            });
        }

    });

    function getTagValue(currentEditData) {
        var reqData = {
            idUser: currentEditData.userId
        }
        $.ajax({
            url: basePath + '/pf/r/sp/tag/value',
            type: 'post',
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify(reqData),
            success: function (data) {
                layer.closeAll('loading');
                if (data.code != 0) {
                    common.errorMsg(data.msg);
                    return false;
                } else {
                    var spMapList = eval(data.data);

                    for (var i = 0; i < spMapList.length; i++) {
                        currentEditData['spTag-' + spMapList[i].id_sp_tag2] = spMapList[i].value
                    }

                    $("#spForm").autofill(currentEditData);
                    treeSelect.render({
                        elem: '#idOrg',
                        data: basePath + '/pf/r/org/tree/select',
                        type: 'post',
                        placeholder: '请选择机构',
                        // 加载完成后的回调函数
                        success: function (d) {
                            treeSelect.checkNode('orgTree', currentEditData.idOrg);
                        }
                    });
                    form.render();
                    return true;
                }
            },
            error: function () {
                common.errorMsg("获取标签值失败");
                return false;
            }
        });
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
                    layer.msg(data.msg);
                    return false;
                } else {
                    layer.msg("保存成功");
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


});

