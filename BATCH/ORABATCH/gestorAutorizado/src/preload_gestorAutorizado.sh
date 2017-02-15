#!/bin/sh
for filename in ../data/*.txt
do    
#	echo "Variable filename is set to $filename..."
	if [ ! -f $filename ] ; then exit; fi
	./load_gestorAutorizado.sh $filename

done