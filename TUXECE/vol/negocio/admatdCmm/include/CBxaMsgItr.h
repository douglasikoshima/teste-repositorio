#ifndef CBaixaMensagemItrH
#define CBaixaMensagemItrH

#include <stdlib.h>
#include <string.h>


struct STBaixaMensagemRegistro
{
	char cidBaixaMensagem[21+1];
	char cidBaixa[21+1];
	char cidTipoComunicacao[21+1];
	char cdsTipoComunicacao[255+1];
	char cidMensagem[21+1];
	char cdsMensagem[255+1];
};

class CBaixaMensagemItr
{
public:
	CBaixaMensagemItr();
	~CBaixaMensagemItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STBaixaMensagemRegistro* Registro( void );
	STBaixaMensagemRegistro* Registro(int nPosicao);

private:
	STBaixaMensagemRegistro* stcrBaixaMensagem;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( char* cidBaixaMensagem
			 ,char* cidBaixa
			 ,char* cidTipoComunicacao
			 ,char* cdsTipoComunicacao
			 ,char* cidMensagem
			 ,char* cdsMensagem );

	void ZeraBaixaMensagem( void );

};

#endif
