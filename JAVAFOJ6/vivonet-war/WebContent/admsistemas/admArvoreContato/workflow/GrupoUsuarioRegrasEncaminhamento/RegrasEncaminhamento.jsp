<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<acesso:controlInitEnv/>

<bean:define id="Form"     name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="usuarioEncaminhamentoForm" />
<bean:define id="aGrupoVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="usuarioEncaminhamentoForm.AGrupoVO" />

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Vivo Net >> Administração de Sistemas"/>
    <netui-template:setAttribute name="modulo" value="Administração Sistemas"/>
    <netui-template:section name="headerSection">
    	<script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/utils.js"></script>
        <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/xtooltip.js"></script>
        <script language=javascript>
        
        function transfereSelecaoLista(listaOrigem, listaDestino) {
            var i;
            for(i = 0; i<listaOrigem.options.length; i++)
                if(listaOrigem.options[i].selected)
                    listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                    
            for(i = listaOrigem.options.length-1; i>=0; i--)
                if(listaOrigem.options[i].selected)
                    listaOrigem.options[i] = null;
    
            //sortOptions(listaDestino);
    
            return false;
        }
                
        function transfereSelecaoListaTodos(listaOrigem, listaDestino) {
            var i;
            for(i = 0; i<listaOrigem.options.length; i++)
                listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
            for(i = listaOrigem.options.length-1; i>=0; i--)
                listaOrigem.options[i] = null;
                
            //sortOptions(listaDestino);
            
            return false;
        }
        
        
        function gravar(){
        
                //Processa gravação
                var form = document.usuarioEncaminhamentoForm;
        
                var tot = 0;
                
                var lista1 = form.tipoLinhaAssociada;
                var lista2 = form.segmentacaoAssociada;
                var lista3 = form.carterizacaoAssociada;
                var lista4 = form.procedenciaAssociada;
                var lista5 = form.tipoClienteAssociada;
                var lista6 = form.tipoPessoaAssociada;
                var lista7 = form.tipoAberturaAssociada;
                var lista8 = form.canalAssociado;
                var lista9 = form.regionalAssociada;
                
                tot = lista1.length+lista2.length+lista3.length+lista4.length+
                lista5.length+lista6.length+lista7.length+lista8.length+lista9.length;
                
                // se tot == 0, todas as listas vazias, nao verificar lista por lista.
                // se tot > 0, no minimo uma selecao existe, verficar todas as listas.
                if (tot > 0) {
                    if (lista1.length == 0) {
                        alert("Selecione pelo menos um item para cada Lista!");
                        return false;
                    }
                    if (lista2.length == 0) {
                        alert("Selecione pelo menos um item para cada Lista!");
                        return false;
                    }
                    if (lista3.length == 0) {
                        alert("Selecione pelo menos um item para cada Lista!");
                        return false;
                    }
                    if (lista4.length == 0) {
                        alert("Selecione pelo menos um item para cada Lista!");
                        return false;
                    }
                    if (lista5.length == 0) {
                        alert("Selecione pelo menos um item para cada Lista!");
                        return false;
                    }
                    if (lista6.length == 0) {
                        alert("Selecione pelo menos um item para cada Lista!");
                        return false;
                    }
                    if (lista7.length == 0) {
                        alert("Selecione pelo menos um item para cada Lista!");
                        return false;
                    }
                    if (lista8.length == 0) {
                        alert("Selecione pelo menos um item para cada Lista!");
                        return false;
                    }
                    if (lista9.length == 0) {
                        alert("Selecione pelo menos um item para cada Lista!");
                        return false;
                    }
        
                }
        
                var campo = new Array(form.tipoLinhaDisponivel, form.tipoLinhaAssociada, 
                                      form.segmentacaoDisponivel, form.segmentacaoAssociada,
                                      form.carterizacaoDisponivel, form.carterizacaoAssociada,
                                      form.procedenciaDisponivel, form.procedenciaAssociada,
                                      form.tipoClienteDisponivel, form.tipoClienteAssociada,
                                      form.tipoPessoaDisponivel, form.tipoPessoaAssociada,
                                      form.tipoAberturaDisponivel, form.tipoAberturaAssociada,
                                      form.canalDisponivel, form.canalAssociado, form.regionalAssociada
                                      );
                             
                for(x = 0; x < campo.length; x++){
                    temp = campo[x];
                    for( y = 0; y < temp.options.length; y++ )
                        temp.options[y].selected = true;
                } 
        
                //Inicio animação
                if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                //Monta o path seleção
                document.forms["usuarioEncaminhamentoForm"].target = "iframe";
                document.forms["usuarioEncaminhamentoForm"].action = "AplicarRegrasEncaminhamento.do";
                document.forms["usuarioEncaminhamentoForm"].submit();
            }
        
	        function ObtemRegrasIFrame(grupo) {
	            
	            if(grupo.selectedIndex == '-1')
	                return;
	            
	            //Inicio animação
	            if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
	    
	            //Controle do click no tratamento
	            grupoInicio = 1;
	    
	            //Oculta o quadro tratamento
	            exibicao(1);
	            
	            //Obtém o nome da celula
	            var indexSelected = 0;
	            
	            for( indexSelected = 0; indexSelected < grupo.length; indexSelected++ )
	                if( grupo[indexSelected].selected ) break;
	            
	            //Monta o nome do grupo no quadro 
	            qdRegras.innerHTML   = "&nbsp;Regras ao Grupo [" + grupo[indexSelected].innerText + "]";
	            qdNVRegras.innerHTML = qdRegras.innerHTML;
	            qdRegras3.innerHTML = qdRegras.innerHTML;
	            
	            //Informa o código selecionado
	            document.forms["usuarioEncaminhamentoForm"].elements["codigoGrupo"].value = grupo.value;
	            
	            //Monta o path seleção
	            document.forms["usuarioEncaminhamentoForm"].target = "iframe";
	            document.forms["usuarioEncaminhamentoForm"].action = "ObtemRegrasEncaminhamento.do";
	            document.forms["usuarioEncaminhamentoForm"].submit();
	    }
    
    </script>	    
    
    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="adm_renc_verpagina">
        <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/utils.js"></script>
        <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/xtooltip.js"></script>
        <!--APLICAÇÃO->MENU-->
        <!-- Menu de Administração de Sistemas -->
        <logic:notEqual  name="Form" property="flgFrame" value="frame">
            <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        </logic:notEqual>
        <logic:equal  name="Form" property="flgFrame" value="frame">
            <script>
                document.getElementById("hdMinPrincipal").style.display = 'none';      
                document.getElementById("hdMaxPrincipal").style.display = 'none';      
            </script>
        </logic:equal>
        
        <!--APLICACAO-->
        <div><img width="1" height="5" src="/FrontOfficeWeb/resources/images/transp.gif"></div> 
        <vivo:sessao id="correspondenciadevolvida" height="470" width="790" label="Configurar Grupo de Usuário" operacoes="Regras de Encaminhamento" scroll="no">
        <html:form id="RegrasEncaminhamento" action="/admsistemas/admArvoreContato/workflow/GrupoUsuarioRegrasEncaminhamento/AplicarRegrasEncaminhamento.do">                        
            <html:hidden name="Form" property="codigoGrupo"/>
            <html:hidden name="Form" property="flgFrame"/>
            <div><img width="1" height="5" src="/FrontOfficeWeb/resources/images/transp.gif"></div>
            <vivo:abaGrupo id="btGruposNiveis" width="400px" height="10px" styleClass="abatexto">
            <acesso:controlHiddenItem nomeIdentificador="adm_renc_abagru">
                <vivo:abaItem id="btGrupos"   onclick="exibicao(0);" value="&nbsp;&nbsp;Grupos&nbsp;&nbsp;" select="S"/>
            </acesso:controlHiddenItem>
            <acesso:controlHiddenItem nomeIdentificador="adm_renc_abaniv">
                <vivo:abaItem id="btNiveis"   onclick="exibicao(1);" value="&nbsp;&nbsp;Regras Parte 1&nbsp;&nbsp;"/>
            </acesso:controlHiddenItem>
            <acesso:controlHiddenItem nomeIdentificador="adm_renc_abareg">
                <vivo:abaItem id="btNVRegras" onclick="exibicao(2);" value="&nbsp;&nbsp;Regras Parte 2&nbsp;&nbsp;"/>
            </acesso:controlHiddenItem>
                <vivo:abaItem id="btNVRegras2" onclick="exibicao(3);" value="&nbsp;&nbsp;Regras Parte 3&nbsp;&nbsp;"/>
            </vivo:abaGrupo>
            <table cellpadding="0" cellspacing="0" border="0" height="440" width="100%">
                <tr id="trGrupos" name="trGrupos">
                    <td>
                        <vivo:quadro id="qdGrupos" height="100%" width="100%" label="&nbsp;Grupos Disponíveis" scroll="n">
                            <html:select name="Form" property="grupoVOSelecionado.codigo" size="30"  onclick="ObtemRegrasIFrame(this);" style="width=350px">
                                <html:options collection="aGrupoVO" property="codigo" labelProperty="descricao" /> 
                            </html:select>
                            <table cellpadding="0" cellspacing="0" border="0" align="left">
                            <tr>
                                <td colspan=3 style="height=5px;"></td>
                            </tr>
                            <tr>
                                <td align="center">
                                    <logic:notEqual  name="Form" property="flgFrame" value="frame">
                                        <img hspace="10" vspace="6" id="btVoltar" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_voltar_over.gif" onClick="window.location.href='/FrontOfficeWeb/index.jsp'; return false" style="border:none;"/>
                                    </logic:notEqual>
                                </td>                                            
                            </tr>
                            </table>  
                        </vivo:quadro>
                    </td>
                </tr>
                <tr id="trRegras" name="trRegras">
                    <td>
                        <vivo:quadro id="qdRegras" height="100%" width="100%" label="&nbsp;Regras ao Grupo" scroll="n">
                            <div><img width="1" height="5" src="/FrontOfficeWeb/resources/images/transp.gif"></div>
                            <table width="100%" align="center" cellpadding="0" cellspacing="0" border="0">
                                <tr>
                                    <td width="49%" align="center">
                                        <vivo:quadro id="qdTipoLinha" height="170" width="370" label="Tipo Linha" scroll="n">
                                            <table  width="100%" height="100%" align="center" border="0">
                                                <tr>
                                                    <td>
                                                        Disponível<br/>
                                                        <html:select name="Form" property="tipoLinhaDisponivel" multiple="true" onmouseover="ativarToolTip(this)" onmouseout="desativarToolTip();" size="10" style="width=155px" ondblclick="btTipoLinha01.click();"/>
                                                    </td>
                                                    <td>
                                                        <vivo:botao id="btTipoLinha01" width="25px" height="20px" value=">" styleClass="btTemplate" onclick="transfereSelecaoLista(document.usuarioEncaminhamentoForm.tipoLinhaDisponivel, document.usuarioEncaminhamentoForm.tipoLinhaAssociada);return false;"/>
                                                        <vivo:botao id="btTipoLinha02" width="25px" height="20px" value=">>" styleClass="btTemplate" onclick="transfereSelecaoListaTodos(document.usuarioEncaminhamentoForm.tipoLinhaDisponivel, document.usuarioEncaminhamentoForm.tipoLinhaAssociada);return false;"/>
                                                        <vivo:botao id="btTipoLinha03" width="25px" height="20px" value="<" styleClass="btTemplate" onclick="transfereSelecaoLista(document.usuarioEncaminhamentoForm.tipoLinhaAssociada, document.usuarioEncaminhamentoForm.tipoLinhaDisponivel);return false;"/>
                                                        <vivo:botao id="btTipoLinha04" width="25px" height="20px" value="<<" styleClass="btTemplate" onclick="transfereSelecaoListaTodos(document.usuarioEncaminhamentoForm.tipoLinhaAssociada, document.usuarioEncaminhamentoForm.tipoLinhaDisponivel);return false;"/>                                                
                                                    </td>
                                                    <td>
                                                        Associada<br/>
                                                        <html:select name="Form" property="tipoLinhaAssociada" multiple="true"  onmouseover="ativarToolTip(this)" onmouseout="desativarToolTip();" size="10" style="width=155px" ondblclick="btTipoLinha03.click();"/>
                                                    </td>
                                                </tr>
                                            </table>
                                        </vivo:quadro>
                                    </td>
                                    <td width="2%">&nbsp;</td>
                                    <td width="49%" align="center">
                                        <vivo:quadro id="qdSegmentacao" height="170" width="370" label="Segmentação" scroll="n">
                                            <table  width="100%" height="100%" align="center" border="0">
                                                <tr>
                                                    <td>
                                                        Disponivel<br>
                                                        <html:select name="Form" property="segmentacaoDisponivel" onmouseover="ativarToolTip(this)" onmouseout="desativarToolTip();" multiple="true" size="10" style="width=155px" ondblclick="btSegmentacao01.click();"/>
                                                    </td>
                                                    <td>
                                                        <vivo:botao id="btSegmentacao01" width="25px" height="20px" value=">" styleClass="btTemplate" onclick="transfereSelecaoLista(document.usuarioEncaminhamentoForm.segmentacaoDisponivel, document.usuarioEncaminhamentoForm.segmentacaoAssociada);return false;"/>
                                                        <vivo:botao id="btSegmentacao02" width="25px" height="20px" value=">>" styleClass="btTemplate" onclick="transfereSelecaoListaTodos(document.usuarioEncaminhamentoForm.segmentacaoDisponivel, document.usuarioEncaminhamentoForm.segmentacaoAssociada);return false;"/>
                                                        <vivo:botao id="btSegmentacao03" width="25px" height="20px" value="<" styleClass="btTemplate" onclick="transfereSelecaoLista(document.usuarioEncaminhamentoForm.segmentacaoAssociada, document.usuarioEncaminhamentoForm.segmentacaoDisponivel);return false;"/>
                                                        <vivo:botao id="btSegmentacao04" width="25px" height="20px" value="<<" styleClass="btTemplate" onclick="transfereSelecaoListaTodos(document.usuarioEncaminhamentoForm.segmentacaoAssociada, document.usuarioEncaminhamentoForm.segmentacaoDisponivel);return false;"/>                                                                                            
                                                    </td>
                                                    <td>
                                                        Associada<br>                    
                                                        <html:select name="Form" property="segmentacaoAssociada" onmouseover="ativarToolTip(this)" onmouseout="desativarToolTip();" multiple="true" size="10" style="width=155px" ondblclick="btSegmentacao03.click();"/>
                                                    </td>
                                                </tr>
                                            </table>
                                        </vivo:quadro>
                                    </td>
                                </tr>
                                <tr>
                                    <td ><div><img width="1" height="5" src="/FrontOfficeWeb/resources/images/transp.gif"></div></td>
                                </tr>
                                <tr>
                                    <td align="center">
                                        <vivo:quadro id="qdCarterizacao" height="170" width="370" label="Carteirização" scroll="n">
                                            <table  width="100%" height="100%" align="center" border="0">
                                                <tr>
                                                    <td>
                                                        Disponivel<br>
                                                        <html:select name="Form" property="carterizacaoDisponivel" onmouseover="ativarToolTip(this)" onmouseout="desativarToolTip();" multiple="true" size="10" style="width=155px" ondblclick="btCarterizacao01.click();"/>
                                                    </td>
                                                    <td>
                                                        <vivo:botao id="btCarterizacao01" width="25px" height="20px" value=">" styleClass="btTemplate" onclick="transfereSelecaoLista(document.usuarioEncaminhamentoForm.carterizacaoDisponivel, document.usuarioEncaminhamentoForm.carterizacaoAssociada);return false;"/>
                                                        <vivo:botao id="btCarterizacao02" width="25px" height="20px" value=">>" styleClass="btTemplate" onclick="transfereSelecaoListaTodos(document.usuarioEncaminhamentoForm.carterizacaoDisponivel, document.usuarioEncaminhamentoForm.carterizacaoAssociada);return false;"/>
                                                        <vivo:botao id="btCarterizacao03" width="25px" height="20px" value="<" styleClass="btTemplate" onclick="transfereSelecaoLista(document.usuarioEncaminhamentoForm.carterizacaoAssociada, document.usuarioEncaminhamentoForm.carterizacaoDisponivel);return false;"/>
                                                        <vivo:botao id="btCarterizacao04" width="25px" height="20px" value="<<" styleClass="btTemplate" onclick="transfereSelecaoListaTodos(document.usuarioEncaminhamentoForm.carterizacaoAssociada, document.usuarioEncaminhamentoForm.carterizacaoDisponivel);return false;"/>                                                
                                                    </td>
                                                    <td>
                                                        Associada<br>                    
                                                        <html:select name="Form" property="carterizacaoAssociada" onmouseover="ativarToolTip(this)" onmouseout="desativarToolTip();" multiple="true" size="10" style="width=155px" ondblclick="btCarterizacao03.click();"/>
                                                    </td>
                                                </tr>
                                            </table>
                                        </vivo:quadro>
                                    </td>
                                    <td>&nbsp;</td>
                                    <td align="center">
                                        <vivo:quadro id="qdProcedencia" height="170" width="370" label="Procedência" scroll="n">
                                            <table  width="100%" height="100%" align="center" border="0">
                                                <tr>
                                                    <td>
                                                        Disponivel<br>
                                                        <html:select name="Form" property="procedenciaDisponivel" onmouseover="ativarToolTip(this)" onmouseout="desativarToolTip();" multiple="true" size="10" style="width=155px" ondblclick="btProcedenci01.click();"/>
                                                    </td>
                                                    <td>
                                                        <vivo:botao id="btProcedenci01" width="25px" height="20px" value=">" styleClass="btTemplate" onclick="transfereSelecaoLista(document.usuarioEncaminhamentoForm.procedenciaDisponivel, document.usuarioEncaminhamentoForm.procedenciaAssociada);return false;"/>
                                                        <vivo:botao id="btProcedenci02" width="25px" height="20px" value=">>" styleClass="btTemplate" onclick="transfereSelecaoListaTodos(document.usuarioEncaminhamentoForm.procedenciaDisponivel, document.usuarioEncaminhamentoForm.procedenciaAssociada);return false;"/>
                                                        <vivo:botao id="btProcedenci03" width="25px" height="20px" value="<" styleClass="btTemplate" onclick="transfereSelecaoLista(document.usuarioEncaminhamentoForm.procedenciaAssociada, document.usuarioEncaminhamentoForm.procedenciaDisponivel);return false;"/>
                                                        <vivo:botao id="btProcedenci04" width="25px" height="20px" value="<<" styleClass="btTemplate" onclick="transfereSelecaoListaTodos(document.usuarioEncaminhamentoForm.procedenciaAssociada, document.usuarioEncaminhamentoForm.procedenciaDisponivel);return false;"/>                                                
                                                    </td>
                                                    <td>
                                                        Associada<br>                    
                                                        <html:select name="Form" property="procedenciaAssociada" onmouseover="ativarToolTip(this)" onmouseout="desativarToolTip();" multiple="true" size="10" style="width=155px" ondblclick="btProcedenci03.click();"/>
                                                    </td>
                                                </tr>
                                            </table>
                                        </vivo:quadro>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan=3 style="height=5px;"></td>
                                </tr>
                                <tr>
                                    <td align="left">
                                        <logic:notEqual  name="Form" property="flgFrame" value="frame">
                                            <img hspace="10" vspace="6" id="btVoltar" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_voltar_over.gif" onClick="window.location.href='/FrontOfficeWeb/index.jsp'; return false" style="border:none;"/>
                                        </logic:notEqual>

                                    </td>                                            
                                    <td align="right" colspan=2>
                                        <acesso:controlHiddenItem nomeIdentificador="adm_renc_gravar">
                                        <img vspace="3" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_gravar_over.gif" style="border:none;" onClick="gravar(); return false"/>&nbsp;
                                        </acesso:controlHiddenItem>
                                    </td>
                                </tr>
                            </table>
                        </vivo:quadro>
                    </td>
                </tr>
                <tr id="trNVRegras" name="trNVRegras">
                    <td>
                        <vivo:quadro id="qdNVRegras" height="100%" width="100%" label="&nbsp;Regras ao Grupo" scroll="n">
                           <div><img width="1" height="5" src="/FrontOfficeWeb/resources/images/transp.gif"></div> 
                            <table width="100%" align="center" cellpadding="0" cellspacing="0" border="0">
                                <tr>
                                    <td width="49%" align="center">
                                        <vivo:quadro id="qdTipoContato" height="170" width="370" label="Natureza" scroll="n">
                                            <table  width="100%" height="100%" align="center" border="0">
                                                <tr>
                                                    <td>
                                                        Disponível<br/>
                                                        <html:select name="Form" property="tipoClienteDisponivel" onmouseover="ativarToolTip(this)" onmouseout="desativarToolTip();" multiple="true" size="10" style="width=155px" ondblclick="btTipoCliente01.click();"/>
                                                    </td>
                                                    <td>
                                                        <vivo:botao id="btTipoCliente01" width="25px" height="20px" value=">" styleClass="btTemplate" onclick="transfereSelecaoLista(document.usuarioEncaminhamentoForm.tipoClienteDisponivel, document.usuarioEncaminhamentoForm.tipoClienteAssociada);return false;"/>
                                                        <vivo:botao id="btTipoCliente02" width="25px" height="20px" value=">>" styleClass="btTemplate" onclick="transfereSelecaoListaTodos(document.usuarioEncaminhamentoForm.tipoClienteDisponivel, document.usuarioEncaminhamentoForm.tipoClienteAssociada);return false;"/>
                                                        <vivo:botao id="btTipoCliente03" width="25px" height="20px" value="<" styleClass="btTemplate" onclick="transfereSelecaoLista(document.usuarioEncaminhamentoForm.tipoClienteAssociada, document.usuarioEncaminhamentoForm.tipoClienteDisponivel);return false;"/>
                                                        <vivo:botao id="btTipoCliente04" width="25px" height="20px" value="<<" styleClass="btTemplate" onclick="transfereSelecaoListaTodos(document.usuarioEncaminhamentoForm.tipoClienteAssociada, document.usuarioEncaminhamentoForm.tipoClienteDisponivel);return false;"/>                                                
                                                    </td>
                                                    <td>
                                                        Associada<br/>
                                                        <html:select name="Form" property="tipoClienteAssociada" onmouseover="ativarToolTip(this)" onmouseout="desativarToolTip();" multiple="true" size="10" style="width=155px" ondblclick="btTipoCliente03.click();"/>
                                                    </td>
                                                </tr>
                                            </table>
                                        </vivo:quadro>
                                    </td>
                                    <td width="2%">&nbsp;</td>
                                    <td width="49%" align="center">
                                        <vivo:quadro id="qdTipoPessoa" height="170" width="370" label="Tipo Cliente" scroll="n">
                                            <table  width="100%" height="100%" align="center" border="0" >
                                                <tr>
                                                    <td>
                                                        Disponivel<br>
                                                        <html:select name="Form" property="tipoPessoaDisponivel" onmouseover="ativarToolTip(this)" onmouseout="desativarToolTip();" multiple="true" size="10" style="width=155px" ondblclick="btTipoPessoa01.click();"/>
                                                    </td>
                                                    <td>
                                                        <vivo:botao id="btTipoPessoa01" width="25px" height="20px" value=">" styleClass="btTemplate" onclick="transfereSelecaoLista(document.usuarioEncaminhamentoForm.tipoPessoaDisponivel, document.usuarioEncaminhamentoForm.tipoPessoaAssociada);return false;"/>
                                                        <vivo:botao id="btTipoPessoa02" width="25px" height="20px" value=">>" styleClass="btTemplate" onclick="transfereSelecaoListaTodos(document.usuarioEncaminhamentoForm.tipoPessoaDisponivel, document.usuarioEncaminhamentoForm.tipoPessoaAssociada);return false;"/>
                                                        <vivo:botao id="btTipoPessoa03" width="25px" height="20px" value="<" styleClass="btTemplate" onclick="transfereSelecaoLista(document.usuarioEncaminhamentoForm.tipoPessoaAssociada, document.usuarioEncaminhamentoForm.tipoPessoaDisponivel);return false;"/>
                                                        <vivo:botao id="btTipoPessoa04" width="25px" height="20px" value="<<" styleClass="btTemplate" onclick="transfereSelecaoListaTodos(document.usuarioEncaminhamentoForm.tipoPessoaAssociada, document.usuarioEncaminhamentoForm.tipoPessoaDisponivel);return false;"/>                                                                                            
                                                    </td>
                                                    <td>
                                                        Associada<br>                    
                                                        <html:select name="Form" property="tipoPessoaAssociada" onmouseover="ativarToolTip(this)" onmouseout="desativarToolTip();" multiple="true" size="10" style="width=155px" ondblclick="btTipoPessoa03.click();"/>
                                                    </td>
                                                </tr>
                                            </table>
                                        </vivo:quadro>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan=3 style="height=5px;"></td>
                                </tr>
                                <tr>
                                    <td align="center">
                                        <vivo:quadro id="qdGrupoAbertura" height="170" width="370" label="Grupo de Abertura" scroll="n">
                                            <table  width="100%" height="100%" align="center" border="0" >
                                                <tr>
                                                    <td>
                                                        Disponível<br/>
                                                        <html:select name="Form" property="tipoAberturaDisponivel" onmouseover="ativarToolTip(this)" onmouseout="desativarToolTip();" multiple="true" size="10" style="width=155px" ondblclick="btTipoAbertura01.click();"/>
                                                    </td>
                                                    <td>
                                                        <vivo:botao id="btTipoAbertura01" width="25px" height="20px" value=">" styleClass="btTemplate" onclick="transfereSelecaoLista(document.usuarioEncaminhamentoForm.tipoAberturaDisponivel, document.usuarioEncaminhamentoForm.tipoAberturaAssociada);return false;"/>
                                                        <vivo:botao id="btTipoAbertura02" width="25px" height="20px" value=">>" styleClass="btTemplate" onclick="transfereSelecaoListaTodos(document.usuarioEncaminhamentoForm.tipoAberturaDisponivel, document.usuarioEncaminhamentoForm.tipoAberturaAssociada);return false;"/>
                                                        <vivo:botao id="btTipoAbertura03" width="25px" height="20px" value="<" styleClass="btTemplate" onclick="transfereSelecaoLista(document.usuarioEncaminhamentoForm.tipoAberturaAssociada, document.usuarioEncaminhamentoForm.tipoAberturaDisponivel);return false;"/>
                                                        <vivo:botao id="btTipoAbertura04" width="25px" height="20px" value="<<" styleClass="btTemplate" onclick="transfereSelecaoListaTodos(document.usuarioEncaminhamentoForm.tipoAberturaAssociada, document.usuarioEncaminhamentoForm.tipoAberturaDisponivel);return false;"/>                                                
                                                    </td>
                                                    <td>
                                                        Associada<br/>
                                                        <html:select name="Form" property="tipoAberturaAssociada" onmouseover="ativarToolTip(this)" onmouseout="desativarToolTip();" multiple="true" size="10" style="width=155px" ondblclick="btTipoAbertura03.click();"/>
                                                    </td>
                                                </tr>
                                            </table>
                                        </vivo:quadro>
                                    </td>
                                    <td>&nbsp;</td>
                                    <td align="center">
                                        <vivo:quadro id="qdCanalGrupo" height="170" width="370" label="Canal" scroll="n">
                                            <table  width="100%" height="100%" align="center" border="0">
                                                <tr>
                                                    <td>
                                                        Disponivel<br>
                                                        <html:select name="Form" property="canalDisponivel" onmouseover="ativarToolTip(this)" onmouseout="desativarToolTip();" multiple="true" size="10" style="width=155px" ondblclick="btCanal01.click();"/>
                                                    </td>
                                                    <td>
                                                        <vivo:botao id="btCanal01" width="25px" height="20px" value=">" styleClass="btTemplate"  onclick="transfereSelecaoLista(     document.usuarioEncaminhamentoForm.canalDisponivel, document.usuarioEncaminhamentoForm.canalAssociado );return false;"/>
                                                        <vivo:botao id="btCanal02" width="25px" height="20px" value=">>" styleClass="btTemplate" onclick="transfereSelecaoListaTodos(document.usuarioEncaminhamentoForm.canalDisponivel, document.usuarioEncaminhamentoForm.canalAssociado );return false;"/>
                                                        <vivo:botao id="btCanal03" width="25px" height="20px" value="<" styleClass="btTemplate"  onclick="transfereSelecaoLista(     document.usuarioEncaminhamentoForm.canalAssociado,  document.usuarioEncaminhamentoForm.canalDisponivel);return false;"/>
                                                        <vivo:botao id="btCanal04" width="25px" height="20px" value="<<" styleClass="btTemplate" onclick="transfereSelecaoListaTodos(document.usuarioEncaminhamentoForm.canalAssociado,  document.usuarioEncaminhamentoForm.canalDisponivel);return false;"/>                                                                                            
                                                    </td>
                                                    <td>
                                                        Associada<br>                    
                                                        <html:select name="Form" property="canalAssociado" onmouseover="ativarToolTip(this)" onmouseout="desativarToolTip();" multiple="true" size="10" style="width=155px" ondblclick="btCanal03.click();"/>
                                                    </td>
                                                </tr>
                                            </table>
                                        </vivo:quadro>
                                    </td>                                            
                                </tr>
                                <tr>
                                    <td colspan=3 style="height=5px;"></td>
                                </tr>
                                <tr>
                                    <td align="left">
                                    <logic:notEqual  name="Form" property="flgFrame" value="frame">
                                        <img hspace="10" vspace="6" id="btVoltar" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_voltar_over.gif" onClick="window.location.href='/FrontOfficeWeb/index.jsp'; return false" style="border:none;"/>
                                    </logic:notEqual>
                                    </td>                                            
                                    <td align="right" colspan=2>
                                        <acesso:controlHiddenItem nomeIdentificador="adm_renc_gravar">
                                        <img vspace="3" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_gravar_over.gif" style="border:none;" onClick="gravar(); return false"/>&nbsp;
                                        </acesso:controlHiddenItem>
                                    </td>
                                </tr>
                            </table>
                        </vivo:quadro>
                    </td>
                </tr>
                <tr id="trNVRegras3" style="display:none" valign="top">
                    <td>
                        <vivo:quadro id="qdRegras3" height="100%" width="100%" label="&nbsp;Regras ao Grupo" scroll="n">
                            <div><img width="1" height="5" src="/FrontOfficeWeb/resources/images/transp.gif"></div>
                            <table width="100%" align="center" cellpadding="0" cellspacing="0" border="0">
                                <tr>
                                    <td width="49%" align="left">
                                        <vivo:quadro id="qdRegional" height="170" width="370" label="Regional" scroll="n">
                                            <table  width="100%" height="100%" align="center" border="0">
                                                <tr>
                                                    <td>
                                                        Disponivel<br>
                                                        <html:select name="Form" property="regionalDisponivel" onmouseover="ativarToolTip(this)" onmouseout="desativarToolTip();" multiple="true" size="10" style="width=155px" ondblclick="btRegional01.click();"/>
                                                    </td>
                                                    <td>
                                                        <vivo:botao id="btRegional01" width="25px" height="20px" value=">" styleClass="btTemplate" onclick="transfereSelecaoLista(document.usuarioEncaminhamentoForm.regionalDisponivel, document.usuarioEncaminhamentoForm.regionalAssociada);return false;"/>
                                                        <vivo:botao id="btRegional02" width="25px" height="20px" value=">>" styleClass="btTemplate" onclick="transfereSelecaoListaTodos(document.usuarioEncaminhamentoForm.regionalDisponivel, document.usuarioEncaminhamentoForm.regionalAssociada);return false;"/>
                                                        <vivo:botao id="btRegional03" width="25px" height="20px" value="<" styleClass="btTemplate" onclick="transfereSelecaoLista(document.usuarioEncaminhamentoForm.regionalAssociada, document.usuarioEncaminhamentoForm.regionalDisponivel);return false;"/>
                                                        <vivo:botao id="btRegional04" width="25px" height="20px" value="<<" styleClass="btTemplate" onclick="transfereSelecaoListaTodos(document.usuarioEncaminhamentoForm.regionalAssociada, document.usuarioEncaminhamentoForm.regionalDisponivel);return false;"/>                                                                                            
                                                    </td>
                                                    <td>
                                                        Associada<br>                    
                                                        <html:select name="Form" property="regionalAssociada" onmouseover="ativarToolTip(this)" onmouseout="desativarToolTip();" multiple="true" size="10" style="width=155px" ondblclick="btRegional03.click();"/>
                                                    </td>
                                                </tr>
                                            </table>
                                        </vivo:quadro>
                                    </td>
                                </tr>
                                <tr>
                                <td align="left">
                                    <logic:notEqual  name="Form" property="flgFrame" value="frame">
                                        <img hspace="10" vspace="6" id="btVoltar" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_voltar_over.gif" onClick="window.location.href='/FrontOfficeWeb/index.jsp'; return false" style="border:none;"/>
                                    </logic:notEqual>
                                    </td> 
                                    <td valign="bottom" align="right" colspan=2>
                                    <acesso:controlHiddenItem nomeIdentificador="adm_renc_gravar">
                                    <img vspace="3" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_gravar_over.gif" style="border:none;" onClick="gravar(); return false"/>&nbsp;
                                    </acesso:controlHiddenItem>
                                    </td>
                                </tr>
                            </table>
                        </vivo:quadro>
                    </td>
                </tr>
            </table>
            <iframe scrolling="yes" style="visibility:hidden;" name="iframe" height="1px" width="1px"></iframe>
        </html:form>
        </vivo:sessao>
         <script language=javascript>   
            
            //Controle no click em tratamento
            var grupoInicio;
            
            //Monta a exibição de tratamento
            exibicao(0);
            
            //Indicação de aguardando click em tratamento
            grupoInicio = 0;
            
            //Controle da exibição
            function exibicao(index) {
                if( index == 0) {
                    abaSelected(btGruposNiveis, btGrupos);
                    
                    trGrupos.style.display   = '';
                    trRegras.style.display   = 'none';
                    trNVRegras.style.display = 'none';
                    trNVRegras3.style.display = 'none';
        
                } else if( index == 1) {
                    if( grupoInicio == 0 ) return;
        
                    abaSelected(btGruposNiveis, btNiveis);
                    
                    trGrupos.style.display   = 'none';
                    trRegras.style.display   = '';
                    trNVRegras.style.display = 'none';
                    trNVRegras3.style.display = 'none';
                    
        
                } else if( index == 2) {
                    if( grupoInicio == 0 ) return;
        
                    abaSelected(btGruposNiveis, btNVRegras);
                    
                    trGrupos.style.display   = 'none';
                    trRegras.style.display   = 'none';
                    trNVRegras.style.display = '';
                    trNVRegras3.style.display = 'none';
                    
                    
                }else if(index == 3)
                {
                    if( grupoInicio == 0 ) return;
        
                    abaSelected(btGruposNiveis, btNVRegras2);
                    
                    trGrupos.style.display   = 'none';
                    trRegras.style.display   = 'none';
                    trNVRegras.style.display = 'none';
                    trNVRegras3.style.display = '';
                
                }
         }
        //Fim animação
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
    
        <logic:equal  name="Form" property="flgFrame" value="frame">
            parent.oculta_div();
        </logic:equal>
            
        </script>
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>