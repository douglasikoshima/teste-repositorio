#ifndef CPesquisaSatisfacaoTPPessoaItrH
#define CPesquisaSatisfacaoTPPessoaItrH

#include <stdlib.h>
#include <string.h>


struct STPesquisaSatisfacaoTPPessoaRegistro
{
	char cidPesquisaSatisfacao[21+1];
	char cidTipoPessoa[21+1];
};

class CPesquisaSatisfacaoTPPessoaItr
{
public:
	CPesquisaSatisfacaoTPPessoaItr();
	~CPesquisaSatisfacaoTPPessoaItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STPesquisaSatisfacaoTPPessoaRegistro* Registro( void );
	STPesquisaSatisfacaoTPPessoaRegistro* Registro(int nPosicao);

private:
	STPesquisaSatisfacaoTPPessoaRegistro* stcrPesquisaSatisfacaoTPPessoa;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidPesquisaSatisfacao,
			char* cidTipoPessoa );

	void ZeraPesquisaSatisfacaoTPPessoa( void );

};

#endif
