<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<bean:define id="Tipos" name="abaContato" property="listaTipos.tipoComunicacaoVOArray"/>
<head>
        <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>

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
                    <td valign="top" width="80">
                        <b>Tipo:</b>
                    </td>
                    <td>
                        <html:select style="width:130px;text-indent:3px;" property="idTipoSelecionado">
                            <html:options collection="Tipos" property="idTipoComunicacao" labelProperty="dsTipoComunicacao"/>
                        </html:select>
                    </td>
                </tr>             
                <tr> 
                    <td valign="top">                    
                        <b>DescriÃ§Ã£o:</b>
                    </td>
                    <td>
                        <html:text name="abaContato" property="contato.dsContato" maxlength="255" style="width:300px;" styleId="dsContato"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" colspan="2">
                        <img style="border:none;" onclick="salvar('novo')" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif"/>
                    </td>
                </tr>
            </table>                   
       </html:form>
