<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<bean:define id="Form"               name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="consultaMatrizOfertaForm" type="fidelizacao.FidelizacaoController.ConsultaMatrizOfertaForm"/>
<bean:define id="param"              name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="parametrosVO" type="br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO"/>
<bean:define id="aDesconto"          name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="ofertaAparelhoForm.listaDescontoVO" />
<bean:define id="aParcela"           name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="ofertaAparelhoForm.listaParcelaVO" />
<bean:define id="listaAparelhosVO"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaAparelhosVO.itemOfertaAparelhoVOArray"/>
<bean:define id="listaTipoPagamento" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaAparelhosVO"/>
<bean:define id="msgErro"            name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaAparelhosVO.msgErro"/>

<netui-template:template templatePage="/resources/jsp/templateRetencao.jsp">
    <netui-template:section name="headerSection">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
        <script language="JavaScript"  src="<%=request.getContextPath()%>/resources/scripts/frameweb.js" ></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <script type="text/javascript" src="../resources/scripts/fidelizacao-aparelhos.js"></script>
        <script language="javascript">
        <logic:notEmpty name="msgSERASA">
            alert('<bean:write name="msgSERASA"/>');
        </logic:notEmpty>
        <logic:notEmpty name="msgSAP">
            alert('<bean:write name="msgSAP"/>');
        </logic:notEmpty>
        <logic:equal name="msgErro" property="codErro" value="1" >
            alert('<bean:write name="msgErro" property="dsErro"/>');
        </logic:equal>

            function validaValorParcela(){
                var f = document.forms[0];

                if(f.elements['meioPagamento'].value=='3' || f.elements['meioPagamento'].value=='4'){
                    return "";
                }
                if (f.elements['vlParcela'].value!='') {
                    var valParcMin = '<bean:write name="Form" property="vlMinimoParcela"/>';
                    var vlParcelaMinima = parseFloat(valParcMin);
                    parcela = f.elements['vlParcela'];
                    valor = parcela.value.replace(".","").replace(",",".");
                    if (parseInt(valor) < vlParcelaMinima) {
                        //return "- Valor da parcela não pode ser inferior a " + vlParcelaMinima + " reais\n";
                        return "O valor total da parcela não atingiu o valor mínimo necessário para efetuar o parcelamento (R$ "+ valParcMin + "). Favor consultar a política de vendas";
                    }
                }else{
                    return "";
                }
            }

            function checaSubmit(){
                var f = document.forms[0];
                erro = true;
                if(f.elements['wlw-radio_button_group_key:{pageContext.listaAparelhosVO}'] && f.elements['wlw-radio_button_group_key:{pageContext.listaAparelhosVO}'].length){
                    for(i=0;i<f.elements['wlw-radio_button_group_key:{pageContext.listaAparelhosVO}'].length;i++){
                        if(f.elements['wlw-radio_button_group_key:{pageContext.listaAparelhosVO}'][i].checked == true){
                            erro = false;
                        }
                    }
                }else if(f.elements['wlw-radio_button_group_key:{pageContext.listaAparelhosVO}']){
                    if(f.elements['wlw-radio_button_group_key:{pageContext.listaAparelhosVO}'].checked == true){
                        erro = false;
                    }
                }
                if(erro){
                    alert('Favor Selecionar um Aparelho')
                }else{
                    var msg = "Por favor, especifique o valor dos campos:\n";
                    if(f.elements['meioPagamento'].selectedIndex <=0){
                        msg  += "- Favor Selecionar um Meio de Pagamento\n";
                        erro = true;
                    }
                    <logic:notEqual value="APC" name="Form" property="sgOfertaAceita">
                    if(f.elements['percDesconto'].selectedIndex <=0 && f.elements['percDesconto'].options[0].text != 100){
                        msg  += "- Favor Selecionar o Percentual de Desconto\n";
                        erro = true;
                    }
                    if(f.elements['nroParcela'].selectedIndex <=0 && f.elements['percDesconto'].options[f.elements['percDesconto'].selectedIndex].value!="100" && f.elements['percDesconto'].options[0].text != 100 ){
                        msg  += "- Favor Selecionar o Número da Parcela\n";
                        erro = true;
                    }
                    </logic:notEqual>
                    <%if("APC".equals(Form.getSgOfertaAceita()) || "PJ".equals(param.getInTipoPessoa()) ){%>
                    if(f.elements['przVigencia'].selectedIndex<=0){
                        msg  += "- Favor Selecionar um Prazo de Vigência\n";
                        erro = true;
                    }
                    <%}%>
                    var validacao=validaValorParcela();

                    if(validacao!=null && validacao!=''){
                        msg += validacao;
                        erro=true;
                    }
                    if(erro){
                        alert(msg);
                    }else{
                        if(f.elements['meioPagamento'].value == "3"){
                            f.action = "getDadosAparelho.do?acao=proxima&meioPgto=3";
                        }else{
                            var nrCPF = onlyNumbers(window.top.frameApp.$('ddClienteDocumento').innerHTML);
                            var dsMeioPagamento = f.meioPagamento.options[f.meioPagamento.selectedIndex].text;
                            f.action = "getDadosAparelho.do?acao=proxima&nrCPF="+nrCPF+"&dsMeioPagamento="+dsMeioPagamento;
                        }
                        parent.parent.mostrar_div();
                        f.target = "";
                        f.submit();
                        f.elements['vlDesconto'].disabled=true;
                        f.elements['vlParcela'].disabled=true;
                        f.elements['nroParcela'].disabled=true;
                        f.elements['percDesconto'].disabled=true;
                        f.elements['meioPagamento'].disabled=true;
                        <%if("APC".equals(Form.getSgOfertaAceita()) || "PJ".equals(param.getInTipoPessoa()) ){%>
                        f.elements['przVigencia'].disabled=true;
                        <%}%>

                    }
                }
            }

        </script>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
            <!--
                top.frameApp.oculta_div();
                <logic:equal value="APC" name="Form" property="sgOfertaAceita">
                document.forms[0].elements["percDesconto"].disabled=true;
                document.forms[0].elements["nroParcela"].disabled=true;
                </logic:equal>
            -->
        </SCRIPT>
    </netui-template:section>
<netui-template:section name="bodySection">
<acesso:controlHiddenItem nomeIdentificador="fid_oa_verpagina">
    <vivo:body idTable="tbMain" idDiv="dvain" height="335" width="770">
    <div style="width:760px;height:328px;overflow:auto;margin-bottom:1px solid #adadad;">
    <form action="getDadosAparelho.do" onsubmit="return false;" method="post">
        <input type="hidden" name="tipoEncerramento">
        <html:hidden name="Form" property="idRetencao"/>
        <html:hidden name="Form" property="index"/>
        <html:hidden name="Form" property="vlCalcularDesconto"/>
        <jsp:include page="informacoesCliente.jsp"/>
        <vivo:quadro id="qdMain" height="136" width="750" label="Oferta de Aparelho">
            <vivo:tbTable layoutHeight="105" selectable="true" layoutWidth="723" tableWidth="720" styleId="tbMain" sortable="true">
                <vivo:tbHeader>
                    <vivo:tbHeaderColumn align="left" width="5%" type=""></vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="left" width="10%" type="string">Manual</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="left" width="23%" type="string">Marca</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="left" width="23%" type="string">Modelo</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="left" width="13%" type="string">Cor</vivo:tbHeaderColumn>
                    <!--vivo:tbHeaderColumn align="left" width="10%" type="number">Multa<!--/vivo:tbHeaderColumn-->
                    <vivo:tbHeaderColumn align="left" width="11%" type="number">Preço</vivo:tbHeaderColumn>
                </vivo:tbHeader>
                <vivo:tbRows>
                    <netui-data:repeater dataSource="{pageContext.listaAparelhosVO}">
                        <netui-data:repeaterHeader> </netui-data:repeaterHeader>
                        <netui-data:repeaterItem>
                            <vivo:tbRow key="linha1">
                            <vivo:tbRowColumn>
                                <netui:radioButtonGroup dataSource="{pageContext.listaAparelhosVO}" labelStyle="display:none;background:none;" style="border:0px" optionsDataSource="{container.index}"/>
                                <netui:hidden dataSource="{container.item.idMatrizAparelho}"/>
                                <netui:hidden dataSource="{container.item.preco}"/>
                                <netui:hidden dataSource="{container.item.SAP}"/>
                            </vivo:tbRowColumn>
                            <vivo:tbRowColumn>
                                <img src="/FrontOfficeWeb/resources/images/lupa_bg_ffffff.gif" style="border:0px" onClick="abreUrl(this);return false"/>
                                <netui:hidden dataSource="{container.item.dsManual}"/>
                            </vivo:tbRowColumn>
                             <vivo:tbRowColumn>
                                <netui:label value="{container.item.nmMarca}"/>
                            </vivo:tbRowColumn>
                            <vivo:tbRowColumn>
                                <netui:label value="{container.item.modelo}"/>
                            </vivo:tbRowColumn>
                            <vivo:tbRowColumn>
                                <netui:label value="{container.item.cor}"/>
                            </vivo:tbRowColumn>
                            <!--vivo:tbRowColumn-->
                                <!--netui:label value="{container.item.multa}"/-->
                            <!--/vivo:tbRowColumn-->
                            <vivo:tbRowColumn>
                                <netui:label value="{container.item.preco}"/>
                            </vivo:tbRowColumn>
                            </vivo:tbRow>
                        </netui-data:repeaterItem>
                        <netui-data:repeaterFooter> </netui-data:repeaterFooter>
                    </netui-data:repeater>
                </vivo:tbRows>
            </vivo:tbTable>
        </vivo:quadro>
        <table border="0" cellpadding="1" cellspacing="1" width="740">
            <tr>
                <td>% de Desconto:</td>
                <td>
    				<html:select name="Form" property="percDesconto" size="1" style="width=70px;height=10px" onchange="calculaPerc(this)">
    					<option value=""></option>
    					<html:options collection="aDesconto" property="descricao" labelProperty="descricao"/>
    				</html:select>
                </td>
                <td>Valor com Desconto:</td>
                <td>
                    <html:text name="Form" property="vlDesconto" styleClass="textfield" readonly="true" size="15"/>
                </td>
                <td>Número Parcelas:</td>
                <td>
    				<html:select name="Form" property="nroParcela" size="1" style="width=70px;height=10px" onchange="calculaParc(this)">
    					<option value="0">&nbsp;</option>
    					<html:options collection="aParcela" property="descricao" labelProperty="descricao"/>
    				</html:select>
                </td>
                <td>Valor da Parcela:</td>
                <td>
                    <html:text name="Form" property="vlParcela" styleClass="textfield" readonly="true" size="15"/>
                </td>
            </tr>
            <tr>
                <td colspan="2">Meio de Pagamento:</td>
                <td colspan="2">
                    <html:select name="Form" property="meioPagamento" size="1" style="width=150px;height=10px" onchange="verificaMeio(this);">
    					<logic:notEmpty name="listaTipoPagamento" property="fidelizacaoListaGeralVO">
                            <bean:define id="optionTipoPagamento" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaAparelhosVO.fidelizacaoListaGeralVO.itemListaVOArray"/>
                            <option value="0">&nbsp;</option>
                            <html:options collection="optionTipoPagamento" property="id" labelProperty="descricao"/>
                        </logic:notEmpty>
    				</html:select>
                </td>
                <%if("APC".equals(Form.getSgOfertaAceita()) || "PJ".equals(param.getInTipoPessoa()) ){%>
                <td colspan="2">Prazo de Vigência de Contrato:</td>
                <td colspan="2">
                    <html:select name="Form" property="przVigencia" size="1" style="width=150px;height=10px">
    					<logic:notEmpty name="listaTipoPagamento" property="listaPrazoVigenciaVO">
                            <bean:define id="optionPrazoVigencia" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaAparelhosVO.listaPrazoVigenciaVO.itemListaVOArray"/>
                            <option value="0">&nbsp;</option>
                            <html:options collection="optionPrazoVigencia" property="id" labelProperty="descricao"/>
                        </logic:notEmpty>
    				</html:select>
                </td>
                <%}else{%>
                <td colspan="4"></td>
                <%}%>
            </tr>
            <acesso:controlHiddenItem nomeIdentificador="fid_oa_excecao">
            <tr>
                <td colspan="2">
                    <b>Oferta de Exceção:</b>
                </td>
                <td>
					<html:checkbox name="Form" property="inExcecao" onclick="mostraTodosAparelhos(this);"  style="border:none;background-color:#E3ECF4;" />
                </td>
                <td colspan="5"></td>
            </tr>
            </acesso:controlHiddenItem>
        </table>
        <script language="JavaScript">
            <!--
            if(document.forms[0].elements["index"].value!=""){
                if(document.forms[0].elements("wlw-radio_button_group_key:{pageContext.listaAparelhosVO}").length){
                    for(i=0; i<document.forms[0].elements["wlw-radio_button_group_key:{pageContext.listaAparelhosVO}"].length; i++){
                        if(document.forms[0].elements["wlw-radio_button_group_key:{pageContext.listaAparelhosVO}"][i].value==document.forms[0].elements["index"].value){
                            document.forms[0].elements["wlw-radio_button_group_key:{pageContext.listaAparelhosVO}"][i].checked=true;
                            <logic:notEqual value="APC" name="Form" property="sgOfertaAceita">
                            document.forms[0].elements["percDesconto"].disabled=false;
                            document.forms[0].elements["nroParcela"].disabled=false;
                            </logic:notEqual>
                            break;
                        }
                    }
                }else{
                    if(document.forms[0].elements["wlw-radio_button_group_key:{pageContext.listaAparelhosVO}"].value==document.forms[0].elements["index"].value){
                        document.forms[0].elements["wlw-radio_button_group_key:{pageContext.listaAparelhosVO}"].checked=true;
                        <logic:notEqual value="APC" name="Form" property="sgOfertaAceita">
                        document.forms[0].elements["percDesconto"].disabled=false;
                        document.forms[0].elements["nroParcela"].disabled=false;
                        </logic:notEqual>
                    }
                }
            }
            verificaMeio(document.forms[0].elements['meioPagamento']);
            -->
        </script>
        <img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="3" border="0">
        <table width="735" cellpadding="0" cellspacing="0">
            <tr>
                <td>
                    <img onmouseup="document.forms[0].action='voltarOfertas.do';document.forms[0].submit()"
                    	 src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif"
                    	 border="0"
                    	 onClick="parent.parent.mostrar_div();"/>
                </td>
                <td align="center">
                    <acesso:controlHiddenItem nomeIdentificador="fid_oa_manualeletronico">
                    
                        <a href="http://intranet.vivo-sp.com.br/manual/retencao/index.htm"
                           target="_blank">
                        	<img src="/FrontOfficeWeb/resources/images/bt_manelet_nrml.gif" border="0">
                        </a>
                    </acesso:controlHiddenItem>
                    <acesso:controlHiddenItem nomeIdentificador="fid_oa_agendar">
                        <img src="/FrontOfficeWeb/resources/images/bt_agendar_nrml.gif"
                        	 onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_agendar_nrml.gif'"
                        	 onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_agendar_over.gif'"
                        	 style="border:none;cursor:hand;" border="0" onClick="agendar();">
                    </acesso:controlHiddenItem>
                    <acesso:controlHiddenItem nomeIdentificador="fid_oa_vaipensar">
                        <img src="/FrontOfficeWeb/resources/images/bt_vaipensar_nrml.gif"
                        	 border="0"
                        	 onClick="cancela('vaipensar'); return false"
                        	 formSubmit="true" />
                    </acesso:controlHiddenItem>
                </td>
                <td align="right" valign="bottom">
                    <acesso:controlHiddenItem nomeIdentificador="fid_oa_proxima">
                        <img action="getDadosAparelho.do?acao=proxima"
                        	 class="button"
                        	 src="/FrontOfficeWeb/resources/images/bt_proxima_nrml.gif"
                        	 onClick="checaSubmit();return false;"
                        	 border="0" />
                    </acesso:controlHiddenItem>
                </td>
            </tr>
        </table>
    </form>
    </div>
    </vivo:body>
</acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>
