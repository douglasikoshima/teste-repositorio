/**
 *
 * This file content the javascript rotines to handler the header.
 *
 * @author Alexandre Nunes
 * @version 1.0, 20/04/2004
 *  
 */

/*
 * Global variables
 */
var currentHeaderMin; //Identificador do Header Min
var currentHeaderMax; //Identificador do Header Max

/*
 * Functions
 */

/**
 *  
 */
/**
 *  Controla o maximiza/minimiza do menu
 */
function mudarHeader(headerMin, headerMax) {
    //Cria a expressão pesquisa
    var stringSearch;
    stringSearch = /none/i;
    
    //Maximiza ou Minimiza
    if( headerMin.style.display.search(stringSearch) == 0 )  {
        menuMax = 1;    //false
        headerMin.style.display = '';
        headerMax.style.display = 'none';
    } else {
        menuMax = 0;    //true
        headerMin.style.display = 'none';
        headerMax.style.display = '';
    }
}