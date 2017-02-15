#ifndef CPessoaDocumentoItrH
#define CPessoaDocumentoItrH

#include <stdlib.h>
#include <string.h>
#include<tuxfw.h>

struct STPessoaDocumentoRegistro
{
	char cidPessoaDocumento[21+1];
	char cidDocumento[21+1];
	char ctsSincronismo[21+1];
	char csqSincronismo[21+1];
	char cidSistemaOrigem[21+1];
	char cidDocumentoSistemaOrigem[255+1];
	char cdtexpiracao[12+1];
	char cidPessoa[21+1];
	char cnmPessoa[255+1];
	char cnmNome[255+1];
	char cnmNomemeio[255+1];
	char cnmSobrenome[255+1];
};

class CPesDocItr
{
public:
	CPesDocItr();
	~CPesDocItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STPessoaDocumentoRegistro* Registro( void );
	STPessoaDocumentoRegistro* Registro(int nPosicao);

private:
	STPessoaDocumentoRegistro* stcrPessoaDocumento;
	int _iQuantidade;
	int _iPosicao;

protected:
	void Add( char* cidPessoaDocumento,
	          char* cidPessoa,
	          char* cidDocumento,
	          char* ctsSincronismo,
	          char* csqSincronismo,
	          char* cidSistemaOrigem,
	          char* cidDocumentoSistemaOrigem,
	          char* cdtexpiracao);

	void Add( char* cidPessoaDocumento,
	          char* cidPessoa,
	          char* cidDocumento,
	          char* ctsSincronismo,
	          char* csqSincronismo,
	          char* cidSistemaOrigem,
	          char* cidDocumentoSistemaOrigem,
	          char* cdtexpiracao,
			  char* cnmPessoa,
			  char* cnmNome,
			  char* cnmNomemeio,
			  char* cnmSobrenome);
	void ZeraPessoaDocumento( void );

};

#endif