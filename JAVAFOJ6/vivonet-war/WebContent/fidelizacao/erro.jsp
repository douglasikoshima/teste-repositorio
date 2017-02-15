<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
   
<acesso:controlInitEnv/>
<html>    
<acesso:controlHiddenItem nomeIdentificador="fid_erro_verpagina">
<script>
    <logic:notEmpty name="error">
        alert("<bean:write name="error"/>");
    </logic:notEmpty>
</script>
<SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
    <!--
        parent.oculta_div();
    -->
  </SCRIPT>
   
</acesso:controlHiddenItem>
</html>
