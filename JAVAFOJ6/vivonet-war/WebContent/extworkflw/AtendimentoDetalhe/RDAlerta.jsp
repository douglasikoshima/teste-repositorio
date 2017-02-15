<%--
*** REFACTORING ***
 Date: 20/11/2004
 Author: emocana - osaavedra
*******************
--%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%-- TODO: Poner control de acceso
<acesso:controlInitEnv/>
<acesso:controlHiddenItem nomeIdentificador="wor_rdal_verpagina">
--%>
<bean:define id="rdalertas" scope="request" name="rWFAlertaForm"/>
<bean:define id="alertaVO" name="rdalertas" property="alertaVO"/>
<html:select name="rdalertas" property="alertaVO" style="width=200px">
<html:options collection="alertaVO" property="idAlerta" labelProperty="dsAlerta"/> 
</html:select>
<%-- TODO: Poner control de acceso
</acesso:controlHiddenItem>
--%><script>parent.showIfr("Alertas");</script>
