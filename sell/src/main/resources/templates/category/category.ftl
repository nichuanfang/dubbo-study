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
                            <th>类别名称</th>
                            <th>类别种类</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list pageInfo.list as category>
                        <tr>
                            <td>${category.categoryName}</td>
                            <td>${category.categoryType}</td>
                            <td>
                                <a href="/sell/seller/productcategory/update?categoryId=${category.categoryId}">修改</a>
                            </td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>

                <#--分页-->
                    <ul class="pagination pull-right">
                    <#--上一页-->
                        <#if pageInfo.isFirstPage>
                            <li class="disabled">
                                <a href="javascript:void(0)">上一页</a>
                            </li>
                        <#else>
                            <li>
                                <a href="/sell/seller/productcategory/list?page=${pageInfo.prePage}">上一页</a>
                            </li>
                        </#if>

                    <#--导航页-->
                        <#list pageInfo.navigatepageNums as page>
                            <#if pageInfo.pageNum==page>
                                <li class="disabled">
                                    <a href="/sell/seller/productcategory/list?page=${page}">${page}</a>
                                </li>
                            <#else>
                                <li>
                                    <a href="/sell/seller/productcategory/list?page=${page}">${page}</a>
                                </li>
                            </#if>
                        </#list>

                    <#--下一页-->
                        <#if pageInfo.isLastPage>
                            <li class="disabled">
                                <a href="javascript:void(0)">下一页</a>
                            </li>
                        <#else>
                            <li>
                                <a href="/sell/seller/productcategory/list?page=${pageInfo.nextPage}">下一页</a>
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
    $(function () {
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
