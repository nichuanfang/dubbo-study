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
                    <form role="form" method="post" action="#" id="form">
                        <div class="form-group">
                            <label for="">分类名称</label><input type="text" class="form-control" id="categoryName" name="categoryName" value="${(category.categoryName)!''}"/>
                        </div>
                        <div class="form-group">
                            <label for="">分类类别</label>
                            <input
                                <#if category??>readonly</#if>
                                type="text" class="form-control" id="categoryType" name="categoryType" value="${(category.categoryType)!''}"/>
                        </div>
                        <input hidden name="categoryId" value="${(category.categoryId)!''}"/>
                        <button type="button" class="btn btn-default" id="ncf">Submit</button>
                    </form>
                </div>
            </div>
        </div>


    </div>
</div>


<script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
<script src="http://www.jq22.com/jquery/bootstrap-3.3.4.js"></script>

<script>

    $("#ncf").click(function () {
        $.ajax({
            url:"/sell/seller/productcategory/submit",
            type:"post",
            data:$("#form").serialize(),
            dataType:"text",
            success: function (data) {
                alert(data);
                location.href="/sell/seller/productcategory/list";
            },
            error: function () {
                alert("fail");
            }
        });
    });

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

