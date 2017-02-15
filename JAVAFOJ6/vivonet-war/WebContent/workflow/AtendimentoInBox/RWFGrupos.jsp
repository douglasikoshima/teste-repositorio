<%--
*** REFACTORING ***
 Date: 20/11/2004
 Author: emocana - osaavedra
*******************
--%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%-- 
<acesso:controlInitEnv/>
<acesso:controlHiddenItem nomeIdentificador="wor_rigps_verpagina">
--%>

<bean:define id="form" scope="request" name="rWFInBoxGruposUserForm"/>
<bean:define id="gruposVO" name="form" property="gruposVO"/>
<html:select name="form" property="idGrupo" style="width=250px" onchange="parent.focaTipCampos(this.options[this.selectedIndex].text,this, 110, 125, 35);" onfocus="parent.focaTipCampos(this.options[this.selectedIndex].text,this, 110, 125, 35);" onmouseover="parent.focaTipCampos(this.options[this.selectedIndex].text,this, 110, 125, 35);" onblur="parent.HideTip();" onmouseout="parent.HideTip();"><html:option value=" "></html:option>
<html:options collection="gruposVO" property="idGrupo" labelProperty="dsGrupo"/></html:select>

<%--</acesso:controlHiddenItem>--%>

<script>parent.showIfr("Grupos");</script>
