#ifndef _FILAPREENVIOSETCLIINFOPC_H_
#define _FILAPREENVIOSETCLIINFOPC_H_

#include "PPGlobalTMA.h"

class CFilaPreEnvioSetCliInfopc
{

public:
    void inserir(const char *cdAreaRegistro);

    void setIdFilaPreEnvioSetCliInfo(const char *valor) { strcpy(tilaPreEnvioSetCliInfo.idFilaPreEnvioSetCliInfo,valor); }
    void setIdPessoaEndereco(const char *valor) { strcpy(tilaPreEnvioSetCliInfo.idPessoaEndereco,valor); }
    void setIdAreaRegistro(const char *valor) { strcpy(tilaPreEnvioSetCliInfo.idAreaRegistro,valor); }
    void setNrLinha(const char *valor) { strcpy(tilaPreEnvioSetCliInfo.nrLinha,valor); }
    void setIdUsuarioAlteracao(const char *valor) { strcpy(tilaPreEnvioSetCliInfo.idUsuarioAlteracao,valor); }

private:
    TFilaPreEnvioSetCliInfo tilaPreEnvioSetCliInfo;

};

#endif
