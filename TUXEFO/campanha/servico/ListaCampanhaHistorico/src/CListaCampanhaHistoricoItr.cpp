#include <stdio.h>
#include <CListaCampanhaHistoricoItr.h>

CListaCampanhaHistoricoItr::CListaCampanhaHistoricoItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrListaCampanhaHistorico    = NULL;
}

CListaCampanhaHistoricoItr::~CListaCampanhaHistoricoItr()
{
	ZeraListaCampanhaHistorico();
}

int CListaCampanhaHistoricoItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CListaCampanhaHistoricoItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CListaCampanhaHistoricoItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CListaCampanhaHistoricoItr::Ultimo( void )
{
	
	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CListaCampanhaHistoricoItr::Quantidade( void )
{
	return _iQuantidade;
}

STListaCampanhaHistoricoRegistro* CListaCampanhaHistoricoItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrListaCampanhaHistorico[ _iPosicao ];
	else
		return 0;
}

STListaCampanhaHistoricoRegistro* CListaCampanhaHistoricoItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrListaCampanhaHistorico[ nPosicao ];
		
	}
	else
		return 0;
}

void CListaCampanhaHistoricoItr::Add(   char* cidCampanha, 
								        char* cdsCampanha, 
									    char* csgCampanha, 
									    char* cidSubCampanhaHistorico, 
									    char* cdsScriptSubCampanha,
									    char* cidCanal,
									    char* ccdCanal,
									    char* cnmCanal,
									    char* cidMotivoCampanha,
										char* cidAtendimentoCampanha,
										char* cdtAtendimento,
										char* cqtTempoAtendimento,
										char* cidUFOperadora,
										char* cidPessoaDeParaOperadora,
										char* cidTipoStatusCampanha,
										char* cidTipoMotivoCampanha,
										char* cidTipoSubMotivoCampanha,
										char* cidSubCampanhaFixa,
										char* cdsTipoSubMotivoCampanha,
										char* csgTipoSubMotivoCampanha,
										char* cdsTipoStatusCampanha,
										char* csgTipoStatusCampanha,
										char* cdsTipoMotivoCampanha,
										char* csgTipoMotivoCampanha,
										char* cidListaConteudo,
										char* cidPessoaDePara,
										char* cidCanalCampanha,
										char* cidCanalUfOperadora,
										char* csgUfOperadora,
										char* cnmSubCampanha)
{
	_iQuantidade++;
	stcrListaCampanhaHistorico = (struct STListaCampanhaHistoricoRegistro*) 
		realloc( stcrListaCampanhaHistorico, sizeof(STListaCampanhaHistoricoRegistro)*_iQuantidade );
	strcpy( stcrListaCampanhaHistorico[_iQuantidade-1].cidCampanha, cidCampanha );
	strcpy( stcrListaCampanhaHistorico[_iQuantidade-1].cdsCampanha, cdsCampanha );
	strcpy( stcrListaCampanhaHistorico[_iQuantidade-1].csgCampanha, csgCampanha );
	strcpy( stcrListaCampanhaHistorico[_iQuantidade-1].cidSubCampanhaHistorico, cidSubCampanhaHistorico );
	strcpy( stcrListaCampanhaHistorico[_iQuantidade-1].cdsScriptSubCampanha, cdsScriptSubCampanha );
	strcpy( stcrListaCampanhaHistorico[_iQuantidade-1].cidCanal, cidCanal );
	strcpy( stcrListaCampanhaHistorico[_iQuantidade-1].ccdCanal, ccdCanal );
	strcpy( stcrListaCampanhaHistorico[_iQuantidade-1].cnmCanal, cnmCanal );
	strcpy( stcrListaCampanhaHistorico[_iQuantidade-1].cidMotivoCampanha, cidMotivoCampanha );
	strcpy( stcrListaCampanhaHistorico[_iQuantidade-1].cidAtendimentoCampanha, cidAtendimentoCampanha );
	strcpy( stcrListaCampanhaHistorico[_iQuantidade-1].cdtAtendimento, cdtAtendimento );
	strcpy( stcrListaCampanhaHistorico[_iQuantidade-1].cqtTempoAtendimento, cqtTempoAtendimento );
	strcpy( stcrListaCampanhaHistorico[_iQuantidade-1].cidUFOperadora, cidUFOperadora );
	strcpy( stcrListaCampanhaHistorico[_iQuantidade-1].cidPessoaDeParaOperadora, cidPessoaDeParaOperadora );
	strcpy( stcrListaCampanhaHistorico[_iQuantidade-1].cidTipoStatusCampanha, cidTipoStatusCampanha );
	strcpy( stcrListaCampanhaHistorico[_iQuantidade-1].cidTipoMotivoCampanha, cidTipoMotivoCampanha );
	strcpy( stcrListaCampanhaHistorico[_iQuantidade-1].cidTipoSubMotivoCampanha, cidTipoSubMotivoCampanha );
	strcpy( stcrListaCampanhaHistorico[_iQuantidade-1].cidSubCampanhaFixa, cidSubCampanhaFixa );
	strcpy( stcrListaCampanhaHistorico[_iQuantidade-1].cdsTipoSubMotivoCampanha, cdsTipoSubMotivoCampanha );
	strcpy( stcrListaCampanhaHistorico[_iQuantidade-1].csgTipoSubMotivoCampanha, csgTipoSubMotivoCampanha );
	strcpy( stcrListaCampanhaHistorico[_iQuantidade-1].cdsTipoStatusCampanha, cdsTipoStatusCampanha );
	strcpy( stcrListaCampanhaHistorico[_iQuantidade-1].csgTipoStatusCampanha, csgTipoStatusCampanha );
	strcpy( stcrListaCampanhaHistorico[_iQuantidade-1].cdsTipoMotivoCampanha, cdsTipoMotivoCampanha );
	strcpy( stcrListaCampanhaHistorico[_iQuantidade-1].csgTipoMotivoCampanha, csgTipoMotivoCampanha );
	strcpy( stcrListaCampanhaHistorico[_iQuantidade-1].cidListaConteudo, cidListaConteudo );
	strcpy( stcrListaCampanhaHistorico[_iQuantidade-1].cidPessoaDePara, cidPessoaDePara );
	strcpy( stcrListaCampanhaHistorico[_iQuantidade-1].cidCanalCampanha, cidCanalCampanha );
	strcpy( stcrListaCampanhaHistorico[_iQuantidade-1].cidCanalUfOperadora, cidCanalUfOperadora );
	strcpy( stcrListaCampanhaHistorico[_iQuantidade-1].csgUfOperadora, csgUfOperadora);
	strcpy( stcrListaCampanhaHistorico[_iQuantidade-1].cnmSubCampanha, cnmSubCampanha);
}


void CListaCampanhaHistoricoItr::ZeraListaCampanhaHistorico( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrListaCampanhaHistorico );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrListaCampanhaHistorico    = NULL;
}
