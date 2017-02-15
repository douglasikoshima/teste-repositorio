<%@page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<bean:define id="FormEditaBaixa"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formEditaBaixa"/>
<bean:define id="AdmIndicadorAnatelExistenteVO"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formEditaBaixa.admIndicadorAnatelExistenteVO"/>
<bean:define id="AdmIndicadorAnatelAssociadoVO"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formEditaBaixa.admIndicadorAnatelAssociadoVO"/>


<%String[] idTipoComunicacao = (String[])session.getAttribute("idTipoComunicacao");%>
<%String[] dsTipoComunicacao = (String[])session.getAttribute("dsTipoComunicacao");%>
<%String[] idTipoComunicacaoLista = (String[])session.getAttribute("idTipoComunicacaoLista");%>
<%String[] dsTipoComunicacaoLista = (String[])session.getAttribute("dsTipoComunicacaoLista");%>
<%String[] idBaixaMensagem = (String[])session.getAttribute("idBaixaMensagem");%>
<%String[] dsMensagem = (String[])session.getAttribute("dsMensagem");%>
<%boolean folder;%>
    <%if (dsMensagem.length>0) {%>
        <%folder=false;%>
    <%}else {%>
        <%folder=true;%>
    <%}%>        
<netui-template:template templatePage="/resources/jsp/templateUsuarioClean.jsp">
    <netui-template:section name="headerSection">
    <script language="Javascript">
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
                    a.style.visibility = 'visible'; 
            }
    
            function ocultaCampos<%=i%>(){
                var a = document.getElementById("divx<%=i%>");
                a.style.visibility = 'hidden';
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
            if(document.forms[0].nmBaixa.value==''){
                alert('Escolha um tipo.');
                return;
            }
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
                        document.forms[0].submit(); 
                    <%}%>                
             <%}%>
        }
        
        function gravar(){
            var lista = document.forms[0].arrayAdmIndicadorAnatelAssociado;
            //Processa gravação
            for(i = 0;i<lista.length;i++){
               document.forms[0].arrayAdmIndicadorAnatelAssociado.options[i].selected =true; 
            }
            document.forms[0].action='salvaItemEditado.do';
            document.forms[0].target = "_parent";
            document.forms[0].submit(); 
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
    </script>    
    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="adm_detbaix_verpagina">
    <vivo:quadro id="descricao" width="438" height="90" label="Descriç&atilde;o" scroll="no">
        <table width="100%" border="0" cellspacing="1" cellpadding="5">
           <tr>
                <td class="tblEstatica_campoNome" width="25%">Caminho:</td>
                <td width="75%"><bean:write name="FormEditaBaixa" property="dsPath"/></td>
            </tr>
            <tr>
                <td class="tblEstatica_campoNome" width="20%">Nome do Item:</td>
                <td width="80%"><bean:write name="FormEditaBaixa" property="nmBaixa"/></td>
            </tr>
         </table>
    </vivo:quadro>
    <%if (!folder){%>
    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
        <%try{%>
        <%if(idTipoComunicacaoLista.length>0){%>
            <vivo:quadro id="descricao" width="440" height="250" label="Mensagens">
                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="1"></div>
                <table width="400" border="0" cellspacing="1" cellpadding="1">
                    <%if(idTipoComunicacao.length>0){%>
                        <%for(int i=0;i<idTipoComunicacao.length;i++){%>
                            <tr>
                                <td class="tblEstatica_campoNome" width="35%">
                                    <%=dsTipoComunicacao[i]%>
                                </td>
                                <td width="65%" align="left">
                                        <%=dsMensagem[i]%>
                                </td>
                            </tr>                                       
                        <%}%>
                        <%for(int i=idTipoComunicacao.length;i<idTipoComunicacaoLista.length;i++){%>
                            <%if(!idTipoComunicacaoLista[i].equals(ConstantesCRM.SVAZIO)){%>      
                                <tr>
                                    <td class="tblEstatica_campoNome" width="35%">
                                        <%=dsTipoComunicacaoLista[i]%>
                                    </td>
                                    <td class="tblEstatica_campoNome" width="65%">
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
    <vivo:quadro id="indicadores" width="440" height="120" label="Indicadores Anatel Associados">
            <table width="100%">
                <tr>
                    <td width="65%">
                        <logic:iterate name="FormEditaBaixa" property="admIndicadorAnatelAssociadoVO" id="itemInd">
                           <bean:write name="itemInd" property="nmIndicador"/><br>
                        </logic:iterate>
                    </td>
                </tr>
            </table>
    </vivo:quadro>
    <%}%>
        <SCRIPT FOR=window EVENT=onload  LANGUAGE="JScript">
            document.body.style.backgroundColor = '#ededed';
            ajustaTamanho(parent.document.getElementById('detalhe'));
            ajustaTamanho(parent.document.getElementById('detalhe'));
            ajustaTamanho(parent.document.getElementById('detalhe'));
            parent.oculta_div();
        </SCRIPT>
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>