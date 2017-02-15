#ifndef CPessoaDocumentoH
#define CPessoaDocumentoH

#include<tuxfw.h>
#include "CPesDocItr.h"
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CPesDoc : public CPesDocItr
{
	private:
		char cTexto[300+1];
	public:
		CPesDoc();
		~CPesDoc();
		
		int ListId( char* cid );
		
		int ListAll( void );
		
		int ListDoc( char* cidPessoa );
		
		int ListPessoa( char* cnrDocumento );
		int ListPessoa( char* cnrDocumento, char* cidTipoDocumento );
		int ListPessoaUsuario( char* cnrDocumento, char* cidTipoDocumento );
		int ListPessoaIdDoc( char* cidDocumento );

		int VerificaPessoaIdDoc( char* cidPessoa, char* cidDocumento );

		int VerificaPessoaDoc( char* idPessoa, char* cnrDocumento );
		int VerificaPessoaDocIdTipo( char* cidPessoa, char* cnrDocumento, char* cidTipo );
		int VerificaPessoaDocSiglaTipo( char* cidPessoa, char* cnrDocumento, char* csgTipo );

		void GetXml( char* cNomeTag, XMLGen*xml );
		
		int InsertId( char* cidPessoa
                     ,char* cidDocumento
                     ,char* cidUser );
		
		int RemoveRelacaoComPessoa( char* cidPessoa );

		char* toUpper( char* cText );
		char* toLower( char* cText );
};

#endif