<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>是你呀</title>
</head>
<body class="hold-transition skin-blue sidebar-mini">

	<form action= "${basePath }/user/doLogin" method="post" >
		<table>
			<tr>
				<td>用户名：</td>
				<td><input type="text" name="userName" value="${user.userName }"> </td>
			</tr>
			<tr>
				<td>密码：</td>
				<td><input type="password" name="password"  value="${user.password }" > </td>
			</tr>
			<tr>
				<td><input type="submit"></td>
				<td>${msg }</td>
			</tr>
		</table>
	</form>
</body>
</html>
