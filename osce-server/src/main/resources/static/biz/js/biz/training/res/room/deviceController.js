layui.config({
    base: basePath + '/layui/build/js/'
}).use(['table', 'form', 'jquery', 'common'], function () {
    var $ = layui.$
        , table = layui.table
        , form = layui.form
        , common = layui.common;

    //执行渲染
    table.render({
        elem: '#deviceTable' //指定原始表格元素选择器（推荐id选择器）
        , id: 'deviceTableId'
        , height: 'full-50' //容器高度
        , cols: [[
            {checkbox: true, fixed: true},
            {field: 'fgActive', width: 100, title: '状态',fixed: true, templet: '#fgActiveTpl'},
            {field: 'naRoom', minWidth: 170, title: '房号', fixed: true},
            {field: 'desRoom', minWidth: 200, title: '描述'},
            {field: 'deviceNum', minWidth: 100, title: '设备数量', align: "right"},
            {field: 'gmtCreate', minWidth: 170, title: '创建时间'},
            {fixed: 'right', width: 120, title: '操作', align: 'center', toolbar: '#deviceBar'}
        ]] //设置表头
        , url: basePath + '/pf/p/device/list'
        , limit: 15
        , even: true
        , limits: [15, 30, 100]
        , page: true
    });

    //监听工具条
    table.on('tool(deviceTableFilter)', function (obj) {
        var data = obj.data;
        if (obj.event === 'edit') {
            _addOrEdit("edit", data);
        }
    });

    //监听提交
    form.on('submit(deviceSearchFilter)', function (data) {
        table.reload('deviceTableId', {
            height: 'full-50'
            , page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    });

    $('#add').on('click', function () {
        _addOrEdit("add");
    });

    $('#edit').on('click', function () {
        var checkStatus = table.checkStatus('deviceTableId')
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
            common.open('新增固定设备', basePath + '/pf/p/room/device/form?formType=' + formType, 430, 300);
        } else {
            common.open('编辑固定设备', basePath + '/pf/p/room/device/form?formType=' + formType, 430, 300, _successFunction(currentEditData));
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
    table.on('rowDouble(deviceTableFilter)', function (obj) {
        _addOrEdit("edit", obj.data);
    });

    $("#del").on('click', function () {
        var checkStatus = table.checkStatus('deviceTableId')
            , data = checkStatus.data;
        if (data.length == 0) {
            layer.tips('请先选中一行记录', '#del', {tips: 1});
            return;
        }
        _delGrade(data);
    });

    var _delGrade = function (currentData) {
        var url = basePath + '/pf/r/device/del';
        var reqData = new Array();
        var messageTitle = '';
        var delFlag = false, delMsg = '';
        $.each(currentData, function (index, content) {
            if (messageTitle) {
                messageTitle += ', ';
            }
            messageTitle += '【' + content.naGrade + '】';
            reqData.push(content.idGrade);

            if (content.deviceNum > 0) {
                delFlag = true;
                delMsg += '【' + content.naGrade + '】';
            }
        });

        if(delFlag) {
            layer.alert(delMsg + '<br><span style="color: red; font-weight: bold">学届下已有班级，不允许删除，请重新选择操作</span>', {
                title: '删除学届提示',
                resize: false,
                btn: ['确定']
            });
            return false;
        }

        var data = {};
        data.list = reqData;
        data.status = '1';
        layer.confirm('确定删除' + messageTitle + '么？', {
            title: '删除学届提示',
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
                    _deviceTableReload();
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

    var _deviceTableReload = function () {
        table.reload('deviceTableId', {
            where: {
                //type: type
            },
            height: 'full-50'
        });
    }

    //监听删除操作
    form.on('switch(fgActiveCheckFilter)', function (obj) {
        var reqData = new Array();
        var data = {};
        reqData.push(this.value);
        data.list = reqData;
        if (obj.elem.checked) {
            data.status = '1';
        } else {
            data.status = '0';
        }
        common.commonPost(basePath + '/pf/r/device/updateStatus', data, '设置', obj.othis);
    });

});


