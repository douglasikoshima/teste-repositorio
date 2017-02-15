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

<bean:define id="FormPesquisa" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="pesquisaForm"/>

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
            function carregaTI() {
                divDetalhe = top.frameApp.document.getElementById("detalhe");
                var selecionado = null;
                for (i=0; i < document.forms[0].linhaSelecionada.length; i++){
                    if(document.forms[0].linhaSelecionada[i].checked == true){
                        selecionado = i;
                    }
                }
                if (selecionado != null) {
                    linhaSelecionada = document.forms[0].linhaSelecionada[selecionado].value;
                    inBloqueado      = document.forms[0].linhaSelecionada[selecionado].accessKey;
                    nrDDD            = linhaSelecionada.substring(0,2);
                    if (inBloqueado==1) {
                        alert('DDD '+nrDDD+' bloqueado temporariamente. Não é possível seguir com o atendimento.');
                    } else {
                        top.frameApp.fechaLupa();
                        top.frameApp.setValoresPesquisa(linhaSelecionada);
                        try {
                            top.frameApp.$('ti_comboPesquisa').value = "celular";
                            top.frameApp.$('ti_inputValorPesquisa').value = linhaSelecionada;
                            top.frameApp.escolheMascara(top.frameApp.$('ti_inputValorPesquisa'));
                            top.frameApp.abrePesquisa();
                            //parent.document.getElementById('frmDados').src = "pesquisaCliente.do?valor="+linhaSelecionada+"&pesquisa=celular&tipoLista=clientes";
                        } catch(e) {}
                    }
                } else {
                    alert("Nenhuma linha foi selecionada.")
                }
            }
        
            function pesquisar(offset) {
                if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                document.forms[0].offset.value = offset;
                document.forms[0].action = "pesquisaCliente.do";
                document.forms[0].submit();
            }  
        </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="cli_lli_verpagina">
    <html:form action="/cliente/TelaInicial/pesquisaCliente.do">
        <html:hidden name="FormPesquisa" property="tipoPesquisa"/>
        <html:hidden name="FormPesquisa" property="pageNumber"/>
        <html:hidden name="FormPesquisa" property="hasNext"/>
        <html:hidden name="FormPesquisa" property="nome"/>
        <input type="hidden" name="offset" value="">
        <input type="hidden" name="tipoLista" value="<%=request.getParameter("tipoLista")%>">
        <input type="hidden" name="pesquisa" value="<%=request.getParameter("pesquisa")%>">
        <input type="hidden" name="valor" value="<%=request.getAttribute("valor")%>">
        <input type="hidden" name="idPessoa" value="<%=request.getAttribute("idPessoa")%>">
        
        
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
        <table class="tbl_bgGray" width="780">
            <tr>
                <td align="center" id="textoPesquisa"></td>
            </tr>        
        </table>
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
        <center><vivo:tbTable selectable="false" layoutWidth="300" layoutHeight="325" tableWidth="300" styleId="tableTitle" sortable="true">
            <vivo:tbHeader>
                <vivo:tbHeaderColumn align="center" width="5%"  type="string"></vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="95%" type="string">Linha</vivo:tbHeaderColumn>            
            </vivo:tbHeader>        
            <vivo:tbRows>
                <logic:iterate id="listaLinhas" name="FormPesquisa" property="listaLinhas.linhasPesquisadasArray">
                <vivo:tbRow key="linha">
                    <vivo:tbRowColumn><input type="radio" accesskey="<bean:write name="listaLinhas" property="inBloqueado"/>" class="radio" name="linhaSelecionada" value="<bean:write name="listaLinhas" property="nrLinha"/>"/></vivo:tbRowColumn>
                    <vivo:tbRowColumn><bean:write name="listaLinhas" property="nrLinha"/></vivo:tbRowColumn>                
                </vivo:tbRow>
            </logic:iterate>
            </vivo:tbRows>        
        </vivo:tbTable>
        
        <logic:notEmpty name="FormPesquisa" property="listaLinhas.linhasPesquisadasArray">
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
                    <acesso:controlHiddenItem nomeIdentificador="cli_lli_prosseguir">
                    <img src="/FrontOfficeWeb/resources/images/bt_prosseguir_nrml.gif" onmouseover="this.src='/FrontOfficeWeb/resources/images/bt_prosseguir_over.gif';" onmouseout="this.src='/FrontOfficeWeb/resources/images/bt_prosseguir_nrml.gif';" style="cursor:hand;" onclick="carregaTI()" id="btProsseguir">
                    </acesso:controlHiddenItem>
                    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
                </td>
            </tr>        
        </table>
    </html:form>
    <script language="Javascript">
        if (document.forms[0].linhaSelecionada && document.forms[0].linhaSelecionada.length){
            texto = "Foram encontradas múltiplas linhas. Selecione a opç&atilde;o desejada e clique em <b>Prosseguir</b>.";
        }else if(document.forms[0].clienteSelecionado){
            texto = "Foi encontrado um resultado. Selecione e clique em <b>Prosseguir</b>.";
        }else{
            texto = "Não foi encontrada nenhuma linha.";
            document.getElementById('btProsseguir').style.display = "none";
        }
        document.getElementById('textoPesquisa').innerHTML = texto;
    </script>
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>