#ifndef CCSmsItrH
#define CCSmsItrH

#include <stdlib.h>
#include <string.h>

struct STClassificacaoSMSRegistro
{
	char cidClassificacaoSMS[21+1];
	char cdsClassificacaoSMS[255+1];
};

class CCSmsItr
{
public:
	CCSmsItr();
	~CCSmsItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STClassificacaoSMSRegistro* Registro( void );
	STClassificacaoSMSRegistro* Registro(int nPosicao);

private:
	STClassificacaoSMSRegistro* stcrAtribuicao;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidClassificacaoSMS,
			char* cdsClassificacaoSMS );

	void ZeraAtribuicao( void );

};

#endif
