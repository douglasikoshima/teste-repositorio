/**
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Marcelo Nunes
 * @version $Revision: 1.1 $
 * @CVS     $Author:
 **/

#include "../../../commons/routerLib/include/RouterClient.h"
#include "../include/classdef.h"

int usrID = -1;
int grpID = -1;

void CheckUsr()
{
	if(usrID<0)
		throw new TuxBasicSvcException("00E9999","Invalid UserID");
	if(grpID<0)
		throw new TuxBasicSvcException("00E9999","Invalid GroupID");
}

RetornoWFCTI::RetornoWFCTI(DOMNode*dom,XMLGen*xml):pdom(dom),pxml(xml)
{
   ULOG_START("RetornoWFCTI::RetornoWFCTI()");  
   
   
	// CheckUsr() esta analisando variáveis que não pertencem à classe !?
    
	memset(&rwfcti,0,sizeof(rwfcti));
	rwfcti.idusuarioalteracao=usrID;
	rwfcti.idgrupo=grpID;
	POPULATE_INT(pdom,IDRETORNOWFCTI,rwfcti.idretornowfcti);
	POPULATE_STR(pdom,SGRETORNOWFCTI,rwfcti.sgretornowfcti,255);
	POPULATE_STR(pdom,DSRETORNOWFCTI,rwfcti.dsretornowfcti,255);
	POPULATE_STR(pdom,SGSTATUS,rwfcti.sgstatus,1);
	POPULATE_INT(pdom,INPADRAO,rwfcti.inpadrao);
	
	ULOG_END("RetornoWFCTI::RetornoWFCTI()");  
}

RetornoWFCTI::RetornoWFCTI(int* idUsuario, int* idGrupo, DOMNode*dom,XMLGen*xml):pdom(dom),pxml(xml)
{
   
   ULOG_START("RetornoWFCTI::RetornoWFCTI()");  
   
	// CheckUsr() esta analisando variáveis que não pertencem à classe !?

    memset(&rwfcti,0,sizeof(rwfcti));
	rwfcti.idusuarioalteracao=*idUsuario;
	rwfcti.idgrupo=*idGrupo;
	POPULATE_INT(pdom,IDRETORNOWFCTI,rwfcti.idretornowfcti);
	POPULATE_STR(pdom,SGRETORNOWFCTI,rwfcti.sgretornowfcti,255);
	POPULATE_STR(pdom,DSRETORNOWFCTI,rwfcti.dsretornowfcti,255);
	POPULATE_STR(pdom,SGSTATUS,rwfcti.sgstatus,1);
	POPULATE_INT(pdom,INPADRAO,rwfcti.inpadrao);
	
	ULOG_END("RetornoWFCTI::RetornoWFCTI()");  
}

BEGIN_DECLARE_ROUTER_PARM(SetUser)
	ADD_ROUTER_FUNCTION_ITEM(AC_INTEGER)
END_DECLARE_ROUTER_PARM
BEGIN_DECLARE_ROUTER_PARM(SetGroup)
	ADD_ROUTER_FUNCTION_ITEM(AC_INTEGER)
END_DECLARE_ROUTER_PARM
BEGIN_DECLARE_ROUTER_PARM(InsertWFRetWFCTI)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM
BEGIN_DECLARE_ROUTER_PARM(DeleteWFRetWFCTI)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM
BEGIN_DECLARE_ROUTER_PARM(UpdateWFRetWFCTI)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM
BEGIN_DECLARE_ROUTER_PARM(SelByUserWFRetWFCTI)
END_DECLARE_ROUTER_PARM
BEGIN_DECLARE_ROUTER_PARM(SelByGroupWFRetWFCTI)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(InsertWFRetWFCTI,AC_XMLGEN)
DECLARE_RETURN_TYPE(DeleteWFRetWFCTI,AC_XMLGEN)
DECLARE_RETURN_TYPE(UpdateWFRetWFCTI,AC_XMLGEN)
DECLARE_RETURN_TYPE(SelByUserWFRetWFCTI,AC_XMLGEN)
DECLARE_RETURN_TYPE(SelByGroupWFRetWFCTI,AC_XMLGEN)
DECLARE_NO_RETURN(SetUser)
DECLARE_NO_RETURN(SetGroup)

BEGIN_DECLARE_ROUTER_INTERF(SetUser,1)
	usrID=pSetUser[0].accum->var.i32;
END_DECLARE_ROUTER_INTERF
BEGIN_DECLARE_ROUTER_INTERF(SetGroup,1)
	grpID=pSetGroup[0].accum->var.i32;
END_DECLARE_ROUTER_INTERF
BEGIN_DECLARE_ROUTER_INTERF(InsertWFRetWFCTI,1)
	RetornoWFCTI rwfcti(pInsertWFRetWFCTI[0].accum->var.dom->RetrieveDOM(),ac->var.xml);
	rwfcti.Insert();
END_DECLARE_ROUTER_INTERF
BEGIN_DECLARE_ROUTER_INTERF(DeleteWFRetWFCTI,1)
	RetornoWFCTI rwfcti(pDeleteWFRetWFCTI[0].accum->var.dom->RetrieveDOM(),ac->var.xml);
	rwfcti.Delete();
END_DECLARE_ROUTER_INTERF
BEGIN_DECLARE_ROUTER_INTERF(UpdateWFRetWFCTI,1)
	RetornoWFCTI rwfcti(pUpdateWFRetWFCTI[0].accum->var.dom->RetrieveDOM(),ac->var.xml);
	rwfcti.Update();
END_DECLARE_ROUTER_INTERF
BEGIN_DECLARE_ROUTER_INTERF(SelByUserWFRetWFCTI,0)
	RetornoWFCTI rwfcti(0L,ac->var.xml);
	rwfcti.SelectByUser();
END_DECLARE_ROUTER_INTERF
BEGIN_DECLARE_ROUTER_INTERF(SelByGroupWFRetWFCTI,0)
	RetornoWFCTI rwfcti(0L,ac->var.xml);
	rwfcti.SelectByGroup();
END_DECLARE_ROUTER_INTERF
