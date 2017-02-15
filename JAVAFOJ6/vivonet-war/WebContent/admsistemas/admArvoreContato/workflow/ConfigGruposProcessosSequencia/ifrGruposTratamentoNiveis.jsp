<!--
Módulo.....: Gestão de Sistemas
Caso de Uso: Configurar Grupo de Usuário com Níveis Workflow
Versão.....: $Author: a5112272 $ - $Revision: 1.4 $ - $Date: 2011/06/14 14:34:06 $
-->
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
    <title>Vivo Net - Aviso / Erro</title>
    <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">        
  </head>
  <body onload="top.frameApp.oculta_div();" style="background-color:#E3ECF4;">
  <acesso:controlHiddenItem nomeIdentificador="adm_ifgrt_verpagina">
    <form action="AplicaGruposTratamentoNiveis" id="AplicaGruposTratamentoNiveis" name="AplicaGruposTratamentoNiveis" method="post">
        <html:hidden name="form" property="grupoSelecionado" />
        <html:hidden name="form" property="numNiveisRetorno" />
        <table border="0">
            <tr>
                <td>
                    Níveis:
                    <html:select name="form" property="nivelSelecionado" title="nivelSelecionado" onchange="JavaScript:atualizaNiveis(this)">
                        <html:options collection="niveisArray" property="codigo" labelProperty="descricao" /> 
                    </html:select>
                </td>
            </tr>
        </table>
        
        <logic:iterate name="grpTratamento" property="grupoTratamentoArray" id="GrupoTratamentoVO" indexId="ctr" >
            <bean:define name="GrupoTratamentoVO" property="nivel"       id="nivel" />
            <bean:define name="GrupoTratamentoVO" property="disponiveis" id="disponiveis" />
            <bean:define name="GrupoTratamentoVO" property="sequencia"   id="sequencia" />
            
            <vivo:quadro id="quadroNivel" height="170" width="370" label="<%=\"Nível:&nbsp;\" + nivel.toString()%>"> 
                <table border="0">
                    <tr>
                        <td>
                            <!-- Disponíveis -->
                            Disponível<br/>
                            <html:select name="form" property="disponiveis" multiple="true" size="10" style="width=160px">
                                <logic:iterate name="disponiveis" property="grupoVOArray" id="aGrupoVODisp">
                                    <bean:define name="aGrupoVODisp" property="codigo" id="codigo" />
                                    <option value="<%=ctr.toString() + codigo%>"><bean:write name="aGrupoVODisp" property="descricao"/></option>
                                </logic:iterate>
                            </html:select>
                        </td>
                        <td>
                            <vivo:botao id="bt01" width="25px" height="10px" value=">"  styleClass="btTemplate" onclick="<%= \"JavaScript:executaBotao('>', \" + ctr + \");\"%>" />
                            <vivo:botao id="bt02" width="25px" height="10px" value=">>" styleClass="btTemplate" onclick="<%= \"JavaScript:executaBotao('>>', \" + ctr + \");\"%>" />
                            <vivo:botao id="bt03" width="25px" height="10px" value="<"  styleClass="btTemplate" onclick="<%= \"JavaScript:executaBotao('<', \" + ctr + \");\"%>" />
                            <vivo:botao id="bt04" width="25px" height="10px" value="<<" styleClass="btTemplate" onclick="<%= \"JavaScript:executaBotao('<<', \" + ctr + \");\"%>" />
                        </td>
                        <td>
                            <!-- Sequencia -->
                            Seqüência<br/>
                            <html:select name="form" property="sequencia" multiple="true" size="10" style="width=160px">
                                <logic:iterate name="sequencia" property="grupoVOArray" id="aGrupoVOSeq">
                                    <bean:define name="aGrupoVOSeq" property="codigo" id="codigo" />
                                    <option value="<%=ctr.toString() + codigo%>"><bean:write name="aGrupoVOSeq" property="descricao"/></option>
                                </logic:iterate>
                            </html:select>
                        </td>
                    </tr>
                </table>
            </vivo:quadro>        
            <br>                    
        </logic:iterate>
        <acesso:controlHiddenItem nomeIdentificador="adm_ifgrt_submit">
        <vivo:botao id="btOK" width="60px" height="10px" value="Aplicar" styleClass="btTemplate" onclick="Submit();"/>
        </acesso:controlHiddenItem>
    </form>
    
    <script language="JavaScript">
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
            //Inicio animação
            if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();

            document.forms[0].action = "AlteraNumeroNiveis.do";
            parent.parent.mostrar_div();
            document.forms[0].submit();
        }
        
        function Submit() {
            //Inicio animação
            if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();

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
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
        
    </script>
    </acesso:controlHiddenItem>
  </body>    
</html>