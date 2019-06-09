$(document).ready(function () {

    layui.config({
        base: basePath + '/layui/build/js/'
    }).use(['jquery', 'common'], function () {
        var $ = layui.$
            , common = layui.common;

        var bizData = {
            idModel: idModel
        };
        $.ajax({
            url: basePath + '/pf/p/plan/paper/select/leftList',
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
                    addMenu(data.data);
                    return true;
                }
            },
            error: function () {
                common.errorMsg("查询失败");
                return false;
            }
        });

        function addMenu(data) {
            $('#leftMenu').empty();
            $('#leftMenu').append(appendHtml(data));
            setStyle();
        }

        function appendHtml(data) {
            var html = '';
            $.each(data, function (index, content) {
                if (content.sdSkillCa == '1') {
                    content.sdSkillCaText = '理论试题';
                } else if (content.sdSkillCa == '2') {
                    content.sdSkillCaText = '技能操作';
                } else if (content.sdSkillCa == '3') {
                    content.sdSkillCaText = '病史采集';
                }

                html += '<ul class="paper-ul" data-index="' + content.sdSkillCa + '">\n' +
                    '       <li>' + content.sdStationCaText + '</li>\n' +
                    '       <li>' + content.sdSkillCaText + '</li>\n' +
                    '       <li>房间：' + content.rooms + '</li>\n' +
                    '    </ul>';
            });
            return html;
        }

        function setStyle() {
            var ul = document.querySelectorAll(".paper-ul");
            for (var i = 0; i < ul.length; i++) {
                ul[i].addEventListener('click', function () {
                    $(this).addClass("select").siblings().removeClass("select");
                    loadIframe(this.getAttribute('data-index'));
                });
                ul[i].addEventListener('mouseover', function () {
                    $(this).addClass("mouseOver").siblings().removeClass("mouseOver");
                });
                ul[i].addEventListener('mouseout', function () {
                    $(this).removeClass("mouseOver");
                });
            }
            ul[0].click();


            function loadIframe(dataIndex) {
                if (dataIndex == 1) {
                    $('#paperTag').attr('src', basePath + '/pf/p/plan/paper/skill/one?idModel=' + idModel);
                } else if (dataIndex == 2) {
                    $('#paperTag').attr('src', basePath + '/pf/p/plan/paper/skill/two?idModel=' + idModel);
                } else if (dataIndex == 3) {
                    $('#paperTag').attr('src', basePath + '/pf/p/plan/paper/skill/third?idModel=' + idModel);
                }
            }
        }
    });

});

