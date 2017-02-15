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
<bean:define id="Form"                 name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaAssuntoForm" />
<bean:define id="aDisponibilidadeVO"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaAssuntoForm.listaAssuntoVO.disponibilidades.disponibilidadeVOArray" />

<head>

    <script language="javaScript">
        function valida() {
            if (document.getElementById("assunto").value == '')  {            
                alert("Favor preencher o campo Assunto!");
                return(false);
            }
                salvarAssunto(true);
        }
    
        // Caso seja teclado Enter, ele não dispara o Form e manda pra valida();
        function verEnter() {
            if (window.event && event.keyCode == '13'){
                valida();
                return !(window.event && window.event.keyCode == 13);
                document.getElementById("assunto").setFocus();
            }
        }
    
        function salvarAssunto(boolean) { 
            if(boolean) {
                var action   = "salvarAssunto.do"
                var operacao = "<bean:write name="operacao" scope="request"/>";
                
                switch(operacao) {
                    case "INSERT":
                        action += "?operacao=INSERT";
                        break;
                        
                    case "UPDATE":
                        action += "?operacao=UPDATE&index=<bean:write name="index" scope="request"/>";
                        break;
                }             
                document.forms[0].action = action;
                document.forms[0].target = "executa";
                parent.mostrar_div();
                document.forms[0].submit();
            }
        }
        
        function mostraDisponibilidadeAssociada(){
            var disponibilidadeAssociada = "<bean:write name="Form" property="disponibilidadeAssociada"/>";
            if(disponibilidadeAssociada == 0)
                listaAssuntoForm.disponibilidadeSelecionado.options[disponibilidadeAssociada+1].selected = true;
        }
        
    </script>
    <script for="window" event="onload">
        parent.oculta_div();
    </script>

    <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">        
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/vivoval.js" ></script>

</head>

<body leftmargin="10" topmargin="10" rightmargin="10" bottommargin="10">
<acesso:controlHiddenItem nomeIdentificador="cli_iaa_verpagina">
        <form name="listaAssuntoForm" action="salvarAssunto.do" method="post" target="_parent" onKeyPress="return verEnter()">        
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
            <html:hidden name="Form" property="codigoAssunto" />
            
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="10"></div>
            
            <table width="480" border="0" cellspacing="0" cellpadding="0" align="center">
                <tr> 
                    <td width="90" style="text-indent:6px;" valign="top">Assunto:</td>
                    <td width="390"><html:text name="Form" property="assunto" maxlength="50" title="assunto" size="50" /><br><br></td>
                </tr>
                <tr> 
                    <td style="text-indent:6px;">Disponibilidade:</td>
                    <td>
                        <html:select name="Form" property="disponibilidadeSelecionado" styleClass="SELECT" style="width:50px">
                            <html:options collection="aDisponibilidadeVO" property="codigo" labelProperty="descricao" />
                        </html:select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="right">
                        <acesso:controlHiddenItem nomeIdentificador="cli_iaa_gravar">
                            <img hspace="10" style="border:0px;" onClick="valida();" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif"/>
                        </acesso:controlHiddenItem>
                    </td>
                </tr>
            </table>
  
            <script>
                mostraDisponibilidadeAssociada();
                document.body.style.backgroundColor = '#ededed';
            </script>                 
        </form>
</acesso:controlHiddenItem>
</body>
