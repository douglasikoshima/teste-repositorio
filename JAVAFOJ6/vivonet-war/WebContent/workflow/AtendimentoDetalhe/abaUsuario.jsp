<!--
Módulo.....: Gestão de Processos
Caso de Uso: Detalhe do processo
Versão.....: $Author: a5112272 $ - $Revision: 1.2 $ - $Date: 2011/05/25 18:27:57 $
-->

<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
   
<acesso:controlInitEnv/>
<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">

<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoDetalheForm"/>
<bean:define id="atdVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoDetalheForm.atendimentoDetalheVO.atendimentoVO"/>
<bean:define id="pessoaVO" name="atdVO" property="atendimentoPessoaVO"/>
<bean:define id="carterizacaoVO" name="pessoaVO" property="carterizacaoVO"/>
<bean:define id="segmentacaoVO" name="pessoaVO" property="segmentacaoVO"/>
<acesso:controlHiddenItem nomeIdentificador="wor_abus_verpagina">
<form action="processoAction.do" method="post">
    <table width="100%">
        <tr valign="top">
            <td width="15%">Nome:</td>
            <td width="35%"><html:text name="pessoaVO" property="nome" style="width=240" readonly="true"/></td>
            <td width="15%"></td>
            <td width="35%"></td>
        </tr>
        <tr valign="top">
            <td>Segmentação:</td>
            <td><html:text name="segmentacaoVO" property="descricao" style="width=150" readonly="true"/></td>
        </tr>
        <tr valign="top">
            <td>Carterização:</td>
            <td><html:text name="carterizacaoVO" property="descricao" style="width=150" readonly="true"/></td>
        </tr>
    </table>
</form>
   
</acesso:controlHiddenItem>
