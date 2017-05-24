<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functionss" prefix="fns" %> --%>
<%@ page import="com.pxjy.common.constant.Property" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="base" value="<%=Property.BASE%>"/>
<c:set var="systemName" value="<%=Property.SYSTEM_NAME%>"/>
<c:set var="companyName" value="<%=Property.COMP_NAME%>"/>