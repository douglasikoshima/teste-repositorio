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

<bean:define id="FormManterDaemon" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formManterDaemon"  type="VOLTAV.manterDaemonDesativacaoComprovanteEmail.ManterDaemonController.FormManterDaemon"/>

<bean:define id="aGrupoErro" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formManterDaemon.filaComprovanteEmailVO.grupoErroVOArray"/>

<html>
    <head>
            <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    </head>

    <body>
        <div id="div_lista_erro_detalhada">

            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>

            <table class="tbl_bgGray" width="100%" border="0">
                <tr>
                    <td width="100">
                        <netui:label styleClass="tblEstatica_campoNome" value="Código do Erro: " />
                    </td>
                    <td width="200">
                        <bean:write name="FormManterDaemon" property="codigoErro" />
                    </td>

                    <td width="120">
                        <netui:label styleClass="tblEstatica_campoNome" value="Descrição do Erro: " />
                    </td>

                    <td>
                        <vivo:hint maxLength="30"><bean:write name="FormManterDaemon" property="descricaoErro" /></vivo:hint>
                    </td>
                </tr>
            </table>

            <br>

            <vivo:quadro id="qdMain" height="410" width="780" label="Detalhe da Consulta" scroll="no">

            <table width="100%" align="center">
                <tr>
                    <td>

                        <vivo:tbTable selectable="true" layoutWidth="745" layoutHeight="300" tableWidth="745" styleId="tableTitle" sortable="true">
                            <vivo:tbHeader>
                                <vivo:tbHeaderColumn align="center" width="5%" type="string">&nbsp;</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="20%" type="string">ID da Fila</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="35%" type="string">Data da Publicação</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="35%" type="string">Data do Erro</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="5%"  type="string">&nbsp;</vivo:tbHeaderColumn>
                            </vivo:tbHeader>
                            <vivo:tbRows>
                                <logic:iterate name="aGrupoErro" id="lista" indexId="idx">
                                    <vivo:tbRow key="linha">

                                        <vivo:tbRowColumn>
                                             <input type="checkbox" name="chk_daemon<%=idx%>" value="<bean:write name="lista" property="idcompservicodesativado" />;<%=idx%>" class="radio" onclick="checkLista(this);">
                                        </vivo:tbRowColumn>

                                        <vivo:tbRowColumn>
                                            <bean:write name="lista" property="idcompservicodesativado"/>
                                        </vivo:tbRowColumn>

                                        <vivo:tbRowColumn>
                                            <bean:write name="lista" property="dtTimeStamp"/>
                                        </vivo:tbRowColumn>

                                        <vivo:tbRowColumn>
                                            <bean:write name="lista" property="dtErro"/>
                                        </vivo:tbRowColumn>

                                        <vivo:tbRowColumn>
                                            <img src="/FrontOfficeWeb/resources/images/bt_icon_xml.gif" border="0" alt="Visualizar XML" onclick="visualizarXML('<bean:write name="lista" property="idcompservicodesativado"/>');">
                                        </vivo:tbRowColumn>

                                    </vivo:tbRow>
                                </logic:iterate>
                            </vivo:tbRows>
                        </vivo:tbTable>

                        <table align="center">
                            <tr>
                                <td>
                                    <logic:notEqual name="FormManterDaemon" property="paginaAtual" value="1">
                                        <a href="Javascript:paginarPesquisa('-1', true);"><<</a>
                                    </logic:notEqual>
                                        <%
                                            for(int i = 0; i < Integer.parseInt(FormManterDaemon.getTotalPaginas()); i++){
                                        %>
                                            <%if(FormManterDaemon.getPaginaAtual().equals(String.valueOf(i+1))){%>
                                                <%=i+1%>
                                            <%}else{%>
                                                <a href="Javascript:paginarPesquisa('<%=i+1%>', false);"><%=i+1%></a>
                                            <%}%>
                                        <%}%>

                                     <%if(!FormManterDaemon.getTotalPaginas().equals(FormManterDaemon.getPaginaAtual()) && !FormManterDaemon.getPaginaAtual().equals("1")){%>
                                        <a href="Javascript:paginarPesquisa('+1', true);">>></a>
                                    <%}%>
                                </td>
                            </tr>
                        </table>


                        <!--vivo:tbRowColumn>
                            <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" border="0" alt="Excluir Registro" onclick="excluirById('');">
                        < /vivo:tbRowColumn>

                        < vivo:tbRowColumn>
                            <img src="/FrontOfficeWeb/resources/images/bt_icon_atualizar.gif" border="0" alt="Atualizar Registro" onclick="atualizarById('');">
                        < /vivo:tbRowColumn -->

                    </td>
                </tr>
            </table>
                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>

                  <table width="100%" border="0" cellpadding="3" cellspacing="1" bgcolor="#adadad">
                        <tr>
                          <td bgcolor="#ededed">
                              <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                  <tr>
                                        <td width="10%"><span class="tblEstatica_campoNome">Legendas:</span></td>
                                        <td width="5%"><div align="center"><img src="/FrontOfficeWeb/resources/images/bt_icon_xml.gif" width="18" height="18"></div></td>
                                        <td width="45%">Visualizar XML</td>
                                  </tr>
                            </table>
                        </td>
                    </tr>
                </table>

            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>

            <table border="0" width="100%">
                <tr>
                    <td align="left">
                        <img vspace="2" style="border:none;" onclick="voltar();" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_voltar_over.gif"/>
                    </td>
                    <td align="right">

                        <acesso:controlHiddenItem nomeIdentificador="excluir_by_id_daemon_con_fila_dce">
                            <img vspace="2" style="border:none;" onclick="excluirById();" src="/FrontOfficeWeb/resources/images/bt_excluir_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_excluir_over.gif"/>
                        </acesso:controlHiddenItem>

                        <acesso:controlHiddenItem nomeIdentificador="atualizar_by_id_daemon_con_fila_dce">
                            <img vspace="2" style="border:none;" onclick="atualizarById();" src="/FrontOfficeWeb/resources/images/bt_atualizar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_atualizar_over.gif"/>
                        </acesso:controlHiddenItem>
                    </td>
                </tr>
            </table>

        </vivo:quadro>

        <script language="JavaScript">
            parent.oculta_div();
        </script>

        <vivo:quadroFlutuante label="Visualização do XML" scroll="false" src="" idIframe="ifrmXmlDaemon" id="divXmlDaemon" spacesLeft="140" height="200" spacesTop="160" url="<%=request.getContextPath()%>" display="none" width="500" ></vivo:quadroFlutuante>

        <html:hidden name="FormManterDaemon" property="paginaAtual"/>
        <html:hidden name="FormManterDaemon" property="arrayIdFila"/>
        <html:hidden name="FormManterDaemon" property="idcompservicodesativado"/>

        </div>

    <script>
        parent.document.getElementById('div_lista_agrupada_erro').innerHTML = document.getElementById('div_lista_erro_detalhada').innerHTML;
    </script>


</body>

</html>