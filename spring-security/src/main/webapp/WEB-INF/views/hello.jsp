<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <script>
        function formSubmit() {
            alert(document.getElementById("logoutForm").action );
            document.getElementById("logoutForm").submit();
        }
    </script>
</head>
<body>
<h1>欢迎进入 Spring Security</h1>
<h1>你好，<security:authentication property="principal.username"/></h1>

<form action='<c:url value='/j_spring_security_logout' />' method="post" id="logoutForm">
    <input type="hidden" name="${_csrf.parameterName}"
           value="${_csrf.token}" />
</form>
<a href="javascript:formSubmit()">注销登录</a>
<a href="<c:url value="/admin" />">后台管理</a>
<security:authorize access="hasRole('ROLE_ADMIN')"><a>admin 用户才可以看到</a></security:authorize>
<security:authorize url="/admin**"><a>/admin**</a></security:authorize>
</body>
</html>