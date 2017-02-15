///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase PessoaLinhaHistorico
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:15 $
 **/
///////////////////////////////////////////////////////////////////////////////

#ifndef PESSOALINHAPRE
#define PESSOALINHAPRE

#include "Global.h"
#include "PessoaLinhaPrepc.h"

class CPessoaLinhaPre
{
public:
    TPessoaLinhaPre tPessoaLinhaPre;
    CPessoaLinhaPrepc clPessoaLinhaPrepc;
    
    CPessoaLinhaPre(void);  
    void inserePessoaLinhaPre(void);
    bool buscaPessoaLinhaPre(TPessoaLinhaPre *ptPessoaLinhaPre);
    bool buscaPessoaLinhaPre(void);
    void atualizaPessoaLinhaPre(void);

    void setIdPessoaLinha(char *pszIdPessoaLinha);
    void setInMudancaTitularidade(char *pszInMudancaTitularidade);
    void setInSincronismo(char *pszInSincronismo);
    void setInUsuarioNaoInformado(char *pszInUsuarioNaoInformado);
    void insereMudancaTitularidade(char* szCodAreaRegistro, char* szNrLinha);

    void setStruct(TPessoaLinhaPre *ptPessoaLinhaPre);
};
#endif
