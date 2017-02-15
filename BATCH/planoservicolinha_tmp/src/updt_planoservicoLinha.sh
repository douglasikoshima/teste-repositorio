#!/bin/sh
for filename in ../data/*.txt
do    
#	echo "Variable filename is set to $filename..."
	if [ ! -f $filename ] ; then exit; fi
	./planoservicolinha.sh $filename
#	rm $filename

done