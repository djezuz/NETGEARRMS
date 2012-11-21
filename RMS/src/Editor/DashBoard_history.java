package Editor;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
//import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.entity.Router;
import com.query.QueryData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;

public class DashBoard_history extends EditorPart{
	public static String ID = "RMS.editor2";
	private Table table_1;
	private Table table;
	private List router;
	
	public DashBoard_history() {
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
		parent.setLayout(new FormLayout());
		
		Composite composite = new Composite(parent, SWT.NONE);
		FormData fd_composite = new FormData();
		fd_composite.bottom = new FormAttachment(0, 40);
		fd_composite.right = new FormAttachment(100, 0);
		fd_composite.top = new FormAttachment(0);
		fd_composite.left = new FormAttachment(0);
		composite.setLayoutData(fd_composite);
		GridLayout gl_composite = new GridLayout(4, false);
		gl_composite.horizontalSpacing = 10;
		gl_composite.marginLeft = 5;
		gl_composite.marginTop = 5;
		gl_composite.verticalSpacing = 1;
		composite.setLayout(gl_composite);
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setText("Alerts Range:");
		
		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.setText("Past 3 Days");
		
		
		
		Button btnNewButton_1 = new Button(composite, SWT.NONE);
		btnNewButton_1.setText("Past 7 Days");
		
		Button btnNewButton_2 = new Button(composite, SWT.NONE);
		btnNewButton_2.setText("Past 30 Days");
		//��ѯ��ʮ��ǰ������
		btnNewButton_2.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				QueryData qd=new QueryData();
				router=qd.queryPastThirty();
				fillTable();
			}
			private  void fillTable(){
				for(int i=0;i<router.size();i++){
					Router r=(Router)router.get(i);
				TableItem ti =new TableItem(table, SWT.None);
				ti.setText(new String[]{r.getSerial(),r.getMessage(),r.getTime(),r.getClearedBy()});
				}
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		//����ǰ����ʷ����
		btnNewButton_1.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				QueryData qd=new QueryData();
				router=qd.queryPastSeven();
				fillTable();
			}
			
			private void fillTable() {
				table.removeAll();
				for(int i=0;i<router.size();i++){
					Router r = (Router)router.get(i);
					TableItem ti = new TableItem(table,SWT.NONE);
					ti.setText(new String[]{r.getSerial(),r.getMessage(),r.getTime(),r.getClearedBy()});
				}
				
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		//����ǰ����ʷ����querypastThree
		btnNewButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				QueryData  qd=new QueryData();
				router = qd.querypastThree();
				fillTable();
				
			}
			
			private void fillTable() {
				table.removeAll();
				for(int i=0;i<router.size();i++){
					Router r = (Router)router.get(i);
					TableItem ti = new TableItem(table,SWT.NONE);
					ti.setText(new String[]{r.getSerial(),r.getMessage(),r.getTime(),r.getClearedBy()});
				}
				
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		table = new Table(parent, SWT.BORDER | SWT.FULL_SELECTION);
		FormData fd_table = new FormData();
		fd_table.top = new FormAttachment(composite, 5);
		fd_table.left = new FormAttachment(0);
		fd_table.bottom = new FormAttachment(0, 300);
		fd_table.right = new FormAttachment(100, 0);
		table.setLayoutData(fd_table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(109);
		tblclmnNewColumn.setText("Serial");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(170);
		tblclmnNewColumn_1.setText("Message");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(292);
		tblclmnNewColumn_2.setText("Date/Time");
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_3.setWidth(229);
		tblclmnNewColumn_3.setText("Cleared");
		
		Label lblNewLabel_2 = new Label(parent, SWT.NONE);
		FormData fd_lblNewLabel_2 = new FormData();
		fd_lblNewLabel_2.left = new FormAttachment(0);
		fd_lblNewLabel_2.right = new FormAttachment(100,0);
		fd_lblNewLabel_2.bottom = new FormAttachment(0, 330);
		fd_lblNewLabel_2.top = new FormAttachment(table, 5);
		lblNewLabel_2.setLayoutData(fd_lblNewLabel_2);
		lblNewLabel_2.setText("Missed heartbeats");

		table_1 = new Table(parent, SWT.BORDER | SWT.FULL_SELECTION);
		FormData fd_table_1 = new FormData();
		fd_table_1.bottom = new FormAttachment(100, 0);
		fd_table_1.top = new FormAttachment(lblNewLabel_2, 5);
		fd_table_1.right = new FormAttachment(100, 0);
		fd_table_1.left = new FormAttachment(0);
		table_1.setLayoutData(fd_table_1);
		table_1.setHeaderVisible(true);
		table_1.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn_4 = new TableColumn(table_1, SWT.NONE);
		tblclmnNewColumn_4.setWidth(203);
		tblclmnNewColumn_4.setText("Serial");
		
		TableColumn tblclmnNewColumn_5 = new TableColumn(table_1, SWT.NONE);
		tblclmnNewColumn_5.setWidth(252);
		tblclmnNewColumn_5.setText("Last Heartbeat Date/Time");
		
		TableColumn tblclmnNewColumn_6 = new TableColumn(table_1, SWT.NONE);
		tblclmnNewColumn_6.setWidth(270);
		tblclmnNewColumn_6.setText("Cleared by");
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}
}
