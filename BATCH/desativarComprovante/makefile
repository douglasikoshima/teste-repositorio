.SILENT:

APPNAME=desativarComprovante

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


CFLAGS= -L$(ORALIBDIR) -lclntsh -lld -lm -lm -lc_r -lpthreads
export CFLAGS

all:  build

build:$(APPNAME)

clean:
	rm -f $(COMPILE_SRC)/*.o $(COMPILE_SRC)/*.lis $(COMPILE_SRC)/ComprovanteServico.cpp $(COMPILE_BIN)/desativarComprovante
	rm -r -f $(COMPILE_BIN)
	echo 'make clean completo' 	


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
	# copia executa.sh
	cp -pf $(COMPILE_DIR)/executa.sh $(EXEC_DIR)
	cp -pf $(COMPILE_DIR)/desativarComprovante.sh $(EXEC_DIR)
	# copia executavel
	cp -pf $(COMPILE_BIN)/$(APPNAME) $(EXEC_BIN)
	# copia arquivos de configuracao comuns
	cp -pf $(BATCH_DIR)/oracle.`hostname`.`whoami`.cfg $(PROCESSOS_DIR)/oracle.cfg
	# copia arquivo de configuracao da aplicacao
	cp -pf $(COMPILE_CFG)/$(APPNAME).`hostname`.`whoami`.cfg $(EXEC_CFG)/$(APPNAME).cfg
	# copia para arquivo de configuracao com nome fora do padrao
	cp -pf $(EXEC_CFG)/$(APPNAME).cfg $(EXEC_CFG)/legado.cfg

uninstall:
	rm -r -f $(EXEC_DIR)


desativarComprovante: ComprovanteServico.cpp desativarComprovante.o Log.o MFile.o Properties.o Attribute.o Collection.o ComprovanteServico.o
	if ! [ -d $(COMPILE_BIN) ]; then mkdir $(COMPILE_BIN); fi 
	export CC=xlC;	\
	buildclient -w -f " -g " -f $(COMPILE_SRC)/desativarComprovante.o -f $(COMPILE_SRC)/ComprovanteServico.o -f $(COMPILE_SRC)/Attribute.o -f $(COMPILE_SRC)/Collection.o -f $(COMPILE_SRC)/Properties.o -f $(COMPILE_SRC)/MFile.o -f $(COMPILE_SRC)/Log.o -f "$(XERCESCPATH)/lib/libxerces-c25.0.a" -o $(COMPILE_BIN)/desativarComprovante


Log.o: $(COMPILE_COMMONS_LOG)/Log.cpp makefile
	xlC -c $(PINCLUDE) $(COMPILE_COMMONS_LOG)/Log.cpp -o $(COMPILE_SRC)/Log.o
	echo "compilando Log.cpp"

MFile.o: $(COMPILE_COMMONS_PROP)/MFile.cpp makefile
	xlC -c $(PINCLUDE) $(COMPILE_COMMONS_PROP)/MFile.cpp -o $(COMPILE_SRC)/MFile.o
	echo "compilando MFile.cpp"

Properties.o: $(COMPILE_COMMONS_PROP)/Properties.cpp makefile
	xlC -c $(PINCLUDE) $(COMPILE_COMMONS_PROP)/Properties.cpp -o $(COMPILE_SRC)/Properties.o
	echo "compilando Properties.cpp"

Attribute.o: $(COMPILE_COMMONS_PROP)/Attribute.cpp makefile
	xlC -c $(PINCLUDE) $(COMPILE_COMMONS_PROP)/Attribute.cpp -o $(COMPILE_SRC)/Attribute.o
	echo "compilando Attribute.cpp"

Collection.o: $(COMPILE_COMMONS_COLE)/Collection.cpp makefile
	xlC -c $(PINCLUDE) $(COMPILE_COMMONS_COLE)/Collection.cpp -o $(COMPILE_SRC)/Collection.o
	echo "compilando Collection.cpp"

ComprovanteServico.o: $(COMPILE_SRC)/ComprovanteServico.cpp makefile
	xlC -c -I$(COMPILE_INC) $(PINCLUDE) $(COMPILE_SRC)/ComprovanteServico.cpp -o $(COMPILE_SRC)/ComprovanteServico.o
	echo "compilando ComprovanteServico.cpp"

desativarComprovante.o: $(COMPILE_SRC)/desativarComprovante.cpp makefile
	xlC -c -I$(COMPILE_INC) $(PINCLUDE) $(COMPILE_SRC)/desativarComprovante.cpp -o $(COMPILE_SRC)/desativarComprovante.o
	echo "compilando desativarComprovante.cpp"

ComprovanteServico.cpp: $(COMPILE_SRC)/ComprovanteServico.pcpp makefile
	proc include=$(TUXDIR)/include  parse=none cpp_suffix=cpp code=cpp $(COMPILE_SRC)/ComprovanteServico.pcpp
