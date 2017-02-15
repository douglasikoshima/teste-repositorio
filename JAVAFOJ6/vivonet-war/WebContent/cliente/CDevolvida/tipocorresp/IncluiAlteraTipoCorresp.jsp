<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="tipoCorrespForm" />
<bean:define id="TipoCorresp" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="tipoCorrespForm.listaTipoCorrespVO"/>

<head>
    <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">        
    <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js" ></script>
    <script language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    
    <script language="javaScript">
        
        function valida() {
            if (document.forms[0].sigla.value == ""){
                alert("Favor preencher o campo Sigla!");
                return(false);
            }else if (document.forms[0].descricao.value == ""){
                alert("Favor preencher o campo Tipo!");
                return(false);
            } else {
                return(true);
            }
        }
    
        function salvarTipoCorresp() {
            if(valida()) {
                var action   = "<%=request.getContextPath()%>/cliente/CDevolvida/tipocorresp/salvartipocorresp.do"
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
                top.frameApp.mostrar_div();
                document.forms[0].submit();
            }
        }
        
    </script>

</head>

<body>
<acesso:controlHiddenItem nomeIdentificador="cli_iatc_verpagina">
    <form action="<%=request.getContextPath()%>/salvartipocorresp.do" name="salvartipocorresp" target="_parent" method="post"> 
    <html:hidden name="Form" property="id"/>    
        <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" width="1" height="5"></div>
        
            <table cellspacing="0" cellpadding="0">
                <tr><td colspan="2" height="10"></td></tr>
                <tr> 
                    <td style="text-indent:6px;" width="48">
                        Sigla:
                    </td>
                    <td width="432">
                        <html:text name="Form" property="sigla" maxlength="6" title="sigla" style="width:50px;"/>
                    </td>
                </tr>             
                <tr> 
                    <td style="text-indent:4px;" height="30">
                        Tipo:
                    </td>
                    <td>
                        <html:text name="Form" property="descricao" maxlength="50" title="descricao" size="50"/>
                        <logic:equal value="UPDATE" name="operacao" scope="request">
                            <td style="text-indent:4px;" height="30">Disponibilidade:</td>
                            <td>
                                <html:select name="Form" property="inDisponibilidade">
                                    <html:option value="1">Sim</html:option>
                                    <html:option value="0">Não</html:option>
                                </html:select>
                            </td>
                        </logic:equal>
                    </td>                    
                </tr>
                <tr><td height="10"></td></tr>
                <tr>
                    <td colspan="4" align="right">
                    <acesso:controlHiddenItem nomeIdentificador="cli_iatc_salvar">
                        <img hspace="5"
						     style="border:none;cursor:hand;"
							 onClick="salvarTipoCorresp(); return false"
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