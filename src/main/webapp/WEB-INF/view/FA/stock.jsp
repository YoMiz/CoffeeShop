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
<title>在庫表 | 管理ページ</title>
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
		<div class="img-view-text">
			<div class="illustration"></div>
			<h2 class="page_title">在庫管理</h2>
		</div>
	</div>
	<div class="main">
		<table border="1">
			<tr>
				<th>在庫番号</th>
				<th>商品名</th>
				<th>数量</th>
				<th>商品単価</th>
				<th>商品価格</th>
				<th>業者名</th>
				<th>連絡先</th>
				<th>商品説明文</th>
				<th>写真ファイル</th>
				<th>価格更新日</th>
				<th>入荷日</th>

			</tr>
			<c:forEach var="Item" items="${inventoryList}">
				<tr>
					<td><c:out value="${Item.productId}" /></td>
					<td><c:out value="${Item.productName}" /></td>
					<td><c:out value="${Item.quantity}" /></td>
					<td><c:out value="${Item.itemCost}" /></td>
					<td><c:out value="${Item.itemPrice}" /></td>
					<td><c:out value="${Item.companyName}" /></td>
					<td><c:out value="${Item.contact}" /></td>
					<td><c:out value="${Item.description}" /></td>
					<td><c:out value="${Item.file}" /></td>
					<td><c:out value="${Item.costUpdated}" /></td>
					<td><c:out value="${Item.inventoryUpdated}" /></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<br />
	<div class="flex-container">
		<form action="" method="post">
			<table>
				<tr>
					<th width="100%">製品の種類</th>
					<th>製品名</th>
					<th>在庫数</th>
					<th>仕入れ値</th>
					<th>販売価格</th>
					<th>会社名</th>
					<th>連絡先</th>
					<th>説明文</th>
					<th>ファイル名</th>
					<th></th>
				</tr>
				<tr>
					<td><span><input type="radio" name="itemType"
							id="blend" value="1" checked></span> <span><label
							for="blend">ブレンド</label></span> <span><input type="radio"
							name="itemType" id="coffeeBeans" value="2"></span> <span><label
							for="coffeeBeans">コーヒー豆</label></span></td>
					<td><input type="text" name="itemName" value="製品名" /></td>
					<td><input type="number" name="quantity" value="在庫数" min="100"
						step="10" /></td>
					<td><input type="number" name="itemCost" value="仕入れ値"
						min="100" step="10" /></td>
					<td><input type="number" name="itemPrice" value="販売価格"
						min="100" step="10" /></td>
					<td><input type="text" name="companyName" value="会社名" /></td>
					<td><input type="text" name="contact" value="連絡先" /></td>
					<td><input type="text" name="description" value="製品説明文" /></td>
					<td><input type="text" name="fileName" value="画像ファイル名" /></td>
					<td><input type="submit" value="追加" /></td>
				</tr>
			</table>
		</form>
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