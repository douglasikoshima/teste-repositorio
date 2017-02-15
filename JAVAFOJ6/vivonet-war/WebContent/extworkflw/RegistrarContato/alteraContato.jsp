<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<head>

    <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
    

    <script language="javaScript">
       
        function salvar(tipo){
	        if (document.getElementById("dsContato").value == ""){
						alert('Favor preencher o campo Descrição!');
	        }else{            
	          document.forms[0].action="salvarContato.do?tipo="+tipo;
	          document.forms[0].submit();
	        }
        }
        
    </script>

</head>
<body style="background-color:#E3ECF4;">

        <html:form action="salvarContato" target="_parent">    
            <table border="0" cellspacing="10" cellpadding="0">
                <tr>
                    <html:hidden property="idPessoa"/> 
                    <html:hidden name="abaContato" property="idComunicacao" styleId="idContato"/>
                    <td><b>Tipo:</b></td>
                    <td>
                        <html:text name="abaContato" property="contato.tipoComunicacaoVO.dsTipoComunicacao" size="70" styleId="dsContato" readonly="true"/>
                    </td>
                </tr>             
                <tr> 
                    <td><b>DescriÃ§Ã£o:</b></td>
                    <td>
                        <html:text name="abaContato" property="contato.dsContato" size="70" maxlength="255" styleId="dsTipoComunicacao"/>                        
                    </td>                    
                </tr>                
                <tr>
                    <td  align="right" colspan="2">
                        <img style="border:0px;" onclick="salvar('alteracao')" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif"/>
                    </td>
                </tr>
            </table>                   
       </html:form>