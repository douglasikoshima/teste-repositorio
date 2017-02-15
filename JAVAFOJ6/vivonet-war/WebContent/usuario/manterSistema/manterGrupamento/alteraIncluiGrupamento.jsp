<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<bean:define id="FormManterGrupam" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterGrupamForm"/>
<bean:define id="grupamento"       name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterGrupamForm.grupamento"/>
<bean:define id="aGrupamentos"     name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterGrupamForm.listaGrupamento"/>

<head>
    <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">        
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>

    <script language="javaScript">
        function valida() {
            if (trim(document.forms[0].dsGrupamento.value) == ""){
                alert("Favor preencher o campo!");
                return(false);
            } else {
                return(true);
            }
        }

        function salvaGrupamento() {
            if(valida()) {
                var action = 'salvaGrupam.do';
                document.forms[0].action = action;
                parent.mostrar_div();
                document.forms[0].submit();
            }
        }
        
        function limpaCampo()
        {
            var grupamento = '<%=request.getParameter("operacao")%>';
            if(grupamento == 'incluir')
            {
                document.forms[0].dsGrupamento.value = '';
            }
        }

        function cancelar(){
        
        <%if(request.getParameter("operacao") != null && request.getParameter("operacao").equals("incluir")) {%>
            top.divIncluiGrupamento.style.display='none';
        <%}else{%>
            top.divAlteraGrupamento.style.display='none';
        <%}%>
            
        }
        </script>

</head>

<body>
    <acesso:controlHiddenItem nomeIdentificador="usu_aigr_verpagina">
    <form action="salvaGrupam.do" target="_parent" onSubmit="return false;" method="post">    
        <html:hidden name="FormManterGrupam" property="idGrupamento" />

        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
            <table cellspacing="0" cellpadding="0" height="60">
                <tr> 
                    <td style="text-indent:6px;" width="78">
                        <b>Grupamento:</b>
                    </td>
                    <td width="402">
                        <html:text name="FormManterGrupam" property="dsGrupamento" style="width:400px;" maxlength="60"/>
                    </td>
                </tr>
                <tr><td height="10"></td></tr>
                <tr>
                    <td colspan="2" align="right" width="780" style="padding-right:20px;">
                        <acesso:controlHiddenItem nomeIdentificador="usu_aigr_gravar">
                            <img hspace="5" onclick="salvaGrupamento();" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_salvar_over.gif'" style="border:none;cursor:hand;"/>
                        </acesso:controlHiddenItem>
                    </td>
                </tr>
            </table>
            
    <script language="javascript" for="window" event="onload">        
        parent.oculta_div();
        document.body.style.backgroundColor = '#ededed';
        <%if(request.getParameter("operacao") != null && request.getParameter("operacao").equals("incluir")) {%>
            document.forms[0].dsGrupamento.value = '';
        <%}%>
    </script>
            
   </form>
   </acesso:controlHiddenItem>
</body>