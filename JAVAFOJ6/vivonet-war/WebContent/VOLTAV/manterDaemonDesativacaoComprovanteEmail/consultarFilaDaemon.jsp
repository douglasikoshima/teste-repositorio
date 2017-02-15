<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="FormManterDaemon" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formManterDaemon" type="VOLTAV.manterDaemonDesativacaoComprovanteEmail.ManterDaemonController.FormManterDaemon"/>

<bean:define id="aGrupoErro" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formManterDaemon.prePagoFilaPorGrupo.grupoErroVOArray"/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Manter Daemon"/>
    <netui-template:setAttribute name="modulo" value="Gestão de Clientes"/>

    <netui-template:section name="headerSection">

        <script language="Javascript">
            var auxNavega = '';
            var idFilaArray = new Array(50);

            function carregaDetalhes(cdErro){
                var form = document.forms[0];

                form.cdErro.value = cdErro;

                form.target = 'innerBrowserPesquisaDetalhada';
                form.action = 'consultaDetalheFila.do'

                mostrar_div();
                form.submit();


            }

            function atualizarById(valor){

                var form = document.forms[0];
                var strIdFila = '';
                for(i=0; i < idFilaArray.length ; i++){
                    if(idFilaArray[i] != '' && idFilaArray[i] != 0 && idFilaArray[i] != undefined){
                        strIdFila = strIdFila + idFilaArray[i]+';';
                    }
                }

                form.arrayIdFila.value = strIdFila;

                if(trim(form.arrayIdFila.value) == ''){
                    alert('Não existe(em) registro(s) selecionado(s).\n Selecione pelo menos um item na lista de erro.');
                    return false;
                }

                if(!confirm('Deseja enviar novamente este(s) registro(s) para o processamento ?')){
                    return false;
                }

                form.target = '';
                form.action = 'atualizarById.do'
                mostrar_div();

                form.submit();

            }

            function excluirById(valor){

                var form = document.forms[0];
                var strIdFila = '';

                for(i=0; i < idFilaArray.length ; i++){
                    if(idFilaArray[i] != '' && idFilaArray[i] != 0 && idFilaArray[i] != undefined){
                        strIdFila = strIdFila + idFilaArray[i]+';';
                    }
                }

                form.arrayIdFila.value = strIdFila;

                if(trim(form.arrayIdFila.value) == ''){
                    alert('Não existe(em) registro(s) selecionado(s).\n Selecione pelo menos um item na lista de erro.');
                    return false;
                }

                if(!confirm('Deseja excluir o(s) registro(s) selecionado(s) ?')){
                    return false;
                }

                form.target = '';
                form.action = 'deleteById.do'
                mostrar_div();

                form.submit();
            }

            function voltar(){
                   document.getElementById('div_lista_agrupada_erro').innerHTML = auxNavega;
            }

            function paginarPesquisa(op, botao){
                var form = document.forms[0];
                if(botao == true){
                    var pagAtual = form.paginaAtual.value;
                    pagAtual = parseInt(pagAtual)+parseInt(op);

                    form.paginaAtual.value = pagAtual;

                }else{
                    form.paginaAtual.value = op;
                }


                form.action = 'paginarPesquisa.do';
                form.target = 'innerBrowserPesquisaDetalhada';

                mostrar_div();

                form.submit();

            }


            function checkLista(obj){

                var strArray = obj.value.split(';');

                var idFila          = strArray[0];
                var idx             = strArray[1];

                if(obj.checked == true){
                    idFilaArray[idx]        = idFila;

                }else{
                    idFilaArray[idx]        = '';
                }

            }

            function atualizarByGrupoErro(cdErroParam){

                if(!confirm('Deseja enviar novamente para a fila, todos os registros que apresentaram este erro ?')){
                    return false;
                }

                var form = document.forms[0];

                form.cdErro.value = cdErroParam;

                form.target = '';
                form.action = 'atualizarByCodigoErro.do'
                mostrar_div();

                form.submit();

            }

            function excluirByGrupoErro(cdErroParam){

                if(!confirm('Deseja realmente excluir todos os registros que apresentaram este erro ?')){
                    return false;
                }

                var form = document.forms[0];

                form.cdErro.value = cdErroParam;

                form.target = '';
                form.action = 'deleteByCodigoErro.do'
                mostrar_div();

                form.submit();

            }

            function visualizarXML(idFilaSetClient){

                var form = document.forms[0];

                form.idcompservicodesativado.value = idFilaSetClient;

                divXmlDaemon.style.display = '';
                form.action = 'getXml.do';
                form.target = "ifrmXmlDaemon";
                mostrar_div();

                form.submit();

            }

        </script>

    </netui-template:section>

    <netui-template:section name="bodySection">

        <!--APLICAÇÃO-> MENU-->
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />

        <vivo:quadro id="qdMain" height="475" width="790" label="Consulta de Fila" scroll="no">

            <acesso:controlHiddenItem nomeIdentificador="ver_manter_daemon_con_fila_dce">

            <form name="listaConjuntoForm" onsubmit="return false;">

            <div id="div_lista_agrupada_erro">

            <vivo:quadro id="qdMain" height="435" width="780" label="Status da Fila" scroll="no">
                <logic:greaterThan name="FormManterDaemon" property="lengthLista" value="0">
                <table width="100%">
                    <tr>
                        <td>
                            <vivo:tbTable selectable="true" layoutWidth="750" layoutHeight="350" tableWidth="740" styleId="tableTitle" sortable="true" >
                                <vivo:tbHeader>
                                    <vivo:tbHeaderColumn align="center" width="25%" type="string">Código de Erro</vivo:tbHeaderColumn>
                                    <vivo:tbHeaderColumn align="center" width="40%" type="string">Descrição do Erro</vivo:tbHeaderColumn>
                                    <vivo:tbHeaderColumn align="center" width="20%" type="string">Qtde de Registros</vivo:tbHeaderColumn>
                                    <vivo:tbHeaderColumn align="center" width="5%" type="string">&nbsp</vivo:tbHeaderColumn>
                                    <vivo:tbHeaderColumn align="center" width="5%"  type="string">&nbsp;</vivo:tbHeaderColumn>
                                    <vivo:tbHeaderColumn align="center" width="5%"  type="string">&nbsp;</vivo:tbHeaderColumn>
                                </vivo:tbHeader>
                                <vivo:tbRows>
                                    <logic:iterate name="aGrupoErro" id="lista" indexId="idx">
                                        <vivo:tbRow key="linha">
                                            <vivo:tbRowColumn>
                                                  <bean:write name="lista" property="cdErro"/>
                                            </vivo:tbRowColumn>

                                            <vivo:tbRowColumn>
                                                    <bean:write name="lista" property="dsErro"/>
                                            </vivo:tbRowColumn>

                                            <vivo:tbRowColumn>
                                                    <bean:write name="lista" property="countErro"/>
                                            </vivo:tbRowColumn>

                                            <vivo:tbRowColumn>
                                                    <acesso:controlHiddenItem nomeIdentificador="excluir_manter_daemon_con_fila_dce">
                                                        <logic:notEqual name="lista" property="cdErro" value="">
                                                            <logic:notEqual name="lista" property="cdErro" value="NULO">
                                                                <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" border="0" onclick="excluirByGrupoErro('<bean:write name="lista" property="cdErro"/>');">
                                                            </logic:notEqual>
                                                        </logic:notEqual>
                                                    </acesso:controlHiddenItem>
                                            </vivo:tbRowColumn>

                                            <vivo:tbRowColumn>
                                                    <acesso:controlHiddenItem nomeIdentificador="atualizar_manter_daemon_con_fila_dce">
                                                        <logic:notEqual name="lista" property="cdErro" value="">
                                                            <logic:notEqual name="lista" property="cdErro" value="NULO">
                                                                <img src="/FrontOfficeWeb/resources/images/bt_icon_atualizar.gif" border="0" onclick="atualizarByGrupoErro('<bean:write name="lista" property="cdErro"/>');">
                                                            </logic:notEqual>
                                                        </logic:notEqual>
                                                    </acesso:controlHiddenItem>
                                            </vivo:tbRowColumn>

                                            <vivo:tbRowColumn>
                                                    <acesso:controlHiddenItem nomeIdentificador="parametrizar_manter_daemon_con_fila_dce">
                                                        <logic:notEqual name="lista" property="cdErro" value="">
                                                            <logic:notEqual name="lista" property="cdErro" value="NULO">
                                                                <img src="/FrontOfficeWeb/resources/images/bt_icon_propried.gif" border="0" onclick="carregaDetalhes('<bean:write name="lista" property="cdErro"/>');">
                                                            </logic:notEqual>
                                                        </logic:notEqual>
                                                    </acesso:controlHiddenItem>
                                            </vivo:tbRowColumn>
                                        </vivo:tbRow>
                                    </logic:iterate>
                                </vivo:tbRows>
                            </vivo:tbTable>

                        </td>
                    </tr>
                </table>
                </logic:greaterThan>
                <logic:equal name="FormManterDaemon" property="lengthLista" value="0">
                    <table width="100%" height="370">
                        <tr>
                            <td align="center" valign="top"><b>* Não Existem erros até o momento.</b></td>
                        </tr>
                    </table>
                </logic:equal>
                <br>
              <table width="100%" border="0" cellpadding="3" cellspacing="1" bgcolor="#adadad">
                <tr>
                  <td bgcolor="#ededed"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td width="10%"><span class="tblEstatica_campoNome">Legendas:</span></td>
                        <td width="5%"><div align="center"><img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" width="18" height="18"></div></td>
                        <td width="15%">Excluir Erro </td>
                        <td width="5%"><div align="center"><img src="/FrontOfficeWeb/resources/images/bt_icon_atualizar.gif" width="20" height="20"></div></td>
                        <td width="15%">Atualizar Erro </td>
                        <td width="5%"><div align="center"><img src="/FrontOfficeWeb/resources/images/bt_icon_propried.gif" width="18" height="18"></div></td>
                        <td width="45%">Exibir Detalhes </td>
                      </tr>
                  </table>
                  </td>
                 </tr>
            </table>

            <html:hidden name="FormManterDaemon" property="cdErro"/>

            </form>

            </vivo:quadro>

            <img hspace="8" vspace="6" style="border:none;" onClick="window.location.href='/FrontOfficeWeb/index.jsp'; return false" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_voltar_over.gif"/>

            </div>

            </acesso:controlHiddenItem>

        </vivo:quadro>

        <iframe scrolling="yes"  name="innerBrowserPesquisaDetalhada" height="0px" width="0px"></iframe>

        <div id="div_copia_voltar" style="visibility:hidden;"></div>

        <script language="JavaScript">

            <acesso:controlHiddenItem nomeIdentificador="ver_manter_daemon_con_fila_dce">

                auxNavega = document.getElementById('div_lista_agrupada_erro').innerHTML;

           </acesso:controlHiddenItem>

        </script>

        <vivo:alert atributo="msgStatus" scope="request"/>

    </netui-template:section>
</netui-template:template>