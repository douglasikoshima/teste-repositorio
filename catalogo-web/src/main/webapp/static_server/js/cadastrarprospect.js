$(document).ready(function() {

	$('#tipoDocumento').change(function() { 
		
		$('#DIVtipocontato').show();
		$('#DIVdocnumLabel').show();
		$('#DIVdocnumForm').show();
		
		valor = $('#tipoDocumento').val();
		if(valor=='cpf') { mascara = varMascaraCPF; $('#DIVdados').hide(); $('#DIVpessoa').show(); $('#DIVempresa').hide(); }
		if(valor=='rg') { mascara = varMascaraRG; $('#DIVdados').show(); $('#DIVpessoa').show(); $('#DIVempresa').hide(); }
		if(valor=='rne') { mascara = varMascaraRNE; $('#DIVdados').show(); $('#DIVpessoa').show(); $('#DIVempresa').hide(); }
		if(valor=='passaporte') { mascara = varMascaraPassaporte; $('#DIVdados').hide(); $('#DIVpessoa').show(); $('#DIVempresa').hide(); }
		if(valor=='cnpj') { mascara = varMascaraCNPJ; $('#DIVdados').hide(); $('#DIVpessoa').hide(); $('#DIVempresa').show(); }
		
		if(!valor) { 
		$('#DIVdados').hide();
		$('#DIVpessoa').hide();
		$('#DIVempresa').hide();
		$('#DIVtipocontato').hide();
		$('#DIVdocnumLabel').hide();
		$('#DIVdocnumForm').hide();
		 }
	
		$('#documentoNumero').mask(mascara);
	});	
	
	$('#tipoContato').change(function() { 
		valor = $('#tipoContato').val();
		if(valor=='Telefone Celular') mascara = varMascaraNumeroTelefone;
		if(valor=='Telefone Comercial') mascara = varMascaraNumeroTelefone;
		if(valor=='Telefone de Recado') mascara = varMascaraNumeroTelefone;
		if(valor=='Telefone Residencial') mascara = varMascaraNumeroTelefone;
		if(valor=='E-mail') mascara = '';
		$('#descricaoContato').mask(mascara);
	});	
	
});	

