/**
 * 
 * @modulo  Commons
 * @usecase Envio de mensagens SMS para o celular do usuário.
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:11 $
 **/ 

#include "../include/cEmailUtil.h"

extern bool proCEnviarMail(st_EmailUtil* dados, st_vlEmailUtil* status, XMLGen* saida);

cEmailUtil::cEmailUtil()
{
	tuxfw_getlogger()->debug("EmailUtil - Iniciando construtor padrao...");

    entrada = 0;
    saida = 0;

	memset(&m_stDados,0,sizeof(m_stDados));
	memset(&m_vlDados,-1,sizeof(m_vlDados));
}

cEmailUtil::cEmailUtil(DOMNode*dnode, XMLGen*xml_g)
{
	tuxfw_getlogger()->debug("EmailUtil - Iniciando construtor entradas...");

	entrada = dnode;
	saida   = xml_g;

	carregaDados();
}

cEmailUtil::cEmailUtil(st_EmailUtil *dados,st_vlEmailUtil *status,XMLGen*xml_g)
{
	tuxfw_getlogger()->debug("EmailUtil - Iniciando construtor entradas...");

	entrada = 0;
	saida   = xml_g;

	memcpy(&m_stDados,dados,sizeof(m_stDados));
	memcpy(&m_vlDados,status,sizeof(m_vlDados));
}

void cEmailUtil::setRemetente(char* remetente)
{
	strcpy(m_stDados.remetente, remetente);
	m_vlDados.remetente = 1;
}

void cEmailUtil::setDestinatario(char* destinatario)
{
	strcpy(m_stDados.destinatario, destinatario);
	m_vlDados.destinatario = 1;
}

void cEmailUtil::setAssunto(char* assunto)
{
	strcpy(m_stDados.assunto, assunto);
	m_vlDados.assunto = 1;
}

void cEmailUtil::setMensagem(char* mensagem)
{
	strcpy(m_stDados.mensagem, mensagem);
	m_vlDados.mensagem = 1;
}

void cEmailUtil::enviar()
{
	tuxfw_getlogger()->debug("EmailUtil - Vai enviar o mail...");

	if (m_vlDados.assunto == -1
		|| m_vlDados.destinatario == -1
		|| m_vlDados.mensagem == -1
	   )
	{
		throw new TuxException( "99E99999"
            ,"Não foram recebidos dados obrigatórios para envio de e-mail.");		
	}

	tuxfw_getlogger()->debug("Chamada a função de envio do e-mail...");
	TuxMessage tm;

	tuxfw_getlogger()->debug("Declara o retorno da chamada com o XMLGen...");
	XMLGen gen;

	gen.addItem( "to",      m_stDados.destinatario );
	gen.addItem( "subject", m_stDados.assunto );
	gen.addItem( "data",    m_stDados.mensagem );


	try 
	{
		tuxfw_getlogger()->debug("Vai chamar servico remoto [SMTPSEND]...");
		remoteCall("SMTPSEND", &gen, &tm);
		tuxfw_getlogger()->debug("Chamou servico remoto...");

		setStatusCode("00I0000", "Operação realizada com sucesso.");
	}
	catch(TuxException* pTux)
	{
		tuxfw_getlogger()->debug("ATENCAO !!! Problemas na chamada do servico remoto...");
		setStatusCode(pTux->getCode(),pTux->getMessage());
	}
}

void cEmailUtil::carregaDados()
{
    char *p;

	// Inicializa as estruturas de dados para armazenar os valores.
	memset(&m_stDados,0,sizeof(m_stDados));
	memset(&m_vlDados,-1,sizeof(m_vlDados));
	
	tuxfw_getlogger()->debug("Carregando variaveis recebidas...");

	if (p=tx.walkTree( entrada, "remetente", 0 ),p)
    {
		tuxfw_getlogger()->debug("Encontrado remetente...");
		strcpy(m_stDados.remetente,p);
		m_vlDados.remetente = 1;
        XMLString::release(&p);
	}

	if (p=tx.walkTree( entrada, "destinatario", 0 ),p)
    {
		tuxfw_getlogger()->debug("Encontrado destinatario...");
		strcpy(m_stDados.destinatario,p);
		m_vlDados.destinatario = 1;
        XMLString::release(&p);
	}

	if (p=tx.walkTree( entrada, "assunto", 0 ),p)
    {
		tuxfw_getlogger()->debug("Encontrado assunto...");
		strcpy(m_stDados.assunto,p);
		m_vlDados.assunto = 1;
        XMLString::release(&p);
	}

	if (p=tx.walkTree( entrada, "mensagem", 0 ),p)
    {
		tuxfw_getlogger()->debug("Encontrado mensagem...");
		strcpy(m_stDados.mensagem,p);
		m_vlDados.mensagem = 1;
        XMLString::release(&p);
	}
	tuxfw_getlogger()->debug("PRONTO - Carregando variaveis recebidas...");
}
