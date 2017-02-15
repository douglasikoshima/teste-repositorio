/*****************************************************************************
* Arquivo:   GRPSKLPUT.cpp
* Historico:
* Data        Autor                 Descricao
* ----------  --------------------  -----------------------------------------
* 07/10/2005  C_edmartins           Criacao
*
****************************************************************************/

//Definicao Global
#define GRPSKLPUTCPP

//Header de Srv de Infra-Estrutura
#include <tuxfw.h>
#include "../../../negocio/acessoCmm/include/CSafePointer.h"
#include "../../../negocio/acessoCmm/include/CContatoFolhaUsuario.h"
#include "../../../negocio/acessoCmm/include/CContatoFolhaUsuarioSkill.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(GRPSKLPUT);

void implGRPSKLPUT::Execute(DOMNode*dnXmlIn,XMLGen*XmlSaida)
{
	ULOG_START("implGRPSKLPUT::Execute()");
	
	CSafePointer oSafePointer;
	
	char* pzcinOperacao     = oSafePointer.getTag( dnXmlIn, "inOperacao" );
	char* pzcidGrupo        = oSafePointer.getTag( dnXmlIn, "grupoSelecionado" );
	char* pzcidGrupoSkill   = oSafePointer.getTag( dnXmlIn, "skillSelecionado" );
	char* pzcidUser         = getUser();
	int   inOperacao = atoi(pzcinOperacao);
	
	// Contato
	char* pzcdeletaContatos;
	char* pzccontatosSelecionados;
	CContatoFolhaUsuario oContatoGrupoSkill;
	TVector2 stVector2[100000];
	int insertContato;
	
	// Usuario
	char* pzcdeletaUsuarios;
	char* pzcusuariosSelecionados;
	CContatoFolhaUsuario oUsuarioGrupoSkill;
	TVector stVector[100000];
	int insertUsuario;
	
	int  nTemSkill = strlennull( pzcidGrupoSkill );
	long iCont=0;
	
	memset(&stVector  , 0x00, sizeof(stVector));
	memset(&stVector2 , 0x00, sizeof(stVector2));
	
	if( strlennull( pzcidGrupoSkill ) > 0 )
	{
		if( strlennull( pzcidUser ) > 0 )
		{
			if(inOperacao == 1) //operacao 1 para gravar usuariosSelecionados
			{	
				pzcdeletaUsuarios = oSafePointer.getTag( dnXmlIn, "deleteUsuario" );
				if( strlennull( pzcdeletaUsuarios ) > 0 )														  
				{ 	
					ULOG("Iniciando operacao 1 para insercao de usuarios selecionados.");
					pzcusuariosSelecionados = oSafePointer.getTag( dnXmlIn, "usuariosSelecionados" );
					if( strlennull( pzcusuariosSelecionados ) > 0 )
						insertUsuario = 1;											
					else
						insertUsuario = 0;
					
					// Recupera todos os usuarios
					for(int nUsuarios = 0;;nUsuarios++)
					{
						pzcusuariosSelecionados = oSafePointer.getTag( dnXmlIn, "usuariosSelecionados", nUsuarios );
						strcpy(stVector[iCont].pzcidSkillUsuario, pzcidGrupoSkill);
						strcpy(stVector[iCont].pzcidUser, pzcidUser);
						ULOG("Vetor(%d)(%d)---- iCont(%ld) ---- idSkillUsuario= %s", sizeof(stVector), sizeof(TVector), iCont, stVector[iCont].pzcidSkillUsuario);			                            
						ULOG("Vetor(%d)(%d)---- iCont(%ld) ---- idUser= %s", sizeof(stVector), sizeof(TVector), iCont, stVector[iCont].pzcidUser); 
						if( strlennull( pzcusuariosSelecionados ) <= 0 )
						{
							ULOG("Iniciando insercao de usuarios:");
							oUsuarioGrupoSkill.DeleteInsertUsuarioGrupoSkill(stVector, pzcdeletaUsuarios, insertUsuario);
							memset(&stVector, 0x00, sizeof(stVector));
							iCont=0;
							break;	
						}			                
						ULOG("Popula vetor(%d)(%d)---- iCont(%ld)", sizeof(stVector), sizeof(TVector), iCont);			                            
						strcpy(stVector[iCont].pzcusuariosSelecionados, pzcusuariosSelecionados);
						ULOG("Vetor(%d)(%d)---- iCont(%ld) ---- usuariosSelecionados= %s", sizeof(stVector), sizeof(TVector), iCont, stVector[iCont].pzcusuariosSelecionados);
						iCont++;
						if(iCont >= 100000) 
						{
							ULOG("iCont(%ld)", iCont);
							setStatusCode("14E0001","mais de 100.000 registros inseridos");
						}
						
					}//for(int nUsuarios = 0;;nUsuarios++)
					
					setStatusCode("14I0000","Serviço finalizado com sucesso!");
					
				} //if( strlennull( pzcdeletaUsuarios ) > 0 )
				else
				{
					setStatusCode("14E0001","deleteUsuarios deve conter um valor - 0 ou 1");
				}						
				
			}
			else if(inOperacao == 2) // operacao 2 para gravar contatosSelecionados
			{
				pzcdeletaContatos = oSafePointer.getTag( dnXmlIn, "deleteContato" );
				if( strlennull( pzcdeletaContatos ) > 0 )														  
				{ 																  
					ULOG("Iniciando operacao 2 para insercao de contatos selecionados.");
					pzccontatosSelecionados = oSafePointer.getTag( dnXmlIn, "contatosSelecionados" );
					if( strlennull( pzccontatosSelecionados ) > 0 )
						insertContato = 1;
					else
						insertContato = 0;
					
					// Recupera os contatos
					for(int nContatos = 0;;nContatos++)
					{
						pzccontatosSelecionados = oSafePointer.getTag( dnXmlIn, "contatosSelecionados", nContatos );
						strcpy(stVector2[iCont].pzcidSkillContato, pzcidGrupoSkill);
						strcpy(stVector2[iCont].pzcidUser, pzcidUser);
						ULOG("Vetor(%d)(%d)---- iCont(%ld) ---- idSkillContato= %s", sizeof(stVector2), sizeof(TVector2), iCont, stVector2[iCont].pzcidSkillContato);			                            
						ULOG("Vetor(%d)(%d)---- iCont(%ld) ---- idUser= %s", sizeof(stVector2), sizeof(TVector2), iCont, stVector2[iCont].pzcidUser); 
						if( strlennull( pzccontatosSelecionados ) <= 0 )
						{
							ULOG("Iniciando insercao de contatos:");
							oContatoGrupoSkill.DeleteInsertContatoGrupoSkill(stVector2, pzcdeletaContatos, insertContato);
							memset(&stVector2, 0x00, sizeof(stVector2));
							iCont=0;
							break;
						}			                
						ULOG("Popula vetor(%d)(%d)---- iCont(%ld)", sizeof(stVector2), sizeof(TVector2), iCont);			                            
						strcpy(stVector2[iCont].pzccontatosSelecionados, pzccontatosSelecionados);
						ULOG("Vetor(%d)(%d)---- iCont(%ld) ---- contatosSelecionados= %s", sizeof(stVector2), sizeof(TVector2), iCont, stVector2[iCont].pzccontatosSelecionados);
						iCont++;
						if(iCont >= 100000)
						{
							ULOG("iCont(%ld)", iCont);
							setStatusCode("14E0001","mais de 100.000 registros inseridos");
						}
						
					}//for(int nContatos = 0;;nContatos++)
					
					setStatusCode("14I0000","Serviço finalizado com sucesso!");
					
				}//if( strlennull( pzcdeletaContatos ) > 0 )
				else
				{
					setStatusCode("14E0001","deleteContatos deve conter um valor - 0 ou 1");
				}											
				
			} // inOperacao
			
		}
		else 
		{
			setStatusCode("14E0003","TAG <user> do XMLIN está nula");
		}
		
	}
	else 
	{
		setStatusCode("14E0004","TAG <idSkillUsuario> está nula");
	}

ULOG_END("implGRPSKLPUT::Execute()");
}