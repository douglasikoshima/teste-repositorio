/**
 * 
 * @modulo  Batch
 * @usecase Batch
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.4.2 $
 * @CVS     $Author: cmgarcia $ - $Date: 2008/08/12 15:31:04 $
 **/

#ifndef PRIORIZACAOATENDIMENTOPORTOUTPC
#define PRIORIZACAOATENDIMENTOPORTOUTPC

#undef SQLCA
#define SQLCA_NONE
#include <sqlca.h>
#include <sqlda.h>
#include <ctype.h>

#include <stdlib.h>

#include "../../commons/Propriedade/include/Propriedade.h"
#include "../../commons/queryMacro.h"
#include "../../commons/Log/include/Log.h"
#include "PriorizacaoAtendimentoPortoutSt.h"

class cPriorizacaoAtendimentoPortoutPC
{
	public:
		void processaAtualizacao();
        void atualizaAtendimentoGrupoAtual(int vlLog);
        void atualizaAtendimentoAndamentoAtual(int vlLog);

	private:
        Log logFile;
        char linha[2048];
        bool calcularPesoMaximo(double *pesoMaximo,ParametrosCalculo *parametrosCalculo);
        bool obterParametrosCalculo(ParametrosCalculo *parametrosCalculo);
};
 
#endif
