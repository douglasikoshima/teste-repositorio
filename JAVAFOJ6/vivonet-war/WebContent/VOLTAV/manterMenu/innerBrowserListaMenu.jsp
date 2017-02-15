<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="FormManterMenu" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formManterMenu" type="VOLTAV.manterMenu.ManterMenuController.FormManterMenu"/>

<html>
    <head>
            <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    </head>

    <body>
        <div id="manter_menu_lista_inner">
        
                <vivo:tbTable selectable="true" layoutWidth="740" layoutHeight="300" tableWidth="740" styleId="tableTitle" sortable="true">
                    <vivo:tbHeader>
                        <vivo:tbHeaderColumn align="center" width="10%" type="string">Canal</vivo:tbHeaderColumn>
                        <vivo:tbHeaderColumn align="left" width="45%" type="string">Menu</vivo:tbHeaderColumn>
                        <vivo:tbHeaderColumn align="center" width="5%" type="string">UF</vivo:tbHeaderColumn>
                        <vivo:tbHeaderColumn align="center" width="15%" type="string">Tipo de Linha</vivo:tbHeaderColumn>
                        <vivo:tbHeaderColumn align="center" width="20%" type="string">Grupo Pessoa</vivo:tbHeaderColumn>
                        <vivo:tbHeaderColumn align="center" width="5%"  type="string">Status</vivo:tbHeaderColumn>
                    </vivo:tbHeader>
                    <vivo:tbRows>
                        <logic:iterate name="FormManterMenu" property="resultadoPesquisaVO" id="lista" indexId="id">
                            <vivo:tbRow key="linha">
                                <vivo:tbRowColumn>
                                        <logic:equal name="lista" property="idCanal" value="15">
                                            VOL
                                        </logic:equal>
                                        <logic:equal name="lista" property="idCanal" value="13">
                                            TAV
                                        </logic:equal>
                                </vivo:tbRowColumn>
                    
                                <vivo:tbRowColumn>
                                    \<bean:write name="lista" property="nmItemPai"/>
                                    
                                    <logic:notEqual name="lista" property="nmItem" value="">
                                        \<bean:write name="lista" property="nmItem"/>
                                    </logic:notEqual>
                                </vivo:tbRowColumn>

                                <vivo:tbRowColumn>
                                    <bean:write name="lista" property="sgUf"/>
                                </vivo:tbRowColumn>

                                <vivo:tbRowColumn>
                                    <bean:write name="lista" property="dsTipoLinha"/>
                                </vivo:tbRowColumn>

                                <vivo:tbRowColumn>
                                
                                    <logic:equal name="lista" property="idGrupo" value="1">
                                        PF - Cliente e Usuário
                                    </logic:equal>
                                    
                                    <logic:equal name="lista" property="idGrupo" value="3">
                                        PJ - Apenas Usuário
                                    </logic:equal>

                                </vivo:tbRowColumn>
                            
                                    <vivo:tbRowColumn>
                                            <logic:equal name="lista" property="inAtivo" value="1">
                                                <img src="/FrontOfficeWeb/resources/images/bt_icon_habilitar.gif" border="0" alt="Desabilitar Item" <acesso:controlHiddenItem nomeIdentificador="altera_voltav_manter_menu"> onclick="alteraStatusItemMenu('<bean:write name="lista" property="idUf"/>', '<bean:write name="lista" property="idCanal"/>', '<bean:write name="lista" property="idTipoLinha"/>', '<bean:write name="lista" property="idGrupo"/>', '<bean:write name="lista" property="idItemMenu"/>', '<bean:write name="lista" property="inAtivo"/>', '<bean:write name="FormManterMenu" property="paginaAtual"/>','<%=id%>');"</acesso:controlHiddenItem>>
                                            </logic:equal>
                                            <logic:equal name="lista" property="inAtivo" value="0">
                                                <img src="/FrontOfficeWeb/resources/images/bt_icon_desabilitar.gif" border="0" alt="Habilitar Item" <acesso:controlHiddenItem nomeIdentificador="altera_voltav_manter_menu"> onclick="alteraStatusItemMenu('<bean:write name="lista" property="idUf"/>', '<bean:write name="lista" property="idCanal"/>', '<bean:write name="lista" property="idTipoLinha"/>', '<bean:write name="lista" property="idGrupo"/>', '<bean:write name="lista" property="idItemMenu"/>', '<bean:write name="lista" property="inAtivo"/>', '<bean:write name="FormManterMenu" property="paginaAtual"/>', '<%=id%>');"</acesso:controlHiddenItem>>
                                            </logic:equal>
                                    </vivo:tbRowColumn>
                            </vivo:tbRow>
                        </logic:iterate>
                    </vivo:tbRows>
                </vivo:tbTable>

                <table align="center">
                    <tr>
                        <td>
                            <logic:notEqual name="FormManterMenu" property="paginaAtual" value="1">
                                <a href="Javascript:paginarPesquisa('-1', true);"><<</a>
                            </logic:notEqual>
                                <%
                                    for(int i = 0; i < Integer.parseInt(FormManterMenu.getTotalPaginas()); i++){
                                %> 
                                    <%if(FormManterMenu.getPaginaAtual().equals(String.valueOf(i+1))){%>
                                        <%=i+1%>
                                    <%}else{%>
                                        <a href="Javascript:paginarPesquisa('<%=i+1%>', false);"><%=i+1%></a>
                                    <%}%>
                                <%}%>
                                
                             <%if(!FormManterMenu.getTotalPaginas().equals(FormManterMenu.getPaginaAtual()) && !FormManterMenu.getPaginaAtual().equals("1")){%>
                                <a href="Javascript:paginarPesquisa('+1', true);">>></a>
                            <%}%>
                        </td>
                    </tr>
                </table>

                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>

                <table width="750" border="0" align="center" cellspacing="0" cellpadding="5" style="border:1px solid #D4D7DE; background-color:#F7F9FA; font-size:9px;">
                    <tr>
                        <td valign="middle" width="70"><b>&nbsp;Legendas:</b></td>
                        <td>
                            <img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="4">
                        </td>
                        <td>
                            <img src="/FrontOfficeWeb/resources/images/bt_icon_desabilitar.gif" align="left">&nbsp;Desabilita Item
                        </td>
                        <td>
                            <img src="/FrontOfficeWeb/resources/images/bt_icon_habilitar.gif" align="left">&nbsp;Habilita Item
                        </td>

                    </tr>
                </table>
        <script>
            parent.parent.oculta_div();
        </script>
        <vivo:alert atributo="msgStatus" scope="request"/>
        <iframe scrolling="yes"  name="innerBrowserPesquisaMenu" height="0px" width="0px"></iframe>              
                <html:hidden name="FormManterMenu" property="idUf"/>
                <html:hidden name="FormManterMenu" property="idCanal"/>
                <html:hidden name="FormManterMenu" property="idTipoLinha"/>
                <html:hidden name="FormManterMenu" property="idGrupo"/>
                <html:hidden name="FormManterMenu" property="inAtivo"/>
                <html:hidden name="FormManterMenu" property="idItemMenu"/>
                <html:hidden name="FormManterMenu" property="paginaAtual"/>
                <html:hidden name="FormManterMenu" property="idx"/>
        
        </div>

    <script>
       parent.document.getElementById('div_lista_usuario_pag').innerHTML = document.getElementById('manter_menu_lista_inner').innerHTML;
    </script>


</body>

</html>