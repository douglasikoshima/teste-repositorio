///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase PessoaDocumento
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:26 $
 **/
///////////////////////////////////////////////////////////////////////////////

#ifndef PESSOADPC
#define PESSOADPC

#include "Global.h"

class CPessoaDocumentopc
{
public:
    void proCInserePessoaDocumento(TPessoaDocumento *ptPessoaDocumento);
    void proCAtualizaPessoaDocumento(TPessoaDocumento tPessoaDocumento);
    bool proCBuscaPessoaDocumento(TPessoaDocumento *ptPessoaDocumento);
    void proCApagaPessoaDocumento(TPessoaDocumentoB01 tPessoaDocumentoB01);
    bool proCBuscaPessoaDocumentoB01(TPessoaDocumentoB01 *ptPessoaDocumentoB01);
	bool proCBuscaPessoaDocumentoB01(
		TPessoaDocumentoB01 *ptPessoaDocumentoB01,
		TPessoaDocumentoB01 *ptPessoaDocumentoB01a,
		TPessoaDocumentoB01 *ptPessoaDocumentoB01b,
		char *pszIdPessoa);
    bool proCBuscaPessoaDocumentoApagaB01(TPessoaDocumentoB01 *ptPessoaDocumentoB01);
};

#endif
