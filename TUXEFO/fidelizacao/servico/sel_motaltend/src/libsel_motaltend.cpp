#include "../../negocio/fidutil/include/retencao.hpp"

extern int sel_motaltend(int usuario, DOMNode*dnode, XMLGen*xml);

extern int sel_protocolos(int usuario, DOMNode*dnode, XMLGen*xml);

DECLARE_TUXEDO_SERVICE(SELMOTALTEND);

void implSELMOTALTEND::Execute(DOMNode*dnode,XMLGen*xml_g)
{
  ULOG_START("SELMOTALTEND");  
  char parm[255];
  int idUsr;
  char szIdPessoaDePara[21+1];
  int iBuscaProtocolos=0;

  idUsr = get_idUsuario(getUser());

  memset(&szIdPessoaDePara,0,sizeof(szIdPessoaDePara));

  iBuscaProtocolos = get_tag(parm,dnode,"idPessoaDePara",0,-1);

  if(!iBuscaProtocolos)
	{
		xml_g->createTag("AdmContatoFolhaVO");	
	    xml_g->addProp("xmlns","admsistemas.fo.vivo.com.br/vo");
				sel_protocolos(idUsr,dnode,xml_g);
		xml_g->closeTag();  
	}
else 
	{	  
		  xml_g->createTag("listaMotivosAlteracaoEndereco");
				 sel_motaltend(idUsr,dnode,xml_g);
		  xml_g->closeTag();
	}
	



  setStatusCode(OKFID, OKMSG);

  ULOG_END("SELMOTALTEND");
}

