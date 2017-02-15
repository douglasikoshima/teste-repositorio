#undef SQLCA
#define SQLCA_NONE
#include <sqlca.h>
#include <sqlda.h>
#include <ctype.h>

#include <tuxfw.h>

#include "stVariaveisTpComunic.h"
#include "../../../commons/queryMacro.h"
#include "../../../commons/Collection/include/Collection.h"
#include "../../../commons/msgPadrao.h"

class cPsqPessoTpComPC
{
	public:
      void  pesquisaPessoaTpComunicPC(  st_VariaveisTpComunic *, Collection * );
      void  pesquisaPessoaComunicacaoPC(  unsigned long sIdPessoa, XMLGen *saida );
      void  pesquisaTipoComunicacaoPC( XMLGen *saida );

	private:
		void sql_error_WFpsqPessoaTpComunic( sqlca * sqlca );
};

