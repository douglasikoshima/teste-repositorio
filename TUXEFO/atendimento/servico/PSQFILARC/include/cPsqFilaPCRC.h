/**
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:41 $
 **/

#ifndef SQLCA
	#define SQLCA_NONE

#include<sqlca.h>
#include<sqlda.h>
#include<ctype.h>

#endif

#include <tuxfw.h>
#include"../../../commons/Collection/include/Collection.h"
#include"../../../commons/queryMacro.h"

class cPsqFilaPCRC
{
	public:
		void obtemGrupos(int* _idPessoaUsuario, Collection* _grupos);

	private:
		void sql_error_WFPesquisaFila( sqlca * sqlca );
};
