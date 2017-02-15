<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="FormCalendario"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formCalendario"/>

<bean:define id="aTipoFeriado" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="calendarioForm.listaTipoFeriado"/>
<bean:define id="aDescFeriado" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="calendarioForm.listaDescFeriado"/>
<bean:define id="aUfsExist"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="calendarioForm.listaUfsVOExistentes"/>
<bean:define id="aUfsRel"      name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="calendarioForm.listaUfsVORelacionadas"/>

   

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Manter Calendário"/>
    <netui-template:setAttribute name="modulo" value="Administração de Sistemas"/>

    <netui-template:section name="headerSection">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
    </netui-template:section>

    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="adm_cfer_verpagina">

    <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/calendar.js"></script>
    <script>
        
        function mostraJanelaDetalhe(){
            var x = document.getElementById("detalhe");
            x.style.visibility = 'visible';
            var b = document.getElementById("bg");
            b.style.visibility = 'visible';
        }
        
        
        function abreJanela(){
            mostraJanelaDetalhe()
            divFrame = document.getElementById('frameDetalhe');
            divFrame.src = 'pesquisarFeriado.jsp';
        }

        function fechaJanela(){
            var x = document.getElementById("detalhe");
            x.style.visibility = 'hidden';
            divFrame = document.getElementById('frameDetalhe');
            divFrame.src = 'nada.html';
            var b = document.getElementById("bg");
            b.style.visibility = 'hidden';
        }
    
        function descricao(esconde) {
            document.divcampo = document.all['divcampo'];
            
            var ctext = "<input type='text' name='descricao' size='40' style='text-indent:8px;width:300px' value='' class='input'/>";

            var cselect = "<select name='descricao' class='SELECT' style='text-indent:8px;width:301px'>" +
                                "<option value='0'>Confraternização Universal</option>" +
                                "<option value='1'>Carnaval</option>" +
                                "<option value='2'>Paixão</option>" +
                                "<option value='3'>Pascoa</option>" +
                                "<option value='4'>Tiradentes</option>" +
                                "<option value='5'>Dia do Trabalho</option>" +
                                "<option value='6'>Corpus Christi</option>" +
                                "<option value='7'>Independência do Brasil</option>" +
                            "</select>";
            
            if (esconde == 0){
                document.divcampo.innerHTML = ctext;
            }
            else { 
                document.divcampo.innerHTML=cselect;
            }
        }
        
    </script>
    <script language="javascript" for="window" event="onload">
    <!--   
        if('<bean:write name="FormCalendario" property="msgError" />' != "")
        {
            alert('<bean:write name="FormCalendario" property="msgError" />');
        }
    -->
    </script> 

        <!-- Menu de Administração de Sistemas -->
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>

            <vivo:sessao id="qdMain" height="468" width="790" label="Calendário - Manutenção de Feriados" operacoes="Cadastrar / Alterar">
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="10"></div>
                <table align="center" width="757" border="0" cellpadding="0" cellspacing="0" class="tbl_bgGray">
                    <tr>
                        <td colspan="6"><div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="3"></div></td>                            
                    </tr>
                    <tr>
                        <td>                                    
                            <netui:label value="Data:" styleClass="tblEstatica_campoNome"/>
                        </td>
                        <td style="text-align:left;" align="left">
                            <input style="text-indent:8px;" type="text" id="dtAbertura" name="dtAbertura" size="10" maxlength="10" disabled onfocus="Javascript:this.blur();"/><img src="<%=request.getContextPath()%>/resources/images/calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dtAbertura', '%d/%m/%Y');">
                        </td>
                        <td>
                            &nbsp;<b><netui:label value="Dia da Semana:"/></b>
                            <netui:label style="font-weight:normal;" value="Segunda-feira" styleClass="tblEstatica_campoValor"/>
                        </td>
                    </tr>    
                    <tr>
                        <td>
                            <netui:label value="Descrição: " styleClass="tblEstatica_campoNome"/>
                        </td>
                        <td valign="top" colspan="5"> 
                            <netui:radioButtonGroup dataSource="{}" tagId="tipo2" defaultValue="0">
                                <netui:radioButtonOption styleClass="radio" style="vertical-align:middle;" value="0" onclick="descricao(0);">Nova</netui:radioButtonOption>
                                <netui:radioButtonOption styleClass="radio" style="vertical-align:middle;" value="1" onclick="descricao(1);">Existente</netui:radioButtonOption>
                            </netui:radioButtonGroup>
                        </td>
                    </tr>
                    <tr>
                        <td></td>            
                        <td colspan="2">
                            <div id="divcampo" style="visibility:visible; top: 0px; left: 0px; height: 24px; padding: 0px;">
                                <input type='text' name='descricao' size='40' style='text-indent:8px;width:300px' value='' class='input' maxlength="200"/>
                            </div>
                        </td>                        
                        <td colspan="3">
                            <b>Feriado Móvel:</b>&nbsp;&nbsp;
                            <netui:select dataSource="{}" style="width:50px" styleClass="SELECT">
                                <netui:selectOption value="Sim"/>
                                <netui:selectOption value="Não"/>
                            </netui:select>
                        </td>                       
                    </tr>
                    <tr>
                        <td>
                            <netui:label value="Tipo do Feriado: " styleClass="tblEstatica_campoNome"/>
                        </td>
                        <td colspan="2" class="tblEstatica_campoValor" align="left">
                            <logic:greaterThan name="FormCalend" property="listaTipoFeriadoLength" value="0">
                                <html:select name="FormCalend" property="idTipoFeriado" style="text-indent:3px;width:200px" styleClass="SELECT">
                                    <html:option value="-1">Escolha uma opção...</html:option>
                                    <html:options collection="aTipoFeriado" property="idTipoFeriado" labelProperty="dsTipoFeriado" /> 
                                </html:select>
                            </logic:greaterThan>
                            <logic:equal name="FormCalend" property="listaTipoFeriadoLength" value="0">
                                <html:select name="FormCalend" property="idTipoFeriado" style="text-indent:3px;width:200px" styleClass="SELECT">
                                    <html:option value="-1">Não existem tipos de feriados cadastrados.</html:option>
                                </html:select>
                            </logic:equal>
                        </td>
                        <td align="left">
            <b><netui:label value="Relatório DRC:"/></b>&nbsp;
            <netui:select dataSource="{}" style="width:50px" styleClass="SELECT">
                <netui:selectOption value="Sim"/>
                <netui:selectOption value="Não"/>
            </netui:select>
            </td>
                        <td>&nbsp;</td>
                        <td class="tblEstatica_campoValor">&nbsp;</td>
                    </tr>
                    <tr>
                        <td colspan="6"><div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div></td>                            
                    </tr>
                </table>
                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="10"></div>
                <center>
                <vivo:quadro width="757" height="100" id="UFs" scroll="no" label="Relacione as UFs para as quais o feriado cadastrado é válido">
                <table width="100%" border="0" cellspacing="1" cellpadding="1" align="center">
                            <tr>
                                <td width="47%" align="center"><b>UFs existentes</b></td>
                                <td width="5%">&nbsp;</td>
                                <td width="47%" align="center"><b>UFs relacionadas</b></td>
                            </tr>
                            <tr>
                                <td align="center">&nbsp;
                                <html:select name="FormCalend" property="listaUfsExist" multiple="true" size="4" style="text-indent:3px;width:200px" styleClass="SELECT">
                                    <html:options collection="aUfsExist" property="idUF" labelProperty="nmUF" /> 
                                </html:select>
                                </td>
                                <td width="5%">
                                    <table width="100%" border="0" cellspacing="1" cellpadding="0" align="center">
                                        <tr>
                                            <td align="center">
                                                <img id="btRightSimp" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_over.gif'" style="border:none;cursor:hand;"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="center">
                                                &nbsp;
                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="center">
                                            	<img id="btRightSimp" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_over.gif'" style="border:none;cursor:hand;"/>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                                <td align="center">&nbsp;
                                <html:select name="FormCalend" property="listaUfsRel" multiple="true" size="4" style="text-indent:3px;width:200px" styleClass="SELECT">
                                    <html:options collection="aUfsRel" property="idUF" labelProperty="nmUF" /> 
                                </html:select>
                                </td>
                            </tr>                            
                        </table>
                </vivo:quadro>
                </center>
               
                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="10"></div>            
                
                <center>
                <vivo:quadro width="757" height="100" id="UFs" scroll="no" label="Relacione os munic&iacute;pios para os quais o feriado cadastrado é válido">
                    <table border="0" cellspacing="1" cellpadding="1" align="center" width="100%">
                        <tr>
                            <td align="center">
                                <table width="100%" border="0" cellspacing="1" cellpadding="1" align="center">
                                    <tr>
                                        <td width="47%" align="center"><b>Municípios existentes</b></td>
                                        <td width="5%">&nbsp;</td>
                                        <td width="47%" align="center"><b>Municípios relacionados</b></td>
                                    </tr>
                                    <tr>
                                        <td align="center">&nbsp;
                                            <netui:select dataSource="{}" multiple="true" style="width:200px" styleClass="SELECT" size="4">
                                                <netui:selectOption value="Município 1"/>
                                                <netui:selectOption value="Município 2"/>
                                                <netui:selectOption value="Município 5"/>
                                                <netui:selectOption value="Município 6"/>
                                                <netui:selectOption value="Município 7"/>
                                                <netui:selectOption value="Município 8"/>
                                                <netui:selectOption value="Município 9"/>
                                                <netui:selectOption value="Município 10"/>
                                                <netui:selectOption value="Município 13"/>
                                                <netui:selectOption value="Município 14"/>
                                                <netui:selectOption value="Município 15"/>
                                                <netui:selectOption value="Município 16"/>
                                                <netui:selectOption value="Município 17"/>
                                                <netui:selectOption value="Município 18"/>
                                                <netui:selectOption value="Município 19"/>
                                                <netui:selectOption value="Município 20"/>
                                            </netui:select>
                                        </td>
                                        <td width="5%">
                                            <table width="100%" border="0" cellspacing="1" cellpadding="0" align="center">
                                                <tr>
                                                    <td align="center">
                                                    	<img id="btRightSimp" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_over.gif'" style="border:none;cursor:hand;"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="center">
                                                        &nbsp;
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="center">
                                                    	<img id="btRightSimp" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_over.gif'" style="border:none;cursor:hand;"/>
                                                    </td>
                                                </tr>
                                            </table>
                                        </td>
                                        <td align="center">&nbsp;
                                            <netui:select dataSource="{}" multiple="true" style="width:200px" styleClass="SELECT" size="4">
                                                <netui:selectOption value="Município 3"/>
                                                <netui:selectOption value="Município 4"/>
                                                <netui:selectOption value="Município 11"/>
                                                <netui:selectOption value="Município 12"/>                                                
                                            </netui:select>
                                        </td>
                                    </tr>
                                    <tr><td height="4"></td></tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                
                
                </vivo:quadro>
                </center>
                    
                    
                    
            
            <br>
            <table width="757" border="0" cellspacing="0" cellpadding="0" align="center">
                <tr>                                    
                    <td align="right">
                    <acesso:controlHiddenItem nomeIdentificador="adm_cfer_pesq">
                        <img onClick="location.href='pesquisarFeriado.jsp'" id="btPesquisar" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif'"   onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif'"  style="border:none;cursor:hand" border ="0"/>
                    </acesso:controlHiddenItem>
                    <acesso:controlHiddenItem nomeIdentificador="adm_cfer_gravar">
                        <img id="btSalvar" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_salvar_over.gif'" style="border:none;cursor:hand;"/>
                    </acesso:controlHiddenItem>
                    </td>
                </tr>
            </table>
            </vivo:sessao>
            
            <div id="detalhe" style="z-index:999 ;visibility: hidden; position:absolute; top:36; left:36; width:728; height:528; border-style:solid; border-width:1px; border-color:#adadad; background-color:#e3ecf4;">
                <table width="728" height="21" cellpadding="0" cellspacing="0" background="/FrontOfficeWeb/resources/images/window_topbg.gif" bgcolor="#0066cb" class="tbl_titulo">
                    <tr>
                        <td id="titleBar" style="cursor:move" width="540" height="21">Pesquisa de Feriados </td>
                        <td width="64" valign="top" background="/FrontOfficeWeb/resources/images/window_topbtbg.gif">
                            <div align="right">
                                <img src="/FrontOfficeWeb/resources/images/window_fechar.gif" hspace="3" onClick="fechaJanela();" style="cursor:hand;" alt="Fechar">
                            </div>
                        </td>
                    </tr>
                </table>
                <table width="728" cellpadding="0" cellspacing="0">
                    <tr>
                        <td>
                            <iframe id="frameDetalhe" src="/FrontOfficeWeb/admsistemas/nada.html" frameborder="0" width="728" height="507"></iframe>
                        </td>
                    </tr>
                </table>
            </div>
            <div id="bg" style="z-index:998 ;visibility: hidden; position:absolute; top:0; left:0; width:100%; height:100%; border-style:solid; background-image:url(/FrontOfficeWeb/resources/images/window_bg.gif); border-width:1px; border-color:#adadad;"></div>
                <table align='center' width='757' border='0' cellpadding='0' cellspacing='0'>
                   <tr>                       
                       <td align='left' valign='top'>
                           <img hspace="8" vspace="10" id="btVoltar" onClick="window.location.href='/FrontOfficeWeb/index.jsp';" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_voltar_over.gif'" style="border:none;cursor:hand;"/>
                       </td>
                   </tr>
                </table>
            
               
            
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>
