<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<!--Define Form Bean -->
<bean:define id="FormCampo"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="campoDinamicoForm"/>

<!--Define VOArray -->
<bean:define id="aCamposVO"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="campoDinamicoForm.camposVO"/>

<!--Define Combos -->
<bean:define id="aClassificador"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="campoDinamicoForm.campoComboVO.admClassificadorCampoVOArray"/>
<bean:define id="aLayout"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="campoDinamicoForm.campoComboVO.admLayoutApresentacaoCampoVOArray"/>
<bean:define id="aTipoDado"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="campoDinamicoForm.campoComboVO.admTipoDadoCampoVOArray"/>
<bean:define id="aMascara"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="campoDinamicoForm.campoComboVO.admMascaraApresentacaoVOArray"/>

<head>
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <script language="Javascript">

        function removeItem(id) 
        {
            document.getElementById("campoVO.idCampo").value  = id;
            document.forms[0].target = '';
            document.forms[0].action = "removeCampo.do";
            if (window.confirm("Confirma remoção do item?")) 
            {
                parent.mostrar_div();
                document.forms[0].submit(); 
            }
        }
        
        function listaCampo() 
        {
            document.forms[0].target = '';
            document.getElementById("campoVO.nmCampo").value = trim(document.getElementById("campoVO.nmCampo").value);
            document.forms[0].action = "pesquisaCampo.do";
            parent.mostrar_div();
            document.forms[0].submit();
        }
        
        // limpa campos.
        function limpar()
        {
            document.forms[0].target = '';
            
            document.getElementById("campoVO.idCampo").value            = "";
            document.getElementById("campoVO.nmCampo").value            = "";
            document.getElementById("campoVO.nrTamanho").value          = "";

            document.getElementById("campoVO.inDisponibilidade").checked    = false;
            document.getElementById("campoVO.inFiltro").checked             = false;
            document.getElementById("campoVO.inObrigatorio").checked        = false;
            document.getElementById("campoVO.inPesquisa").checked           = false;
            
            document.getElementById("campoVO.admClassificadorCampoVO.idClassificadorCampo").selectedIndex = 0;
            document.getElementById("campoVO.admMascaraApresentacaoVO.idMascaraApresentacao").selectedIndex = 0;
            document.getElementById("campoVO.admLayoutApresentacaoCampoVO.idLayoutApresentacaoCampo").selectedIndex = 0;
            document.getElementById("campoVO.admTipoDadoCampoVO.idTipoDadoCampo").selectedIndex = 0;

            document.getElementById("campoVO.nmCampo").focus();
        }
        
        function carregaAlteraItem(id)
        {
            document.getElementById("campoVO.idCampo").value  = id;

            divAlteraCampo.style.display = '';            
            document.forms[0].target = "ifrmAlteraCampo";
            document.forms[0].action = 'carregaAlterar.do';
            parent.mostrar_div();

            document.forms[0].submit();
        }
        
        function carregaIncluiItem()
        {
            limpar();
            divIncluiCampo.style.display = '';            
            document.forms[0].target = "ifrmIncluiCampo";
            document.forms[0].action = 'carregaIncluir.do';
            parent.mostrar_div();

            document.forms[0].submit();
        }

        function listaEnter(ev)
        {
            if(ev.keyCode == 13)
                listaCampo();
            else
                return false;    
        }
        
        function limpaForm()
        {
            document.forms[0].target = '';
            document.forms[0].action = 'limpaForm.do';
            parent.mostrar_div();

            document.forms[0].submit();

        }
        
        function editarParametros(id)
        {
            divParamCampo.style.display = '';            

            document.getElementById("campoVO.idCampo").value  = id;
            document.forms[0].target = "ifrmParamCampo";
            document.forms[0].action = 'carregaEdicaoAssoc.do';
            parent.mostrar_div();

            document.forms[0].submit();

        }
        
        window.name = 'base';
        
    </script>
</head>              
<body>
<acesso:controlHiddenItem nomeIdentificador="adm_cdina_verpagina">
        <script>
            parent.mostrar_div();
        </script>  

            <html:form action="pesquisaCampo.do" onsubmit="return false;">
            <html:hidden name="FormCampo" property="campoVO.idCampo"/>
    <table class="tbl_bgGray" width="100%" height="420" border="1">
        <tr>
            <td valign="top">
                <table border="0" width="100%" class="tbl_bgGray">
                    <tr>
                        <td>
                            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>

                                <table class="tbl_bgBlue" align="center" width="98%" border="0">
                                    <tr>
                                        <td align="left" width="20%">
                                            <netui:label value="Nome do Campo:" styleClass="tblEstatica_campoNome"/>
                                        </td>
                                        <td align="left" width="25%" colspan="3">
                                            <html:text name="FormCampo" property="campoVO.nmCampo" style="width:400px" styleClass="input" maxlength="200" onkeypress="listaEnter(event);"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="left">
                                            <netui:label value="Tamanho do Campo:" styleClass="tblEstatica_campoNome"/>
                                        </td>
                                        <td align="left">
                                            <html:text name="FormCampo" property="campoVO.nrTamanho" style="width:70px" styleClass="input" maxlength="3" onkeypress="listaEnter(event);"  onkeyup="checaInteiro(this)"/>
                                        </td>
                                        <td width="20%">
                                            <html:checkbox name="FormCampo" property="campoVO.inDisponibilidade" styleClass="radio" value="1"/> 
                                            Disponível
                                        </td>
                                        <td width="20%">
                                            <html:checkbox name="FormCampo" property="campoVO.inObrigatorio" styleClass="radio" value="1"/> 
                                            Obrigatório
                                        </td>
                                    <tr>
                                        <td align="left">
                                            <netui:label value="Classificador:" styleClass="tblEstatica_campoNome"/>
                                        </td>
                                        <td align="left">
                                            <html:select name="FormCampo" property="campoVO.admClassificadorCampoVO.idClassificadorCampo" style="width:200px;" styleClass="SELECT">
                                                <html:option value="-1">-- Selecione --</html:option>
                                                <html:options collection="aClassificador" property="idClassificadorCampo" labelProperty="nmClassificadorCampo" /> 
                                            </html:select>
                                        </td>
                                        <td>
                                            <html:checkbox name="FormCampo" property="campoVO.inFiltro" styleClass="radio" value="1"/> 
                                            Possui Filtro?
                                        </td>
                                        <td>
                                            <html:checkbox name="FormCampo" property="campoVO.inPesquisa" styleClass="radio" value="1"/> 
                                            Pesquisa?
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="left">
                                            <netui:label value="Mascara Apresentação:" styleClass="tblEstatica_campoNome"/>
                                        </td>
                                        <td align="left">
                                            <html:select name="FormCampo" property="campoVO.admMascaraApresentacaoVO.idMascaraApresentacao" style="width:200px;" styleClass="SELECT">
                                                <html:option value="-1">-- Selecione --</html:option>
                                                <html:options collection="aMascara" property="idMascaraApresentacao" labelProperty="nmMascaraApresentacao" /> 
                                            </html:select>
                                        </td>
                                        <td>
                                            <netui:label value="Layout Apresentação:" styleClass="tblEstatica_campoNome"/>
                                        </td>
                                        <td>
                                            <html:select name="FormCampo" property="campoVO.admLayoutApresentacaoCampoVO.idLayoutApresentacaoCampo" style="width:150px;" styleClass="SELECT">
                                                <html:option value="-1">-- Selecione --</html:option>
                                                <html:options collection="aLayout" property="idLayoutApresentacaoCampo" labelProperty="nmLayoutApresentacaoCampo" />
                                            </html:select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <netui:label value="Tipo do Dado:" styleClass="tblEstatica_campoNome"/>
                                        </td>
                                        <td colspan="2">
                                            <html:select name="FormCampo" property="campoVO.admTipoDadoCampoVO.idTipoDadoCampo" style="width:200px;" styleClass="SELECT">
                                                <html:option value="-1">-- Selecione --</html:option>
                                                <html:options collection="aTipoDado" property="idTipoDadoCampo" labelProperty="nmTipoDadoCampo" />
                                            </html:select>
                                        </td>
                                        <td  align="right">
                                            <img style="cursor:hand;border:none" id="btLimpar" onClick="limpar();" src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_limpar_over.gif'"/>
                                            <acesso:controlHiddenItem nomeIdentificador="adm_cdina_pesquisa">
                                                <img style="cursor:hand;border:none" id="btPesquisar" onclick="listaCampo();" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif'" />                    
                                            </acesso:controlHiddenItem>
                                        
                                        </td>
                                    </tr>
                                </table>                            
                            
                        </td>
                    </tr>
                    <tr>
                        <td align="center">
                            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>

                            <logic:greaterThan name="FormCampo" property="campoVOArrayLength" value="0">
                                        <vivo:tbTable selectable="true" layoutWidth="705" layoutHeight="180" tableWidth="705" styleId="tableTitle" sortable="true">
                                            <vivo:tbHeader>
                                                <vivo:tbHeaderColumn align="center" width="35%" type="string">Nome do Campo</vivo:tbHeaderColumn>
                                                <vivo:tbHeaderColumn align="center" width="25%" type="string">Tipo do Dado</vivo:tbHeaderColumn>
                                                <vivo:tbHeaderColumn align="center" width="20%" type="string">Classificador</vivo:tbHeaderColumn>
                                                <vivo:tbHeaderColumn align="center" width="10%" type="string">Obrigatório?</vivo:tbHeaderColumn>
                                                <vivo:tbHeaderColumn align="center" width="4%" type="string"></vivo:tbHeaderColumn>
                                                <vivo:tbHeaderColumn align="center" width="4%" type="string"></vivo:tbHeaderColumn>
                                                <vivo:tbHeaderColumn align="center" width="4%" type="string"></vivo:tbHeaderColumn>
                                            </vivo:tbHeader>
                                            <vivo:tbRows>
                                                <logic:iterate name="aCamposVO" property="admCampoContatoVOArray" id="itemCampo">
                                                    <vivo:tbRow key="linha">
                                                        <vivo:tbRowColumn>
                                                            <vivo:hint maxLength="35"><bean:write name="itemCampo" property="nmCampo"/></vivo:hint>
                                                        </vivo:tbRowColumn>
                                                        <vivo:tbRowColumn>
                                                            <vivo:hint maxLength="25"><bean:write name="itemCampo" property="admTipoDadoCampoVO.nmTipoDadoCampo"/></vivo:hint>
                                                        </vivo:tbRowColumn>
                                                        <vivo:tbRowColumn>
                                                            <vivo:hint maxLength="20"><bean:write name="itemCampo" property="admClassificadorCampoVO.nmClassificadorCampo"/></vivo:hint>
                                                        </vivo:tbRowColumn>
                                                        <vivo:tbRowColumn>
                                                            <logic:equal name="itemCampo" property="inObrigatorio" value="1">
                                                                Sim
                                                            </logic:equal>
                                                            <logic:equal name="itemCampo" property="inObrigatorio" value="0">
                                                                Não
                                                            </logic:equal>
                                                        </vivo:tbRowColumn>
                                                        <vivo:tbRowColumn>
                                                            <acesso:controlHiddenItem nomeIdentificador="adm_cdina_edita">
                                                                <a href="Javascript:carregaAlteraItem('<bean:write name="itemCampo" property="idCampo" />');" >
                                                                    <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" border="0" alt="Editar Campo">
                                                                </a>
                                                            </acesso:controlHiddenItem>
                                                        </vivo:tbRowColumn>
                                                        <vivo:tbRowColumn>
                                                            <acesso:controlHiddenItem nomeIdentificador="adm_cdina_exclui">
                                                                <a href="#" onclick="removeItem('<bean:write name="itemCampo" property="idCampo" />'); return false">
                                                                    <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" border="0" alt="Excluir Campo">
                                                                </a>
                                                            </acesso:controlHiddenItem>
                                                        </vivo:tbRowColumn>
                                                        <vivo:tbRowColumn>
                                                            <acesso:controlHiddenItem nomeIdentificador="adm_cdina_param">
                                                                <logic:equal name="itemCampo" property="admTipoDadoCampoVO.inDominio" value="1">
                                                                    <logic:equal name="itemCampo" property="inDominio" value="1">
                                                                            <a href="javascript:editarParametros('<bean:write name="itemCampo" property="idCampo" />');">
                                                                                <img src="/FrontOfficeWeb/resources/images/bt_icon_relac.gif" border="0" alt="Editar Parâmetros de Campo">
                                                                            </a>
                                                                    </logic:equal>
                                                                    <logic:equal name="itemCampo" property="inDominio" value="0">
                                                                            <a href="javascript:editarParametros('<bean:write name="itemCampo" property="idCampo" />');">
                                                                                <img src="/FrontOfficeWeb/resources/images/icone_desagrupado1.gif" border="0" alt="Incluir Parâmetros para Campo">
                                                                            </a>
                                                                    </logic:equal>
                                                                </logic:equal>
                                                                <logic:equal name="itemCampo" property="admTipoDadoCampoVO.inDominio" value="0">
                                                                        <img src="/FrontOfficeWeb/resources/images/icone_desagrupado2.gif" border="0" alt="Este Campo não possui Domínio">
                                                                </logic:equal>
                                                            </acesso:controlHiddenItem>
                                                        </vivo:tbRowColumn>

                                                    </vivo:tbRow>
                                                </logic:iterate>
                                            </vivo:tbRows>
                                        </vivo:tbTable>
    
                                        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
    
                                        <table width="720" border="0" cellspacing="0" cellpadding="5" style="border:1px solid #D4D7DE; background-color:#F7F9FA; font-size:9px;">
                                            <tr>
                                                <td valign="middle" width="10%"><b>&nbsp;Legendas:</b></td>
                                                <td>
                                                    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
                                                </td>
                                                <td width="20%">
                                                    <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" align="absmiddle"> &nbsp;Editar Campo Dinâmico
                                                <td>
                                                <td>
                                                    <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" align="absmiddle"> &nbsp;Remover Campo Dinâmico
                                                </td>
                                            </tr>
                                        </table>
                            </logic:greaterThan>
                            <logic:equal name="FormCampo" property="campoVOArrayLength" value="0">

                                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>

                                <table width="98%" border="0" cellspacing="1" cellpadding="1" align="center">
                                    <tr>
                                        <td width="90%" align="center" class="tblEstatica_campoNome">
                                            Não foi encontrado nenhum Campo com a descrição fornecida.
                                        </td>
                                    </tr>
                                </table>
                            </logic:equal>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">
                            <acesso:controlHiddenItem nomeIdentificador="adm_cdina_inclui">
                                <img align="rigth" id="btIncluir" onClick="carregaIncluiItem();return false" src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_incluir_over.gif'" style="cursor:hand;border:none;" hspace="5" vspace="5" />
                            </acesso:controlHiddenItem>
                        </td>
                    </tr>
                </table>
                
            </td>
        </tr>
    </table>

        </html:form>
            <vivo:quadroFlutuante onclick="limpaForm();" label="Alterar Campo" scroll="false" src="" idIframe="ifrmAlteraCampo" id="divAlteraCampo" spacesLeft="10" height="150" width="740" spacesTop="40" url="<%=request.getContextPath()%>" display="none" ></vivo:quadroFlutuante>
            <vivo:quadroFlutuante onclick="limpaForm();" label="Incluir Campo" scroll="false" src="" idIframe="ifrmIncluiCampo" id="divIncluiCampo" spacesLeft="10" height="150" width="740" spacesTop="40" url="<%=request.getContextPath()%>" display="none" ></vivo:quadroFlutuante>
            <vivo:quadroFlutuante onclick="limpaForm();" label="Editar Parâmetros Campos" scroll="false" src="" idIframe="ifrmParamCampo" id="divParamCampo" spacesLeft="10" height="350" width="740" spacesTop="5" url="<%=request.getContextPath()%>" display="none" ></vivo:quadroFlutuante>


        <vivo:alert atributo="msgError" scope="request"/>
</acesso:controlHiddenItem>

        <script>
            parent.oculta_div();
        </script>  
        
</body>