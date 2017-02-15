<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="parametrizacaoOfertaForm"/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
<netui-template:setAttribute name="title" value="Parametrização de Ofertas"/>
<netui-template:setAttribute name="modulo" value="Administração"/>

<netui-template:section name="headerSection">
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <script type="text/javascript">
        function pesquisarOfertas() {
            if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
            var f = document.forms[0];
            f.action = "pesquisar.do";
            f.submit();
        }

        function incluirOferta() {
            var f = document.forms[0];
            if(validaForm()){
                if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                f.action = "incluir.do";
                f.submit();
            }
        }

        function validaForm(){
            var f = document.forms[0];
            if(f.sgTipoOferta.value==""){
                alert("Por favor, selecione o Tipo de Oferta");
                return false;
            }
            if(f.dsOferta.value==""){
                alert("Por favor, preencha a Descrição da Oferta");
                return false;
            }
            return true;
        }

        function selecionaAlterar(idOferta,sgTpOferta,dsOferta){
            document.getElementById('btPesquisar').style.display = "none";
            document.getElementById('btCancelar').style.display = "block";
            document.getElementById('btIncluir').style.display = "none";
            document.getElementById('btAlterar').style.display = "block";
            var f = document.forms[0];
            f.sgTipoOferta.value = sgTpOferta;
            f.dsOferta.value     = dsOferta;
            f.idOferta.value     = idOferta;
        }

        function alterarOferta(){
            var f = document.forms[0];
            if(validaForm()){
                if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                f.action = "alterar.do";
                f.submit();
            }
        }

        function excluirOferta(idOferta){
            if(confirm('Deseja realmente excluir esta oferta?')){
                if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                var f = document.forms[0];
                f.idOferta.value = idOferta;
                f.action = "excluir.do";
                f.submit();
            }
        }

        function cancelarAlteracao(){
            document.getElementById('btPesquisar').style.display = "block";
            document.getElementById('btCancelar').style.display = "none";
            document.getElementById('btIncluir').style.display = "block";
            document.getElementById('btAlterar').style.display = "none";
            document.forms[0].reset();
        }

        function voltar(){
            window.top.frameApp.location.href = "/FrontOfficeWeb/index.jsp";
        }
    </script>
    <script language="javascript" FOR="window" event="onload">
        <!--
            <logic:notEmpty name="msgError" scope="request">
                alert('<bean:write name="msgError" scope="request"/>');
            </logic:notEmpty>
            if(top.frameApp.dvAnimarAguarde != null) top.frameApp.stopAnimation();
            document.body.style.backgroundColor = '#ededed';
        -->
    </script>
</netui-template:section>
<netui-template:section name="bodySection">
<jsp:include page="/resources/menus/MenuPrincipal.jsp" />
<div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>

<vivo:sessao id="qdMain" height="460" width="790" label="Administração" operacoes="Parametrização de Ofertas">
    <form method="post" action="" id="formContato" name="formContato" enctype="multipart/form-data" style="margin:5 0px;" onsubmit="return false;">
        <input type="hidden" name="idOferta" value="">
        <table cellpadding="0" cellspacing="0" width="100%">
            <tr>
                <td>Tipo da oferta:</td>
                <td colspan="3" align="left">Descrição:</td>
            </tr>
            <tr>
                <td>
                    <html:select name="Form" property="sgTipoOferta" style="width:150px;" styleClass="SELECT">
                        <option value="">-- Selecione --</option>
                        <html:optionsCollection name="Form" property="listaOfertas.itArray" value="id" label="ds"/>
                    </html:select>
                    <%--select name="sgTipoOferta" id="sgTipoOferta" style="width:150px;" class="SELECT">
                        <option value="">-- Selecione --</option>
                        <logic:iterate id="it" name="Form" property="listaOfertas.itArray" indexId="idx">
                            <option value="<bean:write name="it" property="id"/>"><bean:write name="it" property="ds"/></option>
                        </logic:iterate>
                    </select--%>
                </td>
                <td align="left">
                    <input type="text" id="dsOferta" name="dsOferta" style="width:300px" />
                </td>
                <td>
                    <div id="btPesquisar"><img src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" style="border:0px;cursor:pointer;" onclick="pesquisarOfertas();"/></div>
                    <div id="btCancelar" style="display:none;"><img src="/FrontOfficeWeb/resources/images/bt_cancelar_nrml.gif" style="border:0px;cursor:pointer;" onclick="cancelarAlteracao();"/></div>
                </td>
                <td>
                    <div id="btIncluir"><img src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif" style="border:0px;cursor:pointer;" onclick="incluirOferta();"/></div>
                    <div id="btAlterar" style="display:none;"><img src="/FrontOfficeWeb/resources/images/bt_alterar_nrml.gif" style="border:0px;cursor:pointer;" onclick="alterarOferta();"/></div>
                </td>
            </tr>
        </table>
    </form>

    <vivo:tbTable selectable="true" onRowClick="" layoutWidth="763" layoutHeight="355" tableWidth="763" styleId="tableTitle" sortable="true">
        <vivo:tbHeader>
            <vivo:tbHeaderColumn align="left" width="45%" type="string">Oferta</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="left" width="45%" type="">Tipo</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="left" width="5%"  type="">&nbsp;</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="left" width="5%"  type="">&nbsp;</vivo:tbHeaderColumn>
        </vivo:tbHeader>
        <vivo:tbRows>

            <logic:iterate id="it" name="Form" property="fidelizacaoVO.tabelas.paramOfertas.linhaArray" indexId="idx">
            <vivo:tbRow key="linha1">
                <vivo:tbRowColumn><bean:write name="it" property="dsOferta"/></vivo:tbRowColumn>
                <vivo:tbRowColumn><bean:write name="it" property="dsTpOferta"/></vivo:tbRowColumn>
                <vivo:tbRowColumn><img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" onclick="selecionaAlterar('<bean:write name="it" property="idOferta"/>','<bean:write name="it" property="sgTpOferta"/>','<bean:write name="it" property="dsOferta"/>');" style="cursor:pointer;"/></vivo:tbRowColumn>
                <vivo:tbRowColumn><img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" onclick="excluirOferta('<bean:write name="it" property="idOferta"/>');" style="cursor:pointer;"/></vivo:tbRowColumn>
            </vivo:tbRow>
            </logic:iterate>

        </vivo:tbRows>
    </vivo:tbTable>

    <div style="float:left;margin-top:10px;">
        <img src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" onclick="voltar();" style="cursor:pointer;"/>
    </div>
</vivo:sessao>
</netui-template:section>
</netui-template:template>
