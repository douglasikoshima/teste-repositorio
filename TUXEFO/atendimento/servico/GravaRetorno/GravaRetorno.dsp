# Microsoft Developer Studio Project File - Name="GravaRetorno" - Package Owner=<4>
# Microsoft Developer Studio Generated Build File, Format Version 6.00
# ** DO NOT EDIT **

# TARGTYPE "Win32 (x86) Console Application" 0x0103

CFG=GravaRetorno - Win32 Debug
!MESSAGE This is not a valid makefile. To build this project using NMAKE,
!MESSAGE use the Export Makefile command and run
!MESSAGE 
!MESSAGE NMAKE /f "GravaRetorno.mak".
!MESSAGE 
!MESSAGE You can specify a configuration when running NMAKE
!MESSAGE by defining the macro CFG on the command line. For example:
!MESSAGE 
!MESSAGE NMAKE /f "GravaRetorno.mak" CFG="GravaRetorno - Win32 Debug"
!MESSAGE 
!MESSAGE Possible choices for configuration are:
!MESSAGE 
!MESSAGE "GravaRetorno - Win32 Release" (based on "Win32 (x86) Console Application")
!MESSAGE "GravaRetorno - Win32 Debug" (based on "Win32 (x86) Console Application")
!MESSAGE 

# Begin Project
# PROP AllowPerConfigDependencies 0
# PROP Scc_ProjName ""
# PROP Scc_LocalPath ""
CPP=cl.exe
RSC=rc.exe

!IF  "$(CFG)" == "GravaRetorno - Win32 Release"

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
# ADD BASE LINK32 kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib /nologo /subsystem:console /machine:I386
# ADD LINK32 kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib /nologo /subsystem:console /machine:I386

!ELSEIF  "$(CFG)" == "GravaRetorno - Win32 Debug"

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
# ADD CPP /nologo /W3 /Gm /GX /ZI /Od /D "WIN32" /D "_DEBUG" /D "_CONSOLE" /D "_MBCS" /YX /FD /GZ /c
# ADD BASE RSC /l 0x416 /d "_DEBUG"
# ADD RSC /l 0x416 /d "_DEBUG"
BSC32=bscmake.exe
# ADD BASE BSC32 /nologo
# ADD BSC32 /nologo
LINK32=link.exe
# ADD BASE LINK32 kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib /nologo /subsystem:console /debug /machine:I386 /pdbtype:sept
# ADD LINK32 kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib /nologo /subsystem:console /debug /machine:I386 /pdbtype:sept

!ENDIF 

# Begin Target

# Name "GravaRetorno - Win32 Release"
# Name "GravaRetorno - Win32 Debug"
# Begin Group "src"

# PROP Default_Filter ""
# Begin Source File

SOURCE=src\GrvCanalEntrada.cpp
# End Source File
# Begin Source File

SOURCE=src\GrvGrupo.cpp
# End Source File
# Begin Source File

SOURCE=src\GrvPessoa.cpp
# End Source File
# Begin Source File

SOURCE=src\GrvProcedencia.cpp
# End Source File
# Begin Source File

SOURCE=src\GrvRelacionamento.cpp
# End Source File
# Begin Source File

SOURCE=src\GrvSegmentacao.cpp
# End Source File
# Begin Source File

SOURCE=src\GrvTipoCarteira.cpp
# End Source File
# Begin Source File

SOURCE=src\GrvTipoContatoRetorno.cpp
# End Source File
# Begin Source File

SOURCE=src\GrvTipoLinha.cpp
# End Source File
# Begin Source File

SOURCE=.\src\GrvTipoRetornoUfOperadora.cpp
# End Source File
# Begin Source File

SOURCE=src\RmvCanalEntrada.cpp
# End Source File
# Begin Source File

SOURCE=src\RmvContatoTipoRetorno.cpp
# End Source File
# Begin Source File

SOURCE=src\RmvGrupo.cpp
# End Source File
# Begin Source File

SOURCE=src\RmvPessoa.cpp
# End Source File
# Begin Source File

SOURCE=src\RmvProcedencia.cpp
# End Source File
# Begin Source File

SOURCE=src\RmvRelacionamento.cpp
# End Source File
# Begin Source File

SOURCE=src\RmvSegmentacao.cpp
# End Source File
# Begin Source File

SOURCE=src\RmvTipoCarteira.cpp
# End Source File
# Begin Source File

SOURCE=src\RmvTipoLinha.cpp
# End Source File
# Begin Source File

SOURCE=.\src\RmvTipoRetornoUfOperadora.cpp
# End Source File
# Begin Source File

SOURCE=.\src\svcmain.cpp
# End Source File
# End Group
# Begin Group "include"

# PROP Default_Filter ""
# Begin Source File

SOURCE=..\include\GravaRetorno.h
# End Source File
# End Group
# Begin Group "Pro*c"

# PROP Default_Filter ""
# Begin Source File

SOURCE=src\GrvCanalEntrada.pcpp

!IF  "$(CFG)" == "GravaRetorno - Win32 Release"

!ELSEIF  "$(CFG)" == "GravaRetorno - Win32 Debug"

# Begin Custom Build
InputPath=src\GrvCanalEntrada.pcpp

"GrvCanalEntrada.cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc       parse=none       cpp_suffix=cpp       code=cpp       $(InputPath)       include=D:\Projeto\FrontOffice\TUXEFO\workflow\servico\gravaretorno\include\ 

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=src\GrvGrupo.pcpp

!IF  "$(CFG)" == "GravaRetorno - Win32 Release"

!ELSEIF  "$(CFG)" == "GravaRetorno - Win32 Debug"

# Begin Custom Build
InputPath=src\GrvGrupo.pcpp

"GrvGrupo.cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc       parse=none       cpp_suffix=cpp       code=cpp       $(InputPath)       include=D:\Projeto\FrontOffice\TUXEFO\workflow\servico\gravaretorno\include\ 

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=src\GrvPessoa.pcpp

!IF  "$(CFG)" == "GravaRetorno - Win32 Release"

!ELSEIF  "$(CFG)" == "GravaRetorno - Win32 Debug"

# Begin Custom Build
InputPath=src\GrvPessoa.pcpp

"GrvPessoa.cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc       parse=none       cpp_suffix=cpp       code=cpp       $(InputPath)       include=D:\Projeto\FrontOffice\TUXEFO\workflow\servico\gravaretorno\include\ 

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=src\GrvProcedencia.pcpp

!IF  "$(CFG)" == "GravaRetorno - Win32 Release"

!ELSEIF  "$(CFG)" == "GravaRetorno - Win32 Debug"

# Begin Custom Build
InputPath=src\GrvProcedencia.pcpp

"GrvProcedencia.cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc       parse=none       cpp_suffix=cpp       code=cpp       $(InputPath)       include=D:\Projeto\FrontOffice\TUXEFO\workflow\servico\gravaretorno\include\ 

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=src\GrvRelacionamento.pcpp

!IF  "$(CFG)" == "GravaRetorno - Win32 Release"

!ELSEIF  "$(CFG)" == "GravaRetorno - Win32 Debug"

# Begin Custom Build
InputPath=src\GrvRelacionamento.pcpp

"GrvRelacionamento.cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc       parse=none       cpp_suffix=cpp       code=cpp       $(InputPath)       include=D:\Projeto\FrontOffice\TUXEFO\workflow\servico\gravaretorno\include\ 

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=src\GrvSegmentacao.pcpp

!IF  "$(CFG)" == "GravaRetorno - Win32 Release"

!ELSEIF  "$(CFG)" == "GravaRetorno - Win32 Debug"

# Begin Custom Build
InputPath=src\GrvSegmentacao.pcpp

"GrvSegmentacao.cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc       parse=none       cpp_suffix=cpp       code=cpp       $(InputPath)       include=D:\Projeto\FrontOffice\TUXEFO\workflow\servico\gravaretorno\include\ 

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=src\GrvTipoCarteira.pcpp

!IF  "$(CFG)" == "GravaRetorno - Win32 Release"

!ELSEIF  "$(CFG)" == "GravaRetorno - Win32 Debug"

# Begin Custom Build
InputPath=src\GrvTipoCarteira.pcpp

"GrvTipoCarteira.cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc       parse=none       cpp_suffix=cpp       code=cpp       $(InputPath)       include=D:\Projeto\FrontOffice\TUXEFO\workflow\servico\gravaretorno\include\ 

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=src\GrvTipoContatoRetorno.pcpp

!IF  "$(CFG)" == "GravaRetorno - Win32 Release"

!ELSEIF  "$(CFG)" == "GravaRetorno - Win32 Debug"

# Begin Custom Build
InputPath=src\GrvTipoContatoRetorno.pcpp

"GrvTipoContatoRetorno.cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc       parse=none       cpp_suffix=cpp       code=cpp       $(InputPath)       include=D:\Projeto\FrontOffice\TUXEFO\workflow\servico\gravaretorno\include\ 

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=src\GrvTipoLinha.pcpp

!IF  "$(CFG)" == "GravaRetorno - Win32 Release"

!ELSEIF  "$(CFG)" == "GravaRetorno - Win32 Debug"

# Begin Custom Build
InputPath=src\GrvTipoLinha.pcpp

"GrvTipoLinha.cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc       parse=none       cpp_suffix=cpp       code=cpp       $(InputPath)       include=D:\Projeto\FrontOffice\TUXEFO\workflow\servico\gravaretorno\include\ 

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\src\GrvTipoRetornoUfOperadora.pcpp

!IF  "$(CFG)" == "GravaRetorno - Win32 Release"

!ELSEIF  "$(CFG)" == "GravaRetorno - Win32 Debug"

# Begin Custom Build
InputPath=.\src\GrvTipoRetornoUfOperadora.pcpp

"GrvTipoRetornoUfOperadora.pcpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc       parse=none       cpp_suffix=cpp       code=cpp       $(InputPath)       include=D:\Projeto\FrontOffice\TUXEFO\workflow\servico\gravaretorno\include\ 

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=src\RmvCanalEntrada.pcpp

!IF  "$(CFG)" == "GravaRetorno - Win32 Release"

!ELSEIF  "$(CFG)" == "GravaRetorno - Win32 Debug"

# Begin Custom Build
InputPath=src\RmvCanalEntrada.pcpp

"RmvCanalEntrada.cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc       parse=none       cpp_suffix=cpp       code=cpp       $(InputPath)       include=D:\Projeto\FrontOffice\TUXEFO\workflow\servico\gravaretorno\include\ 

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=src\RmvContatoTipoRetorno.pcpp

!IF  "$(CFG)" == "GravaRetorno - Win32 Release"

!ELSEIF  "$(CFG)" == "GravaRetorno - Win32 Debug"

# Begin Custom Build
InputPath=src\RmvContatoTipoRetorno.pcpp

"RmvContatoTipoRetorno.cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc       parse=none       cpp_suffix=cpp       code=cpp       $(InputPath)       include=D:\Projeto\FrontOffice\TUXEFO\workflow\servico\gravaretorno\include\ 

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=src\RmvGrupo.pcpp

!IF  "$(CFG)" == "GravaRetorno - Win32 Release"

!ELSEIF  "$(CFG)" == "GravaRetorno - Win32 Debug"

# Begin Custom Build
InputPath=src\RmvGrupo.pcpp

"RmvGrupo.cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc       parse=none       cpp_suffix=cpp       code=cpp       $(InputPath)       include=D:\Projeto\FrontOffice\TUXEFO\workflow\servico\gravaretorno\include\ 

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=src\RmvPessoa.pcpp

!IF  "$(CFG)" == "GravaRetorno - Win32 Release"

!ELSEIF  "$(CFG)" == "GravaRetorno - Win32 Debug"

# Begin Custom Build
InputPath=src\RmvPessoa.pcpp

"RmvPessoa.cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc       parse=none       cpp_suffix=cpp       code=cpp       $(InputPath)       include=D:\Projeto\FrontOffice\TUXEFO\workflow\servico\gravaretorno\include\ 

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=src\RmvProcedencia.pcpp

!IF  "$(CFG)" == "GravaRetorno - Win32 Release"

!ELSEIF  "$(CFG)" == "GravaRetorno - Win32 Debug"

# Begin Custom Build
InputPath=src\RmvProcedencia.pcpp

"RmvProcedencia.cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc       parse=none       cpp_suffix=cpp       code=cpp       $(InputPath)       include=D:\Projeto\FrontOffice\TUXEFO\workflow\servico\gravaretorno\include\ 

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=src\RmvRelacionamento.pcpp

!IF  "$(CFG)" == "GravaRetorno - Win32 Release"

!ELSEIF  "$(CFG)" == "GravaRetorno - Win32 Debug"

# Begin Custom Build
InputPath=src\RmvRelacionamento.pcpp

"RmvRelacionamento.cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc       parse=none       cpp_suffix=cpp       code=cpp       $(InputPath)       include=D:\Projeto\FrontOffice\TUXEFO\workflow\servico\gravaretorno\include\ 

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=src\RmvSegmentacao.pcpp

!IF  "$(CFG)" == "GravaRetorno - Win32 Release"

!ELSEIF  "$(CFG)" == "GravaRetorno - Win32 Debug"

# Begin Custom Build
InputPath=src\RmvSegmentacao.pcpp

"RmvSegmentacao.cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc       parse=none       cpp_suffix=cpp       code=cpp       $(InputPath)       include=D:\Projeto\FrontOffice\TUXEFO\workflow\servico\gravaretorno\include\ 

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=src\RmvTipoCarteira.pcpp

!IF  "$(CFG)" == "GravaRetorno - Win32 Release"

!ELSEIF  "$(CFG)" == "GravaRetorno - Win32 Debug"

# Begin Custom Build
InputPath=src\RmvTipoCarteira.pcpp

"RmvTipoCarteira.cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc       parse=none       cpp_suffix=cpp       code=cpp       $(InputPath)       include=D:\Projeto\FrontOffice\TUXEFO\workflow\servico\gravaretorno\include\ 

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=src\RmvTipoLinha.pcpp

!IF  "$(CFG)" == "GravaRetorno - Win32 Release"

!ELSEIF  "$(CFG)" == "GravaRetorno - Win32 Debug"

# Begin Custom Build
InputPath=src\RmvTipoLinha.pcpp

"RmvTipoLinha.cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc       parse=none       cpp_suffix=cpp       code=cpp       $(InputPath)       include=D:\Projeto\FrontOffice\TUXEFO\workflow\servico\gravaretorno\include\ 

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\src\RmvTipoRetornoUfOperadora.pcpp

!IF  "$(CFG)" == "GravaRetorno - Win32 Release"

!ELSEIF  "$(CFG)" == "GravaRetorno - Win32 Debug"

# Begin Custom Build
InputPath=.\src\RmvTipoRetornoUfOperadora.pcpp

"RmvTipoRetornoUfOperadora.cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc       parse=none       cpp_suffix=cpp       code=cpp       $(InputPath)       include=D:\Projeto\FrontOffice\TUXEFO\workflow\servico\gravaretorno\include\ 

# End Custom Build

!ENDIF 

# End Source File
# End Group
# End Target
# End Project
