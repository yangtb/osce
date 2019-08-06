layui.config({
    base: basePath + '/layui/build/js/'
}).use(['form', 'layer', 'jquery', 'common'], function () {
    var $ = layui.$,
        form = layui.form,
        common = layui.common;

    form.verify({
        naItemSection: function (value) {
            if (value.length > 64) {
                return '长度不能超过64个字';
            }
        },
        sort: [/^[1-9]\d*$/, '排序必须是正整数']
    });

    //监听提交
    form.on('submit(addSection)', function (data) {
        if (!data.field.fgActive) {
            data.field.fgActive = '0';
        }
        data.field.idItemStore = idItemStore;
        layer.load(2);
        $.ajax({
            url: basePath + '/pf/r/td/item/section/save',
            type: 'post',
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify(data.field),
            success: function (data) {
                layer.closeAll('loading');
                if (data.code != 0) {
                    common.errorMsg(data.msg);
                    return false;
                } else {
                    common.sucMsg("保存成功");
                    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                    parent.layer.close(index); //再执行关闭
                    //刷新父页面下拉目录
                    parent._setSection();
                    return true;
                }
            },
            error: function () {
                layer.closeAll('loading');
                common.errorMsg("保存失败");
                return false;
            }
        });
        return false;
    });
});

