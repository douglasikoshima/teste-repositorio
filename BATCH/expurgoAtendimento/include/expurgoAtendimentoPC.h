/**
 * 
 * @modulo  BATCH
 * @usecase Expurgos
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.8.1 $
 * @CVS     $Author: cmgarcia $ - $Date: 2009/06/04 19:11:22 $
 **/

#ifndef _EXPURGOATENDIMENTOPC_H_
#define _EXPURGOATENDIMENTOPC_H_

#undef SQLCA
#define SQLCA_NONE
#include<sqlca.h>
#include<sqlda.h>
#include<ctype.h>

#include<stdlib.h>

#include "../../commons/Propriedade/include/Propriedade.h"
#include "../../commons/queryMacro.h"
#include "../../commons/Log/include/Log.h"

class cExpurgoAtendimentoPC
{
    public:
        bool processaExpurgo();
};
 
#endif // _EXPURGOATENDIMENTOPC_H_
