<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<bean:define id="FormPagina"       name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="paginaForm"/>
<bean:define id="idSistema"        name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="paginaForm.idSistema"/>
<bean:define id="listaSubSistemas" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="paginaForm.listaSubSistemasVO"/>
<bean:define id="paginaAtual"      name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="paginaForm.paginaAtual"/>
<bean:define id="listaPaginasVO"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="paginaForm.listaPaginasVO"/>

<head>

    <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">        
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>

    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/tratamento.js" ></script>
    <script language="javaScript">
        function valida() 
        {

            if (trim(document.forms[0].nmPagina.value) == "")
            {
                alert("Nome da Página é um campo obrigatório, favor preencher.");
                return(false);
                
            } else if(trim(document.forms[0].nmUrl.value) == "") {
                
                alert('URL da Página é um campo obrigatório, favor preencher.')            
                return(false);
                
            }else if(document.forms[0].idSubSistema.selectedIndex == 0)
            {
                alert('Pertence ao Sub-Sistema é um campo obrigatório, favor preencher.');
                return(false);
                
            }else if(document.forms[0].inDisponib.selectedIndex == 0)
            {
                alert('Disponibilidade é um campo obrigatório, favor preencher.');
                return(false);
            }else
            {
                return true;
            }
            
        }
    
        function alteraServidor() {
            return false;
        }
        
        function salvarItem() 
        {
            if(valida())
            {
                document.forms[0].target = 'ifrmAbas';
                if(document.forms[0].idPagina.value == "")
                    document.forms[0].action = "salvaPagina.do?tipo=novo";
                else
                    document.forms[0].action = "salvaPagina.do?tipo=edicao&idPagina="+document.forms[0].idPagina.value;

                parent.parent.mostrar_div();
                document.forms[0].submit(); 
            }
        }
        function atribuiValor()
        {
            var comboDisp = '<%= request.getParameter("inDisponib") %>';
            if(comboDisp == "1")
                document.forms[0].inDisponib.selectedIndex = 1;
            else
                document.forms[0].inDisponib.selectedIndex = 2;
        }
        function limpar()
        {
            var form = document.forms[0];
            
            form.nmPagina.value     = '';
            form.idPagina.value     = '';
            form.nmUrl.value        = '';
            
            form.idSubSistema.selectedIndex = 0;
            form.inDisponib.selectedIndex = 0;
        
        }
        
    </script>

</head>

<body onload="atribuiValor();">
    <acesso:controlHiddenItem nomeIdentificador="usu_iappg_verpagina">
    <script>
        document.body.style.backgroundColor = '#ededed';
        parent.parent.mostrar_div();
    </script>     
    <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
        parent.parent.oculta_div();
    </script>
    
        <form action="salvaPagina.do" onSubmit="return false;" method="post">
        
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
                <table cellspacing="0" cellpadding="0" height="60">
                    <tr> 
                        <td style="text-indent:6px;">
                            
                            <table width="97%" border="0" cellspacing="1" cellpadding="1" align="center">
                                <tr>
                                    <td width="175" class="tblEstatica_campoNome">
                                        <netui:label value="Nome da Página: " styleClass="tblEstatica_campoNome"/>
                                        <html:hidden property="idPagina" name="paginaAtual" value='<%=request.getParameter("idPagina")%>' />
                                    </td>
                                    <td width="" align="left">
                                        <html:text name="paginaAtual" property="nmPagina"  style="width:300px" styleClass="input" maxlength="254"/>
                                    </td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td class="tblEstatica_campoNome">
                                        <netui:label value="URL da Página: " styleClass="tblEstatica_campoNome"/>
                                    </td>
                                    <td align="left">
                                        <html:text name="paginaAtual" property="nmUrl"  style="width:300px" styleClass="input" maxlength="254"  onkeypress="return ValidarTeclaURL()"/>
                                    </td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td class="tblEstatica_campoNome">
                                        <netui:label value="Pertence ao Sub-Sistema: " styleClass="tblEstatica_campoNome"/>
                                    </td>
                                    <td align="left" colspan="2" style="padding-left:3px;">
                                        <html:select name="paginaAtual" property="idSubSistema" style="width:200"  styleClass="SELECT">
                                            <html:option value="">Escolha um subsistema...</html:option>
                                            <html:options collection="listaSubSistemas" property="idSubSistema" labelProperty="dsSubSistema" /> 
                                        </html:select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <netui:label value="Disponibilidade: " styleClass="tblEstatica_campoNome"/>
                                    </td>
                                    <td>
                                        <html:select name="paginaAtual" property="inDisponib">
                                            <html:option value="">Escolha...</html:option>
                                            <html:option value="1">Sim</html:option>
                                            <html:option value="0">Não</html:option>
                                        </html:select>
                                    </td>
                                </tr>
                            </table>
                            
                        </td>
                    </tr>             
                    <tr><td height="10"></td></tr>
                    <tr>
                        <td colspan="2" align="right" width="780" style="padding-right:20px;">
                        <acesso:controlHiddenItem nomeIdentificador="usu_iappg_salvar">
                            <img id="btIncluir" onClick="salvarItem(); return false" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_salvar_over.gif'" style="border:none;cursor:hand"/>
                        </acesso:controlHiddenItem>
                        </td>
                    </tr>
                </table>
                
       </form>
       
       <script>
            <%if(request.getParameter("operacao") != null && request.getParameter("operacao").equals("incluir")){%>
                limpar();
            <%}%>
       </script>
       
       </acesso:controlHiddenItem>
</body>