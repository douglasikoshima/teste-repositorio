///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Cliente
 * @usecase PessoaLinhaPre
 * @author  Renato Striitzel Russo
 * @author  Robinson Vieira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:26 $
 **/
///////////////////////////////////////////////////////////////////////////////

#ifndef PESSOALINHAPRE
#define PESSOALINHAPRE

#include "GlobalPre.h"
#include "PessoaLinhaPrepc.h"

class CPessoaLinhaPre
{

public:

    CPessoaLinhaPre(void);
    CPessoaLinhaPre(char *pszIdTipoRelacionamento, char *pszIdLinhaTelefonica);

    TPessoaLinha tPessoaLinha;
    CPessoaLinhaPrepc clPessoaLinhapc;

    bool buscaPessoaLinha(void);
    bool buscaPessoaLinha(TPessoaLinha *ptPessoaLinhaAux);
    void falsoInsertPessoaLinha(void);
    void atualizaPessoaLinha(void);
    void inserePessoaLinha(void);
    void apagaPessoaLinha(void);

    void setIdPessoaDePara(char *pszIdPessoaDePara);
    void setIdLinhaTelefonica(char *pszIdLinhaTelefonica);
    void setIdTipoRelacionamento(char *pszIdTipoRelacionamento);
    void setIdPessoaLinha(char *pszIdPessoaLinha);
};

#endif
