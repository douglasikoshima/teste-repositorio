<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<netui-data:getData resultId="warningFrame" value="{globalApp.warningFrame}" />
<acesso:controlInitEnv/>
<html>    
<acesso:controlHiddenItem nomeIdentificador="fid_dierroz_verpagina">
<script>
    location.href = "begin.do?acao=erroMatriz"
</script>
<%=pageContext.getAttribute("warningFrame")%>
</acesso:controlHiddenItem>
</html>
