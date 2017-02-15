#ifndef SETLINHASH
#define SETLINHASH

#include "Global.h"

#define XML_IN_IDPESSOALINHAHISTORICO       "idLinha"
#define XML_IN_IDGRUPO                      "idGrupoAssociado"

typedef struct {
    char szIdPessoaLinhaHistorico[LEN_IDPESSOALINHAHISTORICO + LEN_EOS];
    char szIdGrupo[LEN_IDGRUPO + LEN_EOS];
} TXmlIn;

#endif
