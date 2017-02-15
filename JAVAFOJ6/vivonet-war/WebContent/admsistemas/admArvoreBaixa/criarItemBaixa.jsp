<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%String[] idTipoComunicacao = (String[])session.getAttribute("idTipoComunicacao");%>
<%String[] dsTipoComunicacao = (String[])session.getAttribute("dsTipoComunicacao");%>

<acesso:controlInitEnv/>

<bean:define id="FormInsereBaixa"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formInsereBaixa"/>
<bean:define id="AdmNomeBaixaVO"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formInsereBaixa.admNomeBaixaVO"/>
<bean:define id="AdmIndicadorAnatelExistenteVO"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formInsereBaixa.admIndicadorAnatelExistenteVO"/>
<bean:define id="AdmIndicadorAnatelAssociadoVO"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formInsereBaixa.admIndicadorAnatelAssociadoVO"/>
<bean:define id="Mensagens"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formInsereBaixa.mensagens"/>

<netui-template:template templatePage="/resources/jsp/templateUsuarioClean.jsp">    
    <netui-template:section name="headerSection">
    <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/xtooltip.js"></script>
    <script language="Javascript">
        // variaveis globais
        var valorT = ["0", "0", "0", "0", "0", "0", "0", "0", "0"];
        var tamanhoCamunica = '<%=idTipoComunicacao.length%>';
        
        <%for(int i=0;i<idTipoComunicacao.length;i++){%>
            function exibeCampos<%=i%>(){
                    var a = document.getElementById("divx<%=i%>");
                    a.style.display = 'block'; 
                    a.style.visibility = 'visible'; 
                    var b = document.getElementById("divC<%=i%>");
                    b.style.display = 'none'; 
                    b.style.visibility = 'hidden'; 
            }
    
            function ocultaCampos<%=i%>(){
                    var a = document.getElementById("divx<%=i%>");
                    //a.style.display = 'none'; 
                    a.style.visibility = 'hidden';
                    var b = document.getElementById("divC<%=i%>");
                    //b.style.display = 'none'; 
                    b.style.visibility = 'hidden'; 
            }
        <%}%>
        <%for(int i=0;i<idTipoComunicacao.length;i++){%>
            function exibeCamposC<%=i%>(){
                    var a = document.getElementById("divx<%=i%>");
                    a.style.display = 'none'; 
                    a.style.visibility = 'hidden'; 
                    var b = document.getElementById("divC<%=i%>");
                    b.style.display = 'block'; 
                    b.style.visibility = 'visible'; 
            }
    
            function ocultaCamposC<%=i%>(){
                    var a = document.getElementById("divx<%=i%>");
                    a.style.visibility = 'hidden';
                    //a.style.display = 'none'; 
                    var b = document.getElementById("divC<%=i%>");
                    b.style.visibility = 'hidden'; 
                    //b.style.display = 'none'; 
            }
        <%}%>
        <%if(idTipoComunicacao.length>0){%>
            function validaComunicacao(){
                <%for(int i=0;i<idTipoComunicacao.length;i++){%>  
                    if(document.forms[0].kenai<%=i%>.value=='1' && document.forms[0].dsMensagem<%=i%>.value==''){
                        alert('Preencha um valor válido para o campo <%=dsTipoComunicacao[i]%> ou selecione nenhum.');
                        return false;
                    }else
                        return true;
                <%}%>
            }
        <%}%>
        function transfereSelecaoLista(listaOrigem, listaDestino){
            var i;
            for(i = 0; i<listaOrigem.options.length; i++)
                if(listaOrigem.options[i].selected)
                    listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                    
            for(i = listaOrigem.options.length-1; i>=0; i--)
                if(listaOrigem.options[i].selected)
                    listaOrigem.options[i] = null;
        }
    
        function gravar(){
            if(valida()){
                var lista = document.forms[0].arrayAdmIndicadorAnatelAssociado;
                //Processa gravação
                for(i = 0;i<lista.length;i++){
                   document.forms[0].arrayAdmIndicadorAnatelAssociado.options[i].selected =true; 
                }
                document.forms[0].target = "_parent";
                document.forms[0].action='salvaItemInserido.do';
                parent.mostrar_div();
                document.forms[0].submit(); 
            }
        }
        
        function escondeLista(){
            var a = document.getElementById("div1");
            a.style.visibility = 'hidden';
            var b = document.getElementById("div2");
            b.style.visibility = 'visible';
            var c = document.getElementById("div4");
            c.style.visibility = 'hidden';
            var c = document.getElementById("div5");
            c.style.visibility = 'hidden';
            document.forms[0].novaExistente.value = 0;
        }
        
        function exibeLista(){
            var a = document.getElementById("div1");
            a.style.visibility = 'visible';
            var b = document.getElementById("div2");
            b.style.visibility = 'hidden';
            var c = document.getElementById("div4");
            c.style.visibility = 'hidden';
            var c = document.getElementById("div5");
            c.style.visibility = 'visible';
            document.forms[0].novaExistente.value = 1;
        }
        
        function valida(){
            var isMensagem = 0;
           // if(document.forms[0].inDisponibilidade.selectedIndex == 0)
           // {
           //     alert("Selecione um Status para Disponibilidade.")
           //     return false;
          //  }
            
            if(document.forms[0].novaExistente.value == 0){
                if(document.forms[0].nmBaixaEscolhido.value == ''){
                    alert('Preencha um novo Nome de Item.');
                    return false;
                }                    
            }else if(document.forms[0].novaExistente.value == 1){
                if(document.forms[0].idNomeBaixa.selectedIndex == 0){
                    alert('Selecione um Nome de Item Existente.');
                    return false;
                }
            }
            // Se folha.
            if(document.forms[0].inFolha.value == '1'){
                if(document.forms[0].mensagemSelect.value == 0){
                        alert('É necessário o preenchimento de pelo menos uma mensagem.');
                        return false;
                }else{
                    for(var i =0;i<tamanhoCamunica;i++){
                            var kenai = eval("document.forms[0].kenai"+i);
                            var dsMensagem = eval("document.forms[0].dsMensagem"+i);
                            var idMensagemAtual = eval("document.forms[0].idMensagemAtual"+i);
                        if(kenai[1].checked){
                            if(trim(dsMensagem.value) == ''){
                                    alert('É necessário o preenchimento de mensagem(ns) nova(s).');
                                    return false;
                                }
                        }else if(kenai[2].checked){
                            if(idMensagemAtual.selectedIndex == 0){
                                    alert('É necessário seleção de mensagem(ns) existente(s).');
                                    return false;
                                }
                            }
                        }                   
                   }
            }
            return true;
        }
        
        function hideCampos(){
            document.getElementById('frmMensagens').style.display = "none";
            document.getElementById('frmIndicadores').style.display = "none";
        }
        
        function showCampos(){
            document.getElementById('frmMensagens').style.display = "block";
            document.getElementById('frmIndicadores').style.display = "block";
        }

        function ajustaTamanho(obj){
            if(obj.readyState=="complete"){
                iframe = parent.document.getElementById('frameDetalhe');
                div = parent.document.getElementById('detalhe');
                altura  = parent.frameDetalhe.document.body.scrollHeight;
                largura = 440;
                iframe.style.width = largura;
                iframe.style.height = altura;
                iframe.style.top = (600 - altura) / 2;
                iframe.style.left = (800 - largura) / 2;
                div.style.width = largura;
                div.style.height = altura;
                div.style.top = (600 - altura) / 2;
                div.style.left = (800 - largura) / 2;
            }
        }

        function carregaH(op){
            document.forms[0].inFolha.value  = op;
        }
        
        function validaMensagem(valor,idx){
            document.forms[0].mensagemSelect.value = 0;
            for(var i =0;i<tamanhoCamunica+100;i++){
                var kenai = eval("document.forms[0].kenai"+i);
                if(kenai != null && !kenai[0].checked)
                    document.forms[0].mensagemSelect.value = 1;                  
            }
        }        
        
        function exibeAtual(){
            var a = document.getElementById("div1");
            a.style.visibility = 'hidden';
            var b = document.getElementById("div2");
            b.style.visibility = 'hidden';
            var c = document.getElementById("div4");
            c.style.visibility = 'visible';
            var c = document.getElementById("div5");
            c.style.visibility = 'hidden';
            //document.forms[0].novaExistente.value = 2;
            document.forms[0].idNomeBaixa.selectedIndex = 0;
            document.forms[0].nmBaixaNovo.value = '';
        }
        
        function pesquisa(){
            if(trim(document.forms[0].descPesquisa.value).length < 3){
                alert('É necessário digitar no mínimo 3 caracteres para pesquisa.');
                document.forms[0].descPesquisa.focus();
                return false;
            }
            document.forms[0].target = 'ifmInnerBrowserBaixa';
            document.forms[0].action= 'getNome.do';
            parent.mostrar_div();
            document.forms[0].submit();
        }
    </script>    
    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="adm_cib_verpagina">
        <form action="salvaItemInserido.do" id="salvaItemInserido" name="salvaItemInserido" method="POST">
        <input type="hidden" name="inFolha" value="1">
        <input type="hidden" name="novaExistente" value="1">
        <input type="hidden" name="mensagemSelect" value="">
        <vivo:quadro id="descricao" width="440" height="125" label="Descriç&atilde;o" scroll="no">
            <table width="100%" border="0" cellspacing="0" cellpadding="2">
                <tr>
                    <td width="15%" ><b>Caminho:</b></td>
                    <td width="85%">
                        &nbsp;&nbsp;<bean:write name="FormInsereBaixa" property="dsPath"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    </td>
                </tr>
                <tr>
                </tr>
                <tr>
                    <td class="tblEstatica_campoNome"><b>Tipo:</b></td>
                    <td><!-- actionForm.inFolha -->
                        <netui:radioButtonGroup dataSource="{}" tagId="folderType" defaultValue="1">
                            <netui:radioButtonOption styleClass="radio" value="0" onClick="hideCampos();ajustaTamanho(parent.document.getElementById('detalhe'));carregaH(this.value);" >Pasta</netui:radioButtonOption>
                            <netui:radioButtonOption styleClass="radio" value="1" onClick="showCampos();ajustaTamanho(parent.document.getElementById('detalhe'));carregaH(this.value);">&nbsp;Folha</netui:radioButtonOption>
                        </netui:radioButtonGroup>  
                    <!-- b>&nbsp;&nbsp;&nbsp;&nbsp;Disponibilidade:</b>
                       &nbsp;&nbsp; < html:select name="FormInsereBaixa" property="inDisponibilidade" >
                            < html:option value="">Escolha...< /html:option>
                            < html:option value="0">Não< /html:option>
                            < html:option value="1">Sim< /html:option>
                        < /html:select>                                        
                    </td -->
                </tr>
                <tr>
                    <td class="tblEstatica_campoNome"><b>Nome:</b></td>
                    <td><!-- actionForm.idBaixa -->
                        <netui:radioButtonGroup dataSource="{}" tagId="nomeTipo" defaultValue="1">
                            <netui:radioButtonOption styleClass="radio" value="0" onClick="escondeLista();" tabindex="0">Novo</netui:radioButtonOption>
                            <netui:radioButtonOption styleClass="radio" value="1" onClick="exibeLista();" tabindex="1">&nbsp;Existente</netui:radioButtonOption>
                        </netui:radioButtonGroup>                                            
                       <div id="div3" style="visibility:'visible'; position:relative; top: 0px; left: 0px; height: 24px; padding: 0px;">
                            <div id="div1" style="visibility:'hidden'; position:absolute; top: 0px; left: 0px; height: 0px; padding: 0px;">
                               &nbsp;&nbsp; <html:select name="FormInsereBaixa" property="idNomeBaixa" style="text-indent:3px;width:150px" styleClass="SELECT">
                                    <html:option value="-1">Escolha uma opção...</html:option>
                                    <!-- html:options collection="AdmNomeBaixaVO" property="idNomeBaixa" labelProperty="nmBaixa" /--> 
                                </html:select>
                            </div>
                            <div id="div2" style="visibility:'hidden'; position:absolute; top: 0px; left: 0px; height: 0px; padding: 0px;">
                              &nbsp;&nbsp;  <html:text name="FormInsereBaixa" property="nmBaixaEscolhido" style="width:150px" styleClass="input" rows="1" maxlength="254"/>
                            </div>
                            <div id="div4" style="visibility:'hidden'; position:absolute; top: 0px; left: 0px; height: 0px; padding: 0px;">
                                  <bean:write name="FormInsereBaixa" property="nmBaixa"/>
                            </div>
                            <div id="div5" style="visibility:'hidden';position:absolute; top: 0px; left: 160px; height: 0px; padding: 0px;width:210px;">
                                    <html:text name="FormInsereBaixa" property="descPesquisa" maxlength="30" />
                                    <img  src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif"  style="border:none;cursor:hand" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif';" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif';" onClick="return pesquisa();" />
                            </div>                            
                        </div>
                    </td>
                </tr>
            </table>
        </vivo:quadro>
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
        <%try{%>
            <%if(idTipoComunicacao.length>0){%>
            <div id="frmMensagens">
                <vivo:quadro id="descricao" width="440" height="215" label="Mensagens">
                    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="1"></div>
                    <table width="400" border="0" cellspacing="1" cellpadding="1">
                        <%for(int i=0;i<idTipoComunicacao.length;i++){%>
                            <tr>
                                <td class="tbl_bgGray" width="150" height="20" align="center">
                                    &nbsp;<%=dsTipoComunicacao[i]%>
                                </td>
                                <td width="233" align="center">
                                    <input type="radio" class="radio" name="kenai<%=i%>" value="0" checked onkeypress="ocultaCampos<%=i%>();validaMensagem(0,<%=i%>);" onclick="ocultaCampos<%=i%>();validaMensagem(0,<%=i%>);">&nbsp;Nenhuma
                                    <input type="radio" class="radio" name="kenai<%=i%>" value="1" onkeypress="exibeCampos<%=i%>();validaMensagem(1,<%=i%>);" onclick="exibeCampos<%=i%>();validaMensagem(1,<%=i%>);">&nbsp;Nova
                                    <input type="radio" class="radio" name="kenai<%=i%>" value="2" onkeypress="exibeCamposC<%=i%>();validaMensagem(2,<%=i%>);" onclick="exibeCamposC<%=i%>();validaMensagem(2,<%=i%>);">&nbsp;Existente
                                    <div id="div3" style="visibility:'visible'; position:relative; top: 0px; left: 0px; height: 0px; padding: 0px;">
                                        <div id="divx<%=i%>" style="visibility:'hidden';display:block; top: 0px; left: 0px; height: 0px; padding: 0px;">
                                            <input type="hidden" name="koda<%=i%>" value="<%=idTipoComunicacao[i]%>">
                                            <input type="text" name="dsMensagem<%=i%>" maxlength="254" style="width:250px">
                                        </div>
                                        <div id="divC<%=i%>" style="visibility:'hidden';display:none; top: 0px; left: 0px; height: 0px; padding: 0px;">
                                        <select name="idMensagemAtual<%=i%>" style="width:250px">
                                            <option value="0">Escolha uma opção...</option>
                                            <logic:iterate name="FormInsereBaixa" property="mensagens" id="msg">
                                                <option value="<bean:write name="msg" property="idMensagemAviso" />"><bean:write name="msg" property="dsMensagemAviso" /></option>                                           
                                            </logic:iterate>
                                        </select>
                                        </div>                                    
                                    </div>
                                </td>
                            </tr>                                       
                            <tr>
                                <td colspan="2" align="left">
                                    <div id="divtelef" style="visibility:'visible'; top: 0px; left: 0px; height: 24px; padding: 0px;"></div>
                                </td>
                            </tr>     
                        <%}%>
                    </table> 
                </vivo:quadro>
                </div>
            <%}%>
        <%}catch(Exception ex){}%>
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
        <div id="frmIndicadores">
        <vivo:quadro id="indicadores" width="440" height="120" label="Indicadores Anatel">
            <table width="100%">
                <tr>
                    <td width="171" valign="top" align="center">
                        Indicadores Existentes<br>
                        <html:select name="FormInsereBaixa" property="arrayAdmIndicadorAnatelExistente" multiple="true" style="height:85px;width:171px;" styleClass="SELECT" size="6" onmouseover="ativarToolTip(this)">
                            <html:options collection="AdmIndicadorAnatelExistenteVO" property="idIndicador" labelProperty="nmIndicador" /> 
                        </html:select>
                    </td>
                    <td align="center" width="90">
                        <img style="border:none;cursor:hand" id="btRightSimp1" onclick="transfereSelecaoLista(document.forms[0].arrayAdmIndicadorAnatelExistente, document.forms[0].arrayAdmIndicadorAnatelAssociado);" border="0" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_over.gif'"/>                            
                        <br><br>
                        <img style="border:none;cursor:hand" id="btRightSimp2" onclick="transfereSelecaoLista(document.forms[0].arrayAdmIndicadorAnatelAssociado, document.forms[0].arrayAdmIndicadorAnatelExistente);" border="0" src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_over.gif'" />
                    </td>
                    <td width="172" valign="top" align="center">
                        Indicadores Associados<br>
                        <html:select name="FormInsereBaixa" property="arrayAdmIndicadorAnatelAssociado" multiple="true" style="height:85px;width:172px;" styleClass="SELECT" size="6" onmouseover="ativarToolTip(this)">
                            <html:options collection="AdmIndicadorAnatelAssociadoVO" property="idIndicador" labelProperty="nmIndicador" /> 
                        </html:select>
                    </td>
                </tr>
            </table>
        </vivo:quadro> 
        </div>
        <table width="100%" height="20" cellpadding="0" cellspacing="0">
            <tr>
                <td align="right" valign="bottom">
                <acesso:controlHiddenItem nomeIdentificador="adm_cib_salvar">
                    <img id="btSalvar1" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_salvar_over.gif" style="border:none;" onclick="gravar(); return false;" align="right"/> 
                </acesso:controlHiddenItem>
                </td>
            </tr>
        </table>
        <SCRIPT FOR=window EVENT=onload  LANGUAGE="JScript">
            document.body.style.backgroundColor = '#ededed';
            ajustaTamanho(parent.document.getElementById('detalhe'));
            ajustaTamanho(parent.document.getElementById('detalhe'));
            ajustaTamanho(parent.document.getElementById('detalhe'));
            showCampos();
            exibeLista();
            document.forms[0].inFolha.value = 1;
            validaMensagem(-1,-1);
            parent.oculta_div();
        </SCRIPT>
        <iframe style="width:0px;height:0px;" name="ifmInnerBrowserBaixa">
        <iframe id="DivShim" src="javascript:false;" scrolling="no" frameborder="0" style="position:absolute; top:0px; left:0px; display:none;"></iframe>
        <div id="PopupDiv" style="position:absolute;padding:2px; display:none;background-color:#000000; color:#ffffff; z-index:100;font-size:11px;"></div>
    </form>
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>