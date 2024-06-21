<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="ja">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="./css/bootstrap.min.css">
<link rel="stylesheet" href="./css/styleCartLogin.css">
<title>CartLogin | Your Coffee</title>
</head>

<body>
	<header>
		<div class="image_beans"></div>
		<nav>
			<ul>
				<li><a href="main">HOME</a></li>
				<li><a href="#Login">MEMBERS</a></li>
				<li><a href="newMember">新規登録</a></li>
			</ul>
		</nav>
		<div class="cart container-fluid">
			<div class="page_title">
				<h1>買い物カゴ</h1>
			</div>
			<div class="login container-fluid">
				<div class="loginBtn">
					<form action="cart_login_member" method="post">
						<c:if test="${empty user_name}">
							<div class="form-group row">
								<label for="user_name" class="col-sm-1 col-form-label">UserName</label>
								<div class="col-3">
									<input type="text" class="form-control" id="user_name"
										name="user_name" placeholder="USER NAME">
								</div>
							</div>
							<div class="form-group row">
								<label for="user_pass" class="col-sm-1 col-form-label">Password</label>
								<div class="col-3">
									<input type="password" class="form-control" id="user_pass"
										name="user_pass" placeholder="PASSWORD">
								</div>
							</div>
							<br />
							<div class="row">
								<div class="col-sm-2">
									<input type="submit" class="form-control button" value="ログイン">
								</div>
							</div>
						</c:if>
						<c:forEach var="CartItem" items="${cartItemList}">
							<input type="hidden" id="<c:out value ="${CartItem.itemId}"/>"
								class="amount" name="<c:out value="${CartItem.itemId}"/>" />
						</c:forEach>
					</form>
					<c:if test="${ empty user_name }">
						<form action="newMember" class="newMemberBtn">
							<input type="submit" class="form-control button" value="新規登録">
						</form>
					</c:if>
				</div>
			</div>

			<c:if test="${not empty error}">
				<div class="error">ユーザー名かパスワードが正しくありません。</div>
			</c:if>
			<c:if test="${empty error and not empty user_name}">
				<div class="welcome">
					<c:out value="ようこそ　${user_name}　様" />
				</div>
			</c:if>
			<br />
		</div>
	</header>
	<br />
	<div class="cartItemList container-block">
		<table border="1">
			<tr>
				<th class="col-3"><img
					src="./images/logo/Logo_coffeeBeans (1).jpeg" alt="豆" width="20px"
					height="20px"> 商品名 <img
					src="./images/logo/Logo_coffeeBeans (1).jpeg" alt="豆" width="20px"
					height="20px"></th>
				<th class="col-2"><img
					src="./images/logo/Logo_coffeeBeans (1).jpeg" alt="豆" width="20px"
					height="20px"> 商品価格 <img
					src="./images/logo/Logo_coffeeBeans (1).jpeg" alt="豆" width="20px"
					height="20px"></th>
				<th class="col-2"><img
					src="./images/logo/Logo_coffeeBeans (1).jpeg" alt="豆" width="20px"
					height="20px"> 数量 <img
					src="./images/logo/Logo_coffeeBeans (1).jpeg" alt="豆" width="20px"
					height="20px"></th>
				<th class="col-3"><img
					src="./images/logo/Logo_coffeeBeans (1).jpeg" alt="豆" width="20px"
					height="20px"> 小計 <img
					src="./images/logo/Logo_coffeeBeans (1).jpeg" alt="豆" width="20px"
					height="20px"></th>
			</tr>
			<c:forEach var="CartItem" items="${cartItemList}">
				<tr class="cart-item">
					<td><c:out value="${CartItem.itemName}" /></td>
					<td class="price"><c:out value="${CartItem.itemPrice}" /></td>
					<td><input type="number"
						name="<c:out value="${CartItem.itemId}"/>" class="amount"
						value="${CartItem.itemAmount}" id="A"
						onchange="updateSubtotal(), amountChg()" min="0" max="10" step="1" />
						<input type="checkbox" value="${CartItem.itemId}"
						id="${CartItem.itemId}" hidden />
					<td class="subtotal"><c:out
							value="${CartItem.itemPrice * CartItem.itemAmount}" /> 円</td>
				</tr>
			</c:forEach>


			<tr>
				<td>合計</td>
				<td></td>
				<td></td>
				<td id="total">円</td>
			</tr>

		</table>
	</div>
	<c:if test="${empty error and not empty user_name}">
		<p>＊不要な商品の場合、数量を0にしてください。</p>
	</c:if>
	<br />

	<c:if test="${empty error and not empty user_name}">
		<div class="sub-contents container-block">
			<br />
			<h2>配送先</h2>
			<br />
		</div>
		<div class="user_info">
			<br />
			<div class="container-block">
				<form action="reconfirm" method="post">
					<table border="1">
						<tr>
							<th class="col-2"></th>
							<th class="title-user col-5">ユーザー情報</th>
						</tr>
						<tr>
							<th>氏名</th>
							<td><input class="col-12" type="text" name="new_user_name"
								value="${user.getUserName()}" /></td>
						</tr>
						<tr>
							<th>住所</th>
							<td><input class="col-12" type="text"
								name="new_user_address" value="${user.getUserAddress()}" /></td>
						</tr>
						<tr>
							<th>E-mail</th>
							<td><input class="col-12" type="text" name="new_user_mail"
								value="${user.getUserMail()}" /></td>
							<input type="hidden" name="total" value="" id="recontotal" />
						</tr>
						<tr>
							<th></th>
							<td>
								<div class="reconfirm-btn">
									<input class="col-6" type="submit" value="購入確認ページへ" />
								</div>
							</td>
						</tr>
					</table>
					<br />
				</form>
			</div>
		</div>
	</c:if>
	<br />
	<footer>
		<nav>
			<ul>
				<li><a href="main">HOME</a></li>
				<li><a href="#Login">MEMBERS</a></li>
				<li><a href="newMember">新規登録</a></li>
			</ul>
		</nav>
		<p class="copyright">©YourCoffee Co.</p>
	</footer>
	<script src="js/bootstrap.bundle.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.7.1.min.js"
		integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
		crossorigin="anonymous"></script>
	<script>
		window.onload = function() {
			// doc内のinput type="number"を取得する。
			document.querySelectorAll('input[type="number"]').forEach(
					function(e) {
						// 各`input`要素に対して、'change'イベントリスナーを追加します。
						e.addEventListener('change', function() {
							// 'change'イベントが発生した`input`要素と同じ名前（`this.name`）を持つ要素を取得します。
							let checkbox = document.getElementById(this.name);
							// その要素の'value'属性を、`input`要素の名前と値を"-"で連結した文字列に設定します。
							checkbox.setAttribute('value', this.name + "-"
									+ this.value);
							// `input`要素の値が0より大きい場合、その要素の'checked'属性を設定します。
							if (this.value > 0) {
								checkbox.setAttribute('checked', 'checked');
							} else {
								// `input`要素の値が0以下の場合、その要素の'checked'属性を削除します。
								checkbox.removeAttribute('checked');
							}
							// コンソールに、`input`要素と同じ名前を持つ要素の情報を出力します。
							console.log(document.getElementById(this.name));
						});
					});

			// すべての入力ボックスにイベントリスナーを追加
			document.querySelectorAll('.amount').forEach(function(inputA) {
				inputA.addEventListener('change', function() {
					// 同じIDを持つ隠し入力ボックスを取得
					var inputB = document.getElementById(this.name);
					// 入力ボックスBが存在する場合のみ値を設定
					if (inputB) {
						inputB.value = this.value;
					}
				});
			});

			// すべての入力ボックスに対して処理を行う
			document.querySelectorAll('.amount').forEach(function(inputA) {
				// 同じIDを持つ隠し入力ボックスを取得
				var inputB = document.getElementById(inputA.name);
				// 入力ボックスAの値を入力ボックスBに設定
				if (inputB) {
					inputB.value = inputA.value;
				}
			});

			updateSubtotal();
		};

		function updateSubtotal() {
			/* サーブレットからリストを取得する */
			var cartItems = document.getElementsByClassName('cart-item');
			/* 変数totalを先に作成しておく */
			var total = 0;
			/* 変数iを作り、リストの長さより小さい間は繰り返し作業させる */
			for (var i = 0; i < cartItems.length; i++) {
				/* リストを呼び出す */
				var cartItem = cartItems[i];
				/* リストに格納されているカラムから、それぞれの変数に格納していく */
				var price = cartItem.getElementsByClassName('price')[0];
				var input = cartItem.getElementsByClassName('amount')[0];
				var subtotal = cartItem.getElementsByClassName('subtotal')[0];
				var amount = input.value;/* amountは、上記input変数と同等の為、引数に指定 */
				/* itemPriceは変動する値のため、タグの"price"クラスを目印にしている */
				var itemPrice = price.innerText;
				var itemSubtotal = amount * itemPrice;
				/* 小計は"subtotal"に格納する。タグ、subtotalを目印にしている。 */
				subtotal.innerText = itemSubtotal + " 円";
				/* 合計金額にて商品ごとのsubtotalを加算させる */
				total += itemSubtotal;
			}
			document.getElementById('total').innerText = total + " 円";
			document.getElementById('recontotal').value = total;
		}
	</script>
</body>

</html>
