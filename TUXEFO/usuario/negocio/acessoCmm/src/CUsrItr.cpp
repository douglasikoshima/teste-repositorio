#include <stdio.h>
#include "CUsrItr.h"

CUsrItr::CUsrItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
    _tamBloco    = 100;
	stcrUsuario    = NULL;
}

CUsrItr::~CUsrItr()
{
	ZeraUsuario();
}

int CUsrItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CUsrItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CUsrItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;
	return _iPosicao;
}

int CUsrItr::Ultimo( void )
{
	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;
	return _iPosicao;
}

int CUsrItr::Quantidade( void )
{
	return _iQuantidade;
}

STUsuarioRegistro* CUsrItr::Registro( void )
{
	if( _iQuantidade > 0 )
	{
		return &stcrUsuario[ _iPosicao ];
	}

	return 0;
}

STUsuarioRegistro* CUsrItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrUsuario[ nPosicao ];
		
	}

	return 0;
}

void CUsrItr::Add( char* cidUsuario
			 	  ,char* cnome
			 	  ,char* csobrenome
			 	  ,char* cemail
			 	  ,char* cidDDD
			 	  ,char* cdsDDD
			 	  ,char* cTelefone
			 	  ,char* clogin
			 	  ,char* cloginCti
			 	  ,char* cidStatusAtual
			 	  ,char* cdescricaoStatusAtual
			 	  ,char* cidMotivo
			 	  ,char* cdsMotivo
			 	  ,char* cdtInicio
			 	  ,char* cdtRetorno
			 	  ,char* cidCargoAtual
			 	  ,char* cdescricaoCargoAtual
			 	  ,char* cloginChefe
			 	  ,char* cnomeChefe
			 	  ,char* cidUF
			 	  ,char* cnmUF
			 	  ,char* cdtInclusao
			 	  ,char* cdtExclusao
			 	  ,char* cinConsultor
				  ,char* cidUFOperadora
				  ,char* cidPerfilConsultorAtd
				  ,char* cidFornecedorConsultorAtd
				  ,char* cidSiteConsultorAtd
                  ,char* cdsLoginRoteamento
                 )
{
    // Só realoca quando preencher o bloco anterior, ou quando for a primeira vez--Junho,2006 - Cassio
    if ( (_iQuantidade % _tamBloco) == 0 )
    {
        int Resto = _iQuantidade / _tamBloco;
        int tamBlocoAtu = (Resto+1) * _tamBloco;

        stcrUsuario = 
                (struct STUsuarioRegistro*)
                    realloc( stcrUsuario, sizeof(STUsuarioRegistro)*tamBlocoAtu );
    }

	memset( &stcrUsuario[_iQuantidade], 0, sizeof( STUsuarioRegistro ) );
	
	strcpy( stcrUsuario[_iQuantidade].cidUsuario               ,cidUsuario               );
	strcpy( stcrUsuario[_iQuantidade].cnome                    ,cnome                    );
	strcpy( stcrUsuario[_iQuantidade].csobrenome               ,csobrenome               );
	strcpy( stcrUsuario[_iQuantidade].cemail                   ,cemail                   );
	strcpy( stcrUsuario[_iQuantidade].cidDDD                   ,cidDDD                   );
	strcpy( stcrUsuario[_iQuantidade].cdsDDD                   ,cdsDDD                   );
	strcpy( stcrUsuario[_iQuantidade].cTelefone                ,cTelefone                );
	strcpy( stcrUsuario[_iQuantidade].clogin                   ,clogin                   );
	strcpy( stcrUsuario[_iQuantidade].cloginCti                ,cloginCti                );
	strcpy( stcrUsuario[_iQuantidade].cidStatusAtual           ,cidStatusAtual           );
	strcpy( stcrUsuario[_iQuantidade].cdescricaoStatusAtual    ,cdescricaoStatusAtual    );
	strcpy( stcrUsuario[_iQuantidade].cidMotivo                ,cidMotivo                );
	strcpy( stcrUsuario[_iQuantidade].cdsMotivo                ,cdsMotivo                );
	strcpy( stcrUsuario[_iQuantidade].cdtInicio                ,cdtInicio                );
	strcpy( stcrUsuario[_iQuantidade].cdtRetorno               ,cdtRetorno               );
	strcpy( stcrUsuario[_iQuantidade].cidCargoAtual            ,cidCargoAtual            );
	strcpy( stcrUsuario[_iQuantidade].cdescricaoCargoAtual     ,cdescricaoCargoAtual     );
	strcpy( stcrUsuario[_iQuantidade].cloginChefe              ,cloginChefe              );
	strcpy( stcrUsuario[_iQuantidade].cnomeChefe               ,cnomeChefe               );
	strcpy( stcrUsuario[_iQuantidade].cidUF                    ,cidUF                    );
	strcpy( stcrUsuario[_iQuantidade].cnmUF                    ,cnmUF                    );
	strcpy( stcrUsuario[_iQuantidade].cdtInclusao              ,cdtInclusao              );
	strcpy( stcrUsuario[_iQuantidade].cdtExclusao              ,cdtExclusao              );
	strcpy( stcrUsuario[_iQuantidade].cinConsultor             ,cinConsultor             );
	strcpy( stcrUsuario[_iQuantidade].cidUFOperadora           ,cidUFOperadora           );
	strcpy( stcrUsuario[_iQuantidade].cidPerfilConsultorAtd    ,cidPerfilConsultorAtd    );
	strcpy( stcrUsuario[_iQuantidade].cidFornecedorConsultorAtd,cidFornecedorConsultorAtd);
	strcpy( stcrUsuario[_iQuantidade].cidSiteConsultorAtd      ,cidSiteConsultorAtd      );
	strcpy( stcrUsuario[_iQuantidade].cdsLoginRoteamento       ,cdsLoginRoteamento       );

	_iQuantidade++;
}

void CUsrItr::Add(   char* cidUsuario
					,char* cnome
					,char* clogin
					,char* cloginCti
					,char* cloginChefe
					,char* cinConsultor
					,char* cidStatusAtual
					,char* csiglaStatusAtual
					,char* cdescricaoStatusAtual
					,char* cidPerfilConsultorAtd
					,char* cidFornecedorConsultorAtd
					,char* cidSiteConsultorAtd
                    ,char* cdsLoginRoteamento
                    ,char* cdsFornecedorConsultorAtd
                    ,char* cdsSiteConsultorAtd
				)
{
    if ( (_iQuantidade % _tamBloco) == 0 )
    {
        int Resto = _iQuantidade / _tamBloco;
        int tamBlocoAtu = (Resto+1) * _tamBloco;

        stcrUsuario = 
                (struct STUsuarioRegistro*)
                    realloc( stcrUsuario, sizeof(STUsuarioRegistro)*tamBlocoAtu );
    }

	memset( &stcrUsuario[_iQuantidade], 0, sizeof( STUsuarioRegistro ) );

	strcpy( stcrUsuario[_iQuantidade].cidUsuario               ,cidUsuario               );
	strcpy( stcrUsuario[_iQuantidade].cnome                    ,cnome                    );
	strcpy( stcrUsuario[_iQuantidade].clogin                   ,clogin                   );
	strcpy( stcrUsuario[_iQuantidade].cloginCti                ,cloginCti                );
	strcpy( stcrUsuario[_iQuantidade].cloginChefe              ,cloginChefe              );
	strcpy( stcrUsuario[_iQuantidade].cinConsultor             ,cinConsultor             );
	strcpy( stcrUsuario[_iQuantidade].cidStatusAtual           ,cidStatusAtual           );
	strcpy( stcrUsuario[_iQuantidade].csiglaStatusAtual        ,csiglaStatusAtual        );
	strcpy( stcrUsuario[_iQuantidade].cdescricaoStatusAtual    ,cdescricaoStatusAtual    );
	strcpy( stcrUsuario[_iQuantidade].cidPerfilConsultorAtd    ,cidPerfilConsultorAtd    );
	strcpy( stcrUsuario[_iQuantidade].cidFornecedorConsultorAtd,cidFornecedorConsultorAtd);
	strcpy( stcrUsuario[_iQuantidade].cidSiteConsultorAtd      ,cidSiteConsultorAtd      );
	strcpy( stcrUsuario[_iQuantidade].cdsLoginRoteamento       ,cdsLoginRoteamento       );
	strcpy( stcrUsuario[_iQuantidade].cdsFornecedorConsultorAtd,cdsFornecedorConsultorAtd);
	strcpy( stcrUsuario[_iQuantidade].cdsSiteConsultorAtd      ,cdsSiteConsultorAtd      );

	_iQuantidade++;
}

void CUsrItr::Add(   char* cidUsuario
					,char* cnome
					,char* clogin
					,char* cloginCti
					,char* cloginChefe
					,char* cinConsultor
					,char* cidStatusAtual
					,char* csiglaStatusAtual
					,char* cdescricaoStatusAtual
					,char* cdsSessaoUsuario
				)
{
    if ( (_iQuantidade % _tamBloco) == 0 )
    {
        int Resto = _iQuantidade / _tamBloco;
        int tamBlocoAtu = (Resto+1) * _tamBloco;

        stcrUsuario = 
                (struct STUsuarioRegistro*)
                    realloc( stcrUsuario, sizeof(STUsuarioRegistro)*tamBlocoAtu );
    }

	memset( &stcrUsuario[_iQuantidade], 0, sizeof( STUsuarioRegistro ) );

	strcpy( stcrUsuario[_iQuantidade].cidUsuario            ,cidUsuario            );
	strcpy( stcrUsuario[_iQuantidade].cnome                 ,cnome                 );
	strcpy( stcrUsuario[_iQuantidade].clogin                ,clogin                );
	strcpy( stcrUsuario[_iQuantidade].cloginCti             ,cloginCti             );
	strcpy( stcrUsuario[_iQuantidade].cloginChefe           ,cloginChefe           );
	strcpy( stcrUsuario[_iQuantidade].cinConsultor          ,cinConsultor          );
	strcpy( stcrUsuario[_iQuantidade].cidStatusAtual        ,cidStatusAtual        );
	strcpy( stcrUsuario[_iQuantidade].csiglaStatusAtual     ,csiglaStatusAtual     );
	strcpy( stcrUsuario[_iQuantidade].cdescricaoStatusAtual ,cdescricaoStatusAtual );
	strcpy( stcrUsuario[_iQuantidade].cdsSessaoUsuario      ,cdsSessaoUsuario      );

    _iQuantidade++;
}

void CUsrItr::ZeraUsuario( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrUsuario );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrUsuario    = NULL;
}