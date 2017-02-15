#ifndef CCTipoArvoreItrH
#define CCTipoArvoreItrH

#include <stdlib.h>
#include <string.h>

struct STTipoArvoreRegistro
{
	char cidTipoArvore[2+1];
	char cdsTipoArvore[255+1];
};

class CCTipoArvoreItr
{
public:
	CCTipoArvoreItr();
	~CCTipoArvoreItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	int BytesAlocados( void );
	STTipoArvoreRegistro* Registro( void );
	STTipoArvoreRegistro* Registro(int nPosicao);
	char* GetErro() { return cErroGeral; }

private:
	STTipoArvoreRegistro* stcrTpArvore;
	int _iQuantidade;
	int _iPosicao;
	char cErroGeral[255+1];

protected:

	void Add(char* cidTipoArvore,char* cdsTipoArvore);

	void Zera( void );
	void SetErro( char* cErro );

};

#endif
