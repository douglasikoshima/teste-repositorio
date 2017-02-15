/**
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:37 $
 **/

#include "../include/classdef.h"
#include "../include/cWFRetWFCTI.h"

cWFRetWFCTI::cWFRetWFCTI(DOMNode *dnode, XMLGen *xml_g)
{
    entrada = dnode;
    saida = xml_g;

}

bool cWFRetWFCTI::Inserir()
{
    RetornoWFCTI rWFCTI(entrada,saida);

    rWFCTI.Insert();

    return true;
}

bool cWFRetWFCTI::Delete()
{
    RetornoWFCTI rWFCTI(entrada,saida);

    rWFCTI.Delete();

    return true;
}

bool cWFRetWFCTI::Alterar()
{
    RetornoWFCTI rWFCTI(entrada,saida);

    rWFCTI.Update();

    return true;
}

bool cWFRetWFCTI::ConsultarPorUsuario(int idUsuario,int idGrupo)
{
    RetornoWFCTI rWFCTI(entrada,saida);

    rWFCTI.SetarUsuario(idUsuario);
    rWFCTI.SetarGrupo(idGrupo);

    rWFCTI.SelectByUser();

    return true;
}

bool cWFRetWFCTI::ConsultarCTI( int idUsuario,int idGrupo,XMLGen * xml_g )
{
    RetornoWFCTI rWFCTI(entrada,saida);

    rWFCTI.SetarUsuario(idUsuario);
    rWFCTI.SetarGrupo(idGrupo);

    rWFCTI.SelectByUserCTI( xml_g );

    return true;
}

bool cWFRetWFCTI::ConsultarPorGrupo(int idUsuario,int idGrupo)
{
    RetornoWFCTI rWFCTI(entrada,saida);

    rWFCTI.SetarUsuario(idUsuario);
    rWFCTI.SetarGrupo(idGrupo);

    rWFCTI.SelectByGroup();

    return true;
}
