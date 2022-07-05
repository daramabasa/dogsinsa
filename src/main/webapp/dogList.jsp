<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="vo.Dog, java.util.ArrayList, java.util.HashMap" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#listForm {
		width: 700px; height: 500px;
		border: 1px solid red;
		margin: auto;
	}
	
		h2 {
			text-align: center;
		}
		
		table {
			width: 550px;
			margin:auto;
		}
		
		.div_empty {
			width: 100%; heightL 100%;
			text-align: center;
			background-color: red;
		}
		
		#todatImageList { text-align: center; }
		#productImage { width: 150px; height: 150px; border: none; }
		#todayImage { width: 100px; height: 100px; border: none; }
</style>
</head>
<body>
	<section id = "listForm">
		<c:if test="${dogList != null }">
		<header>
			<h4><a href="dogRegistForm.dog">옷 상품 등록</a></h4>
			<h2>옷 상품 목록</h2>
		</header>
		<table>
			<tr>
				<c:forEach var = "dog" items="${dogList }" varStatus="status">
				<td>
					<a href="dogView.dog?id=${dog.id }">
					<img src="images/${dog.image }" id="productImage">
					</a>
					상품명: ${dog.name }<br>
					가격: ${dog.price }<br>
				</td>
				<c:if test="${((status.index+1) mod 4) == 0 }">
			</tr>
			<tr>
				</c:if>
				</c:forEach>
			</tr>
		</table>
		</c:if>
		<c:if test="${dogList == null }">
			<div class="div_empty">
				옷 상품이 없습니다. 구매 불가
			</div>
		</c:if>
		<c:if test="${todayImageList != null }">
			<div id="todayImageList">
				<h2>오늘 본 옷 상품 목록</h2>
				<table>
					<tr>
						<c:forEach var="todayImage" items="${todayImageList }" varStatus="status">
							<td>
								<img src="images/${todayImage }" id="todayImage">
							</td>
							<c:if test="${((statuc.index+1) mod 4) == 0 }">
					</tr>
					<tr>
						</c:if>
						</c:forEach>
					</tr>
				</table>
			</div>
		</c:if>
	</section>
</body>
</html>