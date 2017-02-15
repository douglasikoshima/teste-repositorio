/*****************************************************************************
 *
 * Modulo:    UsrEditar
 * Arquivo:   UsrEditar.cpp
 * Proposito: servico do framework tuxedo que aciona servico pro*c
 * Historico:
 * Data        Autor                 Descricao
 * ----------  --------------------  -----------------------------------------
 * 17/06/2004  C_RECOliveira         Criacao
 * 17/06/2004  C_EDJMartins          Criacao
 *
 ****************************************************************************/
/**************************************************************************
 * Notas:
 *
 **************************************************************************/

//Definicao Global
#define UsrEditarCPP

//Header de Usr de Infra-Estrutura
#include <time.h>
#include "../../../negocio/acessoCmm/include/CUsr.h"
#include "../../../negocio/acessoCmm/include/CPesDoc.h"
#include "../../../negocio/acessoCmm/include/CDoc.h"
#include <tuxfw.h>
#include "../../../negocio/acessoCmm/include/CSafePointer.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(UsrEditar);

/**************************************************************************
 *  Funcao de Negocios:  UsrEditar
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
void implUsrEditar::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	ULOG_START("implUsrEditar::Execute()");
	
	CSafePointer oSafePointer;
	CUsr oUsuario;
	CDocumento oDocumento;
	CPesDoc oPessoaDocumento;
	struct stDOCUMENTO stDocumento;
	char cMens[255+1];
	char ccdCpfCnpjBase[21+1];
	char ccdCpfCnpjControle[21+1];

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
    char* cidPerfilConsultorAtdAtual     = oSafePointer.getTag(dnode ,"idPerfilConsultorAtdAtual"    ,0);
    char* cidFornecedorConsultorAtdAtual = oSafePointer.getTag(dnode ,"idFornecedorConsultorAtdAtual",0);
    char* cidSiteConsultorAtdAtual       = oSafePointer.getTag(dnode ,"idSiteConsultorAtdAtual"      ,0);
	char* cdsLoginRoteamento             = oSafePointer.getTag(dnode ,"dsLoginRoteamento"            ,0);

	if ( strcmp(cidFornecedorConsultorAtdAtual,"NAO_CLASSIFICADO")==0 )
    {
        cidFornecedorConsultorAtdAtual = 0;
    }

	if ( strcmp(cidSiteConsultorAtdAtual,"NAO_CLASSIFICADO")==0 )
    {
        cidSiteConsultorAtdAtual = 0;
    }

	char* cidUser = getUser();
	
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

	if( strlennull( cidUsuario ) <= 0 )
	{
		setStatusCode("08W0001", "idUsuario está nulo" );
		ULOG_END("implUsrEditar::Execute()");
		return;
	}
	if( ( strlennull( clogin ) > 9 ) || ( strlennull( clogin ) < 6 ) )
	{
		setStatusCode("08W1001", "Login não pode ter menos que 6 caracteres nem ser maior que 9 caracteres." );
		ULOG_END("implUsrInserir::Execute()");	
		return;
	}
	if( oUsuario.ListLogin( clogin, cidUsuario ) )
	{
		setStatusCode("08W0001", "Login já existe" );
		ULOG_END("implUsrEditar::Execute()");
		return;
	}
	if( !oUsuario.ListLogin( cloginChefe ) )
	{
        if ( strcmp(cidPerfilConsultorAtdAtual,"VIVO") && strcmp(cidPerfilConsultorAtdAtual,"CR"))
        {
		    // ETD MeuCliente - setStatusCode("08W0002", "Login do chefe não existe" );
		    setStatusCode("08W0002", "Login Superior Imediato é Obrigatório" );
		    ULOG_END("implUsrEditar::Execute()");
		    return;
        }
	}
	if( strlennull( cidUFOperadora ) <= 0)
	{
		setStatusCode("08E0001", "idUFOperadora está nulo" );
		ULOG_END("implUsrEditar::Execute()");
		return;
	}
	//Tem usuário, então verifica se ja eh usuario
	if( oUsuario.ListId( cidUsuario ) <= 0 )
	{
		setStatusCode("08W0003", "Esta pessoa não é um usuário" );
		ULOG_END("implUsrEditar::Execute()");
		return;
	}
	if( !oUsuario.MesmoStatus( cidUsuario, cidStatusAtual ) )
	{
		if( strcmp( cidStatusAtual, "1" ) == 0 )
		{
			if( oUsuario.VerificaGrupo( cidUsuario ) == 0  )
			{
				setStatusCode("08W0006", "Usuário sem grupo não pode ser ativado" );
				ULOG_END("implUsrEditar::Execute()");
				return;
			}
			if( oUsuario.VerificaRegionais( cidUsuario ) == 0  )
			{
				setStatusCode("08W0005", "Usuário sem operadora não pode ser ativado" );
				ULOG_END("implUsrEditar::Execute()");
				return;
			}
		}
	}
    if ( cidPerfilConsultorAtdAtual )
    {
        if ( oUsuario.VerificaRebaixamentoPerfil(cidUsuario,cidPerfilConsultorAtdAtual)==false )
        {
	        setStatusCode("08W0007", "Este usuário possui um ou mais usuários abaixo dele. Perfil não pode ser rebaixado." );
	        ULOG_END("implUsrEditar::Execute()");
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
	memset( cMens, 0, sizeof( cMens ) );
	strcpy( cMens , "Operação realizada com sucesso" );

	for( int x = 0;; x++ )
	{
		node = walkDOM( dnode, "DocumentoSimpVO", x );
		if( node == NULL )
		{
			stDocumento.iCont = x;
			break;
		}

		stDocumento.stDoc[x].cidDocumento = oSafePointer.getTag( node ,"idDocumento", 0 );
		stDocumento.stDoc[x].cnrDocumento = oSafePointer.getTag( node ,"dsDocumento", 0 );
		stDocumento.stDoc[x].cidTipoDocumento = oSafePointer.getTag( node ,"ns1:idTipoDocumento", 0 );
		stDocumento.stDoc[x].csgTipoDocumento = oSafePointer.getTag( node ,"ns1:sgTipoDocumento", 0 );
		stDocumento.stDoc[x].cidUF = oSafePointer.getTag( node ,"ns1:idUF", 0 );
		stDocumento.stDoc[x].cidPais = oSafePointer.getTag( node ,"ns1:idPais", 0 );

		oUsuario.RemoveEspecial( stDocumento.stDoc[x].cnrDocumento, stDocumento.stDoc[x].cnrDocumento );

		if( strlennull( stDocumento.stDoc[x].cidTipoDocumento ) <= 0 )
		{
			stDocumento.iCont = x;
			break;
		}

		if( oUsuario.ExisteDocumento( cidUsuario,
									  stDocumento.stDoc[x].cnrDocumento, 
									  stDocumento.stDoc[x].cidTipoDocumento ) )
		{
			memset( cMens, 0, sizeof( cMens ) );
			sprintf( cMens , "O %s de número %s já está cadastrado para outro usuário do sistema.", stDocumento.stDoc[x].csgTipoDocumento, stDocumento.stDoc[x].cnrDocumento );
			setStatusCode("08W0004", cMens );
			ULOG_END("implUsrEditar::Execute()");
			return;
		}
	}//for( int x = 0;; x++ )
	//Verifica se tem algum documento no XML
	if( stDocumento.iCont > 0 )
	{
		//Nao tem idUsuario, entao tem que inserir em pessoa, documento, relacionar e depois em usuario
		oUsuario.EditPessoa( cidUsuario
			                ,cidUF
							,tDesmembraNome.szNomePrimeiro
							,tDesmembraNome.szNomeMeio
							,tDesmembraNome.szNomeFim
							,cidUser );

		//Remove as associacoes destes usuario com documento
		oPessoaDocumento.RemoveRelacaoComPessoa( cidUsuario );

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
			if( oPessoaDocumento.InsertId( cidUsuario
										  ,oDocumento.Registro()->cidDocumento
										  ,cidUser ) <= 0 )
			{
				setStatusCode("08E0004", "Erro ao associar um documento a uma pessoa" );
				break;
			}
		}//for( int x = 0;x<stDocumento.iCont; x++ )

		//Eh soh pessoa, tem que inserir como usuario
		if( oUsuario.UsrEditar( cidUsuario     
							   ,cemail         
							   ,cidDDD         
							   ,cdsTelefone    
							   ,clogin         
							   ,cloginCti      
							   ,cidStatusAtual 
							   ,cidMotivo      
							   ,cloginChefe    
							   ,cinConsultor
							   ,cidUFOperadora
							   ,cidUser
                               ,cidPerfilConsultorAtdAtual
                               ,cidFornecedorConsultorAtdAtual
                               ,cidSiteConsultorAtdAtual
                               ,cdsLoginRoteamento) == 1 )
		{
			setStatusCode("08I0000", cMens );
		}
		else
		{
			setStatusCode("08W0005", oUsuario.GetErro() );
		}//if( oUsuario.UsrEditar... )
	}
	else//else if( stDocumento.iCont > 0 )
	{
		strcpy( cMens , "USAUARIO não pode ser cadastrado sem um documento" );
		setStatusCode("08W0006", cMens );
	}//if( stDocumento.iCont > 0 )
	
	ULOG_END("implUsrEditar::Execute()");
}
