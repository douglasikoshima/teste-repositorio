var arrayB2B = new Array('00','05','10','15','20','25','35');
var arrayB2C = new Array('30','40','55');

manageCanaisVenda = function(v) {
    $('inConsolidado').checked = false;
    var i;
    var arrayCanais = eval(v);
    for (i = 0; i < $('canaisVendaSelecionados').length; i++)
        $('canaisVendaSelecionados').options[i].selected = true;
    transfereSelecaoLista($('canaisVendaSelecionados'), $('canaisVendaDisponiveis'), false, false, true);
    for (i = 0; i < $('canaisVendaDisponiveis').length; i++) {
        if (arrayCanais.toString().indexOf($('canaisVendaDisponiveis').options[i].value) >= 0) {
            $('canaisVendaDisponiveis').options[i].selected = true;
        }
    }
    transfereSelecaoLista($('canaisVendaDisponiveis'), $('canaisVendaSelecionados'), false, false, true);
}

isAllSelected = function() {
    if ($('regionaisDisponiveis').length == 0
        && $('canaisVendaDisponiveis').length == 0
        && $('inPremium').checked
        && $('inMassivo').checked) {
        return true;
    }
    return false;
}

transfereSelecaoLista = function(listaOrigem, listaDestino, inverse, flAll, inAgrupador){
    var i;
    if (inverse) {
        for(i = 0; i<listaDestino.options.length; i++) {
            if (flAll) listaDestino.options[i].selected = true;
                if(listaDestino.options[i].selected)
                    listaOrigem.options[listaOrigem.options.length] = new Option(listaDestino.options[i].text, listaDestino.options[i].value);
            }
        for(i = listaDestino.options.length-1; i>=0; i--)
            if(listaDestino.options[i].selected)
                listaDestino.options[i] = null;
    } else {
        for(i = 0; i<listaOrigem.options.length; i++) {
            if (flAll) listaOrigem.options[i].selected = true;
            if(listaOrigem.options[i].selected)
                listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
        }
        for(i = listaOrigem.options.length-1; i>=0; i--)
            if(listaOrigem.options[i].selected)
                listaOrigem.options[i] = null;
    }
    setConsolidado();
    if (!inAgrupador) {
        removerAgrupador();
    }
}

submitPesquisa = function() {
    var aFields = new Array();
    if (!validaData($F('dtPeriodoDe')))
         aFields[aFields.length] = 'Data';
    if (!$('RDPA').checked && !$('RSPA').checked && !$('RDOL').checked)
        aFields[aFields.length] = $('RDPA').title;
    if ($('regionaisSelecionadas').length == 0)
        aFields[aFields.length] = $('regionaisSelecionadas').title;
    if ($('canaisVendaSelecionados').length == 0)
        aFields[aFields.length] = $('canaisVendaSelecionados').title;
    if (!$('inPremium').checked && !$('inMassivo').checked)
        aFields[aFields.length] = $('inPremium').title;
    if (aFields.length==0) {
        var f = document.forms[0];
        selectAllListOptions($('canaisVendaSelecionados'));
        selectAllListOptions($('regionaisSelecionadas'));
        if(window.top.frameApp.dvAnimarAguarde) window.top.frameApp.startAnimation();
        f.submit();
    } else
        formMessage(aFields);
}

removerAgrupador = function(event) {
    $('arrayB2B').checked = false;
    $('arrayB2C').checked = false;
}

setConsolidado = function(event) {
    if (!isAllSelected()) {
        $('inConsolidado').checked = false;
    } else {
        $('inConsolidado').checked = true;
        removerAgrupador();
    }
}

manageConsolidado = function(objBool) {
    var boolean, boolInverse;
    if (objBool == true) {
        $('arrayB2B').checked = false;
        $('arrayB2C').checked = false;
        boolean = true;
        boolInverse = false;
    } else {
        boolInverse = true;
        boolean = false;
    }
    transfereSelecaoLista($('canaisVendaDisponiveis'), $('canaisVendaSelecionados'), boolInverse, true, false);
    transfereSelecaoLista($('regionaisDisponiveis'), $('regionaisSelecionadas'), boolInverse, true, false);
    $('inMassivo').checked = boolean;
    $('inPremium').checked = boolean;
    $('inConsolidado').checked = boolean;
}

validaDataInput = function(data){
    if(data.value == '')
        return false;
    if(!validate_date(data.value)){
        data.value = '';
        data.focus();
        alert("Data inválida");
    }
}

selectAllListOptions = function(o) {
    if (o) {
        for (var i = 0; i < o.options.length; i++) {
            o.options[i].selected = true;
        }
    }
}

limparDatas = function() {
    $('dtPeriodoDe').value = "";
    $('RDPA').checked = false;
    $('RSPA').checked = false;
    $('RDOL').checked = false;
}

replaceAll = function(string, token, newtoken) {
	while (string.indexOf(token) != -1) {
 		string = string.replace(token, newtoken);
	}
	return string;
}

paginacao = function(nrPagina) {
    var f = document.forms[0];
    f.pageNumber.value = nrPagina;
    if(window.top.frameApp.dvAnimarAguarde) window.top.frameApp.startAnimation();
    f.submit();
}