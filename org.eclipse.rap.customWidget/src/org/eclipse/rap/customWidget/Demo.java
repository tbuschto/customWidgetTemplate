package org.eclipse.rap.customWidget;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.rap.rwt.application.Application;
import org.eclipse.rap.rwt.application.ApplicationConfiguration;
import org.eclipse.rap.rwt.lifecycle.IEntryPoint;
import org.eclipse.rap.rwt.resources.ResourceLoader;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;


public class Demo implements IEntryPoint, ApplicationConfiguration {

  public int createUI() {
    Display display = new Display();
    Shell shell = new Shell( display );
    shell.setLayout( new GridLayout( 1, false ) );
    shell.setText( "Custom Widget Template" );

    CustomWidget widget = new CustomWidget( shell, SWT.BORDER );
    widget.setLayoutData( new GridData( SWT.FILL, SWT.FILL, true, true ) );

    shell.setBounds( 50, 50, 300, 300 );
    shell.open();
    return 0;
  }

  public void configure( Application application ) {
    application.addEntryPoint( "/custom", getClass(), null );
    ResourceLoader resourceLoader = new ResourceLoader() {
      public InputStream getResourceAsStream( String resourceName ) throws IOException {
        return getClass().getClassLoader().getResourceAsStream( resourceName );
      }
    };
    application.addResource( "org/eclipse/rap/customWidget/CustomWidget.js", resourceLoader );
  }

}
