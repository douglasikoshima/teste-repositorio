#ifndef CPessoaItrH
#define CPessoaItrH

#include <stdlib.h>
#include <string.h>

struct STPessoaRegistro
{
	char cidPessoaUsuario[21+1];
	char cnmLoginUsuario[255+1];
	char cidStatusUsuario[21+1];
	char cinDisponivelWF[21+1];
};

class CPessoaItr
{
public:
	CPessoaItr();
	~CPessoaItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STPessoaRegistro* Registro( void );
	STPessoaRegistro* Registro( int );

private:
	STPessoaRegistro* stcrPessoa;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidPessoaUsuario,
			char* cnmLoginUsuario,
			char* cidStatusUsuario,
			char* cinDisponivelWF );

	void ZeraPessoa( void );

};

#endif
