<%@page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<bean:define id="form"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="lupaFaturamentoPos" type="cliente.TelaInicial.DetalheFatura.DetalheFaturaController.LupaFaturamentoPosForm" />
<bean:define id="Contas" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="lupaFaturamentoPos.lupaFaturamentoPosVO.LFContasFaturamentoArray"/>
<bean:define id="Linhas" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="lupaFaturamentoPos.linhasArray"/>
<html>
    <head>
        <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
        <script type="text/javascript" language="javascript" src="/FrontOfficeWeb/resources/scripts/prototype.js"></script>
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
				<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js"></script>
        <script>
            function validaPesquisa(inicio) {
                if (document.getElementById("nrCont").value == "" && document.getElementById("nrLinh").value == "")
                    alert('Preencher ao menos um dos campos de busca!');
                else if (document.getElementById("nrLinh").value != "" && document.getElementById("nrLinh").value.length != 13 && document.getElementById("nrLinh").value.length != 14)
                    alert('O número da linha inválido, favor corrigir!');
                else {
                    document.getElementById("nrLinh").value = limpaInteiro(document.getElementById("nrLinh").value);
                    if(inicio=="inicio"){
                        document.forms[0].action = 'pesquisar.do?inicio=TRUE';
                    }else{
                        document.forms[0].action = 'pesquisar.do';
                    }
                    parent.mostrar_div();
                    document.forms[0].method = "POST";
                    document.forms[0].submit();
                }
            }

            function carregaLinhas(ndxConta, idConta) {
                document.forms[0].ndxContaSelecionada.value = ndxConta;
                document.forms[0].idContaSelecionada.value = idConta;
                document.forms[0].action = 'mostraLinhas.do';
                parent.mostrar_div();
                document.forms[0].method = "POST";
                document.forms[0].submit();
            }

            function CarregaAba(nome){
                var dyniframe = document.getElementById("fraSubAbas");
                var pagina = "";
                /* Remover botoes faturamento */
                if(nome!="btx01") {
                    document.getElementById("botoesFaturamento").style.display ="none";
                } else {
                    document.getElementById("botoesFaturamento").style.display ="block";
                }
                parent.mostrar_div();
                if(nome=="btx01") pagina = "loadFatura.do";
                if(nome=="btx03") pagina = "loadPagamento.do";
                if(nome=="btx04") pagina = "loadAjustes.do";
                if(nome=="btx05") pagina = "loadCobranca.do";
                if(nome=="btx06") pagina = "loadLinhaIntraGrupo.do";
                if(nome=="btx07") pagina = "loadEstimativa.do";
                if(nome=="btx08") pagina = "loadSimulador.do";
                dyniframe.src = pagina;
            }

            function CheckMBRow(Obj) {
                qtElemForm = document.form.length;
                // Volta as outras linhas ao estado normal
                for (i=1; i<qtElemForm+1; i++){
                    carinha = document.getElementById(i);
                    carinha.parentNode.parentNode.className = 'normal';
                }
                // Depois de voltar as linhas, pinta a linha Selecionada
                Row = Obj.parentNode.parentNode;
                Chk = Obj.checked;
                Row.className = (Chk ? 'selecionada' : 'normal');
            }

            function loadDetalhada(){
                //alert('Sistema de IMAGEM DA CONTA temporariamente fora de serviço!');
                document.forms[0].action="loadDetalhada.do";
                dvContaDetalhe.style.display='';
                document.forms[0].target="ifrmCtDetalhe";
                parent.mostrar_div();
                document.forms[0].method = "POST";
                document.forms[0].submit();
            }

            /*function reformatTel(obj){
                  valor = obj.value;
                  if(valor != oldValor || oldObj != obj){
                    for(i=0;i<valor.length;i++){
                      if(!inteiro.test(valor.charAt(i))){
                        valor = valor.substring(0,i) + valor.substring(i+1,valor.length);
                        i = -1;
                      }
                    }
                    if(valor.length < 1){
                      valor = "";
                    }else if(valor.length < 3){
                      valor = "(" + valor.substring(0,2);
                    }else if(valor.length < 7){
                      valor = "(" + valor.substring(0,2) + ")" + valor.substring(2,6);
                    }else{
                      valor = "(" + valor.substring(0,2) + ")" + valor.substring(2,6) + "-" + valor.substring(6,10);
                    }
                    obj.value = valor;
                    oldValor = valor;
                    oldObj = obj;
                    return oldObj.value;
                  }
                }*/

                showFaturaDetalhada = function() {
                    var f = document.forms[0];
                    $('divFaturaDetalhada').show();
                    f.action = "faturaDetalhada.do?operacao=getMesVencimento";
                    f.target = "iframeFaturaDetalhada";
                    f.method = "post";
                    parent.mostrar_div();
                    f.submit();
                }

                showPesquisaDetalhada = function() {
                    var f = document.forms[0];
                    $('dvContaDetalhe').show();
                    f.action = "faturaDetalhada.do?operacao=getPesquisaDetalhada";
                    f.target = "ifrmCtDetalhe";
                    f.method = "post";
                    parent.mostrar_div();
                    f.submit();
                }

                function showFiltrosFatura(tipoPessoa) {
                    /* Linhas do tipo Controle
                     * id Sigla Descrição
                     * 4  HIB   Controle CDMA
                     * 7  CTRL  Controle GSM
                     */
                    if (top.frameApp.idTipoLinha == 4 || top.frameApp.idTipoLinha == 7) {
                        alert('Funcionalidade indisponível para clientes controle 1 e 2.');
                    } else {
                        var f = document.forms[0];
                        $('dvContaDetalhe').show();
                        
                        if(tipoPessoa == 'PF')
                            f.action = "faturaDetalhada.do?operacao=getPesquisaDetalhada";
                        else
                            f.action = "faturaDetalhadaPJ.do?operacao=getPesquisaDetalhada";
                            
                        f.target = "ifrmCtDetalhe";
                        f.method = "post";
                        parent.mostrar_div();
                        f.submit();
                    }
                }

                onload = function() {
                    if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                }

        </script>
    </head>
    <body style="margin-left:5px; margin-right:5px; margin-top:5px;">
    <acesso:controlHiddenItem nomeIdentificador="cli_lfat_verpagina">
        <html:form action="/cliente/TelaInicial/DetalheFatura/pesquisar.do">
            <html:hidden name="form" property="ndxContaSelecionada"></html:hidden>
            <html:hidden name="form" property="idContaSelecionada"></html:hidden>
            <vivo:quadro id="faturamento" width="775" height="40" label="Faturamento">
                <table border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="230" style="font-weight:bold;text-indent:5px;">Conta</td>
                        <td width="210" style="font-weight:bold;text-indent:5px;">Linha</td>
                        <td width="100" style="font-weight:bold;text-indent:5px;"></td>
                        <td width="350" style="font-weight:bold;text-indent:5px;"></td>
                    </tr>
                    <tr>
                        <td>
                            <html:text name="form" property="lupaFaturamentoPosVO.busca.nrConta" onkeyup="checaInteiro(this)" maxlength="255" size="30" styleId="nrCont"/>
                        </td>
                        <td>
                            <html:text name="form" property="lupaFaturamentoPosVO.busca.nrLinha" onblur="formatPhoneNumberObj(this)" maxlength="11" size="30" styleId="nrLinh"/>
                            
                        </td>
                        <td>
                        <acesso:controlHiddenItem nomeIdentificador="cli_lfat_pesq">
                            <img src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" style="border:none;cursor:pointer;" onClick="validaPesquisa(''); return false" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif'" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif'"/>
                        </acesso:controlHiddenItem>
                        </td>
                    </tr>
                </table>
            </vivo:quadro>
            <div>
                <img src="<%=request.getContextPath()%>/resources/images/transp.gif" width="1" height="5">
            </div>
            <table width="775" class="tbl_bgBlue" border="0" cellpadding="0" cellspacing="1">
                <tr>
                    <td>
                    <table width="345" border="0" cellpadding="0" cellspacing="1">
                        <tr>
                            <td valign="top">
                            <vivo:tbTable selectable="true" layoutWidth="355" layoutHeight="100" tableWidth="355" styleId="tableTitle" sortable="false">
                            <vivo:tbHeader>
                                <vivo:tbHeaderColumn align="center" width="5%" type="string">&nbsp;</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="32%" type="string">Conta</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="31%" type="string">Ciclo</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="32%" type="string">Vencimento</vivo:tbHeaderColumn>
                            </vivo:tbHeader>
                            <vivo:tbRows >
                                    <logic:iterate id="cont" name="Contas" indexId="ndx">
                                        <vivo:tbRow key="Line" >
                                            <vivo:tbRowColumn>
                                                <input type="radio" class="radio" name="rdConta" value="<%=ndx%>" onClick="carregaLinhas('<%=ndx%>','<bean:write name="cont" property="idConta"/>')" <logic:equal name="cont"  property="idConta" value="<%=form.getIdContaSelecionada()%>">checked</logic:equal>>
                                            </vivo:tbRowColumn>
                                            <vivo:tbRowColumn><bean:write name="cont" property="nrConta"/></vivo:tbRowColumn><html:hidden name="cont" property="idConta" styleId="idConta"/>
                                            <vivo:tbRowColumn><bean:write name="cont" property="dsCicloFatura"/></vivo:tbRowColumn>
                                            <vivo:tbRowColumn><bean:write name="cont" property="dtVencimento"/></vivo:tbRowColumn>
                                        </vivo:tbRow>
                                    </logic:iterate>
                            </vivo:tbRows>
                            </vivo:tbTable>
                            </td>
                        </tr>
                    </table>
                    </td>
                    <td>
                    <table align="right" width="325" border="0" cellpadding="0" cellspacing="1">
                        <tr>
                            <td>
                            <vivo:tbTable selectable="true" layoutWidth="295" layoutHeight="100" tableWidth="295" styleId="tableTitle2" sortable="false">
                                <vivo:tbHeader>
                                    <vivo:tbHeaderColumn align="center" width="60%" type="string">Número da Linha</vivo:tbHeaderColumn>
                                    <vivo:tbHeaderColumn align="center" width="40%" type="string">Status</vivo:tbHeaderColumn>
                                </vivo:tbHeader>
                                <vivo:tbRows  >
                                    <logic:iterate id="lin" name="Linhas">
                                        <vivo:tbRow  key="Line" >
                                            <vivo:tbRowColumn><bean:write name="lin" property="nrCodArea"/><bean:write name="lin" property="nrLinha"/></vivo:tbRowColumn>
                                            <vivo:tbRowColumn><bean:write name="lin" property="dsEstadoLinha"/> </vivo:tbRowColumn>
                                        </vivo:tbRow>
                                    </logic:iterate>
                                </vivo:tbRows>
                            </vivo:tbTable>
                            </td>
                        </tr>
                    </table>
                    </td>
                </tr>
            </table>
            <div>
                <img src="<%=request.getContextPath()%>/resources/images/transp.gif" width="1" height="5">
            </div>
            <logic:greaterThan name="form" property="idContaSelecionada" value="-1">
                <table width="775" border="0" class="tbl_bgBlue" cellspacing="0" cellpadding="0">
                    <tr>
                        <vivo:abaGrupo id="btxAba" width="775" height="10" styleClass="abatexto">
                            <acesso:controlHiddenItem nomeIdentificador="cli_lfat_abafat">
                                <vivo:abaItem id="btx01" onclick="abaSelected(btxAba, btx01);CarregaAba(this.id); return false" value="Faturamento" select="S"/>
                            </acesso:controlHiddenItem>
                            <acesso:controlHiddenItem nomeIdentificador="cli_lfat_abapag">
                                <vivo:abaItem id="btx03" onclick="abaSelected(btxAba, btx03);CarregaAba(this.id); return false" value="Pagamento"/>
                            </acesso:controlHiddenItem>
                            <acesso:controlHiddenItem nomeIdentificador="cli_lfat_abaaju">
                                <vivo:abaItem id="btx04" onclick="abaSelected(btxAba, btx04);CarregaAba(this.id); return false" value="Ajustes"/>
                            </acesso:controlHiddenItem>
                            <acesso:controlHiddenItem nomeIdentificador="cli_lfat_abacobr">
                                <vivo:abaItem id="btx05" onclick="abaSelected(btxAba, btx05);CarregaAba(this.id); return false" value="Cobrança"/>
                            </acesso:controlHiddenItem>
                            <acesso:controlHiddenItem nomeIdentificador="cli_lfat_abalinh">
                                <vivo:abaItem id="btx06" onclick="abaSelected(btxAba, btx06);CarregaAba(this.id); return false" value="LinhaIntraGrupo"/>
                            </acesso:controlHiddenItem>
                            <acesso:controlHiddenItem nomeIdentificador="cli_lfat_abaestim">
                                <vivo:abaItem id="btx07" onclick="abaSelected(btxAba, btx07);CarregaAba(this.id); return false" value="Estimativa"/>
                            </acesso:controlHiddenItem>
                            <acesso:controlHiddenItem nomeIdentificador="cli_lfat_abasimul">
                                <vivo:abaItem id="btx08" onclick="abaSelected(btxAba, btx08);CarregaAba(this.id); return false" value="Simulador de Planos"/>
                            </acesso:controlHiddenItem>
                        </vivo:abaGrupo>
                    </tr>
                </table>
                <table width="775" class="tbl_bgBlue" border="0" cellpadding="0" cellspacing="0" bgcolor="#F5F5F5">
                    <tr>
                        <td bgcolor="#0092DD" colspan="4" valign="top">
                            <iframe id="fraSubAbas" width="773" height="128" frameborder="0" scrolling="no" src="loadFatura.do"></iframe>
                        </td>
                    </tr>
                    <tr align="right">
                        <td id="botoesFaturamento">
                            <img src="<%=request.getContextPath()%>/resources/images/bt_filtros_fatura_nrml.gif" width="100" height="13" border="0" style="cursor:pointer;" onmouseup="showFiltrosFatura('<%=((ParametrosVO)request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getInTipoPessoa()%>')" />
                            <img src="<%=request.getContextPath()%>/resources/images/bt_faturadetalhada_nrml.gif" width="100" height="13" border="0" style="cursor:pointer;" onmouseup="showFaturaDetalhada()" />
                            <!--img src="<%=request.getContextPath()%>/resources/images/bt_pesquisadetalhada_nrml.gif" width="120" height="13" border="0" style="cursor:pointer;" onmouseup="showPesquisaDetalhada()" /-->
                        </td>
                    </tr>
                </table>
            </logic:greaterThan>
        <%if(request.getAttribute("inicioFatura")!= null && request.getAttribute("inicioFatura").toString().equalsIgnoreCase(ConstantesCRM.STRUE)){%>
            <script>
                document.getElementById("nrCont").value='<%=((ParametrosVO)request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrConta()%>';
                validaPesquisa('inicio');
            </script>
        <%}%>
            <%if(form.getInInicio()!=null && form.getInInicio().equalsIgnoreCase(ConstantesCRM.STRUE)){
                form.setInInicio(ConstantesCRM.SFALSE);%>
                <script>
                    if($('rdConta').length>0)
                        $('rdConta')[0].click();
                    else
                        $('rdConta').click();
                </script>
            <%}%>
        </html:form>
    </acesso:controlHiddenItem>
    </body>
    <vivo:quadroFlutuante id="dvContaDetalhe" idIframe="ifrmCtDetalhe" width="790" height="400" scroll="no" spacesTop="0" spacesLeft="0" display="none" url="<%=request.getContextPath()%>" label="Conta Detalhada"/>
    <vivo:quadroFlutuante id="divFaturaDetalhada"
                          idIframe="iframeFaturaDetalhada"
                          width="350"
                          height="250"
                          scroll="auto"
                          spacesTop="50"
                          spacesLeft="220"
                          display="none"
                          url="<%=request.getContextPath()%>"
                          label="Filtros de Faturamento"/>
</html>