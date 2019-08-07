<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>加载中....</title>
</head>
<body>
<form action="${pay_p2p_return_url}" method="post">
    <input type="hidden" name="signVerified" value="${signVerified}"/>
    <c:forEach items="${params}" var="paramMap">
        <input type="hidden" name="${paramMap.key}" value="${paramMap.value}"/>
    </c:forEach>
</form>
<script type="text/javascript">document.forms[0].submit();</script>
</body>
</html>