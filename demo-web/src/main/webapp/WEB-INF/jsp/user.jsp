<%@ page pageEncoding="UTF-8" %>
<%@ include file="common/config.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sample - 用户</title>
    <%@ include file="common/meta.jsp" %>
    <%@ include file="common/style.jsp" %>
</head>
<body>

<%@ include file="common/nav.jsp"%>

<div class="container">
    <ol class="breadcrumb">
        <li class="active">用户</li>
    </ol>
    <div id="toolbar" class="btn-group">
        <button type="button" id="userCreateButton" class="btn btn-success">
            <i class="fa fa-plus"></i> 添加用户
        </button>
    </div>
    <table id="userTable"></table>
</div>

<%@ include file="common/script.jsp" %>

<script>
    $(function () {
        $('#userTable').bootstrapTable({
            sortName: 'created_time',
            sortOrder: 'desc',
            search: true,
            toolbar: '#toolbar',
            pagination: true,
            sidePagination: 'server',
            url: '${API}/users',
            responseHandler: function (response) {
                if (response.meta.success) {
                    return response.data;
                }
            },
            columns: [{
                field: 'username',
                title: '名称',
                class: 'col-md-2',
                searchable: true,
                sortable: true
            }, {
                field: 'password',
                title: '密码',
                class: 'col-md-6',
                searchable: false,
                sortable: true
            }, {
                field: 'created_time',
                title: '创建时间',
                class: 'col-md-2',
                searchable: false,
                sortable: true
            },{
                field: 'action',
                title: '操作',
                class: 'col-md-2',
                searchable: false,
                sortable: false,
                formatter: function (value, row) {
                    var html = '';
                    html += '<a href="${CTX}/user_edit?id=' + row.id + '" class="btn btn-primary btn-xs"><i class="fa fa-edit"></i> 编辑</a>';
                    html += ' ';
                    html += '<a data-action="delete" data-id="' + row.id + '" class="btn btn-danger btn-xs" data-name="' + row.user_name + '"><i class="fa fa-remove"></i> 删除</a>';
                    return html;
                }
            }]
        });

        $(document).on('click', 'a[data-action="delete"]', function () {
            var name = $(this).data('name');
            if (confirm('确认删除 ' + name + ' 吗？')) {
                var id = $(this).data('id');
                Ajax.delete({
                    url: '${API}/user/' + id,
                    success: function (response) {
                        if (response.meta.success) {
                            location.reload();
                        }
                    }
                });
            }
            return false;
        });

        $('#userCreateButton').click(function () {
            location.href = '${CTX}/user_create';
        });
    });
</script>

</body>
</html>
