///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Prepago
 * @usecase FilaSetClientInfo
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1.130.1 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/09/04 20:27:21 $
 **/
///////////////////////////////////////////////////////////////////////////////

#ifndef FILASETCLIENTINFOPREH
#define FILASETCLIENTINFOPREH

#include "Global.h"
#include "FilaSetClientInfoPREpc.h"
#include <tuxfw.h>

class CFilaSetClientInfo:private TuxHelper
{

    public:
        TFilaSetClientInfoPRE   tFilaSetClientInfo;
    
        CFilaSetClientInfopc    clFilaSetClientInfopc;

        CFilaSetClientInfo(void);
        void insereFilaSetClientInfo(void);
        void insereFilaSetClientInfo_4(void);
        bool existeFilaSetClientInfo(void);
        void atualizaXmlFilaSetClientInfo(void);

        void setIdLinhaTelefonica(char *pszIdLinhaTelefonica);
        void setXml(char *pszXml);
};

#endif
