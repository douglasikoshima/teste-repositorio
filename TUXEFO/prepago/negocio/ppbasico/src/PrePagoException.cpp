///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase SincException
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:22 $
 **/
///////////////////////////////////////////////////////////////////////////////

#include "Global.h"
#include "PrePagoException.h"

PrePagoException::PrePagoException(int iTipoAUX, char *szMessageAUX)
{
    iTipo = iTipoAUX;

    strncpy(szMessage,szMessageAUX?szMessageAUX:"",sizeof(szMessage)-1);
    szMessage[sizeof(szMessage)-1]=0;

    ULOGE("Exception - Tipo (%d) - Message[%s]", iTipo, szMessage);
}

PrePagoException::~PrePagoException(void)
{
};

char* PrePagoException::getMsg(void)
{
    return szMessage;
}

int PrePagoException::getTipo(void)
{
    return iTipo;
}
