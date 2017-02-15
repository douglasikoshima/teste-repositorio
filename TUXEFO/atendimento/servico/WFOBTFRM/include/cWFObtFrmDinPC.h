#undef SQLCA
#define SQLCA_NONE
#include <sqlca.h>
#include <sqlda.h>
#include <ctype.h>

#include <tuxfw.h>


#include "../commons/queryMacro.h"
/*
#include "../../../commons/Collection/include/Collection.h"
#include "../../../commons/msgPadrao.h"
*/

class cWFObtFrmDinPC
{
	public:
        char *gerarIDDom() { static int id=0; static char stID[32]; sprintf(stID,"ID_%d",++id); return stID; }
        int ProcObtemContato( long idAtendimentoPrm, int *idContatoPrm, int *idFasePrm);
		int ProcObtemTipoContato( int sIdContato, int *idTipoProcesso );
        int ProcFormaRetorno( long idAtendimentoPrm, int *idFormaRetorno, char *dsTipoComunicacao );
        int ProcObtemDescricaoRetorno( long idAtendimentoPrm, int *idTipoLinha, char *nrTelefoneContato );

    private:
	  void sql_error_WFObtemFormularioDin( sqlca * sqlca );

};

