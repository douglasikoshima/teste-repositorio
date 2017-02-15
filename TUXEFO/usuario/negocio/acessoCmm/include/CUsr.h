/*****************************************************************************
 *
 * Modulo:    CUsr
 * Arquivo:   CUsr.h
 * Proposito: servicos Pro*C++ para persistencia de banco de dados
 * Historico:
 * Data        Autor                 Descricao
 * ----------  --------------------  --------------------------------
 * 18/05/2004  C_RECOliveira         Criacao
 * 18/05/2004  C_EDMartins           Criacao
 *
 ****************************************************************************/
#ifndef CUsrH

struct stDOC
{
	char* cidDocumento;
	char* cnrDocumento;
	char* cidTipoDocumento;
	char* csgTipoDocumento;
	char* cidUF;
	char* cidPais;
};

struct stDOCUMENTO
{
	stDOC stDoc[10];//No maximo 10, resolver fazer assim pra nao usar malloc ou new
	int   iCont;
};

/*****************************************************************************
 * Definicao Global
 ****************************************************************************/
#define CUsrH

/*****************************************************************************
 * Header
 ****************************************************************************/
#include<tuxfw.h>

#include "CUsrItr.h"
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

/*****************************************************************************
 * Classe
 ****************************************************************************/
class CUsr : public CUsrItr
{
	public:
		char cContadorPagina[255+1];

		CUsr();
		~CUsr();
        bool VerificaRebaixamentoPerfil(const char* cLogin, const char *cidPerfilConsultorAtdAtual);
		void RemoveNaoNumero( char* cDestino, const char* cOrigem );
		void RemoveEspecial( char* cDestino, const char* cOrigem );
		char* RTrim(char *pszString);
		int UsrGrpRelacao(DOMNode*dnode, XMLGen*xml_g);
		int UsrLista(DOMNode*dnode, XMLGen*xml_g);
		int UsrEditar( const char* cidUsuario     
				     , const char* cemail         
                     , const char* cidDDD         
                     , const char* cdsTelefone    
                     , const char* clogin         
                     , const char* cloginCti      
                     , const char* cidStatusAtual 
                     , const char* cidMotivo
                     , const char* cloginChefe
                     , const char* cinConsultor
				     , const char* cidUFOperadora
                     , const char* cLogUser
                     , const char* cidPerfilConsultorAtd
                     , const char* cidFornecedorConsultorAtd
                     , const char* cidSiteConsultorAtd
                     , const char* cdsLoginRoteamento );

		int UsrImnRelacao(DOMNode*dnode, XMLGen*xml_g);
		int UsrGrpRelaciona(DOMNode*dnode, XMLGen*xml_g, char* cLogUser);
		int UsrPrfRelaciona(DOMNode*dnode, XMLGen*xml_g, char* cLogUser);
		int UsrImnRelaciona(DOMNode*dnode, XMLGen*xml_g, char* cLogUser);
		int UsrUfoRelaciona(DOMNode*dnode, XMLGen*xml_g, char* cLogUser);
		int UsrInserir(DOMNode*dnode, XMLGen*xml_g, char* cLogUser);
		int UsrListaPar(DOMNode*dnode, XMLGen*xml_g);
		int UsrPrfRelacao(DOMNode*dnode, XMLGen*xml_g);
		int UsrUfoRelacao(DOMNode*dnode, XMLGen*xml_g);
		int UpdateSessaoUsuario(char* clogin, char* cdsSessaoUsuario);

/*****************************************************************************
 * Metodos do Eder (Serviços complexos)
 ****************************************************************************/
		int ListDoc( char* cnrDocumento );
		int ListCpf( char* cnrDocumento );
		int ListId( char* cid );
		int ListLogin( char* cLogin );
		int ListLogin( char* cLogin, char* cidUsuario );

		int ListPar( char* cnmnome,
		             char* cnmnomemeio,
				     char* cnmsobrenome,
				     char* cidUFOperadora,
				     char* clogin,
				     char* cloginCti,
				     char* cidStatusAtual,
				     char* cidCargoAtual,
				     char* cloginChefe,
				     char* csgTipoDocAtual,
				     char* cdsDocAtual,
				     char* cinConsultor,
				     char* cpaginaAtual,
				     char* cregistrosPPagina,
				     char* cIdNivel,
				     char* cIdCargo,
				     char* cIdOrganizacao,
				     char* cIdDepartamento);
			
		int ListComDadosUsuario( char* cnmnome,
		                         char* cnmnomemeio,
						         char* cnmsobrenome,
							     char* cidUFOperadora,
							     char* clogin,
							     char* cloginCti,
							     char* cidStatusAtual,
							     char* cidCargoAtual,
							     char* cloginChefe,
							     char* csgTipoDocAtual,
							     char* cdsDocAtual,
						         char* cinConsultor,
							     char* cpaginaAtual,
							     char* cregistrosPPagina );

		int ListComDocumento( char* cnmnome,
		                      char* cnmnomemeio,
						      char* cnmsobrenome,
							  char* csgTipoDocAtual,
							  char* cdsDocAtual,
							  char* cpaginaAtual,
							  char* cregistrosPPagina );

		int ListComNome( char* cnmnome,
		                 char* cnmnomemeio,
					     char* cnmsobrenome,
					     char* cpaginaAtual,
					     char* cregistrosPPagina );

		int ListSemParametro( char* cpaginaAtual,
					          char* cregistrosPPagina );

		int ListComOrganograma( 	char* cnmnome,
						char* cnmnomemeio,
						char* cnmsobrenome,
						char* cidUFOperadora,
						char* clogin,
						char* cloginCti,
						char* cidStatusAtual,
						char* cidCargoAtual,
						char* cloginChefe,
						char* csgTipoDocAtual,
						char* cdsDocAtual,
						char* cinConsultor,
						char* cpaginaAtual,
						char* cregistrosPPagina,
						char* cIdNivel,
						char* cIdCargo,
						char* cIdOrganizacao,
						char* cIdDepartamento );

       int ListComOrganogramaPessoa(char* cnmnome,
		                            char* cnmnomemeio,
				                    char* cnmsobrenome,
				                    char* cidUFOperadora,
				                    char* clogin,
				                    char* cloginCti,
				                    char* cidStatusAtual,
				                    char* cidCargoAtual,
				                    char* cloginChefe,
				                    char* csgTipoDocAtual,
				                    char* cdsDocAtual,
				                    char* cinConsultor,
				                    char* cpaginaAtual,
				                    char* cregistrosPPagina,
				                    char* cIdNivel,
				                    char* cIdCargo,
				                    char* cIdOrganizacao,
				                    char* cIdDepartamento );

        int ListComOrganogramaUsuario(char* cnmnome,
		                              char* cnmnomemeio,
									  char* cnmsobrenome,
									  char* cidUFOperadora,
									  char* clogin,
									  char* cloginCti,
									  char* cidStatusAtual,
									  char* cidCargoAtual,
									  char* cloginChefe,
									  char* csgTipoDocAtual,
									  char* cdsDocAtual,
									  char* cinConsultor,
									  char* cpaginaAtual,
									  char* cregistrosPPagina,
									  char* cIdNivel,
									  char* cIdCargo,
									  char* cIdOrganizacao,
									  char* cIdDepartamento );


		int InsertPessoa( char* cidUF         
		                 ,char* cnome         
					     ,char* cnomemeio
		                 ,char* csobrenome    
		                 ,char* cidUser );

		int EditPessoa( char* cidPessoa
					   ,char* cidUF
		               ,char* cnome
					   ,char* cnomemeio
		               ,char* csobrenome
					   ,char* cidUser );

		int InsertUsuario( char* cidUsuario
                        ,char* cemail
	                    ,char* cidDDD
	                    ,char* cTelefone
	                    ,char* clogin
	                    ,char* cloginCti
	                    ,char* cidStatusAtual
	                    ,char* cidMotivo
	                    ,char* cloginChefe
	                    ,char* cindisponivelWF
	                    ,char* cinConsultor
						,char* cidUFOperadora
				        ,char* cidUser
                        ,char* cidPerfilConsultorAtd
                        ,char* cidFornecedorConsultorAtd
                        ,char* cidSiteConsultorAtd
                        ,char* cdsLoginRoteamento
                        ,char* cnome
					    ,char* cnomemeio
		                ,char* csobrenome
                        );

		int InsertChefeId( char* cidUsuario
		                  ,char* cidChefe
		                  ,char* cidUser );

        int ListUsuariosSuperiores( const char* idPessoaUsuarioWeb,
                                    const char* pszidPerfilConsultorAtdAtual,
                                    const char* pszLogin,
                                    const char* cpaginaAtual,
                                    const char* cregistrosPPagina
                                  );

		int InsertChefeLogin( const char* cidUsuario
		                     ,const char* cloginChefe
		                     ,const char* cidUser );

		int InsertUsuarioUFOperadora( char* cidUsuario
		                             ,char* cidUFOperadora
		                             ,char* cidUser );

		void  GetXml( char* cNomeTag, XMLGen*xml );
		char* GetErro( void ) { return cErro; };
		int FindIdUser( char* cidUsuario );
		int TemProximaPagina( void ) { return iProximaPagina; };
		int TamanhoPagina( void ) { return iTamanhoPagina; };
		int AltStatusUsu(DOMNode*,XMLGen*);
		int ExisteDocumento( char* cidUsuario
			                ,char* cnrDocumento
							,char* cidTipoDocumento );

		int VerificaGrupo( char* cidUsuario );
		int VerificaRegionais( char* cidUsuario );
		int MesmoStatus( char* cidUsuario, char* cidStatus );
		int MesmoStatusSigla( char* cidUsuario, char* csgStatus );
		int CopiaPessoa( char* cidUsuario
					    ,char* cidUser );
		//Busca na tabela APOIO.PARAMETRO pelo parametro CDPARAMETRO = 'TOTAL_PAGINAS_PESQUISA_USUARIO'
		//Este parametro indica a quantidade maxima de linhas que o relatorio retorna
		int TotalLinhasPorPesquisa( void );
        int ValidaUsuario( char * nrDocumentoPrm , 
                           char * nmLoginPrm , 
                           char * nmPrimeiroPrm , 
                           char * nmMeioPrm ,  
                           char * nmUltimoPrm , 
                           char * idUFPrm ,
                           char * cAuxloginCtiPrm ,
                           char * inDisponivelPrm ,
                           char * cAuxemailPrm ,
                           char * cAuxTelefonePrm ,
                           char * cAuxidDDDPrm ,
                           char * cAuxinConsultorPrm ,
                           char * cAuxidPerfilConsultorAtdPrm ,
                           char * cAuxidFornecedorConsultorAtdPrm ,
                           char * cAuxidSiteConsultorAtdPrm ,
                           char * cAuxdsLoginRoteamentoPrm ,
                           char * cloginChefe ,
                           char * idUsuarioPrm );


	private:
		void Zerar( void );

		int  iRegistroVazio;
		int  iProximaPagina;
		int  iTamanhoPagina;
		char cErro[255+1];

	//Metodos utilizados por workflow criado por Renato Teixeira
	void removeAtendimentos(const char *cLogUserOra,const char* pzcidPessoaUsuario);
    // void removeUsuarioDevolucao(char *cLogUserOra,char* pzcidPessoaUsuario);
};

#endif
