/*
 * main.cpp
 *
 *  Created on: 09/05/2013
 *      Author: Jones Randis
 */

#include "garbage_collector.h"
#include "CValidarLinhaCPF.h"

int main(int argC, char* argV[])
{
	GC_INIT();
	GC_find_leak = 1;

	int retval = batch::CValidarLinhaCPF::main(argC, argV);

	GC_gcollect();
	return retval;
}
