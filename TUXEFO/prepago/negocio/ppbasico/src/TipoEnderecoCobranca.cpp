///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  PrePago
 * @usecase TipoEnderecoCobranca
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:22 $
 **/
///////////////////////////////////////////////////////////////////////////////

#include <string.h>
#include "TipoEnderecoCobranca.h"

CTipoEnderecoCobranca::CTipoEnderecoCobranca(void)
{
//	memset(&tTipoEnderecoCobranca, 0x00, sizeof(TTipoEnderecoCobranca));
}

bool CTipoEnderecoCobranca::buscaTipoEnderecoCobranca(void)
{
    return clTipoEnderecoCobrancapc.proCBuscaTipoEnderecoCobranca(&tTipoEnderecoCobranca);
}

void CTipoEnderecoCobranca::setSgTipoEnderecoCobranca(char *pszSgTipoEnderecoCobranca)
{
    strcpy(tTipoEnderecoCobranca.szSgTipoEnderecoCobranca, pszSgTipoEnderecoCobranca);
}

char *CTipoEnderecoCobranca::getIdTipoEnderecoCobranca(void)
{
    static char szAux[LEN_IDTIPOENDERECOCOBRANCA + LEN_EOS];

    strcpy(szAux, tTipoEnderecoCobranca.szIdTipoEnderecoCobranca);
    return szAux;
}
