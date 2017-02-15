#ifndef CContatoFolhaCampoH
#define CContatoFolhaCampoH

#include <tuxfw.h>
#include "../include/CFlhCmpItr.h"

#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

using namespace std;
#include <iterator>
#include <vector>
#include <list>
#include <string>

typedef list<unsigned long> LISTA_UF;
typedef list<unsigned long> LISTA_TPLINHA;


class CContatoFolhaCampo : public CContatoFolhaCampoItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		int hasNext;
		int pageNumber;
 
		unsigned long idClassificadorPrm;
		unsigned long idContatoPrm;
		unsigned long idFaseProcessoPrm;
		
		list<unsigned long>::iterator it;
			
		LISTA_UF * pUF;
		LISTA_TPLINHA * pTpLinha;

		CContatoFolhaCampo();
		CContatoFolhaCampo( DOMNode* dnode,LISTA_UF * pUF_Prm,LISTA_TPLINHA * pTpLinha_Prm );
		~CContatoFolhaCampo();

		void GetXml( char* cNomeTag, XMLGen*xml );
		int ListId( char* cidContato, char* cidFaseProcesso, char* cidCampoClassificador );
		int ListId( char* cidContato );
		int ListAll( void );
		int Insert( 
			char* cidContato,
			char* cidUFOperadora,
			char* cidTipoLinha,
			char* cidCampo,
			char* csqOrdemApresentacao,
			char* cidFaseProcesso,
			char* cidUsuarioAlteracao );
		int InsertFalso( 
			char* cidContato,
			char* cidUFOperadora,
			char* cidTipoLinha,
			char* cidCampo,
			char* csqOrdemApresentacao,
			char* cidFaseProcesso );
		//Insere todos os registros armazenados por InserFalso
		int InsertArray( char* cidUsuarioAlteracao );
		//Atualiza um registro
		int Update( 
			char* cidContatoFolhaCampo,
			char* cidContato,
			char* cidUFOperadora,
			char* cidTipoLinha,
			char* cidCampo,
			char* csqOrdemApresentacao,
			char* cidFaseProcesso,
			char* cidUsuarioAlteracao );
		//Apaga um ou mais registros
		int Delete( char* cidContatoFolhaCampo );
		//Procura par uma folha
		int Find( char* cidContato
			     ,char* cidUFOperadora
				 ,char* cidTipoLinha
				 ,char* cidCampo
				 ,char* cidFaseProcesso );
		void AddExistente( char* cidContato
					      ,char* cidUFOperadora
						  ,char* cidTipoLinha
						  ,char* cidCampo
						  ,char* cidFaseProcesso );

		void Copy( char* cidContatoOrigem
			      ,char* cidContatoDestino
				  ,char* cidUser ); 
	    
	    int  Selecao(  XMLGen * xml_g  ) ;
	    void trim2(string& str);
		int  CarregaGrupos( void ) ;

};

#endif

