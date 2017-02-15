#ifndef CBaixaH
#define CBaixaH

#include <tuxfw.h>
#include "../include/CBxaItr.h"

#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

class CBaixa : public CBaixaItr
{
    private:
		int InserirRaiz();
		int InserirNode();
		char pzcidNome[21+1];
		char pzcidBaixaPai[21+1];
		char pzcPath[2048+1];

	public:
		CBaixa();
		~CBaixa();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidBaixa );
		//Recupera todos os registros
		int ListAll( void );
		int ListNivel( char* cidBaixa );
		//Insere um registro
		int Insert( char* cidBaixaPai,
			        char* cidNomeBaixa,
			        char* cnmBaixa,
                 	char* cinDisponibilidade,
					char* cinFolha,
			        char* cidUsuarioAlteracao );
		//Atualiza um registro
		int Update( char* cidBaixa,
			        char* cidNomeBaixa,
			        char* cnmBaixa,
                 	char* cinDisponibilidade,
					char* cinFolha,
			        char* cidUsuarioAlteracao );
		//Apaga um ou mais registros
		int Delete( char* cidBaixa
                   ,char* cidUsuarioAlteracao );
		//Lista baixas relacionadas a contato folha (arvore inteira)
		int RelacaoBxaCtt( char* cidContato );
		//Lista baixas relacionadas a contato folha (nivel-a-nivel)
		int RelacaoBxaCtt( char* cidContato, char* cidBaixa );

		//Recupera o path entre dois nos da arvore
		char* getPath( char* cidBaixa, char* cidBaixaPai );
		//Recupera o path a partir da raiz
		char* getPath( char* cidBaixa );
		char* GravaPath( char* cidBaixa );
		int  isFolha( char* cidBaixa );
		int  ativaInativaArvore( char* cidBaixa, char* cinHabilitado );
		int  ativaInativaArvoreUP1( char* cidBaixa, char* cinHabilitado, XMLGen* xml_g);
        int ativaInativaArvoreUP2(char *pszPesquisa, char *pinHabilitado);
		int  TemContatoAssociado( char* cidBaixa );
		int  TemPaiInativo( char* cidBaixa );
		int  TemNomeIgualNoMesmoNivelPai( char* cidBaixaPai, char* cidNomeBaixa, char* cinFolha );
		int  TemNomeIgualNoMesmoNivel( char* cidBaixa, char* cidNomeBaixa, char* cinFolha );
		void ApagaNomesNaoUtilizados( void );

		int   ProcuraNome( char* cnmBaixa );
		char* getIdNome( void );
		void  setIdNome( char* cidNome );
		char* getIdBaixaPai( void );
		void  setIdBaixaPai( char* cidBaixaPai );
		int   RecuperaIdBaixaPai( void );
		int   TemFolha( char* cidBaixa );
//Codigos abaixo de autoria de Marcelo Nunez
		//  Lista Baixas Filhos
		int ListChildren( char * cidBaixaParam, char * cidContatoParam );
		//  Lista Baixa Pai
		int ListOnlyParent( char * cidContatoParam );
		
		int ListChildrenSemContat( void );
		
		int ListChildrenIdBaixa( char * idBaixaWrk );
		int ListBxaComContatos( char * cidContatoParam );
		int ListBxa( char * idBaixaParam, char * cidContatoParam );
		
};

#endif