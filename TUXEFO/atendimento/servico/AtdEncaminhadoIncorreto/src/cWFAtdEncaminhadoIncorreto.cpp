/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:35 $
 **/

#include "../include/cWFAtdEncaminhadoIncorreto.h"

extern void proCIncluirWFAtdEncaminhadoIncorreto(st_AtdEncaminhadoIncorreto* dados, st_vlAtdEncaminhadoIncorreto* status);

void cWFAtdEncaminhadoIncorreto::incluir(st_AtdEncaminhadoIncorreto* dados, st_vlAtdEncaminhadoIncorreto* status)
{
    ULOG_START("cWFAtdEncaminhadoIncorreto::incluir()");

    proCIncluirWFAtdEncaminhadoIncorreto(dados,status);

    ULOG_END("cWFAtdEncaminhadoIncorreto::incluir()");
}

