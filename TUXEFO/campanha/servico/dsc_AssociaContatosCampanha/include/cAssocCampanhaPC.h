/**
 * 
 * @modulo  Commons
 * @usecase Envio de e-mails.
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:16 $
 **/ 
#include <stdio.h>
#include <tuxfw.h>

#undef SQLCA
#define SQLCA_NONE
#include<sqlca.h>
#include<sqlda.h>
#include<ctype.h>

#ifndef CAssocCampanhaPC
	#define CAssocCampanhaPC


#include"../../commons/Collection/include/Collection.h"
#include"../../commons/queryMacro.h"
#include"RegistroContato.h"
#include"stAssocCampanha.h"

class cAssocCampanhaPC
{

	public:

		cAssocCampanhaPC();
		st_DadosCampanha buscaDadosCampanha(int idSubCampanhaHistorico);
		Collection* buscaDadosCarga(int idSubCampanhaHistorico);
		Collection* buscaDadosUsuarioCampanha(int idSubCampanhaHistorico);

	private:
		void sql_error_DiscadorCampanha(sqlca*sqlca);

};

#endif

