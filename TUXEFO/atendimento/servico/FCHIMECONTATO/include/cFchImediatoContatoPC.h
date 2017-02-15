/**
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:15 $
 **/

#ifndef SQLCA
	#define SQLCA_NONE

#include<sqlca.h>
#include<sqlda.h>
#include<ctype.h>

#endif

#include <tuxfw.h>
#include"../../../commons/queryMacro.h"
#include "stFchImediatoContato.h"

class cFchImediatoContatoPC
{
	st_FchImediatoContato* dados;
	TuxHelper	  tx;

	public:
		cFchImediatoContatoPC(st_FchImediatoContato* origem);
		~cFchImediatoContatoPC();
		void dataAtual(char* data);
		void dataAndamento(char* data);
		int  obtemBaixaMensagem(int* _idBaixa, int* _idMensagemBaixa);
		int  verificaBaixas(long * _idAtendimento);

	private:
		void sql_error_WFFchImediatoAtendimento( sqlca * sqlca );
};
