        
/*
 ********************************************************************************
 *- Inicio Cronometro
 *- Pablo Henrique
 *- 24-09-04
 *
 *- Variaveis globais
 *  - ctrlQstIntervalId
 *  - ctrlQstIntervalId
 *
 *- Funções
 *  - qstCronometro()
 *  - qstIniciaContador() 
 *  - drawView()
 *  - calculaCronometro()
 *********************************************************************************
*/

// Variaveis globais
var ctrlQstIntervalId      = null; //Guarda o id do cronometro
var ctrlQstTmpDecorridoId  = null; //Guarda o id do tempo decorrido
var ctrlQstContador        = 0;    //Porcentagem corrente                
var dateQstTmpDecorrido    = 0;    //Tempo corrente
var dateQstSegundo   = 0;
var dateQstMinuto    = 0;
var dateQstHora      = 0;
 



function qstCronometro()
{

        var strObjetoC = "";
        var strObjetoB = "";               
        
        if(ctrlQstIntervalId == null)
        {
            return;
        }                                   
        
        if(ctrlQstContador ==0 )
        {
            ctrlQstContador = 1;
        }               
        
        if(ctrlQstContador <= 9)
        {
            strObjetoC = "tdC" + (ctrlQstContador * 10);
            strObjetoB = "tdB" + (ctrlQstContador * 10);               


            if(ctrlQstContador < 5)
            {
                
                document.getElementById(strObjetoC).background = "/FrontOfficeWeb/resources/images/cronometro/verde_cima.gif"
                document.getElementById(strObjetoB).background = "/FrontOfficeWeb/resources/images/cronometro/verde_baixo.gif"
            }
            else if(ctrlQstContador < 8)
            {
                document.getElementById(strObjetoC).background = "/FrontOfficeWeb/resources/images/cronometro/amarelo_cima.gif"
                document.getElementById(strObjetoB).background = "/FrontOfficeWeb/resources/images/cronometro/amarelo_baixo.gif"
            }
            else
            {
                document.getElementById(strObjetoC).background = "/FrontOfficeWeb/resources/images/cronometro/vermelho_cima.gif"
                document.getElementById(strObjetoB).background = "/FrontOfficeWeb/resources/images/cronometro/vermelho_baixo.gif"                      
            }
            ctrlQstContador ++;
        }
        else
        {
            window.clearInterval(ctrlQstIntervalId);
            window.clearInterval(ctrlQstTmpDecorridoId)
            qstCronometroCheio();
        }
}

function qstIniciaContador(segundosTotal, segundosCorridos)
{

    var strObjetoC = "";
    var strObjetoB = "";
    var porcentagemCorrida;
    
    if(segundosTotal < 60)
    {
        return;
    }

    document.getElementById("tblCronometro").style.display = "block";                
    
    
    if(segundosCorridos < 0) 
    {
        segundosCorridos *= -1;
    }

    porcentagemCorrida = parseInt((segundosCorridos *100) / segundosTotal);                                                
            


    if(porcentagemCorrida >= 100)
    {	                

        qstCronometroCheio();
        return;
    }
    else if(porcentagemCorrida > 0)
    {
        
        for(i = 0; i < porcentagemCorrida && i < 100 ;  i+=10)
        {
            strObjetoC = "tdC" + (i + 10);
            strObjetoB = "tdB" + (i + 10);

            if(ctrlQstContador < 5)
            {
                    document.getElementById(strObjetoC).background = "/FrontOfficeWeb/resources/images/cronometro/verde_cima.gif"
                    document.getElementById(strObjetoB).background = "/FrontOfficeWeb/resources/images/cronometro/verde_baixo.gif"
            }
            else if(ctrlQstContador < 8)
            {
                document.getElementById(strObjetoC).background = "/FrontOfficeWeb/resources/images/cronometro/amarelo_cima.gif"
                document.getElementById(strObjetoB).background = "/FrontOfficeWeb/resources/images/cronometro/amarelo_baixo.gif"
            }
            else
            {
                document.getElementById(strObjetoC).background = "/FrontOfficeWeb/resources/images/cronometro/vermelho_cima.gif"
                document.getElementById(strObjetoB).background = "/FrontOfficeWeb/resources/images/cronometro/vermelho_baixo.gif"
            }
            
            ctrlQstContador++;
        } 
    
    }

    dateQstTmpDecorrido   = parseInt(segundosTotal) -  parseInt(segundosCorridos);
    
    /*
     * Calcula cronometros
    */
    for(i = 0; i < segundosCorridos; i ++)
    {
        calculaCronometro();
    }
    
    tempoCorrido();

    ctrlQstTmpDecorridoId = window.setInterval("tempoCorrido()", 1000); //Executa a cada um segundo

    segundosTotal *= (1000);
    
    segundosTotal /= 10;
    
    
    /*
     * Inicia o cronometro
    */
    ctrlQstIntervalId     = window.setInterval("qstCronometro()", segundosTotal);    
            
}                                                   

function tempoCorrido()
{						
    if(dateQstTmpDecorrido >= 0)
    {
        calculaCronometro();
        drawView();
    }
    dateQstTmpDecorrido --;
}

function qstCronometroCheio()
{
  for(i = 0; i < 100;  i+=10)
  {
            strObjetoC = "tdC" + (i + 10);
            strObjetoB = "tdB" + (i + 10);
            document.getElementById(strObjetoC).background = "/FrontOfficeWeb/resources/images/cronometro/vermelho_cima.gif"
            document.getElementById(strObjetoB).background = "/FrontOfficeWeb/resources/images/cronometro/vermelho_baixo.gif"
  } 
    document.getElementById("lyrTemp").innerText = "ESGOTADO";	
}


function calculaCronometro()
{
    dateQstSegundo ++;
    
    if(dateQstSegundo == 60)
    {
        dateQstSegundo = 0
        dateQstMinuto++;
        
        if(dateQstMinuto ==  60)
        {
            dateQstMinuto ++ ;
            dateQstHora ++;
        }
    }
}


function doisDigitos(valor)
{
    var tmp = new String(valor);
    return (tmp.length == 1) ? "0" + tmp : tmp;
}

function drawView()
{
    document.getElementById("lyrTemp").innerText = "Tempo: " + doisDigitos(dateQstHora) + ":" + doisDigitos(dateQstMinuto) + ":" + doisDigitos(dateQstSegundo);
}


/*
 *********************************************************************************
 *Fim Cronometro
 *********************************************************************************
*/