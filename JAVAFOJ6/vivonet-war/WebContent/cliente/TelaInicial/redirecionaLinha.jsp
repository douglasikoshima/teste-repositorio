<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page import="cliente.TelaInicial.TelaInicialController.PesquisaForm"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>
<acesso:controlHiddenItem nomeIdentificador="cli_redli_verpagina">
  
  <html:form action="/cliente/TelaInicial/TelaInicial.do">

    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js" ></script>

    <%
    PesquisaForm pesquisaForm = (PesquisaForm) request.getAttribute("pesquisaForm");
    String inClienteUsuario = ConstantesCRM.SVAZIO;
    if ("protocolo".equals(request.getParameter("pesquisa"))) {
        if(pesquisaForm.getListaClientes().getClientesPesquisadosArray().length>0)
            inClienteUsuario = pesquisaForm.getListaClientes().getClientesPesquisadosArray(0).getInClienteUsuario();
    %>

      <script>
        top.frameApp.inClienteUsuario = "<%=inClienteUsuario%>";
      </script>
  
    <% } %>

    <% if ("celular".equals(request.getParameter("pesquisa"))) { %>
  
      <% if (pesquisaForm.getLinhasConvergentes() == null || pesquisaForm.getLinhasConvergentes().getLinhaArray().length == 0) { %>
        <script>
          top.frameApp.verificaLinhaPreAtiva();
          top.frameApp.hideDataScreen();
          setTimeout(function(){top.frameApp.$('ti_inputValorPesquisa').focus();},300);
          if( window.top.frameApp.dvAnimarAguarde) window.top.frameApp.stopAnimation();
        </script>
      <% } else { %>

        <script>
          var params = $H();
          params.set('idLinhaTelefonica', '<%= request.getAttribute("idLinhaTelefonica") %>');
          params.set('valor', '<%= request.getAttribute("nrLinha") %>');
          params.set('pesquisaScreenPop', (top.frameApp.nrProtocoloScreenPop != '' || top.frameApp.isScreenPop) ? true : false);
          new Ajax.Request('/FrontOfficeWeb/cliente/TelaInicial/carregaTelaInicialXml.do', {
            method: 'get',
            parameters: params,
            contentType: 'text/xml',
            onComplete: top.frameApp.loadUserData
          });
        </script>

      <% } %>

    <% } else { %>
  
      <% if (request.getAttribute("nrLinhaNaoCliente") != null) { %>

        <script type="text/javascript" language="javascript">
          onload = function() {
            var reqParamProtInativo = '';
            <% if (request.getAttribute("isProtocoloAtivo") == null) { %>
              reqParamProtInativo = '&protocoloInativo=true';
            <% } %>
            top.location.href = '<%=request.getContextPath()%>/consultaLinha.do?nrLinha=<%=request.getAttribute("nrLinhaNaoCliente")%>&nrProtocolo=<%=request.getAttribute("nrProtocolo")%>' + reqParamProtInativo;
          }
        </script>

      <% } else if(request.getAttribute("nrLinha") != null
              && (request.getAttribute("inBloqueado") == null
              || ConstantesCRM.SZERO.equals(request.getAttribute("inBloqueado")))) { %>

        <script type="text/javascript" language="javascript">
          top.frameApp.nrLinha = '<%=request.getAttribute("nrLinha")%>';
          top.frameApp.$('ti_inputValorPesquisa').value = top.frameApp.nrLinha;
          top.frameApp.$('ti_comboPesquisa').value = "celular";
          top.frameApp.$("divPopupTI").hide();
          top.frameApp.controlCombos();
          var sProtAtivo = null;
          <% if (session.getAttribute(ConstantesCRM.NRPROTOCOLO) != null) { %>
			sProtAtivo = true;
            top.frameApp.$('nrProtocolo').update('<%=session.getAttribute(ConstantesCRM.NRPROTOCOLO)%>');
  		      top.frameApp.nrProtocolo = <%=session.getAttribute(ConstantesCRM.NRPROTOCOLO)%>;
            top.frameApp.setAlteracaoSMSSolicitacaoProtocolo('alteracaoSMS');
          <% } %>
		  top.frameApp.abrePesquisa(sProtAtivo);
        </script>

      <% } else if (ConstantesCRM.SONE.equals(request.getAttribute("inicioTela"))) { %>

        <script type="text/javascript" language="javascript">
          top.frameApp.$("divPopupTI").hide();
          top.frameApp.controlCombos();
  		    if (top.frameApp.$('ti_comboPesquisa') && top.frameApp.$('ti_comboPesquisa').value == 'protocolo') {
      			alert('Protocolo não localizado.');
      		} else {
      			alert('Dados do cliente não localizados.');
      		}
          if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
        </script>

      <% } else if (ConstantesCRM.SONE.equals(request.getAttribute("inBloqueado"))) { %>

        <script type="text/javascript" language="javascript">
          top.frameApp.$("divPopupTI").hide();
          top.frameApp.controlCombos();
          top.frameApp.nrLinha = "<%=request.getAttribute("nrLinha")%>";
          alert('DDD '+top.frameApp.nrLinha.substring(0,2)+' bloqueado temporariamente. Não é possível seguir com o atendimento.');
          if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
        </script>

      <% } else { %>

        <script>
          top.frameApp.$("divPopupTI").hide();
          top.frameApp.controlCombos();
          <% if (request.getAttribute("isProtocoloAtivo") != null && session.getAttribute(ConstantesCRM.NRPROTOCOLO) != null) { %>
            top.frameApp.$('nrProtocolo').update('<%=session.getAttribute(ConstantesCRM.NRPROTOCOLO)%>');
  		      top.frameApp.nrProtocolo = <%=session.getAttribute(ConstantesCRM.NRPROTOCOLO)%>;
            top.frameApp.setAlteracaoSMSSolicitacaoProtocolo('alteracaoSMS');
          <% } %>
          top.frameApp.getProspect();
        </script>

      <% } %>

    <% } %>

  </html:form>
</acesso:controlHiddenItem>