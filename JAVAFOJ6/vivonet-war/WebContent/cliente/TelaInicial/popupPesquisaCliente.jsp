<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<acesso:controlInitEnv/>
<html>
    <head>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">        
        <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>
        <script>
            function submitPesquisa(obj) {        
                if(document.getElementById('tipoPesquisa').value=='razao'){
                  if ((window.event && event.keyCode == '13') ||
                        obj.id == "btnEnvia"){
                        if(trim(document.forms[0].nome.value) == ""){
                            alert("Favor digitar o campo Razão Social!");
                            /* crappy hack :-)  ...unico jeito q eu sei pra ter o foco depois de um alert */
                            setTimeout('document.forms[0].nome.focus()',300);
                            return false;
                        }else{
                            document.getElementById('nome').value = trim(document.getElementById('nome').value);
                            //top.frameApp.dvPesqNomeResultados.style.display = 'block';
                            //document.forms[0].target = 'ifrmPesqNomeResultados';
                            document.forms[0].action = "pesquisaCliente.do?tipoLista=clientes&pesquisa=razao";
                            parent.mostrar_div();
                            document.forms[0].method = "POST";
                            document.forms[0].submit();
                            //top.frameApp.dvPesqNome.style.display = 'none';  
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
                        document.getElementById('nome').value = trim(document.getElementById('nome').value);
                        document.getElementById('nmMeio').value = trim(document.getElementById('nmMeio').value);
                        document.getElementById('sobrenome').value = trim(document.getElementById('sobrenome').value);            
                        //top.frameApp.dvPesqNomeResultados.style.display = 'block';
                        //document.forms[0].target = 'ifrmPesqNomeResultados';
                        document.forms[0].action = "pesquisaCliente.do?tipoLista=clientes&pesquisa=nome";
                        //parent.mostrar_div();
                        document.forms[0].method = "POST";
                        document.forms[0].submit();
                        //top.frameApp.dvPesqNome.style.display = 'none';  
                    }
                }
            }
        </script>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
            <!--
                parent.oculta_div();
            -->
        </SCRIPT>
    </head>
    <body leftmargin="10">
        <bean:define id="FormPesquisa" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="pesquisaForm"/>
        <acesso:controlHiddenItem nomeIdentificador="cli_pppc_verpagina">
        <form id="myform" action="pesquisaCliente.do" name="pesquisaForm" method="post">
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="10"></div>
        <html:hidden property="tipoPesquisa" name="FormPesquisa" styleId="tipoPesquisa"/>
        <table cellpadding="0" cellspacing="0">
            <logic:equal name="FormPesquisa" property="tipoPesquisa" value="nome">
                <tr>
                    <td width="20">&nbsp;&nbsp;Nome:</td>
                    <td width="130"><html:text name="FormPesquisa" property="nome" style="width:120px;" maxlength="84" styleId="nome" onfocus="this"/></td>
                    <td width="75">Nome do Meio:</td>
                    <td width="130"><html:text name="FormPesquisa" property="nmMeio" style="width:120px;" maxlength="84" styleId="nmMeio"/></td>
                    <td width="30">Sobrenome:</td>
                    <td width="120"><html:text name="FormPesquisa" property="sobrenome" style="width:120px;" maxlength="84" styleId="sobrenome"/></td>
                </tr>
            </logic:equal>
            <logic:equal name="FormPesquisa" property="tipoPesquisa" value="razao">
                <tr>
                    <td width="100">&nbsp;&nbsp;Razão Social:</td>
                    <td width="460"><html:text name="FormPesquisa" property="nome" style="width:450px;" maxlength="254" styleId="nome" onkeypress="return submitPesquisa(this)"/></td>
                </tr>
            </logic:equal>
            <tr>
                <td colspan="6" align="right"><div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="10"></div>
                <acesso:controlHiddenItem nomeIdentificador="cli_pppc_pesquisar">
                    <img id="btnEnvia" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" style="border:none;" onClick="submitPesquisa(this);return false;"/>
                </acesso:controlHiddenItem>
                </td>
            </tr>
        </table>
        </form>
        <script>
            document.body.style.backgroundColor = '#ededed';
            top.frameApp.$('ti_comboPesquisa').options[0].selected = true;
            document.forms[0].nome.focus();
            top.frameApp.$('iframePopupTI').height = document.body.scrollHeight + 7;
        </script>
    </acesso:controlHiddenItem>
    </body>
</html>