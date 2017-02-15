/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Charles Santos
 * @version $Revision: 1.3 $
 * @CVS     $Author: mnunes $ - $Date: 2006/05/30 15:43:55 $
 **/

#include <stdio.h>
#include <time.h>



#include "../include/LerDados.h"

int main(int argc, char* argv[])
{

	Collection	Lista;

    LerDados oLerDados("TESTE.xml");

//	oLerDados.CarregaLista (&Lista,"idContato");

	char *login      = oLerDados.getParametro("login"      ); //=String
	char *timestamp  = oLerDados.getParametro("timestamp"  );  //=1
	char *inVariaveis= oLerDados.getParametro("inVariaveis");  //=1
	char *inRetorno  = oLerDados.getParametro("inRetorno"  );  //=1
	char *inArvore   = oLerDados.getParametro("inArvore"   );  //=1
	char *inGrupos   = oLerDados.getParametro("inGrupos"   );  //=1
//    char * TEMP = oLerDados.getConteudoLista(&Lista ,0 );
//	TEMP = oLerDados.getConteudoLista(&Lista ,2 );
//	oLerDados.DestroiList(&Lista );

//    Lista.~Collection();
//    oLerDados.~LerDados();
		
	return 1;
}

