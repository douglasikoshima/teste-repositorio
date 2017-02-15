#!/bin/sh

CFG='cargaDadosSRE.cfg'

DIR=$(dirname $0)

cd "$DIR"
. "${HOME}/.profile"
. "$DIR/$CFG"

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=$DIR/log/cargaDadosSRE.`whoami`.log

export PERL5LIB=$DIR
export PERL5LIB=$DIR/../../perl64/lib64:$DIR/../../perl64/lib64/site_perl:$PERL5LIB
export PATH=/usr/opt/perl5/bin:$PATH


echo "`date +%Y%m%d-%H%M%S` -- Execucao....: $NOME" >> ${logfile}

#perl -i -pe 's/\r\n/\n/g' $DIR/abrevLocalidade.txt
#perl -i -pe 's/\r\n/\n/g' $DIR/abrevLogradouro.txt
#perl -i -pe 's/\r\n/\n/g' $DIR/abrevBairro.txt
#perl -i -pe 's/\r\n/\n/g' $DIR/DNE/*.TXT 2> /dev/null
#perl -i -pe 's/\r\n/\n/g' $DIR/DNE/*.txt 2> /dev/null
#perl -i -pe 's/\r\n/\n/g' $DIR/DNE/*.LOG 2> /dev/null
#perl -i -pe 's/\r\n/\n/g' $DIR/DNE/*.log 2> /dev/null


ulimit -c unlimited
ulimit -d unlimited
ulimit -m unlimited
ulimit -n unlimited
ulimit -s 4194304


nohup perl_64bit $DIR/loadDNE.pl --input=$DIR/DNE >> $DIR/log/$NOME 2>&1
RET=$?

echo "`date +%Y%m%d-%H%M%S` -- Fim execucao: $NOME" >> ${logfile}

bash executaRelCargaCdDNE.sh

echo "Saindo com codigo de retorno: $RET"

exit $RET

