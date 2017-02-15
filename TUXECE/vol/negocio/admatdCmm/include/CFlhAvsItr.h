#ifndef CContatoFolhaMensagemAvisoItrH
#define CContatoFolhaMensagemAvisoItrH

#include <stdlib.h>
#include <string.h>


struct STContatoFolhaMensagemAvisoRegistro
{
	char cidContato[21+1];
	char cidMensagemAviso[21+1];
};

class CContatoFolhaMensagemAvisoItr
{
public:
	CContatoFolhaMensagemAvisoItr();
	~CContatoFolhaMensagemAvisoItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STContatoFolhaMensagemAvisoRegistro* Registro( void );
	STContatoFolhaMensagemAvisoRegistro* Registro(int nPosicao);

private:
	STContatoFolhaMensagemAvisoRegistro* stcrContatoFolhaMensagemAviso;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidContato,
			char* cidMensagemAviso );

	void ZeraContatoFolhaMensagemAviso( void );

};

#endif
