/**
 * 
 * @modulo  Commons
 * @usecase Prototipo para geração de ligações no discador.
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:51 $
 **/ 


#include "../include/AssociaContatoCampanha.h"

AssociaContatoCampanha::AssociaContatoCampanha() : cDiscadorUtil() {
	cDiscadorUtil::setChamada("AssContatoCamp");
}

AssociaContatoCampanha::AssociaContatoCampanha(DOMNode*dnode, XMLGen*xml_g)  : cDiscadorUtil(dnode, xml_g) { 
	cDiscadorUtil::setChamada("AssContatoCamp");
}	

int   AssociaContatoCampanha::getNPU()
{
	return cDiscadorUtil::getNPU();
}

int   AssociaContatoCampanha::getIdLigacao()
{
	return cDiscadorUtil::getIdLigacao();
}

char* AssociaContatoCampanha::getDataHoraLigacao()
{
	return cDiscadorUtil::getDataHoraLigacaoInicio();
}

void AssociaContatoCampanha::setNPU(int npu)
{
	cDiscadorUtil::setNPU(npu);
}

void AssociaContatoCampanha::setIdLigacao(int idLigacao)
{
	cDiscadorUtil::setIdLigacao(idLigacao);
}

void AssociaContatoCampanha::setIdCampanha(int idCampanha)
{
	cDiscadorUtil::setIdCampanha(idCampanha);
}

void AssociaContatoCampanha::setDataHoraLigacao(char* dataHoraLigacao)
{
	cDiscadorUtil::setDataHoraLigacaoInicio(dataHoraLigacao);
}

int AssociaContatoCampanha::getRegistrosContatoLista()
{
	return cDiscadorUtil::getRegistrosContatoLista();
}

int AssociaContatoCampanha::addContatoLista(char* campanha)
{
	return cDiscadorUtil::addContatoLista(campanha);
}

char* AssociaContatoCampanha::getContatoLista(int idRegistro)
{
	return cDiscadorUtil::getContatoLista(idRegistro);
}

void AssociaContatoCampanha::limpaContatoLista()
{
	cDiscadorUtil::limpaContatoLista();
}



void AssociaContatoCampanha::enviar() {

	if (cDiscadorUtil::m_vlDados.npu == -1)
		throw new TuxException( "99E00001", "É necessário informar o código do NPU para associar contatos a campanha.");		
	
	if (cDiscadorUtil::m_vlDados.idLigacao == -1)
		throw new TuxException( "99E00002", "É necessário informar o idLigacao para associar contatos a campanha.");		

	if (cDiscadorUtil::m_vlDados.idCampanha == -1)
		throw new TuxException( "99E00003", "É necessário informar o idCampanha para associar contatos a campanha.");		

	if (cDiscadorUtil::m_vlDados.dataHoraLigacaoInicio == -1)
		throw new TuxException( "99E00004", "É necessário informar a dataHoraLigacao para associar contatos a campanha.");		

	if (cDiscadorUtil::getRegistrosContatoLista() <= 0)
		throw new TuxException( "99E00004", "É necessário informar a os contatos para associar a campanha.");		

	cDiscadorUtil::enviar();
	
}
