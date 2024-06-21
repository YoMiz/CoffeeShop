<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*,java.text.*"%>
<!DOCTYPE html>
<html>
<head>
<linkhref ="https://cdn.jsdelivr.net/npm/bootstrap
	@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="./css/bootstrap.min.css">
<link rel="stylesheet" href="./css/styleCartLogin.css">
<meta charset="UTF-8">
<title>ThankYou | Your Coffee</title>
</head>
<body>
	<%
	Calendar.getInstance(); // 現在の日付を取得
	Calendar cal = Calendar.getInstance();
	cal.add(Calendar.DATE, 7);
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	String dateAfterOneWeek = sdf.format(cal.getTime());
	%>

	<header>
		<div class="image_beans"></div>
		<nav>
			<ul>
				<li><a href="main">HOME</a></li>
			</ul>
		</nav>
	</header>

	<h1>ご購入有り難うございました。</h1>
	<div class="container-block"></div>
	<table>
		<tr>
			<th class="col-2">注文番号</th>
			<td class="col-4"><c:out value="${estimate_num}" /></td>
		</tr>
		<tr>
			<th>代金</th>
			<td><c:out value="${totalPrice}" />円</td>
		</tr>
		<tr>
			<th>お届け予定日時</th>
			<td><%=dateAfterOneWeek%></td>
		</tr>
		<tr>
		<th></th>
			<td>
				<form action="main">
					<input type="submit" value="ログアウト" />
				</form>
			</td>
		</tr>
	</table>
	<br />
	<footer>
		<nav>
			<ul>
				<li><a href="main">HOME</a></li>
			</ul>
		</nav>
		<p class="copyright">©YourCoffee Co.</p>
	</footer>
</body>
</html>