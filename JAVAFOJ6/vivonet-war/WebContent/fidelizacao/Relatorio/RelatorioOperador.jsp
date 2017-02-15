<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/fidelizacao.tld" prefix="fid"%>

<acesso:controlInitEnv/>


<bean:define id="relatorio" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formOperador"/>                 
<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/fidelizacao_relatorios.css">
<script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute value="Fidelizacao" name="Relatorio"></netui-template:setAttribute>
    <netui-template:section name="headerSection"> </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="fid_rel_oper_verpagina">
        <SCRIPT FOR=window EVENT=onload>
            top.frameApp.oculta_div();            
        </script>    
    <jsp:include page="/resources/menus/MenuPrincipal.jsp"/>
    <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="5"></div>
    <vivo:sessao id="idFrelatorio" height="438" width="790" label="Relatorio Operador" operacoes="" scroll="y">
            <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="3"></div>
            <!--
                INICIO HEADER DE FILTROS ESCOLHIDOS
            -->
            <jsp:include page="headerRelCrit.jsp" />          
            <!--
                FIM HEADER DE FILTROS ESCOLHIDOS
            -->       
            <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="3"></div>
            
            <fid:table>
                <fid:tr bgColor="#545454">
                    <fid:headerTd>Operador</fid:headerTd>
                    <fid:headerTd>Soma</fid:headerTd>
                    <fid:headerTd>% Retenções</fid:headerTd>
                    <fid:headerTd>% Migração</fid:headerTd>
                    <fid:headerTd>% Cancelado</fid:headerTd>
                    <fid:headerTd>% Oferta</fid:headerTd>
                    <fid:headerTd>% Suspensão</fid:headerTd>
                    <fid:headerTd>% Adequação de Plano</fid:headerTd>
                    <fid:headerTd>Percentual</fid:headerTd>
                    <fid:headerTd>Resultado</fid:headerTd>
                    <fid:headerTd>Quantidade</fid:headerTd>
                </fid:tr>
                <logic:iterate id="linha" name="relatorio" property="linhaOperador">
                    <fid:tr bgColor="#ffffff" onMouseOverColor="#F5F5F5">
                        <fid:dataTd><bean:write name="linha" property="operador"/></fid:dataTd>
                        <fid:dataTd><bean:write name="linha" property="soma"/></fid:dataTd>
                        <fid:dataTd><bean:write name="linha" property="porcretencoes"/></fid:dataTd>
                        <fid:dataTd><bean:write name="linha" property="porcmigracao"/></fid:dataTd>
                        <fid:dataTd><bean:write name="linha" property="porccancelado"/></fid:dataTd>
                        <fid:dataTd><bean:write name="linha" property="porcoferta"/></fid:dataTd>
                        <fid:dataTd><bean:write name="linha" property="porcsuspensao"/></fid:dataTd>
                        <fid:dataTd><bean:write name="linha" property="porcadequacaoplano"/></fid:dataTd>
                        <fid:dataTd><bean:write name="linha" property="percentual"/></fid:dataTd>
                        <fid:dataTd>
                            <logic:notEmpty name="linha" property="resultado">
                                <logic:iterate id="descricao" name="linha" property="resultado.descricaoArray" offset="0" length="1">
                                    <bean:write name="descricao"/>
                                </logic:iterate>
                            </logic:notEmpty>                                    
                        </fid:dataTd>
                        <fid:dataTd>
                            <logic:notEmpty name="linha" property="resultado">
                                <logic:iterate id="quantidade" name="linha" property="resultado.quantidadeArray" offset="0" length="1">
                                    <bean:write name="quantidade"/>
                                </logic:iterate>
                            </logic:notEmpty>                                    
                        </fid:dataTd>
                    </fid:tr>
                    <logic:greaterThan name="linha" property="resultado.descricaoArray" value="1">
                        <logic:iterate id="descricao" name="linha" property="resultado.descricaoArray" offset="1" indexId="index">
                            <fid:tr bgColor="#ffffff" onMouseOverColor="#F5F5F5">
                                <fid:dataTd></fid:dataTd>
                                <fid:dataTd></fid:dataTd>
                                <fid:dataTd></fid:dataTd>
                                <fid:dataTd></fid:dataTd>
                                <fid:dataTd></fid:dataTd>
                                <fid:dataTd></fid:dataTd>
                                <fid:dataTd></fid:dataTd>
                                <fid:dataTd></fid:dataTd>
                                <fid:dataTd></fid:dataTd>
                                <fid:dataTd><bean:write name="descricao"/></fid:dataTd>
                                <fid:dataTd>
                                    <logic:iterate id="quantidade" name="linha" property="resultado.quantidadeArray" offset="index" length="1">
                                        <bean:write name="quantidade" />
                                    </logic:iterate>
                                </fid:dataTd>
                            </fid:tr>
                        </logic:iterate>
                    </logic:greaterThan>            
                </logic:iterate>
            </fid:table>           

        </vivo:sessao>
        <table width="790" class="tbl_bgGray">
            <tr>
                <td width="500"><img align="letf" id="voltar" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_voltar_over.gif'" onClick="window.location.href='/FrontOfficeWeb/fidelizacao/Relatorio/begin.do'; top.frameApp.mostrar_div(); return false" style="border:none;cursor:hand;" hspace="5" vspace="5"/></td>
                <td align="right">
                <acesso:controlHiddenItem nomeIdentificador="fid_rel_oper_imp">
                    <img id="imprimir" src="/FrontOfficeWeb/resources/images/imprimir-retrato-nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/imprimir-retrato-over.gif" onClick="mostrarTelaImpressao('printOperador.do','Relatório Operador'); return false;" style="border:none" hspace="5" vspace="5"/>
                </acesso:controlHiddenItem>
                </td>
                <td align="right">
                <acesso:controlHiddenItem nomeIdentificador="fid_rel_oper_imp">
                    <img id="imprimir" src="/FrontOfficeWeb/resources/images/imprimir-paisagem-nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/imprimir-paisagem-over.gif" onClick="mostrarTelaImpressao('relatorioOperadorImpressao.jsp','Relatório Operador'); return false;" style="border:none" hspace="5" vspace="5"/>
                </acesso:controlHiddenItem>
                </td>
                <td align="right">
                <acesso:controlHiddenItem nomeIdentificador="fid_rel_oper_arq">
                    <img id="gerararquivo" src="/FrontOfficeWeb/resources/images/bt_gerararq_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_gerararq_over.gif" onClick="window.location.href='/FrontOfficeWeb/fidelizacao/Relatorio/gerarArquivo.do?idRelatorio=8'" style="border:none" hspace="5" vspace="5" />
                </acesso:controlHiddenItem>
                </td>                
            </tr>
        </table>
        </acesso:controlHiddenItem>
        </netui-template:section>
    </netui-template:template>
    
