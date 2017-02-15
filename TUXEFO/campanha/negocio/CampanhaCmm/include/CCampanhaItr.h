#ifndef CCampanhaItrH
#define CCampanhaItrH

#include <stdlib.h>
#include <string.h>

struct STCampanhaRegistro
{
	char cidCampanha[21+1];
	char csgCampanha[256+1];
	char cdsCampanha[256+1];
	char cdtInclusao[12+1];
	char cdtAlteracao[12+1];
	char cidPessoaUsuarioInclusao[21+1];
	char cidPessoaUsuarioAlteracao[21+1];
	char cinAtivo[21+1];
};

class CCampanhaItr
{
public:
	CCampanhaItr();
	~CCampanhaItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STCampanhaRegistro* Registro( void );
	STCampanhaRegistro* Registro(int nPosicao);

private:
	STCampanhaRegistro* stcrCampanha;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( char* cidCampanha, 
		      char* csgCampanha, 
			  char* cdsCampanha, 
			  char* cdtInclusao, 
			  char* cdtAlteracao,
			  char* cidPessoaUsuarioInclusao,
			  char* cidPessoaUsuarioAlteracao,
			  char* cinAtivo );

	void ZeraCampanha( void );

};

#endif
