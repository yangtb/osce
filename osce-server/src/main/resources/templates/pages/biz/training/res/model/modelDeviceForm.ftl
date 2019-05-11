<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${contextPath}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath}/biz/css/common.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/biz/iconfont/iconfont.css">

    <script>
        var basePath = '${basePath}';
        var contextPath = '${contextPath}';
    </script>
</head>

<body>
<div style="margin: -10px 0px 0px -10px">
    <div class="layui-col-xs4">
        <table id="answerTable" lay-filter="answerTableFilter">
        </table>
    </div>

    <div class="layui-col-xs8">
        <form class="layui-form" id="roomForm" style="padding-top: 10px;">
            <div hidden>
                <input name="idRoom" hidden>
            </div>
            <div class="layui-form-item form-item-my">
                <div class="layui-inline">
                    <label class="layui-form-label">设备编码<i class="iconfont icon-required"
                                                         style="color: #f03f2d"></i></label>
                    <div class="layui-input-inline" style="width: 514px;">
                        <input type="text" name="naRoom" lay-verify="required" lay-vertype="tips"
                               autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">

                </div>
            </div>

            <div class="layui-form-item form-item-my">
                <div class="layui-inline">
                    <label class="layui-form-label">品牌</label>
                    <div class="layui-input-inline">
                        <input type="text" name="creator" autocomplete="off" class="layui-input"
                               >
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">型号</label>
                    <div class="layui-input-inline">
                        <input type="text" name="gmtCreate" autocomplete="off" class="layui-input"
                               >
                    </div>
                </div>
            </div>

            <div class="layui-form-item form-item-my">
                <div class="layui-inline">
                    <label class="layui-form-label">单价（￥）</label>
                    <div class="layui-input-inline">
                        <input type="text" name="creator" autocomplete="off" class="layui-input"
                               placeholder="￥">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">购入时间</label>
                    <div class="layui-input-inline">
                        <input type="text" name="gmtStoreIn" id="gmtStoreIn" autocomplete="off" class="layui-input">
                    </div>
                </div>
            </div>

            <div class="layui-form-item form-item-my">
                <div class="layui-inline">
                    <label class="layui-form-label">保修截止</label>
                    <div class="layui-input-inline">
                        <input type="text" name="creator" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">维修电话</label>
                    <div class="layui-input-inline">
                        <input type="text" name="gmtCreate" autocomplete="off" class="layui-input">
                    </div>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">图片</label>
                <div class="layui-input-inline" style="vertical-align: middle;width: 317px;">
                    <input id="path" name="path" placeholder="请上传文件" autocomplete="off"
                           class="layui-input layui-disabled" disabled>
                </div>
                <button type="button" class="layui-btn layui-btn-primary" id="test3">
                    <i class="layui-icon" style="color: #009688; font-weight: bold">&#xe608;</i>上传
                </button>
                <button type="button" class="layui-btn layui-btn-primary" id="preview">
                    <i class="iconfont icon-look1" style="color: #009688; font-weight: bold"></i> 预览
                </button>
            </div>

            <div class="layui-form-item form-item-my">
                <div class="layui-inline">
                    <label class="layui-form-label">设备报废</label>
                    <div class="layui-input-inline">
                        <input type="checkbox" checked="" name="fgActive" lay-skin="switch"
                               lay-filter="fgActiveSwitch" value="1" lay-text="NO|OFF">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">报废时间</label>
                    <div class="layui-input-inline">
                        <input type="text" name="gmtScrap" id="gmtScrap" autocomplete="off" class="layui-input">
                    </div>
                </div>
            </div>
            <div class="layui-form-item form-item-my">
                <div class="layui-inline">
                    <label class="layui-form-label">报废描述</label>
                    <div class="layui-input-inline" style="width: 514px;">
                                <textarea name="remark" class="layui-textarea"
                                          autocomplete="off" lay-verify="commonLength255" lay-vertype="tips"/></textarea>
                    </div>
                </div>
            </div>

            <div class="layui-form-item form-item-my">
                <div class="layui-inline">
                    <label class="layui-form-label">创建人</label>
                    <div class="layui-input-inline">
                        <input type="text" name="creator" autocomplete="off" class="layui-input layui-disabled"
                               disabled>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">创建时间</label>
                    <div class="layui-input-inline">
                        <input type="text" name="gmtCreate" autocomplete="off" class="layui-input layui-disabled"
                               disabled>
                    </div>
                </div>
            </div>
            <div class="layui-form-item form-item-my">
                <div class="layui-inline">
                    <label class="layui-form-label">修改人</label>
                    <div class="layui-input-inline">
                        <input type="text" name="operator" autocomplete="off" class="layui-input layui-disabled"
                               disabled>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">修改时间</label>
                    <div class="layui-input-inline">
                        <input type="text" name="gmtModify" autocomplete="off" class="layui-input layui-disabled"
                               disabled>
                    </div>
                </div>
            </div>

            <div class="layui-form-item" style="padding-top: 5px">
                <div class="layui-input-block">
                    <div class="layui-btn-group">
                        <button id="add" type="button" class="layui-btn">
                            <i class="layui-icon layui-icon-add-1"></i>增加
                        </button>
                        <button class="layui-btn" lay-submit="" lay-filter="addUser">
                            <i class="iconfont icon-save-copy"></i>保存
                        </button>
                        <button type="button" class="layui-btn layui-btn-normal" id="record">
                            <i class="iconfont icon-erweima"></i>生成二维码
                        </button>
                        <button type="button" class="layui-btn layui-btn-danger">
                            <i class="iconfont icon-guzhang"></i> 故障登记
                        </button>
                        <button type="button" class="layui-btn layui-btn-normal">
                            <i class="iconfont icon-weixiu"></i> 维修登记
                        </button>
                    </div>
                </div>
            </div>

        </form>
    </div>

</div>

<script src="${contextPath}/layui/plugins/layui/layui.all.js"></script>
<script src="${contextPath}/common/js/jquery.min.js"></script>
<script src="${contextPath}/common/js/jquery.formautofill.js"></script>
<script src="${contextPath}/biz/js/biz/training/res/model/modelDeviceController.js"></script>

<script type="text/html" id="fgActiveTpl">
    {{#  if(d.fgActive == '1'){ }}
    <button class="layui-btn layui-btn-normal layui-btn-xs">启用</button>
    {{#  } else { }}
    <button class="layui-btn layui-btn-danger layui-btn-xs">停用</button>
    {{#  } }}
</script>

<script type="text/html" id="inquisitionAnswer">
    <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del"><i class="layui-icon layui-icon-delete"></i></a>
</script>

</body>
</html>