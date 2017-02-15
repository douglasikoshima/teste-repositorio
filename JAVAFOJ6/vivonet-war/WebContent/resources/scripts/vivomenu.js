/**
 * This file content the javascript rotines to handler the menus.
 * @author Alexandre Nunes
 * @version 1.0, 20/04/2004
 */

/*
 * Global variables
 */
var mnActived;      //the current actived menu
var menuMax = 0;    //controle do menu para max/min default true
var clickMenu = 0;  //Analise click menu
var currentMenuMin; //Identificador do menu min
var currentMenuMax; //Identificador do menu max

/*
 * Functions
 */

/**
 *  Controla o maximiza/minimiza do menu
 */
function mudarMenu(mnMin, mnMax) {
    //Cria a expressão pesquisa
    var stringSearch;
    stringSearch = /none/i;
    
    //Maximiza ou Minimiza
    if( mnMin.style.display.search(stringSearch) == 0 )  {
        menuMax = 1;    //false
        mnMin.style.display = '';
        mnMax.style.display = 'none';
    } else {
        menuMax = 0;    //true
        mnMin.style.display = 'none';
        mnMax.style.display = '';
    }

    //Menus selecionados para max/min
    currentMenuMin = mnMin;
    currentMenuMax = mnMax;
}

function mudarMenuMin(mnMin, mnMax) {
    if( menuMax == 0 ) return;
    mnMin.style.display = 'none';
    mnMax.style.display = '';
}

function mudarMenuMax(mnMin, mnMax) {
    if( menuMax == 0 ) return;
    if( clickMenu == 1 ) return;
    mnMin.style.display = '';
    mnMax.style.display = 'none';
}

function mudarMenuMinMax() {
    //Click menu
    clickMenu = 0;

    //Menus selecionados para max/min
    if( currentMenuMin && currentMenuMax)
        mudarMenuMax(currentMenuMin, currentMenuMax);
}

/**
 *  Shows the submenu menu and set the current object style to style.
 *  menu - a submenu name
 *  style - a style name
 */
function doShowSubMenu(menu,style) {
    setStyle(style);
    showSubMenu(menu);
}

/**
 *  Hides the submenu menu and set the current object style to style.
 *  menu - a submenu name
 *  style - a style name
 */
function doHideSubMenu(menu,style) {
    setStyle(style);
    hideMenu(menu);
}

/**
 *  Sets the current object style to style.
 *  style - a style name
 */
function setStyle(style) {
    window.event.srcElement.className=style;
}


/**
 *  Shows the menu menu and set the current object style to style.
 *  menu - a submenu name
 *  style - a style name
 */
function showMenu(menu,style) {	
    //Monta a posição do menu
    var tamanho;
    var searchString = /none/i;
    if(menuMax == 1) tamanho = 23;
    else             tamanho = 55;
    
    var oField = window.event.srcElement;
    var oMenu = document.all(menu);
    
    if(oMenu.style.display != "none"){		
        window.event.cancelBubble = true;
        return;
    }

    var iMnOffsetTop  =  oField.offsetTop  + oField.parentElement.parentElement.offsetTop + oField.offsetHeight + tamanho;
    var iMnOffsetLeft =  oField.offsetLeft + oField.parentElement.offsetLeft;

    oField.className = style;
    hideMenu();
    oMenu.style.display = "";

    if(oMenu.offsetWidth < oField.offsetWidth) {
        oMenu.style.width = oField.offsetWidth + "px";
    }

    oMenu.style.top = iMnOffsetTop  + "px";
    oMenu.style.left= iMnOffsetLeft + "px";
    
    //hideElements("SELECT", oMenu);
    //hideElements("IFRAME", oMenu);
    
    mnActived = oMenu;
    window.event.cancelBubble = true;
    //Click menu
    clickMenu = 1;
}

/**
 *  Shows the submenu menu
 *  menu - a submenu name
 */
function showSubMenu(menu) {
    var oField = window.event.srcElement;
    var oMenu = document.all(menu);
    
    var oSpanMenu = oField.parentElement.parentElement.parentElement.parentElement;
    var iMnOffsetTop  =  oSpanMenu.offsetTop + oField.offsetTop - 2;
    var iMnOffsetLeft;
    var sizeTotalMenu = 790;

    hideMenu( oSpanMenu );
    oMenuId = oSpanMenu.id;
    oMenu.menuPai = oSpanMenu.id;
    oMenu.style.display = "";

    if(oMenu.offsetWidth < oField.offsetWidth) {
        //oMenu.style.width = oField.offsetWidth + "px";
    }
    if((oSpanMenu.offsetLeft + oField.offsetWidth + oSpanMenu.offsetWidth) - sizeTotalMenu > 0){
        iMnOffsetLeft = oSpanMenu.offsetLeft - oField.offsetWidth + 1; 
    }else{
        iMnOffsetLeft = oSpanMenu.offsetLeft + oSpanMenu.offsetWidth - 6;
    }

    oMenu.style.top = iMnOffsetTop  + "px";
    oMenu.style.left= iMnOffsetLeft + "px";

    //hideElements("SELECT", oMenu);
    //hideElements("IFRAME", oMenu);

    mnActived = oMenu;
    window.event.cancelBubble = true;
}

/**
 *  Hides the all sub-menus of menu menu.
 *  menu - a menu
 */
function doHideAllSubMenu(menu) {
    // gets the menu named menu
    var oMenu = document.all(menu);
    
    // if found it, hides all sub-menus of the menu.
    if (oMenu) {
        hideMenu(oMenu);
    }
}

/**
 *  Hides all its sub-menus of a menu.
 *  menu - a menu
 */
function hideMenu(menu) {
    
    if(mnActived) {
        // if some menu is actived
        // while the current active menu is not the menu
        while(mnActived != menu) {
            if(!mnActived.menuPai) {
                //if there is not parent menu, end the recursion
                return;
            }
            // gets the parent menu
            var oMenu = document.all(mnActived.menuPai);
            ifrSubMenu(false,mnActived.id);
            
            // hides the current actived menu
            mnActived.style.display = 'none';
            
            // sets the current actived menu to parent menu, and shows it
            mnActived = oMenu;
            //showElements("SELECT");
            //showElements("IFRAME");
    
            if ( oMenu ) {
                //hideElements("SELECT", oMenu);
                //hideElements("IFRAME", oMenu);
            }
        }
        
        window.event.cancelBubble=true;
    }
}

function hideElements(elmID, oMenu) {
    var y = oMenu.offsetTop;
    var x = oMenu.offsetLeft;
    
    for (i = 0; i < document.all.tags(elmID).length; i++)	{
        obj = document.all.tags(elmID)[i];
        if (! obj || ! obj.offsetParent) {
            continue;
        }
        // Find the element's offsetTop and offsetLeft relative to the BODY tag.
        objLeft   = obj.offsetLeft;
        objTop    = obj.offsetTop;
        objParent = obj.offsetParent;
        while (objParent.tagName.toUpperCase() != "BODY") {
            objLeft  += objParent.offsetLeft;
            objTop   += objParent.offsetTop;
            objParent = objParent.offsetParent;
        }
        
        // Adjust the element's offsetTop relative to the dropdown menu
        objTop = objTop - y;
        if (x > (objLeft + obj.offsetWidth) || objLeft > (x + oMenu.offsetWidth))
          ;
        else if (objTop > oMenu.offsetHeight)
          ;
        else if ((y + oMenu.offsetHeight) <= 80)
          ;
        else
          obj.style.visibility = "hidden";
    }
}

function showElements(elmID) {
    for (i = 0; i < document.all.tags(elmID).length; i++) {
        obj = document.all.tags(elmID)[i];
        obj.style.visibility = "";
    }
}

function ifrMenu(state,spanId){
    var divRef = document.getElementById(spanId);
    if(state){
        var ifrRef = criaIFrame("ifrm"+spanId);
        ifrRef.style.width   = divRef.offsetWidth;
        ifrRef.style.height  = divRef.offsetHeight;
        ifrRef.style.top     = divRef.style.top;
        ifrRef.style.left    = divRef.style.left;
        ifrRef.style.zIndex  = divRef.style.zIndex - 1;
        ifrRef.style.display = "block";
    }else{
        var ifrRef = document.getElementById("ifrm"+spanId);
        if(ifrRef != undefined){
            ifrRef.style.display = "none";
            document.body.removeChild(ifrRef);
        }
    }
}

function ifrSubMenu(state,spanId){
    var divRef = document.getElementById(spanId);
    if(state){
        var ifrRef = criaIFrame("ifrm"+spanId);
        ifrRef.style.width   = divRef.offsetWidth;
        ifrRef.style.height  = divRef.offsetHeight;
        ifrRef.style.top     = divRef.style.top;
        ifrRef.style.left    = divRef.style.left;
        ifrRef.style.zIndex  = divRef.style.zIndex - 1;
        ifrRef.style.display = "block";
    }else{
        var ifrRef = document.getElementById("ifrm"+spanId);
        if(ifrRef != undefined){
            ifrRef.style.display = "none";
            document.body.removeChild(ifrRef);
        }
    }
}

var loaderBody = "<div style='background-image:url(/FrontOfficeWeb/resources/images/loader.gif);'></div>";

function criaIFrame(idIFrame){
    try{
        var iframe = document.createElement("IFRAME");
        iframe.id = idIFrame;
        iframe.frameborder = 0;
        iframe.style.top = '0px';
        iframe.style.left = '0px';
        iframe.style.position = "absolute";
        iframe.style.display = "none";
        iframe.scrolling = "no";
        iframe.src = ""
        document.body.appendChild(iframe);
        return iframe;
    }catch(exception){
        alert(exception.description);
    }
}