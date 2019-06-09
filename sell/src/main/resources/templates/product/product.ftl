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
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-condensed table-bordered">
                        <thead>
                        <tr>
                            <th>
                                商品id
                            </th>
                            <th>
                                名称
                            </th>
                            <th>图片</th>
                            <th>单价</th>
                            <th>库存</th>
                            <th>描述</th>
                            <th>类目</th>
                            <th>创建时间</th>
                            <th>修改时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list pageinfo.list as productinfo>
                        <tr>
                            <td>${productinfo.productId}</td>
                            <td>${productinfo.productName}</td>
                            <td>${productinfo.productIcon}</td>
                            <td>${productinfo.productPrice}</td>
                            <td>${productinfo.productStock}</td>
                            <td>${productinfo.productDescription}</td>
                            <td>${productinfo.categoryName}</td>
                            <td>${productinfo.createTime}</td>
                            <td>${productinfo.updateTime}</td>
                            <td><a href="/sell/seller/product/update?productId=${productinfo.productId}">修改</a></td>
                            <td>
                                <#if productinfo.productStatus==0>
                                    <a href="/sell/seller/product/Down?productId=${productinfo.productId}">下架</a>
                                <#else>
                                    <a href="/sell/seller/product/Up?productId=${productinfo.productId}">上架</a>
                                </#if>
                            </td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>

                <#--分页-->
                    <ul class="pagination pull-right">
                    <#--上一页-->
                        <#if pageinfo.isFirstPage>
                            <li class="disabled">
                                <a href="javascript:void(0)">上一页</a>
                            </li>
                        <#else>
                            <li>
                                <a href="/sell/seller/product/list?page=${pageinfo.prePage}">上一页</a>
                            </li>
                        </#if>

                    <#--导航页-->
                        <#list pageinfo.navigatepageNums as page>
                            <#if pageinfo.pageNum==page>
                                <li class="disabled">
                                    <a href="/sell/seller/product/list?page=${page}">${page}</a>
                                </li>
                            <#else>
                                <li>
                                    <a href="/sell/seller/product/list?page=${page}">${page}</a>
                                </li>
                            </#if>
                        </#list>

                    <#--下一页-->
                        <#if pageinfo.isLastPage>
                            <li class="disabled">
                                <a href="javascript:void(0)">下一页</a>
                            </li>
                        <#else>
                            <li >
                                <a href="/sell/seller/product/list?page=${pageinfo.nextPage}">下一页</a>
                            </li>
                        </#if>
                    </ul>

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
