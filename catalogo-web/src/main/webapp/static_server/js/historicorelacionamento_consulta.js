$(document).ready(function() {
	
	//Histórico de relacionamento
	
	$('#optlinha').click(function() {
		$('#historico_relacionamento').hide();
	});


	$('#optcliente').click(function() {
		$('#historico_relacionamento').hide();
	});
	
													 
	$('.BTbuscahistorico').click(function() {
	
		if ($('#optcliente').attr('checked')==true) {
			$('#historico_relacionamento').css('display','block');
		}
 
		if ($('#optlinha').attr('checked')==true) {
			$('#historico_relacionamento').css('display','block');

		}
		jQuery('#TabelaHistoricoRelacionamento').Scrollable(150, 820);
	
	});


});
