/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:30 $
 **/ 

#ifndef STWFChamadaTelefonica
	#define STWFChamadaTelefonica

struct st_ChamadaTelefonica
{
	unsigned long idChamadaTelefonica;
	int idGrauSatisfacao;
	char dtChamada[20];
	unsigned long idPessoaDePara;
	char sgTipoRelacionamento[2];
};

struct st_vlChamadaTelefonica
{
	short idChamadaTelefonica;
	short idGrauSatisfacao;
	short dtChamada;
	short idPessoaDePara;
	short sgTipoRelacionamento;
};

#endif
