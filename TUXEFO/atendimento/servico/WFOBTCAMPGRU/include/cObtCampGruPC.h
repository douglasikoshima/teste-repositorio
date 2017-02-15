/**
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:24 $
 **/

#undef SQLCA
#define SQLCA_NONE
#include <sqlca.h>
#include <sqlda.h>
#include <ctype.h>

#include <tuxfw.h>

#include "stVariaveisCampanhaGrupo.h"
#include"../../../commons/queryMacro.h"
#include "../../../commons/Collection/include/Collection.h"
#include "../../../commons/msgPadrao.h"


class cObtCampGruPC
{
	public:
		void SelectByGroupPC(  st_VariaveisCampanhaGrupo *_dadosEntradaPC, Collection *_ResultadoPC );

	private:
      void sql_error_WFobtemCampGrupos( sqlca * sqlca );
};
