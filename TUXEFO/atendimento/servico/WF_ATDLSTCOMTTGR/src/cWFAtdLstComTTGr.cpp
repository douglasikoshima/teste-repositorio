/**
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:20 $
 **/

#include "../include/cWFAtdLstComTTGr.h"

#include "../../Usuario/include/cWFUsuario.h"

#include "../../TabelaSegmentacao/include/cWFTabSegmentacao.h"
#include "../../TabelaTipoCarteira/include/cWFTabTipoCarteira.h"
#include "../../TabelaTipoSequencia/include/cWFTabTipoSequencia.h"
#include "../../TabelaUFOperadora/include/cWFTabUFOperadora.h"

extern void getListaNiveisCargo(int parametro, char* tag, XMLGen* saida);

cWFAtdLstComTTGr::cWFAtdLstComTTGr(DOMNode *dnode, XMLGen *xml_g)
{
    entrada = dnode;
    saida = xml_g;

    carregaDados();
}

bool cWFAtdLstComTTGr::Executar()
{
    ULOG_START("cWFAtdLstComTTGr::Executar()");

    bool retorno;

	saida->createTag("WFRelatoriosFiltrosVO");

	saida->addProp("xmlns","workflow.fo.vivo.com.br/vo");

	if (m_stDados.idOperadora > 0)
    {
		PesquisarUfOperadora();
		retorno = true;
    }
	else if (m_stDados.idGrupo > 0)
    {
		pesquisarUsuarios();
		retorno = true;
    }
	else
	{

		if ( retorno = PesquisarTabelaTipoSequencia(), retorno )
		{
			if ( retorno = PesquisarTabelaSegmentacao(), retorno )
			{
				if ( retorno = PesquisarTabelaTipoCarteira(), retorno )
				{
					if ( retorno = ConsultarWFGrupos(), retorno )
					{
						if ( retorno = PesquisarOperadoras(), retorno )
						{
							ObterListaStatusUsuario();
						}
					}
				}
			}
		}

		pesquisarDivisao();
		pesquisarGerencia();
		pesquisarCoordinacao();
		pesquisarSupervisao();
																		  // no script original !!
    }

	saida->closeTag();
	
	ULOG_END("cWFAtdLstComTTGr::Executar()");

    return retorno;
}

bool cWFAtdLstComTTGr::PesquisarTabelaSegmentacao()
{
    ULOG_START("cWFAtdLstComTTGr::PesquisarTabelaSegmentacao()");

    cWFTabSegment cwfTabSegment;

    
    bool retorno = cwfTabSegment.PesquisarTabelaSegmentacao(entrada, saida);

    if ( !retorno )
    {
        SetarErro(NULL,"cWFTabSegment::PesquisarTabelaSegmentacao falhou!");

        ULOGE(ObterMsgErro());
    }

    ULOG_END("cWFAtdLstComTTGr::PesquisarTabelaSegmentacao()");

    return retorno;
}

bool cWFAtdLstComTTGr::PesquisarTabelaTipoCarteira()
{
    ULOG_START("cWFAtdLstComTTGr::PesquisarTabelaTipoCarteira()");

    cWFTabTipoCarteira cwfTabTipoCarteira;

    
    bool retorno = cwfTabTipoCarteira.PesquisarTabelaTipoCarteira(entrada, saida);

    if ( !retorno )
    {
        SetarErro(NULL,"cWFTabTipoCarteira::PesquisarTabelaTipoCarteira falhou!");

        ULOGE(ObterMsgErro());
    }

    ULOG_END("cWFAtdLstComTTGr::PesquisarTabelaTipoCarteira()");

    return retorno;
}

bool cWFAtdLstComTTGr::PesquisarTabelaTipoSequencia()
{
    ULOG_START("cWFAtdLstComTTGr::PesquisarTabelaTipoSequencia()");

    cWFTabTipoSequencia cwfTabTipoSequencia;

    

    bool retorno = cwfTabTipoSequencia.PesquisarTabelaTipoSequencia(entrada, saida);

    if ( !retorno )
    {
        SetarErro(NULL,"cWFTabTipoSequencia::PesquisarTabelaTipoSequencia falhou!");

        ULOGE(ObterMsgErro());
    }

    ULOG_END("cWFAtdLstComTTGr::PesquisarTabelaTipoSequencia()");

    return retorno;
}

bool cWFAtdLstComTTGr::PesquisarOperadoras()
{
    ULOG_START("cWFAtdLstComTTGr::PesquisarOperadoras()");

    cWFTabUFOper cwfTabUFOper;

    

    bool retorno = cwfTabUFOper.PesquisarOperadoras(entrada,saida);

    if ( !retorno )
    {
        SetarErro(NULL,"cWFTabUFOper::PesquisarOperadoras falhou!");

        ULOGE(ObterMsgErro());
    }

    ULOG_END("cWFAtdLstComTTGr::PesquisarOperadoras()");

    return retorno;
}

bool cWFAtdLstComTTGr::PesquisarUfOperadora()
{
    ULOG_START("cWFAtdLstComTTGr::PesquisarUfOperadora()");

    cWFTabUFOper cwfTabUFOper;

    

    bool retorno = cwfTabUFOper.PesquisarTabelaUFRegional(entrada,saida);

    if ( !retorno )
    {
        SetarErro(NULL,"cWFTabUFOper::PesquisarUfOperadora falhou!");

        ULOGE(ObterMsgErro());
    }

    ULOG_END("cWFAtdLstComTTGr::PesquisarUfOperadora()");

    return retorno;
}

bool cWFAtdLstComTTGr::ConsultarWFGrupos()
{
    ULOG_START("cWFAtdLstComTTGr::ConsultarWFGrupos()");

    cWFUsuario cwfUsuario;

    bool retorno = cwfUsuario.pesqConsultaWFGruposRelatorios(saida);

    if ( !retorno )
    {
        SetarErro(NULL,"cWFUsuario::ConsultarWFGrupos falhou!");

        ULOGE(ObterMsgErro());
    }

    ULOG_END("cWFAtdLstComTTGr::ConsultarWFGrupos()");

    return retorno;
}

bool cWFAtdLstComTTGr::pesquisarUsuarios()
{
    ULOG_START("cWFAtdLstComTTGr::pesquisarUsuarios()");

    cWFUsuario cwfUsuario;

    bool retorno = cwfUsuario.pesqUsuarioRelatorio(m_stDados.idGrupo, saida);

    if ( !retorno )
    {
        SetarErro(NULL,"cWFUsuario::pesqUsuarioRelatorio falhou!");

        ULOGE(ObterMsgErro());
    }

    ULOG_END("cWFAtdLstComTTGr::pesquisarUsuarios()");

    return retorno;
}

void cWFAtdLstComTTGr::ObterListaStatusUsuario()
{
    ULOG_START("cWFAtdLstComTTGr::ObterListaStatusUsuario()");

    cWFUsuario cwfUsuario;

    cwfUsuario.obterListaStatusUsuario(saida);

    ULOG_END("cWFAtdLstComTTGr::ObterListaStatusUsuario()");
}

void cWFAtdLstComTTGr::pesquisarDivisao()
{
    ULOG_START("cWFAtdLstComTTGr::pesquisarDivisao()");

    getListaNiveisCargo(30, "WFRelatoriosFiltroDiretoriaVO", saida);

    ULOG_END("cWFAtdLstComTTGr::pesquisarDivisao()");
}

void cWFAtdLstComTTGr::pesquisarGerencia()
{
    ULOG_START("cWFAtdLstComTTGr::pesquisarGerencia()");

    getListaNiveisCargo(31, "WFRelatoriosFiltroAreaVO", saida);

    ULOG_END("cWFAtdLstComTTGr::pesquisarGerencia()");
}

void cWFAtdLstComTTGr::pesquisarCoordinacao()
{
    ULOG_START("cWFAtdLstComTTGr::pesquisarCoordinacao()");

    getListaNiveisCargo(32, "WFRelatoriosFiltroSessaoVO", saida);

    ULOG_END("cWFAtdLstComTTGr::pesquisarCoordinacao()");
}

void cWFAtdLstComTTGr::pesquisarSupervisao()
{
    ULOG_START("cWFAtdLstComTTGr::pesquisarSupervisao()");

    getListaNiveisCargo(33, "WFRelatoriosFiltroCargoVO", saida);

    ULOG_END("cWFAtdLstComTTGr::pesquisarSupervisao()");
}

void cWFAtdLstComTTGr::carregaDados()
{
    char *p;

    // Inicializa as estruturas de dados para armazenar os valores
    memset(&m_stDados,0,sizeof(m_stDados));
    memset(&m_vlDados,-1,sizeof(m_vlDados));

    if (p=tx.walkTree( entrada, "idOperadora", 0 ),p) 
    {
        m_stDados.idOperadora = atoi(p);
        m_vlDados.idOperadora = 1;
		XMLString::release(&p);
    }
    if (p=tx.walkTree( entrada, "idGrupo", 0 ),p) 
    {
        m_stDados.idGrupo = atoi(p);
        m_vlDados.idGrupo = 1;
		XMLString::release(&p);
    }
    if (p=tx.walkTree( entrada, "nrRegistroInicial", 0 ),p) 
    {
        start = atoi(p);
		XMLString::release(&p);
    }
	if (p=tx.walkTree( entrada, "nrRegistroFinal", 0 ),p) 
    {
        stop = atoi(p);
		XMLString::release(&p);
    }
}
