<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/fidelizacao.tld" prefix="fid"%>

<bean:define id="relatorio" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relaOperadorForm"/>
<bean:define id="relHeader" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="headerVO"/>
<html>
<head>
    <meta HTTP-EQUIV="Pragma" CONTENT="no-cache">
    <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
    <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    <SCRIPT FOR=window EVENT=onload>
        //top.frameApp.oculta_div();
        window.print();
    </script>
</head>
<body leftmargin="0" bottommargin="0" rightmargin="0" topmargin="0">
<table width="600">
    <tr>
        <td><img src="/FrontOfficeWeb/resources/images/img_relatorio.gif"></td>
    </tr>
</table>
<table border="0" width="600" class="tbl_bgblue">
    <tr>
        <td><b>Relatório Operador</b></td>
    </tr>
</table>
<table width="600" cellpadding="0" cellspacing="0" border="0" >
<tr>
    <td>
            <table width="100%" class="tbl_bggray" cellpadding="5" border="0">
                <tr>
                    <td width="40%">Campanha:</td>
                    <td width="60%"><b><bean:write name="relHeader" property="sgCampanha" /></b></td>

                </tr>
                <tr>
                    <td>Subcampanha:</td>
                    <td><b><bean:write name="relHeader" property="nmSubCampanha" /></b></td>
                </tr>
                <tr>
                    <td>Período:</td>
                    <td>de <b><bean:write name="relHeader" property="filtroDtInicio" /></b> a <b><bean:write name="relHeader" property="filtroDtFim" /></b></td>
                </tr>
                <tr>
                    <td>Operador:</td>
                    <td><b><bean:write name="relHeader" property="sgUsuario" /></b></td>
                </tr>
            </table>
            <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="3"></div>
            <div class="holderTabela">
            <fid:table>
                <fid:tr bgColor="#545454">
                    <fid:headerTd>Operador</fid:headerTd>
                    <fid:headerTd>Publico Total</fid:headerTd>
                    <fid:headerTd>Meta Diária</fid:headerTd>
                    <fid:headerTd>Contatos Efetivos</fid:headerTd>
                    <fid:headerTd>Tempo Médio Operador(seg)</fid:headerTd>
                    <fid:headerTd>Motivos</fid:headerTd>
                    <fid:headerTd>Qtd.</fid:headerTd>
                    <fid:headerTd>Não Adesões</fid:headerTd>
                    <fid:headerTd>Reagendados</fid:headerTd>
                    <fid:headerTd>Adesões</fid:headerTd>
                    <fid:headerTd>Submotivo Oferta</fid:headerTd>
                    <fid:headerTd>Qtd</fid:headerTd>
                </fid:tr>
                <logic:iterate id="linha" name="relatorio" property="numerico">
                    <fid:tr bgColor="#ffffff" onMouseOverColor="#F5F5F5">
                        <fid:dataTd><bean:write name="linha" property="operador"/></fid:dataTd>
                        <fid:dataTd><bean:write name="linha" property="publicoTotal"/></fid:dataTd>
                        <fid:dataTd><bean:write name="linha" property="metaDiaria"/></fid:dataTd>
                        <fid:dataTd><bean:write name="linha" property="contatosEfetivos"/></fid:dataTd>
                        <fid:dataTd><bean:write name="linha" property="tmo"/></fid:dataTd>

                        <fid:dataTd>
                            <logic:notEmpty name="linha" property="motivos">
                                <logic:iterate id="motivodescricao" name="linha" property="motivos.dsMotivoArray" offset="0" length="1">
                                    <bean:write name="motivodescricao"/>
                                </logic:iterate>
                            </logic:notEmpty>
                        </fid:dataTd>
                        <fid:dataTd>
                            <logic:notEmpty name="linha" property="motivos">
                                <logic:iterate id="motivoquantidade" name="linha" property="motivos.qtdMotivoArray" offset="0" length="1">
                                    <bean:write name="motivoquantidade"/>
                                </logic:iterate>
                            </logic:notEmpty>
                        </fid:dataTd>

                        <fid:dataTd><bean:write name="linha" property="naoAdesoes"/></fid:dataTd>
                        <fid:dataTd><bean:write name="linha" property="reagendados"/></fid:dataTd>
                        <fid:dataTd><bean:write name="linha" property="adesoes"/></fid:dataTd>

                        <fid:dataTd>
                            <logic:notEmpty name="linha" property="ofertas">
                                <logic:iterate id="ofertadescricao" name="linha" property="ofertas.dsOfertaArray" offset="0" length="1">
                                    <bean:write name="ofertadescricao"/>
                                </logic:iterate>
                            </logic:notEmpty>
                        </fid:dataTd>
                        <fid:dataTd>
                            <logic:notEmpty name="linha" property="ofertas">
                                <logic:iterate id="ofertaquantidade" name="linha" property="ofertas.qtdOfertaArray" offset="0" length="1">
                                  <bean:write name="ofertaquantidade"/>
                                </logic:iterate>
                            </logic:notEmpty>
                        </fid:dataTd>

                    <logic:greaterThan name="linha" property="motivos.dsMotivoArray" value="1">
                        <logic:iterate id="linhaofertadescricao" name="linha" property="ofertas.dsOfertaArray" offset="1" indexId="index">
                            <fid:tr bgColor="#ffffff" onMouseOverColor="#F5F5F5">
                                <fid:dataTd></fid:dataTd>
                                <fid:dataTd></fid:dataTd>
                                <fid:dataTd></fid:dataTd>
                                <fid:dataTd></fid:dataTd>
                                <fid:dataTd></fid:dataTd>
                                <fid:dataTd>
                                    <logic:iterate id="linhamotivodescricao" name="linha" property="motivos.dsMotivoArray" offset="index" length="1">
                                        <bean:write name="linhamotivodescricao"/>
                                    </logic:iterate>
                                </fid:dataTd>
                                <fid:dataTd>
                                    <logic:iterate id="linhamotivoquantidade" name="linha" property="motivos.qtdMotivoArray" offset="index" length="1">
                                        <bean:write name="linhamotivoquantidade"/>
                                    </logic:iterate>
                                </fid:dataTd>
                                <fid:dataTd></fid:dataTd>
                                <fid:dataTd></fid:dataTd>
                                <fid:dataTd></fid:dataTd>
                                <fid:dataTd><bean:write name="linhaofertadescricao"/></fid:dataTd>
                                <fid:dataTd>
                                    <logic:iterate id="linhaofertaquantidade" name="linha" property="ofertas.qtdOfertaArray" offset="index" length="1">
                                        <bean:write name="linhaofertaquantidade"/>
                                    </logic:iterate>
                                </fid:dataTd>
                            </fid:tr>
                        </logic:iterate>
                    </logic:greaterThan>

                    </fid:tr>

                </logic:iterate>
           </fid:table>
        </div>
    </td>
</tr>
</table>
</body>
</html>