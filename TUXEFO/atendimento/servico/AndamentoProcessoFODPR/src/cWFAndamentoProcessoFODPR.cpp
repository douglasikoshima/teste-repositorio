/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.2.2 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:17 $
 **/

#include "../include/cWFAndamentoProcessoFODPR.h"

extern long proCIncluirWFAndamentoProcFODPR(st_AndamentoProcFODPR* dados, st_vlAndamentoProcFODPR* status,char *_idAtendimento);

long cWFAndamentoProcFODPR::incluir(st_AndamentoProcFODPR* dados, st_vlAndamentoProcFODPR* status,char *idAtendimento)
{
    ULOG_START("cWFAndamentoProcFODPR::incluir()");
    
    return proCIncluirWFAndamentoProcFODPR(dados,status,idAtendimento);

    ULOG_END("cWFAndamentoProcFODPR::incluir()");
}

