$(document).ready(function() {
         
	$('.form-clear').click(function(){
		var cdma = false;
		var gsm = false;
		var recarga = false;
		var acessorio = false;
		$('#formOperacao').find('input:checkbox').each(function() {
			if (this.checked) {
			valor = this.id;
				if ( valor.indexOf('1') != -1) {
					cdma = true;
				} else if (valor.indexOf('2') != -1) {
					gsm = true;
				} else if (valor.indexOf('3') != -1) {
					recarga = true;
				} else if (valor.indexOf('4') != -1) {
					acessorio = true;
				}
			}
		});
		
		if (cdma) var ff = "UC005-VenderProdutosAvulsos-Consultar_Dados_Aparelho.html"; 
		if (gsm) var ff = "UC009-VenderProdutosAvulsos_Dados_SimCard.html"; 
		if (gsm && cdma) var ff = 'UC005-VenderProdutosAvulsos-Consultar_Dados_Aparelho_UC009.html'; 
		if (recarga) var ff = "UC009-VenderProdutosAvulsos_Dados_SimCard_Recarga.html";
		if (acessorio) var ff = "UC009-VenderProdutosAvulsos_Dados_SimCard_Acessorio.html";
		
		$('#formOperacao').attr('action',ff);
	});
 
});
