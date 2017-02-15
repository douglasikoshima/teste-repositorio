#ifndef CPesquisaPessoaItrH
#define CPesquisaPessoaItrH

#include <stdlib.h>
#include <string.h>


struct STPesquisaPessoaRegistro
{
	char cidPessoa[21+1];
	char cnmPessoa[255+1];
	char cidTipoPessoa[21+1];
	char csgTipoPessoa[255+1];
	char cdsTipoPessoa[255+1];
	char cidDocumento[21+1];
	char cnrDocumento[255+1];
	char cidTipoDocumento[21+1];
	char csgTipoDocumento[255+1];
	char cdsTipoDocumento[255+1];
};

class CPesquisaPessoaItr
{
public:
	CPesquisaPessoaItr();
	~CPesquisaPessoaItr();
	int Primeiro( void );
	int Ultimo( void );
	int Quantidade( void );
	STPesquisaPessoaRegistro* Registro( void );
	STPesquisaPessoaRegistro* Registro(int nPosicao);

private:
	STPesquisaPessoaRegistro* stcrPesquisaPessoa;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
				char* cidPessoa
			   ,char* cnmPessoa
			   ,char* cidTipoPessoa
			   ,char* csgTipoPessoa
			   ,char* cdsTipoPessoa
			   ,char* cidDocumento
			   ,char* cnrDocumento
			   ,char* cidTipoDocumento
			   ,char* csgTipoDocumento
			   ,char* cdsTipoDocumento
			);
	void Add( 
				 char* cidPessoa
				,char* cnmPessoa 
				,char* cdsTipoPessoa
				,char* cnrDocumento 
				,char* cdsTipoDocumento
			);
	void Add( 
			    char* cidTipoDocumento
			   ,char* csgTipoDocumento
			   ,char* cdsTipoDocumento
			);
	void ZeraPesquisaPessoa( void );

};

#endif
