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

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">

<netui-template:setAttribute name="title" value="Manutenção de Campanhas"/>
<netui-template:setAttribute name="modulo" value="VOL/TAV"/>

<netui-template:section name="headerSection">


<script type="text/javascript" language="javascript">
    
        function cancelar() {
            document.forms[0].action = 'cancelar.do';
            document.forms[0].submit();   
        }

        function controleTela() {
            if(document.forms[0].operacao.value == "ALTERACAO") {
                document.forms[0].nomeParceiro.readOnly = true;
                document.forms[0].menupai.disabled      = true;
            } else {
                document.forms[0].nomeParceiro.readOnly = false;
                document.forms[0].menupai.disabled = false;
            }
        }


        function manterParceiro() {
            var f = document.forms[0];
            if (validarFormulario()) {
                //document.forms[0].action = 'manterParceiro.do';
                document.forms[0].submit();     
            }
        }
        
        function closeWindow() {
            document.forms[0].action = 'begin.do';
            document.forms[0].submit();     
        }        
        

        function validarFormulario() {

            if (trim($F('nomeParceiro'))=="") {
                alert("Campo Nome do Parceiro é de preenchimento obrigatório");
                $('nomeParceiro').focus();
                return false;
            }        
            
            if (trim($F('menupai'))=="") {
                alert('Favor selecionar um item de Menu.');
                return false; 
            }
            
            if (trim($F('contato'))=="") {
                alert('Favor selecionar o Nome da Folha.');
                return false; 
            }   
            
            if (trim($F('urlfinal'))=="") {
                alert("Campo URL Final é de preenchimento obrigatório.");
                $('urlfinal').focus();
                return false;
            }                         
            
            if (trim($F('ipparceiro'))=="") {
                alert("Campo IP Parceiro é de preenchimento obrigatório.");
                $('ipparceiro').focus();
                return false;
            }               
            
            return true;
        }


    </script>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js" ></script>
    <script type="text/javascript" language="javascript" src="/FrontOfficeWeb/resources/scripts/calendar.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js" ></script>
    <script type="text/javascript" language="javascript" src="/FrontOfficeWeb/resources/scripts/vivoval.js" ></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/controleEventos.js"></script>    

<bean:define id="Form"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="parceiroSDPForm" type="VOLTAV.parceiroSDP.ParceiroSDPController.ParceiroSDPForm"/>
<bean:define id="folhas" name="Form" property="folhaContato" />    
<bean:define id="menus" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="comboSubSistema.itemMenuVOArray"/>    
    
</netui-template:section>

<netui-template:section name="bodySection">

<jsp:include page="/resources/menus/MenuPrincipal.jsp" />
<div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>

<vivo:sessao id="qdMain" height="468" width="790" label="Parceiro" operacoes="Manutenção SDP" scroll="no">
    
<form action="manterParceiro.do" name="parceiroSDPForm" enctype="multipart/form-data" method="post" onSubmit="return false">

<html:hidden name="Form" property="operacao" styleId="operacao" />
<html:hidden name="Form" property="menu" styleId="menu" />

<table cellpadding="2" cellspacing="1" style="margin-left:35px;">
    <tr>
        <td align="right" nowrap>Nome do Parceiro:</td>
        <td colspan="4">
             <html:text name="Form" property="nomeParceiro" styleId="nome" maxlength="50" style="width:550px;margin-left:3px;" />
        </td>
    </tr>
    <tr>
        <td align="right" nowrap>Menu</td>
        <td colspan="4">
            <html:select name="Form" property="menupai" styleId="menupai" style="margin-left:3px;width:235px">
                <html:option value="">-- Selecione --</html:option>
                <html:options collection="menus" property="idItemMenu" labelProperty="nmMenu" />
             </html:select>
        </td>
    </tr>
    <tr>
        <td align="right" nowrap>Nome da folha</td>
        <td colspan="4">
             <html:select name="Form" property="contato" styleId="contato" style="margin-left:3px;width:235px">
                <html:option value="">-- Selecione --</html:option>
                <html:options collection="folhas" property="idcontato" labelProperty="nmcontato"/>
             </html:select>
        </td>
    </tr>    
    <tr>
        <td align="right" nowrap>IP Parceiro</td>
        <td colspan="4">
             <html:text name="Form" property="ipparceiro" styleId="ipparceiro" maxlength="15" style="width:550px;margin-left:3px;" />
        </td>
    </tr>    
    <tr>
        <td align="right" nowrap>URL final</td>
        <td colspan="4">
             <html:text name="Form" property="urlfinal" styleId="urlfinal" maxlength="250" style="width:550px;margin-left:3px;" />
        </td>
    </tr>        
</table>
    
<table cellpadding="2" cellspacing="1" align="right" style="margin-right:15px;">    
    <tr>
        <td colspan="5" valign="top" align="right">

            <img id="btCancelar"
                               value="Cancelar"
                               src="/FrontOfficeWeb/resources/images/bt_cancelar_nrml.gif"
                               rolloverImage="/FrontOfficeWeb/resources/images/bt_cancelar_over.gif"
							   onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_cancelar_over.gif'"
                               style="border:none;margin-right:4px;cursor:hand;"
                               onMouseUp="cancelar()" 
							   onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_cancelar_nrml.gif'"/>
							      							   

            <img id="btGravar"
                               value="Gravar"
                               src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif"
							   onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_gravar_over.gif'"
                               rolloverImage="/FrontOfficeWeb/resources/images/bt_gravar_over.gif"
                               style="border:none;margin-right:4px;cursor:hand;"
                               onMouseUp="manterParceiro()" 
							   onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif'"/>
        </td>
    </tr>
</table>  

    <script language="JavaScript">
        controleTela();
    </script>

</form>        



<vivo:quadroFlutuante id="divInclusaoAlteracao"
                      idIframe="iframeInclusaoAlteracao"
                      width="763"                
                      height="520"
                      spacesTop="65"
                      spacesLeft="25"
                      display="none"
                      url="<%=request.getContextPath()%>"
                      label="Manter Questionário"
                      onclick="return false;"/>
        


</vivo:sessao>

<vivo:alert atributo="msgStatus" scope="request" afterFunction="closeWindow()" />
<vivo:alert atributo="msgErro" scope="request" />
</netui-template:section>


</netui-template:template>
