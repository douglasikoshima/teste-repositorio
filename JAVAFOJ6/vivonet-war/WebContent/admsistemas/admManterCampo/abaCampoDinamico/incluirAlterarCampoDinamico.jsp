<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!--Define Form Bean -->
<bean:define id="FormCampo"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="campoDinamicoForm"/>

<!--Define Combos -->
<bean:define id="aClassificador"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="campoDinamicoForm.campoComboVO.admClassificadorCampoVOArray"/>
<bean:define id="aLayout"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="campoDinamicoForm.campoComboVO.admLayoutApresentacaoCampoVOArray"/>
<bean:define id="aTipoDado"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="campoDinamicoForm.campoComboVO.admTipoDadoCampoVOArray"/>
<bean:define id="aMascara"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="campoDinamicoForm.campoComboVO.admMascaraApresentacaoVOArray"/>

<head>
    <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">        
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <script language="javaScript">
        function valida() 
        {
            if (trim(document.getElementById("campoVO.nmCampo").value) == "")
            {
                alert("Nome Campo é um campo obrigatório, favor preencher.");
                return false;
                
            }else if(trim(document.getElementById("campoVO.nrTamanho").value) == "")
            {
                alert("Tamanho é um campo obrigatório, favor preencher.");
                return false;
                
            }else if(document.getElementById("campoVO.admClassificadorCampoVO.idClassificadorCampo").selectedIndex == 0)
            {
                alert("Selecione um Classificador.");
                return false;
            
            }else if(document.getElementById("campoVO.admMascaraApresentacaoVO.idMascaraApresentacao").selectedIndex == 0)
            {
                alert("Selecione uma Mascara de Apresentação.");
                return false;
            
            }else if(document.getElementById("campoVO.admLayoutApresentacaoCampoVO.idLayoutApresentacaoCampo").selectedIndex == 0)
            {
                alert("Selecione um Layout de Apresentação.");
                return false;
            
            }else if(document.getElementById("campoVO.admTipoDadoCampoVO.idTipoDadoCampo").selectedIndex == 0)
            {
                alert("Selecione o Tipo do Dado do Campo.");
                return false;
            }
             
           return true;
        }

        function persisteCampo() 
        {
            if(valida())
            {
                document.forms[0].action = 'incluiAlteraCampo.do';
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
        
    <html:form action="incluiAlteraCampo" target="_parent" onsubmit="return false;">  
        <html:hidden name="FormCampo" property="campoVO.idCampo"/>
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
            <table cellspacing="0" cellpadding="1" height="60">
                <tr>
                    <td align="left" width="20%">
                       <font color="red">*</font> <netui:label value="Nome do Campo:" styleClass="tblEstatica_campoNome"/>
                    </td>
                    <td align="left" width="25%" colspan="3">
                        <html:text name="FormCampo" property="campoVO.nmCampo" style="width:400px" styleClass="input" maxlength="254" />
                    </td>
                </tr>
                <tr>
                    <td align="left">
                       <font color="red">*</font> <netui:label value="Tamanho do Campo:" styleClass="tblEstatica_campoNome"/>
                    </td>
                    <td align="left">
                        <html:text name="FormCampo" property="campoVO.nrTamanho" style="width:70px" styleClass="input" maxlength="3" onkeyup="checaInteiro(this)"/>
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
                       <font color="red">*</font> <netui:label value="Classificador:" styleClass="tblEstatica_campoNome"/>
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
                       <font color="red">*</font> <netui:label value="Mascara Apres.:" styleClass="tblEstatica_campoNome"/>
                    </td>
                    <td align="left">
                        <html:select name="FormCampo" property="campoVO.admMascaraApresentacaoVO.idMascaraApresentacao" style="width:200px;" styleClass="SELECT">
                            <html:option value="-1">-- Selecione --</html:option>
                            <html:options collection="aMascara" property="idMascaraApresentacao" labelProperty="nmMascaraApresentacao" /> 
                        </html:select>
                    </td>
                    <td>
                      <font color="red">*</font>  <netui:label value="Layout Apresentação:" styleClass="tblEstatica_campoNome"/>
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
                      <font color="red">*</font>  <netui:label value="Tipo do Dado:" styleClass="tblEstatica_campoNome"/>
                    </td>
                    <td colspan="2">
                        <html:select name="FormCampo" property="campoVO.admTipoDadoCampoVO.idTipoDadoCampo" style="width:200px;" styleClass="SELECT">
                            <html:option value="-1">-- Selecione --</html:option>
                            <html:options collection="aTipoDado" property="idTipoDadoCampo" labelProperty="nmTipoDadoCampo" />
                        </html:select>
                    </td>
                    <td  align="right">
                        <img hspace="5" style="border:none;cursor:hand;"  onclick="persisteCampo();" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_salvar_over.gif'"/>
                    </td>
                </tr>
            </table>
   </html:form>    
</body>