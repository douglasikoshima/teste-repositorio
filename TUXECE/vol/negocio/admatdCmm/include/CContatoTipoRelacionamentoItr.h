#ifndef CContatoTipoRelacionamentoItrH
#define CContatoTipoRelacionamentoItrH

#include <stdlib.h>
#include <string.h>


struct STContatoTipoRelacionamentoRegistro
{
	char cidContatoTipoRelacionamento[21+1];
	char cidContato[21+1];
	char cidTipoRelacionamento[21+1];
};

class CContatoTipoRelacionamentoItr
{
public:
	CContatoTipoRelacionamentoItr();
	~CContatoTipoRelacionamentoItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STContatoTipoRelacionamentoRegistro* Registro( void );
	STContatoTipoRelacionamentoRegistro* Registro(int nPosicao);

private:
	STContatoTipoRelacionamentoRegistro* stcrContatoTipoRelacionamento;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidContatoTipoRelacionamento,
			char* cidContato,
			char* cidTipoRelacionamento );

	void ZeraContatoTipoRelacionamento( void );

};

#endif
