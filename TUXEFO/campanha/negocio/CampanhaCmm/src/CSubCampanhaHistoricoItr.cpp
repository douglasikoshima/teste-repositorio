#include <stdio.h>
#include <CSubCampanhaHistoricoItr.h>

CSubCampanhaHistoricoItr::CSubCampanhaHistoricoItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrSubCampanhaHistorico    = NULL;
}

CSubCampanhaHistoricoItr::~CSubCampanhaHistoricoItr()
{
	ZeraSubCampanhaHistorico();
}

int CSubCampanhaHistoricoItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CSubCampanhaHistoricoItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CSubCampanhaHistoricoItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CSubCampanhaHistoricoItr::Ultimo( void )
{
	
	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CSubCampanhaHistoricoItr::Quantidade( void )
{
	return _iQuantidade;
}

STSubCampanhaHistoricoRegistro* CSubCampanhaHistoricoItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrSubCampanhaHistorico[ _iPosicao ];
	else
		return 0;
}

STSubCampanhaHistoricoRegistro* CSubCampanhaHistoricoItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrSubCampanhaHistorico[ nPosicao ];
		
	}
	else
		return 0;
}

void CSubCampanhaHistoricoItr::Add( char* cidSubCampanhaHistorico, 
							        char* cidSubCampanhaFixa, 
								    char* cdsScriptSubCampanha, 
								    char* cinClienteTelefonica, 
								    char* cqtMaximaAgenda,
								    char* cdtInicio,
								    char* cdtTermino,
								    char* csqVersao,
									char* cinReincidente,
									char* cidTipoCampanha,
									char* sqApresentacao,
									char* cdsNmSubCampanha )
{
	_iQuantidade++;
	stcrSubCampanhaHistorico = (struct STSubCampanhaHistoricoRegistro*) 
		realloc( stcrSubCampanhaHistorico, 
		sizeof(STSubCampanhaHistoricoRegistro)*_iQuantidade );
	strcpy( stcrSubCampanhaHistorico[_iQuantidade-1].cidSubCampanhaHistorico, cidSubCampanhaHistorico );
	strcpy( stcrSubCampanhaHistorico[_iQuantidade-1].cidSubCampanhaFixa, cidSubCampanhaFixa );
	strcpy( stcrSubCampanhaHistorico[_iQuantidade-1].cdsScriptSubCampanha, cdsScriptSubCampanha );
	strcpy( stcrSubCampanhaHistorico[_iQuantidade-1].cinClienteTelefonica, cinClienteTelefonica );
	strcpy( stcrSubCampanhaHistorico[_iQuantidade-1].cqtMaximaAgenda, cqtMaximaAgenda );
	strcpy( stcrSubCampanhaHistorico[_iQuantidade-1].cdtInicio , cdtInicio );
	strcpy( stcrSubCampanhaHistorico[_iQuantidade-1].cdtTermino, cdtTermino );
	strcpy( stcrSubCampanhaHistorico[_iQuantidade-1].csqVersao   , csqVersao );
	strcpy( stcrSubCampanhaHistorico[_iQuantidade-1].cinReincidente, cinReincidente );
	strcpy( stcrSubCampanhaHistorico[_iQuantidade-1].cidTipoCampanha   , cidTipoCampanha );
	strcpy( stcrSubCampanhaHistorico[_iQuantidade-1].sqApresentacao   , sqApresentacao );
	strcpy( stcrSubCampanhaHistorico[_iQuantidade-1].cdsNmSubCampanha   , cdsNmSubCampanha );
}

void CSubCampanhaHistoricoItr::Add( char* cidSubCampanhaHistorico, 
							        char* cidSubCampanhaFixa, 
								    char* cdsScriptSubCampanha, 
								    char* cinClienteTelefonica, 
								    char* cqtMaximaAgenda,
								    char* cdtInicio,
								    char* cdtTermino,
								    char* csqVersao,
									char* cinReincidente,
									char* cidTipoCampanha,
									char* sqApresentacao,
									char* cnmSubCampanha,
									char* cinDisponibilidade )
{
	_iQuantidade++;
	stcrSubCampanhaHistorico = (struct STSubCampanhaHistoricoRegistro*) 
		realloc( stcrSubCampanhaHistorico, 
		sizeof(STSubCampanhaHistoricoRegistro)*_iQuantidade );
	strcpy( stcrSubCampanhaHistorico[_iQuantidade-1].cidSubCampanhaHistorico, cidSubCampanhaHistorico );
	strcpy( stcrSubCampanhaHistorico[_iQuantidade-1].cidSubCampanhaFixa, cidSubCampanhaFixa );
	strcpy( stcrSubCampanhaHistorico[_iQuantidade-1].cdsScriptSubCampanha, cdsScriptSubCampanha );
	strcpy( stcrSubCampanhaHistorico[_iQuantidade-1].cinClienteTelefonica, cinClienteTelefonica );
	strcpy( stcrSubCampanhaHistorico[_iQuantidade-1].cqtMaximaAgenda, cqtMaximaAgenda );
	strcpy( stcrSubCampanhaHistorico[_iQuantidade-1].cdtInicio , cdtInicio );
	strcpy( stcrSubCampanhaHistorico[_iQuantidade-1].cdtTermino, cdtTermino );
	strcpy( stcrSubCampanhaHistorico[_iQuantidade-1].csqVersao, csqVersao );
	strcpy( stcrSubCampanhaHistorico[_iQuantidade-1].cinReincidente, cinReincidente );
	strcpy( stcrSubCampanhaHistorico[_iQuantidade-1].cidTipoCampanha, cidTipoCampanha );
	strcpy( stcrSubCampanhaHistorico[_iQuantidade-1].sqApresentacao, sqApresentacao );
	strcpy( stcrSubCampanhaHistorico[_iQuantidade-1].cdsNmSubCampanha, cnmSubCampanha );
	strcpy( stcrSubCampanhaHistorico[_iQuantidade-1].cinDisponibilidade, cinDisponibilidade );
}

void CSubCampanhaHistoricoItr::ZeraSubCampanhaHistorico( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrSubCampanhaHistorico );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrSubCampanhaHistorico    = NULL;
}
