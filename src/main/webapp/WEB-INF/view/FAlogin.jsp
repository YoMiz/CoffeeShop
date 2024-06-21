<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<form action="FAlogin" method="post">
		<tr>
			<th>管理者名</th>
			<td><input type="text" name="FAloginName" /></td>
			<th>管理者パスワード</th>
			<td><input type="text" name="FAloginPass" /></td>
			<th>the button</th>
			<td><input type="submit" value="管理者ページへ" /></td>
		</tr>
	</form>
</body>
</html>