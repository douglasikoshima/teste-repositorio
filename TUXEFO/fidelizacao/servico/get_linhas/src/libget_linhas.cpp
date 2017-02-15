//
// $Id: libget_linhas.cpp,v 1.1 2009/07/31 15:34:55 a5110702 Exp $
//

#include "../../../negocio/fidutil/include/retencao.hpp"

extern int get_linhas(int usuario, DOMNode*dnode, XMLGen*xml);
extern int get_linhasPout(int usuario, DOMNode*dnode, XMLGen*xml,char *idAtendimento,int idPessoaUsuarioAtual);
extern bool obterGrupoAberturaPortout(XMLGen*xml,const char *cdFuncionalidade);

DECLARE_TUXEDO_SERVICE(GETLINHAS);

void implGETLINHAS::Execute(DOMNode*dnode,XMLGen*xml_g)
{
    int idUsr;
    char parm[256];
    char idAtendimento[32];
    char inPortout[2];

    ULOG_START("GETLINHAS");

    idUsr = get_idUsuario(getUser());

    xml_g->createTag("tns:listaDetalheLinhaVO ");
    xml_g->addProp("xmlns:tns","fidelizacao.fo.vivo.com.br/vo");
    xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

    get_tag(parm,dnode,"idAtendimento",0,0);
    strcpy(idAtendimento,parm);

    get_tag(parm,dnode,"inPortout",0,0);
    strcpy(inPortout,parm);

    if ( inPortout[0] == '1' )
    {
        //obterGrupoAberturaPortout(xml_g,"PORTOUT_RETENÇÃO");
        obterGrupoAberturaPortout(xml_g,idAtendimento);

        get_linhasPout(idUsr,dnode,xml_g,idAtendimento,idUsr);
    }
    else
    {
        get_linhas(idUsr,dnode,xml_g);
    }

    xml_g->closeTag();

    setStatusCode(OKFID, OKMSG);

    ULOG_END("GETLINHAS");
}

