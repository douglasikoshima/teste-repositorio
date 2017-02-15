#ifndef APOIOSTATUSUSUARIOH
#define APOIOSTATUSUSUARIOH

#include "Global.h"

#define QTD_ELEMENTOS_STATUS_USUARIO              10  /* quantidade de elementos alocados inicialmente */

class ApoioStatusUsuario
{
public:
    ApoioStatusUsuario(void);
    ~ApoioStatusUsuario(void);

    void clearApoioStatusUsuario(void);


    TApoioStatusUsuario *buscaApoioStatusUsuario(void);

private:
    TApoioStatusUsuario m_tApoioStatusUsuario;
    TApoioStatusUsuario *m_ptApoioStatusUsuario;

    int m_iQtdElementosAlocados;
    int m_iQtdElementosObtidos;

    void buscaApoioStatusUsuarioPc(void);
    void controleAlocacao(void);
};
#endif
