#undef SQLCA
#define SQLCA_NONE
#include <sqlca.h>
#include <sqlda.h>
#include <ctype.h>

#include <tuxfw.h>

#include "st_VariaveisLstDocAssoc.h"
#include "../../../commons/queryMacro.h"
#include "../../../commons/TString.h"
#include "../../../commons/Collection/include/Collection.h"
#include "../../../commons/msgPadrao.h"

class cWF_LstDocAssoc
{
	TuxHelper tx;

	public:
      bool selecaoDocumentosTecnicos( st_DocumentoTecnico * dados,st_vlDocumentoTecnico * status,Collection *_ResultadoPC );
      bool selecaoDocumentosTecnicosAtendimento( st_DocumentoTecnico * dados,st_vlDocumentoTecnico * status,Collection *_ResultadoPC );
      void carregaDados( DOMNode * dnode,st_DocumentoTecnico * dados,st_vlDocumentoTecnico * status );
      bool proCPegaDocumentoTecnico( char * dsDocumentoTecnico, int idDocumentoTecnico );

	private:
		void sql_error_WF_LstDocAssoc( sqlca * sqlca );

};

