<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!-- Form Bean -->
<bean:define id="FormValorDominio"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="valorDominioForm"/>

<!-- Combos -->
<bean:define id="aOperadora"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="valorDominioForm.valorDominioCombo.admUFOperadoraSimplVOArray"/>
<bean:define id="aTipoLinha"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="valorDominioForm.valorDominioCombo.admTipoLinhaSimplVOArray"/>
<bean:define id="aTabelaDominio"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="valorDominioForm.valorDominioCombo.admTabelaDominioVOArray"/>

<head>
    <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">        
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>

    <script language="javaScript">
        function valida() 
        {
            if (trim(document.getElementById("dominioValorIncluiVO.nmDominio").value) == "")
            {
                alert("Valor Domínio é um campo obrigatório, favor preencher.");
                document.getElementById("dominioValorIncluiVO.nmDominio").focus();
                return false;
                
            }else if(document.getElementById("dominioValorIncluiVO.idTabelaDominio").selectedIndex == 0)
            {
                alert("Selecione Tabela.");
                return false;
            
            } 

            else if(document.getElementById("operadoraArrayAssoc").options.length == 0)
            {
                alert("É necessário selecionar Operadora.");
                return false;
                
            }else if(document.getElementById("tipoLinhaArrayAssoc").options.length == 0)
            {
                alert("É necessário selecionar Tipo Linha.");
                return false;
                
            }
            
        return true;
            
        }

        function persisteValorDominio() 
        {
            if(valida())
            {
                for ( i = 0; i < document.getElementById("operadoraArrayAssoc").options.length; i++ )
                    document.getElementById("operadoraArrayAssoc").options[i].selected = true;

                for ( i = 0; i < document.getElementById("tipoLinhaArrayAssoc").options.length; i++ )
                    document.getElementById("tipoLinhaArrayAssoc").options[i].selected = true;
            
                document.forms[0].action = 'incluiValorDominio.do';
                parent.parent.mostrar_div();
                document.forms[0].submit();
            }
        }
        
        
        function transfereSelecaoLista(listaOrigem, listaDestino)
        {
            var i;

            for(i = 0; i<listaOrigem.options.length; i++)
                if(listaOrigem.options[i].selected)
                    listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                    
            for(i = listaOrigem.options.length-1; i>=0; i--)
                if(listaOrigem.options[i].selected)
                    listaOrigem.options[i] = null;
        }
            
            
        function listaEnter(ev)
        {
            if(ev.keyCode == 13)
                listaDominio();
            else
                return false;    
        }
        

    </script>

</head>


<body>

    <script>
        document.body.style.backgroundColor = '#ededed';
    </script>     

    <html:form action="incluiValorDominio" target="_parent" onsubmit="return false;">  
        <!-- html:hidden name="FormValorDominio" property="dominioValorIncluiVO.idDominio"  /-->   
            <table cellspacing="1" cellpadding="1" height="20" width="100%" align="center" border="0">
                    <tr>
                        <td colspan="3"><font color="red">*</font><netui:label value="Valor Domínio:" styleClass="tblEstatica_campoNome"/></td>
                          <td><html:text name="FormValorDominio" property="dominioValorIncluiVO.nmDominio" style="width:360px" styleClass="input" maxlength="200" onkeypress="listaEnter(event);"/>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<html:checkbox name="FormValorDominio" property="dominioValorIncluiVO.inDisponibilidade" value="1" styleClass="radio"/>Disponível
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3"><font color="red">*</font><netui:label value="Tabela:" styleClass="tblEstatica_campoNome"/></td>
                        <td>
                                <html:select name="FormValorDominio" property="dominioValorIncluiVO.idTabelaDominio">
                                    <html:option value="-1">-- Selecione --</html:option>
                                    <html:options collection="aTabelaDominio"  labelProperty="nmTabelaDominio" property="idTabelaDominio"/>
                                </html:select>
                        </td>
                    </tr>
            </table>
            <br>
            <table cellspacing="1" cellpadding="1" height="40" width="100%" align="center" border="0">
                    <tr>
                        <td width="310" align="center">
                           Operadora Existente<br>
                            <html:select name="FormValorDominio" property="operadoraArray" multiple="true" style="width:230px;" size="7" ondblclick="transfereSelecaoLista(document.forms[0].operadoraArray, document.getElementById('operadoraArrayAssoc'));">
                                <html:options collection="aOperadora" property="idUFOperadora" labelProperty="dsUFOperadora" /> 
                            </html:select>
                        </td>
                        <td width="70" align="center" valign="bottom">
                            <img class="button" id="btRightSimp" onmouseup="transfereSelecaoLista(document.forms[0].operadoraArray, document.getElementById('operadoraArrayAssoc'));" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_rightaln_over.gif" border="0"/><br><br>
                            <img class="button" id="btLeftSimp" onmouseup="transfereSelecaoLista(document.getElementById('operadoraArrayAssoc'), document.forms[0].operadoraArray);"src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_leftaln_over.gif" border="0"/>
                        </td>
                        <td width="310" align="center" >
                           <font color="red">*</font>Operadora Associado<br>
                            <html:select name="FormValorDominio" property="operadoraArrayAssoc" multiple="true" style="width:230px;" size="7" ondblclick="transfereSelecaoLista(document.getElementById('operadoraArrayAssoc'), document.forms[0].operadoraArray);">
                                
                            </html:select>
                        </td>
                    </tr>
                    <tr>
                        <td width="310" align="center">
                           Tipo Linha Existente<br>
                            <html:select name="FormValorDominio" property="tipoLinhaArray" multiple="true" style="width:230px;" size="7" ondblclick="transfereSelecaoLista(document.forms[0].tipoLinhaArray, document.getElementById('tipoLinhaArrayAssoc'));">
                                <html:options collection="aTipoLinha" property="idTipoLinha" labelProperty="dsTipoLinha" /> 
                            </html:select>
                        </td>
                        <td width="70" align="center" valign="bottom">
                            <img class="button" id="btRightSimp" href="javascript:transfereSelecaoLista(document.forms[0].tipoLinhaArray, document.getElementById('tipoLinhaArrayAssoc'));" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" border="0"/><br><br>
                            <img class="button" id="btLeftSimp" href="javascript:transfereSelecaoLista(document.getElementById('tipoLinhaArrayAssoc'), document.forms[0].tipoLinhaArray);"src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" border="0"/>
                        </td>
                        <td width="310" align="center" >
                           <font color="red">*</font>Tipo Linha Associado<br>
                            <html:select name="FormValorDominio" property="tipoLinhaArrayAssoc" multiple="true" style="width:230px;" size="7" ondblclick="transfereSelecaoLista(document.getElementById('tipoLinhaArrayAssoc'), document.forms[0].tipoLinhaArray);"></html:select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="6" align="right"><br>
                            <img hspace="5" style="border:none;cursor:hand;"  onclick="persisteValorDominio();" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_salvar_over.gif'"/>
                        </td>
                    </tr>
            </table>
   </html:form>
    <script language="javascript">
        parent.parent.oculta_div();
    </script>
</body>