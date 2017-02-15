/**
 * 
 * @modulo  Commons
 * @usecase Prototipo para gera��o de liga��es no discador.
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:51 $
 **/ 


#include "../include/DesassociaContatoCampanha.h"

DesassociaContatoCampanha::DesassociaContatoCampanha() : cDiscadorUtil() {
	cDiscadorUtil::setChamada("DesContatoCamp");
}

DesassociaContatoCampanha::DesassociaContatoCampanha(DOMNode*dnode, XMLGen*xml_g)  : cDiscadorUtil(dnode, xml_g) {
	cDiscadorUtil::setChamada("DesContatoCamp");
}	

int   DesassociaContatoCampanha::getNPU()
{
	return cDiscadorUtil::getNPU();
}

int   DesassociaContatoCampanha::getIdLigacao()
{
	return cDiscadorUtil::getIdLigacao();
}

int   DesassociaContatoCampanha::getIdCampanha()
{
	return cDiscadorUtil::getIdCampanha();
}

void DesassociaContatoCampanha::setNPU(int npu)
{
	cDiscadorUtil::setNPU(npu);
}

void DesassociaContatoCampanha::setIdLigacao(int idLigacao)
{
	cDiscadorUtil::setIdLigacao(idLigacao);
}

void DesassociaContatoCampanha::setIdCampanha(int idCampanha)
{
	cDiscadorUtil::setIdCampanha(idCampanha);
}

void DesassociaContatoCampanha::enviar() {

	if (cDiscadorUtil::m_vlDados.npu == -1)
		throw new TuxException( "99E00001", "� necess�rio informar o c�digo do NPU para cancelar um contato de uma campanha.");		
	
	if (cDiscadorUtil::m_vlDados.idLigacao == -1)
		throw new TuxException( "99E00002", "� necess�rio informar o idLigacao para cancelar um contato de uma campanha.");		

	if (cDiscadorUtil::m_vlDados.idCampanha == -1)
		throw new TuxException( "99E00003", "� necess�rio informar o idCampanha para cancelar um contato de uma campanha.");		

	cDiscadorUtil::enviar();
	
}
