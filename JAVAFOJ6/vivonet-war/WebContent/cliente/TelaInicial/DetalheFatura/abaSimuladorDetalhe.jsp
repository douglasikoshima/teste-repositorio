<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="lupaFaturamentoPos"/>
<html>
    <head>
        <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <script language="javascript">
            function voltar(){
                if(top.frameApp.dvAnimarAguarde!=null) top.frameApp.startAnimation();
                self.location.href = 'loadSimulador.do';
            }

            function enviarEmail(){
                if(top.frameApp.dvAnimarAguarde!=null) top.frameApp.startAnimation();
                self.location.href = 'loadContatoEnviaEmailSimulador.do';
            }
            
            function atualizaProtocolo() {
              <logic:equal name="Form" property="atualizacaoProtocolo" value="true">
                alert('Protocolo <bean:write name="Form" property="nrProtocolo" /> gerado.');
                top.frameApp.updateProtocolo('<bean:write name="Form" property="nrProtocolo" />');
                top.frameApp.nrProtocoloScreenPop = '';
             </logic:equal>
        }
        </script>
        <script FOR=window EVENT=onload LANGUAGE="JScript">
        <!--
            parent.parent.oculta_div();
            atualizaProtocolo();
            document.body.style.backgroundColor = '#e3ecf4';
        -->
        </script>
    </head>
    <body rightmargin="0" leftmargin="0" topmargin="0">
         <form action="" name="formSimulador" method="post" onsubmit="return false;">
    
            <table width="99%" border="0" style="margin-top:2px;margin-left:2px;">
                <tr>
                    <td>
                        <table width="100%" border="0" style="margin-top:2px;">
                            <tr valign="top">
                                <td>Plano Atual:&nbsp;<b><bean:write name="Form" property="planoAtual.nome"/></b></td>
                            </tr>
                            <tr valign="top">
                                <td>
                                    <vivo:tbTable selectable="false" layoutWidth="350" layoutHeight="40" tableWidth="350" styleId="tableTitle">
                                        <vivo:tbHeader>
                                            <vivo:tbHeaderColumn align="center" width="70%" type="string">Detalhes do Plano</vivo:tbHeaderColumn>
                                            <vivo:tbHeaderColumn align="center" width="10%" type="string">Valor Total (média/mês)</vivo:tbHeaderColumn>
                                            <vivo:tbHeaderColumn align="center" width="20%" type="string">Minutos locais plano</vivo:tbHeaderColumn>
                                        </vivo:tbHeader>
                                        <vivo:tbRows>
                                            <vivo:tbRow key="linha1">
                                                <vivo:tbRowColumn><bean:write name="Form" property="planoAtual.descricao"/></vivo:tbRowColumn>
                                                <vivo:tbRowColumn>R$&nbsp;<bean:write name="Form" property="planoAtual.valorAssinaturaFormatado"/></vivo:tbRowColumn>
                                               <vivo:tbRowColumn><bean:write name="Form" property="planoAtual.stringQtdMinutosFranquia" ignore="true"/></vivo:tbRowColumn>
                                            </vivo:tbRow>
                                        </vivo:tbRows>
                                    </vivo:tbTable>
                                </td>
                            </tr>
                        </table>
                    </td>
                    <td>
                        <table width="100%" border="0" style="margin-top:2px;">
                            <tr valign="top">
                                <td>Plano Comparado:&nbsp;<b><bean:write name="Form" property="planoEscolhido.nome"/></b></td>
                            </tr>
                            <tr valign="top">
                                <td>
                                    <vivo:tbTable selectable="false" layoutWidth="350" layoutHeight="40" tableWidth="350" styleId="tableTitle">
                                        <vivo:tbHeader>
                                            <vivo:tbHeaderColumn align="center" width="70%" type="string">Detalhes do Plano</vivo:tbHeaderColumn>
                                            <vivo:tbHeaderColumn align="center" width="10%" type="string">Valor total conta</vivo:tbHeaderColumn>
                                            <vivo:tbHeaderColumn align="center" width="20%" type="string">Minutos locais plano</vivo:tbHeaderColumn>
                                        </vivo:tbHeader>
                                        <vivo:tbRows>
                                            <vivo:tbRow key="linha1">
                                                <vivo:tbRowColumn><bean:write name="Form" property="planoEscolhido.descricao"/></vivo:tbRowColumn>
                                                <vivo:tbRowColumn>R$&nbsp;<bean:write name="Form" property="planoEscolhido.valorAssinaturaFormatado"/></vivo:tbRowColumn>
                                                <vivo:tbRowColumn><bean:write name="Form" property="planoEscolhido.stringQtdMinutosFranquia" ignore="true"/></vivo:tbRowColumn>
                                            </vivo:tbRow>
                                        </vivo:tbRows>
                                    </vivo:tbTable>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td align="right" colspan="2">
                        <img src="/FrontOfficeWeb/resources/images/bt_enviarporemail_nrml.gif" style="cursor:pointer;" onclick="enviarEmail();">&nbsp;
                        <img src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" style="cursor:pointer;" onclick="voltar();">
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>