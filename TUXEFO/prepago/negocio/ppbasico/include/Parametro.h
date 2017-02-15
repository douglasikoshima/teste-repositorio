///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  PrePago
 * @usecase Parametro
 * @author  Renato Striitzel Russo
 * @author  Eder Jani Martins
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:15 $
 **/
///////////////////////////////////////////////////////////////////////////////

#ifndef PARAMETROH
#define PARAMETROH

#include "Global.h"
#include "Parametropc.h"

class CParametro
{

public:
    CParametro(void);
    bool buscaParametro(void);

	TParametro      tParametro;
	CParametropc    clParametropc;

    void clearStruct(void);
    void setCdParametro(char *pszCdParametro);
    char *getDsValorParametro(void);
    char *getCdParametro(void);
};

#endif
