/* ListaCampanha.cpp : Defines the entry point for the console application.
*/

#include <stdio.h>

#include "../include/AtualizaPriorizacaoPC.h"

#include "../../commons/Log/include/Log.h"

int main(int argc, char* argv[])
{
	Log log;

	log.logInformation("Iniciando o processamento de atualizacao da priorizacao.\n");

	try
	{
		cAtualizaPriorizacaoPC apx;

		apx.processaAtualizacao();

	}
	catch (char* msg) {
		log.logError( msg );
		return -1;
	}

	/* Encerra. */
	log.logInformation("PRONTO - Encerrando processamento de atualizacao de priorizacao.\n");
	return 0;
}


