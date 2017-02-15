$(document).ready(function() {

	// Start de Migração GSM Pré-Pago
	
	$("#aquisicao-radio").click(function() {	
		// Reseta
		if( $("#DIVsemaquisicao").css('display')=='block' ) $("#DIVsemaquisicao").hide(); 
		if( $("#DIVaparelho").css('display')=='block' ) $("#DIVaparelho").hide();
		if( $("#DIVimei").css('display')=='block' ) $("#DIVimei").hide();
		if( $("#DIViccid").css('display')=='block' ) $("#DIViccid").hide();		
		// Exibe
		$("#DIVaquisicao").show();
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
		$("#DIVaparelho").show();
		$("#DIVimei").show();
		$("#DIViccid").show();
		$("#FORMestado").attr('disabled',false);
		$("#FORMddd").attr('disabled',false);
		$("#FORMmotivo").attr('disabled',false);
	});	
	
	
	$('#aquisicao-combo').change(function() {
		if($('#aquisicao-radio').attr('checked')==true)
		{	
			if($('#aquisicao-combo').val()=='sochip')
			{
				var ActionForm = 'UC009-MigracaoGSMPre-Consultar_Dados_SimCardChip.html';
				$("#DIVaparelho").show();
				$("#DIVimei").show();
				$("#DIVsimulacao").hide();
			}
			if($('#aquisicao-combo').val()=='comchip')
			{
				var ActionForm = 'UC005-MigracaoGSMPre-Consultar_Dados_Aparelho.html';
				$("#DIVaparelho").hide();
				$("#DIVimei").hide();
				$("#DIVsimulacao").show();
			}
			$('#formOperacao').attr('action',ActionForm);
		}
	});
	
	$('#semaquisicao-radio').change(function() {
		var ActionForm = 'UC022-MigracaoGSMPre-Consultar_PlanoSemAquisicao.html';
		$('#formOperacao').attr('action',ActionForm);
	});
	
	$('#aparelho-radio').change(function() {$('#formOperacao').attr('action','UC005-MigracaoGSMPre-Consultar_Dados_Aparelho.html');});
	$('#plano-radio').change(function() {$('#formOperacao').attr('action','UC022-MigracaoGSMPre-Consultar_Plano.html');});

});