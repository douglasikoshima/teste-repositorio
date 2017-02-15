<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="Form"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="lupaLinhaForm"
             type="cliente.TelaInicial.TelaInicialController.LupaLinhaForm"/>
<bean:define id="pup"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="lupaLinhaForm.formLinhaPUPTO"
             type="br.com.indrasistemas.vivoservices.listapup.webservice.to.ResultadoLinhaPUPTO"/>

<html>

<head>
    <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
    <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <script type="text/javascript" language="javascript">

        var naoClienteQueryString = '';
        var naoCliente;
        onload = function() {
            if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
            <% if (request.getAttribute(ConstantesCRM.NRPROTOCOLO) != null) { %>
            alert('Protocolo <%=request.getAttribute(ConstantesCRM.NRPROTOCOLO)%> gerado.');
            top.frameApp.updateProtocolo('<%=request.getAttribute(ConstantesCRM.NRPROTOCOLO)%>');
            top.frameApp.nrProtocoloScreenPop = '';
            <% } else if (request.getAttribute("msgSucesso") != null) { %>
                if (naoCliente) {
                    alert('<%=request.getAttribute("msgSucesso")%>');
                }
            <% } %>

        }

        submitPermissoesPUP = function(cdPermissao, inAceitacao) {
            if (validar(cdPermissao, inAceitacao)) {
                var f = document.forms[0];
                f.action = f.action + naoClienteQueryString;
                f.cdPermissao.value = cdPermissao;
                f.inAceitacaoPermissao.value = inAceitacao;
                if (top.frameApp.dvAnimarAguarde != null) top.frameApp.startAnimation();
                f.submit();
            }
        }

    </script>
    <style type="text/css">
        body {
            background-color:#ededed;
            padding:5px;
            font-size:11px;
        }
        .button { cursor:pointer; }
        dl, dt, dd {
            display:inline;
            margin:0;
        }
        dd {
            font-weight:bold;
            margin-right:100px;
        }
        #divErro {
            height:150px;
            padding-top:75px;
            text-align:center;
        }
    </style>
</head>

<body rightmargin="0" leftmargin="0" topmargin="0">

<acesso:controlHiddenItem nomeIdentificador="cli_pup_verpagina">

    <logic:equal name="pup" property="status" value="OK">

        <%
        boolean bloqueioProcon = "BLOQUEIO_PROCON".equals(pup.getLinhaPUPWSTO().getSgProcedencia())? true : false;
        boolean naoCliente = ConstantesCRM.STRUE.equals(request.getAttribute("naoCliente")) ? true : false;
        %>

        <script type="text/javascript">
            naoClienteQueryString = "?naoCliente=<%=naoCliente ? ConstantesCRM.STRUE : ConstantesCRM.SFALSE %>"
            naoCliente = <%=String.valueOf(naoCliente)%>;

            validar = function(cdPermissao, inAceitacao) {

                var msg;
                var simNao = (inAceitacao == 1) ? '' : 'não ';
                var dsSMSPromocoes = '<bean:write  name="pup" property="linhaPUPWSTO.dsSMSPromocoes" />';
                var dsSMSProdutos = '<bean:write  name="pup" property="linhaPUPWSTO.dsSMSProdutos" />';
                var dsSMSParceiros = '<bean:write  name="pup" property="linhaPUPWSTO.dsSMSParceiros" />';
                var dsTelmktMsgVoz = '<bean:write  name="pup" property="linhaPUPWSTO.dsTelmktMsgVoz" />';

                if (naoCliente) {

                    if (cdPermissao == 'SMSPROM') {
                        msg = dsSMSPromocoes;
                    } else if (cdPermissao == 'SMSPROD') {
                        msg = dsSMSProdutos;
                    } else if (cdPermissao == 'SMSPARC') {
                        msg = dsSMSParceiros;
                    } else if (cdPermissao == 'BLKTEAT') {
                        msg = dsTelmktMsgVoz;
                    }
                    

                    msg = 'Deseja realmente cadastrar o número acima para ' + simNao + 'receber ' + msg + '?';

                    if (confirm(msg)) {
                        return true;
                    } else {
                        return false;
                    }

                } else {
                    return true;
                }

            }

        </script>

        <html:form action="/cliente/TelaInicial/gravarLinhaPUP.do">

        <html:hidden name="Form" property="inAceitacaoPermissao" />
        <html:hidden name="Form" property="cdPermissao" />

         <dl>
            <dt>Data de Inclus&atilde;o:</dt>
            <dd>
                <bean:write  name="pup" property="linhaPUPWSTO.dtInclusao" />
            </dd>
            <dt>Data da &uacute;ltima altera&ccedil;&atilde;o:</dt>
            <dd>
                <bean:write name="pup" property="linhaPUPWSTO.dtUltimaAlteracao" />
            </dd>
         </dl>

         <vivo:tbTable selectable="true"
                       onRowClick=""
                       layoutWidth="<%= naoCliente ? String.valueOf(701) : String.valueOf(726)%>"
                       layoutHeight="<%= naoCliente ? String.valueOf(100) : String.valueOf(83)%>"
                       tableWidth="<%= naoCliente ? String.valueOf(700) : String.valueOf(725)%>"
                       styleId="tableTitle"
                       sortable="false"
                       resize="true">
            <vivo:tbHeader>
                <vivo:tbHeaderColumn align="left" width="45%" type="string">Tipo de mensagem</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="35%" type="string">Status atual</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="10%" type="string">Aceita</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="10%" type="string">Não aceita</vivo:tbHeaderColumn>
            </vivo:tbHeader>
            <vivo:tbRows>

                <vivo:tbRow key="rowsPermissoes">
                    <vivo:tbRowColumn>
                        <bean:write  name="pup" property="linhaPUPWSTO.dsSMSPromocoes" />
                    </vivo:tbRowColumn>
                    <vivo:tbRowColumn>
                        <% if (pup.getLinhaPUPWSTO().getInSMSPromocoes() == null) { %>

                        <% } else if (pup.getLinhaPUPWSTO().getInSMSPromocoes().intValue() == 0) { %>
                        N&atilde;o aceito
                        <% } else if (pup.getLinhaPUPWSTO().getInSMSPromocoes().intValue() == 1) { %>
                        Aceito
                        <% } %>
                    </vivo:tbRowColumn>
                    <vivo:tbRowColumn>
                        <% if (!bloqueioProcon) { %>
                        <a href="javascript:submitPermissoesPUP('SMSPROM',1)"><img border="0" title="Aceito receber mensagens" src="<%=request.getContextPath()%>/resources/images/bt_icon_habilitar.gif" class="button" /></a>
                        <% } %>
                    </vivo:tbRowColumn>
                    <vivo:tbRowColumn>
                        <% if (!bloqueioProcon) { %>
                        <a href="javascript:submitPermissoesPUP('SMSPROM',0)"><img border="0" title="N&atilde;o aceito receber mensagens" src="<%=request.getContextPath()%>/resources/images/bt_icon_desabilitar.gif" class="button" /></a>
                        <% } %>
                    </vivo:tbRowColumn>
                </vivo:tbRow>

                <vivo:tbRow key="rowsPermissoes">
                    <vivo:tbRowColumn>
                        <bean:write  name="pup" property="linhaPUPWSTO.dsSMSProdutos" />
                    </vivo:tbRowColumn>
                    <vivo:tbRowColumn>
                        <% if (pup.getLinhaPUPWSTO().getInSMSProdutos() == null) { %>

                        <% } else if (pup.getLinhaPUPWSTO().getInSMSProdutos().intValue() == 0) { %>
                        N&atilde;o aceito
                        <% } else if (pup.getLinhaPUPWSTO().getInSMSProdutos().intValue() == 1) { %>
                        Aceito
                        <% } %>
                    </vivo:tbRowColumn>
                    <vivo:tbRowColumn>
                        <% if (!bloqueioProcon) { %>
                        <a href="javascript:submitPermissoesPUP('SMSPROD',1)"><img border="0" title="Aceito receber mensagens" src="<%=request.getContextPath()%>/resources/images/bt_icon_habilitar.gif" class="button" /></a>
                        <% } %>
                    </vivo:tbRowColumn>
                    <vivo:tbRowColumn>
                        <% if (!bloqueioProcon) { %>
                        <a href="javascript:submitPermissoesPUP('SMSPROD',0)"><img border="0" title="N&atilde;o aceito receber mensagens" src="<%=request.getContextPath()%>/resources/images/bt_icon_desabilitar.gif" class="button" /></a>
                        <% } %>
                    </vivo:tbRowColumn>
                </vivo:tbRow>
                
                 <vivo:tbRow key="rowsPermissoes">
                    <vivo:tbRowColumn>
                        <bean:write  name="pup" property="linhaPUPWSTO.dsSMSParceiros" />
                    </vivo:tbRowColumn>
                    <vivo:tbRowColumn>
                        <% if (pup.getLinhaPUPWSTO().getInSMSParceiros() == null) { %>

                        <% } else if (pup.getLinhaPUPWSTO().getInSMSParceiros().intValue() == 0) { %>
                        N&atilde;o aceito
                        <% } else if (pup.getLinhaPUPWSTO().getInSMSParceiros().intValue() == 1) { %>
                        Aceito
                        <% } %>
                    </vivo:tbRowColumn>
                    <vivo:tbRowColumn>
                        <% if (!bloqueioProcon) { %>
                        <a href="javascript:submitPermissoesPUP('SMSPARC',1)"><img border="0" title="Aceito receber mensagens" src="<%=request.getContextPath()%>/resources/images/bt_icon_habilitar.gif" class="button" /></a>
                        <% } %>
                    </vivo:tbRowColumn>
                    <vivo:tbRowColumn>
                        <% if (!bloqueioProcon) { %>
                        <a href="javascript:submitPermissoesPUP('SMSPARC',0)"><img border="0" title="N&atilde;o aceito receber mensagens" src="<%=request.getContextPath()%>/resources/images/bt_icon_desabilitar.gif" class="button" /></a>
                        <% } %>
                    </vivo:tbRowColumn>
                </vivo:tbRow>
                
                <vivo:tbRow key="rowsPermissoes">
                    <vivo:tbRowColumn>
                        <bean:write  name="pup" property="linhaPUPWSTO.dsTelmktMsgVoz" />
                    </vivo:tbRowColumn>
                    <vivo:tbRowColumn>
                        <% if (pup.getLinhaPUPWSTO().getInTelmktMsgVoz() == null) { %>

                        <% } else if (pup.getLinhaPUPWSTO().getInTelmktMsgVoz().intValue() == 0) { %>
                        N&atilde;o aceito
                        <% } else if (pup.getLinhaPUPWSTO().getInTelmktMsgVoz().intValue() == 1) { %>
                        Aceito
                        <% } %>
                    </vivo:tbRowColumn>
                    <vivo:tbRowColumn>
                        <% if (!bloqueioProcon) { %>
                        <a href="javascript:submitPermissoesPUP('BLKTEAT',1)"><img border="0" title="Aceito receber mensagens" src="<%=request.getContextPath()%>/resources/images/bt_icon_habilitar.gif" class="button" /></a>
                        <% } %>
                    </vivo:tbRowColumn>
                    <vivo:tbRowColumn>
                        <% if (!bloqueioProcon) { %>
                        <a href="javascript:submitPermissoesPUP('BLKTEAT',0)"><img border="0" title="N&atilde;o aceito receber mensagens" src="<%=request.getContextPath()%>/resources/images/bt_icon_desabilitar.gif" class="button" /></a>
                        <% } %>
                    </vivo:tbRowColumn>
                </vivo:tbRow>
                
            </vivo:tbRows>
        </vivo:tbTable>

        <div id="dvLegendas" style="margin-top:3px">
        <table style="background-color:#fff;border:1px solid #adadad;" width="<%= naoCliente ? String.valueOf(715) : String.valueOf(740)%>" align="left">
            <tr>
                <td valign="middle" style="padding-left:10px;padding-right:15px;"><b>Legendas:</b></td>
                <td valign="middle" align="left" nowrap style="padding-right:15px;"><img align="absmiddle" src="<%=request.getContextPath()%>/resources/images/bt_icon_habilitar.gif" class="button" />&nbsp;Aceito receber mensagens</td>
                <td valign="middle" align="left" width="100%"><img align="absmiddle" src="/FrontOfficeWeb/resources/images/bt_icon_desabilitar.gif" border="0">&nbsp;N&atilde;o aceito receber mensagens</td>
            </tr>
        </table>
        </div>

        </html:form>

        <logic:notEqual name="pup" property="reason" value="">
            <script type="text/javascript">alert('Operação não pôde ser executada devido a erro no acesso à Plataforma Unificada de Permissões.');</script>
        </logic:notEqual>

    </logic:equal>

    <logic:equal name="pup" property="status" value="NOK">

        <logic:equal name="pup" property="cdRetorno" value="02">
        <div id="divErro"><bean:write name="Form" property="erro" /></div>
        </logic:equal>

        <logic:notEqual name="pup" property="cdRetorno" value="02">
        <div id="divErro">Ocorreu um erro durante acesso à Plataforma Unificada de Permissões.</div>
        <!-- <%= pup.getReason() != null ? pup.getReason() : "" %>  -->
        </logic:notEqual>

    </logic:equal>

</acesso:controlHiddenItem>

</body>

</html>