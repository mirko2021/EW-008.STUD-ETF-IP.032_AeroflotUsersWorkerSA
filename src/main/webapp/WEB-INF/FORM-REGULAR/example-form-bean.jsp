<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id='exampleInfoBean' class='yatospace.worker.services.bean.ExampleInfoBean' scope='session'></jsp:useBean>

<c:if test='example_info_bean_submit'>
	<c:out value="${exampleInfoBean.setData(param['example_info_bean_data'])}"></c:out>
</c:if>


<script>
	function filpflop_ajaxform(){	
		var obj = document.getElementById('example_info_bean_div');
		if(obj.style.display=='none')   obj.style.display = 'block';
		else if(obj.style.display=='block')  obj.style.display = 'none';
	}
</script>

<a id='example_info_bean_ff' href='javascript:filpflop_ajaxform()'>Regular set form for example info bean</a>
<div id='example_info_bean_div' style='display:none'>
	<br>
	<form method='POST'>
		<table>
			<tr>
				<td>Информација : </td>
			</tr>
			<tr>
				<td><input type='text' name='example_info_bean_data' value='${exampleInfoBean.data}'/></td>
			</tr>
		</table>
		<input type='submit' value='Потврда' name='example_info_bean_submit'/>
	</form>
</div>