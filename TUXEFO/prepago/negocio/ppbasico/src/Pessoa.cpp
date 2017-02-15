///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase Pessoa
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @author  Eder Martins
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:22 $
 **/
///////////////////////////////////////////////////////////////////////////////

#include "Pessoa.h"
#include "PrePagoException.h"
#include "Tools.h"

CPessoa::CPessoa(void)
{
    memset(&tPessoa, 0x00, sizeof(TPessoa));
    memset(&tPessoaDePara, 0x00, sizeof(TPessoaDePara));
 
    strcpy(tPessoaDePara.szIdPessoa      , "-1");
    strcpy(tPessoaDePara.szIdPessoaDePara, "-1");
    strcpy(tPessoaDePara.szIdPessoaOrigem, "-1");
}

CPessoa::CPessoa(char* pszIdPessoaSistemaOrigem, char* pszIdSistemaOrigem)
{
    memset(&tPessoa, 0x00, sizeof(TPessoa));
    strcpy(tPessoa.szIdPessoaSistemaOrigem, pszIdPessoaSistemaOrigem);
    strcpy(tPessoa.szIdSistemaOrigem, pszIdSistemaOrigem);
}

void CPessoa::resetIdPessoaDePara(void)
{
    strcpy(tPessoaDePara.szIdPessoa      , "-1");
    strcpy(tPessoaDePara.szIdPessoaDePara, "-1");
    strcpy(tPessoaDePara.szIdPessoaOrigem, "-1");
}

void CPessoa::setIdPessoa(char *pszIdPessoa)
{
    strcpy(tPessoa.szIdPessoa, pszIdPessoa);
}

void CPessoa::setIdSistemaOrigem(char *pszIdSistemaOrigem)
{
     strcpy(tPessoa.szIdSistemaOrigem, pszIdSistemaOrigem);
}

void CPessoa::setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao)
{
    strcpy(tPessoa.szIdUsuarioAlteracao, pszIdUsuarioAlteracao);
}

void CPessoa::setIdUsuarioAlteracaoDePara(char *pszIdUsuarioAlteracaoDePara)
{
    strcpy(tPessoaDePara.szIdUsuarioAlteracao, pszIdUsuarioAlteracaoDePara);
}

void CPessoa::setIdPessoaSistemaOrigem(char *pszIdPessoaSistemaOrigem)
{
    strcpy(tPessoa.szIdPessoaSistemaOrigem, pszIdPessoaSistemaOrigem);
}

void CPessoa::setIdPessoaSistemaOrigem(char *pszPrefix, char *pszIdPessoaSistemaOrigem)
{
    sprintf(tPessoa.szIdPessoaSistemaOrigem, "%s%s", pszPrefix, pszIdPessoaSistemaOrigem);
}

void CPessoa::setTsSincronismo(char *pszTsSincronismo)
{
    strcpy(tPessoa.szTsSincronismo, pszTsSincronismo);
}

void CPessoa::setSqSincronismo(char *pszSqSincronismo)
{
    strcpy(tPessoa.szSqSincronismo, pszSqSincronismo);
}

void CPessoa::setDeParaIdPessoa(char *pszIdPessoa)
{
    strcpy(tPessoaDePara.szIdPessoa, pszIdPessoa);
}

void CPessoa::setIdTipoPessoa(char *pszIdTipoPessoa)
{
    strcpy(tPessoa.szIdTipoPessoa, pszIdTipoPessoa);
}

void CPessoa::setDeParaIdPessoaOrigem(char *pszIdPessoaOrigem)
{
    strcpy(tPessoaDePara.szIdPessoaOrigem, pszIdPessoaOrigem);
}

void CPessoa::setIdPessoaDePara(char *pszIdPessoaDePara)
{
    strcpy(tPessoaDePara.szIdPessoaDePara, pszIdPessoaDePara);
}

void CPessoa::setNmContato(char *pszNmContato)
{
    TDesmembraNome tDesmembraNome;

    strcpy(tDesmembraNome.szNomeCompleto, pszNmContato);

    DesmembraNome(&tDesmembraNome);

    strcpy(tPessoa.szNmPessoa   , tDesmembraNome.szNomeCompleto);
    strcpy(tPessoa.szNmNome     , tDesmembraNome.szNomePrimeiro);
    strcpy(tPessoa.szNmNomeMeio , tDesmembraNome.szNomeMeio);
    strcpy(tPessoa.szNmSobrenome, tDesmembraNome.szNomeFim);
}

void CPessoa::setDsCargoContato(char *pszDsCargoContato)
{
    strcpy(tPessoa.szDsCargoContato, pszDsCargoContato);
}

void CPessoa::setDsDeptoContato(char *pszDsDeptoContato)
{
    strcpy(tPessoa.szDsDeptoContato, pszDsDeptoContato);
}

void CPessoa::setInFalecimentoInformado(char *pszInFalecimentoInformado)
{
    strcpy(tPessoa.szInFalecimentoInformado, pszInFalecimentoInformado);
}

void CPessoa::setIdUf(char *pszIdUf)
{
    strcpy(tPessoa.szIdUf, pszIdUf);
}

void CPessoa::setIdTipoCarteira(char *pszIdTipoCarteira)
{
    strcpy(tPessoa.szIdTipoCarteira, pszIdTipoCarteira);
}

void CPessoa::setIdProbInadimplencia(char *pszIdProbInadimplencia)
{
    strcpy(tPessoa.szIdProbInadimplencia, pszIdProbInadimplencia);
}

void CPessoa::setIdChurnProbabilidade(char *pszIdChurnProbabilidade)
{
    strcpy(tPessoa.szIdChurnProbabilidade, pszIdChurnProbabilidade);
}

void CPessoa::setDtCadastro(char *pszDtCadastro)
{
    strcpy(tPessoa.szDtCadastro, pszDtCadastro);
}


char *CPessoa::getIdPessoa(void)
{
    static char szAUX[LEN_IDPESSOA + LEN_EOS];

    strcpy(szAUX, tPessoa.szIdPessoa);
    return szAUX;
}

char *CPessoa::getIdPessoaDePara(void)
{
    static char szAUX[LEN_IDPESSOADEPARA + LEN_EOS];

    strcpy(szAUX, tPessoaDePara.szIdPessoaDePara);
    return szAUX;
}

char *CPessoa::getIdTipoPessoa(void)
{
    static char szAUX[LEN_IDTIPOPESSOA + LEN_EOS];

    strcpy(szAUX, tPessoa.szIdTipoPessoa);
    return szAUX;
}

char *CPessoa::getIdSistemaOrigem(void)
{
    static char szAUX[LEN_IDSISTEMAORIGEM + LEN_EOS];

    strcpy(szAUX, tPessoa.szIdSistemaOrigem);
    return szAUX;
}

char *CPessoa::getSqSincronismo(void)
{
    static char szAUX[LEN_SQSINCRONISMO + LEN_EOS];

    strcpy(szAUX, tPessoa.szSqSincronismo);
    return szAUX;
}

char *CPessoa::getTsSincronismo(void)
{
    static char szAUX[LEN_TSSINCRONISMO + LEN_EOS];

    strcpy(szAUX, tPessoa.szTsSincronismo);
    return szAUX;
}

char *CPessoa::getIdPessoaSistemaOrigem(void)
{
    static char szAUX[LEN_IDPESSOASISTEMAORIGEM + LEN_EOS];

    strcpy(szAUX, tPessoa.szIdPessoaSistemaOrigem);
    return szAUX;
}


///////////////////////////////////////////////////////////////////////////////
// Métodos de negócio.
///////////////////////////////////////////////////////////////////////////////
bool CPessoa::buscaPessoa(TPessoa &tPessoaAUX)
{
    if(clPessoapc.proCBuscaPessoa(tPessoaAUX) == false)
        return false;

    // Prepara PessoaDePara para fazer busca.
    this->setDeParaIdPessoaOrigem(tPessoaAUX.szIdPessoa);

    return this->buscaPessoaDePara();
}

bool CPessoa::buscaPessoa(void)
{
    if(clPessoapc.proCBuscaPessoa(tPessoa) == false)
        return false;

    // Prepara PessoaDePara para fazer busca
    this->setDeParaIdPessoaOrigem(tPessoa.szIdPessoa);

    // Busca o PessoaDePara.
    return this->buscaPessoaDePara();
}

bool CPessoa::buscaPessoaDePara(TPessoaDePara &tPessoaDeParaAUX)
{
    return clPessoapc.proCBuscaPessoaDePara(tPessoaDeParaAUX);
}

bool CPessoa::buscaPessoaDePara(void)
{
    return clPessoapc.proCBuscaPessoaDePara(tPessoaDePara);
}

bool CPessoa::buscaIdPessoaDePara(void)
{
    return clPessoapc.proCBuscaIdPessoaDePara(tPessoaDePara);
}

bool CPessoa::buscaIdPessoaDePara(TPessoaDePara &tPessoaDeParaAUX)
{
    return clPessoapc.proCBuscaIdPessoaDePara(tPessoaDeParaAUX);
}

void CPessoa::atualizaPessoa(void)
{
    clPessoapc.proCAtualizaPessoa(tPessoa);
}

void CPessoa::inserePessoa(void)
{
    clPessoapc.proCInserePessoa(tPessoa);

    strcpy(tPessoaDePara.szIdPessoa, tPessoa.szIdPessoa);
    clPessoapc.proCInserePessoaDePara(tPessoaDePara);
}

void CPessoa::falsoInserePessoa(TPessoa &tPessoaAUX)
{
    strcpy(tPessoa.szIdTipoCarteira        , ID_NULL);
    strcpy(tPessoa.szIdTipoPessoa          , ID_NULL);
    strcpy(tPessoa.szInFalecimentoInformado, SEM_VALOR);
    strcpy(tPessoa.szSqSincronismo         , SEM_VALOR);
    strcpy(tPessoa.szTsSincronismo         , SEM_VALOR);
    strcpy(tPessoa.szIdUf                  , ID_NULL);
    strcpy(tPessoa.szIdProbInadimplencia   , ID_NULL);
    strcpy(tPessoa.szIdChurnProbabilidade  , ID_NULL);

    clPessoapc.proCInserePessoa(tPessoaAUX);

    strcpy(tPessoaDePara.szIdPessoa, tPessoaAUX.szIdPessoa);
    clPessoapc.proCInserePessoaDePara( tPessoaDePara );
}

void CPessoa::falsoInserePessoa(void)
{
    strcpy( tPessoa.szIdTipoCarteira        , ID_NULL );
    strcpy( tPessoa.szIdTipoPessoa          , ID_NULL );
    strcpy( tPessoa.szInFalecimentoInformado, SEM_VALOR );
    strcpy( tPessoa.szSqSincronismo         , SEM_VALOR );
    strcpy( tPessoa.szTsSincronismo         , SEM_VALOR );
    strcpy( tPessoa.szIdUf                  , ID_NULL );
    strcpy( tPessoa.szIdProbInadimplencia   , ID_NULL );
    strcpy( tPessoa.szIdChurnProbabilidade  , ID_NULL );

    clPessoapc.proCInserePessoa( tPessoa );

    strcpy(tPessoaDePara.szIdPessoa, tPessoa.szIdPessoa);
    clPessoapc.proCInserePessoaDePara( tPessoaDePara );
}

void CPessoa::atualizaGestorConta(void)
{
    strcpy( tPessoa.szIdPessoa, tPessoaDePara.szIdPessoa );

    strcpy( tPessoa.szIdTipoCarteira        , ID_NULL );
    sprintf( tPessoa.szIdTipoPessoa, "%i", PESSOA_FISICA );
    strcpy( tPessoa.szInFalecimentoInformado, SEM_VALOR );
    strcpy( tPessoa.szSqSincronismo         , SEM_VALOR );
    strcpy( tPessoa.szTsSincronismo         , SEM_VALOR );
    strcpy( tPessoa.szIdUf                  , ID_NULL );
    strcpy( tPessoa.szIdProbInadimplencia   , ID_NULL );
    strcpy( tPessoa.szIdChurnProbabilidade  , ID_NULL );

    clPessoapc.proCAtualizaPessoa(tPessoa);
}

void CPessoa::insereGestorConta(void)
{
    strcpy( tPessoa.szIdTipoCarteira        , ID_NULL );
    sprintf( tPessoa.szIdTipoPessoa, "%i", PESSOA_FISICA );
    strcpy( tPessoa.szInFalecimentoInformado, SEM_VALOR );
    strcpy( tPessoa.szSqSincronismo         , SEM_VALOR );
    strcpy( tPessoa.szTsSincronismo         , SEM_VALOR );
    strcpy( tPessoa.szIdUf                  , ID_NULL );
    strcpy( tPessoa.szIdProbInadimplencia   , ID_NULL );
    strcpy( tPessoa.szIdChurnProbabilidade  , ID_NULL );

    clPessoapc.proCInserePessoa(tPessoa);

    strcpy(tPessoaDePara.szIdPessoa, tPessoa.szIdPessoa);
    clPessoapc.proCInserePessoaDePara(tPessoaDePara);
}

void CPessoa::mapeiaPessoa(DOMNode*dnode)
{
    return;
}

void CPessoa::setStruct(TPessoa *ptPessoa)
{
    memcpy(&tPessoa, ptPessoa, sizeof(TPessoa));
    memset(ptPessoa, 0x00, sizeof(TPessoa));
}

bool CPessoa::buscaPessoaPorIdPessoa( void )
{
	return clPessoapc.proCBuscaPessoa( tPessoa );
}

bool CPessoa::buscaPessoaPorNrGrupo(TPessoa &tPessoaAUX)
{
	return clPessoapc.proCBuscaPessoaPorNrGrupo( tPessoaAUX );
}
