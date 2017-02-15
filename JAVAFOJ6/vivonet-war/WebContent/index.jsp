<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<html>
<head>
<title>Vivo Net</title>
<script>
	window.name="janelaPrincipal";
	checkCtrl = function() {
	    var ch = String.fromCharCode(event.keyCode);
	    if ((event.ctrlLeft || event.CtrlRight) && (ch == "n" || ch == "N"))
	        return false;
	}
	document.onkeydown = checkCtrl;
</script>
</head>
<frameset rows="0px, *" cols="*" border="0" frameSpacing="0" frameBorder="no">
    <frame name="frameCTI" src="<%=request.getContextPath()%>/AtenaDummyOCX.jsp" noResize scrolling="no">
        <%
        String urlParam    = ConstantesCRM.SVAZIO;
        String nrLinha     = (String) request.getAttribute("consultaLinha");
        String nrProtocolo = (String) request.getAttribute("consultaProtocolo");

        if (nrLinha != null && !ConstantesCRM.SVAZIO.equals(nrLinha)) {
            urlParam = "nrLinha="+nrLinha;
        }
        if (nrProtocolo != null && !ConstantesCRM.SVAZIO.equals(nrProtocolo)) {
            urlParam += "&nrProtocolo="+nrProtocolo;
        }
        if (request.getAttribute("protocoloInativo") != null) {
            urlParam += "&protocoloInativo=true";
        }
        if (urlParam != null && !ConstantesCRM.SVAZIO.equals(urlParam)){ %>
            <frame name="frameApp" src="<%=request.getContextPath()%>/cliente/TelaInicial/begin.do?<%=urlParam%>" noResize scrolling="no">
        <% } else if (ConstantesCRM.STRUE.equals(request.getAttribute("inMeuCliente"))) { %>
            <frame name="frameApp" src="<%=request.getContextPath()%>/workflow/AtendimentoFila/MeuCliente/begin.do?ACESSO_EXTERNO=true" noResize scrolling="no">
        <% } else { %>
            <frame name="frameApp" src="<%=request.getContextPath()%>/inicio.jsp" noResize scrolling="no">
        <% } %>
</frameset>
<noframes></noframes>
</html>