rap.registerTypeHandler( "rap.myWidget", {

  factory : function( properties ) {
    console.log( "create" );
    return {  };
  },

  destructor : function() {
    console.log( "destroy" );
  }

} );