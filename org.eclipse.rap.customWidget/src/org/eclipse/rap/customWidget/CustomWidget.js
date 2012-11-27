rwt.protocol.AdapterRegistry.add( "rap.myWidget", {

  factory : function( properties ) {
    console.log( "create" );
    return {  };
  },

  destructor : function() {
    console.log( "destroy" );
  }

} );

console.log( "registered" );