#!/bin/sh


# Seta separador de registro para nova linha
IFS='
'

# Arquivos perl e cfg que serao carregados
PRL='cargacluster.pl'
CFG='cargacluster.cfg'

# Obtem nome e local deste script
EXE=$(basename $0)
DIR=$(dirname $0)

# Entra no dirtorio do script
cd "$DIR"

# Arquivo de log de execucao
DTM=$(date '+%Y%m%d-%H%M%S')
LOG="log/$DTM.log"

# Importa profile e cfg
. "${HOME}/.profile"
. "$DIR/$CFG"

# Locale oracle
export NLS_LANG=AMERICAN_AMERICA.WE8ISO8859P1

# Converte arquivos de dados para formato unix
#for d2u in $(find $DIR_DATA -type f -name '*.[Tt][Xx][Tt]')
#do
#	perl -i -pe 's/\r\n/\n/g' "$d2u"
#done


# Exporta variaveis do cfg
export DB_USER
export DB_PASS
export DB_NAME
export DIR_DATA
export DIR_PRC
export DIR_ERR
export DIR_LOG
export DIR_TMP
export PARAM_VOLUMETRIA
export PARAM_COMMIT

	# Exporta variavels do perl (Bibliotecas e executavel 64bits)
	export PERL5LIB=$DIR/../../perl64/lib64:$DIR/../../perl64/lib64/site_perl:$PERL5LIB
	export PATH=/usr/opt/perl5/bin:$PATH 
	
	# Executa script perl
nohup perl_64bit -W $PRL > $LOG 2>&1
retcode=$?

if [ $retcode == 0 ]
then
	echo "Processo executado com sucesso!"
	exit 0
else
	echo "Processo executado com erro/critica: $retcode"
	exit 1
fi


