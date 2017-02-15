///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  PrePago
 * @usecase TipoRelacionamento
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:15 $
 **/
///////////////////////////////////////////////////////////////////////////////

#ifndef TIPO_RELACIONAMENTOH
#define TIPO_RELACIONAMENTOH

#include "Global.h"
#include "TipoRelacionamentopc.h"

class CTipoRelacionamento
{
public:
	TTipoRelacionamento		tTipoRelacionamento;
	CTipoRelacionamentopc   clTipoRelacionamentopc;

    CTipoRelacionamento(void);
    bool buscaTipoRelacionamento(void);

    void setSgTipoRelacionamento(char *pszSgTipoRelacionamento);
    char *getIdTipoRelacionamento(void);
};

#endif
