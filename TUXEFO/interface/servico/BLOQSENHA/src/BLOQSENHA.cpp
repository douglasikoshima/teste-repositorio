#include <tuxfw.h>
#include "../../../negocio/commons/include/CSafePointer.h"
#include "CSenha.h"
#include "../../../negocio/commons/include/vectorlist.h"

DECLARE_TUXEDO_SERVICE(BLOQSENHA);

void implBLOQSENHA::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	CSafePointer oSafePointer;
	CSenha oSenha;
	char *numTelefone = NULL;
	char *cdDDD = NULL;
	numTelefone = oSafePointer.getTag(dnode,"numTelefone",0);
	cdDDD = oSafePointer.getTag(dnode,"cdDDD",0);
	int retorno = oSenha.execute(numTelefone,cdDDD);
	
	if(retorno > 0)
	{
		setStatusCode("00I0000","Sucesso");
	}
	else
	if(retorno == -9)
	{
		setStatusCode("00W0002","Conta não Cadastrada");
	}
	else
	if(retorno == -10)
	{
		setStatusCode("00W0004","Senha Bloqueada");
	}
	else
	if(retorno == -11)
	{
		setStatusCode("00W0005","Conta sem senha");
	}
	else
	if(retorno == -1 || retorno == -2)
	{
		setStatusCode("00W0003","Parâmetro(s) de entrada inválido(s)");
	}
	else
	{
		setStatusCode("00W0001","Problemas ao executar operação");
	}
	return;
}
