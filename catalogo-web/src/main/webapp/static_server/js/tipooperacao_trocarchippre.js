$(document).ready(function() {

	// Start de Habilitação Pré-Pago
	
	$("#aquisicao-radio").click(function() {
	
		// Reseta
		if( $("#DIVsemaquisicao").css('display')=='block' ) $("#DIVsemaquisicao").hide(); 
		if( $("#DIVgsm").css('display')=='block' ) $("#DIVgsm").hide();
		// Exibe
		$("#DIVaquisicao").show();
		$('#formOperacao').attr('action','UC009-TrocarChipPre-Consultar_Dados_SimCard.html');
		$("#FORMestado").attr('disabled',false);
		$("#FORMddd").attr('disabled',false);
		$("#FORMmotivo").attr('disabled',false);
		$("#DIVuf").show();
		$("#DIVddd").show();
	});
	
	$("#semaquisicao-radio").click(function() {

		// Reseta
		if( $("#DIVaquisicao").css('display')=='block' ) $("#DIVaquisicao").hide();
		// Exibe
		$("#DIVsemaquisicao").show();
		$("#DIVgsm").show();
		$('#formOperacao').attr('action','UC032-TrocarChipPre-Resumo_da_Solicitacao_SemAquisicao.html');
		$("#FORMestado").attr('disabled',false);
		$("#FORMddd").attr('disabled',false);
		$("#FORMmotivo").attr('disabled',false);
		if( $("#DIVuf").css('display')=='block' ) $("#DIVuf").hide();
		if( $("#DIVddd").css('display')=='block' ) $("#DIVddd").hide();
	});	
	
	

});