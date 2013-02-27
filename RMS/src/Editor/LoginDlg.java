package Editor;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.entity.LoginUser;
import com.wsclient.LoginServer;

public class LoginDlg extends Dialog {
	private Text user_name;
	private Text password;
	private boolean islogin;
	private LoginUser loginUser;

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
		user_name.setBounds(94, 28, 226, 24);
		user_name.setText("Ye.zhang@netgear.com");
		
		Label lblPassword = new Label(container, SWT.NONE);
		lblPassword.setBounds(9, 100, 79, 24);
		lblPassword.setText("Password:");
		
		password = new Text(container, SWT.BORDER | SWT.PASSWORD);
		password.setBounds(94, 97, 226, 24);
		password.setText("");

//		this.setTitleImage(ResourceManager.getPluginImage("RMS", "icons/alt_launcher.ico"));
		
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
			//登录
			LoginServer ls=new LoginServer();
			islogin=ls.login(user_name.getText(),password.getText());
			System.out.println(islogin);
			 
			if(!islogin){
				System.out.println(islogin);
				MessageBox messageBox = new MessageBox(getShell(), SWT.OK); 
				messageBox.setText("LOGIN PROMPT");
				messageBox.setMessage("Please input the correct username and password!"); 
				messageBox.open();
				
			}else{
				this.loginUser=new LoginUser();
				this.loginUser.setUsername(ls.getUername().trim());
				
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
		return new Point(396, 300);
	}
	
	
	@Override  
	protected void configureShell(Shell newShell) {  
	    // TODO Auto-generated method stub  
	    super.configureShell(newShell);  
	    newShell.setText("RMS LOGIN");
//	    System.out.println("-->"+this.getClass().getResource("/icons/logo.png").getPath());
	    newShell.setImage(new Image(Display.getCurrent(), this.getClass().getResourceAsStream("/icons/logo.png")));
//	    newShell.
	}
	@Override  
	protected void setShellStyle(int newShellStyle) {  
	    // TODO Auto-generated method stub  
	    // 取消关闭“X”按钮  
	    super.setShellStyle(newShellStyle ^ SWT.CLOSE);  
	}
	
	/**
	 * 获得用户信息
	 * @return
	 */
	public LoginUser getLoginUser() {
		return loginUser;
	}
	
	 
}
