<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<linkhref ="https://cdn.jsdelivr.net/npm/bootstrap
	@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="./css/bootstrap.min.css">
<link rel="stylesheet" href="./css/styleCartLogin.css">
<meta charset="UTF-8">
<title>NewMember</title>
</head>
<body>
	<header>
		<div class="image_beans"></div>
		<nav>
			<ul>
				<li><a href="main">HOME</a></li>
			</ul>
		</nav>
		<div class="page_title">
			<h1>ユーザー登録</h1>
		</div>

	</header>

	<div>
		<form action="newMemberDone" method="post">
			<table class="cart container-block new-user-list">
				<tr>
					<th>氏名</th>
					<td><input class="col-12" type="text" name="user_name" /></td>
				</tr>
				<tr>
					<th>住所</th>
					<td><input class="col-12" type="text" name="user_address" /></td>
				</tr>
				<tr>
					<th>E-Mail</th>
					<td><input class="col-12" type="text" name="E-mail" /></td>
				</tr>
				<tr>
					<th>パスワード</th>
					<td><input class="col-12" type="text" name="login_pass" /></td>
				</tr>
				<tr>
					<th>パスワード確認</th>
					<td><input class="col-12" type="text"
						name="reconfirmed_login_pass" /></td>
				</tr>
				<tr>
					<th></th>
					<td><input type="submit" value="登録" /></td>
				</tr>

			</table>
		</form>
	</div>
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