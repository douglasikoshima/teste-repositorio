<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<bean:define id="FormArvoreContato"             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreContato"/>
<bean:define id="AdmNomeContatoVO"              name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreContato.admNomeContatoVO"/>
<bean:define id="AdmIndicadorAnatelExistenteVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreContato.admIndicadoresAnatelExistentesVO"/>
<bean:define id="AdmIndicadorAnatelAssociadoVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreContato.admIndicadoresAnatelAssociadosVO"/>
<bean:define id="aMensagem"                     name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreContato.listaMensagem"/>
<bean:define id="aFechamento"                   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreContato.listaFechamento"/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Administra��o de �rvore"/>
    <netui-template:setAttribute name="modulo" value="Administra��o de Sistemas"/>
    <netui-template:section name="headerSection">
        <link href="<%=request.getContextPath()%>/resources/css/xtree.css" type="text/css" rel="stylesheet"/>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/xtree.js"></script>
        <script language="Javascript">
            var tmpArvore = null;
            
            function expandirNo(idContato, nmContato, idContatoPai, inDisponibilidade, idNomeContato, nrNivel, inFolha, dsPath){
                if(document.getElementById('btInc'))
                    document.getElementById('btInc').style.display='block';
                    
                if(document.getElementById('btAlt'))
                    document.getElementById('btAlt').style.display='block';
                
                if(document.getElementById('btExc'))
                    document.getElementById('btExc').style.display='block';
                
                if(document.getElementById('edtDtBas'))
                    document.getElementById('edtDtBas').style.display='none';
                
                if(document.getElementById('edtLink'))
                    document.getElementById('edtLink').style.display='block';
                
                if(document.getElementById('edtWork'))
                    document.getElementById('edtWork').style.display='none';
                    
                if(inDisponibilidade == '1'){
                    if(document.getElementById('btHab'))
                        document.getElementById('btHab').style.display='none';
                    
                    if(document.getElementById('btDes'))
                        document.getElementById('btDes').style.display='block';
                
                }else{
                    if(document.getElementById('btHab'))
                        document.getElementById('btHab').style.display='block';
                
                    if(document.getElementById('btDes'))
                        document.getElementById('btDes').style.display='none';
                }
                
                if(document.getElementById('origemParam'))
                    document.getElementById('origemParam').style.display='none';
                
                if(document.getElementById('destinoParam'))
                    document.getElementById('destinoParam').style.display='block';            
                
                document.forms[0].idContato.value = idContato;
                document.forms[0].nmContato.value = nmContato;
                document.forms[0].idContatoPai.value = idContatoPai;
                document.forms[0].inDisponibilidade.value = inDisponibilidade;            
                document.forms[0].idNomeContato.value = idNomeContato;
                document.forms[0].nrNivel.value = nrNivel;
                document.forms[0].inFolha.value = inFolha;
                document.forms[0].dsPath.value = dsPath;

                if (!tree.getSelected().isAddEnabled()){
                    return;
                }

                document.forms[0].method = "POST";
                document.forms[0].action = "montaArvoreParte.do";
                document.forms[0].target = "iframeArvoreContato";
                mostrar_div();
                document.forms[0].submit();
            }

            function capturaParametrosContato (idContato, nmContato, idContatoPai, inDisponibilidade, idNomeContato, nrNivel, inFolha, dsPath){

                if(document.getElementById('btInc'))
                    document.getElementById('btInc').style.display='none';
                
                if(document.getElementById('btAlt'))
                    document.getElementById('btAlt').style.display='block';
                    
                if(document.getElementById('btExc'))
                    document.getElementById('btExc').style.display='block';
                    
                if(document.getElementById('edtDtBas'))
                    document.getElementById('edtDtBas').style.display='block';
                    
                if(document.getElementById('edtLink'))                    
                    document.getElementById('edtLink').style.display='block';
    
                if(document.getElementById('edtWork'))            
                    document.getElementById('edtWork').style.display='block';

                if(inDisponibilidade == '1'){
                    if(document.getElementById('btHab'))
                        document.getElementById('btHab').style.display='none';
                        
                    if(document.getElementById('btDes'))
                        document.getElementById('btDes').style.display='block';
                
                }else{
                    if(document.getElementById('btHab'))
                        document.getElementById('btHab').style.display='block';
                        
                    if(document.getElementById('btDes'))
                        document.getElementById('btDes').style.display='none';
                }

                if(document.getElementById('origemParam'))
                    document.getElementById('origemParam').style.display='block';
                
                if(document.getElementById('destinoParam'))
                    document.getElementById('destinoParam').style.display='none';

                document.forms[0].idContato.value = idContato;            
                document.forms[0].nmContato.value = nmContato;
                document.forms[0].idContatoPai.value = idContatoPai;
                document.forms[0].inDisponibilidade.value = inDisponibilidade;            
                document.forms[0].idNomeContato.value = idNomeContato;
                document.forms[0].nrNivel.value = nrNivel;
                document.forms[0].inFolha.value = inFolha;
                document.forms[0].dsPath.value = dsPath;
            }

            function capturaParametrosContatoRaiz (idContato, nmContato, idContatoPai, inDisponibilidade, idNomeContato, nrNivel, inFolha, dsPath){
                if(document.getElementById('btInc'))
                    document.getElementById('btInc').style.display='block';
                
                if(document.getElementById('btAlt'))
                    document.getElementById('btAlt').style.display='block';
                
                if(document.getElementById('btExc'))
                    document.getElementById('btExc').style.display='none';
                
                if(document.getElementById('edtDtBas'))
                    document.getElementById('edtDtBas').style.display='none';
                
                if(document.getElementById('edtLink'))
                    document.getElementById('edtLink').style.display='none';
                
                if(document.getElementById('edtWork'))
                    document.getElementById('edtWork').style.display='none';

                if(document.getElementById('btHab'))
                    document.getElementById('btHab').style.display='none';
                    
                if(document.getElementById('btDes'))                    
                    document.getElementById('btDes').style.display='none';

                if(document.getElementById('origemParam'))
                    document.getElementById('origemParam').style.display='none';
                
                if(document.getElementById('destinoParam'))
                    document.getElementById('destinoParam').style.display='block';

                document.forms[0].idContato.value = idContato;            
                document.forms[0].nmContato.value = nmContato;
                document.forms[0].idContatoPai.value = idContatoPai;
                document.forms[0].inDisponibilidade.value = inDisponibilidade;            
                document.forms[0].idNomeContato.value = idNomeContato;
                document.forms[0].nrNivel.value = nrNivel;
                document.forms[0].inFolha.value = inFolha;
                document.forms[0].dsPath.value = dsPath;
            }
    
            function capturaParametrosRaiz (idContato, nmContato, idContatoPai, inDisponibilidade, idNomeContato, nrNivel, inFolha, dsPath){
                document.forms[0].idContato.value = idContato;            
                document.forms[0].nmContato.value = nmContato;
                document.forms[0].idContatoPai.value = idContatoPai;
                document.forms[0].inDisponibilidade.value = inDisponibilidade;            
                document.forms[0].idNomeContato.value = idNomeContato;
                document.forms[0].nrNivel.value = nrNivel;
                document.forms[0].inFolha.value = inFolha;
                document.forms[0].dsPath.value = dsPath;
            }

            function limpaCampos(){
                document.forms[0].idContato.value = '';            
                document.forms[0].nmContato.value = '';
                document.forms[0].idContatoPai.value = '';
                document.forms[0].inDisponibilidade.value = '';            
                document.forms[0].idNomeContato.value = '';
                document.forms[0].nrNivel.value = '';
                document.forms[0].inFolha.value = '';
                document.forms[0].dsPath.value = '';
            }

            function removerItem(){
                if(document.forms[0].idContato.value == ''){
                    alert("Selecione um Item para ser exclu�do.");
                    return;
                }
                if (window.confirm("Confirma exclus�o do Item?")) {
                    document.forms[0].action = "removeItem.do?idContato=" + document.forms[0].idContato.value;
                    mostrar_div();
                    document.forms[0].target = '';
                    document.forms[0].submit();
                    limpaCampos(); 
                }   
            }
        
            function chamaConfigArvoreContato(){
                if(document.forms[0].idContato.value==''){
                    alert('Selecione um Item para ser configurado.');
                    return;
                }else{
                    document.forms[0].action = '/FrontOfficeWeb/admsistemas/admArvoreContato/dadosBasicos/begin.do?action=begin.do&idContato=' 
                                                + document.forms[0].idContato.value 
                                                + '&nmContato=' + escape(document.forms[0].nmContato.value)
                                                + '&idContatoPai=' + document.forms[0].idContatoPai.value
                                                + '&inDisponibilidade=' + document.forms[0].inDisponibilidade.value
                                                + '&idNomeContato=' + document.forms[0].idNomeContato.value
                                                + '&nrNivel=' + document.forms[0].nrNivel.value
                                                + '&inFolha=' + document.forms[0].inFolha.value
                                                + '&dsPath=' + escape(document.forms[0].dsPath.value);
                    document.forms[0].target = '';
                    mostrar_div();
                    document.forms[0].submit();
                }
            }
        
            function chamaConfigArvoreWorkflow(){
                if(document.forms[0].idContato.value==''){
                    alert('Selecione um Item para ser configurado.');
                    return;
                }else{
                    document.forms[0].action = '/FrontOfficeWeb/admsistemas/admArvoreContato/workflow/begin.do?action=begin.do&idContato=' 
                                                + document.forms[0].idContato.value 
                                                + '&nmContato=' + escape(document.forms[0].nmContato.value)
                                                + '&idContatoPai=' + document.forms[0].idContatoPai.value
                                                + '&inDisponibilidade=' + document.forms[0].inDisponibilidade.value
                                                + '&idNomeContato=' + document.forms[0].idNomeContato.value
                                                + '&nrNivel=' + document.forms[0].nrNivel.value
                                                + '&inFolha=' + document.forms[0].inFolha.value
                                                + '&dsPath=' + escape(document.forms[0].dsPath.value);
                    document.forms[0].target = '';
                    mostrar_div();
                    document.forms[0].submit();
                }
            }
        
            function chamaLink(){
                if(document.forms[0].idContato.value==''){
                    alert('Selecione um Item para ser configurado.');
                    return;
                }else{
                    document.forms[0].action = '/FrontOfficeWeb/admsistemas/admArvoreContato/dadosBasicos/abaLinkInfo/begin.do';
                    /*
                    &idContato=' 
                                                + document.forms[0].idContato.value 
                                                + '&nmContato=' + document.forms[0].nmContato.value 
                                                + '&idContatoPai=' + document.forms[0].idContatoPai.value
                                                + '&inDisponibilidade=' + document.forms[0].inDisponibilidade.value
                                                + '&idNomeContato=' + document.forms[0].idNomeContato.value
                                                + '&nrNivel=' + document.forms[0].nrNivel.value
                                                + '&inFolha=' + document.forms[0].inFolha.value
                                                + '&dsPath=' + document.forms[0].dsPath.value;
                                                */
                    document.forms[0].target = '';
                    mostrar_div();
                    document.forms[0].submit();
                }
            }
        
            function abreJanelaInserir(){
                if(document.forms[0].idContato.value==''){
                    alert('Selecione um Item pai para inser��o.');
                    return;
                }else{
                    document.forms[0].inFolha.value = ''; 
                    detalhe.style.display = '';
                    var b = document.getElementById("bg");
                    b.style.visibility = 'visible';
                    divFrame = document.getElementById('frameDetalhe');
                    divFrame.src = 'criarInserirItemContato.do?' 
                                    + 'idContato=' 
                                    + document.forms[0].idContato.value
                                    + '&nmContato=' + escape(document.forms[0].nmContato.value) 
                                    + '&idContatoPai=' + document.forms[0].idContatoPai.value
                                    + '&idNomeContato=' + document.forms[0].idNomeContato.value
                                    + '&inFolha=' + document.forms[0].inFolha.value
                                    + '&dsPath=' + escape(document.forms[0].dsPath.value)
                                    + '&salvaedita=salva';
                }
            }

            function abreJanelaEditar(){
                if(document.forms[0].idContato.value==''){
                    alert('Selecione um Item para altera��o.');
                    return;
                }else{
                    detalhe.style.display = '';
                    divFrame = document.getElementById('frameDetalhe');
                    var b = document.getElementById("bg");
                    b.style.visibility = 'visible';
                    divFrame.src = 'criarEditarItemContato.do?' 
                                    + 'idContato=' 
                                    + escape(document.forms[0].idContato.value) 
                                    + '&nmContato=' + escape(document.forms[0].nmContato.value) 
                                    + '&idContatoPai=' + escape(document.forms[0].idContatoPai.value)
                                    + '&inDisponibilidade=' + escape(document.forms[0].inDisponibilidade.value)
                                    + '&idNomeContato=' + escape(document.forms[0].idNomeContato.value)
                                    + '&nrNivel=' + escape(document.forms[0].nrNivel.value)
                                    + '&inFolha=' + escape(document.forms[0].inFolha.value)
                                    + '&dsPath=' + escape(document.forms[0].dsPath.value)
                                    + '&salvaedita=edita';
                        mostrar_div();
    
                }
            }
        
            function fechaJanela(){
            
                var comboQtdCopia = document.getElementById("qtdCopia");
                comboQtdCopia.style.visibility = 'visible';
                
            
                var x = document.getElementById("detalhe");
                x.style.display = 'none';
                divFrame = document.getElementById('frameDetalhe');
                divFrame.src = 'nada.html';
                var b = document.getElementById("bg");
                b.style.visibility = 'hidden';
                document.forms[0].idContato.value = '';
            }
        
            function capturaHabilitaDesabilita(idContato, inDisponibilidade){
                //document.forms[0].idContato.value = idContato;
                //document.forms[0].inDisponibilidade.value = inDisponibilidade;        
    
            }
        
            function enviaHabilitaDesabilita(){
                var msg = '';
            
                if(document.forms[0].idContato.value==''){
                    alert('Selecione um �tem para alterar o Status.');
                    return;
        
                }else{
                    if(document.forms[0].inDisponibilidade.value == '1'){
                        msg = 'Confirma Indisponibiliza��o do �tem?';
        
                    }else if(document.forms[0].inDisponibilidade.value == '0'){
                        msg = 'Confirma Disponibiliza��o do �tem?';
                    }
                    
                    if(confirm(msg)){
                        document.forms[0].target = '';
                        document.forms[0].action = 'habilitaDesabilitaArvore.do';
                        mostrar_div();
                        document.forms[0].submit();
                    }
                }
            }

            function origem(){
                if(document.forms[0].idContato.value == ''){   
                    alert('Selecione um �tem para Origem.');
                    return false;
    
                }else{
                    var pathOrigem = document.forms[0].dsPath.value;
                    if(pathOrigem.length > 50){
                       var subOrigem = pathOrigem.substring(pathOrigem.length - 50);
                        spOrigem.innerHTML = "..."+subOrigem;
        
                    }else
                        spOrigem.innerHTML = pathOrigem;
        
                    document.forms[0].idContatoOrigem.value = document.forms[0].idContato.value;
                }
            }
        
            function destino(){
                if(document.forms[0].idContato.value == ''){   
                    alert('Selecione um �tem para Destino.');
                    return false;
    
                }else{
                    var pathDestino = document.forms[0].dsPath.value;
                    if(pathDestino.length > 50){
                        var subDestino = pathDestino.substring(pathDestino.length - 50);
                        spDestino.innerHTML = "..."+subDestino;
    
                    }else
                        spDestino.innerHTML = pathDestino;
                    
                    document.forms[0].idContatoDestino.value = document.forms[0].idContato.value;
                }
            }

            function chamaCopiaArvore(){
                if(document.forms[0].idContatoOrigem.value == ''){
                    alert('Selecione uma Folha de Origem.');
                    return false;
    
                }else if(document.forms[0].idContatoDestino.value == ''){
                    alert('Selecione uma pasta de Destino.');
                    return false;
    
                }else if(document.forms[0].idContatoDestino.value == document.forms[0].idContatoOrigem.value){
                    alert('N�o � possivel c�piar parametros do mesmo �tem.');
                    return false;
    
                }else if(document.forms[0].qtdCopia.value == ''){
                    alert('Selecione n�mero de itens para c�pia.');
                    return false;
    
                }else if(confirm("Confirma c�pia de �tem?")){
                    document.forms[0].target = '';
                    document.forms[0].action = 'copiaArvore.do';
                    mostrar_div();
                    document.forms[0].submit();
                }
            }
        </script>
    </netui-template:section>

    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="adm_mave_verpagina">
        <!-- Menu de Administra��o de Sistemas -->
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="3"></div>
        <!--APLICACAO-->
        <script>
            mostrar_div();
        </script>
        <vivo:sessao id="qdMain" height="475" width="790" label="�rvore de contatos" operacoes="Manuten�&atilde;o" scroll="no">
            <form name="myForm" action="">
            <html:hidden name="FormArvoreContato" property="idContato"/>
            <html:hidden name="FormArvoreContato" property="nmContato"/>
            <html:hidden name="FormArvoreContato" property="idContatoPai"/>
            <html:hidden name="FormArvoreContato" property="inDisponibilidade"/>
            <html:hidden name="FormArvoreContato" property="idNomeContato"/>
            <html:hidden name="FormArvoreContato" property="nrNivel"/>
            <html:hidden name="FormArvoreContato" property="inFolha"/>
            <html:hidden name="FormArvoreContato" property="dsPath"/>
            <html:hidden name="FormArvoreContato" property="salvaedita"/>
            <html:hidden name="FormArvoreContato" property="idContatoOrigem"/>
            <html:hidden name="FormArvoreContato" property="idContatoDestino"/>
            <input type="hidden" name="pathOrigem" value="">
            <input type="hidden" name="pathDestino" value="">

            <table>
                <tr>
                    <td>
                        <vivo:quadro width="383" height="50" id="grupo1" label="Manuten��o da &Aacute;rvore de Contatos"><br>
                            <table width="100%" align="center">
                                <tr>
                                    <td align="center">
										<acesso:controlHiddenItem nomeIdentificador="adm_mave_incluir">
											<img id="btInc"
												 onClick="abreJanelaInserir(); return false"
												 src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif"
												 style="border:none;cursor:hand;" />
										</acesso:controlHiddenItem>
                                    </td>
                                    <td align="center">
										<acesso:controlHiddenItem nomeIdentificador="adm_mave_editar">
											<img id="btAlt"
											     onClick="abreJanelaEditar(); return false"
												 src="/FrontOfficeWeb/resources/images/bt_alterar_nrml.gif"
												 style="border:none;cursor:hand;"/>
										</acesso:controlHiddenItem>
                                    </td>
                                    <td align="center">
										<acesso:controlHiddenItem nomeIdentificador="adm_mave_apagar">
											<img id="btExc"
											     onClick="removerItem(); return false"
												 src="/FrontOfficeWeb/resources/images/bt_excluir_nrml.gif"
												 style="border:none;cursor:hand;"/>
										</acesso:controlHiddenItem>
                                    </td>
                                </tr>
                            </table>
                        </vivo:quadro>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <vivo:quadro width="772" height="40" id="grupo3" label="C�pia de Par�metros">
                             <table width="100%" height="100%" align="center" cellpadding="0" cellspacing="0" border="0">
                                <tr>
                                    <td align="left" width="350"><span id="spOrigem"></span></td>
                                    <td align="left" colspan="2"><span id="spDestino"></span></td>
                                </tr>
                                <tr valign="bottom">
                                    <td align="left">
                                        <img id="origemParam"
											 onclick="origem(); return false"
											 src="/FrontOfficeWeb/resources/images/bt_origem_nrml.gif"
											 style="border:none;cursor:hand;" />
                                    </td>
                                    <td align="left">
                                        <img id="destinoParam"
										     onclick="destino(); return false"
											 src="/FrontOfficeWeb/resources/images/bt_destino_nrml.gif"
											 style="border:none;cursor:hand;" />
                                    </td>
                                    <td align="right">
                                        <b>N�mero de c�pias:</b>
                                        <html:select name="FormArvoreContato" property="qtdCopia">
                                                <html:option value="">-- Selecione --</html:option>
                                                <html:option value="1">1</html:option>
                                                <html:option value="2">2</html:option>
                                                <html:option value="3">3</html:option>                                                
                                        </html:select>
										<img id="copiaParam"
									         onclick="chamaCopiaArvore();
											 return false"
											 src="/FrontOfficeWeb/resources/images/bt_copiar_nrml.gif"
											 style="border:none;cursor:hand;" />
                                    </td>
                                </tr>
                            </table>
                        </vivo:quadro>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <table width="100%" height="290" border="0" cellspacing="1" cellpadding="1" align="center" class="tbl_bgGray">
                            <tr>
                                <td valign="top">
                                    <div style="top:0px;left:0px;height:310px;width:762px;padding:5px;overflow:auto;">
                                         <script>
                                            <%=(String)request.getAttribute("arvore")%>
                                        </script>
                                    </div>
                                </td>
                            </tr>
                        </table>                    
                    </td>
                </tr>
            </table>
            <img vspace="3" id="btVoltar" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" style="border:none;cursor:hand;" onClick="window.location.href='/FrontOfficeWeb/index.jsp'; return false;" />
            </form> 
            <iframe scrolling="yes"  name="iframeArvoreContato" height="110px" width="110px"></iframe>              
            
        </vivo:sessao>
        <!--vivo:quadroFlutuante id="detalhe" idIframe="frameDetalhe" spacesLeft="60" spacesTop="50" url="< %=request.getContextPath()%>" display="none" height="470" width="600" label="Inser��o/Altera��o de Item da �rvore de Contatos"/-->
            <div  id="detalhe" style="z-index:999 ;display:none;position:absolute; top:25; left:25; width:550; height:550; border-style:solid; border-width:1px; border-color:#adadad; background-color:#e3ecf4;">
                <table width="600" height="21" cellpadding="0" cellspacing="0" background="/FrontOfficeWeb/resources/images/window_topbg.gif" bgcolor="#0066cb" class="tbl_titulo">
                    <tr>
                        <td id="titleBar" style="cursor:move" width="600" height="21">Inser��o/Altera��o de Item da �rvore de Contatos</td>
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
                            <iframe name="frameDetalhe" id="frameDetalhe" src="/FrontOfficeWeb/admsistemas/nada.html" frameborder="0" width="100%" height="100%"></iframe>
                        </td>
                    </tr>
                </table>
            </div>
        <div id="bg" style="z-index:998 ;visibility: hidden; position:absolute; top:0; left:0; width:100%; height:100%; border-style:solid; background-image:url(/FrontOfficeWeb/resources/images/window_bg.gif); border-width:1px; border-color:#adadad;"></div>
        
        <script language="javascript" for="window" event="onload">
        <!--   
            oculta_div();
            document.forms[0].idContato.value = '';
            if(('<%=(String)request.getAttribute("msgError")%>' != "") && ('<%=(String)request.getAttribute("msgError")%>' != 'null')){
                alert('<%=(String)request.getAttribute("msgError")%>');
            }
        -->
        </script> 
        
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>
