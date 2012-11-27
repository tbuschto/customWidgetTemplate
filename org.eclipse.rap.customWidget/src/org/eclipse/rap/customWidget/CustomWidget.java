package org.eclipse.rap.customWidget;

import java.util.Map;

import org.eclipse.rap.rwt.internal.remote.RemoteObject;
import org.eclipse.rap.rwt.internal.remote.RemoteObjectFactory;
import org.eclipse.rap.rwt.internal.remote.RemoteOperationHandler;
import org.eclipse.rap.rwt.lifecycle.WidgetUtil;
import org.eclipse.swt.widgets.Composite;

@SuppressWarnings("restriction")
public class CustomWidget extends Composite {

  private RemoteObject ro;
  private String text = "Hello World!";

  public CustomWidget( Composite parent, int style ) {
    super( parent, style );
    ro = RemoteObjectFactory.createRemoteObject( "rap.myWidget" );
    ro.set( "parent", WidgetUtil.getId( this ) );
    ro.set( "text", text );
    ro.setHandler( new RemoteOperationHandler(){
      @Override
      public void handleSet( Map<String, Object> properties ) {
        if( properties.containsKey( "text" ) ) {
          text = ( String )properties.get( "text" );
        }
      }
    } );
  }

  public String getText() {
    return text;
  }

  public void setText( String text ) {
    assert( text != null );
    if( !this.text.equals( text ) ) {
      ro.set( "text", text );
    }
    this.text = text;
  }

  @Override
  public void dispose() {
    ro.destroy();
    super.dispose();
  }

}
