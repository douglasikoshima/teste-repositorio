<%--
Módulo.....: Gestão de Processos
Caso de Uso: Detalhe do processo
Versão.....: $Author: a5112274 $ - $Revision: 1.1 $ - $Date: 2011/04/25 19:36:40 $

*** REFACTORING ***
 Date: 21/11/2004
 Author: emocana - osaavedra
*******************
--%>
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