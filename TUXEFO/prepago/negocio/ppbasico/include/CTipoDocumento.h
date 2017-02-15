#ifndef CTipoDocumentoH
#define CTipoDocumentoH

#include <tuxfw.h>
#include <CTipoDocumentoItr.h>


class CTipoDocumento : public CTipoDocumentoItr
{
	public:
		CTipoDocumento();
		~CTipoDocumento();
		//Dado um ID, recupera um tipo de documento
		int ListId( char* cidTipoDocumento );
		//Recupera todos os tipos de documentos
		int ListAll( void );
		//Recupera somente tipos de documentos de pessoa fisica
		int ListPF( void );
		int ListPFFiltrado( void );
		//Recupera somente tipos de documentos de pessoa juridica
		int ListPJ( void );
		int ListPJFiltrado( void );
		//Recupera somente tipos de documentos de pessoa juridica que seja inscricoes
		int ListPJInscricoes( void );
		//Monta XML no formato da tabela
		void getXmlBasico( char* cNomeTag, XMLGen*xml );
		//Monta um XML com id e descricao para combos
		void getXmlCombo( char* cNomeTag,
                          char* cidTag,
                          char* cdsTag,
                          XMLGen* xml );
		//Monta um XML com id e descricao para combos
		void getXmlCombo( char* cNomeTag,
                          char* cidTag,
                          char* csgTag,
                          char* cdsTag,
                          XMLGen* xml );
		//Monta um XML colocando a sgclassificacao no lugar do idtipodocumento
		void getXmlCombo2( char* cNomeTag,
                          char* cidTag,
                          char* csgTag,
                          char* cdsTag,
                          XMLGen* xml );
		//Monta um XML com id e descricao para combos sem mostrar itens repetidos (descricao)
		void getXmlComboSemRepetencia( char* cNomeTag,
									   char* cidTag,
									   char* csgTag,
									   char* cdsTag,
									   XMLGen* xml );
};

#endif