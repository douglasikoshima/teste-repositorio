<%--
Módulo.....: Gestão de Processos

Caso de Uso: Detalhe do processo
Versão.....: $Author: a5112274 $ - $Revision: 1.1 $ - $Date: 2011/04/25 19:36:40 $

*** REFACTORING ***
 Date: 21/11/2004
 Author: emocana - osaavedra
*******************
--%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%-- TODO: Poner control de acceso
<acesso:controlInitEnv/>
--%>
<%-- TODO: Poner control de acceso
    <acesso:controlHiddenItem nomeIdentificador="wor_rdenc_verpagina">
--%>
<bean:define id="form" scope="request" name="rWFEncerramentoForm"/>
<bean:define id="encerramentoVO" name="form" property="encerramentoVO"/>
<table border="0" height="230">
    <tr>
        <td valign="top" width="355" >
            <div style="width:355px;height:120px;overflow:auto;">
                <%=(String)request.getAttribute("scrPalitagem")%>
            </div>
        </td>
        <td valign="top">
            <table cellspacing="0">
                <tr>
                    <td valign="top">Tipo Comunicação:</td>
                    <td valign="top"><html:text name ="encerramentoVO" property="dsFormaRetorno" readonly="true"/></td>
                </tr>
                <tr>
                    <td valign="top">Resposta Padrão:</td>
                    <td valign="top"><html:textarea name ="encerramentoVO" property="dsMensagem" readonly="true" style="width:270px;height:120px;font-family:Verdana;font-size:11px;color:#006699" /></td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <b>Coment&aacute;rios</b><br>
            <html:textarea name ="encerramentoVO" property="dsComentario" readonly="true" cols="30" rows="4" style="width:745px;font-family:Verdana;font-size:11px;color:#006699"/>
        </td>
    </tr>
</table>
<%-- TODO: Poner control de acceso
</acesso:controlHiddenItem>
--%>
<script>parent.showIfrInf('Enc');</script>