/* charls */
#include <stdio.h>
#include "../include/CFlhItr.h"

CContatoFolhaItr::CContatoFolhaItr():tamBlocoCCItr(100)
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrContatoFolha    = NULL;
}

CContatoFolhaItr::~CContatoFolhaItr()
{
	ZeraContatoFolha();
}

int CContatoFolhaItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CContatoFolhaItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CContatoFolhaItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CContatoFolhaItr::Ultimo( void )
{
    _iPosicao = _iQuantidade > 0?_iQuantidade-1:0;
    return _iPosicao;
}

int CContatoFolhaItr::Quantidade( void )
{
	return _iQuantidade;
}

STContatoFolhaRegistro* CContatoFolhaItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrContatoFolha[ _iPosicao ];
	return 0;
}

STContatoFolhaRegistro* CContatoFolhaItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();
		return &stcrContatoFolha[ nPosicao ];
	}

    return 0;
}

void CContatoFolhaItr::Add(  char* cidContato
                            ,char* cdsContato
                            ,char* cidPagina
                            ,char* cdsPagina
                            ,char* cidTipoRetorno
                            ,char* cdsTipoRetorno
                            ,char* cidTipoFechamento
                            ,char* cdsTipoFechamento
                            ,char* cidTipoProcesso
                            ,char* cdsTipoProcesso
                            ,char* cqtHorasPrazo
                            ,char* cinFechamentoImediato
                            ,char* cvlPeso
                            ,char* cinProcessoTecnico
                            ,char* cidMensagem
                            ,char* cnmMensagem
                            ,char* cqtHorasPrazoAnatel
                            ,char* cInSms
                            ,char* cDsSms
                            ,char* cInRelacionamento
                            ,char* cInProtocolo
                            ,char* cInexibeProtocolo
                            ,char* cDsContatoCanais
                            ,char* cDsMsgExcecao
                            ,char* cInCancelamento
                            ,char* cIdClassificacaoSms
                            ,char* cInAberturaContato
                            ,char* cSgRegraEncaminhamento
                            ,char* cSgFluxoAtendimento)
{
    // Só realoca quando preencher o bloco anterior, ou quando for a primeira vez--Cassio--redução de memory leaks
    if ( (_iQuantidade % tamBlocoCCItr) == 0 )
    {
        int Resto = _iQuantidade / tamBlocoCCItr;
        int tamBlocoAtu = (Resto+1) * tamBlocoCCItr;

        stcrContatoFolha = 
            (struct STContatoFolhaRegistro*) 
                realloc(stcrContatoFolha,sizeof(STContatoFolhaRegistro)*tamBlocoAtu);
    }

    //@cassio stcrContatoFolha = 
        // (struct STContatoFolhaRegistro*) 
            // realloc( stcrContatoFolha, sizeof(STContatoFolhaRegistro)*(_iQuantidade+1) );

    //@cassio tuxfw_getlogger()->debug("           cidContato='%s'",cidContato);
    //@cassio tuxfw_getlogger()->debug("           cdsContato='%s'",cdsContato);
    //@cassio tuxfw_getlogger()->debug("            cidPagina='%s'",cidPagina);
    //@cassio tuxfw_getlogger()->debug("            cdsPagina='%s'",cdsPagina);
    //@cassio tuxfw_getlogger()->debug("       cidTipoRetorno='%s'",cidTipoRetorno);
    //@cassio tuxfw_getlogger()->debug("       cdsTipoRetorno='%s'",cdsTipoRetorno);
    //@cassio tuxfw_getlogger()->debug("    cidTipoFechamento='%s'",cidTipoFechamento);
    //@cassio tuxfw_getlogger()->debug("    cdsTipoFechamento='%s'",cdsTipoFechamento);
    //@cassio tuxfw_getlogger()->debug("      cidTipoProcesso='%s'",cidTipoProcesso);
    //@cassio tuxfw_getlogger()->debug("      cdsTipoProcesso='%s'",cdsTipoProcesso);
    //@cassio tuxfw_getlogger()->debug("        cqtHorasPrazo='%s'",cqtHorasPrazo);
    //@cassio tuxfw_getlogger()->debug("cinFechamentoImediato='%s'",cinFechamentoImediato);
    //@cassio tuxfw_getlogger()->debug("              cvlPeso='%s'",cvlPeso);
    //@cassio tuxfw_getlogger()->debug("   cinProcessoTecnico='%s'",cinProcessoTecnico);
    //@cassio tuxfw_getlogger()->debug("          cidMensagem='%s'",cidMensagem);
    //@cassio tuxfw_getlogger()->debug("          cnmMensagem='%s'",cnmMensagem);
    //@cassio tuxfw_getlogger()->debug("  cqtHorasPrazoAnatel='%s'",cqtHorasPrazoAnatel);
    //@cassio tuxfw_getlogger()->debug("               cInSms='%s'",cInSms);
    //@cassio tuxfw_getlogger()->debug("               cDsSms='%s'",cDsSms);
    //@cassio tuxfw_getlogger()->debug("    cInRelacionamento='%s'",cInRelacionamento);
    //@cassio tuxfw_getlogger()->debug("         cInProtocolo='%s'",cInProtocolo);
    //@cassio tuxfw_getlogger()->debug("    cInexibeProtocolo='%s'",cInexibeProtocolo);
    //@cassio tuxfw_getlogger()->debug("     cDsContatoCanais='%s'",cDsContatoCanais);
    //@cassio tuxfw_getlogger()->debug("        cDsMsgExcecao='%s'",cDsMsgExcecao);
    //@cassio tuxfw_getlogger()->debug("      cInCancelamento='%s'",cInCancelamento);
    //@cassio tuxfw_getlogger()->debug("   cInAberturaContato='%s'",cInAberturaContato);

    strcpy( stcrContatoFolha[_iQuantidade].cidContato, cidContato);
    strcpy( stcrContatoFolha[_iQuantidade].cdsContato, cdsContato);
    strcpy( stcrContatoFolha[_iQuantidade].cidPagina, cidPagina);
    strcpy( stcrContatoFolha[_iQuantidade].cdsPagina, cdsPagina);
    strcpy( stcrContatoFolha[_iQuantidade].cidTipoRetorno, cidTipoRetorno);
    strcpy( stcrContatoFolha[_iQuantidade].cdsTipoRetorno, cdsTipoRetorno);
    strcpy( stcrContatoFolha[_iQuantidade].cidTipoFechamento, cidTipoFechamento);
    strcpy( stcrContatoFolha[_iQuantidade].cdsTipoFechamento, cdsTipoFechamento);
    strcpy( stcrContatoFolha[_iQuantidade].cidTipoProcesso, cidTipoProcesso);
    strcpy( stcrContatoFolha[_iQuantidade].cdsTipoProcesso, cdsTipoProcesso);
    strcpy( stcrContatoFolha[_iQuantidade].cqtHorasPrazo, cqtHorasPrazo);
    strcpy( stcrContatoFolha[_iQuantidade].cinFechamentoImediato, cinFechamentoImediato);
    strcpy( stcrContatoFolha[_iQuantidade].cvlPeso, cvlPeso);
    strcpy( stcrContatoFolha[_iQuantidade].cinProcessoTecnico, cinProcessoTecnico);
    strcpy( stcrContatoFolha[_iQuantidade].cidMensagem, cidMensagem);
    strcpy( stcrContatoFolha[_iQuantidade].cnmMensagem, cnmMensagem);
    strcpy( stcrContatoFolha[_iQuantidade].cqtHorasPrazoAnatel, cqtHorasPrazoAnatel);
    strcpy( stcrContatoFolha[_iQuantidade].cInSms, cInSms);
    strcpy( stcrContatoFolha[_iQuantidade].cDsSms, cDsSms);
    strcpy( stcrContatoFolha[_iQuantidade].cInRelacionamento, cInRelacionamento);
    strcpy( stcrContatoFolha[_iQuantidade].cInProtocolo, cInProtocolo);
    strcpy( stcrContatoFolha[_iQuantidade].cInexibeProtocolo, cInexibeProtocolo);
    strcpy( stcrContatoFolha[_iQuantidade].cDsContatoCanais, cDsContatoCanais);
    strcpy( stcrContatoFolha[_iQuantidade].cDsMsgExcecao, cDsMsgExcecao);
    strcpy( stcrContatoFolha[_iQuantidade].cInCancelamento, cInCancelamento);
    strcpy( stcrContatoFolha[_iQuantidade].cIdClassificacaoSms, cIdClassificacaoSms);
    strcpy( stcrContatoFolha[_iQuantidade].cInAberturaContato, cInAberturaContato);
    strcpy( stcrContatoFolha[_iQuantidade].cSgRegraEncaminhamento, cSgRegraEncaminhamento);
    strcpy( stcrContatoFolha[_iQuantidade].cSgFluxoAtendimento, cSgFluxoAtendimento);

	_iQuantidade++;
}


void CContatoFolhaItr::ZeraContatoFolha( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrContatoFolha );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrContatoFolha    = NULL;
}
