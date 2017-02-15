    function formatarCPF(campo, teclaPres)
    {
        var tecla = teclaPres.keyCode;
        vr = "";

        for (i = 0 ; i < campo.value.length ; i++)
        {
            if (campo.value.charAt(i) != "." && campo.value.charAt(i) != "-" && campo.value.charAt(i) != "/")
            {
                vr=vr + campo.value.charAt(i);
            }
        }

        tam = vr.length ;
        campo.maxLength=14;

        if (tam < 15 && tecla != 8)
        {
            tam = vr.length + 1;
        }

        if (tecla == 8)
        {
            tam = tam - 1;
        }

        if (tecla == 8 || tecla == 88 || tecla >= 48 && tecla <= 57 || tecla >= 96 && tecla <= 105)
        {
            if (tam <= 2) { campo.value = vr ;}
            if ((tam > 2) && (tam <= 5) ){ campo.value = vr.substr( 0, tam - 2 ) + '-' + vr.substr( tam - 2, tam ) ; }
            if ((tam >= 6) && (tam <= 8) ){ campo.value = vr.substr( 0, tam - 5 ) + '.' + vr.substr( tam - 5, 3 ) + '-' + vr.substr( tam - 2, tam ) ; }
            if ((tam >= 9) && (tam <= 11) ){campo.value = vr.substr( 0, tam - 8 ) + '.' + vr.substr( tam - 8, 3 ) + '.' + vr.substr( tam - 5, 3 ) + '-' + vr.substr( tam - 2, tam ) ;}
        }

    } /* formatarCPF */


    function CPFFormatado(campo)
    {

        var numValor = new String(campo.value);

        if (numValor.length == 11)
        {

            re = /^(\d{3})\.?(\d{3})\.?(\d{3})\-?(\d{2})$/;
            numCPF = re.exec(numValor);

            if (numCPF)
            {
                numValor = numCPF[1] + "." + numCPF[2] + "." + numCPF[3] + "-" + numCPF[4];
            }

            campo.value = numValor;

            return;

        }
        else if (numValor.length < 11 && numValor.length != 0)
        {

            zeros  = 11 - numValor.length;

            for (intCount = 1 ; intCount <= zeros ; intCount++)
            {
                numValor = '0' + numValor;
            }

            re = /^(\d{3})\.?(\d{3})\.?(\d{3})\-?(\d{2})$/;
            numCPF = re.exec(numValor);

            if (numCPF)
            {
                numValor = numCPF[1] + "." + numCPF[2] + "." + numCPF[3] + "-" + numCPF[4];
            }

            campo.value = numValor;

            return;

        }
        else if(numValor.length > 11)
        {

            re = /^(\d{3})\.?(\d{3})\.?(\d{3})\-?(\d{2})$/;
            numCPF = re.exec(numValor);

            if(numCPF)
            {
                numValor = numCPF[1] + "." + numCPF[2] + "." + numCPF[3] + "-" + numCPF[4];
            }

            campo.value = numValor;

            return;
        }

        return;

    } /* CPFFormatado */


    function mascaraData(campoData)
    {
        if(event.keyCode<48 || event.keyCode>57){
            event.returnValue=false;
        }

        var data = campoData.value;
        if (data.length == 2)
        {
            data = data + '/';
            campoData.value = data;
            return true;
        }
        if (data.length == 5){
            data = data + '/';
            campoData.value = data;
            return true;
        }
        if (data.length > 9) {
            event.returnValue = false;
        }
    }

    function Mascara_Telefone(objeto){

       if(event.keyCode<48 || event.keyCode>57)
            event.returnValue=false;

       if(objeto.value.length == 0)
            objeto.value = '(' + objeto.value;

       if(objeto.value.length == 3)
            objeto.value = objeto.value + ')';

       if(objeto.value.length == 8)
            objeto.value = objeto.value + '-';

       if(objeto.value.length == 13)
            event.returnValue=false;
    }

    function Mascara_DataHora(evento, objeto){
        var keypress=(window.event)?event.keyCode:evento.which;
        campo = eval (objeto);
        if (campo.value == '00/00/0000 00:00:00')
            campo.value=""

        caracteres = '0123456789';
        separacao1 = '/';
        separacao2 = ' ';
        separacao3 = ':';
        conjunto1 = 2;
        conjunto2 = 5;
        conjunto3 = 10;
        conjunto4 = 13;
        conjunto5 = 16;

        if ((caracteres.search(String.fromCharCode (keypress))!=-1) && campo.value.length < (19)) {
            if (campo.value.length == conjunto1 )
                campo.value = campo.value + separacao1;
            else if (campo.value.length == conjunto2)
                campo.value = campo.value + separacao1;
            else if (campo.value.length == conjunto3)
                campo.value = campo.value + separacao2;
            else if (campo.value.length == conjunto4)
                campo.value = campo.value + separacao3;
            else if (campo.value.length == conjunto5)
                campo.value = campo.value + separacao3;
        }
        else
            event.returnValue = false;
    }

    function Mascara_Moeda(objTextBox, e) {

        var sep = 0;
        var key = '';
        var i = j = 0;
        var len = len2 = 0;
        var strCheck = '0123456789';
        var aux = aux2 = '';
        var SeparadorMilesimo = '.';
        var SeparadorDecimal = ',';

        var keypress=(window.event)?event.keyCode:evento.which;
        if (keypress == 13) return true;

        key = String.fromCharCode(keypress); // Valor para o código da Chave
        if (strCheck.indexOf(key) == -1) return false; // Chave inválida
        len = objTextBox.value.length;
        for(i = 0; i < len; i++)
            if ((objTextBox.value.charAt(i) != '0') && (objTextBox.value.charAt(i) != SeparadorDecimal)) break;
        aux = '';
        for(; i < len; i++)
            if (strCheck.indexOf(objTextBox.value.charAt(i))!=-1) aux += objTextBox.value.charAt(i);
        aux += key;
        len = aux.length;
        if (len == 0) objTextBox.value = '';
        if (len == 1) objTextBox.value = '0'+ SeparadorDecimal + '0' + aux;
        if (len == 2) objTextBox.value = '0'+ SeparadorDecimal + aux;
        if (len > 2) {
            aux2 = '';
            for (j = 0, i = len - 3; i >= 0; i--) {
                if (j == 3) {
                    aux2 += SeparadorMilesimo;
                    j = 0;
                }
                aux2 += aux.charAt(i);
                j++;
            }
            objTextBox.value = '';
            len2 = aux2.length;
            for (i = len2 - 1; i >= 0; i--)
            objTextBox.value += aux2.charAt(i);
            objTextBox.value += SeparadorDecimal + aux.substr(len - 2, len);
        }
        return false;
    }

    function numeros(event) {
        var Key = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;

        if(Key == 13 || Key == 46 || (Key >= 48 && Key <=57))
            return true;
        else {
            if (Key != 8)
                return false;
            else
                return true;
        }
    }

    function inteiro(event) {
        var Key = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;

        if(Key == 46 || Key == 44)
            return false;
        if(Key == 13 ||(Key >= 48 && Key <=57))
            return true;
        else {
            if (Key != 8)
                return false;
            else
                return true;
        }
    }

    function valida_horas(edit) {
        if(event.keyCode<48 || event.keyCode>57){
            event.returnValue=false;
        }
        if(edit.value.length==2 || edit.value.length==5){
            edit.value+=":";
        }
        if(edit.value.length > 7) {
            event.returnValue = false;
        }
    }