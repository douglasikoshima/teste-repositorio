#ifndef GETSIMLOCKH
#define GETSIMLOCKH

#include "Global.h"

#define LEN_SISTEMAOPERACIONAL              5
#define LEN_IDGRUPOABERTURA                 LEN_NUMBER_ORA
#define LEN_SIMLOCK                         8 * 10 /* saida em decimal */
#define LEN_SIMLOCK2                        8 * 10 * 2 /* saida em decimal */
#define LEN_SIMLOCK3DES                     LEN_SIMLOCK * 2 /* entrada em hexa */
#define LEN_COD                             5
#define LEN_MSG                             255
#define LEN_ERRORDESCRIPTION                LEN_MSG
#define LEN_ERRORCODE                       LEN_COD
#define LEN_VALOR                           16 * 3 /* as 3 chaves estao sendo passadas juntas */
#define LEN_TPOPERACAO                      1
#define LEN_INRESPONSAVELABERTURA           1
#define LEN_KEY                             16 /* tamanho da chave em hexa */


typedef struct {
    char szSIMLock[LEN_SIMLOCK2 + LEN_EOS];
    char szErrorDescription[LEN_ERRORDESCRIPTION + LEN_EOS];
    char szErrorCode[LEN_ERRORCODE + LEN_EOS];
} TDadosSaida;

typedef struct {
    char szIMEI[LEN_IMEI + LEN_EOS];
    char szIP[LEN_IP + LEN_EOS];
    char szInResponsavelAbertura[LEN_INRESPONSAVELABERTURA + LEN_EOS];
    char szIdTipoDocumento[LEN_IDTIPODOCUMENTO + LEN_EOS];
    char szNrDocumento[LEN_NRDOCUMENTO + LEN_EOS];
    char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char szCdAreaRegistro[LEN_CDAREAREGISTRO + LEN_EOS];
    char szNrLinha[LEN_NRLINHA + LEN_EOS];
    char szIdGrupoAbertura[LEN_IDGRUPOABERTURA + LEN_EOS];
    char szIdPessoa[LEN_IDPESSOA + LEN_EOS];
} TDadosEntrada;

typedef struct {
    char szSistemaOperacional[LEN_SISTEMAOPERACIONAL + LEN_EOS];
    char szIMEI[LEN_IMEI + LEN_EOS];
    char szCdAreaRegistro[LEN_CDAREAREGISTRO + LEN_EOS];
    char szNrLinha[LEN_NRLINHA + LEN_EOS];
    char szIdGrupoAbertura[LEN_IDGRUPOABERTURA + LEN_EOS];
    char szIdPessoa[LEN_IDPESSOA + LEN_EOS];

    char szInResponsavelAbertura[LEN_INRESPONSAVELABERTURA + LEN_EOS];
    char szTpOperacao[LEN_TPOPERACAO + LEN_EOS]; // fixo 1
    char szIdProcedencia[LEN_IDPROCEDENCIA + LEN_EOS];
    char szIdCanal[LEN_IDCANAL + LEN_EOS];
    char szIdContato[LEN_IDCONTATO + LEN_EOS];
} TDadosEntradaVivoSafe;

typedef struct {
    char szSIMLock3des[LEN_SIMLOCK3DES + LEN_EOS];
    char szCod[LEN_COD + LEN_EOS];
    char szMsg[LEN_MSG + LEN_EOS];
    char szValor[LEN_VALOR + LEN_EOS];
} TDadosRetornoVivoSafe;

#endif
