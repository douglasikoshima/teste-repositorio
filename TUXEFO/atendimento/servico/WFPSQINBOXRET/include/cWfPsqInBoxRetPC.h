
#undef SQLCA
#define SQLCA_NONE
#include <sqlca.h>
#include <sqlda.h>
#include <ctype.h>
#include <tuxfw.h>

#include "stVariaveisPsqCTI.h"
#include "../../../commons/queryMacro.h"
#include "../../../commons/Collection/include/Collection.h"
#include "../../../commons/msgPadrao.h"


class cWfPsqInBoxRetPC
{
	public:
      void  pesquisaWFRetornoCTI( st_VariaveisPsqCTI *, Collection * );

	private:
		void sql_error_WFRetornoCTI( sqlca * sqlca );
};

