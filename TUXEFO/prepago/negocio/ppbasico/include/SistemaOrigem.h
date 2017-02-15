///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase SistemaOrigem
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:15 $
 **/
///////////////////////////////////////////////////////////////////////////////

#ifndef SISTEMA_ORIGEMH
#define SISTEMA_ORIGEMH

#include "Global.h"
#include "SistemaOrigempc.h"

class CSistemaOrigem
{
public:
	TSistemaOrigem		tSistemaOrigem;
	CSistemaOrigempc    clSistemaOrigempc;

    CSistemaOrigem(void);
    bool buscaSistemaOrigem(void);

    void setSgSistemaOrigem(char *pszSgSistemaOrigem);
    char *getIdSistemaOrigem(void);
};

#endif
