layui.config({
    base: basePath + '/layui/build/js/'
}).use(['form', 'layer', 'jquery', 'common'], function () {
    var $ = layui.$,
        form = layui.form,
        common = layui.common;

    form.verify({
        naGrade: function (value) {
            if (value.length > 64) {
                return '长度不能超过64个字';
            }
        },
        desGrade: function (value) {
            if (value && value.length > 255) {
                return '长度不能超过255个字';
            }
        }
    });

    //监听提交
    form.on('submit(addGrade)', function (data) {
       /* if (!data.field.fgActive) {
            data.field.fgActive = '0';
        }*/
        var url = basePath + '/pf/r/grade/';
        if (formType == 'add') {
            url += 'add';
        } else if (formType == 'edit') {
            url += 'edit';
        }
        return common.commonParentFormPost(url, data.field, formType, 'gradeTableId', '保存');
    });
});

