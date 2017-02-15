/**
 * 
 * @modulo  Clientes
 * @usecase Clientes
 * @author  ??
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:26 $
 **/

#ifndef CLASSBUSCADADOSCONTA
#define CLASSBUSCADADOSCONTA

#include "Global.h"

EXEC SQL BEGIN DECLARE SECTION;

typedef struct
{
    VARCHAR sIdPessoa[LEN_NUMBER+LEN_EOS];
    VARCHAR sNmNome[LEN_NOME+LEN_EOS];
    VARCHAR sDsTipoDocumento[LEN_DSTIPODOCUMENTO+LEN_EOS];
    VARCHAR sNrDocumento[LEN_NRDOCUMENTO+LEN_EOS];
    VARCHAR sDsTipoPessoa[LEN_DSTIPOPESSOA+LEN_EOS];
    VARCHAR sSgTipoPessoa[LEN_DSTIPOPESSOA+LEN_EOS];
    VARCHAR sNrLinha[LEN_NRLINHA+LEN_EOS];
    VARCHAR sSgTipoDocumento[LEN_NRLINHA+LEN_EOS];
    VARCHAR sSgTipoRelacionamento[LEN_NRLINHA+LEN_EOS];
    int     nUltimaPagina;

    //Nulls
    short iIdPessoa_ora;
    short iNmNome_ora;
    short iDsTipoDocumento_ora;
    short iNrDocumento_ora;
    short iDsTipoPessoa_ora;
    short iSgTipoPessoa_ora;
    short iNrLinha_ora;
    short iSgTipoDocumento_ora;
    short iSgTipoRelacionamento_ora;
    short iUltimaPagina;
}TDOCPESQUISADOSOLD;

typedef struct
{
    VARCHAR sDsTipoDocumento[LEN_DSTIPODOCUMENTO+LEN_EOS];
    VARCHAR sDsTipoPessoa[LEN_DSTIPOPESSOA+LEN_EOS];
    VARCHAR sIdDocumento[LEN_NUMBER+LEN_EOS];
    VARCHAR sIdPessoa[LEN_NUMBER+LEN_EOS];
    VARCHAR sIdTipoPessoa[LEN_NUMBER+LEN_EOS];
    VARCHAR sNmNome[LEN_NOME+LEN_EOS];
    VARCHAR sNrDocumento[LEN_NRDOCUMENTO+LEN_EOS];
    VARCHAR sNrLinha[LEN_NRLINHA+LEN_EOS];
    VARCHAR sSgTipoDocumento[LEN_NRLINHA+LEN_EOS];
    VARCHAR sSgTipoPessoa[LEN_DSTIPOPESSOA+LEN_EOS];
    VARCHAR sSgTipoRelacionamento[LEN_NRLINHA+LEN_EOS];
    int nUltimaPagina;
}TDOCPESQUISADOS;

typedef struct
{
    short sDsTipoDocumento;
    short sDsTipoPessoa;
    short sIdDocumento;
    short sIdPessoa;
    short sIdTipoPessoa;
    short sNmNome;
    short sNrDocumento;
    short sNrLinha;
    short sSgTipoDocumento;
    short sSgTipoPessoa;
    short sSgTipoRelacionamento;
    short nUltimaPagina;
}TDOCPESQUISADOSST;


EXEC SQL END DECLARE SECTION;

class CBuscaDocumentoPessoa{

public:
       /* Construtor/Destrutor */
    CBuscaDocumentoPessoa();
    virtual ~CBuscaDocumentoPessoa();
    static CBuscaDocumentoPessoa *buscarDocPorNrConta(int *, char*, char*);
    static CBuscaDocumentoPessoa *buscarDocPorTipDocumento(int* , char*, char*, char*);
    static CBuscaDocumentoPessoa *buscarDocPorNrLinha(int*, char *, char*);
    static CBuscaDocumentoPessoa *buscarDocPorNome(int*, char*, char*, char*, char*);
    static CBuscaDocumentoPessoa *buscarDocPorNomePJ(int*, char*, char*);
    static CBuscaDocumentoPessoa *buscarNrLinhaPorProtocolo(int*, char *, char*);
    static CBuscaDocumentoPessoa *buscarProspect(int*, char*, char*);
    static int getTotalLinhasPorPagina( void );

    void clear();

    //Metodos de acessos aos atributos
    //Setters
    void setIdPessoa(char*);
    void setNmNome(char*);
    void setDsTipoDocumento(char*);
    void setNrDocumento(char*);
    void setDsTipoPessoa(char*);
    void setSgTipoPessoa(char*);
    void setNrLinha(char*);
	void setSgTipoDocumento(char *);
	void setSgTipoRelacionamento(char *);
	void setUltimaPagina(int);
	void setIsProtocoloAtivo(int);


    //Getters
    char* getIdPessoa(void);
    char* getNmNome(void);
    char* getDsTipoDocumento(void);
    char* getNrDocumento(void);
    char* getDsTipoPessoa(void);
    char* getSgTipoPessoa(void);
    char* getNrLinha(void);
	char* getSgTipoDocumento(void);
	char* getSgTipoRelacionamento(void);
	int   getUltimaPagina(void);
	int   getIsProtocoloAtivo(void); //protocolo

private:
    EXEC SQL BEGIN DECLARE SECTION;
        TDOCPESQUISADOS   tDocPesq;
        TDOCPESQUISADOSST tDocPesqST;
    EXEC SQL END DECLARE SECTION;

    int isProtocolo;
};

#endif
