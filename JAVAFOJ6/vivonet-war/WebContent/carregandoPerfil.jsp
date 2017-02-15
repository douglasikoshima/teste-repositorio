<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<html style="margin:0;padding:0;">
    <head>
        <title>Carregando Perfil do Usuário</title>
	    <script>
	        <!--
	            function oculta_div(obj){
	                idAnime_PERFIL.style.display="none";
	            }
	
	            function mostrar_div(obj){
	                idAnime_PERFIL.style.display="block";
	            }
	
	            function enviaInicioApp(){
	                document.forms[0].target = 'ifmCarregaPerfil';
	                document.forms[0].action = '<%=request.getContextPath()%>/autenticado.do?carregando_perfil=1';
	                document.forms[0].submit();
	            }
	        -->
	    </script>
    </head>
    <body style="margin:0;padding:0;">
    <form>
        <input type="hidden" name="carregando_perfil" id="carregando_perfil" value="1">
        <div id='idAnime_PERFIL'  style='display:none;z-index:2000 ;position:absolute; top:0px; left:0px; width:100%; height:100%;background-image:url(<%=request.getContextPath()%>/resources/images/window_bg.gif); '>
            <table border='1' cellpadding='0' cellspacing='0' height="100%" width="100%">
                <tr>
                    <td align="center" valign="middle">
                    	<iframe frameborder="0" scrolling="no" height='63' width='81'  src='http:<%=request.getContextPath()%>/resources/images/carregandoPerfil.html'></iframe>
                    </td>
                </tr>
            </table>
        </div >
        <iframe style="width:800px;height:600px;" name="ifmCarregaPerfil" id="ifmCarregaPerfil"></iframe>
    </form>
    <SCRIPT>
        <!--
        mostrar_div();
        enviaInicioApp();
        -->
    </SCRIPT>
    </body>
</html>