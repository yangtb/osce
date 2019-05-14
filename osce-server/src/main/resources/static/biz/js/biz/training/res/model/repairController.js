layui.config({
    base: basePath + '/layui/build/js/'
}).use(['layer', 'table', 'jquery', 'laydate', 'common'], function () {
    var $ = layui.$,
        table = layui.table,
        common = layui.common,
        laydate = layui.laydate;

    //执行渲染
    table.render({
        elem: '#repairTable' //指定原始表格元素选择器（推荐id选择器）
        , id: 'repairTableId'
        , height: 'full-60' //容器高度
        , cols: [[
            {type: 'numbers'},
            {checkbox: true},
            {field: 'gmtRepair', event: 'date', width: 120, title: '维修时间'},
            {field: 'desRepair', edit: 'text', minWidth: 140, title: '维修结果'}
        ]] //设置表头
        //, url: basePath + '/pf/p/model/device/repair/list'
        , limit: 500
        , even: true
        , page: false
        , where: {
            idDeviceCase: idDeviceCase
        }
        , data: []
    });

    var _selectRepair = function () {
        var reqData = {
            idDeviceCase: idDeviceCase
        }
        layer.load(2);
        $.ajax({
            url: basePath + '/pf/r/model/device/repair/list',
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
                    table.reload('repairTableId', {
                        data: data.data
                    });
                    return true;
                }
            },
            error: function () {
                layer.closeAll('loading');
                common.errorMsg("查询维修登记失败");
                return false;
            }
        });
    }

    $(document).ready(function () {
        _selectRepair();
    });

    $('#add').on('click', function () {
        var oldData = table.cache["repairTableId"];
        if (!oldData) {
            oldData = [];
        }
        var bizData = {
            "idDeviceRepair": "",
            "idDeviceCase": idDeviceCase,
            "desRepair": "",
            "gmtRepair": ""
        }
        oldData.push(bizData);
        table.reload('repairTableId', {
            data: oldData
        });
    });

    //监听单元格事件
    table.on('tool(repairTableFilter)', function (obj) {
        var data = obj.data;
        var newdata = {};
        if (obj.event === 'date') {
            var field = $(this).data('field');
            laydate.render({
                elem: this.firstChild
                , show: true //直接显示
                , closeStop: this
                , done: function (value, date) {
                    //console.log(value)
                    newdata[field] = value;
                    obj.update(newdata);
                    //alert(JSON.stringify(table.cache["repairTableId"]))
                }
            });
        }
    });

    $("#del").on('click', function () {
        var checkStatus = table.checkStatus('repairTableId')
            , data = checkStatus.data;
        if (data.length == 0) {
            layer.tips('请先选中一行记录', '#del', {tips: 1});
            return;
        }
        console.log(data)
        _delRepair(data);
    });

    var _delRepair = function (currentData) {
        var url = basePath + '/pf/r/model/device/repair/del';
        var reqData = new Array();
        $.each(currentData, function (index, content) {
            if (content.idRepair) {
                reqData.push(content.idRepair);
            }
        });

        if (!reqData || reqData.length == 0) {
            _selectRepair();
            return false;
        }

        var data = {
            list: reqData
        };
        common.commonPost(url, data, '删除', 'del', _selectRepair, true);
        return false;
    };

    $("#save").on('click', function () {
        var tableData = table.cache["repairTableId"];
        if (!tableData) {
            common.errorMsg("请先录入维修");
            return false;
        }
        var flag = false, desRepairMax = false;
        $.each(tableData,function(index, content) {
            if (!content.gmtRepair || !content.desRepair) {
                flag = true;
                return;
            }
            if (content.desRepair && content.desRepair.length > 255) {
                desRepairMax = true;
            }
        });
        if (flag) {
            common.errorMsg("请完整填写维修时间及维修结果");
            return false;
        }
        if (desRepairMax) {
            common.errorMsg("维修结果不能超过255字");
            return false;
        }
        var reqData = {
            list : tableData
        }
        common.commonPost(basePath + '/pf/r/model/device/repair/save', reqData, '保存', null, _selectRepair, true);
        return false;
    });



});

