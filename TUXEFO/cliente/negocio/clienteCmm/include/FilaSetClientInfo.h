#ifndef FILASETCLIENTINFOH
#define FILASETCLIENTINFOH

#include "Global.h"

#define QTD_ELEMENTOS_FILA_PRE              50  /* quantidade de elementos alocados inicialmente */

class FilaSetClientInfo
{
public:
    FilaSetClientInfo(void);
    ~FilaSetClientInfo(void);

    void setIdFilaSetClientInfo(char *pszIdFilaSetClientInfo);
    void setCdErro(char *pszCdErro);
    void clearFilaSetClientInfo(void);

    void setRegIni(char *pszRegIni);
    void setRegFim(char *pszRegFim);

    TFilaSetClientInfo *buscaFilaSetClientInfoGroupError(void);
    TFilaSetClientInfo *buscaFilaSetClientInfoDetailError(void);

    void apagaGrupoErroFilaSetClientInfo(void);
    void atualizaGrupoErroFilaSetClientInfo(void);

    void apagaIdFilaSetClientInfo(void);
    void atualizaIdFilaSetClientInfo(void);

    void buscaFilaSetClientInfo(TFilaSetClientInfo *ptFilaSetClientInfo);
    TFilaSetClientInfo *buscaFilaSetClientInfoCdErro(char *pszCdErro);

private:
    TFilaSetClientInfo m_tFilaSetClientInfo;
    TFilaSetClientInfo *m_ptFilaSetClientInfo;
    int m_iQtdElementosAlocados;
    int m_iQtdElementosObtidos;

    int m_iRegIni;
    int m_iRegFim;

    void buscaFilaSetClientInfoPcGroupError(void);
    void buscaFilaSetClientInfoPcDetailError(void);
    void controleAlocacao(void);

    int getRegIni(void);
    int getRegFim(void);
};
#endif
