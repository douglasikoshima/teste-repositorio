#!/bin/sh
for filename in ../data/*PCONTATO*.txt
do
    ./VerificaPontoContato $filename VerificaPontoContato.cfg
#	echo "Variable filename is set to $filename..."
	if [ ! -f $filename ] ; then exit; fi
	./load_pontocontato.sh $filename
	rm $filename
done
