<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="FormFiltro"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formFiltro"/>
<bean:define id="TipoCarteiraAssociadaVO"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formFiltro.tipoCarteiraAssociadaVO"/>
<bean:define id="TipoCarteiraExistenteVO"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formFiltro.tipoCarteiraExistenteVO"/>
<bean:define id="TipoLinhaExistenteVO"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formFiltro.tipoLinhaExistenteVO"/>
<bean:define id="TipoLinhaAssociadaVO"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formFiltro.tipoLinhaAssociadaVO"/>
<bean:define id="TipoPessoaAssociadaVO"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formFiltro.tipoPessoaAssociadaVO"/>
<bean:define id="TipoPessoaExistenteVO"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formFiltro.tipoPessoaExistenteVO"/>
<bean:define id="TipoSegmentacaoAssociadaVO"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formFiltro.tipoSegmentacaoAssociadaVO"/>
<bean:define id="TipoSegmentacaoExistenteVO"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formFiltro.tipoSegmentacaoExistenteVO"/>

<netui-template:template templatePage="/resources/jsp/templateAdmSistClean.jsp">

    <netui-template:section name="bodySection">
        <acesso:controlHiddenItem nomeIdentificador="adm_fltr_ver_pagina">    
        <form action="salvarFiltros" id="formFiltro" name="formFiltro" method="POST">
        <html:hidden name="FormFiltro" property="idBaixa"/>
        
        <script language="Javascript">
            function transfereSelecaoLista(listaOrigem, listaDestino)
            {
                var i;
                for(i = 0; i<listaOrigem.options.length; i++)
                    if(listaOrigem.options[i].selected)
                        listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                        
                for(i = listaOrigem.options.length-1; i>=0; i--)
                    if(listaOrigem.options[i].selected)
                        listaOrigem.options[i] = null;
    
            }
            
            function salvar() {
                
                for ( i = 0; i < document.forms[0].arrayTipoPessoaAssociada.options.length; i++ ){
                    document.forms[0].arrayTipoPessoaAssociada.options[i].selected = true;
                }
                for ( i = 0; i < document.forms[0].arrayTipoLinhaAssociada.options.length; i++ ){
                    document.forms[0].arrayTipoLinhaAssociada.options[i].selected = true;
                }
                for ( i = 0; i < document.forms[0].arraySegmentacaoAssociada.options.length; i++ ){
                    document.forms[0].arraySegmentacaoAssociada.options[i].selected = true;
                }
                for ( i = 0; i < document.forms[0].arrayTipoCarteiraAssociada.options.length; i++ ){
                    document.forms[0].arrayTipoCarteiraAssociada.options[i].selected = true;
                }
                
                document.forms[0].action = 'salvarFiltros.do';
                parent.mostrar_div();
                document.forms[0].submit();
                
            }
            
            
            document.body.style.backgroundColor = '#ededed';
            
            function initPagina() { }
            
        </script>
        
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="10"></div>
        <html:hidden  name="FormFiltro" property="idBaixa"/>
        <table width="730" align="center" cellpadding="0" cellspacing="0">
            
            <tr>
                <td width="330" align="center">
                    Tipos de Pessoas Existentes<br>
                    <html:select name="FormFiltro" property="a" multiple="true" style="width:320px;" size="5">
                        <html:options collection="TipoPessoaExistenteVO" property="idTipoPessoa" labelProperty="dsTipoPessoa" /> 
                    </html:select>
                </td>
                <td width="70" valign="middle" style="padding-top:15px;" align="center">
                    <img id="btRightSimp" onclick="transfereSelecaoLista(document.formFiltro.a, document.formFiltro.arrayTipoPessoaAssociada); return false" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_over.gif'" style="border:none;cursor:hand;"/><br><br>
                    <img id="btLeftSimp" onclick="transfereSelecaoLista(document.formFiltro.arrayTipoPessoaAssociada, document.formFiltro.a); return false" src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_over.gif'" style="border:none;cursor:hand;"/>
                </td>
                <td width="330" align="center">
                    Tipos de Pessoas Associados<br>
                    <html:select name="FormFiltro" property="arrayTipoPessoaAssociada" multiple="true" style="width:320px;" size="5">
                        <html:options collection="TipoPessoaAssociadaVO" property="idTipoPessoa" labelProperty="dsTipoPessoa" /> 
                    </html:select>
                </td>
            </tr>
            
            <tr>
                <td align="center">
                    Tipos de Linhas Existentes<br>
                    <html:select name="FormFiltro" property="b" multiple="true" style="width:320px;" size="5">
                        <html:options collection="TipoLinhaExistenteVO" property="idTipoLinha" labelProperty="dsTipoLinha" /> 
                    </html:select>
                </td>
                <td width="70" valign="middle" style="padding-top:15px;" align="center">                    
                    <img id="btRightSimp" onclick="transfereSelecaoLista(document.formFiltro.b, document.formFiltro.arrayTipoLinhaAssociada); return false" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_over.gif'" style="border:none;cursor:hand;"/><br><br>
                    <img id="btLeftSimp" onclick="transfereSelecaoLista(document.formFiltro.arrayTipoLinhaAssociada, document.formFiltro.b); return false" src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_over.gif'" style="border:none;cursor:hand;"/>
                </td>
                <td align="center">
                    Tipos de Linhas Associados<br>
                    <html:select name="FormFiltro" property="arrayTipoLinhaAssociada" multiple="true" style="width:320px;" size="5">
                        <html:options collection="TipoLinhaAssociadaVO" property="idTipoLinha" labelProperty="dsTipoLinha" /> 
                    </html:select>
                </td>
            </tr>
            
            <tr>
                <td align="center">
                    Segmentações Existentes<br>
                    <html:select name="FormFiltro" property="c" multiple="true" style="width:320px" size="5">
                        <html:options collection="TipoSegmentacaoExistenteVO" property="idSegmentacao" labelProperty="dsSegmentacao" /> 
                    </html:select>
                </td>
                <td width="70" valign="middle" style="padding-top:15px;" align="center">
                    <img id="btRightSimp" onclick="transfereSelecaoLista(document.formFiltro.c, document.formFiltro.arraySegmentacaoAssociada); return false" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_over.gif'" style="border:none;cursor:hand;"/><br><br>
                    <img id="btLeftSimp" onclick="transfereSelecaoLista(document.formFiltro.arraySegmentacaoAssociada, document.formFiltro.c); return false" src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_over.gif'" style="border:none;cursor:hand;"/>
                </td>
                <td align="center">
                    Segmentações Associadas<br>
                    <html:select name="FormFiltro" property="arraySegmentacaoAssociada" multiple="true" style="width:320px" size="5">
                        <html:options collection="TipoSegmentacaoAssociadaVO" property="idSegmentacao" labelProperty="dsSegmentacao" /> 
                    </html:select>
                </td>
            </tr>
            
            <tr>
                <td align="center">
                    Carteiras Existentes<br>
                    <html:select name="FormFiltro" property="d" multiple="true" style="width:320px;" size="5">
                        <html:options collection="TipoCarteiraExistenteVO" property="idTipoCarteira" labelProperty="dsTipoCarteira" /> 
                    </html:select>
                </td>
                <td width="70" valign="middle" style="padding-top:15px;" align="center">
                    <img id="btRightSimp" onclick="transfereSelecaoLista(document.formFiltro.d, document.formFiltro.arrayTipoCarteiraAssociada); return false" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_over.gif'" style="border:none;cursor:hand;"/><br><br>
                    <img id="btLeftSimp" onclick="transfereSelecaoLista(document.formFiltro.arrayTipoCarteiraAssociada, document.formFiltro.d); return false" src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_over.gif'" style="border:none;cursor:hand;"/>
                </td>
                <td align="center">
                    Carteiras Associadas<br>
                    <html:select name="FormFiltro" property="arrayTipoCarteiraAssociada" multiple="true" style="width:320px;" size="5">
                        <html:options collection="TipoCarteiraAssociadaVO" property="idTipoCarteira" labelProperty="dsTipoCarteira" /> 
                    </html:select>
                </td>
            </tr>
            
            <tr>
                <td colspan="3" align="right">
                    <acesso:controlHiddenItem nomeIdentificador="adm_fltr_salvar">    
                        <img vspace="6" hspace="4" id="btSalvar1" onClick="salvar();" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_salvar_over.gif'" style="border:none;cursor:hand;"/>
                    </acesso:controlHiddenItem>
                </td>
            </tr>
            
        </table>
        
        </form>

        <script language="javascript" for="window" event="onload">
        <!--   
            if('<bean:write name="FormFiltro" property="msgError" />' != "")
            {
                alert('<bean:write name="FormFiltro" property="msgError" />');
            }
            parent.oculta_div();

        -->
        </script> 
    </acesso:controlHiddenItem>
    </netui-template:section>
    
</netui-template:template>
