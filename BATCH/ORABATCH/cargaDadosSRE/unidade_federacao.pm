#!/apps/perl-5.005_03/bin/perl
#ident @(#) P2K: src/cacs/tools/loadDNE/unidade_federacao.pm 70.2 09/12/14 14:48:58
# (c) 2007,2008,2009, Convergys Information Management Group Inc. All Rights Reserved. Private and Confidential. May not be disclosed without permission.

################################################################################
#   unidade_federacao.pm  -  03/24/2004 by aldebaran perseke
#
#   Brazil's address unidade_federacao class
################################################################################

package unidade_federacao;

use     strict;

use     pais;

################################################################################
#   contants
################################################################################
use constant SUCCESS => 0;
use constant ERROR => 1;

################################################################################
#   construct a new unidade_federacao object
################################################################################

sub new {
    my  $self = {};

    $self->{ COD_UF } = undef;
    $self->{ SGL_UF } = undef;
    $self->{ NOM_UF } = undef;
    $self->{ HOR_FUSO_HORARIO } = undef;
    $self->{ COD_PAIS } = undef;
    $self->{ DAT_ATUALIZACAO } = undef;
    $self->{ USUARIO } = undef;
    $self->{ VER_NBR } = undef;
    $self->{ DAT_CARGA } = undef;

    bless( $self );

    return $self;
}

################################################################################
#   set/get COD_UF attribute
################################################################################

sub codigo {
    my  $self = shift;

    if( @_ ) {
        $self->{ COD_UF } = shift;
    }

    $self->{ COD_UF };
}

################################################################################
#   set/get SGL_UF attribute
################################################################################

sub sigla {
    my  $self = shift;

    if( @_ ) {
        $self->{ SGL_UF } = substr( shift, 0, 2 );
    }

    $self->{ SGL_UF };
}

################################################################################
#   set/get NOM_UF attribute
################################################################################

sub nome {
    my  $self = shift;

    if( @_ ) {
        $self->{ NOM_UF } = substr( shift, 0, 40 );
    }

    $self->{ NOM_UF };
}

################################################################################
#   set/get HOR_FUSO_HORARIO attribute
################################################################################

sub fusoHorario {
    my  $self = shift;

    if( @_ ) {
        $self->{ HOR_FUSO_HORARIO } = shift;
    }

    $self->{ HOR_FUSO_HORARIO };
}

################################################################################
#   set/get COD_PAIS attribute
################################################################################

sub codigoPais {
    my  $self = shift;

    if( @_ ) {
        $self->{ COD_PAIS } = shift;
    }

    $self->{ COD_PAIS };
}

################################################################################
#   set/get DAT_ATUALIZACAO attribute
################################################################################

sub dataAtualizacao {
    my  $self = shift;

    if( @_ ) {
        $self->{ DAT_ATUALIZACAO } = shift;
    }

    $self->{ DAT_ATUALIZACAO };
}

################################################################################
#   set/get DAT_CARGA attribute
################################################################################

sub dataCarga {
    my  $self = shift;

    if( @_ ) {
        $self->{ DAT_CARGA } = shift;
    }

    $self->{ DAT_CARGA };
}

################################################################################
#   set/get USUARIO attribute
################################################################################

sub usuarioAtualizacao {
    my  $self = shift;

    if( @_ ) {
        $self->{ USUARIO } = shift;
    }

    $self->{ USUARIO };
}

################################################################################
#   set/get VER_NBR attribute
################################################################################

sub verNbr {
    my  $self = shift;

    if( @_ ) {
        $self->{ VER_NBR } = shift;
    }

    $self->{ VER_NBR };
}


################################################################################
#   transform an object in a printable string
################################################################################

sub to_string {
    my  $self = shift;

    sprintf qq/codigo: %d\nsigla: '%s'\nnome: '%s'\nfuso: %d\npais: %d\ndata: %s\nusuario: %d/
            , ( defined $self->{ COD_UF } )?$self->{ COD_UF }:0
            , ( defined $self->{ SGL_UF } )?$self->{ SGL_UF }:''
            , ( defined $self->{ NOM_UF } )?$self->{ NOM_UF }:''
            , ( defined $self->{ HOR_FUSO_HORARIO } )?$self->{ HOR_FUSO_HORARIO }:0
            , ( defined $self->{ COD_PAIS } )?$self->{ COD_PAIS }:0
            , ( defined $self->{ DAT_ATUALIZACAO } )?$self->{ DAT_ATUALIZACAO }:0
            , ( defined $self->{ USUARIO } )?$self->{ USUARIO }:0
            , ( defined $self->{ VER_NBR } )?$self->{ VER_NBR }:0
            , ( defined $self->{ DAT_CARGA } )?$self->{ DAT_CARGA }:0;
}

################################################################################
#   delete an object from database
################################################################################
sub delete {
    
    my  $self = shift;
    my  $connection = shift;
    my  $returnCode = SUCCESS;

    if( defined $self->{ COD_UF } ) {

        my  $SQLQuery = <<SQL_QUERY;
delete from UNIDADE_FEDERACAO
where COD_UF = ?
SQL_QUERY
        my  $recordSet;

        $recordSet = $connection->prepare( $SQLQuery );
        $recordSet->execute( $self->{ COD_UF } )
                or return ERROR;

        if ($recordSet->rows == 0) {
            $returnCode = ERROR;
        }
    } else {
        $returnCode = ERROR;
    }
    
    $returnCode;
}

################################################################################
#   get an object from database by codigo
################################################################################

sub queryByCodigo {
    my  $connection = shift;
    my  $COD_UF = shift;

    my  $SQLQuery = <<SQL_QUERY;
select
    COD_UF
    , SGL_UF
    , NOM_UF
    , HOR_FUSO_HORARIO
    , COD_PAIS
    , DAT_ATUALIZACAO
    , USUARIO
    , VER_NBR
    , DAT_CARGA
from
    UNIDADE_FEDERACAO
where
    COD_UF = ?
SQL_QUERY
    my  $recordSet;
    my  @resultRow;

    $recordSet = $connection->prepare( $SQLQuery );
    $recordSet->execute( $COD_UF )
            or return ERROR;

    if( @resultRow = $recordSet->fetchrow_array() ) {
        my  $self = {};

        $self->{ COD_UF } = $resultRow[ 0 ];
        $self->{ SGL_UF } = $resultRow[ 1 ];
        $self->{ NOM_UF } = $resultRow[ 2 ];
        $self->{ HOR_FUSO_HORARIO } = $resultRow[ 3 ];
        $self->{ COD_PAIS } = $resultRow[ 4 ];
        $self->{ DAT_ATUALIZACAO } = $resultRow[ 5 ];
        $self->{ USUARIO } = $resultRow[ 6 ];
        $self->{ VER_NBR } = $resultRow[ 7 ];
        $self->{ DAT_CARGA } = $resultRow[ 8 ];

        $recordSet->finish();
        bless( $self );

        $self;
    }
}

################################################################################
#   get a hash of objects from database by pais
################################################################################

sub queryByPais {
    my  $connection = shift;
    my  $pais = shift;

    my  $SQLQuery = <<SQL_QUERY;
select
    COD_UF
    , SGL_UF
    , NOM_UF
    , HOR_FUSO_HORARIO
    , COD_PAIS
    , DAT_ATUALIZACAO
    , USUARIO
    , VER_NBR
    , DAT_CARGA
from
    UNIDADE_FEDERACAO
where
    COD_PAIS = ?
SQL_QUERY
    my  $recordSet;
    my  @resultRow;
    my  %container;

    $recordSet = $connection->prepare( $SQLQuery );
    $recordSet->execute( $pais->codigo() )
            or return ERROR;

    while( @resultRow = $recordSet->fetchrow_array() ) {
        my  $new = {};

        $new->{ COD_UF } = $resultRow[ 0 ];
        $new->{ SGL_UF } = $resultRow[ 1 ];
        $new->{ NOM_UF } = $resultRow[ 2 ];
        $new->{ HOR_FUSO_HORARIO } = $resultRow[ 3 ];
        $new->{ COD_PAIS } = $resultRow[ 4 ];
        $new->{ DAT_ATUALIZACAO } = $resultRow[ 5 ];
        $new->{ USUARIO } = $resultRow[ 6 ];
        $new->{ VER_NBR } = $resultRow[ 7 ];
        $new->{ DAT_CARGA } = $resultRow[ 8 ];

        bless( $new );

        $container{ $new->{ SGL_UF } } = $new;
    }

    $recordSet->finish();

    %container;
}

#   return 1 to module requiring or using it

1;

