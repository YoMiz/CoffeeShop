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
<title>reconfirm | Your Coffee</title>
</head>
<body>
	<header>
		<div class="image_beans"></div>
		<nav>
			<ul>
				<li><a href="main">HOME</a></li>
			</ul>
		</nav>
		<div class="cart container-fluid">
			<div class="page_title">
				<h1>購入確認</h1>
			</div>
		</div>
	</header>
	<form action="thankyou" method="post">
		<table class="reconfirm-list">
			<tr class="col-5">
				<th>氏名</th>
				<td>"${user.getUserName()}"</td>
			</tr>
			<tr>
				<th>お届け先</th>
				<td><c:out value="${user.getUserAddress()}" /></td>
			</tr>
			<tr>
				<th>E-Mail</th>
				<td>"${user.getUserMail()}"</td>
			</tr>
			<tr>
				<th>購入商品</th>
				<td>
				<c:forEach var="CartItem" items="${ReconfirmCartItemList}">
					<p><c:out value="${CartItem.itemName}" /> : <c:out
							value="${CartItem.itemAmount}" />個</p>
				</c:forEach>
				</td>
			</tr>
			<tr>
				<th>合計金額</th>
				<td><c:out value="${totalPrice}"/>円</td>
			</tr>
			<tr>
				<th></th>
				<td><input type="submit" value="購入する" /></td>
			</tr>
		</table>
	</form>
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