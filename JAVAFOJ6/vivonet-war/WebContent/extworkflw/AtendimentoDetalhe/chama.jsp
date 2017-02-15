<frameset rows="0px, *" cols="*" border="0" frameSpacing="0" frameBorder="0">
    <frame name="frameCTI" src="/FrontOfficeWeb/AtenaDummyOCX.jsp" noResize scrolling="no">
    <frame name="frameApp" src="telaInicial.do?nrLinha=<%=request.getParameter("nrLinha")%>&pesquisa=<%=request.getParameter("pesquisa")%>&relacionamento=<%=request.getParameter("inResponsavelAbertura")%>&framesMostrados=dados" noResize scrolling="no">
</frameset><noframes></noframes>