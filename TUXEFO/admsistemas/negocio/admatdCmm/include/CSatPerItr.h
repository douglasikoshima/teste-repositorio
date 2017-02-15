#ifndef CPesquisaSatisfacaoPerguntaItrH
#define CPesquisaSatisfacaoPerguntaItrH

#include <stdlib.h>
#include <string.h>


struct STPesquisaSatisfacaoPerguntaRegistro
{
	char cidPesquisaSatisfacaoPergunta[21+1];
	char cidPesquisaSatisfacao[21+1];
	char cidPergunta[21+1];
};

class CPesquisaSatisfacaoPerguntaItr
{
public:
	CPesquisaSatisfacaoPerguntaItr();
	~CPesquisaSatisfacaoPerguntaItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STPesquisaSatisfacaoPerguntaRegistro* Registro( void );
	STPesquisaSatisfacaoPerguntaRegistro* Registro(int nPosicao);

private:
	STPesquisaSatisfacaoPerguntaRegistro* stcrPesquisaSatisfacaoPergunta;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidPesquisaSatisfacaoPergunta,
			char* cidPesquisaSatisfacao,
			char* cidPergunta );

	void ZeraPesquisaSatisfacaoPergunta( void );

};

#endif
