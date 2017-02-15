function selecionar() {
    if(idContatoSel == '') {
        alert('Nenhum item (folha) da árvore de contato foi selecionado!');
    } else {    
        parent.document.getElementById('idContato').value = idContatoSel;
        parent.document.getElementById('textoContato').value = dscContatoSel;
        parent.dvArvore.style.display = 'none';
        parent.ativar_combos();
    }
}
function cancelar() {
    parent.dvArvore.style.display = 'none';
    parent.ativar_combos();
}
function selecionaContato(idC, inF, dscC,idPai) {
    if(idPai!="PAI"){
        if(inF == 1) {
            idContatoSel = idC;
            dscContatoSel = dscC;
        } else {
            idContatoSel = '';
            dscContatoSel = '';
            expandirNo(idC);
        }
    }
}
function expandirNo(idC){
    if (!tree.getSelected().isAddEnabled()) { return; }
    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();   
    document.forms[0].idContato.value = idC;
    document.forms[0].target = "ifrmArvoreContato";
    document.forms[0].action = "expandeArvoreContato.do?IDCONTATO="+idC;
    document.forms[0].submit(); 
}
function itemDblClick(idContato, inFolha, dscContato) {
    idContatoSel=idContato;
    dscContatoSel=dscContato;
    selecionar();
}
