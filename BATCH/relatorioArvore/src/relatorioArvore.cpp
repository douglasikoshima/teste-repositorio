
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <time.h>

#include "../include/cRelatArvore.h"

#define LEN_DATA                    14


/*
int AbreArqConf(FILE **pFile, char *pszPathArq, cTrace * log );
int FechaArqConf(FILE **pFile);
int LeArqConf(FILE **pFile, char *szBuffer, int iSizeOfBuffer,cTrace * log );
int ObtemPath(char *pszNomeArqConf, char *pszChave, char *pszPath, cTrace * log );
*/

int main( int argc, char *argv[] )
{
    char sArquivoConfiguracao[512];
    char sDiretorioSaida[512];
    int i;
    string Msg;

    if ( argc < 1 )
    {
        printf("Uso: relatorioArvore [caminho/arquivo de configuracao] [diretorio de saida]");
        exit( 0 );
    }   

    for ( i=1; i < argc; i++ )
    {
        if ( i == 1 )
        {
            strcpy( sArquivoConfiguracao, argv[i] );
        }
    }
    
    cRelatorioArvore pclRelatorioArvore( sArquivoConfiguracao );
    cTrace trc( pclRelatorioArvore.debugOn );
    /*
     * Atribui handle da classe cTrace para Instancia cTrace de cRlatorioArvore
     * POR FORCA MAIOR (TEMPO) A CODIFICACAO
     * TORNOU-SE SUJA, DEVERAH SER MELHORADA NO FUTURO...
     */
    pclRelatorioArvore.log = &trc;

    strcpy( sDiretorioSaida, pclRelatorioArvore.sDiretorioSaida );
    try
    {
		pclRelatorioArvore.Conectar();
        pclRelatorioArvore.OrganizaArquivoSaida();
        for ( ;; )
        {
            if ( !pclRelatorioArvore.CarregaVariaveis() ) break;
            pclRelatorioArvore.Executa();
        }
		pclRelatorioArvore.Desconectar();
    }
    catch ( char  * msgOra )
    {
        trc.Trace( ERROR_LEVEL,"### ERROR=> [%s]", msgOra );
		pclRelatorioArvore.Desconectar();
    }
    catch ( ... )
    {
		trc.Trace( ERROR_LEVEL,"### erro desconhecido" );
		pclRelatorioArvore.Desconectar();
    }


  return 0;
}


