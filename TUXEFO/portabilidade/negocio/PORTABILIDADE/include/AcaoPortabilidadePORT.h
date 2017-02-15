#ifndef ACAOPORTABILIDADEHPORT
#define ACAOPORTABILIDADEHPORT

#include "tuxfw.h"
#include "PPGlobalPORT.h"
#include "AcaoPortabilidadepcPORT.h"

class CAcaoPortabilidade
{

public:
	TAcaoPortabilidade tAcaoPortabilidade;
	CAcaoPortabilidadepc clAcaoPortabilidadepc;

    CAcaoPortabilidade(void);
    bool buscaAcaoPortabilidade(void);

    void setDsAcaoPortabilidade(char *pszDsAcaoPortabilidade);

    char *getIdAcaoPortabilidade(void);
    char *getDsAcaoPortabilidade(void);
};

#endif
