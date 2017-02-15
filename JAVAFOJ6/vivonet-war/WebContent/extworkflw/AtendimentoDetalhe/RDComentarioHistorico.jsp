<%--
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<acesso:controlHiddenItem nomeIdentificador="wor_rdcacao_verpagina">
--%>
<p><b>Comentário: </b><textarea readonly="true" style="width=260px; height=80px;"><%=(String)request.getAttribute("coment")%></textarea></p>   
<%--

</acesso:controlHiddenItem>
--%>
<script>
parent.dvComentAcao.style.display = '';
if(top.frameApp.dvAnimarAguarde!=null)top.frameApp.stopAnimation();
</script>