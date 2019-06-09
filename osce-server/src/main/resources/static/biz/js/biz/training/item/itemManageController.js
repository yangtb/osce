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
        elem: '#itemManageTable' //指定原始表格元素选择器（推荐id选择器）
        , id: 'itemManageTableId'
        , height: 'full-50' //容器高度
        , cols: [[
            {type: 'numbers', fixed: true, title: 'R'},
            {checkbox: true, fixed: true},
            {field: 'fgActive', width: 100, title: '状态',fixed: true, templet: '#fgActiveTpl'},
            {field: 'mainItem', minWidth: 250, title: '题干'},
            {field: 'sdItemCa', width: 100, title: '题目类型', templet: '#sdItemCaTpl'},
            {field: 'sdItemLevel', width: 100, title: '难度', templet: '#sdItemLevelTpl'},
            {field: 'gmtCreate', width: 170, title: '创建时间'},
            {fixed: 'right', width: 140, title: '操作', align: 'center', toolbar: '#itemManageBar'}
        ]] //设置表头
        , url: basePath + '/pf/p/item/manage/list'
        , limit: 15
        , even: true
        , limits: [15, 30, 100]
        , page: true
        , where : {
            idItemStore : idItemStore,
            idItemSection : $('#idItemSection option:selected').val()
        }
    });


    form.on('select(idItemSectionFilter)', function(data){
        table.reload('itemManageTableId', {
            height: 'full-50',
            where : {
                idItemStore : idItemStore,
                idItemSection : data.value
            }
        });
    });

    $('#addSection').on('click', function () {
        var y = $(this).offset().top;
        var x = $(this).offset().left;
        common.openOffset('新增目录', basePath + '/pf/p/item/section/form?idItemStore=' + idItemStore,
            350, 190, x, y + 35);
    });

    $('#delSection').on('click', function () {
        var idItemSection =  $('#idItemSection option:selected').val(),
            idItemSectionText =  $('#idItemSection option:selected').text();
        if (!idItemSection) {
            layer.tips('请先选中目录', '#idItemSection', {tips: 1});
            return false;
        }

        var url = basePath + '/pf/r/item/section/del';
        var reqData = new Array();
        reqData.push(idItemSection);

        if (!reqData || reqData.length == 0) {
            return false;
        }
        var data = {
            list: reqData
        };


        layer.confirm('真的要删除目录【' + idItemSectionText + '】么？', {
            title: '删除目录提示',
            resize: false,
            btn: ['确定', '取消'],
            btnAlign: 'c',
            icon: 3,
            offset: [($(this).offset().top + 35) + 'px', $(this).offset().left + 'px'],
        }, function (index) {
            layer.close(index);
            common.commonPost(url, data, '删除', 'delSection', _backFun, true);
        });
        return false;
    });

    var _backFun = function () {
        var idItemSection = $('#idItemSection option:selected').val();
        $("#idItemSection option[value='" + idItemSection + "']").remove();
        form.render();
    }


    $('#addItem').on('click', function () {
        var idItemSection =  $('#idItemSection option:selected').val();
        if (!idItemSection) {
            layer.tips('请先新增目录', '#addSection', {tips: 1});
            return false;
        }
        _addOrEdit("add");
    });

    //监听行双击事件
    table.on('rowDouble(itemManageTableFilter)', function (obj) {
        _addOrEdit("edit", obj.data);
    });

    //监听工具条
    table.on('tool(itemManageTableFilter)', function (obj) {
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
        var idItemSection = $('#idItemSection option:selected').val();
        if (formType == 'add') {
            var index = common.open('新增题目', basePath + '/pf/p/item/detail/form?formType='
                + formType + '&idItemStore=' + idItemStore + '&idItemSection=' + idItemSection, 500, 350);
            layer.full(index)
        } else {
            var index = common.open('新增题目', basePath + '/pf/p/item/detail/form?formType='
                + formType + '&idItemStore=' + idItemStore + '&idItemSection=' + idItemSection, 500, 350, _successFunction(currentEditData));
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
        var url = basePath + '/pf/r/item/exam/del';
        var reqData = new Array();
        reqData.push(currentData.idItem);
        var data = {
            list: reqData
        };
        layer.confirm('确定要删除题干【'+ currentData.mainItem +'】？', {
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
        table.reload('itemManageTableId', {
            height: 'full-50'
        });
    }

});


$(document).ready(function(){
    // 设置目录
    _setSection();
});

var _setSection = function () {
    layui.use(['jquery', 'form','common'],function(){
        var $ = layui.$
            , form = layui.form
            , common = layui.common;
        var bizData = {
            "idItemStore" : idItemStore
        };
        layer.load(2);
        $.ajax({
            url: basePath + '/pf/r/item/section/list',
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
                    $("#idItemSection").empty();
                    $.each(listData, function (index, content) {
                        $('#idItemSection').append("<option value='" + content.idItemSection + "'>" + content.naItemSection + "</option>");
                    });
                    form.render();
                    return true;
                }
            },
            error: function () {
                layer.closeAll('loading');
                common.errorMsg("查询目录失败");
                return false;
            }
        });
    });
}



