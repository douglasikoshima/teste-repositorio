/**
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:19 $
 **/

#include "../include/cWFAtdGetMotCan.h"

#include "../../Atendimento/include/cWFAtendimento.h"
#include "../../TabelaMotivo/include/cWFTabMotivo.h"

#include "../../FluxoFase/include/cWFFluxoFase.h"
#include "../../Usuario/include/cWFUsuario.h"

#include "../../../commons/msgPadrao.h"

extern void proCObterFaseAtendimento(int idPessoaUsr,long idAtendimento,int *idFaseMax);

cWFAtdGetMotCan::cWFAtdGetMotCan(DOMNode *dnode, XMLGen *xml_g)
{
    memset(&m_stDados,0,sizeof(m_stDados));
    memset(&m_vlDados,-1,sizeof(m_vlDados));

    entrada = dnode;
    saida = xml_g;

    carregaDados();
}

bool cWFAtdGetMotCan::executar(char **codErro,char **msgErro)
{
    int idFase;

	if ( m_vlDados.idAtividade == -1 )
    {
        *msgErro = "O código de 'idAtividade' é obrigatório";

        return false;
    }

    if ( m_vlDados.idAtendimento == -1 )
    {
        *msgErro = "O código de 'idAtendimento' é obrigatório";

        return false;
    }

    saida->createTag("AtendimentoWorkflowVO");
        saida->addProp("xmlns", "workflow.fo.vivo.com.br/vo");
        saida->createTag("AtendimentoWorkflowComumVO");

		ObterFaseMaxAtendimento();

		ObterDetalheAtd();

		if ( (m_stDados.idFaseMax == 1 ) || (m_stDados.inFase == 1))
		{
			idFase = 1;
		}
		else
		{
			idFase = 2;
		}

		ObterWFCancelamento(idFase);
        
		saida->closeTag();
    saida->closeTag();

    return true;
}

void cWFAtdGetMotCan::ObterWFCancelamento(int idFase)
{
    ULOG_START("cWFAtdGetMotCan::ObterWFCancelamento()");
    
    cWFFluxoFase cwfFluxoFase;
    int index=0;
    int tamSaida;
    SmallString strRecorFluxo;
    st_FluxoFase dados;
    st_vlFluxoFase status;
    XMLGen saidaInterna;

    memset(&dados,0,sizeof(dados));
    memset(&status,-1,sizeof(status));

    dados.idAtividade = m_stDados.idAtividade;
    status.idAtividade = 1;

    dados.idFase = idFase;
    status.idFase = 1;

    dados.idAgrupamentoEstadoTpProc = m_stDados.idAgrEstadoTProc;
    status.idAgrupamentoEstadoTpProc = 1;

    cwfFluxoFase.ObtemWFCanc(&dados,&status,&saidaInterna);

    char *xml = saidaInterna.retrieveXML(&tamSaida);

    if ( tamSaida < 1 )
    {
        ULOGW("cwfFluxoFase::ObtemWFCanc() nao gerou xml de saida");
        ULOG_END("cWFAtdGetMotCan::ObterWFCancelamento()");
        return;
    }

    strRecorFluxo += "<Fluxo>";
    strRecorFluxo += xml;
    strRecorFluxo += "</Fluxo>";

    tamSaida = strRecorFluxo.Size();

    XercesDOMParser *pParser = new XercesDOMParser;
    MemBufInputSource *pMemBuf = new 
              MemBufInputSource((const XMLByte*)((char*)strRecorFluxo),tamSaida,gerarIDDom());

    if ( pParser && pMemBuf )
    {
        xml = 0;

        pParser->parse(*pMemBuf);
        DOMNode* pDoc = pParser->getDocument();
        DOMNode* dn;

        while ( !xml && (dn = tx.walkDOM(pDoc,"WFAcoesVO",index++ )) )
        {
            char *_idMotivo = tx.walkTree(dn,"idMotivo",0);
            char *_dsMotivo = tx.walkTree(dn,"dsMotivo",0);
            char *_idAgrupamentoEstadoTpfuturo = tx.walkTree(dn,"idAgrupamentoEstadoTpfuturo",0);

            if ( _idMotivo && _dsMotivo && _idAgrupamentoEstadoTpfuturo )
            {
                saida->createTag("WFMotivoVO");
                    saida->addItem("idMotivo", _idMotivo);
                    saida->addItem("dsMotivo", _dsMotivo);
                    saida->addItem("idAgrupamentoEstadoTpfuturo", _idAgrupamentoEstadoTpfuturo);
                saida->closeTag();
            }
            else
            {
                xml = "cWFFluxoFase::ObtemWFCanc() não retornou xml valido";
            }

            if (_idMotivo) { XMLString::release(&_idMotivo); }
            if (_dsMotivo) { XMLString::release(&_dsMotivo); }
            if (_idAgrupamentoEstadoTpfuturo) { XMLString::release(&_idAgrupamentoEstadoTpfuturo); }
        }
    }

    if ( pParser ) { delete pParser; }
    if ( pMemBuf ) { delete pMemBuf; }

    if ( xml )
    {
        ULOGE(xml);
        ULOG_END("cWFAtdGetMotCan::ObterWFCancelamento()");
        throw new TuxBasicSvcException("00E0000",xml);
    }

    ULOG_END("cWFAtdGetMotCan::ObterWFCancelamento()");
}

void cWFAtdGetMotCan::CarregarTabMotivosAtividade()
{
    ULOG_START("cWFAtdGetMotCan::CarregarTabMotivosAtividade()");
    char *xml=0;
    
    ConsultarMotivoAtividade();

    if ( xmlObtemPsqTab.Size() )
    {
        SmallString strRecorFluxo;

        strRecorFluxo += "<Fluxo>";
        strRecorFluxo += xmlObtemPsqTab;
        strRecorFluxo += "</Fluxo>";
        int tamSaida = strRecorFluxo.Size();
        int index=0;

        XercesDOMParser *pParser = new XercesDOMParser;
        MemBufInputSource *pMemBuf = new 
                  MemBufInputSource((const XMLByte*)((char*)strRecorFluxo),tamSaida,gerarIDDom());

        if ( pParser && pMemBuf )
        {
            xml = 0;

            pParser->parse(*pMemBuf);
            DOMNode* pDoc = pParser->getDocument();
            DOMNode* dn;

            while ( !xml && (dn = tx.walkDOM(pDoc,"WFMotivoVO",index++ )) )
            {
                char *_dsMotivo = tx.walkTree(dn,"dsMotivo",0);

                if ( _dsMotivo )
                {
					saida->createTag("WFMotivoVO");
						saida->addItem("idMotivo", m_stDados.idAgrEstadoTProc);
						saida->addItem("dsMotivo", _dsMotivo);
					saida->closeTag();
                }
                else
                {
                    xml = "ConsultarMotivoAtividade() não retornou xml valido";
                }

				if (_dsMotivo)
					XMLString::release(&_dsMotivo);

            }
        }

        if ( pParser ) { delete pParser; }
        if ( pMemBuf ) { delete pMemBuf; }
    }

    if ( xml )
    {
        ULOGE(xml);
        ULOG_END("cWFAtdGetMotCan::CarregarTabMotivosAtividade()");
        throw new TuxBasicSvcException("00E0000",xml);
    }
    ULOG_END("cWFAtdGetMotCan::CarregarTabMotivosAtividade()");
}

void cWFAtdGetMotCan::ObterDetalheAtd()
{
    ULOG_START("cWFAtdGetMotCan::ObterDetalheAtd()");
    cWFAtendimento cwfAtendimento;
    int tamSaida;
    int idAgrEstadoTProc;
    XMLGen saida;

    ULOG("Executando ObterDetalheAtd() ...");

    if ( cwfAtendimento.ObtemDetalheAtend(m_stDados.idAtendimento,&saida,&idAgrEstadoTProc) )
    {
        char *xml = saida.retrieveXML(&tamSaida);

        if ( tamSaida < 1 )
        {
            ULOG( "cWFAtendimentoOrigem::ObtemDetalheAtend() nao gerou xml de saida" );
            return;
        }

        m_stDados.idAgrEstadoTProc = idAgrEstadoTProc;
        m_vlDados.idAgrEstadoTProc = 1;
    }
    ULOG_END("cWFAtdGetMotCan::ObterDetalheAtd()");
}

void cWFAtdGetMotCan::ObterFaseMaxAtendimento()
{
    ULOG_START("cWFAtdGetMotCan::ObterFaseMaxAtendimento()");

    proCObterFaseAtendimento(obterIdUsuario(),m_stDados.idAtendimento,&m_stDados.idFaseMax);

    if ( m_stDados.idFaseMax >= 0 )
    {
        m_vlDados.idFaseMax = 1;
    }
    
    ULOG_END("cWFAtdGetMotCan::ObterFaseMaxAtendimento()");
}

void cWFAtdGetMotCan::ConsultarMotivoAtividade()
{
    ULOG_START("cWFAtdGetMotCan::ConsultarMotivoAtividade()");
    
    cWFTabMotivo cwfTabMotivo;
    int tamSaida;
    XMLGen saida;

    

    if ( !cwfTabMotivo.pesqMotivoAtividade(entrada,&saida) )
    {
        ULOG("falhou execução");
        ULOG_END("cWFAtdGetMotCan::ConsultarMotivoAtividade()");

        return;
    }

    char *xml = saida.retrieveXML(&tamSaida);

    if ( !tamSaida )
    {
        ULOG("não gerou xml de saida");
        ULOG_END("cWFAtdGetMotCan::ConsultarMotivoAtividade()");
        return;
    }

    xmlObtemPsqTab = xml;
    
    ULOG_END("cWFAtdGetMotCan::ConsultarMotivoAtividade()");
}

void cWFAtdGetMotCan::carregaDados()
{
	char *p;

	if (p=tx.walkTree( entrada, "idAtividade", 0 ),p) 
	{
		m_stDados.idAtividade = atoi(p);
		m_vlDados.idAtividade = 1;
		XMLString::release(&p);
	}

	if (p=tx.walkTree( entrada, "idAtendimento", 0 ),p) 
	{
		m_stDados.idAtendimento = atol(p);
		m_vlDados.idAtendimento = 1;
		XMLString::release(&p);
	}

	if (p=tx.walkTree( entrada, "inFase", 0 ),p) 
	{
		m_stDados.inFase = atoi(p);
		m_vlDados.inFase = 1;
		XMLString::release(&p);
	}

	ULOG("idAtividade=%d",m_stDados.idAtividade);
	ULOG("idAtendimento=%d" ,m_stDados.idAtendimento);
	
}

