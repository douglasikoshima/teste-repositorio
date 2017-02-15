#ifndef ATUALIZAPRIORIZACAOPC
#define ATUALIZAPRIORIZACAOPC

#undef SQLCA
#define SQLCA_NONE
#include <sqlca.h>
#include <sqlda.h>
#include <ctype.h>

#include <stdlib.h>

#include "../../commons/Propriedade/include/Propriedade.h"
#include "../../commons/queryMacro.h"
#include "../../commons/Log/include/Log.h"

class cResetaIndisponibilidadeUsuarioPC
{
	public:
        int proCResetarUsuariosLivres();
 
	private:
	    Log log;
};
 
#endif
