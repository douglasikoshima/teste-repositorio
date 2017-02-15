

#undef SQLCA
#define SQLCA_NONE
#include <sqlca.h>
#include <sqlda.h>
#include <ctype.h>
#include <tuxfw.h>

#include "st_VariaveisPsqGrpCTI.h"
#include "../../../commons/queryMacro.h"
#include "../../../commons/Collection/include/Collection.h"
#include "../../../commons/msgPadrao.h"


class cWFPsqGrpCTIPC
{
	public:
      void  SelectGroupsByRetWFCTI( stVariaveisPsqGrpCTI *, Collection * );

	private:
		void sql_error_PesquisaGrupos( sqlca * sqlca );
      void SelectGroupsPdrCam( stVariaveisPsqGrpCTI * _dadosEntradaPC, Collection * _ResultadoPC );
      void SelectGroupsNPdCam( stVariaveisPsqGrpCTI * _dadosEntradaPC, Collection * _ResultadoPC );
      void SelectGroupsPdrTodos( stVariaveisPsqGrpCTI * _dadosEntradaPC, Collection * _ResultadoPC );
      void SelectGroupsNPdTodos( stVariaveisPsqGrpCTI * _dadosEntradaPC, Collection * _ResultadoPC );
};

