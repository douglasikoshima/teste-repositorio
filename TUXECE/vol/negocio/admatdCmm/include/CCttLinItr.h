#ifndef CContatoTipoLinhaItrH
#define CContatoTipoLinhaItrH

#include <stdlib.h>
#include <string.h>


struct STContatoTipoLinhaRegistro
{
	char cidContatoTipoLinha[21+1];
	char cidContato[21+1];
	char cidTipoLinha[21+1];
};

class CContatoTipoLinhaItr
{
public:
	CContatoTipoLinhaItr();
	~CContatoTipoLinhaItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STContatoTipoLinhaRegistro* Registro( void );
	STContatoTipoLinhaRegistro* Registro(int nPosicao);

private:
	STContatoTipoLinhaRegistro* stcrContatoTipoLinha;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidContatoTipoLinha,
			char* cidContato,
			char* cidTipoLinha );

	void ZeraContatoTipoLinha( void );

};

#endif
