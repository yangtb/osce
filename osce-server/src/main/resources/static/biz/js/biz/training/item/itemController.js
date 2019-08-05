layui.config({
    base: basePath + '/layui/build/js/'
}).extend({
    excel: 'layui_exts/excel.min'
}).use(['table', 'form', 'jquery', 'common', 'layer', 'excel', 'laytpl', 'element'], function () {
    var $ = layui.$
        , table = layui.table
        , form = layui.form
        , common = layui.common
        , layer = layui.layer
        , excel = layui.excel
        , laytpl = layui.laytpl
        , element = layui.element;

    //执行渲染
    table.render({
        elem: '#itemTable' //指定原始表格元素选择器（推荐id选择器）
        , id: 'itemTableId'
        , height: 'full-68' //容器高度
        , cols: [[
            {type: 'numbers', fixed: true, title: 'R'},
            {checkbox: true, fixed: true},
            {field: 'fgActive', width: 100, title: '状态',fixed: true, templet: '#fgActiveTpl'},
            {field: 'naItemStore', minWidth: 170, title: '题集', fixed: true},
            {field: 'desItemStore', minWidth: 250, title: '描述'},
            {field: 'itemNum', minWidth: 100, title: '题目数量', align: "right"},
            {field: 'gmtCreate', minWidth: 170, title: '创建时间'},
            {fixed: 'right', width: 120, title: '操作', align: 'center', toolbar: '#itemBar'}
        ]] //设置表头
        , url: basePath + '/pf/p/item/list'
        , limit: 15
        , even: true
        , limits: [15, 30, 100]
        , page: true
    });

    //监听工具条
    table.on('tool(itemTableFilter)', function (obj) {
        var data = obj.data;
        if (obj.event === 'edit') {
            _addOrEdit("edit", data);
        }
    });

    //监听提交
    form.on('submit(itemSearchFilter)', function (data) {
        table.reload('itemTableId', {
            height: 'full-68'
            , page: {
                curr: 1 //重新从第 1 页开始
            }
            , where :{
                naItemStore : data.field.naItemStore
            }
        });
    });

    $('#add').on('click', function () {
        _addOrEdit("add");
    });

    $('#edit').on('click', function () {
        var checkStatus = table.checkStatus('itemTableId')
            , data = checkStatus.data;
        if (data.length == 0) {
            layer.tips('请先选中一行记录', '#edit', {tips: 1});
            return;
        }
        if (data.length > 1) {
            layer.tips('请选中一行记录进行编辑', '#edit', {tips: 1});
            return;
        }
        var currentEditData = data[0];
        _addOrEdit("edit", currentEditData);
    });

    var _addOrEdit = function (formType, currentEditData) {
        if (formType == 'add') {
            var index = common.open('新增题集', basePath + '/pf/p/item/form?formType=' + formType, 500, 350);
            layer.full(index)
        } else {
            var index = common.open('编辑题集', basePath + '/pf/p/item/form?formType=' + formType, 500, 350, _successFunction(currentEditData));
            layer.full(index)
        }
    };

    var _successFunction = function (data) {
        return function (layero, index) {
            var iframe = window['layui-layer-iframe' + index];
            //调用子页面的全局函数
            iframe.fullForm(data);
        }
    };

    //监听行双击事件
    table.on('rowDouble(itemTableFilter)', function (obj) {
        _addOrEdit("edit", obj.data);
    });

    $("#del").on('click', function () {
        var checkStatus = table.checkStatus('itemTableId')
            , data = checkStatus.data;
        if (data.length == 0) {
            layer.tips('请先选中一行记录', '#del', {tips: 1});
            return;
        }
        _delGrade(data);
    });

    var _delGrade = function (currentData) {
        var url = basePath + '/pf/r/item/del';
        var reqData = new Array();
        var messageTitle = '';
        var delFlag = false, delMsg = '';
        $.each(currentData, function (index, content) {
            if (messageTitle) {
                messageTitle += ', ';
            }
            messageTitle += '【' + content.naItemStore + '】';
            reqData.push(content.idItemStore);

            if (content.itemNum > 0) {
                delFlag = true;
                delMsg += '【' + content.naItemStore + '】';
            }
        });

        if(delFlag) {
            layer.alert(delMsg + '<br><span style="color: red; font-weight: bold">题集下已有题目，不允许删除，请重新选择操作</span>', {
                title: '删除题集提示',
                resize: false,
                btn: ['确定']
            });
            return false;
        }

        var data = {};
        data.list = reqData;
        data.status = '1';
        layer.confirm('确定删除' + messageTitle + '么？', {
            title: '删除题集提示',
            resize: false,
            btn: ['确定', '取消'],
            btnAlign: 'c',
            icon: 3
        }, function (index) {
            _commonAjax(index, url, data, "删除");
        })
    }

    var _commonAjax = function (index, url, reqData, msg) {
        layer.load(2);
        $.ajax({
            url: url,
            type: 'post',
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify(reqData),
            success: function (data) {
                layer.closeAll('loading');
                if (data.code != 0) {
                    common.errorMsg(data.msg);
                    return false;
                } else {
                    common.sucChildMsg(msg + "成功");
                    if (index) {
                        layer.close(index);
                    }
                    _itemTableReload();
                    return true;
                }
            },
            error: function () {
                layer.closeAll('loading');
                common.errorMsg(msg + "失败");
                return false;
            }
        });
    }

    var _itemTableReload = function () {
        table.reload('itemTableId', {
            where: {
                //type: type
            },
            height: 'full-68'
        });
    }

    //监听删除操作
    form.on('switch(fgActiveCheckFilter)', function (obj) {
        var reqData = new Array();
        var data = {};
        reqData.push(this.value);
        data.list = reqData;
        if (obj.elem.checked) {
            data.status = '1';
        } else {
            data.status = '0';
        }
        common.commonPost(basePath + '/pf/r/item/updateStatus', data, '设置', obj.othis);
    });


    // 模板下载
    $("#templateDownLoad").on('click', function () {
        document.getElementById("exportForm").submit();
    });

    // 模板上传
    $(function(){
        // 监听上传文件的事件
        $('#itemImport').change(function(e) {
            var files = e.target.files;
            uploadExcel(files);
        });
        // 文件拖拽
        $('body')[0].ondragover = function(e) {
            e.preventDefault();
        }
        $('body')[0].ondrop = function(e) {
            e.preventDefault();
            var files = e.dataTransfer.files;
            uploadExcel(files);
        }
    });

    /**
     * 上传excel的处理函数，传入文件对象数组
     * @param  {[type]} files [description]
     * @return {[type]}       [description]
     */
    function uploadExcel(files) {
        try {
            excel.importExcel(files, {
                // 读取数据的同时梳理数据
                fields: {
                    'naItemStore': 'A'
                    ,'naItemSection': 'B'
                    ,'sort': 'C'
                    ,'sdItemCa': 'D'
                    ,'mainItem': 'E'
                    ,'sdItemLevel': 'F'
                    ,'itemAnalysis': 'G'
                    ,'scoreDefault': 'H'
                    ,'cdIteStr_A': 'I'
                    ,'naOption_A': 'J'
                    ,'fgRight_A': 'K'
                    ,'cdIteStr_B': 'L'
                    ,'naOption_B': 'M'
                    ,'fgRight_B': 'N'
                    ,'cdIteStr_C': 'O'
                    ,'naOption_C': 'P'
                    ,'fgRight_C': 'Q'
                    ,'cdIteStr_D': 'R'
                    ,'naOption_D': 'S'
                    ,'fgRight_D': 'T'
                }
            }, function(data) {
                // 如果不需要展示直接上传，可以再次 $.ajax() 将JSON数据通过 JSON.stringify() 处理后传递到后端即可
                layer.open({
                    title: '文件转换结果'
                    ,area: ['980px', '500px']
                    ,tipsMore: true
                    ,content: laytpl($('#LAY-excel-export-ans').html()).render({data: data, files: files})
                    ,success: function() {
                        element.render('tab');
                    }
                    ,yes: function(index, layero){
                        //console.log(JSON.stringify(data));
                        bachImportItem(data);
                        layer.close(index); //如果设定了yes回调，需进行手工关闭
                    }
                    ,end: function(){
                        $('#itemImport').val('');
                    }
                });

            });
        } catch (e) {
            $('#itemImport').val('')
            layer.alert(e.message);
        }
    };


    function bachImportItem(data) {
        var itemArr = new Array();
        for (var key in data) {
            var dataList = data[key];

            for (var key1 in dataList) {
                $.each(dataList[key1], function (index, content) {
                    if (index > 1) {
                        /*if (content.sdItemLevelStr == '男') {
                            content.sdItemLevel = 1;
                        } else if (content.sexStr == '女') {
                            content.sdItemLevel = 2;
                        }*/
                        itemArr.push(content);
                    }
                });
            }
        }

        console.log(itemArr)

        var reqData = {
            items: itemArr
        }

        layer.msg('正在导入题目数据，请耐心等待......', {
            icon: 16,
            shade: 0.01,
            time: false
        });
        $.ajax({
            url: basePath + '/pf/r/item/batch/add',
            type: 'post',
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify(reqData),
            success: function (data) {
                layer.closeAll('loading');
                if (data.code != 0) {
                    common.errorMsg(data.msg);
                    return false;
                } else {
                    var sucData = data.data;
                    if (sucData.length == 0) {
                        layer.msg("导入成功");
                    } else {
                        var msg = '';
                        $.each(sucData, function (index, content) {
                            if (msg) {
                                msg += ', ';
                            }
                            msg += content.naItemStore;
                        });
                        layer.alert('导入题目【' + msg + '】数据失败');
                    }
                    _itemTableReload();
                    return true;
                }
            },
            error: function () {
                layer.closeAll('');
                layer.msg("网络异常");
                return false;
            }
        });
    }



});




