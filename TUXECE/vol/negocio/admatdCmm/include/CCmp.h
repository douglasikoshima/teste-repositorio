#ifndef CCampoH
#define CCampoH

#include <tuxfw.h>
#include "../include/CCmpItr.h"

#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )
     
     
using namespace std;
#include <iterator>
#include <vector>
#include <list>
#include <string>

typedef list<unsigned long> LISTA_IDS;
typedef list<unsigned long> LISTA_UF;
typedef list<unsigned long> LISTA_TPLINHA;
typedef list<unsigned long> ListCampos;
typedef list<unsigned long> ListGrupos;


class CCampo : public CCampoItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
        LISTA_IDS       pCamposDinamicos;
        LISTA_IDS       pCamposExcluir;
		list<unsigned long>::iterator it;
			
		LISTA_UF * pUF;
		LISTA_TPLINHA * pTpLinha;
		
		ListGrupos * pGrupos;         
		ListCampos * pCampos;
		
		char nmGrupoCampoPrm[256];
		unsigned long idGrupoCampoPrm;
		unsigned long idClassificadorPrm;
		unsigned long idContatoFolhaCampoPrm;
		unsigned long idContatoPrm;
		unsigned long idFaseProcessoPrm;
		unsigned long idUsuarioPrm;

		char* cidUser;

		CCampo();
		CCampo( DOMNode* dnode );
		CCampo( DOMNode* dnode,LISTA_UF * pUF_Prm,LISTA_TPLINHA * pTpLinha_Prm );
		CCampo( DOMNode* dnode,LISTA_UF * pUF_Prm,LISTA_TPLINHA * pTpLinha_Prm,ListCampos * pCamposPrm,ListGrupos * pGruposPrm );
		~CCampo();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		void GetXmlListaAssociada( char* cNomeTag, char* cNomeClassificador, char* cNomeCampo, XMLGen*xml );
		void GetXmlListAll(char* cNomeTag, XMLGen*xml);
		void GetXmlClassificador(char* cNomeTag, XMLGen*xml);
		void GetXmlTipoDado(char* cNomeTag, XMLGen*xml);
		void GetXmlLayoutApresentacao(char* cNomeTag, XMLGen*xml);
		void GetXmlMascaraApresentacao(char* cNomeTag, XMLGen*xml);
		void GetXmlCampos( XMLGen*xml );
		void GetCurrentID(char *);
		//Recupera um registro
		int ListId( char* cidCampo );
		//Recupera todos os registros
		int ListAll( void );
		//Recupera os registros segundo os critérios de busca
		int ListPar(
						 char* cidCampo
					 	,char* cnmCampo
					 	,char* cidTipoDadoCampo
					 	,char* cidMascaraApresentacaoCampo
					 	,char* cidLayoutApresentacaoCampo
					 	,char* cnrTamanho
					 	,char* cinDisponibilidade
					 	,char* cidClassificadorCampo
					 	,char* cinFiltro
					 	,char* cinPesquisa
					 	,char* cinObrigatorio
			);
		int ListPar(char* cidCampo);
		int ListClassificadorCampo( void );
		int ListTipoDadoCampo( void );
		int ListMascaraApresentacaoCampo( void );
		int ListLayoutApresentacaoCampo(void);
		//Relacinado com formulario
		int Relacionados( char* cidContato );
		//Nao relacionados a formulario
		int NaoRelacionados( char* cidContato );
		//Relacinado com formulario com filtro de classificador
		int Relacionados( char* cidContato, char* cidClassificadorCampo );
		//Nao relacionados a formulario com filtro de classificador
		int NaoRelacionados( char* cidContato, char* cidClassificadorCampo );
		//Relacinado com formulario com filtro fase
		int RelacionadosFase( char* cidContato, char* cidFaseProcesso );
		//Nao relacionados a formulario com filtro fase
		int NaoRelacionadosFase( char* cidContato, char* cidFaseProcesso );
		//Relacinado com formulario com filtro de classificador e fase
		int Relacionados( char* cidContato, char* cidClassificadorCampo, char* cidFaseProcesso );
		//Nao relacionados a formulario com filtro de classificador e fase
		int NaoRelacionados( char* cidContato, char* cidClassificadorCampo, char* cidFaseProcesso );
		//Lista com todos os campos, sem filtro
		int ListaAssociada( char* cidContato );
		int ListaAssociada( char* cidContato, char* cidFaseProcesso );
		int Insert(
					 	 char* cnmCampo
					 	,char* cidTipoDadoCampo
					 	,char* cidMascaraApresentacaoCampo
					 	,char* cidLayoutApresentacaoCampo
					 	,char* cnrTamanho
					 	,char* cinDisponibilidade
					 	,char* cidClassificadorCampo
					 	,char* cinFiltro
					 	,char* cinPesquisa
					 	,char* cinObrigatorio
					    ,char* cidUser
		);
		int Update(
					 	 char* cidCampo
					 	,char* cnmCampo
					 	,char* cidTipoDadoCampo
					 	,char* cidMascaraApresentacaoCampo
					 	,char* cidLayoutApresentacaoCampo
					 	,char* cnrTamanho
					 	,char* cinDisponibilidade
					 	,char* cidClassificadorCampo					 	
					 	,char* cinFiltro
					 	,char* cinPesquisa
					 	,char* cinObrigatorio
					    ,char* cidUser
		);
		int  Delete( char* cidCampo );
		void IncluirGrupoDependentes( void ) ;
		void AlterarGrupoDependentes( void ) ;
		void SelecaoGruposDinamicos(  XMLGen* xml  ) ;
		void ListaCamposDinamicos( void ) ;
        void CamposDinamicosGrupoRelacion( void ) ;
        void CamposDinamicosGrupoNaoRelacion( void ) ;
        int  ListaComponentes( XMLGen* xml );
        int  ListaGrupoComponentes( XMLGen* xml );
		int  RelacionadosClassif( XMLGen * xml_g );
		int  ListaGrupoCampos( XMLGen * xml );
		int  CamposRelac( XMLGen * xml );
		int  CamposNaoRelac( XMLGen * xml );
		int  MostraCampos( XMLGen * xml ) ;
		int  GravaAgrupamento( XMLGen * xml );
		int  AtualizaCampos( XMLGen * xml );
		int  CarregaCampos( unsigned long idGrupoPrm,ListCampos *L );
		int  GrupoCamposDependentes( XMLGen* xml );
		int  CamposValores( XMLGen* xml );
		int  ObtemValores( unsigned long idCampoPrm,XMLGen* xml );
		int  InsereValor( DOMNode* dnode,XMLGen* xml ); 
		int  BuscaValor( unsigned long * idTabelaDominio,char * nmGrupo );
		void trim2(string& str);
		bool VerificaCampos( unsigned long idContatoPrm,unsigned long idUFPrm,unsigned long idTipoLinhaPrm,unsigned long idCampoPrm, char * MsgErro  ) ;

};

#endif

