#ifndef COrganizacaoDepartamentoItrH
#define COrganizacaoDepartamentoItrH

#include <stdlib.h>
#include <string.h>

struct STOrganizacaoDepartamentoRegistro
{
	char cidOrganizacaoDepartamento[21+1];
	char cidDepartamento[21+1];
	char cidOrganizacao[21+1];
};

class COrganizacaoDepartamentoItr
{
public:
	COrganizacaoDepartamentoItr();
	~COrganizacaoDepartamentoItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STOrganizacaoDepartamentoRegistro* Registro( void );
	STOrganizacaoDepartamentoRegistro* Registro(int nPosicao);

private:
	STOrganizacaoDepartamentoRegistro* stcrOrganizacaoDepartamento;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidOrganizacaoDepartamento,
			char* cidDepartamento,
			char* cidOrganizacao );

	void ZeraOrganizacaoDepartamento( void );

};

#endif
