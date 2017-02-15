

#undef SQLCA
#define SQLCA_NONE
#include <sqlca.h>
#include <sqlda.h>
#include <ctype.h>
#include <tuxfw.h>

#include "stVariaveisCnsCTI.h"
#include "../../../commons/queryMacro.h"
#include "../../../commons/Collection/include/Collection.h"
#include "../../../commons/msgPadrao.h"


class cWFCnsInBoxRetPC
{
	public:
      void  consultaWFRetornoCTI( st_VariaveisCnsCTI *, Collection * );

	private:
		void sql_error_WFRetornoCTI( sqlca * sqlca );
};

