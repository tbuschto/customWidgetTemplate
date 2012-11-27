rap.registerTypeHandler( "rap.myWidget", {

  factory : function( properties ) {
    var el = document.createElement( "div" );
    el.style.position = "absolute";
    el.style.left = "0xp";
    el.style.top = "0xp";
    el.style.width = "100%";
    el.style.height = "100%";
    el.style.backgroundColor = "#FF7766";
    el.innerText = "Hello World!"
    rap.getObject( properties.parent ).append( el );
    return el;
  },

  destructor : function( el ) {
    if( el.parentNode ) {
      el.parentNode.removeChild( el );
    }
  }

} );