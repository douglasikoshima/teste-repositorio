///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase Pessoa
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:15 $
 **/
///////////////////////////////////////////////////////////////////////////////

#ifndef PESSOAH
#define PESSOAH

#include "tuxfw.h"
#include "Pessoapc.h"

class CPessoa : private TuxHelper
{
public:
	TPessoa         tPessoa;
    TPessoaDePara   tPessoaDePara;

	CPessoapc       clPessoapc;

    CPessoa(void);
    CPessoa(char* pszIdPessoaSistemaOrigem, char* pszIdSistemaOrigem);

    void mapeiaPessoa(DOMNode*dnode);
    void resetIdPessoaDePara(void);

    void setIdPessoa(char *pszIdPessoa);
    void setIdSistemaOrigem(char *pszIdSistemaOrigem);
    void setIdPessoaSistemaOrigem(char *pszIdPessoaSistemaOrigem);
    void setIdPessoaSistemaOrigem(char *pszPrefix, char *pszIdPessoaSistemaOrigem);
    void setTsSincronismo(char *pszTsSincronismo);
    void setSqSincronismo(char *pszSqSincronismo);
    void setDeParaIdPessoa(char *pszIdPessoa);
    void setDeParaIdPessoaOrigem(char *pszIdPessoaOrigem);
    void setIdPessoaDePara(char *pszIdPessoaDePara);
    void setNmContato(char *pszNmContato);
    void setDsCargoContato(char *pszDsCargoContato);
    void setDsDeptoContato(char *pszDsDeptoContato);
    void setInFalecimentoInformado(char *pszInFalecimentoInformado);
    void setIdUf(char *pszIdUf);
    void setIdTipoCarteira(char *pszIdTipoCarteira);
    void setIdProbInadimplencia(char *pszIdProbInadimplencia);
    void setIdChurnProbabilidade(char *pszIdChurnProbabilidade);
    void setDtCadastro(char *pszDtCadastro);
    void setIdTipoPessoa(char *pszIdTipoPessoa);
    void setStruct(TPessoa *ptPessoa);
    void setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao);
    void setIdUsuarioAlteracaoDePara(char *pszIdUsuarioAlteracaoDePara);

    char *getIdPessoa(void);
    char *getIdPessoaDePara(void);
    char *getIdTipoPessoa(void);
    char *getIdSistemaOrigem(void);
    char *getSqSincronismo(void);
    char *getTsSincronismo(void);
    char *getIdPessoaSistemaOrigem(void);

    bool buscaPessoa(TPessoa &tPessoaAUX);
    bool buscaPessoa(void);
    bool buscaPessoaDePara(TPessoaDePara &tPessoaDeParaAUX);
    bool buscaPessoaDePara(void);
    bool buscaIdPessoaDePara(void);
    bool buscaIdPessoaDePara(TPessoaDePara &tPessoaDeParaAUX);
    void atualizaPessoa(void);
    void inserePessoa(void);
    void falsoInserePessoa(TPessoa &tPessoaAUX);
    void falsoInserePessoa(void);
    void atualizaGestorConta(void);
    void insereGestorConta(void);

	bool buscaPessoaPorIdPessoa( void );
    bool buscaPessoaPorNrGrupo(TPessoa &tPessoaAUX);
};

#endif
