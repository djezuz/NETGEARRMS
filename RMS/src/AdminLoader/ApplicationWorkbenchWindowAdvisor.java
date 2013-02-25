package AdminLoader;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

import Editor.DashBoard;
import Editor.DashBoardInput;
import Editor.DashBoard_history;
import Editor.DashBoard_history_Input;
import Editor.DashBoard_alters;
import Editor.DashBoard_alters_Input;

import com.entity.LoginUser;
import com.query.QueryData;


public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

	private LoginUser loginUser;
	
    public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        super(configurer);
    }
    
    public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer,LoginUser loginUser) {
        super(configurer);
        this.loginUser=loginUser;
    }

    public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
        return new ApplicationActionBarAdvisor(configurer);
    }
    
    public void preWindowOpen() {
        IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
        configurer.setInitialSize(new Point(600, 600));
        configurer.setShowCoolBar(false);
        configurer.setShowMenuBar(false);
        configurer.setShowStatusLine(false);
        configurer.setTitle("ReadyNAS Monitoring System"); //$NON-NLS-1$
        configurer.setShellStyle(SWT.TITLE);
       
    }
public void postWindowOpen() {
	PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell().setMaximized(true);
	PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell().setMinimized(false);
	PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell().setImage(new Image(Display.getCurrent(), this.getClass().getResourceAsStream("/icons/logo.png")));
		try {
			
			if(loginUser!=null){
				
				PlatformUI
				.getWorkbench()
				.getActiveWorkbenchWindow()
				.getActivePage()
				.openEditor(new DashBoard_alters_Input(this.loginUser),
						DashBoard_alters.ID);
				
				PlatformUI
				.getWorkbench()
				.getActiveWorkbenchWindow()
				.getActivePage()
				.openEditor(new DashBoard_history_Input(this.loginUser),
						DashBoard_history.ID);
				
				PlatformUI
				.getWorkbench()
				.getActiveWorkbenchWindow()
				.getActivePage()
				.openEditor(new DashBoardInput(loginUser),
						DashBoard.ID);
			}
			
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public LoginUser getLoginUser() {
		return loginUser;
	}
	
	public void setLoginUser(LoginUser loginUser) {
		this.loginUser = loginUser;
	}

	

}
