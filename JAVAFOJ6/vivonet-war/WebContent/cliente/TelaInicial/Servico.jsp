<%@page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<bean:define id="FormFrameServico" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="lupaLinhaForm" type="cliente.TelaInicial.TelaInicialController.LupaLinhaForm"/>
<bean:define id="Servicos"         name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="lupaLinhaForm.formDadosLupaLinha.servicosVOArray"/>
<bean:define id="ServicosPrePago"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="lupaLinhaForm.formServicoVO"/>
<html>
    <head>
        <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
            <!--
                parent.parent.oculta_div();
            -->
        </SCRIPT>
    </head>
    <body rightmargin="0" leftmargin="0" topmargin="0">
    <acesso:controlHiddenItem nomeIdentificador="cli_serv_verpagina">
    <!-- //Autorizado -->
    <logic:notEmpty name="ServicosPrePago" property="codErro">
        <div class="tblEstatica_linhapar" style="width:100%;height:100%;font-family:Arial;text-align:center;font-size:11px;vertical-align:absmiddle;">
            <br><br><br><br><br><bean:write name="ServicosPrePago" property="msgErro" />
        </div>
    </logic:notEmpty>
    <logic:empty name="ServicosPrePago" property="codErro">
        <%String idTipoLinha = FormFrameServico.getFormIDTipoLinha();
        if(ConstantesCRM.SONE.equals(idTipoLinha) || ConstantesCRM.SFIVE.equals(idTipoLinha) || ConstantesCRM.SFOUR.equals(idTipoLinha) || ConstantesCRM.SSEVEN.equals(idTipoLinha)){%>
              <form id="fFaturaAjustes" action="salvar.do" method="post" name="lupaLinhaForm">
                <table width="500" border="0" cellpadding="0" bgcolor="white"cellspacing="1">
                    <tr>
                        <td>
                            <vivo:tbTable selectable="true" onRowClick="" layoutWidth="735" layoutHeight="136" tableWidth="735" styleId="tableTitle" sortable="true">
                                <vivo:tbHeader>
                                    <vivo:tbHeaderColumn align="left" width="200" type="string">Nome</vivo:tbHeaderColumn>
                                </vivo:tbHeader>
                                <vivo:tbRows>
                                    <logic:iterate name="Servicos" id="serv" indexId="iserv" length="">
                                        <vivo:tbRow key="linha1">
                                            <vivo:tbRowColumn>
                                                <bean:write name="serv" property="nmServico"/>
                                            </vivo:tbRowColumn>
                                        </vivo:tbRow>
                                    </logic:iterate>
                                </vivo:tbRows>
                            </vivo:tbTable>
                        </td>
                    </tr>
                </table>
            </form>
        <%}%>
        <%if(ConstantesCRM.SSIX.equals(idTipoLinha) || ConstantesCRM.STWO.equals(idTipoLinha)){%>
        <logic:equal value="2" name="FormFrameServico" property="formIDTipoLinha">
            <vivo:tbTable selectable="true" onRowClick="" layoutWidth="735" layoutHeight="155" tableWidth="735" styleId="tableTitle" sortable="true">
                <vivo:tbHeader>
                    <vivo:tbHeaderColumn align="center" width="120" type="string">Código do Serviço</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="250" type="string">Descrição do Serviço</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="150" type="string">Descrição do Estado</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="125" type="string">Período de Vigência</vivo:tbHeaderColumn>
                </vivo:tbHeader>
                <vivo:tbRows>
                    <logic:iterate id="item" name="ServicosPrePago" property="servicosItemArray">
                        <vivo:tbRow key="Linha">
                            <vivo:tbRowColumn><bean:write name="item" property="codigo"/></vivo:tbRowColumn>
                            <vivo:tbRowColumn><bean:write name="item" property="descricao"/></vivo:tbRowColumn>
                            <vivo:tbRowColumn><bean:write name="item" property="status"/></vivo:tbRowColumn>
                            <vivo:tbRowColumn><bean:write name="item" property="validade"/></vivo:tbRowColumn>
                        </vivo:tbRow>
                    </logic:iterate>
                </vivo:tbRows>
            </vivo:tbTable>
        </logic:equal>
        <%}%>
    </logic:empty>
    </acesso:controlHiddenItem>
    </body>
</html>