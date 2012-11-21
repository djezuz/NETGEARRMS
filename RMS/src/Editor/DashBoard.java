package Editor;

import java.awt.Font;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
//import org.eclipse.wb.swt.SWTResourceManager;

import com.entity.Router;
import com.query.QueryData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.GridLayout;

public class DashBoard extends EditorPart {
	    public static String ID = "RMS.WebJieMian";
		private Text text;
		private Label lblNewLabel_1;
		private Label lblNewLabel_2;
		private Table table_1;
		private Table table_2;
		private TableColumn tblclmnNewColumn;
		private TableColumn tblclmnNewColumn_1;
		private TableColumn tblclmnNewColumn_2;
		private TableColumn tblclmnNewColumn_3;
		private TableColumn tblclmnNewColumn_4;
		private Table table;
		
		private List router;
		private TableColumn tblclmnDatetime;
		private TableColumn tblclmnNewColumn_5;
		public DashBoard() {
		}

		@Override
		public void doSave(IProgressMonitor monitor) {
			
		}

		@Override
		public void doSaveAs() {
			
		}

		@Override
		public void init(IEditorSite site, IEditorInput input)
				throws PartInitException {
			this.setSite(site);
			this.setInput(input);
			this.setPartName(input.getName());
			if(input instanceof DashBoardInput){
				this.router = ((DashBoardInput) input).getRouter();
			}
		}

		@Override
		public boolean isDirty() {
			return false;
		}

		@Override
		public boolean isSaveAsAllowed() {
			return false;
		}

		@Override
		public void createPartControl(Composite parent) {
			parent.setLayout(new FormLayout());
			//sashForm.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND));
			
			Composite composite = new Composite(parent, SWT.BORDER);
			composite.setLayout(new GridLayout(4, false));
			FormData fd_composite = new FormData();
			fd_composite.right = new FormAttachment(100, 0);
			fd_composite.left = new FormAttachment(0);
			fd_composite.top = new FormAttachment(0);
			fd_composite.bottom = new FormAttachment(0, 40);
			composite.setLayoutData(fd_composite);
			//composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND));
			
			Label lblNewLabel = new Label(composite, SWT.NONE);
			lblNewLabel.setText("Enter Serial:");
			
			text = new Text(composite, SWT.BORDER);
			
			Button btnNewButton = new Button(composite, SWT.NONE);
			btnNewButton.setText("Show All");
			btnNewButton.addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					// TODO Auto-generated method stub
					QueryData  qd=new QueryData();
					router = qd.query(text.getText());
					fillTable();
					System.out.println(text.getText());
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {
					// TODO Auto-generated method stub
					
				}
			});
			
			Button btnNewButton_1 = new Button(composite, SWT.NONE);
			btnNewButton_1.setText("Show only Critical/Alter-Level Events ");
			btnNewButton_1.addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent e) {
					QueryData  qd=new QueryData();
					router = qd.querylevel(text.getText());
					fillTable();
					
					
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			
			
			Composite composite_1 = new Composite(parent, SWT.NONE);
			FormData fd_composite_1 = new FormData();
			fd_composite_1.bottom = new FormAttachment(0, 300);
			fd_composite_1.right = new FormAttachment(100, 0);
			fd_composite_1.top = new FormAttachment(composite, 5);
			fd_composite_1.left = new FormAttachment(0);
			composite_1.setLayoutData(fd_composite_1);
			composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
			//composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND));
			
			
			table = new Table(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
			table.setHeaderVisible(true);
			table.setLinesVisible(true);
			
			tblclmnNewColumn = new TableColumn(table, SWT.NONE);
			tblclmnNewColumn.setText("Serial");
			tblclmnNewColumn.setWidth(184);
			
			tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
			tblclmnNewColumn_1.setWidth(229);
			tblclmnNewColumn_1.setText("Level");
			
			tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
			tblclmnNewColumn_2.setWidth(356);
			tblclmnNewColumn_2.setText("Message");
			
			tblclmnNewColumn_3 = new TableColumn(table, SWT.NONE);
			tblclmnNewColumn_3.setWidth(305);
			tblclmnNewColumn_3.setText("Date/Time");
			
			tblclmnNewColumn_4 = new TableColumn(table, SWT.NONE);
			tblclmnNewColumn_4.setWidth(314);
			tblclmnNewColumn_4.setText("Cleared by");
			
			Composite composite_3 = new Composite(parent, SWT.NONE);
			composite_3.setLayout(null);
			FormData fd_composite_3 = new FormData();
			fd_composite_3.bottom = new FormAttachment(100,0);
			fd_composite_3.right = new FormAttachment(100, 0);
			fd_composite_3.top = new FormAttachment(composite_1, 0);
			fd_composite_3.left = new FormAttachment(0, 0);
			composite_3.setLayoutData(fd_composite_3);
			//composite_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND));
			
			lblNewLabel_1 = new Label(composite_3, SWT.NONE);
			lblNewLabel_1.setBounds(3, 3, 257, 20);
			lblNewLabel_1.setText("Missed Heartbeat History");
			
			lblNewLabel_2 = new Label(composite_3, SWT.NONE);
			lblNewLabel_2.setBounds(329, 3, 257, 20);
			lblNewLabel_2.setText("Current Heartbeat status");
			//composite_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND));
			
			table_1 = new Table(composite_3, SWT.BORDER | SWT.FULL_SELECTION);
			table_1.setBounds(3, 29, 295, 145);
			table_1.setHeaderVisible(true);
			table_1.setLinesVisible(true);
			
			tblclmnDatetime = new TableColumn(table_1, SWT.CENTER);
			tblclmnDatetime.setWidth(293);
			tblclmnDatetime.setText("Date/Time");
			
			table_2 = new Table(composite_3, SWT.BORDER | SWT.FULL_SELECTION);
			table_2.setBounds(329, 29, 432, 145);
			table_2.setHeaderVisible(true);
			table_2.setLinesVisible(true);
			
			tblclmnNewColumn_5 = new TableColumn(table_2, SWT.CENTER);
			tblclmnNewColumn_5.setWidth(431);
			tblclmnNewColumn_5.setText("Last Successful  Heartbeat");
			Table1();
			Table2();
			fillTable();
		}
		
		public void fillTable(){
			table.removeAll();
			for(int i=0;i<router.size();i++){
				Router r = (Router)router.get(i);
				TableItem ti = new TableItem(table,SWT.NONE);
				ti.setText(new String[]{r.getSerial(),r.getLevel(),r.getMessage(),r.getTime(),r.getClearedBy()});
			}
		}
		public void Table2(){
			table_2.removeAll();
			QueryData qd = new QueryData();
		   String time=qd.queryLast();
				TableItem ti = new TableItem(table_2,SWT.NONE);
				ti.setText(time);
		
		}
		public void Table1(){
			table_1.removeAll();
			QueryData qd = new QueryData();
		   String time=qd.queryMoreOne();
				TableItem ti = new TableItem(table_1,SWT.NONE);
				ti.setText(time);
		
		}
		
		@Override
		public void setFocus() {
			
			
		}
	}
