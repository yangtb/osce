layui.config({
    base: basePath + '/layui/build/js/'
}).use(['table', 'form', 'laydate', 'upload', 'jquery', 'element', 'common'], function () {
    var $ = layui.$
        , table = layui.table
        , form = layui.form
        , upload = layui.upload
        , common = layui.common
        , element = layui.element
        , laydate = layui.laydate;

    laydate.render({
        elem: '#gmtScrap'
        ,trigger: 'click'
    });

    laydate.render({
        elem: '#gmtStoreIn'
        ,trigger: 'click'
    });

    laydate.render({
        elem: '#gmtRepairEnd'
        ,trigger: 'click'
    });


    //执行渲染
    table.render({
        elem: '#deviceTable' //指定原始表格元素选择器（推荐id选择器）
        , id: 'deviceTableId'
        , height: '510' //容器高度
        , cols: [[
            {type: 'radio', fixed: true},
            {field: 'cdDeviceCase', minWidth: 100, title: '设备编码'},
            {field: 'gmtStoreIn', minWidth: 100, title: '购入时间'},
            {fixed: 'right', width: 60, title: '操作', align: 'center', toolbar: '#deviceBar'}
        ]] //设置表头
        , url: basePath + '/pf/p/model/device/list'
        , where: {
            idDevice: idDevice
        }
        , limit: 1000
        , page: false
    });

    form.verify({
        commonLength255: function (value) {
            if (value.length > 255) {
                return '长度不能超过255个字';
            }
        }
    });


    // 上传头像
    upload.render({
        elem: '#LAY_avatarUpload'
        , url: basePath + '/upload'
        , field: 'file'
        , accept: 'images' //普通文件
        , exts: 'jpg|png|bmp|jpeg'
        , before: function (obj) {
            layer.msg('正在上传', {
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
            $('#picDiviceCase').val(res.data.path);
            layer.closeAll();
        }
        , error: function () {
            layer.closeAll();
        }
    });

    $('#reviewPhoto').on('click', function () {
        var i = $("#picDiviceCase").val();
        if (!i) {
            layer.tips("请先上传图片", '#reviewPhoto', {
                tips: [1, '#FF5722']
            });
            return;
        }
        layui.layer.photos({
            photos: {
                title: "查看图片",
                data: [{
                    src: i
                }]
            },
            shade: .01,
            closeBtn: 1,
            anim: 5
        })
    });

    $('#add').on('click', function () {
        $('#reset').click();
        $('#save').click();
    });

    form.on('submit(addModelDevice)', function (data) {
        if (!data.field.fgScrap) {
            data.field.fgScrap = '0';
        }
        data.field.idDevice = idDevice;
        common.commonPost(basePath + '/pf/r/model/device/save', data.field, '保存', '', _callBack);
        return false;
    });

    var _callBack = function (data) {
        _tableReload();
        $('#idDeviceCase').val(data.data);
    }

    //监听工具条
    table.on('tool(deviceTableFilter)', function (obj) {
        var data = obj.data;
        if (obj.event === 'del') {
            _delAnswer(data);
        }
    });

    var _delAnswer = function (currentData) {
        var url = basePath + '/pf/r/model/device/del';
        var reqData = new Array();
        var name = '【' + currentData.cdDeviceCase + '】';
        reqData.push(currentData.idDeviceCase);
        var data = {
            list: reqData,
            status: '1',
            extId: currentData.idDeviceCase
        };
        
        layer.confirm('真的要删除设备编码：' + name + '么？', {
            title: '删除设备提示',
            resize: false,
            btn: ['确定', '取消'],
            btnAlign: 'c',
            icon: 3
        }, function (index) {
            common.commonPost(url, data, '删除', '', _tableReload);
            layer.closeAll(index);
            $('#reset').click();
            _tableReload();
        })
    };

    var _tableReload = function () {
        table.reload('deviceTableId', {
            where: {
                idDevice: idDevice
            }
            , height: '510'
        });
    };

    //单击行选中radio
    table.on('row(deviceTableFilter)', function (obj) {
        obj.tr.addClass('layui-table-click').siblings().removeClass('layui-table-click');//选中行样式
        obj.tr.find('input[lay-type="layTableRadio"]').prop("checked", true);
        form.render('radio');
        rowClick(obj)
    });

    function rowClick(obj) {
        $('#reset').click();
        $("#deviceForm").autofill(obj.data);
        layui.use('form', function () {
            layui.form.render();
        });
    };

    $('#fault').on('click', function () {
        if (!$('#idDeviceCase').val()) {
            layer.tips("请先保存设备或在左侧选中设备", '#fault', {tips: 1});
            return;
        }
        common.open('故障登记', basePath + '/pf/p/model/device/fault/form?idDeviceCase=' + $('#idDeviceCase').val(),
            560, 380, null, 2);
    });

    $('#repair').on('click', function () {
        if (!$('#idDeviceCase').val()) {
            layer.tips("请先保存设备或在左侧选中设备", '#repair', {tips: 1});
            return;
        }
        common.open('维修登记', basePath + '/pf/p/model/device/repair/form?idDeviceCase=' + $('#idDeviceCase').val(),
            560, 380, null, 2);
    });

});

