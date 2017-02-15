#ifndef CContatoPesquisaSatisfacaoItrH
#define CContatoPesquisaSatisfacaoItrH

#include <stdlib.h>
#include <string.h>


struct STContatoPesquisaSatisfacaoRegistro
{
	char cidContatoPesquisaSatisfacao[21+1];
	char cidContato[21+1];
	char cidPesquisaSatisfacao[21+1];
	char cidTipoPessoa[21+1];
};

class CContatoPesquisaSatisfacaoItr
{
public:
	CContatoPesquisaSatisfacaoItr();
	~CContatoPesquisaSatisfacaoItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STContatoPesquisaSatisfacaoRegistro* Registro( void );
	STContatoPesquisaSatisfacaoRegistro* Registro(int nPosicao);

private:
	STContatoPesquisaSatisfacaoRegistro* stcrContatoPesquisaSatisfacao;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidContatoPesquisaSatisfacao,
			char* cidContato,
			char* cidPesquisaSatisfacao );
	void Add( 
			char* cidContatoPesquisaSatisfacao,
			char* cidContato,
			char* cidPesquisaSatisfacao,
			char* cidTipoPessoa );

	void ZeraContatoPesquisaSatisfacao( void );

};

#endif
