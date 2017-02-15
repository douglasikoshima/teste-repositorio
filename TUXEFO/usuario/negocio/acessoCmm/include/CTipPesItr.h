#ifndef CTipPesItrH
#define CTipPesItrH

#include <stdlib.h>
#include <string.h>
#include<tuxfw.h>

struct STTipoPessoaRegistro
{
	char cidTipoPessoa[21+1];
	char csgTipoPessoa[255+1];
	char cdsTipoPessoa[255+1];
};

class CTipPesItr
{
public:
	CTipPesItr();
	~CTipPesItr();
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
	void Add( char* cid, char* csigla, char* cdescricao );
	void ZeraTipoPessoa( void );

};

#endif