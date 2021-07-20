<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>员工管理</title>
    <%@include file="/WEB-INF/views/common/link.jsp" %>

    <script>
        //移动选中
        function moveSelected(srcClass, targetClass) {
            $("." + srcClass + " option:selected").appendTo($("." +targetClass));
        }

        //移动全部
        function moveAll(srcClass, targetClass) {
            $("." + srcClass + " option").appendTo($("." +targetClass));
        }


        $(function () {
            //获取右边框中option的value,存到数组中
            var ids = [];
            $.each($(".selfRoles option"), function (index,option) {
                ids.push(option.value);
            })
            console.log(ids);
            console.log(12333333);
        })

        $(function () {
            //排重
            var ids = $.map($(".selfRoles option"), function (item) {
                return item.value;
            })
            //遍历左边框中的所有option,判断其value值是否在上面的数组中
            //在,则删除
            $.each($(".allRoles option"), function (index, item) {
                if ($.inArray(item.value, ids) >= 0) {
                    $(item).remove();
                }
            })




            //提交表单
            $("#submitBtn").click(function () {
                $(".selfRoles option").prop("selected", true);
                $("#editForm").submit();
            })
            var role = "";
            //超级管理员
            $("#admin").change(function () {
                if (this.checked) {
                    role = $("#role").detach();
                } else {
                    $(this).closest(".form-group").after(role);
                }
            })
            //编辑时候， 根据是否是超级管理员决定是否显示下拉框
            if (${employee!=null && employee.admin}) {
                role = $("#role").detach();
            }
        })
    </script>

</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <%@include file="/WEB-INF/views/common/navbar.jsp" %>
    <!--菜单回显-->
    <c:set var="currentMenu" value="employee"/>
    <%@include file="/WEB-INF/views/common/menu.jsp" %>
    <div class="content-wrapper">
        <section class="content-header">
            <h1>员工编辑</h1>
        </section>
        <section class="content">
            <div class="box">
                <form class="form-horizontal" action="/employee/saveOrUpdate" method="post" id="editForm">
                    <input type="hidden" value="${employee.id}" name="id">
                    <div class="form-group" style="margin-top: 10px;">
                        <label for="name" class="col-sm-1 control-label">名称：</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="name" name="name" value="${employee.name}"
                                   placeholder="请输入用户名">
                        </div>
                    </div>


                    <%--如果对象是空的(添加操作),则显示密码--%>
                    <c:if test="${empty employee}">
                        <div class="form-group" style="margin-top: 10px;">
                            <label for="password" class="col-sm-1 control-label">输入密码：</label>
                            <div class="col-sm-4">
                                <input type="password" class="form-control" id="password" name="password"
                                       value="${employee.password}"
                                       placeholder="请输入密码">
                            </div>
                        </div>
                    </c:if>


                    <div class="form-group" style="margin-top: 10px;">
                        <label for="email" class="col-sm-1 control-label">邮箱：</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="email" name="email" value="${employee.email}"
                                   placeholder="请输入邮箱">
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 10px;">
                        <label for="age" class="col-sm-1 control-label">年龄：</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="age" name="age" value="${employee.age}"
                                   placeholder="请输入年龄">
                        </div>
                    </div>


                    <div class="form-group" style="margin-top: 10px;">
                        <label for="dept" class="col-sm-1 control-label">部门：</label>
                        <div class="col-sm-4">
                            <select type="text" class="form-control" id="dept" name="dept.id">
                                <c:forEach items="${departments}" var="d">
                                    <option value="${d.id}">${d.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <%--部门下拉框的回显操作--%>
                    <script>
                        $("select[name='dept.id']").val(${employee.dept.id});
                    </script>


                    <div class="form-group" style="margin-top: 10px;">
                        <label for="admin" class="col-sm-1 control-label">超级管理员：</label>
                        <div class="col-sm-4">
                            <input type="checkbox" id="admin" name="admin">
                        </div>
                    </div>
                    <script>
                        $("input[name='admin']").prop("checked", ${employee.admin});
                    </script>


                    <div class="form-group " id="role">
                        <label for="role" class="col-sm-2 control-label">分配角色：</label><br/>
                        <div class="row" style="margin-top: 10px">
                            <div class="col-sm-2 col-sm-offset-2">
                                <select multiple class="form-control allRoles" size="15">
                                    <c:forEach items="${roles}" var="r">
                                        <option value="${r.id}">${r.name}</option>
                                    </c:forEach>
                                </select>
                            </div>


                            <div class="col-sm-1" style="margin-top: 60px;" align="center">
                                <div>
                                    <a type="button" class="btn btn-primary  " style="margin-top: 10px" title="右移动"
                                       onclick="moveSelected('allRoles', 'selfRoles')">
                                        <span class="glyphicon glyphicon-menu-right"></span>
                                    </a>
                                </div>
                                <div>
                                    <a type="button" class="btn btn-primary " style="margin-top: 10px" title="左移动"
                                       onclick="moveSelected('selfRoles', 'allRoles')">
                                        <span class="glyphicon glyphicon-menu-left"></span>
                                    </a>
                                </div>
                                <div>
                                    <a type="button" class="btn btn-primary " style="margin-top: 10px" title="全右移动"
                                       onclick="moveAll('allRoles', 'selfRoles')">
                                        <span class="glyphicon glyphicon-forward"></span>
                                    </a>
                                </div>
                                <div>
                                    <a type="button" class="btn btn-primary " style="margin-top: 10px" title="全左移动"
                                       onclick="moveAll('selfRoles', 'allRoles')">
                                        <span class="glyphicon glyphicon-backward"></span>
                                    </a>
                                </div>
                            </div>

                            <%--编辑操作回显当前员工对应的角色信息--%>
                            <div class="col-sm-2">
                                <select multiple class="form-control selfRoles" size="15" name="roleIds">
                                    <c:forEach items="${employee.roles}" var="r">
                                        <option value="${r.id}">${r.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-1 col-sm-6">
                            <button id="submitBtn" type="submit" class="btn btn-primary">保存</button>
                            <button type="reset" class="btn btn-danger">重置</button>
                        </div>
                    </div>
                </form>

            </div>

        </section>
    </div>
    <%@include file="/WEB-INF/views/common/footer.jsp" %>
</div>


</body>
</html>