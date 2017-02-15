#ifndef CHierarquiaDeptoPessoaH
#define CHierarquiaDeptoPessoaH

#include <tuxfw.h>
#include "../include/CHUPItr.h"
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

/*******************************************************************************
 * Definição de tamanhos de campos
 *******************************************************************************/
#define LEN_ID          22
#define LEN_DATE_ORA    8
#define LEN_EOS         1

/*******************************************************************************
 * Estrutura referente a HierarquiaDeptoPessoa
 *******************************************************************************/
typedef struct 
{
    char szIdHierarquiaDeptoPessoa[ LEN_ID + LEN_EOS ];
    char szIdNivelCargo[ LEN_ID + LEN_EOS ];
    char szIdPessoa[ LEN_ID + LEN_EOS ];
    char szIdOrganizacaoDepartamento[ LEN_ID + LEN_EOS ];
    char szIdUsuarioAlteracao[ LEN_ID + LEN_EOS ];
    char szDtUltimaAlteracao[ LEN_DATE_ORA + LEN_EOS ];
} THierarquiaDeptoPessoa;

class CHierarquiaDeptoPessoa : public CHierarquiaDeptoPessoaItr
{
    private:
        int InserirRaiz();
        int InserirNode();

    THierarquiaDeptoPessoa tHierarquiaDeptoPessoa;

    public:
        CHierarquiaDeptoPessoa();
        ~CHierarquiaDeptoPessoa();

        // Métodos de SET.
        void  setIdUsuarioAlteracao( char* pszIdUsuarioAlteracao );
        void  setIdHierarquiaDeptoPessoa( char* pszIdHierarquiaDeptoPessoa );
        void  setIdNivelCargo( char* pszIdNivelCargo );
        void  setIdPessoa( char* pszIdPessoa );
        void  setIdOrganizacaoDepartamento( char* pszIdOrganizacaoDepartamento );
        void  setDtUltimaAlteracao( char* pszDtUltimaAlteracao );

        // Métodos de negócio.
        void  atualizaHierarquiaDeptoPessoa( void );
        void  insereHierarquiaDeptoPessoa( void );

        bool  buscaPorIdPessoa( void );

        // Métodos de negócio que buscam em outras tabelas;
        bool  buscaIdOrganizacaoDepartamento( char* pszIdOrganizacao
                                            , char* pszIdDepartamento );

        bool  buscaIdNivelCargo( char* pszIdNivel
                               , char* pszCargo);

        // Métodos de GET.
        // char* getIdPessoa( void );
        // char* getIdUsuarioAuteracao( void );
        // char* getIdHierarquiaDeptoPessoa( void );
        // char* getIdNivelCargo( void );
        // char* getIdPessoa( void );
        // char* getIdOrganizacaoDepartamento( void );
        // char* getIdUsuarioAlteracao( void );
        // char* getDtUltimaAlteracao( void );

		/***********************************************************************************/

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Monta o xml array padrao desta tabela
		void GetXml( char* cNomeTagArray, char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidHierarquiaDeptoPessoa );
		//Recupera todos os registros
		int ListAll( void );
		//Lista pesquisa
		int List( 
			char* cidHierarquiaDeptoPessoa,
			char* cidNivelCargo,
			char* cidPessoa,
			char* cidOrganizacaoDepartamento,
			char* cidUsuarioAlteracao );
		//Insere um registro
		int Insert( 
			char* cidNivelCargo,
			char* cidPessoa,
			char* cidOrganizacaoDepartamento,
			char* cidUsuarioAlteracao );
		//Apaga um ou mais registros
		int Delete( char* cidHierarquiaDeptoPessoa );
		//Relaciona e Lista relacionados e existentes
		int RelacionaPss( char*cidPessoa, char*cNomeIdTag, char*cidUsuarioAlteracao, DOMNode*dnode );
		//Apaga um ou mais registros
		int ErasePss( char* cidPessoa );
		//Relaciona e Lista relacionados e existentes
		int RelacionaNvl( char*cidNivel, char*cNomeIdTag, char*cidUsuarioAlteracao, DOMNode*dnode );
		//Apaga um ou mais registros
		int EraseNvl( char* cidNivel );
		//Relaciona e Lista relacionados e existentes
		int RelacionaOrg( char*cidOrganizacao, char*cNomeIdTag, char*cidUsuarioAlteracao, DOMNode*dnode );
		//Apaga um ou mais registros
		int EraseOrg( char* cidOrganizacao );

};

#endif

