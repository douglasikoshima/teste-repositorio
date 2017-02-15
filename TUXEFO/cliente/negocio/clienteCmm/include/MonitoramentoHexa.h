// MonitoramentoHexa.h: interface for the 
// CMonitoramentoHexa class.
//////////////////////////////////////////////////////////////////////

#ifndef MonitoramentoHexa
#define MonitoramentoHexa

#define MSG_ERR_MEMORIA		"Erro de aloca��o de mem�ria"
#define NRO_ERR_MEMORIA		"24E0359"
 
class CMonitoramentoHexa  
{
public:
	// Construtor/Destrutor
	CMonitoramentoHexa();
	virtual ~CMonitoramentoHexa();

	// M�todos de acesso a banco de dados
	static CMonitoramentoHexa* busca(char*, char*, char*, char*, char*, int*);
    bool existeNrLinha(char *pszNrLinha);
    bool existeNmLogin(char *pszNmLogin);
    bool existeCdHexa(char *pszCdHexa);
    bool existeCdDecimal(char *pszCdDecimal);
    char *convertDecimal2Hexa(char *pszCdDecimal);

	void registraConsulta();

	// M�todos de acesso aos atributos
	// Getters
	char* getDtConsulta();
	int getIdLinha();
	char* getNrLinha();
	int getIdUsuario();
	char* getNmLogin();
	char* getCdHexa();
	char* getCdDecimal();
	char* getDsTipoLinha();
	char* getNrDocumento();
	char* getDsTipoDocumento();
	// Setters
	void setDtConsulta(char*);
	void setIdLinha(int);
	void setNrLinha(char*);
	void setIdUsuario(int);
	void setNmLogin(char*);
	void setCdHexa(char*);
	void setCdDecimal(char*);
	void setDsTipoLinha(char*);
	void setNrDocumento(char*);
	void setDsTipoDocumento(char*);
	// Usu�rio
	void setUsuarioAlteracao(char*);

private:

	EXEC SQL BEGIN DECLARE SECTION;
	char cDtConsulta[257];
	int iIdLinha;
	char cNrLinha[257];
	int iIdUsuario;
	char cNmLogin[257];
	char cCdHexa[257];
	char cCdDecimal[257];
	char cDsTipoLinha[257];
	char cNrDocumento[257];
    char cDsTipoDocumento[257];

	// Vari�veis indicativas do oracle
	short icDtConsulta;
	short icNmLogin;
	short icCdHexa;
	short icCdDecimal;
	short icNrDocumento;
    short icDsTipoDocumento;

	// Usuario altera��o
	char sIdUsuarioAlteracao[257];
	EXEC SQL END DECLARE SECTION;
};

#endif

