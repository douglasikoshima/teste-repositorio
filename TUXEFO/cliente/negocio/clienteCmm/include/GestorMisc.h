#ifndef GESTORMISCPCH
#define GESTORMISCPCH

#include "Global.h"

#define QTD_ELEMENTOS_GESTOR    30  /* quantidade de elementos alocados inicialmente */

/* Identificadores para os diversos tipos de pesquisa */
#define ID_CONTAS_                  1
#define ID_CNPJ_                    2
#define ID_GESTOR_CONTA_            3
#define ID_LISTA_UF_                4
#define ID_BUSCA_TELEFONE           5
#define ID_BUSCA_ENDERECO           6
#define ID_BUSCA_CONTA              7

typedef struct{
    char szIdNrCPF[LEN_IDNRCPF + LEN_EOS];
    char szIdConta[LEN_IDCONTA + LEN_EOS];
    char szCdConta[LEN_CDCONTA + LEN_EOS];

    char szNmNomeGestor[LEN_NMNOMEGC + LEN_EOS];
    char szNmNomeCliente[LEN_NMPESSOA + LEN_EOS];
    char szIdNrLinha[LEN_IDNRLINHA + LEN_EOS];
    char szDsTipoGestor[LEN_DSTIPOGESTOR + LEN_EOS];

    char szIdUF[LEN_IDUF + LEN_EOS];
    char szSgUF[LEN_SGUF + LEN_EOS];

    char szCdAreaRegistro[LEN_CDAREAREGISTROGC + LEN_EOS];
    char szDsTipoComunicacao[LEN_IDTIPOCOMUNICACAO + LEN_EOS];
    char szNrRamal[LEN_NRRAMAL + LEN_EOS];

    char szNmLogradouro[LEN_NMLOGRADOUROGC + LEN_EOS];
    char szNrEndereco[LEN_NRENDERECOGC + LEN_EOS];
    char szNmEnderecoComplemento[LEN_NMENDERECOCOMPLEMENTO + LEN_EOS];
    char szNmBairro[LEN_NMBAIRROGC + LEN_EOS];
    char szNmCidade[LEN_NMCIDADE + LEN_EOS];
    char szNrCEP[LEN_NRCEPGC + LEN_EOS];
} TGestorMisc;


typedef struct{
    char szIdConta[LEN_IDCONTA + LEN_EOS];
    char szCdConta[LEN_CDCONTA + LEN_EOS];
    char szNrDocumento[LEN_NRDOCUMENTO + LEN_EOS];
    char szIdPessoaConta[LEN_IDPESSOACONTA + LEN_EOS];

    char szNmNome[LEN_NMNOME + LEN_EOS];
    char szNmNomeMeio[LEN_NMNOMEMEIO + LEN_EOS];
    char szNmSobreNome[LEN_NMSOBRENOME + LEN_EOS];
    char szCdContaCliente[LEN_CDCONTA + LEN_EOS];
    char szNrDocumentoCliente[LEN_NRDOCUMENTO + LEN_EOS];

    char szIdNrCPF[LEN_IDNRCPF + LEN_EOS];
    char szDsTipoGestor[LEN_DSTIPOGESTOR + LEN_EOS];
} TGestorMiscInput;


class CGestorMiscpc
{

public:

    CGestorMiscpc(void);
    ~CGestorMiscpc(void);

    TGestorMisc *buscaGestorMisc(int iBusca);

    bool buscaIdPessoaConta(void);
    bool buscaGestorMiscCNPJCount(void);
    bool buscaGestorMiscContasCount(void);

    void clearStruct(void);

    void setIdNrCPF(char *pszIdNrCPF);
    void setIdConta(char *pszIdConta);
    void setCdConta(char *pszCdConta);
    void setNrDocumento(char *pszNrDocumento);

    void setNmNome(char *pszNmNome);
    void setNmNomeMeio(char *pszNmNomeMeio);
    void setNmSobreNome(char *pszNmSobreNome);
    void setCdContaCliente(char *pszCdContaCliente);
    void setNrDocumentoCliente(char *pszNrDocumentoCliente);
    void setDsTipoGestor(char *pszDsTipoGestor);


    char *getIdConta(void);
    char *getCdConta(void);

    char *getNrDocumento(void);
    char *getIdPessoaConta(void);

private:

    TGestorMiscInput tGestorMiscInput;
    TGestorMisc m_tGestorMisc;
    TGestorMisc *m_ptGestorMisc;

    int m_iQtdElementosAlocados;
    int m_iQtdElementosObtidos;

    void proCBuscaGestorMiscContasDisp(void);
    void proCBuscaGestorMiscCNPJDisp(void);
    void proCBuscaGestorContaDinamica(void);
    void proCBuscaUF(void);

    void proCBuscaGestorMiscComunicacao(void);
    void proCBuscaGestorMiscEndereco(void);
    void proCBuscaGestorMiscConta(void);

    void controleAlocacao(void);
};

#endif
