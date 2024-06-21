<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/FAstyle.css">
<meta charset="UTF-8">
<title>売上 | 管理ページ</title>
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
						<li><a href="Profit">売上</a></li>
						<li><a href="Users">顧客</a></li>
						<li><a href="Stock">商品</a></li>
						<li><a href="Trend">トレンド</a></li>
					</ul>
					<br />
				</nav>
			</div>
		</div>
	</header>
	<!-- <div id="new-falling-images">
		<img class="new-falling-image" src="../images/character/money.png"
			alt="falling images" width="40px" height="30px" /> <img
			class="new-falling-image" src="../images/character/money.png"
			alt="falling images" width="40px" height="30px" /> <img
			class="new-falling-image" src="../images/character/money.png"
			alt="falling images" width="40px" height="30px" /> <img
			class="new-falling-image" src="../images/character/money.png"
			alt="falling images" width="40px" height="30px" /> <img
			class="new-falling-image" src="../images/character/money.png"
			alt="falling images" width="40px" height="30px" /> <img
			class="new-falling-image" src="../images/character/money.png"
			alt="falling images" width="40px" height="30px" /> <img
			class="new-falling-image" src="../images/character/money.png"
			alt="falling images" width="40px" height="30px" /> <img
			class="new-falling-image" src="../images/character/money.png"
			alt="falling images" width="40px" height="30px" />
	</div> -->
	<div class="img-view">
			<div class="illustration"></div>
		<div class="img-view-text">
			<h2>売上管理</h2>
		</div>
	</div>
	<div class="main">
		<table border="1">
			<tr>
				<th>作成日</th>
				<th>見積番号</th>
				<th>商品名</th>
				<th>商品コード</th>
				<th>販売数量</th>
				<th>販売単価</th>
				<th>販売金額</th>
				<th>単価</th>
				<th>原価</th>
				<th>利益</th>
				<th>顧客名</th>
				<th>顧客コード</th>
				<th>状況</th>
			</tr>
			<c:forEach var="Profit" items="${profitList}">
				<tr>
					<td><c:out value="${Profit.created}" /></td>
					<td><c:out value="${Profit.estNum}" /></td>
					<td><c:out value="${Profit.productName}" /></td>
					<td><c:out value="${Profit.productNum}" /></td>
					<td><c:out value="${Profit.quantity}" /></td>
					<td><c:out value="${Profit.salePrice}" /></td>
					<td><c:out value="${Profit.totalSales}" /></td>
					<td><c:out value="${Profit.unitCost}" /></td>
					<td><c:out value="${Profit.totalCost}" /></td>
					<td><c:out value="${Profit.diff}" /></td>
					<td><c:out value="${Profit.userName}" /></td>
					<td><c:out value="${Profit.userId}" /></td>
					<td><c:out value="${Profit.estStatus}" /></td>
				</tr>
			</c:forEach>
		</table>
	</div>
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
	<script>
	window.onload = function() {
	    const images = document.getElementsByClassName('new-falling-image');
	    for (let i = 0; i < images.length; i++) {
	        const image = images[i];
	        // 初期位置をよりランダムに設定
	        image.style.left = (Math.random() * window.innerWidth / 2 + window.innerWidth / 4) + 'px';
	        // 落下速度をよりランダムに設定
	        	        image.style.animationDuration = Math.random() * 8 + 2 + 's';
	        // 揺れ幅を設定
	        image.style.animationName = 'sway, fall';
	    }
	};
	</script>
</body>
</html>

