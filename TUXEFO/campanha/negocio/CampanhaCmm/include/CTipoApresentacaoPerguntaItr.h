#ifndef CTipoApresentacaoPerguntaItrH
#define CTipoApresentacaoPerguntaItrH

#include <stdlib.h>
#include <string.h>

struct STTipoApresentacaoPerguntaRegistro
{
	char cidTipoApresentacaoPergunta[21+1];
	char csgTipoApresentacaoPergunta[256+1];
	char cdsTipoApresentacaoPergunta[256+1];
};

class CTipoApresentacaoPerguntaItr
{
public:
	CTipoApresentacaoPerguntaItr();
	~CTipoApresentacaoPerguntaItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STTipoApresentacaoPerguntaRegistro* Registro( void );
	STTipoApresentacaoPerguntaRegistro* Registro(int nPosicao);

private:
	STTipoApresentacaoPerguntaRegistro* stcrTipoApresentacaoPergunta;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( char* cidTipoApresentacaoPergunta, 
		      char* csgTipoApresentacaoPergunta, 
			  char* cdsTipoApresentacaoPergunta );

	void ZeraTipoApresentacaoPergunta( void );

};

#endif