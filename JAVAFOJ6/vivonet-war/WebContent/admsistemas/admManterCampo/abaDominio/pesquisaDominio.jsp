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
<bean:define id="Formdominio"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="dominioForm"/>

<!-- VOArray -->
<bean:define id="aAdmDominios"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="dominioForm.dominiosVO"/>

<head>
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <script language="Javascript">

        function removeItem(id) 
        {
            document.getElementById("dominioVO.idTabelaDominio").value  = id;
            document.forms[0].target = '';
            document.forms[0].action = "removeDominio.do";
            if (window.confirm("Confirma remoção do item?")) 
            {
                parent.mostrar_div();
                document.forms[0].submit(); 
            }
        }
        
        function listaDominio() 
        {
            document.forms[0].target = '';
            document.getElementById("dominioVO.nmTabelaDominio").value = trim(document.getElementById("dominioVO.nmTabelaDominio").value);
            document.forms[0].action = "pesquisaDominio.do";
            parent.mostrar_div();
            document.forms[0].submit();
        }
        
        // limpa campos.
        function limpar()
        {
            document.forms[0].target = '';
            document.getElementById("dominioVO.idTabelaDominio").value = "";
            document.getElementById("dominioVO.nmTabelaDominio").value = "";
            document.getElementById("dominioVO.nmTabelaDominio").focus();
        }
        
        function carregaAlteraItem(id)
        {
            document.getElementById("dominioVO.idTabelaDominio").value  = id;

            divAlteraDominio.style.display = '';            
            document.forms[0].target = "ifrmAlteraDominio";
            document.forms[0].action = 'carregaAlterar.do';
            parent.mostrar_div();

            document.forms[0].submit();
        }
        
        function carregaIncluiItem()
        {
            document.getElementById("dominioVO.idTabelaDominio").value  = "";
            document.getElementById("dominioVO.nmTabelaDominio").value  = "";

            divIncluiDominio.style.display = '';            
            document.forms[0].target = "ifrmIncluiDominio";
            document.forms[0].action = 'carregaIncluir.do';
            parent.mostrar_div();

            document.forms[0].submit();
        }

        function listaEnter(ev)
        {
            if(ev.keyCode == 13)
                listaDominio();
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

<acesso:controlHiddenItem nomeIdentificador="adm_dom_verpagina">

        <script>
            parent.mostrar_div();
        </script>  

            <html:form action="pesquisaDominio.do" onsubmit="return false;">
            <html:hidden name="Formdominio" property="dominioVO.idTabelaDominio"/>
    <table class="tbl_bgGray" width="100%" height="420" >
        <tr>
            <td valign="top">
                <table border="0" width="100%" class="tbl_bgGray">
                    <tr>
                        <td>
                            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>

                                <table class="tbl_bgBlue" align="center" width="98%">
                                    <tr>
                                        <td><netui:label value="Nome do Domínio:" styleClass="tblEstatica_campoNome"/></td>
                                        <td><html:text name="Formdominio" property="dominioVO.nmTabelaDominio" style="width:330px" styleClass="input" maxlength="200" onkeypress="listaEnter(event);"/></td>
                                        <td>
                                            <img style="cursor:hand;border:none" id="btLimpar" onClick="limpar();" src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_limpar_over.gif'"/>
                                            <acesso:controlHiddenItem nomeIdentificador="adm_dom_pesquisa">
                                                <img style="cursor:hand;border:none" id="btPesquisar" onclick="listaDominio();" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif'" />
                                            </acesso:controlHiddenItem>
                                        </td>
                                    </tr>
                                </table>                            
                            
                        </td>
                    </tr>
                    <tr>
                        <td align="center">
                            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>

                            <logic:greaterThan name="Formdominio" property="dominiosVOLength" value="0">
                                        <vivo:tbTable selectable="true" layoutWidth="705" layoutHeight="270" tableWidth="705" styleId="tableTitle" sortable="true">
                                            <vivo:tbHeader>
                                                <vivo:tbHeaderColumn align="left"   width="" type="string">Domínios encontrados</vivo:tbHeaderColumn>
                                                <vivo:tbHeaderColumn align="center" width="20" type="string"></vivo:tbHeaderColumn>
                                                <vivo:tbHeaderColumn align="center" width="20" type="string"></vivo:tbHeaderColumn>
                                            </vivo:tbHeader>
                                            <vivo:tbRows>
                                                <logic:iterate name="aAdmDominios" property="admTabelaDominioVOArray" id="itemDominio">
                                                    <vivo:tbRow key="linha">
                                                        <vivo:tbRowColumn>
                                                            <bean:write name="itemDominio" property="nmTabelaDominio"/>
                                                        </vivo:tbRowColumn>
                                                        <vivo:tbRowColumn>
                                                            <acesso:controlHiddenItem nomeIdentificador="adm_dom_edita">
                                                                <a href="Javascript:carregaAlteraItem('<bean:write name="itemDominio" property="idTabelaDominio" />');" >
                                                                    <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" border="0" alt="Editar Dominio">
                                                                </a>
                                                            </acesso:controlHiddenItem>
                                                        </vivo:tbRowColumn>
                                                        <vivo:tbRowColumn>
                                                            <acesso:controlHiddenItem nomeIdentificador="adm_dom_exclui">
                                                                <a href="#" onclick="removeItem('<bean:write name="itemDominio" property="idTabelaDominio" />'); return false">
                                                                    <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" border="0" alt="Excluir Dominio">
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
                                                    <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" align="absmiddle"> &nbsp;Editar Domínio
                                                <td>
                                                <td>
                                                    <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" align="absmiddle"> &nbsp;Remover Domínio
                                                </td>
                                            </tr>
                                        </table>
                            </logic:greaterThan>
                            <logic:equal name="Formdominio" property="dominiosVOLength" value="0">

                                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>

                                <table width="98%" border="0" cellspacing="1" cellpadding="1" align="center">
                                    <tr>
                                        <td width="90%" align="center" class="tblEstatica_campoNome">
                                            Não foi encontrado nenhum Domínio com a descrição fornecida.
                                        </td>
                                    </tr>
                                </table>
                            </logic:equal>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">
                            <acesso:controlHiddenItem nomeIdentificador="adm_dom_inclui">
                            <img align="rigth" id="btIncluir" onClick="carregaIncluiItem();return false" src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_incluir_over.gif'" style="cursor:hand;border:none;" hspace="5" vspace="5" />
                            </acesso:controlHiddenItem>
                        </td>
                    </tr>
                </table>
                
            </td>
        </tr>
    </table>
    
        </html:form>
            <vivo:quadroFlutuante onclick="limpaForm();" label="Alterar Domínio" scroll="false" src="" idIframe="ifrmAlteraDominio" id="divAlteraDominio" spacesLeft="150" height="80" spacesTop="120" url="<%=request.getContextPath()%>" display="none" width="500"></vivo:quadroFlutuante>
            <vivo:quadroFlutuante onclick="limpaForm();" label="Incluir Domínio" scroll="false" src="" idIframe="ifrmIncluiDominio" id="divIncluiDominio" spacesLeft="150" height="80" spacesTop="120" url="<%=request.getContextPath()%>" display="none" width="500"></vivo:quadroFlutuante>

        <vivo:alert atributo="msgError" scope="request"/>
</acesso:controlHiddenItem>
        <script>
            parent.oculta_div();
        </script>  
</body>