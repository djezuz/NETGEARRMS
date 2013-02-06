package Editor.method;

import java.text.Collator;
import java.util.Locale;

import org.eclipse.swt.SWT;
//import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import Editor.DashBoard;
import Editor.DashBoard_history;

/**
 * 2012-11-21
 * @author zhangfan
 *
 */
public class TableSort {
	private Table table;
//	private Image upImage;
//	private Image downImage;

	private int[] sortFlags;
	private int[] sortColIndexes;

	public TableSort(Table table){
		int[] sortCols = new int[table.getColumnCount()];
		for (int i = 1; i < sortCols.length; i++) {
			sortCols[i] = i;
		}

		this.table = table;
		this.sortColIndexes = sortCols; // ��Ҫ���������
		this.sortFlags = new int[table.getColumnCount()];

		init();
	}

	private void init() {
		for (int i = 0; i < sortColIndexes.length; i++) {
			final int sortColIndex = this.sortColIndexes[i];
			TableColumn col = table.getColumn(sortColIndex);

			col.addListener(SWT.Selection, new Listener() {
				private static final long serialVersionUID = 1214094827062413757L;

				public void handleEvent(Event event) {
					columnHandleEvent(event, sortColIndex);
					//����༭��
					Control oldEditor1=DashBoard.routerTable_caseEditor.getEditor();
		  			if(oldEditor1!=null){
		  				oldEditor1.dispose();
		  			}
		  			Control oldEditor2=DashBoard.routerTable_clearEditor.getEditor();
		  			if(oldEditor2!=null){
		  				oldEditor2.dispose();
		  			}
		  			
		  			Control oldEditor3=DashBoard_history.routerTable_caseEditor.getEditor();
		  			if(oldEditor3!=null){
		  				oldEditor3.dispose();
		  			}
		  			Control oldEditor4=DashBoard_history.routerTable_clearEditor.getEditor();
		  			if(oldEditor4!=null){
		  				oldEditor4.dispose();
		  			}
				}
			});
		}

		// this.upImage =
		// FrameCommonActivator.getImageDescriptor("icons/up.gif").createImage();
		// this.downImage =
		// FrameCommonActivator.getImageDescriptor("icons/down.gif").createImage();
	}

	private void columnHandleEvent(Event event, int sortColIndex) {
		try {
			for (int i = 0; i < sortColIndexes.length; i++) {
				TableColumn tabCol = table.getColumn(i);
				tabCol.setImage(null);
			}

			boolean selectColumnType = this.isStringOrNumberType(sortColIndex);

			if (this.sortFlags[sortColIndex] == 1) {
				clearSortFlags();
				this.sortFlags[sortColIndex] = -1;

				if (selectColumnType) {
					this.addNumberSorter(table.getColumn(sortColIndex), true);
				} else {
					this.addStringSorter(table.getColumn(sortColIndex), true);
				}

				// table.getColumn(sortColIndex).setImage(this.upImage);
			} else {
				this.sortFlags[sortColIndex] = 1;

				if (selectColumnType) {
					this.addNumberSorter(table.getColumn(sortColIndex), false);
				} else {
					this.addStringSorter(table.getColumn(sortColIndex), false);
				}

				// table.getColumn(sortColIndex).setImage(this.downImage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param table
	 * @param column
	 * @param isAscend
	 */
	private void addStringSorter(TableColumn column, boolean isAscend) {

		Collator comparator = Collator.getInstance(Locale.getDefault());
		int columnIndex = getColumnIndex(table, column);
		TableItem[] items = table.getItems();
		// ʹ��ð�ݷ���������
		for (int i = 1; i < items.length; i++) {
			String str2value = items[i].getText(columnIndex);
			if (str2value.equalsIgnoreCase("")) {
				// ����������еĿ���Ŀʱ����ֹͣ���¼���������Ŀ
				break;
			}
			for (int j = 0; j < i; j++) {
				String str1value = items[j].getText(columnIndex);
				boolean isLessThan = comparator.compare(str2value, str1value) < 0;
				if ((isAscend && isLessThan) || (!isAscend && !isLessThan)) {
					String[] values = getTableItemText(table, items[i]);
					Object obj = items[i].getData();
					items[i].dispose();
					TableItem item = new TableItem(table, SWT.NONE, j);
					item.setText(values);
					item.setData(obj);
					items = table.getItems();
					break;
				}
			}
		}
		table.setSortColumn(column);
		table.setSortDirection((isAscend ? SWT.UP : SWT.DOWN));
		isAscend = !isAscend;
	}

	private void addNumberSorter(TableColumn column, boolean isAscend) {

		int columnIndex = getColumnIndex(table, column);
		TableItem[] items = table.getItems();
		// ʹ��ð�ݷ���������
		for (int i = 1; i < items.length; i++) {
			String strvalue2 = items[i].getText(columnIndex);
			if (strvalue2.equalsIgnoreCase("")) {
				// ����������еĿ���Ŀʱ����ֹͣ���¼���������Ŀ
				break;
			}

			for (int j = 0; j < i; j++) {
				String strvalue1 = items[j].getText(columnIndex);

				// ���ַ�����������ת��Ϊfloat����
				float numbervalue1 = Float.valueOf(strvalue1);
				float numbervalue2 = Float.valueOf(strvalue2);

				boolean isLessThan = false;
				if (numbervalue2 < numbervalue1) {
					isLessThan = true;
				}

				if ((isAscend && isLessThan) || (!isAscend && !isLessThan)) {
					String[] values = getTableItemText(table, items[i]);
					Object obj = items[i].getData();
					items[i].dispose();
					TableItem item = new TableItem(table, SWT.NONE, j);
					item.setText(values);
					item.setData(obj);
					items = table.getItems();
					break;
				}
			}
		}

		table.setSortColumn(column);
		table.setSortDirection((isAscend ? SWT.UP : SWT.DOWN));
		isAscend = !isAscend;
	}

	private int getColumnIndex(Table table, TableColumn column) {
		TableColumn[] columns = table.getColumns();
		for (int i = 0; i < columns.length; i++) {
			if (columns[i].equals(column))
				return i;
		}
		return -1;
	}

	private String[] getTableItemText(Table table, TableItem item) {
		int count = table.getColumnCount();
		String[] strs = new String[count];
		for (int i = 0; i < count; i++) {
			strs[i] = item.getText(i);
		}
		return strs;
	}

	private void clearSortFlags() {
		for (int i = 0; i < table.getColumnCount(); i++) {
			this.sortFlags[i] = 0;
		}
	}

	/**
	 * �жϵ�ǰѡ���е���������
	 * 
	 * @return
	 */
	private boolean isStringOrNumberType(int selectColumnIndex) {
		boolean isok = false;

		TableItem[] items = table.getItems();
		String[] str = new String[items.length];

		for (int i = 0; i < items.length; i++) {
			str[i] = items[i].getText(selectColumnIndex);
		}

		for (int i = 0; i < str.length; i++) {
			String string = str[i];
			isok = string.matches("^(-|\\+)?\\d+\\.?\\d*$");
			// �����һ������һ�����ַ�����Ҳ���ַ�������
			if (!isok) {
				return isok;
			}
		}

		return isok;
	}

}