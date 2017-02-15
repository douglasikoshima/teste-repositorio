#ifndef CTipoComunicacaoItrH
#define CTipoComunicacaoItrH

#include <stdlib.h>
#include <string.h>


struct STTipoComunicacaoRegistro
{
	char cidTipoComunicacao[21+1];
	char csgTipoComunicacao[255+1];
	char cdsTipoComunicacao[255+1];
	char cidFormaRetorno[21+1];
};

class CTipoComunicacaoItr
{
public:
	CTipoComunicacaoItr();
	~CTipoComunicacaoItr();
	int Primeiro( void );
	int Ultimo( void );
	int Quantidade( void );
	STTipoComunicacaoRegistro* Registro( void );
	STTipoComunicacaoRegistro* Registro(int nPosicao);

private:
	STTipoComunicacaoRegistro* stcrTipoComunicacao;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidTipoComunicacao,
			char* csgTipoComunicacao,
			char* cdsTipoComunicacao,
			char* cidFormaRetorno );

	void ZeraTipoComunicacao( void );

};

#endif
