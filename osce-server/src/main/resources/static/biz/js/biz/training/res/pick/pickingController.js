layui.config({
    base: basePath + '/layui/plugins/'
}).extend({
    index: 'lib/index', //主入口模块
    tableMerge: 'tableMerge',
    formSelects: 'formSelects-v4'
}).use(['layer', 'index', 'tableMerge', 'form', 'formSelects'], function () {
    var $ = layui.$
        , table = layui.table
        , form = layui.form
        , tableMerge = layui.tableMerge
        , formSelects = layui.formSelects;


    //执行渲染
    var tableIns = table.render({
        elem: '#tpPickingTable' //指定原始表格元素选择器（推荐id选择器）
        //, id: 'tpPickingTableId'
        , height: 'full-120' //容器高度
        , toolbar: true
        , title: '领料计划'
        , cols: [[
            {type: 'numbers', fixed: true, title: 'R'},
            //{field: 'naPlan', merge: true, width: 170, title: '考试名称', fixed: true},
            {field: 'planDay', merge: true, width: 110, title: '日期', fixed: true},
            {field: 'planSection', merge: ['planDay', 'planSection'], width: 200, title: '时段'},
            {field: 'naRoom', width: 100, title: '房间', merge: ['planDay', 'planSection', 'naRoom']},
            {field: 'naSkillCase', width: 160, title: '技能', merge: ['planDay', 'planSection', 'naRoom', 'naSkillCase']},
            {field: 'numSkillCase', width: 100, title: '训练次数'},
            {field: 'fgConsumables', width: 100, title: '设备类型', templet: '#fgConsumablesTpl'},
            {field: 'naDevice', width: 170, title: '设备'},
            {field: 'sdDeviceUnit', width: 80, title: '单位'},
            {field: 'numDevice', width: 120, title: '单次消耗数量'},
            {field: 'numPlan', width: 120, title: '总消耗数量'},
            {field: 'right1', width: 80, title: '操作', fixed: 'right', align: 'center', toolbar: '#rightBar'},
            {field: 'fgPicked', width: 120, title: '实领数量', templet: '#fgPickedTpl', fixed: 'right'}
        ]] //设置表头
        , url: basePath + '/pf/p/pick/list'
        , limit: 20
        , limits: [20, 50, 100]
        , page: true
        , done: function () {
            tableMerge.render(this)
        }
    });

    form.on('radio(fgPickingFilter)', function (data) {
        _tpPickingTableReload(data.value)
    });

    var _tpPickingTableReload = function (v_fgPicking) {
        if (v_fgPicking == -1) {
            v_fgPicking = null;
        }
        tableIns.reload({
            where: {
                fgPicking: v_fgPicking
            }
            , page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    }

    //监听工具条
    table.on('tool(tpPickingTableFilter)', function (obj) {
        var data = obj.data;
        if (obj.event === 'add') {
            console.log(data);
            // 消耗品
            if (data.fgConsumables == '1') {
                //layer.tips('请填写实领数量', obj.othis);
                return;
            } else {
                // 非消耗品
                if (data.idDevice) {
                    loadDevice(data.idTpPicking, data.idDevice);
                }
            }
        }
    });


    function loadDevice(v_idTpPicking, v_idDevice) {
        var reqData = {
            idTpPicking: v_idTpPicking,
            idDevice: v_idDevice
        }
        layer.load(2);
        $.ajax({
            url: basePath + '/pf/r/pick/device/case/list',
            type: 'post',
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify(reqData),
            success: function (data) {
                layer.closeAll('loading');
                if (data.code != 0) {
                    layer.msg(data.msg);
                    return false;
                } else {
                    openDevicePage(data.data, v_idTpPicking);
                    return true;
                }
            },
            error: function () {
                layer.closeAll('loading');
                layer.msg("网络异常");
                return false;
            }
        });
    }

    function openDevicePage(data, v_idTpPicking) {
        if (!data) {
            return;
        }
        // 获取设备
        layer.open({
            title: '设备编码',
            type: 1,
            closeBtn: 0, //不显示关闭按钮
            anim: 5,
            shadeClose: true, //开启遮罩关闭
            resize: false,
            area: ['320px', '320px'],
            btn: ['确定', '关闭'],
            zIndex: 999,
            content: '<div style="margin: 10px;">\n' +
                '       <select name="idDeviceCase" id="idDeviceCase" xm-select="select1"></select>' +
                '    </div>'
            , yes: function () {
                var idDeviceCaseArr = formSelects.value('select1', 'val');
                // console.log(idDeviceCaseArr)
                if (idDeviceCaseArr.length == 0) {
                    layer.msg('请选择设备编码');
                    return;
                }
                saveTpPickingCase(idDeviceCaseArr, v_idTpPicking);
                layer.closeAll();
            }, success: function (layero, index) {
                formSelects.data('select1', 'local', {
                    arr: data
                });
            }
        });
    }

    // 保存 计划_领料实例
    function saveTpPickingCase(idDeviceCaseArr, v_idTpPicking) {
        var reqData = {
            idTpPicking: v_idTpPicking,
            idDeviceCases: idDeviceCaseArr
        }
        layer.load(2);
        $.ajax({
            url: basePath + '/pf/r/pick/case/save',
            type: 'post',
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify(reqData),
            success: function (data) {
                layer.closeAll('loading');
                if (data.code != 0) {
                    layer.msg(data.msg);
                    return false;
                } else {
                    layer.msg("保存成功");
                    $(".layui-laypage-btn").click();
                    return true;
                }
            },
            error: function () {
                layer.closeAll('loading');
                layer.msg("网络异常");
                return false;
            }
        });
    }

});


function updatePickedNum(event, idTpPicking, numPlan) {
    if (!event.target.value) {
        return;
    }
    if (event.target.value > numPlan) {
        layer.tips('不能超过总消耗数量', event.target);
        return;
    }
    var reqData = {
        idTpPicking: idTpPicking,
        fgPicked: event.target.value
    }
    layer.load(2);
    $.ajax({
        url: basePath + '/pf/r/pick/edit/pickedNum',
        type: 'post',
        dataType: 'json',
        contentType: "application/json",
        data: JSON.stringify(reqData),
        success: function (data) {
            layer.closeAll('loading');
            if (data.code != 0) {
                layer.msg(data.msg);
                return false;
            } else {
                return true;
            }
        },
        error: function () {
            layer.closeAll('loading');
            layer.msg("网络异常");
            return false;
        }
    });
}


