///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase TipoConta
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:15 $
 **/
///////////////////////////////////////////////////////////////////////////////

#ifndef TIPOCONTAH
#define TIPOCONTAH

#include "Global.h"
#include "TipoContapc.h"

class CTipoConta
{

public:
    CTipoConta(void);
    bool buscaTipoConta(void);

	TTipoConta		tTipoConta;
	CTipoContapc    clTipoContapc;

    void setSgTipoConta(char *pszSgTipoConta);
    char *getIdTipoConta(void);
};

#endif
