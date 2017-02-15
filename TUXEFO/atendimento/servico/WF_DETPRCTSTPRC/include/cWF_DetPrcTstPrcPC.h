

#undef SQLCA
#define SQLCA_NONE
#include <sqlca.h>
#include <sqlda.h>
#include <ctype.h>
#include <tuxfw.h>

#include "../../../commons/queryMacro.h"
#include "../../../commons/Collection/include/Collection.h"
#include "../../../commons/msgPadrao.h"


class cWF_DetPrcTstPrcPC
{
	public:
      int SaveTesteResposta( char crs,int idt,int iat,int iusr );
      int SaveAtendimentoTeste( int iat,int iusr,char*pobs );

   private:
		void sql_error_DetalheProcesso( sqlca * sqlca );
};

