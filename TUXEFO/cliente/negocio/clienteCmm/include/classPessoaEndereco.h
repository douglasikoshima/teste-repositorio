//-----------------------------------------------------------------------------------------
//* 
//* Classe: CPessoaEndereco
//* 
//* Purpose: Classe para a tabela PessoaEndereco
//*
//* Relacao com outra(s) classe(s):
//*
//* Instrucoes de uso: Os atributos desta classe sao do tipo Pro*C.
//-----------------------------------------------------------------------------------------
#ifndef CLASSPESSOAENDERECO
#define CLASSPESSOAENDERECO

#include "Global.h"

EXEC SQL BEGIN DECLARE SECTION;

typedef struct{
VARCHAR sCodLogradouro[255+LEN_EOS];
VARCHAR sInCnl[225+LEN_EOS];
VARCHAR sInCodigoIBGE[255+LEN_EOS];
VARCHAR sIdPessoa[LEN_NUMBER+LEN_EOS];
VARCHAR sIdEndereco[LEN_NUMBER+LEN_EOS];
VARCHAR sIdTipoEndereco[LEN_NUMBER+LEN_EOS];
VARCHAR sSgTipoEndereco[LEN_SGTIPOENDERECO+LEN_EOS];
VARCHAR sDsTipoEndereco[LEN_DSTIPOENDERECO+LEN_EOS];
VARCHAR sNmTipoLogradouro[LEN_NMTIPOLOGRADOURO+LEN_EOS];
VARCHAR sNmTituloLogradouro[LEN_NMTITULOLOGRADOURO+LEN_EOS];
VARCHAR sNmLogradouro[LEN_NMLOGRADOURO+LEN_EOS];
VARCHAR sNmLogradouro1[LEN_NMLOGRADOURO+LEN_EOS];
VARCHAR sNmBairro[LEN_BAIRRO+LEN_EOS];
VARCHAR sNmMunicipio[LEN_MUNICIPIO+LEN_EOS];
VARCHAR sNrEndereco[LEN_NRENDERECO+LEN_EOS];
VARCHAR sDsEnderecoComplemento[LEN_DSENDERECOCOMPLEMENTO+LEN_EOS];
VARCHAR sNrCep[LEN_CEP+LEN_EOS];
VARCHAR sIdUF[LEN_NUMBER+LEN_EOS];
VARCHAR sSgUF[LEN_SGUF+LEN_EOS];
VARCHAR sNmUF[LEN_NMUF+LEN_EOS];
VARCHAR sIdPais[LEN_NUMBER+LEN_EOS];
VARCHAR sSgPais[LEN_SGPAIS+LEN_EOS];
VARCHAR sNmPais[LEN_DSPAIS+LEN_EOS];
VARCHAR sDsNacionalidade[LEN_DSNACIONALIDADE+LEN_EOS];
VARCHAR sDtCadastroOut[LEN_DATE_FORMATADA+LEN_EOS];
VARCHAR sNmPessoaContato[LEN_NMPESSOACONTATO+LEN_EOS];
VARCHAR sInEnderecoPreferencial[LEN_NUMBER+LEN_EOS];
VARCHAR sDtExpiracaoOut[LEN_DATE_FORMATADA+LEN_EOS];
VARCHAR sIdSistemaOrigem[LEN_NUMBER+LEN_EOS];
VARCHAR sIdSistemaOrigemBase[LEN_NUMBER+LEN_EOS];
VARCHAR sDtUltimaAlteracao[LEN_DATE_FORMATADA+LEN_EOS];
VARCHAR sNrLinhaAssociada[LEN_DSENDERECOCOMPLEMENTO+LEN_EOS];
long    lTsSincronismo;
long    lSqSincronismo;

//Nulls
short i_sCodLogradouro;
short i_sInCnl;
short i_sInCodigoIBGE;
short iIdPessoa_ora;
short iIdEndereco_ora;
short iIdTipoEndereco_ora;
short iSgTipoEndereco_ora;
short iDsTipoEndereco_ora;
short iNmTipoLogradouro_ora;
short iNmTituloLogradouro_ora;
short iNmLogradouro_ora;
short iNmLogradouro1_ora;
short iNmBairro_ora;
short iNmMunicipio_ora;
short iNrEndereco_ora;
short iDsEnderecoComplemento_ora;
short iNrLinhaAssociada_ora;
short iNrCep_ora;
short iIdUF_ora;
short iSgUF_ora;
short iNmUF_ora;
short iIdPais_ora;
short iSgPais_ora;
short iNmPais_ora;
short iDsNacionalidade_ora;
short iDtCadastroOut_ora;
short iNmPessoaContato_ora;
short iInEnderecoPreferencial_ora;
short iDtExpiracaoOut_ora;
short iTsSincronismo_ora;
short iSqSincronismo_ora;
short iIdSistemaOrigem_ora;
short iIdSistemaOrigemBase_ora;
short iDtUltimaAlteracao_ora;
}TPESSOAEND;

EXEC SQL END DECLARE SECTION;

class CPessoaEndereco{

public:

    CPessoaEndereco();
    virtual ~CPessoaEndereco();

    int Incluir();
    int Excluir();
    int Alterar();

    void RecuperarTodos(char *pszIdPessoa, XMLGen *pXmlG);

    int buscarPorIdLinhaTelefonica(char*, char *);
    int buscarPorNrLinha(char *pNrLinha);

    //Metodos de acessos aos atributos
    //Setters
    void setIdPessoa(char *);
    void setIdEndereco(char *);
    void setIdTipoEndereco(char *);
    void setSgTipoEndereco(char *);
    void setDsTipoEndereco(char *);
    void setNmTipoLogradouro(char *);
    void setNmTituloLogradouro(char *);
    void setNmLogradouro(char *);
    void setCodLogradouro(char* pDado);
    void setInCnl(char* pDado);
    void setInCodigoIBGE(char* pDado);
    void setNmLogradouro1(char *);
    void setNmBairro(char *);
    void setNmMunicipio(char *);
    void setNrEndereco(char *);
    void setNrLinhaAssociada(char *);
    void setDsEnderecoComplemento(char *);
    void setNrCep(char *);
    void setIdUF(char *);
    void setSgUF(char *);
    void setNmUF(char *);
    void setIdPais(char *);
    void setSgPais(char *);
    void setNmPais(char *);
    void setDsNacionalidade(char *);
    void setDtCadastroOut(char *);
    void setNmPessoaContato(char *);
    void setInEnderecoPreferencial(char *);
    void setDtExpiracaoOut(char *);
    void setTsSincronismo(long);
    void setSqSincronismo(long);
    void setIdSistemaOrigem(char*);
    void setIdSistemaOrigemBase(char*);
    void setDtUltimaAlteracao(char*);
    void SetData(TEnderecoXML xmlJ);

    //Getters
    char* getCodLogradouro(void);
    char* getInCnl(void);
    char* getInCodigoIBGE(void);
    char* getIdPessoa(void);
    char* getIdEndereco(void);
    char* getIdTipoEndereco(void);
    char* getSgTipoEndereco(void);
    char* getDsTipoEndereco(void);
    char* getNmTipoLogradouro(void);
    char* getNmTituloLogradouro(void);
    char* getNmLogradouro(void);
    char* getNmLogradouro1(void);
    char* getNmBairro(void);
    char* getNmMunicipio(void);
    char* getNrEndereco(void);
    char* getDsEnderecoComplemento(void);
    char* getNrLinhaAssociada(void);
    char* getNrCep(void);
    char* getIdUF(void);
    char* getSgUF(void);
    char* getNmUF(void);
    char* getIdPais(void);
    char* getSgPais(void);
    char* getNmPais(void);
    char* getDsNacionalidade(void);
    char* getDtCadastroOut(void);
    char* getNmPessoaContato(void);
    char* getInEnderecoPreferencial(void);
    char* getDtExpiracaoOut(void);
    long  getTsSincronismo(void);
    long  getSqSincronismo(void);
    char* getIdSistemaOrigem(void);
    char* getIdSistemaOrigemBase(void);
    char* getDtUltimaAlteracao(void);

	// Usuário
	void setUsuarioAlteracao(char*);

    bool existePessoaEnderecoIdSistemaOrigem7(void);
    bool existeCEnderecoePessoaIdSO7(void);
    bool existePessoaEnderecoIdSistemaOrigem1(void);
    bool existePessoaEnderecoIdSistemaOrigem4ou7(void);
    bool existePessoaEnderecoContaEndereco(void);

    bool buscaDadosNGINPessoaJuridica(TDadosNGIN *ptDadosNGIN);
    bool buscaDadosNGINPessoaFisica(TDadosNGIN *ptDadosNGIN);
    bool buscaTipoPessoa(int *piTipoPessoa);
	bool buscaIdLinhaTelefonicaPorNrLinha(char *szNrLinha, char *pszIdLinhaTelefonica);
    int atualizaContaEndereco(char *pszIdContaEndereco, char *pszIdPessoaEndereco, char *pszIdUsuarioAlteracao);
    bool buscaIdContaEndereco(char *pszIdPessoa, char *pszNrLinha, char *pszIdContaEndereco);
    void associarContaEndereco(const char *pszIdPessoaEndereco,const char *pszIdPessoa,const char *pszIdLinhaTelefonica,const char *pszIdUsuarioAlteracao);

    TDadosDocumento *BuscaDocumentoPorIdPessoa(char *pszIdPessoa);

private:
EXEC SQL BEGIN DECLARE SECTION;
	TPESSOAEND tPessEnd;
	char sIdUsuarioAlteracao[256];
EXEC SQL END DECLARE SECTION;

protected:
    TDadosDocumento m_tDadosDocumento;
    TDadosDocumento *m_ptDadosDocumento;

    // controle de alocacao de memoria
    int m_iQtdElementosAlocados;
    int m_iQtdElementosObtidos;
    void controleAlocacao(void);

    bool proCBuscaDocumentoPorIdPessoa(char *pszIdPessoa);
};
#endif /* Fim */
