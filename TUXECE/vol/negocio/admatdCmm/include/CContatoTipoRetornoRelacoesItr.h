#ifndef CContatoTipoRetornoRelacoesItrH
#define CContatoTipoRetornoRelacoesItrH

#include <stdlib.h>
#include <string.h>


struct STContatoTipoRetornoRelacoesRegistro
{
	char cidContatoTipoRetornoRelacoes[21+1];
	char cidContatoTipoRetorno[21+1];
	char cidContatoRelacao[21+1];
	char cdsContatoRelacao[255+1];
	char cidTipoRetornoContato[21+1];
};

class CContatoTipoRetornoRelacoesItr
{
public:
	CContatoTipoRetornoRelacoesItr();
	~CContatoTipoRetornoRelacoesItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STContatoTipoRetornoRelacoesRegistro* Registro( void );
	STContatoTipoRetornoRelacoesRegistro* Registro(int nPosicao);

private:
	STContatoTipoRetornoRelacoesRegistro* stcrContatoTipoRetornoRelacoes;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidContatoTipoRetornoRelacoes,
			char* cidContatoTipoRetorno,
			char* cidContatoRelacao,
			char* cidTipoRetornoContato );

	void Add( char* cidContatoRelacao,
			  char* cdsContatoRelacao );

	void Add( char* cidContatoTipoRetornoRelacoes,
			  char* cidContatoRelacao,
			  char* cdsContatoRelacao );

	void ZeraContatoTipoRetornoRelacoes( void );

};

#endif
