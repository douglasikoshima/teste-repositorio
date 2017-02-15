///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase PessoaComunicacao
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:15 $
 **/
///////////////////////////////////////////////////////////////////////////////

#ifndef PESSOACOMUNICACAOPCH
#define PESSOACOMUNICACAOPCH

#include "Global.h"

class CPessoaComunicacaopc
{
public:
    void proCApagaPessoaComunicacao( TPessoaComunicacao tDadosPessoaComunicacao );
    void proCAtualizaPessoaComunicacao( TPessoaComunicacao tDadosPessoaComunicacao );
    bool proCBuscaPessoaComunicacao( TPessoaComunicacao &tDadosPessoaComunicacao );
    void proCInserePessoaComunicacao( TPessoaComunicacao &tDadosPessoaComunicacao );
	//Busca tipos de comunicacao atravez de um idpessoa
	bool proCBuscaPessoaComunicacaoPorIdPessoa( TPessoaComunicacaoArr* tDadosPessoaComunicacaoArr, TPessoaComunicacao &tDadosPessoaComunicacao );
    bool proCBuscaTodosPessoaComPorIdPessoa( TPessoaComunicacaoArr *tDadosPessoaComunicacaoArr, TPessoaComunicacao &tDadosPessoaComunicacao );

	//Método para manipular o tipo TPessoaEnderecoArr
	void desaloca( TPessoaComunicacaoArr* pztPessoaComunicacaoArrAux );

private:
	//Método para manipular o tipo TPessoaEnderecoArr
	void aloca( TPessoaComunicacaoArr* pztPessoaComunicacaoArrAux );
};

#endif
