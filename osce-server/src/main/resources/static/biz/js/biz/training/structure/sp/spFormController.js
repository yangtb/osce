layui.config({
    base: basePath + '/layui/build/js/'
}).use(['form', 'layer', 'treeSelect', 'jquery', 'common'], function () {
    var $ = layui.$,
        form = layui.form,
        common = layui.common,
        layer = layui.layer,
        treeSelect= layui.treeSelect;


    treeSelect.render({
        // 选择器
        elem: '#idOrg',
        // 数据
        data: basePath + '/pf/r/org/tree/select',
        // 异步加载方式：get/post，默认get
        type: 'post',
        // 占位符
        placeholder: '请选择机构',
        // 是否开启搜索功能：true/false，默认false
        search: true,
        // 点击回调
        click: function(d){
            console.log(d);
        },
        // 加载完成后的回调函数
        success: function (d) {
            console.log(d);
//                选中节点，根据id筛选
//                treeSelect.checkNode('tree', 3);

//                获取zTree对象，可以调用zTree方法
//                var treeObj = treeSelect.zTree('tree');
//                console.log(treeObj);

//                刷新树结构
//                treeSelect.refresh();
        }
    });
    
    $("#addTag").on('click', function () {
        common.open('标签管理', basePath + '/pf/p/sp/tag/form', 490, 465);
    });



});

