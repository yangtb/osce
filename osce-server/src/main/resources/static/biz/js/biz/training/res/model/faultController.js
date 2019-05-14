layui.config({
    base: basePath + '/layui/build/js/'
}).use(['layer', 'table', 'jquery', 'laydate', 'common'], function () {
    var $ = layui.$,
        table = layui.table,
        common = layui.common,
        laydate = layui.laydate;

    //执行渲染
    table.render({
        elem: '#faultTable' //指定原始表格元素选择器（推荐id选择器）
        , id: 'faultTableId'
        , height: 'full-60' //容器高度
        , cols: [[
            {type: 'numbers'},
            {checkbox: true},
            {field: 'gmtFault', event: 'date', width: 120, title: '故障时间'},
            {field: 'desFault', edit: 'text', minWidth: 140, title: '故障描述'}
        ]] //设置表头
        //, url: basePath + '/pf/p/model/device/fault/list'
        , limit: 500
        , even: true
        , page: false
        , where: {
            idDeviceCase: idDeviceCase
        }
        , data: []
    });

    var _selectFault = function () {
        var reqData = {
            idDeviceCase: idDeviceCase
        }
        layer.load(2);
        $.ajax({
            url: basePath + '/pf/r/model/device/fault/list',
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
                    table.reload('faultTableId', {
                        data: data.data
                    });
                    return true;
                }
            },
            error: function () {
                layer.closeAll('loading');
                common.errorMsg("查询故障登记失败");
                return false;
            }
        });
    }

    $(document).ready(function () {
        _selectFault();
    });

    $('#add').on('click', function () {
        var oldData = table.cache["faultTableId"];
        if (!oldData) {
            oldData = [];
        }
        var bizData = {
            "idDeviceFault": "",
            "idDeviceCase": idDeviceCase,
            "desFault": "",
            "gmtFault": ""
        }
        oldData.push(bizData);
        table.reload('faultTableId', {
            data: oldData
        });
    });

    //监听单元格事件
    table.on('tool(faultTableFilter)', function (obj) {
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
                    //alert(JSON.stringify(table.cache["faultTableId"]))
                }
            });
        }
    });

    $("#del").on('click', function () {
        var checkStatus = table.checkStatus('faultTableId')
            , data = checkStatus.data;
        if (data.length == 0) {
            layer.tips('请先选中一行记录', '#del', {tips: 1});
            return;
        }
        console.log(data)
        _delFault(data);
    });

    var _delFault = function (currentData) {
        var url = basePath + '/pf/r/model/device/fault/del';
        var reqData = new Array();
        $.each(currentData, function (index, content) {
            if (content.idDeviceFault) {
                reqData.push(content.idDeviceFault);
            }
        });

        if (!reqData || reqData.length == 0) {
            _selectFault();
            return false;
        }

        var data = {
            list: reqData
        };
        common.commonPost(url, data, '删除', 'del', _selectFault, true);
        return false;
    };

    $("#save").on('click', function () {
        var tableData = table.cache["faultTableId"];
        if (!tableData) {
            common.errorMsg("请先录入故障");
            return false;
        }
        var flag = false, desFaultMax = false;
        $.each(tableData,function(index, content) {
            if (!content.gmtFault || !content.desFault) {
                flag = true;
                return;
            }
            if (content.desFault && content.desFault.length > 255) {
                desFaultMax = true;
                return;
            }
        });
        if (flag) {
            common.errorMsg("请完整填写故障时间及故障描述");
            return false;
        }
        if (desFaultMax) {
            common.errorMsg("故障描述不能超过255字");
            return false;
        }
        var reqData = {
            list : tableData
        }
        common.commonPost(basePath + '/pf/r/model/device/fault/save', reqData, '保存', null, _selectFault, true);
        return false;
    });



});

