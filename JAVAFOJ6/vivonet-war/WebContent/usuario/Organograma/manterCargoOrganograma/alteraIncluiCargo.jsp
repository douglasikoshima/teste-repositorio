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
  
<bean:define id="ManterCargoForm" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterCargoForm"/>
<bean:define id="ListaCargos"     name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterCargoForm.listaCargoVO"/>

<head>
    <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">        
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>

    <script language="javaScript">
        function valida() {
            if (trim(document.forms[0].nmCargo.value) == ""){
                alert("Favor preencher o campo!");
                return(false);
            } else {
                return(true);
            }
        }

        function salvaCargo2() {		
            document.forms[0].nmCargo.value = trim(document.forms[0].nmCargo.value);
            if(valida()) {
                var action = "salvaCargo.do";
                document.forms[0].action = action;
                parent.mostrar_div();
                document.forms[0].submit();
            }
        }

        function cancelar() {
            var action = "limpaCargo.do";
            document.forms[0].action = action;
            parent.mostrar_div();
            document.forms[0].submit();
        }

        </script>

</head>

<body onload="document.forms[0].nmCargo.select();document.forms[0].nmCargo.focus()">    
    <script>
        document.body.style.backgroundColor = '#ededed';
    </script>     
    
    <acesso:controlHiddenItem nomeIdentificador="usu_aicargo_verpagina">
    <form action="<%=request.getContextPath()%>/usuario/Organograma/manterCargoOrganograma/salvaCargo.do"  id="salvaCargo" name="salvaCargo" method="POST" target="_parent" onSubmit="return false;">    
        <html:hidden property="idCargo" name="ManterCargoForm"/>
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
            <table cellspacing="0" cellpadding="0" height="60">
                <tr> 
                    <td style="text-indent:6px;" width="78">
                        <b>Cargo:</b>
                    </td>
                    <td width="402">
                        <html:text style="width:400px;" name="ManterCargoForm" property="nmCargo" maxlength="254"/>
                    </td>
                </tr>
                <tr><td height="10"></td></tr>
                <tr>
                    <td colspan="2" align="right" width="780" style="padding-right:20px;">
                    <acesso:controlHiddenItem nomeIdentificador="usu_aicargo_gravar">
                        <img hspace="5" style="border:none;cursor:hand;" onclick="salvaCargo2();" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_salvar_over.gif'"/>
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
