package Editor;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class LoginDlg extends Dialog {
	private Text user_name;
	private Text password;

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
		lblNewLabel.setBounds(9, 28, 70, 24);
		lblNewLabel.setText("\u7528\u6237\u540D:");
		
		user_name = new Text(container, SWT.BORDER);
		user_name.setBounds(94, 28, 174, 24);
		
		Label label = new Label(container, SWT.NONE);
		label.setBounds(10, 97, 49, 24);
		label.setText("\u5BC6\u7801:");
		
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
		Button button = createButton(parent, IDialogConstants.OK_ID, "登录",
				true);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				//登录
			}
		});
		createButton(parent, IDialogConstants.CANCEL_ID,
				"取消", false);
	}

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
	    newShell.setText("RMS Login");
	}
	@Override  
	protected void setShellStyle(int newShellStyle) {  
	    // TODO Auto-generated method stub  
	    // 取消关闭“X”按钮  
	    super.setShellStyle(newShellStyle ^ SWT.CLOSE);  
	}
}
