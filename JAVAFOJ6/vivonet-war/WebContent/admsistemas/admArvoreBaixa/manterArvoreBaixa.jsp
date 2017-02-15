<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<bean:define id="FormArvoreBaixa"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreBaixa"/>
<bean:define id="AdmIndicadoresAnatelExistentesVO"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreBaixa.admIndicadoresAnatelExistentesVO"/>
<bean:define id="AdmIndicadoresAnatelAssociadosVO"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreBaixa.admIndicadoresAnatelAssociadosVO"/>
<bean:define id="AdmMensagemBaixaVO"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreBaixa.admMensagemBaixaVO"/>
<bean:define id="AdmComunicacaoBaixaVO"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreBaixa.admComunicacaoBaixaVO"/>

<netui-template:template templatePage="./../../resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Manutenção da Árvore de Baixa"/>
    <netui-template:setAttribute name="modulo" value="Administração de Sistemas"/>

    <netui-template:section name="headerSection">
        <link href="<%=request.getContextPath()%>/resources/css/xtree.css" type="text/css" rel="stylesheet"/>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/xtree.js"></script>
        <script language="javascript">
            function detalhar(){
                if(document.forms[0].idBaixa.value == ''){
                    alert('Selecione a raiz para inserir um ítem.');
                    return;
                    limpaCampos();
                }else{
                    document.forms[0].action = 'montaDetalharItem.do';
                    document.forms[0].target = '';
                    mostrar_div();
                    document.forms[0].submit();
                }
            }   

            function abreJanelaDetalhar(){
                spTitulo.innerHTML = "Consultar Ítem da arvore de Baixa";
                mostraJanelaDetalhe()
                divFrame = document.getElementById('frameDetalhe');
                divFrame.src = 'detalheBaixa.do';
            }
        
            function capturaParametrosContato(idBaixa, idBaixaPai, idNomeBaixa, nmBaixa, dsPath, inDisponibilidade, inFolha)
            {
                document.forms[0].idBaixa.value = idBaixa;
                document.forms[0].idBaixaPai.value = idBaixaPai;
                document.forms[0].idNomeBaixa.value = idNomeBaixa;
                document.forms[0].nmBaixa.value = nmBaixa;
                document.forms[0].dsPath.value = dsPath;
                document.forms[0].inDisponibilidade.value = inDisponibilidade;
                document.forms[0].inFolha.value = inFolha;
                
                if(inFolha == '1')
                {
                        if(inDisponibilidade == '1')
                        {
                            //document.getElementById('btinclui').style.display       ='none';
                            setDisplayNone("btinclui");
                            //document.getElementById('btRemover').style.display      ='block';
                            setDisplayBlock("btRemover");
                            //document.getElementById('btDes').style.display          ='block';
                            setDisplayBlock("btDes");
                            //document.getElementById('btHab').style.display          ='none';                    
                            setDisplayNone("btHab");
                            //document.getElementById("edtDtBas").style.display       ='block';
                            setDisplayBlock("edtDtBas");
    
                        }else
                        {
                        
                            //document.getElementById('btinclui').style.display       ='none';
                            setDisplayNone("btinclui");
                            //document.getElementById('btRemover').style.display      ='block';
                            setDisplayBlock("btRemover");
                            //document.getElementById('btDes').style.display          ='none';
                            setDisplayNone("btDes");
                            //document.getElementById('btHab').style.display          ='block';                    
                            setDisplayBlock("btHab");
                            //document.getElementById("edtDtBas").style.display       ='block';
                            setDisplayBlock("edtDtBas");
    
                        }
                }else
                {
                    if(inDisponibilidade == '1')
                    {
                        //document.getElementById('btinclui').style.display       ='block';
                        setDisplayBlock("btinclui");
                        //document.getElementById('btRemover').style.display      ='block';
                        setDisplayBlock("btRemover");
                        //document.getElementById('btDes').style.display          ='block';
                        setDisplayBlock("btDes");
                        //document.getElementById('btHab').style.display          ='none';                
                        setDisplayNone("btHab");
                        //document.getElementById("edtDtBas").style.display       ='none';
                        setDisplayNone("edtDtBas");
                    
                    }else
                    {
                        //document.getElementById('btinclui').style.display       ='block';
                        setDisplayBlock("btinclui");
                        //document.getElementById('btRemover').style.display      ='block';
                        setDisplayBlock("btRemover");
                        //document.getElementById('btDes').style.display          ='none';
                        setDisplayNone("btDes");
                        //document.getElementById('btHab').style.display          ='block';
                        setDisplayBlock("btHab");
                        //document.getElementById("edtDtBas").style.display       ='none';
                        setDisplayNone("edtDtBas");
                    
                    }
                
                }

            }


            function expandirNo(idBaixa, idBaixaPai, idNomeBaixa, nmBaixa, dsPath, inDisponibilidade, inFolha)
            {
                document.forms[0].idBaixa.value             = idBaixa;
                document.forms[0].idBaixaPai.value          = idBaixaPai;
                document.forms[0].idNomeBaixa.value         = idNomeBaixa;
                document.forms[0].nmBaixa.value             = nmBaixa;
                document.forms[0].dsPath.value              = dsPath;
                document.forms[0].inDisponibilidade.value   = inDisponibilidade;
                document.forms[0].inFolha.value             = inFolha;
                
                if(inDisponibilidade == '1')
                {
                    //document.getElementById('btinclui').style.display       ='block';
                    setDisplayBlock("btinclui");
                    //document.getElementById('btRemover').style.display      ='block';
                    setDisplayBlock("btRemover");
                    //document.getElementById('btDes').style.display          ='block';
                    setDisplayBlock("btDes");
                    //document.getElementById('btHab').style.display          ='none';                
                    setDisplayNone("btHab");
                    //document.getElementById("edtDtBas").style.display       ='none';
                    setDisplayNone("edtDtBas");
                
                }else
                {
                    //document.getElementById('btinclui').style.display       ='block';
                    setDisplayBlock("btinclui");
                    //document.getElementById('btRemover').style.display      ='block';
                    setDisplayBlock("btRemover");
                    //document.getElementById('btDes').style.display          ='none';
                    setDisplayNone("btDes");
                    //document.getElementById('btHab').style.display          ='block';
                    setDisplayBlock("btHab");
                    //document.getElementById("edtDtBas").style.display       ='none';
                    setDisplayNone("edtDtBas");
                }
                
                if (!tree.getSelected().isAddEnabled()) 
                {
                    return;
                }

                document.forms[0].method = "POST";
                document.forms[0].action = "montaArvoreParte.do";
                document.forms[0].target = "iframeArvoreBaixa";
                mostrar_div();
                document.forms[0].submit();

            }


            function capturaParametrosRaiz(idBaixa, idBaixaPai, idNomeBaixa, nmBaixa, dsPath, inDisponibilidade, inFolha){
                //document.getElementById('btinclui').style.display   ='block';
                setDisplayBlock("btinclui");
                //document.getElementById('btRemover').style.display  ='none';
                setDisplayNone("btRemover");
                //document.getElementById('btHab').style.display      ='none';
                setDisplayNone("btHab");
                //document.getElementById('btDes').style.display      ='none';
                setDisplayNone("btDes");
                //document.getElementById("edtDtBas").style.display='none';
                setDisplayNone("edtDtBas");

                document.forms[0].idBaixa.value = idBaixa;
                document.forms[0].idBaixaPai.value = idBaixaPai;
                document.forms[0].idNomeBaixa.value = idNomeBaixa;
                document.forms[0].nmBaixa.value = nmBaixa;
                document.forms[0].dsPath.value = dsPath;
                document.forms[0].inDisponibilidade.value = inDisponibilidade;
                document.forms[0].inFolha.value = inFolha;
            }
            
            function limpaCampos()
            {
                document.forms[0].idBaixa.value = '';
                document.forms[0].idBaixaPai.value = '';
                document.forms[0].idNomeBaixa.value = '';
                document.forms[0].nmBaixa.value = '';
                document.forms[0].dsPath.value = '';
            }   
            
            function inserir()
            {
                if(document.forms[0].idBaixa.value == ''){
                    alert('Selecione a raiz para inserir um ítem.');
                    return;
                    limpaCampos();
                }else{
                    document.forms[0].action = 'montaInsereItem.do';
                    document.forms[0].target = '';
                    mostrar_div();
                    document.forms[0].submit();
                }
                
            }
            
            function editar(){
                if(document.forms[0].idBaixa.value == ''){
                    alert('Selecione um ítem para editar.');
                    return;
                    limpaCampos();
                }else{
                    document.forms[0].action = 'montaEditarItem.do';
                    document.forms[0].target = '';
                    mostrar_div();
                    document.forms[0].submit();
                }
            }   
            
            function remover(){
                if(document.forms[0].idBaixa.value==''){
                    alert('Selecione um ítem para exclusão.');
                    return;
                }
                if (window.confirm("Confirma remoção do item?")) {
                    document.forms[0].action = 'removeItem.do';
                    document.forms[0].target = '';
                    mostrar_div();
                    document.forms[0].submit();
                    limpaCampos(); 
                } 
            }
                          
            var up = true;
            function resizeFrameDetalhe(){
                var dynfrm = document.getElementById("ifrmAbas");
                var dyndiv = document.getElementById("areaWorkflow");
                var objRef = document.getElementById("imgRefUp");
                var objDow = document.getElementById("imgRefDw");
                dyndiv.style.backgroundColor = '#e3ecf4';
                objRef.style.position = 'absolute';
                objDow.style.position = 'absolute';
                var novaTop   = objRef.offsetTop;
                var oldposdiv = objDow.offsetTop;
                objRef.style.position = '';
                objDow.style.position = '';
                if(up){
                    dyndiv.style.position = 'absolute';
                    dyndiv.style.top = novaTop;
                    dyndiv.style.left= 0;
                    oldtamfrm = dynfrm.height;
                    oldlardiv = dyndiv.style.width;
                    dynfrm.height = parseInt(oldtamfrm) + parseInt(oldposdiv) - parseInt(novaTop);
                    dyndiv.style.height = dynfrm.height;
                    up = false;
                }else{
                    dyndiv.style.backgroundColor = '';
                    dyndiv.style.left= 0;
                    dynfrm.height = oldtamfrm;
                    dyndiv.style.height = oldtamfrm;
                    dyndiv.style.position = '';
                    dyndiv.style.width = oldlardiv;
                    up = true;
                }
            }
            
    
            function mostraJanelaDetalhe(){
                var x = document.getElementById("detalhe");
                x.style.display = 'block';
                var b = document.getElementById("bg");
                b.style.visibility = 'visible';
            }
            
            
            function abreJanelaEditar(){
                spTitulo.innerHTML = "Alterar Ítem da arvore de Baixa";
                mostraJanelaDetalhe()
                divFrame = document.getElementById('frameDetalhe');
                divFrame.src = 'editarItemBaixa.do';
            }
            
             function abreJanelaInserir(){
                spTitulo.innerHTML = "Inserir Ítem da arvore de Baixa";
                mostraJanelaDetalhe()
                divFrame = document.getElementById('frameDetalhe');
                divFrame.src = 'criarItemBaixa.do';
            }
    
            function fechaJanela(){
                var x = document.getElementById("detalhe");
                x.style.display = 'none';
                divFrame = document.getElementById('frameDetalhe');
                divFrame.src = 'nada.html';
                var b = document.getElementById("bg");
                b.style.visibility = 'hidden';
            }
    
            function ajustaTamanho(obj){
                if(obj.readyState=="complete"){
                    div = document.getElementById('detalhe');
                    altura  = window.frames.frameDetalhe.document.body.scrollHeight + 16;
                    largura = 451;
                    if(altura > 589) altura = 589;
                    if(largura > 789) largura = 789;
                    div.style.width = largura;
                    div.style.height = altura;
                    div.style.top = (600 - altura) / 2;
                    div.style.left = (800 - largura) / 2;
                }
            }
        
            function enviaHabilitaDesabilita()
            {
                var msg = '';
            
                if(document.forms[0].idBaixa.value=='')
                {
                    alert('Selecione um Ítem para alterar o Status.');
                    return;
        
                }else
                {
    
                    if(document.forms[0].inDisponibilidade.value == '1')
                    {
                        msg = 'Confirma Indisponibilização do Ítem?';
        
                    }else if(document.forms[0].inDisponibilidade.value == '0')
                    {
                        msg = 'Confirma Disponibilização do Ítem?';
                    }
                    
                    if(confirm(msg))
                    {
                        document.forms[0].target = '';
                        document.forms[0].action = 'habilitaDesabilitaArvore.do';
                        mostrar_div();
                        document.forms[0].submit();
                    }
    
                }
    
            }
        
         function initPagina() { }
        
        function chamaConfigArvoreBaixa(){
            if(document.forms[0].idBaixa.value==''){
                alert('Selecione um Item para ser configurado.');
                return;
            }else{
                document.forms[0].action = '/FrontOfficeWeb/admsistemas/admArvoreBaixa/dadosBasicos/begin.do'; 
                document.forms[0].target = '';
                mostrar_div();
                document.forms[0].submit();
            }
        }
        
        //Pablo 17-03-05
        function setDisplayBlock(objectName) {
            setDisplay(objectName, "block");
        }
        function setDisplayNone(objectName) {
            setDisplay(objectName, "none");
        }      
        
        function setDisplay(objectName, _display) {
            if ( document.getElementById(objectName) ) {
                document.getElementById(objectName).style.display = _display;
            }
        }  
        //Pablo
        </script>

    </netui-template:section>



    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="adm_mab_verpagina">

        <!-- Menu de Administração de Sistemas -->
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
                
        <!--APLICACAO--> 

        <script>
            mostrar_div();
        </script>
            
            <vivo:sessao id="qdMain" height="470" width="790" label="Manuten&ccedil;&atilde;o da &Aacute;rvore de Baixa" scroll="no">
                <body>
                <form action="removeItem" id="formArvoreBaixa" name="formArvoreBaixa" method="POST">
                    <html:hidden name="FormArvoreBaixa" property="idBaixa"/>
                    <html:hidden name="FormArvoreBaixa" property="idBaixaPai"/>
                    <html:hidden name="FormArvoreBaixa" property="idNomeBaixa"/>
                    <html:hidden name="FormArvoreBaixa" property="nmBaixa"/>
                    <html:hidden name="FormArvoreBaixa" property="dsPath"/>
                    <html:hidden name="FormArvoreBaixa" property="inDisponibilidade"/>
                    <html:hidden name="FormArvoreBaixa" property="inFolha"/>
                    <html:hidden name="FormArvoreBaixa" property="msgError"/>

                <table width="780" height="450" border="0" cellspacing="0" cellpadding="1">                
                    <tr>
                        <td width="560">
                            <div class="tbl_bgGray" style="width: 450px; top: 0px; left: 0px; height: 435px; padding: 5px; overflow: auto;">
                                <script>
                                    <%=(String)request.getAttribute("arvore")%>
                                    <%request.setAttribute("arvore",(String)request.getAttribute("arvore"));%>
                                </script>
                            </div>
                            <img vspace="8" onClick="window.location.href='/FrontOfficeWeb/index.jsp'; return false;" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_voltar_over.gif'" style="border:none;"/>
                        </td>
                        <td valign="top" width="220">
                            <vivo:quadro id="operArvBaixa" width="322" height="60" label="Manuten&ccedil;&atilde;o de Ítens">
                            <table width="100%" height="100%" border="0" cellspacing="1" cellpadding="0" align="center">
                                <tr>
                                    <td align="center">
                                    <acesso:controlHiddenItem nomeIdentificador="adm_mab_adicionarno">
                                        <img id="btinclui" onclick="inserir();" src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_incluir_over.gif'" border="0" style="cursor:hand"/>
                                    </acesso:controlHiddenItem>
                                    </td>
                                    <td align="center">
                                    <acesso:controlHiddenItem nomeIdentificador="adm_mab_alterarno">
                                        <img onClick="editar(); return false" src="/FrontOfficeWeb/resources/images/bt_alterar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_alterar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_alterar_over.gif'" style="border:none;" style="cursor:hand"/>
                                    </acesso:controlHiddenItem>
                                    </td>
                                    <td align="center">
                                    <acesso:controlHiddenItem nomeIdentificador="adm_mab_removerno">
                                        <img id="btRemover" onclick="remover();" src="/FrontOfficeWeb/resources/images/bt_excluir_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_excluir_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_excluir_over.gif'" style="border:none;cursor:hand"/>
                                    </acesso:controlHiddenItem>
                                    </td>
                                    <td align="center">
                                    <acesso:controlHiddenItem nomeIdentificador="adm_mab_consultano">
                                        <img onclick="detalhar();" src="/FrontOfficeWeb/resources/images/bt_consultar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_consultar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_consultar_over.gif'" border="0" style="cursor:hand"/>
                                    </acesso:controlHiddenItem>
                                    </td>
                                    <td>
                                    <acesso:controlHiddenItem nomeIdentificador="adm_mab_habilita">
                                        <img id="btDes" onClick="enviaHabilitaDesabilita(); return false" src="/FrontOfficeWeb/resources/images/bt_desabilitar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_desabilitar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_desabilitar_over.gif'" style="border:none;cursor:hand;display:none;"/>
                                    </acesso:controlHiddenItem>
                                    </td>
                                    <td>
                                    <acesso:controlHiddenItem nomeIdentificador="adm_mab_desabilita">
                                        <img id="btHab" onClick="enviaHabilitaDesabilita(); return false" src="/FrontOfficeWeb/resources/images/bt_habilitar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_habilitar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_habilitar_over.gif'" style="border:none;cursor:hand;"/>
                                    </acesso:controlHiddenItem>
                                    </td>
                                </tr>
                                <tr align="center">
                                    <td align="center" colspan="6">
                                    <!--nome da unidade alterada, voltar original -->
                                    <!--acesso:controlHiddenItem nomeIdentificador="adm_mab_dados_basicos" -->
                                    <acesso:controlHiddenItem nomeIdentificador="adm_mab_dados_basicos_pro">
                                        <img id="edtDtBas" onclick="chamaConfigArvoreBaixa(); return false" src="/FrontOfficeWeb/resources/images/bt_eddadbasic_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_eddadbasic_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_eddadbasic_over.gif'" style="border:none;cursor:hand;"/>
                                    </acesso:controlHiddenItem>
                                    </td>                                
                                </tr>
                            </table>
                            </vivo:quadro>
                        </td>
                    </tr>
                </table>
                
                </form>
                
                    <iframe scrolling="yes"  name="iframeArvoreBaixa" height="110px" width="110px"></iframe>
                
            </vivo:sessao>
            
            <div  id="detalhe" style="z-index:999 ;display: none; position:absolute; top:15; left:15; width:750; height:550; border-style:solid; border-width:1px; border-color:#adadad; background-color:#e3ecf4;">
                <table width="450" height="21" cellpadding="0" cellspacing="0" background="/FrontOfficeWeb/resources/images/window_topbg.gif" bgcolor="#0066cb" class="tbl_titulo">
                    <tr>
                        <td id="titleBar" style="cursor:move" width="450" height="21"><span id="spTitulo"></span></td>
                        <td width="64" valign="top" background="/FrontOfficeWeb/resources/images/window_topbtbg.gif">
                            <div align="right">
                                <img src="/FrontOfficeWeb/resources/images/window_fechar.gif" hspace="3" onClick="fechaJanela();" style="cursor:hand;" alt="Fechar">
                            </div>
                        </td>
                    </tr>
                </table>
                <table width="100%" cellpadding="0" cellspacing="5">
                    <tr>
                        <td valign="top">
                            <iframe name="frameDetalhe" id="frameDetalhe" src="/FrontOfficeWeb/admsistemas/nada.html" frameborder="0" width="100%" height="100%" onreadystatechange="ajustaTamanho(this)" scrolling="no"></iframe>
                        </td>
                    </tr>
                </table>
            </div>
            <div id="bg" style="z-index:998 ;visibility: hidden; position:absolute; top:0; left:0; width:100%; height:100%; border-style:solid; background-image:url(/FrontOfficeWeb/resources/images/window_bg.gif); border-width:1px; border-color:#adadad;"></div>

        <SCRIPT FOR=window EVENT=onload  LANGUAGE="JScript">
            <%if(request.getParameter("flag") != null && request.getParameter("flag").equals("insere")){%>
                    abreJanelaInserir();
                    mostrar_div();
            <%}else if(request.getParameter("flag") != null && request.getParameter("flag").equals("edita")){%>
                    abreJanelaEditar();
                    mostrar_div();
            <%}else if(request.getParameter("flag") != null && request.getParameter("flag").equals("detalha")){%>
                    abreJanelaDetalhar();
                    mostrar_div();
            <%}else{%>
                    oculta_div();
            <%}%>

            <!--   
                if('<bean:write name="FormArvoreBaixa" property="msgError" />' != "")
                {
                    alert('<bean:write name="FormArvoreBaixa" property="msgError" />');
                }              
                
            -->

        </SCRIPT>
        
    </body>

    
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>