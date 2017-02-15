<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<bean:define id="extratoVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="prePagoExtratoVO"/>

<html>
    <head>
        <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>        
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js"></script>      
        <script>
            function validaPesquisa() {
                if (document.getElementById("dtInicial").value == "")
                    alert("A data inicial é obrigatoria!");
                else if (document.getElementById("dtFinal").value == "")
                    alert("A data final é obrigatoria!");
                else {
                    document.forms[0].action="pesquisarExtrato.do";
                    document.forms[0].method = "POST";
                    document.forms[0].submit();
                }
            }
        </script>
    </head>
    <body style="margin-left:5px; margin-right:5px; margin-top:5px;">
	
            <table width="700" height="90" border="0" cellpadding="0" cellspacing="0" align="center">
                <logic:empty name="error">
                    <tr><td height="7"></td></tr>
                    <tr>
                        <td>&nbsp;<b>Emissão:</b></td>
                        <td><bean:write name="extratoVO" property="buffer.EXTRACTODETALHADO.CABECALHO.INFO.EMISSAO" /></td>
                    </tr>
                    <tr>
                        <td>&nbsp;<b>Período:</b></td>
                        <td><bean:write name="extratoVO" property="buffer.EXTRACTODETALHADO.CABECALHO.INFO.PERIODO" /></td>
                    </tr>
                    <tr>
                        <td>&nbsp;<b>Celular:</b></td>
                        <td><bean:write name="extratoVO" property="buffer.EXTRACTODETALHADO.CABECALHO.INFO.CELULAR" /></td>
                    </tr>
                    <tr>
                        <td>&nbsp;<b>Conta</b></td>
                        <td><bean:write name="extratoVO" property="buffer.EXTRACTODETALHADO.CABECALHO.INFO.CONTA" /></td>
                    </tr>
                    <tr>
                        <td>&nbsp;<b>Perfil</b></td>
                        <td><bean:write name="extratoVO" property="buffer.EXTRACTODETALHADO.CABECALHO.INFO.PERFIL" /></td>
                    </tr>
                </logic:empty>
                <logic:notEmpty name="error">
                    <tr>
                        <td colspan="2"><bean:write name="error"/></td>                        
                    </tr>
                </logic:notEmpty>        
            </table><br>
            <b>Resumo</b>
            <vivo:tbTable selectable="true" onRowClick="" layoutWidth="705" layoutHeight="" tableWidth="705" styleId="tableTitle" sortable="true">
                    <vivo:tbHeader>
                        <vivo:tbHeaderColumn align="center" width="40%" type="string">Tipo</vivo:tbHeaderColumn>
                        <vivo:tbHeaderColumn align="center" width="15%" type="string">Débitos</vivo:tbHeaderColumn>
                        <vivo:tbHeaderColumn align="center" width="15%" type="string">Créditos</vivo:tbHeaderColumn>
                        <vivo:tbHeaderColumn align="center" width="15%" type="string">Saldo Atual</vivo:tbHeaderColumn>
                        <vivo:tbHeaderColumn align="center" width="15%" type="string">Data</vivo:tbHeaderColumn>
                    </vivo:tbHeader>
                    <logic:empty name="error">
                    <vivo:tbRows>
                    <logic:iterate id="resumo" name="extratoVO" property="buffer.EXTRACTODETALHADO.TABELA.REGISTROArray">
                        <logic:equal value="RESUMO" name="resumo" property="ID">
                            <vivo:tbRow>
                                <TD width="40%"><bean:write name="resumo" property="TIPOSALDO"/></TD>
                                <TD align="right" width="15%"><bean:write name="resumo" property="DEBITOS"/></TD>
                                <TD align="right" width="15%"><bean:write name="resumo" property="CREDITOS"/></TD>
                                <TD align="right" width="15%"><bean:write name="resumo" property="SALDOATUAL"/></TD>
                                <TD align="right" width="15%"><bean:write name="resumo" property="DTEXP"/></TD>
                            </vivo:tbRow>
                        </logic:equal>
                    </logic:iterate>
                    </vivo:tbRows>
                    <tr><td colspan="5" height="20">&nbsp;</td></tr>                    
                </logic:empty>
                <logic:notEmpty name="error">
                    <tr>
                        <td colspan="2"><bean:write name="error"/></td>                        
                    </tr>
                </logic:notEmpty>        
            </vivo:tbTable>
            <br>
            <b>Extrato</b>
            <vivo:tbTable selectable="true" onRowClick="" layoutWidth="705" layoutHeight="" tableWidth="705" styleId="tableTitle2" sortable="true">
                <vivo:tbHeader>
                    <vivo:tbHeaderColumn align="center" width="05%" type="string">Item</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="10%" type="string">Data e Hora</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="05%" type="string">Área Origem</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="10%" type="string">Área Destino</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="10%" type="string">N.ºDestino</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="10%" type="string">Tipo Saldo</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="10%" type="string">Tipo Cham.</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="10%" type="string">Descrição</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="10%" type="string">Periodo</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="10%" type="string">Duração Cobr.</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="10%" type="string">Valor Total</vivo:tbHeaderColumn>
                </vivo:tbHeader>
                <vivo:tbRows>
                    <logic:empty name="error">
                        <%int contador = 0;%>
                        <logic:iterate id="registro" name="extratoVO" property="buffer.EXTRACTODETALHADO.TABELA.REGISTROArray">                
                            <logic:notEqual value="RESUMO" name="registro" property="ID">
                                <vivo:tbRow key="Linha">
                                    <vivo:tbRowColumn><bean:write name="registro" property="ITEM"/></vivo:tbRowColumn>
                                    <vivo:tbRowColumn><bean:write name="registro" property="DATAHORA"/></vivo:tbRowColumn>
                                    <vivo:tbRowColumn><bean:write name="registro" property="ORIGEM"/></vivo:tbRowColumn>
                                    <vivo:tbRowColumn><bean:write name="registro" property="DESTINO"/></vivo:tbRowColumn>
                                    <vivo:tbRowColumn><bean:write name="registro" property="NRDESTINO"/></vivo:tbRowColumn>
                                    <vivo:tbRowColumn><bean:write name="registro" property="TIPOSALDO"/></vivo:tbRowColumn>
                                    <vivo:tbRowColumn><bean:write name="registro" property="TIPOCHAMADA"/></vivo:tbRowColumn>
                                    <vivo:tbRowColumn><bean:write name="registro" property="DESCRICAO"/></vivo:tbRowColumn>
                                    <vivo:tbRowColumn><bean:write name="registro" property="PERIODO"/></vivo:tbRowColumn>
                                    <vivo:tbRowColumn><bean:write name="registro" property="DURACAO"/></vivo:tbRowColumn>
                                    <vivo:tbRowColumn><logic:empty name="registro" property="VALOR">
                                                        <bean:write name="registro" property="TOTAL"/>
                                                      </logic:empty>
                                                      <logic:notEmpty name="registro" property="VALOR">
                                                        <bean:write name="registro" property="VALOR"/>
                                                      </logic:notEmpty></vivo:tbRowColumn>
                                </vivo:tbRow>
                                <%contador++;%>
                            </logic:notEqual>
                        </logic:iterate>
                        <%if(contador == 0){%>
                            <vivo:tbRow>
                                <td colspan="11"><B>Não há itens para o extrato.</B><td>
                            </vivo:tbRow>
                        <%}%>
                    </logic:empty>
                    <logic:notEmpty name="error">
                        <vivo:tbRow key="Linha">
                            <td colspan="11"><bean:write name="error"/></td>
                        </vivo:tbRow>
                    </logic:notEmpty>
                </vivo:tbRows>                            
            </vivo:tbTable>
	
    </body>
</html>
