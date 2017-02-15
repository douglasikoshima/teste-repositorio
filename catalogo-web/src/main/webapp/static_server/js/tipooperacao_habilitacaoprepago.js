$(document).ready(function() {

	// Start de Habilitação Pré-Pago
	
	$("#aquisicao-radio").click(function() {	
		// Reseta
		if( $("#DIVsemaquisicao").css('display')=='block' ) $("#DIVsemaquisicao").hide(); 
		if( $("#DIVaparelho").css('display')=='block' ) $("#DIVaparelho").hide();
		if( $("#DIVimei").css('display')=='block' ) $("#DIVimei").hide();
		if( $("#DIViccid").css('display')=='block' ) $("#DIViccid").hide();		
		if( $("#DIVcdma").css('display')=='block' ) $("#DIVcdma").hide();
		// Exibe
		$("#DIVaquisicao").show();
		$("#DIVsimulacao").show();
		$("#DIVuf").show();
		$("#DIVddd").show();
		$("#FORMestado").attr('disabled',false);
		$("#FORMddd").attr('disabled',false);
		$("#FORMmotivo").attr('disabled',false);
	});
	
	$("#semaquisicao-radio").click(function() {
		// Reseta
		if( $("#DIVaquisicao").css('display')=='block' ) $("#DIVaquisicao").hide();
		if( $("#DIVsimulacao").css('display')=='block' ) $("#DIVsimulacao").hide();
		if( $("#DIVaparelho").css('display')=='block' ) $("#DIVaparelho").hide();
		if( $("#DIVimei").css('display')=='block' ) $("#DIVimei").hide();
		if( $("#DIVuf").css('display')=='block' ) $("#DIVuf").hide();
		if( $("#DIVddd").css('display')=='block' ) $("#DIVddd").hide();
		
		// Exibe
		$("#DIVsemaquisicao").show();
		$("#FORMestado").attr('disabled',false);
		$("#FORMddd").attr('disabled',false);
		$("#FORMmotivo").attr('disabled',false);
	});
	
	$("#aquisicao-combo").change(function() {
		if($("#aquisicao-combo").attr('value')=='comchip')
		{
			$("#DIVsimulacao").show();
			$("#DIVaparelho").hide();
			$("#DIVimei").hide();
		} else {
			$("#DIVsimulacao").hide();
			$("#DIVaparelho").show();
			$("#DIVimei").show();
		}
	});	
	
	$("#gsm-radio").click(function() {
		$("#DIVaparelho").show();
		$("#DIVimei").show();
		$("#DIViccid").show();
		$("#DIVcdma").hide();
	});
	
	$("#cmda-radio").click(function() {
		$("#DIVaparelho").show();
		$("#DIVcdma").show();
		$("#DIVimei").hide();
		$("#DIViccid").hide();
	});
	
	$('#aparelho-radio').click(function(){$('#formOperacao').attr('action','UC005-HabilitacaoPre-Consultar_Dados_Aparelho.html')});
	$('#plano-radio').click(function(){$('#formOperacao').attr('action','UC022-HabilitacaoPre-Consultar_Plano.html')});
	
});