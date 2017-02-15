<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sessão Ativa</title>
</head>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js" ></script>
<script type="text/javascript">
	window.onload = function(){
		startTimer();
	}
		
	function startTimer(){
		setTimeout('sessionReload()', 120000);
	}
	
	function sessionReload(){
		var isAlive = true;
		
		var params = $H();
        params.set('isAlive', isAlive);

        new Ajax.Updater('msg', '<%=request.getContextPath()%>/admsistemas/relatorioRechamadaDetalhado/sessionAlive.do', {
            method: 'post',
            parameters: params,
            evalScripts: true,
            onSucess: function(e){
            	startTimer();
            }
        });
	}
</script>
<body>
	<p id="msg">Falha na Sessão!</p>
</body>
</html>