<%--
Módulo.....: Gestão de Processos
Caso de Uso: Detalhe do processo
Versão.....: $Author: a5110705 $ - $Revision: 1.2 $ - $Date: 2011/05/28 14:37:35 $

*** REFACTORING ***
 Date: 20/11/2004
 Author: emocana - osaavedra
*******************
--%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<bean:define id="form" scope="request" name="rWFPessoaForm"/>
<bean:define id="pessoaVO" name="form" property="pessoaVO"/>
<bean:define id="carterizacaoVO" name="pessoaVO" property="carterizacaoVO"/>
<bean:define id="segmentacaoVO" name="pessoaVO" property="segmentacaoVO"/>
<%-- TODO: Poner control de acceso
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<acesso:controlHiddenItem nomeIdentificador="wor_rdacl_verpagina">
--%>
<table width="100%" height="50" cellspacing="5">
<tr valign="top">
    <td width="15%" class="TableCellL3"><b>Nome:</b></td>
    <td width="35%" style="color:#006699;"><bean:write property="nome" name="pessoaVO"/></td>
</tr>
<tr valign="top">
    <td class="TableCellL3"><b>Segmentação:</b></td>
    <td style="color:#006699;"><bean:write name="segmentacaoVO" property="descricao"/></td>
</tr>
<tr valign="top">
    <td class="TableCellL3"><b>Carterização:</b></td>
    <td style="color:#006699;"><bean:write name="carterizacaoVO" property="descricao"/></td>
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