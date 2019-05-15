layui.config({
    base: basePath + '/layui/build/js/'
}).use(['table', 'form', 'jquery', 'common'], function () {
    var $ = layui.$
        , table = layui.table
        , form = layui.form
        , common = layui.common;

    //执行渲染
    table.render({
        elem: '#skillDeviceTable' //指定原始表格元素选择器（推荐id选择器）
        , id: 'skillDeviceTableId'
        , height: 'full-50' //容器高度
        , cols: [[
            {type: 'numbers', fixed: true},
            {checkbox: true, fixed: true},
            {field: 'naDevice', width: 300, title: '设备', fixed: true},
            {field: 'numDevice', width: 120, title: '数量'},
            {fixed: 'right', width: 120, title: '操作', align: 'center', toolbar: '#skillDeviceBar'}
        ]] //设置表头
        , url: basePath + '/pf/p/skill/device/list'
        , limit: 15
        , even: true
        , limits: [15, 30, 100]
        , page: true
        , where : {
            idSkillCase : idSkillCase
        }
    });

    //监听工具条
    table.on('tool(skillDeviceTableFilter)', function (obj) {
        var data = obj.data;
        if (obj.event === 'edit') {
            _addOrEdit("edit", data);
        }
    });

    //监听提交
    form.on('submit(skillDeviceSearchFilter)', function (data) {
        table.reload('skillDeviceTableId', {
            height: 'full-50'
            , page: {
                curr: 1 //重新从第 1 页开始
            }
            , where :{
                naDevice : data.field.naDevice
            }
        });
    });

    $('#add').on('click', function () {
        _addOrEdit("add");
    });

    $('#edit').on('click', function () {
        var checkStatus = table.checkStatus('skillDeviceTableId')
            , data = checkStatus.data;
        if (data.length == 0) {
            layer.tips('请先选中一行记录', '#edit', {tips: 1});
            return;
        }
        if (data.length > 1) {
            layer.tips('请选中一行记录进行编辑', '#edit', {tips: 1});
            return;
        }
        var currentEditData = data[0];
        _addOrEdit("edit", currentEditData);
    });

    var _addOrEdit = function (formType, currentEditData) {
        if (formType == 'add') {
            var index = common.open('新增技能操作病例', basePath + '/pf/p/skill/device/form?formType=' + formType, 500, 350);
            layer.full(index)
        } else {
            var index = common.open('编辑技能操作病例', basePath + '/pf/p/skill/device/form?formType=' + formType, 500, 350, _successFunction(currentEditData));
            layer.full(index)
        }
    };

    var _successFunction = function (data) {
        return function (layero, index) {
            var iframe = window['layui-layer-iframe' + index];
            //调用子页面的全局函数
            iframe.fullForm(data);
        }
    };

    //监听行双击事件
    table.on('rowDouble(skillDeviceTableFilter)', function (obj) {
        _addOrEdit("edit", obj.data);
    });

    $("#del").on('click', function () {
        var checkStatus = table.checkStatus('skillDeviceTableId')
            , data = checkStatus.data;
        if (data.length == 0) {
            layer.tips('请先选中一行记录', '#del', {tips: 1});
            return;
        }
        _delSkill(data);
    });

    var _delSkill = function (currentData) {
        var url = basePath + '/pf/r/skill/device/del';
        var reqData = new Array();
        var messageTitle = '';
        $.each(currentData, function (index, content) {
            if (messageTitle) {
                messageTitle += ', ';
            }
            messageTitle += '【' + content.naDevice + '】';
            reqData.push(content.idSkillDevice);
        });
        var data = {
            list : reqData,
            status : '1'
        };

        layer.confirm('确定删除' + messageTitle + '么？', {
            title: '删除提示',
            resize: false,
            btn: ['确定', '取消'],
            btnAlign: 'c',
            icon: 3
        }, function (index) {
            _commonAjax(index, url, data, "删除");
        })
    }

    var _commonAjax = function (index, url, reqData, msg) {
        layer.load(2);
        $.ajax({
            url: url,
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
                    common.sucChildMsg(msg + "成功");
                    if (index) {
                        layer.close(index);
                    }
                    _skillDeviceTableReload();
                    return true;
                }
            },
            error: function () {
                layer.closeAll('loading');
                common.errorMsg(msg + "失败");
                return false;
            }
        });
    }

    var _skillDeviceTableReload = function () {
        table.reload('skillDeviceTableId', {
            where: {
                //type: type
            },
            height: 'full-50'
        });
    }

});



