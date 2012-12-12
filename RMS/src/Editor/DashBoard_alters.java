package Editor;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import Editor.method.TableSort;

import com.entity.Router;
import com.query.QueryData;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.layout.FillLayout;

public class DashBoard_alters extends EditorPart {
	public static String ID = "RMS.editor3";
	private Table table;
	private Table table_1;
	private List router;
	private List router1;
	public DashBoard_alters() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub

	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		// TODO Auto-generated method stub
		this.setSite(site);
		this.setInput(input);
		this.setPartName(input.getName());
		if(input instanceof DashBoard_alters_Input){
			this.router = ((DashBoard_alters_Input) input).getRouter();
			this.router1 = ((DashBoard_alters_Input) input).getRouter1();
		}
	}

	@Override
	public boolean isDirty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(parent, SWT.BORDER | SWT.VERTICAL);
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setLayout(new FormLayout());
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.bottom = new FormAttachment(0, 40);
		fd_lblNewLabel.right = new FormAttachment(100,0);
		fd_lblNewLabel.top = new FormAttachment(0);
		fd_lblNewLabel.left = new FormAttachment(0);
		lblNewLabel.setLayoutData(fd_lblNewLabel);
		lblNewLabel.setText("Units with new,unresolved alerts");
		
		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		FormData fd_table = new FormData();
		fd_table.bottom = new FormAttachment(100,0);
		fd_table.right = new FormAttachment(100,0);
		fd_table.top = new FormAttachment(lblNewLabel,5);
		fd_table.left = new FormAttachment(0);
		table.setLayoutData(fd_table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(205);
		tblclmnNewColumn.setText("Serial");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(380);
		tblclmnNewColumn_1.setText("Date/Time Latest Alert Received");
		
		TabFolder tabFolder = new TabFolder(sashForm, SWT.NONE);
		
		TabItem tbtmMissedHeartbeats = new TabItem(tabFolder, SWT.NONE);
		tbtmMissedHeartbeats.setText("Missed heartbeats");
		
		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		tbtmMissedHeartbeats.setControl(composite_1);
		composite_1.setLayout(new FormLayout());
		
		Label lblNewLabel_1 = new Label(composite_1, SWT.NONE);
		FormData fd_lblNewLabel_1 = new FormData();
		fd_lblNewLabel_1.bottom = new FormAttachment(0, 0);
		fd_lblNewLabel_1.right = new FormAttachment(100,0);
		fd_lblNewLabel_1.top = new FormAttachment(0);
		fd_lblNewLabel_1.left = new FormAttachment(0);
		lblNewLabel_1.setLayoutData(fd_lblNewLabel_1);
		lblNewLabel_1.setText("Missed heartbeats");
		
		table_1 = new Table(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
		FormData fd_table_1 = new FormData();
		fd_table_1.bottom = new FormAttachment(100,0);
		fd_table_1.right = new FormAttachment(100,0);
		fd_table_1.top = new FormAttachment(lblNewLabel_1,5);
		fd_table_1.left = new FormAttachment(0);
		table_1.setLayoutData(fd_table_1);
		table_1.setHeaderVisible(true);
		table_1.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table_1, SWT.NONE);
		tblclmnNewColumn_2.setWidth(200);
		tblclmnNewColumn_2.setText("Serial");
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table_1, SWT.NONE);
		tblclmnNewColumn_3.setWidth(383);
		tblclmnNewColumn_3.setText("Last Successful Heartbeat Date /Time");
		sashForm.setWeights(new int[] {337, 222});
        fillTable();
        Table();
        sortTable();
	}

	private void sortTable(){
		new TableSort(table);
		new TableSort(table_1);
	}
	
	public void fillTable(){
		    table_1.removeAll();
			for(int i=0;i<router1.size();i++){
				Router r = (Router)router1.get(i);
				TableItem ti = new TableItem(table_1,SWT.NONE);
				ti.setText(new String[]{r.getSerial(),r.getTime()});
			}
		}
	
	public  void Table(){
		table.removeAll();
		for(int i=0;i<router.size();i++){
		   Router r = (Router)router.get(i);
				TableItem ti = new TableItem(table,SWT.NONE);
				ti.setText(new String[]{r.getSerial(),r.getTime()});
		}
	}
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
