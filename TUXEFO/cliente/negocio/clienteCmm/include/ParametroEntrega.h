#ifndef PARAMETROENTREGAPCH
#define PARAMETROENTREGAPCH

#include "Global.h"

/* quantidade de elementos alocados inicialmente */
#define QTD_ELEMENTOS                   30

/* Identificadores para os diversos tipos de pesquisa */
#define ID_LISTA_UF                     1
#define ID_LISTA_SO                     2
#define ID_BUSCA                        3

class CParametroEntregapc
{

public:

    CParametroEntregapc(void);
    ~CParametroEntregapc(void);

    TParametroEntrega *buscaParametroEntrega(int iBusca);

    void insereParametroEntrega(void);
    void atualizaParametroEntrega(void);

    void clearStruct(void);

    void setIdSistemaOrigem(char *pszIdSistemaOrigem);
    void setNmSistemaOrigem(char *pszNmSistemaOrigem);
    void setUFRegiao(char *pszUFRegiao);
    void setQtDiaPicking(char *pszQtDiaPicking);
    void setQtDiaFaturamento(char *pszQtDiaFaturamento);
    void setQtDiaEntrega(char *pszQtDiaEntrega);
    void setQtDiaFornecimento(char *pszQtDiaFornecimento);
    void setQtDiaRegistroSaida(char *pszQtDiaRegistroSaida);
    void setQtDiaConfirmaPicking(char *pszQtDiaConfirmaPicking);

    char *getUFRegiao(void);
    char *getIdSistemaOrigem(void);

private:

    TParametroEntrega tParametroEntregaInput;
    TParametroEntrega *m_ptParametroEntrega;

    int m_iQtdElementosAlocados;
    int m_iQtdElementosObtidos;

    void proCBuscaParametroEntrega(void);
    void proCBuscaUF(void);
    void proCBuscaSistemaOrigem(void);

    void controleAlocacao(void);
};

#endif
