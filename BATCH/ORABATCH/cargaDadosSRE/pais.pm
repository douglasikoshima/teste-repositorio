#!/apps/perl-5.005_03/bin/perl
#ident @(#) P2K: src/cacs/tools/loadDNE/pais.pm 70.2 09/12/14 14:49:46
# (c) 2007,2008,2009, Convergys Information Management Group Inc. All Rights Reserved. Private and Confidential. May not be disclosed without permission.

################################################################################
#   pais.pm  -  03/24/2004 by aldebaran perseke
#
#   Brazil's address pais class
################################################################################

package pais;

use     strict;

################################################################################
#   contants
################################################################################
use constant SUCCESS => 0;
use constant ERROR => 1;

################################################################################
#   construct a new pais object
################################################################################

sub new {
    my  $self = {};

    $self->{ COD_PAIS } = undef;
    $self->{ SGL_ISO_PAIS } = undef;
    $self->{ NUM_ISO_PAIS } = undef;
    $self->{ SGL_ISO2_PAIS } = undef;
    $self->{ NUM_PAIS } = undef;
    $self->{ NOM_PAIS } = undef;
    $self->{ DAT_ATUALIZACAO } = undef;
    $self->{ USUARIO } = undef;
    $self->{ VER_NBR } = undef;
    $self->{ DAT_CARGA } = undef;
    
    bless( $self );

    return $self;
}

################################################################################
#   set/get COD_PAIS attribute
################################################################################

sub codigo {
    my  $self = shift;

    if( @_ ) {
        $self->{ COD_PAIS } = shift;
    }

    $self->{ COD_PAIS };
}

################################################################################
#   set/get SGL_ISO_PAIS attribute
################################################################################

sub siglaISO {
    my  $self = shift;

    if( @_ ) {
        $self->{ SGL_ISO_PAIS } = substr( shift, 0, 3 );
    }

    $self->{ SGL_ISO_PAIS };
}

################################################################################
#   set/get NUM_ISO_PAIS attribute
################################################################################

sub numeroISO {
    my  $self = shift;

    if( @_ ) {
        $self->{ NUM_ISO_PAIS } = substr( shift, 0, 3 );
    }

    $self->{ NUM_ISO_PAIS };
}

################################################################################
#   set/get SGL_ISO2_PAIS attribute
################################################################################

sub siglaISO2 {
    my  $self = shift;

    if( @_ ) {
        $self->{ SGL_ISO2_PAIS } = substr( shift, 0, 2 );
    }

    $self->{ SGL_ISO2_PAIS };
}

################################################################################
#   set/get NUM_PAIS attribute
################################################################################

sub numero {
    my  $self = shift;

    if( @_ ) {
        $self->{ NUM_PAIS } = substr( shift, 0, 3 );
    }

    $self->{ NUM_PAIS };
}

################################################################################
#   set/get NOM_PAIS attribute
################################################################################

sub nome {
    my  $self = shift;

    if( @_ ) {
        $self->{ NOM_PAIS } = substr( shift, 0, 40 );
    }

    $self->{ NOM_PAIS };
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

    sprintf qq/codigo: %d\nsigla ISO: '%s'\nnumero ISO: %d\nsigla ISO2: '%s'\nnumero: %d\nnome: '%s'\ndata: %s\nusuario: %d/
            , ( defined $self->{ COD_PAIS } )?$self->{ COD_PAIS }:0
            , ( defined $self->{ SGL_ISO_PAIS } )?$self->{ SGL_ISO_PAIS }:''
            , ( defined $self->{ NUM_ISO_PAIS } )?$self->{ NUM_ISO_PAIS }:0
            , ( defined $self->{ SGL_ISO2_PAIS } )?$self->{ SGL_ISO2_PAIS }:''
            , ( defined $self->{ NUM_PAIS } )?$self->{ NUM_PAIS }:0
            , ( defined $self->{ NOM_PAIS } )?$self->{ NOM_PAIS }:''
            , ( defined $self->{ DAT_ATUALIZACAO } )?$self->{ DAT_ATUALIZACAO }:''
            , ( defined $self->{ USUARIO } )?$self->{ USUARIO }:0
            , ( defined $self->{ VER_NBR } )?$self->{ VER_NBR }:0
            , ( defined $self->{ DAT_CARGA } )?$self->{ DAT_CARGA }:0;
}

################################################################################
#   get an object from database by codigo
################################################################################

sub queryByCodigo {
    my  $connection = shift;
    my  $COD_PAIS = shift;

    my  $SQLQuery = <<SQL_QUERY;
select
    COD_PAIS
    , SGL_ISO_PAIS
    , NUM_ISO_PAIS
    , SGL_ISO2_PAIS
    , NUM_PAIS
    , NOM_PAIS
    , DAT_ATUALIZACAO
    , USUARIO
    , VER_NBR
    , DAT_CARGA
from
    PAIS
where
    COD_PAIS = ?
SQL_QUERY
    my  $recordSet;
    my  @resultRow;

    $recordSet = $connection->prepare( $SQLQuery );
    $recordSet->execute( $COD_PAIS )
            or return ERROR;

    if( @resultRow = $recordSet->fetchrow_array() ) {
        my  $self = {};

        $self->{ COD_PAIS } = $resultRow[ 0 ];
        $self->{ SGL_ISO_PAIS } = $resultRow[ 1 ];
        $self->{ NUM_ISO_PAIS } = $resultRow[ 2 ];
        $self->{ SGL_ISO2_PAIS } = $resultRow[ 3 ];
        $self->{ NUM_PAIS } = $resultRow[ 4 ];
        $self->{ NOM_PAIS } = $resultRow[ 5 ];
        $self->{ DAT_ATUALIZACAO } = $resultRow[ 6 ];
        $self->{ USUARIO } = $resultRow[ 7 ];
        $self->{ VER_NBR } = $resultRow[ 8 ];
        $self->{ DAT_CARGA } = $resultRow[ 9 ];

        $recordSet->finish();
        bless( $self );

        $self;
    }
}

################################################################################
#   delete an object from database
################################################################################
sub delete {
    
    my  $self = shift;
    my  $connection = shift;
    my  $returnCode = SUCCESS;

    if( defined $self->{ COD_PAIS } ) {

        my  $SQLQuery = <<SQL_QUERY;
delete from PAIS
where COD_PAIS = ?
SQL_QUERY
        my  $recordSet;

        $recordSet = $connection->prepare( $SQLQuery );
        $recordSet->execute( $self->{ COD_PAIS } )
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
#   get an object from database by nome
################################################################################

sub queryByNome {
    my  $connection = shift;
    my  $NOM_PAIS = shift;

#   the select query

    my  $SQLQuery = <<SQL_QUERY;
select
    COD_PAIS
    , SGL_ISO_PAIS
    , NUM_ISO_PAIS
    , SGL_ISO2_PAIS
    , NUM_PAIS
    , NOM_PAIS
    , DAT_ATUALIZACAO
    , USUARIO
    , VER_NBR
    , DAT_CARGA
from
    PAIS
where
    NOM_PAIS = ?
SQL_QUERY
    my  $recordSet;
    my  @resultRow;

    $recordSet = $connection->prepare( $SQLQuery );
    $recordSet->execute( $NOM_PAIS )
            or return ERROR;

    if( @resultRow = $recordSet->fetchrow_array() ) {
        my  $self = {};

        $self->{ COD_PAIS } = $resultRow[ 0 ];
        $self->{ SGL_ISO_PAIS } = $resultRow[ 1 ];
        $self->{ NUM_ISO_PAIS } = $resultRow[ 2 ];
        $self->{ SGL_ISO2_PAIS } = $resultRow[ 3 ];
        $self->{ NUM_PAIS } = $resultRow[ 4 ];
        $self->{ NOM_PAIS } = $resultRow[ 5 ];
        $self->{ DAT_ATUALIZACAO } = $resultRow[ 6 ];
        $self->{ USUARIO } = $resultRow[ 7 ];
        $self->{ VER_NBR } = $resultRow[ 8 ];
        $self->{ DAT_CARGA } = $resultRow[ 9 ];

        $recordSet->finish();
        bless( $self );

        $self;
    }
}

#   return 1 to module requiring or using it

1;

