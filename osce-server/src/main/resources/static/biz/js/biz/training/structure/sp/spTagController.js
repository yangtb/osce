layui.config({
    base: basePath + '/layui/build/js/'
}).use(['layer', 'table', 'jquery', 'common'], function () {
    var $ = layui.$,
        table = layui.table,
        common = layui.common;

    //执行渲染
    table.render({
        elem: '#spTagTable' //指定原始表格元素选择器（推荐id选择器）
        , id: 'spTagTableId'
        , height: 'full-60' //容器高度
        , cols: [[
            {type: 'numbers'},
            {checkbox: true},
            {field: 'descript', minWidth: 140, title: '标签内容'}
        ]] //设置表头
        , url: basePath + '/pf/p/sp/tag/list'
        , limit: 100
        , even: true
        , page: false
    });


    $('#add').on('click', function () {
        layer.prompt({title: '请输入标签名称', formType: 2}, function(text, index){
            if (text.length > 255) {
                layer.msg("标签名称不能超过255", {icon: 5});
                return false;
            }
            var bizData = {"descript" : text};
            // 增加标签
            $.ajax({
                url: basePath + '/pf/r/sp/tag/add',
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
                        table.reload('spTagTableId', {
                            height: 'full-60'
                        });
                        return true;
                    }
                },
                error: function () {
                    common.errorMsg("保存失败");
                    return false;
                }
            });
            layer.close(index);
        });
    });

    $("#del").on('click', function () {
        var checkStatus = table.checkStatus('spTagTableId')
            , data = checkStatus.data;
        if (data.length == 0) {
            layer.tips('请先选中一行记录', '#del', {tips: 1});
            return;
        }
        _delSpTag(data);
    });

    var _delSpTag = function (currentData) {
        var url = basePath + '/pf/r/sp/tag/del';
        var reqData = new Array();
        $.each(currentData, function (index, content) {
            reqData.push(content.idSpTag);
        });

        var data = {
            list : reqData
        };
        common.commonPost(url, data, '删除');
        table.reload('spTagTableId', {
            height: 'full-60'
        });
    }


});

