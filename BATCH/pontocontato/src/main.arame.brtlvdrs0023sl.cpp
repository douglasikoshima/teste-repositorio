
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <time.h>
#include <errno.h>

#include "../../commons/LerDados/include/Propriedade.h"
#include "../../commons/Log/include/Log.h"
#include "../../commons/CFile/include/CFile.h"

#define PATH_TEXTO "/var/opt/batch/producao/deploy/processos/pontocontato/data"
#define PATH_CFG "/var/opt/batch/producao/deploy/processos/pontocontato/cfg"

extern void procInconsistencia( char * sArquivoConfiguracao, char * sArquivoTexto, int nrLinhasPrm );

int main( int argc, char *argv[] )
{

    FILE * fp;
    char sLido[1500];
    char sBuffer[1500];
    char sNomeArquivo[128];
    char sArquivo[128];
    char sNomeArquivoCnf[128];
    char * p;
    char * szPth;
    char comando[256];
    int idTipoEmpresa;
    int i;
    int iCampos;
    int iErroLayOut = 0;
    int linhas;

    memset( sLido,0x0,sizeof(sLido) );



    strcpy( sBuffer, argv[1] );
    strcpy( sArquivo,&sBuffer[8] );
    
    //printf( "%s\n", sArquivo);

    strcpy( sNomeArquivoCnf, argv[2] );

    sprintf( sNomeArquivo,"%s/%s",PATH_TEXTO,sArquivo );
    sprintf( sNomeArquivoCnf,"%s/%s",PATH_CFG,argv[2] );



    /*
    printf( "sNomeArquivo [%s]\n",sNomeArquivo );
    printf( "sNomeArquivoCnf [%s]\n",sNomeArquivoCnf );
    */

    fp = fopen( sNomeArquivo,"r");
    if ( fp == NULL )
    {
        //printf( "Erro ao abrir arquivo texto [%d]",errno);
        return 0;
    }

    linhas=0;
    while( !feof(fp) )
    {
        
        sLido[strlen(sLido)-1] = 0x0;

        /*------------------------------------------------*/
        // Atribui Tipo de Empresa
        /*------------------------------------------------*/
        if ( sLido != NULL )
        {
            memset( sLido, 0x0, sizeof(sLido) );
            
            fgets( sLido,sizeof(sLido),fp );
            if ( sLido[0] == NULL )
                break;

            linhas++;


            strcpy(sBuffer,sLido);
            p = strtok(sLido,"|");
            if ( p != NULL )
               idTipoEmpresa = atoi(p);

            i = 1;
            if ( idTipoEmpresa == 0 )
            {
                iCampos=0;
                while (p != NULL)
                {
                    p = strtok(NULL,"|");
                    i++;
                    if ( i < 4 )
                        continue;

                    if ( i > 21 )
                        break;

                    if ( i < 7 || i > 9 )
                       iCampos++;

                    /*
                    if ( p == NULL )
                        printf( "Campo faltante\n" );
                    */

                }
                if ( iCampos < 14 )
                    iErroLayOut = 0;  //  Nao faz mais conscistencia...
            }


            i = 1;
            if ( idTipoEmpresa == 1 )
            {
                p=strchr(sBuffer,'|');
                while (p!=NULL)
                {
                    //printf ("found at %d\n",p-sBuffer+1);
                    p=strchr(p+1,'|');
                    i++;
                    if ( i < 21 )
                        continue;
                    else
                        break;
                }
                p++;
                strcpy(sBuffer,p);
                iCampos=0;
                p = strtok(sBuffer,"|");
                while ( p != NULL )
                {
                    iCampos++;
                    p = strtok(NULL,"|");
                    if ( *p == 0x0a || *p == 0x0d )
                        break;
                }

                if ( iCampos < 6 )
                    iErroLayOut = 0;  //  Nao faz mais conscistencia...

            }
        
       }

    }

    fclose( fp );
    if ( iErroLayOut == 1 )
    {
        procInconsistencia( sNomeArquivoCnf, sArquivo, linhas );
        sprintf( comando,"rm -f %s",sNomeArquivo );
        //printf( "Removendo [%s]\n",comando );
        system( comando );
    }

  return 1;
}


