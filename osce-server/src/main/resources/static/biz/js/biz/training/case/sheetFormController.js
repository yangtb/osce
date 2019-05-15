layui.config({
    base: basePath + '/layui/build/js/'
}).use(['form', 'layer', 'jquery', 'common'], function () {
    var $ = layui.$,
        form = layui.form,
        common = layui.common;

    form.verify({
        naScoreSheet: function (value) {
            if (value.length > 64) {
                return '长度不能超过64个字';
            }
        },
        scorePass: [/^[1-9]\d*$/, '必须是正整数']
    });

    //监听提交
    form.on('submit(addScoreSheet)', function (data) {
        data.field.idCase = idCase;
        layer.load(2);
        $.ajax({
            url: basePath + '/pf/r/case/sheet/save',
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
                    parent._setSheet();
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

