#Compile Tuxedo services
#This makefile generate a executable binary files

.SILENT:
DEP=Router Usuario AtendimentoCommonsRel Atendimento AndamentoObservacao ANDAOBSERVACAO AtendimentoFrm PPRelatorios WFRetWFCTI
SUBDIRS=`ls -l | grep ^d | grep -v CVS | grep -v Router | grep -w -v Usuario | grep -w -v AtendimentoCommonsRel | grep -w -v ANDAOBSERVACAO | grep -w -v AndamentoObservacao | grep -w -v Atendimento | grep -w -v AtendimentoFrm | grep -v WFRetWFCTI | awk '{print $$9}'`
all:
	for dir in $(DEP); do \
		cd $$dir; \
		make all; \
		cd ..; \
	done
	for dir in $(SUBDIRS); do \
		cd $$dir; \
		make all; \
		cd ..; \
	done

clean:
	for dir in $(DEP); do \
		cd $$dir; \
		make clean; \
		cd ..; \
	done	
	for dir in $(SUBDIRS); do \
		cd $$dir; \
		make clean; \
		cd ..; \
	done

install:
	for dir in $(DEP); do \
		cd $$dir; \
		make install; \
		cd ..; \
	done 
	for dir in $(SUBDIRS); do \
		cd $$dir; \
		make install; \
		cd ..; \
	done 

build:
	for dir in $(DEP); do \
		cd $$dir; \
		make; \
		make install; \
		cd ..; \
	done
	for dir in $(SUBDIRS); do \
		cd $$dir; \
		make; \
		make install; \
		cd ..; \
	done

