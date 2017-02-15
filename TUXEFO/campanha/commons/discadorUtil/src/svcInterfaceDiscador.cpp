/**
 * 
 * @modulo  Commons
 * @usecase Envio de e-mails.
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:51 $
 **/ 


#include <tuxfw.h>
#include <RouterClient.h>

#include "../include/AssociaContatoCampanha.h"
#include "../include/AssociaContatosCampanha.h"
#include "../include/AssociaUsuarioCampanha.h"
#include "../include/CancelaContatoCampanha.h"
#include "../include/ConsultaStatusLigacoes.h"
#include "../include/DesassociaContatoCampanha.h"
#include "../include/ExcluirUsuario.h"
#include "../include/FinalizaContatoCampanha.h"
#include "../include/IncluirUsuario.h"
#include "../include/ReagendaContatoCampanha.h"
#include "../include/RecarregaLigacaoDiscador.h"

DECLARE_TUXEDO_SERVICE(DiscadorUtil);

void implDiscadorUtil::Execute(DOMNode*dnode,XMLGen*xml_g) {

	RecarregaLigacaoDiscador ac(dnode,xml_g);

	ac.setNPU(9999);        
	ac.setIdLigacao(666999);
	ac.setSequence("11");

	ac.enviar();

	setStatusCode("00I0000");

}

/**
* AssociaContatoCampanha
**/
BEGIN_DECLARE_ROUTER_PARM(AssConCampanha)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(AssConCampanha,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(AssConCampanha,1)
	AssociaContatoCampanha objeto(pAssConCampanha[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.enviar();
END_DECLARE_ROUTER_INTERF

/**
* AssociaContatosCampanha
**/
BEGIN_DECLARE_ROUTER_PARM(AssCosCampanha)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(AssCosCampanha,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(AssCosCampanha,1)
	AssociaContatosCampanha objeto(pAssCosCampanha[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.enviar();
END_DECLARE_ROUTER_INTERF

/**
* AssociaUsuarioCampanha
**/
BEGIN_DECLARE_ROUTER_PARM(AssUsuCampanha)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(AssUsuCampanha,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(AssUsuCampanha,1)
	AssociaUsuarioCampanha objeto(pAssUsuCampanha[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.enviar();
END_DECLARE_ROUTER_INTERF

/**
* CancelaContatoCampanha
**/
BEGIN_DECLARE_ROUTER_PARM(CanConCampanha)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(CanConCampanha,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(CanConCampanha,1)
	CancelaContatoCampanha objeto(pCanConCampanha[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.enviar();
END_DECLARE_ROUTER_INTERF

/**
* ConsultaStatusLigacoes
**/
BEGIN_DECLARE_ROUTER_PARM(ConStaLigacoes)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(ConStaLigacoes,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(ConStaLigacoes,1)
	ConsultaStatusLigacoes objeto(pConStaLigacoes[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.enviar();
END_DECLARE_ROUTER_INTERF

/**
* DesassociaContatoCampanha
**/
BEGIN_DECLARE_ROUTER_PARM(DesConCampanha)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(DesConCampanha,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(DesConCampanha,1)
	DesassociaContatoCampanha objeto(pDesConCampanha[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.enviar();
END_DECLARE_ROUTER_INTERF

/**
* ExcluirUsuario
**/
BEGIN_DECLARE_ROUTER_PARM(ExcluiUsuario)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(ExcluiUsuario,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(ExcluiUsuario,1)
	ExcluirUsuario objeto(pExcluiUsuario[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.enviar();
END_DECLARE_ROUTER_INTERF

/**
* FinalizaContatoCampanha
**/
BEGIN_DECLARE_ROUTER_PARM(FinConCampanha)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(FinConCampanha,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(FinConCampanha,1)
	FinalizaContatoCampanha objeto(pFinConCampanha[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.enviar();
END_DECLARE_ROUTER_INTERF

/**
* IncluirUsuario
**/
BEGIN_DECLARE_ROUTER_PARM(IncUsuario)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(IncUsuario,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(IncUsuario,1)
	IncluirUsuario objeto(pIncUsuario[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.enviar();
END_DECLARE_ROUTER_INTERF

/**
* ReagendaContatoCampanha
**/
BEGIN_DECLARE_ROUTER_PARM(ReaConCampanha)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(ReaConCampanha,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(ReaConCampanha,1)
	ReagendaContatoCampanha objeto(pReaConCampanha[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.enviar();
END_DECLARE_ROUTER_INTERF

/**
* RecarregaLigacaoDiscador
**/
BEGIN_DECLARE_ROUTER_PARM(RecLigDiscador)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(RecLigDiscador,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(RecLigDiscador,1)
	RecarregaLigacaoDiscador objeto(pRecLigDiscador[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.enviar();
END_DECLARE_ROUTER_INTERF