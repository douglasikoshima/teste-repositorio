#include <stdio.h>
#include <classGravaLegadoItr.h>

CGravaLegadoItr::CGravaLegadoItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrGravaLegado    = NULL;
}

CGravaLegadoItr::~CGravaLegadoItr()
{
	Zera();
}

int CGravaLegadoItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CGravaLegadoItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CGravaLegadoItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CGravaLegadoItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CGravaLegadoItr::Quantidade( void )
{
	return _iQuantidade;
}

STGravaLegadoRegistro* CGravaLegadoItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrGravaLegado[ _iPosicao ];
	else
		return 0;
}

STGravaLegadoRegistro* CGravaLegadoItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrGravaLegado[ nPosicao ];

	}
	else
		return 0;
}

void CGravaLegadoItr::Add(  
							char* pzcIdPessoa,
				            char* pzcNrLinha,
							char* pzcIdGrupo,
							char* pzcTipoCliente,
							char* pzcNome,
							char* pzcDataNascimento,
							char* pzcEstadoCivil,
							char* pzcCodSexo,
							char* pzcDsRazaoSocial,
							char* pzcIE,
							char* pzcCPF,
							char* pzcRG,
							char* pzcDataExpiracao,
							char* pzcOrgaoExpeditor,
							char* pzcEstadoExpedicao,
							char* pzcPassaporte,
							char* pzcCNPJ,
							char* pzcCNAE,
							char* pzcTelefone,
							char* pzcFax,
							char* pzcEMail,
							char* pzcNumDepend,
							char* pzcNomeContato,
							char* pzcLogradouro,
							char* pzcEndereco,
							char* pzcComplemento,
							char* pzcBairro,
							char* pzcCEP,
							char* pzcCidade,
							char* pzcEstado,
							char* pzcNumero
                         )
{
    // Só realoca quando preencher o bloco anterior, ou quando for a primeira vez
    // Maio, 2007 - Cassio
    int tamBlocoGrupo = 50;
    if ( (_iQuantidade % tamBlocoGrupo) == 0 )
    {
        int Resto = _iQuantidade / tamBlocoGrupo;
        int tamBlocoAtu = (Resto+1) * tamBlocoGrupo;

	    stcrGravaLegado = (struct STGravaLegadoRegistro*) 
                realloc( stcrGravaLegado, sizeof(STGravaLegadoRegistro)*tamBlocoAtu );
    }
	_iQuantidade++;
	//stcrGravaLegado = (struct STGravaLegadoRegistro*) realloc( stcrGravaLegado, sizeof(STGravaLegadoRegistro)*_iQuantidade );
	strcpy( stcrGravaLegado[_iQuantidade-1].pzcIdPessoa,        pzcIdPessoa       );
	strcpy( stcrGravaLegado[_iQuantidade-1].pzcNrLinha,         pzcNrLinha        );
	strcpy( stcrGravaLegado[_iQuantidade-1].pzcIdGrupo,         pzcIdGrupo        );
	strcpy( stcrGravaLegado[_iQuantidade-1].pzcTipoCliente,     pzcTipoCliente    );
	strcpy( stcrGravaLegado[_iQuantidade-1].pzcNome,            pzcNome           );
	strcpy( stcrGravaLegado[_iQuantidade-1].pzcDataNascimento,  pzcDataNascimento );
	strcpy( stcrGravaLegado[_iQuantidade-1].pzcEstadoCivil,     pzcEstadoCivil    );
	strcpy( stcrGravaLegado[_iQuantidade-1].pzcCodSexo,         pzcCodSexo        );
	strcpy( stcrGravaLegado[_iQuantidade-1].pzcDsRazaoSocial,   pzcDsRazaoSocial  );
	strcpy( stcrGravaLegado[_iQuantidade-1].pzcIE,              pzcIE             );
	strcpy( stcrGravaLegado[_iQuantidade-1].pzcCPF,             pzcCPF            );
	strcpy( stcrGravaLegado[_iQuantidade-1].pzcRG,              pzcRG             );
	strcpy( stcrGravaLegado[_iQuantidade-1].pzcDataExpiracao,   pzcDataExpiracao  );
	strcpy( stcrGravaLegado[_iQuantidade-1].pzcOrgaoExpeditor,  pzcOrgaoExpeditor );
	strcpy( stcrGravaLegado[_iQuantidade-1].pzcEstadoExpedicao, pzcEstadoExpedicao);
	strcpy( stcrGravaLegado[_iQuantidade-1].pzcPassaporte,      pzcPassaporte     );
	strcpy( stcrGravaLegado[_iQuantidade-1].pzcCNPJ,            pzcCNPJ           );
	strcpy( stcrGravaLegado[_iQuantidade-1].pzcCNAE,            pzcCNAE           );
	strcpy( stcrGravaLegado[_iQuantidade-1].pzcTelefone,        pzcTelefone       );
	strcpy( stcrGravaLegado[_iQuantidade-1].pzcFax,             pzcFax            );
	strcpy( stcrGravaLegado[_iQuantidade-1].pzcEMail,           pzcEMail          );
	strcpy( stcrGravaLegado[_iQuantidade-1].pzcNumDepend,       pzcNumDepend      );
	strcpy( stcrGravaLegado[_iQuantidade-1].pzcNomeContato,     pzcNomeContato    );
	strcpy( stcrGravaLegado[_iQuantidade-1].pzcLogradouro,      pzcLogradouro     );
	strcpy( stcrGravaLegado[_iQuantidade-1].pzcEndereco,        pzcEndereco       );
	strcpy( stcrGravaLegado[_iQuantidade-1].pzcComplemento,     pzcComplemento    );
	strcpy( stcrGravaLegado[_iQuantidade-1].pzcBairro,          pzcBairro         );
	strcpy( stcrGravaLegado[_iQuantidade-1].pzcCEP,             pzcCEP            );
	strcpy( stcrGravaLegado[_iQuantidade-1].pzcCidade,          pzcCidade         );
	strcpy( stcrGravaLegado[_iQuantidade-1].pzcEstado,          pzcEstado         );
	strcpy( stcrGravaLegado[_iQuantidade-1].pzcNumero,          pzcNumero         );
}


void CGravaLegadoItr::Zera( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrGravaLegado );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrGravaLegado    = NULL;
}
