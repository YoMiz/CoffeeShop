<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/FAstyle.css">
<meta charset="UTF-8">
<title>顧客 | 管理ページ</title>
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
			<h2 class="page_title">顧客管理</h2>
		</div>
	</div>
	<div class="main">
		<table border="1">
			<tr>
				<th>顧客番号</th>
				<th>氏名</th>
				<th>パスワード</th>
				<th>住所</th>
				<th>メール</th>
				<th>作成日</th>
				<th>更新日</th>
				<th>削除日</th>
			</tr>
			<c:forEach var="User" items="${userList}">
				<tr>
					<td><c:out value="${User.userId}" /></td>
					<td><c:out value="${User.userName}" /></td>
					<td><c:out value="${User.userPass}" /></td>
					<td><c:out value="${User.userAddress}" /></td>
					<td><c:out value="${User.userMail}" /></td>
					<td><c:out value="${User.userCreated}" /></td>
					<td><c:out value="${User.userUpdated}" /></td>
					<td><c:out value="${User.userDeleted}" /></td>
				</tr>
			</c:forEach>
		</table>
		<br />
		<form action="" method="post">
			<div class="chg">
				<table>
					<tr>
						<th>顧客番号</th>
						<th>氏名</th>
						<th>パスワード</th>
						<th>住所</th>
						<th>メール</th>
						<th>削除</th>
						<th>変更</th>
					</tr>
					<tr>
						<td><input type="number" id="userId" name="SelectUserId"
							class="userId" max="${listLen}" min="1"
							onChange="updateUserInfo()" /></td>
						<td><input type="text" id="userName" name="SelectUserName" /></td>
						<td><input type="password" id="userPass"
							name="SelectUserPass" /></td>
						<td><input type="text" id="userAddress"
							name="SelectUserAddress" /></td>
						<td><input type="text" id="userMail" name="SelectUserMail" /></td>
						<td><input type="checkbox" name="SelectUserDeleted" /></td>
						<td><input type="submit" value="変更" /></td>
					</tr>
				</table>
			</div>
		</form>
	</div>
	<br />
	<script src="js/bootstrap.bundle.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.7.1.min.js"
		integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
		crossorigin="anonymous"></script>
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