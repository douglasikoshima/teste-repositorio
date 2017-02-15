<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>

<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="motivoDevolucaoForm" />
<bean:define id="MotivoDevolucao" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="motivoDevolucaoForm.listaMotivoDevolucaoVO"/>

<head>

    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">        
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>

    <script language="javaScript">
        function valida() {
            if (document.forms[0].sigla.value == ""){
                alert("Favor preencher o campo Sigla!");
                return(false);
            }else if (document.forms[0].descricao.value == ""){
                alert("Favor preencher o campo Motivo!");
                return(false);
            } else {
                return(true);
            }
        }
    
        function salvarMotivo() {
            if(valida()) {
                var action   = "salvarmotivodevolucao.do"
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
        
    </script>
    <script for="window" event="onload">
        parent.oculta_div();
    </script>

</head>

<body>
    
	<acesso:controlHiddenItem nomeIdentificador="cli_iamd_verpagina">
    
	<form action="salvarmotivodevolucao" name="salvarmotivodevolucao" target="_parent" method="post">
    
    <logic:equal name="Form" property="inMsgRetorno" value="true">
        <script>
            alert('Motivo de devolução não pôde ser incluído/alterado pois já existe um motivo com essa descrição/sigla!');
        </script>
    </logic:equal> 
    
    <html:hidden name="Form" property="id"/>
        <table width="480" border="0" cellspacing="0" cellpadding="0">
            <tr><td height="20"></td></tr>
            <tr> 
                <td style="text-indent:6px;" height="10">Sigla:</td>
                <td>
                    <html:text name="Form" maxlength="6" property="sigla" title="sigla" size="60" style="width:40px;"/>
                </td>
            </tr>
            <tr>
                <td style="text-indent:4px;" height="30">Motivo:</td>
                <td>
                    <html:text name="Form" maxlength="50" property="descricao" title="descricao" size="50" />
                </td>
                <logic:equal value="UPDATE" name="operacao" scope="request">
                    <td style="text-indent:4px;" height="30">Disponibilidade:</td>
                    <td>
                        <html:select name="Form" property="inDisponibilidade">
                            <html:option value="1">Sim</html:option>
                            <html:option value="0">Não</html:option>
                        </html:select>
                    </td>
                </logic:equal>
            </tr>            
            <tr><td height="10"></td></tr>
            <tr>
                <td colspan="4" align="right">
					<acesso:controlHiddenItem nomeIdentificador="cli_iamd_salvar">
						<img style="border:0px;"
							 vspace="10"
							 onClick="salvarMotivo(); return false"
							 src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" />
					</acesso:controlHiddenItem>
                </td>
            </tr>
        </table>                   
   </form>
    
    <script>
        document.body.style.backgroundColor = '#ededed';
    </script>    
</acesso:controlHiddenItem>
</body>