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


<bean:define id="FormPesquisa" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="pesquisaForm" />

<netui-template:template templatePage="./../../resources/jsp/CRMTemplateSemCabec.jsp">
    <netui-template:setAttribute value="FrontOffice - Atendimento" name="title"/>
    <netui-template:setAttribute value="Atendimento" name="modulo"/>
    <netui-template:section name="headerSection">
        <netui:exceptions showMessage="false"/>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
            <!--
                parent.oculta_div();
            -->
        </SCRIPT>
        <script>
            <!--
            function listaLinhas(){
                //alert('listaLinhas');
                valor = null;
                if(document.forms[0].clienteSelecionado.length){
                    for(i=0; i < document.forms[0].clienteSelecionado.length; i++){
                        if (document.forms[0].clienteSelecionado[i].checked){
                            valor = document.forms[0].clienteSelecionado[i].value;
                        }
                    }
                }else{
                    valor = document.forms[0].clienteSelecionado.value;
                }
                if(valor == null){
                    alert("Favor selecionar um Cliente!");
                }else{
                    //alert('else');
                    document.forms[0].action = "pesquisaCliente.do?tipoLista=linhas&idPessoa="+valor;
                    parent.mostrar_div();
                    document.forms[0].method = "POST";
                    document.forms[0].submit();
                }
            }
    
            function cadastrarProspect(){
                //document.forms[0].action = "/FrontOfficeWeb/cliente/Prospects/begin.do?destino=POPUP";
                document.forms[0].action = "irCadastroProspect.do";
                parent.mostrar_div();
                document.forms[0].method = "POST";
                document.forms[0].submit();
            }
    
            function pesquisar(offset) {
                //Inicio animação
                if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                document.forms[0].offset.value = offset;
                document.forms[0].action = "pesquisaCliente.do";
                document.forms[0].submit();
            }
            -->
        </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="cli_lcl_verpagina">
    <html:form method="post" action="/cliente/TelaInicial/pesquisaCliente.do">
        <html:hidden name="FormPesquisa" property="tipoPesquisa"/>
        <html:hidden name="FormPesquisa" property="nome"/>
        <html:hidden name="FormPesquisa" property="nmMeio"/>
        <html:hidden name="FormPesquisa" property="sobrenome"/>
        <html:hidden name="FormPesquisa" property="pageNumber"/>
        <html:hidden name="FormPesquisa" property="hasNext"/>
        <input type="hidden" name="offset" value="">
        <input type="hidden" name="tipoLista" value="<%=request.getParameter("tipoLista")%>">
        <input type="hidden" name="pesquisa" value="<%=request.getParameter("pesquisa")%>">
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
        <table class="tbl_bgGray" width="780">
            <tr>
                <td align="center" id="textoPesquisa"></td>
            </tr>
        </table>
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
        <logic:equal name="FormPesquisa" property="inError" value="TRUE">
            <script>
                alert('Muitos resultados de retorno! Favor refinar a pesquisa!');
            </script>
        </logic:equal>
        <vivo:tbTable selectable="true" layoutWidth="764" layoutHeight="425" tableWidth="764" styleId="tableTitle" sortable="true">
            <vivo:tbHeader>
                <vivo:tbHeaderColumn align="center" width="31"  type="string"></vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width=""    type="string">Nome</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="66"  type="string">Documento</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="80"  type="string">Nº Documento</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="110" type="string">Tipo</vivo:tbHeaderColumn>
            </vivo:tbHeader>
            <vivo:tbRows>
                <logic:iterate id="listaClientes" name="FormPesquisa" property="listaClientes.clientesPesquisadosArray">
                    <vivo:tbRow key="Linha">
                        <vivo:tbRowColumn>
                            <input type="radio" class="radio" name="clienteSelecionado" value="<bean:write name="listaClientes" property="idPessoa"/>"/>
                        </vivo:tbRowColumn>
                        <vivo:tbRowColumn><bean:write name="listaClientes" property="nmNome"/></vivo:tbRowColumn>
                        <vivo:tbRowColumn><bean:write name="listaClientes" property="dsTipoDocumento"/></vivo:tbRowColumn>
                        <vivo:tbRowColumn><bean:write name="listaClientes" property="nrDocumento"/></vivo:tbRowColumn>
                        <vivo:tbRowColumn><bean:write name="listaClientes" property="dsTipoPessoa"/></vivo:tbRowColumn>
                    </vivo:tbRow>
                </logic:iterate>
            </vivo:tbRows>
        </vivo:tbTable>
        <logic:notEmpty name="FormPesquisa" property="listaClientes.clientesPesquisadosArray">
            <table width="100%">
                <tr>
                    <td align="center">
                        <logic:notEqual name="FormPesquisa" property="pageNumber" value="1">
                            <img onClick="pesquisar(-1);return false;" id="btAnterior" src="/FrontOfficeWeb/resources/images/bt_pag_anterior_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_pag_anterior_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_pag_anterior_over.gif'" style="border:nonecursor:hand;"/>
                        </logic:notEqual>
                    </td>
                    <td align="center">
                        <logic:equal name="FormPesquisa" property="hasNext" value="1">
                            <img onClick="pesquisar(1); return false;" id="btProximo" src="/FrontOfficeWeb/resources/images/bt_prox_pag_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_prox_pag_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_prox_pag_over.gif'" style="border:nonecursor:hand;"/>
                        </logic:equal>
                    </td>
                </tr>
            </table>
        </logic:notEmpty>
        <table width="780">
            <tr>
                <td align="right">
                    <acesso:controlHiddenItem nomeIdentificador="cli_lcl_cadastrar">
                    <img src="/FrontOfficeWeb/resources/images/bt_cadastrar_prospect_nrml.gif" onmouseover="this.src='/FrontOfficeWeb/resources/images/bt_cadastrar_prospect_over.gif';" onmouseout="this.src='/FrontOfficeWeb/resources/images/bt_cadastrar_prospect_nrml.gif';" style="cursor:hand;border:none;" onclick="cadastrarProspect()">
                    </acesso:controlHiddenItem>
                    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
                    <acesso:controlHiddenItem nomeIdentificador="cli_lcl_prosseguir">
                    <img id="btProsseguir" src="/FrontOfficeWeb/resources/images/bt_prosseguir_nrml.gif" onmouseover="this.src='/FrontOfficeWeb/resources/images/bt_prosseguir_over.gif';" onmouseout="this.src='/FrontOfficeWeb/resources/images/bt_prosseguir_nrml.gif';" style="cursor:hand;border:none;" onclick="listaLinhas()">
                    </acesso:controlHiddenItem>
                    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
                </td>
            </tr>
        </table>
    </html:form>
    <script language="Javascript">
        if (document.forms[0].clienteSelecionado && document.forms[0].clienteSelecionado.length){
            texto = "Foram encontrados múltiplos resultados. Selecione a opç&atilde;o desejada e clique em <b>Prosseguir</b>.";
        }else if(document.forms[0].clienteSelecionado){
            texto = "Foi encontrado um resultado. Selecione e clique em <b>Prosseguir</b>.";
        }else{
            texto = "Não foi encontrado nenhum registro.";
            document.getElementById('btProsseguir').style.display = "none";
        }
        document.getElementById('textoPesquisa').innerHTML = texto;

        /* top.frameApp.$('iframePopupTI').height = document.body.scrollHeight + 7;
        top.frameApp.$('iframePopupTI').width  = document.body.scrollWidth + 7;
        top.frameApp.changePopupTIProps(); */
        //top.frameApp.changePopupTIProps(document.body.scrollWidth + 7, document.body.scrollHeight + 7)
        top.frameApp.changePopupTIProps(document.body.scrollWidth, document.body.scrollHeight);

    </script>
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>