/* ListaCampanha.cpp : Defines the entry point for the console application.
*/

#include <stdio.h>

#include "../include/resetaIndisponibilidadeUsuarioPC.h"

#include "../../commons/Log/include/Log.h"

int main(int argc, char* argv[])
{
	Log log;

	log.logInformation("Iniciando o processamento de disponibilização de usuarios parados.\n");

	try
	{
		cResetaIndisponibilidadeUsuarioPC apx;

        int nUsuariosResetados = apx.proCResetarUsuariosLivres();

		if ( nUsuariosResetados >= 0 )
        {
            char msg[512];

            if ( nUsuariosResetados == 0 )
            {
                strcpy(msg,"PRONTO - Encerrando processamento sem necessidade que nenhum usuario fosse tornado disponivel\n");
            }
            else if ( nUsuariosResetados == 1 )
            {
                strcpy(msg,"PRONTO - Um usuario foi tornado disponivel\n");
            }
            else if ( nUsuariosResetados > 1 )
            {
                sprintf(msg
                       ,"PRONTO - %d usuarios foram tornados disponiveis\n"
                       ,nUsuariosResetados);
            }
            
	        log.logInformation(msg);
        }
        else
        {
	        log.logInformation("ERRO - Encerrando processamento de disponibilização de usuarios parados.\n");
        }
	}
	catch (char* msg)
    {
		log.logError( msg );
		return -1;
	}
	catch (...)
    {
		log.logError( "erro de processamento desconhecido" );
		return -1;
	}

    return 0;
}


