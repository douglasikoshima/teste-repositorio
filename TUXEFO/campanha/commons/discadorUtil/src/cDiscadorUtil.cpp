/**
 * 
 * @modulo  Commons
 * @usecase Classe utilitária para geração de discagens no discador via EAI.
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:51 $
 **/ 


#include "../include/cDiscadorUtil.h"

cDiscadorUtil::cDiscadorUtil()
{
	memset(&m_stDados,0,sizeof(m_stDados));
	memset(&m_vlDados,-1,sizeof(m_vlDados));

	con = new Collection;
	cam = new Collection;
}

cDiscadorUtil::cDiscadorUtil(DOMNode*dnode, XMLGen*xml_g)
{
	entrada = dnode;
	saida   = xml_g;

	con = new Collection;
	cam = new Collection;

	carregaDados();
}

cDiscadorUtil::~cDiscadorUtil() {

	delete con;
	delete cam;

}



char* cDiscadorUtil::getEntity()
{
	return m_stDados.entity;
}

char* cDiscadorUtil::getSequence()
{
	return m_stDados.sequence;
}

int   cDiscadorUtil::getNPU()
{
	return m_stDados.npu;
}

int   cDiscadorUtil::getIdLigacao()
{
	return m_stDados.idLigacao;
}

int   cDiscadorUtil::getIdCampanha()
{
	return m_stDados.idCampanha;
}

char* cDiscadorUtil::getHoraInicio()
{
	return m_stDados.horaInicio;
}

char* cDiscadorUtil::getHoraFim()
{
	return m_stDados.horaFim;
}

char* cDiscadorUtil::getDataHoraInicio()
{
	return m_stDados.dataHoraInicio;
}

char* cDiscadorUtil::getDataHoraFim()    
{
	return m_stDados.dataHoraFim;
}
     
char* cDiscadorUtil::getDataConsultaInicio()
{
	return m_stDados.dataConsultaInicio;
}

char* cDiscadorUtil::getDataConsultaFim()
{
	return m_stDados.dataConsultaFim;
}

char* cDiscadorUtil::getDataHoraLigacaoInicio()
{
	return m_stDados.dataHoraLigacaoInicio;
}

char* cDiscadorUtil::getDataHoraLigacaoFim()
{
	return m_stDados.dataHoraLigacaoFim;
}

int   cDiscadorUtil::getNumeroTentativas()
{
	return m_stDados.numeroTentativas;
}

int   cDiscadorUtil::getIntervaloTentativa()
{
	return m_stDados.intervaloTentativa;
}

int   cDiscadorUtil::getIdProcessoRetorno()
{
	return m_stDados.idProcessoRetorno;
}

char* cDiscadorUtil::getFileName()
{
	return m_stDados.fileName;
}

int   cDiscadorUtil::getIdUsuario()
{
	return m_stDados.idUsuario;
}

char* cDiscadorUtil::getLoginUsuarioCTI()
{
	return m_stDados.loginUsuarioCTI;
}

char* cDiscadorUtil::getLoginPabx()
{
	return m_stDados.loginPabx;
}

char* cDiscadorUtil::getChamada()
{
	return m_stDados.chamada;
}

void cDiscadorUtil::setEntity(char* entity)
{
	strcpy(m_stDados.entity, entity);
	m_vlDados.entity= 1;
}

void cDiscadorUtil::setSequence(char* sequence)
{
	strcpy(m_stDados.sequence, sequence);
	m_vlDados.sequence= 1;
}

void cDiscadorUtil::setNPU(int npu)
{
	m_stDados.npu = npu;
	m_vlDados.npu= 1;
}

void cDiscadorUtil::setIdLigacao(int idLigacao)
{
	m_stDados.idLigacao = idLigacao;
	m_vlDados.idLigacao = 1;
}

void cDiscadorUtil::setIdCampanha(int idCampanha)
{
	m_stDados.idCampanha = idCampanha;
	m_vlDados.idCampanha = 1;
}

void cDiscadorUtil::setHoraInicio(char* horaInicio)
{
	strcpy(m_stDados.horaInicio, horaInicio);
	m_vlDados.horaInicio = 1;
}

void cDiscadorUtil::setHoraFim(char* horaFim)
{
	strcpy(m_stDados.horaFim , horaFim);
	m_vlDados.horaFim = 1;
}

void cDiscadorUtil::setDataHoraInicio(char* dataHoraInicio)
{
	strcpy(m_stDados.dataHoraInicio, dataHoraInicio);
	m_vlDados.dataHoraInicio = 1;
}

void cDiscadorUtil::setDataHoraFim(char* dataHoraFim)
{
	strcpy(m_stDados.dataHoraFim, dataHoraFim);
	m_vlDados.dataHoraFim = 1;
}

void cDiscadorUtil::setDataConsultaInicio(char* dataConsultaInicio)
{
	strcpy(m_stDados.dataConsultaInicio, dataConsultaInicio);
	m_vlDados.dataConsultaInicio = 1;
}

void cDiscadorUtil::setDataConsultaFim(char* dataConsultaFim)
{
	strcpy(m_stDados.dataConsultaFim, dataConsultaFim);
	m_vlDados.dataConsultaFim = 1;
}

void cDiscadorUtil::setDataHoraLigacaoInicio(char* dataHoraLigacaoInicio)
{
	strcpy(m_stDados.dataHoraLigacaoInicio, dataHoraLigacaoInicio);
	m_vlDados.dataHoraLigacaoInicio = 1;
}

void cDiscadorUtil::setDataHoraLigacaoFim(char* dataHoraLigacaoFim)
{
	strcpy(m_stDados.dataHoraLigacaoFim, dataHoraLigacaoFim);
	m_vlDados.dataHoraLigacaoFim = 1;
}

void cDiscadorUtil::setNumeroTentativas(int numeroTentativas)
{
	m_stDados.numeroTentativas = numeroTentativas;
	m_vlDados.numeroTentativas = 1;
}

void cDiscadorUtil::setIntervaloTentativa(int intervaloTentativa)
{
	m_stDados.intervaloTentativa = intervaloTentativa;
	m_vlDados.intervaloTentativa = 1;
}

void cDiscadorUtil::setIdProcessoRetorno(int idProcessoRetorno)
{
	m_stDados.idProcessoRetorno = idProcessoRetorno;
	m_vlDados.idProcessoRetorno = 1;
}

void cDiscadorUtil::setFileName(char* fileName)
{
	strcpy(m_stDados.fileName, fileName);
	m_vlDados.fileName = 1;
}

void cDiscadorUtil::setIdUsuario(int idUsuario)
{
	m_stDados.idUsuario = idUsuario;
	m_vlDados.idUsuario = 1;
}

void cDiscadorUtil::setLoginUsuarioCTI(char* loginUsuarioCTI)
{
	strcpy(m_stDados.loginUsuarioCTI, loginUsuarioCTI);
	m_vlDados.loginUsuarioCTI = 1;
}

void cDiscadorUtil::setLoginPabx(char* loginPabx)
{
	strcpy(m_stDados.loginPabx, loginPabx);
	m_vlDados.loginPabx = 1;
}

void cDiscadorUtil::setChamada(char* chamada)
{
	strcpy(m_stDados.chamada, chamada);
	m_vlDados.chamada = 1;
}

/**
	Controle dos registros da lista de contatos.
**/
int cDiscadorUtil::getRegistrosContatoLista()
{
	return con->GetCount();
}

int cDiscadorUtil::addContatoLista(char* contato)
{
    if ( contato )
    {
	    m_vlDados.listaContatos = 1;

        char *p = new char [strlen(contato)+1];
        strcpy(p,contato);

	    return con->AddItem((void*)p);
    }

    return -1;
}

char* cDiscadorUtil::getContatoLista(int idContato)
{
	return (char*) con->GetItem(idContato);
}

void cDiscadorUtil::limpaContatoLista()
{
	delete con;
	con = new Collection;
}

/**
	Controle dos registros da lista de campanhas.
**/
int cDiscadorUtil::getRegistrosCampanhaLista()
{
	return cam->GetCount();
}

int cDiscadorUtil::addCampanhaLista(char* campanha)
{
    if ( campanha )
    {
	    m_vlDados.listaCampanhas = 1;

        char *p = new char [strlen(campanha)+1];
        strcpy(p,campanha);

	    return con->AddItem((void*)p);
    }

    return -1;
}

char* cDiscadorUtil::getCampanhaLista(int idRegistro)
{
	return (char*) cam->GetItem(idRegistro);
}

void cDiscadorUtil::limpaCampanhaLista()
{
	delete cam;
	cam = new Collection;
}

/**
	Processa a chamada ao EAI.
**/
void cDiscadorUtil::enviar() 
{
    int camGetCount = cam->GetCount();
    int conGetCount = con->GetCount();

	if (cDiscadorUtil::m_vlDados.entity == -1)
		throw new TuxException( "99E01000", "É necessário informar o código do entity para essa operação.");		

	if (cDiscadorUtil::m_vlDados.sequence == -1)
		throw new TuxException( "99E02000", "É necessário informar o código de sequence para essa operação.");		
	
	tuxfw_getlogger()->debug("Chamada a função de envio de dados ao discador, vai montar o XML...");
	TuxMessage tm;

	tuxfw_getlogger()->debug("Declara o retorno da chamada com o XMLGen...");
	XMLGen gen;

	tm.setService( m_stDados.chamada );
	tm.setSubSystem("FOF");
	tm.setVersion("000");
	tm.setEntity(m_stDados.entity);
	tm.setSequence(m_stDados.sequence);
	
	if (m_vlDados.npu != -1)
		gen.addItem( "NPU" , m_stDados.npu );
		
	if (m_vlDados.idLigacao != -1)
		gen.addItem( "Id_Ligacao_FO" , m_stDados.idLigacao );

	if (m_vlDados.idCampanha != -1)
		gen.addItem( "Id_Campanha_FO" , m_stDados.idCampanha );

	if (camGetCount > 0)
	{
		gen.createTag( "Id_Campanha_FO_in" );
		for(int i = 0; i < camGetCount; i++)
		{
			gen.addItem( "string" , (char*)cam->GetItem(i) );
		}
		gen.closeTag();
	}

	if (conGetCount > 0)
	{
		gen.createTag( "ListaContatos" );
		for(int i = 0; i < conGetCount; i++)
		{
			gen.addItem( "string" , (char*)con->GetItem(i) );
		}
		gen.closeTag();
	}

	if (m_vlDados.horaInicio != -1)
		gen.addItem( "HoraInicioLigacoes" , m_stDados.horaInicio );

	if (m_vlDados.horaFim != -1)
		gen.addItem( "HoraFimLigacoes" , m_stDados.horaFim );

	if (m_vlDados.dataHoraInicio != -1)
		gen.addItem( "DataHoraInicioCampanha" , m_stDados.dataHoraInicio );

	if (m_vlDados.dataHoraFim != -1)
		gen.addItem( "DataHoraFimCampanha" , m_stDados.dataHoraFim );

	if (m_vlDados.dataConsultaInicio != -1)
		gen.addItem( "DataInicioConsulta" , m_stDados.dataConsultaInicio );

	if (m_vlDados.dataConsultaFim != -1)
		gen.addItem( "DataFimConsulta" , m_stDados.dataConsultaFim );

	if (m_vlDados.dataHoraLigacaoInicio != -1 && m_vlDados.dataHoraLigacaoFim == -1)
		gen.addItem( "DataHoraLigacao" , m_stDados.dataHoraLigacaoInicio );

	if (m_vlDados.dataHoraLigacaoInicio != -1 && m_vlDados.dataHoraLigacaoFim != -1)
		gen.addItem( "dataHoraLigacaoInicio" , m_stDados.dataHoraLigacaoInicio );

	if (m_vlDados.dataHoraLigacaoFim != -1)
		gen.addItem( "dataHoraLigacaoFim" , m_stDados.dataHoraLigacaoFim );

	if (m_vlDados.numeroTentativas != -1)
		gen.addItem( "NumeroTentativas" , m_stDados.numeroTentativas );

	if (m_vlDados.intervaloTentativa != -1)
		gen.addItem( "IntervaloEntreTentativas" , m_stDados.intervaloTentativa );

	if (m_vlDados.idProcessoRetorno != -1)
		gen.addItem( "IdProcessoaSerRetornado" , m_stDados.idProcessoRetorno );

	if (m_vlDados.fileName != -1)
		gen.addItem( "FileName" , m_stDados.fileName );

	if (m_vlDados.idUsuario != -1)
		gen.addItem( "Id_Usuario_FO" , m_stDados.idUsuario );

	if (m_vlDados.loginUsuarioCTI != -1)
		gen.addItem( "Login_Usuario_CTI" , m_stDados.loginUsuarioCTI );

	if (m_vlDados.loginPabx != -1)
		gen.addItem( "Login_Pabx" , m_stDados.loginPabx );

	int len;

	tm.setMessageBody( gen.retrieveXML( &len ) );

	try 
	{

		TuxRemoteService rc;

		rc.setServiceName( m_stDados.chamada );
		rc.setInputMessage( &tm );

		tuxfw_getlogger()->debug("Vai chamar servico remoto [%s]...", m_stDados.chamada);

		rc.remoteCall();

		TuxMessage* outTm = rc.getOutputMessage();

		tuxfw_getlogger()->debug("Chamou servico remoto...");

		char* codigoRetorno = outTm->getStatusCode();

		tuxfw_getlogger()->debug("ATENCAO - Status code retornado [%s]...", codigoRetorno);

		if (codigoRetorno[2] == 'E')
		{
			tuxfw_getlogger()->debug("ATENCAO !!! Retornoado statuts code de erro, tratando...");
			char mensagem[256];

			sprintf(mensagem,"%.*s",sizeof(mensagem)-1,outTm->getStatusText());

			delete outTm;

			throw new TuxException( codigoRetorno, mensagem);		
		}

		if (outTm)
			delete outTm;

		if (codigoRetorno)
			free( codigoRetorno );

		setStatusCode("00I0000", "Operação realizada com sucesso.");
	}
	catch(TuxException* pTux)
	{
		tuxfw_getlogger()->debug("ATENCAO !!! Problemas na chamada do servico remoto...");

		char codigoErro[256];
		char mensagemErro[256];
		
		sprintf(codigoErro  ,"%.*s",sizeof(codigoErro)-1,  pTux->getCode());
		sprintf(mensagemErro,"%.*s",sizeof(mensagemErro)-1,pTux->getMessage());

		delete pTux;
		throw new TuxException( codigoErro, mensagemErro);		
	}

}

/**
	Carrega os dados quando estes são recebido via XML.
**/
void cDiscadorUtil::carregaDados()
{
    char *p;
    char *tmp = 0L;
    DOMNode*dom;

	// Inicializa as estruturas de dados para armazenar os valores.
	memset(&m_stDados,0,sizeof(m_stDados));
	memset(&m_vlDados,-1,sizeof(m_vlDados));
	
	if (p=tx.walkTree( entrada, "npu", 0 ),p)
    {
		m_stDados.npu = atoi(p);
		m_vlDados.npu = 1;
        XMLString::release(&p);
	}

	if (p=tx.walkTree( entrada, "idLigacao", 0 ),p)
    {
		m_stDados.idLigacao = atoi(p);
		m_vlDados.idLigacao = 1;
        XMLString::release(&p);
	}

	if (p=tx.walkTree( entrada, "idCampanha", 0 ),p)
    {
		m_stDados.idCampanha = atoi(p);
		m_vlDados.idCampanha = 1;
        XMLString::release(&p);
	}

	if ( p = tx.walkTree( entrada, "listaContatos", 0 ),p )
    {
        XMLString::release(&p);

		while (dom = tx.walkDOM(entrada,"listaContatos"))
		{
			if ( p = tx.walkTree(dom, "contato", 0),p )
			{
                if ( tmp = new char [strlen(p)+1],tmp )
                {
				    strcpy(tmp, p);

				    if ( strlen(tmp) )
				    {
					    con->AddItem((void*) tmp);
					    m_vlDados.listaContatos = 1;
				    }
                }
                XMLString::release(&p);
			}
		}
	}

	if ( p = tx.walkTree( entrada, "listaCampanhas", 0 ),p )
    {
        XMLString::release(&p);

		while (dom = tx.walkDOM(entrada,"listaCampanhas"))
		{   
			if ( p = tx.walkTree(dom, "campanha", 0),p )
			{
                if ( tmp = new char [strlen(p)+1],tmp )
                {
				    strcpy(tmp, p);

				    if (strlen(tmp) > 0)
				    {
					    cam->AddItem((void*) tmp);
					    m_vlDados.listaCampanhas = 1;
				    }
                }
                XMLString::release(&p);
			}
		}
	}

	if (p=tx.walkTree( entrada, "horaInicio", 0 ),p)
    {
		strcpy(m_stDados.horaInicio,p);
		m_vlDados.horaInicio = 1;
        XMLString::release(&p);
	}

	if (p=tx.walkTree( entrada, "horaFim", 0 ),p)
    {
		strcpy(m_stDados.horaFim,p);
		m_vlDados.horaFim = 1;
        XMLString::release(&p);
	}

	if (p=tx.walkTree( entrada, "dataHoraInicio", 0 ),p)
    {
		strcpy(m_stDados.dataHoraInicio,p);
		m_vlDados.dataHoraInicio = 1;
        XMLString::release(&p);
	}

	if (p=tx.walkTree( entrada, "dataHoraFim", 0 ),p)
    {
		strcpy(m_stDados.dataHoraFim,p);
		m_vlDados.dataHoraFim = 1;
        XMLString::release(&p);
	}

	if (p=tx.walkTree( entrada, "dataHoraLigacao", 0 ),p)
    {
		strcpy(m_stDados.dataHoraLigacaoInicio,p);
		m_vlDados.dataHoraLigacaoInicio = 1;
        XMLString::release(&p);
	}

	if (p=tx.walkTree( entrada, "dataHoraLigacaoInicio", 0 ),p)
    {
		strcpy(m_stDados.dataHoraLigacaoInicio,p);
		m_vlDados.dataHoraLigacaoInicio = 1;
        XMLString::release(&p);
	}

	if (p=tx.walkTree( entrada, "dataHoraLigacaoFim", 0 ),p)
    {
		strcpy(m_stDados.dataHoraLigacaoFim,p);
		m_vlDados.dataHoraLigacaoFim = 1;
        XMLString::release(&p);
	}

	if (p=tx.walkTree( entrada, "numeroTentativas", 0 ),p)
    {
		m_stDados.numeroTentativas = atoi(p);
		m_vlDados.numeroTentativas = 1;
        XMLString::release(&p);
	}

	if (p=tx.walkTree( entrada, "intervaloTentativa", 0 ),p)
    {
		m_stDados.intervaloTentativa = atoi(p);
		m_vlDados.intervaloTentativa = 1;
        XMLString::release(&p);
	}

	if (p=tx.walkTree( entrada, "idProcessoRetorno", 0 ),p)
    {
		m_stDados.idProcessoRetorno = atoi(p);
		m_vlDados.idProcessoRetorno = 1;
        XMLString::release(&p);
	}

	if (p=tx.walkTree( entrada, "fileName", 0 ),p)
    {
		strcpy(m_stDados.fileName,p);
		m_vlDados.fileName = 1;
        XMLString::release(&p);
	}

	if (p=tx.walkTree( entrada, "idUsuario", 0 ),p)
    {
		m_stDados.idUsuario = atoi(p);
		m_vlDados.idUsuario = 1;
        XMLString::release(&p);
	}

	if (p=tx.walkTree( entrada, "loginUsuarioCTI", 0 ),p)
    {
		strcpy(m_stDados.loginUsuarioCTI,p);
		m_vlDados.loginUsuarioCTI = 1;
        XMLString::release(&p);
	}

	if (p=tx.walkTree( entrada, "loginPabx", 0 ),p)
    {
		strcpy(m_stDados.loginPabx,p);
		m_vlDados.loginPabx = 1;
        XMLString::release(&p);
	}
}
