#!/bin/sh
for filename in ../data/*.csv
do    
#	echo "Variable filename is set to $filename..."
	if [ ! -f $filename ] ; then exit; fi
	./load_linhaParceiro.sh $filename
#	rm $filename

done