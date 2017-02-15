$(document).ready(function() {
	
	//Escolha de Número
	
	
	$('#portabilidade_s_protocolo').click(function() {
		$('#portabilidade-editar').css('display','block');
		$('#portabilidade-buscar').hide();
		$('#portabilidade-form').attr('action','UC041-TrocarNumero-Resumo_de_Solitacao.html');
		$('#numeros-disponiveis').hide();
		$('#botao-externo').hide();
		$('#portabilidade-protocolo').hide();
		
		$('.alvo01').show();
		$('.alvo02').show();
		$('.alvo03').hide();
		$('.alvo04').hide();
		$('.alvo05').hide();
		$('.alvo06').show();
		$('.alvo07').show();
		$('.alvo08').show();
		$('.alvo09').show();	
		$('.alvo10').show();	
		
		$('#resultado-numeros').hide();
		
		$('#numeros-disponiveis').hide();
		$('#portabilidade-protocolo').hide();
	});
	

	$('#s_portabilidade').click(function() {

		$('#portabilidade-editar').hide();
		$('#portabilidade-buscar').show();

		$('.alvo01').show();
		$('.alvo02').show();
		$('.alvo03').show();
		$('.alvo04').show();
		$('.alvo05').show();
		$('.alvo06').hide();
		$('.alvo07').hide();
		$('.alvo08').hide();
		$('.alvo09').hide();		
		$('.alvo10').hide();	
		
		$('#numeros-disponiveis').hide();
		$('#portabilidade-protocolo').hide();
		$('#botao-externo').show();
																			 
	});
	
	$('#portabilidade_c_protocolo').click(function() {
												
		$('#portabilidade-editar').hide();
		$('#portabilidade-buscar').show();
		
		$('.alvo01').show();
		$('.alvo02').show();
		$('.alvo03').hide();
		$('.alvo04').hide();
		$('.alvo05').hide();
		$('.alvo06').show();
		$('.alvo07').hide();
		$('.alvo08').hide();
		$('.alvo09').hide();	
		$('.alvo10').hide();
		
		$('#numeros-disponiveis').hide();
		$('#portabilidade-protocolo').hide();	
		$('#botao-externo').show();
																			 
	});															 
	

	$('.BTescolhanumero').click(function() {
		if ($('#s_portabilidade').attr('checked')==true) {
			$('#numeros-disponiveis').css('display','block');
			$('#botao-externo').show();	
			$('#portabilidade-protocolo').hide();
			jQuery('#TabelaNumerosDisponiveis').Scrollable(150, 820);
		}
 
		if ($('#portabilidade_c_protocolo').attr('checked')==true) {
			$('#numeros-disponiveis').hide();
			$('#botao-externo').show();			
			$('#portabilidade-protocolo').css('display','block');
			jQuery('#TabelaPortabilidadeProtocolo').Scrollable(150, 820);				
			
		}

	});

	

});
