package Editor;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.wsclient.LoginServer;

public class LoginDlg extends Dialog {
	private Text user_name;
	private Text password;
	private boolean islogin;

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public LoginDlg(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(null);
		
		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setBounds(9, 31, 79, 24);
		lblNewLabel.setText("Username:");
		
		user_name = new Text(container, SWT.BORDER);
		user_name.setBounds(94, 28, 174, 24);
		
		Label lblPassword = new Label(container, SWT.NONE);
		lblPassword.setBounds(9, 100, 79, 24);
		lblPassword.setText("Password:");
		
		password = new Text(container, SWT.BORDER | SWT.PASSWORD);
		password.setBounds(94, 97, 174, 24);

		return container;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		Button button = createButton(parent, IDialogConstants.OK_ID, "Login",
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				"Cancel", false);
	}

	@Override
	protected void buttonPressed(int buttonId) {
		if(buttonId==Dialog.OK){
			//��¼
			LoginServer ls=new LoginServer();
			islogin=ls.login(user_name.getText(),password.getText());
			System.out.println(islogin);
			 
				if(!islogin){
				System.out.println(islogin);
				MessageBox messageBox = new MessageBox(getShell(), SWT.OK); 
				messageBox.setMessage("Please input the correct username and password!"); 
				messageBox.open();
				
			}else{
				MessageBox messageBox = new MessageBox(getShell(), SWT.OK); 
				messageBox.setMessage("Login success!"); 
				messageBox.open();
				super.buttonPressed(buttonId);
			}
		}else if(buttonId==Dialog.CANCEL){
			super.buttonPressed(buttonId);
		}
		
	};
	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(302, 324);
	}
	
	@Override  
	protected void configureShell(Shell newShell) {  
	    // TODO Auto-generated method stub  
	    super.configureShell(newShell);  
	    newShell.setText("RMS LOGIN");
	}
	@Override  
	protected void setShellStyle(int newShellStyle) {  
	    // TODO Auto-generated method stub  
	    // ȡ���رա�X����ť  
	    super.setShellStyle(newShellStyle ^ SWT.CLOSE);  
	}
}
