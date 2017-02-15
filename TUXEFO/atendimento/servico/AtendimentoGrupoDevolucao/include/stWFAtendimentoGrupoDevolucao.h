/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:31 $
 **/ 
 
#ifndef STWFAtendimentoGrupoDevolucao
	#define STWFAtendimentoGrupoDevolucao

	struct st_AtendimentoGrupoDevolucao {
		long idAtendimento;
		int  idGrupo;
	};

	struct st_vlAtendimentoGrupoDevolucao {
		short idAtendimento;
		short idGrupo;
	};
#endif
