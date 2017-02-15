<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
   
<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">

<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoDetalheForm"/>
<bean:define id="atdVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoDetalheForm.atendimentoDetalheVO.atendimentoVO"/>
<bean:define id="pessoaVO" name="atdVO" property="atendimentoPessoaVO"/>
<bean:define id="enderecoVO" name="pessoaVO" property="enderecoVO"/>
<bean:define id="ufVO" name="enderecoVO" property="UFVO"/>

<form action="processoAction.do" method="post">
    <table width=100%>
        <tr valign="top">
            <td width="15%">Nome:</td>
            <td width="35%"><html:text name="pessoaVO" property="nome" readonly="true" style="width=240"/></td>
            <td width="15%">Endereço:</td>
            <td width="35%"><html:text name="enderecoVO" property="dsEnderecoComplemento" readonly="true" style="width=240"/></td>
        </tr>
        <tr valign="top">
            <td>CEP:</td>
            <td><html:text name="enderecoVO" property="nrCEP" readonly="true" style="width=60"/></td>
            <td>Bairro:</td>
            <td><html:text name="enderecoVO" property="nmBairro" readonly="true" style="width=240"/></td>
        </tr>
        <tr valign="top">
            <td>Cidade:</td>
            <td><html:text name="enderecoVO" property="nmMunicipio" readonly="true" style="width=240"/></td>
            <td>Estado:</td>
            <td><html:text name="ufVO" property="sgUF" readonly="true" style="width=20"/></td>
        </tr>
        <tr valign="top">
            <td>Telefone de Contato:</td>
            <td><html:text name="pessoaVO" property="nrTelefoneContato" readonly="true" style="width=90"/></td>
            <td>Data do Cadastro:</td>
            <td><html:text name="pessoaVO" property="dtCadastro" readonly="true" style="width=110"/></td>
        </tr>
        <tr valign="top">
            <td>Cadastrado por:</td>
            <td><html:text name="pessoaVO" property="nmCadastradoPor" readonly="true" style="width=240"/></td>
        </tr>
    </table>
</form>
   
