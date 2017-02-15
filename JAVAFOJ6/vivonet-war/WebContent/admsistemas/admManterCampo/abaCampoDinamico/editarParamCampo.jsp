<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!--Define Form Bean -->
<bean:define id="FormEditarParam"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="campoDinamicoForm"/>

<!--Define Combo Tabela Dominio -->
<bean:define id="aTabelaDominio"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="campoDinamicoForm.dominioComboVO.admTabelaDominioVOArray"/>

<head>
        <link href="<%=request.getContextPath()%>/resources/css/xtree.css" type="text/css" rel="stylesheet"/>
        <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/tratamento.js" ></script>
        <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">        
        <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <script language="javascript">
        <!--
        function valida()
        {
            if(trim(document.getElementById("campoDominioVO.nmAtributoIdentificador").value) == '')
            {
                alert("Atributo é um campo obrigatório, favor preencher.");
                return false;
            
            }else if(trim(document.getElementById("campoDominioVO.nmAtributoDescritivo").value) == '')
            {
                alert("Descrição é um campo obrigatório, favor preencher.");
                return false;
            
            }else if(trim(document.getElementById("campoDominioVO.dsQuery").value) == '' 
                    && document.getElementById("campoDominioVO.idTabelaDominio").selectedIndex == 0)
            {
                alert("É necessário preencher o campo Query ou o campo Tabela.");
                return false;        

            }else if(trim(document.getElementById("campoDominioVO.dsQuery").value) != '' 
                    && document.getElementById("campoDominioVO.idTabelaDominio").selectedIndex != 0)
            {
                alert("É necessário preencher o campo Query ou o campo Tabela.");
                return false;
            
            }
            
            return true;
        
        }        
    
        // envia para action Gravar.
        function persiste()
        {
            if(valida())
            {
                var form = document.campoDinamicoForm;
                form.target = 'base';
                parent.parent.mostrar_div();
                form.submit();
            }
        }
        
        // -->
        </script>
</head>
<body>
        
            <form action="persisteDadosParam.do" name="campoDinamicoForm" method="post">
            <html:hidden name="FormEditarParam" property="campoDominioVO.idCampo" value='<%=request.getParameter("campoVO.idCampo")%>' />
        <vivo:quadro id="dadosEditar" height="70" width="730" label="Dados Campo Selecionado" scroll="no">
            <table width="100%" border="0" cellspacing="1" cellpadding="1" align="center" class="tbl_bgGray">
                <tr>
                    <td class="tblEstatica_campoNome" width="80">
                        <netui:label value="Nome:" styleClass="tblEstatica_campoNome"/>
                    </td>
                    <td class="tblEstatica_campoValor">
                        <vivo:hint maxLength="20"><bean:write name="FormEditarParam" property="campoVO.nmCampo"/></vivo:hint>
                    </td>
                    <td class="tblEstatica_campoNome">
                        <netui:label value="Tamanho: " styleClass="tblEstatica_campoNome"/>
                    </td>
                    <td class="tblEstatica_campoValor">
                        <bean:write name="FormEditarParam" property="campoVO.nrTamanho"/>
                    </td>
                </tr>
                <tr>
                    <td class="tblEstatica_campoNome">
                        <netui:label value="Classificador: " styleClass="tblEstatica_campoNome"/>
                    </td>
                    <td class="tblEstatica_campoValor">
                        <vivo:hint maxLength="20"><bean:write name="FormEditarParam" property="campoVO.admClassificadorCampoVO.nmClassificadorCampo"/></vivo:hint>
                    </td>
                    <td class="tblEstatica_campoNome">
                        <netui:label value="Layout Apres.: " styleClass="tblEstatica_campoNome"/>
                    </td>
                    <td class="tblEstatica_campoValor">
                        <vivo:hint maxLength="20"><bean:write name="FormEditarParam" property="campoVO.admLayoutApresentacaoCampoVO.nmLayoutApresentacaoCampo"/></vivo:hint>
                    </td>
                </tr>
                <tr>
                    <td class="tblEstatica_campoNome">
                        <netui:label value="Mascara Apres.: " styleClass="tblEstatica_campoNome"/>
                    </td>
                    <td class="tblEstatica_campoValor">
                        <vivo:hint maxLength="20"><bean:write name="FormEditarParam" property="campoVO.admMascaraApresentacaoVO.nmMascaraApresentacao"/></vivo:hint>
                    </td>
                    <td class="tblEstatica_campoNome">
                        <netui:label value="Tipo do Dado: " styleClass="tblEstatica_campoNome"/>
                    </td>
                    <td class="tblEstatica_campoValor">
                        <vivo:hint maxLength="20"><bean:write name="FormEditarParam" property="campoVO.admTipoDadoCampoVO.nmTipoDadoCampo"/></vivo:hint>
                    </td>
                </tr>
            </table>
        </vivo:quadro>
    
        <vivo:quadro id="dadosEditar" height="240" width="730" label="Parâmetros" scroll="no">
            <table width="100%" height="230" border="0" cellspacing="1" cellpadding="1" align="center" class="tbl_bgGray" >
                <tr>
                    <td width="100"><netui:label value="Tabela: " styleClass="tblEstatica_campoNome"/></td>
                    <td width="200" colspan="2">
                        <html:select name="FormEditarParam" property="campoDominioVO.idTabelaDominio" style="width:230px;">
                            <html:option value="-1">-- Selecione --</html:option>
                            <html:options collection="aTabelaDominio" property="idTabelaDominio" labelProperty="nmTabelaDominio" />
                        </html:select>
                    </td>
                </tr>
                <tr>
                    <td><font color="red">*</font><netui:label value="Atributo: " styleClass="tblEstatica_campoNome"/></td>
                    <td colspan="2">
                        <html:text name="FormEditarParam" property="campoDominioVO.nmAtributoIdentificador" style="width:400px;"/>
                    </td>
                </tr>
                <tr>
                    <td><font color="red">*</font><netui:label value="Descrição: " styleClass="tblEstatica_campoNome"/></td>
                    <td colspan="2">
                        <html:text name="FormEditarParam" property="campoDominioVO.nmAtributoDescritivo" style="width:400px;"/>
                    </td>
                </tr>
                <tr>
                    <td valign="top"><netui:label value="Query: " styleClass="tblEstatica_campoNome"/></td>
                    <td valign="top">
                        <html:textarea name="FormEditarParam" property="campoDominioVO.dsQuery" style="width:500px;height:150px;font: arial;color: #006699;" size="6" styleClass="textfield"/>
                    </td>
                    <td valign="baseline" align="right">
                        <img hspace="5" style="border:none;cursor:hand;"  onClick="persiste();" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_salvar_over.gif'"/>
                    </td>
                </tr>
            </table>    
        </vivo:quadro>            
        </form>
        <vivo:alert atributo="msgError" scope="request"/>
        <script>
            parent.parent.oculta_div();
        </script>          
        
     </body>   