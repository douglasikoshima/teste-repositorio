///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  PrePago
 * @usecase TipoEnderecoCobranca
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:15 $
 **/
///////////////////////////////////////////////////////////////////////////////

#ifndef TIPO_ENDERECO_COBRANCAH
#define TIPO_ENDERECO_COBRANCAH

#include "Global.h"
#include "TipoEnderecoCobrancapc.h"

class CTipoEnderecoCobranca
{
public:
	TTipoEnderecoCobranca		tTipoEnderecoCobranca;
	CTipoEnderecoCobrancapc     clTipoEnderecoCobrancapc;

    CTipoEnderecoCobranca(void);
    bool buscaTipoEnderecoCobranca(void);

    void setSgTipoEnderecoCobranca(char *pszSgTipoEnderecoCobranca);
    char *getIdTipoEnderecoCobranca(void);
};

#endif
