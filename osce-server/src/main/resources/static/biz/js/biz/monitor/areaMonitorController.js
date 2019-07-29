layui.config({
    base: basePath + '/layui/plugins/'
}).extend({
    index: 'lib/index' //主入口模块
}).use(['layer', 'index', 'table', 'carousel', 'form', 'jquery', 'common', 'element'], function () {
    var $ = layui.$
        , table = layui.table
        , carousel = layui.carousel
        , form = layui.form
        , element = layui.element
        , common = layui.common;

    $(document).ready(function () {
        // 其实和非理论考试一体机类似，只是一体机查的是考场下某站点的信息；这里是考场下所有站点的信息
        queryMonitorInfo();
    });

    function queryMonitorInfo() {
        layer.load(2);
        var bizData = {

        }
        $.ajax({
            url: basePath + '/pf/r/monitor/area/list',
            type: 'post',
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify(bizData),
            success: function (data) {
                layer.closeAll();
                if (data.code != 0) {
                    layer.msg(data.msg);
                    return false;
                } else {
                    fullMonitorCard(data.data);
                    return false;
                }
            },
            error: function () {
                layer.msg("网络异常");
                return false;
            }
        });
    }

    function fullMonitorCard(data) {
        console.log(data)
        $('#monitorCard').empty();
        if (data.length == 0) {
            $('#monitorCard').append(buildMonitorHtml(data));
        }

        // 轮播
        carousel.render({
            elem: '#test1'
            , arrow: 'hover'
            , width: '100%' //设置容器宽度
            , height: '650px' //设置容器宽度
            , interval: 5000
        });
    }

    function buildMonitorHtml(data) {
        var html = '';
        $.each(data, function (index, content) {
            var rowNum = index + 1;
            if (rowNum == 1 || (rowNum % 8 == 1)) {
                html += '<div>\n';
            }

            if (rowNum == 1 || (rowNum % 4 == 1)) {
                html += '<div class="layui-row layui-col-space15" style="padding-top: 20px;">\n';
            }

            var borderColor = '', realName = content.realName ? content.realName : '';
            if (!content.sdExecQueue) {
                borderColor = 'border: 1px solid #c2c2c2; ';
            } else if (content.sdExecQueue == '2') {
                borderColor = 'border: 1px solid #FFB800; ';
            } else if (content.sdExecQueue == '3') {
                borderColor = 'border: 1px solid #5FB878; ';
            } else if (content.sdExecQueue == '4') {
                borderColor = 'border: 1px solid #1E9FFF; ';
            }

            html += '<div class="layui-col-md3">\n' +
                '       <div class="layui-card" style="' + borderColor + 'height: 275px;">\n' +
                '           <div class="layui-card-body card-font">\n' +
                '               <ul style="height: 230px;">\n' +
                '                   <li class="li-normal"><span class="left-label">站点</span><span class="right-label">' + content.naStation + '</span></li>\n' +
                '                   <li class="li-normal"><span class="left-label">房间号</span><span class="right-label">' + content.naRoom + '</span></li>\n' +
                '                   <li class="li-normal"><span class="left-label">考试时长</span><span class="right-label">' + content.minCost + '分钟</span></li>\n' +
                '                   <li class="li-normal"><span class="left-label">考试内容</span><span class="right-label">' + content.naPaper + '</span></li>\n' +
                '                   <li class="li-normal"><span class="left-label">监考员</span><span class="right-label">' + realName + '</span></li>\n' +
                '                   <li class="li-normal"><span class="left-label">考试描述</span><span class="right-label">' + content.desPaper + '</span></li>\n' +
                '               </ul>\n' +
                '               <div style="border-top: 1px dashed #e2e2e2; padding-top: 5px;">\n' +
                '                   <i class="iconfont icon-wenjian" style="font-size:25px; cursor: pointer"></i>\n' +
                '               </div>\n' +
                '           </div>\n' +
                '       </div>\n' +
                '   </div>\n';

            if (rowNum % 4 == 0) {
                html += '</div>\n';
            }

            if (rowNum % 8 == 0) {
                html += '</div>\n';
            }
        });

        return html;
    }


    element.on('tab(monitorFilter)', function (data) {
        if (data.index == 1) {
            loadTab1();
        }
    });

    element.on('tab(stuTabFilter)', function (data) {
        if (data.index == 0) {
            loadTab1();
        } else if (data.index == 1) {
            loadTab2();
        } else if (data.index == 2) {
            loadTab3();
        }
    });


    function loadTab1() {
        table.render({
            elem: '#toBeExaminedTable'
            , id: 'toBeExaminedTableId'
            , toolbar: '#toolbarDemo'
            , size: 'sm'
            , url: basePath + '/pf/p/monitor/area/list/toBeExamined'
            , height: 'full-100'
            , skin: 'line'
            , cols: [[
                {type: 'radio'}
                , {title: '', width: 90, align: 'center', toolbar: '#toBeExaminedBar'}
                , {field: 'realName', minWidth: 150, title: '姓名'}
                , {field: 'noReg', minWidth: 100, title: '入场序号'}
                , {field: 'phoneNo', minWidth: 140, title: '手机号'}
                , {field: 'idCard', minWidth: 180, title: '身份证号'}
                , {field: 'gmtReg', minWidth: 140, title: '入场时间'}
            ]]
            , where: {
                idPlan: 1,
                idArea: 1,
                timeSection: 1
            }
        });
    }

    function loadTab2() {
        table.render({
            elem: '#onSiteTable'
            , id: 'onSiteTableId'
            , size: 'sm'
            , url: basePath + '/pf/p/monitor/area/list/onSite'
            , height: 'full-100'
            , skin: 'line'
            , cols: [[
                {type: 'radio'}
                , {field: 'realName', minWidth: 150, title: '姓名'}
                , {field: 'noReg', minWidth: 100, title: '入场序号'}
                , {field: 'phoneNo', minWidth: 140, title: '手机号'}
                , {field: 'idCard', minWidth: 180, title: '身份证号'}
                , {field: 'gmtReg', minWidth: 140, title: '入场时间'}
            ]]
            , where: {
                idPlan: 1,
                idArea: 1,
                timeSection: 1
            }
        });
    }

    function loadTab3() {
        table.render({
            elem: '#endTable'
            , id: 'endTableId'
            , size: 'sm'
            , toolbar: '#endBar'
            , url: basePath + '/pf/p/monitor/area/list/end'
            , height: 'full-100'
            , skin: 'line'
            , cols: [[
                {type: 'radio'}
                , {field: 'endStatus', width: 150, title: '', templet: '#endStatusTpl'}
                , {field: 'realName', minWidth: 150, title: '姓名'}
                , {field: 'noReg', minWidth: 100, title: '入场序号'}
                , {field: 'phoneNo', minWidth: 140, title: '手机号'}
                , {field: 'idCard', minWidth: 180, title: '身份证号'}
                , {field: 'gmtReg', minWidth: 140, title: '入场时间'}
            ]]
            , where: {
                idPlan: 1,
                idArea: 1,
                timeSection: 1
            }
        });
    }

    //头工具栏事件
    table.on('toolbar(toBeExaminedTableFilter)', function (obj) {
        var checkStatus = table.checkStatus(obj.config.id);
        switch (obj.event) {
            case 'addStudent':
                var data = checkStatus.data;
                addStudent(data);
                break;
            case 'delStudent':
                var data = checkStatus.data;
                if (data.length == 0) {
                    layer.tips('请先选中一行记录', '#delStudent');
                    return;
                }
                delStudent(data);
                break;
        }
        ;
    });

    function addStudent(data) {
        var y = $("#addStudent").offset().top + $("#addStudent").outerHeight() + "px";
        var x = ($("#addStudent").offset().left + 1) + "px";
        var index = layui.layer.open({
            title: '<b>添加学员</b>',
            type: 2,
            area: ['800px', '500px'],
            offset: [y, x],
            fixed: false,
            maxmin: false,
            content: basePath + '/pf/p/monitor/addStu/page',
            shadeClose: true
        });
    }

    function delStudent(data) {
        console.log(data)
        var url = basePath + '/pf/r/monitor/area/student/del';
        var reqData = new Array();
        reqData.push(data[0].userId);

        if (!reqData || reqData.length == 0) {
            return false;
        }

        var bizData = {
            list: reqData
        };

        layer.confirm('确认删除【' + data[0].realName + '】么？', {
            title: '删除学员提示',
            resize: false,
            btn: ['确定', '取消'],
            btnAlign: 'c',
            icon: 3
        }, function (index) {
            common.commonPost(url, bizData, '删除', null, toBeExaminedTableReload, true);
        })
        return false;

    }

    function toBeExaminedTableReload() {
        table.reload('toBeExaminedTableId');
    }

    function endTableReload() {
        table.reload('endTableId');
    }


    //头工具栏事件
    table.on('toolbar(endTableFilter)', function (obj) {
        var checkStatus = table.checkStatus(obj.config.id);
        switch (obj.event) {
            case 'recoveryTest':
                var data = checkStatus.data;
                if (data.length == 0) {
                    layer.tips('请先选中一行记录', '#recoveryTest');
                    return;
                }
                recoveryTest(data);
                break;
        }
        ;
    });

    function recoveryTest(data) {
        var reqData = new Array();
        reqData.push(data[0].userId);

        if (!reqData || reqData.length == 0) {
            return false;
        }

        var bizData = {
            list: reqData
        };

        layer.confirm('确认恢复学员【' + data[0].realName + '】考试么？', {
            title: '恢复学员考试提示',
            resize: false,
            btn: ['确定', '取消'],
            btnAlign: 'c',
            icon: 3
        }, function (index) {
            var url = basePath + '/pf/r/monitor/area/student/recovery';
            common.commonPost(url, bizData, '恢复', null, endTableReload, true);
        })
        return false;
    }

});



