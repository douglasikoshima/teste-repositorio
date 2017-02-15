/**
 * 
 * @modulo  Commons
 * @usecase Envio de mensagens SMS para o celular do usuário.
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:32 $
 **/ 


#include "../include/cSMSUtil.h"

cSMSUtil::cSMSUtil()
{
	entrada = 0;
	saida   = 0;

	memset(&m_stDados,0,sizeof(m_stDados));
	memset(&m_vlDados,-1,sizeof(m_vlDados));
}

cSMSUtil::cSMSUtil(DOMNode*dnode, XMLGen*xml_g)
{
	entrada = dnode;
	saida   = xml_g;

	carregaDados();
}

cSMSUtil::cSMSUtil(st_SMSUtil *dados,st_vlSMSUtil *status,XMLGen*xml_g)
{
	entrada = 0;
	saida   = xml_g;

	memcpy(&m_stDados,dados,sizeof(m_stDados));
	memcpy(&m_vlDados,status,sizeof(m_vlDados));
}

void cSMSUtil::setDestinatario(char* destinatario)
{
	strcpy(m_stDados.destinatario, destinatario);
	m_vlDados.destinatario = 1;
}

void cSMSUtil::setMensagem(char* mensagem)
{
	strcpy(m_stDados.mensagem, mensagem);
	m_vlDados.mensagem = 1;
}

void cSMSUtil::enviar()
{

	tuxfw_getlogger()->debug("SMSUtil - Vai enviar a mensagem...");

	if (m_vlDados.destinatario == -1 || m_vlDados.mensagem == -1)
	{
		throw new TuxException( "99E99999", "Não foram recebidos dados obrigatórios para envio do SMS.");		
	}

    TuxRemoteService remoteService;
	TuxMessage tm;

	tuxfw_getlogger()->debug("SMSUtil - Gerando XML de envio...");

	try 
	{
		if( strlen(getUser()) > 0 )
			tm.setUser( getUser() );
		else
			tm.setUser( "2" );

        tm.setService("SMSSend");

		// reservamos 16KB de memoria pois a mensagem pode ser grande ou ter limite expandido no futuro
		char strMsgBody[16384]; memset(strMsgBody, 0, 16384);

		sprintf(strMsgBody, "<message>%s</message><recipient>%s</recipient>", m_stDados.mensagem, m_stDados.destinatario);

        tm.setMessageBody( strMsgBody );

        remoteService.setServiceName("SMSSend");
        remoteService.setInputMessage( &tm );

        if ( remoteService.remoteCall() != TUXFWRET_OK )
        {
			tuxfw_getlogger()->debug("SMSUtil - Erro ao chamar TuxRemoteService::remoteCall().");
			saida->createTag("SMSSend");
				saida->addItem("inEnviado", "0");
			saida->closeTag();
			setStatusCode("00E0000", "Erro ao chamar TuxRemoteService::remoteCall().");
        }

//        TuxMessage* outTm = remoteService.getOutputMessage();

//        char *codigoRetorno = outTm->getStatusCode();

		tuxfw_getlogger()->debug("SMSUtil - Mensagem Enviada...");

		saida->createTag("SMSSend");
			saida->addItem("inEnviado", "1");
		saida->closeTag();

		setStatusCode("00I0000", "SMS enviada com sucesso.");
	}
	catch(TuxException* pTux)
	{
		tuxfw_getlogger()->debug("SMSUtil - Ocorreu um erro no envio da mensagem.");

		saida->createTag("SMSSend");
			saida->addItem("inEnviado", "0");
		saida->closeTag();

		setStatusCode(pTux->getCode(),pTux->getMessage());
		delete pTux;
	}
	
}

void cSMSUtil::carregaDados()
{
    char *p;

	// Inicializa as estruturas de dados para armazenar os valores.
	memset(&m_stDados,0,sizeof(m_stDados));
	memset(&m_vlDados,-1,sizeof(m_vlDados));

	tuxfw_getlogger()->debug("SMSUtil - Carregando dados...");
	
	if (p=tx.walkTree( entrada, "destinatario", 0 ),p)
    {
		strcpy(m_stDados.destinatario,p);
		m_vlDados.destinatario = 1;
        XMLString::release(&p);
	}

	if (p=tx.walkTree( entrada, "mensagem", 0 ),p)
    {
		strcpy(m_stDados.mensagem,p);
		m_vlDados.mensagem = 1;
        XMLString::release(&p);
	}
}

