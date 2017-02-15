/**
 * 
 * @modulo  Commons
 * @usecase Prototipo para gera��o de liga��es no discador.
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:51 $
 **/ 


#include "../include/ConsultaStatusLigacoes.h"

ConsultaStatusLigacoes::ConsultaStatusLigacoes() : cDiscadorUtil() {
	cDiscadorUtil::setChamada("ConStatusLigac");
	cDiscadorUtil::setEntity("005");
}

ConsultaStatusLigacoes::ConsultaStatusLigacoes(DOMNode*dnode, XMLGen*xml_g) : cDiscadorUtil(dnode, xml_g) {
	cDiscadorUtil::setChamada("ConStatusLigac");
	cDiscadorUtil::setEntity("005");
}	

int   ConsultaStatusLigacoes::getNPU()
{
	return cDiscadorUtil::getNPU();
}

char* ConsultaStatusLigacoes::getDataConsultaInicio()
{
	return cDiscadorUtil::getDataConsultaInicio();
}

char* ConsultaStatusLigacoes::getDataConsultaFim()
{
	return cDiscadorUtil::getDataConsultaFim();
}

void ConsultaStatusLigacoes::setNPU(int npu)
{
	cDiscadorUtil::setNPU(npu);
}

void ConsultaStatusLigacoes::setDataConsultaInicio(char* dataConsultaInicio)
{
	cDiscadorUtil::setDataConsultaInicio(dataConsultaInicio);
}

void ConsultaStatusLigacoes::setDataConsultaFim(char* dataConsultaFim)
{
	cDiscadorUtil::setDataConsultaFim(dataConsultaFim);
}

/**
	Controle dos registros da lista de campanhas.
**/
int ConsultaStatusLigacoes::getRegistrosCampanhaLista()
{
	return cDiscadorUtil::getRegistrosCampanhaLista();
}

int ConsultaStatusLigacoes::addCampanhaLista(char* campanha)
{
	return cDiscadorUtil::addCampanhaLista(campanha);
}

char* ConsultaStatusLigacoes::getCampanhaLista(int idRegistro)
{
	return cDiscadorUtil::getCampanhaLista(idRegistro);
}

void ConsultaStatusLigacoes::limpaCampanhaLista()
{
	cDiscadorUtil::limpaCampanhaLista();
}

void ConsultaStatusLigacoes::enviar() {

	if (cDiscadorUtil::m_vlDados.npu == -1)
		throw new TuxException( "99E00001", "� necess�rio informar o c�digo do NPU para consultar os status das ligacoes.");		
	
	if (cDiscadorUtil::m_vlDados.dataConsultaInicio == -1)
		throw new TuxException( "99E00003", "� necess�rio informar a dataConsultaInicio para consultar os status das ligacoes.");		

	if (cDiscadorUtil::m_vlDados.dataConsultaFim == -1)
		throw new TuxException( "99E00003", "� necess�rio informar a dataConsultaFim para consultar os status das ligacoes.");		

	if (cDiscadorUtil::getRegistrosCampanhaLista() <= 0)
		throw new TuxException( "99E00004", "� necess�rio informar as campanhas para consulta dos status.");		

	cDiscadorUtil::enviar();
	
}
