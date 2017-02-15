#ifndef CNivelHierarquiaItrH
#define CNivelHierarquiaItrH

#include <stdlib.h>
#include <string.h>

struct STNivelHierarquiaRegistro
{
	char cidNivel[21+1];
	char cidNivelPai[21+1];
};

class CNivelHierarquiaItr
{
public:
	CNivelHierarquiaItr();
	~CNivelHierarquiaItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STNivelHierarquiaRegistro* Registro( void );
	STNivelHierarquiaRegistro* Registro(int nPosicao);

private:
	STNivelHierarquiaRegistro* stcrNivelHierarquia;
	int _iQuantidade;
	int _iPosicao;
	char cErroGeral[255+1];

protected:

	void Add( 
			char* cidNivel,
			char* cidNivelPai);
	
	void Add( char* cidNivel,
			  char* cidNivelPai,
			  char* cdsNivel,
			  char* cdsPath,
			  int   iLevel );

	void ZeraNivelHierarquia( void );
	void  SetErro( char* cErro );
};

#endif
