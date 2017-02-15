<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>

<catalogo:defaultTemplate titulo="Home Catalogo">
<jsp:attribute name="headScripts"/>
<jsp:body>
		<div style="float:left;"><img src="/catalogo/static_server/img/banners/banner1.jpg"/></div>
		<div style="width:12px;height:10px;float:left;"></div>
		<div style="float:left;"><img src="/catalogo/static_server/img/banners/banner2.jpg"/></div>
		<br clear="all"/>
		<div style="width:12px;height:12px;"></div>
		<div><img src="/catalogo/static_server/img/banners/banner3.jpg"/></div>
</jsp:body>
</catalogo:defaultTemplate>