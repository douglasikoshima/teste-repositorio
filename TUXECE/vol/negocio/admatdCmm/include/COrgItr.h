#ifndef COrganizacaoItrH
#define COrganizacaoItrH

#include <stdlib.h>
#include <string.h>


struct STOrganizacaoRegistro
{
	char cidOrganizacao[21+1];
	char cidOrganizacaoPai[21+1];
	char cidTipoOrganizacao[21+1];
	char cdsTipoOrganizacao[255+1];
	char cdsPath[1024+1];
	int  iLevel;
};

class COrganizacaoItr
{
public:
	COrganizacaoItr();
	~COrganizacaoItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STOrganizacaoRegistro* Registro( void );
	STOrganizacaoRegistro* Registro(int nPosicao);
	char* GetErro() { return cErroGeral; }

private:
	STOrganizacaoRegistro* stcrOrganizacao;
	int _iQuantidade;
	int _iPosicao;
	char cErroGeral[255+1];

protected:

	void Add( char* cidOrganizacao,
			  char* cidOrganizacaoPai,
			  char* cidTipoOrganizacao,
			  char* cdsTipoOrganizacao,
			  char* cdsPath,
			  int   iLevel );

	void ZeraOrganizacao( void );
	void  SetErro( char* cErro );

};

#endif
