<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="FormRetorno"                   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formRetorno"/>
<bean:define id="AdmTipoRetornoVO"              name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formRetorno.admTipoRetornoVO"/>
<bean:define id="AdmCanalEntradaExistenteVO"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formRetorno.admCanalEntradaExistenteVO"/>
<bean:define id="AdmCanalEntradaAssociadoVO"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formRetorno.admCanalEntradaAssociadoVO"/>
<bean:define id="AdmProcedenciaExistenteVO"     name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formRetorno.admProcedenciaExistenteVO"/>
<bean:define id="AdmProcedenciaAssociadaVO"     name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formRetorno.admProcedenciaAssociadaVO"/>
<bean:define id="AdmTipoClienteExistenteVO"     name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formRetorno.admTipoClienteExistenteVO"/>
<bean:define id="AdmTipoClienteAssociadoVO"     name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formRetorno.admTipoClienteAssociadoVO"/>
<bean:define id="AdmSegmentacaoExistenteVO"     name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formRetorno.admSegmentacaoExistenteVO"/>
<bean:define id="AdmSegmentacaoAssociadaVO"     name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formRetorno.admSegmentacaoAssociadaVO"/>
<bean:define id="AdmTipoLinhaExistenteVO"       name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formRetorno.admTipoLinhaExistenteVO"/>
<bean:define id="AdmTipoLinhaAssociadaVO"       name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formRetorno.admTipoLinhaAssociadaVO"/>
<bean:define id="AdmPessoaExistenteVO"          name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formRetorno.admPessoaExistenteVO"/>
<bean:define id="AdmPessoaAssociadaVO"          name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formRetorno.admPessoaAssociadaVO"/>
<bean:define id="AdmCarteirizacaoExistenteVO"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formRetorno.admCarteirizacaoExistenteVO"/>
<bean:define id="AdmCarteirizacaoAssociadaVO"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formRetorno.admCarteirizacaoAssociadaVO"/>
<bean:define id="AdmGrupoExistenteVO"           name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formRetorno.admGrupoExistenteVO"/>
<bean:define id="AdmGrupoAssociadoVO"           name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formRetorno.admGrupoAssociadoVO"/>
<bean:define id="AdmTipoUfOperadoraExistenteVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formRetorno.admTipoUfOperadoraExistenteVO"/>
<bean:define id="AdmTipoUfOperadoraAssociadaVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formRetorno.admTipoUfOperadoraAssociadaVO"/>


<head>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/xtree.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">        
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>
    <script type="text/javascript" src="../../../../resources/scripts/utils.js"></script>
    <script language="Javascript">
        
        function salvar(){
            if(document.forms[0].idTipoRetornoAtivo.value=='000'){
                alert('Selecione um tipo de retorno.');
                return false;
            }else{
                if (gravar()) {
                    document.forms[0].action = 'salvaRetorno.do';
                    parent.mostrar_div();
                    document.forms[0].submit();
                }
            }
            return false;
        }
        
        function gravar(){
            var lista1 = document.forms[0].arrayAdmCanalEntradaAssociado;
            var lista2 = document.forms[0].arrayAdmProcedenciaAssociada;
            var lista3 = document.forms[0].arrayAdmTipoClienteAssociado;
            var lista4 = document.forms[0].arraySegmentacaoAssociada;
            var lista5 = document.forms[0].arrayAdmTipoLinhaAssociada;
            var lista6 = document.forms[0].arrayAdmPessoaAssociada;
            var lista7 = document.forms[0].arrayAdmCarteirizacaoAssociada;
            var lista8 = document.forms[0].arrayAdmGrupoAssociado;
            var lista9 = document.forms[0].arrayAdmTipoUfOperadoraAssociada;
            
            tot = lista1.length+lista2.length+lista3.length+lista4.length+
            lista5.length+lista6.length+lista7.length+lista8.length+lista9.length;
            
            // se tot == 0, todas as listas vazias, nao verificar lista por lista.
            // se tot > 0, no minimo uma selecao existe, verficar todas as listas.
            if (tot > 0) {
                if (lista1.length == 0) {
                    alert("Selecione pelo menos um item para cada Lista!");
                    return false;
                }
                if (lista2.length == 0) {
                    alert("Selecione pelo menos um item para cada Lista!");
                    return false;
                }
                if (lista3.length == 0) {
                    alert("Selecione pelo menos um item para cada Lista!");
                    return false;
                }
                if (lista4.length == 0) {
                    alert("Selecione pelo menos um item para cada Lista!");
                    return false;
                }
                if (lista5.length == 0) {
                    alert("Selecione pelo menos um item para cada Lista!");
                    return false;
                }
                if (lista6.length == 0) {
                    alert("Selecione pelo menos um item para cada Lista!");
                    return false;
                }
                if (lista7.length == 0) {
                    alert("Selecione pelo menos um item para cada Lista!");
                    return false;
                }
                if (lista8.length == 0) {
                    alert("Selecione pelo menos um item para cada Lista!");
                    return false;
                }
                if (lista9.length == 0) {
                    alert("Selecione pelo menos um item para cada Lista!");
                    return false;
                }
            }
            
            //Seleciona todos itens na lista para enviar pelo Form
            for(i = 0;i<lista1.length;i++){
               document.forms[0].arrayAdmCanalEntradaAssociado.options[i].selected =true; 
            }
            for(i = 0;i<lista2.length;i++){
               document.forms[0].arrayAdmProcedenciaAssociada.options[i].selected =true; 
            }
            for(i = 0;i<lista3.length;i++){
               document.forms[0].arrayAdmTipoClienteAssociado.options[i].selected =true; 
            }
            for(i = 0;i<lista4.length;i++){
               document.forms[0].arraySegmentacaoAssociada.options[i].selected =true; 
            }
            for(i = 0;i<lista5.length;i++){
               document.forms[0].arrayAdmTipoLinhaAssociada.options[i].selected =true; 
            }
            for(i = 0;i<lista6.length;i++){
               document.forms[0].arrayAdmPessoaAssociada.options[i].selected =true; 
            }
            for(i = 0;i<lista7.length;i++){
               document.forms[0].arrayAdmCarteirizacaoAssociada.options[i].selected =true; 
            }
            for(i = 0;i<lista8.length;i++){
               document.forms[0].arrayAdmGrupoAssociado.options[i].selected =true; 
            }
            for(i = 0;i<lista9.length;i++){
               document.forms[0].arrayAdmTipoUfOperadoraAssociada.options[i].selected =true; 
            }
            return true;                                                                        
        }
        
        function transfereSelecaoLista(listaOrigem, listaDestino)
        {
            var i;
            for(i = 0; i<listaOrigem.options.length; i++)
                if(listaOrigem.options[i].selected)
                    // Trata a primeira posicao vazia do list, se houver
                    if ((listaDestino.options.length == 1) && (trim(listaDestino.options[0].text) == "")) {
                        listaDestino.options[0] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                    } else {
                        listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                    }
                    
            for(i = listaOrigem.options.length-1; i>=0; i--)
                if(listaOrigem.options[i].selected)
                    listaOrigem.options[i] = null;
                    
            sortOptions(listaDestino);

        }
        
        function manipula(){
            if(document.forms[0].idTipoRetornoAtivo.selectedIndex == '0'){
                limpar();
                return false;
            }
            
            document.forms[0].target = "iframe";
            document.forms[0].action = "obtemRetorno.do";
            parent.mostrar_div();
            document.forms[0].submit();
        
        
            /*if(document.forms[0].idTipoRetornoAtivo.value == '0'){

                var a = document.getElementById("div2");
                a.style.visibility = 'hidden';
                var b = document.getElementById("div1");
                b.style.visibility = 'visible';
                document.forms[0].indicativo.value='1';
                
                
            }else{
                var a = document.getElementById("div2");
                a.style.visibility = 'visible';
                var b = document.getElementById("div1");
                b.style.visibility = 'hidden';
                document.forms[0].indicativo.value = '0';
            }*/
        }
        
        function limpar(){
            var aOptComponent = new Array(document.forms[0].elements["arrayAdmCanalEntradaExistente"],
                                      document.forms[0].elements["arrayAdmCanalEntradaAssociado"],
                                      document.forms[0].elements["arrayAdmProcedenciaExistente"],
                                      document.forms[0].elements["arrayAdmProcedenciaAssociada"],
                                      document.forms[0].elements["arrayAdmTipoClienteExistente"],
                                      document.forms[0].elements["arrayAdmTipoClienteAssociado"],
                                      document.forms[0].elements["arraySegmentacaoExistente"],
                                      document.forms[0].elements["arraySegmentacaoAssociada"],
                                      document.forms[0].elements["arrayAdmTipoLinhaExistente"],
                                      document.forms[0].elements["arrayAdmTipoLinhaAssociada"],
                                      document.forms[0].elements["arrayAdmPessoaExistente"],
                                      document.forms[0].elements["arrayAdmPessoaAssociada"],
                                      document.forms[0].elements["arrayAdmCarteirizacaoExistente"],
                                      document.forms[0].elements["arrayAdmCarteirizacaoAssociada"],
                                      document.forms[0].elements["arrayAdmGrupoExistente"],
                                      document.forms[0].elements["arrayAdmGrupoAssociado"],
                                      document.forms[0].elements["arrayAdmTipoUfOperadoraExistente"],
                                      document.forms[0].elements["arrayAdmTipoUfOperadoraAssociada"]);
                                      
                                     
           for(i = 0; i < aOptComponent.length; i++) 
               while(aOptComponent[i].options.length != 0)
                    aOptComponent[i].options.remove(0);
        }

        function ocultarDiv()
        {
           if (typeof(parent.oculta_div) != "undefined")
            {
                parent.oculta_div();
            }
            else
            {
                parent.parent.oculta_div();
            }
        }
    </script>

</head>
<body onload="ocultarDiv()">
<acesso:controlHiddenItem nomeIdentificador="adm_aret_verpagina">
    <form action="salvaRetorno" id="formRetorno" name="formRetorno" method="post">
        <html:hidden name="FormRetorno" property="indicativo"/>    
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
        
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
    
            <table width="707" border="0" cellspacing="0" cellpadding="1" class="tbl_bgGray" height="30" align="center">
                <tr><td height="5" colspan="2"></td></tr>
                <tr>
                    <td width="100" style="text-indent:6px;" valign="middle">
                        <b>Tipo de Retorno:</b>
                    </td>
                    <td class="tblEstatica_campoNome" align="left" valign="top">
                        <html:select name="FormRetorno" property="idTipoRetornoAtivo" style="width:200px;" styleClass="SELECT" size="1" onchange="Javascript:manipula();" >
                            <html:option value="000">Escolha uma opção...</html:option>
                            <html:options collection="AdmTipoRetornoVO" property="idTipoRetorno" labelProperty="nmTipoRetorno" /> 
                        </html:select>
                    </td>
                    
                    <td>
                        <div id="div1" style="visibility:'hidden'; top: 0px; left: 0px; height: 24px; padding: 0px;">
                        <img align="middle" id="btSalvar" href="Javascript:salvar();" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_salvar_over.gif" style="border:none;"></img>
                        </div>
                    </td>
                    
                    <tr><td height="5" colspan="2"></td></tr>
                <tr>
            </table>
            <br>
            
            <table width="707" align="center" cellpadding="0" cellspacing="0">
                <tr>
                    <td>
            
                            <div id="div2" style="overflow:auto;visibility:'visible'; width:730px; top: 0px; left: 0px; height:280px; padding: 0px;" align="center">
                                <table width="707" align="center">
                                    <tr>
                                        <td width="300" align="center">
                                            Canal de Entrada - Listagem<br>
                                            <html:select name="FormRetorno" property="arrayAdmCanalEntradaExistente" styleClass="SELECT" multiple="true" style="width:300px;" size="4" ondblclick="transfereSelecaoLista(document.formRetorno.arrayAdmCanalEntradaExistente, document.formRetorno.arrayAdmCanalEntradaAssociado);">
                                                <html:options collection="AdmCanalEntradaExistenteVO" property="idCanalEntrada" labelProperty="nmCanalEntrada" /> 
                                            </html:select>
                                            
                                        </td>
                                        <td align="right" valign="middle" width="76">
                                            <img id="btRightSimp" onclick="transfereSelecaoLista(document.formRetorno.arrayAdmCanalEntradaExistente, document.formRetorno.arrayAdmCanalEntradaAssociado);" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_rightaln_over.gif" style="border:none;"></img>
                                            <img id="btLeftSimp" onclick="transfereSelecaoLista(document.formRetorno.arrayAdmCanalEntradaAssociado, document.formRetorno.arrayAdmCanalEntradaExistente);" src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_leftaln_over.gif" style="border:none;"></img>
                                        </td>
                                        <td width="30"></td>
                                        <td width="300" align="center">
                                            Canal de Entrada - Selecionados<br>
                                            <html:select name="FormRetorno" property="arrayAdmCanalEntradaAssociado" styleClass="SELECT" multiple="true" style="width:300px;" size="4" ondblclick="transfereSelecaoLista(document.formRetorno.arrayAdmCanalEntradaAssociado, document.formRetorno.arrayAdmCanalEntradaExistente);">
                                                <html:options collection="AdmCanalEntradaAssociadoVO" property="idCanalEntrada" labelProperty="nmCanalEntrada" /> 
                                            </html:select>
                                        </td>
                                    </tr>
                                </table>
                                
                                
                                <table width="707" align="center">
                                    <tr>
                                        <td width="300" align="center">
                                            Procedência - Listagem<br>
                                            <html:select name="FormRetorno" property="arrayAdmProcedenciaExistente" styleClass="SELECT" multiple="true" style="width:300px;" size="4" ondblclick="transfereSelecaoLista(document.formRetorno.arrayAdmProcedenciaExistente, document.formRetorno.arrayAdmProcedenciaAssociada);">
                                                <html:options collection="AdmProcedenciaExistenteVO" property="idProcedencia" labelProperty="nmProcedencia" /> 
                                            </html:select>
                                        </td>
                                        <td align="right" valign="middle" width="76">
                                            <img id="btRightSimp" onclick="transfereSelecaoLista(document.formRetorno.arrayAdmProcedenciaExistente, document.formRetorno.arrayAdmProcedenciaAssociada);" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_rightaln_over.gif" style="border:none;"></img>
                                            <img id="btLeftSimp" onclick="transfereSelecaoLista(document.formRetorno.arrayAdmProcedenciaAssociada, document.formRetorno.arrayAdmProcedenciaExistente);" src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_leftaln_over.gif" style="border:none;"></img>
                                        </td>
                                        <td width="30"></td>
                                        <td width="300" align="center">
                                            Procedência - Selecionados<br>
                                            <html:select name="FormRetorno" property="arrayAdmProcedenciaAssociada" styleClass="SELECT" multiple="true" style="width:300px;" size="4" ondblclick="transfereSelecaoLista(document.formRetorno.arrayAdmProcedenciaAssociada, document.formRetorno.arrayAdmProcedenciaExistente);">
                                                <html:options collection="AdmProcedenciaAssociadaVO" property="idProcedencia" labelProperty="nmProcedencia" /> 
                                            </html:select>
                                        </td>
                                    </tr>
                                </table>
                                
                                <table width="707" align="center">
                                    <tr>
                                        <td width="300" align="center">
                                            Tipo de Cliente - Listagem<br>
                                            <html:select name="FormRetorno" property="arrayAdmTipoClienteExistente" styleClass="SELECT" multiple="true" style="width:300px;" size="4" ondblclick="transfereSelecaoLista(document.formRetorno.arrayAdmTipoClienteExistente, document.formRetorno.arrayAdmTipoClienteAssociado);">
                                                <html:options collection="AdmTipoClienteExistenteVO" property="idTipoCliente" labelProperty="nmTipoCliente" /> 
                                            </html:select>
                                        </td>
                                        <td align="right" valign="middle" width="76">
                                            <img id="btRightSimp" onclick="transfereSelecaoLista(document.formRetorno.arrayAdmTipoClienteExistente, document.formRetorno.arrayAdmTipoClienteAssociado);" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_rightaln_over.gif" style="border:none;"></img>
                                            <img id="btLeftSimp" onclick="transfereSelecaoLista(document.formRetorno.arrayAdmTipoClienteAssociado, document.formRetorno.arrayAdmTipoClienteExistente);" src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_leftaln_over.gif" style="border:none;"></img>
                                        </td>
                                        <td width="30"></td>
                                        <td width="300" align="center">
                                            Tipo de Cliente - Selecionados<br>
                                            <html:select name="FormRetorno" property="arrayAdmTipoClienteAssociado" styleClass="SELECT" multiple="true" style="width:300px;" size="4" ondblclick="transfereSelecaoLista(document.formRetorno.arrayAdmTipoClienteAssociado, document.formRetorno.arrayAdmTipoClienteExistente);">
                                                <html:options collection="AdmTipoClienteAssociadoVO" property="idTipoCliente" labelProperty="nmTipoCliente" /> 
                                            </html:select>
                                        </td>
                                    </tr>
                                </table>
                                
                                <table width="707" align="center">
                                    <tr>
                                        <td width="300" align="center">
                                            Segmentação - Listagem<br>
                                            <html:select name="FormRetorno" property="arraySegmentacaoExistente" styleClass="SELECT" multiple="true" style="width:300px;" size="4" ondblclick="transfereSelecaoLista(document.formRetorno.arraySegmentacaoExistente, document.formRetorno.arraySegmentacaoAssociada);">
                                                <html:options collection="AdmSegmentacaoExistenteVO" property="idSegmentacao" labelProperty="dsSegmentacao" /> 
                                            </html:select>
                                        </td>
                                        <td align="right" valign="middle" width="76">
                                            <img id="btRightSimp" onclick="transfereSelecaoLista(document.formRetorno.arraySegmentacaoExistente, document.formRetorno.arraySegmentacaoAssociada);" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_rightaln_over.gif" style="border:none;"></img>
                                            <img id="btLeftSimp" onclick="transfereSelecaoLista(document.formRetorno.arraySegmentacaoAssociada, document.formRetorno.arraySegmentacaoExistente);" src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_leftaln_over.gif" style="border:none;"></img>
                                        </td>
                                        <td width="30"></td>
                                        <td width="300" align="center">
                                            Segmentação - Selecionados<br>
                                            <html:select name="FormRetorno" property="arraySegmentacaoAssociada" styleClass="SELECT" multiple="true" style="width:300px;" size="4" ondblclick="transfereSelecaoLista(document.formRetorno.arraySegmentacaoAssociada, document.formRetorno.arraySegmentacaoExistente);">
                                                <html:options collection="AdmSegmentacaoAssociadaVO" property="idSegmentacao" labelProperty="dsSegmentacao" /> 
                                            </html:select>
                                        </td>
                                    </tr>
                                </table>
                                
                                <table width="707" align="center">
                                    <tr>
                                        <td width="300" align="center">
                                            Tipo de Linha - Listagem<br>
                                            <html:select name="FormRetorno" property="arrayAdmTipoLinhaExistente" styleClass="SELECT" multiple="true" style="width:300px;" size="4" ondblclick="transfereSelecaoLista(document.formRetorno.arrayAdmTipoLinhaExistente, document.formRetorno.arrayAdmTipoLinhaAssociada);">
                                                <html:options collection="AdmTipoLinhaExistenteVO" property="idTipoLinha" labelProperty="dsTipoLinha" /> 
                                            </html:select>
                                        </td>
                                        <td align="right" valign="middle" width="76">
                                            <img id="btRightSimp" onclick="transfereSelecaoLista(document.formRetorno.arrayAdmTipoLinhaExistente, document.formRetorno.arrayAdmTipoLinhaAssociada);" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_rightaln_over.gif" style="border:none;"></img>
                                            <img id="btLeftSimp" onclick="transfereSelecaoLista(document.formRetorno.arrayAdmTipoLinhaAssociada, document.formRetorno.arrayAdmTipoLinhaExistente);" src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_leftaln_over.gif" style="border:none;"></img>
                                        </td>
                                        <td width="30"></td>
                                        <td width="300" align="center">
                                            Tipo de Linha - Selecionados<br>
                                            <html:select name="FormRetorno" property="arrayAdmTipoLinhaAssociada" styleClass="SELECT" multiple="true" style="width:300px;" size="4" ondblclick="transfereSelecaoLista(document.formRetorno.arrayAdmTipoLinhaAssociada, document.formRetorno.arrayAdmTipoLinhaExistente);">
                                                <html:options collection="AdmTipoLinhaAssociadaVO" property="idTipoLinha" labelProperty="dsTipoLinha" /> 
                                            </html:select>
                                        </td>
                                    </tr>
                                </table>
                                
                                <table width="707" align="center">
                                    <tr>
                                        <td width="300" align="center">
                                            Natureza - Listagem<br>
                                            <html:select name="FormRetorno" property="arrayAdmPessoaExistente" styleClass="SELECT" multiple="true" style="width:300px;" size="4" ondblclick="transfereSelecaoLista(document.formRetorno.arrayAdmPessoaExistente, document.formRetorno.arrayAdmPessoaAssociada);">
                                                <html:options collection="AdmPessoaExistenteVO" property="idPessoa" labelProperty="nmPessoa" /> 
                                            </html:select>
                                        </td>
                                        <td align="right" valign="middle" width="76">
                                            <img id="btRightSimp" onclick="transfereSelecaoLista(document.formRetorno.arrayAdmPessoaExistente, document.formRetorno.arrayAdmPessoaAssociada);" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_rightaln_over.gif" style="border:none;"></img>
                                            <img id="btLeftSimp" onclick="transfereSelecaoLista(document.formRetorno.arrayAdmPessoaAssociada, document.formRetorno.arrayAdmPessoaExistente);" src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_leftaln_over.gif" style="border:none;"></img>
                                        </td>
                                        <td width="30"></td>
                                        <td width="300" align="center">
                                            Natureza - Selecionados<br>
                                            <html:select name="FormRetorno" property="arrayAdmPessoaAssociada" styleClass="SELECT" multiple="true" style="width:300px;" size="4" ondblclick="transfereSelecaoLista(document.formRetorno.arrayAdmPessoaAssociada, document.formRetorno.arrayAdmPessoaExistente);">
                                                <html:options collection="AdmPessoaAssociadaVO" property="idPessoa" labelProperty="nmPessoa" /> 
                                            </html:select>
                                        </td>
                                    </tr>
                                </table>
                                
                                <table width="707" align="center">
                                    <tr>
                                        <td width="300" align="center">
                                            Carteirização - Listagem<br>
                                            <html:select name="FormRetorno" property="arrayAdmCarteirizacaoExistente" styleClass="SELECT" multiple="true" style="width:300px;" size="4" ondblclick="transfereSelecaoLista(document.formRetorno.arrayAdmCarteirizacaoExistente, document.formRetorno.arrayAdmCarteirizacaoAssociada);">
                                                <html:options collection="AdmCarteirizacaoExistenteVO" property="idCarteirizacao" labelProperty="nmCarteirizacao" /> 
                                            </html:select>
                                        </td>
                                        <td align="right" valign="middle" width="76">
                                            <img id="btRightSimp" onclick="transfereSelecaoLista(document.formRetorno.arrayAdmCarteirizacaoExistente, document.formRetorno.arrayAdmCarteirizacaoAssociada);" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_rightaln_over.gif" style="border:none;"></img>
                                            <img id="btLeftSimp" onclick="transfereSelecaoLista(document.formRetorno.arrayAdmCarteirizacaoAssociada, document.formRetorno.arrayAdmCarteirizacaoExistente);" src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_leftaln_over.gif" style="border:none;"></img>
                                        </td>
                                        <td width="30"></td>
                                        <td width="300" align="center">
                                            Carteirização - Selecionados<br>
                                            <html:select name="FormRetorno" property="arrayAdmCarteirizacaoAssociada" styleClass="SELECT" multiple="true" style="width:300px;" size="4" ondblclick="transfereSelecaoLista(document.formRetorno.arrayAdmCarteirizacaoAssociada, document.formRetorno.arrayAdmCarteirizacaoExistente);">
                                                <html:options collection="AdmCarteirizacaoAssociadaVO" property="idCarteirizacao" labelProperty="nmCarteirizacao" /> 
                                            </html:select>
                                        </td>
                                    </tr>
                                </table>
                    
                                <table width="707" align="center">
                                    <tr>
                                        <td width="300" align="center">
                                            Grupo - Listagem<br>
                                            <html:select name="FormRetorno" property="arrayAdmGrupoExistente" styleClass="SELECT" multiple="true" style="width:300px;" size="4" ondblclick="transfereSelecaoLista(document.formRetorno.arrayAdmGrupoExistente, document.formRetorno.arrayAdmGrupoAssociado);">
                                                <html:options collection="AdmGrupoExistenteVO" property="idGrupo" labelProperty="nmGrupo" /> 
                                            </html:select>
                                        </td>
                                        <td align="right" valign="middle" width="76">
                                            <img id="btRightSimp" onclick="transfereSelecaoLista(document.formRetorno.arrayAdmGrupoExistente, document.formRetorno.arrayAdmGrupoAssociado);" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_rightaln_over.gif" style="border:none;"></img>
                                            <img id="btLeftSimp" onclick="transfereSelecaoLista(document.formRetorno.arrayAdmGrupoAssociado, document.formRetorno.arrayAdmGrupoExistente);" src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_leftaln_over.gif" style="border:none;"></img>
                                        </td>
                                        <td width="30"></td>
                                        <td width="300" align="center">
                                            Grupo - Selecionados<br>
                                            <html:select name="FormRetorno" property="arrayAdmGrupoAssociado" styleClass="SELECT" multiple="true" style="width:300px;" size="4" ondblclick="transfereSelecaoLista(document.formRetorno.arrayAdmGrupoAssociado, document.formRetorno.arrayAdmGrupoExistente);">
                                                <html:options collection="AdmGrupoAssociadoVO" property="idGrupo" labelProperty="nmGrupo" /> 
                                            </html:select>
                                        </td>
                                    </tr>
                                </table>

                                <table width="707" align="center">
                                    <tr>
                                        <td width="300" align="center">
                                            Regional - Listagem<br>
                                            <html:select name="FormRetorno" property="arrayAdmTipoUfOperadoraExistente" styleClass="SELECT" multiple="true" style="width:300px;" size="4" ondblclick="transfereSelecaoLista(document.listaGruposForm.canaisExistentes, document.listaGruposForm.canaisRelacionados);">
                                                <html:options collection="AdmTipoUfOperadoraExistenteVO" property="idUfOperadora" labelProperty="dsRegional" /> 
                                            </html:select>
                                        </td>
                                        <td align="right" valign="middle" width="76">
                                            <img id="btRightSimp" onclick="transfereSelecaoLista(document.formRetorno.arrayAdmTipoUfOperadoraExistente, document.formRetorno.arrayAdmTipoUfOperadoraAssociada);" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_rightaln_over.gif" style="border:none;"></img>
                                            <img id="btLeftSimp" onclick="transfereSelecaoLista(document.formRetorno.arrayAdmTipoUfOperadoraAssociada, document.formRetorno.arrayAdmTipoUfOperadoraExistente);" src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_leftaln_over.gif" style="border:none;"></img>
                                        </td>
                                        <td width="30"></td>
                                        <td width="300" align="center">
                                            Regional - Selecionados<br>
                                            <html:select name="FormRetorno" property="arrayAdmTipoUfOperadoraAssociada" styleClass="SELECT" multiple="true" style="width:300px;" size="4" ondblclick="transfereSelecaoLista(document.listaGruposForm.canaisExistentes, document.listaGruposForm.canaisRelacionados);">
                                                <html:options collection="AdmTipoUfOperadoraAssociadaVO" property="idUfOperadora" labelProperty="dsRegional" /> 
                                            </html:select>
                                        </td>
                                    </tr>
                                </table>

                            </div>

                    </td>
                </tr>
            </table>    
            <table width="707" align="center">
                <tr>
                    <td align="right" valign="middle">
                    <acesso:controlHiddenItem nomeIdentificador="adm_aret_gravar">
                        <img vspace="5" align="middle" href="" id="btSalvar" onClick="salvar(); return false;" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_salvar_over.gif" style="border:none;"></img>
                    </acesso:controlHiddenItem>
                    </td>
                </tr>
            </table>
            <iframe scrolling="yes" style="visibility:hidden;" name="iframe" height="1px" width="1px"></iframe>
        </form>  

    <script>
        document.body.style.backgroundColor = '#ededed';
    </script>
    <vivo:alert atributo="msgStatus" scope="request"/>
</acesso:controlHiddenItem>
</body>