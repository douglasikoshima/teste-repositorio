# Microsoft Developer Studio Project File - Name="PPPESPESADC" - Package Owner=<4>
# Microsoft Developer Studio Generated Build File, Format Version 6.00
# ** DO NOT EDIT **

# TARGTYPE "Win32 (x86) Console Application" 0x0103

CFG=PPPESPESADC - Win32 Debug
!MESSAGE This is not a valid makefile. To build this project using NMAKE,
!MESSAGE use the Export Makefile command and run
!MESSAGE 
!MESSAGE NMAKE /f "PPPESPESADC.mak".
!MESSAGE 
!MESSAGE You can specify a configuration when running NMAKE
!MESSAGE by defining the macro CFG on the command line. For example:
!MESSAGE 
!MESSAGE NMAKE /f "PPPESPESADC.mak" CFG="PPPESPESADC - Win32 Debug"
!MESSAGE 
!MESSAGE Possible choices for configuration are:
!MESSAGE 
!MESSAGE "PPPESPESADC - Win32 Release" (based on "Win32 (x86) Console Application")
!MESSAGE "PPPESPESADC - Win32 Debug" (based on "Win32 (x86) Console Application")
!MESSAGE 

# Begin Project
# PROP AllowPerConfigDependencies 0
# PROP Scc_ProjName ""
# PROP Scc_LocalPath ""
CPP=cl.exe
RSC=rc.exe

!IF  "$(CFG)" == "PPPESPESADC - Win32 Release"

# PROP BASE Use_MFC 0
# PROP BASE Use_Debug_Libraries 0
# PROP BASE Output_Dir "Release"
# PROP BASE Intermediate_Dir "Release"
# PROP BASE Target_Dir ""
# PROP Use_MFC 0
# PROP Use_Debug_Libraries 0
# PROP Output_Dir "Release"
# PROP Intermediate_Dir "Release"
# PROP Target_Dir ""
# ADD BASE CPP /nologo /W3 /GX /O2 /D "WIN32" /D "NDEBUG" /D "_CONSOLE" /D "_MBCS" /YX /FD /c
# ADD CPP /nologo /W3 /GX /O2 /D "WIN32" /D "NDEBUG" /D "_CONSOLE" /D "_MBCS" /YX /FD /c
# ADD BASE RSC /l 0x416 /d "NDEBUG"
# ADD RSC /l 0x416 /d "NDEBUG"
BSC32=bscmake.exe
# ADD BASE BSC32 /nologo
# ADD BSC32 /nologo
LINK32=link.exe
# ADD BASE LINK32 kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib /nologo /subsystem:console /machine:I386
# ADD LINK32 kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib /nologo /subsystem:console /machine:I386

!ELSEIF  "$(CFG)" == "PPPESPESADC - Win32 Debug"

# PROP BASE Use_MFC 0
# PROP BASE Use_Debug_Libraries 1
# PROP BASE Output_Dir "Debug"
# PROP BASE Intermediate_Dir "Debug"
# PROP BASE Target_Dir ""
# PROP Use_MFC 0
# PROP Use_Debug_Libraries 1
# PROP Output_Dir "Debug"
# PROP Intermediate_Dir "Debug"
# PROP Target_Dir ""
# ADD BASE CPP /nologo /W3 /Gm /GX /ZI /Od /D "WIN32" /D "_DEBUG" /D "_CONSOLE" /D "_MBCS" /YX /FD /GZ /c
# ADD CPP /nologo /W3 /Gm /GX /ZI /Od /D "WIN32" /D "_DEBUG" /D "_CONSOLE" /D "_MBCS" /FR /YX /FD /GZ /c
# ADD BASE RSC /l 0x416 /d "_DEBUG"
# ADD RSC /l 0x416 /d "_DEBUG"
BSC32=bscmake.exe
# ADD BASE BSC32 /nologo
# ADD BSC32 /nologo
LINK32=link.exe
# ADD BASE LINK32 kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib /nologo /subsystem:console /debug /machine:I386 /pdbtype:sept
# ADD LINK32 kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib /nologo /subsystem:console /debug /machine:I386 /pdbtype:sept

!ENDIF 

# Begin Target

# Name "PPPESPESADC - Win32 Release"
# Name "PPPESPESADC - Win32 Debug"
# Begin Group "Source Files"

# PROP Default_Filter "cpp;c;cxx;rc;def;r;odl;idl;hpj;bat"
# Begin Source File

SOURCE=..\..\negocio\ppbasico\src\CPais.cpp
# End Source File
# Begin Source File

SOURCE=..\..\negocio\ppbasico\src\CPaisItr.cpp
# End Source File
# Begin Source File

SOURCE=..\..\negocio\ppbasico\src\CTipoComunicacao.cpp
# End Source File
# Begin Source File

SOURCE=..\..\negocio\ppbasico\src\CTipoComunicacaoItr.cpp
# End Source File
# Begin Source File

SOURCE=..\..\negocio\ppbasico\src\CTipoDocumento.cpp
# End Source File
# Begin Source File

SOURCE=..\..\negocio\ppbasico\src\CTipoDocumentoItr.cpp
# End Source File
# Begin Source File

SOURCE=..\..\negocio\ppbasico\src\CUF.cpp
# End Source File
# Begin Source File

SOURCE=..\..\negocio\ppbasico\src\CUFItr.cpp
# End Source File
# Begin Source File

SOURCE=..\..\negocio\ppbasico\src\CValorPossivel.cpp
# End Source File
# Begin Source File

SOURCE=..\..\negocio\ppbasico\src\CValorPossivelItr.cpp
# End Source File
# Begin Source File

SOURCE=..\..\negocio\ppbasico\src\Documento.cpp
# End Source File
# Begin Source File

SOURCE=..\..\negocio\ppbasico\src\Documentopc.cpp
# End Source File
# Begin Source File

SOURCE=..\..\negocio\ppbasico\src\LinhaConta.cpp
# End Source File
# Begin Source File

SOURCE=..\..\negocio\ppbasico\src\LinhaContapc.cpp
# End Source File
# Begin Source File

SOURCE=..\..\negocio\ppbasico\src\Pessoa.cpp
# End Source File
# Begin Source File

SOURCE=..\..\negocio\ppbasico\src\PessoaComunicacao.cpp
# End Source File
# Begin Source File

SOURCE=..\..\negocio\ppbasico\src\PessoaComunicacaopc.cpp
# End Source File
# Begin Source File

SOURCE=..\..\negocio\ppbasico\src\PessoaEndereco.cpp
# End Source File
# Begin Source File

SOURCE=..\..\negocio\ppbasico\src\PessoaEnderecopc.cpp
# End Source File
# Begin Source File

SOURCE=..\..\negocio\ppbasico\src\PessoaFisica.cpp
# End Source File
# Begin Source File

SOURCE=..\..\negocio\ppbasico\src\PessoaFisicapc.cpp
# End Source File
# Begin Source File

SOURCE=..\..\negocio\ppbasico\src\PessoaJuridica.cpp
# End Source File
# Begin Source File

SOURCE=..\..\negocio\ppbasico\src\PessoaJuridicapc.cpp
# End Source File
# Begin Source File

SOURCE=..\..\negocio\ppbasico\src\Pessoapc.cpp
# End Source File
# Begin Source File

SOURCE=.\src\PPPESPESADC.cpp
# End Source File
# Begin Source File

SOURCE=..\..\negocio\ppbasico\src\PrePagoException.cpp
# End Source File
# Begin Source File

SOURCE=.\src\PsqPFDocDadosContatopc.cpp
# End Source File
# Begin Source File

SOURCE=.\src\PsqPFDocDadosDocpc.cpp
# End Source File
# Begin Source File

SOURCE=.\src\PsqPFDocEMailCompc.cpp
# End Source File
# Begin Source File

SOURCE=.\src\PsqPFDocEMailPartpc.cpp
# End Source File
# Begin Source File

SOURCE=.\src\PsqPFDocLinPospc.cpp
# End Source File
# Begin Source File

SOURCE=.\src\PsqPFDocLinPrepc.cpp
# End Source File
# Begin Source File

SOURCE=.\src\PsqPJDocLinPospc.cpp
# End Source File
# Begin Source File

SOURCE=.\src\PsqPJDocLinPrepc.cpp
# End Source File
# Begin Source File

SOURCE=..\..\..\..\TuxCli\RouterClient.cpp
# End Source File
# Begin Source File

SOURCE=..\..\negocio\ppbasico\src\Tools.cpp
# End Source File
# End Group
# Begin Group "Header Files"

# PROP Default_Filter "h;hpp;hxx;hm;inl"
# Begin Source File

SOURCE=..\..\negocio\ppbasico\include\Global.h
# End Source File
# Begin Source File

SOURCE=..\..\negocio\ppbasico\include\PrePagoException.h
# End Source File
# Begin Source File

SOURCE=.\include\PsqPFDoc.h
# End Source File
# Begin Source File

SOURCE=.\include\PsqPFDocDdsCttpc.h
# End Source File
# Begin Source File

SOURCE=.\include\PsqPFDocpc.h
# End Source File
# Begin Source File

SOURCE=.\include\PsqPJDocDdsCttpc.h
# End Source File
# Begin Source File

SOURCE=..\..\..\..\TuxCli\RouterClient.h
# End Source File
# End Group
# Begin Group "Resource Files"

# PROP Default_Filter "ico;cur;bmp;dlg;rc2;rct;bin;rgs;gif;jpg;jpeg;jpe"
# Begin Source File

SOURCE=.\makefile
# End Source File
# Begin Source File

SOURCE=..\..\servidor\srvprepago\makefile
# End Source File
# End Group
# Begin Group "ProC"

# PROP Default_Filter "*.pcpp"
# Begin Source File

SOURCE=..\..\negocio\ppbasico\src\CPais.pcpp

!IF  "$(CFG)" == "PPPESPESADC - Win32 Release"

!ELSEIF  "$(CFG)" == "PPPESPESADC - Win32 Debug"

# Begin Custom Build
InputDir=\Projeto\FrontOffice\EVOLUTIVA-V1\TUXEFO\prepago\negocio\ppbasico\src
InputPath=..\..\negocio\ppbasico\src\CPais.pcpp
InputName=CPais

"$(InputDir)/$(InputName).cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc  parse=none  cpp_suffix=cpp  code=cpp $(InputPath)  include=.\include include=..\..\negocio\ppbasico\include

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=..\..\negocio\ppbasico\src\CTipoComunicacao.pcpp

!IF  "$(CFG)" == "PPPESPESADC - Win32 Release"

!ELSEIF  "$(CFG)" == "PPPESPESADC - Win32 Debug"

# Begin Custom Build
InputDir=\Projeto\FrontOffice\EVOLUTIVA-V1\TUXEFO\prepago\negocio\ppbasico\src
InputPath=..\..\negocio\ppbasico\src\CTipoComunicacao.pcpp
InputName=CTipoComunicacao

"$(InputDir)/$(InputName).cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc  parse=none  cpp_suffix=cpp  code=cpp $(InputPath)  include=.\include include=..\..\negocio\ppbasico\include

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=..\..\negocio\ppbasico\src\CUF.pcpp

!IF  "$(CFG)" == "PPPESPESADC - Win32 Release"

!ELSEIF  "$(CFG)" == "PPPESPESADC - Win32 Debug"

# Begin Custom Build
InputDir=\Projeto\FrontOffice\EVOLUTIVA-V1\TUXEFO\prepago\negocio\ppbasico\src
InputPath=..\..\negocio\ppbasico\src\CUF.pcpp
InputName=CUF

"$(InputDir)/$(InputName).cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc  parse=none  cpp_suffix=cpp  code=cpp $(InputPath)  include=.\include include=..\..\negocio\ppbasico\include

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=..\..\negocio\ppbasico\src\CValorPossivel.pcpp

!IF  "$(CFG)" == "PPPESPESADC - Win32 Release"

!ELSEIF  "$(CFG)" == "PPPESPESADC - Win32 Debug"

# Begin Custom Build
InputDir=\Projeto\FrontOffice\EVOLUTIVA-V1\TUXEFO\prepago\negocio\ppbasico\src
InputPath=..\..\negocio\ppbasico\src\CValorPossivel.pcpp
InputName=CValorPossivel

"$(InputDir)/$(InputName).cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc  parse=none  cpp_suffix=cpp  code=cpp $(InputPath)  include=.\include include=..\..\negocio\ppbasico\include

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=..\..\negocio\ppbasico\src\Documentopc.pcpp

!IF  "$(CFG)" == "PPPESPESADC - Win32 Release"

!ELSEIF  "$(CFG)" == "PPPESPESADC - Win32 Debug"

# Begin Custom Build
InputDir=\Projeto\FrontOffice\EVOLUTIVA-V1\TUXEFO\prepago\negocio\ppbasico\src
InputPath=..\..\negocio\ppbasico\src\Documentopc.pcpp
InputName=Documentopc

"$(InputDir)/$(InputName).cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc  parse=none  cpp_suffix=cpp  code=cpp $(InputPath)  include=.\include include=..\..\negocio\ppbasico\include

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=..\..\negocio\ppbasico\src\LinhaContapc.pcpp

!IF  "$(CFG)" == "PPPESPESADC - Win32 Release"

!ELSEIF  "$(CFG)" == "PPPESPESADC - Win32 Debug"

# Begin Custom Build
InputDir=\Projeto\FrontOffice\EVOLUTIVA-V1\TUXEFO\prepago\negocio\ppbasico\src
InputPath=..\..\negocio\ppbasico\src\LinhaContapc.pcpp
InputName=LinhaContapc

"$(InputDir)/$(InputName).cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc  parse=none  cpp_suffix=cpp  code=cpp $(InputPath)  include=.\include include=..\..\negocio\ppbasico\include

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=..\..\negocio\ppbasico\src\PessoaComunicacaopc.pcpp

!IF  "$(CFG)" == "PPPESPESADC - Win32 Release"

!ELSEIF  "$(CFG)" == "PPPESPESADC - Win32 Debug"

# Begin Custom Build
InputDir=\Projeto\FrontOffice\EVOLUTIVA-V1\TUXEFO\prepago\negocio\ppbasico\src
InputPath=..\..\negocio\ppbasico\src\PessoaComunicacaopc.pcpp
InputName=PessoaComunicacaopc

"$(InputDir)/$(InputName).cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc  parse=none  cpp_suffix=cpp  code=cpp $(InputPath)  include=.\include include=..\..\negocio\ppbasico\include

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=..\..\negocio\ppbasico\src\PessoaEnderecopc.pcpp

!IF  "$(CFG)" == "PPPESPESADC - Win32 Release"

!ELSEIF  "$(CFG)" == "PPPESPESADC - Win32 Debug"

# Begin Custom Build
InputDir=\Projeto\FrontOffice\EVOLUTIVA-V1\TUXEFO\prepago\negocio\ppbasico\src
InputPath=..\..\negocio\ppbasico\src\PessoaEnderecopc.pcpp
InputName=PessoaEnderecopc

"$(InputDir)/$(InputName).cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc  parse=none  cpp_suffix=cpp  code=cpp $(InputPath)  include=.\include include=..\..\negocio\ppbasico\include

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=..\..\negocio\ppbasico\src\PessoaFisicapc.pcpp

!IF  "$(CFG)" == "PPPESPESADC - Win32 Release"

!ELSEIF  "$(CFG)" == "PPPESPESADC - Win32 Debug"

# Begin Custom Build
InputDir=\Projeto\FrontOffice\EVOLUTIVA-V1\TUXEFO\prepago\negocio\ppbasico\src
InputPath=..\..\negocio\ppbasico\src\PessoaFisicapc.pcpp
InputName=PessoaFisicapc

"$(InputDir)/$(InputName).cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc  parse=none  cpp_suffix=cpp  code=cpp $(InputPath)  include=.\include include=..\..\negocio\ppbasico\include

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=..\..\negocio\ppbasico\src\PessoaJuridicapc.pcpp

!IF  "$(CFG)" == "PPPESPESADC - Win32 Release"

!ELSEIF  "$(CFG)" == "PPPESPESADC - Win32 Debug"

# Begin Custom Build
InputDir=\Projeto\FrontOffice\EVOLUTIVA-V1\TUXEFO\prepago\negocio\ppbasico\src
InputPath=..\..\negocio\ppbasico\src\PessoaJuridicapc.pcpp
InputName=PessoaJuridicapc

"$(InputDir)/$(InputName).cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc  parse=none  cpp_suffix=cpp  code=cpp $(InputPath)  include=.\include include=..\..\negocio\ppbasico\include

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=..\..\negocio\ppbasico\src\Pessoapc.pcpp

!IF  "$(CFG)" == "PPPESPESADC - Win32 Release"

!ELSEIF  "$(CFG)" == "PPPESPESADC - Win32 Debug"

# Begin Custom Build
InputDir=\Projeto\FrontOffice\EVOLUTIVA-V1\TUXEFO\prepago\negocio\ppbasico\src
InputPath=..\..\negocio\ppbasico\src\Pessoapc.pcpp
InputName=Pessoapc

"$(InputDir)/$(InputName).cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc  parse=none  cpp_suffix=cpp  code=cpp $(InputPath)  include=.\include include=..\..\negocio\ppbasico\include

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\src\PsqPFDocDadosContatopc.pcpp

!IF  "$(CFG)" == "PPPESPESADC - Win32 Release"

!ELSEIF  "$(CFG)" == "PPPESPESADC - Win32 Debug"

# Begin Custom Build
InputDir=.\src
InputPath=.\src\PsqPFDocDadosContatopc.pcpp
InputName=PsqPFDocDadosContatopc

"$(InputDir)/$(InputName).cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc  parse=none  cpp_suffix=cpp  code=cpp $(InputPath)  include=.\include include=..\..\negocio\ppbasico\include

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\src\PsqPFDocDadosDocpc.pcpp

!IF  "$(CFG)" == "PPPESPESADC - Win32 Release"

!ELSEIF  "$(CFG)" == "PPPESPESADC - Win32 Debug"

# Begin Custom Build
InputDir=.\src
InputPath=.\src\PsqPFDocDadosDocpc.pcpp
InputName=PsqPFDocDadosDocpc

"$(InputDir)/$(InputName).cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc  parse=none  cpp_suffix=cpp  code=cpp $(InputPath)  include=.\include include=..\..\negocio\ppbasico\include

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\src\PsqPFDocEMailCompc.pcpp

!IF  "$(CFG)" == "PPPESPESADC - Win32 Release"

!ELSEIF  "$(CFG)" == "PPPESPESADC - Win32 Debug"

# Begin Custom Build
InputDir=.\src
InputPath=.\src\PsqPFDocEMailCompc.pcpp
InputName=PsqPFDocEMailCompc

"$(InputDir)/$(InputName).cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc  parse=none  cpp_suffix=cpp  code=cpp $(InputPath)  include=.\include include=..\..\negocio\ppbasico\include

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\src\PsqPFDocEMailPartpc.pcpp

!IF  "$(CFG)" == "PPPESPESADC - Win32 Release"

!ELSEIF  "$(CFG)" == "PPPESPESADC - Win32 Debug"

# Begin Custom Build
InputDir=.\src
InputPath=.\src\PsqPFDocEMailPartpc.pcpp
InputName=PsqPFDocEMailPartpc

"$(InputDir)/$(InputName).cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc  parse=none  cpp_suffix=cpp  code=cpp $(InputPath)  include=.\include include=..\..\negocio\ppbasico\include

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\src\PsqPFDocLinPospc.pcpp

!IF  "$(CFG)" == "PPPESPESADC - Win32 Release"

!ELSEIF  "$(CFG)" == "PPPESPESADC - Win32 Debug"

# Begin Custom Build
InputDir=.\src
InputPath=.\src\PsqPFDocLinPospc.pcpp
InputName=PsqPFDocLinPospc

"$(InputDir)/$(InputName).cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc  parse=none  cpp_suffix=cpp  code=cpp $(InputPath)  include=.\include include=..\..\negocio\ppbasico\include

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\src\PsqPFDocLinPrepc.pcpp

!IF  "$(CFG)" == "PPPESPESADC - Win32 Release"

!ELSEIF  "$(CFG)" == "PPPESPESADC - Win32 Debug"

# Begin Custom Build
InputDir=.\src
InputPath=.\src\PsqPFDocLinPrepc.pcpp
InputName=PsqPFDocLinPrepc

"$(InputDir)/$(InputName).cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc  parse=none  cpp_suffix=cpp  code=cpp $(InputPath)  include=.\include include=..\..\negocio\ppbasico\include

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\src\PsqPJDocLinPospc.pcpp

!IF  "$(CFG)" == "PPPESPESADC - Win32 Release"

!ELSEIF  "$(CFG)" == "PPPESPESADC - Win32 Debug"

# Begin Custom Build
InputDir=.\src
InputPath=.\src\PsqPJDocLinPospc.pcpp
InputName=PsqPJDocLinPospc

"$(InputDir)/$(InputName).cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc  parse=none  cpp_suffix=cpp  code=cpp $(InputPath)  include=.\include include=..\..\negocio\ppbasico\include

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\src\PsqPJDocLinPrepc.pcpp

!IF  "$(CFG)" == "PPPESPESADC - Win32 Release"

!ELSEIF  "$(CFG)" == "PPPESPESADC - Win32 Debug"

# Begin Custom Build
InputDir=.\src
InputPath=.\src\PsqPJDocLinPrepc.pcpp
InputName=PsqPJDocLinPrepc

"$(InputDir)/$(InputName).cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc  parse=none  cpp_suffix=cpp  code=cpp $(InputPath)  include=.\include include=..\..\negocio\ppbasico\include

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=..\..\negocio\ppbasico\src\TipoDocumentopc.pcpp

!IF  "$(CFG)" == "PPPESPESADC - Win32 Release"

!ELSEIF  "$(CFG)" == "PPPESPESADC - Win32 Debug"

# Begin Custom Build
InputDir=\Projeto\FrontOffice\EVOLUTIVA-V1\TUXEFO\prepago\negocio\ppbasico\src
InputPath=..\..\negocio\ppbasico\src\TipoDocumentopc.pcpp
InputName=TipoDocumentopc

"$(InputDir)/$(InputName).cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc  parse=none  cpp_suffix=cpp  code=cpp $(InputPath)  include=.\include include=..\..\negocio\ppbasico\include

# End Custom Build

!ENDIF 

# End Source File
# End Group
# End Target
# End Project
