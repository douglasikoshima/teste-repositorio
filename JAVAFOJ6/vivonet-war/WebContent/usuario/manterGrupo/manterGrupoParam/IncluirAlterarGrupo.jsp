<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<bean:define id="FormGrupo"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaGruposForm"/>
<bean:define id="grupoVO"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaGruposForm.grupoNovoVO"/>
<!-- utilizado para percorrer cada item do array de resultados-->
<bean:define id="itemGrupo"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaGruposForm.itemGrupo"/>
<bean:define id="ComboTipoGrupo"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaGruposForm.tiposGrupo"/>

<head>
    <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">        
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>
    <script language="javaScript">
        function valida(){
            if (trim(document.forms[0].dsGrupo.value) == ""){
                alert("Grupo é um campo obrigatório, favor preencher.");
                return(false);
            } else if(document.forms[0].idTipoGrupoSelecionado.value == ""){
                alert("Tipo do Grupo é um campo obrigatório, favor preencher.");
                return(false);
            } else 
                return(true);
        }

        function salvaGrupo(){
            var idgrupo = document.forms[0].idAlt.value;
            if(valida()){
                // Se ID do grupo nao foi preenchido, salva NOVO.
                if(idgrupo == ''){
                    document.forms[0].action = 'salvaItemPesquisa.do';
                    parent.mostrar_div();
                    document.forms[0].submit();
                }else{
                    document.forms[0].action = 'salvaItemAlterado.do';
                    parent.mostrar_div();
                    document.forms[0].submit();
                }
            }
        }
        
        function carregaGrupo(){
            var dsGrupo = '<bean:write name="itemGrupo" property="dsGrupo" />';
            var idGrupo = '<%= request.getParameter("idGrupo") %>';
            var operacao = '<%= request.getParameter("operacao") %>';
            
            if(dsGrupo != "" && dsGrupo != 'null' && operacao != 'incluir'){
                document.forms[0].dsGrupo.value = dsGrupo;
            }else
                document.forms[0].dsGrupo.value = '';
            
            if(idGrupo != "" && idGrupo != 'null'){
                document.forms[0].idAlt.value = idGrupo;
            }
            parent.oculta_div();
        }
    </script>
</head>
<body onload="carregaGrupo();">
    <acesso:controlHiddenItem nomeIdentificador="usu_iagr_verpagina">
    <script>
        document.body.style.backgroundColor = '#ededed';
    </script>     
        
    <form action="salvaItemPesquisa.do" target="_parent" onSubmit="return false;" method="post">  
        <html:hidden property="idAlt" name="FormGrupo" />   
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
            <table cellspacing="0" cellpadding="0" height="60">
                <tr> 
                    <td style="text-indent:6px;">
                        <netui:label value="Grupo:" styleClass="tblEstatica_campoNome"/>
                    </td>
                    <td>
                        <html:text name="itemGrupo" property="dsGrupo" size="60" maxlength="254"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-indent:6px;">
                        <netui:label value="Tipo:" styleClass="tblEstatica_campoNome"/>
                    </td>
                    <td>
                        <html:select name="itemGrupo" property="idTipoGrupoSelecionado" styleId="idTipoGrupoSelecionado">
                            <html:option value="">--Selecione--</html:option>
                            <html:options collection="ComboTipoGrupo" property="idTipoGrupo" labelProperty="dsTipoGrupo"/>
                        </html:select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="right" width="780" style="padding-right:20px;">
						<acesso:controlHiddenItem nomeIdentificador="usu_iagr_gravar">
                        <img hspace="5" style="border:none;cursor:hand;"  onclick="salvaGrupo();" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_salvar_over.gif'"/>
						</acesso:controlHiddenItem>
                    </td>
                </tr>
            </table>
   </form>

<%if(request.getParameter("operacao") != null && request.getParameter("operacao").equals("incluir")){%>
    <script>
        document.forms[0].idAlt.value = '';
        document.forms[0].dsGrupo.value = '';
    </script>
<%}%>            

   </acesso:controlHiddenItem>
</body>