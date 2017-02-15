.SILENT:

APPNAME=CadUsuarioMassivo

BATCH_DIR=$(HOME)/BATCH

COMPILE_DIR=$(BATCH_DIR)/$(APPNAME)
COMPILE_SRC=$(COMPILE_DIR)/src
COMPILE_BIN=$(COMPILE_DIR)/bin
COMPILE_CFG=$(COMPILE_DIR)/cfg
COMPILE_INC=$(COMPILE_DIR)/include

COMPILE_COMMONS_COLE=$(HOME)/BATCH/commons/Collection/src
COMPILE_COMMONS_PROP=$(HOME)/BATCH/commons/Propriedade/src
COMPILE_COMMONS_LOG=$(HOME)/BATCH/commons/Log/src

DEPLOY_DIR=$(HOME)/deploy
PROCESSOS_DIR=$(DEPLOY_DIR)/processos

EXEC_DIR=$(PROCESSOS_DIR)/$(APPNAME)
EXEC_BIN=$(EXEC_DIR)/bin
EXEC_CFG=$(EXEC_DIR)/bin
EXEC_LOG=$(EXEC_DIR)/log
EXEC_DTA=$(EXEC_DIR)/data
EXEC_ERR=$(EXEC_DIR)/error


CFLAGS= -L$(ORALIBDIR) -lclntsh -lld -lm -lc -lc_r -lbsd -ldl -lpthreads -lbsd
export CFLAGS

CXXFLAGS= -L$(ORALIBDIR) -lclntsh -lld -lm -lc -lc_r -lbsd -ldl -lpthreads -lbsd
export CXXFLAGS

all:  build

build:$(APPNAME)

clean:
	rm -f $(COMPILE_SRC)/*.o $(COMPILE_SRC)/*.lis $(COMPILE_SRC)/CadUsuarioMassivo.cpp $(COMPILE_BIN)/CadUsuarioMassivo
	rm -r -f $(COMPILE_BIN)


install:
	umask 077
	# cria estrutura inicial
	if [ ! -d $(DEPLOY_DIR) ] ; then mkdir $(DEPLOY_DIR); fi
	if [ ! -d $(PROCESSOS_DIR) ] ; then mkdir $(PROCESSOS_DIR); fi
	# cria diretorios de aplicacao
	if [ ! -d $(EXEC_DIR) ] ; then mkdir $(EXEC_DIR); fi
	if [ ! -d $(EXEC_BIN) ] ; then mkdir $(EXEC_BIN); fi
	if [ ! -d $(EXEC_CFG) ] ; then mkdir $(EXEC_CFG); fi
	if [ ! -d $(EXEC_LOG) ] ; then mkdir $(EXEC_LOG); fi
	if [ ! -d $(EXEC_DTA) ] ; then mkdir $(EXEC_DTA); fi
	if [ ! -d $(EXEC_ERR) ] ; then mkdir $(EXEC_ERR); fi
	# copia executa.sh
	cp -pf $(COMPILE_DIR)/CadUsuarioMassivo.sh $(EXEC_DIR)
	chmod 754 $(EXEC_DIR)/CadUsuarioMassivo.sh
	# copia executavel
	cp -pf $(COMPILE_BIN)/$(APPNAME) $(EXEC_BIN)
	# copia arquivos de configuracao comuns
	cp -pf $(BATCH_DIR)/oracle.`hostname`.`whoami`.cfg $(PROCESSOS_DIR)/oracle.cfg
	# copia arquivo de configuracao da aplicacao
	cp -pf $(COMPILE_CFG)/$(APPNAME).`hostname`.`whoami`.cfg $(EXEC_CFG)/$(APPNAME).cfg
	cp -pf $(COMPILE_DIR)/LDAPUser.jar $(EXEC_BIN)
	cp -pf $(COMPILE_DIR)/LDAPUser.sh $(EXEC_BIN)
	
	chmod a+w $(EXEC_CFG)/$(APPNAME).cfg
	chmod a+w $(EXEC_LOG) $(EXEC_DTA) $(EXEC_ERR)
	chmod 755 $(EXEC_BIN)/LDAPUser.jar
	chmod 755 $(EXEC_BIN)/LDAPUser.sh

uninstall:
	rm -r -f $(EXEC_DIR)


CadUsuarioMassivo: CadUsuarioMassivo.cpp CadUsuarioMassivo.o Log.o MFile.o
	if ! [ -d $(COMPILE_BIN) ]; then mkdir $(COMPILE_BIN); fi 
	export CC=xlC;	\
	buildclient -w -f " -g " -f $(COMPILE_SRC)/CadUsuarioMassivo.o -f $(COMPILE_SRC)/MFile.o -f $(COMPILE_SRC)/Log.o -f "$(XERCESCPATH)/lib/libxerces-c25.0.a" -o $(COMPILE_BIN)/CadUsuarioMassivo


Log.o: $(COMPILE_COMMONS_LOG)/Log.cpp makefile
	xlC -g -c $(PINCLUDE) $(COMPILE_COMMONS_LOG)/Log.cpp -o $(COMPILE_SRC)/Log.o

MFile.o: $(COMPILE_COMMONS_PROP)/MFile.cpp makefile
	xlC -g -c $(PINCLUDE) $(COMPILE_COMMONS_PROP)/MFile.cpp -o $(COMPILE_SRC)/MFile.o

CadUsuarioMassivo.o: $(COMPILE_SRC)/CadUsuarioMassivo.cpp makefile
	xlC -g -c -I$(COMPILE_INC) $(PINCLUDE) $(COMPILE_SRC)/CadUsuarioMassivo.cpp -o $(COMPILE_SRC)/CadUsuarioMassivo.o

CadUsuarioMassivo.cpp: $(COMPILE_SRC)/CadUsuarioMassivo.pcpp makefile
	proc include=$(TUXDIR)/include  parse=none cpp_suffix=cpp code=cpp $(COMPILE_SRC)/CadUsuarioMassivo.pcpp
