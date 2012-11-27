rap.registerTypeHandler( "rap.myWidget", {

  factory : function( properties ) {
    var el = document.createElement( "div" );
    el.style.position = "absolute";
    el.style.left = "0xp";
    el.style.top = "0xp";
    el.style.width = "100%";
    el.style.height = "100%";
    el.style.backgroundColor = "#FF5555";
    var text = document.createElement( "div" );
    text.style.position = "absolute";
    text.style.left = "40px";
    text.style.top = "40px";
    text.style.backgroundColor = "#FFFF00";
    text.onmousedown = function( ev ) {
      var newText = text.innerText + ", " + ev.button;
      text.innerText = newText;
    };
    el.appendChild( text );
    rap.getObject( properties.parent ).append( el );
    return el;
  },

  destructor : function( el ) {
    if( el.parentNode ) {
      el.parentNode.removeChild( el );
    }
  },

  properties : [
    "text"
  ],

  propertyHandler : {
    "text" : function( el, text ) {
      el.firstChild.innerText = text;
    }
  }

} );