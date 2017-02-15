#define IMGCONTACPP

#include<tuxfw.h>
#include "../../../negocio/InfoConta/include/ImagemConta.h"

DECLARE_TUXEDO_SERVICE(SETIMAGEM);


void implSETIMAGEM::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
	tuxfw_getlogger()->debug("SETIMAGEM");
	ImagemConta imagemConta;
	int processado = 0;
	bool retorno = false;
	char*dtFimCiclo = walkTree(dnode, "dtFimCiclo", 0);
	char*idConta = walkTree(dnode, "idConta", 0);
	char*sgTipoImagem = walkTree(dnode, "sgTipoImagem", 0);
	char*nmImagem = walkTree(dnode, "nmImagem", 0);
	char*idTipoImagem = walkTree(dnode, "idTipoImagem", 0);
	char*inXml = walkTree(dnode, "inXml", 0);
	char*cProcessado = walkTree(dnode, "processado", 0);
	char*nmPathDetalhada = walkTree(dnode, "nmPathDetalhada",0);
	char*nmPathResumida = walkTree(dnode, "nmPathResumida",0);
	char*nmPathBoleto = walkTree(dnode, "nmPathBoleto",0);
	char*remover = walkTree(dnode, "remover", 0);

	stImagemConta stDados;
	memset(&stDados,0,sizeof(stDados));

	strcpy(stDados.dtFimCiclo,dtFimCiclo);
	strcpy(stDados.idConta,idConta);
	strcpy(stDados.sgTipoImagem,sgTipoImagem);
	strcpy(stDados.nmImagem,nmImagem);
	strcpy(stDados.idTipoImagem,idTipoImagem);
	stDados.inXml = atoi(inXml);
	strcpy(stDados.nmPathDetalhada,nmPathDetalhada);
	strcpy(stDados.nmPathResumida,nmPathResumida);
	strcpy(stDados.nmPathBoleto,nmPathBoleto);
		
	try
	{
		// para remover a imagem
		if(remover != NULL && strcmp(remover,"1") == 0)
		{
			retorno = imagemConta.removerRegistro(stDados);
		}
		else
		{
			processado = atoi(cProcessado);
			retorno = imagemConta.gravarRegistro(stDados,processado);
		}
	}
	catch(TuxBasicOraException tboe)
	{
		tuxfw_getlogger()->debug("erro de oracle ao salvar dados de imagem");
	}

	// limpar variaveis
	XMLString::release(&dtFimCiclo);
	XMLString::release(&idConta);
	XMLString::release(&sgTipoImagem);
	XMLString::release(&nmImagem);
	XMLString::release(&idTipoImagem);
	XMLString::release(&inXml);
	XMLString::release(&cProcessado);
	XMLString::release(&nmPathDetalhada);
	XMLString::release(&nmPathResumida);
	XMLString::release(&nmPathBoleto);
	
	if(retorno)
		setStatusCode("00I0000","Sucesso");
	else
		setStatusCode("00W0001","Erro ao gravar dados de imagem");
}