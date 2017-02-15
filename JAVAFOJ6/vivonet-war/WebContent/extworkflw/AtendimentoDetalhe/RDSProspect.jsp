<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<bean:define id="form" scope="request" name="rWFPessoaForm"/>
<bean:define id="pessoaVO" name="form" property="pessoaVO"/>
<%--
<acesso:controlHiddenItem nomeIdentificador="wor_rdabpro_verpagina">
--%>
<table width=100%>
<tr valign="top">
    <td width="15%">Nome:</td>
    <td width="35%"><html:text name="pessoaVO" property="nome" readonly="true" style="width=240"/></td>
    <td width="15%">Endereço:</td>
    <td width="35%"><html:text name="pessoaVO" property="dsEndereco" readonly="true" style="width=240"/></td>
</tr>
<tr valign="top">
    <td>CEP:</td>
    <td><html:text name="pessoaVO" property="nrCep" readonly="true" style="width=60"/></td>
    <td>Bairro:</td>
    <td><html:text name="pessoaVO" property="nmBairro" readonly="true" style="width=240"/></td>
</tr>
<tr valign="top">
    <td>Cidade:</td>
    <td><html:text name="pessoaVO" property="nmMunicipio" readonly="true" style="width=240"/></td>
    <td>Estado:</td>
    <td><html:text name="pessoaVO" property="dsSiglaEstado" readonly="true" style="width=20"/></td>
</tr>
<tr valign="top">
    <td>Telefone de Contato:</td>
    <td><html:text name="pessoaVO" property="dsComunicacao" readonly="true" style="width=90"/></td>
    <td>Data do Cadastro:</td>
    <td><html:text name="pessoaVO" property="dtCadastro" readonly="true" style="width=110"/></td>
</tr>
<tr valign="top">
    <td>Cadastrado por:</td>
    <td><html:text name="pessoaVO" property="nmCadastradoPor" readonly="true" style="width=240"/></td>
</tr>
</table>
<a href="abaVoltarSolicitante.do" target="ifrCont" id="abaVoltar"></a>
<%-- TODO: Poner control de acceso
</acesso:controlHiddenItem>
--%>
<script>
    parent.showIfrS('UCP');
    abaVoltar.click();
</script>