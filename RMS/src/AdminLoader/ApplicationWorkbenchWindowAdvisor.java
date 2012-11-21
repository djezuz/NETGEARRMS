package AdminLoader;

import java.util.List;

import org.eclipse.swt.graphics.Point;
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

import com.query.QueryData;


public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

    public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        super(configurer);
    }

    public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
        return new ApplicationActionBarAdvisor(configurer);
    }
    
    public void preWindowOpen() {
        IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
        configurer.setInitialSize(new Point(600, 600));
        configurer.setShowCoolBar(false);
        configurer.setShowStatusLine(false);
        configurer.setTitle("ReadyNAS Monitoring System"); //$NON-NLS-1$
       
    }
public void postWindowOpen() {
	PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell().setMaximized(true);
		try {
			QueryData qd = new QueryData();
			List li = qd.query("all");
		
			PlatformUI
			.getWorkbench()
			.getActiveWorkbenchWindow()
			.getActivePage()
			.openEditor(new DashBoard_alters_Input(),
					DashBoard_alters.ID);
			
			PlatformUI
			.getWorkbench()
			.getActiveWorkbenchWindow()
			.getActivePage()
			.openEditor(new DashBoard_history_Input(li),
					DashBoard_history.ID);
			
			PlatformUI
			.getWorkbench()
			.getActiveWorkbenchWindow()
			.getActivePage()
			.openEditor(new DashBoardInput(li),
					DashBoard.ID);
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
