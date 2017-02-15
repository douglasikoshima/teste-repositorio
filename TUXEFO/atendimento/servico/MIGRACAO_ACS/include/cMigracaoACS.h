#undef SQLCA
#define SQLCA_NONE
#include <sqlca.h>
#include <sqlda.h>
#include <ctype.h>

#include <tuxfw.h>

#include "stVariaveisMigracaoACS.h"
#include "../../../commons/queryMacro.h"
#include "../../../commons/Collection/include/Collection.h"
#include "../../../commons/msgPadrao.h"

class cMigracaoACSPC
{
	public:
      bool  ConsultaWFMigracaoAcsPC( st_VariaveisMigracaoACS *, Collection * );

	private:
		void sql_error_WFMigracaoAcs( sqlca * sqlca );
};

