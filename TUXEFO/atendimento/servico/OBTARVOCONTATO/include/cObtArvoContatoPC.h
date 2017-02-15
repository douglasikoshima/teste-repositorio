#undef SQLCA
#define SQLCA_NONE
#include <sqlca.h>
#include <sqlda.h>
#include <ctype.h>

#include <tuxfw.h>

#include "stVariaveisObtArvContato.h"
#include "../../../commons/queryMacro.h"
#include "../../../commons/Collection/include/Collection.h"
#include "../../../commons/msgPadrao.h"

class cObtArvoContatoPC
{
	public:
      bool obtemWFContatoRaiz( Collection * );
      // bool  obtemWFContatoFolha( st_Parametros * _dadosEntradaPC, Collection *_ResultadoPC );
      bool obtemWFContatoFolhaVar(st_Parametros * _dadosEntradaPC,Collection *_ResultadoPC,bool varFornecidas);
      bool obtemWFContatoLista(st_Parametros * _dadosEntradaPC,Collection *_ResultadoPC);

	private:
		void sql_error_WFLookUp( sqlca * sqlca );
};

