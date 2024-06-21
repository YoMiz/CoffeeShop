<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/FAstyle.css">
<meta charset="UTF-8">
<title>仕入れ・業者 | 管理ページ</title>
</head>
<body>
<header>
		<div class="image">
			<div class="image_text">
				<h1>管理ページ</h1>
			</div>
			<div class="menu">
				<nav>
					<ul>
						<li><a href="#profit">売上</a></li>
						<li><a href="users">顧客</a></li>
						<li><a href="stock">在庫</a></li>
						<li><a href="contractor">仕入れ業者</a></li>
						<li><a href="trend">トレンド</a></li>
					</ul>
					<br />
				</nav>
			</div>
		</div>
	</header>

	<div class="img-view">
		<div class="img-view-text">
			<div class="illustration"></div>
			<h2 class="page_title">
				業者・仕入れ管理
				</h1>
		</div>
	</div>
	<div class="main">
		<table border="1">
			<tr>
				<th>業者番号</th>
				<th>会社名</th>
				<th>商品番号</th>
				<th>商品名</th>
				<th>仕入れ値</th>
				<th>販売価格</th>
				<th>連絡先</th>
				<th>作成日</th>
				<th>情報更新日</th>
				<th>削除日</th>
				
			</tr>
			<c:forEach var="Item" items="${itemList}">
				<tr>
					<td><c:out value="${Item.inventoryId}" /></td>
					<td><c:out value="${Item.productId}" /></td>
					<td><c:out value="${Item.productName}" /></td>
					<td><c:out value="${Item.productCost}" /></td>
					<td><c:out value="${Item.productPrice}" /></td>
					<td><c:out value="${Item.inventoryQuantity}" /></td>
					<td><c:out value="${Item.productUpdated}" /></td>
					<td><c:out value="${Item.inventoryUpdated}" /></td>
					<td><c:out value="${Item.productDeleted}" /></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<br />
	<input class="dummy1" type="submit" value="変更" />
	<br />

	<!-- ボタン -->
	<div class="openbtn" onclick="toggleNav()">
		<span></span> <span></span> <span></span>
	</div>
	<!-- ナビゲーション -->
	<nav id="g-nav">
		<div id="g-nav-list">
			<ul>
				<li><a href="#">HOME</a></li>
				<li><a href="#">ブロク記事</a></li>
				<li class="parent"><a href="#">会社概要</a>
					<ul>
						<li><a href="#">サービス１</a></li>
						<li><a href="#">サービス２</a></li>
						<li class="parent"><a href="#">サービス３</a>
							<ul>
								<li><a href="#">カテゴリー１</a></li>
								<li><a href="#">カテゴリー２</a></li>
							</ul></li>
					</ul></li>
				<li><a href="#">会社案内ダウンロード</a></li>
				<li><a href="#">採用情報</a></li>
				<li><a href="#">お問い合わせ</a></li>
			</ul>
		</div>
	</nav>

	<footer>
		<div class="menu">
			<br />
			<nav>
				<ul>
					<li><a href="#profit">売上</a></li>
					<li><a href="users">顧客</a></li>
					<li><a href="stock">在庫</a></li>
					<li><a href="contractor">仕入れ業者</a></li>
					<li><a href="trend">トレンド</a></li>
				</ul>
			</nav>
			<br />
		</div>
	</footer>
</body>
</html>