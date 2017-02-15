<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>
<bean:define id="form"     name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"  property="matrizForm" />
<bean:define id="ofertasDisp" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"  property="matrizForm.ofertasDisp" />
<bean:define id="ofertasSel" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"  property="matrizForm.ofertasSel" />
<bean:define id="RegionalArray"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="matrizForm.regionalArray"/>
<bean:define id="TipoClienteArray"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="matrizForm.tipoClienteArray"/>
<bean:define id="SegmentacaoArray"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="matrizForm.segmentacaoArray"/>
<bean:define id="IntencaoArray"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="matrizForm.intencaoArray"/>
<bean:define id="listaGrupo"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaGrupo.itemListaVOArray"/>
<bean:define id="DestinoPrevistoArray"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="matrizForm.destinoPrevistoArray"/>
<netui-template:template templatePage="/resources/jsp/templateRetencao.jsp">
    <netui-template:section name="headerSection">
        <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/abas.js"></script>
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js" ></script>
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/xtooltip.js" ></script>
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js" ></script>
        <script language=javascript>
            <!--
                var aba = new Abas();
                var abaOfertas;
                var abaFiltro;

                function desabilitaSubmit(){
                    aba.desabilitaAbas();
                }

                function habilitaSubmit(){
                    aba.habilitaAbas();
                }

                function initPagina(){
                    top.frameApp.mostrar_div();

                    abaFiltro   = aba.criaAba("Filtros de Pesquisa", "/FrontOfficeWeb/fidelizacao/Configurar/ConfigurarMatrizOferta/abaFiltro.do");
                    document.getElementById(abaFiltro).onclick = new Function("mostra(this); top.frameApp.mostrar_div();");

                    abaOfertas = aba.criaAba("Ofertas", "/FrontOfficeWeb/fidelizacao/Configurar/ConfigurarMatrizOferta/pesquisarIntencao.do");
                    document.getElementById(abaOfertas).onclick = new Function("mostra(this); top.frameApp.mostrar_div();");

                    aba.selecionaAba(document.getElementById(abaFiltro), true);
                }

                function selecionaAbaOfertas(){
                    aba.selecionaAba(document.getElementById(abaOfertas), false);
                }

                function selecionaAbaFiltro(){
                    aba.selecionaAba(document.getElementById(abaFiltro), false);
                }

                function LimpaSelecao(lista) {
                    for(var i = 0; i < lista.options.length; i++) {
                        if(lista.options[i].selected) {
                            lista.options[i] = null;
                            i = -1;
                        }
                    }
                }

                function VerificaDuplicidadeLista(valor, lista) {
                    for(var i = 0; i < lista.options.length; i++) {
                        if(lista.options[i].text == valor) {
                            return(true);
                        }
                    }
                    return(false);
                }

                function buscaOfertas(){
                    if(validarCombos2()){
                        top.frameApp.mostrar_div();
                        document.forms["matrizOfertaForm"].action = "ActionMatrizOferta.do?acao=pesquisarOfertas";
                        document.forms["matrizOfertaForm"].submit();
                    }
                }

                function salvar(obj){
                    if(document.matrizOfertaForm.regionalSel.value == ""){
                        alert("Favor selecionar uma Regional!");
                    }else if(document.matrizOfertaForm.tipoClienteSel.value == ""){
                        alert("Favor selecionar um Tipo de Cliente!");
                    }else if(document.matrizOfertaForm.segmentacaoSel.value == ""){
                        alert("Favor selecionar uma Seguimentação!");
                    }else if(document.matrizOfertaForm.idGrupo.value == ""){
                        alert("Favor selecionar um Grupo de Atendimento!");
                    }else if(document.matrizOfertaForm.intencaoSel.value == ""){
                        alert("Favor selecionar uma Intenção de Cancelamento!");
                    }else if(document.matrizOfertaForm.destinoSel.value == ""){
                        alert("Favor selecionar um Destino Previsto!");
                    }else{
                        top.frameApp.mostrar_div();
                        for(i=0; i < document.matrizOfertaForm.ofertasSelecionadasSel.length; i++){
                            document.matrizOfertaForm.ofertasSelecionadasSel.options[i].selected = true;
                        }
                        document.matrizOfertaForm.action = "ActionMatrizOferta.do?acao=salvar";
                        document.matrizOfertaForm.submit();
                    }
                }

                function buscaDestino(){
                    if(validarCombos()){
                        top.frameApp.mostrar_div();
                        document.matrizOfertaForm.action = "ActionMatrizOferta.do?acao=pesquisarDestino";
                        document.matrizOfertaForm.submit();
                    }
                }

                function matrizOferta() {
                    top.frameApp.mostrar_div();
                    divMatriz = top.frameApp.document.getElementById('dvMatOfertas');
                    divMatriz.style.display = '';
                    top.frameApp.ifrmMatOfertas.location.href = 'montarArvore.do?acao=matrizOferta';
                }

                function limpaCampos(){
                    for(i=0;i<document.matrizOfertaForm.intencaoSel.options.length;i++){
                        if(document.matrizOfertaForm.intencaoSel.options[i].value == ""){
                            document.matrizOfertaForm.intencaoSel.options[i].selected = true;
                        }
                    }
                    deleteFixoTodos(document.matrizOfertaForm.destinoSel);
                    deleteFixoTodos(document.matrizOfertaForm.ofertasSelecionadasSel);
                    deleteFixoTodos(document.matrizOfertaForm.ofertaDispSel);
                }

                function validarCombos(){
                    msg = "";
                    if(document.forms[0].elements["regionalSel"].value==""){
                        msg+= "- Regional\n";
                    }
                    if(document.forms[0].elements["tipoClienteSel"].value==""){
                        msg+= "- Tipo de Cliente\n";
                    }
                    if(document.forms[0].elements["segmentacaoSel"].value==""){
                        msg+= "- Segmentação\n";
                    }
                    if(document.forms[0].elements["idGrupo"].value==""){
                        msg+= "- Grupo de Atendimento\n";
                    }
                    if(msg!=""){
                        alert("Por favor selecione:\n" + msg);
                        return false;
                    }
                    return true;
                }

                function validarCombos2(){
                    msg = "";
                    if(document.forms[0].elements["regionalSel"].value==""){
                        msg+= "- Regional\n";
                    }
                    if(document.forms[0].elements["tipoClienteSel"].value==""){
                        msg+= "- Tipo de Cliente\n";
                    }
                    if(document.forms[0].elements["segmentacaoSel"].value==""){
                        msg+= "- Segmentação\n";
                    }
                    if(document.forms[0].elements["idGrupo"].value==""){
                        msg+= "- Grupo de Atendimento\n";
                    }
                    if(document.forms[0].elements["intencaoSel"].value==""){
                        msg+= "- Intenção de Cancelamento\n";
                    }
                    if(document.forms[0].elements["destinoSel"].value==""){
                        msg+= "- Destino Previsto\n";
                    }
                    if(msg!=""){
                        alert("Por favor selecione:\n" + msg);
                        return false;
                    }
                    return true;
                }
            -->
        </script>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
            <!--
                top.frameApp.oculta_div();
            -->
        </SCRIPT>
    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="fid_cmo_verpagina">
    <form method="post" action="" id="formMatrizOferta" name="formMatrizOferta" enctype="multipart/form-data" style="margin:0px;">
        <table border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;margin-left:10px;">
            <tr id="trAbas">
        </table>
        <table width="760" height="390" cellpadding="0" cellspacing="0" class="BorderTable1" style="margin-left:10px;">
            <tr>
                <td valign="top" class="tbl_bgGray">
                    <iframe id="fraAbas" width="760" height="390" frameborder="0" scrolling="no" src="about:blank"></iframe>
                </td>
            </tr>
        </table>
        <script language="javaScript">
            initPagina();
        </script>
    <script>
        document.body.style.backgroundColor = '#ededed';
    </script>
    </form>
</acesso:controlHiddenItem>
</netui-template:section>
</netui-template:template>
