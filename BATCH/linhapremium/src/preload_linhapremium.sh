#!/bin/sh
for filename in ../data/FO_AVIP*.txt
do    
#	echo "Variable filename is set to $filename..."
	if [ ! -f $filename ] ; then exit; fi
	./load_linhapremium.sh $filename
	rm $filename

done