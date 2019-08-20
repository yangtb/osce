<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>实训领料</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${contextPath}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath}/biz/css/common.css">
    <link rel="stylesheet" href="${contextPath}/layui/expand/css/formSelects-v4.css">

    <script>
        var basePath = '${basePath}';
        var idPlan = '6';
    </script>
</head>

<body>

<div class="wrapper-content">

    <form class="layui-form">
        <div class="layui-inline">
            <input type="radio" name="fgPicking" value="-1" title="全部" lay-filter="fgPickingFilter" checked="">
            <input type="radio" name="fgPicking" value="0" title="未领" lay-filter="fgPickingFilter">
            <input type="radio" name="fgPicking" value="1" title="已领" lay-filter="fgPickingFilter">
        </div>
    </form>

    <table id="tpPickingTable" lay-filter="tpPickingTableFilter">
    </table>

</div>

<script src="${contextPath}/common/js/jquery.min.js"></script>
<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/biz/js/biz/training/res/pick/pickingController.js"></script>
<script src="${contextPath}/layui/plugins/modules/formSelects-v4.js" type="text/javascript" charset="utf-8"></script>


<script type="text/html" id="rightBar">
    <div class="layui-btn-container">
        {{#  if(d.fgConsumables == '1'){ }}

        {{#  } else { }}
        <button class="layui-btn layui-btn-xs layui-btn-normal" id="add" lay-event="add">&nbsp;<i class="layui-icon">&#xe654;</i></button>
        {{#  } }}
    </div>
</script>

<script type="text/html" id="fgPickedTpl">
    <#--{{#  if(d.fgConsumables == '1'){ }}
    <input type="text" id="fgPicked-{{d.idTpPicking}}"
           value="{{d.fgPicked}}" lay-filter="fgPickedFilter" style="width: 80px; height: 28px;">
    {{#  } else { }}

    {{#  } }}-->
    <input type="text" name="fgPicked" class="{{ d.fgConsumables!= '1' ? 'layui-disabled' : '' }}"
           value="{{d.fgPicked || ''}}" autocomplete="off" onchange="updatePickedNum(event, {{d.idTpPicking}})"
           style="width: 80px; height: 25px; padding-left: 5px;" {{ d.fgConsumables!= '1' ? 'disabled' : '' }}>
</script>

<script type="text/html" id="fgConsumablesTpl">
    {{#  if(d.fgConsumables == '1'){ }}
    消耗品
    {{#  } else { }}
    非耗品
    {{#  } }}
</script>


</body>

</html>