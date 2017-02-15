//Definicao Global
#define CadUsrADCPP

//Header de Usr de Infra-Estrutura
#include <tuxfw.h>
#include <time.h>
#include "../../../negocio/acessoCmm/include/CUsr.h"
#include "../../../negocio/acessoCmm/include/CDoc.h"
#include "../../../negocio/acessoCmm/include/CPesDoc.h"
#include "../../../negocio/acessoCmm/include/CCrg.h"
#include "../../../negocio/acessoCmm/include/CSafePointer.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(CADUSRAD);

/**************************************************************************
 *  Funcao de Negocios:  CadUsrAD
 *
 *  Descricao: chama funcao de negocios
 *
 *  Parametros de Entrada e Saida:
 *  DOMNode		*dnode    Nodos da Arvore DOM XML
 *  XMLGen		*xml_g    XML de entrada
 *
 *  Parametros de Saida:
 *  DOMNode		*dnode    Nodos da Arvore DOM XML
 *  XMLGen		*xml_g    XML de saida
 *
 *************************************************************************/
void implCADUSRAD::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	ULOG_START("implCadUsrAD::Execute()");
	
	CSafePointer oSafePointer;
	CUsr oUsuario;
	CCrg oCargo;
	CDocumento oDocumento;
	CPesDoc oPessoaDocumento;
	struct stDOCUMENTO stDocumento;
	char cMens[255+1];
	char cAuxidUsuario[256];
	char ccdCpfCnpjBase[21+1];
	char ccdCpfCnpjControle[21+1];
	int  iAux = 0;
    int retorno;

	TDesmembraNome tDesmembraNome;

	char* cidUFOperadora                 = oSafePointer.getTag(dnode ,"idUFOperadora"                ,0);
	char* cnome                          = oSafePointer.getTag(dnode ,"nome"                         ,0);
	char* csobrenome                     = oSafePointer.getTag(dnode ,"sobrenome"                    ,0);
	char* cemail                         = oSafePointer.getTag(dnode ,"email"                        ,0);
	char* cidDDD                         = oSafePointer.getTag(dnode ,"idDDD"                        ,0);
	char* cdsTelefone                    = oSafePointer.getTag(dnode ,"dsTelefone"                   ,0);
	char* clogin                         = oSafePointer.getTag(dnode ,"login"                        ,0);
	char* cloginCti                      = oSafePointer.getTag(dnode ,"loginCti"                     ,0);
	char* cidStatusAtual                 = oSafePointer.getTag(dnode ,"idStatusAtual"                ,0);
	char* cidPerfilConsultorAtdAtual     = oSafePointer.getTag(dnode ,"idPerfilConsultorAtdAtual"    ,0);
	char* cidFornecedorConsultorAtdAtual = oSafePointer.getTag(dnode ,"idFornecedorConsultorAtdAtual",0);
	char* cidSiteConsultorAtdAtual       = oSafePointer.getTag(dnode ,"idSiteConsultorAtdAtual"      ,0);
	char* cdsLoginRoteamento             = oSafePointer.getTag(dnode ,"dsLoginRoteamento"            ,0);
	char* cidMotivo                      = oSafePointer.getTag(dnode ,"idMotivo"                     ,0);
	char* cdtInicio                      = oSafePointer.getTag(dnode ,"dtInicio"                     ,0);
	char* cdtRetorno                     = oSafePointer.getTag(dnode ,"dtRetorno"                    ,0);
	char* cidCargoAtual                  = oSafePointer.getTag(dnode ,"idCargoAtual"                 ,0);
	char* cloginChefe                    = oSafePointer.getTag(dnode ,"loginChefe"                   ,0);
	char* cidUF                          = oSafePointer.getTag(dnode ,"idUF"                         ,0);
	char* cdtInclusao                    = oSafePointer.getTag(dnode ,"dtInclusao"                   ,0);
	char* cdtExclusao                    = oSafePointer.getTag(dnode ,"dtExclusao"                   ,0);
	char* cidUsuario                     = oSafePointer.getTag(dnode ,"idUsuario"                    ,0);
	char* cinConsultor                   = oSafePointer.getTag(dnode ,"inConsultor"                  ,0);

	char* cidUser = getUser();
	//char* cidUser = "11";
	
	//Estrutura para recuperar node DOM
	DOMNode*node;
	
	//Estruturas para recuperar a data do sistema
	struct tm *ptTime;
	time_t tTimeNow;
	char cDataAtual[10+1];
	
    // Realiza Desmembramento do Nome da Pessoa Fisica.
    memset(&tDesmembraNome, 0x00, sizeof(TDesmembraNome));
    strcpy(tDesmembraNome.szNomeCompleto, cnome);
    strcat(tDesmembraNome.szNomeCompleto, " ");
    strcat(tDesmembraNome.szNomeCompleto, csobrenome);
    oSafePointer.DesmembraNome(&tDesmembraNome);

	if( ( strlennull( clogin ) > 9 ) || ( strlennull( clogin ) < 6 ) )
	{
		setStatusCode("08W1001", "Login não pode ter menos que 6 caracteres nem ser maior que 9 caracteres." );
		ULOG_END("implCadUsrAD::Execute()");	
		return;
	}
	// if( oUsuario.ListLogin( clogin ) )
	// {
		// setStatusCode("08W0001", "Login já existe" );
		// ULOG_END("implCadUsrAD::Execute()");	
		// return;
	// }
	if( !oUsuario.ListLogin( cloginChefe ) )
	{
        if ( strcmp(cidPerfilConsultorAtdAtual,"VIVO") && strcmp(cidPerfilConsultorAtdAtual,"CR"))
        {
		    // ETD MeuCliente - setStatusCode("08W0002", "Login do chefe não existe" );
		    setStatusCode("08W0002", "Login Superior Imediato é Obrigatório" );
		    ULOG_END("implCadUsrAD::Execute()");
		    return;
        }
	}
	if( strlennull( cidUFOperadora ) <= 0)
	{
		setStatusCode("08E0001", "idUFOperadora está nulo" );
		ULOG_END("implCadUsrAD::Execute()");
		return;
	}
	
	if( strlennull( cidUsuario ) > 0 )
	{
		//Tem usuário, então verifica se ja eh usuario
		if( oUsuario.ListId( cidUsuario ) > 0 )
		{
			setStatusCode("08W0003", "Esta pessoa já é um usuário" );
			ULOG_END("implCadUsrAD::Execute()");
			return;
		}
	}

	time(&tTimeNow);
	ptTime=localtime(&tTimeNow);
	memset( cDataAtual, 0, sizeof( cDataAtual ) );
	sprintf(cDataAtual, "%02d/%02d/%04d", ptTime->tm_mday
	                                    , ptTime->tm_mon+1
	                                    , ptTime->tm_year+1900 );
	
	//Zera variareis
	memset( &stDocumento, 0, sizeof( stDocumento ) );
	memset( cAuxidUsuario, 0, sizeof( cAuxidUsuario ) );
	memset( cMens, 0, sizeof( cMens ) );
	strcpy( cMens , "Operação realizada com sucesso" );

    char * fromWS;
    char cIndisponivelWF[3];
    memset(cIndisponivelWF,0x0,sizeof(cIndisponivelWF));
    
	for( int x = 0;; x++ )
	{
		node = walkDOM( dnode, "DocumentoSimpVO", x );
		if( node == NULL )
		{
			stDocumento.iCont = x;
			break;
		}
        fromWS = oSafePointer.getTag( node ,"fromWS", 0 );
        if ( fromWS[0] == '1' )
        {
            ULOG( "*** Requisicao WS ***" );
            strcpy( cIndisponivelWF,"S" );
            
            stDocumento.stDoc[x].cidDocumento = oSafePointer.getTag( node ,"idDocumento", 0 );
            stDocumento.stDoc[x].cnrDocumento = oSafePointer.getTag( node ,"dsDocumento", 0 );
            stDocumento.stDoc[x].cidTipoDocumento = oSafePointer.getTag( node ,"idTipoDocumento", 0 );
            stDocumento.stDoc[x].csgTipoDocumento = oSafePointer.getTag( node ,"sgTipoDocumento", 0 );
            stDocumento.stDoc[x].cidUF = oSafePointer.getTag( node ,"idUF", 0 );
            stDocumento.stDoc[x].cidPais = oSafePointer.getTag( node ,"idPais", 0 );

        }
        else
        {
            ULOG( "*** Requisicao FO ***" );
            strcpy( cIndisponivelWF,"N" );

            stDocumento.stDoc[x].cidDocumento = oSafePointer.getTag( node ,"idDocumento", 0 );
            stDocumento.stDoc[x].cnrDocumento = oSafePointer.getTag( node ,"dsDocumento", 0 );
            stDocumento.stDoc[x].cidTipoDocumento = oSafePointer.getTag( node ,"ns1:idTipoDocumento", 0 );
            stDocumento.stDoc[x].csgTipoDocumento = oSafePointer.getTag( node ,"ns1:sgTipoDocumento", 0 );
            stDocumento.stDoc[x].cidUF = oSafePointer.getTag( node ,"ns1:idUF", 0 );
            stDocumento.stDoc[x].cidPais = oSafePointer.getTag( node ,"ns1:idPais", 0 );
        }
		oUsuario.RemoveEspecial( stDocumento.stDoc[x].cnrDocumento, stDocumento.stDoc[x].cnrDocumento );
        
		if( strlennull( stDocumento.stDoc[x].cidTipoDocumento ) <= 0 )
		{
			stDocumento.iCont = x;
			break;
		}
        
		//if( oUsuario.ExisteDocumento( cidUsuario,
									  // stDocumento.stDoc[x].cnrDocumento, 
									  // stDocumento.stDoc[x].cidTipoDocumento ) )
		// {
			// memset( cMens, 0, sizeof( cMens ) );
			// sprintf( cMens , "O %s de número %s já está cadastrado para outro usuário do sistema.", stDocumento.stDoc[x].csgTipoDocumento, stDocumento.stDoc[x].cnrDocumento );
			// setStatusCode("08W0004", cMens );
			// ULOG_END("implCadUsrAD::Execute()");
			// return;
		// }
        retorno = oUsuario.ValidaUsuario( stDocumento.stDoc[x].cnrDocumento , 
                                          clogin , 
                                          tDesmembraNome.szNomePrimeiro , 
                                          tDesmembraNome.szNomeMeio , 
                                          tDesmembraNome.szNomeFim , 
                                          stDocumento.stDoc[x].cidUF ,
                                          cloginCti ,
                                          cIndisponivelWF ,
                                          cemail ,
                                          cdsTelefone ,
                                          cidDDD ,
                                          cinConsultor ,
                                          cidPerfilConsultorAtdAtual ,
                                          cidFornecedorConsultorAtdAtual ,
                                          cidSiteConsultorAtdAtual ,
                                          cdsLoginRoteamento ,
                                          cloginChefe ,
                                          cAuxidUsuario );
	}//for( int x = 0;; x++ )
    
    if ( retorno == 11 )   // Cenario 1 - Usuario jah existe, apenas foi atualizado
		{
        xml_g->createTag("UsuarioLDAPVO");
        xml_g->addProp("xmlns", "usuario.fo.vivo.com.br/vo" ); 
        xml_g->addItem( "idUsuario", cAuxidUsuario );
        xml_g->addItem( "login", clogin );
        xml_g->addItem( "idUF", cidUF );
        xml_g->addItem( "nome", cnome );
        xml_g->addItem( "sobrenome", csobrenome );
        xml_g->closeTag();
        setStatusCode("08I0006", "LOGIN ja existe na base" );
    } else if ( retorno == 15 )   // Cenario 3
    {
        //  FORMATAR o RETORNO 
        // - VO_CDERRO: “33”
        // - VO_DSERRO: “CPF ou Login Ativo na Base”
        // - STATUS: NOK
        xml_g->createTag("UsuarioLDAPVO");
        xml_g->addProp("xmlns", "usuario.fo.vivo.com.br/vo" ); 
        xml_g->addItem( "idUsuario", cAuxidUsuario );
        xml_g->addItem( "login", clogin );
        xml_g->addItem( "idUF", cidUF );
        xml_g->addItem( "nome", cnome );
        xml_g->addItem( "sobrenome", csobrenome );
        xml_g->closeTag();
        setStatusCode("08W0033", "CPF ou Login Ativo na Base" );
    } else if ( retorno == 30 )   // Cenario 5
    {
        //  FORMATAR o RETORNO 
        // - VO_CDERRO: “33”
        // - VO_DSERRO: “CPF ou Login Ativo na Base”
        // - STATUS: NOK
        xml_g->createTag("UsuarioLDAPVO");
        xml_g->addProp("xmlns", "usuario.fo.vivo.com.br/vo" ); 
        xml_g->addItem( "idUsuario", cAuxidUsuario );
        xml_g->addItem( "login", clogin );
        xml_g->addItem( "idUF", cidUF );
        xml_g->addItem( "nome", cnome );
        xml_g->addItem( "sobrenome", csobrenome );
        xml_g->closeTag();
        setStatusCode("08W0033", "CPF ou Login Ativo na Base" );
    } else if ( retorno == 40 )   // Cenario 7
    {
        //  FORMATAR o RETORNO 
        // - VO_CDERRO: “34”
        // - VO_DSERRO: “CPF e LOGIN estão cadastrados para IDPESSOAs diferentes”
        // - STATUS: NOK
        xml_g->createTag("UsuarioLDAPVO");
        xml_g->addProp("xmlns", "usuario.fo.vivo.com.br/vo" ); 
        xml_g->addItem( "idUsuario", cAuxidUsuario );
        xml_g->addItem( "login", clogin );
        xml_g->addItem( "idUF", cidUF );
        xml_g->addItem( "nome", cnome );
        xml_g->addItem( "sobrenome", csobrenome );
        xml_g->closeTag();
        setStatusCode("08W0034", "CPF e LOGIN estão cadastrados para IDPESSOAs diferentes" );
		}
    else
    {
	//Verifica se tem algum documento no XML
	if( stDocumento.iCont > 0 )
	{
        ULOG( ">>> Existe documento a inserir..." );

            //Se nao existe um id, entao insere em pessoa
		if( strlennull( cidUsuario ) <= 0 )
		{
			//Nao tem idUsuario, entao tem que inserir em pessoa, documento, relacionar e depois em usuario
            
            ULOG( ">>> Vai chamar InsertPessoa..." );
            
			if( oUsuario.InsertPessoa( cidUF
									  ,tDesmembraNome.szNomePrimeiro
									  ,tDesmembraNome.szNomeMeio
									  ,tDesmembraNome.szNomeFim
									  ,cidUser ) )
			{
				strcpy( cAuxidUsuario, oUsuario.Registro()->cidUsuario );
			}//if( oUsuario.InsertPessoa ... )
			else
			{
				setStatusCode("08E0002", "Não foi possível inserir a pessoa" );
			}
		}//if( strlennull( cidUsuario ) <= 0 )
		else
		{
			//Nao existindo o usuario, realiza a copia
			if( oUsuario.CopiaPessoa( cidUsuario, cidUser ) > 0 )
				strcpy( cAuxidUsuario, oUsuario.Registro()->cidUsuario );
			else
			{
				setStatusCode("08E0003", oUsuario.GetErro() );
			}
		}//else if( strlennull( cidUsuario ) > 0 )

		iAux = 0;
		if( strlennull( cAuxidUsuario ) > 0 )
		{
			//Insere todos os documentos de uma pessoa
			for( int x = 0;x<stDocumento.iCont; x++ )
			{
				//Verifica se o documento ja existe, se existe nao insere
				if( oDocumento.ListDocIdTipo( stDocumento.stDoc[x].cnrDocumento, 
											  stDocumento.stDoc[x].cidTipoDocumento ) <= 0 )
				{
					//Insere o documento
					if( strcmp( stDocumento.stDoc[x].csgTipoDocumento, "CPF" ) == 0 )
					{
						memset( ccdCpfCnpjBase, 0, sizeof( ccdCpfCnpjBase ) );
						memset( ccdCpfCnpjControle, 0, sizeof( ccdCpfCnpjControle ) );
						strcpy( ccdCpfCnpjControle, &stDocumento.stDoc[x].cnrDocumento[strlen(stDocumento.stDoc[x].cnrDocumento)-2] );
						strncpy( ccdCpfCnpjBase, &stDocumento.stDoc[x].cnrDocumento[0], strlen(stDocumento.stDoc[x].cnrDocumento)-2 );
						oDocumento.Insert( ccdCpfCnpjBase
										  ,"" //ccdCnpjFilial
										  ,ccdCpfCnpjControle
										  ,stDocumento.stDoc[x].cnrDocumento
										  ,"" //csgOrgaoExpedidor
										  ,cDataAtual
										  ,stDocumento.stDoc[x].cidPais
										  ,stDocumento.stDoc[x].cidUF
										  ,stDocumento.stDoc[x].cidTipoDocumento
										  ,cidUser );
					}
					else
					{
						oDocumento.Insert( "" //ccdCpfCnpjBase
										  ,"" //ccdCnpjFilial
										  ,"" //ccdCpfCnpjControle
										  ,stDocumento.stDoc[x].cnrDocumento
										  ,"" //csgOrgaoExpedidor
										  ,cDataAtual
										  ,stDocumento.stDoc[x].cidPais
										  ,stDocumento.stDoc[x].cidUF
										  ,stDocumento.stDoc[x].cidTipoDocumento
										  ,cidUser );
					}
				}
				//Relaciona com pessoa
				if( oPessoaDocumento.InsertId( cAuxidUsuario
											  ,oDocumento.Registro()->cidDocumento
											  ,cidUser ) <= 0 )
				{
					memset( cAuxidUsuario, 0, sizeof( cAuxidUsuario ) );
					setStatusCode("08E0004", "Erro ao associar um documento a uma pessoa" );
					break;
				}
			}//for( int x = 0;x<stDocumento.iCont; x++ )

			if( strlennull( cAuxidUsuario ) > 0 )
			{

            if ( strcmp(cidFornecedorConsultorAtdAtual,"NAO_CLASSIFICADO")==0 )
            {
               cidFornecedorConsultorAtdAtual = 0;
            }

            if ( strcmp(cidSiteConsultorAtdAtual,"NAO_CLASSIFICADO")==0 )
            {
               cidSiteConsultorAtdAtual = 0;
            }

               //Eh soh pessoa, tem que inserir como usuario
               if( oUsuario.InsertUsuario(  cAuxidUsuario
                                    ,cemail
                                    ,cidDDD
                                    ,cdsTelefone
                                    ,clogin
                                    ,cloginCti
                                    ,cidStatusAtual
                                    ,cidMotivo
                                    ,cloginChefe
                                    ,cIndisponivelWF
                                    ,cinConsultor
                                    ,cidUFOperadora
                                    ,cidUser
                                    ,cidPerfilConsultorAtdAtual
                                    ,cidFornecedorConsultorAtdAtual
                                    ,cidSiteConsultorAtdAtual
                                    ,cdsLoginRoteamento
                                    ,tDesmembraNome.szNomePrimeiro
                                    ,tDesmembraNome.szNomeMeio
                                    ,tDesmembraNome.szNomeFim ) )
               {
                  xml_g->createTag("UsuarioLDAPVO");
                  xml_g->addProp("xmlns", "usuario.fo.vivo.com.br/vo" ); 
                  xml_g->addItem( "idUsuario", cAuxidUsuario );
                  xml_g->addItem( "login", clogin );
                  xml_g->addItem( "idUF", cidUF );
                  xml_g->addItem( "nome", cnome );
                  xml_g->addItem( "sobrenome", csobrenome );
                  xml_g->closeTag();
                  setStatusCode("08I0000", cMens );
               }
               else
               {
                  setStatusCode("08W0005", oUsuario.GetErro() );
                       }   //if( oUsuario.InsertUsuario ... )
                    }   //if( strlennull( cAuxidUsuario ) > 0 )
                }   //if( strlennull( cidUsuario ) > 0 )
        }   // if( stDocumento.iCont > 0 )
      else//else if( stDocumento.iCont > 0 )
      {
         strcpy( cMens , "USUARIO não pode ser cadastrado sem um documento" );
         setStatusCode("08W0006", cMens );
      }//if( stDocumento.iCont > 0 )
    }

   ULOG_END("implCadUsrAD::Execute()");
}
