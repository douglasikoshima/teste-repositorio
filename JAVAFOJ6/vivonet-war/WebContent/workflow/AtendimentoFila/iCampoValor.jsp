<%--
Módulo.....: Gestão de Processos
Caso de Uso: Atendimento Fila
Versão.....: $Author: a5112272 $ - $Revision: 1.2 $ - $Date: 2011/05/17 17:18:00 $

*** REFACTORING ***
 Date: 29/11/2004
 Author: emocana - osaavedra
*******************
--%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoFilaForm"/>
<html>
<body>
<acesso:controlHiddenItem nomeIdentificador="wor_icva_verpagina">
<script>
<logic:equal name="form" property="tipoCampo" value="S">var divMostrar="divFormSemDominio";</logic:equal>
<logic:equal name="form" property="tipoCampo" value="D">var divMostrar="divFormComDominio";</logic:equal>
parent[divMostrar].style.display="";
if(top.frameApp.dvAnimarAguarde!=null) top.frameApp.stopAnimation();
</script>
</acesso:controlHiddenItem>
</body>
</html>