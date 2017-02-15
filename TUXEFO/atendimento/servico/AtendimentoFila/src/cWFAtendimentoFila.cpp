

#include "../../../commons/definesAtendimento.h"
#include "../include/cWFAtendimentoFila.h"
#include "../../../commons/msgPadrao.h"

extern bool proCConsultaWFAtendimentoFila(int tipo, st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFila(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaUsuario(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaUsuarioLinha(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaLinha(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFInboxUsuario(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFInboxUsuarioLinha(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

/**
	Construtor padrão da classe.
	@autor Renato Teixeira
	@since 21/10/2004
*/
cWFAtendimentoFila::cWFAtendimentoFila()
{
    entrada=0;
    saida=0;

	memset(&m_stDados,0,sizeof(m_stDados));
	memset(&m_vlDados,-1,sizeof(m_vlDados));
	
    memset(&m_stFila,0,sizeof(m_stFila));
	memset(&m_vlFila,-1,sizeof(m_vlFila));

    memset(&dadosAtendimento,0,sizeof(dadosAtendimento));
}

cWFAtendimentoFila::~cWFAtendimentoFila()
{
    // Warning: Este ponteiro não foi alocado pela classe e portanto não é de
    //          responsabilidade da classe desalocá-lo. Como consequencia o VC
    //          estava gerando uma exception em ambiente de depuração pois a
    //          área do ponteiro já havia sido liberada pelo sistema operacional
    //
    // delete[] m_stFila.gruposUsuario;
}

/**
	Construtor da classe concebido para sobrepor o construtor original que recebia como parametros
	um DOMNode e um XMLGen, esse novo construtor recebe duas structs e um XMLGen, essas duas structs
	tem a função de fornecer os valores a serem manipulados e a indicação dos campos que possuem valor.
	O XMLGen continua existindo na declaração pois os métodos de consulta devolverão os dados diretamente
	ao XMLGen, assim não sendo necessário criar um segundo processo para a conversão do resultado diferente.
	@autor Renato Teixeira
	@since 21/10/2004
*/
cWFAtendimentoFila::cWFAtendimentoFila(st_Atendimento* dados, st_vlAtendimento* status, XMLGen*xml_g)
{
	memcpy(&m_stDados, dados, sizeof(m_stDados));
	memcpy(&m_vlDados, status, sizeof(m_vlDados));
	
    memset(&m_stFila,0,sizeof(m_stFila));
	memset(&m_vlFila,-1,sizeof(m_vlFila));

    entrada = 0;
	saida   = xml_g;
}

/**
	Esse construtor foi desenvolvido levando em conta as necessidades do SSKlunk10.
	@autor Renato Teixeira
	@since 21/10/2004
*/
cWFAtendimentoFila::cWFAtendimentoFila(DOMNode*dnode, XMLGen*xml_g)
{
	entrada = dnode;
	saida   = xml_g;
	
    memset(&m_stFila,0,sizeof(m_stFila));
	memset(&m_vlFila,-1,sizeof(m_vlFila));

	carregaDados();
}

bool cWFAtendimentoFila::consultarFila()
{
	return consultarFila(NULL);
}
	
bool cWFAtendimentoFila::consultarFila(Collection* _grupos)
{
	carregaDadosConsultaFila();

	if ( _grupos )
	{
		m_stFila.gruposUsuario = _grupos;
		m_vlFila.gruposUsuario = 1;
	}

	// return proCConsultaWFAtendimentoFila(1, &m_stFila, &m_vlFila, saida);
	return true;
}

bool cWFAtendimentoFila::consultarFila(Collection* _grupos, int idPessoaUsuario)
{
	carregaDadosConsultaFila();

	ULOG("Obteniendo la fila para el usuario =  [%d]", idPessoaUsuario);

	m_stFila.idUsuarioGrupo = idPessoaUsuario;
	m_vlFila.idUsuarioGrupo = 1;

	ULOG("Filtro por data de Fechamento =  [%d]", m_stFila.dtFechamentoInicio);
	ULOG("Filtro por Campos dinamicos =  [%d]", m_stFila.nrCampos);
	ULOG("Filtro por numero de linha =  [%s]", m_stFila.nrLinha);
	ULOG("Filtro por usuario =  [%d]", m_stFila.idPessoaUsuario);
	ULOG("Filtro por grupo =  [%d]", m_stFila.idGrupo);
	ULOG("Filtro por data de Abertura Inicio =  [%s]", m_stFila.dtAberturaInicio);
	ULOG("Filtro por data de Abertura Fim =  [%s]", m_stFila.dtAberturaFim);
	ULOG("Filtro por Atendimento =  [%d]", m_stFila.idAtendimento);
	ULOG("Filtro por Contato =  [%d]", m_stFila.idContato);
	ULOG("Filtro por Estado =  [%d]", m_stFila.idEstado);
	ULOG("Filtro por SubEstado =  [%d]", m_stFila.idSubEstado);
	ULOG("Filtro por UsuarioGrupo =  [%d]", m_stFila.idUsuarioGrupo);


	ULOG("Filtro por data de Fechamento =  [%d]", m_vlFila.dtFechamentoInicio);
	ULOG("Filtro por Campos dinamicos =  [%d]", m_vlFila.nrCampos);
	ULOG("Filtro por numero de linha =  [%d]", m_vlFila.nrLinha);
	ULOG("Filtro por usuario =  [%d]", m_vlFila.idPessoaUsuario);
	ULOG("Filtro por grupo =  [%d]", m_vlFila.idGrupo);
	ULOG("Filtro por data de Abertura Inicio =  [%d]", m_vlFila.dtAberturaInicio);
	ULOG("Filtro por data de Abertura Fim =  [%d]", m_vlFila.dtAberturaFim);
	ULOG("Filtro por Atendimento =  [%d]", m_vlFila.idAtendimento);
	ULOG("Filtro por Contato =  [%d]", m_vlFila.idContato);
	ULOG("Filtro por Estado =  [%d]", m_vlFila.idEstado);
	ULOG("Filtro por SubEstado =  [%d]", m_vlFila.idSubEstado);
	ULOG("Filtro por UsuarioGrupo =  [%d]", m_vlFila.idUsuarioGrupo);

    return true;
		
}

bool cWFAtendimentoFila::consultarFila(int idPessoaUsuario)
{
	carregaDadosConsultaFila();

	m_stFila.idUsuarioGrupo = idPessoaUsuario;
	m_vlFila.idUsuarioGrupo = 1;

    return true;
		
}

bool cWFAtendimentoFila::consultarBox()
{
	carregaDadosConsultaFila();

    return true;
}

bool cWFAtendimentoFila::consultarBox(int idPessoaUsuario)
{
	carregaDadosConsultaFila();

	m_stFila.idPessoaUsuario = idPessoaUsuario;
	m_vlFila.idPessoaUsuario = 1;

    return true;
}

bool cWFAtendimentoFila::consultarRelacionamento()
{
	carregaDadosConsultaFila();

    return true;
}

bool cWFAtendimentoFila::consultarMassa()
{
	carregaDadosConsultaFila();

    return true;
}


/**
	Esse método é usado para carregar os dados usados na classe a partir de um DOMNode.
	Os dados são carregados em duas estruturas, sendo a primeira, a que contém o valor obtido e a segunda
	indicado que aquele atributo da primeira estrutrua possui valor.
	O método é chamado pelo construtor que recebe como parametro um DOMNode e um XMLGen.
	@autor Renato Teixeira
	@since 21/10/2004
*/
void cWFAtendimentoFila::carregaDados()
{

	// Inicializa as estruturas de dados para armazenar os valores.
	memset(&m_stDados,0,sizeof(m_stDados));
	memset(&m_vlDados,-1,sizeof(m_vlDados));

	char* p;

	if (p = tx.walkTree( entrada, "idAtendimento", 0 ), p) 
	{
		m_stDados.idAtendimento = atol(p);
		m_vlDados.idAtendimento = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "dtAbertura", 0 ), p) 
	{
		strcpy(m_stDados.dtAbertura, p );
		m_vlDados.dtAbertura = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idContato", 0 ), p) 
	{
		m_stDados.idContato = atoi(p);
		m_vlDados.idContato = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "dtPrazoFinalInterno", 0 ), p) 
	{
		strcpy(m_stDados.dtPrazoFinalInterno, p );
		m_vlDados.dtPrazoFinalInterno = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "qtInsistencia", 0 ), p) 
	{
		m_stDados.qtInsistencia = atoi(p);
		m_vlDados.qtInsistencia = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "inAlarme", 0 ), p) 
	{
		m_stDados.inAlarme = atoi(p);
		m_vlDados.inAlarme = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idCanal", 0 ), p) 
	{
		m_stDados.idCanal = atoi(p);
		m_vlDados.idCanal = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idProcedencia", 0 ), p) 
	{
		m_stDados.idProcedencia = atoi(p);
		m_vlDados.idProcedencia = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idTipoCarteira", 0 ), p) 
	{
		m_stDados.idTipoCarteira = atoi(p);
		m_vlDados.idTipoCarteira = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idSegmentacao", 0 ), p) 
	{
		m_stDados.idSegmentacao = atoi(p);
		m_vlDados.idSegmentacao = 1;
		XMLString::release(&p);
	}

    // Remodelagem Atendimento - Fev/2007
	//if (p = tx.walkTree( entrada, "idDiscador", 0 ), p) 
	//{
	//	m_stDados.idDiscador = atoi(p);
	//	m_vlDados.idDiscador = 1;
	//	XMLString::release(&p);
	//}

	if (p = tx.walkTree( entrada, "idPessoaUsuarioAbertura", 0 ), p) 
	{
		m_stDados.idPessoaUsuarioAbertura = atol(p);
		m_vlDados.idPessoaUsuarioAbertura = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idGrupoAbertura", 0 ), p) 
	{
		m_stDados.idGrupoAbertura = atoi(p);
		m_vlDados.idGrupoAbertura = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "dtPrazoFinalAnatel", 0 ), p) 
	{
		strcpy( m_stDados.dtPrazoFinalAnatel , p);
		m_vlDados.dtPrazoFinalAnatel = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "nrNivel", 0 ), p) 
	{
		m_stDados.nrNivel = atoi(p);
		m_vlDados.nrNivel = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idFase", 0 ), p) 
	{
		m_stDados.idFase = atoi(p);
		m_vlDados.idFase = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "qtHorasPrazoAtendimento", 0 ), p) 
	{
		m_stDados.qtHorasPrazoAtendimento = atoi(p);
		m_vlDados.qtHorasPrazoAtendimento = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "vlPesoAtendimento", 0 ), p) 
	{
        SAFE_STRNCPY(m_stDados.vlPesoAtendimento,p);
		m_vlDados.vlPesoAtendimento = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idTipoRetornoContato", 0 ), p) 
	{
		m_stDados.idTipoRetornoContato = atoi(p);
		m_vlDados.idTipoRetornoContato = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idSequencia", 0 ), p) 
	{
		m_stDados.idSequencia = atoi(p);
		m_vlDados.idSequencia = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idUsuarioAlteracao", 0 ), p) 
	{
		m_stDados.idUsuarioAlteracao = atoi(p);
		m_vlDados.idUsuarioAlteracao = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "dtUltimaAlteracao", 0 ), p) 
	{
		strcpy( m_stDados.dtUltimaAlteracao , p );
		m_vlDados.dtUltimaAlteracao = 1;
		XMLString::release(&p);
	}
}


void cWFAtendimentoFila::carregaDadosConsultaFila()
{
	// Inicializa as estruturas de dados para armazenar os valores.

	ULOG_START( "cWFAtendimentoFila::carregaDadosConsultaFila()" );

	memset(&m_stFila,0,sizeof(m_stFila));
	memset(&m_vlFila,-1,sizeof(m_vlFila));

	char* p;
	DOMNode* dom;

	if (p = tx.walkTree( entrada, "idGrupo", 0 ), p) 
	{
		m_stFila.idGrupo = atoi(p);
		m_vlFila.idGrupo = 1;
		XMLString::release(&p);
		ULOG("WFAtendimento - Pesquisa Fila - Encontrado idGrupo [%i]", m_stFila.idGrupo);
	}

	if (p = tx.walkTree( entrada, "idContato", 0 ), p) 
	{
		if (strlen(p) > 0)
		{
			m_stFila.idContato = atoi(p);
			m_vlFila.idContato = 1;
		}
		XMLString::release(&p);
		ULOG("WFAtendimento - Pesquisa Fila - Encontrado idContato [%i]", m_stFila.idContato);
	}

	if (p = tx.walkTree( entrada, "idEstado", 0 ), p) 
	{
		if (strlen(p) > 0)
		{
			m_stFila.idEstado = atoi(p);
			m_vlFila.idEstado = 1;
		}
		XMLString::release(&p);
		ULOG("WFAtendimento - Pesquisa Fila - Encontrado idEstado [%i]", m_stFila.idEstado);
	}

	if (p = tx.walkTree( entrada, "idSubEstado", 0 ), p) 
	{
		if (strlen(p) > 0)
		{
			m_stFila.idSubEstado = atoi(p);
			m_vlFila.idSubEstado = 1;
		}
		XMLString::release(&p);
		ULOG("WFAtendimento - Pesquisa Fila - Encontrado idSubEstado [%i]", m_stFila.idSubEstado);
	}

	if (p = tx.walkTree( entrada, "idPessoaUsuario", 0 ), p) 
	{
		m_stFila.idPessoaUsuario = atoi(p);
		m_vlFila.idPessoaUsuario = 1;
		XMLString::release(&p);
		ULOG("WFAtendimento - Pesquisa Fila - Encontrado idPessoaUsuario [%i]", m_stFila.idPessoaUsuario);
	}

	if (p = tx.walkTree( entrada, "dtAberturaInicio", 0 ), p) 
	{
		if (strlen(p) > 0)
		{
			strcpy(m_stFila.dtAberturaInicio, p );
			m_vlFila.dtAberturaInicio= 1;
		}
		XMLString::release(&p);
		ULOG("WFAtendimento - Pesquisa Fila - dtAberturaInicio idAtendimento [%s]", m_stFila.dtAberturaInicio);
	}

	if (p = tx.walkTree( entrada, "dtAberturaFim", 0 ), p) 
	{
		if (strlen(p) > 0)
		{
			strcpy(m_stFila.dtAberturaFim, p );
			m_vlFila.dtAberturaFim= 1;
		}		
		XMLString::release(&p);
		ULOG("WFAtendimento - Pesquisa Fila - Encontrado dtAberturaFim [%s]", m_stFila.dtAberturaFim);
	}

	if (p = tx.walkTree( entrada, "dtFechamentoInicio", 0 ), p) 
	{
		if (strlen(p) > 0)
		{
			strcpy(m_stFila.dtFechamentoInicio, p );
			m_vlFila.dtFechamentoInicio= 1;
		}
		XMLString::release(&p);
		ULOG("WFAtendimento - Pesquisa Fila - Encontrado dtFechamentoInicio [%s]", m_stFila.dtFechamentoInicio);
	}

	if (p = tx.walkTree( entrada, "dtFechamentoFim", 0 ), p) 
	{
		if (strlen(p) > 0)
		{
			strcpy(m_stFila.dtFechamentoFim, p );
			m_vlFila.dtFechamentoFim= 1;
		}
		XMLString::release(&p);
		ULOG("WFAtendimento - Pesquisa Fila - Encontrado dtFechamentoFim [%s]", m_stFila.dtFechamentoFim);
	}

	if (p = tx.walkTree( entrada, "pesquisa", 0 ), p) 
	{
		strcpy(m_stFila.pesquisa, p );
		m_vlFila.pesquisa= 1;
		XMLString::release(&p);
		ULOG("WFAtendimento - Pesquisa Fila - Encontrado pesquisa [%s]", m_stFila.pesquisa);
	}

	if (p = tx.walkTree( entrada, "tpPesquisa", 0 ), p) 
	{
		m_stFila.tpPesquisa = atoi(p);
		m_vlFila.tpPesquisa = 1;
		XMLString::release(&p);
		ULOG("WFAtendimento - Pesquisa Fila - Encontrado tpPesquisa [%i]", m_stFila.tpPesquisa);
	}

	if (p = tx.walkTree( entrada, "idPessoaDePara", 0 ), p) 
	{
		m_stFila.idPessoaDePara = atoi(p);
		m_vlFila.idPessoaDePara = 1;
		XMLString::release(&p);
		ULOG("WFAtendimento - Pesquisa Fila - Encontrado idPessoaDePara [%i]", m_stFila.idPessoaDePara);
	}

	if (p = tx.walkTree( entrada, "tpRelacionamento", 0 ), p) 
	{
		m_stFila.tpRelacionamento = atoi(p);
		m_vlFila.tpRelacionamento = 1;
		XMLString::release(&p);
		ULOG("WFAtendimento - Pesquisa Fila - Encontrado tpRelacionamento [%i]", m_stFila.tpRelacionamento);
	}

	if (p = tx.walkTree( entrada, "idPessoaLinhaHistorico", 0 ), p) 
	{
		m_stFila.idPessoaLinhaHistorico = atol(p);
		m_vlFila.idPessoaLinhaHistorico = 1;
		XMLString::release(&p);
		ULOG("WFAtendimento - Pesquisa Fila - Encontrado idPessoaLinhaHistorico [%ld]", m_stFila.idPessoaLinhaHistorico);
	}

	if (p = tx.walkTree( entrada, "textoContato", 0 ), p) 
	{
		strcpy(m_stFila.textoContato, p );
		m_vlFila.textoContato = 1;
		XMLString::release(&p);
		ULOG("WFAtendimento - Pesquisa Fila - Encontrado textoContato [%s]", m_stFila.textoContato);
	}

	if (p = tx.walkTree( entrada, "idAtendimento", 0 ), p) 
	{
		if (strlen(p) > 0)
		{
			m_stFila.idAtendimento = atol(p);
			m_vlFila.idAtendimento = 1;
		}
		XMLString::release(&p);
		ULOG("WFAtendimento - Pesquisa Fila - Encontrado idAtendimento [%i]", m_stFila.idAtendimento);
	}

	if (p = tx.walkTree( entrada, "nrLinha", 0 ), p) 
	{
		if (strlen(p) > 0)
		{
			strcpy(m_stFila.nrLinha, p );
			m_vlFila.nrLinha = 1;
			ULOG("WFAtendimento - Pesquisa Fila - Encontrado nrLinha [%s]", m_stFila.nrLinha);
		}
		XMLString::release(&p);
	}


	if (dom = tx.walkDOM( entrada, "WFPesquisaAvancadaVO", 0 ), dom) 
	{
		m_stFila.pesquisaDinamica = dom;
		m_vlFila.pesquisaDinamica = 1;
		ULOG("WFAtendimento - Pesquisa Fila - Encontrado pesquisaDinamica...");
		if (p = tx.walkTree( dom, "nrCampos", 0 ), p) 
	        {
		    m_stFila.nrCampos = atoi(p);
		    m_vlFila.nrCampos = 1;
		    XMLString::release(&p);
        	}
	}

	if (p = tx.walkTree( entrada, "dtSuspeitoInicio", 0 ), p) 
	{
		strcpy(m_stFila.dtSuspeitoInicio, p );
		m_vlFila.dtSuspeitoInicio = 1;
		XMLString::release(&p);
		ULOG("WFAtendimento - Pesquisa Fila - Encontrado dtSuspeitoInicio [%s]", m_stFila.dtSuspeitoInicio);
	}

	if (p = tx.walkTree( entrada, "dtSuspeitoFim", 0 ), p) 
	{
		strcpy(m_stFila.dtSuspeitoFim, p );
		m_vlFila.dtSuspeitoFim = 1;
		XMLString::release(&p);
		ULOG("WFAtendimento - Pesquisa Fila - Encontrado dtSuspeitoFim [%s]", m_stFila.dtSuspeitoFim);
	}

	if (p = tx.walkTree( entrada, "nmLoginUsuario", 0 ), p) 
	{
		strcpy(m_stFila.nmLoginUsuario, p );
		m_vlFila.nmLoginUsuario = 1;
		XMLString::release(&p);
		ULOG("WFAtendimento - Pesquisa Fila - Encontrado nmLoginUsuario [%s]", m_stFila.nmLoginUsuario);
	}

	if (p = tx.walkTree( entrada, "dsAndamentoObservacao", 0 ), p) 
	{
		strcpy(m_stFila.dsAndamentoObservacao, p );
		m_vlFila.dsAndamentoObservacao = 1;
		XMLString::release(&p);
		ULOG("WFAtendimento - Pesquisa Fila - Encontrado dsAndamentoObservacao [%s]", m_stFila.dsAndamentoObservacao);
	}

	if (dom = tx.walkDOM( entrada, "GruposDoUsuario", 0 ), dom) 
	{
		m_stFila.gruposUsuario = new Collection();
		char* grupo;
		char* x;
		int contador = 0;

		while (grupo = tx.walkTree(dom, "idGrupoFiltro", contador++) )
		{
            if ( x = new char[strlen(grupo)+1],x )
            {
			    strcpy(x, grupo);
                m_stFila.gruposUsuario->AddItem((void*) x);

                ULOG("<idGrupoFiltro>=%s",grupo);
            }
			XMLString::release(&grupo);
		}

		m_vlFila.gruposUsuario = 1;
		ULOG("WFAtendimento - Pesquisa Fila - Encontrado gruposUsuario - registro = [%i]", contador);
	}

	if (p = tx.walkTree( entrada, "inPrimeiraChamada", 0 ), p) 
	{
		m_stFila.inPrimeiraChamada = atoi(p);
		m_vlFila.inPrimeiraChamada = 1;
		XMLString::release(&p);
		ULOG("WFAtendimento - Pesquisa Fila - Encontrado inPrimeiraChamada [%i]", m_stFila.inPrimeiraChamada);
	}

	if (p = tx.walkTree( entrada, "tabPausa", 0 ), p) 
	{
		m_stFila.tbPausa = atoi(p);
		m_vlFila.tbPausa = 1;
		XMLString::release(&p);
		ULOG("WFAtendimento - Pesquisa Fila - Encontrado tbPausa [%i]	", m_stFila.tbPausa);
	}

	ULOG_END( "cWFAtendimentoFila::carregaDadosConsultaFila()" );
}
