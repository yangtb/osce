layui.config({
    base: basePath + '/layui/build/js/'
}).use(['table', 'form', 'layer', 'jquery', 'common'], function () {
    var $ = layui.$
        , table = layui.table
        , form = layui.form
        , common = layui.common
        , layer = layui.layer;

    //执行渲染
    table.render({
        elem: '#sheetTable' //指定原始表格元素选择器（推荐id选择器）
        , id: 'sheetTableId'
        , height: 'full-50' //容器高度
        , cols: [[
            {type: 'numbers', fixed: true, title: 'R'},
            {checkbox: true, fixed: true},
            {field: 'fgActive', width: 100, title: '状态',fixed: true, templet: '#fgActiveTpl'},
            {field: 'naScoreItem', minWidth:150, title: '评分项'},
            {field: 'sdScoreItemCa', width: 100, title: '分类', templet: '#sdScoreItemTpl'},
            {field: 'desScoreItem', minWidth: 230, title: '内容'},
            {field: 'gmtCreate', width: 170, title: '创建时间'},
            {fixed: 'right', width: 140, title: '操作', align: 'center', toolbar: '#sheetBar'}
        ]] //设置表头
        , url: basePath + '/pf/p/case/item/list'
        , limit: 15
        , even: true
        , limits: [15, 30, 100]
        , page: true
        , where : {
            idCase : idCase
        }
    });

    $('#addSheet').on('click', function () {
        var y = $(this).offset().top;
        var x = $(this).offset().left;
        common.openOffset('新增考评表', basePath + '/pf/p/case/sheet/page?idCase=' + idCase,
            350, 190, x, y + 35);
    });

    $('#delSheet').on('click', function () {
        var idScoreSheet =  $('#idScoreSheet option:selected').val(),
            idScoreSheetText =  $('#idScoreSheet option:selected').text();
        if (!idScoreSheet) {
            layer.tips('请先选中考评表', '#idScoreSheet', {tips: 1});
            return false;
        }

        var url = basePath + '/pf/r/case/sheet/del';
        var reqData = new Array();
        reqData.push(idScoreSheet);

        if (!reqData || reqData.length == 0) {
            return false;
        }
        var data = {
            list: reqData,
            status : 1
        };


        layer.confirm('真的要删除考评表【' + idScoreSheetText + '】么？', {
            title: '删除提示',
            resize: false,
            btn: ['确定', '取消'],
            btnAlign: 'c',
            icon: 3,
            offset: [($(this).offset().top + 35) + 'px', $(this).offset().left + 'px'],
        }, function (index) {
            layer.close(index);
            common.commonPost(url, data, '删除', 'delSheet', _backFun, true);
        });
        return false;
    });

    var _backFun = function () {
        var idScoreSheet = $('#idScoreSheet option:selected').val();
        $("#idScoreSheet option[value='" + idScoreSheet + "']").remove();
        form.render();
    }


    $('#addScoreItem').on('click', function () {
        var idScoreSheet =  $('#idScoreSheet option:selected').val();
        if (!idScoreSheet) {
            layer.tips('请先新增考评表', '#addSheet', {tips: 1});
            return false;
        }
        _addOrEdit("add");
    });

    //监听行双击事件
    table.on('rowDouble(sheetTableFilter)', function (obj) {
        _addOrEdit("edit", obj.data);
    });

    //监听工具条
    table.on('tool(sheetTableFilter)', function (obj) {
        var data = obj.data;
        if (obj.event === 'add') {
            _addOrEdit("add", data);
        } else if (obj.event === 'edit') {
            _addOrEdit("edit", data);
        } else if (obj.event === 'del') {
            _delItem(data);
        }
    });

    var _addOrEdit = function (formType, currentEditData) {
        var idScoreSheet = $('#idScoreSheet option:selected').val();
        if (formType == 'add') {
            var index = common.open('新增题目', basePath + '/pf/p/case/item/form?formType='
                + formType + '&idCase=' + idCase + '&idScoreSheet=' + idScoreSheet, 500, 350);
            layer.full(index)
        } else {
            var index = common.open('新增题目', basePath + '/pf/p/case/item/form?formType='
                + formType + '&idCase=' + idCase + '&idScoreSheet=' + idScoreSheet, 500, 350, _successFunction(currentEditData));
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

    var _delItem = function (currentData) {
        var url = basePath + '/pf/r/case/item/del';
        var reqData = new Array();
        reqData.push(currentData.idScoreItem);
        var data = {
            list: reqData,
            status : 1
        };
        layer.confirm('确定要删除评分项【'+ currentData.naScoreItem +'】？', {
            title: '提示',
            resize: false,
            btn: ['确定', '取消'],
            btnAlign: 'c',
            icon: 3,
        }, function (index) {
            layer.close(index);
            return common.commonPost(url, data, '删除', null, _delItemBack, true);
        });
        return false;
    };

    var _delItemBack = function () {
        table.reload('sheetTableId', {
            height: 'full-50'
        });
    }

});


$(document).ready(function(){
    // 设置考评表
    _setSheet();
});

var _setSheet = function () {
    layui.use(['jquery', 'form','common'],function(){
        var $ = layui.$
            , form = layui.form
            , common = layui.common;
        var bizData = {
            "idCase" : idCase
        };
        layer.load(2);
        $.ajax({
            url: basePath + '/pf/r/case/sheet/list',
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
                    var listData = data.data;
                    $("#idScoreSheet").empty();
                    $.each(listData, function (index, content) {
                        $('#idScoreSheet').append("<option value='" + content.idScoreSheet + "'>" + content.naScoreSheet + "</option>");
                    });
                    form.render();
                    return true;
                }
            },
            error: function () {
                layer.closeAll('loading');
                common.errorMsg("查询考评表失败");
                return false;
            }
        });
    });
}



