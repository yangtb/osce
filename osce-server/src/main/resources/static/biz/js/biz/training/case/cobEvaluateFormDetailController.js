layui.config({
    base: basePath + '/layui/build/js/'
}).use(['form', 'layer', 'jquery', 'common'], function () {
    var $ = layui.$,
        form = layui.form,
        common = layui.common;

    form.verify({
        commonLength64: function (value) {
            if (value.length > 64) {
                return '长度不能超过64个字';
            }
        }
    });

    //监听提交
    form.on('submit(addCobEvaluateDetail)', function (data) {
        data.field.idCobEvaluate = idCobEvaluate;
        layer.load(2);
        $.ajax({
            url: basePath + '/pf/r/cob/evaluate/detail/save',
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
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
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

