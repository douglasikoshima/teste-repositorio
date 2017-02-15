/**
 * 
 * @modulo  Commons
 * @usecase Prototipo para geração de ligações no discador.
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:51 $
 **/ 


#include "../include/FinalizaContatoCampanha.h"

FinalizaContatoCampanha::FinalizaContatoCampanha() : cDiscadorUtil() {
	cDiscadorUtil::setChamada("FinContatoCamp");
	cDiscadorUtil::setEntity("003");
}

FinalizaContatoCampanha::FinalizaContatoCampanha(DOMNode*dnode, XMLGen*xml_g)  : cDiscadorUtil(dnode, xml_g) {
	cDiscadorUtil::setChamada("FinContatoCamp");
	cDiscadorUtil::setEntity("003");
}	

int   FinalizaContatoCampanha::getNPU()
{
	return cDiscadorUtil::getNPU();
}

int   FinalizaContatoCampanha::getIdLigacao()
{
	return cDiscadorUtil::getIdLigacao();
}

int   FinalizaContatoCampanha::getIdCampanha()
{
	return cDiscadorUtil::getIdCampanha();
}

void FinalizaContatoCampanha::setNPU(int npu)
{
	cDiscadorUtil::setNPU(npu);
}

void FinalizaContatoCampanha::setIdLigacao(int idLigacao)
{
	cDiscadorUtil::setIdLigacao(idLigacao);
}

void FinalizaContatoCampanha::setIdCampanha(int idCampanha)
{
	cDiscadorUtil::setIdCampanha(idCampanha);
}

void FinalizaContatoCampanha::enviar() {

	if (cDiscadorUtil::m_vlDados.npu == -1)
		throw new TuxException( "99E00001", "É necessário informar o código do NPU para finalizar o contato de uma campanha.");		
	
	if (cDiscadorUtil::m_vlDados.idLigacao == -1)
		throw new TuxException( "99E00002", "É necessário informar o idLigacao para finalizar o contato de uma campanha.");		

	if (cDiscadorUtil::m_vlDados.idCampanha == -1)
		throw new TuxException( "99E00003", "É necessário informar o idCampanha para finalizar o contato de uma campanha.");		

	cDiscadorUtil::enviar();
	
}
