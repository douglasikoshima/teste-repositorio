<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<bean:define id="carteiraPFForm" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="carteiraPFForm" />

<html>
    <head>
    <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>    
    <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js"></script>  
    </head>
    <body rightmargin="0" leftmargin="0" topmargin="0">
    <acesso:controlHiddenItem nomeIdentificador="cli_lcpf_verpagina">
    <form id="fCarteiraPF" action="loadCarteiraPF.do"> 
        
        <table width="400" class="tbl_bgBlue" border="0" cellspacing="1" cellpadding="1" align="center" > 
            <tr> 
                <td width="500" class="tbl_titulo">Detalhe Carteirização (Não-Corporativo)</td>
            </tr> 
			<tr> 
				<td> 
					<table width="500" border="0" cellspacing="1" cellpadding="0"  > 
						<tr> 
							<td width="250" align="right" class="td">Consultor de Relacionamento:</td>
							<td width="465"><html:text name="carteiraPFForm" property="lupaCarteira.nmNome" styleClass="textfield" size="40" readonly="true"/></td>
						</tr> 
						<tr> 
							<td align="right" class="td">Telefone de Contato:
							<td><html:text name="carteiraPFForm" property="lupaCarteira.nrTelefone" styleClass="textfield" size="40" readonly="true"/></td> 
						</tr> 
						<tr> 
							<td align="right" class="td">Celular:</td>
							<td><html:text name="carteiraPFForm" property="lupaCarteira.nrCelular" styleClass="textfield" size="40" readonly="true"/></td> 
						</tr> 
						<tr> 
							<td align="right" class="td">E-mail:</td>
							<td><html:text name="carteiraPFForm" property="lupaCarteira.nmEmail" styleClass="textfield" size="40" readonly="true"/></td> 
						</tr> 
					</table> 
				</td> 
			</tr> 
			<tr> 
                <td height="5"></td>
            </tr>
            <tr>
                <td><img src="/FrontOfficeWeb/resources/images/pixel_azul.gif" height="1" width="100%"></td>
            </tr> 
        </table> 
    </form> 
    
    <script>
        if('<bean:write name="carteiraPFForm" property="inVazio"/>' == 'TRUE'){
            alert('Cliente não é atendido por um Gestor de Relacionamento!');
            top.frameApp.fechaLupa();
        }
    </script>
    
    </acesso:controlHiddenItem>
    </body>
</html>
