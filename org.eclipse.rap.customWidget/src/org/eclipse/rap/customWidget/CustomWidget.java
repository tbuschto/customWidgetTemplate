package org.eclipse.rap.customWidget;

import java.util.Map;

import org.eclipse.rap.rwt.internal.remote.RemoteObject;
import org.eclipse.rap.rwt.internal.remote.RemoteObjectFactory;
import org.eclipse.rap.rwt.internal.remote.RemoteOperationHandler;
import org.eclipse.rap.rwt.lifecycle.WidgetUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

@SuppressWarnings("restriction")
public class CustomWidget extends Composite {

  private RemoteObject ro;
  private String text = "Hello World!";
  private boolean hasModifyListener = false;
  private boolean hasMouseUpListener = false;

  public CustomWidget( Composite parent, int style ) {
    super( parent, style );
    ro = RemoteObjectFactory.getInstance().createRemoteObject( "rap.myWidget" );
    ro.set( "parent", WidgetUtil.getId( this ) );
    ro.set( "text", text );
    ro.setHandler( new RemoteOperationHandler(){
      @Override
      public void handleSet( Map<String, Object> properties ) {
        if( properties.containsKey( "text" ) ) {
          text = ( String )properties.get( "text" );
          CustomWidget.this.notifyListeners( SWT.Modify, new Event() );
        }
      }
      public void handleNotify( String event, java.util.Map< String, Object > properties ) {
        if( event.equals( "MouseUp" ) ) {
          CustomWidget.this.notifyListeners( SWT.MouseUp, new Event() );
        }
      };
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

  public void addListener( int eventType, Listener listener ) {
    super.addListener( eventType, listener );
    checkModifyListener();
    checkMouseListener();
  }

  public void removeListener( int eventType, Listener listener ) {
    super.removeListener( eventType, listener );
    checkModifyListener();
    checkMouseListener();
  }

  private void checkModifyListener() {
    if( !hasModifyListener && isListening( SWT.Modify ) ) {
      ro.listen( "Modify", true );
      hasModifyListener = true;
    }
    if( hasModifyListener && !isListening( SWT.Modify ) ) {
      ro.listen( "Modify", false );
      hasModifyListener = false;
    }
  }

  private void checkMouseListener() {
    if( !hasMouseUpListener && isListening( SWT.MouseUp ) ) {
      ro.listen( "MouseUp", true );
      hasMouseUpListener = true;
    }
    if( hasMouseUpListener && !isListening( SWT.MouseUp ) ) {
      ro.listen( "MouseUp", false );
      hasMouseUpListener = false;
    }
  }

  @Override
  public void dispose() {
    ro.destroy();
    super.dispose();
  }

}
