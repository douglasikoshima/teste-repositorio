$(document).ready(function() {

	// Start de Habilitação Pré-Pago
	
	$("#aquisicao-radio").click(function() {
	
		// Reseta
		if( $("#DIVsemaquisicao").css('display')=='block' ) $("#DIVsemaquisicao").css('display', 'none'); 
		if( $("#DIVaparelho").css('display')=='block' ) $("#DIVaparelho").css('display', 'none');
		if( $("#DIVgsm").css('display')=='block' ) $("#DIVgsm").css('display', 'none');
		if( $("#DIVcdma").css('display')=='block' ) $("#DIVcdma").css('display', 'none');
	
		// Exibe
		$("#DIVaquisicao").css('display', 'block');

	});
	
	$("#semaquisicao-radio").click(function() {

		// Reseta
		if( $("#DIVaquisicao").css('display')=='block' ) $("#DIVaquisicao").css('display', 'none');
		if( $("#DIVsimulacao").css('display')=='block' ) $("#DIVsimulacao").css('display', 'none');
		
		// Exibe
		$("#DIVsemaquisicao").css('display', 'block');

	});
	
	$("#aquisicao-combo").change(function() {
		if($("#aquisicao-combo").attr('value')=='comchip')
		{
			$("#DIVsimulacao").css('display', 'block');
			$("#DIVaparelho").css('display', 'none');
		} else {
			$("#DIVsimulacao").css('display', 'none');
			$("#DIVaparelho").css('display', 'block');
		}
	});	
	
	$("#gsm-radio").click(function() {
		$("#DIVaparelho").css('display', 'block');
		$("#DIVgsm").css('display', 'block');
		$("#DIVcdma").css('display', 'none');
	});
	
	$("#cmda-radio").click(function() {
		$("#DIVaparelho").css('display', 'block');
		$("#DIVcdma").css('display', 'block');
		$("#DIVgsm").css('display', 'none');
	});
	
	$('#aparelho-radio').click(function(){$('#formOperacao').attr('action','UC005-HabilitacaoPre-Consultar_Dados_Aparelho.html')});
	$('#plano-radio').click(function(){$('#formOperacao').attr('action','UC022-HabilitacaoPre-Consultar_Plano.html')});


	
	
	
});