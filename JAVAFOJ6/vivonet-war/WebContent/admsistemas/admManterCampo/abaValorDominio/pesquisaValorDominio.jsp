<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<!-- Form Bean -->
<bean:define id="FormValorDominio"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="valorDominioForm"/>

<!-- VOArray -->
<bean:define id="aValorDominio"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="valorDominioForm.dominiosVO"/>

<!-- Combos -->
<bean:define id="aOperadora"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="valorDominioForm.valorDominioCombo.admUFOperadoraSimplVOArray"/>
<bean:define id="aTipoLinha"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="valorDominioForm.valorDominioCombo.admTipoLinhaSimplVOArray"/>
<bean:define id="aTabelaDominio"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="valorDominioForm.valorDominioCombo.admTabelaDominioVOArray"/>

<head>
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <script language="Javascript">

        function removeItem(id) 
        {
            document.getElementById("dominioVO.idDominio").value  = id;
            document.forms[0].target = '';
            document.forms[0].action = "removeValorDominio.do";
            if (window.confirm("Confirma remoção do item?")) 
            {
                parent.mostrar_div();
                document.forms[0].submit(); 
            }
        }
        
        function valida()
        {
            if(document.getElementById("dominioVO.admTabelaDominioVO.idTabelaDominio").selectedIndex == 0)
            {
                alert("É necessário selecionar um Domínio para busca.");
                return false;
            }
            
            return true;            
        
        }
        
        function listaValorDominio() 
        {
            if(valida())
            {
                document.forms[0].target = '';
                document.forms[0].action = "pesquisaValorDominio.do";
                parent.mostrar_div();
                document.forms[0].submit();
            }
        }
        
        // limpa campos.
        function limpar()
        {
            document.forms[0].target = '';
            document.getElementById("dominioVO.idDominio").value = "";
            document.getElementById("dominioVO.nmDominio").value = "";

            document.getElementById("dominioVO.admUFOperadoraSimplVO.idUFOperadora").selectedIndex = 0;
            document.getElementById("dominioVO.admTipoLinhaSimplVO.idTipoLinha").selectedIndex = 0;
            document.getElementById("dominioVO.admTabelaDominioVO.idTabelaDominio").selectedIndex = 0;
            document.getElementById("dominioVO.inDisponibilidade").selectedIndex = 0;

            document.getElementById("dominioVO.nmDominio").focus();
        }
        
        function carregaAlteraItem(id)
        {
            document.getElementById("dominioVO.idDominio").value  = id;

            divAlteraValorDominio.style.display = '';            
            document.forms[0].target = "ifrmAlteraValorDominio";
            document.forms[0].action = 'carregaAlterar.do';
            parent.mostrar_div();

            document.forms[0].submit();
        }
        
        function carregaIncluiItem()
        {
            document.getElementById("dominioVO.idDominio").value  = "";
            document.getElementById("dominioVO.nmDominio").value  = "";

            divIncluiValorDominio.style.display = '';            
            document.forms[0].target = "ifrmIncluiValorDominio";
            document.forms[0].action = 'carregaIncluir.do';
            parent.mostrar_div();

            document.forms[0].submit();
        }

        function listaEnter(ev)
        {
            if(ev.keyCode == 13)
                listaValorDominio();
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
        
    </script>
</head>              
<body>
<acesso:controlHiddenItem nomeIdentificador="adm_vldom_verpagina">
        <script>
            parent.mostrar_div();
        </script>  

            <html:form action="pesquisaValorDominio.do" onsubmit="return false;">
            <html:hidden name="FormValorDominio" property="dominioVO.idDominio"/>
    <table class="tbl_bgGray" width="100%" height="420" >
        <tr>
            <td valign="top">
                <table border="0" width="100%" class="tbl_bgGray">
                    <tr>
                        <td>
                            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>

                                <table class="tbl_bgBlue" border="0" cellpadding="1" cellspacing="1" align="center" width="98%">
                                    <tr>
                                        <td ><netui:label value="Valor Domínio:" styleClass="tblEstatica_campoNome"/></td>
                                        <td colspan="3">
                                            <html:text name="FormValorDominio" property="dominioVO.nmDominio" style="width:360px" styleClass="input" maxlength="200" onkeypress="listaEnter(event);"/>
                                        </td>
                                        <td>
                                            <netui:label value="Disponível:" styleClass="tblEstatica_campoNome"/>
                                        </td>
                                        <td>
                                            <html:select name="FormValorDominio" property="dominioVO.inDisponibilidade" style="width:90px;">
                                                <html:option value="">Todos</html:option>
                                                <html:option value="1">Sim</html:option>
                                                <html:option value="0">Não</html:option>
                                            </html:select>
                                    </tr>
                                    <tr>
                                        <td><font color="red">*</font> <netui:label value="Domínio:" styleClass="tblEstatica_campoNome"/></td>
                                        <td>
                                            <html:select name="FormValorDominio" property="dominioVO.admTabelaDominioVO.idTabelaDominio" style="width:150px;">
                                                <html:option value="-1">-- Selecione --</html:option>
                                                <html:options collection="aTabelaDominio"  labelProperty="nmTabelaDominio" property="idTabelaDominio"/>
                                            </html:select>
                                        </td>
                                        <td><netui:label value="Operadora:" styleClass="tblEstatica_campoNome"/></td>
                                        <td>
                                            <html:select name="FormValorDominio" property="dominioVO.admUFOperadoraSimplVO.idUFOperadora" style="width:150px;">
                                                <html:option value="-1">-- Selecione --</html:option>
                                                <html:options collection="aOperadora"  labelProperty="dsUFOperadora" property="idUFOperadora"/>
                                            </html:select>
                                        </td>
                                        <td><netui:label value="Tipo Linha:" styleClass="tblEstatica_campoNome"/></td>
                                        <td>
                                            <html:select name="FormValorDominio" property="dominioVO.admTipoLinhaSimplVO.idTipoLinha" style="width:150px;">
                                                <html:option value="-1">-- Selecione --</html:option>
                                                <html:options collection="aTipoLinha"  labelProperty="dsTipoLinha" property="idTipoLinha"/>
                                            </html:select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="6" align="right">
                                            <img style="cursor:hand;border:none" id="btLimpar" onClick="limpar();" src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_limpar_over.gif'"/>
                                            <img style="cursor:hand;border:none" id="btPesquisar" onclick="listaValorDominio();" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif'" />
                                        </td>
                                    </tr>
                                </table>                            
                            
                        </td>
                    </tr>
                    <tr>
                        <td align="center">

                            <logic:greaterThan name="FormValorDominio" property="arrayValorDominioLength" value="0">
                                        <vivo:tbTable selectable="true" layoutWidth="705" layoutHeight="225" tableWidth="705" styleId="tableTitle" sortable="true">
                                            <vivo:tbHeader>
                                                <vivo:tbHeaderColumn align="left"   width="35%" type="string">Valor Domínio</vivo:tbHeaderColumn>
                                                <vivo:tbHeaderColumn align="center" width="25%" type="string">Operadora</vivo:tbHeaderColumn>
                                                <vivo:tbHeaderColumn align="center" width="20%" type="string">Tipo da Linha</vivo:tbHeaderColumn>
                                                <vivo:tbHeaderColumn align="center" width="10%" type="string">Disponível</vivo:tbHeaderColumn>
                                                <vivo:tbHeaderColumn align="center" width="3%" type="string"></vivo:tbHeaderColumn>
                                                <vivo:tbHeaderColumn align="center" width="3%" type="string"></vivo:tbHeaderColumn>
                                            </vivo:tbHeader>
                                            <vivo:tbRows>
                                                <logic:iterate name="aValorDominio" property="admDominioVOArray" id="itemDominio">
                                                    <vivo:tbRow key="linha">
                                                        <vivo:tbRowColumn>
                                                           <vivo:hint maxLength="35"><bean:write name="itemDominio" property="nmDominio"/></vivo:hint>
                                                        </vivo:tbRowColumn>
                                                        <vivo:tbRowColumn>
                                                           <vivo:hint maxLength="30"><bean:write name="itemDominio" property="admUFOperadoraSimplVO.dsUFOperadora"/></vivo:hint>
                                                        </vivo:tbRowColumn>
                                                        <vivo:tbRowColumn>
                                                            <vivo:hint maxLength="20"><bean:write name="itemDominio" property="admTipoLinhaSimplVO.dsTipoLinha"/></vivo:hint>
                                                        </vivo:tbRowColumn>
                                                        <vivo:tbRowColumn>
                                                            <logic:equal name="itemDominio" property="inDisponibilidade" value="1">
                                                                Sim
                                                            </logic:equal>
                                                            <logic:equal name="itemDominio" property="inDisponibilidade" value="0">
                                                                Não
                                                            </logic:equal>
                                                        </vivo:tbRowColumn>
                                                        <vivo:tbRowColumn>
                                                            <acesso:controlHiddenItem nomeIdentificador="adm_vldom_altera">
                                                                <a href="Javascript:carregaAlteraItem('<bean:write name="itemDominio" property="idDominio" />');" >
                                                                    <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" border="0" alt="Editar Valor Domínio" >
                                                                </a>
                                                            </acesso:controlHiddenItem>
                                                        </vivo:tbRowColumn>
                                                        <vivo:tbRowColumn>
                                                            <acesso:controlHiddenItem nomeIdentificador="adm_vldom_remove">
                                                                <a href="Javascript:removeItem('<bean:write name="itemDominio" property="idDominio" />');" >
                                                                    <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" border="0" alt="Excluir Valor Domínio" >
                                                                </a>
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
                                                    <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" align="absmiddle"> &nbsp;Editar Valor Domínio
                                                <td>
                                                <td>
                                                    <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" align="absmiddle"> &nbsp;Remover Valor Domínio
                                                </td>
                                                <td width="270">
                                                     &nbsp; Os campos indicados com (<font color="red">*</font>) são obrigatórios
                                                </td>
                                            </tr>
                                        </table>
                            </logic:greaterThan>
                            <logic:equal name="FormValorDominio" property="arrayValorDominioLength" value="0">

                                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>

                                <table width="98%" border="0" cellspacing="1" cellpadding="1" align="center">
                                    <tr>
                                        <td width="90%" align="center" class="tblEstatica_campoNome">
                                            Não foi encontrado nenhum Valor de Domínio com a descrição fornecida.
                                        </td>
                                    </tr>
                                </table>
                            </logic:equal>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">
                            <acesso:controlHiddenItem nomeIdentificador="adm_vldom_inclui">
                                <img align="rigth" id="btIncluir" onClick="carregaIncluiItem();return false" src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_incluir_over.gif'" style="cursor:hand;border:none;" hspace="5" vspace="5" />
                            </acesso:controlHiddenItem>
                        </td>
                    </tr>
                </table>
                
            </td>
        </tr>
    </table>
    
        </html:form>
            <vivo:quadroFlutuante onclick="limpaForm();" label="Alterar Valor Domínio" scroll="false" src="" idIframe="ifrmAlteraValorDominio" id="divAlteraValorDominio" spacesLeft="20" height="100" spacesTop="120" url="<%=request.getContextPath()%>" display="none" width="720"></vivo:quadroFlutuante>
            <vivo:quadroFlutuante onclick="limpaForm();" label="Incluir Valor Domínio" scroll="false" src="" idIframe="ifrmIncluiValorDominio" id="divIncluiValorDominio" spacesLeft="20" height="335" spacesTop="60" url="<%=request.getContextPath()%>" display="none" width="720"></vivo:quadroFlutuante>
            <!--vivo:quadroFlutuante onclick="limpaForm();" label="Cópia Valor Domínio" scroll="false" src="" idIframe="ifrmCopiaValorDominio" id="divCopiaValorDominio" spacesLeft="20" height="350" spacesTop="10" url="<%=request.getContextPath()%>" display="none" width="720" >< /vivo:quadroFlutuante -->

        <vivo:alert atributo="msgError" scope="request"/>
</acesso:controlHiddenItem>
        
        <script>
            parent.oculta_div();
        </script>  