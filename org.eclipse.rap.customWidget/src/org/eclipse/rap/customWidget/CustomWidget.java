package org.eclipse.rap.customWidget;

import org.eclipse.rap.rwt.internal.remote.RemoteObject;
import org.eclipse.rap.rwt.internal.remote.RemoteObjectFactory;
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
