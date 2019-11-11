layui.config({
    base: basePath + '/layui/build/js/'
}).use(['table', 'form', 'jquery', 'common'], function () {
    var $ = layui.$
        , table = layui.table
        , form = layui.form
        , common = layui.common;


    table.render({
        elem: '#test1'
        , cellMinWidth: 80 //时间、上午&下午、站点、考场、房间、试卷名称
        , cols: [[
            {field: 'planDay', width: 110, title: '时间', align: 'center'}
            , {
                field: 'timeFlag', minwidth: 100, title: '上午/下午', align: 'center', templet: function (d) {
                    return d.timeFlag == 1 ? '上午' : '下午';
                }
            }
            , {field: 'naStation', minwidth: 75, title: '站点', align: 'center'}
            , {field: 'naArea', minwidth: 75, title: '考场', align: 'center'}
            , {field: 'idRoomText', minwidth: 75, title: '房间', align: 'center'}
            , {field: 'idPaperText', minwidth: 100, title: '试卷名称', align: 'center'}
        ]]
        , url: basePath + '/pf/p/plan/sp/station/list'
        , where: {
            idPlan: idPlan
        }
    });


    $("#querySp").on('click', function () {
        var conditionSp = new Array();
        for (var i = 1; i <= 9; i++) {
            var idSpTag = $('#type-' + (i - 1)).attr('data-idSpTag');
            var spData = {
                idSpTag: idSpTag,
                type: $('#type-' + (i - 1)).val(),
                tagName : $('#tagName-' + idSpTag).text(),
                condition: $('#condition-' + idSpTag).val(),
                value: $('#spTag-' + idSpTag).val()
            }
            conditionSp.push(spData)
        }
        console.log(conditionSp)
        table.reload('test2Id', {
            where : {
                conditions: conditionSp
            }
        });
    });


    table.render({
        elem: '#test2'
        , id: 'test2Id'
        , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
        , cols: [[
            {type: 'checkbox'}
            , {field: 'idcard', width: 180, title: '身份证号', align: 'center'}
            , {field: 'realName', minwidth: 120, title: '姓名', align: 'center'}
            , {
                field: 'sex', width: 100, title: '性别', align: 'center', templet: function (d) {
                    return d.sex == 1 ? '男' : '女';
                }
            }
            , {field: 'height', width: 100, title: '身高(cm)', align: 'center'}
            , {field: 'weight', width: 100, title: '体重(kg)', align: 'center'}
        ]]
        , method : 'post'
        , contentType: "application/json"
        , url: basePath + '/pf/r/plan/sp/list'
        , limit: 15
        , even: true
        , limits: [15, 30, 100]
        , page: true
        , height: '290' //容器高度
        /*, parseData: function(res){ //res 即为原始返回的数据
            return {
                "code": res.status, //解析接口状态
                "msg": res.message, //解析提示文本
                "count": res.total, //解析数据长度
                "data": res.data.item //解析数据列表
            };
        }*/
    });

    $('#addSpCache').on('click', function () {
        var checkStatus = table.checkStatus('test2Id')
            , data = checkStatus.data;
        if (data.length == 0) {
            layer.tips('请先选中一行记录', '#addSpCache', {tips: 4});
            return;
        }
        var reqData = new Array();
        $.each(data, function (index, content) {
            var bizData = {
                idPlan: idPlan,
                idUser: content.userId
            }
            reqData.push(bizData);
        });
        common.commonPost(basePath + '/pf/r/plan/sp/cache/add', reqData, '添加', null, _addOptionBack, true);
    });

    function _addOptionBack() {
        table.reload('test3Id');
    }

    table.render({
        elem: '#test3'
        , id: 'test3Id'
        , cellMinWidth: 100 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
        , cols: [[
            {type: 'checkbox'}
            , {field: 'idcard', width: 180, title: '身份证号', align: 'center'}
            , {field: 'realName', minwidth: 120, title: '姓名', align: 'center'}
            , {
                field: 'sex', width: 100, title: '性别', align: 'center', templet: function (d) {
                    return d.sex == 1 ? '男' : '女';
                }
            }
            , {field: 'height', width: 100, title: '身高(cm)', align: 'center'}
            , {field: 'weight', width: 100, title: '体重(kg)', align: 'center'}
        ]]
        , url: basePath + '/pf/p/plan/sp/cache/list?idPlan=' + idPlan
        , height: '290' //容器高度
    });

    $("#delSpCache").on('click', function () {
        var checkStatus = table.checkStatus('test3Id')
            , data = checkStatus.data;
        if (data.length == 0) {
            layer.tips('请先选中一行记录', '#delSpCache', {tips: 4});
            return;
        }
        _delOption(data);
    });

    var _delOption = function (currentData) {
        var url = basePath + '/pf/r/plan/sp/cache/del';
        var reqData = new Array();
        $.each(currentData, function (index, content) {
            reqData.push(content.idTpSpCache);
        });

        var data = {
            list: reqData,
            extId: idPlan
        };
        common.commonPost(url, data, '删除', 'delSpCache', _delOptionBack, true);
        return false;
    };

    function _delOptionBack() {
        table.reload('test3Id');
    }

    $('#spManage').on('click', function () {
        var index = common.openParent('SP管理', basePath + '/pf/p/sp/page?showFlag=2', 880, 600);
        //layer.full(index);
    });

});






