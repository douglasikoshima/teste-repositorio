$(document).ready(function() {

	// Start de Habilitação Pré-Pago
	
	$("#trocaaparelho-aquisicao-radio").click(function() {
	
		// Reseta
		if( $("#DIVsemaquisicao").css('display')=='block' ) $("#DIVsemaquisicao").css('display', 'none'); 
		if( $("#DIVaparelho").css('display')=='block' ) $("#DIVaparelho").css('display', 'none');
		if( $("#DIVgsm").css('display')=='block' ) $("#DIVgsm").css('display', 'none');
		if( $("#DIVcdma").css('display')=='block' ) $("#DIVcdma").css('display', 'none');
		$("#FORMestado").attr('disabled',false);
		$("#FORMddd").attr('disabled',false);
		$("#FORMmotivo").attr('disabled',false);
		$("#DIVuf").show();
		$("#DIVddd").show();
	
		// Exibe
		$("#DIVaquisicao").css('display', 'block');

	});
	
	$("#trocaaparelho-semaquisicao-radio").click(function() {

		// Reseta
		if( $("#DIVaquisicao").css('display')=='block' ) $("#DIVaquisicao").css('display', 'none');
		//if( $("#DIVsimulacao").css('display')=='block' ) $("#DIVsimulacao").css('display', 'none');
		
		// Exibe
		$("#DIVsemaquisicao").css('display', 'block');
		$("#DIVaparelho").css('display', 'block');
		$("#DIVgsm").css('display', 'block');
		$("#DIVcdma").css('display', 'none');
		
		$("#FORMestado").attr('disabled',false);
		$("#FORMddd").attr('disabled',false);
		$("#FORMmotivo").attr('disabled',false);
		if( $("#DIVuf").css('display')=='block' ) $("#DIVuf").hide();
		if( $("#DIVddd").css('display')=='block' ) $("#DIVddd").hide();

	});	
	
	$('#aquisicao-combo').change(function() {
		if($('#trocaaparelho-aquisicao-radio').attr('checked')==true)
		{	
			if($('#aquisicao-combo').val()=='comchip') var ActionForm = 'UC005-TrocarAparelhoGSMPre-Consultar_Dados_AparelhoPlacaModem_Chip.html';
			$('#trocaaparelho-form').attr('action',ActionForm);
		}
	});
	
	$('#trocaaparelho-semaquisicao-radio').change(function() {
			var ActionForm = 'UC033-TrocarAparelhoGSMPre-Configurar_Promocao_SemAquisicao.html';
			$('#trocaaparelho-form').attr('action',ActionForm);

	});
	
	// UC-04 trocar aparelho GSM
	$('#trocaaparelho-aquisicao-radio').click(function() {
		$('#trocaaparelho-form').attr('action','UC005-TrocarAparelhoGSMPre-Consultar_Dados_AparelhoPlacaModem.html');
		$('#DIVaquisicao').css('display','block');
	});
	$('#trocaaparelho-semaquisicao-radio').click(function() {
		$('#trocaaparelho-form').attr('action','UC033-TrocarAparelhoGSMPre-Configurar_Promocao.html');
		$('#DIVaquisicao').hide();
	});


});