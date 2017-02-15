#ifndef STVARIAVEISCAMPANHAGRUPO
	#define STVARIAVEISCAMPANHAGRUPO

struct st_VariaveisCampanhaGrupo
{
   char idGrupo[ 256 ];
   char idretornowfcti[ 256 ];
   char sgretornowfcti[ 256 ];
   char dsretornowfcti[ 256 ];
   char sgstatus[ 2 ];
   char inpadrao[ 2 ];

   st_VariaveisCampanhaGrupo()
   { memset(this,0,sizeof(st_VariaveisCampanhaGrupo)); }
} ;

#endif
