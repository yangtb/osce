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
        , height: 'full-20' //容器高度
        , cols: [[
            {type: 'numbers', fixed: true, title: 'R'},
            {checkbox: true, fixed: true},
            {field: 'naDevice', width: 300, title: '设备', fixed: true},
            {field: 'numDevice', width: 120, title: '数量'},
            {fixed: 'right', width: 200, title: '操作', align: 'center', toolbar: '#skillDeviceBar'}
        ]] //设置表头
        , url: basePath + '/pf/p/exam/skill/device/list'
        , limit: 500
        , even: true
        //, limits: [15, 30, 100]
        , page: false
        , where : {
            idSkillCase : idSkillCase
        }
    });

    //监听工具条
    table.on('tool(skillDeviceTableFilter)', function (obj) {
        var data = obj.data;
        if (obj.event === 'add') {
            _addOrEdit("add", data);
        }
        if (obj.event === 'edit') {
            _addOrEdit("edit", data);
        }
        if (obj.event === 'del') {
            _delSkill(data);
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

    var _addOrEdit = function (formType, currentEditData) {
        if (formType == 'add') {
            var index = common.open('新增设备', basePath + '/pf/p/exam/skill/device/form?formType='
                + formType + '&idSkillCase=' + idSkillCase, 550, 400);
            layer.full(index);
        } else {
            var index = common.open('编辑设备', basePath + '/pf/p/exam/skill/device/form?formType='
                + formType + '&idSkillCase=' + idSkillCase, 550, 400, _successFunction(currentEditData));
            layer.full(index);
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


    var _delSkill = function (currentData) {
        var url = basePath + '/pf/r/exam/skill/device/del';
        var reqData = new Array();
        reqData.push(currentData.idSkillDevice);
        var messageTitle = currentData.naDevice;
        var data = {
            list : reqData,
            status : '1'
        };

        layer.confirm('确定删除【' + messageTitle + '】么？', {
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



