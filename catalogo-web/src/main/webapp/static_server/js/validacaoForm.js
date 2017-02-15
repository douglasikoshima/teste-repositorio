var validacoes = {
		"obrigatorio": function(elemento){

			var type = ($jq(elemento).attr('type')?$jq(elemento).attr('type').toUpperCase() : '' );
			if($jq(elemento).val() == ''){

				return mensagens.E01;
			}else if($jq(elemento).is('select') && $jq(elemento).val() == 'DEFAULT'){

				return mensagens.E01;
			}else if((type== 'RADIO'|| type == 'CHECKBOX')&&
					$jq('[name="'+$jq(elemento).attr('name')+'"]:checked').size()==0){

				return mensagens.E01;
			}
			return null;
		},
		"campoEmail": function(elemento){

			var er = RegExp(/(^([a-zA-Z]+[a-zA-Z0-9_.-]+@{1}[a-zA-Z0-9_.-]*\.+[a-z]{2,4})(;([a-zA-Z0-9]+[a-zA-Z0-9_.-]+@{1}[a-zA-Z0-9_.-]*\.+[a-z]{2,4})){0,})/);
			if($jq(elemento).val()!='' && !er.test($jq(elemento).val())){

				return mensagens.E02;
			}
			return null;
		},
		"dataDDMMYYYY": function(elemento){

			if($jq(elemento).val() == ''){

				return null;
			}
			var data = $jq(elemento).val().split('/');

			if(data.length!=3){
				return mensagens.E03;
			}
			var date = new Date(data[2],data[1]-1,data[0]);

			if(date.getFullYear() != data[2] || date.getMonth()!= parseInt(data[1],10)-1 || date.getDate() != data[0]){
				return mensagens.E03;
			}
			return null;
		},
		"afterDate": function(data1, data2){

			if(!(data1 instanceof Date)){

				var data = data1.split('/');
				data1 = new Date(data[2],data[1]-1,data[0]);
			}
			if(!(data2 instanceof Date)){

				var data = data2.split('/');
				data2 = new Date(data[2],data[1]-1,data[0]);
			}
			return data1.getTime() > data2.getTime();
		},
		"beforeDate": function (data1, data2){

			if(!(data1 instanceof Date)){

				var data = data1.split('/');
				data1 = new Date(data[2],data[1]-1,data[0]);
			}
			if(!(data2 instanceof Date)){

				var data = data2.split('/');
				data2 = new Date(data[2],data[1]-1,data[0]);
			}
			return data1.getTime() < data2.getTime();
		},
		"maximo": function(elemento){

			var maximo = $jq.parseJSON($jq(elemento).attr("validation")).maximo;
			if(parseInt($jq(elemento).val(),10)>maximo){

				return mensagens.E04+ ' '+ maximo;
			}
		},
		"minimo": function(elemento){

			var minimo = $jq.parseJSON($jq(elemento).attr("validation")).minimo;
			if(parseInt($jq(elemento).val(),10)<minimo){

				return mensagens.E05+ ' '+ minimo;
			}
		},
		"comparaMenorMaior": function(element){

			var menor = 0;
			var maior = 0;
			$jq('[validation]').each(function(){

				var json = $jq.parseJSON($jq(this).attr('validation'));
				if(json.comparaMenorMaior && json.comparaMenorMaior == 'maior'){

					maior = parseInt($jq(this).val(),10);
				}
				if(json.comparaMenorMaior && json.comparaMenorMaior == 'menor'){

					menor = parseInt($jq(this).val(),10);
				}
			});
			if((menor && maior) && (menor > maior)){

				return mensagens.E06;
			}
		}
};

function validarCampos(contexto){

	var ok = true;
	var msg = {"obrigatorio": null,"campoEmail":null,"dataDDMMYYYY":null};
	esconteMSGs();
	$jq('[validation]:visible',contexto).each(function (){

		var elemento = this;
		var checagens = $jq.parseJSON($jq(this).attr('validation'));
		var type = ($jq(this).attr('type')?$jq(this).attr('type').toUpperCase() : '' );
		$jq.each(checagens, function(key, value){

			var pinta = false;
			if(value || value == 0){

				if(validacoes[key](elemento)){

					msg[key]= validacoes[key](elemento);
					pinta = true;
					ok=false;
				}
			}
			if(pinta){

				if(type == 'RADIO' || type == 'CHECKBOX'){

					$jq(elemento).parent().addClass('red');
					$jq(elemento).parent().attr('onclick','$jq(\'[name="'+$jq(elemento).attr('name')+'"]\').parents().removeClass("red");');
				}else{

					$jq(elemento).addClass('red');
					$jq(elemento).attr('onfocus','$jq(this).removeClass("red");');
				}
			}
		});
	});

	if(!ok){

		var mensagem = new String();
		$jq.each(msg,function(key,value){

			if(value){
				mensagem += value+'<br/>';
			}
		});
		$jq('#conteudo_divErros').html(mensagem);
		$jq('#divErros').show('fast');
	}
	staticMaps.camposValidado=ok;
	return ok;
}
function esconteMSGs(){

	$jq('#divErros').hide('fast');
}