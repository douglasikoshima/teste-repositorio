//Biblioteca tratamento
//Autor: Renato Caires de Oliveira

function ValidarTeclaURL()
{
	var tk;
	var c;

	// Recebe a tela pressionada
	tk = window.event.keyCode;
	c=String.fromCharCode(tk);
	c=c.toUpperCase();
	
	// Só aceita teclas alfanuméricas. Não aceita teclas de controle
	if ((c<"A" || c>"Z") && (c<"0" || c>"9") && c!="/" && c!=":" && c!="." && c!="&" && c!="?" && c!="_" && c!="(" && c!=")" && c!="@" && c!=" ")
		window.event.keyCode = 0;

	return window.event.keyCode;
}    


function verificaEnter(ev)
{
    if(ev.keyCode == 13)
        return false;
    else
        return true;    
}
