<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>



<html>
<body>
	欢迎注册,3秒后跳转至首页...
	<script>
		var t = 3;//设定跳转的时间 
		setInterval("refer()", 1000); //启动1秒定时 
		function refer() {
			if (t == 0) {
				location = "${pageContext.request.contextPath}/index"; //#设定跳转的链接地址 
			}
			t--; // 计数器递减 
		}
	</script>
</body>
</html>
