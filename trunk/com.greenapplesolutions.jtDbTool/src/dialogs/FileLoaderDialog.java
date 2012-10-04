package dialogs;

import java.util.List;

import modelProvider.FileLoaderModelProvider;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.greenapplesolutions.dbloader.domain.Judgement;

import util.Util;

public class FileLoaderDialog extends Dialog {
	private DataBindingContext m_bindingContext;

	protected Object result;
	protected Shell shlFileLoader;
	private Text text;
	private FileLoaderModelProvider modelProvider;
	private Combo combo;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public FileLoaderDialog(Shell parent) {
		super(parent);
		modelProvider = new FileLoaderModelProvider();
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlFileLoader.open();
		shlFileLoader.layout();
		Display display = getParent().getDisplay();
		while (!shlFileLoader.isDisposed()) {
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
		shlFileLoader = new Shell(getParent());
		shlFileLoader.setSize(450, 300);
		shlFileLoader.setText("File Loader");
		shlFileLoader.setLayout(new FormLayout());

		Label lblSelectCourt = new Label(shlFileLoader, SWT.NONE);
		FormData fd_lblSelectCourt = new FormData();
		fd_lblSelectCourt.top = new FormAttachment(0, 10);
		fd_lblSelectCourt.left = new FormAttachment(0, 10);
		lblSelectCourt.setLayoutData(fd_lblSelectCourt);
		lblSelectCourt.setText("Select Court");

		combo = new Combo(shlFileLoader, SWT.READ_ONLY);
		FormData fd_combo = new FormData();
		fd_combo.right = new FormAttachment(100, -10);
		fd_combo.left = new FormAttachment(lblSelectCourt, 6);
		fd_combo.top = new FormAttachment(lblSelectCourt, -3, SWT.TOP);
		combo.setLayoutData(fd_combo);

		Label lblSelectFile = new Label(shlFileLoader, SWT.NONE);
		FormData fd_lblSelectFile = new FormData();
		fd_lblSelectFile.top = new FormAttachment(lblSelectCourt, 22);
		fd_lblSelectFile.left = new FormAttachment(lblSelectCourt, 0, SWT.LEFT);
		lblSelectFile.setLayoutData(fd_lblSelectFile);
		lblSelectFile.setText("Select File");

		text = new Text(shlFileLoader, SWT.BORDER);
		FormData fd_text = new FormData();
		fd_text.top = new FormAttachment(lblSelectFile, -3, SWT.TOP);
		fd_text.left = new FormAttachment(lblSelectFile, 6);
		text.setLayoutData(fd_text);

		Button btnNewButton = new Button(shlFileLoader, SWT.NONE);
		fd_text.right = new FormAttachment(btnNewButton, -6);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog dialog = new FileDialog(shlFileLoader);
				String path = dialog.open();
				if (!Util.isStringNullOrEmpty(path))
					modelProvider.setFilePath(path);
			}
		});
		FormData fd_btnNewButton = new FormData();
		fd_btnNewButton.top = new FormAttachment(lblSelectFile, -5, SWT.TOP);
		fd_btnNewButton.right = new FormAttachment(combo, 0, SWT.RIGHT);
		btnNewButton.setLayoutData(fd_btnNewButton);
		btnNewButton.setText("Browse");

		Button btnLoad = new Button(shlFileLoader, SWT.NONE);
		btnLoad.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				modelProvider.load();
				if (modelProvider.getIsDataInserted()
						&& modelProvider.getIsFileProcessed()
						&& modelProvider.getIsResultsDisplayed())
					shlFileLoader.close();
				else if (!modelProvider.getIsFileProcessed())
					MessageDialog.openInformation(new Shell(), "Error",
							"Error in Reading File");
				else if (!modelProvider.getIsDataInserted())
					MessageDialog.openInformation(new Shell(), "Error",
							"Error in Inserting Judgements");
				else if (!modelProvider.getIsResultsDisplayed())
					MessageDialog.openInformation(new Shell(), "Error",
							"Error in Displaying Results.");
			}
		});
		FormData fd_btnLoad = new FormData();
		fd_btnLoad.right = new FormAttachment(100, -10);
		fd_btnLoad.bottom = new FormAttachment(100, -10);
		btnLoad.setLayoutData(fd_btnLoad);
		btnLoad.setText("Load");

		Label label_1 = new Label(shlFileLoader, SWT.SEPARATOR | SWT.HORIZONTAL);
		FormData fd_label_1 = new FormData();
		fd_label_1.left = new FormAttachment(0, 10);
		fd_label_1.right = new FormAttachment(100, -10);
		fd_label_1.top = new FormAttachment(btnLoad, -8, SWT.TOP);
		fd_label_1.bottom = new FormAttachment(btnLoad, -6);
		label_1.setLayoutData(fd_label_1);
		m_bindingContext = initDataBindings();

	}

	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableList itemsComboObserveWidget = WidgetProperties.items()
				.observe(combo);
		IObservableList courtsModelProviderObserveList = BeanProperties.list(
				"courts").observe(modelProvider);
		bindingContext.bindList(itemsComboObserveWidget,
				courtsModelProviderObserveList, null, null);
		//
		IObservableValue observeTextTextObserveWidget = WidgetProperties.text(
				SWT.Modify).observe(text);
		IObservableValue filePathModelProviderObserveValue = BeanProperties
				.value("filePath").observe(modelProvider);
		bindingContext.bindValue(observeTextTextObserveWidget,
				filePathModelProviderObserveValue, null, null);
		//
		IObservableValue observeSelectionComboObserveWidget = WidgetProperties
				.selection().observe(combo);
		IObservableValue selectedCourtModelProviderObserveValue = BeanProperties
				.value("selectedCourt").observe(modelProvider);
		bindingContext.bindValue(observeSelectionComboObserveWidget,
				selectedCourtModelProviderObserveValue, null, null);
		//
		return bindingContext;
	}
}
