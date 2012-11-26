package Editor;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridLayout;
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

import Editor.method.TableSort;

import com.entity.Router;
import com.query.QueryData;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.custom.CTabItem;

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
			parent.setLayout(new FillLayout(SWT.HORIZONTAL));
			
			SashForm sashForm = new SashForm(parent, SWT.VERTICAL);
			
			Composite composite_2 = new Composite(sashForm, SWT.NONE);
			composite_2.setLayout(new FormLayout());
			//sashForm.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND));
			
			Composite composite = new Composite(composite_2, SWT.NONE);
			FormData fd_composite = new FormData();
			fd_composite.top = new FormAttachment(0,0);
			fd_composite.left = new FormAttachment(0,0);
			fd_composite.bottom = new FormAttachment(0, 40);
			fd_composite.right = new FormAttachment(100, 0);
			composite.setLayoutData(fd_composite);
			composite.setLayout(new GridLayout(4, false));
			//composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND));
			
			Label lblNewLabel = new Label(composite, SWT.NONE);
			lblNewLabel.setText("Enter Serial:");
			
			text = new Text(composite, SWT.BORDER);
			text.addKeyListener(new KeyListener() {
				
				private static final long serialVersionUID = -7242738273561650313L;

				@Override
				public void keyReleased(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void keyPressed(KeyEvent e) {
					// TODO Auto-generated method stub
					if(e.keyCode == 13){
						QueryData  qd=new QueryData();
						router = qd.query(text.getText());
						fillTable();
					}
				}
			});
			Button btnNewButton = new Button(composite, SWT.NONE);
			btnNewButton.setText("Show All");
			btnNewButton.addSelectionListener(new SelectionListener() {
				
				private static final long serialVersionUID = -2212711577026360395L;

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
				
				private static final long serialVersionUID = 5630586116844726973L;

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
			//composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND));
			
			
			table = new Table(composite_2, SWT.BORDER | SWT.FULL_SELECTION);
			FormData fd_table = new FormData();
			fd_table.bottom = new FormAttachment(100, 0);
			fd_table.top = new FormAttachment(composite, 5);
			fd_table.left = new FormAttachment(0, 0);
			fd_table.right = new FormAttachment(100, 0);
			table.setLayoutData(fd_table);
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
			
			Composite composite_3 = new Composite(sashForm, SWT.NONE);
			composite_3.setLayout(new FillLayout(SWT.HORIZONTAL));
			
			TabFolder tabFolder = new TabFolder(composite_3, SWT.NONE);
			
			TabItem tbtmMissedHeartbeatHistory = new TabItem(tabFolder, SWT.NONE);
			tbtmMissedHeartbeatHistory.setText("Missed Heartbeat History");
			
			Composite composite_1 = new Composite(tabFolder, SWT.NONE);
			tbtmMissedHeartbeatHistory.setControl(composite_1);
			composite_1.setLayout(new FormLayout());
			//composite_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND));
			
			lblNewLabel_1 = new Label(composite_1, SWT.NONE);
			FormData fd_lblNewLabel_1 = new FormData();
			fd_lblNewLabel_1.bottom = new FormAttachment(0, 40);
			fd_lblNewLabel_1.right = new FormAttachment(100, 0);
			fd_lblNewLabel_1.top = new FormAttachment(0);
			fd_lblNewLabel_1.left = new FormAttachment(0);
			lblNewLabel_1.setLayoutData(fd_lblNewLabel_1);
			lblNewLabel_1.setText("Missed Heartbeat History");
			//composite_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND));
			
			table_1 = new Table(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
			FormData fd_table_1 = new FormData();
			fd_table_1.top = new FormAttachment(lblNewLabel_1, 5);
			fd_table_1.left = new FormAttachment(0,0);
			fd_table_1.bottom = new FormAttachment(100, 0);
			fd_table_1.right = new FormAttachment(100,0);
			table_1.setLayoutData(fd_table_1);
			table_1.setHeaderVisible(true);
			table_1.setLinesVisible(true);
			
			tblclmnDatetime = new TableColumn(table_1, SWT.CENTER);
			tblclmnDatetime.setWidth(293);
			tblclmnDatetime.setText("Date/Time");
			
			TabItem tbtmCurrentHeartbeatStatus = new TabItem(tabFolder, SWT.NONE);
			tbtmCurrentHeartbeatStatus.setText("Current Heartbeat status");
			
			Composite composite_4 = new Composite(tabFolder, SWT.NONE);
			tbtmCurrentHeartbeatStatus.setControl(composite_4);
			composite_4.setLayout(new FormLayout());
			
			lblNewLabel_2 = new Label(composite_4, SWT.NONE);
			FormData fd_lblNewLabel_2 = new FormData();
			fd_lblNewLabel_2.bottom = new FormAttachment(0, 40);
			fd_lblNewLabel_2.right = new FormAttachment(100, 0);
			fd_lblNewLabel_2.top = new FormAttachment(0);
			fd_lblNewLabel_2.left = new FormAttachment(0);
			lblNewLabel_2.setLayoutData(fd_lblNewLabel_2);
			lblNewLabel_2.setText("Current Heartbeat status");
			
			table_2 = new Table(composite_4, SWT.BORDER | SWT.FULL_SELECTION);
			FormData fd_table_2 = new FormData();
			fd_table_2.top = new FormAttachment(lblNewLabel_2, 5);
			fd_table_2.left = new FormAttachment(0, 0);
			fd_table_2.bottom = new FormAttachment(100, 0);
			fd_table_2.right = new FormAttachment(100,0);
			table_2.setLayoutData(fd_table_2);
			table_2.setHeaderVisible(true);
			table_2.setLinesVisible(true);
			
			tblclmnNewColumn_5 = new TableColumn(table_2, SWT.CENTER);
			tblclmnNewColumn_5.setWidth(431);
			tblclmnNewColumn_5.setText("Last Successful  Heartbeat");
			sashForm.setWeights(new int[] {275, 186});
			Table1();
			Table2();
			fillTable();
			
			sortTable();
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
		
		private void sortTable(){
			new TableSort(table);
			new TableSort(table_1);
			new TableSort(table_2);
		}
		@Override
		public void setFocus() {
			
			
		}
	}
