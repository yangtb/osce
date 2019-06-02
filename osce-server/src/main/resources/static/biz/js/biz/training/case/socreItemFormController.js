layui.config({
    base: basePath + '/layui/build/js/'
}).extend({
    numinput: 'numinput.min'
}).use(['table', 'form', 'jquery', 'element', 'common', 'numinput'], function () {
    var $ = layui.$
        , table = layui.table
        , form = layui.form
        , common = layui.common
        , numinp = layui.numinput
        , element = layui.element;

    numinp.init({
        rightBtns: true
    });

    form.verify({
        commonLength64: function (value) {
            if (value.length > 64) {
                return '长度不能超过64个字';
            }
        },
        commonLength255: function (value) {
            if (value && value.length > 255) {
                return '长度不能超过255个字';
            }
        },
        sort: function (value) {
            if (value && !value.match(/^[1-9]\d*$/)) {
                return '必须是正整数';
            }
        }
    });

    form.on('submit(addItem)', function (data) {
        var url = basePath + '/pf/r/case/item/save';
        data.field.idCase = idCase;
        data.field.idScoreSheet = idScoreSheet;
        return _addItem(url, data.field, formType, 'sheetTableId', '保存');
    });

    var _addItem = function (url, bizData, formType, tableId, msg) {
        layer.load(2);
        $.ajax({
            url: url,
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
                    common.sucMsg(msg + "成功");
                    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                    parent.layer.close(index); //再执行关闭
                    //刷新父页面table
                    if (formType == 'edit') {
                        parent.layui.common.refreshCurrentPage();
                    } else {
                        $('#idScoreItem').val(data.data);
                        parent.layui.table.reload(tableId, {
                            height: 'full-68'
                        });
                    }
                    return false;
                }
            },
            error: function () {
                layer.closeAll('loading');
                common.errorMsg(msg + "失败");
                return false;
            }
        });
        return false;
    }

});

function fullForm(data) {
    $(document).ready(function(){
        $("#itemForm").autofill(data);
        layui.use(['form'],function(){
            var form = layui.form;
            // 渲染表单
            form.render();
        });
    });
}

