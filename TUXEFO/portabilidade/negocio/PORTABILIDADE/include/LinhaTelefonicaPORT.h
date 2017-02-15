#ifndef LINHATELEFONICAHPORT
#define LINHATELEFONICAHPORT

#include "tuxfw.h"
#include "LinhaTelefonicapcPORT.h"

class CLinhaTelefonica:private TuxHelper
{
public:
	TLinhaTelefonica tLinhaTelefonica;
	CLinhaTelefonicapc clLinhaTelefonicapc;

    CLinhaTelefonica(void);

    void insereLinhaTelefonica(void);

    void setIdSistemaOrigem(char *pszIdSistemaOrigem);
    void setIdLinhasistemaOrigem(char *pszIdLinhasistemaOrigem);
    void setIdTipoLinha(char *pszIdTipoLinha);
    void setInDivulgacaoNrLinha(char *pszInDivulgacaoNrLinha);
    void setCdChurnProbabilidade(char *pszCdChurnProbabilidade);
    void setVlrChurnProbabilidade(char *pszVlrChurnProbabilidade);
    void setIdLinhaBase(char *pszIdLinhaBase);
    void setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao);
    void setInUsuarioInformado(char *pszInUsuarioInformado);

};

#endif
