#ifndef CContatoInformacaoItrH
#define CContatoInformacaoItrH

#include <stdlib.h>
#include <string.h>


struct STContatoInformacaoRegistro
{
	char cidContatoInformacao[21+1];
	char cidContato[21+1];
	char cidUFOperadora[21+1];
	char cdsUFOperadora[355+1];
	char cidTipoLinha[21+1];
	char cdsTipoLinha[255+1];
	char cidTipoCliente[21+1];
	char cdsTipoCliente[255+1];
	char cnmURLContatoInformacao[255+1];
};

class CContatoInformacaoItr
{
public:
	CContatoInformacaoItr();
	~CContatoInformacaoItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STContatoInformacaoRegistro* Registro( void );
	STContatoInformacaoRegistro* Registro(int nPosicao);

private:
	STContatoInformacaoRegistro* stcrContatoInformacao;
	int _iQuantidade;
	int _iPosicao;

protected:

	void AddUFOperadora( char* cidContato
						,char* cidUFOperadora
						,char* cdsUFOperadora );

	void AddTipoLinha( char* cidContato
					  ,char* cidTipoLinha
					  ,char* cdsTipoLinha );

	void AddTipoCliente( char* cidContato
						,char* cidTipocliente
						,char* cdsTipocliente );

	void Add( char* cidContatoInformacao,
			  char* cidContato,
			  char* cidUFOperadora,
			  char* cdsUFOperadora,
			  char* cidTipoLinha,
			  char* cdsTipoLinha,
			  char* cidTipoCliente,
			  char* cdsTipoCliente,
			  char* cnmURLContatoInformacao );

	void ZeraContatoInformacao( void );

};

#endif
