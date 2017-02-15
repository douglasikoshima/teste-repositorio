#ifndef GETDADOSCHIPH
#define GETDADOSCHIPH

#define LEN_TIPOCHIP                30 // verificar
#define LEN_TAMANHOCHIP             30 // verificar
#define LEN_PUK1                    30 // verificar
#define LEN_PUK2                    30 // verificar
#define LEN_ERROR_CODE              10 // Verificar
#define LEN_ERROR_DESCRIPTION       100 // Verificar

typedef struct {
    char szNrLinha[LEN_NRLINHA + LEN_EOS];
    char szIdLinhaTelefonica[LEN_IDLINHATELEFONICA + LEN_EOS];
    char szICCID[LEN_ICCID + LEN_EOS];
    char szTipoChip[LEN_TIPOCHIP + LEN_EOS];
    char szTamanhoChip[LEN_TAMANHOCHIP + LEN_EOS];
    char szPUK1[LEN_PUK1 + LEN_EOS];
    char szPUK2[LEN_PUK2 + LEN_EOS];
    char szNrIP[LEN_IP + LEN_EOS];
    char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char szErrorCode[LEN_ERROR_CODE + LEN_EOS];
    char szErrorDescription[LEN_ERROR_DESCRIPTION + LEN_EOS];
} TGetDadosChip;


char *ObtemIdTipoLinha(char *pszNrLinha);

#endif
