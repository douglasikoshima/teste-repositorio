<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!-- COMBOS FORMULARIO -->
<bean:define id="FormArvoreConsulta" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreConsulta"/>

<!-- COMBOS EXISTENTES -->
<bean:define id="atipoSegmentacao" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreConsulta.containerCombosExist.admTipoSegmentacaoVOArray"/>
<bean:define id="atipoCarteira" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreConsulta.containerCombosExist.admTipoCarteiraSimplVOArray"/>
<bean:define id="aProcedencia" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreConsulta.containerCombosExist.admProcedenciaVOArray"/>

<!-- COMBOS ASSOCIADOS -->
<bean:define id="aatipoSegmentacao" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreConsulta.containerCombosAssoc.admTipoSegmentacaoVOArray"/>
<bean:define id="aatipoCarteira" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreConsulta.containerCombosAssoc.admTipoCarteiraSimplVOArray"/>
<bean:define id="aaProcedencia" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreConsulta.containerCombosAssoc.admProcedenciaVOArray"/>

    <head>
        <title>Arvore Parâmetros</title>
        <link href="<%=request.getContextPath()%>/resources/css/xtree.css" type="text/css" rel="stylesheet"/>
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <link rel="stylesheet" type="text/css" href="/FrontOfficeWeb/resources/css/calendar.css">
        <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/calendar.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    </head>

    <body>

    <script language="JavaScript">
            
            function filtarArvore()
            {
                selecionaCombos(true);
                document.forms[0].target = '';
                document.forms[0].action = 'filtraArvore.do';
                parent.mostrar_div();
                document.forms[0].submit();
            }
            
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
            
            
            function selecionaCombos(op)
            {
            
                var componentName = new Array(  "idSegmentacaoAssocArray",
                                                "idSegmentacaoExistArray",
                                                "idTipoCarteiraAssocArray",
                                                "idTipoCarteiraExistArray",
                                                "idProcedenciaExistArray",
                                                "idProcedenciaAssocArray");
                
                for(x =0;x < componentName.length ; x++)
                    for(z =0;z < eval("document.forms[0]."+componentName[x]).options.length;z++)
                    {
                        eval("document.forms[0]."+componentName[x]).options[z].selected = op;
                    }
                
            }
            
            function limpar()
            {
                var form  = document.forms[0];
                
                form.limpaForm.value  = 'limpa';
                form.target = '';
                form.action = 'carregaFiltro.do';
                form.submit();

            }

    </script>

    <form method="post" name="myForm" action="montaLista.do">

        <html:hidden name="FormArvoreConsulta" property="limpaForm"/>

<table width="100%" border="1" class="tbl_bgGray">
    <tr>
        <td align="center">
    
           <vivo:quadro width="770" height="410" id="qdrSegmentacao" scroll="no" label="Filtros">
                <table align="center" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                        <td width="310" align="center" valign="top">
                            Segmentação Existente<br>
                            <html:select name="FormArvoreConsulta" property="idSegmentacaoExistArray" multiple="true" style="width:230px;" size="7" ondblclick="transfereSelecaoLista(document.forms[0].idSegmentacaoExistArray, document.forms[0].idSegmentacaoAssocArray);">
                                <html:options collection="atipoSegmentacao" property="idSegmentacao" labelProperty="dsSegmentacao" /> 
                            </html:select>
                        </td>
                        <td width="70" align="center" valign="bottom">
                            <img id="btRightSimp" onmouseup="transfereSelecaoLista(document.forms[0].idSegmentacaoExistArray, document.forms[0].idSegmentacaoAssocArray);" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" border="0">
                            <img class="button" id="btLeftSimp" onmouseup="transfereSelecaoLista(document.forms[0].idSegmentacaoAssocArray, document.forms[0].idSegmentacaoExistArray);"src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_leftaln_over.gif" border="0">
                        </td>
                        <td width="310" align="center" valign="top">
                           Segmentação Associada<br>
                            <html:select name="FormArvoreConsulta" property="idSegmentacaoAssocArray" multiple="true" style="width:230px;" size="7" ondblclick="transfereSelecaoLista(document.forms[0].idSegmentacaoAssocArray, document.forms[0].idSegmentacaoExistArray);">
                                <html:options collection="aatipoSegmentacao" property="idSegmentacao" labelProperty="dsSegmentacao" /> 
                            </html:select>
                        </td>
                    </tr>
                </table>
                
                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="4"></div>
                    
                    <table align="center" border="0">
                        <tr>
                            <td width="310" align="center" >
                               Carteirização Existente<br>
                                <html:select name="FormArvoreConsulta" property="idTipoCarteiraExistArray" multiple="true" style="width:230px;" size="7" ondblclick="transfereSelecaoLista(document.forms[0].idTipoCarteiraExistArray, document.forms[0].idTipoCarteiraAssocArray);">
                                    <html:options collection="atipoCarteira" property="idTipoCarteira" labelProperty="dsTipoCarteira" /> 
                                </html:select>
                            </td>
                            <td width="70" align="center" valign="bottom">
                                <img class="button" id="btRightSimp" onmouseup="transfereSelecaoLista(document.forms[0].idTipoCarteiraExistArray, document.forms[0].idTipoCarteiraAssocArray);" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_rightaln_over.gif" border="0"><br><br>
                                <img class="button" id="btLeftSimp" onmouseup="transfereSelecaoLista(document.forms[0].idTipoCarteiraAssocArray, document.forms[0].idTipoCarteiraExistArray);"src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_leftaln_over.gif" border="0">
                            </td>
                            <td width="310" align="center" >
                               Carteirização Associado<br>
                                <html:select name="FormArvoreConsulta" property="idTipoCarteiraAssocArray" multiple="true" style="width:230px;" size="7" ondblclick="transfereSelecaoLista(document.forms[0].idTipoCarteiraAssocArray, document.forms[0].idTipoCarteiraExistArray);">
                                    <html:options collection="aatipoCarteira" property="idTipoCarteira" labelProperty="dsTipoCarteira" /> 
                                </html:select>
                            </td>
                        </tr>
                    </table>

                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="4"></div>

                    <table align="center" border="0" >
                        <tr>
                            <td width="310" align="center">
                               Procedência Existente<br>
                                <html:select name="FormArvoreConsulta" property="idProcedenciaExistArray" multiple="true" style="width:230px;" size="7" ondblclick="transfereSelecaoLista(document.forms[0].idProcedenciaExistArray, document.forms[0].idProcedenciaAssocArray);">
                                    <html:options collection="aProcedencia" property="idProcedencia" labelProperty="nmProcedencia" /> 
                                </html:select>
                            </td>
                            <td width="70" align="center" valign="bottom">
                                <img class="button" id="btRightSimp" onmouseup="transfereSelecaoLista(document.forms[0].idProcedenciaExistArray, document.forms[0].idProcedenciaAssocArray);" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_rightaln_over.gif" border="0"><br><br>
                                <img class="button" id="btLeftSimp" onmouseup="transfereSelecaoLista(document.forms[0].idProcedenciaAssocArray, document.forms[0].idProcedenciaExistArray);"src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_leftaln_over.gif" border="0">
                            </td>
                            <td width="310" align="center" >
                               Procedência Associado<br>
                                <html:select name="FormArvoreConsulta" property="idProcedenciaAssocArray" multiple="true" style="width:230px;" size="7" ondblclick="transfereSelecaoLista(document.forms[0].idProcedenciaAssocArray, document.forms[0].idProcedenciaExistArray);">
                                    <html:options collection="aaProcedencia" property="idProcedencia" labelProperty="nmProcedencia" /> 
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td align="right" colspan="4">
                                <br><br>
                                <img vspace="5" onClick="return limpar(); return false;" src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_limpar_over.gif" style="border:none;"/>
                                <img vspace="5" onClick="filtarArvore();" src="/FrontOfficeWeb/resources/images/bt_filtrar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_filtrar_over.gif" style="border:none;"/>
                            </td>
                        </tr>
                    </table>

               </vivo:quadro>             
            </td>
        </tr>
    </table>
    </form>
        
    </body>

    <script language="javascript" for="window" event="onload">
    <!--   
        if('<bean:write name="FormArvoreConsulta" property="msgError" />' != "")
        {
            alert('<bean:write name="FormArvoreConsulta" property="msgError" />');
        }
        selecionaCombos(false);
        parent.oculta_div();
    -->
    </script> 