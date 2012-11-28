rap.registerTypeHandler( "rap.myWidget", {

  factory : function( properties ) {
    console.log( 1 );
    var el = document.createElement( "div" );
    el.style.position = "absolute";
    el.style.left = "0px";
    el.style.top = "0px";
    el.style.width = "100%";
    el.style.height = "100%";
    var text = document.createElement( "div" );
    text.style.position = "absolute";
    text.style.left = "40px";
    text.style.top = "40px";
    text.style.backgroundColor = "#FFFF00";
    text.onmouseup = function( ev ) {
      ev = ev ? ev : window.event; // IE 7
      if( ev.stopPropagation ) {
        ev.stopPropagation()
      } else {
        ev.cancelBubble = true;
      };
    }
    text.onmousedown = function( ev ) {
      ev = ev ? ev : window.event;
      if( ev.stopPropagation ) {
        ev.stopPropagation()
      } else {
        ev.cancelBubble = true;
      };
      var newText = text.innerText + ", " + ev.button;
      text.innerText = newText;
      rap.getRemoteObject( el ).set( "text", newText );
      if( el._hasModifyListener ) {
        rap.getRemoteObject( el ).notify( "Modify", { "a" : "b" } );
      }
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
  },

  listeners : [
    "Modify",
    "MouseUp"
  ],

  listenerHandler : {
    "Modify" : function( el, value ) {
      el._hasModifyListener = value;
    },
    "MouseUp" : function( el, value ) {
      if( value ) {
        el.onmouseup = function() {
          rap.getRemoteObject( el ).notify( "MouseUp", { "a" : "b" } );
        };
      } else {
        el.onmousedown = null;
      }
    }
  }

} );