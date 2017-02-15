<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="FormSistema"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="sistemasForm"/>
<bean:define id="sistema"        name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="sistemasForm.sistema"/>
<bean:define id="itemSistema"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="sistemasForm.itemSistema"/>
<!-- utilizado para percorrer cada item do array de resultados-->
<bean:define id="listaSistemas"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="sistemasForm.listaSistemas"/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp"> 
    <netui-template:setAttribute name="title" value="Manter Sistema"/>
    <netui-template:setAttribute name="modulo" value="Gestão de Usuários"/>
    <netui-template:section name="headerSection">
        <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/tratamento.js" ></script>
        <script language="JavaScript" type="text/JavaScript">
        <!--

        function removeItem(){
            document.forms[0].target = '';
            if (window.confirm("Confirma exclusão do item?")){
                return true;
            }else{
                return false;
            }
        }

        function listaSistemas(){
            document.forms[0].action = 'listaSistemas.do';
            document.forms[0].target = '';
            mostrar_div();
            document.forms[0].submit();
        }

        function alteraSistema(id){
            divAlteraSistema.style.display = '';
            document.forms[0].target = "ifrmAlteraSistema";
            document.forms[0].action = "alterarSistema.do?operacao=alterar&idSistema="+id;
            mostrar_div();
            document.forms[0].submit();
        }
        
        function incluiSistema(){
            divIncluiSistema.style.display = '';
            document.forms[0].target = "ifrmIncluiSistema";
            document.forms[0].action = "alterarSistema.do?operacao=incluir";
            mostrar_div();
            document.forms[0].submit();
        }

        // limpa campos.
        function limpa(){
            document.forms[0].target = '';
            document.forms[0].sgSistema.value = '';
            document.forms[0].nmUrlBase.value = '';
            document.forms[0].dsSistema.value = '';
            document.forms[0].inAcessoControlado.selectedIndex = 0;
        }

        function verificaEnter(ev){
            if(ev.keyCode == 13)
                return false;
        }
        -->
        </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="usu_ms_verpagina">
        <!-- Menu de Gestao de Usuarios -->
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <script>
            mostrar_div();
        </script>   
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
        <vivo:sessao id="qdMain" height="470" width="790" label="Sistema" operacoes="Manuten&ccedil;&atilde;o" scroll="no">
            <form action="salvaSistema.do" onSubmit="return false;" method="post">
            <html:hidden name="FormSistema" property="idSistema"/>
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="10"></div>
            <center>    
            <vivo:quadro height="410" id="listasistemas" width="760" label="Sistemas cadastrados" scroll="no">
                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="2"></div>
                <table width="740" border="0" cellspacing="0" cellpadding="" class="tbl_bgGray">
                    <tr><td height="10" colspan="3"></td></tr>
                    <tr>
                        <td width="125" class="tblEstatica_campoNome">&nbsp;
                            <netui:label value="Sigla do Sistema:" styleClass="tblEstatica_campoNome"/>
                        </td>
                        <td width="70%" style="padding-left:3px;" colspan="2">
                            <html:text name="FormSistema" property="sgSistema" style="width:100px;" maxlength="10"/>
                        </td>
                    </tr>
                    <tr>
                        <td width="30%" class="tblEstatica_campoNome">&nbsp;
                            <netui:label value="Nome do Sistema: " styleClass="tblEstatica_campoNome"/>
                        </td>
                        <td width="70%" style="padding-left:3px;" colspan="2">
                            <html:text name="FormSistema" property="dsSistema" style="width:250px" maxlength="140"/>
                        </td>
                    </tr>
                    <tr>
                        <td width="30%" class="tblEstatica_campoNome">&nbsp;
                            <netui:label value="URL Base: " styleClass="tblEstatica_campoNome"/>
                        </td>
                        <td width="70%" style="padding-left:3px;" colspan="2">
                            <html:text name="FormSistema" property="nmUrlBase" style="width:250px" maxlength="50" onkeypress="return ValidarTeclaURL()"/>
                        </td>
                    </tr>
                    <tr>
                        <td width="30%" class="tblEstatica_campoNome">&nbsp;
                            <netui:label value="Acesso Controlado: " styleClass="tblEstatica_campoNome"/>
                        </td>
                        <td width="40%" align="left">&nbsp;
                            <html:select name="FormSistema" property="inAcessoControlado" value="inAcessoControlado">
                                <html:option value="">Escolha...</html:option>
                                <html:option value="1">Sim</html:option>
                                <html:option value="0">Não</html:option>
                            </html:select>                                
                        </td>
                        <td width="30%" align="right">
	                        <img hspace="15" style="border:none;cursor:hand;" align="middle" src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_limpar_over.gif'" onClick="limpa();"/>
	                        <acesso:controlHiddenItem nomeIdentificador="usu_ms_listas">
	                            <img hspace="20" style="border:none;cursor:hand;" align="middle" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" border="0" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif'" onClick="listaSistemas();" />
	                        </acesso:controlHiddenItem>
                        </td>
                    </tr>
                    <tr><td height="4" colspan="3"></td></tr>
                </table>
                     
                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="4"></div>
                
                <!-- Testa se alguma busca foi realizada -->
                <!-- Caso haja uma lista de sistemas como retorno, seu comprimento será diferente de 0 -->

                <logic:notEqual name="FormSistema" property="listaSistemasLength" value="0">
                
                <logic:notEqual name="FormSistema" property="listaSistemasLength" value="-1">
                    <vivo:tbTable selectable="true" layoutWidth="730" layoutHeight="220" tableWidth="730" styleId="tableTitle" sortable="true">
                        <vivo:tbHeader>
                            <vivo:tbHeaderColumn align="center" width="10%" type="string">Sigla</vivo:tbHeaderColumn>					
                            <vivo:tbHeaderColumn align="left"   width="30%" type="string">Nome do Sistema</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="left"   width="40" type="string">URL Base</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="center" width="5%" type="string">Acesso Control.</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="center" width="5%" type="string"></vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="center" width="5%" type="string"></vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="center" width="5%" type="string"></vivo:tbHeaderColumn>
                        </vivo:tbHeader>
                        <vivo:tbRows>
                            <logic:iterate name="FormSistema" property="listaSistemas" id="itemSistema">
                                <vivo:tbRow key="linha">
                                    <vivo:tbRowColumn>
                                        <bean:write name="itemSistema" property="sgSistema"/>
                                    </vivo:tbRowColumn>
                                    <vivo:tbRowColumn>
                                        <vivo:hint maxLength="20">
                                        <bean:write name="itemSistema" property="dsSistema"/>
                                        </vivo:hint>
                                    </vivo:tbRowColumn>
                                    <vivo:tbRowColumn>
                                        <vivo:hint maxLength="40">
                                            <bean:write name="itemSistema" property="nmUrlBase"/>
                                        </vivo:hint>
                                    </vivo:tbRowColumn>
                                    <vivo:tbRowColumn>
                                        <logic:equal name="itemSistema" property="inAcessoControlado" value="0">
                                            Não
                                        </logic:equal>
                                        <logic:equal name="itemSistema" property="inAcessoControlado" value="1">
                                            Sim
                                        </logic:equal>
                                    </vivo:tbRowColumn>
                                    <vivo:tbRowColumn>
                                    <acesso:controlHiddenItem nomeIdentificador="usu_ms_alterar">
                                        <img onclick="alteraSistema(<bean:write name="itemSistema" property="idSistema" />);" src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" border="0" alt="Alterar Sistema">
                                    </acesso:controlHiddenItem>
                                    </vivo:tbRowColumn>
                                    <vivo:tbRowColumn>   
                                        <acesso:controlHiddenItem nomeIdentificador="usu_ms_remover"> 
                                        <html:link page="/usuario/manterSistema/editarParSistema/removeSistema.do" paramId="sistemaId" onclick="return removeItem();mostrar_div();" paramName="itemSistema" paramProperty="idSistema">
                                            <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" alt="Excluir Sistema" style="border:none;cursor:hand;" />
                                        </html:link>
                                        </acesso:controlHiddenItem>
                                    </vivo:tbRowColumn>
                                    <vivo:tbRowColumn>
                                        <acesso:controlHiddenItem nomeIdentificador="usu_ms_editar">
                                        <html:link onclick="mostrar_div();" page="/usuario/manterSistema/editarParSistema/editaSistema.do?tipo=param" paramId="sistemaId" paramName="itemSistema" paramProperty="idSistema">
                                             <img src="/FrontOfficeWeb/resources/images/bt_icon_propried.gif"  alt="Editar Par&acirc;metros" style="border:none;cursor:hand;"/>
                                        </html:link>
                                        </acesso:controlHiddenItem>
                                    </vivo:tbRowColumn>
                                </vivo:tbRow>
                            </logic:iterate>
                        </vivo:tbRows>
                        
                    </vivo:tbTable>
                    
                    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="3"></div>
                
                    <table width="747" border="0" cellspacing="0" cellpadding="5" style="border:1px solid #D4D7DE; background-color:#F7F9FA; font-size:9px;">
                        <tr>
                            <td valign="middle" width="70"><b>&nbsp;Legendas:</b></td>
                            <td>
                                <img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="4">
                            </td>
                            <td>
                                <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" align="middle"> &nbsp;Alterar Sistema
                            </td>
                            <td>
                                <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" align="middle"> &nbsp;Excluir Sistema
                            </td>
                            <td width="320">
                                <img src="/FrontOfficeWeb/resources/images/bt_icon_propried.gif" align="middle"> &nbsp;Editar Par&acirc;metros
                            </td>
                        </tr>
                    </table>                    
                    </logic:notEqual>
                    <logic:equal name="FormSistema" property="listaSistemasLength" value="-1">
	                    <div align="center" style="font-size:11px;">
	                        Não foi encontrado nenhum registro com a descrição fornecida.
	                    </div>
                    </logic:equal>
                </logic:notEqual>
                <div align="right">
                <acesso:controlHiddenItem nomeIdentificador="usu_ms_incluir">
                    <img hspace="10" vspace="5" onClick="incluiSistema(); return false" id="btIncluir" src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_incluir_over.gif'" style="border:none;cursor:hand;"/>            
                </acesso:controlHiddenItem>
                </div>        
            </vivo:quadro>
            </center>
        <table width="760" cellpadding="0" cellspacing="0">
            <tr>
                <td>
                    <img hspace="8" vspace="10" id="btVoltar" onClick="window.location.href='/FrontOfficeWeb/index.jsp'; return false" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_voltar_over.gif'" style="border:none;cursor:hand;"/>
                </td>
                <td align="right">
                    
                </td>
            </tr>
        </table>
        </form>
        <vivo:quadroFlutuante label=" Alterar Sistema" scroll="false" src="" idIframe="ifrmAlteraSistema" id="divAlteraSistema" spacesLeft="150" height="130" spacesTop="120" url="<%=request.getContextPath()%>" display="none" width="500"></vivo:quadroFlutuante>
        <vivo:quadroFlutuante label=" Incluir Sistema" scroll="false" src="" idIframe="ifrmIncluiSistema" id="divIncluiSistema" spacesLeft="150" height="130" spacesTop="120" url="<%=request.getContextPath()%>" display="none" width="500"></vivo:quadroFlutuante>
        <script language="javascript" for="window" event="onload">
        <!--   
            if('<bean:write name="FormSistema" property="msgError" />' != "")
            {
                alert('<bean:write name="FormSistema" property="msgError" />');
            }
            oculta_div();
        -->
        </script> 
        </vivo:sessao>
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>
