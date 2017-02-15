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

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="campanhasForm" type="VOLTAV.campanhas.CampanhasController.CampanhasForm"/>

<html>

<head>

      <script type="text/javascript" language="javascript">
    <% String salvarQuestionario = (String)request.getAttribute("salvarQuestionario");
        if ("true".equals(salvarQuestionario)){%>       
            parent.$('divInclusaoAlteracao').style.display = 'none';

        <%}
    %>
    
    onload = function() {
    if( window.top.frameApp.dvAnimarAguarde != null ) window.top.frameApp.stopAnimation();
    
        }   
    function closeWindow() {
            parent.$('divInclusaoAlteracao').style.display = 'none';
            top.frameApp.location.href = "begin.do";
        }

        function validarFormCampanha() {

            if (trim($F('txtExplicativo'))=="") {
                alert("Favor preencher o campo texto explicativo.");
                $('txtExplicativo').focus();
                return false; }
            if (trim($F('dsPergunta'))=="") {
                alert("Favor preencher o campo texto da pergunta.");
                $('dsPergunta').focus();
                return false; }
            if (trim($F('dsRespostaCorreta'))=="") {
                alert("Favor preencher o campo resposta correta.");
                $('dsRespostaCorreta').focus();
                return false; }          
            if (trim($F('dsRespostaIncorreta'))=="") {
                alert("Favor preencher o campo resposta incorreta.");
                $('dsRespostaIncorreta').focus();
                return false; }
                      
            return true;
        }

        function salvarQuestionario() {
        
        
            var f = document.forms[0];
            if (validarFormCampanha()) {
         
    
                 f.action  = "salvarQuestionarioCampanha.do";
         
                 f.submit();
           }
        }

  
    </script>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js" ></script>
    <script type="text/javascript" language="javascript" src="/FrontOfficeWeb/resources/scripts/calendar.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js" ></script>
    <script type="text/javascript" language="javascript" src="/FrontOfficeWeb/resources/scripts/vivoval.js" ></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/controleEventos.js"></script>
    <style type="text/css">

        textarea {
            font-family: Tahoma, Arial, Helvetica, sans-serif;
            font-size: 11px;
            color: #006699;
            background-color: #ffffff;
            border: 1px solid #000000;
            text-indent: 3px;
        }

    </style>

</head>

<body style="background-color:#ededed;">

<div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>

<form action="salvarQuestionarioCampanha.do" name="campanhasForm" enctype="multipart/form-data" method="post" onSubmit="return false">

<html:hidden name="Form" property="campanhaVO.idCampanha" styleId="idCampanha" />

<table cellpadding="2" cellspacing="1" style="margin:10px;">
    
      <tr>
        <td align="right" valign="top">Texto explicativo:</td>
        <td colspan="4" valign="top">
             <html:textarea name="Form" property="campanhaVO.txtExplicativo"
                                        styleId="txtExplicativo"
                                        onkeyup="limitaQtdeCaracteres(this, 300)"
                                        onblur="limitaQtdeCaracteres(this, 300)"
                                        style="margin-left:3px;width:550px ;height:60px;" />
        </td>
    </tr>
    
    <tr>
        <td align="right" valign="top">Texto da pergunta:</td>
        <td colspan="4" valign="top">
             <html:textarea name="Form" property="campanhaVO.dsPergunta"
                                        styleId="dsPergunta"
                                        onkeyup="limitaQtdeCaracteres(this, 200)"
                                        onblur="limitaQtdeCaracteres(this, 200)"
                                        style="margin-left:3px;width:550px ;height:60px;" />
        </td>
    </tr>
    
    <tr>
        <td align="right" nowrap>Resposta correta:</td>
        <td colspan="4">
             <html:text name="Form" property="campanhaVO.dsRespostaCorreta" styleId="dsRespostaCorreta" maxlength="50" style="width:550px;margin-left:3px;" />
        </td>
    </tr>
    
      <tr>
        <td align="right" nowrap>Resposta incorreta:</td>
        <td colspan="4"> 
             <html:text name="Form" property="campanhaVO.dsRespostaIncorreta" styleId="dsRespostaIncorreta" maxlength="50" style="width:550px;margin-left:3px;" />
        </td>
    </tr>
      
    <tr>
    </tr>
    <tr>
        <td colspan="5" valign="top">
        </td>
    </tr>
    <tr>
        <td colspan="5" valign="top" align="right">

            <img id="btCancelar"
                               value="Cancelar"
                               src="/FrontOfficeWeb/resources/images/bt_cancelar_nrml.gif"
                               rolloverImage="/FrontOfficeWeb/resources/images/bt_cancelar_over.gif"
                               style="border:none;margin-right:4px;"
                               onMouseUp="parent.$('divInclusaoAlteracao').style.display = 'none';return false;" />

            <img id="btGravar"
                               value="Gravar"
                               src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif"
                               rolloverImage="/FrontOfficeWeb/resources/images/bt_gravar_over.gif"
                               style="border:none;margin-right:4px;"
                               onMouseUp="salvarQuestionario()" />
        </td>
    </tr>
</table>

</form>

<vivo:alert atributo="msgStatus" scope="request" afterFunction="closeWindow()" />
<vivo:alert atributo="msgErro" scope="request" />

</body>
</html>