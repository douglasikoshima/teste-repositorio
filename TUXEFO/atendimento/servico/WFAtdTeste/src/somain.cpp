#include<classdata.h>

BEGIN_DECLARE_ROUTER_PARM(AddAtendimentoTeste)
	ADD_ROUTER_FUNCTION_ITEM(AC_INTEGER)
	ADD_ROUTER_FUNCTION_ITEM(AC_INTEGER)
	ADD_ROUTER_FUNCTION_ITEM(AC_STRING)
END_DECLARE_ROUTER_PARM
BEGIN_DECLARE_ROUTER_PARM(AddTesteResposta)
	ADD_ROUTER_FUNCTION_ITEM(AC_STRING)
	ADD_ROUTER_FUNCTION_ITEM(AC_INTEGER)
	ADD_ROUTER_FUNCTION_ITEM(AC_INTEGER)
	ADD_ROUTER_FUNCTION_ITEM(AC_INTEGER)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(AddAtendimentoTeste,AC_INTEGER)
DECLARE_RETURN_TYPE(AddTesteResposta,AC_INTEGER)

BEGIN_DECLARE_ROUTER_INTERF(AddAtendimentoTeste,3)
	int rv;
	rv=SaveAtendimentoTeste(pAddAtendimentoTeste[0].accum->var.i32,pAddAtendimentoTeste[1].accum->var.i32,pAddAtendimentoTeste[2].accum->var.pstr);
	if(rv<0)
		throw TuxBasicOraException(rv);
	ac->var.i32=rv;
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(AddTesteResposta,4)
	int rv;
	if(pAddTesteResposta[0].accum->var.pstr[0]!='S'&&
		pAddTesteResposta[0].accum->var.pstr[0]!='N')
		throw new TuxBasicSvcException("00E9999","Invalid answer type");
	rv=SaveTesteResposta(pAddTesteResposta[0].accum->var.pstr[0],
		pAddTesteResposta[1].accum->var.i32,pAddTesteResposta[2].accum->var.i32,
		pAddTesteResposta[3].accum->var.i32);
	if(rv<0)
		throw TuxBasicOraException(rv);
	ac->var.i32=rv;
END_DECLARE_ROUTER_INTERF
