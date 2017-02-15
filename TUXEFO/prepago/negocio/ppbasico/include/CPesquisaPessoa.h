#ifndef CPesquisaPessoaH
#define CPesquisaPessoaH

#include <tuxfw.h>
#include <CPesquisaPessoaItr.h>
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CPesquisaPessoa : public CPesquisaPessoaItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CPesquisaPessoa();
		~CPesquisaPessoa();

		void upper(char *pTok);

		int buscarDocPorNome( char* cnmPessoa
							, char* cnmNomeMeio
							, char* cnmSobrenome
							, char* cinTipoPessoa );

		int buscarDocPorRazao( char* cnmPessoa
							 , char* cinTipoPessoa );

		int buscarDocPorNrLinha( char* ccdAreaRegistro
		                       , char* cnrLinha
							   , char* cinTipoPessoa);

		int buscarDocPorNrConta(char* ccdConta
							  , char* cinTipoPessoa );

		int buscarDocPorTipDocumento( char* csgDocumento
		                            , char* cnrDocumento
							        , char* cinTipoPessoa);

		int validaSiglaDocumento( char* csgDocumento );
};

#endif

