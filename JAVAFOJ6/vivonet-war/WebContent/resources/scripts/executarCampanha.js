function atendimentoCampanha_executarCampanha() {
    if (document.forms.viewScriptActionForm.elements["idListaConteudo"].value == "0"
            || isNaN(document.forms.viewScriptActionForm.elements["idListaConteudo"].value)) {
        alert("Nenhum número selecionado para esta operação!");
    } else {
        document.getElementById("lyrBotoes").style.display = "none";
        document.getElementById("lyrBotaoVoltar").style.display = "block";
        frames.frmQuest.document.forms.viewScriptActionForm.submit();
        frames.frmQuest.document.forms.viewScriptActionForm.target = "";
        top.frameApp.mostrar_div();
    }
}

function atendimentoCampanha_ligarManualmente()
{

    var valida = false;

    var objOpt  = frames.frmQuest.document.forms.viewScriptActionForm.elements["campanha"];

    var objOpt1 = frames.frmQuest.document.forms.viewScriptActionForm.elements["idSubCampanhaHistoricoSel"];

    var idCanalCampanha = -1;

    if (objOpt){
        if(objOpt.length)
        {
            for(i=0; i < objOpt.length; i++){
                if(objOpt[i].checked == true){
                    valida = true;
                    idCanalCampanha = objOpt[i].value;
                }
            }
        }
        else
        {
            valida = objOpt.checked;
            idCanalCampanha = objOpt.value;
        }
    }
    if (valida == false){
        alert("Favor selecionar uma campanha!");
    }
    else
    {

        document.forms.ligarManualmenteForm.campanha.value = idCanalCampanha;
        document.forms.ligarManualmenteForm.idSubCampanhaHistoricoSel.value = objOpt1.value;
        document.forms.ligarManualmenteForm.target = "";
        document.forms.ligarManualmenteForm.submit();

        top.frameApp.mostrar_div();
    }
}


function campanhasRelacionadas_selecionar()
{
    var objSel = document.forms["viewScriptActionForm"].elements["campanha"];
    var objTxt1 = document.forms["viewScriptActionForm"].elements["idSubCampanhaHistorico"];
    var objTxt2 = document.forms["viewScriptActionForm"].elements["idSubCampanhaHistoricoSel"];

    if(objSel && objSel.length)
    {
        var x = -1;

        for(i = 0; i < objTxt1.length; i++)
        {
            if(objSel[i].checked)
            {
                x = i;
            }
        }
        if(x > -1)
        {
            objTxt2.value = objTxt1[x].value;
        }
    }
    else if(objSel)
    {
        objTxt2.value = objTxt1.value;
    }
}




function campanhasRelacionadas_manterChecado(par_idCampanha)
{
    //Manter campanha selecionada
    var objCampTemp = document.forms["viewScriptActionForm"].elements["campanha"];
    if(objCampTemp && objCampTemp.length)
    {
        for(i = 0; i < objCampTemp.length; i ++)
        {
            if(objCampTemp[i].value == par_idCampanha)
            {
                objCampTemp[i].checked  = "true";
                break;
            }
        }
    }
    else if(objCampTemp)
    {
        if(objCampTemp.value == par_idCampanha)
        {
            objCampTemp.checked  = "true";
        }
    }
}


function campanhasRelacionadasTelaInicial_selecionar()
{
    var objSel       = document.forms["viewScriptActionForm"].elements["campanha"];

    var objSubCam    = document.forms["viewScriptActionForm"].elements["idSubCampanhaHistorico"];

    var objSubCamSel = document.forms["viewScriptActionForm"].elements["idSubCampanhaHistoricoSel"];

    var objLstCot    = document.forms["viewScriptActionForm"].elements["idListaConteudo"];

    var objLstCotSel = document.forms["viewScriptActionForm"].elements["idListaConteudoSel"];

    if(objSel && objSel.length)
    {
        var x = -1;

        for(i = 0; i < objSubCam.length; i++)
        {
            if(objSel[i].checked)
            {
                x = i;
            }
        }
        if(x > -1)
        {
            objSubCamSel.value = objSubCam[x].value;
            objLstCotSel.value = objLstCot[x].value;
        }
    }
    else if(objSel)
    {
        objSubCamSel.value = objSubCam.value;
        objLstCotSel.value = objLstCot.value;
    }

    document.forms["viewScriptActionForm"].submit();
     parent.mostrar_div();
}




function iFrameObtemListas_copiarCombo()
{
        var aOptComponent = new Array(document.forms[0].elements["motivoDispSelecionado"],document.forms[0].elements["subMotivoDispSelecionado"]);

        var aOptComponentsParent = new Array(parent.document.forms["obtemCampanhaListaActionForm"].elements["motivoDispSelecionado"],
                                             parent.document.forms["obtemCampanhaListaActionForm"].elements["subMotivoDispSelecionado"]);


        for(i = 0; i < aOptComponentsParent.length; i++)
        {

           while(aOptComponentsParent[i].options.length != 0)
                aOptComponentsParent[i].options.remove(0);

           for( x = 0; x < aOptComponent[i].options.length; x++ ) {
                aOptComponent[i].options[x].selected;
                oOption = parent.document.createElement("OPTION");
                aOptComponentsParent[i].options.add(oOption);
                oOption.innerText = aOptComponent[i].options(x).text;
                oOption.value     = aOptComponent[i].options(x).value;
                oOption.selected     = aOptComponent[i].options(x).selected;
            }
        }
}



function terminoCampanha_consultaIFrameMotivo() {

     var idStatus = document.forms["obtemCampanhaListaActionForm"].statusDispSelecionado.options[document.forms["obtemCampanhaListaActionForm"].statusDispSelecionado.selectedIndex].value;


     if (idStatus == "")
     {
        document.forms["obtemCampanhaListaActionForm"].motivoDispSelecionado.options.length = 0;
        document.forms["obtemCampanhaListaActionForm"].subMotivoDispSelecionado.options.length = 0;
     }
     else
     {
         //Monta o path seleção
         document.forms["obtemCampanhaListaActionForm"].target = "iframe";
         document.forms["obtemCampanhaListaActionForm"].action = "/FrontOfficeWeb/campanha/ExecutarCampanha/obtemMotivos.do?acao=status&idStatus="+idStatus;
         document.forms["obtemCampanhaListaActionForm"].submit();
         top.frameApp.mostrar_div();

         //se status igual pendente ou vai pensar
         if(idStatus == "1" || idStatus == "3")
         {
            document.getElementById("btnCamTercaAgen").style.display = "block";
            document.getElementById("btnGravar").style.display = "none";
         }
         else
         {
            document.getElementById("btnCamTercaAgen").style.display = "none";
            document.getElementById("btnGravar").style.display = "block";
         }
     }
}

function terminoCampanha_consultaIFrameSubMotivo() {

     var idStatus = document.forms["obtemCampanhaListaActionForm"].statusDispSelecionado.options[document.forms["obtemCampanhaListaActionForm"].statusDispSelecionado.selectedIndex].value;
     var idSubMotivo = document.forms["obtemCampanhaListaActionForm"].motivoDispSelecionado.options[document.forms["obtemCampanhaListaActionForm"].motivoDispSelecionado.selectedIndex].value;

    if(idSubMotivo == "")
    {
        document.forms["obtemCampanhaListaActionForm"].subMotivoDispSelecionado.options.length = 0;
    }
    else
    {
         //Monta o path seleção
         document.forms["obtemCampanhaListaActionForm"].target = "iframe";
         document.forms["obtemCampanhaListaActionForm"].action = "/FrontOfficeWeb/campanha/ExecutarCampanha/obtemSubMotivos.do?acao=motivo&idStatus=" + idStatus + "&idMotivo="+idSubMotivo;
         document.forms["obtemCampanhaListaActionForm"].submit();
         top.frameApp.mostrar_div();
     }
}

function terminoCampanha_salvar(){
    if(document.forms.obtemCampanhaListaActionForm.elements["statusDispSelecionado"].value == "")
    {
        alert("Favor selecionar um Status!");
    }
    else if(document.forms.obtemCampanhaListaActionForm.elements["motivoDispSelecionado"].value == "")
    {
        alert("Favor selecionar um Motivo!");
    }
    else if(document.forms.obtemCampanhaListaActionForm.elements["subMotivoDispSelecionado"].value == "")
    {
        alert("Favor selecionar um Sub Motivo!");
    }
    else
    {
        document.forms.obtemCampanhaListaActionForm.action = "/FrontOfficeWeb/campanha/ExecutarCampanha/obtemCampanhaListaAction.do?acao=salvar";
        document.forms.obtemCampanhaListaActionForm.target = "_self";
        document.forms.obtemCampanhaListaActionForm.submit();
        top.frameApp.mostrar_div();
    }
}

function terminoCampanha_agendar()
{
    var mt = document.forms.obtemCampanhaListaActionForm.elements["motivoDispSelecionado"].value;
    var sb = document.forms.obtemCampanhaListaActionForm.elements["subMotivoDispSelecionado"].value;

    if(mt == "")
    {
        alert("Favor selecionar um Motivo!");
    }
    else if(sb == "")
    {
        alert("Favor selecionar um Sub Motivo!");
    }
    else
    {
        var st = document.forms.obtemCampanhaListaActionForm.elements["statusDispSelecionado"].value;
        window.location.href =
   "/FrontOfficeWeb/campanha/AgendaContato/begin.do?status=" + st + "&motivo=" + mt + "&sub=" + sb;
        top.frameApp.mostrar_div();
    }
}