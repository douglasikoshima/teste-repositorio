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

    <script language="javaScript">
        function valida() 
        {
            if (trim(document.getElementById("dominioVO.nmDominio").value) == "")
            {
                alert("Valor Domínio é um campo obrigatório, favor preencher.");
                return false;
                
            }else if(document.getElementById("dominioVO.admUFOperadoraSimplVO.idUFOperadora").selectedIndex == 0)
            {
                alert("Selecione Operadora.");
                return false;
                
            }else if(document.getElementById("dominioVO.admTipoLinhaSimplVO.idTipoLinha").selectedIndex == 0)
            {
                alert("Selecione Tipo Linha.");
                return false;
                
            }else if(document.getElementById("dominioVO.admTabelaDominioVO.idTabelaDominio").selectedIndex == 0)
            {
                alert("Selecione Tabela.");
                return false;
            
            } 
            
        return true;
            
        }

        function persisteValorDominio() 
        {
            if(valida())
            {
                document.forms[0].action = 'alteraValorDominio.do';
                parent.parent.mostrar_div();
                document.forms[0].submit();
            }
        }

    </script>
     <script language="javascript" for="window" event="onload">
        <!--              
            parent.parent.oculta_div();            
        -->
        </script> 
</head>

<body>
    <script>
        document.body.style.backgroundColor = '#ededed';
    </script>     
        
    <html:form action="alteraValorDominio" target="_parent" onsubmit="return false;">  
        <html:hidden name="FormValorDominio" property="dominioVO.idDominio"  />   
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
            <table cellspacing="1" cellpadding="1" height="60" align="center">
                    <tr>
                        <td ><font color="red">*</font><netui:label value="Valor Domínio:" styleClass="tblEstatica_campoNome"/></td>
                        <td colspan="5">
                            <html:text name="FormValorDominio" property="dominioVO.nmDominio" style="width:360px" styleClass="input" maxlength="200" onkeypress="listaEnter(event);"/>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<html:checkbox name="FormValorDominio" property="dominioVO.inDisponibilidade" value="1" styleClass="radio"/>Disponível
                        </td>
                    </tr>
                    <tr>
                        <td><font color="red">*</font><netui:label value="Domínio:" styleClass="tblEstatica_campoNome"/></td>
                        <td>
                            <html:select name="FormValorDominio" property="dominioVO.admTabelaDominioVO.idTabelaDominio" style="width:140px;" disabled="true">
                                <html:option value="-1">-- Selecione --</html:option>
                                <html:options collection="aTabelaDominio"  labelProperty="nmTabelaDominio" property="idTabelaDominio"/>
                            </html:select>
                        </td>
                        <td><font color="red">*</font><netui:label value="Operadora:" styleClass="tblEstatica_campoNome"/></td>
                        <td>
                            <html:select name="FormValorDominio" property="dominioVO.admUFOperadoraSimplVO.idUFOperadora" style="width:140px;">
                                <html:option value="-1">-- Selecione --</html:option>
                                <html:options collection="aOperadora"  labelProperty="dsUFOperadora" property="idUFOperadora"/>
                            </html:select>
                        </td>
                        <td><font color="red">*</font><netui:label value="Tipo Linha:" styleClass="tblEstatica_campoNome"/></td>
                        <td>
                            <html:select name="FormValorDominio" property="dominioVO.admTipoLinhaSimplVO.idTipoLinha" style="width:140px;">
                                <html:option value="-1">-- Selecione --</html:option>
                                <html:options collection="aTipoLinha"  labelProperty="dsTipoLinha" property="idTipoLinha"/>
                            </html:select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="6" align="right">
                            <img hspace="5" style="border:none;cursor:hand;"  onclick="persisteValorDominio();" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_salvar_over.gif'"/>
                        </td>
                    </tr>
            </table>
   </html:form>    
</body>