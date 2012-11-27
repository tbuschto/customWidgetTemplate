package org.eclipse.rap.customWidget;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.rap.rwt.application.Application;
import org.eclipse.rap.rwt.application.ApplicationConfiguration;
import org.eclipse.rap.rwt.lifecycle.IEntryPoint;
import org.eclipse.rap.rwt.resources.ResourceLoader;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class Demo implements IEntryPoint, ApplicationConfiguration {

  public int createUI() {
    Display display = new Display();
    final Shell shell = new Shell( display );
    shell.setLayout( new GridLayout( 4, false ) );
    shell.setText( "Custom Widget Template" );

    final CustomWidget widget = new CustomWidget( shell, SWT.BORDER );
    GridData widgetLayout = new GridData( SWT.FILL, SWT.FILL, true, true );
    widgetLayout.horizontalSpan = 4;
    widget.setLayoutData( widgetLayout );

    final Text text = new Text( shell, SWT.BORDER );
    text.setMessage( "Change text" );
    text.addSelectionListener( new SelectionAdapter() {
      public void widgetDefaultSelected( SelectionEvent e ) {
        widget.setText( text.getText() );
        text.setText( "" );
      }
    } );

    final Button get = new Button( shell, SWT.PUSH );
    get.setText( "get" );
    get.addSelectionListener( new SelectionAdapter() {
      public void widgetSelected( SelectionEvent e ) {
        MessageBox box = new MessageBox( shell );
        box.setText( "Current text" );
        box.setMessage( widget.getText() );
        org.eclipse.rap.rwt.widgets.DialogUtil.open( box, null );
      }
    } );

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
