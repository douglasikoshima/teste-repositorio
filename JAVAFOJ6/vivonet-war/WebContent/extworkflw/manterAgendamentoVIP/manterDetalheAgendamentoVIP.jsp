<%@page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.vol.dados.vo.ListaAlertaDocument.ListaAlerta"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formManterAgendamentoVIP" type="extworkflw.manterAgendamentoVIP.ManterAgendamentoVIPController.FormManterAgendamentoVIP"/>
<bean:define id="agendamento" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formManterAgendamentoVIP.agendamento" type="br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xsd.consultaAgendamentoPorLinha.output.AgendamentoType"/>

<html>
<head>
    <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">        
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/tratamento.js" ></script>
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/prototype.js" ></script>
    <script language="javaScript">
   
    </script>

</head>
<body>

    <form name="formManterAgendamentoVIP" method="post">
    
        <fieldset>
            <legend><b>Loja</b></legend>
            
            <table border="0" width="100%" cellpadding="1" cellspacing="6">
                <tr>
                <%
                    String horario = ConstantesCRM.SVAZIO;
                    if(agendamento.getHora() != null && agendamento.getHora().length() > 5){
                        horario = agendamento.getHora().substring(0,5);
                    }
                    
                %>
                    <td>Data e Hora do Agendamento:</td>
                    <td><b><bean:write name="agendamento" property="data" /> as <%=horario%>h</b></td>
                    <td>Nome da Loja:</td>
                    <td><b><bean:write name="agendamento" property="nome" /></b></td>
                </tr>
                <tr>
                    <td>Endereço Loja:</td>
                    <td colspan="3"><b>                    
                        <bean:write name="agendamento" property="endereco" />
                        <br>
                        <bean:write name="agendamento" property="municipio" /> / 
                        <bean:write name="agendamento" property="uf" /></b></td>
                </tr>
            </table>
        
        </fieldset>
        
            <br>
            
            <logic:equal name="agendamento" property="status" value="Agendado">
            
                <logic:iterate id="alerta" name="listaAlerta" scope="request" indexId="idx">
                    <fieldset>
                    <legend><b>Lembrete</b></legend>
                    
                        <table>
                            <logic:equal name="alerta" property="sgTipoComunicacao" value="EMAIL"> 
                                <tr>
                                    <td>Tipo do Lembrete:</td>
                                    <td><b><bean:write name="alerta" property="sgTipoComunicacao"/> - <bean:write name="alerta" property="dsContato"/></b></td>
                                    <td>Data/Hora:</td>
                                    <td><b><bean:write name="alerta" property="dtAlerta"/>h</b></td>
        
                                </tr>
                            </logic:equal>
                            <logic:equal name="alerta" property="sgTipoComunicacao" value="SMS" > 
                                <tr>
                                    <td>Tipo do Lembrete:</td>
                                    <td><b><bean:write name="alerta" property="sgTipoComunicacao"/></b></td>
                                    <td>Data/Hora:</td>
                                    <td><b><bean:write name="alerta" property="dtAlerta" />h</b></td>
                                </tr>
        
                            </logic:equal>
                        </table>
                        
                    </fieldset>
                    
                </logic:iterate>
            
             <BR>
            
            </logic:equal>
            
            <fieldset>
            <legend><b>Assunto</b></legend>
            
                <table>
                    <tr>
                        <td colspan="4" align="left"><b><bean:write name="agendamento" property="assunto" /></b></td>
                    </tr>
                    <tr>
                        <td colspan="4" align="left"><b><bean:write name="agendamento" property="detalhe1" /></b></td>
                    </tr>
                    <tr>
                        <td colspan="4" align="left"><b><bean:write name="agendamento" property="detalhe2" /></b></td>
                    </tr>
                    <tr>
                        <td colspan="4" align="left"><b><bean:write name="agendamento" property="detalhe3" /></b></td>
                    </tr>
                </table>

            </fieldset>
            
            <br>
            
            <fieldset>
            <legend><b>Status</b></legend>

                <table>
                    <tr>
                        <td>Estado do Agendamento:</td>
                        <td colspan="3"><b><bean:write name="agendamento" property="status" /></b></td>
                    </tr>
                </table>

            </fieldset>
        
    </form>
    
    <vivo:alert atributo="msgSucessoAlertaDetalhe" scope="request"/>  
    
    <script language="javaScript">
        document.body.style.backgroundColor = '#ededed';
        parent.oculta_div();
    </script>

</body>
</html>