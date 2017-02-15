SUBDIRS=VOLPESSOA VOLPESSOAT VOLMSG VOLPAG TAVPAG VOLACESSO TAVLOJAT FRASECRET Intermed VOLPONTOS VOLWS sVOLt01 sVOLt02 sVOLn01 sVOLn02 sVOLn03 sVOLn07 sVOLn09

all:
	for dir in $(SUBDIRS); do \
		cd $$dir; \
		make all; \
		cd ..; \
	done

clean:
	for dir in $(SUBDIRS); do \
		cd $$dir; \
		make clean; \
		cd ..; \
	done

install:
	for dir in $(SUBDIRS); do \
		cd $$dir; \
		make install; \
		cd ..; \
	done 

build:
	for dir in $(SUBDIRS); do \
		cd $$dir; \
		make; \
		make install; \
		cd ..; \
	done
