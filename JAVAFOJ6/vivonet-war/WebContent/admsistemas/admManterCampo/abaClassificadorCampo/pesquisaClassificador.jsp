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
<bean:define id="FormClassificador"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formClassificador"/>

<!-- VOArray -->
<bean:define id="admClassificadorCampos"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formClassificador.admClassificadorVOArray"/>

<head>
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <script language="Javascript">

        function removeItem(id) 
        {
            document.getElementById("admClassificardorVO.idClassificadorCampo").value  = id;
            document.forms[0].target = '';
            document.forms[0].action = "removeClassificador.do";
            if (window.confirm("Confirma remoção do item?")) 
            {
                parent.mostrar_div();
                document.forms[0].submit(); 
            }
        }
        
        function listaClassificador() 
        {
            document.forms[0].target = '';
            document.getElementById("admClassificardorVO.nmClassificadorCampo").value = trim(document.getElementById("admClassificardorVO.nmClassificadorCampo").value);
            document.forms[0].action = "pesquisaClassificador.do";
            parent.mostrar_div();
            document.forms[0].submit();
        }
        
        // limpa campos.
        function limpar()
        {
            document.forms[0].target = '';
            document.getElementById("admClassificardorVO.nmClassificadorCampo").value = "";
            document.getElementById("admClassificardorVO.idClassificadorCampo").value = "";
            document.getElementById("admClassificardorVO.nmClassificadorCampo").focus();
        }
        
        function carregaAlteraItem(id)
        {
            document.getElementById("admClassificardorVO.idClassificadorCampo").value  = id;

            divAlteraClassificador.style.display = '';            
            document.forms[0].target = "ifrmAlteraClassificador";
            document.forms[0].action = 'carregaAlterar.do';
            parent.mostrar_div();

            document.forms[0].submit();
        }
        
        function carregaIncluiItem()
        {
            document.getElementById("admClassificardorVO.idClassificadorCampo").value  = "";
            document.getElementById("admClassificardorVO.nmClassificadorCampo").value  = "";

            divIncluiClassificador.style.display = '';            
            document.forms[0].target = "ifrmIncluiClassificador";
            document.forms[0].action = 'carregaIncluir.do';
            parent.mostrar_div();

            document.forms[0].submit();
        }

        function listaEnter(ev)
        {
            if(ev.keyCode == 13)
                listaClassificador();
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
        <script>
            parent.mostrar_div();
        </script>  

<acesso:controlHiddenItem nomeIdentificador="adm_clas_verpagina">
            <html:form action="pesquisaClassificador.do" onsubmit="return false;">
            <html:hidden name="FormClassificador" property="admClassificardorVO.idClassificadorCampo"/>
    <table class="tbl_bgGray" width="100%" height="420" >
        <tr>
            <td valign="top">
                <table border="0" width="100%" class="tbl_bgGray">
                    <tr>
                        <td>
                            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>

                                <table class="tbl_bgBlue" align="center" width="98%">
                                    <tr>
                                        <td><netui:label value="Nome do Classificador:" styleClass="tblEstatica_campoNome"/></td>
                                        <td><html:text name="FormClassificador" property="admClassificardorVO.nmClassificadorCampo" style="width:330px" styleClass="input" maxlength="200" onkeypress="listaEnter(event);"/></td>
                                        <td>
                                            <img style="cursor:hand;border:none" id="btLimpar" onClick="limpar();" src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_limpar_over.gif'"/>
                                            <acesso:controlHiddenItem nomeIdentificador="adm_clas_pesquisa">  
                                                <img style="cursor:hand;border:none" id="btPesquisar" onclick="listaClassificador();" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif'" />                    
                                            </acesso:controlHiddenItem>
                                        </td>
                                    </tr>
                                </table>                            
                            
                        </td>
                    </tr>
                    <tr>
                        <td align="center">
                            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>

                            <logic:greaterThan name="FormClassificador" property="arrayClassificadorLength" value="0">
                                        <vivo:tbTable selectable="true" layoutWidth="705" layoutHeight="270" tableWidth="705" styleId="tableTitle" sortable="true">
                                            <vivo:tbHeader>
                                                <vivo:tbHeaderColumn align="left"   width="" type="string">Classificadores encontrados</vivo:tbHeaderColumn>
                                                <vivo:tbHeaderColumn align="center" width="20" type="string"></vivo:tbHeaderColumn>
                                                <vivo:tbHeaderColumn align="center" width="20" type="string"></vivo:tbHeaderColumn>
                                            </vivo:tbHeader>
                                            <vivo:tbRows>
                                                <logic:iterate name="admClassificadorCampos" property="admClassificadorCampoVOArray" id="itemClassificador">
                                                    <vivo:tbRow key="linha">
                                                        <vivo:tbRowColumn>
                                                            <bean:write name="itemClassificador" property="nmClassificadorCampo"/>
                                                        </vivo:tbRowColumn>
                                                        <vivo:tbRowColumn>
                                                            <acesso:controlHiddenItem nomeIdentificador="adm_clas_altera">  
                                                                <a href="Javascript:carregaAlteraItem('<bean:write name="itemClassificador" property="idClassificadorCampo" />');" >
                                                                    <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" border="0" alt="Editar Classificador">
                                                                </a>
                                                            </acesso:controlHiddenItem>
                                                        </vivo:tbRowColumn>
                                                        <vivo:tbRowColumn>
                                                            <acesso:controlHiddenItem nomeIdentificador="adm_clas_remove">  
                                                                <a href="#" onclick="removeItem('<bean:write name="itemClassificador" property="idClassificadorCampo" />'); return false">
                                                                    <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" border="0" alt="Excluir Classificador">
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
                                                    <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" align="absmiddle"> &nbsp;Editar Classificador
                                                <td>
                                                <td>
                                                    <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" align="absmiddle"> &nbsp;Remover Classificador
                                                </td>
                                            </tr>
                                        </table>
                            </logic:greaterThan>
                            <logic:equal name="FormClassificador" property="arrayClassificadorLength" value="0">

                                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>

                                <table width="98%" border="0" cellspacing="1" cellpadding="1" align="center">
                                    <tr>
                                        <td width="90%" align="center" class="tblEstatica_campoNome">
                                            Não foi encontrado nenhum Classificador com a descrição fornecida.
                                        </td>
                                    </tr>
                                </table>
                            </logic:equal>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">
                            <acesso:controlHiddenItem nomeIdentificador="adm_clas_inclui">                        
                                <img align="rigth" id="btIncluir" onClick="carregaIncluiItem();return false" src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_incluir_over.gif'" style="cursor:hand;border:none;" hspace="5" vspace="5" />
                            </acesso:controlHiddenItem>
                        </td>
                    </tr>
                </table>
                
            </td>
        </tr>
    </table>
    
        </html:form>
            <vivo:quadroFlutuante onclick="limpaForm();" label="Alterar Classificador" scroll="false" src="" idIframe="ifrmAlteraClassificador" id="divAlteraClassificador" spacesLeft="150" height="80" spacesTop="120" url="<%=request.getContextPath()%>" display="none" width="500"></vivo:quadroFlutuante>
            <vivo:quadroFlutuante onclick="limpaForm();" label="Incluir Classificador" scroll="false" src="" idIframe="ifrmIncluiClassificador" id="divIncluiClassificador" spacesLeft="150" height="80" spacesTop="120" url="<%=request.getContextPath()%>" display="none" width="500"></vivo:quadroFlutuante>

        <vivo:alert atributo="msgError" scope="request"/>
</acesso:controlHiddenItem>
        <script>
            parent.oculta_div();
        </script>  