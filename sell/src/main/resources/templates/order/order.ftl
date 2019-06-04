<html>
<head>
    <#include "../commons/head.ftl"/>
</head>
<body>

<div id="wrapper">
    <div class="overlay"></div>
    <#include "../commons/nav.ftl"/>
    <div id="page-content-wrapper">
        <button type="button" class="hamburger is-closed animated fadeInLeft" data-toggle="offcanvas">
            <span class="hamb-top"></span>
            <span class="hamb-middle"></span>
            <span class="hamb-bottom"></span>
        </button>
    <#--主要内容-->
        <div class="container">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-condensed table-bordered" style="width: 30%">
                        <thead>
                        <tr>
                            <th>
                                订单id
                            </th>
                            <th>
                                订单总金额
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>
                            ${orderDto.orderId}
                            </td>
                            <td>
                            ${orderDto.orderAmount}
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <br>
                    <br>
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>
                                商品id
                            </th>
                            <th>
                                商品名称
                            </th>
                            <th>
                                价格
                            </th>
                            <th>
                                数量
                            </th>
                            <th>
                                总额
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list orderDto.orderDetails as orderDetail>
                            <tr>
                                <td>
                                    ${orderDetail.productId}
                                </td>
                                <td>
                                    ${orderDetail.productName}
                                </td>
                                <td>
                                    ${orderDetail.productPrice}
                                </td>
                                <td>
                                    ${orderDetail.productQuantity}
                                </td>
                                <td>
                                    ${(orderDetail.productPrice)*(orderDetail.productQuantity)}
                                </td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                    <br>
                    <a href="/sell/seller/order/finish?orderId=${orderDto.orderId}&currentPage=${currentPage}" style="text-decoration: none" type="button" class="btn btn-default btn-primary">完结订单</a>
                    <a href="/sell/seller/order/cancel?orderId=${orderDto.orderId}&currentPage=${currentPage}" style="text-decoration: none" type="button" class="btn btn-default btn-danger">取消订单</a>
                </div>
            </div>
        </div>
    </div>
</div>


<script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
<script src="http://www.jq22.com/jquery/bootstrap-3.3.4.js"></script>

<script>
    $(document).ready(function () {
        var trigger = $('.hamburger'),
                overlay = $('.overlay'),
                isClosed = false;

        trigger.click(function () {
            hamburger_cross();
        });

        function hamburger_cross() {

            if (isClosed == true) {
                overlay.hide();
                trigger.removeClass('is-open');
                trigger.addClass('is-closed');
                isClosed = false;
            } else {
                overlay.show();
                trigger.removeClass('is-closed');
                trigger.addClass('is-open');
                isClosed = true;
            }
        }

        $('[data-toggle="offcanvas"]').click(function () {
            $('#wrapper').toggleClass('toggled');
        });
    });
</script>
</body>
</html>
