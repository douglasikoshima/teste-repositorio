/**
 * 
 * @modulo  Commons
 * @usecase Envio de mensagens SMS para o celular do usuário.
 * @author  Renato Teixeira
 * @version $Revision: 1.2 $
 * @CVS     $Author: drdantas $ - $Date: 2004/09/15 18:55:11 $
 **/ 


#include "../include/cSMSUtil.h"

cSMSUtil::cSMSUtil() {
	memset(&m_stDados,0,sizeof(m_stDados));
	memset(&m_vlDados,-1,sizeof(m_vlDados));
}

cSMSUtil::cSMSUtil(DOMNode*dnode, XMLGen*xml_g) {
	entrada = dnode;
	saida   = xml_g;

	carregaDados();
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


void cSMSUtil::enviar() {

	if (m_vlDados.destinatario == -1 || m_vlDados.mensagem == -1)
	{
		throw new TuxException( "99E99999", "Não foram recebidos dados obrigatórios para envio do SMS.");		
	}

	TuxMessage tm;

	XMLGen gen;

	gen.createTag( "AtendimentoVO" );
		gen.addItem( "recipient" , m_stDados.destinatario );
		gen.addItem( "message" , m_stDados.mensagem );
	gen.closeTag();


	try 
	{
		remoteCall("SMSSend", &gen, &tm);

		saida->createTag("SMSSend");
			saida->addItem("inEnviado", "1");
		saida->closeTag();

		setStatusCode("00I0000", "SMS enviada com sucesso.");
	}
	catch(TuxException* pTux)
	{
		saida->createTag("SMSSend");
			saida->addItem("inEnviado", "0");
		saida->closeTag();

		setStatusCode(pTux->getCode(),pTux->getMessage());
		delete pTux;
	}
	
}

void cSMSUtil::carregaDados() {
	// Inicializa as estruturas de dados para armazenar os valores.
	memset(&m_stDados,0,sizeof(m_stDados));
	memset(&m_vlDados,-1,sizeof(m_vlDados));
	
	if (tx.walkTree( entrada, "destinatario", 0 ) != NULL) {
		strcpy(m_stDados.destinatario,tx.walkTree( entrada, "destinatario", 0 ));
		m_vlDados.destinatario = 1;
	}

	if (tx.walkTree( entrada, "mensagem", 0 ) != NULL) {
		strcpy(m_stDados.mensagem,tx.walkTree( entrada, "mensagem", 0 ));
		m_vlDados.mensagem = 1;
	}

}

