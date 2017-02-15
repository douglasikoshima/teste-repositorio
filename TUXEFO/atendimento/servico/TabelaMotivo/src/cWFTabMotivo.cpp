/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:31 $
 **/

#include "../include/cWFTabMotivo.h"
#include "../../../commons/msgPadrao.h"

extern bool proCPesquisaTabelaMotivo(DOMNode*entrada,XMLGen* saida);
extern bool proCPesquisaMotivoAtividade(DOMNode*entrada,XMLGen* saida);
extern bool proCPesquisaMotivoAtividade(int _Fase,DOMNode*entrada,XMLGen* saida);

bool cWFTabMotivo::pesqTabelaMotivo(DOMNode* entrada,XMLGen* saida)
{
    ULOG_START("pesqTabelaMotivo()");

    bool retorno = proCPesquisaTabelaMotivo(entrada,saida);

	ULOG("retorno=[%d]",retorno);

    ULOG_END("pesqTabelaMotivo()");

    return retorno;
}

bool cWFTabMotivo::pesqMotivoAtividade(DOMNode* entrada,XMLGen* saida)
{
    ULOG_START("pesqMotivoAtividade()");

    bool retorno = proCPesquisaMotivoAtividade(entrada,saida);

	ULOG("retorno=[%d]",retorno);

    ULOG_END("pesqMotivoAtividade()");

    return retorno;
}

bool cWFTabMotivo::pesqMotivoAtividade(int _idFase,DOMNode* entrada,XMLGen* saida)
{
    ULOG_START("pesqMotivoAtividade()");

    bool retorno = proCPesquisaMotivoAtividade(_idFase,entrada,saida);

	ULOG("retorno=%d",retorno);

    ULOG_END("pesqMotivoAtividade()");

    return retorno;
}

