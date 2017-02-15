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

     
</head>
<body onload="parent.parent.oculta_div()">
<acesso:controlHiddenItem nomeIdentificador="adm_iret_verpagina">
    <form name="retornoIframe">
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
                        <html:select name="FormRetorno" property="arrayAdmTipoRetorno" value="idTipoRetornoAtivo" style="width:200px;" styleClass="SELECT" size="1" onchange="Javascript:manipula();" >
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
                                            <html:select name="FormRetorno" property="arrayAdmCanalEntradaExistente" styleClass="SELECT" multiple="true" style="width:300px;" size="4" ondblclick="transfereSelecaoLista(document.listaGruposForm.canaisExistentes, document.listaGruposForm.canaisRelacionados);">
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
                                            <html:select name="FormRetorno" property="arrayAdmCanalEntradaAssociado" styleClass="SELECT" multiple="true" style="width:300px;" size="4" ondblclick="transfereSelecaoLista(document.listaGruposForm.canaisExistentes, document.listaGruposForm.canaisRelacionados);">
                                                <html:options collection="AdmCanalEntradaAssociadoVO" property="idCanalEntrada" labelProperty="nmCanalEntrada" /> 
                                            </html:select>
                                        </td>
                                    </tr>
                                </table>
                                
                                
                                <table width="707" align="center">
                                    <tr>
                                        <td width="300" align="center">
                                            Procedência - Listagem<br>
                                            <html:select name="FormRetorno" property="arrayAdmProcedenciaExistente" styleClass="SELECT" multiple="true" style="width:300px;" size="4" ondblclick="transfereSelecaoLista(document.listaGruposForm.canaisExistentes, document.listaGruposForm.canaisRelacionados);">
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
                                            <html:select name="FormRetorno" property="arrayAdmProcedenciaAssociada" styleClass="SELECT" multiple="true" style="width:300px;" size="4" ondblclick="transfereSelecaoLista(document.listaGruposForm.canaisExistentes, document.listaGruposForm.canaisRelacionados);">
                                                <html:options collection="AdmProcedenciaAssociadaVO" property="idProcedencia" labelProperty="nmProcedencia" /> 
                                            </html:select>
                                        </td>
                                    </tr>
                                </table>
                                
                                <table width="707" align="center">
                                    <tr>
                                        <td width="300" align="center">
                                            Tipo de Cliente - Listagem<br>
                                            <html:select name="FormRetorno" property="arrayAdmTipoClienteExistente" styleClass="SELECT" multiple="true" style="width:300px;" size="4" ondblclick="transfereSelecaoLista(document.listaGruposForm.canaisExistentes, document.listaGruposForm.canaisRelacionados);">
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
                                            <html:select name="FormRetorno" property="arrayAdmTipoClienteAssociado" styleClass="SELECT" multiple="true" style="width:300px;" size="4" ondblclick="transfereSelecaoLista(document.listaGruposForm.canaisExistentes, document.listaGruposForm.canaisRelacionados);">
                                                <html:options collection="AdmTipoClienteAssociadoVO" property="idTipoCliente" labelProperty="nmTipoCliente" /> 
                                            </html:select>
                                        </td>
                                    </tr>
                                </table>
                                
                                <table width="707" align="center">
                                    <tr>
                                        <td width="300" align="center">
                                            Segmentação - Listagem<br>
                                            <html:select name="FormRetorno" property="arraySegmentacaoExistente" styleClass="SELECT" multiple="true" style="width:300px;" size="4" ondblclick="transfereSelecaoLista(document.listaGruposForm.canaisExistentes, document.listaGruposForm.canaisRelacionados);">
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
                                            <html:select name="FormRetorno" property="arraySegmentacaoAssociada" styleClass="SELECT" multiple="true" style="width:300px;" size="4" ondblclick="transfereSelecaoLista(document.listaGruposForm.canaisExistentes, document.listaGruposForm.canaisRelacionados);">
                                                <html:options collection="AdmSegmentacaoAssociadaVO" property="idSegmentacao" labelProperty="dsSegmentacao" /> 
                                            </html:select>
                                        </td>
                                    </tr>
                                </table>
                                
                                <table width="707" align="center">
                                    <tr>
                                        <td width="300" align="center">
                                            Tipo de Linha - Listagem<br>
                                            <html:select name="FormRetorno" property="arrayAdmTipoLinhaExistente" styleClass="SELECT" multiple="true" style="width:300px;" size="4" ondblclick="transfereSelecaoLista(document.listaGruposForm.canaisExistentes, document.listaGruposForm.canaisRelacionados);">
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
                                            <html:select name="FormRetorno" property="arrayAdmTipoLinhaAssociada" styleClass="SELECT" multiple="true" style="width:300px;" size="4" ondblclick="transfereSelecaoLista(document.listaGruposForm.canaisExistentes, document.listaGruposForm.canaisRelacionados);">
                                                <html:options collection="AdmTipoLinhaAssociadaVO" property="idTipoLinha" labelProperty="dsTipoLinha" /> 
                                            </html:select>
                                        </td>
                                    </tr>
                                </table>
                                
                                <table width="707" align="center">
                                    <tr>
                                        <td width="300" align="center">
                                            Natureza - Listagem<br>
                                            <html:select name="FormRetorno" property="arrayAdmPessoaExistente" styleClass="SELECT" multiple="true" style="width:300px;" size="4" ondblclick="transfereSelecaoLista(document.listaGruposForm.canaisExistentes, document.listaGruposForm.canaisRelacionados);">
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
                                            <html:select name="FormRetorno" property="arrayAdmPessoaAssociada" styleClass="SELECT" multiple="true" style="width:300px;" size="4" ondblclick="transfereSelecaoLista(document.listaGruposForm.canaisExistentes, document.listaGruposForm.canaisRelacionados);">
                                                <html:options collection="AdmPessoaAssociadaVO" property="idPessoa" labelProperty="nmPessoa" /> 
                                            </html:select>
                                        </td>
                                    </tr>
                                </table>
                                
                                <table width="707" align="center">
                                    <tr>
                                        <td width="300" align="center">
                                            Carteirização - Listagem<br>
                                            <html:select name="FormRetorno" property="arrayAdmCarteirizacaoExistente" styleClass="SELECT" multiple="true" style="width:300px;" size="4" ondblclick="transfereSelecaoLista(document.listaGruposForm.canaisExistentes, document.listaGruposForm.canaisRelacionados);">
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
                                            <html:select name="FormRetorno" property="arrayAdmCarteirizacaoAssociada" styleClass="SELECT" multiple="true" style="width:300px;" size="4" ondblclick="transfereSelecaoLista(document.listaGruposForm.canaisExistentes, document.listaGruposForm.canaisRelacionados);">
                                                <html:options collection="AdmCarteirizacaoAssociadaVO" property="idCarteirizacao" labelProperty="nmCarteirizacao" /> 
                                            </html:select>
                                        </td>
                                    </tr>
                                </table>
                    
                                <table width="707" align="center">
                                    <tr>
                                        <td width="300" align="center">
                                            Grupo - Listagem<br>
                                            <html:select name="FormRetorno" property="arrayAdmGrupoExistente" styleClass="SELECT" multiple="true" style="width:300px;" size="4" ondblclick="transfereSelecaoLista(document.listaGruposForm.canaisExistentes, document.listaGruposForm.canaisRelacionados);">
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
                                            <html:select name="FormRetorno" property="arrayAdmGrupoAssociado" styleClass="SELECT" multiple="true" style="width:300px;" size="4" ondblclick="transfereSelecaoLista(document.listaGruposForm.canaisExistentes, document.listaGruposForm.canaisRelacionados);">
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
                    <acesso:controlHiddenItem nomeIdentificador="adm_iret_gravar">
                        <img vspace="5" align="middle" id="btSalvar" href="Javascript:salvar();" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_salvar_over.gif" style="border:none;"></img>
                    </acesso:controlHiddenItem>
                    </td>
                </tr>
            </table>
        </form>  
        <script>
        var aOptComponent = new Array(document.forms["retornoIframe"].elements["arrayAdmCanalEntradaExistente"],
                                      document.forms["retornoIframe"].elements["arrayAdmCanalEntradaAssociado"],
                                      document.forms["retornoIframe"].elements["arrayAdmProcedenciaExistente"],
                                      document.forms["retornoIframe"].elements["arrayAdmProcedenciaAssociada"],
                                      document.forms["retornoIframe"].elements["arrayAdmTipoClienteExistente"],
                                      document.forms["retornoIframe"].elements["arrayAdmTipoClienteAssociado"],
                                      document.forms["retornoIframe"].elements["arraySegmentacaoExistente"],
                                      document.forms["retornoIframe"].elements["arraySegmentacaoAssociada"],
                                      document.forms["retornoIframe"].elements["arrayAdmTipoLinhaExistente"],
                                      document.forms["retornoIframe"].elements["arrayAdmTipoLinhaAssociada"],
                                      document.forms["retornoIframe"].elements["arrayAdmPessoaExistente"],
                                      document.forms["retornoIframe"].elements["arrayAdmPessoaAssociada"],
                                      document.forms["retornoIframe"].elements["arrayAdmCarteirizacaoExistente"],
                                      document.forms["retornoIframe"].elements["arrayAdmCarteirizacaoAssociada"],
                                      document.forms["retornoIframe"].elements["arrayAdmGrupoExistente"],
                                      document.forms["retornoIframe"].elements["arrayAdmGrupoAssociado"],
                                      document.forms["retornoIframe"].elements["arrayAdmTipoUfOperadoraExistente"],
                                      document.forms["retornoIframe"].elements["arrayAdmTipoUfOperadoraAssociada"]);
                                      
        var aOptComponentsParent = new Array(parent.document.forms[0].elements["arrayAdmCanalEntradaExistente"],
                                             parent.document.forms[0].elements["arrayAdmCanalEntradaAssociado"],
                                             parent.document.forms[0].elements["arrayAdmProcedenciaExistente"],
                                             parent.document.forms[0].elements["arrayAdmProcedenciaAssociada"],
                                             parent.document.forms[0].elements["arrayAdmTipoClienteExistente"],
                                             parent.document.forms[0].elements["arrayAdmTipoClienteAssociado"],
                                             parent.document.forms[0].elements["arraySegmentacaoExistente"],
                                             parent.document.forms[0].elements["arraySegmentacaoAssociada"],
                                             parent.document.forms[0].elements["arrayAdmTipoLinhaExistente"],
                                             parent.document.forms[0].elements["arrayAdmTipoLinhaAssociada"],
                                             parent.document.forms[0].elements["arrayAdmPessoaExistente"],
                                             parent.document.forms[0].elements["arrayAdmPessoaAssociada"],
                                             parent.document.forms[0].elements["arrayAdmCarteirizacaoExistente"],
                                             parent.document.forms[0].elements["arrayAdmCarteirizacaoAssociada"],
                                             parent.document.forms[0].elements["arrayAdmGrupoExistente"],
                                             parent.document.forms[0].elements["arrayAdmGrupoAssociado"],
                                             parent.document.forms[0].elements["arrayAdmTipoUfOperadoraExistente"],
                                             parent.document.forms[0].elements["arrayAdmTipoUfOperadoraAssociada"]);
                                 
       for(i = 0; i < aOptComponent.length; i++) {
           while(aOptComponentsParent[i].options.length != 0)
                aOptComponentsParent[i].options.remove(0);

           for( x = 0; x < aOptComponent[i].options.length; x++ ) {
                oOption = parent.document.createElement("OPTION");               
                aOptComponentsParent[i].options.add(oOption);
                oOption.innerText = aOptComponent[i].options(x).text;
                oOption.value     = aOptComponent[i].options(x).value;            
            }  
        }

        //Desliga animação
        if( top.dvAnimarAguarde != null ) top.stopAnimation();
    </script> 
</acesso:controlHiddenItem>
</body>