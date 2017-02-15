<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="lupaLinhaAbasGSMForm" type="cliente.TelaInicial.TelaInicialController.LupaLinhaAbasGSMForm"/>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<html>
    <head>
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <script language="javascript">
            <!--
                function atualizarParam(nmParam){
                    var f = document.forms[0];
                    f.action = "lupaLinhaAbaParametrosAtualiza.do";
                    f.nmParam.value = nmParam;
                    f.submit();
                }
                
                function atualizarTodosParam(){
                    var f = document.forms[0];
                    f.action = "lupaLinhaAbaParametrosAtualiza.do";
                    f.nmParam.value = "";
                    f.submit();
                }
            -->
        </script>
        <script FOR=window event=onload>
            <!--
                //Fim animação
                if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
            -->
        </script>
    </head>
    <body>
        <form action="lupaLinhaAbaParametros.do" name="lupaLinhaAbasGSMForm" method="post" style="margin:0;" onSubmit="return false;">
            <input type="hidden" name="nrLinha" value="<bean:write name="Form" property="nrLinha"/>"/>
            <input type="hidden" name="idLinha" value="<bean:write name="Form" property="idLinha"/>"/>
            <input type="hidden" name="nmParam" value=""/>
            <vivo:tbTable selectable="true" layoutWidth="737" layoutHeight="117" tableWidth="737" styleId="tableTracking" sortable="true">
                <vivo:tbHeader>
                    <vivo:tbHeaderColumn align="center" width="95%" type="string">Nome do Parametro</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="5%"  type="string">&nbsp</vivo:tbHeaderColumn>
                </vivo:tbHeader>
                <vivo:tbRows scroll="true">
                    <logic:present name="Form" property="lupaLinhaAbasGSMVO.abaParametro">
                    <logic:iterate id="lista" name="Form" property="lupaLinhaAbasGSMVO.abaParametro.listaParametroArray">
                    <vivo:tbRow key="linha1">
                        <vivo:tbRowColumn><bean:write name="lista" property="nmParametro"/></vivo:tbRowColumn>
                        <vivo:tbRowColumn><img src="<%=request.getContextPath()%>/resources/images/bt_icon_atualizar.gif" border="0" onclick="atualizarParam('<bean:write name="lista" property="nmParametro"/>');"/></vivo:tbRowColumn>
                    </vivo:tbRow>
                    </logic:iterate>
                    </logic:present>
                    <logic:notEqual value="" name="Form" property="lupaLinhaAbasGSMVO.msgErro">
                        <table width="100%" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <td align="center" style="font-color:red;"><bean:write name="Form" property="lupaLinhaAbasGSMVO.msgErro"/></td>
                            </tr>
                        </table>
                    </logic:notEqual>
                </vivo:tbRows>
            </vivo:tbTable>
            <logic:present name="Form" property="lupaLinhaAbasGSMVO.abaParametro">
            <div>
                <table width="100%" border="0" cellpadding="0" cellspacing="0">
                    <tr valign="middle">
                        <td align="left">Legenda: <img src="<%=request.getContextPath()%>/resources/images/bt_icon_atualizar.gif" border="0"> Solicitação de atualização de parâmetro</td>
                        <td align="right"><img onClick="atualizarTodosParam(); return false;" src="../../resources/images/bt_atualizartodos_nrml.gif" rolloverImage="../../resources/images/bt_atualizartodos_over.gif" style="border:none;"/></td>
                    </tr>
                </table>
            </div>
            </logic:present>
        </form>
    </body>
</html>