/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Unknow
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:28 $
 **/

#undef SQLCA
#define SQLCA_NONE
#include <sqlca.h>
#include <sqlda.h>
#include <ctype.h>

#include <tuxfw.h>

#include "stVariaveisObtCampoDin.h"
#include "../../../commons/queryMacro.h"
#include "../../../commons/Collection/include/Collection.h"
#include "../../../commons/msgPadrao.h"

class cObtCampoDinPC
{
	public:
      void  obtemCampoDinamicoPC( st_VariaveisObtCampoDin *, Collection * );

	private:
		void sql_error_WFpsqObtCampoDin( sqlca * sqlca );
};

