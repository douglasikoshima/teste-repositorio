
#undef SQLCA
#define SQLCA_NONE
#include <sqlca.h>
#include <sqlda.h>
#include <ctype.h>
#include <tuxfw.h>

#include "st_VariaveisPsqLinhas.h"
#include "../../../commons/queryMacro.h"
#include "../../../commons/Collection/include/Collection.h"
#include "../../../commons/msgPadrao.h"


class cWFAbrPrcPsqLinPC
{
	public:
      bool  proCObtemWFAtdLinhas(int idConta, int idLinha, XMLGen *saida);

	private:
		void sql_error_PesquisaLinhas( sqlca * sqlca );
};

