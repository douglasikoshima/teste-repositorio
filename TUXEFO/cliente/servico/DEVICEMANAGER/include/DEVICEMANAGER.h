#ifndef DEVICEMANAGERH
#define DEVICEMANAGERH

#include "Global.h"

#define ID_CONSULTA_APARELHO                "1"
#define ID_CONSULTA_HISTORICO               "2"
#define ID_ATUALIZA_APARELHO                "3"


#define LEN_ERRORDESCRIPTION                255
#define LEN_ERRORCODE                       255
#define LEN_APARELHOPARAMETROSNOME          255
#define LEN_APARELHODTASSOCIACAO            LEN_DATA_HORA
#define LEN_TIPOOPERACAO                    1
#define LEN_MSIDN                           10


typedef struct {
    char szIP[LEN_IP + LEN_EOS];
    char szMSISDN[LEN_MSIDN + LEN_EOS];
    char szTipoOperacao[LEN_TIPOOPERACAO + LEN_EOS];
    char szIdLinhaTelefonica[LEN_IDLINHATELEFONICA + LEN_EOS];
    char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char szAparelhoParametrosNome[LEN_APARELHOPARAMETROSNOME + LEN_EOS];
} TDadosEntrada;

typedef struct {
    char szMSISDN[LEN_MSIDN + LEN_EOS];
    char szTipoOperacao[LEN_TIPOOPERACAO + LEN_EOS];
    char szAparelhoParametrosNome[LEN_APARELHOPARAMETROSNOME + LEN_EOS];
} TDadosEntradaDeviceManager;

#endif
