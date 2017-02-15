#SUBDIRS=`ls -l | grep ^d | awk '{print $$9}'`
SUBDIRS=admsistemas atendimento fidelizacao campanha senha usuario interface cliente prepago canais prepagoTMA portabilidade

all:
	for dir in $(SUBDIRS); do \
		cd $$dir; \
		make; \
		cd ..; \
        done

clean:
	for dir in $(SUBDIRS); do \
		cd $$dir; \
		make clean; \
		cd ..; \
	done

install:
	if [ ! -d $(TUXDIR_DES) ] ; then mkdir $(TUXDIR_DES); fi
	if [ ! -d $(TUXDIR_DES)/config ] ; then mkdir $(TUXDIR_DES)/config; fi
	if [ ! -d $(TUXDIR_DES)/config/xml ] ; then mkdir $(TUXDIR_DES)/config/xml; fi
	if [ ! -d $(TUXDIR_DES)/config/env ] ; then mkdir $(TUXDIR_DES)/config/env; fi
	if [ ! -d $(TUXDIR_DES)/config/xml/app ] ; then mkdir $(TUXDIR_DES)/config/xml/app; fi
	if [ ! -d $(TUXDIR_DES)/config/env/app ] ; then mkdir $(TUXDIR_DES)/config/env/app; fi
	for dir in $(SUBDIRS); do \
		cd $$dir; \
		make install; \
		cd ..; \
	done 

build:
	for dir in $(SUBDIRS); do \
		cd $$dir; \
		make build; \
		cd ..; \
	done

