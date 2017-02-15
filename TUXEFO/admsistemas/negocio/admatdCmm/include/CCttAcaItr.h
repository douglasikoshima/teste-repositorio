#ifndef CContatoAcaoItrH
#define CContatoAcaoItrH

#include <stdlib.h>
#include <string.h>


struct STContatoAcaoRegistro
{
	char cidContatoAcao[21+1];
	char cidUFOperadora[21+1];
	char cidTipoLinha[21+1];
	char cnmURLContatoAcao[255+1];
	char cidContato[21+1];
};

class CContatoAcaoItr
{
public:
	CContatoAcaoItr();
	~CContatoAcaoItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STContatoAcaoRegistro* Registro( void );
	STContatoAcaoRegistro* Registro(int nPosicao);

private:
	STContatoAcaoRegistro* stcrContatoAcao;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidContatoAcao,
			char* cidUFOperadora,
			char* cidTipoLinha,
			char* cnmURLContatoAcao,
			char* cidContato );

	void ZeraContatoAcao( void );

};

#endif
