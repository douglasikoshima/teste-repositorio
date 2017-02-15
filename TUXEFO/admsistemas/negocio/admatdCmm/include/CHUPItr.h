#ifndef CHierarquiaDeptoPessoaItrH
#define CHierarquiaDeptoPessoaItrH

#include <stdlib.h>
#include <string.h>

struct STHierarquiaDeptoPessoaRegistro
{
	char cidHierarquiaDeptoPessoa[21+1];
	char cidNivelCargo[21+1];
	char cidPessoa[21+1];
	char cidOrganizacaoDepartamento[21+1];
};

class CHierarquiaDeptoPessoaItr
{
public:
	CHierarquiaDeptoPessoaItr();
	~CHierarquiaDeptoPessoaItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STHierarquiaDeptoPessoaRegistro* Registro( void );
	STHierarquiaDeptoPessoaRegistro* Registro(int nPosicao);

private:
	STHierarquiaDeptoPessoaRegistro* stcrHierarquiaDeptoPessoa;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidHierarquiaDeptoPessoa,
			char* cidNivelCargo,
			char* cidPessoa,
			char* cidOrganizacaoDepartamento );

	void ZeraHierarquiaDeptoPessoa( void );

};

#endif
