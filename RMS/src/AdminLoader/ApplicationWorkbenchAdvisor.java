package AdminLoader;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

import com.entity.LoginUser;

public class ApplicationWorkbenchAdvisor extends WorkbenchAdvisor {

	private static final String PERSPECTIVE_ID = "RMS.perspective"; //$NON-NLS-1$
	private LoginUser loginUser;

	public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
		ApplicationWorkbenchWindowAdvisor advisor=new ApplicationWorkbenchWindowAdvisor(configurer,loginUser);
        return advisor;
    }
	
	public String getInitialWindowPerspectiveId() {
		return PERSPECTIVE_ID;
	}

	public LoginUser getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(LoginUser loginUser) {
		this.loginUser = loginUser;
	}
	
	
	
}
