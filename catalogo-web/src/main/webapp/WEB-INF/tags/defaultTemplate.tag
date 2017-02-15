<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ attribute name="titulo"  required="true" %>
<%@ attribute name="headScripts"  fragment="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="catalogo" tagdir="/WEB-INF/tags" %>

<html>
	<title>${titulo}</title>

	<head>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
	<meta name="resource-type" content="document" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta name="revisit-after" content="1" />
	<meta name="classification" content="Internet" />
	<meta name="description" content="FrontOffice VIVO" />
	<meta name="keywords" content="" />
	<meta name="robots" content="ALL" />
	<meta name="distribution" content="Global" />
	<meta name="rating" content="General" />
	<meta name="author" content="Worbi Internet Business" />
	<meta name="language" content="pt-br" />
	<meta name="doc-class" content="Completed" />
	<meta name="doc-rights" content="Public" />
	<meta name="DC.title" content="FrontOffice VIVO" />
	
	<link rel="stylesheet" type="text/css" href="/catalogo/static_server/css/default.css"> 
	<link rel="stylesheet" type="text/css" href="/catalogo/static_server/css/menu.css"> 
	<link rel="stylesheet" type="text/css" href="/catalogo/static_server/css/conteudo.css"> 
	<link rel="stylesheet" type="text/css" href="/catalogo/static_server/css/tabela.css"> 
	<link rel="stylesheet" type="text/css" href="/catalogo/static_server/css/button.css"> 
	<link rel="stylesheet" type="text/css" href="/catalogo/static_server/css/popup.css"> 
	<link rel="stylesheet" type="text/css" href="/catalogo/static_server/css/form.css">
	<link rel="stylesheet" type="text/css" href="/catalogo/static_server/css/image.css">

	<link type="text/css" href="/catalogo/static_server/jquery/css/cupertino/jquery-ui-1.8.17.custom.css" rel="stylesheet" />	
	<script type="text/javascript" src="/catalogo/static_server/jquery/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="/catalogo/static_server/jquery/js/jquery-ui-1.9.2.custom.min.js"></script>	
	<script type="text/javascript" src="/catalogo/static_server/jquery/js/jquery.ui.datepicker-pt-BR.js"></script>
	<script type="text/javascript" src="/catalogo/static_server/jquery/js/jquery-datatables.js"></script>
	<script src="/catalogo/static_server/jquery/js/application.js" type="text/javascript"></script>
	<script src="/catalogo/static_server/jquery/colorPicker/javascripts/mColorPicker_min.js" type="text/javascript" charset="UTF-8"></script>
	<script src="/catalogo/static_server/js/prototype.js" type="text/javascript"></script>
	<script src="/catalogo/static_server/js/menu.js" type="text/javascript"></script>
	<script src="/catalogo/CatalogoPRS.js" type="text/javascript"></script>
	<script src="/catalogo/static_server/js/scriptaculous.js" type="text/javascript"></script>
	<script src="/catalogo/static_server/js/table.js" type="text/javascript"></script>
	<script src="/catalogo/static_server/js/teclado.js" type="text/javascript"></script>
	<script src="/catalogo/static_server/js/json2.js" type="text/javascript"></script>
	
	<!-- Script para rodar o multiselect-->
	<script type="text/javascript" src="/catalogo/static_server/jquery/js/jquery.multiselect.js"></script>
	<script type="text/javascript" src="/catalogo/static_server/jquery/js/jquery.multiselect.filter.js"></script>
	
	
    <jsp:invoke fragment="headScripts"/>
	
</head>
<body>
 		<c:if test="${logged_user == null}">
			<script>
				window.location.href="/catalogo/br/com/vivo/catalogoPRS/pageflows/inicio/InicioLoginAction.do";
			</script>
		</c:if>
		<div class="hide">
			<img src="/catalogo/static_server/img/botoes/bt-alterar.gif"/>
			<img src="/catalogo/static_server/img/botoes/bt-excluir.gif"/>
			<img src="/catalogo/static_server/img/botoes/valorCarac.gif"/>
			<img src="/catalogo/static_server/img/botoes/bt-detalhes-ico.gif"/>
			<img src="/catalogo/static_server/img/botoes/toolbarButtonGraphics/general/Copy16.gif"/>
			<img src="/catalogo/static_server/img/botoes/toolbarButtonGraphics/media/Movie16.gif"/>
			<img src="/catalogo/static_server/img/botoes/toolbarButtonGraphics/table/table16.gif"/>
			<img src="/catalogo/static_server/img/botoes/toolbarButtonGraphics/general/bluetick.gif"/>
		</div>
		<div id="trash" style="display:none;">
		</div>
		<div id="popup1Cortina" style="z-index: 10; display: none;" class="cortina">
		</div>
		<iframe id="popup1IF" style="display: none;background-color: transparent;" class="popupIF"></iframe>
		<div id="popup1" style="display:none;background-color: transparent;z-index: 25;top:100px;" class="popup">
		</div>
		<div id="popup2Cortina" style="z-index: 30; display: none;" class="cortina">
		</div>
		<iframe id="popup2IF" style="display: none;background-color: transparent;" class="popupIF"></iframe>
		<div id="popup2" style="display:none;background-color: transparent;z-index: 45;top:125px;" class="popup">
		</div>
		<div id="higher_cortina" style="z-index: 80; display: none;" class="higher_cortina">
		</div>
		<div id="carregando" style="z-index: 90; display: none;" class="carregando">
			<img src="/catalogo/static_server/img/carregando.gif"/>
		</div>
		
     	<!-- Cabeçalho -->
		<div id="header">
			<div id="logo" class="Logo">
			</div>
			<br>
			<div style="width:100%;position:absolute;left:0px;top:10px;" align="center">
				<label class="lblTexto" style="font-size: 16px; color: black;">Catálogo</label>
			</div>
			<div id="spinner" style="display:none;">
				<img src="/catalogo/static_server/img/tree/spinner.gif" /> Carregando...
			</div>
			<div id="header_close">
				<label class="lblTexto">Usuário:</label>
				<label class="lblNome">${logged_user.user.username}&nbsp;&nbsp;&nbsp;&nbsp;</label>					
				
				<a href="/catalogo/br/com/vivo/catalogoPRS/pageflows/inicio/popupConfirmaLogout.do" onclick="abrirPopup1(this.href, null , 'right_section');return false;">
					<img title="Fechar" alt="Fechar" src="/catalogo/static_server/img/botoes/btSair.gif"/>
				</a>																		
			</div>
		</div>		
   
		<div id="main">
			<div id="left_section">
				<jsp:include page="/templates/menu.jsp"/>
				<div id="publicidade_menu">
					<a href="/"><img src="/catalogo/static_server/img/publicidade_menu.jpg" width="138" height="112" alt="" border="0"/></a>
				</div>
			</div>
			<div id="right">
				<div id="divErros" style="display:none; background-color: white;position:relative;">
					<catalogo:contentSimple>
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
							<tr>
								<td rowspan="2" class="box_middle_left"></td>
								<td>
									<div class="box_middle_center">
										<div id="conteudo_divErros" class="box_middle_center_conteudo"></div>
									</div>
								</td>
								<td rowspan="2" class="box_middle_right"></td>
							</tr>
						</table>
					</catalogo:contentSimple>	
				</div>
				<div id="right_section">
					<c:choose>
						<c:when test="${labelSucess != null}">
							<div id="divSucess" style="display:inline; background-color: white;position:relative;">
							<catalogo:contentSimple>
								<table border="0" cellpadding="0" cellspacing="0" width="100%">
									<tr>
										<td rowspan="2" class="box_middle_left"></td>
										<td>
											<div class="box_middle_center">
												<div id="conteudo_divSucess" class="box_middle_center_conteudo" style="color: #299B00">${labelSucess}</div>
											</div>
										</td>
										<td rowspan="2" class="box_middle_right"></td>
									</tr>
								</table>
							</catalogo:contentSimple>
						</div>
						</c:when>
						<c:otherwise>
							<div id="divSucess" style="display:none;"></div>
						</c:otherwise>
					</c:choose>
			   		<jsp:doBody/>
		       	</div>
			</div>
		</div>
	</body>
	<script>init();</script>
</html>