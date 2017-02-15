///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Prepago
 * @usecase FilaSetClientInfo
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1.2.3 $
 * @CVS     $Author: a5114878 $ - $Date: 2009/09/29 18:09:18 $
 **/
///////////////////////////////////////////////////////////////////////////////

#ifndef FILASETCLIENTINFOH
#define FILASETCLIENTINFOH

#include "Global.h"
#include "FilaSetClientInfopc.h"
#include "tuxfw.h"

class CFilaSetClientInfo:private TuxHelper
{

    public:
        TFilaSetClientInfo      tFilaSetClientInfo;
    
        CFilaSetClientInfopc    clFilaSetClientInfopc;

        CFilaSetClientInfo(void);
        void insereFilaSetClientInfo(void);
        void insereFilaSetClientInfo_2(void);
        bool existeFilaSetClientInfo(void);
        bool existeFilaSetClientInfo_2(void);
        void atualizaXmlFilaSetClientInfo(void);
        void atualizaXmlFilaSetClientInfo_2(void);

        void setIdLinhaTelefonica(char *pszIdLinhaTelefonica);
        void setXml(char *pszXml);
        void setCdAreaRegistro(char *pszCdAreaRegistro);
        void setNrLinha(char *pszNrLinha);
        void setInterceptado(char *pszInterceptado);
        void setCdErro(char *pszCdErro);
        void setDsErro(char *pszDsErro);

        char *getInterceptado();
};

#endif
