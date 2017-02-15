///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase PessoaLinha
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:15 $
 **/
///////////////////////////////////////////////////////////////////////////////

#ifndef PESSOALINHAHPC
#define PESSOALINHAHPC

#include "Global.h"

class CPessoaLinhapc
{
  public:
    bool proCBuscaPessoaLinha(TPessoaLinha *ptPessoaLinha);
    void proCInserePessoaLinha(TPessoaLinha *ptPessoaLinha);
    void proCAtualizaPessoaLinha(TPessoaLinha tPessoaLinha);
    void proCApagaPessoaLinha(TPessoaLinha tPessoaLinha);
    void proCAtualizaPessoaLinhaPorIdConta(char *pszIdPessoaDePara, char *pszIdConta);

	/********************************************************************************
	* OBSERVACAO: Este metodo retorna o IDPESSOA no lugar do campo IDUSUARIOALTERACAO
	 ********************************************************************************/
    bool proCBuscaIdClienteUsuario(TPessoaLinhaArr *ptPessoaLinhaArr, TPessoaLinha *ptPessoaLinha);

	//Método para manipular o tipo TPessoaLinhaArr
	void desaloca( TPessoaLinhaArr* pztPessoaLinhaArrAux );

private:
	//Método para manipular o tipo TPessoaLinhaArr
	void aloca( TPessoaLinhaArr* pztPessoaLinhaArrAux );
};

#endif
