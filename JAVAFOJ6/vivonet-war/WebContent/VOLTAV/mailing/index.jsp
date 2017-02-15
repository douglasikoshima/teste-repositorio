<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<netui-template:template templatePage="../../resources/jsp/CRMTemplate.jsp">

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js"></script>
<script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/tratamento.js" ></script>
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/prototype.js" ></script>
    <script type="text/javascript" language="javascript" src="/FrontOfficeWeb/resources/scripts/vivoval.js" ></script>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="mailingFormBean"/>
<bean:define id="iareaBanner" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="mailingFormBean.areaBanner"/>
<bean:define id="imailingBanner" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="mailingFormBean.mailingBanner"/>
<bean:define id="mailingLinha" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="mailingFormBean.mailingLinha"/>

<netui-template:setAttribute name="title" value="Mailing"/>
<netui-template:setAttribute name="modulo" value="VOL/TAV"/>

<netui-template:section name="headerSection">
    <script type="text/javascript" language="javascript">


        function validaDDD(obj){
            if(obj.value.length == 2){
                document.getElementById('mailingFormBean').linha.focus();
            }
        }

        function enviarArquivo() {
            f = document.forms[0];
            if ($F('file')=="") {
                alert('Por favor, selecione um arquivo para upload.');
            } else {
                if (validaArquivoEntrada($F('file'))) {
                    var dataInicial = document.getElementById('mailingFormBean').dtVigenciaInicio.value;
                    var dataFim = document.getElementById('mailingFormBean').dtVigenciaFim.value;
                    if(document.getElementById('mailingFormBean').acao[0].checked){
                        if(document.getElementById('mailingFormBean').nmMailingBanner.value.length == 0){
                            alert('Por favor, informe o campo nome.');
                            document.getElementById('mailingFormBean').nmMailingBanner.focus();
                            return false;
                        }
                        else
                        if(document.getElementById('mailingFormBean').nmMailingBanner.value.length > 60){
                            alert('O campo nome excedeu o limite máximo de 60 caracteres.');
                            document.getElementById('mailingFormBean').nmMailingBanner.focus();
                            return false;
                        }
                        else if(document.getElementById('mailingFormBean').dtVigenciaInicio.value.length == 0 ||
                        document.getElementById('mailingFormBean').dtVigenciaFim.length == 0){
                            alert('Por favor, informe o campo data inicial de vigência.');
                            document.getElementById('mailingFormBean').dtVigenciaInicio.focus();
                            return false;
                        }
                        else if(document.getElementById('mailingFormBean').dtVigenciaFim.value.length == 0){
                            alert('Por favor, informe o campo data final de vigência.');
                            document.getElementById('mailingFormBean').dtVigenciaFim.focus();
                            return false;
                        }
                        else if(document.getElementById('mailingFormBean').idAreaBannerSelecionado.value== 0){
                            alert('Por favor, informe o campo área do banner.');
                            document.getElementById('mailingFormBean').idAreaBannerSelecionado.focus();
                            return false;
                        }

                        // a data final do prazo de vigência, não pode ser inferior a data de inicio.

                        f.action = "incluirMailing.do";
                    }else{
                        if(document.getElementById('mailingFormBean').idMailingBannerSelecionado.value == 0){
                            alert('Por favor, informe o campo mailing.');
                            document.getElementById('mailingFormBean').idMailingBannerSelecionado.focus();
                            return false;
                        }
                        else if(document.getElementById('mailingFormBean').dtVigenciaInicio.value.length == 0 ||
                        document.getElementById('mailingFormBean').dtVigenciaFim.length == 0){
                            alert('Por favor, informe o campo data inicial de vigência.');
                            document.getElementById('mailingFormBean').dtVigenciaInicio.focus();
                            return false;
                        }
                        else if(document.getElementById('mailingFormBean').dtVigenciaFim.value.length == 0){
                            alert('Por favor, informe o campo data final de vigência.');
                            document.getElementById('mailingFormBean').dtVigenciaFim.focus();
                            return false;
                        }
                        f.action = "alterarMailing.do";
                    }

                    var dtInicial = dataInicial.substring(6,10) + "" + dataInicial.substring(3,5) + dataInicial.substring(0,2);
                    var dtFim = dataFim.substring(6,10) + "" + dataFim.substring(3,5) + dataFim.substring(0,2);
                    var numDtInicial = parseInt(dtInicial);
                    var numDtFim = parseInt(dtFim);
                    if(numDtFim <= numDtInicial){
                        alert('A data final do prazo de vigência precisa ser maior do que a data inicial do prazo de vigência.');
                        return false;
                    }
                    var data = new Date();
                    var ano = data.getYear();
                    var mes = data.getMonth()+1;
                    var dia = data.getDate();
                    if(mes < 10){
                        mes = "0" + mes;
                    }
                    if(dia < 10){
                        dia = "0" + dia;
                    }
                    var dataFormatada = ano +""+mes+""+dia;

                    if(dtInicial < dataFormatada){
                        alert('data de início do prazo de vigência não pode ser inferior  a data de hoje.');
                        return false;
                    }

                    f.submit();
                }
            }
        }

        function validaArquivoEntrada(pathArquivo) {
            var extensao = pathArquivo.substring(pathArquivo.lastIndexOf(".")+1, pathArquivo.length);
            if (extensao.toLowerCase() != "txt") {
                alert("Por favor, faça o upload de um arquivo TXT.");
                return false;
            }
            return true;
        }

        function pesquisar(){
            if(document.getElementById('mailingFormBean').acao[0].checked){
                alert('Selecione a opção de alteração para pesquisar uma linha ou DDD.');
                return false;
            }
            if(document.getElementById('mailingFormBean').idMailingBannerSelecionado.value == 0){
                            alert('Por favor, informe o campo mailing.');
                            document.getElementById('mailingFormBean').idMailingBannerSelecionado.focus();
                            return false;
                        }
            if(document.getElementById('mailingFormBean').dsTipoPesquisa.value == 0){
                alert("Selecione o tipo de Pesquisa, DDD ou Linha.");
                return false;
            }

            if(document.getElementById('mailingFormBean').dsTipoPesquisa.value == '----'){
                alert('Selecione um criterio e tente novamente.');
                return false;
            }

            if(document.getElementById('mailingFormBean').queryPesquisa.value.length == 0){
                alert('Preencha o campo de pesquisa e tente novamente.');
                return false;
            }

            document.getElementById('mailingFormBean').action = "pesquisar.do";
            document.getElementById('mailingFormBean').submit();
        }




        function incluirLinha(){
            if(!document.getElementById('mailingFormBean').acao[1].checked){
                alert('Selecione a opção de alteração para incluir uma linha.');
                return false;
            }
            if(document.getElementById('mailingFormBean').idMailingBannerSelecionado.value == 0){
                alert('Selecione um mailing para incluir uma linha.');
                document.getElementById('mailingFormBean').idMailingBannerSelecionado.focus();
                return false;
            }

            if(document.getElementById('mailingFormBean').ddd.value.length != 2){
                alert('DDD é um campo obrigatório, preencha o campo corretamente.');
                document.getElementById('mailingFormBean').ddd.focus();
                return false;
            }
            if(!formatPhoneNumber(document.getElementById('mailingFormBean').linha.value)){
                alert('Linha é um campo obrigatório, preencha o campo corretamente.');
                document.getElementById('mailingFormBean').linha.focus();
                return false;
            }
            document.getElementById('mailingFormBean').action  = "incluirLinha.do";
            document.getElementById('mailingFormBean').submit();
        }



        function excluirLinha(id){
            if (confirm('Deseja excluir a linha selecionada?')){
                document.getElementById('mailingFormBean').dddLinha.value = id;
                document.getElementById('mailingFormBean').action = "excluirLinha.do";
                document.getElementById('mailingFormBean').submit();
            }
        }

        function excluirMailing(id){
            if(document.getElementById('mailingFormBean').idMailingBannerSelecionado.value == 0){
                alert('Selecione um mailing para exclusão.');
                document.getElementById('mailingFormBean').idMailingBannerSelecionado.focus();
                return false;
            }
            if (confirm('Deseja excluir o mailing selecionado?')){
                document.getElementById('mailingFormBean').action = "excluirMailing.do";
                document.getElementById('mailingFormBean').submit();
            }
        }


        function limpar(){
            document.getElementById('mailingFormBean').ddd.value = "";
            document.getElementById('mailingFormBean').linha.value = "";
            return false;
        }

        function doSubmit(action){
            document.getElementById('mailingFormBean').action = action;
            document.getElementById('mailingFormBean').submit();
        }

        function proximoCampo(obj){
            if(obj.value.length == 2)
                document.getElementById('mailingFormBean').linha.focus();
        }


 </script>
</netui-template:section>

    <netui-template:section name="bodySection">

    <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>

    <vivo:sessao id="qdMain" height="468" width="790" label="Parametrização" operacoes="Mailing" scroll="no">

        <form action="uploadArquivo.do" enctype="multipart/form-data" method="post" name="mailingFormBean">
            <html:hidden name="Form" property="operacao" />
            <html:hidden name="Form" property="paginaAtual" />
            <html:hidden name="Form" property="dddLinha" />
            <div id="erroOracle" style="display:none;"><bean:write name="Form" property="erroOracle" /></div>
            <vivo:sessao id="consultaHexa" width="780" height="150" label="Mailing"  scroll="false" operacoes="Upload de Linha">
                      <table cellpadding="5" width="100%" cellspacing="0">
                        <tr>
                            <td width="100" align="left">
                                Incluir <html:radio styleClass="radio" name="Form" value="incluir" property="acao" tabindex="1" onclick="doSubmit('exibeInclusaoMailing.do');"/>
                            </td>
                            <td>
                                Alterar <html:radio styleClass="radio" name="Form" value="alterar" property="acao" tabindex="2" onclick="doSubmit('exibeAlteracaoMailing.do');"/>
                            </td>
                        </tr>
                        <tr>
                            <td width="100" align="left">
                                Nome:
                            </td>
                            <td>
                              <logic:equal value="incluir" property="acao" name="Form">
                                <html:text name="Form" property="nmMailingBanner" tabindex="3" style="width=250px;" maxlength="60"/>
                              </logic:equal>
                              <logic:equal value="alterar" property="acao" name="Form">
                                <html:select name="Form" tabindex="6" property="idMailingBannerSelecionado" size="1" style="width=250px;height=10px" onchange="javascript:doSubmit('carregaDadosMailing.do');">
									<html:options collection="imailingBanner"  property="idMailingBanner" labelProperty="nmMailingBanner"/>
                                </html:select>
                                <img id="btExcluir"
                                                       value="Excluir"
                                                       src="/FrontOfficeWeb/resources/images/bt_excluir_nrml.gif"
                                                       rolloverImage="/FrontOfficeWeb/resources/images/bt_excluir_over.gif"
                                                       style="border:none;margin-left:4px;"
                                                       onMouseUp="excluirMailing()" />
                              </logic:equal>
                            </td>
                        </tr>
                        <tr>
                            <td width="100" align="left">
                               <netui:label value="Vigência:"/>
                            </td>
                            <td>
                                <html:text property="dtVigenciaInicio" tabindex="4" name="Form" styleClass="textfield" maxlength="10" onkeypress="this.value = Format(this.value,'##/##/####');" size="10"/><img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dtVigenciaInicio', '%d/%m/%Y');">
                               <netui:label value="Até:"/>
                               <html:text property="dtVigenciaFim" tabindex="5" name="Form" styleClass="textfield" maxlength="10" onkeypress="this.value = Format(this.value,'##/##/####');" size="10"/><img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dtVigenciaFim', '%d/%m/%Y');">
                            </td>
                        </tr>
                        <tr>
                            <td width="100" align="left">
                                Área:
                            </td>
                            <td>
                              <logic:equal value="incluir" property="acao" name="Form">
                              <html:select name="Form" tabindex="6" property="idAreaBannerSelecionado" size="1" style="width=250px;height=10px">
									<html:options collection="iareaBanner"  property="idAreaBanner" labelProperty="dsAreaBanner"/>
                              </html:select>
                              </logic:equal>
                              <logic:equal value="alterar" property="acao" name="Form">
                                 <bean:write name="Form" property="dsAreaBannerSelecionado" />
                              </logic:equal>
                            </td>
                        </tr>
                        <tr>
                            <td width="100" nowrap>
                                Arquivo para upload
                            </td>
                            <td>
                                <html:file name="Form" property="file" styleId="file" style="width:400px;" />
                                  <img id="btEnviar"
                                                       value="Enviar"
                                                       src="/FrontOfficeWeb/resources/images/bt_enviar_nrml.gif"
                                                       rolloverImage="/FrontOfficeWeb/resources/images/bt_enviar_over.gif"
                                                       style="border:none;margin-left:4px;"
                                                       onMouseUp="enviarArquivo()" />
                            </td>
                        </tr>
                        </table>
            </vivo:sessao>
            <vivo:sessao id="consultaHexa" width="780" height="175" label="Mailing"  scroll="false" operacoes="Lista de Linhas">
                <table>
                    <tr>
                        <td>Pesquisar por:</td>
                        <td>
                            <html:select name="Form"  property="dsTipoPesquisa" style="width:50px;">
                                <html:option value="0">----</html:option>
                                <html:option value="ddd">DDD</html:option>
                                <html:option value="linha">Linha</html:option>
                            </html:select>
                        </td>
                        <td><html:text name="Form" property="queryPesquisa" style="width:100px;" maxlength="9"/></td>
                        <td>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <img src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" style="border:none;cursor:hand" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif';" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif';" onMouseUp="pesquisar();" />
                        </td>
                    </tr>
                </table>
                <table width="100%" border="0">
                    <tr>
                        <td align="left">
                            <vivo:tbTable selectable="true" layoutWidth="500" layoutHeight="150" tableWidth="500" onRowClick="false" resize="true" styleId="tableTitle" sortable="true">
                                <vivo:tbHeader>
                                    <vivo:tbHeaderColumn align="center" width="45%"  type="string">DDD</vivo:tbHeaderColumn>
                                    <vivo:tbHeaderColumn align="center" width="50%"  type="string">Linha</vivo:tbHeaderColumn>
                                    <vivo:tbHeaderColumn align="center" width="5%" type="string">&nbsp;</vivo:tbHeaderColumn>
                                </vivo:tbHeader>
                                <logic:notEqual value="null" name="Form" property="mailingLinha">
                                <vivo:tbRows>
                                    <logic:iterate name="Form" property="mailingLinha" id="lista" indexId="id">
                                        <vivo:tbRow key="linha">
                                            <vivo:tbRowColumn>
                                                    <bean:write name="lista" property="ddd" />
                                            </vivo:tbRowColumn>
                                            <vivo:tbRowColumn>
                                                    <bean:write name="lista" property="linha" />
                                            </vivo:tbRowColumn>
                                            <vivo:tbRowColumn>
                                               <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" onclick="excluirLinha('<bean:write name="lista" property="ddd" /><bean:write name="lista" property="linha" />');" style="cursor: hand;" border="0" alt="Excluir linha do Mailing" >
                                            </vivo:tbRowColumn>
                                        </vivo:tbRow>
                                    </logic:iterate>
                                </vivo:tbRows>
                                </logic:notEqual>
                            </vivo:tbTable>
                        </td>
                    </tr>
                </table>
            </vivo:sessao>
            <vivo:sessao id="consultaHexa" width="780" height="72" label="Mailing" scroll="false" operacoes="Incluir Linha">
                <table width="100%" border="0" height="100%">
                    <tr>
                        <td>
                            DDD:&nbsp;<html:text name="Form" property="ddd" maxlength="2" onkeypress="validaDDD(this);" size="3" onkeyup="checaInteiro(this);proximoCampo(this);"/>&nbsp; &nbsp; &nbsp;
                            Linha:&nbsp;<html:text name="Form" property="linha" onkeyup="checaInteiro(this);" maxlength="9" size="11"/>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <img id="btIncluir" onClick="incluirLinha();" src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif'"  onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_incluir_over.gif'" style="cursor:hand;border:none;" />
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <img style="cursor:hand;border:none;" tabindex="3" id="btLimpar" onclick="return limpar();" src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_limpar_over.gif'"/>
                        </td>
                    </tr>
                </table>
            </vivo:sessao>
        </form>
        </vivo:sessao>

    <vivo:alert atributo="msgStatus" scope="request"/>

    </netui-template:section>
</netui-template:template>