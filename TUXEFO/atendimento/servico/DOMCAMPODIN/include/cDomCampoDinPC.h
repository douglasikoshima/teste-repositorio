#undef SQLCA
#define SQLCA_NONE
#include <sqlca.h>
#include <sqlda.h>
#include <ctype.h>

#include <tuxfw.h>

#include "../include/stVariaveisDomCampoDin.h"
#include "../../../commons/queryMacro.h"
#include "../../../commons/Collection/include/Collection.h"
#include "../../../commons/msgPadrao.h"

class cDomCampoDinPC
{
	public:
      void  pesquisaDominioCampoDinamicoPC( st_VariaveisDomCampoDin *, Collection * );

	private:
		void sql_error_WFpsqDomCampoDin( sqlca * sqlca );
};

