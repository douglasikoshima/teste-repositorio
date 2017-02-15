/**
 * 
 * @modulo  BATCH
 * @usecase Expurgos e encerramento de processos de porout vencidos não tratados pelo SPN
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.8.2 $
 * @CVS     $Author: cmgarcia $ - $Date: 2009/06/04 19:11:22 $
 **/

#include <stdio.h>

#include "../include/expurgoAtendimentoPC.h"
#include "../../commons/Log/include/Log.h"

int main(int argc, char* argv[])
{
    Log log;

    log.logInformation("Inicio do processamento.\n");

    cExpurgoAtendimentoPC apx;

    if ( apx.processaExpurgo() )
    {
        log.logInformation("Processamento finalizado com sucesso.\n");
    }
    else
    {
        log.logInformation("Processamento finalizado com problemas, favor veriricar.\n");
    }

    return 0;
}
