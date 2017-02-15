/* $Id: svcAlteraSenha.cpp,v 1.1.2.7.6.3 2011/09/15 17:22:18 a5116174 Exp $ */

#include <tuxfw.h>
#include <string>
#include "../../AtualizaCadastro/include/stAlteraSenha.h"
#include "../../AtualizaCadastro/include/AtualizaCadastro.h"


using namespace std;

DECLARE_TUXEDO_SERVICE(AlteraSenha);

#define IDHISTORICOALTERASENHA 4
#define IDTIPOSISTEMA 2

void implAlteraSenha::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implAlteraSenha::Execute()"); 

    char *p;

    struct stAlteraSenha m_stDados;

    memset(&m_stDados,0,sizeof(m_stDados));

    string strAlterarSenhaService;
	
	strAlterarSenhaService = walkTree( dnode, "alteraSenhaService", 0 );
	
		if (p=walkTree( dnode, "telefone", 0 ),p) 
		{
			strncpy(m_stDados.telefone, p, 10);
			m_stDados.telefone[10] = '\0';
		 XMLString::release(&p);
		}

		if (p=walkTree( dnode, "cdSenha", 0 ),p) 
		{
			if (!strcmp(p, "null") || !strcmp(p, "NULL"))
			{
				setStatusCode("07E0003","Senha Inválida.");
				return;
			}
			/*
			strncpy(m_stDados.cdSenha, p, 255);
			m_stDados.cdSenha[255] = '\0';
			*/
			strcpy( m_stDados.cdSenha, p );
			
			XMLString::release(&p);
		}

		if (p=walkTree( dnode, "titularidadeSenha", 0 ),p) 
		{
		 strncpy(m_stDados.tipoTitularidade, p, 1);
			 m_stDados.tipoTitularidade[1] = '\0';
		 XMLString::release(&p);
		}

		if (p=walkTree( dnode, "idPessoa", 0 ),p) 
		{
			m_stDados.idPessoa = atoi(p);
		XMLString::release(&p);
		}
		
		if (p=walkTree( dnode, "idCanal", 0 ),p) 
		{
			m_stDados.idCanal = atoi(p);
		XMLString::release(&p);
		}

		 if (p=walkTree( dnode, "idTerminal", 0 ),p) 
		{
			m_stDados.iIdTerminal = atoi(p);
		XMLString::release(&p);
		}
		
		m_stDados.idTipoSistema         = IDTIPOSISTEMA;
		m_stDados.idTipoHistoricoSenha  = IDHISTORICOALTERASENHA;
		// m_stDados.registraHistorico     = 0; // Não registra esse tipo de histórico na gestao de senha, apenas no atendimento.
		m_stDados.registraHistorico     = 1; // OS 1275
		m_stDados.idPessoaUsuario       = atoi(getUser());
		m_stDados.inTrocaSenha          = 0;


		if (p=walkTree( dnode, "obsRegistro", 0 ),p) 
		{
			 strncpy(m_stDados.obsRegistro, p, 255);
			 m_stDados.obsRegistro[255] = '\0';
		 XMLString::release(&p);
		}

		// Update de 'Lembrete Senha' - @Cassio - Set/2004
		if (p=walkTree( dnode, "dsLembreteSenha", 0 ),p) 
		{
			strncpy(m_stDados.dsLembreteSenha, p, 255);
		m_stDados.dsLembreteSenha[255] = '\0';
		XMLString::release(&p);
		}
		else
		{
			m_stDados.dsLembreteSenha[0] = 0;
		m_stDados.dsLembreteSenha[1] = '\0';
		}

		// Indica o tipo da titularidade da senha.
		if (strcmp( m_stDados.tipoTitularidade, "U") == 0)
			m_stDados.titularidadeSenha = 1;
		else
			m_stDados.titularidadeSenha = 2;
		

		AtualizaCadastro aC;

		//ULOG("passou_aqui_1_");
		
		if( strAlterarSenhaService.compare("1") == 0 )
		{
			//ULOG("passou_aqui_2_");
			ULOG("A senha para o telefone [%s]", strAlterarSenhaService);
			if (m_stDados.idPessoa  == 0)
			{
			//ULOG("passou_aqui_3_");
				m_stDados.titularidadeSenha = 2;
				m_stDados.idPessoa = aC.consultarIdPessoa(m_stDados.telefone,  m_stDados.titularidadeSenha);
			}
			if (m_stDados.idPessoa  == 0)
			{
			//ULOG("passou_aqui_4_");
				m_stDados.titularidadeSenha = 1;
				m_stDados.idPessoa = aC.consultarIdPessoa(m_stDados.telefone,  m_stDados.titularidadeSenha);
			}
			
			ULOG("Verificanco se a Senha esta bloqueada");
			// Realiza o desbloqueio dos usuários.
			if( aC.verificaSenhaBloqueada(m_stDados.telefone) == true )
			{
				//ULOG("passou_aqui_5_");
				ULOG("A senha para o telefone [%s] está BLOQUEADA",m_stDados.telefone);
				ULOG("Realizando o desbloqueio...");
				
				aC.desbloqueiaSenhas(m_stDados.telefone, m_stDados.idCanal, m_stDados.idPessoaUsuario, m_stDados.iIdTerminal, m_stDados.obsRegistro);
			}
			else
			{
				//ULOG("passou_aqui_6_");
				ULOG("A senha para o telefone [%s] não está BLOQUEADA",m_stDados.telefone);
			}
		
		}
		else
		{
			//ULOG("passou_aqui_7_");
			if (m_stDados.idPessoa  == 0)
			{
				//ULOG("passou_aqui_8_");
				 m_stDados.idPessoa = aC.consultarIdPessoa(m_stDados.telefone,  m_stDados.titularidadeSenha);
			}
		}
		//ULOG("passou_aqui_9_");
		int retorno = aC.alteraSenha( &m_stDados );

		xml_g->createTag("AlteraSenha");
		
		if ( retorno == 1 )
		{
			setStatusCode("07I0000","A senha foi alterada.");
			xml_g->addItem("statusCode","07I0000");
			xml_g->addItem("statusText","A senha foi alterada.");
		}
		else if (retorno == -2)
		{
			setStatusCode("07E0002","A senha já existe na estrutura.");
			xml_g->addItem("statusCode","07E0002");
			xml_g->addItem("statusText","A senha já existe na estrutura.");
		}
		else if (retorno == -3)
		{
			setStatusCode("07E0003","Senha Inválida.");
			xml_g->addItem("statusCode","07E0003");
			xml_g->addItem("statusText","Senha Inválida.");
		}
		else if (retorno == -11)
		{
			setStatusCode("07W0007","Para garantir a segurança de seus dados, não é permitida a criação de uma nova senha que seja igual às 5 últimas senhas utilizadas. Por favor, escolha outra senha.");
			//setStatusCode("07W0007","Para garantir a segurança de seus dados, não é permitida a criação de uma nova senha que seja igual às 10 últimas senhas utilizadas. Por favor, escolha outra senha.");
			xml_g->addItem("statusCode","07W0007");
			xml_g->addItem("statusText","Para garantir a segurança de seus dados, não é permitida a criação de uma nova senha que seja igual às 5 últimas senhas utilizadas. Por favor, escolha outra senha.");
			//xml_g->addItem("statusText","Para garantir a segurança de seus dados, não é permitida a criação de uma nova senha que seja igual às 10 últimas senhas utilizadas. Por favor, escolha outra senha.");
		}
		else
		{
			setStatusCode("07E0001","A senha não foi alterada.");
			xml_g->addItem("statusCode","07E0001");
			xml_g->addItem("statusText","A senha não foi alterada.");
		}
		xml_g->closeTag();
		
		
	ULOG_END("implAlteraSenha::Execute()"); 


}