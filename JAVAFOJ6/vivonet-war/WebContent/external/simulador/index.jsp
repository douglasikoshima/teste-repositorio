<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="simuladorForm"/>

<html>
    <head>
        <title>Simulador de Ofertas Siebel - Carga de Premissa</title>
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/xml2json.js"></script>
        <script type="text/javascript" language="javaScript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js" ></script>
        <script type="text/javascript" language="javaScript" src="<%=request.getContextPath()%>/resources/scripts/xtooltip.js" ></script>
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/validacao.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/jquery-1.3.2.min.js"></script>
        <script type="text/javascript">
            jQuery().ready(function(){
                jQuery('#mascara').css('height', jQuery(document).height()).hide();
            });

            onload = function(){
                $('#abaCarregar a').css('color','#000');
                $('#resultadoPesquisa').load('pesquisar.do #tbResultadoPesquisa', '', function(response, status, xhr) {
                    if (status == ConstantesCRM.SERROR) {
                        var msg = "Ocorreu um erro: ";
                        writeMessage(msg + xhr.status + " " + xhr.statusText + '['+'<bean:write name="Form" property="msgStatus"/>'+']');
                    }
                });
            }

            writeMessage = function(s){
                $('#msg').text(s);
            }

            limparCampos = function(o){
                o.reset();
            }

            submitForm = function(f){
                if(validaCarregar(f)){
                    $('#mascara').show();
                    f.submit();
                }
            }

            validaCarregar = function(f){
                var isOK = false;
                if(f.tpVariavel.value!="0"){
                    if(f.idTemplate.value!="0"){
                        if(f.fileUpload.value!=""){
                            if(f.tpVariavel.value=="6"){
                                if(f.idTipoProduto.value!="0" && f.tpItem.value!="0"){
                                    isOK = true;
                                }else{
                                    alert('Por favor, selecione o Nome do Produto e o Tipo.');
                                }
                            }else {
                                isOK = true;
                            }
                        }else{
                            alert('Por favor, selecione um Arquivo.');
                        }
                    }else{
                        alert('Por favor, selecione um Template.');
                    }
                }else{
                    alert('Por favor, selecione uma Variavel.');
                }
                return isOK;
            }

            selAbaPesquisar = function(){
                $('#dvPesquisar').show();
                $('#dvIncluir').hide();
                $('#dvCarregar').hide();
                $('#abaPesquisar a').css('color','#000');
                $('#abaIncluir a').css('color','#81b1e5');
                $('#abaCarregar a').css('color','#81b1e5');
            }

            selAbaIncluir = function(){
                $('#dvPesquisar').hide();
                $('#dvIncluir').show();
                $('#dvCarregar').hide();
                $('#abaPesquisar a').css('color','#81b1e5');
                $('#abaIncluir a').css('color','#000');
                $('#abaCarregar a').css('color','#81b1e5');
            }

            selAbaCarregar = function(){
                $('#dvPesquisar').hide();
                $('#dvIncluir').hide();
                $('#dvCarregar').show();
                $('#abaPesquisar a').css('color','#81b1e5');
                $('#abaIncluir a').css('color','#81b1e5');
                $('#abaCarregar a').css('color','#000');
            }

            selProduto = function(o){
                if(o.value=='6'){
                    $('#tbSelProduto').show();
                }else{
                    o.form.idTipoProduto.value="0";
                    o.form.tpItem.value="0";
                    $('#tbSelProduto').hide();
                }
            }

            sair = function(){
                location.href = '/FrontOfficeWeb/begin.do';
            }

            </script>
        <style type="text/css">
        </style>
        <link rel="stylesheet" type="text/css" href="simulador.css"/>
    </head>
    <body id="vivo_360">

        <div id="vivo360_header">
            <div id="vivo360_header_logo">
                <!--img src="<%=request.getContextPath()%>/resources/images/vivo360_logo.gif" alt="Vivo 360" width="119" height="47" /-->
            </div>
            <div id="vivo360_header_login_info">
            </div>
            <div id="vivo360_header_sair">
                <img src="<%=request.getContextPath()%>/resources/images/vivo360_top_sair.gif" alt="Vivo 360" width="42" height="22" onclick="sair();" style="cursor:pointer;"/>
            </div>
        </div>

        <div id="vivo360_body">
            <h1>Carregar Premissas do Simulador</h1>

            <div id="main">

                <div class="basic" id="list1a">

                    <ul class="tab_list">
                        <li id="abaCarregar"><a href="javascript:selAbaCarregar();"><span>Carregar</span></a></li>
                        <li id="abaIncluir"><a href="javascript:selAbaIncluir();"><span>Incluir</span></a></li>
                        <%--li id="abaPesquisar"><a href="javascript:selAbaPesquisar();"><span>Pesquisar</span></a></li--%>
                    </ul>

                    <div id="dvPesquisar" class="tab_content_body" style="height:160px;display:none;">
                        <form name="frmPesquisar" action="pesquisar.do" onsubmit="return false;" enctype="multipart/form-data" method="post">
                            <table width="100%" class="tbl_bggray" align="center">
                                <tr>
                                    <td valign="top" align="right">Tipo Carteira:&nbsp;</td>
                                    <td valign="top">
                                        <html:select name="Form" property="idCarteira">
                                            <option value="0">-- Selecione --</option>
                                            <html:optionsCollection name="Form" property="optionsCarteira" value="id" label="name"/>
                                        </html:select>
                                    </td>
                                    <td valign="top" align="right">Tipo Variavel:&nbsp;</td>
                                    <td valign="top">
                                        <html:select name="Form" property="tpVariavel">
                                            <option value="0">-- Selecione --</option>
                                            <html:optionsCollection name="Form" property="optionsVariavel" value="id" label="name"/>
                                        </html:select>
                                    </td>
                                </tr>
                                <tr>
                                    <td valign="top" align="right">Produto:&nbsp;</td>
                                    <td valign="top">
                                        <html:select name="Form" property="idTipoProduto">
                                            <option value="0">-- Selecione --</option>
                                            <html:optionsCollection name="Form" property="optionsProduto" value="id" label="name"/>
                                        </html:select>
                                    </td>
                                    <td valign="top" align="right">Tipo:&nbsp;</td>
                                    <td valign="top">
                                        <html:select name="Form" property="tpItem">
                                            <option value="0">-- Selecione --</option>
                                            <html:optionsCollection name="Form" property="optionsTpProduto" value="id" label="name"/>
                                        </html:select>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="4" align="right" height="18">
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="4" align="right">
                                        <div class="separador"></div>
                                        <input type="button" class="input_small" value="Limpar" onmouseup="limparCampos(this.form)" />
                                        <input type="button" class="input_small" value="Pesquisar" onmouseup="submitForm(this.form)" />
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>

                    <div id="dvCarregar" class="tab_content_body" style="height:160px;">
                        <form id="frmCarregar" name="frmCarregar" action="carregar.do" enctype="multipart/form-data" method="post" onsubmit="return false;">
                            <table width="100%" class="tbl_bggray" align="center">
                                <tr>
                                    <td valign="top" align="right">Tipo Variavel:&nbsp;</td>
                                    <td valign="top">
                                        <html:select name="Form" property="tpVariavel" onchange="selProduto(this);">
                                            <option value="0">-- Selecione --</option>
                                            <html:optionsCollection name="Form" property="optionsVariavel" value="id" label="name"/>
                                        </html:select>
                                    </td>
                                    <td colspan="2" valign="top">
                                        <table id="tbSelProduto" cellpadding="0" cellspacing="0" style="display:none;margin:0;">
                                            <tr>
                                                <td valign="top">&nbsp;Produto:&nbsp;</td>
                                                <td valign="top">
                                                    <html:select name="Form" property="idTipoProduto">
                                                        <option value="0">-- Selecione --</option>
                                                        <html:optionsCollection name="Form" property="optionsProduto" value="id" label="name"/>
                                                    </html:select>
                                                </td>
                                                <td valign="top">&nbsp;Tipo:&nbsp;</td>
                                                <td valign="top">
                                                    <html:select name="Form" property="tpItem">
                                                        <option value="0">-- Selecione --</option>
                                                        <html:optionsCollection name="Form" property="optionsTpProduto" value="id" label="name"/>
                                                    </html:select>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td valign="top" align="right">Template:</td>
                                    <td valign="top" colspan="3">
                                        <html:select name="Form" property="idTemplate">
                                            <option value="0">-- Selecione --</option>
                                            <html:optionsCollection name="Form" property="optionsTemplate" value="id" label="name"/>
                                        </html:select>
                                    </td>
                                </tr>
                                <tr>
                                    <td valign="top" align="right">Arquivo:&nbsp;</td>
                                    <td valign="top" colspan="3">
                                        <html:file name="Form" property="fileUpload" style="width:540px;"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="4" align="right">
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="4" align="right">
                                        <div class="separador"></div>
                                        <input type="button" class="input_small" value="Limpar" onmouseup="limparCampos(this.form);" />
                                        <input type="button" class="input_small" value="Carregar" onmouseup="submitForm(this.form);" />
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>

                    <div id="dvIncluir" class="tab_content_body" style="height:160px;display:none;">
                        <form name="frmIncluir" action="incluir.do" enctype="multipart/form-data" method="post" onsubmit="return false;">
                            <table width="100%" class="tbl_bggray" align="center">
                                <tr>
                                    <td valign="top" align="right">Nome:&nbsp;</td>
                                    <td valign="top">
                                        <input type="text" id="nmTemplate" name="nmTemplate" size="80" maxlength="50"/>
                                    </td>
                                    <td valign="top" align="right">Sigla:&nbsp;</td>
                                    <td valign="top">
                                        <input type="text" id="sgTemplate" name="sgTemplate" size="5" maxlength="5"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td valign="top" align="right">CNPJ:&nbsp;</td>
                                    <td valign="top">
                                        <input type="text" id="nrCnpj" name="nrCnpj" size="20" maxlength="18"/>
                                    </td>
                                    <td valign="top" align="right" nowrap>Tipo Carteira:&nbsp;</td>
                                    <td valign="top">
                                        <html:select name="Form" property="idCarteira">
                                            <option value="0">-- Selecione --</option>
                                            <html:optionsCollection name="Form" property="optionsCarteira" value="id" label="name"/>
                                        </html:select>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="4" align="right">
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="4" align="right">
                                        <div class="separador"></div>
                                        <input type="button" class="input_small" value="Limpar" onmouseup="limparCampos(this.form);" />
                                        <input type="button" class="input_small" value="Incluir" onmouseup="submitForm(this.form);" />
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>

                    <div class="legendas">
                        <table class="tbl_bggray" cellspacing="2" cellpadding="2" border="0">
                            <tr>
                                <td><span id="msg"></span></td>
                            </tr>
                        </table>
                    </div>

                    <a class="table_title">Templates</a>
                    <div id="resultado" class="table_body" style="height:220px;">
                        <div id="resultadoPesquisa">
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <div id="mascara"></div>
    </body>
</html>