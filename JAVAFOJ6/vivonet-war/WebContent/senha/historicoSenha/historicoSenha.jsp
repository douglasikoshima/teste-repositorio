<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<%!
public final String TP_CLIENTE = "2";
public final String TP_USUARIO = "1";
%>
<acesso:controlInitEnv/>
<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="historicoConsultarForm" />
<html>
    <head>
        <title>Web Application Page</title>
        <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" rel='stylesheet' type='text/css'>
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <script language="javascript">
            parent.mostrar_div();
            function update( poRow ) {
                coluna1 = poRow.cells(0).innerText;
                coluna2 = poRow.cells(1).innerText;
                coluna3 = poRow.cells(2).innerText;
                coluna4 = poRow.cells(2).innerText;
            }
            
            function flutuante() {
            }
            
            function mudarTexto() {
                if(document.forms[0].tipoRelacionamento[0].checked == true) {
                    document.all.nomeUsuario.style.display = '';
                    document.all.nomeCliente.style.display = 'none';
                } else {
                    document.all.nomeUsuario.style.display = 'none';
                    document.all.nomeCliente.style.display = '';
                }
            }
            
            function submitSolicitarSenha() {
                var str = "Cliente";
                if(trim(document.forms[0].obsReg.value) == '') {
                        alert("Inclua um Motivo!");
                        return;
                }
                if(document.forms[0].tipoRelacionamento[0].checked == true)
                    str = "Usuário";
                    
                if(confirm("Confirma a solicitação da senha do "+str+" ?")) {
                    document.forms[0].method="POST";
                    document.forms[0].action="solicitarSenhaAction.do";
                    document.forms[0].submit();
                }
            }
            
            function submitDesbloquear() {
                if(confirm("Confirma o desbloqueio da senha?")) {
                    document.forms[0].method="POST";
                    document.forms[0].action="desbloquearAction.do";
                    document.forms[0].submit();
                }
            }
            
            function submitConsultar() {
                document.forms[0].method="POST";
                document.forms[0].action="historicoConsultarAction.do";
                document.forms[0].submit();
            }
            
            function contaCaracter(objeto) {
                return !(objeto.length > 254);  //tamanho do campo no oracle = 255, menos 1, igual a 254, além disso
                                                //é necessário garantir no servidor que não está indo mais que 255 caracteres.
            }
        </script>
    </head>
    <acesso:controlHiddenItem nomeIdentificador="wor_hs_verpagina">
        <html:form styleId="formHistoricoConsultar" action="/senha/historicoSenha/historicoConsultarAction.do" method="post">
            <html:hidden name="form" property="foneArea"/>
            <html:hidden name="form" property="foneNumero"/>
            <html:hidden name="form" property="nmCliente"/>
            <html:hidden name="form" property="nmUsuario"/>
            <vivo:quadro id="qdHist" width="760" height="220" label="Histórico de Movimentação de Senha">
                <table border="0" cellpadding="0" cellspacing="0" width="100%">
                    <tr>
                        <td colspan="3">
                        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="3"></div>
                        <table width="100%" class="tbl_bggray">
                            <tr>
                                <td>Telefone:</td>
                                <td colspan="2">
                                    <netui:label value="foneFormatado"></netui:label>
                                </td>
                            </tr>
                            <tr>
                                <td width="20%">Consultar histórico de:</td>
                                <td width="400" valign="middle">
                                    <html:radio name="form" property="tipoRelacionamento" value="<%=TP_USUARIO%>" onclick="mudarTexto('U');" styleClass="radio">Usuário</html:radio>&nbsp;&nbsp;
                                    <html:radio name="form" property="tipoRelacionamento" value="<%=TP_CLIENTE%>" onclick="mudarTexto('U');" styleClass="radio">Cliente</html:radio>
                                </td>
                                <td align="right" >
                                <acesso:controlHiddenItem nomeIdentificador="wor_hs_pesquisar">
                                    <img hspace="10" id="btConsultar" style="cursor:hand;border:none" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" onclick="submitConsultar(); return false;"/>
                                </acesso:controlHiddenItem>
                                </td>
                            </tr>
                            <tr id="nomeUsuario" style="display:none">
                                <td>Nome:</td>
                                <td colspan="2">
                                    <bean:write name="form" property="nmUsuario"/>
                                </td>
                            </tr>
                            <tr id="nomeCliente" style="display:none">
                                <td>Nome:</td>
                                <td colspan="2">
                                    <bean:write name="form" property="nmCliente"/>
                                </td>
                            </tr>
                            <tr>
                                <td>Status de Acesso aos Canais Eletr&ocirc;nicos:</td>
                                <td colspan="2">
                                    <logic:equal name="form" property="inBloqueado" value="0">
                                        <b>Desbloqueado</b>
                                    </logic:equal>
                                    <logic:equal name="form" property="inBloqueado" value="1">
                                        <b>Bloqueado a Pedido do Cliente</b>
                                    </logic:equal>
                                </td>
                            </tr>
                        </table>
                        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="3"></div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3">
                            <vivo:tbTable selectable="false" onRowClick="" layoutWidth="715" layoutHeight="120" tableWidth="715" styleId="tableTitle" sortable="true">
                                <vivo:tbHeader>
                                    <vivo:tbHeaderColumn align="center" width="15%" type="string">Telefone</vivo:tbHeaderColumn>
                                    <vivo:tbHeaderColumn align="center" width="25%" type="string">
                                        <logic:equal name="form" property="tipoRelacionamento" value="2">Cliente</logic:equal>
                                        <logic:equal name="form" property="tipoRelacionamento" value="1">Usuário</logic:equal>
                                    </vivo:tbHeaderColumn>
                                    <vivo:tbHeaderColumn align="center" width="15%" type="string">Canal</vivo:tbHeaderColumn>
                                    <vivo:tbHeaderColumn align="center" width="25%" type="string">Evento</vivo:tbHeaderColumn>
                                    <vivo:tbHeaderColumn align="center" width="20%" type="date">Data</vivo:tbHeaderColumn>
                                </vivo:tbHeader>
                                <vivo:tbRows scroll="false">
                                    <logic:iterate id="historicoSenhaItemVo" name="form" property="movimentoVo" indexId="idx">
                                        <bean:define name="historicoSenhaItemVo" property="obsRegistro" id="obsRegistro"/>
                                        <vivo:tbRow key="linha" title="<%=(obsRegistro == null ? \"\" : obsRegistro.toString())%>">
                                            <vivo:tbRowColumn><bean:write name="historicoSenhaItemVo" property="telefone"/></vivo:tbRowColumn>
                                            <vivo:tbRowColumn><bean:write name="historicoSenhaItemVo" property="nmNome"/></vivo:tbRowColumn>
                                            <vivo:tbRowColumn><bean:write name="historicoSenhaItemVo" property="canalOrigem"/></vivo:tbRowColumn>
                                            <vivo:tbRowColumn><bean:write name="historicoSenhaItemVo" property="tipoMovimento"/></vivo:tbRowColumn>
                                            <vivo:tbRowColumn><bean:write name="historicoSenhaItemVo" property="data" /></vivo:tbRowColumn>
                                        </vivo:tbRow>
                                    </logic:iterate>
                                </vivo:tbRows>
                            </vivo:tbTable>
                        </td>
                    </tr>
                </table>
            </vivo:quadro>
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
            <vivo:quadro id="qdOper" width="766" height="121" label="Reinicializar Senha">
                <table border="0" width="100%">
                    <tr>
                        <td width="7%" valign="top">Motivo:</td>
                        <td width="50%" valign="top">
                            <html:textarea styleId="obsReg" cols="25" rows="2" property="motivo" style="width:300px; height:50px;" onkeypress="return contaCaracter(this.value);"/>
                        </td>
                        <acesso:controlHiddenItem nomeIdentificador="wor_hs_reinicializar">
	                        <td width="43%" valign="top" align="right">
                                <img id="btSolicitarSenha" style="cursor:hand;border:none" src="/FrontOfficeWeb/resources/images/bt_reinicia_senha_nrml.gif" onclick="submitSolicitarSenha(); return false;"/>
                            </td>
                        </acesso:controlHiddenItem>
                    </tr>
                    <tr>
                        <td colspan="3">
                            <netui:label value="{pageContext.form.message}" style="color:green;"/>
                            <netui:label value="{pageContext.form.messageError}" style="color:red;"/>
                        </td>
                    </tr>
                </table>
            </vivo:quadro>
        </html:form>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
        <!--
            parent.oculta_div();
            mudarTexto();            
        -->
        </SCRIPT>
</acesso:controlHiddenItem>
</html>