<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<% String inDisponivelWF=(String)request.getParameter("disp");%>

<%-- 
<acesso:controlInitEnv/>
<acesso:controlHiddenItem nomeIdentificador="wor_riinds_verpagina">
--%>

<table cellspacing="1" cellpadding="1" border="0">
<tr><td><a target="ifrDisp" href="analistaDisponivel.do?disp=1" style="border:0px;"><img src='<%="/FrontOfficeWeb/resources/images/"+(inDisponivelWF.equals("1") ? "bt_disponivel_disponivel.gif" : "bt_disponivel_indisponivel.gif")%>' style="border:0px;"/></a></td>
<td><a target="ifrDisp" href="analistaDisponivel.do?disp=0" style="border:0px;"><img src='<%="/FrontOfficeWeb/resources/images/"+(inDisponivelWF.equals("0") ? "bt_indisponivel_disponivel.gif" : "bt_indisponivel_indisponive.gif")%>' style="border:0px;"/></a></td>
</tr></table>

<%--</acesso:controlHiddenItem>--%>

<script>
parent.carrDisp();
parent.parent.submitPesquisar(0);
</script>