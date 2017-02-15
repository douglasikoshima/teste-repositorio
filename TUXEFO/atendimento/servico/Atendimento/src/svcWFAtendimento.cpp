/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:29 $
 **/


#include "../include/cWFAtendimento.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(WFAtendi);

void implWFAtendi::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	char operacao = 0;
	cWFAtendimento cs(dnode, xml_g);
	int ret = 0;

	char* p = walkTree( dnode, "tipoOperacao", 0 );
	int idPessoaUsuario = atoi( getUser() );

	if (p)
	{
        operacao = *p;
		XMLString::release(&p);
	}

    switch (operacao)
    {
        case 'I':
		    cs.incluir();
        break;

        case 'A':
		    ret = cs.alterar();
        break;

        case 'E':
		    ret = cs.excluir();
        break;

        case 'C':
		    cs.consultar();
        break;

        case 'F':
		    cs.consultarFila();
        break;

        case 'B':
		    cs.consultarBox(idPessoaUsuario);
        break;

        case 'M':
		    cs.consultarMassa();
        break;

        case 'R':
		    cs.consultarRelacionamento();
        break;

        case 'O':
		    cs.ObtemPessoaComunic();
        break;

        case 'D':
		    cs.diasUteis();
        break;

        default:
		    setStatusCode("04E0002","operação invalida");
            ret = -2;
        break;
    }

	if (ret == -1)
    {
		setStatusCode("04E0001","O atributo 'idAtendimento' é obrigatório para essa operação.");
	}
	else if (ret >= 0)
    {
		setStatusCode("04I0000","Processo concluído.");
    }
}

BEGIN_DECLARE_ROUTER_PARM(WFAtendiIncluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtendiAlterar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtendiExcluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtendiConsultar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(AtendiConsFila)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(AtendiConsBox)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(AtendiConsRel)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(AtendiConsMas)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(AtendiConsAle)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(ObtemAtend)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(ObtemCtasCli)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(ObtemLinhas)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(ObtemPessoa)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(ObtemCanal)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(ObtemTipoComunic)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(ObtemComunicacao)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(ObtemPessoaComunic)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(ObtemDetalheAtend)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(ObtemReaberAtend)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(WFAtendiIncluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtendiAlterar,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtendiExcluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtendiConsultar,AC_XMLGEN)
DECLARE_RETURN_TYPE(AtendiConsFila,AC_XMLGEN)
DECLARE_RETURN_TYPE(AtendiConsBox,AC_XMLGEN)
DECLARE_RETURN_TYPE(AtendiConsAle,AC_XMLGEN)
DECLARE_RETURN_TYPE(AtendiConsRel,AC_XMLGEN)
DECLARE_RETURN_TYPE(AtendiConsMas,AC_XMLGEN)
DECLARE_RETURN_TYPE(ObtemAtend,AC_XMLGEN)
DECLARE_RETURN_TYPE(ObtemCtasCli,AC_XMLGEN)
DECLARE_RETURN_TYPE(ObtemLinhas,AC_XMLGEN)
DECLARE_RETURN_TYPE(ObtemPessoa,AC_XMLGEN)
DECLARE_RETURN_TYPE(ObtemCanal,AC_XMLGEN)
DECLARE_RETURN_TYPE(ObtemTipoComunic,AC_XMLGEN)
DECLARE_RETURN_TYPE(ObtemComunicacao,AC_XMLGEN)
DECLARE_RETURN_TYPE(ObtemPessoaComunic,AC_XMLGEN)
DECLARE_RETURN_TYPE(ObtemDetalheAtend,AC_XMLGEN)
DECLARE_RETURN_TYPE(ObtemReaberAtend,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(WFAtendiIncluir,1)
	cWFAtendimento objeto(pWFAtendiIncluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.incluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtendiAlterar,1)
	cWFAtendimento objeto(pWFAtendiAlterar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.alterar();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtendiExcluir,1)
	cWFAtendimento objeto(pWFAtendiExcluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.excluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtendiConsultar,1)
	cWFAtendimento objeto(pWFAtendiConsultar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.consultar();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(AtendiConsFila,1)
	cWFAtendimento objeto(pAtendiConsFila[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.consultarFila();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(AtendiConsBox,1)
	cWFAtendimento objeto(pAtendiConsBox[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.consultarBox();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(AtendiConsRel,1)
	cWFAtendimento objeto(pAtendiConsRel[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.consultarRelacionamento();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(AtendiConsMas,1)
	cWFAtendimento objeto(pAtendiConsMas[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.consultarMassa();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(AtendiConsAle,1)

	TuxHelper tx;

	char* p = tx.walkTree( pAtendiConsAle[0].accum->var.dom->RetrieveDOM(), "idAtendimento", 0 );

    long idAtendimento = p ? atol(p) : 0;

	if (p) XMLString::release(&p);

	cWFAtendimento atd;

	atd.consultarAlerta(&idAtendimento, ac->var.xml);

END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(ObtemAtend,1)
	cWFAtendimento objeto(pObtemAtend[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.ObtemAtend();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(ObtemCtasCli,1)
	cWFAtendimento objeto(pObtemCtasCli[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.ObtemContasCliente();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(ObtemLinhas,1)
	cWFAtendimento objeto(pObtemLinhas[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.ObtemLinhas();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(ObtemPessoa,1)
	cWFAtendimento objeto(pObtemPessoa[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.ObtemPessoa();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(ObtemCanal,1)
	cWFAtendimento objeto(pObtemCanal[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.ObtemCanal();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(ObtemTipoComunic,1)
	cWFAtendimento objeto(pObtemTipoComunic[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.ObtemTipoComunic();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(ObtemComunicacao,1)
	cWFAtendimento objeto(pObtemComunicacao[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.ObtemComunicacao();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(ObtemPessoaComunic,1)
	cWFAtendimento objeto(pObtemPessoaComunic[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.ObtemPessoaComunic();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(ObtemDetalheAtend,1)
	cWFAtendimento objeto(pObtemDetalheAtend[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.ObtemDetalheAtend();
END_DECLARE_ROUTER_INTERF

// Função de calculo de dias úteis.
BEGIN_DECLARE_ROUTER_PARM(DiasUteis)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(DiasUteis,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(DiasUteis,1)
	cWFAtendimento objeto(pDiasUteis[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.diasUteis();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(ObtemReaberAtend,1)
	cWFAtendimento objeto(pObtemReaberAtend[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.ObtemReaberAtend();
END_DECLARE_ROUTER_INTERF
