<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<bean:define id="FormManterGrupam" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterGrupamForm"/>
<bean:define id="grupamento"       name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterGrupamForm.grupamento"/>
<bean:define id="aGrupamentos"     name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterGrupamForm.listaGrupamento"/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Manter Sistema"/>
    <netui-template:setAttribute name="modulo" value="Gestão de Usuários"/>
    <netui-template:section name="headerSection">
        <SCRIPT LANGUAGE="JavaScript">
        <!--
            // Simple rollover function which replaces the image.src with the passed image
            function swapImage(control, image) {
                control.src = image;
            }

            function removeItem() {
                if (window.confirm("Confirma remoção do item?")) {
                    return true;
                }else{
                    return false;
                }
            }

            function pesquisaGrupamento(grupamento) {
                var action = 'listaGrupam.do?dsGrupamento='+grupamento;
                document.forms[0].target = '_top';
                document.forms[0].action = action;
                mostrar_div();
                document.forms[0].submit();
            }

            function limpa() {
                document.forms[0].idGrupamento.value = "";
                document.forms[0].dsGrupamento.value = "";
                document.forms[0].dsGrupamento.focus();
            }
                        
            function testaCampos() {
                document.forms[0].dsGrupamento.value = trim(document.forms[0].dsGrupamento.value);
                
                if (document.forms[0].dsGrupamento.value == "") {
                    alert("Você não digitou um nome para o Grupamento.");
                } else {
                    document.forms[0].action = "salvaGrupam.do";
                    mostrar_div();
                    document.forms[0].submit();
                }
            }
            
            function alteraGrupamento(id) {
                divAlteraGrupamento.style.display = '';
                document.forms[0].idGrupamento.value = id;
                document.forms[0].target = "ifrmAlteraGrupamento";
                document.forms[0].action = 'salvaEditaGrupam.do?operacao=alterar';
                mostrar_div();
                document.forms[0].submit();
                //ifrmAlteraGrupamento.src = 'alteraIncluiGrupamento.jsp';
            }
            
            function incluiGrupamento() {
                divIncluiGrupamento.style.display = '';
                document.forms[0].target = "ifrmIncluiGrupamento";
                document.forms[0].action = "salvaEditaGrupam.do?operacao=incluir";
                mostrar_div();
                document.forms[0].submit();
                //ifrmIncluiGrupamento.src = 'alteraIncluiGrupamento.jsp';
            }
            
            function relacGrupamento(id) {
                document.forms[0].target = '';
                document.forms[0].action = "editaParGrupam.do?grupamId="+id;
                mostrar_div();
                document.forms[0].submit();
            }            

            function testaBotao() {
            }
            
            // leva para página de relacionamento.
            function editaRel(id)
            {   
                document.forms[0].target = '';
                document.forms[0].action = 'iniciaGrupam.do?grupamId='+id;
                mostrar_div();
                document.forms[0].submit();
            }
        // -->
        </SCRIPT>


    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="usu_mgrp_verpagina">
        <!-- Menu de Gestao de Usuarios -->
        <jsp:include page="/resources/menus/MenuPrincipal.jsp"/>
        <script>
            mostrar_div();
        </script>   
        
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
        
        <vivo:sessao id="qdMain" height="468" width="790" label="Sistemas" operacoes="Manuten&ccedil;&atilde;o de Grupamentos" scroll="no">
        
        <html:form action="/usuario/manterSistema/manterGrupamento/salvaGrupam.do" target="_parent" onsubmit="return false" method="post">
            
            <html:hidden name="grupamento" property="idGrupamento"/>
            
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="10"></div>
            
            <center>
            <vivo:quadro height="404" id="listagrupamentos" width="760" label="Grupamentos cadastrados" scroll="no">
                
                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="2"></div>
                
                <table width="750" cellpadding="0" cellspacing="0" class="tbl_bgGray">
                    <tr>
                        <td width="165" style="text-indent:10px;"><netui:label value="Pesquisa de Grupamentos: " styleClass="tblEstatica_campoNome"/>
                        </td>
                        <td><html:text name="grupamento" property="dsGrupamento" style="width:350px" styleClass="input" maxlength="60"/>
                        </td>
                        <td>
                                <img onclick="limpa();" vspace="5" id="btLimpar" src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif'" style="border:none;cursor:hand;" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_limpar_over.gif'"/>   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                     
                                <acesso:controlHiddenItem nomeIdentificador="usu_mgrp_pesq"><img onclick="pesquisaGrupamento(document.forms[0].dsGrupamento.value);" vspace="5" id="btPesquisar" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif'" style="border:none;cursor:hand;" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif'"/></acesso:controlHiddenItem>

                        </td>
                    </tr>
                </table>
                
                <br>
                
                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="4"></div>
                
                <logic:greaterThan name="FormManterGrupam" property="listaGrupamentoLength" value="0">
                    <vivo:tbTable selectable="true" layoutWidth="735" layoutHeight="265" tableWidth="735" styleId="tableTitle" sortable="true">
                        <vivo:tbHeader>
                            <vivo:tbHeaderColumn align="left"   width="" type="string">Nome do Grupamento</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="center" width="20" type="string"></vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="center" width="20" type="string"></vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="center" width="20" type="string"></vivo:tbHeaderColumn>
                        </vivo:tbHeader>
        
                        <vivo:tbRows>
                            <logic:iterate name="FormManterGrupam" property="listaGrupamento" id="itemGrupamento">
                                <vivo:tbRow key="linha">
                                    <vivo:tbRowColumn>
                                        <bean:write name="itemGrupamento" property="dsGrupamento"/>
                                    </vivo:tbRowColumn>
                                    <vivo:tbRowColumn>
                                        <acesso:controlHiddenItem nomeIdentificador="usu_mgrp_alter">
                                        <a href="Javascript:alteraGrupamento(<bean:write name="itemGrupamento" property="idGrupamento"/>);">
                                            <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" alt="Alterar Grupamento" border="0">
                                        </a>
                                        </acesso:controlHiddenItem>
                                    </vivo:tbRowColumn>
                                    <vivo:tbRowColumn>
                                        <acesso:controlHiddenItem nomeIdentificador="usu_mgrp_remov">
                                        <html:link page="/usuario/manterSistema/manterGrupamento/removeGrupam.do" paramId="grupamId" paramName="itemGrupamento" paramProperty="idGrupamento">
                                            <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" alt="Excluir Grupamento" border="0" onclick="return removeItem()">
                                        </html:link>
                                        </acesso:controlHiddenItem>
                                    </vivo:tbRowColumn>
                                    <vivo:tbRowColumn>
                                        <acesso:controlHiddenItem nomeIdentificador="usu_mgrp_rel">
                                        <a href="Javascript:editaRel(<bean:write name="itemGrupamento" property="idGrupamento" />);">
                                            <img src="/FrontOfficeWeb/resources/images/bt_icon_relac.gif" alt="Relacionar Grupamento &agrave;s Unidades" border="0">
                                        </a>
                                        </acesso:controlHiddenItem>
                                    </vivo:tbRowColumn >
                                </vivo:tbRow>
                            </logic:iterate>
                        </vivo:tbRows>
                    </vivo:tbTable>
                    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
                
                    <table width="735" border="0" cellspacing="0" cellpadding="5" style="border:1px solid #D4D7DE; background-color:#F7F9FA; font-size:9px;">
                        <tr>
                            <td valign="middle" width="70"><b>&nbsp;Legendas:</b></td>
                            <td>
                                <img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="4">
                            </td>
                            <td>
                                <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" align="absmiddle"> &nbsp;Alterar Grupamento
                            </td>
                            <td>
                                <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" align="absmiddle"> &nbsp;Excluir Grupamento
                            </td>
                            <td width="320">
                                <img src="/FrontOfficeWeb/resources/images/bt_icon_relac.gif" align="absmiddle"> &nbsp;Relacionar Grupamento &agrave;s Unidades
                            </td>
                        </tr>
                    </table>
                    
                </logic:greaterThan>
                
                <logic:equal name="FormManterGrupam" property="listaGrupamentoLength" value="0">
                            <div align="center" style="font-size:11px;">
                                Não foi encontrado nenhum registro com a descrição fornecida.
                            </div>
                </logic:equal>
            
                <div align="right">
                <acesso:controlHiddenItem nomeIdentificador="usu_mgrp_incluir">
                    <img hspace="8" vspace="10" id="btVoltar" onclick="incluiGrupamento();" src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_incluir_over.gif'" style="border:none;cursor:hand;"/>        
                </acesso:controlHiddenItem>
                <div>
            
            </vivo:quadro>
            
            </center>
            
            <img hspace="8" vspace="10" id="btVoltar" onClick="window.location.href='/FrontOfficeWeb/index.jsp'; return false" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_voltar_over.gif'" style="border:none;cursor:hand;"/>
            
            </html:form>
            
        </vivo:sessao>

            <vivo:quadroFlutuante label="Alterar Grupamento" scroll="false" src="" idIframe="ifrmAlteraGrupamento" id="divAlteraGrupamento" spacesLeft="150" height="80" spacesTop="240" url="<%=request.getContextPath()%>" display="none" width="500"></vivo:quadroFlutuante>

            <vivo:quadroFlutuante label="Incluir Grupamento" scroll="false" src="" idIframe="ifrmIncluiGrupamento" id="divIncluiGrupamento" spacesLeft="150" height="80" spacesTop="240" url="<%=request.getContextPath()%>" display="none" width="500"></vivo:quadroFlutuante>

            <vivo:quadroFlutuante label="Relacionar Grupamento &agrave;s Unidades" scroll="false" src="" idIframe="ifrmRelacGrupamento" id="divRelacGrupamento" spacesLeft="150" height="80" spacesTop="240" url="<%=request.getContextPath()%>" display="none" width="500"></vivo:quadroFlutuante>
            
    <script language="javascript" for="window" event="onload">
    <!--   
        if('<bean:write name="FormManterGrupam" property="msgError" />' != "")
        {
            alert('<bean:write name="FormManterGrupam" property="msgError" />');
        }
        oculta_div();
    -->
    </script> 
            
            
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>
