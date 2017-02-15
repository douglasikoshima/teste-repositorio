/**
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:25 $
 **/

#include "../include/cPsqPessoTpComPC.h"

DECLARE_TUXEDO_SERVICE(WFPSQPESTPCOM);

void implWFPSQPESTPCOM::Execute( DOMNode*dnode, XMLGen*xml_g )
{
	 ULOG_START("implWFPSQPESTPCOM::Execute()");
	 
    int indx;
    char *p;
    Collection resultado;
    cPsqPessoTpComPC rc;
    st_VariaveisTpComunic dados;
    st_VariaveisTpComunic * pdadosResult;

    if ( p = walkTree( dnode, "idPessoa", 0 ), p )
    {
        strcpy( dados.idPessoa, p);
        XMLString::release(&p);
    }

    if ( p = walkTree( dnode, "idTipoComunicacao", 0 ), p )
    {
        strcpy( dados.idTipoComunic, p);
        XMLString::release(&p);
    }

    if ((atoi(dados.idTipoComunic) > 0) && (atol(dados.idPessoa) > 0))
    {
		rc.pesquisaPessoaTpComunicPC( &dados, &resultado );

		xml_g->createTag("AtendimentoVO");
		xml_g->addProp("xmlns","workflow.fo.vivo.com.br/vo");

		int getCount = resultado.GetCount();

		for ( indx=0;indx<getCount;indx++ )
		{
			pdadosResult  = (st_VariaveisTpComunic *)resultado.GetItem(indx);

			if ( indx == 0 )
			{
				xml_g->createTag("PessoaVO");
				xml_g->addProp("xmlns","workflow.fo.vivo.com.br/vo");
					xml_g->createTag("AtendimentoTipoComunicacaoVO");
					xml_g->addProp("xmlns","workflow.fo.vivo.com.br/vo");
						xml_g->addItem("idTipoComunicacao", pdadosResult->idTipoComunic );
						xml_g->addItem("descricao", pdadosResult->dsTpComunicacao );
			}

			xml_g->createTag("AtendimentoComunicacaoVO");
			   xml_g->addItem("idComunicacao", pdadosResult->idPessoaComunic );
			   xml_g->addItem("descricao", pdadosResult->dsContato );
			xml_g->closeTag();
		}
		xml_g->closeTag();
		xml_g->closeTag();

    }
	else if (atol(dados.idPessoa) > 0)
	{
		xml_g->createTag("LupaClienteVO");
		xml_g->addProp("xmlns","cliente.fo.vivo.com.br/vo");
			xml_g->createTag("DadosAbaLupaCliente");
			rc.pesquisaPessoaComunicacaoPC( atol(dados.idPessoa), xml_g );
			xml_g->closeTag();
		xml_g->closeTag();
	}
	else
	{
		xml_g->createTag("ListaTipoComunicacaoVO");
		xml_g->addProp("xmlns","cliente.fo.vivo.com.br/vo");
			rc.pesquisaTipoComunicacaoPC( xml_g );
		xml_g->closeTag();
	}
    setStatusCode("07I0000","Pesquisa de Pessoa por Tipo de Comunicação Concluída com Sucesso");

	ULOG_END("implWFPSQPESTPCOM::Execute()");
}
