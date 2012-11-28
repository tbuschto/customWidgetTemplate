(function(){ // Everyting is in the same file, no need to put this in the global namespace

var MyWidget = function() {
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
  // block RWT from mouse events fired on "text" element
  text.onmouseup = function( ev ) {
    ev = ev ? ev : window.event; // IE 7
    if( ev.stopPropagation ) {
      ev.stopPropagation()
    } else {
      ev.cancelBubble = true; // IE7
    };
  }
  var that = this;
  text.onmousedown = function( ev ) {
    ev = ev ? ev : window.event;
    if( ev.stopPropagation ) {
      ev.stopPropagation()
    } else {
      ev.cancelBubble = true;
    };
    var newText = text.innerText + ", " + ev.button;
    text.innerText = newText;
    rap.getRemoteObject( that ).set( "text", newText );
    if( that._hasModifyListener ) {
      rap.getRemoteObject( that ).notify( "Modify" );
    }
  };
  el.appendChild( text );
  this._el = el;
  this._text = text;
  this._hasModifyListener = false;
};

MyWidget.prototype = {

  getElement : function() {
    return this._el;
  },

  setText : function( value ) {
    this._text.innerText = value;
  },

  setHasModifyListener : function( value ) {
    this._hasModifyListener = value;
  }

};

rap.registerTypeHandler( "rap.myWidget", {

  factory : function( properties ) {
    var result = new MyWidget();
    rap.getObject( properties.parent ).append( result.getElement() );
    return result;
  },

  destructor : function( widget ) {
    var el = widget.getElement();
    if( el.parentNode ) {
      el.parentNode.removeChild( el );
    }
  },

  properties : [
    "text"
  ],

  listeners : [
    "Modify"
  ]

} );

}());