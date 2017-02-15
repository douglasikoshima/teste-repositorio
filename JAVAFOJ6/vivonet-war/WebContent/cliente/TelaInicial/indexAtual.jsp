<%@page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page import="br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="tiForm" />
<bean:define id="formpesquisa" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="pesquisaForm" />
<bean:define id="grupos" name="form" property="gruposVO"/>
<% 
String template = "./../../resources/jsp/CRMTemplate.jsp";
if((request.getParameter("idAtendimento")!=null) && (request.getParameter("idAtendimento").length() > 0)){
    template = "./../../resources/jsp/CRMTemplateDet.jsp";
}
%>
<netui-template:template templatePage='<%=template%>'>
    <netui-template:setAttribute value="FrontOffice - Atendimento" name="title"/>
    <netui-template:setAttribute value="Atendimento" name="modulo"/>
    <netui-template:section name="headerSection">
        <netui:exceptions showMessage="false"/>    
        <link href="../../resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
        <script language="javascript" src="../../resources/scripts/frameweb.js"></script>
		<script type="text/javascript" src="../../resources/scripts/vivoval.js"></script>
        <script type="text/javascript" src="../../resources/scripts/telainicial_atual.js"></script>                                
        <script type="text/javascript" src="../../resources/scripts/toolTip.js"></script>
        <script>
            <!--
            var oldtopdiv = 0;
            var oldtopfrm = 0;
            var oldtamfrm = 0;
            var oldlardiv = 0;
            var up = true;
            var created = false;
            inVerAbaRelacionamento = null;
            var ajusteFila = 0;
            
            var pesquisaEfetuada = false;
            var idPosAuto = true;
            var idChamadaTelefonica = '<bean:write name="form" property="parametros.idChamadaTelefonica"/>';        
            var idRelacionamento = '';
    
            //variavel q diz se ligaçao identificada
            var identificado = false;
            

            inVerAbaRelacionamento = 1;

            
            var numeroPesquisado = '';
            var telaCarregadaFila = false;
        
            function desabilitaCombos(){
                for(i=0; i < document.frames.length; i++){
                    var  array = document.frames[i].document.getElementsByTagName("SELECT");
                    miolo(array);
                }
                miolo(document.getElementsByTagName("SELECT"));   
            }
        
        function miolo(array){
          for(j=0; j < array.length; j++){
             if(array[j].style.display=='none')
               array[j].style.display='';
             else
               array[j].style.display='none';
          }
     
        }   

        function abraLupa(titulo, pagina, altura, largura){
            escreveTitulo(titulo);
            document.getElementById("detalhe").style.visibility = 'visible';
            document.getElementById("detalhe").style.width = "790px";
            document.getElementById("detalhe").style.left = "5px";
            document.getElementById("bg").style.visibility = 'visible';
            document.getElementById('frameDetalhe').src = pagina;
            desabilitaCombos();
        }

        function escreveTitulo(titulo){
            var texto  = titulo==undefined?"Detalhe":titulo;
            if(!created){
                var header = "<head><link href=\"../../resources/css/frontoffice.css\" type=\"text/css\" rel=\"stylesheet\"/></head><body style=\"margin-left:0px; margin-right:0px; margin-top:0px;\">";
                var textoTitulo = "<table width=\"100%\" height=\"21\" cellpadding=\"0\" cellspacing=\"0\" background=\"../../resources/images/window_topbg.gif\" bgcolor=\"#0066cb\" class=\"tbl_titulo\"><tr><td width=\"91%\" height=\"21\"><div id=\"titleBar\"></div></td><td width=\"8%\" valign=\"top\" background=\"../../resources/images/window_topbtbg.gif\"><div align=\"right\"><img src=\"../../resources/images/window_fechar.gif\" style=\"cursor:hand;\" hspace=\"3\" onClick=\"top.frameApp.fechaLupa(1);\"></div></td></tr></table>";
                window.frames.barTitulo.document.write(header+textoTitulo);
                created = true;
            }
            window.frames.barTitulo.document.getElementById("titleBar").innerText = texto;
        }
                
        function fechaLupa(flag){
            if (telaCarregadaFila)
            {
                if (menuVoltar.style.display !='')
                {
                    if (flag == 1)
                    {
                        mostrar_div();
                        mostraDetalhe();
                        return false;
                    }
                    else
                    {
                        mostrar_div();
                    }
                }
            }
            if (flag == 1)
            {
                flag = false;
            }
            var x = document.getElementById("detalhe");
            try{
                var reload = document.frames("frameDetalhe").document.forms[0].inReloadTOTAL.value;
            }catch(e){
                var reload = "";
            }
            
            x.style.visibility = 'hidden';
            divFrame = document.getElementById('frameDetalhe');
            divFrame.src = '';
            <%request.getSession().removeAttribute("lupaLinhaForm");%>
            var b = document.getElementById("bg");
            b.style.visibility = 'hidden';
                        
            if(reload=="TRUE"){
                document.forms[0].target='frmDados';
                document.forms[0].action='/FrontOfficeWeb/cliente/TelaInicial/loadDados.do?valor='+document.frames("frameDetalhe").document.forms[0].nrLinhaReload.value;
                document.forms[0].method = "POST";
                document.forms[0].submit();
            }
            
            if(!flag){
              desabilitaCombos();
            }
        }
        
        var inCorporativo;
        
        function abreLupaCarteirizacao(Corp){        
            if(Corp==null){
                Corp = inCorporativo;
            }
        
            if(Corp=="1"){
                abraLupa('Detalhes Carteirização','loadCarteirizacao.do');
            }
            else{
                abraLupa('Detalhes Carteirização','loadCarteiraPF.do');
            }
        }

      function abreLupaSegmentacao(){
            abraLupa('Detalhes Segmentação','loadSegmentacao.do');
        }
        
        function abreLupaCliente(){
            abraLupa('Detalhes do Cliente','DetalheCliente/begin.do');
        }
        
        function abreLupaUsuario(){
            abraLupa('Detalhes','DetalheUsuario/begin.do');
        }
        
        function abreLupaLinha(){            
            abraLupa('Detalhes da Linha','DetalheLinha.do');
        }
        
        function abreLupaFatura(){
            abraLupa('Detalhes de Faturamento','DetalheFatura/begin.do');
        }
        
        /*
        *   Funçao para carregamento da tela apenas com frame dados e abas
        */
        function carregaTelaFila(){       
        
            ajusteFila = 46+40;
            //Se chamado pelo workflow
            
            if( document.forms["0"].pesquisa.selectedIndex == 12 ){
                
                abaOcultar(btAba, bt02, true);
                abaOcultar(btAba, bt03, false);
                abaOcultar(btAba, bt04, false);
                abaOcultar(btAba, bt05, false);
                abaOcultar(btAba, bt06, true);
                abaOcultar(btAba, bt07, false);
                abaOcultar(btAba, bt08, true);
                menuVoltar.style.display='';
                document.getElementById('fraAbasRelacionamento').height=(parseInt(document.getElementById('fraAbasRelacionamento').height)+ajusteFila);
                document.getElementById('fraAbas').height=(parseInt(document.getElementById('fraAbas').height)+ajusteFila);
                idAnimeTelaInicial.style.display="none";

            } else {
                
                areaDetalhe.style.display='';
                abaOcultar(btAba, bt02, true);
                abaOcultar(btAba, bt03, false);
                abaOcultar(btAba, bt04, false);
                abaOcultar(btAba, bt05, false);
                abaOcultar(btAba, bt06, true);
                abaOcultar(btAba, bt07, false);
                abaOcultar(btAba, bt08, true);
                

                abaOcultar(btAba, bt06, false);

                
                dvFrameURA.style.display='none';
                tbPesquisa.style.display='none';
                menuPrincipal.style.display='none';
                menuVoltar.style.display='';
                document.getElementById('fraAbasRelacionamento').height=(parseInt(document.getElementById('fraAbasRelacionamento').height)+ajusteFila);
                document.getElementById('fraAbas').height=(parseInt(document.getElementById('fraAbas').height)+ajusteFila);
                frameURA.document.forms["validarClienteForm"].idPos.selectedIndex = 1;
                frameURA.document.forms["validarClienteForm"].tipoRelacionamento.selectedIndex = <%=request.getParameter("relacionamento")%>;
                document.forms[0].method = "POST";
                frameURA.submitIdPos();
            }

        }
        
        function mostraDetalhe(){
        
            var idAtd = '<%=request.getParameter("idAtendimento")!=null?request.getParameter("idAtendimento"):ConstantesCRM.SVAZIO%>';
            var fila = '<%=request.getParameter("fila")!=null?request.getParameter("fila"):ConstantesCRM.SVAZIO%>';    
            top.frameApp.location="/FrontOfficeWeb/workflow/AtendimentoDetalhe/begin.do?idAtendimento=" + idAtd + "&fila=" + fila;
        }
        
        idAtd = '<%=request.getParameter("idAtendimento")!=null?request.getParameter("idAtendimento"):ConstantesCRM.SVAZIO%>';
        
        function carregaTela(){
            //verifica se sera necessário carregar uma linha de imediato
            if(document.getElementById('valorPesquisa').value==''){            
                document.getElementById('valorPesquisa').focus();
                dvFrameDados.style.display='none';
                areaDetalhe.style.display='none';

            }else{
                var tipoAtendimento = '<bean:write name="form" property="tipoAtendimento"/>';
                var nLinha = '<bean:write name="form" property="nrLinha"/>';
                pesquisa = '<bean:write name="form" property="pesquisa"/>';

                if(pesquisa!=''){
                    document.forms["0"].pesquisa.selectedIndex = 2;
                }

                // Se não tem linha nem conta usamos idPessoa
                if( tipoAtendimento=='prospect' && nLinha!=''){
                    document.forms["0"].pesquisa.selectedIndex = 12;
                    idAnimeTelaInicial.style.display="block";
                }
                
                if (nLinha==26){
                    tipoAtendimento = 'naoCliente';
                    pesquisaNome();
                    document.getElementById('valorPesquisa').disabled=true;

                }else{
                    validaPesquisa();
                }

                <%
                String framesMostrados = (request.getParameter("framesMostrados")!=null?request.getParameter("framesMostrados"):"");
                if(framesMostrados.equals("dados")){%>
                telaCarregadaFila = true;
                if(pesquisa=='celular'){
                    carregaTelaFila();

                }else{
                    dvFrameURA.style.display='none';
                    tbPesquisa.style.display='none';
                    menuPrincipal.style.display='none';
                }                
                if(tipoAtendimento=='naoCliente'){
                    document.forms["0"].pesquisa.selectedIndex = 12;
                    carregaTelaFila();
                }
                <%}%>
            }
        }
        
        
        function fechaPagina(){
            alert();
            var flagAux = true;
            var intVezes = 0;
            while((flagAux)){
                if(telaCarregadaFila){
                    flagAux = false;
                    intVezes++;
                    alert();
                }else{
                    alert('else');
                }
            }
        }
        
        function retornaBtPesquisar(){
            document.getElementById('btPesquisar').style.cursor = "hand";
            document.getElementById('btPesquisar').onclick = function(){ mostraIdPos();validaPesquisa();};
            document.getElementById('btPesquisar').src = '/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif';
            document.getElementById('valorPesquisa').disabled = false;
            document.getElementById('valorPesquisa').focus();
        }
        
        preloadOff     = new Image();
        preloadOff.src = "/FrontOfficeWeb/resources/images/bt_pesquisar_off.gif";
        
        function verificaDoc(){
            var selectedDoc = document.forms[0].pesquisa;
            document.getElementById('btPesquisar').onclick = "mostraIdPos(); validaPesquisa();";
            if  (selectedDoc.options[selectedDoc.selectedIndex].value == "naoCliente") {
                document.getElementById('valorPesquisa').disabled = true;
                document.getElementById('btPesquisar').src = "/FrontOfficeWeb/resources/images/bt_pesquisar_off.gif";
                document.getElementById('btPesquisar').onclick = "";
                document.getElementById('btPesquisar').style.cursor = "normal";

            }else{
                document.getElementById('valorPesquisa').disabled=false;            
                document.getElementById('btPesquisar').style.cursor = "hand";
                document.getElementById('btPesquisar').onclick = function(){ mostraIdPos();validaPesquisa();};
                document.getElementById('btPesquisar').src = '/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif';
            }
            pesquisaNome();
        }
        
        function controlCombos1(){
            inTI = true;
            for(i=0; i < document.frames.length; i++){
                var  array = document.frames[i].document.getElementsByTagName("SELECT");
                internalCombos1(array);
            }
            internalCombos1(document.getElementsByTagName("SELECT"));   
        }
        
        function internalCombos1(array){
            for(j=0; j < array.length; j++){
                if(array[j].style.display=='none')
                    array[j].style.display='';
                else
                    if (top.frameApp.idAnime.style.display!='none') array[j].style.display='none';
            }
        } 
        
        -->
        </script>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
        <!--
            //Se não for chamado pelo workflow
            if( document.forms["0"].pesquisa.selectedIndex != 12){
                oculta_div();
                //somente chamado quando carrega pela primeira vez a tela inicial
                idAnimeTelaInicial.style.display="none";
            }
            document.getElementById('divTodaPagina').style.display=''; 
            document.getElementById('pesquisa').disabled=false;
            document.getElementById('valorPesquisa').disabled=false;
            carregaTela();
            //abaOcultar(btAba,bt03);           
            <%if(ConstantesCRM.STRUE.equals(request.getParameter("updateCombos"))){%>
                idGrupoTroca = <%= request.getSession().getAttribute("idGrupoTroca") %>;
                idRelacionamentoTroca = <%= request.getSession().getAttribute("idRelacionamentoTroca") %>;
                idPosTroca = <%= request.getSession().getAttribute("idPosTroca") %>;

                for (i=0;i<document.getElementById('grupoSel').length;i++){
                    if (document.getElementById('grupoSel').options[i].value == idGrupoTroca){
                        document.getElementById('grupoSel').selectedIndex = i;
                        break;
                    }
                }

                for (i=0;i<frameURA.document.getElementById('tipoRelacionamento').length;i++){
                    if (frameURA.document.getElementById('tipoRelacionamento').options[i].value == idRelacionamentoTroca){
                        frameURA.document.getElementById('tipoRelacionamento').selectedIndex = i;
                        break;
                    }
                }

                for (i=0;i<frameURA.document.getElementById('idPos').length;i++){
                    if (frameURA.document.getElementById('idPos').options[i].value == idPosTroca){
                        frameURA.document.getElementById('idPos').selectedIndex = i;
                        break;
                    }
                }
                if (frameURA.document.getElementById('idPos').value='1') frameURA.submitIdPos();
            <%}%>
        -->
        </SCRIPT>
    </netui-template:section>
    <netui-template:section name="bodySection">

<div id="ttip" style="display:none;position:absolute;max-width:200px;"></div>
    <script>
        var moveToolTip = true;;
                            
        xBump=yBump=10;
        MSIE=document.all;
        NS6=document.getElementById&&!document.all;
        if(MSIE||NS6){
            ttipObj=document.all?document.all["ttip"]:document.getElementById?document.getElementById("ttip"):"";
        }
                
        function carregaToolTip(content) {
            ttipObj.innerHTML=content;
            ttipObj.style.display='';
        } 
        
    -->
    </script>
<!--
div colocado aqui para mostrar gif de aguarde
-->    
<div id='idAnimeTelaInicial'  style='display:none;z-index:2000 ;position:absolute; top:0px; left:0px; width:100%; height:100%;background-image:url(/FrontOfficeWeb/resources/images/window_bg.gif); '>
    <table border='1' cellpadding='0' cellspacing='0' height="100%" width="100%">
        <tr>
            <td align="center" valign="middle"><iframe frameborder="0" scrolling="no" height='63' width='81'  src='http:/FrontOfficeWeb/resources/images/carregar.html'></iframe>
            </td>
        </tr>
    </table>
</div >
<% if (request.getAttribute("dsMensagem")!=null){ %>

    <script>
        alert('Não existe nenhum grupo válido para abertura de atendimento!');
        top.location.href='/FrontOfficeWeb/autenticado.do?acao=1';
    </script>

<% request.removeAttribute("dsMensagem"); } %>

<div id="divTodaPagina"  style="display:none;">   
    <div id='menuPrincipal'>
    <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
    </div>
    <script>
        mostrar_div();
        //somente chamado quando carrega pela primeira vez a tela inicial
        idAnimeTelaInicial.style.display="block";

    </script>
        <!--Html que monta o detalhe como Quadro Flutuante, com resize automatico -->
        <div id="detalhe" style="z-index:999 ;visibility: hidden; position:absolute; top:15; left:15; width:750; height:550; border-style:solid; border-width:1px; border-color:#adadad; background-color:#e3ecf4;">            
            <iframe id="barTitulo" frameborder="0" scrolling="no" height="21" width="100%" style="top:0; left: 0;"></iframe>
            <table border="0" width="100%" height="100%" cellpadding="0" cellspacing="0">
                <tr>
                    <td>
                        <iframe name="frameDetalhe" id="frameDetalhe" src="nada.html" frameborder="0" width="100%" height="100%" onreadystatechange="ajustaTamanho(this)" scrolling="no"></iframe>
                    </td>
                </tr>
            </table>
        </div>
        <!-- DIV que monta a desativação do fundo -->
        <div id="bg" style="z-index:998 ;visibility: hidden; position:absolute; top:0; left:0; width:100%; height:100%; border-style:solid; background-color:#000000; border-width:1px; border-color:#adadad;filter:alpha(opacity=60);"></div>
        <!--  -->
        <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="3"></div>
        <table width="768" cellpadding="0" cellspacing="0" class="text_simple_black">
            <tr>
                <td nowrap>
                    <table id="tbPesquisa" width="790" height="29" align="center" cellpadding="0" cellspacing="0" background="../../resources/images/bfixa_bg.gif" class="tblEstatica_txtOver">
                        <tr>
                            <td height="7" colspan="9"><img src="<%=request.getContextPath()%>/resources/images/transp.gif" width="1" height="7"></td>
                        </tr>
                        <tr>
                            <td width="82" class="bfixa_texto_black"><div align="center">Pesquisa por</div></td>
                            <form action="loadDados.do" name="tiForm" target="frmDados" onSubmit="return false;" method="POST">
                            <!--wl:cache-->
                                <td width="134">
                                    <select name="pesquisa" id="pesquisa" disabled="true" style="width:127px;z-index:1;" onChange="document.getElementById('valorPesquisa').value='';verificaDoc();">
                                        <option label="celular" value="celular">N&uacute;mero da linha</option>
                                        <option label="nome" value="nome">Nome do Cliente</option>
                                        <option label="conta" value="conta">N&uacute;mero da Conta</option>
                                        <option label="CPF" value="CPF">CPF</option>
                                        <option label="CNPJ" value="CNPJ">CNPJ</option>
                                        <option label="RNE" value="RNE">RNE</option>
                                        <option label="Passaporte" value="Pas">Passaporte</option>
                                        <option label="IE" value="IE">Inscri&ccedil;&atilde;o Estadual</option>
                                        <option label="razao" value="razao">Razão Social</option>
                                        <option label="CN" value="CN">Certid&atilde;o de Nascimento</option>
                                        <option label="RG" value="RG">RG</option>
                                        <option label="IM" value="IM">Inscri&ccedil;&atilde;o Municipal</option>
                                        <!-- -->
                                        <option label="naoCliente" value="naoCliente">Não Identificado</option>
                                    </select>
                                </td>
                                <td width="170" valign="middle"><html:text name="formpesquisa" property="nome" styleId="valorPesquisa" disabled="true" style="width:150px; height:15px;" onkeyup="document.getElementById('valorPesquisa').value=trim(document.getElementById('valorPesquisa').value);if(this.form.pesquisa.selectedIndex!=5){escolheMascara(this);}else{document.getElementById('valorPesquisa').maxLength=25;};preValidaKey();" value="" onkeydown="document.getElementById('valorPesquisa').value=trim(document.getElementById('valorPesquisa').value);if(this.form.pesquisa.selectedIndex!=5){escolheMascara(this);}else{document.getElementById('valorPesquisa').maxLength=25;};"/></td>
                                <html:hidden name="form" property="nrLinha" styleId="nrLinhaOculto"/>
                                <script>
                                    document.getElementById('valorPesquisa').value=document.getElementById('nrLinhaOculto').value;
                                </script>
                                <!--<td width="170" valign="middle"><input name="valor" id="valorPesquisa" disabled="true" type="text" class="textfield" style="width:150px; height:15px;" onKeyUp="this.form.valor.value=trim(this.form.valor.value);if(this.form.pesquisa.selectedIndex!=5){escolheMascara(this);}else{document.getElementById('valorPesquisa').maxLength=25;};preValidaKey();" value="<bean:write name="form" property="nrLinha"/>" onkeydown="this.form.valor.value=trim(this.form.valor.value);if(this.form.pesquisa.selectedIndex!=5){escolheMascara(this);}else{document.getElementById('valorPesquisa').maxLength=25;};"></td>-->
                                <td width="76" valign="top"><div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" width="1" height="4"></div>
                                
                                <img id="btPesquisar" src="../../resources/images/bt_pesquisar_nrml.gif" style="border:none;cursor:hand;" onClick="mostraIdPos(); validaPesquisa(); return false;"/>
                                
                                </td>
                                <td width="66" valign="top"><div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" width="1" height="4"></div><img style="cursor:hand;border:none;" src="../../resources/images/bt_limpar_nrml.gif" onClick="document.getElementById('valorPesquisa').value=''; this.form.pesquisa.selectedIndex=0; retornaBtPesquisar(); return false"/></td>
                            <!--/wl:cache-->
                            </form>
                            
                            <td width="7">&nbsp;</td>
                            <td width="132" valign="top" class="bfixa_texto_blue">
                            <!--    <br>&nbsp;&nbsp;Corporativo -->       
                                <html:select name="form" property="grupoSel" title="grupoSel" style="width=120px" onchange="guardaIds();trocarGrupo(this.value);focaTipCampos(this.options[this.selectedIndex].text,this, 110, 540, 15);" onfocus="focaTipCampos(this.options[this.selectedIndex].text,this, 110, 540, 15);" onmouseover="focaTipCampos(this.options[this.selectedIndex].text,this, 110, 540, 15);" onblur="HideTip();" onmouseout="HideTip();">
                                    <html:options collection="grupos" property="idGrupo" labelProperty="dsGrupo"/> 
                                </html:select>
                                
                            </td>
                            <td width="12" background="../../resources/images/bfixa_delimit.gif"></td>
                            <td width="123" valign="top"><img src="<%=request.getContextPath()%>/resources/images/bfixa_login.gif" width="23" height="7" hspace="6"><span class="bfixa_texto_blue"><br><bean:write name="form" property="nmLogin"/></span></td>
                        </tr>
                        <tr>
                            <td height="5" colspan="9" class="bfixa_texto_black"><img src="<%=request.getContextPath()%>/resources/images/transp.gif" width="1" height="5"></td>
                        </tr>
                    </table>
                    <div id="dvFrameURA"><iframe id="frameURA" name="frameURA" src="validarCliente.jsp" height="46" width="790" frameborder="0" scrolling="no"></iframe></div>
                    <div><img id="imgRef" src="../../resources/images/transp.gif" border="0" width="1" height="1"></div>
                    <div id="dvFrameDados" style="display:none"><iframe src="dados.jsp" height="227" width="790" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" name="frmDados" id="frmDados"></iframe></div>
                    <div><img id="imgRefDown" src="../../resources/images/transp.gif" width="1" height="1"></div>
                </td>
            </tr>
        </table>
            <!--tr>
                <td  id="teste">
                </td>
            </tr>
        </table-->
        <div id="areaDetalhe" style="display:none">
            <table width="790" cellspacing="0" cellpadding="0">
                <tr>
                    <td valign="top">
                        <table height="16" border="0" width="790" cellspacing="0" cellpadding="0">
                            <tr>
                                <td background="../../resources/images/aba_bg_line.gif" valign="top" height="16">
                                    <vivo:abaGrupo id="btAba" width="766" height="16" styleClass="abatexto">
                                        

                                        <vivo:abaItem id="bt01" onclick="abaSelected(btAba, bt01);CarregaAba(this.id);" value="Relacionamento" select="S"/>

                                        

                                        <vivo:abaItem id="bt02" onclick="CarregaAba(this.id);" value="Atendimento"/>

                                        

                                        <vivo:abaItem id="bt03" onclick="CarregaAba(this.id);" value="Serviços"/>

                                        

                                        <vivo:abaItem id="bt08" onclick="abaSelected(btAba, bt08);CarregaAba(this.id);resizeRetencao();" value="Campanha"/>



                                        <vivo:abaItem id="bt04" onclick="abaSelected(btAba, bt04);CarregaAba(this.id);" value="Hist.Campanha"/>



                                        <vivo:abaItem id="bt05" onclick="CarregaAba(this.id);" value="Senha"/>



                                        <vivo:abaItem id="bt06" onclick="abaSelected(btAba, bt06);CarregaAba(this.id);resizeRetencao();" value="Retenção"/>

                                        

                                        <vivo:abaItem id="bt07" onclick="abaSelected(btAba, bt07);CarregaAba(this.id);" value="C.Devolvida"/>

                                        

                                        <vivo:abaItem id="bt09" onclick="CarregaAba(this.id);" value="Chip"/>

                                        

                                            <vivo:abaItem id="bt10" onclick="CarregaAba(this.id);" value="IMEI"/>

                                    </vivo:abaGrupo>
                                </td>
                                <td background="../../resources/images/aba_bg_line.gif" height="16" valign="top">
                                    <img name="lupaaba" src="../../resources/images/bt_lupa_aba.gif" onclick="resizeFrameDetalhe();" style="cursor:hand">
                                </td>
                            </tr>
                        </table>
                        <table width="790" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td width="1" rowspan="2" bgcolor="#adadad"></td>
                                <td width="2" rowspan="2"><img src="<%=request.getContextPath()%>/resources/images/transp.gif" width="2" height="1"></td>
                                <td height="4" valign="top"><img src="<%=request.getContextPath()%>/resources/images/transp.gif" width="1" height="4"></td>
                            </tr>
                            <tr>
                                <td valign="top" height="159">
                                    <div style="display:none" id="dvFraAbas">
                                        <iframe id="fraAbas" name="fraAbas" src="" width="785" height="155" frameborder="0"></iframe>
                                    </div>
                                    <div style="display:none" id="dvFraAbasRelacionamento">
                                        <iframe id="fraAbasRelacionamento" src="Relacionamento.jsp" width="785" height="155" frameborder="0"></iframe>
                                    </div>
                                </td>
                            </tr>
                            <tr bgcolor="#adadad">
                                <td height="1" colspan="3"></td>
                            </tr>
                        </table>
                 
                    </td>
                </tr>
            </table>  
                <div id='menuVoltar' style="display:none;">
                    <img src="<%=request.getContextPath()%>/resources/images/bt_voltar_nrml.gif" style="border:none;cursor:hand;" onClick="mostraDetalhe();"/>
                </div> 
        </div> 
        
        <!--Form e Quadro Flutuante-->
        <form id="frmSelecao" name="frmSelecao" method="post" />
        <vivo:quadroFlutuante id="dvAtendimento" idIframe="ifrmAtendimento" width="800" height="570" spacesTop="0" spacesLeft="0" display="none" url="<%=request.getContextPath()%>" label="Atendimento"/>        
        <vivo:quadroFlutuante id="dvAtendCorresp" idIframe="ifrmAtendCorresp" width="770" height="550" spacesTop="15" spacesLeft="15" display="none" url="<%=request.getContextPath()%>" label="Tratar Correspondencia Devolvida" onclick="top.frameApp.fraAbas.fechar(); mostrar_div();"/>
        <vivo:quadroFlutuante id="dvTransferir" idIframe="ifrmTransferir" width="300" height="200" spacesTop="200" spacesLeft="250" display="none" url="<%=request.getContextPath()%>" label="Transferir"/>
        <vivo:quadroFlutuante id="dvPesqNome" idIframe="ifrmPesqNome" width="580" height="80" spacesTop="65" spacesLeft="75" display="none" url="<%=request.getContextPath()%>" onclick="desabilitaCombos()" label="Pesquisa de Clientes"/>
        <vivo:quadroFlutuante id="dvPesqNomeResultados" idIframe="ifrmPesqNomeResultados" width="800" height="550" spacesTop="15" spacesLeft="0" display="none" url="<%=request.getContextPath()%>" onclick="desabilitaCombos()" label="Pesquisa de Clientes" scroll="no"/>
        <vivo:quadroFlutuante id="dvPesqEndereco" idIframe="ifrmPesqEndereco" width="790" height="550" spacesTop="15" spacesLeft="3" display="none" url="<%=request.getContextPath()%>" label="Pesquisa de Endereço"/>        
</div>

    <iframe name="iframeRefreshCombos" id="iframeRefreshCombos" frameborder="0" height='1' width='1' src=''></iframe>
    </netui-template:section>
</netui-template:template>