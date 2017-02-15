<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<html>
<head>
<title>Vivo Net</title>
<script>
</script>
</head>
<frameset rows="0,*" cols="*" border="0" frameSpacing="0" frameBorder="no">
	    <frame name="frameCTI" src="<%=request.getContextPath()%>/AtenaDummyOCX.jsp" noResize scrolling="no">
	 	<frame name="frameApp" src="<%=request.getContextPath()%>/extworkflw/AtendimentoFila/MeuCliente/begin.do" noResize scrolling="no">
</frameset>
<noframes></noframes>
</html>