
#ifndef _PROCMATRIZRETENCAOPC_H_
#define _PROCMATRIZRETENCAOPC_H_

#undef SQLCA
#define SQLCA_NONE
#include<sqlca.h>
#include<sqlda.h>
#include<ctype.h>

#include<stdlib.h>

#include "../../commons/Propriedade/include/Propriedade.h"
#include "../../commons/queryMacro.h"
#include "../../commons/Log/include/Log.h"

class cprocMatrizesRetencaoPC
{
    public:
        void processa();
};
 
#endif // _EXPURGOATENDIMENTOPC_H_