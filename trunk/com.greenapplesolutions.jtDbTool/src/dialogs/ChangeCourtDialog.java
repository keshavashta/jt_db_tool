package dialogs;

import modelProvider.ChangeCourtDialogModelProvider;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;

import util.SelectedCourt;
import util.Util;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.ResourceManager;

public class ChangeCourtDialog extends Dialog {
	private DataBindingContext m_bindingContext;

	protected Object result;
	protected Shell shlChangeCourt;
	private ChangeCourtDialogModelProvider modelProvider;
	private Combo combo;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public ChangeCourtDialog(Shell parent) {
		super(parent);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlChangeCourt.open();
		shlChangeCourt.layout();
		Display display = getParent().getDisplay();
		while (!shlChangeCourt.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		modelProvider = new ChangeCourtDialogModelProvider();
		shlChangeCourt = new Shell(getParent());
		shlChangeCourt.setImage(ResourceManager.getPluginImage("com.greenapplesolutions.jtDbTool", "icons/appIcons/court32x32.png"));
//		shlChangeCourt.setSize(379, 160);
		shlChangeCourt.setBounds(Util.getBounds(379, 160));
		shlChangeCourt.setText("Change Court");
		shlChangeCourt.setLayout(new FormLayout());

		Label lblSelectCourt = new Label(shlChangeCourt, SWT.NONE);
		lblSelectCourt.setFont(SWTResourceManager.getFont("Segoe UI", 9,
				SWT.BOLD));
		FormData fd_lblSelectCourt = new FormData();
		lblSelectCourt.setLayoutData(fd_lblSelectCourt);
		lblSelectCourt.setText("Select Court");

		combo = new Combo(shlChangeCourt, SWT.READ_ONLY);
		FormData fd_combo = new FormData();
		fd_combo.right = new FormAttachment(lblSelectCourt, 284, SWT.RIGHT);
		fd_combo.left = new FormAttachment(lblSelectCourt, 6);
		combo.setLayoutData(fd_combo);

		Button btnClose = new Button(shlChangeCourt, SWT.NONE);
		btnClose.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlChangeCourt.close();
			}
		});
		FormData fd_btnClose = new FormData();
		fd_btnClose.bottom = new FormAttachment(100, -10);
		fd_btnClose.right = new FormAttachment(100, -10);
		btnClose.setLayoutData(fd_btnClose);
		btnClose.setText("Close");

		Button btnSet = new Button(shlChangeCourt, SWT.NONE);
		btnSet.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				modelProvider.changeCourt();
				shlChangeCourt.close();
			}
		});
		FormData fd_btnSet = new FormData();
		fd_btnSet.top = new FormAttachment(btnClose, 0, SWT.TOP);
		fd_btnSet.right = new FormAttachment(btnClose, -6);
		btnSet.setLayoutData(fd_btnSet);
		btnSet.setText("Change");

		Label lblToChangeCourt = new Label(shlChangeCourt, SWT.WRAP);
		fd_lblSelectCourt.left = new FormAttachment(lblToChangeCourt, 0,
				SWT.LEFT);
		FormData fd_lblToChangeCourt = new FormData();
		fd_lblToChangeCourt.right = new FormAttachment(btnClose, 0, SWT.RIGHT);
		fd_lblToChangeCourt.top = new FormAttachment(0, 10);
		fd_lblToChangeCourt.left = new FormAttachment(0, 10);
		lblToChangeCourt.setLayoutData(fd_lblToChangeCourt);
		lblToChangeCourt
				.setText("To Change court , select desired court from combo and press/click Chnage button.");

		Label label = new Label(shlChangeCourt, SWT.SEPARATOR | SWT.HORIZONTAL);
		FormData fd_label = new FormData();
		fd_label.top = new FormAttachment(btnClose, -8, SWT.TOP);
		fd_label.right = new FormAttachment(btnClose, 0, SWT.RIGHT);
		fd_label.bottom = new FormAttachment(btnClose, -6);
		fd_label.left = new FormAttachment(0, 10);
		label.setLayoutData(fd_label);

		Label label_1 = new Label(shlChangeCourt, SWT.SEPARATOR
				| SWT.HORIZONTAL);
		fd_combo.top = new FormAttachment(label_1, 5);
		fd_lblSelectCourt.top = new FormAttachment(label_1, 8);
		FormData fd_label_1 = new FormData();
		fd_label_1.left = new FormAttachment(0, 10);
		fd_label_1.right = new FormAttachment(100, -10);
		fd_label_1.top = new FormAttachment(lblToChangeCourt, 6);
		fd_label_1.bottom = new FormAttachment(lblToChangeCourt, 8, SWT.BOTTOM);
		label_1.setLayoutData(fd_label_1);
		m_bindingContext = initDataBindings();
		modelProvider.setSelectedCourt(SelectedCourt.getInstance()
				.getSelectedCourt());

	}

	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue comboObserveSelectionObserveWidget = SWTObservables
				.observeSelection(combo);
		IObservableValue modelProviderSelectedCourtObserveValue = BeansObservables
				.observeValue(modelProvider, "selectedCourt");
		bindingContext.bindValue(comboObserveSelectionObserveWidget,
				modelProviderSelectedCourtObserveValue, null, null);
		//
		IObservableList comboObserveItemsObserveListWidget = SWTObservables
				.observeItems(combo);
		IObservableList modelProviderCourtsObserveList = BeansObservables
				.observeList(Realm.getDefault(), modelProvider, "courts");
		bindingContext.bindList(comboObserveItemsObserveListWidget,
				modelProviderCourtsObserveList, null, null);
		//
		return bindingContext;
	}
}
