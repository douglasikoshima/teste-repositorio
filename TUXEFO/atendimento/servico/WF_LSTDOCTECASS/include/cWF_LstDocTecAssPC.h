#undef SQLCA
#define SQLCA_NONE
#include <sqlca.h>
#include <sqlda.h>
#include <ctype.h>

#include <tuxfw.h>

#include "st_VariaveisLstDocTecAss.h"
#include "../../../commons/queryMacro.h"
#include "../../../commons/Collection/include/Collection.h"
#include "../../../commons/msgPadrao.h"

class cWF_LstDocTecAss
{
	public:
		bool consultaWFRespostaTeste( char* sIdAtendimentoTeste, XMLGen* saida );
		bool consultaWFTestes( XMLGen* saida );
		bool consultaWFTestesAtendimento( st_VariaveisLstDocTecAss * dados, XMLGen* saida );

	private:
		void sql_error_WF_LstDocTecAss( sqlca * sqlca );

};

