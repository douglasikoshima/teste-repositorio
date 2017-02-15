$(document).ready(function() {
						   
	// UC-04 migrar CDMA
	$('#aquisicao-radio').click(function() {
		$('#formOperacao').attr('action','UC005-MigracaoCDMAPre-Consultar_Dados_Aparelho.html');
		$('#DIVaquisicao').css('display','block'); $('#DIVsimulacao').css('display','block');
		$('#DIVsemaquisicao').css('display','none'); $('#DIVaparelho').css('display','none');
		$('#DIVcdma').css('display','none');
		$("#FORMestado").attr('disabled',false);
		$("#FORMddd").attr('disabled',false);
		$("#FORMmotivo").attr('disabled',false);
		$("#DIVuf").show();
		$("#DIVddd").show();
	});
	$('#semaquisicao-radio').click(function() {
		$('#formOperacao').attr('action','UC022-MigracaoCDMAPre-Consultar_PlanoSemAquisicao.html');
		$('#DIVaquisicao').css('display','none'); $('#DIVsimulacao').css('display','none');
		$('#DIVsemaquisicao').css('display','block'); $('#DIVaparelho').css('display','block');
		$('#DIVcdma').css('display','block');
		$("#FORMestado").attr('disabled',false);
		$("#FORMddd").attr('disabled',false);
		$("#FORMmotivo").attr('disabled',false);
		if( $("#DIVuf").css('display')=='block' ) $("#DIVuf").hide();
		if( $("#DIVddd").css('display')=='block' ) $("#DIVddd").hide();
	});
	
});