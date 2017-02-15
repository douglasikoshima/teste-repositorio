#ifndef STVARIAVEISOBTARVCONTATO
	#define STVARIAVEISOBTARVCONTATO

#ifdef WIN32
#pragma warning(disable:4786)
#endif

#include <iostream>
#include <string>
using namespace std;

#define SAFE_STRNCPY(dst,src) strncpy(dst,src?src:"",sizeof(dst)-1);dst[sizeof(dst)-1]=0;

struct st_Parametros
{
   char idContatoPai[23];
   char idGrupo[23];
   char idTipoCarteira[23];
   char idTipoLinha[23];
   char idUFOperadora[23];
   char idSegmentacao[23];
   char idTipoRel[23];
   char idTipoSequencia[23];

   st_Parametros()
   {
       memset(this,0,sizeof(st_Parametros));
   }
};

struct st_ContatoFolha
{
   char idContato[ 23 ];
   char idContatoHierarquia[ 23 ];
   char idNomeContato[ 23 ];
   char nmContato[ 256 ];
   char inFolha[ 2 ];
   char dsMensagemAviso[ 256 ];
   char inPermitido[ 2 ];
   char nmUrlContato[ 256 ];
   char nmUrlContatoAcao[ 256 ];
   char dsPath[ 2001 ];

   st_ContatoFolha()
   {
       memset(this->idContato,0,sizeof(this->idContato));
       memset(this->idContatoHierarquia,0,sizeof(this->idContatoHierarquia));
       memset(this->idNomeContato,0,sizeof(this->idNomeContato));
       memset(this->nmContato,0,sizeof(this->nmContato));
       memset(this->inFolha,0,sizeof(this->inFolha));
       memset(this->dsMensagemAviso,0,sizeof(this->dsMensagemAviso));
       memset(this->inPermitido,0,sizeof(this->inPermitido));
       memset(this->nmUrlContato,0,sizeof(this->nmUrlContato));
       memset(this->nmUrlContatoAcao,0,sizeof(this->nmUrlContatoAcao));
       memset(this->dsPath,0,sizeof(this->dsPath));
   }
};

struct st_ContatoRaiz
{
   char idContatoPai[ 23 ];
   char idNomeContato[ 23 ];
   char idContato[ 23 ];
   char nmContato[ 256 ];
   char inDisponibilidade[ 2 ];

   st_ContatoRaiz()
   {
       memset(this->idContatoPai,0,sizeof(this->idContatoPai));
       memset(this->idNomeContato,0,sizeof(this->idNomeContato));
       memset(this->idContato,0,sizeof(this->idContato));
       memset(this->nmContato,0,sizeof(this->nmContato));
       memset(this->inDisponibilidade,0,sizeof(this->inDisponibilidade));
   }
};


#endif
