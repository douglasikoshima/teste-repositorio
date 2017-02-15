#ifndef FILASETCLIENTINFOHTMA
#define FILASETCLIENTINFOHTMA

#include "FilaSetClientInfopcTMA.h"
#include "tuxfw.h"

class CFilaSetClientInfo:private TuxHelper
{
    public:
        TFilaSetClientInfo      tFilaSetClientInfo;
        CFilaSetClientInfopc    clFilaSetClientInfopc;

        CFilaSetClientInfo(void);
        void insereFilaSetClientInfo(void);
        bool existeFilaSetClientInfo(void);
        void atualizaXmlFilaSetClientInfo(void);

        void setIdLinhaTelefonica(char *pszIdLinhaTelefonica);
        void setXml(char *pszXml);

        char *getIdFilaSetClientInfo(void);
        char *getIdLinhaTelefonica(void);
};

#endif
