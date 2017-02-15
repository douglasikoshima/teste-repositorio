<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<bean:define id="FormUnidade"            name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="unidadeForm"/>
<bean:define id="idSistema"              name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="unidadeForm.idSistema"/>
<bean:define id="listaSubSistemas"       name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="unidadeForm.listaSubSistemasUsuarioVO"/>
<bean:define id="unidadeAtual"           name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="unidadeForm.unidadeAtual"/>
<bean:define id="listaSubSistemaPaginas" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="unidadeForm.listaSubSistemaPaginas"/>
<logic:notEqual name="FormUnidade" property="listaPaginasVO" value="null">
    <bean:define id="listaPaginasVO"     name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="unidadeForm.listaPaginasVO" />
</logic:notEqual>
<bean:define id="listaUnidadesPesquisa"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="unidadeForm.listaUnidadesPesquisa"/>

<head>

    <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">        
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>

    <script language="javaScript">
        function valida() {
            if (trim(document.forms[0].dsservidor.value) == ""){
                alert("Favor preencher o campo!");
                return(false);
            } else {
                return(true);
            }
        }
        // Carrega o select de Paginas
        function carregaSelect(objeto)
        {
            if(objeto.value=='0000'){
                  while(document.forms[0].elements["unidadeAtual.idPagina"].options.length > 1)
                  {
                      document.forms[0].elements["unidadeAtual.idPagina"].options[1]     = null;  
                  }         
                return;
            }else{
                document.forms[0].action = "carregaSelectPagina.do";
                parent.parent.mostrar_div();
                document.forms[0].submit(); 
            }
            
        }
    
        function incluiSubsistema() {
            return false;
        }
        
        function cancelar(){
            return false;
        }
        
        function inserir(){
                if(document.forms[0].elements["unidadeAtual.idSubSistema"].value=="0000"){
                    alert("É necessário a escolha de um subsistema para efetuar a inclusão.");
                    return;
                }
                if(document.forms[0].elements["unidadeAtual.idPagina"].value=="0000"){
                    alert("É necessário existir uma pagina cadastrada para efetuar a inclusão.");
                    return;
                }
                if(document.forms[0].elements["unidadeAtual.nmUnidade"].value==""){
                    alert("É necessário o preenchimento de todos os campos.");
                    return;
                }
                if(document.forms[0].elements["unidadeAtual.cdUnidade"].value==""){
                    alert("É necessário o preenchimento de todos os campos.");
                    return;
                }
                if(document.forms[0].idUnidade.value == "" ||
                 document.forms[0].idUnidade.value == "0"  ||
                 document.forms[0].idUnidade.value == 'null')
                {
                    document.forms[0].flag.value = 0;
                    document.forms[0].target = "_parent";
                    document.forms[0].action ='salvaUnidade.do';
                    parent.parent.mostrar_div();
                    document.forms[0].submit();
                }else
                {
                    document.forms[0].flag.value = 1;
                    document.forms[0].target = "_parent";
                    document.forms[0].action ='salvaUnidade.do';
                    parent.parent.mostrar_div();
                    document.forms[0].submit();
                }
                
            }
            
            function carregaValor()
            {
                var nomeUnid = '<%= request.getParameter("nmUnidade") %>';
                var idenUnid = '<%= request.getParameter("cdUnidade") %>';
                
                if(nomeUnid != 'null')
                {
                    document.forms[0].elements["unidadeAtual.nmUnidade"].value = nomeUnid;
                }
                if(idenUnid != 'null')                    
                {
                    document.forms[0].elements["unidadeAtual.cdUnidade"].value = idenUnid;
                }
                
                if('<%=request.getParameter("idUnidade")%>' != 'null')
                {
                    document.forms[0].elements["unidadeAtual.idSubSistema"].disabled = true;
                    document.forms[0].elements["unidadeAtual.idPagina"].disabled = true;                    
                }            
            }

    </script>

</head>

<body>
    <acesso:controlHiddenItem nomeIdentificador="usu_iau_verpagina">
    <script>
        document.body.style.backgroundColor = '#ededed';
        parent.parent.mostrar_div();
    </script>     
        <html:form action="/usuario/manterSistema/editarParSistema/manterUnidade/pesquisaUnidades.do" onsubmit="return false;" method="post">
            
            <!-- parametro de retorno para action -->
            <input type="hidden" name="incluiAltera" value="incluiAltera">
            
            <input type="hidden" name="flag" value="0"/>  
            <input type="hidden" name="idUnidade" value="<%=request.getParameter("idUnidade")%>">             
            
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
                <table width="485" border="0" cellspacing="1" cellpadding="1" align="center">
                    <tr><td height="4" width="10" rowspan="5"></td></tr>
                    <tr>
                        <td width="100">
                            
                            <netui:label value="Sub-sistema: "/>
                        </td>
                        <td style="padding-left:3px;" width="375">
                            <html:select name="FormUnidade" property="unidadeAtual.idSubSistema" styleClass="SELECT" onchange="carregaSelect(this);" style="width:300px;">
                                <html:option value="0000">Escolha uma opção</html:option>
                                <html:options collection="listaSubSistemas" property="idSubSistema" labelProperty="dsSubSistema" /> 
                            </html:select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <netui:label value="Página: "/>
                        </td>
                        <td style="padding-left:3px;" width="375">
                            <html:select name="FormUnidade" property="unidadeAtual.idPagina" style="width:300px;" styleClass="SELECT">
                                <html:option value="0000">Escolha uma opção</html:option>
                                <html:options collection="listaPaginasVO" property="idPagina" labelProperty="nmPagina" /> 
                            </html:select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <netui:label value="Nome da Unidade: "/>
                        </td>
                        <td>
                            <html:text name="FormUnidade" property="unidadeAtual.nmUnidade" style="width:300px" maxlength="200"/>
                        </td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>
                            <netui:label value="Identificação: "/>
                        </td>
                        <td>
                            <html:text name="FormUnidade" property="unidadeAtual.cdUnidade" style="width:300px;" maxlength="200"/>
                        </td>
                        <td align="right" class="tblEstatica_campoNome">
                            <!--
                            <netui:label value="Detalhe: " styleClass="tblEstatica_campoNome"/>
                            -->
                        </td>
                        <td align="left">&nbsp;
                            <!--
                            <netui:select dataSource="{}" style="width:50px" styleClass="SELECT">
                                <netui:selectOption value="Sim"/>
                                <netui:selectOption value="Não"/>
                            </netui:select>
                            -->
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td align="right" colspan="2">
                        <acesso:controlHiddenItem nomeIdentificador="usu_iau_gravar">
                            <img vspace="10" onClick="inserir(); return false" id="btSalvar" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif'" style="border:none;cursor:hand;" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_gravar_over.gif'"/>
                        </acesso:controlHiddenItem>
                        </td>
                    </tr>
                </table>
       </html:form>

    <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
        carregaValor();
        parent.parent.oculta_div();
    </script>

      </acesso:controlHiddenItem>
</body>