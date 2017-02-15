#ifndef CPesquisaSatisfacaoItrH
#define CPesquisaSatisfacaoItrH

#include <stdlib.h>
#include <string.h>


struct STPesquisaSatisfacaoRegistro
{
	char cidPesquisaSatisfacao[21+1];
	char cnmPesquisaSatisfacao[255+1];
};

class CPesquisaSatisfacaoItr
{
public:
	CPesquisaSatisfacaoItr();
	~CPesquisaSatisfacaoItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STPesquisaSatisfacaoRegistro* Registro( void );
	STPesquisaSatisfacaoRegistro* Registro(int nPosicao);

private:
	STPesquisaSatisfacaoRegistro* stcrPesquisaSatisfacao;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidPesquisaSatisfacao,
			char* cnmPesquisaSatisfacao );

	void ZeraPesquisaSatisfacao( void );

};

#endif
