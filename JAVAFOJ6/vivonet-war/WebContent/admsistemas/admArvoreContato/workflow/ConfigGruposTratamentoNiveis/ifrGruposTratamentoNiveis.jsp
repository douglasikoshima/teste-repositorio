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

<bean:define id="form"          name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="gruposTratamentoNiveisForm" />
<bean:define id="niveisArray"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="gruposTratamentoNiveisForm.niveis" />
<bean:define id="grpTratamento" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="gruposTratamentoNiveisForm.gruposTratamentoNiveisVO" />

<link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>

<!--APLICACAO-->
<html>
  <head>
    <script language="Javascript">
        function lupa()
        {   
            parent.parent.divLupa.style.display = '';
            document.forms[0].target = "ifrmLupa";            
            document.forms[0].action = 'ConsultaGrupoAssociados.do?idContato=<%=request.getParameter("idContato")%>';
            parent.parent.mostrar_div();
            document.forms[0].submit();
            //parent.parent.ifrmLupa.src = 'GrupoTratamentoRegraNiveis.jsp';
        
        }
    </script>
    <title>Vivo Net - Aviso / Erro</title>
    <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">        
  </head>
  <body onload="top.frameApp.oculta_div()" style="background-color:#E3ECF4;">
  <acesso:controlHiddenItem nomeIdentificador="adm_igtni_verpagina">
    <form action="AplicaGruposTratamentoNiveis.do" id="AplicaGruposTratamentoNiveis" name="AplicaGruposTratamentoNiveis" method="post">
    <table cellpadding="0" cellspacing="0" border="0" width="100%" height="100%">
        <tr>
            <td align="left" valign="top">
        <html:hidden name="form" property="grupoSelecionado" />
        <html:hidden name="form" property="numNiveisRetorno" />
        <input type="hidden" name="idContato" value="<%=request.getParameter("idContato")%>">
        <table border="0" width="100%">
            <tr>
                <td align="left">
                    Níveis:
                    <html:select name="form" property="nivelSelecionado" title="nivelSelecionado" onchange="atualizaNiveis(this)">
                        <html:options collection="niveisArray" property="codigo" labelProperty="descricao" /> 
                    </html:select>
                </td>
                <td align="right">
                  Visualizar Regra de Encaminhamento
                    <img style="border:none;cursor:hand" id="lupaNiveis" onclick="lupa();" border="0" src="/FrontOfficeWeb/resources/images/bcliente_lupa_segcart.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bcliente_lupa_segcart.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bcliente_lupa_segcart.gif'" />
                </td>
            </tr>
        </table>
        
        <logic:iterate name="grpTratamento" property="grupoTratamentoArray" id="GrupoTratamentoVO" indexId="ctr" >
            <bean:define name="GrupoTratamentoVO" property="nivel"       id="nivel" />
            <bean:define name="GrupoTratamentoVO" property="disponiveis" id="disponiveis" />
            <bean:define name="GrupoTratamentoVO" property="sequencia"   id="sequencia" />
            <%
                int n = ctr.intValue() + 1;
            %>
            <vivo:quadro id="quadroNivel" height="165" width="680" label="<%=\"Nível:&nbsp;\" + n%>" scroll="n"> 
                <table border="0">
                    <tr>
                        <td>
                            <!-- Disponíveis -->
                            Disponível<br/>
                            <html:select name="form" property="disponiveis" multiple="true" size="10" style="width=300px" ondblclick="<%= \"executaBotao('>', \" + ctr + \");\"%>">
                                <logic:iterate name="disponiveis" property="grupoVOArray" id="aGrupoVODisp">
                                    <bean:define name="aGrupoVODisp" property="codigo" id="codigo" />
                                    <option value="<%=ctr.toString() + codigo%>"><bean:write name="aGrupoVODisp" property="descricao"/></option>
                                </logic:iterate>
                            </html:select>
                        </td>
                        <td>
                            <vivo:botao id="bt01" width="25px" height="10px" value=">"  styleClass="btTemplate" onclick="<%= \"executaBotao('>', \" + ctr + \");\"%>" />
                            <vivo:botao id="bt02" width="25px" height="10px" value=">>" styleClass="btTemplate" onclick="<%= \"executaBotao('>>', \" + ctr + \");\"%>" />
                            <vivo:botao id="bt03" width="25px" height="10px" value="<"  styleClass="btTemplate" onclick="<%= \"executaBotao('<', \" + ctr + \");\"%>" />
                            <vivo:botao id="bt04" width="25px" height="10px" value="<<" styleClass="btTemplate" onclick="<%= \"executaBotao('<<', \" + ctr + \");\"%>" />
                        </td>
                        <td>
                            <!-- Sequencia -->
                            Seqüência<br/>
                            <html:select name="form" property="sequencia" multiple="true" size="10" style="width=300px" ondblclick="<%= \"executaBotao('<', \" + ctr + \");\"%>">
                                <logic:iterate name="sequencia" property="grupoVOArray" id="aGrupoVOSeq">
                                    <bean:define name="aGrupoVOSeq" property="codigo" id="codigo" />
                                    <option value="<%=ctr.toString() + codigo%>"><bean:write name="aGrupoVOSeq" property="descricao"/></option>
                                </logic:iterate>
                            </html:select>
                        </td>
                        <td align="center">
                            <vivo:botao id="bt01" width="25px" height="10px" value="+"  styleClass="btTemplate" onclick='<%="grupoAcima("+ctr+")"%>'/>
                            <vivo:botao id="bt02" width="25px" height="10px" value="-" styleClass="btTemplate" onclick='<%="grupoAbaixo("+ctr+")"%>' />
                        </td>                        
                    </tr>
                </table>
            </vivo:quadro>        
            <br>                    
        </logic:iterate>
                </td>
            </tr>
            <tr>
                <td align="right" height="12px">
        <acesso:controlHiddenItem nomeIdentificador="adm_igtni_aplicar">
            <img vspace="3" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_gravar_over.gif" style="border:none;" onClick="Submit(); return false"/>
        </acesso:controlHiddenItem>
                </td>
            </tr>
        </table>
    </form>
    
    <script language="JavaScript">
    
        function grupoAcima(ctr) {
            var form = document.forms[0];
            var numNiveisRetorno = form.numNiveisRetorno.value;
        
            if(numNiveisRetorno ==1) {
                lista = form.sequencia;
            } else {
                lista = form.sequencia[ctr]
            }
        
            var oSelected;
            var oSelectedAcima;
            for (i=0; i<lista.options.length; i++) {
                if (lista.options[i].selected) {
                    oSelected = lista.options[i];
                    if (i>0) {
                        oSelectedAcima = lista.options[i-1];
                        oSelectedAcima.swapNode(oSelected);
                    } else {
                        return;
                    }
                }
            }
        }

        function grupoAbaixo(ctr) {
            var oSelected;
            var oSelectedAcima;
            for (i=lista.options.length-1; i>=0; i--) {
                if (lista.options[i].selected) {
                    oSelected = lista.options[i];
                    if (i<lista.options.length-1) {
                        oSelectedAcima = lista.options[i+1];
                        oSelectedAcima.swapNode(oSelected);
                    } else {
                        return;
                    }
                }
            }
        }
        
        function transfereSelecaoLista(listaOrigem, listaDestino)
        {
            var i;
            for(i = 0; i<listaOrigem.options.length; i++)
                if(listaOrigem.options[i].selected)
                    listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
            for(i = listaOrigem.options.length-1; i>=0; i--)
                if(listaOrigem.options[i].selected)
                    listaOrigem.options[i] = null;
            return false;
        }
        
        function transfereSelecaoListaTodos(listaOrigem, listaDestino)
        {
            var i;
            for(i = 0; i<listaOrigem.options.length; i++)
                listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
            for(i = listaOrigem.options.length-1; i>=0; i--)
                listaOrigem.options[i] = null;
            return false;
        }
    
        function atualizaNiveis(option) {
            //Start animação
            if( top.dvAnimarAguarde != null ) top.startAnimation();

            document.forms[0].action = "AlteraNumeroNiveis.do";
            document.forms[0].target = '';
            parent.parent.mostrar_div();
            document.forms[0].submit();
        }
        
        function Submit() {
            //Start animação
            if( top.dvAnimarAguarde != null ) top.startAnimation();

            var form = document.forms[0];
            var numNiveisRetorno = form.numNiveisRetorno.value;
    
            if((numNiveisRetorno > 0) && (numNiveisRetorno < 2)) {
                // se tiver um nivel apenas
                for(var i = 0; i < form.disponiveis.options.length; i++) {
                    form.disponiveis.options[i].selected = true;
                }
    
                for(var i = 0; i < form.sequencia.options.length; i++) {
                    form.sequencia.options[i].selected = true;
                }
            }
            else {
                // se tiver mais de um nivel
                for(var j = 0; j < numNiveisRetorno; j++) {
                    for(var i = 0; i < form.disponiveis[j].options.length; i++) {
                        form.disponiveis[j].options[i].selected = true;
                    }
        
                    for(var i = 0; i < form.sequencia[j].options.length; i++) {
                        form.sequencia[j].options[i].selected = true;
                    }
                }
            }
            parent.parent.mostrar_div();
            form.submit();
        }
        
        function executaBotao(botao, ctr) {
            var form = document.forms[0];
            var numNiveisRetorno = form.numNiveisRetorno.value;
        
            if((numNiveisRetorno > 0) && (numNiveisRetorno < 2)) {
                // se tiver um nivel apenas
                switch(botao) {
                    case ">":
                        transfereSelecaoLista(form.disponiveis, form.sequencia);
                        break;
                        
                    case ">>":
                        transfereSelecaoListaTodos(form.disponiveis, form.sequencia);
                        break;
    
                    case "<":
                        transfereSelecaoLista(form.sequencia, form.disponiveis);
                        break;
    
                    case "<<":
                        transfereSelecaoListaTodos(form.sequencia, form.disponiveis);
                        break;
                }
            }
            else {
                // se tiver mais de um nivel
                switch(botao) {
                    case ">":
                        transfereSelecaoLista(form.disponiveis[ctr], form.sequencia[ctr]);
                        break;
                        
                    case ">>":
                        transfereSelecaoListaTodos(form.disponiveis[ctr], form.sequencia[ctr]);
                        break;
    
                    case "<":
                        transfereSelecaoLista(form.sequencia[ctr], form.disponiveis[ctr]);
                        break;
    
                    case "<<":
                        transfereSelecaoListaTodos(form.sequencia[ctr], form.disponiveis[ctr]);
                        break;
                }
            }
        }
        
        //Fim animação
        if( top.dvAnimarAguarde != null ) top.stopAnimation();
    </script>
    <vivo:alert atributo="msgStatus" scope="request"/>
    </acesso:controlHiddenItem>
  </body>    
</html>