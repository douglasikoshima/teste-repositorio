<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv />

<bean:define id="ShowAparelhoForm" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="showAparelhoForm" />
<bean:define id="ItemListaVOMarca" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaMarcasVO.itemListaVOArray" />
<bean:define id="ItemListaVOCores" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaCoresVO.itemListaVOArray" />
<bean:define id="ItemListaVOTipo" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"  property="listaTipoVO.itemListaVOArray" />
<bean:define id="ListaAparelhosVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaAparelhosVO.aparelhoVOArray" />
<bean:define id="ListaCoresSelecionadasVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaCoresSelecionadasVO.itemListaVOArray" />

<netui-template:template templatePage="/resources/jsp/templateRetencao.jsp">
    <netui-template:section name="headerSection">
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/Formatacao.js"></script>
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <script language="JavaScript">
        function addCor(obj){
            erro = false;
            if(document.showAparelhoForm.elements['corSelecionada'].value == ""){
                alert("Selecione uma cor!");
                return false;
            }

            if(document.forms[0].codigoSAP.value == ""){
                alert("Código SAP é obrigatório!");
                return false;
            }

            if(document.forms[0].valorAparelho.value == "" || document.forms[0].valorAparelho.value == "0,00"){
                alert("Valor do Aparelho é obrigatório!");
                return false;
            }


            if(document.showAparelhoForm.elements['wlw-radio_button_group_key:{pageContext.ListaCoresSelecionadasVO}'] && document.showAparelhoForm.elements['wlw-radio_button_group_key:{pageContext.ListaCoresSelecionadasVO}'].length){
                for(i=0;i<document.showAparelhoForm.elements['wlw-radio_button_group_key:{pageContext.ListaCoresSelecionadasVO}'].length;i++){
                    if(document.showAparelhoForm.elements['wlw-radio_button_group_key:{pageContext.ListaCoresSelecionadasVO}'][i].value == document.showAparelhoForm.corSelecionada[document.showAparelhoForm.corSelecionada.selectedIndex].text){
                        erro = true;
                    }
                }
            }else if(document.showAparelhoForm.elements['wlw-radio_button_group_key:{pageContext.ListaCoresSelecionadasVO}']){
                if(document.showAparelhoForm.elements['wlw-radio_button_group_key:{pageContext.ListaCoresSelecionadasVO}'].value == document.showAparelhoForm.corSelecionada[document.showAparelhoForm.corSelecionada.selectedIndex].text){
                    erro = true;
                }
            }
            
            if(erro){
                alert("Cor já selecionada!")
            
            }else{
                /*
                top.frameApp.mostrar_div();
                valor = obj.href.split("?");
                valor[1]?action = document.showAparelhoForm.action + "?" + valor[1]:action = document.showAparelhoForm.action;
                anchor_submit_form("Netui_Form_0",action);
                */
                obj.href = obj.href + "&idCor=" + document.showAparelhoForm.corSelecionada[document.showAparelhoForm.corSelecionada.selectedIndex].value;
                obj.href = obj.href + "&desCor=" + document.showAparelhoForm.corSelecionada[document.showAparelhoForm.corSelecionada.selectedIndex].text;
                obj.href = obj.href + "&cdSAP=" + document.forms[0].codigoSAP.value;
                obj.href = obj.href + "&vlAparelho=" + document.forms[0].valorAparelho.value;
                enviarForm(obj);
            }
        }

        function excluir(obj){
            if (confirm('Deseja realmente excluir este registro?')){
                //valor = obj.href.split("?");
                //valor[1]?action = document.showAparelhoForm.action + "?" + valor[1]:action = document.showAparelhoForm.action;
                //anchor_submit_form("Netui_Form_0",action);
                //document.forms[0].action = obj.href;
                //document.forms[0].submit();
                //top.frameApp.mostrar_div();
                enviarForm(obj)
            }
        }

        function salvar(obj){
            document.showAparelhoForm.elements["modelo"].value = trim(document.showAparelhoForm.elements["modelo"].value);
            if(document.showAparelhoForm.elements["modelo"].value == ""){
                alert("Favor preencher um Modelo!");
            }else if(document.showAparelhoForm.idMarca.value == "netui_null" || document.showAparelhoForm.idMarca.value == ""){
                alert("Favor selecionar uma Marca!");
            }else if(document.all("cor_flag") == null){
                alert("Favor adicionar uma Cor!");
            }else if(document.forms[0].tipoChip.value == ""){
                alert("Favor selecionar um Tipo de Chip!");
            }else{
                /*
                top.frameApp.mostrar_div();
                valor = obj.href.split("?");
                valor[1]?action = document.showAparelhoForm.action + "?" + valor[1]:action = document.showAparelhoForm.action;
                anchor_submit_form("Netui_Form_0",action);
                */
                enviarForm(obj);
            }
        }

        semCaracterEspeciais = new RegExp("[0-9a-zA-z.% ]");

        function validacionStrEspecial(obj){
            valor = obj.value;
            for(i=0;i<valor.length;i++){
                if(!semCaracterEspeciais.test(valor.charAt(i))){
                    valor = valor.substring(0,i) + valor.substring(i+1,valor.length);
                    i = -1;
                }
            }
            obj.value = valor;
        }

        function preValidaKey(){
            if (window.event.keyCode == 13){
                window.event.keyCode = 0;
                return false;
            }
        }

        function enviarForm(obj,param){
            if(param == 'p'){
                document.showAparelhoForm.elements["modelo"].value = trim(document.showAparelhoForm.elements["modelo"].value);
            }
            //setNomeModelo();
            top.frameApp.mostrar_div();
            document.forms[0].action = obj.href;
            document.forms[0].submit();
            //anchor_submit_form(netuiName, newAction)
        }

        function verificaTipoChip(objeto){
            //f = document.forms[0];
            //var erro = false;
            //if(objeto.name == "inChipAvulso"){
                //if(objeto.value == 1 && f.inChipPreProgramado.value == 1) erro = true;
            //}else{
                //if(objeto.value == 1 && f.inChipAvulso.value == 1) erro = true;
            //}
            //if(erro){
                //alert("Não é possível configurar um aparelho com Chip avulso \ne pré-programado ao mesmo tempo!");
                //objeto.value = 2;
            //}
        }

        setNomeModelo = function(){
            //f = document.forms[0];
            //var nmModelo = f["{actionForm.modelo}"].value;
            //extractTextChip();
            //if(f.inChipAvulso.value == 1){
                //if(nmModelo.indexOf("AVULSO")==-1)
                    //nmModelo += " (COM CHIP AVULSO)";
            //}else if(f.inChipPreProgramado.value == 1){
                //if(nmModelo.indexOf("PRÉ-PRO")==-1)
                    //nmModelo += " (COM CHIP PRÉ-PROGRAMADO)";
            //}
            //f["{actionForm.modelo}"].value = nmModelo;
        }

        function extractTextChip(){
            //f = document.forms[0];
            //var nmModelo = f["{actionForm.modelo}"].value;
            //if(nmModelo.indexOf("(COM CHIP AVULSO)")>-1)
                //nmModelo = nmModelo.substr(0,nmModelo.indexOf("(COM CHIP AVULSO)"))
            //if(nmModelo.indexOf("(COM CHIP PRÉ-PROGRAMADO)")>-1)
                //nmModelo = nmModelo.substr(0,nmModelo.indexOf("(COM CHIP PRÉ-PROGRAMADO)"))
            //f["{actionForm.modelo}"].value = nmModelo;
        }
    </script>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
            top.frameApp.oculta_div();
        </SCRIPT>
    </netui-template:section>
    <netui-template:section name="bodySection">
        <acesso:controlHiddenItem nomeIdentificador="fid_aplh_verpagina">
        <form name="showAparelhoForm" action="listarAparelhos.do" onKeyPress="preValidaKey()" method="post">
            <html:hidden name="ShowAparelhoForm" property="idAparelho" />
            <html:hidden name="ShowAparelhoForm" property="isEdit" />
            <table width='770' border='0' cellpadding='0' cellspacing='0'>
                <tr>
                    <td width="370" valign="top">
                    <table width="370">
                        <tr>
                            <td>
                            <table>
                                <tr>
                                    <td><b>Modelo:</b></td>
                                    <td><html:text size="40" maxlength="255" name="ShowAparelhoForm" property="modelo" /></td>
                                </tr>
                                <tr>
                                    <td><b>Marca:</b></td>
                                    <td style="padding-left: 4px">
                                        <html:select name="ShowAparelhoForm" property="idMarca" style="width:222" styleClass="SELECT" size="1">
                                            <option value="">Selecione</option>
                                            <html:options collection="ItemListaVOMarca" property="id" labelProperty="descricao" />
                                        </html:select>
                                    </td>
                                </tr>
                                <tr>
                                    <td><b>Tipo do Chip:</b></td>
                                    <td style="padding-left: 4px">
                                        <html:select name="ShowAparelhoForm" property="tipoChip" style="width:222" styleClass="SELECT" size="1">
                                            <option value="">Selecione</option>
                                            <html:options collection="ItemListaVOTipo" property="id" labelProperty="descricao" />
                                        </html:select>
                                    </td>
                                </tr>
                                <tr>
                                    <td><b>Cores do Aparelho:</b></td>
                                    <td style="padding-left: 4px">
                                        <html:select name="ShowAparelhoForm" property="corSelecionada" style="width:197" styleClass="SELECT" size="1">
                                            <option value="">Selecione</option>
                                            <html:options collection="ItemListaVOCores" property="id" labelProperty="descricao" />
                                        </html:select>
                                        <script language="Javascript">
                                            for(i=0;i<document.showAparelhoForm.corSelecionada.options.length;i++){
                                                if(document.showAparelhoForm.corSelecionada.options[i].value == "" && document.showAparelhoForm.corSelecionada.options[i].text != "Selecione"){
                                                    document.showAparelhoForm.corSelecionada.options[i] = null;
                                                }
                                            }
                                        </script>
                                        <img class="button" src="/FrontOfficeWeb/resources/images/bt_right_sml_nrml.gif" href="adicionarCor.do?acao=addCor" border="0" onClick="addCor(this);return false;" />
                                    </td>
                                </tr>
                                <tr>
                                    <td><b>Código SAP:</b></td>
                                    <td><html:text property="codigoSAP" size="40" maxlength="14" name="ShowAparelhoForm" /></td>
                                </tr>
                                <tr>
                                    <td><b>Valor do Aparelho:</b></td>
                                    <td>
                                        <html:text name="ShowAparelhoForm" 
                                                   property="valorAparelho" value="0,00" 
                                                   style="width:100px;text-align:right;" size="40" maxlength="8"
                                                   onkeypress="return exibirValorFormatado(event);"
                                                   onkeydown="return capturaCodTecla(event);" />
                                    </td>
                                </tr>
                            </table>
                            </td>
                        </tr>
                    </table>
                    </td>
                    <td width="400">
                        <table width="100%" height="100%">
                            <tr>
                                <td>
                                    <vivo:tbTable selectable="true" layoutWidth="385" layoutHeight="80" tableWidth="385" styleId="cor" sortable="true">
                                        <vivo:tbHeader>
                                            <vivo:tbHeaderColumn align="left" width="30%" type="string">Cor</vivo:tbHeaderColumn>
                                            <vivo:tbHeaderColumn align="left" width="30%" type="string">Código SAP</vivo:tbHeaderColumn>
                                            <vivo:tbHeaderColumn align="left" width="30%" type="string">Valor do Aparelho</vivo:tbHeaderColumn>
                                            <vivo:tbHeaderColumn align="left" width="10%" type="">&nbsp;</vivo:tbHeaderColumn>
                                        </vivo:tbHeader>
                                        <vivo:tbRows>
                                            <logic:iterate name="ListaCoresSelecionadasVO" id="item" indexId="c">
                                                <vivo:tbRow key="corLinha">
                                                    <vivo:tbRowColumn><bean:write name="item" property="descricao" /></vivo:tbRowColumn>
                                                    <vivo:tbRowColumn><bean:write name="item" property="codigoSAP" /></vivo:tbRowColumn>
                                                    <vivo:tbRowColumn><bean:write name="item" property="valorAparelho" /></vivo:tbRowColumn>
                                                    <vivo:tbRowColumn>
                                                        <acesso:controlHiddenItem nomeIdentificador="fid_aplh_excluircor">
                                                            <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif"
                                                                class="button"
                                                                href="removerCor.do?acao=delCor&idCor=<bean:write name="item" property="id" />&desCor=<bean:write name="item" property="descricao" />&indexCor=<%=c%>&cdSAP=<bean:write name="item" property="codigoSAP" />&vlAparelho=<bean:write name="item" property="valorAparelho" />"
                                                                id="cor_flag" border="0" onClick="enviarForm(this);return false;" />
                                                        </acesso:controlHiddenItem>
                                                        <div style="position: absolute; top: 0px; left: 0px; width: 0px; height: 0px; visibility: hidden">
                                                        <logic:iterate id="item" name="ListaCoresSelecionadasVO" indexId="c">
                                                            <input type="radio" name="wlw-radio_button_group_key:{pageContext.ListaCoresSelecionadasVO}" value="<bean:write name="item" property="descricao" />" />
                                                        </logic:iterate></div>
                                                    </vivo:tbRowColumn>
                                                </vivo:tbRow>
                                            </logic:iterate>
                                        </vivo:tbRows>
                                    </vivo:tbTable>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td nowrap valign="bottom" colspan="2" align="right">
                    <acesso:controlHiddenItem nomeIdentificador="fid_aplh_pesquisar">
                        <img vspace="4" 
                             href="limpar.do" class="button" 
                             src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" border="0" 
                             onClick="enviarForm(this);return false;" />
                        <img vspace="4" href="listarAparelhos.do?acao=consultar" class="button"
                             src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" border="0" 
                             onClick="enviarForm(this,'p'); return false;" />
                    </acesso:controlHiddenItem>
                    <acesso:controlHiddenItem nomeIdentificador="fid_aplh_incluir">
                        <img id="incluir" vspace="4" class="button" hspace="3" href="persistirAparelho.do?acao=salvar"
                             src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif" 
                             onClick="salvar(this);return false;" border="0" />
                    </acesso:controlHiddenItem></td>
                </tr>
            </table>
            <table width='770' border='0' cellpadding='0' cellspacing='0' align="center">
                <tr>
                    <td valign="top">
                        <vivo:tbTable selectable="true" onRowClick="" layoutWidth="753" layoutHeight="170" tableWidth="753" styleId="aparelho" sortable="true">
                            <vivo:tbHeader>
                                <vivo:tbHeaderColumn align="left" width="45%" type="string">Marca</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="left" width="45%" type="string">Modelo</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="5%" type="">&nbsp;</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="5%" type="">&nbsp;</vivo:tbHeaderColumn>
                            </vivo:tbHeader>
                            <vivo:tbRows>
                                <logic:iterate id="it" name="ListaAparelhosVO" indexId="indice">
                                    <vivo:tbRow key="aparelhoLinha">
                                        <vivo:tbRowColumn>
                                            <bean:write name="it" property="marca" />
                                        </vivo:tbRowColumn>
                                        <vivo:tbRowColumn>
                                            <bean:write name="it" property="modelo" />
                                            <logic:equal name="it" property="inTipoAparelho" value="2">&nbsp; - CHIP AVULSO</logic:equal>
                                            <logic:equal name="it" property="inTipoAparelho" value="3">&nbsp; - CHIP PRÉ-PROGRAMADO</logic:equal>
                                        </vivo:tbRowColumn>
                                        <vivo:tbRowColumn>
                                            <acesso:controlHiddenItem nomeIdentificador="fid_aplh_alterar">
                                                <a href="editarAparelho.do?index=<%=indice%>" onclick="enviarForm(this);return false;">
                                                    <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" border="0" style="border:0px;"/></a>
                                            </acesso:controlHiddenItem>
                                        </vivo:tbRowColumn>
                                        <vivo:tbRowColumn>
                                            <acesso:controlHiddenItem nomeIdentificador="fid_aplh_excluiraparelho">
                                                <a href="excluirAparelho.do?acao=excluir&idAparelho=<bean:write name="it" property="idAparelho"/>" onclick="excluir(this); return false">
                                                    <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" border="0"></a>
                                            </acesso:controlHiddenItem>
                                        </vivo:tbRowColumn>
                                    </vivo:tbRow>
                                </logic:iterate>
                            </vivo:tbRows>
                        </vivo:tbTable>
                        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
                        <table width="770" height="26" border="0" cellspacing="0" cellpadding="5" style="border: 1px solid #D4D7DE; background-color: #F7F9FA; font-size: 10px;">
                            <tr>
                                <td width="100" valign="middle"><b>&nbsp;Legendas:</b></td>
                                <td width="150"><img vspace="2" src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" align="middle">&nbsp;Alterar Aparelho</td>
                                <td width="520"><img vspace="2" src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" align="middle">&nbsp;Excluir Cor/Aparelho</td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
            <netui:error value="marcaNula" />
            <netui:error value="corNula" />
            <netui:error value="modeloNulo" />
            <vivo:alert atributo="msgErro" scope="request" />
        </form>
        <script>
            document.body.style.backgroundColor = '#ededed';
            var inicio ='';
            var fim ='';
            var edite ='';
            inicio = location.href.indexOf("acao=")+5
            fim    = location.href.length;
            edite  = location.href.indexOf("acaoCor=")+4;
    
            if( document.forms[0].elements("isEdit").value == "true") {
                document.getElementById('incluir').src = "/FrontOfficeWeb/resources/images/bt_alterar_nrml.gif";
            }
    
            if(location.href.substring(inicio,fim) == "erro"){
                alert("Aparelho já cadastrado!");
            }
    
            if(location.href.substring(inicio,fim) == "erroMatriz"){
                alert("Existe Matriz associada a este aparelho!");
            }
    
            if(location.href.substring(inicio,fim) == "editar"){
                document.getElementById('incluir').src = "/FrontOfficeWeb/resources/images/bt_alterar_nrml.gif";
            }
    
            if( (location.href.substring(inicio,fim) == "addCor")  && edite == 4){
                document.getElementById('incluir').src = "/FrontOfficeWeb/resources/images/bt_alterar_nrml.gif";
            }
    
            if(inicio == 102  && ('<%=request.getAttribute("editar")%>') == 'true' ){
                document.getElementById('incluir').src = "/FrontOfficeWeb/resources/images/bt_alterar_nrml.gif";
            }
    
            if(location.href.substring(inicio,fim) == "delCor" && ('<%=request.getAttribute("editar")%>') == 'true' ){
                document.getElementById('incluir').src = "/FrontOfficeWeb/resources/images/bt_alterar_nrml.gif";
            }
        </script>
        </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>