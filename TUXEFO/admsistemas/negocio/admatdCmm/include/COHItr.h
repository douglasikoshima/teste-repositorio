#ifndef COrganizacaoHierarquiaItrH
#define COrganizacaoHierarquiaItrH

#include <stdlib.h>
#include <string.h>

struct STOrganizacaoHierarquiaRegistro
{
	char cidOrganizacao[21+1];
	char cidOrganizacaoPai[21+1];
};

class COrganizacaoHierarquiaItr
{
public:
	COrganizacaoHierarquiaItr();
	~COrganizacaoHierarquiaItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STOrganizacaoHierarquiaRegistro* Registro( void );
	STOrganizacaoHierarquiaRegistro* Registro(int nPosicao);

private:
	STOrganizacaoHierarquiaRegistro* stcrOrganizacaoHierarquia;
	int _iQuantidade;
	int _iPosicao;
	char cErroGeral[255+1];

protected:

	void Add( 
			char* cidOrganizacao,
			char* cidOrganizacaoPai );

	void ZeraOrganizacaoHierarquia( void );
	void SetErro( char* cErro );

};

#endif
