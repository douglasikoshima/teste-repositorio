#ifndef LINHABASEHPORT
#define LINHABASEHPORT

#include "tuxfw.h"
#include "LinhaBasepcPORT.h"

class CLinhaBase:private TuxHelper
{
public:
	TLinhaBase tLinhaBase;
	CLinhaBasepc clLinhaBasepc;

    CLinhaBase(void);

    void insereLinhaBase(void);

    void setIdAreaRegistro(char *pszIdAreaRegistro);
    void setNrLinha(char *pszNrLinha);
    void setNrMin(char *pszNrMin);
    void setNrDigitoLinha(char *pszNrDigitoLinha);
    void setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao);
    void setIdEstadoLinha(char *pszIdEstadoLinha);
    void setDtEstadoLinha(char *pszDtEstadoLinha);
    void setDsMotivoEstado(char *pszDsMotivoEstado);
    void setIdNumeroSistemaOrigem(char *pszIdNumeroSistemaOrigem);
    void setIdSistemaOrigem(char *pszIdSistemaOrigem);

};

#endif
