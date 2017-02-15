///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase TipoConta
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:22 $
 **/
///////////////////////////////////////////////////////////////////////////////

#include <string.h>
#include "TipoConta.h"

CTipoConta::CTipoConta(void)
{
	memset(&tTipoConta, 0x00, sizeof(TTipoConta));
}

bool CTipoConta::buscaTipoConta(void)
{
    return clTipoContapc.proCBuscaTipoConta(&tTipoConta);
}

void CTipoConta::setSgTipoConta(char *pszSgTipoConta)
{
    strcpy(tTipoConta.szSgTipoConta, pszSgTipoConta);
}

char *CTipoConta::getIdTipoConta(void)
{
    static char szAux[LEN_IDTIPOCONTA + LEN_EOS];

    strcpy(szAux, tTipoConta.szIdTipoConta);
    return szAux;
}
