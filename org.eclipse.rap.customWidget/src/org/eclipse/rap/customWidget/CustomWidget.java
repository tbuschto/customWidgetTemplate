package org.eclipse.rap.customWidget;

import org.eclipse.rap.rwt.internal.remote.RemoteObject;
import org.eclipse.rap.rwt.internal.remote.RemoteObjectFactory;
import org.eclipse.rap.rwt.lifecycle.WidgetUtil;
import org.eclipse.swt.widgets.Composite;

@SuppressWarnings("restriction")
public class CustomWidget extends Composite {

  RemoteObject ro;

  public CustomWidget( Composite parent, int style ) {
    super( parent, style );
    ro = RemoteObjectFactory.createRemoteObject( "rap.myWidget" );
    ro.set( "parent", WidgetUtil.getId( this ) );
  }

  @Override
  public void dispose() {
    ro.destroy();
    super.dispose();
  }

}
