<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>欢迎登录页</title>
    <style type="text/css">
        .error {
            padding: 15px;
            margin-bottom: 20px;
            border: 1px solid transparent;
            border-radius: 4px;
            color: #a94442;
            background-color: #f2dede;
            border-color: #ebccd1;
        }
        .msg {
            padding: 15px;
            margin-bottom: 20px;
            border: 1px solid transparent;
            border-radius: 4px;
            color: #31708f;
            background-color: #d9edf7;
            border-color: #bce8f1;
        }
        #login-box {
            width: 300px;
            padding: 20px;
            margin: 100px auto;
            background: #fff;
            -webkit-border-radius: 2px;
            -moz-border-radius: 2px;
            border: 1px solid #000;
        }
    </style>
</head>
<body>
<div id="login-box">
    <h3>Spring Security 欢迎进入</h3>
    <c:if test="${not empty error }">
        <div class="error">${error}</div>
    </c:if>
    <c:if test="${not empty msg }">
        <div class="msg">${msg}</div>
    </c:if>
    <form action='<c:url value='/j_spring_security_check' />' method="post">
        <table>
            <tr>
                <td>用户名：</td>
                <td>
                    <input type="text" name="username"/>
                </td>
            </tr>
            <tr>
                <td>密码：</td>
                <td>
                    <input type="password" name="password"/>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input id="remember_me" name="_spring_security_remember_me" type="checkbox"/>
                    <label for="remember_me">记住密码</label>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="登录" />
                </td>
            </tr>
        </table>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </form>
</div>
</body>
</html>