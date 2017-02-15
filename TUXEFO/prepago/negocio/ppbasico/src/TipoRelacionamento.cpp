///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  PrePago
 * @usecase TipoRelacionamento
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:22 $
 **/
///////////////////////////////////////////////////////////////////////////////

#include <string.h>
#include "TipoRelacionamento.h"

CTipoRelacionamento::CTipoRelacionamento(void)
{
	memset(&tTipoRelacionamento, 0x00, sizeof(TTipoRelacionamento));
}

bool CTipoRelacionamento::buscaTipoRelacionamento(void)
{
    return clTipoRelacionamentopc.proCBuscaTipoRelacionamento(&tTipoRelacionamento);
}

void CTipoRelacionamento::setSgTipoRelacionamento(char *pszSgTipoRelacionamento)
{
    strcpy(tTipoRelacionamento.szSgTipoRelacionamento, pszSgTipoRelacionamento);
}

char *CTipoRelacionamento::getIdTipoRelacionamento(void)
{
    static char szAux[LEN_IDTIPORELACIONAMENTO + LEN_EOS];

    strcpy(szAux, tTipoRelacionamento.szIdTipoRelacionamento);
    return szAux;
}
