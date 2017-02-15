<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<html>

<SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
    <!--
        parent.parent.oculta_div();
    -->
</SCRIPT>

<acesso:controlHiddenItem nomeIdentificador="cli_dco_verpagina">
    <frameset cols="300,*" border="0">
        <frame src="customer/loadOutrosDados.do" scrolling="auto" >
        <frame src="customer/loadAtributos.do?start=yes" name="frameAtributos" scrolling="auto">
    </frameset>
</acesso:controlHiddenItem>   
</html>
