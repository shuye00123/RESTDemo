<%@ page pageEncoding="UTF-8" %>
<%@ include file="common/config.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sample - 创建广告主</title>
    <%@ include file="common/meta.jsp" %>
    <%@ include file="common/style.jsp" %>
</head>
<body>

<%@ include file="common/nav.jsp"%>

<div class="container">
    <ol class="breadcrumb">
        <li><a href="${CTX}/user">广告主</a></li>
        <li class="active">创建广告主</li>
    </ol>
    <div class="row">
        <div class="col-md-6">
            <form id="userForm" class="form-horizontal">
                <div class="form-group">
                    <label for="user_name" class="col-md-4 control-label">名称：</label>
                    <div class="col-md-8">
                        <input type="text" id="user_name" name="user_name" class="form-control"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="col-md-4 control-label">密码：</label>
                    <div class="col-md-8">
                        <input type="password" id="password" name="password"  class="form-control"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <button type="submit" class="btn btn-primary"><i class="fa fa-check"></i> 保存</button>
                        <button type="button" class="btn btn-link" id="cancel">取消</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<%@ include file="common/script.jsp" %>

<script>
    $(function () {
        $('#advertiserForm').submit(function () {
            Ajax.post({
                url: '${API}/user',
                data: {
                    'user_name': $('#user_name').val(),
                    'password': $('#password').val()
                },
                success: function (response) {
                    if (response.meta.success) {
                        location.href = '${CTX}/user';
                    }
                }
            });
            return false;
        });

        $('#cancel').click(function () {
            location.href = '${CTX}/user';
        });
    });
</script>

</body>
</html>
