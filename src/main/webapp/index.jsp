<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Индекс</title>
		<script type='text/javascript' src='${pageContext.request.contextPath}/javascript/ajax_example.js'></script>
	</head>
	<body>
		<dl>
			<dt>SPRING FRAMEWORD LEARNING - SIMPLE</dt>
			<dd><br></dd>
			<dd><a href='${pageContext.request.contextPath}/index.jsp' target='_blank'>General index</a></dd>
			<dd><a href='${pageContext.request.contextPath}/spring' target='_blank'>Spring hello</a></dd>
			<dd><a href='${pageContext.request.contextPath}/hello' target='_blank'>Standard hello</a></dd>
			<dd><br></dd>
			<dt>SPRING FRAMEWORD LEARNING - RESTFUL - GETTER</dt>
			<dd><br></dd>
			<dd><a href='${pageContext.request.contextPath}/ex_info/get' target='_blank'>ExampleInfoBean Getter</a></dd>
			<dd><br></dd>
			<dd><br></dd>
			<dt>SPRING FRAMEWORD LEARNING - RESTFULL - AJAX SETTER</dt>
			<dd><br></dd>
			<dd><jsp:include page="/WEB-INF/FORM-AJAX/example-form-bean.jsp"></jsp:include></dd>
			<dd><br></dd>
			<dt>SPRING FRAMEWORD LEARNING - RESTFULL - ANGULAR SETTER</dt>
			<dd><br></dd>
			<dd><a href='/flights/list'>Листа летова</a></dd>
			<dd><a href='/reservations/list'>Листа резервација</a></dd>
			<dd><a href='/messages/list/read'>Листа прочитаних порука</a></dd>
			<dd><a href='/messages/list/non_read'>Листа непрочитаних порука</a></dd>
			<dd><br></dd>
			<dt>SPRING FRAMEWORD LEARNING - RESTFULL - REGULAR SETTER</dt>
			<dd><br></dd>
			<dd><jsp:include page="/WEB-INF/FORM-REGULAR/example-form-bean.jsp"></jsp:include></dd>
			<dd><br></dd>
		</dl>
	</body>
</html>