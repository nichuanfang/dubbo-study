<html>
<head>
    <#include "../commons/head.ftl"/>
    <style>
        .form-group {
            width: 50%;
        }
    </style>
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
                <div class="col-md-6 column">
                    <form role="form" method="post" action="/sell/seller/product/submit">
                        <input hidden type="text" name="productId" value="${(productInfo.productId)!''}">
                        <div class="form-group">
                            <label for="name">名称</label><input type="text" class="form-control" id="name" name="name"
                                                               value="${(productInfo.productName)!''}"/>
                        </div>
                        <div class="form-group">
                            <label for="price">价格</label><input type="text" class="form-control" name="price" id="price"
                                                                value="${(productInfo.productPrice)!''}"/>
                        </div>
                        <div class="form-group">
                            <label for="stock">库存</label><input type="text" class="form-control" id="stock" name="stock"
                                                                value="${(productInfo.productStock)!''}"/>
                        </div>
                        <div class="form-group">
                            <label for="description">描述</label><input type="text" class="form-control"
                                                                      name="description" id="description"
                                                                      value="${(productInfo.productDescription)!''}"/>
                        </div>
                        <div class="form-group">
                            <label for="category">类目</label> <br>
                            <select name="category" id="category" name="category" class="form-control">
                                <#list categoryList as category>
                                    <option value="${category.categoryType}"
                                            <#if productInfo?? && productInfo.categoryType==category.categoryType>
                                                selected="selected"
                                            </#if>
                                    >${category.categoryName}</option>
                                </#list>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-default">Submit</button>
                    </form>
                </div>
                <div class="col-md-6 column">
                    <img src="/sell/images/wallhaven-744071.jpg" width="120%" height="90%">
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
