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
import org.eclipse.swt.custom.TableEditor;

public class DashBoard extends EditorPart {
	    public static String ID = "RMS.WebJieMian";
		private Text text;
		private TableColumn tblclmnNewColumn;
		private TableColumn tblclmnNewColumn_1;
		private TableColumn tblclmnNewColumn_2;
		private TableColumn tblclmnNewColumn_3;
		private TableColumn tblclmnNewColumn_4;
		private Table table;
		
		private List router;
		private Table table_4;
		private Table table_5;
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
			composite_3.setLayout(new FillLayout());
			
			SashForm sashForm_1 = new SashForm(composite_3, SWT.HORIZONTAL);
			
			Composite composite_1 = new Composite(sashForm_1, SWT.NONE);
			composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
			
			SashForm sashForm_2 = new SashForm(composite_1, SWT.VERTICAL);
			
			Label lblNewLabel_3 = new Label(sashForm_2, SWT.NONE);
			//lblNewLabel_3.setFont(SWTResourceManager.getFont("ËÎÌו", 11, SWT.NORMAL));
			lblNewLabel_3.setBounds(0, 0, 297, 12);
			lblNewLabel_3.setText("Missed Heartbeat History");
			
			table_4 = new Table(sashForm_2, SWT.BORDER | SWT.FULL_SELECTION);
			table_4.setBounds(0, 18, 297, 216);
			table_4.setHeaderVisible(true);
			table_4.setLinesVisible(true);
			
			TableColumn tblclmnNewColumn_6 = new TableColumn(table_4, SWT.LEFT);
			tblclmnNewColumn_6.setText("Date/Time");
			tblclmnNewColumn_6.setWidth(720);
			sashForm_2.setWeights(new int[] {23, 200});
			
			Composite composite_4 = new Composite(sashForm_1, SWT.NONE);
			composite_4.setLayout(new FillLayout(SWT.VERTICAL));
			
			//Composite composite_6 = new Composite(composite_4, SWT.NONE);
			
			SashForm sashForm_3 = new SashForm(composite_4, SWT.VERTICAL);
			sashForm.setWeights(new int[] {248, 227});
			
			Label lblNewLabel_4 = new Label(sashForm_3, SWT.NONE);
			//lblNewLabel_4.setFont(SWTResourceManager.getFont("ËÎÌו", 11, SWT.NORMAL));
			lblNewLabel_4.setBounds(0, 0, 294, 12);
			lblNewLabel_4.setText("Current Heartbeat status");
			
			table_5 = new Table(sashForm_3, SWT.BORDER | SWT.FULL_SELECTION);
			table_5.setLocation(0, 18);
			table_5.setSize(158, 181);
			table_5.setHeaderVisible(true);
			table_5.setLinesVisible(true);
			
			TableColumn tblclmnNewColumn_7 = new TableColumn(table_5, SWT.NONE);
			tblclmnNewColumn_7.setWidth(729);
			tblclmnNewColumn_7.setText("Last  Successful  Heartbeat");
			sashForm_3.setWeights(new int[] {24, 199});
			sashForm_1.setWeights(new int[] {286, 305});
			
	        Table4();
		    Table5();
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
		public void Table5(){
			table_5.removeAll();
			QueryData qd = new QueryData();
		   String time=qd.queryLast();
		   TableItem ti = new TableItem(table_5,SWT.NONE);
			ti.setText(time);
		
		}
		public void Table4(){
			table_4.removeAll();
			QueryData qd = new QueryData();
		   String time=qd.queryMoreOne();
		   TableItem ti = new TableItem(table_4,SWT.NONE);
			ti.setText(time);
		
		}
		
		private void sortTable(){
			new TableSort(table);
			new TableSort(table_4);
			new TableSort(table_5);
		}
		@Override
		public void setFocus() {
			
			
		}
	}
