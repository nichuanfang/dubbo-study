<html>
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
    <script src="http://localhost/sell/js/jquery-3.3.1.min.js"></script>
    <script src="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">

            <div class="modal fade" id="modal-show" role="dialog" aria-labelledby="myModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                            <h4 class="modal-title" id="myModalLabel">
                                标题
                            </h4>
                        </div>
                        <div class="modal-body">
                            内容...
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <button type="button" class="btn btn-primary">保存</button>
                        </div>
                    </div>
                </div>
            </div>

            <audio id="mp3" loop="loop">
                <source src="http://localhost/sell/music/love.mp3" type="audio/mpeg">
            </audio>

            <table class="table">
                <thead>
                <tr>
                    <th>
                        订单id
                    </th>
                    <th>
                        姓名
                    </th>
                    <th>
                        手机号
                    </th>
                    <th>
                        地址
                    </th>
                    <th>
                        金额
                    </th>
                    <th>
                        订单状态
                    </th>
                    <th>
                        支付状态
                    </th>
                    <th>
                        创建时间
                    </th>
                    <th colspan="2">
                        操作
                    </th>
                </tr>
                </thead>
                <tbody>
                <#list orderDtoPage.getContent() as orderDto>
                <tr>
                    <td>${orderDto.orderId}</td>
                    <td>${orderDto.buyerName}</td>
                    <td>${orderDto.buyerPhone}</td>
                    <td>${orderDto.buyerAddress}</td>
                    <td>${orderDto.orderAmount}</td>
                    <td>${orderDto.orderStatusEnums.msg}</td>
                    <td>${orderDto.payStatusEnums.msg}</td>
                    <td>${orderDto.createTime}</td>
                    <td><a href="#">详情</a></td>
                    <td><a href="#">取消</a></td>
                </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
</div>

<#--分页-->
<div class="col-md-12 column">
    <ul class="pagination pull-right">
        <#if currentPage lte 1>
            <li class="disabled">
                <a href="#">上一页</a>
            </li>
        <#else >
            <li>
                <a href="http://localhost/sell/seller/order/list?page=${currentPage-1}&size=${size}">上一页</a>
            </li>
        </#if>

        <#list 1..orderDtoPage.totalPages as page>
            <#if page lte 10>
                <#if currentPage==page>
                 <li class="disabled">
                     <a href="http://localhost/sell/seller/order/list?page=${page}&size=${size}">${page}</a>
                 </li>
                <#else>
                <li>
                    <a href="http://localhost/sell/seller/order/list?page=${page}&size=${size}">${page}</a>
                </li>
                </#if>
            <#else>
                <li>
                    <a href="#">...</a>
                </li>
            </#if>

        </#list>


        <#if currentPage gte orderDtoPage.totalPages>
            <li class="disabled">
                <a href="#">下一页</a>
            </li>
        <#else >
            <li>
                <a href="http://localhost/sell/seller/order/list?page=${currentPage+1}&size=${size}">下一页</a>
            </li>
        </#if>
    </ul>
</div>


<script>
    var websocket = null;

    if ('WebSocket' in window) {
        websocket = new WebSocket('ws://localhost/sell/websocket');
    } else {
        alert('该浏览器不支持websocket!');
    }

    //start session
    websocket.onopen = function (eve) {
        console.log('communication started');
    }

    //close session
    websocket.onclose = function (eve) {
        console.log('communication stopped');
    }

    //when fetch message;
    websocket.onmessage = function (message) {
        // alert(message.data);
        $('#modal-show').modal('show');
        //播放音乐
        document.getElementById("mp3").play();
    }

    // when the window closed;
    websocket.onbeforeunload = function (eve) {
        websocket.close();
    }

</script>

</body>
</html>
