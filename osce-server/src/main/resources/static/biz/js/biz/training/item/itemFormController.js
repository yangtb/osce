layui.config({
    base: basePath + '/layui/build/js/'
}).extend({
    numinput: 'numinput.min'
}).use(['table', 'form', 'jquery', 'element', 'common', 'numinput'], function () {
    var $ = layui.$
        , table = layui.table
        , form = layui.form
        , common = layui.common
        , element = layui.element
        , numinp = layui.numinput;

    numinp.init({
        rightBtns: true
    });

    form.verify({
        commonLength64: function (value) {
            if (value.length > 64) {
                return '长度不能超过64个字';
            }
        },
        commonLength255: function (value) {
            if (value && value.length > 255) {
                return '长度不能超过255个字';
            }
        }
    });

    form.on('submit(addItem)', function (data) {
        var url = basePath + '/pf/r/item/exam/save';
        data.field.idItemStore = idItemStore;
        data.field.idItemSection = idItemSection;
        var oldData = table.cache["itemOptionTableId"];
        data.field.itemOptions = oldData;
        if (!oldData || oldData.length == 0) {
            layer.confirm('题目选项未填写，点击【确定】将继续保存', {
                title: '提示',
                resize: false,
                btn: ['确定', '取消'],
                btnAlign: 'c',
                icon: 3,
            }, function (index) {
                layer.close(index);
                data.field.itemOptions = oldData;
                if (!data.field.fgActive) {
                    data.field.fgActive = '0';
                }
                return _addItem(url, data.field, formType, 'itemManageTableId', '保存');
            });
            return false;
        }
        // 1、选项编码重复校验
        var map = {}, fgRightNum = 0;
        $.each(oldData, function (index, content) {
            if (map[content.cdIte]) {
                map[content.cdIte] = map[content.cdIte] + 1;
            } else {
                map[content.cdIte] = 1;
            }

            if (content.fgRight == 1) {
                fgRightNum = fgRightNum + 1;
            }
        });

        var repeatCode = '';
        for (var key in map) {
            if (map[key] > 1) {
                if (!repeatCode) {
                    repeatCode += key;
                } else {
                    repeatCode = repeatCode + "、" + key;
                }
            }
        }

        if (repeatCode) {
            layer.alert('题目选项编码【<span style="color: red; font-weight: bold">' + repeatCode + '</span>】重复，请修改', {
                title: '提示',
                resize: false,
                btn: ['确定']
                , icon: 5
            });
            return false;
        }
        // 2、正确答案校验
        if (data.field.sdItemCa == 2) {
            // 单选
            if (fgRightNum > 1) {
                layer.alert('【<span style="color: red; font-weight: bold">此题目为单选题</span>】，正确答案只有1个，请修改', {
                    title: '提示',
                    resize: false,
                    btn: ['确定']
                    , icon: 5
                });
                return false;
            }
        }
        return _addItem(url, data.field, formType, 'itemManageTableId', '保存');
    });

    var _addItem = function (url, bizData, formType, tableId, msg) {
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
                    common.sucMsg(msg + "成功");
                    //刷新父页面table
                    if (formType == 'edit') {
                        parent.layui.common.refreshCurrentPage();
                    } else {
                        $('#idItem').val(data.data);
                        parent.layui.table.reload(tableId, {
                            height: 'full-68'
                        });
                    }
                    return false;
                }
            },
            error: function () {
                layer.closeAll('loading');
                common.errorMsg(msg + "失败");
                return false;
            }
        });
        return false;
    }

    //执行渲染
    table.render({
        elem: '#itemOptionTable' //指定原始表格元素选择器（推荐id选择器）
        , id: 'itemOptionTableId'
        , height: '380' //容器高度
        , cols: [[
            {checkbox: true},
            {field: 'cdIte', width: 88, edit: 'text', title: '选项编码'},
            {field: 'naOption', minWidth: 100, edit: 'text', title: '选项内容'},
            {field: 'fgRight', width: 92, title: '正确答案', templet: '#fgRightTpl'}
        ]] //设置表头
        , limit: 500
        , even: true
        , page: false
        , data: []
    });

    //监听删除操作
    form.on('switch(fgRightCheckFilter)', function (obj) {
        var oldData = table.cache["itemOptionTableId"];
        oldData[obj.value - 1].fgRight = obj.elem.checked ? '1' :'0';
        table.reload('itemOptionTableId', {
            data: oldData
        });
    });

    $('#addOption').on('click', function () {
        var oldData = table.cache["itemOptionTableId"];
        if (!oldData) {
            oldData = [];
        }
        var bizData = {
            "cdIte": "",
            "naOption": "",
            "fgRight": "0"
        }
        oldData.push(bizData);
        table.reload('itemOptionTableId', {
            data: oldData
        });
    });

    $("#delOption").on('click', function () {
        var checkStatus = table.checkStatus('itemOptionTableId')
            , data = checkStatus.data;
        if (data.length == 0) {
            layer.tips('请先选中一行记录', '#delOption', {tips: 1});
            return;
        }
        _delOption(data);
    });

    var _delOption = function (currentData) {
        var url = basePath + '/pf/r/item/option/del';
        var reqData = new Array();
        $.each(currentData, function (index, content) {
            if (content.idItemOption) {
                reqData.push(content.idItemOption);
            }
        });

        if (!reqData || reqData.length == 0) {
            _delOptionBack();
            return false;
        }

        var data = {
            list: reqData
        };
        common.commonPost(url, data, '删除', 'delOption', _delOptionBack, true);
        return false;
    };
    
    var _delOptionBack = function () {
        // 加载表格
        var reqData = {
            idItem: $('#idItem').val()
        }
        layer.load(2);
        $.ajax({
            url: basePath + '/pf/r/item/option/list',
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
                    table.reload('itemOptionTableId', {
                        data: data.data
                    });
                    return true;
                }
            },
            error: function () {
                layer.closeAll('loading');
                common.errorMsg("获取题目选项失败");
                return false;
            }
        });
    }

});

function fullForm(data) {
    $(document).ready(function(){
        $("#itemForm").autofill(data);
        layui.use(['table', 'form', 'common'],function(){
            var $ = layui.$
                , form = layui.form
                , table = layui.table
                , common = layui.common;
            // 渲染表单
            form.render();

            // 加载表格
            var reqData = {
                idItem: data.idItem
            }
            layer.load(2);
            $.ajax({
                url: basePath + '/pf/r/item/option/list',
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
                        table.reload('itemOptionTableId', {
                            data: data.data
                        });
                        return true;
                    }
                },
                error: function () {
                    layer.closeAll('loading');
                    common.errorMsg("查询题目选项失败");
                    return false;
                }
            });
        });
    });
}

