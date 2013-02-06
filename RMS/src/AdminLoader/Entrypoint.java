package AdminLoader;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.rwt.lifecycle.IEntryPoint;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

import Editor.LoginDlg;

public class Entrypoint implements IEntryPoint {

	@Override
	public int createUI() {
		Display display = PlatformUI.createDisplay();
		display.addListener(SWT.CLOSE, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				on_Exit();
			}
		});
		try {
			LoginDlg login_dlg = new LoginDlg(null);
			if (login_dlg.open()!=Dialog.OK){
				return IApplication.EXIT_OK;
			}else{
				ApplicationWorkbenchAdvisor advisor=new ApplicationWorkbenchAdvisor();
				advisor.setLoginUser(login_dlg.getLoginUser());
				int returnCode = PlatformUI.createAndRunWorkbench(display, advisor);
				if (returnCode == PlatformUI.RETURN_RESTART)
					return IApplication.EXIT_RESTART;
				else
					return IApplication.EXIT_OK;
			}
		} finally {
			display.dispose();
		}
	}
	
	private void on_Exit(){
		if (!PlatformUI.isWorkbenchRunning())
			return;
		final IWorkbench workbench = PlatformUI.getWorkbench();
		final Display display = workbench.getDisplay();
		display.syncExec(new Runnable() {
			public void run() {
				if (!display.isDisposed())
					workbench.close();
			}
		});
	}

}
