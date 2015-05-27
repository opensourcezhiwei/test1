<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="${pay.payUrl}">
		<input type="hidden" name="transName" value="${pay.transName}" />
		<input type="hidden" name="Plain" value="${pay.plain}" />
		<input type="hidden" name="Signature" value="${pay.signature}" />
	</form>
	<script type="text/javascript">
		document.forms[0].submit();
	</script>
</body>
</html>