.SILENT:

APPNAME=ativarAparelho

BATCH_DIR=$(HOME)/BATCH

COMPILE_DIR=$(BATCH_DIR)/$(APPNAME)
COMPILE_SRC=$(COMPILE_DIR)/src
COMPILE_BIN=$(COMPILE_DIR)/bin
COMPILE_CFG=$(COMPILE_DIR)/cfg
COMPILE_INC=$(COMPILE_DIR)/include

COMPILE_COMMONS_TINYXML=$(HOME)/BATCH/commons/tinyxml/src
COMPILE_COMMONS_DOM=$(HOME)/BATCH/commons/Dom/src
COMPILE_COMMONS_COLE=$(HOME)/BATCH/commons/Collection/src
COMPILE_COMMONS_PROP=$(HOME)/BATCH/commons/Propriedade/src
COMPILE_COMMONS_LOG=$(HOME)/BATCH/commons/Log/src

DEPLOY_DIR=$(HOME)/deploy
PROCESSOS_DIR=$(DEPLOY_DIR)/processos

EXEC_DIR=$(PROCESSOS_DIR)/$(APPNAME)
EXEC_BIN=$(EXEC_DIR)/bin
EXEC_CFG=$(EXEC_DIR)/bin
EXEC_LOG=$(EXEC_DIR)/log

INCDIR=include
CFLAGS=-L$(ORALIBDIR) -L/opt/esac/dvfo01rt/staging/lib -L/opt/tuxedo/current/lib -L/usr/lib -L/usr/local/lib -lclntsh -lld -lm -lm -lc_r -lpthreads -qrtti=all
export CFLAGS

export CC='xlC -qrtti'
XERCES_CLIB=-l-lxerces-c
FWLIB=-f "-ltuxfw"
CC=$(SACCPP) $(PINCLUDE) $(PLIBPATH)

all:  build

build:$(APPNAME)

clean:
	rm -f $(COMPILE_SRC)/*.o $(COMPILE_SRC)/*.lis $(COMPILE_SRC)/ativarAparelhoPC.cpp $(COMPILE_BIN)/ativarAparelho
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
	cp -pf $(COMPILE_DIR)/ativarAparelho.sh $(EXEC_DIR)
	# copia executavel
	cp -pf $(COMPILE_BIN)/$(APPNAME) $(EXEC_BIN)
	# copia arquivos de configuracao comuns
	cp -pf $(BATCH_DIR)/oracle.`hostname`.`whoami`.cfg $(PROCESSOS_DIR)/oracle.cfg
	# copia arquivo de configuracao da aplicacao
	cp -pf $(COMPILE_CFG)/$(APPNAME).`hostname`.`whoami`.cfg $(EXEC_CFG)/$(APPNAME).cfg
	# copia para arquivo de configuracao com nome fora do padrao
	#cp -pf $(EXEC_CFG)/$(APPNAME).cfg $(EXEC_CFG)

uninstall:
	rm -r -f $(EXEC_DIR)

ativarAparelho: ativarAparelhoPC.cpp ativarAparelho.o tinystr.o tinyxml.o tinyxmlerror.o tinyxmlparser.o Log.o MFile.o Properties.o Attribute.o Collection.o ativarAparelhoPC.o
	if ! [ -d $(COMPILE_BIN) ]; then mkdir $(COMPILE_BIN); fi 
	echo "vai gerar $(COMPILE_BIN)/ativarAparelho."
	buildclient -w -f " -g " $(FWLIB) -f "-lxml2 -lxslt" -f $(COMPILE_SRC)/ativarAparelho.o -f $(COMPILE_SRC)/ativarAparelhoPC.o -f $(COMPILE_SRC)/Attribute.o -f $(COMPILE_SRC)/Collection.o -f $(COMPILE_SRC)/Properties.o -f $(COMPILE_SRC)/MFile.o -f $(COMPILE_SRC)/Log.o -f $(COMPILE_SRC)/tinystr.o -f $(COMPILE_SRC)/tinyxml.o -f $(COMPILE_SRC)/tinyxmlerror.o -f $(COMPILE_SRC)/tinyxmlparser.o $(XERCES_CLIB) -o $(COMPILE_BIN)/ativarAparelho
	echo "$(COMPILE_BIN)/ativarAparelho gerado."

tinystr.o: $(COMPILE_COMMONS_TINYXML)/tinystr.cpp makefile
	echo "vai compilar tinystr.cpp ..."
	$(CC) -c -g $(COMPILE_COMMONS_TINYXML)/tinystr.cpp -o $(COMPILE_SRC)/tinystr.o
	echo "compilou tinystr.cpp"

tinyxml.o: $(COMPILE_COMMONS_TINYXML)/tinyxml.cpp makefile
	echo "vai compilar tinyxml.cpp ..."
	$(CC) -c -g $(COMPILE_COMMONS_TINYXML)/tinyxml.cpp -o $(COMPILE_SRC)/tinyxml.o
	echo "compilou tinyxml.cpp"

tinyxmlerror.o: $(COMPILE_COMMONS_TINYXML)/tinyxmlerror.cpp makefile
	echo "vai compilar tinyxmlerror.cpp ..."
	$(CC) -c -g $(COMPILE_COMMONS_TINYXML)/tinyxmlerror.cpp -o $(COMPILE_SRC)/tinyxmlerror.o
	echo "compilou tinyxmlerror.cpp"

tinyxmlparser.o: $(COMPILE_COMMONS_TINYXML)/tinyxmlparser.cpp makefile
	echo "vai compilar tinyxmlparser.cpp ..."
	$(CC) -c -g $(COMPILE_COMMONS_TINYXML)/tinyxmlparser.cpp -o $(COMPILE_SRC)/tinyxmlparser.o
	echo "compilou tinyxmlparser.cpp"

#Dom.o: $(COMPILE_COMMONS_DOM)/Dom.cpp makefile
#	echo "vai compilar Dom.cpp ..."
#	$(CC) -c -g $(COMPILE_COMMONS_DOM)/Dom.cpp -o $(COMPILE_SRC)/Dom.o
#	echo "compilou Dom.cpp"

Log.o: $(COMPILE_COMMONS_LOG)/Log.cpp makefile
	echo "vai compilar Log.cpp ..."
	$(CC) -c -g $(COMPILE_COMMONS_LOG)/Log.cpp -o $(COMPILE_SRC)/Log.o
	echo "compilou Log.cpp"

MFile.o: $(COMPILE_COMMONS_PROP)/MFile.cpp makefile
	echo "vai compilar MFile.cpp ..."
	$(CC) -c -g $(COMPILE_COMMONS_PROP)/MFile.cpp -o $(COMPILE_SRC)/MFile.o
	echo "compilou MFile.cpp"

Properties.o: $(COMPILE_COMMONS_PROP)/Properties.cpp makefile
	echo "vai compilar Properties.cpp ..."
	$(CC) -c -g $(COMPILE_COMMONS_PROP)/Properties.cpp -o $(COMPILE_SRC)/Properties.o
	echo "compilou Properties.cpp"

Attribute.o: $(COMPILE_COMMONS_PROP)/Attribute.cpp makefile
	echo "vai compilar Attribute.cpp ..."
	$(CC) -c -g $(COMPILE_COMMONS_PROP)/Attribute.cpp -o $(COMPILE_SRC)/Attribute.o
	echo "compilou Attribute.cpp"

Collection.o: $(COMPILE_COMMONS_COLE)/Collection.cpp makefile
	echo "vai compilar Collection.cpp ..."
	$(CC) -c -g $(COMPILE_COMMONS_COLE)/Collection.cpp -o $(COMPILE_SRC)/Collection.o
	echo "compilou Collection.cpp"

ativarAparelhoPC.o: $(COMPILE_SRC)/ativarAparelhoPC.cpp makefile
	echo "vai compilar ativarAparelhoPC.cpp ..."
	$(CC) -c -g $(COMPILE_SRC)/ativarAparelhoPC.cpp -o $(COMPILE_SRC)/ativarAparelhoPC.o
	echo "compilou ativarAparelhoPC.cpp"

ativarAparelho.o: $(COMPILE_SRC)/ativarAparelho.cpp makefile
	echo "vai compilar ativarAparelho.cpp ..."
	$(CC) -c -g $(COMPILE_SRC)/ativarAparelho.cpp -o $(COMPILE_SRC)/ativarAparelho.o
	echo "compilou ativarAparelho.cpp"

ativarAparelhoPC.cpp: $(COMPILE_SRC)/ativarAparelhoPC.pcpp makefile
	echo "vai gerar ativarAparelhoPC.cpp ..."
	proc include=$(TUXDIR)/include  parse=none cpp_suffix=cpp code=cpp $(COMPILE_SRC)/ativarAparelhoPC.pcpp
	echo "gerou ativarAparelhoPC.cpp"
