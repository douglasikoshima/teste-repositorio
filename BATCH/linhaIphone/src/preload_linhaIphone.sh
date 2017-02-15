#!/bin/sh

for filename in ../data/MAILING*.txt
do    
#	echo "Variable filename is set to $filename..."
	if [ ! -f $filename ] ; then exit; fi
	./load_linhaIphone.sh $filename
	INPUTNAME=`echo ${filename} | cut -d"." -f1`
	NAMEREN=${INPUTNAME}".`date +%Y%m%d_%H%M%S`.prc"
	mv $filename ${NAMEREN}

done