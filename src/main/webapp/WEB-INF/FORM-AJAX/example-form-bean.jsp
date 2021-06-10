<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id='exampleInfoBean' class='yatospace.worker.services.bean.ExampleInfoBean' scope='session'></jsp:useBean>

<c:if test='${param["example_info_bean_submit"] ne null}'>
	<c:out value="${exampleInfoBean.setData(param['example_info_bean_data'])}"></c:out>
</c:if>


<script>
	function filpflop_ajaxform_ajax(){	
		var obj = document.getElementById('example_info_bean_div_ajax');
		if(obj.style.display=='none')   obj.style.display = 'block';
		else if(obj.style.display=='block')  obj.style.display = 'none';
	}
</script>

<a id='example_info_bean_ff_ajax' href='javascript:filpflop_ajaxform_ajax()'>Regular set form for example info bean</a>
<div id='example_info_bean_div_ajax' style='display:none'>
	<br>
	<form method='POST'>
		<table>
			<tr>
				<td>Информација : </td>
			</tr>
			<tr>
				<td><input type='text' id='example_info_bean_data_ajax' name='example_info_bean_data_ajax'/></td>
			</tr>
		</table>
		<input type='button' value='Потврда' name='example_info_bean_submit_ajax' onclick='ajax_set_еxample_info("${pageContext.request.contextPath}", document.getElementById("example_info_bean_data_ajax").value, function(response){ 
			if(response.success) alert("Постављање успјешно.");
			if(!response.success) alert("Постављање неуспјешно.");
		})'/>
	</form>
</div>

<script>
	ajax_get_еxample_info('${pageContext.request.contextPath}', 
			function(response){
				document.getElementById('example_info_bean_data_ajax').value = response.data;
			}
	);
</script>