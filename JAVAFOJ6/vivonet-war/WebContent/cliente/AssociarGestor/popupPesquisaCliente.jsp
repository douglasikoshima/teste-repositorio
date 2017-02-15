<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html>

    <bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="grupoCRIForm"/>
    
    <head>
    
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">        
        <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>
        
        <SCRIPT>        
        
            function submitPesquisa(obj) {                
                if(document.getElementById('tipoPesquisa').value=='RAZAO'){
                    if ((window.event && event.keyCode == '13') ||
                         obj.id == "btnEnvia"){
                        if(trim(document.forms[0].nome.value) == ""){
                            alert("Favor digitar o campo Razão Social!");
                            setTimeout('document.forms[0].nome.focus()',300);
                            return false;
                        }else{
                            document.getElementById('nome').value         = trim(document.getElementById('nome').value);
                            document.getElementById('nomeCompleto').value = document.getElementById('nome').value
                            parent.dvResultados.style.display     = 'block';
                            document.forms[0].target              = 'ifrmPesqNomeResultados';
                            parent.dvPesquisaNome.style.display   = 'none';
                            top.frameApp.mostrar_div();
                            document.forms[0].submit();
                        }
                    }
                }else{
                    if(trim(document.forms[0].nome.value) == ""){
                        alert("Favor digitar o campo Nome!");
                        setTimeout('document.forms[0].nome.focus()',300);
                        return false;
                    }else if(trim(document.forms[0].sobrenome.value) == ""){
                        alert("Favor digitar o campo Sobrenome!");
                        setTimeout('document.forms[0].sobrenome.focus()',300);
                        return false;
                    }else{
                        document.getElementById('nome').value         = trim(document.getElementById('nome').value);
                        document.getElementById('nmMeio').value       = trim(document.getElementById('nmMeio').value);
                        document.getElementById('sobrenome').value    = trim(document.getElementById('sobrenome').value);
                        
                        if(document.getElementById('nmMeio').value==''){
                            document.getElementById('nomeCompleto').value = document.getElementById('nome').value + ' ' + document.getElementById('sobrenome').value;
                        }else{
                            document.getElementById('nomeCompleto').value = document.getElementById('nome').value + ' ' + document.getElementById('nmMeio').value + ' ' + document.getElementById('sobrenome').value;
                        }
                        
                        parent.dvResultados.style.display             = 'block';
                        document.forms[0].target                      = 'ifrmPesqNomeResultados';
                        parent.dvPesquisaNome.style.display           = 'none';
                        top.frameApp.mostrar_div();
                        document.forms[0].submit();
                        
                    }
                }
            }        
        
        </SCRIPT>
        
        <script for="window" event="onload">
            document.forms[0].nome.value = '';
            if(document.forms[0].nmMeio){
                document.forms[0].nmMeio.value = '';
                document.forms[0].sobrenome.value = '';
            }
            document.body.style.backgroundColor = '#ededed';
            top.frameApp.oculta_div();
            document.forms[0].nome.focus();
        </script>
    
    </head>
    
    <body leftmargin="10">
        
        <form action="pesquisarCRI.do" method="post" name="grupoCRIForm">
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="10"></div>
            <table cellpadding="0" cellspacing="0">
                <html:hidden name="Form" property="grupoCRI.pesquisa.filtro.sgTipoPesquisa" styleId="tipoPesquisa"/>
                <html:hidden name="Form" property="grupoCRI.pesquisa.filtro.nmCompleto"     styleId="nomeCompleto"/>
                <logic:equal name="Form" property="grupoCRI.pesquisa.filtro.sgTipoPesquisa" value="NOME">
                    <tr>
                        <td width="20">&nbsp;&nbsp;Nome:</td>
                        <td width="130"><html:text name="Form" property="grupoCRI.pesquisa.filtro.dsValor"      style="width:120px;" maxlength="84" styleId="nome"/></td>
                        <td width="75">Nome do Meio:</td>
                        <td width="130"><html:text name="Form" property="grupoCRI.pesquisa.filtro.dsNomeDoMeio" style="width:120px;" maxlength="84" styleId="nmMeio"/></td>
                        <td width="30">Sobrenome:</td>
                        <td width="120"><html:text name="Form" property="grupoCRI.pesquisa.filtro.dsSobreNome"  style="width:120px;" maxlength="84" styleId="sobrenome"/></td>
                    </tr>
                </logic:equal>
                <logic:equal name="Form" property="grupoCRI.pesquisa.filtro.sgTipoPesquisa" value="RAZAO">
                    <tr>
                        <td width="100">&nbsp;&nbsp;Razão Social:</td>
                        <td width="460"><html:text name="Form" property="grupoCRI.pesquisa.filtro.dsValor"      style="width:450px;" maxlength="254" styleId="nome" onkeypress="return submitPesquisa(this)"/></td>
                    </tr>
                </logic:equal>
                <tr>
                    <td colspan="6" align="right"><div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="10"></div>
                        <img src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif" style="border:none;" onClick="submitPesquisa(this); return false" id="btnEnvia"/>
                    </td>
                </tr>
            </table>
        
        </form>
    </body>

</html>