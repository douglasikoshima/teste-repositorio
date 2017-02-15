#ifndef FILASETCLIENTINFOPCHPORT
#define FILASETCLIENTINFOPCHPORT

#include "PPGlobalPORT.h"

class CFilaSetClientInfopc
{
    public:
        void proCIncluiFilaSetClientInfo(TFilaSetClientInfo *ptFilaSetClientInfo);
        void proCAtualizaXmlFilaSetClientInfo(TFilaSetClientInfo tFilaSetClientInfo);
        bool proCExisteFilaSetClientInfo(TFilaSetClientInfo tFilaSetClientInfo);
};

#endif
