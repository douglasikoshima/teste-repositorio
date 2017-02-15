#ifndef CContatoFolhaCampoItrH
#define CContatoFolhaCampoItrH

#include <stdlib.h>
#include <string.h>


struct STContatoFolhaCampoRegistro
{
	char cidContatoFolhaCampo[21+1];
	char cidContato[21+1];
	char cidUFOperadora[21+1];
	char cidTipoLinha[21+1];
	char cidCampo[21+1];
	char csqOrdemApresentacao[21+1];
	char cidFaseProcesso[21+1];
	int  iInsertArray;
};

class CContatoFolhaCampoItr
{
public:
	CContatoFolhaCampoItr();
	~CContatoFolhaCampoItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STContatoFolhaCampoRegistro* Registro( void );
	STContatoFolhaCampoRegistro* Registro(int nPosicao);

private:
	STContatoFolhaCampoRegistro* stcrContatoFolhaCampo;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidContatoFolhaCampo,
			char* cidContato,
			char* cidUFOperadora,
			char* cidTipoLinha,
			char* cidCampo,
			char* csqOrdemApresentacao,
			char* cidFaseProcesso 
	);

	void AddArray( 
			char* cidContatoFolhaCampo,
			char* cidContato,
			char* cidUFOperadora,
			char* cidTipoLinha,
			char* cidCampo,
			char* csqOrdemApresentacao,
			char* cidFaseProcesso
	);

	void ZeraContatoFolhaCampo( void );

};

#endif
