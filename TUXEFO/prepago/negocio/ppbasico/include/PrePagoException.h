///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase SincException
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:15 $
 **/
///////////////////////////////////////////////////////////////////////////////

#ifndef PREPAGO_EXCEPTIONH
#define PREPAGO_EXCEPTIONH

#include "Global.h"
#include "tuxfw.h"

class PrePagoException
{
	int		iTipo;
	char	szMessage[LEN_MESSAGE_EXCEPTION+LEN_EOS];

public:
	PrePagoException(int iTipoAUX, char *szMessageAUX);

	~PrePagoException(void);

	char* getMsg(void);
    int getTipo(void);
};

#endif
