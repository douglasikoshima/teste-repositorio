#ifndef CLASSAPOIOPARAMETROH
#define CLASSAPOIOPARAMETROH

#include "Global.h"


EXEC SQL BEGIN DECLARE SECTION;

typedef struct{
    VARCHAR oszIdParametro[LEN_IDPARAMETRO + LEN_EOS];
    VARCHAR oszCdParametro[LEN_CDPARAMETRO + LEN_EOS];
    VARCHAR oszDsParametro[LEN_DSPARAMETRO + LEN_EOS];
    VARCHAR oszDsValorParametro[LEN_DSVALORPARAMETRO + LEN_EOS];
    VARCHAR oszIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    VARCHAR oszDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];

    short oiIdParametro;
    short oiCdParametro;
    short oiDsParametro;
    short oiDsValorParametro;
    short oiIdUsuarioAlteracao;
    short oiDtUltimaAlteracao;
} TAPOIOPARAMETRO;

EXEC SQL END DECLARE SECTION;


class CApoioParametro {
public:
    CApoioParametro();
    ~CApoioParametro();

    void buscaApoioParametro(char *pszCdParametro);

    char* getCdParametro(void);
    char* getIdParametro(void);
    char* getDsParametro(void);
    char* getDsValorParametro(void);

private:
    EXEC SQL BEGIN DECLARE SECTION;
        TAPOIOPARAMETRO tApoioParametro;
    EXEC SQL END DECLARE SECTION;
};
#endif
