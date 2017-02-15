<%@page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<acesso:controlInitEnv/>
<bean:define id="FormEditaBaixa"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formEditaBaixa"/>
<bean:define id="AdmIndicadorAnatelExistenteVO"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formEditaBaixa.admIndicadorAnatelExistenteVO"/>
<bean:define id="AdmIndicadorAnatelAssociadoVO"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formEditaBaixa.admIndicadorAnatelAssociadoVO"/>
<bean:define id="AdmNomeBaixaVO"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formEditaBaixa.admNomeBaixaVO"/>

<%
String[] idTipoComunicacao = (String[])session.getAttribute("idTipoComunicacao");
        String[] dsTipoComunicacao = (String[])session.getAttribute("dsTipoComunicacao");
        String[] idTipoComunicacaoLista = (String[])session.getAttribute("idTipoComunicacaoLista");
        String[] dsTipoComunicacaoLista = (String[])session.getAttribute("dsTipoComunicacaoLista");
        String[] idBaixaMensagem = (String[])session.getAttribute("idBaixaMensagem");
        String[] dsMensagem = (String[])session.getAttribute("dsMensagem");
        int tipoComunica = 100;
boolean folder;
if (dsMensagem.length>0){
    folder=false;
}else{
    folder=true;
}%>
<netui-template:template templatePage="/resources/jsp/templateUsuarioClean.jsp">
    <netui-template:section name="headerSection">
    <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/xtooltip.js"></script>
    <script language="Javascript">
        var folder = <%=String.valueOf(folder)%>;
        var tamanhoCamunica = '<%=idTipoComunicacao.length%>';

            function transfereSelecaoLista(listaOrigem, listaDestino){
            var i;
            for(i = 0; i<listaOrigem.options.length; i++)
                if(listaOrigem.options[i].selected)
                    listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
            for(i = listaOrigem.options.length-1; i>=0; i--)
                if(listaOrigem.options[i].selected)
                    listaOrigem.options[i] = null;
        } 
        <%for(int i=0;i<idTipoComunicacaoLista.length;i++){%>
                function exibeCampos<%=i%>(){
                var a = document.getElementById("divx<%=i%>");
                a.style.display     = 'block'; 
                a.style.visibility  = 'visible'; 
                var b = document.getElementById("divC<%=i%>");
                b.style.display     = 'none'; 
                b.style.visibility  = 'hidden'; 
            }
    
                function ocultaCampos<%=i%>(){
                var a = document.getElementById("divx<%=i%>");
                //a.style.display     = 'none'; 
                a.style.visibility = 'hidden';
                var b = document.getElementById("divC<%=i%>");
                //b.style.display     = 'none'; 
                b.style.visibility = 'hidden'; 
            }

                function exibeCamposC<%=i%>(){
                var a = document.getElementById("divx<%=i%>");
                a.style.display     = 'none'; 
                a.style.visibility = 'hidden'; 
                var b = document.getElementById("divC<%=i%>");
                b.style.display     = 'block'; 
                b.style.visibility = 'visible'; 
            }
        <%}%>
        <%for(int i=0;i<idTipoComunicacao.length;i++){%>
                function exibeCampos<%=i+tipoComunica%>(){
                var a = document.getElementById("divx<%=i+tipoComunica%>");
                a.style.display     = 'block'; 
                a.style.visibility  = 'visible'; 
                var b = document.getElementById("divC<%=i+tipoComunica%>");
                b.style.display     = 'none'; 
                b.style.visibility  = 'hidden'; 
            }
    
                function ocultaCampos<%=i+tipoComunica%>(){
                var a = document.getElementById("divx<%=i+tipoComunica%>");
                //a.style.display     = 'none'; 
                a.style.visibility = 'hidden';
                var b = document.getElementById("divC<%=i+tipoComunica%>");
                b.style.visibility = 'hidden'; 
                //b.style.display     = 'none'; 
            }

                function exibeCamposC<%=i+tipoComunica%>(){
                    var a = document.getElementById("divx<%=i+tipoComunica%>");
                    a.style.display     = 'none'; 
                    a.style.visibility = 'hidden'; 
                    var b = document.getElementById("divC<%=i+tipoComunica%>");
                    b.style.display     = 'block'; 
                    b.style.visibility = 'visible'; 
            }
    
                function ocultaCamposC<%=i+tipoComunica%>(){
                    var a = document.getElementById("divx<%=i+tipoComunica%>");
                    //a.style.display     = 'none'; 
                    a.style.visibility = 'hidden';
                    var b = document.getElementById("divC<%=i+tipoComunica%>");
                    //b.style.display     = 'none'; 
                    b.style.visibility = 'hidden'; 
            }
        <%}%>
        <%if(idTipoComunicacaoLista.length>0){%>
            function validaComunicacao(){
                <%for(int i=0;i<idTipoComunicacao.length;i++){%>  
                    if(document.forms[0].kenai<%=i%>.value=='1' && document.forms[0].dsMensagem<%=i%>.value==''){
                        alert('Preencha um valor valido para o campo <%=dsTipoComunicacao[i]%> ou selecione nenhum.');
                        return false;
                    }else{
                        return true;
                    }
                <%}%>
                <%for(int i=idTipoComunicacao.length;i<idTipoComunicacaoLista.length;i++){%>  
                    if(document.forms[0].kenai<%=i%>.value=='1' && document.forms[0].dsMensagem<%=i%>.value==''){
                        alert('Preencha um valor valido para o campo <%=dsTipoComunicacaoLista[i]%> ou selecione nenhum.');
                        return false;
                    }else{
                        return true;
                    }
                <%}%>
            }
        <%}%>
        function validaTela(){
           // if(document.forms[0].nmBaixa.value==''){
            //    alert('Escolha um tipo.');
           //     return;
           // }
             <%if(idTipoComunicacao.length>0){%>
                if(validaComunicacao()){
                    gravar();
                }else{
                    return;
                }
             <%}else{%>
                    <%if (folder){%>
                        document.forms[0].action='salvaItemEditado.do';
                        document.forms[0].target = "_parent";
                        parent.mostrar_div();
                    document.forms[0].submit(); 
                    <%}%>                
             <%}%>
        }
        
            function gravar(){
                if(valida()){
                selecionaIndicadoresAssociados();
                document.forms[0].action='salvaItemEditado.do';
                document.forms[0].target = "_parent";
                parent.mostrar_div();
                document.forms[0].submit(); 
            }
        }
        
            function selecionaIndicadoresAssociados(){
                if(!folder){
                //Processa gravação
                    for(i = 0;i<document.forms[0].arrayAdmIndicadorAnatelAssociado.length;i++){
                    document.forms[0].arrayAdmIndicadorAnatelAssociado.options[i].selected =true; 
                }
            }
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
        
            function valida(){
            var isMensagem = 0;
               // if(document.forms[0].inDisponibilidade.selectedIndex == 0)
               // {
                //    alert("Selecione um Status para Disponibilidade.")
                //    return false;
                if(document.forms[0].idBaixaNome[1].checked){
                    if(document.forms[0].nmBaixaNovo.value == ''){
                        alert('Nome é um campo obrigatório, favor preencher.');
                        return false;
                    }
                }else if(document.forms[0].idBaixaNome[2].checked){
                    if(document.forms[0].idNomeBaixa.selectedIndex == 0){
                        alert('Selecione um nome existente.');
                        return false;
                    }
                }
            // Valida folha.
                if(!folder){
                    if(document.forms[0].mensagemSelect.value == 0){
                        alert('É necessário o preenchimento de pelo menos uma mensagem.');
                        return false;
                   }else
                        for(var i =0;i<tamanhoCamunica+100;i++){
                            var kenai = eval("document.forms[0].kenai"+i);
                            var dsMensagem = eval("document.forms[0].dsMensagem"+i);
                            var idMensagemAtual = eval("document.forms[0].idMensagemAtual"+i);
                            if(kenai != null && kenai[1].checked){
                                if(dsMensagem != null && trim(dsMensagem.value) == ''){
                                    alert('É necessário o preenchimento de mensagem(s) nova(s).');
                                    return false;
                                }
                            }else if(kenai != null && kenai[2].checked){
                                if(idMensagemAtual != null && idMensagemAtual.selectedIndex == 0){
                                    alert('É necessário seleção de mensagem(ns) existente(s).');
                                    return false;
                                }
                            }
                        }        
                    }                              
            return true;
        }

            function carregaH(op){
            document.forms[0].inFolha.value  = op;
        }
        
        var valorT = new Array(200);
            function inicializa(){
            for(var x=0;x < valorT.length;x++)
                valorT[x] = "0";
        }
        
            function validaMensagem(valor,idx){
            document.forms[0].mensagemSelect.value = 0;
                for(var i =0;i<tamanhoCamunica+100;i++){
                var kenai = eval("document.forms[0].kenai"+i);
                if(kenai != null && !kenai[0].checked)
                    document.forms[0].mensagemSelect.value = 1;                  
            }
        }
        
            function escondeLista(){
            document.forms[0].idNomeBaixa.value = '-1';
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
            document.forms[0].nmBaixaNovo.value = '';
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
    <script for="window" event="onload">
        parent.mostrar_div();
    </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="adm_eib_verpagina">
    <form action="salvaItemEditado" id="formArvoreBaixa" name="formArvoreBaixa">
    <html:hidden name="FormEditaBaixa" property="idBaixa"/>
    <html:hidden name="FormEditaBaixa" property="idBaixaPai"/>
    <html:hidden name="FormEditaBaixa" property="idNomeBaixaExistente"/>
    <input type="hidden" name="inFolha" value="1">
    <input type="hidden" name="novaExistente" value="1">
    <input type="hidden" name="mensagemSelect" value="1">
    <vivo:quadro id="descricao" width="438" height="125" label='Descrição' scroll="no">
        <table width="100%" border="0" cellspacing="0" cellpadding="2">
            <tr>
                <td width="15%"><b>&nbsp;Caminho:</b></td>
                <td width="85%">
                    <bean:write name="FormEditaBaixa" property="dsPath"/>&nbsp;&nbsp;&nbsp;&nbsp;
                </td>
            </tr>
            <!--tr>
                <td width="25%"><b>&nbsp;Disponibilidade:</b></td>
                <td width="75%">
                        < html:select name="FormEditaBaixa" property="inDisponibilidade">
                            < html:option value="">Escolha...< /html:option>
                            < html:option value="0">Não< /html:option>
                            < html:option value="1">Sim< /html:option>
                        < /html:select>                                        
                </td>
            </tr-->
            <tr>
                    <td class="tblEstatica_campoNome"><b>Nome:</b></td>
                    <td>
                        <input class="radio" type="radio" name="idBaixaNome" value="2" onClick="exibeAtual();" checked>Atual&nbsp;
                        <input class="radio" type="radio" name="idBaixaNome" value="0" onClick="escondeLista();">Novo
                        <input type="radio" class="radio" name="idBaixaNome" value="1" onClick="exibeLista();">&nbsp;Existente
                       <div id="div3" style="visibility:'visible'; position:relative; top: 0px; left: 0px; height: 24px; padding: 0px;">
                            <div id="div1" style="visibility:'hidden'; position:absolute; top: 0px; left: 0px; height: 0px; padding: 0px;">
                              <html:select name="FormEditaBaixa" property="idNomeBaixa" style="text-indent:3px;width:150px" styleClass="SELECT">
                                    <html:option value="-1">-- Selecione --</html:option>
                                    <!-- html:options collection="AdmNomeBaixaVO" property="idNomeBaixa" labelProperty="nmBaixa" /--> 
                                </html:select>
                            </div>
                            <div id="div2" style="visibility:'hidden'; position:absolute; top: 0px; left: 0px; height: 0px; padding: 0px;">
                              <html:text name="FormEditaBaixa" property="nmBaixaNovo" style="width:150px" styleClass="input" rows="1" maxlength="240"/>
                            </div>
                            <div id="div4" style="visibility:'hidden'; position:absolute; top: 0px; left: 0px; height: 0px; padding: 0px;">
                                  <bean:write name="FormEditaBaixa" property="nmBaixa"/>
                            </div>
                            <div id="div5" style="visibility:'hidden';position:absolute; top: 0px; left: 160px; height: 0px; padding: 0px;width:210px;">
                                    <html:text name="FormEditaBaixa" property="descPesquisa" maxlength="30" />
                                    <img  src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif"  style="border:none;cursor:hand" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif';" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif';" onClick="return pesquisa();" />
                            </div>                            
                        </div>
                </td>
            </tr>
            <tr>
                <td colspan="2" height="40" valign="top">
                    <div id="divcampo" style="display:'block'; top: 0px; left: 0px; height: 24px; padding: 0px;">
                    </div>
                </td>
            </tr>
        </table>
    </vivo:quadro>
    <%if (!folder){%>
    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
        <%try{%>
        <%if(idTipoComunicacaoLista.length>0){%>
            <vivo:quadro id="descricao" width="440" height="215" label="Mensagens">
                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="1"></div>
                <table width="400" border="0" cellspacing="1" cellpadding="1">
                    <%if(idTipoComunicacao.length>0){%>
                        <%for(int i=0;i<idTipoComunicacao.length;i++){%>
                            <tr>
                                <td class="tbl_bgGray" width="130" height="20" align="center">&nbsp;<%=dsTipoComunicacao[i]%></td>
                                    <td width="233" align="center">
                                    <input type="radio" class="radio" name="kenai<%=i+tipoComunica%>" value="0"  onkeypress="ocultaCampos<%=i+tipoComunica%>();validaMensagem(0,<%=i+tipoComunica%>);" onclick="ocultaCampos<%=i+tipoComunica%>();validaMensagem(0,<%=i+tipoComunica%>);">&nbsp;Nenhuma
                                    <input type="radio" class="radio" name="kenai<%=i+tipoComunica%>" value="1"  onkeypress="exibeCampos<%=i+tipoComunica%>();validaMensagem(1,<%=i+tipoComunica%>);" onclick="exibeCampos<%=i+tipoComunica%>();validaMensagem(1,<%=i+tipoComunica%>);">&nbsp;Nova
                                    <input type="radio" class="radio" name="kenai<%=i+tipoComunica%>" value="2" checked onkeypress="exibeCamposC<%=i+tipoComunica%>();validaMensagem(2,<%=i+tipoComunica%>);" onclick="exibeCamposC<%=i+tipoComunica%>();validaMensagem(2,<%=i+tipoComunica%>);">&nbsp;Existente
                                    <div id="divx<%=i+tipoComunica%>" style="display:'none'; top: 0px; left: 0px; height: 0px; padding: 0px;">
                                        <input type="hidden" name="koda<%=i+tipoComunica%>" value="<%=idTipoComunicacao[i]%>">
                                        <input type="hidden" name="tshica<%=i+tipoComunica%>" value="<%=idBaixaMensagem[i]%>">
                                         <input type="text" name="dsMensagem<%=i+tipoComunica%>" value="" style="width:250px"><%//=dsMensagem[i]%>
                                    </div>
                                        <div id="divC<%=i+tipoComunica%>" style="visibility:'visible';display:block; top: 0px; left: 0px; height: 0px; padding: 0px;">
                                        <select name="idMensagemAtual<%=i+tipoComunica%>" style="width:250px">
                                            <option value="0">Escolha uma opção...</option>
                                            <logic:iterate name="FormEditaBaixa" property="mensagens" id="msg" >
                                                <option value="<bean:write name="msg" property="idMensagemAviso" />"><bean:write name="msg" property="dsMensagemAviso" /></option>                                           
                                            </logic:iterate>
                                        </select>
                                            <script>document.forms[0].idMensagemAtual<%=i+tipoComunica%>.value = '<%=idBaixaMensagem[i]%>';</script>
                                        </div>                                    
                                    </td>
                            </tr>                                       
                            <tr>
                                <td colspan="2" align="left">
                                    <div id="divtelef" style="display:'block'; top: 0px; left: 0px; height: 24px; padding: 0px;"></div>
                                </td>
                            </tr> 
                        <%}%>
                        <%for(int i=0;i<idTipoComunicacaoLista.length;i++){%>
                            <%if(idTipoComunicacaoLista[i] != null && !idTipoComunicacaoLista[i].equals(ConstantesCRM.SVAZIO)){%>      
                                <tr>
                                    <td class="tbl_bgGray" width="130" height="20" align="center">&nbsp;<%=dsTipoComunicacaoLista[i]%></td>
                                        <td width="233" align="center">
                                                <input type="radio" class="radio" name="kenai<%=i%>" value="0"  checked onkeypress="ocultaCampos<%=i%>();validaMensagem(0,<%=i%>);" onclick="ocultaCampos<%=i%>();validaMensagem(0,<%=i%>);">&nbsp;Nenhuma
                                                <input type="radio" class="radio" name="kenai<%=i%>" value="1"  onkeypress="exibeCampos<%=i%>();validaMensagem(1,<%=i%>);" onclick="exibeCampos<%=i%>();validaMensagem(1,<%=i%>);">&nbsp;Nova
                                                <input type="radio" class="radio" name="kenai<%=i%>" value="2" onkeypress="exibeCamposC<%=i%>();validaMensagem(2,<%=i%>);" onclick="exibeCamposC<%=i%>();validaMensagem(2,<%=i%>);">&nbsp;Existente
                                                
                                                <div id="divx<%=i%>" style="visibility:'hidden';display:block; top: 0px; left: 0px; height: 0px; padding: 0px;">
                                                    <input type="hidden" name="koda<%=i%>" value="<%=idTipoComunicacaoLista[i]%>">
                                                     <input type="text" name="dsMensagem<%=i%>" value="" style="width:250px">
                                                </div>
                                                <div id="divC<%=i%>" style="visibility:'hidden';display:none; top: 0px; left: 0px; height: 0px; padding: 0px;">
                                                <select name="idMensagemAtual<%=i%>"style="width:250px" >
                                                    <option value="0">Escolha uma opção...</option>
                                                    <logic:iterate name="FormEditaBaixa" property="mensagens" id="msg">
                                                        <option value="<bean:write name="msg" property="idMensagemAviso" />"><bean:write name="msg" property="dsMensagemAviso" /></option>                                           
                                                    </logic:iterate>
                                                </select>
                                                </div>                                    
                                        </td>
                                </tr>                                       
                                <tr>
                                    <td colspan="2" align="left">
                                        <div id="divtelef" style="display:'block'; top: 0px; left: 0px; height: 24px; padding: 0px;"></div>
                                    </td>
                                </tr>
                            <%}%>
                        <%}%>                        
                    <%}%>   
                </table> 
            </vivo:quadro>
        <%}%>
    <%}catch(Exception ex){}%>
    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
    <vivo:quadro id="indicadores" width="440" height="120" label="Indicadores Anatel" scroll="no">
            <table width="100%">
                <tr>
                    <td width="171" valign="top" align="center">
                        Indicadores Existentes<br>
                        <html:select name="FormEditaBaixa" property="arrayAdmIndicadorAnatelExistente" multiple="true" style="height:85px;width:171px;" styleClass="SELECT" size="6" onmouseover="ativarToolTip(this)">
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
                        <html:select name="FormEditaBaixa" property="arrayAdmIndicadorAnatelAssociado" multiple="true" style="height:85px;width:172px;" styleClass="SELECT" size="6" onmouseover="ativarToolTip(this)">
                            <html:options collection="AdmIndicadorAnatelAssociadoVO" property="idIndicador" labelProperty="nmIndicador" /> 
                        </html:select>
                    </td>
                </tr>
            </table>
    </vivo:quadro>
    <%}%>
    <table align="right" width="100%" height="20">
        <tr>
            <td align="right" valign="bottom">
            <acesso:controlHiddenItem nomeIdentificador="adm_eib_salvar">
                <img vspace="4" id="btSalvar1" onclick="gravar();" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_salvar_over.gif'" border="0" style="cursor:hand"/>
            </acesso:controlHiddenItem>
            </td>
        </tr>
    </table>
    </form>
        <SCRIPT FOR=window EVENT=onload  LANGUAGE="JScript">
            document.body.style.backgroundColor = '#ededed';
            ajustaTamanho(parent.document.getElementById('detalhe'));
            ajustaTamanho(parent.document.getElementById('detalhe'));
            ajustaTamanho(parent.document.getElementById('detalhe'));
            exibeAtual();
            inicializa();
            parent.oculta_div();
        </SCRIPT>
        <iframe style="width:0px;height:0px;" name="ifmInnerBrowserBaixa">
        <iframe id="DivShim" src="javascript:false;" scrolling="no" frameborder="0" style="position:absolute; top:0px; left:0px; display:none;"></iframe>
        <div id="PopupDiv" style="position:absolute;padding:2px; display:none;background-color:#000000; color:#ffffff; z-index:100;font-size:11px;"></div>
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>