layui.config({
    base: basePath + '/layui/plugins/'
}).use(['layer', 'table', 'form', 'jquery', 'common'], function () {

    var $ = layui.$
        , common = layui.common;

    $(document).ready(function () {
        loadCobEvaluate();
    });

    function loadCobEvaluate() {
        var bizData = {
            cdCobEvaluate: cdCobEvaluate
        }
        $.ajax({
            url: basePath + '/pf/r/cob/evaluate/list',
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
                    fullCobEvaluate(data.data);
                    return false;
                }
            },
            error: function () {
                layer.msg("网络异常");
                return false;
            }
        });
    }

    function fullCobEvaluate(data) {
        $('#cobTbody').empty();
        if (!data) {
            return;
        }
        $('#cobTbody').append(buildCobEvaluateHtml(data));
        // 增加监听
        addCobEventListener();
        editCobEventListener();
        delCobEventListener();
        scoreCobEventListener();
    }

    function buildCobEvaluateHtml(data) {
        var html = '';
        $.each(data, function (index, content) {
            var naCobEvaluate = content.naCobEvaluate ? content.naCobEvaluate : "",
                idCobEvaluate = content.idCobEvaluate ? content.idCobEvaluate : "";

            html += '<tr>\n' +
                '       <td>' + naCobEvaluate + '</td>\n' +
                '       <td class="label-align score" data-index="score-nvl-'+ idCobEvaluate +'"><span id="score-nvl-'+ idCobEvaluate +'" style="display: none;">NVL</span></td>\n' +
                '       <td class="label-align score" data-index="score-0-'+ idCobEvaluate +'"><span id="score-0-'+ idCobEvaluate +'" style="display: none;">0</span></td>\n' +
                '       <td class="label-1 score" data-index="score-1-'+ idCobEvaluate +'"><span id="score-1-'+ idCobEvaluate +'" style="display: none;">1</span></td>\n' +
                '       <td class="label-1 score" data-index="score-2-'+ idCobEvaluate +'"><span id="score-2-'+ idCobEvaluate +'" style="display: none;">2</td>\n' +
                '       <td class="label-1 score" data-index="score-3-'+ idCobEvaluate +'"><span id="score-3-'+ idCobEvaluate +'" style="display: none;">3</span></td>\n' +
                '       <td class="label-2 score" data-index="score-4-'+ idCobEvaluate +'"><span id="score-4-'+ idCobEvaluate +'" style="display: none;">4</span></td>\n' +
                '       <td class="label-2 score" data-index="score-5-'+ idCobEvaluate +'"><span id="score-5-'+ idCobEvaluate +'" style="display: none;">5</span></td>\n' +
                '       <td class="label-2 score" data-index="score-6-'+ idCobEvaluate +'"><span id="score-6-'+ idCobEvaluate +'" style="display: none;">6</span></td>\n' +
                '       <td class="label-3 score" data-index="score-7-'+ idCobEvaluate +'"><span id="score-7-'+ idCobEvaluate +'" style="display: none;">7</span></td>\n' +
                '       <td class="label-3 score" data-index="score-8-'+ idCobEvaluate +'"><span id="score-8-'+ idCobEvaluate +'" style="display: none;">8</span></td>\n' +
                '       <td class="label-3 score" data-index="score-9-'+ idCobEvaluate +'"><span id="score-9-'+ idCobEvaluate +'" style="display: none;">9</span></td>\n' +
                '       <td class="label-align" width="120">\n' +
                '            <a class="layui-btn layui-btn-normal layui-btn-xs cob-add"><i class="iconfont icon-add"></i></a>\n';
            if (content.idCobEvaluate) {
                html += '    <a class="layui-btn layui-btn-normal layui-btn-xs cob-edit" data-index="' + content.idCobEvaluate + '" data-name="' + naCobEvaluate + '"><i class="iconfont icon-edit"></i></a>\n' +
                    '        <a class="layui-btn layui-btn-normal layui-btn-xs layui-btn-danger cob-del" data-index="' + content.idCobEvaluate + '" data-name="' + naCobEvaluate + '"><i class="layui-icon layui-icon-delete"></i></a>\n';
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
                addCob('');
            });
        }
    }

    function editCobEventListener() {
        var tests = document.querySelectorAll(".cob-edit");
        for (var i = 0; i < tests.length; i++) {
            tests[i].addEventListener('click', function () {
                editCob(this.getAttribute('data-index'), this.getAttribute('data-name'));
            });
        }
    }

    function delCobEventListener() {
        var tests = document.querySelectorAll(".cob-del");
        for (var i = 0; i < tests.length; i++) {
            tests[i].addEventListener('click', function () {
                delCob(this.getAttribute('data-index'), this.getAttribute('data-name'));
            });
        }
    }

    function scoreCobEventListener() {
        var tests = document.querySelectorAll(".score");
        for (var i = 0; i < tests.length; i++) {
            tests[i].addEventListener('click', function () {
                scoreShow(this.getAttribute('data-index'));
            });
        }
    }

    function scoreShow(key) {
        var arr = key.split('-');
        var socre = arr[0],
            colNum = arr[1],
            idCobEvaluate = arr[2];
        if (colNum == 'nvl') {
            if ($('#score-nvl-' + idCobEvaluate).css("display") == 'none') {
                $('#score-nvl-' + idCobEvaluate).css("display", "block");
            } else {
                $('#score-nvl-' + idCobEvaluate).css("display", "none");
            }
        } else {
            for (var j = 0; j < 10; j++) {
                $('#score-' + j + '-' + idCobEvaluate).css("display", "none");
            }
            $('#' + key).css("display", "block")
        }
    }

    function addCob(idCobEvaluate) {
        var index = layui.layer.open({
            title: '<b>新增评量表</b>',
            type: 2,
            area: ['360px', '270px'],
            fixed: false, //不固定
            // maxmin: true,
            content: basePath + '/pf/p/cob/evaluate/page?idCobEvaluate=' + idCobEvaluate + '&cdCobEvaluate=' + cdCobEvaluate,
            shadeClose: true,
            end: function (index, layero) {
                loadCobEvaluate();
                return false;
            }
        });
    }

    function delCob(idCobEvaluate, naCobEvaluate) {
        var url = basePath + '/pf/r/cob/evaluate/del';
        var reqData = new Array();
        reqData.push(idCobEvaluate);

        if (!reqData || reqData.length == 0) {
            return false;
        }

        var data = {
            list: reqData
        };

        layer.confirm('确认删除【' + naCobEvaluate + '】么？', {
            title: '删除评分项目提示',
            resize: false,
            btn: ['确定', '取消'],
            btnAlign: 'c',
            icon: 3
        }, function (index) {
            common.commonPost(url, data, '删除', null, loadCobEvaluate, true);
        })
        return false;
    }

    function editCob(idCobEvaluate, naCobEvaluate) {
        var index = layui.layer.open({
            title: '<b>评分项目【' + naCobEvaluate + '】</b>',
            type: 2,
            area: ['659px', '325px'],
            fixed: false,
            content: basePath + '/pf/p/cob/evaluate/detail/page?idCobEvaluate=' + idCobEvaluate,
            shadeClose: true,
            end: function (index, layero) {
                // loadCobEvaluate();
                return false;
            }
        });
    }


});
