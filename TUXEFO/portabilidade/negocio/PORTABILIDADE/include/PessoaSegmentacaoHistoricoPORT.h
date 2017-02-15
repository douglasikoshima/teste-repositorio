#ifndef PESSOASEGMENTACAOHISTORICOHPORT
#define PESSOASEGMENTACAOHISTORICOHPORT

#include "PPGlobalPORT.h"
#include "PessoaSegmentacaoHistoricopcPORT.h"

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
    char *getIdPessoaDePara(void);
};

#endif
