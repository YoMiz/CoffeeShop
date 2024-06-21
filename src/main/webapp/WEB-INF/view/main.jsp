<%@  page pageEncoding="UTF-8"%>
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
<link rel="stylesheet" href="./css/style.css">
<title>Top | Your Coffee</title>
</head>

<body>
	<header>
		<h1 class="Title">Your Coffee Shop</h1>
		<nav>
			<ul>
				<li class="current"><a href="">HOME</a></li>
				<li><a href="#recommend">recommend</a></li>
				<li><a href="#custom">custom</a></li>
				<li><a href="#access">access</a></li>
			</ul>
		</nav>
	</header>


	<main class="main">
		<div class="first-view">
			<div class="first-view-text">
				<h1>
					<span>Have</span> Fun <br> <span>with <br>Your
					</span> Coffee.
				</h1>
				<p>
					Have you ever Dreamt <br>of Designing your COFFEE?
				</p>
			</div>
			<a href="FAlogin"> <img src="./images/character/BradBean.png"
				width="200px" height="200px" alt="イメージキャラクター：ブラビンさん（仮）" /></a>

		</div>


		<h2 id="recommend" class="content">recommend</h2>
		<form action="cart_login">
			<section>
				<ul class="flex-container">
					<c:forEach var="ProductBeans" items="${recommendList}"
						varStatus="status">
						<li class="recommend-type">
							<ul>
								<li class="product-name"><c:out
										value="${ProductBeans.productName}" /></li>
								<li><img
									src="./images/coffeetype/<c:out value='${ProductBeans.productPict}'/>"
									alt="" width="200" height="200" /></li>
								<li><c:out value="${ProductBeans.productDescription}" /></li>
								<li>酸味：苦味<c:out value="${ProductBeans.productSourBitter}" /></li>
								<li>コク：キレ<c:out value="${ProductBeans.productBodySharp}" /></li>
								<li>香り<c:out value="${ProductBeans.productFlavor}" /></li>
								<li>甘味<c:out value="${ProductBeans.productSweet}" /></li>
								<li>単価<c:out value="${ProductBeans.productPrice}" /></li>
								<c:if test="${not soldOutMap[ProductBeans.productId]}">
									<input type="number"
										name="<c:out value='${ProductBeans.productId}'/>" max="10"
										min="0" step="1" value="" />kg
    								<input type="checkbox" name="recommend"
										value="${ProductBeans.productId}"
										id="${ProductBeans.productId}" hidden />
								</c:if>
								<c:if test="${soldOutMap[ProductBeans.productId]}">
									<li>SOLD OUT</li>
								</c:if>
							</ul>
						</li>
					</c:forEach>
				</ul>
			</section>

			<section>
				<h2 id="custom" class="content">custom</h2>
				<div class="sub_contents">
					<h3>簡単カスタム</h3>
					<ul>
						<li><input type="radio" name="designed_coffee" value="sour">酸味が高い</li>
						<li><input type="radio" name="designed_coffee" value="full">コクが高い</li>
						<li><input type="radio" name="designed_coffee" value="scent">香りが高い</li>
						<li><input type="radio" name="designed_coffee" value="bitter">苦味が高い</li>
						<li><input type="radio" name="designed_coffee"
							value="popular">ランダムな人気の組み合わせ</li>
					</ul>
					<p style="text-align: right;">当店ではお客様のお好みのブレンドで提供しております。</p>

				</div>

				<ul class="container-fluid">
					<div class="row">
						<c:forEach var="ProductBeans" items="${beansList}">
							<li class="coffeebean col-4">
								<ul>
									<li class="product-name"><c:out
											value="${ProductBeans.productName}" /></li>
									<li><img
										src="./images/contractor/
								<c:out value='${ProductBeans.productPict}'/>"
										alt="" width="200" height="200" /></li>
									<li><c:out value="${ProductBeans.productDescription}" /></li>
									<li>酸味：苦味<c:out value="${ProductBeans.productSourBitter}" /></li>
									<li>コク：キレ<c:out value="${ProductBeans.productBodySharp}" /></li>
									<li>香り<c:out value="${ProductBeans.productFlavor}" /></li>
									<li>甘味<c:out value="${ProductBeans.productSweet}" /></li>
									<li>単価<c:out value="${ProductBeans.productPrice}" /></li>
									<li><c:if test="${not soldOutMap[ProductBeans.productId]}">
											<input type="number"
												name="<c:out value='${ProductBeans.productId}'/>" max="10"
												min="0" step="1" value="" />kg
    								<input type="checkbox" name="recommend"
												value="${ProductBeans.productId}"
												id="${ProductBeans.productId}" hidden />
										</c:if> <c:if test="${soldOutMap[ProductBeans.productId]}">
   										 SOLD OUT
								</c:if>
								</ul>
							</li>
						</c:forEach>
					</div>
				</ul>
			</section>
			<div class="btnposition">
				<input type="submit" value="カートへ" class="btn" />
			</div>
		</form>

		<section>
			<h2 id="access" class="content">access</h2>

			<div class="map container-fluid">
				<div class="row">
					<iframe class="col-8"
						src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d809.9971032073738!2d139.69948413476433!3d35.701902751642585!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x60188d2f6ccb2b73%3A0x63c38fd40aa96dad!2z44CSMTY5LTAwNzMg5p2x5Lqs6YO95paw5a6_5Yy655m-5Lq655S677yS5LiB55uu77yU4oiS77yYIOOCueODhuOCouODvOOCuuODk-ODqyA46ZqO!5e0!3m2!1sja!2sjp!4v1697431931503!5m2!1sja!2sjp"
						width="450" height="350" style="border: 0;" allowfullscreen=""
						loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
					<div class="access col">
						<p>〒169-0073</p>
						<p>東京都新宿区百人町２丁目４−８</p>
						<p>ステアーズビル 8階</p>
						<p>
							<a href="FAQ">お問い合わせ</a>
						</p>
					</div>
				</div>
			</div>
		</section>
	</main>

	<footer>
		<nav>
			<ul>
				<li class="current"><a href="">HOME</a></li>
				<li><a href="#recommend">recommend</a></li>
				<li><a href="#custom">custom</a></li>
				<li><a href="#access">access</a></li>
			</ul>
		</nav>
		<p class="copyright">©YourCoffee Co.</p>
	</footer>
	<script src="js/bootstrap.bundle.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.7.1.min.js"
		integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
		crossorigin="anonymous"></script>
	<script>
		$(document).ready(function() {
		});
	</script>
	<script>
		// doc内のinput type="number"を取得する。
		document.querySelectorAll('input[type="number"]').forEach(function(e) {
			// 各`input`要素に対して、'change'イベントリスナーを追加します。
			e.addEventListener('change', function() {
				// 'change'イベントが発生した`input`要素と同じ名前（`this.name`）を持つ要素を取得します。
				let checkbox = document.getElementById(this.name);
				// その要素の'value'属性を、`input`要素の名前と値を"-"で連結した文字列に設定します。
				checkbox.setAttribute('value', this.name + "-" + this.value);
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
	</script>
</body>

</html>
</html>