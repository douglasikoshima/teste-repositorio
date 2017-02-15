function soli() {
    if (document.getElementById('imgConsultarGestor')) {
        document.getElementById('imgConsultarGestor').style.display = 'none';
    }
    abaSelected(btAba0, btPP);
    DoResizeHeaderTableVivo();
    dvUCP.style.display='';
    dvFProc.style.display='none';
    dvCont.style.display='none';
}

function frm() {
    if (document.getElementById('imgConsultarGestor')) {
        document.getElementById('imgConsultarGestor').style.display = 'none';
    }
    abaSelected(btAba0, btPF);
    dvUCP.style.display='none';
    dvCont.style.display='none';
    dvFProc.style.display='';
}

function cnt() {
    if (document.getElementById('imgConsultarGestor')) {
        document.getElementById('imgConsultarGestor').style.display = 'block';
    }
    abaSelected(btAba0, btPC);
    dvUCP.style.display='none';
    dvFProc.style.display='none';
    dvCont.style.display='';
    DoResizeHeaderTableVivo();
}