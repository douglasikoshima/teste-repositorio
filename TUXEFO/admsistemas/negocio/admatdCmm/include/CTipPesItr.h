#ifndef CTipoPessoaItrH
#define CTipoPessoaItrH

#include <stdlib.h>
#include <string.h>


struct STTipoPessoaRegistro
{
	char cidTipoPessoa[21+1];
	char csgTipoPessoa[255+1];
	char cdsTipoPessoa[255+1];
};

class CTipoPessoaItr
{
public:
	CTipoPessoaItr();
	~CTipoPessoaItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STTipoPessoaRegistro* Registro( void );
	STTipoPessoaRegistro* Registro(int nPosicao);

private:
	STTipoPessoaRegistro* stcrTipoPessoa;
	int _iQuantidade;
	int _iPosicao;

protected:
	void Add( char* cidTipoPessoa, 
		      char* csgTipoPessoa, 
			  char* cdsTipoPessoa );
	void ZeraTipoPessoa( void );

};

#endif