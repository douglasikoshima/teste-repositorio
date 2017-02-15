/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/06 12:36:43 $
 **/ 

#ifndef STWFChamadaAtendimento
	#define STWFChamadaAtendimento

	struct st_ChamadaAtendimento {
		long idChamadaAtendimento;
		long idAtendimento;
		int idChamadaTelefonica;
		int  idUsuarioAlteracao;
		char dtUltimaAlteracao[256];
	};

	struct st_vlChamadaAtendimento {
		short idChamadaAtendimento;
		short idAtendimento;
		short idChamadaTelefonica;
		short idUsuarioAlteracao;
		short dtUltimaAlteracao;
	};
#endif
