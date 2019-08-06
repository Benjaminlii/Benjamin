<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>加载中...</title>
</head>
<body>
<%-- 鬼知道这里为啥这么繁琐的用jsp表单访问,简简单单的发请求不是挺好的吗? --%>
<form action="${p2p_alipay_pay_url}" method="post">
    <input type="hidden" name="out_trade_no" value="${out_trade_no}"/>
    <input type="hidden" name="total_amount" value="${total_amount}"/>
    <input type="hidden" name="subject" value="${subject}"/>
    <input type="hidden" name="body" value="${body}"/>
</form>
<script type="text/javascript">document.forms[0].submit();</script>
</body>
</html>