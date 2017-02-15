
#include <stdio.h>

#include "../include/cAtzContato.h"
#include "../../commons/Log/include/Log.h"

int main(int argc, char* argv[])
{
    Log log;

    log.logInformation("Iniciando atualizacao dos contatos\n");

    cAtzContato apx;

    apx.Executa();

    log.logInformation("PRONTO - Processamento finalizado.\n");

    return 0;
}
