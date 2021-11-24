<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>黑马商城信息展示</title>
		<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
		<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
	</head>

	<body>
		<div class="container-fluid">

			<!-- 引入header.jsp -->
			<jsp:include page="/header.jsp"></jsp:include>

			<div class="container-fluid">
				<div class="main_con">
					<h2>公司简介</h2>
					<hr/>
					<div>
						<p>
							<font color="red">“如虹保险”</font>是一个通过顾客自主筛选及诉求描述、客服个性化推荐的网上保险平台，具体有四大功能模块，分别为保险超市，产品筛选，咨询及售后，个人中心，旨在为顾客提供量身定做的保险计划。
						</p>

						<p>
							用户可以通过网站中的AI顾问解答绝大部分的问题，从而感受到及时的回复以及高效的服务，并且筛选购买合乎心意的保险。在完成购买之后，我们网站也会对顾客进行售后服务，及时追踪反馈保险进度。
						</p>

						
					</div>
				</div>
			</div>
		</div>
		
		<!-- 引入footer.jsp -->
		<jsp:include page="/footer.jsp"></jsp:include>

	</body>

</html>