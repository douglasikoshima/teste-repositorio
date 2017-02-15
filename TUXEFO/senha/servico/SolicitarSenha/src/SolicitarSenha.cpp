#include <tuxfw.h>
#include "../include/CSolicitarSenha.h"
#include "../../GRAVASENHA/include/GravaSenha.h"


DECLARE_TUXEDO_SERVICE(SOLICITARSENHA);

void implSOLICITARSENHA::Execute(DOMNode*dnode,XMLGen*xml_g)
{
    ULOG_START("implSolicitarSenha::Execute()"); 

	char telefone[11];
	char senha[256];
	char c_area[3] ="";
	char c_linha[16]="";
	char cIdTipoRelacionamento[2];

	strcpy(c_linha, walkTree( dnode, "nrLinha", 0 ));	
	strcpy(c_area, walkTree( dnode, "cdAreaRegistro", 0 ));	
	strcpy(cIdTipoRelacionamento, walkTree( dnode, "idTipoRelacionamento", 0 ));	

	int  idCanal				= atoi(walkTree( dnode, "idCanal", 0 ));
	int  idPessoaUsuario		= atoi(getUser());
	int  iIdTerminal			= atoi(walkTree(dnode, "idTerminal", 0));
	char *titularidade			= walkTree( dnode, "tituralidade", 0 );


	char *operacao              = walkTree( dnode, "operacao", 0 );

	int  resultado     = 0;
	int  qtTentativaErro = 0;

	int	 iNrLinha = 0;
	int	 iCdAreaRegistro = 0;
	char cSenhaGeradaCript[33];
	char cSenhaGeradaCript2[33];
	int  iIdTipoRelacionamento = 0;
	CSolicitarSenha sSenha;	
    CGravaSenha rc(dnode, xml_g, getUser());
	char cSenha[5];		
		
	iNrLinha = atoi(c_linha);	

	iCdAreaRegistro = atoi(c_area);	

	iIdTipoRelacionamento = atoi(cIdTipoRelacionamento);
	

	try
	{
		sSenha.gerarSenha(iCdAreaRegistro, iNrLinha, iIdTipoRelacionamento);
	}
	catch ( ... )
	{
		 setStatusCode("07W0001","A linha não existe na base");
		 return;
	}
	

	strcpy(cSenhaGeradaCript,  sSenha.getSenhaGeradaCript());

	int intRC  = sSenha.consultarLinhaExpirada(iCdAreaRegistro, iNrLinha);

	if (intRC == ERR_LINHA_EXPIRADA )
	{
		 setStatusCode("07E0003", "Esta linha está cancelada há mais de 30 dias.");
		 return;

	}


	rc.consultarIdPessoa(iCdAreaRegistro, iNrLinha);
	
	if (rc.IdPessoa.ToInt()  == 0) /* Não tem usuário*/
	{
		sSenha.GravaSenhaSemUsuario(iCdAreaRegistro, iNrLinha, cSenhaGeradaCript);
		strcpy (cSenha, sSenha.getSenha());
	
	}
	else
	{	
		if (sSenha.verificarSenhaGeradaInvalida(iCdAreaRegistro, iNrLinha, iIdTipoRelacionamento, cSenhaGeradaCript) == true)
		{	
			rc.setSenha(sSenha.getSenhaGeradaAuxiliarCript());
			strcpy (cSenha, sSenha.getSenhaAuxiliar());

		}else
		{		
			rc.setSenha(sSenha.getSenhaGeradaCript());
			strcpy (cSenha, sSenha.getSenha());
		}	
		
		try
		{
			rc.Executar();	
		}
		catch ( ... )
		{
			 setStatusCode("07E0002","A senha não foi gerada.");
			 return;
		}

	}

	try{

		rc.EnviaSMSSenhaGerada(cSenha, idCanal, iCdAreaRegistro, iNrLinha);
	}

	catch ( ... )
	{	}

	if (!strcmp(operacao, "obterSenha"))	
		xml_g->addItem("valorSenha", cSenha);


	setStatusCode("07I0000","Senha gerada com sucesso");

}