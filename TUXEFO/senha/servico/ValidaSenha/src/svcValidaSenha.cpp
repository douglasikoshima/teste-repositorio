/* $Id: svcValidaSenha.cpp,v 1.1.2.4.6.1 2012/07/30 17:36:31 a5114878 Exp $ */

#include <tuxfw.h>
#include "../include/ValidaSenha.h"

#define PF		1
#define PJ		2
#define USUARIO 1
#define CLIENTE 2
#define NAO_ENCONTRADO -1 

DECLARE_TUXEDO_SERVICE(ValidaSenha);

void string_trim(string& strValue);

void implValidaSenha::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implValidaSenha::Execute()");
   
	char telefone[11];
	char senha[256];
    string strValidarSenhaService;
	
	strValidarSenhaService = walkTree( dnode, "validarSenhaService", 0 );
	
	ULOG("Identificado tag <validarSenhaService> com conteudo[%s] \n", (char*)strValidarSenhaService.c_str());
	
	if( strValidarSenhaService.compare("1") == 0 )
	{
		ULOG("Identificado tag <validarSenhaService>\n");
		
		xml_g->createTag("ValidaSenha");
		
		string strDDD;
		string strNumTelefone;
		string strSenha;
		string strTelefoneFull;
		string strIdCanal;
		string strIdTerminal;
		
		strDDD = walkTree( dnode, "cdDDD", 0 );
		strNumTelefone = walkTree( dnode, "cdNumTelefone", 0 );
		strSenha = walkTree( dnode, "cdSenha", 0 );
		strTelefoneFull = strDDD + strNumTelefone;
		strIdCanal = walkTree( dnode, "idCanal", 0 );
		strIdTerminal = walkTree( dnode, "idTerminal", 0 );
	
		ULOG("Valores de entrada  cdDDD[%s], cdNumTelefone [%s], senha[%s], idCanal [%s] e idTerminal[%s] \n",
		(char*)strDDD.c_str(), (char*)strNumTelefone.c_str(), (char*)strSenha.c_str(), (char*)strIdCanal.c_str(), (char*)strIdTerminal.c_str());
	
		if (strDDD.length() <= 0 || strNumTelefone.length() <= 0 ||strSenha.length() <= 0 ||strIdCanal.length() <= 0 ||strIdTerminal.length() <= 0) 
		{
			// DADOS DE ENTRADA INVALIDOS 05
			ULOG("Dados de entrada inválidos. \n");
			setStatusCode("07W0005","Dados de entrada inválidos.");
			xml_g->addItem("statusCode","07W0005");
			xml_g->addItem("statusText","Dados de entrada inválidos.");
		}
		else
		{
			int  idCanal				= atoi(strIdCanal.c_str());
			int  idPessoaUsuario		= atoi(getUser());
			int  iIdTerminal			= atoi(strIdTerminal.c_str());
			int  resultado				= 0;
			char titulariadade[2] 		= " ";
			int  qtTentativaErro =		0;
			ValidaSenhaC vS;
			
			vS.validar((char *)strTelefoneFull.c_str(), (char *)strSenha.c_str(), &resultado, titulariadade, idCanal, idPessoaUsuario, iIdTerminal);

			if (resultado == ERR_SENHA_NAO_CADASTRADA || resultado == ERR_SENHA_NAO_ENCONTRADA || resultado == ERR_LINHA_NAO_ENCONTRADA) 
			{
			// Não tem usuario cadastrado ou tem usuario mas a senha nao está cadastrada, consultar na tabela preativa
				ULOG("resultado resultado == ERR_SENHA_NAO_CADASTRADA || resultado == ERR_SENHA_NAO_ENCONTRADA || resultado == ERR_LINHA_NAO_ENCONTRADA \n");
				vS.validarSenhaSemUsuario((char *)(strTelefoneFull.c_str()), senha, &resultado);
			}
			
			
			if (resultado == ERR_LINHA_NAO_ENCONTRADA)
			{
				// LINHA NAO EXISTE 01
				ULOG("resultado ERR_LINHA_NAO_ENCONTRADA \n");
				setStatusCode("07W0001","Linha não encontrada.");
				xml_g->addItem("statusCode","07W0001");
				xml_g->addItem("statusText","Linha não encontrada.");
			}
			else if (resultado == ERR_SENHA_REINICIALIZADA)
			{
				// SENHA PRECISA SER REINICIALIZADA 02
				ULOG("resultado ERR_SENHA_REINICIALIZADA \n");
				setStatusCode("07W0002","Senha reinicializada.");
				xml_g->addItem("statusCode","07W0002");
				xml_g->addItem("statusText","Senha reinicializada.");
			}
			else if (resultado == ERR_SENHA_INVALIDA)
			{
				ULOG("resultado ERR_SENHA_INVALIDA \n");
				// SENHA INVALIDA 04
				qtTentativaErro = vS.verificarTentativa((char *)(strTelefoneFull.c_str()), senha);
				
				if (qtTentativaErro == 1)
				{
					setStatusCode("07W0004","Senha inválida. 1a. Tentativa");
					xml_g->addItem("statusCode","07W0004");
					xml_g->addItem("statusText","Senha inválida. 1a. Tentativa");
				}
				else if (qtTentativaErro == 2)
				{
					setStatusCode("07W0004","Senha inválida. 2a. Tentativa");
					xml_g->addItem("statusCode","07W0004");
					xml_g->addItem("statusText","Senha inválida. 2a. Tentativa");
					
				}
				else if (qtTentativaErro == 3)
				{
					setStatusCode("07W0004","Senha inválida. 3a. Tentativa");
					xml_g->addItem("statusCode","07W0004");
					xml_g->addItem("statusText","Senha inválida. 3a. Tentativa");
					
				}
				else if (qtTentativaErro == 4)
				{
					setStatusCode("07W0004","Senha inválida. 4a. Tentativa");
					xml_g->addItem("statusCode","07W0004");
					xml_g->addItem("statusText","Senha inválida. 4a. Tentativa");
					
				}
				else if (qtTentativaErro == 5)
				{
					setStatusCode("07W0011","Usuário Bloqueado.");
					xml_g->addItem("statusCode","07W0011");
					xml_g->addItem("statusText","Usuário Bloqueado.");
				}
				else
				{
					setStatusCode("07W0004","Senha inválida.");
					xml_g->addItem("statusCode","07W0004");
					xml_g->addItem("statusText","Senha inválida.");
					
				}
			}			
			else if (resultado == ERR_SENHA_NAO_CADASTRADA)
			{
				ULOG("resultado ERR_SENHA_NAO_CADASTRADA \n");
				setStatusCode("07W0006","Linha não possui Senha.");
				xml_g->addItem("statusCode","07W0006");
				xml_g->addItem("statusText","Linha não possui Senha.");
				
			}
			else if (resultado == ERR_SENHA_EXPIRADA)
			{
				ULOG("resultado ERR_SENHA_EXPIRADA \n");
				setStatusCode("07W0010","Senha expirada.");
				xml_g->addItem("statusCode","07W0010");
				xml_g->addItem("statusText","Senha expirada.");
			}
			else if (resultado == ERR_USUARIO_BLOQUEADO)
			{
				ULOG("resultado ERR_USUARIO_BLOQUEADO \n");
				setStatusCode("07W0011","Usuário Bloqueado.");
				xml_g->addItem("statusCode","07W0011");
				xml_g->addItem("statusText","Usuário Bloqueado.");
			}			
			else if (resultado == ERR_SENHA_NAO_ENCONTRADA)
			{
				ULOG("resultado ERR_SENHA_NAO_ENCONTRADA \n");
				setStatusCode("07W0012","Senha não encontrada.");
				xml_g->addItem("statusCode","07W0012");
				xml_g->addItem("statusText","Senha não encontrada.");
			}
			else if (resultado > 0) 
			{
				ULOG("resultado > 0 \n");
				int iDDD = atoi(strDDD.c_str());	
				int iNumTelefone = atoi(strNumTelefone.c_str()); 
				int iTipoLinha = 0;
				int iReturn;
				//iTipoLinha = vS.consultarTipoLinha(iDDD,iNumTelefone);
				iReturn = vS.recuperarInformcaoesCadastro(strNumTelefone, strDDD);
				
				switch(iReturn)
				{
					case 0:
					{
						// SUCESSO 00
						
						ULOG("Acesso Autorizado. \n");
						setStatusCode("07I0000","Acesso Autorizado.");
						xml_g->addItem("statusCode","07I0000");
						xml_g->addItem("statusText","Acesso Autorizado.");
						
						
						xml_g->addItem("titularidadeSenha",titulariadade);
						if (resultado == 2)
							xml_g->addItem("alteraSenha","S");
						
						string_trim(vS.m_strPrimeiroNome);
						string_trim(vS.m_iTipoLinha);
						string_trim(vS.m_strNome);
						string_trim(vS.m_strEmail);
						string_trim(vS.m_strSexo);
						string_trim(vS.m_strDtNascimento);
						
						xml_g->addItem("primeiroNome",(char *)vS.m_strPrimeiroNome.c_str());
						xml_g->addItem("tipoLinha",(char *)vS.m_iTipoLinha.c_str());
						xml_g->addItem("nome",(char *)vS.m_strNome.c_str());
						xml_g->addItem("e_mail",(char *)vS.m_strEmail.c_str());
						xml_g->addItem("sexo",(char *)vS.m_strSexo.c_str());
						xml_g->addItem("dtNasc",(char *)vS.m_strDtNascimento.c_str());
						
						
						
						break;
					}
					case -1:
					{
					    // SENHA NAO ESTA COM STATUS ATIVO 03
						ULOG("Senha não esta com status ativo \n");
						setStatusCode("07W0003","Senha não esta com status ativo.");
						xml_g->addItem("statusCode","07W0003");
						xml_g->addItem("statusText","Senha não esta com status ativo.");
						
						break;
					}
					default:
						break;
				}
			} //if
		}// else
		
		xml_g->closeTag();
	}
	else
	{
		ULOG("Não foi Identificada a tag <validarSenhaService>\n");
		
		strcpy(telefone, walkTree( dnode, "telefone", 0 ));
		strcpy(senha,    walkTree( dnode, "cdSenha", 0 ));


		int  idCanal				= atoi(walkTree( dnode, "idCanal", 0 ));
		int  idPessoaUsuario		= atoi(getUser());
		int  iIdTerminal			= atoi(walkTree(dnode, "idTerminal", 0));

		int  resultado     = 0;
		char titulariadade[2] = " ";
		int  qtTentativaErro = 0;
		char c_area[2] ="";
		char c_linha[16]="";
		int	 iNrLinha = 0;
		int	 iCdAreaRegistro = 0;
		int	 iTipoPessoaCliente = 0;
		int  iTipoPessoaUsuario = 0;
		int	 iTipoLinha = 0;	
		ValidaSenhaC vS;
		
		
		sprintf( c_area, "%.2s", telefone );
		sprintf( c_linha, "%s", (char*)&telefone[2] );
		
		iNrLinha = atoi(c_linha);	
		iCdAreaRegistro = atoi(c_area);	
		
		ULOG("Valores de entrada  iCdAreaRegistro [%d], iNrLinha [%d], cdSenha[%s], idCanal [%d] e idTerminal[%d] \n",
		iCdAreaRegistro, iNrLinha, senha, idCanal, iIdTerminal);

		
		vS.validar(telefone, senha, &resultado, titulariadade, idCanal, idPessoaUsuario, iIdTerminal);

		if (resultado == -100 || resultado == 0 || resultado == -1) // Não tem usuario cadastrado ou tem usuario mas a senha nao está cadastrada, consultar na tabela preativa
			vS.validarSenhaSemUsuario(telefone, senha, &resultado);

		if (resultado == -4)
		{
			setStatusCode("07W0010","Senha expirada.");
		}

		if (resultado == 0)
		{
			setStatusCode("07W0000","Senha não encontrada.");
		}
		else if (resultado == -1)
		{
			setStatusCode("07W0001","Linha não encontrada.");
		}
		else if (resultado == -2)
		{
			qtTentativaErro = vS.verificarTentativa(telefone, senha);

			if (qtTentativaErro == 1)
				setStatusCode("07W0004","Senha inválida. 1a. Tentativa");
			else if (qtTentativaErro == 2)
				setStatusCode("07W0005","Senha inválida. 2a. Tentativa");
			else if (qtTentativaErro == 3)
				setStatusCode("07W0005","Senha inválida. 3a. Tentativa");
			else if (qtTentativaErro == 4)
				setStatusCode("07W0005","Senha inválida. 4a. Tentativa");
			else if (qtTentativaErro == 5)
				setStatusCode("07W0009","Usuário Bloqueado.");
			else
				setStatusCode("07W0002","Senha inválida.");
		}
		else if (resultado == -3)
		{
			setStatusCode("07W0003","Usuário Bloqueado.");
		}
		else if (resultado == -100)
		{
			setStatusCode("07W0006","Senha não cadastrada.");
		}
		else if (resultado == -101)
		{
			setStatusCode("07W0007","Senha reinicializada.");
		}
		else if (resultado > 0) 
		{
			setStatusCode("07I0000","Acesso Autorizado.");

			xml_g->createTag("ValidaSenha");
			xml_g->addItem("titularidadeSenha",titulariadade);
			
			if (resultado == 2)
				xml_g->addItem("alteraSenha","S");
		 
			xml_g->closeTag();
		}
	}

   ULOG_END("implValidaSenha::Execute()");
}

void string_trim(string& strValue)
{
  string::size_type posBegin = strValue.find_first_not_of(' ');
  string::size_type posEnd = strValue.find_last_not_of(' ');
  
  string::size_type pInit;
  string::size_type pEnd;

  if( posBegin == string::npos )
	pInit = 0;
  else
	pInit = posBegin;


  if( posEnd == string::npos )
	pEnd = strValue.length() - 1;
  else
	pEnd = posEnd - posBegin + 1;
	  
  strValue = strValue.substr(pInit,pEnd);

}