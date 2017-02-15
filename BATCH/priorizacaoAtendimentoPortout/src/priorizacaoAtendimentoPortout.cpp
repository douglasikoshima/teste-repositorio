/**
 * 
 * @modulo  Batch
 * @usecase Batch
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.4.1 $
 * @CVS     $Author: cmgarcia $ - $Date: 2008/06/03 20:29:21 $
 **/

#include <stdio.h>

#include "../include/PriorizacaoAtendimentoPortoutPC.h"

#include "../../commons/Log/include/Log.h"

int main(int argc, char* argv[])
{
	Log log;

	log.logInformation("Iniciando o processamento de atualizacao da priorizacao.\n");

	try
	{
		cPriorizacaoAtendimentoPortoutPC apx;

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


