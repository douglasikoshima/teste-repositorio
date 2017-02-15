// Prospect.h: interface for the 
// CProspect class.
//////////////////////////////////////////////////////////////////////
 
#ifndef Prospect
#define Prospect

#define MSG_ERR_MEMORIA     "Erro de alocação de memória"
#define NRO_ERR_MEMORIA     "24E0359"

class CProspect
{
public:
    // Construtor/Destrutor
    CProspect();
    CProspect(long);
    virtual ~CProspect();
 
    // Métodos de acesso a banco de dados
    void inclui();          // Inclui dados prospect no banco
    void adicionaContato(long, char*); // Adiciona um contato na lista.
    char* buscaFoneContato();   // busca telefone de contato, caso disponível
    // Métodos de acesso aos atributos
    // Getters
    char* getNmProspect();
    char* getPriNmProspect();
    char* getMeioNmProspect();
    char* getSobNmProspect();
    long getIdTpPessoa();
    long getIdTpDocto();
    char* getDocto();
    long getIdPais();
    char* getOrgaoExp();
    char* getDtExp();
    long getIdUFDoc();
    long getIdProspect();
    long getIdDocto();
    char* getSgTpPessoa();
    long getIdProspectDePara(void);
    // Setters
    void setNmProspect(const char *value);
    void setPriNmProspect(const char *value);
    void setMeioNmProspect(const char *value);
    void setSobNmProspect(const char *value);
    void setIdTpPessoa(long value);
    void setIdTpDocto(long value);
    void setDocto(const char *value);
    void setIdPais(long value);
    void setOrgaoExp(const char *value);
    void setDtExp(const char *value);
    void setIdUFDoc(long value);
    void setIdProspectDePara(long value);
    void setCdAreaRegistro(const char *value);
    void setNrLinha(const char *value);
    void setCdCaixaPostal(const char *value);
    void setDsAosCuidados(const char *value);
    void setDsEnderecoComplemento(const char *value);
    void setIdPais(const char *value);
    void setIdTipoEndereco(const char *value);
    void setIdUF(const char *value);
    void setNmBairro(const char *value);
    void setNmLocalidade(const char *value);
    void setNmLogradouro(const char *value);
    void setNmMunicipio(const char *value);
    void setNmTipoLogradouro(const char *value);
    void setNmTituloLogradouro(const char *value);
    void setNrCEP(const char *value);
    void setNrEndereco(const char *value);

    // Usuário
    void setUsuarioAlteracao(const char*);

 private:
    
    // Estrutura pra definir um contato
    struct SContato {
        char cDsContato[256];
        long iIdTpContato;
    };

    EXEC SQL BEGIN DECLARE SECTION;
    
    // Variáveis de indicação do oracle
    char cdAreaRegistro[3]; 
    char cdCaixaPostal[39]; 
    char cDocto[256];
    char cDsCont[256];
    char cDtExp[11];
    char cMeioNmProspect[256];
    char cNmProspect[256];
    char cOrgaoExp[256];
    char cPriNmProspect[256];
    char cSobNmProspect[256];
    char dsAosCuidados[256];    
    char dsEnderecoComplemento[256];    
    char idPais[39];    
    char idTipoEndereco[39];    
    char idUF[39];  
    char nmBairro[256]; 
    char nmLocalidade[256]; 
    char nmLogradouro[256]; 
    char nmMunicipio[256];  
    char nmTipoLogradouro[31];  
    char nmTituloLogradouro[81];    
    char nrCEP[12]; 
    char nrEndereco[11];    
    char nrLinha[12];   
    char sIdUsuarioAlteracao[256];
    char sSgTpPessoa[256];

    long iIdCont;
    long iIdDocto;
    long iIdPais;
    long iIdProspect;
    long iIdProspectDePara;
    long iIdTpDocto;
    long iIdTpPessoa;
    long iIdUFDoc;

    short icDtExp;
    short icIdDocumento;    
    short icIdPessoaDocumento;  
    short icOrgaoExp;

    VARCHAR cSgClassificacao[256];  

    EXEC SQL END DECLARE SECTION;

    long iNrContatos;
    SContato* poListaCont;
};
 
#endif
