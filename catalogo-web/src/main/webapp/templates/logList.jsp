<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib prefix="catalogo" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display" %>

<catalogo:defaultTemplate titulo="Home Catalogo">
<jsp:attribute name="headScripts">
		<script>
			function obterLog(nameFile){
				document.all('nmFile').value = nameFile;
				document.logForm.submit();
			}
		</script>
</jsp:attribute>
   <jsp:body>
		<html:form action="obterLog" tagId="logForm" genJavaScriptFormSubmit="false">
			<html:hidden property="nmFile" styleId="nmFile"/>
			<div class="secao_conteudo">
				<catalogo:contentBox title="Logs"  requiredFields="true">
					<c:if test="${logList != null}">
						<b>Logs:</b> <br />
						<ul>
							<c:forEach items="${logList}" var="file" >
								<li><a href="#" onclick="obterLog('${file}')">${file}</a></li>
							</c:forEach>
						</ul>
					</c:if>
				</catalogo:contentBox>
			</div>
			</html:form>
   </jsp:body>        
</catalogo:defaultTemplate>