/**
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:52 $
 **/

#include "../include/cWFAtdObtCampUsu.h"

#include "../../WFRetWFCTI/include/classdef.h"
#include "../../WFRetWFCTI/include/cWFRetWFCTI.h"

cWFAtdObtCampUsu::cWFAtdObtCampUsu(DOMNode *dnode, XMLGen *xml_g)
{
    entrada = dnode;
    saida = xml_g;

    carregaDados();
}

bool cWFAtdObtCampUsu::Executar()
{
    cWFRetWFCTI cwfRetWFCTI(entrada,saida);

    return cwfRetWFCTI.ConsultarPorUsuario(obterIdUsuario(),obterUUID());
}

bool cWFAtdObtCampUsu::RetornoCTI( XMLGen * xml_g )
{
    cWFRetWFCTI cwfRetWFCTI(entrada,saida);

    return cwfRetWFCTI.ConsultarCTI(obterIdUsuario(),obterUUID(),xml_g );
}
