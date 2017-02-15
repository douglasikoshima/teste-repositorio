<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/> 
  
<bean:define id="ManterUnidadeForm" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterUnidadeForm"/>


<head>
    <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">        
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>

    <script language="javaScript">
        function valida() {
            if (trim(document.forms[0].nmUnidade.value) == ""){
                alert("Favor preencher o campo!");
                return(false);
            } else {
                return(true);
            }
        }

        function salvaUnidade() {
            if(valida()) {
                var action = "salvaUnidade.do";
                document.forms[0].nmUnidade.value = trim(document.forms[0].nmUnidade.value);
                document.forms[0].action = action;
                parent.mostrar_div();
                document.forms[0].submit();
                document.forms[0].idUnidade.value = '';
                document.forms[0].nmUnidade.value = '';
            }
        }

        function cancelar() {
            var action = "limpaUnidade.do";
            document.forms[0].idUnidade.value = '';
            document.forms[0].nmUnidade.value = '';
            document.forms[0].action = action;
            parent.mostrar_div();
            document.forms[0].submit();
        }
 
        </script>

</head>

<body onload="document.forms[0].nmUnidade.select();document.forms[0].nmUnidade.focus()">
<acesso:controlHiddenItem nomeIdentificador="usu_altincuni_verpagina">    
    <script>
        document.body.style.backgroundColor = '#ededed';
    </script>     
    <form action="<%=request.getContextPath()%>/usuario/Organograma/manterUnidadeOrganizacao/salvaUnidade.do" id="salvaUnidade" name="salvaUnidade" onSubmit="return false;" method="POST" target="_parent" >      
        <html:hidden name="ManterUnidadeForm" property="idUnidade"/>
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
            <table cellspacing="0" cellpadding="0" height="60">
                <tr> 
                    <td style="text-indent:6px;" width="78">
                        <b>Departamento:</b>
                    </td>
                    <td width="402">
                        <html:text property="nmUnidade" style="width:350px;" name="ManterUnidadeForm" maxlength="254"/>
                    </td>
                </tr>
                <tr><td height="10"></td></tr>
                <tr>
                    <td colspan="2" align="right" width="780" style="padding-right:20px;">
                    <acesso:controlHiddenItem nomeIdentificador="usu_altincuni_gravar">
        			<img vspace="5" hspace="10" id="btSalvar1" onclick="salvaUnidade();" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_salvar_over.gif'" style="border:none;cursor:hand;"/>
                    </acesso:controlHiddenItem>
                    </td>
                </tr>
            </table>
   </form>
    <script language="javascript" for="window" event="onload">
    <!--   
        parent.oculta_div();
    -->
    </script> 
</acesso:controlHiddenItem>
</body>
