// CLojista.hpp: interface for the CLojista class.
//
//////////////////////////////////////////////////////////////////////

#ifndef __C_CCADINSID__
#define __C_CCADINSID__


#include <tuxfw/tuxfw.h>

#include <list>
using namespace std;

#define NO_DATA_FOUND							1403

#define ASSERT_STR(value, tag) \
		{	char cTag[64]; \
			sprintf(cTag, "TAG_%s_", tag); \
			if (value == NULL) \
				throw new TuxBasicSvcException(COD_ERR_EXCEPT, strcat(cTag, "INEXISTENTE")); \
			if (!*value) \
				throw new TuxBasicSvcException(COD_ERR_EXCEPT, strcat(cTag, "VALOR_VAZIO")); \
		}





class CCadInSid
{

public:

	CCadInSid();
	~CCadInSid();

	// Métodos de acesso aos atributos


 	// Setters

	char* rtrim(char *pStr);
	char* ltrim(char *pStr);
	char* trim (char *pStr);
	void  consultarRegional (list< CCadInSid > & listaCadInSid);
	void  consultarFuncionalidade (list< CCadInSid > & listaCadInSid);

	void  alterarInSid ( int iIdGrupoOperadora, int iIdApiNGIN, int iIdCanal, int iValorInSid);
	void  consultarPesquisa( list< CCadInSid > & listaCadInSid,  int iIdGrupoOperadora, int iIdApiNGIN, int iIdCanal);

	int	  getIdCanal ();
	void  setIdCanal(int value);

	int   getIdGrupoOperadora ();
	void  setIdGrupoOperadora (int value);

	char*  getNmFuncionalidade();
	void   setNmFuncionalidade(char *value);
	
	char*  getNmGrupoOperadora ();	
	void   setNmGrupoOperadora (char *value);

	void   setNmCanal(char *value);
	char*  getNmCanal();

	void  setIdApi (int value);
	int   getIdApi ();

	void setNrInSid (int value);
	int  getNrInSid();

	



private:

		int iIdCanal;
		int iIdAPI;
		char cNmFuncionalidade[255];
		char cNmGrupoOperadora[255];
		char cNmCanal[255];
		int	 iIdGrupoOperadora;
		int  iNrInSid;

};




#endif // __C_CCADINSID__


#ifndef __C_STATUSCODE__
#define __C_STATUSCODE__
class CStatusCode
{
public:
	CStatusCode();
	~CStatusCode();

	char* getCod();
	char* getMsg();

	void setCod(char* value);
	void setMsg(char* value);

private:
	char  m_cCod[32];
	char  m_cMsg[256];
};

#endif