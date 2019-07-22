layui.config({
    base: basePath + '/layui/plugins/'
}).use(['layer', 'table', 'form', 'jquery', 'common'], function () {

    var $ = layui.$
        , form = layui.form
        , common = layui.common;

    $(document).ready(function () {
        loadCobEvaluateDetail();
    });

    function loadCobEvaluateDetail() {
        var bizData = {
            idCobEvaluate: idCobEvaluate
        }
        $.ajax({
            url: basePath + '/pf/r/cob/evaluate/detail/list',
            type: 'post',
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify(bizData),
            success: function (data) {
                layer.closeAll('loading');
                if (data.code != 0) {
                    layer.msg(data.msg);
                    return false;
                } else {
                    fullCobEvaluateDetail(data.data);
                    return false;
                }
            },
            error: function () {
                layer.msg("网络异常");
                return false;
            }
        });
    }

    function fullCobEvaluateDetail(data) {
        $('#cobDetailTbody').empty();
        if (!data) {
            return;
        }
        $('#cobDetailTbody').append(buildCobEvaluateDetailHtml(data));
        // 增加监听
        // 增加监听
        addCobEventListener();
        delCobEventListener();
    }

    function buildCobEvaluateDetailHtml(data) {
        var html = '';
        $.each(data, function (index, content) {
            var naCobEvaluateDetail = content.naCobEvaluateDetail ? content.naCobEvaluateDetail : "",
                idCobEvaluateDetail = content.idCobEvaluateDetail ? content.idCobEvaluateDetail : "";
            html += '<tr>\n' +
                '            <td>' + naCobEvaluateDetail + '</td>\n' +
                '            <td class="label-align"><input type="radio" name="test' + idCobEvaluateDetail + '" value="1"></td>\n' +
                '            <td class="label-align"><input type="radio" name="test' + idCobEvaluateDetail + '" value="2"></td>\n' +
                '            <td class="label-align"><input type="radio" name="test' + idCobEvaluateDetail + '" value="3"></td>\n' +
                '            <td class="label-align"><input type="radio" name="test' + idCobEvaluateDetail + '" value="4"></td>\n' +
                '            <td class="label-align" width="80">\n' +
                '                <a class="layui-btn layui-btn-normal layui-btn-xs cob-add"><i class="iconfont icon-add"></i></a>\n';

            if (idCobEvaluateDetail) {
                html += '<a class="layui-btn layui-btn-normal layui-btn-xs layui-btn-danger cob-del" data-index="' + idCobEvaluateDetail + '" data-name="' + naCobEvaluateDetail + '"><i class="layui-icon layui-icon-delete"></i></a>\n';
            }
            html += '</td>\n' +
                '   </tr>';
        });
        return html;
    }

    function addCobEventListener() {
        var tests = document.querySelectorAll(".cob-add");
        for (var i = 0; i < tests.length; i++) {
            tests[i].addEventListener('click', function () {
                addCobDetail();
            });
        }
    }

    function delCobEventListener() {
        var tests = document.querySelectorAll(".cob-del");
        for (var i = 0; i < tests.length; i++) {
            tests[i].addEventListener('click', function () {
                delCobDetail(this.getAttribute('data-index'), this.getAttribute('data-name'));
            });
        }
    }

    function addCobDetail() {
        var index = layui.layer.open({
            title: '<b>新增评量表明细</b>',
            type: 2,
            area: ['360px', '215px'],
            fixed: false,
            content: basePath + '/pf/p/cob/evaluate/detail/form?idCobEvaluate=' + idCobEvaluate,
            shadeClose: true,
            end: function (index, layero) {
                loadCobEvaluateDetail();
                return false;
            }
        });
    }

    function delCobDetail(idCobEvaluateDetail, naCobEvaluateDetail) {
        var url = basePath + '/pf/r/cob/evaluate/detail/del';
        var reqData = new Array();
        reqData.push(idCobEvaluateDetail);

        if (!reqData || reqData.length == 0) {
            return false;
        }

        var data = {
            list: reqData
        };

        layer.confirm('确认删除【' + naCobEvaluateDetail + '】么？', {
            title: '删除明细提示',
            resize: false,
            btn: ['确定', '取消'],
            btnAlign: 'c',
            icon: 3
        }, function (index) {
            common.commonPost(url, data, '删除', null, loadCobEvaluateDetail, true);
        })
        return false;
    }


});
