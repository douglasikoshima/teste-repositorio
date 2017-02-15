///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  PrePago
 * @usecase PessoaSegmentacaoHistorico
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:15 $
 **/
///////////////////////////////////////////////////////////////////////////////

#ifndef PESSOASEGMENTACAOHISTORICOH
#define PESSOASEGMENTACAOHISTORICOH

#include "Global.h"
#include "PessoaSegmentacaoHistoricopc.h"

class CPessoaSegmentacaoHistorico
{

public:

    CPessoaSegmentacaoHistorico(void);

    TPessoaSegmentacaoHistorico tPessoaSegmentacaoHistorico;
    CPessoaSegmentacaoHistoricopc clPessoaSegmentacaoHistoricopc;

    bool buscaPessoaSegmentacaoHistorico(void);
    bool buscaPessoaSegmentacaoHistorico(TPessoaSegmentacaoHistorico *ptPessoaSegmentacaoHistoricoAux);
    void inserePessoaSegmentacaoHistorico(void);

    void setIdPessoaSegmentacao(char *pszIdPessoaSegmentacao);
    void setIdSegmentacao(char *pszIdSegmentacao);
    void setIdPessoaDePara(char *pszIdPessoaDePara);
    void setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao);

    char *getIdSegmentacao(void);
    char *getIdPessoaSegmentacao(void);
};

#endif
