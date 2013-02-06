package Editor;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.rwt.RWT;
import org.eclipse.rwt.service.IServiceHandler;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import Editor.method.TableSort;

import com.entity.LassHeartbeat;
import com.entity.LoginUser;
import com.entity.Router;
import com.query.QueryData;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Spinner;

public class DashBoard_history extends EditorPart{
	public static String ID = "RMS.editor2";
	private Table table_1;
	private Table table;
	private List router;
	private List<LassHeartbeat> missedHBList;
	
	public static TableEditor routerTable_clearEditor;
	public static TableEditor routerTable_caseEditor;
	public static TableEditor hbTable_caseEditor;
	
	private TableColumn Column_3;
	private TableColumn hbColumn_2;
	private LoginUser loginUser;
	
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
		//默认获取3天数据
		QueryData  qd=new QueryData();
		router = qd.querypastThree();
		missedHBList=qd.queryMissedHeartBeatByDay(3);
		this.loginUser=((DashBoard_history_Input)input).getLoginUser();
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
		parent.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(parent, SWT.BORDER | SWT.VERTICAL);
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new FormLayout());
		
		Composite composite = new Composite(composite_1, SWT.NONE);
		FormData fd_composite = new FormData();
		fd_composite.bottom = new FormAttachment(0, 40);
		fd_composite.right = new FormAttachment(100,0);
		fd_composite.top = new FormAttachment(0);
		fd_composite.left = new FormAttachment(0);
		composite.setLayoutData(fd_composite);
		GridLayout gl_composite = new GridLayout(6, false);
		gl_composite.verticalSpacing = 1;
		composite.setLayout(gl_composite);
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText("Alerts Range:");
		
		final Spinner spinner_day = new Spinner(composite, SWT.BORDER);
		spinner_day.setMaximum(90);
		spinner_day.setMinimum(1);
		spinner_day.setSelection(3);
		GridData gd_spinner_day = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_spinner_day.widthHint = 50;
		spinner_day.setLayoutData(gd_spinner_day);
		
		Button btnShow = new Button(composite, SWT.NONE);
		btnShow.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if(spinner_day.getSelection()>0){
					int day=spinner_day.getSelection();
					QueryData qd=new QueryData();
					router=qd.queryAlertHistoryByDay(day);
					missedHBList=qd.queryMissedHeartBeatByDay(day);
					fillTable();
					fillTable_new();
				}
				
			}
		});
		btnShow.setText("Show");
		
		Button button_3day = new Button(composite, SWT.NONE);
		button_3day.setText("Past 3 Days");
		
		
		
		Button button_7day = new Button(composite, SWT.NONE);
		button_7day.setText("Past 7 Days");
		
		Button button_30day = new Button(composite, SWT.NONE);
		button_30day.setText("Past 30 Days");
		
		table = new Table(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
		FormData fd_table = new FormData();
		fd_table.bottom = new FormAttachment(100,0);
		fd_table.right = new FormAttachment(100,0);
		fd_table.top = new FormAttachment(composite,5);
		fd_table.left = new FormAttachment(0);
		table.setLayoutData(fd_table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(192);
		tblclmnNewColumn.setText("Serial");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(452);
		tblclmnNewColumn_1.setText("Message");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(250);
		tblclmnNewColumn_2.setText("Date/Time");
		
		Column_3 = new TableColumn(table, SWT.NONE);
		Column_3.setWidth(285);
		Column_3.setText("Cleared");
		
		routerTable_clearEditor=new TableEditor(table);
		routerTable_caseEditor=new TableEditor(table);
		
		table.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if(e.item!=null){
					addButtonToTable((TableItem)e.item);
				}
				
			}
			
		});
		
		
		//查询三十天前的数据
		button_30day.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				QueryData qd=new QueryData();
				router=qd.queryPastThirty();
				missedHBList=qd.queryMissedHeartBeatByDay(30);
				fillTable();
				fillTable_new();
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		//七天前的历史数据
		button_7day.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				QueryData qd=new QueryData();
				router=qd.queryPastSeven();
				missedHBList=qd.queryMissedHeartBeatByDay(7);
				fillTable();
				fillTable_new();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		//三天前的历史数据querypastThree
		button_3day.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				QueryData  qd=new QueryData();
				router = qd.querypastThree();
				missedHBList=qd.queryMissedHeartBeatByDay(3);
				fillTable();
				fillTable_new();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
				
		TabFolder tabFolder = new TabFolder(sashForm, SWT.NONE);
		
		TabItem tbtmMissedHeartbeats = new TabItem(tabFolder, SWT.NONE);
		tbtmMissedHeartbeats.setText("Missed heartbeats");
		
		Composite composite_3 = new Composite(tabFolder, SWT.NONE);
		tbtmMissedHeartbeats.setControl(composite_3);
		composite_3.setLayout(new FormLayout());
				
		table_1 = new Table(composite_3, SWT.BORDER | SWT.FULL_SELECTION);
		FormData fd_table_1 = new FormData();
		fd_table_1.bottom = new FormAttachment(0, 154);
		fd_table_1.top = new FormAttachment(0);
		fd_table_1.right = new FormAttachment(100,0);
		fd_table_1.left = new FormAttachment(0);
		table_1.setLayoutData(fd_table_1);
		table_1.setHeaderVisible(true);
		table_1.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn_4 = new TableColumn(table_1, SWT.NONE);
		tblclmnNewColumn_4.setWidth(187);
		tblclmnNewColumn_4.setText("Serial");
		
		TableColumn tblclmnNewColumn_5 = new TableColumn(table_1, SWT.NONE);
		tblclmnNewColumn_5.setWidth(240);
		tblclmnNewColumn_5.setText("Last Heartbeat Date/Time");
		
		hbColumn_2 = new TableColumn(table_1, SWT.NONE);
		hbColumn_2.setWidth(205);
		hbColumn_2.setText("Cleared by");
		
		hbTable_caseEditor=new TableEditor(table_1);
		
		sashForm.setWeights(new int[] {337, 222});
		
		fillTable();
		fillTable_new();
		sortTable();
	}

	private void sortTable(){
		new TableSort(table);
		new TableSort(table_1);
	}
	
	/**
	 * 
	 */
	private void fillTable() {
		table_1.removeAll();
		for(int i=0;i<missedHBList.size();i++){
			LassHeartbeat hb = (LassHeartbeat)missedHBList.get(i);
			TableItem ti = new TableItem(table_1,SWT.NONE);
			ti.setText(new String[]{hb.getDeviceId(),hb.getTime(),""});
			ti.setData(hb);
		}
		
	}

	
	private  void fillTable_new(){
		table.removeAll();
		
		//移除编辑器
		Control oldEditor1=routerTable_clearEditor.getEditor();
		if(oldEditor1!=null && !oldEditor1.isDisposed()){
			oldEditor1.dispose();
		}
		Control oldEditor2=routerTable_caseEditor.getEditor();
		if(oldEditor2!=null && !oldEditor2.isDisposed()){
			oldEditor2.dispose();
		}
		
		for(int i=0;i<router.size();i++){
			Router r=(Router)router.get(i);
			TableItem ti =new TableItem(table, SWT.None);
			ti.setText(new String[]{r.getSerial(),r.getMessage(),r.getTime(),""});
			if(r.getCaseid()!=null && !"".equals(r.getCaseid().trim())){
				ti.setText(3,"Case:"+r.getCaseid().trim()+" "+(r.getCasedby()==null?"":r.getCasedby().trim()));
			}else{
				ti.setText(3,"n/a (low level alert)");
			}
			ti.setData(r);
		}
	}
	
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 为路由table添加编辑器
	 * @param tableItem 所选项
	 */
	private void addButtonToTable(final TableItem tableItem){
		
		if(tableItem!=null){
			
			final Router router=(Router) tableItem.getData();
			if(router!=null){
				
				//清理旧的编辑器
				Control oldEditor1=routerTable_caseEditor.getEditor();
	  			if(oldEditor1!=null){
	  				oldEditor1.dispose();
	  			}
	  			Control oldEditor2=routerTable_clearEditor.getEditor();
	  			if(oldEditor2!=null){
	  				oldEditor2.dispose();
	  			}
	  			
	  			
	  			final Button caseButton = new Button(table, SWT.NONE);
	  			caseButton.setText("new case");
	  			caseButton.pack();
	  			caseButton.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						System.out.println("case Click Me!!");
						try {
//							URI	url=URI.create("https://fred.netgear.com/");
//				        	java.awt.Desktop.getDesktop().browse(url); 
//				        	
//				        	RWT.getResponse().getWriter().write("<script>window.open('http://www.baidu.com/')</script>");
//				        	RWT.getResponse().getWriter().flush();
//				        	EditorHtmlDlg eh=new EditorHtmlDlg(new Shell());
//				        	eh.open();
							
							final StringBuffer url = new StringBuffer();
						    url.append( RWT.getRequest().getContextPath() );
						    url.append( RWT.getRequest().getServletPath() );
						    url.append( "?" );
						    url.append( IServiceHandler.REQUEST_PARAM ).append( "=" ).append( "netgear" );
							
							RWT.getServiceManager().registerServiceHandler("netgear",new IServiceHandler() {
								public void service() throws IOException, ServletException {
									HttpServletResponse response=RWT.getResponse();
									OutputStream out=response.getOutputStream();
									
									out.write("<script>window.open('https://fred.netgear.com/')</script>".getBytes());
									out.flush();
								}
							});

							Browser brower=new Browser(Display.getCurrent().getActiveShell(),SWT.NONE);
							brower.setUrl(url.toString());
				        	
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
					
				});
	  			routerTable_caseEditor.minimumWidth=caseButton.getSize().x;
	  			routerTable_caseEditor.horizontalAlignment = SWT.RIGHT;
	  			routerTable_caseEditor.setEditor(caseButton, tableItem, 0);
	  			
	  			//未Clear的显示Clear页面
	  			if(router.getStatus()==0){
		  			//clear面板 
		  			Composite clearComp=new Composite(table, SWT.NONE);
		  			clearComp.setLayout(new FormLayout());
		  			
		  			final Text caseText = new Text(clearComp, SWT.BORDER);
		  			FormData fd_text = new FormData();
		  			fd_text.left = new FormAttachment(0);
		  			fd_text.bottom = new FormAttachment(100, 0);
		  			fd_text.top = new FormAttachment(0);
		  			caseText.setToolTipText("Input Case Number");
		  			caseText.setLayoutData(fd_text);
		  			
		  			final Button clearButton = new Button(clearComp, SWT.NONE);
		  			fd_text.right = new FormAttachment(clearButton, -1);
		  			FormData fd_btnNewButton = new FormData();
		  			fd_btnNewButton.bottom = new FormAttachment(caseText, 0, SWT.BOTTOM);
		  			fd_btnNewButton.left = new FormAttachment(100, -86);
		  			fd_btnNewButton.right = new FormAttachment(100, -2);
		  			fd_btnNewButton.top = new FormAttachment(0);
		  			clearButton.setLayoutData(fd_btnNewButton);
		  			clearButton.setText("clear");
					clearButton.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(SelectionEvent e) {
							System.out.println("Click Me!!");
							if(loginUser!=null){
								if(!"".equals(caseText.getText().trim())){
									QueryData qd=new QueryData();
									Router newRouter=qd.clearInputCase(router.getId(), caseText.getText().trim(),loginUser.getUsername().trim());
									if(newRouter!=null){
										if(newRouter.getCaseid()!=null && !"".equals(newRouter.getCaseid().trim())){
											tableItem.setText(3,"Case:"+newRouter.getCaseid().trim()+" "+(newRouter.getCasedby()==null?"":newRouter.getCasedby().trim()));
										}else{
											tableItem.setText(3,"n/a (low level alert)");
										}
										tableItem.setData(newRouter);
										//清除编辑器
										Control oldEditor2=routerTable_clearEditor.getEditor();
							  			if(oldEditor2!=null){
							  				oldEditor2.dispose();
							  			}
							  			
									}else{
										MessageDialog.openError(getSite().getShell(), "Clear", "Clear Fail!");
									}
								}else{
									MessageDialog.openError(getSite().getShell(), "Clear", "Please Input Case Number!");
								}
							}
						}
						
					});
					//clearComp.pack();
					routerTable_clearEditor.horizontalAlignment=SWT.RIGHT;
					routerTable_clearEditor.minimumWidth=Column_3.getWidth();
					routerTable_clearEditor.setEditor(clearComp, tableItem, 3);
		  			
	  			}
				
//	  			table.addListener(SWT.Deactivate, new Listener() {
//					
//					@Override
//					public void handleEvent(Event event) {
//						// TODO Auto-generated method stub
//						Control oldEditor1=routerTable_caseEditor.getEditor();
//			  			if(oldEditor1!=null){
//			  				oldEditor1.dispose();
//			  			}
//			  			Control oldEditor2=routerTable_clearEditor.getEditor();
//			  			if(oldEditor2!=null){
//			  				oldEditor2.dispose();
//			  			}
//					}
//				});
	  			
			
	  			
			}
			
		}
		
	}
	
	/**
	 * 为心跳table添加编辑器
	 * @param tableItem 所选项
	 */
	private void addButtonToHBTable(TableItem tableItem){
		
		if(tableItem!=null){
			
			final Router router=(Router) tableItem.getData();
			if(router!=null){
				
				//清理旧的编辑器
				Control oldEditor1=hbTable_caseEditor.getEditor();
	  			if(oldEditor1!=null){
	  				oldEditor1.dispose();
	  			}
	  			
	  			//未Clear的显示Clear页面
	  			if(router.getStatus()==0){
		  			//clear面板 
		  			Composite clearComp=new Composite(table_1, SWT.NONE);
		  			clearComp.setLayout(new FormLayout());
		  			
		  			final Text caseText = new Text(clearComp, SWT.BORDER);
		  			FormData fd_text = new FormData();
		  			fd_text.left = new FormAttachment(0);
		  			fd_text.bottom = new FormAttachment(100, 0);
		  			fd_text.top = new FormAttachment(0);
		  			caseText.setToolTipText("Input Case Number");
		  			caseText.setLayoutData(fd_text);
		  			
		  			final Button clearButton = new Button(clearComp, SWT.NONE);
		  			fd_text.right = new FormAttachment(clearButton, -1);
		  			FormData fd_btnNewButton = new FormData();
		  			fd_btnNewButton.bottom = new FormAttachment(caseText, 0, SWT.BOTTOM);
		  			fd_btnNewButton.left = new FormAttachment(100, -86);
		  			fd_btnNewButton.right = new FormAttachment(100, -2);
		  			fd_btnNewButton.top = new FormAttachment(0);
		  			clearButton.setLayoutData(fd_btnNewButton);
		  			clearButton.setText("clear");
					clearButton.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(SelectionEvent e) {
							System.out.println("Click Me!!");
							if(loginUser!=null){
								if(!"".equals(caseText.getText().trim())){
									QueryData qd=new QueryData();
									if(qd.clearInputCase(router.getId(), caseText.getText().trim(),loginUser.getUsername().trim())!=null){
										fillTable();
									}else{
										MessageDialog.openError(getSite().getShell(), "Clear", "Clear Fail!");
									}
								}else{
									MessageDialog.openError(getSite().getShell(), "Clear", "Please Input Case Number!");
								}
							}
						}
						
					});
					//clearComp.pack();
					hbTable_caseEditor.horizontalAlignment=SWT.RIGHT;
					hbTable_caseEditor.minimumWidth=hbColumn_2.getWidth();
					hbTable_caseEditor.setEditor(clearComp, tableItem, 2);
		  			
	  			}
				
//	  			table.addListener(SWT.Deactivate, new Listener() {
//					
//					@Override
//					public void handleEvent(Event event) {
//						// TODO Auto-generated method stub
//						Control oldEditor1=routerTable_caseEditor.getEditor();
//			  			if(oldEditor1!=null){
//			  				oldEditor1.dispose();
//			  			}
//			  			Control oldEditor2=routerTable_clearEditor.getEditor();
//			  			if(oldEditor2!=null){
//			  				oldEditor2.dispose();
//			  			}
//					}
//				});
	  			
			
	  			
			}
			
		}
		
	}
	
}
