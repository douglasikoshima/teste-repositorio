/*
*
*/

stepsOnKeyDown = function() {
    var tecla   = window.event.keyCode;
    var control = window.event.ctrlKey;
    var alt     = window.event.altKey;
    var tipo    = event.type;

    //Teste de Teclas de Função F1(112)-F12(123)
    if(tecla>=112 && tecla<=123){ //F4
        switch(tecla){
            case 112: //F1
            case 113: //F2
            case 114: //F3
            case 115: //F4
            case 116: //F5
            case 117: //F6
            case 118: //F7
            case 119: //F8
            case 120: //F9
            case 121: //F10
            case 122: //F11
            case 123: //F12
                window.event.keyCode = 0;
                return false;
                break;
        }
    }
    if(alt){
        switch(tecla){
            case 115:// Alt-F4
                window.event.keyCode = 0;
                top.frameApp.fecharSessao();
                return false;
                break;
        }
    }else if(control){
        switch(tecla){
            case 78://n
            case 87://w
            case 82://r
            case 73://i
            case 68://d
            case 72://h
            case 76://l
            case 79://o
            case 66://b
            case 69://e
                window.event.keyCode = 0;
                return false;
                break;
        }
    }

}

stepsOnBeforeOnload = function(){
    if(window.event != null){
        var abssize = 0;
        if (document.body) abssize = document.body.offsetWidth-30;
        if((window.event.clientY < 0) && (event.clientX >= abssize)){ // X no canto superior direito
            top.frameApp.fecharSessao();
        }
        if((window.event.clientY < 0) && (event.clientX <= 20)){ // duplo-clique no canto superior esquerdo
            top.frameApp.fecharSessao();
        }
    }
}

stepsOnLoad = function(){
    window.history.forward(-1);
    window.moveTo(0,0);
    window.resizeTo(screen.availWidth,screen.availHeight);
    if(window.frames && window.frames[0]){
        window.frames[0].focus();
        for(var i_tem = 0; i_tem < window.frames.length; i_tem++){
            window.frames[i_tem].document.onkeydown = function(){top.frameApp.stepsOnKeyDown()};
            window.frames[i_tem].window.onbeforeunload = function(){top.frameApp.stepsOnBeforeOnload};
        }
    }
}

document.onkeydown = stepsOnKeyDown;
window.onbeforeunload = stepsOnBeforeOnload;

/*document.observe("dom:loaded", function() {
    if (document.body && $('debugWindowBody')) {
        $('debugWindowBody').value = document.body.innerHTML;
    }
    new HotKey('d',function(event){
        if (!$('debugWindow').visible()) {
            $('debugWindow').show();
        } else {
            $('debugWindow').hide();
        }
    },{
        shiftKey: true,
        altKey: true
    });
});*/