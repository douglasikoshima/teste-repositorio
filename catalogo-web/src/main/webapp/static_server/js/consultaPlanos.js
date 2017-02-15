var $jq = jQuery.noConflict();

function exportarVariaveis() {
	document.getElementById('consultaPlanosForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/consulta/planos/consultaPlanos/exportarVariaveis.do";
	document.getElementById('consultaPlanosForm').submit();
}

function listarVariaveisPlano() {
	document.getElementById('consultaPlanosForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/consulta/planos/consultaPlanos/listarVariaveisPlano.do";
	document.getElementById('consultaPlanosForm').submit();
}

function exportarPlanos() {
	document.getElementById('consultaPlanosForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/consulta/planos/consultaPlanos/exportarPlanos.do";
	document.getElementById('consultaPlanosForm').submit();
}

