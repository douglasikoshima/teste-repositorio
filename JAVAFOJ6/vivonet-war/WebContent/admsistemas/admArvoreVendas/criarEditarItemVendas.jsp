<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>

<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<bean:define id="actionForm"             		name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreContato"/>
<bean:define id="FormArvoreContato"             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreContato"/>
<bean:define id="AdmNomeContatoVO"              name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreContato.admNomeContatoVO"/>
<bean:define id="AdmIndicadorAnatelExistenteVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreContato.admIndicadoresAnatelExistentesVO"/>
<bean:define id="AdmIndicadorAnatelAssociadoVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreContato.admIndicadoresAnatelAssociadosVO"/>
<bean:define id="aMensagem"                     name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreContato.listaMensagem"/>
<bean:define id="aFechamento"                   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreContato.listaFechamento"/>
<bean:define id="aRetorno"                      name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreContato.listaRetorno"/>
<bean:define id="aProcesso"                     name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreContato.listaProcesso"/>

<netui-template:template templatePage="/resources/jsp/templateUsuarioClean.jsp">
    <netui-template:section name="headerSection">
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/tratamento.js" ></script>
        <script language="JavaScript">
            function transfereSelecaoLista(listaOrigem, listaDestino){
                var i;
                for(i = 0; i<listaOrigem.options.length; i++)
                    if(listaOrigem.options[i].selected)
                        listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                for(i = listaOrigem.options.length-1; i>=0; i--)
                    if(listaOrigem.options[i].selected)
                        listaOrigem.options[i] = null;
            }

            function ajustaTamanho(obj){
                if(obj.readyState=="complete"){
                    iframe = parent.document.getElementById('frameDetalhe');
                    div = parent.document.getElementById('detalhe');
                    altura  = parent.frameDetalhe.document.body.scrollHeight;
                    largura = 600;
                    iframe.style.width = largura;
                    iframe.style.height = altura;
                    iframe.style.top = (600 - altura) / 2;
                    iframe.style.left = (800 - largura) / 2;
                    div.style.width = largura;
                    div.style.height = altura;
                    div.style.top = (545 - altura) / 2;
                    div.style.left = (815 - largura) / 2;
                }
            }

            function camposPasta(valor){
                var comboQtdCopia = parent.document.getElementById("qtdCopia");
                comboQtdCopia.style.visibility = 'hidden';
                document.forms[0].inFolha.value = valor;
                document.getElementById('divnome').style.display = "none";
            }

            function camposFolha(valor){
                document.forms[0].inFolha.value = valor;
                document.getElementById('divnome').style.display = "block";
            }

            function escondeAmbos(valor){
                document.forms[0].msg.value = valor;
                var a = document.getElementById("divLista1");
                a.style.visibility = 'hidden';
                var b = document.getElementById("divLista2");
                b.style.visibility = 'hidden';
                document.forms[0].mensagem.value = '';
                document.forms[0].mensagem.selectedIdex = 0;
            }

            function escondeLista(valor){
                document.forms[0].msg.value = valor;
                var a = document.getElementById("divLista1");
                a.style.visibility = 'hidden';
                var b = document.getElementById("divLista2");
                b.style.visibility = 'visible';
                document.forms[0].mensagem.selectedIdex = 0;
                document.forms[0].mensagem.value = '';
                document.forms[0].idNomeContatoEscolhido.selectedIndex = 0;
            }

            function exibeLista(valor){
                document.forms[0].mensagem.selectedIndex = 0;
                document.forms[0].msg.value = valor;
                var a = document.getElementById("divLista1");
                a.style.visibility = 'visible';
                var b = document.getElementById("divLista2");
                b.style.visibility = 'hidden';
            }

            function escondeTipo(valor){
                document.forms[0].nomeTipo.value = valor;
                var a = document.getElementById("divTipo1");
                a.style.visibility = 'hidden';
                var b = document.getElementById("divTipo2");
                b.style.visibility = 'visible';
                document.getElementById("nomeContato").style.display = 'none';
                document.getElementById("pesquisaNome").style.display = 'none';
                document.forms[0].idNomeContatoEscolhido.value = '';
                document.forms[0].idNomeContatoEscolhido.selectedIndex = 0;
            }

            function exibeTipo(valor){
                document.forms[0].nomeTipo.value = valor;
                var a = document.getElementById("divTipo1");
                a.style.visibility = 'visible';
                var b = document.getElementById("divTipo2");
                b.style.visibility = 'hidden';
                document.getElementById("pesquisaNome").style.display = "block";
                document.getElementById("nomeContato").style.display = 'none';
                document.forms[0].NomeContatoEscolhido.value = '';
            }

            function salvar(){
                if(valida()){
                    if(document.forms[0].nomeTipo.value == '2'){
                        document.forms[0].idNomeContatoEscolhido.selectedIndex = 0;
                        document.forms[0].NomeContatoEscolhido.value = '';
                    }
                    if (document.forms[0].salvaedita.value == 'salva')
                        document.forms[0].action='insereItem.do';
                    else
                        document.forms[0].action='salvaItemEditado.do';
                    document.forms[0].target = "_parent";
                    parent.mostrar_div();
                    document.forms[0].submit();
                }
            }

           /* Funçao sem uso.
           function gravar(){
                var lista = document.forms[0].arrayAdmIndicadorAnatelAssociado;
                //Processa gravação
                for(i = 0;i<lista.length;i++){
                   document.forms[0].arrayAdmIndicadorAnatelAssociado.options[i].selected =true;
                }
                document.forms[0].action='salvaItemEditado.do';
                document.forms[0].target = "_parent";
                parent.mostrar_div();
                document.forms[0].submit();
            }
            */

        function valida(){
            // validaçao para Folhas e Pastas.
            //if(document.forms[0].inDisponibilidadeNovo.selectedIndex == 0){
            //    alert('Selecione uma opção válida de disponibilidade.');
            //   return false;
            alert('|' + document.forms[0].inFolha.value + '|');
            if(document.forms[0].inFolha.value == '' || document.forms[0].inFolha.value == '-1' ){
                alert('Preencha o campo tipo.');
                return false;

            }else if(document.forms[0].nomeTipo.value == '0' && trim(document.forms[0].NomeContatoEscolhido.value) == ''){
                alert('Preencha o campo nome tipo.');
                return false;

            }else if(document.forms[0].nomeTipo.value == '1' && document.forms[0].idNomeContatoEscolhido.selectedIndex == 0 ){
                alert('Selecione um nome tipo.');
                return false;
            }
            // Se Folha.
            if(document.forms[0].inFolha.value == '1' ){
                /*
                if(document.forms[0].fechamento.selectedIndex == 0){
                    alert('Selecione um Fechamento.');
                    return false;

                }else
                */
                if(trim(document.forms[0].qtDiasPrazoContato.value) == ''){
                    alert('Preencha o campo Prazo de Atendimento.');
                    return false;

                }else if(document.forms[0].processoTec.selectedIndex == 0){
                    alert('Selecione um Processo Técnico.');
                    return false;

                }else if(trim(document.forms[0].qtDiasPrazoAnatel.value) == '-1'){
                    alert('Preencha o campo Prazo C. Prévio.');
                    return false;

                }else if(trim(document.forms[0].qtDiasPrazoAnatel.value) == ""){
                    alert('Preencha o campo Prazo C. Prévio.');
                    return false;

                }else if(trim(document.forms[0].vlPesoContato.value) == 0){
                    alert('Preencha o campo Peso/Prioridade.');
                    return false;

                //}else if(document.forms[0].msg.value == '1' && document.forms[0].mensagem.value == '-1')
                // {
                //    alert('Selecione uma Mensagem de Aviso.');
                //   return false;

                }else if(trim(document.forms[0].qtDiasPrazoContato.value) == '0'
                    || trim(document.forms[0].qtDiasPrazoContato.value) == '00'
                    || trim(document.forms[0].qtDiasPrazoContato.value) == '000'
                    || trim(document.forms[0].qtDiasPrazoContato.value) == '0000')
                {
                    alert('O campo Prazo de Atendimento deve ser maior que 0.');
                    return false;
                }
               // if(document.forms[0].msg.value  == '0' && trim(document.forms[0].mensagemNova.value) == '')
               // {
               //     alert('Preencha uma nova Mensagem de Aviso.');
               //     return false;
               // }
            }
            // var lista = document.forms[0].arrayAdmIndicadorAnatelAssociado;
            //Processa gravação
            // for(i = 0;i<lista.length;i++){
            //    document.forms[0].arrayAdmIndicadorAnatelAssociado.options[i].selected =true;
            // }
            return true;
        }

        function limpaNome(){
            document.forms[0].NomeContatoEscolhido.value = '';
        }

        function pesquisar(){
            if(trim(document.forms[0].descPesquisa.value).length < 3){
                alert('É necessário digitar no mínimo 3 caracteres para pesquisa.');
                document.forms[0].descPesquisa.focus();
                return false;
            }
            document.forms[0].target = 'ifmInnerBrowserContato';
            document.forms[0].action= 'getNome.do';
            parent.mostrar_div();
            document.forms[0].submit();
        }

        function exibeAtual(){
            document.forms[0].nomeTipo.value = 3;
            document.forms[0].idNomeContatoEscolhido.selectedIndex = 0;
            document.forms[0].NomeContatoEscolhido.value = '';
            document.getElementById("nomeContato").style.display = 'block';
            document.getElementById("pesquisaNome").style.display = 'none';
            var a = document.getElementById("divTipo1");
            a.style.visibility = 'hidden';
            var b = document.getElementById("divTipo2");
            b.style.visibility = 'hidden';
        }
        </script>

    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="adm_ceive_verpagina">
        <form action="insereItem.do" id="insereItem" name="insereItem" method="post">
            <input type="hidden" name="idContato" value="<%=request.getParameter("idContato")%>"/>
            <input type="hidden" name="nmContato" value="<%=(String)request.getAttribute("nmContato")%>">
            <input type="hidden" name="idContatoPai" value="<%=(String)request.getParameter("idContatoPai")%>"/>
            <input type="hidden" name="inDisponibilidade" value="<%=request.getParameter("inDisponibilidade")%>"/>
            <input type="hidden" name="idNomeContato" value="<%=request.getParameter("idNomeContato")%>">
            <input type="hidden" name="nrNivel" value="<%=request.getParameter("nrNivel")%>">
            <input type="hidden" name="inFolha" value="<%=request.getParameter("inFolha")%>">
            <html:hidden name="FormArvoreContato" property="dsPath"/>
            <!-- input type="hidden" name="dsPath" value="< %=request.getParameter("dsPath")%>" -->
            <html:hidden  property="nomeTipo" value="3"/>
            <input type="hidden" name="msg" value="2">
            <input type="hidden" name="salvaedita" value="<%=request.getParameter("salvaedita")%>">

        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
        <vivo:quadro id="descricao" width="100%" height="100" label='Descrição'>
            <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                <tr><td height="10" colspan="2"></td></tr>
                <tr>
                    <td width="12%" class="tblEstatica_campoNome" height="20" valign="top">
                        <netui:label value="Caminho: "/>
                    </td>
                    <td colspan="3" align="left" valign="top">
                    <script>
                       document.write('<%=(String)request.getAttribute("dsPath")%>');
                    </script>
                    </td>
                </tr>
                <tr>
                    <td width="15%" class="tblEstatica_campoNome">
                        <netui:label value="Tipo: "/>
                    </td>
                    <td width="35%" align="left" colspan="2">
						<logic:equal name="FormArvoreContato" property="inFolha" value="1">
							<input type="radio"
							       name="FormArvoreContato_inFolha"
							       class="radio"
							       value="1"
							       id="folha"
							       onclick="camposFolha(1);ajustaTamanho(parent.document.getElementById('detalhe'));" />Folha
						</logic:equal>

						<logic:equal name="FormArvoreContato" property="inFolha" value="0">
							<input type="radio"
							       name="FormArvoreContato_inFolha"
							       class="radio"
							       value="0"
							       id="pasta"
							       onclick="camposPasta(0);ajustaTamanho(parent.document.getElementById('detalhe'));" />Pasta
						</logic:equal>

						<logic:equal name="FormArvoreContato" property="inFolha" value="">
							<input type="radio"
							       name="FormArvoreContato_inFolha"
							       class="radio"
							       value="0"
							       id="pasta"
							       onclick="camposPasta(0);ajustaTamanho(parent.document.getElementById('detalhe'));" />Pasta

							<input type="radio"
							       name="FormArvoreContato_inFolha"
							       class="radio"
							       value="1"
							       id="folha"
							       onclick="camposFolha(1);ajustaTamanho(parent.document.getElementById('detalhe'));" />Folha
						</logic:equal>
                    </td>
                </tr>
                <tr>
                    <td width="15%" class="tblEstatica_campoNome">
                        <netui:label value="Nome: " defaultValue="0"/>
                    </td>
                    <td width="25%" align="left" colspan="2">
                        <div id="nomeContato" style="display:block;position:absolute; top: 70px; left: 180px; height: 0px; padding: 0px;">
                            <vivo:hint maxLength="20">
                                <bean:write name="FormArvoreContato" property="nmContato"/>
                            </vivo:hint>
                        </div>

                        <logic:equal name="FormArvoreContato" property="salvaedita" value="salva">
							<html:radio name="FormArvoreContato" styleClass="radio" styleId="novo" property="nomeTipo" value="0" onclick="escondeTipo(0);">&nbsp;Novo</html:radio>
							<html:radio name="FormArvoreContato" styleClass="radio" styleId="existente" property="nomeTipo" value="1" onclick="exibeTipo(1);">&nbsp;Existente</html:radio>
                        </logic:equal>

                        <logic:notEqual name="FormArvoreContato" property="salvaedita" value="salva">
							<html:radio name="FormArvoreContato" styleClass="radio" property="nomeTipo" styleId="atual" value="2" onclick="exibeAtual(0);">&nbsp;Atual</html:radio>
							<html:radio name="FormArvoreContato" styleClass="radio" property="nomeTipo" styleId="novo" value="0" onclick="escondeTipo(0);">&nbsp;Novo</html:radio>
							<html:radio name="FormArvoreContato" styleClass="radio" property="nomeTipo" styleId="existente" value="1" onclick="exibeTipo(1);">&nbsp;Existente</html:radio>
                        </logic:notEqual>

                    <div id="pesquisaNome" style="display:none;position:absolute; height: 0px; padding: 0px;width:210px;">
                        <html:text name="FormArvoreContato" property="descPesquisa" maxlength="30"/>
                        <img src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif"  style="border:none;cursor:hand" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif';" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif';" onClick="return pesquisar();" />
                    </div>
                </tr>
                <tr>
                    <td style="padding-left:6px;" colspan="4">
                        <div id="divTipo3" style="visibility:'visible'; position:relative; top: 0px; left: 0px; padding: 0px;">
                            <div id="divTipo1" style="visibility:'hidden'; position:absolute; top: 0px; left: 85px; height: 0px; padding: 0px;">
                                <html:select name="FormArvoreContato" property="idNomeContatoEscolhido" style="text-indent:3px;width:370px;left: -150px;" styleClass="SELECT" onchange="limpaNome();">
                                    <html:option value="-1">Escolha uma opção...</html:option>
                                </html:select>
                            </div>
                            <div id="divTipo2" style="visibility:'hidden'; position:absolute; top: 0px; left: 85px; height: 0px; padding: 0px;">
                                <input name="NomeContatoEscolhido" type="text"  style="width:370px" class="input" maxlength="240">
                            </div>
                        </div>
                    </td>
                </tr>
            </table>
        </vivo:quadro>
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
        <div id="divnome" style="visibility:'visible'; position:relative; top: 0px; left: 0px; height: 24px; padding: 0px;">
        <vivo:quadro id="dadosBasicos" width="100%" height="150" label="Dados Básicos">
            <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                <tr>
                    <!--<td class="tblEstatica_campoNome" height="30" valign="top">
                        <netui:label value="Fechamento:" styleClass="tblEstatica_campoNome" />
                    </td>
                    <td align="left" valign="top">
                        <html:select name="FormArvoreContato" property="fechamento" style="text-indent:3px;" styleClass="SELECT" >
                            <html:option value="-1">Escolha uma opção...</html:option>
                            <html:options collection="aFechamento" property="idTipoFechamento" labelProperty="nmTipoFechamento" />
                        </html:select>
                    </td>-->
                    <td class="tblEstatica_campoNome" height="30" valign="top">
                        <netui:label value="Prazo de Atendimento:" styleClass="tblEstatica_campoNome"/>
                    </td>
                    <td align="left" valign="top">
                        <!-- html:text  name="FormArvoreContato" property="qtDiasPrazoContato" maxlength="4" styleClass="input" style='width:60px;' onkeyup="checaInteiro(this)"/-->
                        <html:select name="FormArvoreContato" property="qtDiasPrazoContato" style="text-indent:3px;width:70px" styleClass="SELECT">
                            <html:option value="-1">Escolha...</html:option>
                            <logic:iterate name="FormArvoreContato" property="admPrazo" id="prazo">
                                <option value='<bean:write name="prazo" property="vlPrazo"/>'><bean:write name="prazo" property="vlPrazo"/></option>
                            </logic:iterate>
                        </html:select>
                    </td>
                    <td class="tblEstatica_campoNome" height="30" valign="top">
                        <netui:label value="Prazo C. Prévio:" styleClass="tblEstatica_campoNome"/>
                    </td>
                    <td align="left" valign="top">
                        <!-- html:text  name="FormArvoreContato" property="qtDiasPrazoContato" maxlength="4" styleClass="input" style='width:60px;' onkeyup="checaInteiro(this)"/-->
                        <html:select name="FormArvoreContato" property="qtDiasPrazoAnatel" style="text-indent:3px;width:70px" styleClass="SELECT">
                            <html:option value="-1">Escolha...</html:option>
                            <logic:iterate name="FormArvoreContato" property="admPrazoAnatel" id="prazoAnatel">
                                <option value='<bean:write name="prazoAnatel" property="vlPrazoAnatel"/>'><bean:write name="prazoAnatel" property="vlPrazoAnatel"/></option>
                            </logic:iterate>
                        </html:select>

                    </td>
                </tr>
                <tr>
                    <td class="tblEstatica_campoNome" height="30" valign="top">
                        <netui:label value="Processo Técnico:" styleClass="tblEstatica_campoNome"/>
                    </td>
                    <td align="left" valign="top">
                        <html:select name="FormArvoreContato" property="processoTec" style="text-indent:3px;" styleClass="SELECT" >
                            <html:option value="-1">Escolha uma opção...</html:option>
                            <html:options collection="aProcesso" property="idTipoProcesso" labelProperty="dsTipoProcesso" />
                        </html:select>
                    </td>
                    <td class="tblEstatica_campoNome" height="30" valign="top" >
                        <netui:label value="Peso/Prioridade:" styleClass="tblEstatica_campoNome"/>
                    </td>
                    <td align="left" valign="top">
                        <!-- html:text  name="FormArvoreContato" property="vlPesoContato" styleClass="input" style='width:60px' onkeyup="checaInteiro(this);" maxlength="10"/-->
                        <html:select name="FormArvoreContato" property="vlPesoContato" style="text-indent:3px;width:70px" styleClass="SELECT">
                            <html:option value="-1">Escolha...</html:option>
                            <logic:iterate name="FormArvoreContato" property="admPrioridade" id="prioridade">
                                <option value='<bean:write name="prioridade" property="vlPrioridade"/>'><bean:write name="prioridade" property="vlPrioridade"/></option>
                            </logic:iterate>
                        </html:select>
                    </td>
                </tr>
                <tr><td height="4" colspan="4"></td></tr>
            </table>
            </vivo:quadro>
        </div>
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
        <tr>
            <td colspan="2" >
            <acesso:controlHiddenItem nomeIdentificador="adm_ceive_salvar">
                <img id="btSalvar1"
                     src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif"
                     style="border:none;cursor:pointer;"
                     onClick="salvar(); return false;"
                     align="right" />
            </acesso:controlHiddenItem>
            </td>
        </tr>
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
        </form>
        
        <script FOR=window EVENT=onload LANGUAGE="JScript">
            document.body.style.backgroundColor = '#ededed';
            var op = '<bean:write name="FormArvoreContato" property="inFolha"/>';

            // Folha.
            if(op == '1'){
                camposFolha(1);
                document.forms[0].qtDiasPrazoContato.value = '<bean:write name="FormArvoreContato" property="qtDiasPrazoContato"/>';
                document.forms[0].qtDiasPrazoAnatel.value = '<bean:write name="FormArvoreContato" property="qtDiasPrazoAnatel"/>';
                document.forms[0].vlPesoContato.value = '<bean:write name="FormArvoreContato" property="vlPesoContato"/>';
            }
            // Pasta
            else if(op == '0')
                camposPasta(0);
            	//document.getElementById("atual").checked = true
            	//document.getElementById("pasta").checked = true

            else if(op == ''){
                document.forms[0].inFolha.value = 1;
                document.getElementById("existente").checked = true
                document.getElementById("folha").checked = true
                document.forms[0].nomeTipo.value=1;
                document.forms[0].msg.value = 1;
                document.forms[0].qtDiasPrazoContato.value = '120';
                document.forms[0].qtDiasPrazoAnatel.value = '120';
            }
            <logic:equal name="FormArvoreContato" property="salvaedita" value="salva">
                exibeTipo(1);
            </logic:equal>
            ajustaTamanho(parent.document.getElementById('detalhe'));
            document.forms[0].idNomeContatoEscolhido.value = '<%=request.getParameter("idNomeContato")%>';
            //document.forms[0].mensagem.value = '<bean:write name="FormArvoreContato" property="idMensagemAtual" />';
            //if(document.forms[0].mensagem.value == '')
            //{
            //    escondeAmbos(2);
            //}
            parent.oculta_div();
        </script>        
        <iframe style="width:0px;height:0px;" name="ifmInnerBrowserContato">
        </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>