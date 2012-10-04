package editors;

import java.util.List;

import modelProvider.IndexJudgementEditorModelProvider;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import util.SelectedCourt;
import util.Util;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.wb.swt.ResourceManager;

public class IndexJudgementsEditor extends EditorPart {
	private DataBindingContext m_bindingContext;
	public static final String ID = "com.greenapplesolutions.jtDbTool.indexJudgements";
	private Text text;
	private IndexJudgementEditorModelProvider modelProvider;

	public IndexJudgementsEditor() {
		setTitleImage(ResourceManager.getPluginImage("com.greenapplesolutions.jtDbTool", "icons/appIcons/db_save16.png"));
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
		setSite(site);
		setInput(input);
		setPartName(input.getName());
		modelProvider = new IndexJudgementEditorModelProvider();
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
		composite.setLayout(new GridLayout(1, false));
		FormData fd_composite = new FormData();
		fd_composite.left = new FormAttachment(0, 10);
		fd_composite.right = new FormAttachment(0, 306);
		composite.setLayoutData(fd_composite);

		Label lblNewLabel = new Label(parent, SWT.NONE);
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.left = new FormAttachment(0, 10);
		fd_lblNewLabel.top = new FormAttachment(0, 10);
		lblNewLabel.setLayoutData(fd_lblNewLabel);
		lblNewLabel.setText("Select or unselect courts which u want to index");

		Label label = new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL);
		FormData fd_label = new FormData();
		fd_label.top = new FormAttachment(lblNewLabel, 6);
		fd_label.bottom = new FormAttachment(lblNewLabel, 8, SWT.BOTTOM);
		fd_label.left = new FormAttachment(0, 10);
		fd_label.right = new FormAttachment(100, -10);
		label.setLayoutData(fd_label);

		Label lblChooseADirectory = new Label(parent, SWT.NONE);
		FormData fd_lblChooseADirectory = new FormData();
		fd_lblChooseADirectory.left = new FormAttachment(0, 10);
		fd_lblChooseADirectory.top = new FormAttachment(label, 6);
		lblChooseADirectory.setLayoutData(fd_lblChooseADirectory);
		lblChooseADirectory.setText("Choose a directory");

		text = new Text(parent, SWT.BORDER | SWT.READ_ONLY);
		text.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		fd_composite.top = new FormAttachment(text, 6);
		List<String> courts = SelectedCourt.getInstance().getCourts();
		for (int ii = 0; ii < courts.size(); ii++) {
			final Button btnCheckButton = new Button(composite, SWT.CHECK);
			btnCheckButton.setText(courts.get(ii));

			btnCheckButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					modelProvider.changeCourtStatus(btnCheckButton);
				}
			});
			btnCheckButton.setSelection(true);

		}
		FormData fd_text = new FormData();
		fd_text.left = new FormAttachment(lblChooseADirectory, 6);
		fd_text.top = new FormAttachment(label, 6);
		text.setLayoutData(fd_text);

		Button btnNewButton = new Button(parent, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog dialog = new DirectoryDialog(new Shell());
				String dirPath = dialog.open();
				if (!Util.isStringNullOrEmpty(dirPath))
					modelProvider.setDirectoryPath(dirPath);
			}
		});
		fd_text.right = new FormAttachment(btnNewButton, -6);
		FormData fd_btnNewButton = new FormData();
		fd_btnNewButton.top = new FormAttachment(label, 6);
		fd_btnNewButton.right = new FormAttachment(100, -10);
		btnNewButton.setLayoutData(fd_btnNewButton);
		btnNewButton.setText("Browse");

		Button btnIndex = new Button(parent, SWT.NONE);
		btnIndex.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				modelProvider.indexJudgements();
			}
		});
		FormData fd_btnIndex = new FormData();
		fd_btnIndex.bottom = new FormAttachment(100, -10);
		fd_btnIndex.right = new FormAttachment(100, -8);
		btnIndex.setLayoutData(fd_btnIndex);
		btnIndex.setText("Index");

		Label label_1 = new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL);
		fd_composite.bottom = new FormAttachment(label_1, -6);
		FormData fd_label_1 = new FormData();
		fd_label_1.top = new FormAttachment(btnIndex, -8, SWT.TOP);
		fd_label_1.bottom = new FormAttachment(btnIndex, -6);
		fd_label_1.right = new FormAttachment(100, -10);
		fd_label_1.left = new FormAttachment(0, 10);
		label_1.setLayoutData(fd_label_1);
		m_bindingContext = initDataBindings();
		// TODO Auto-generated method stub

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextTextObserveWidget = WidgetProperties.text(
				SWT.Modify).observe(text);
		IObservableValue directoryPathModelProviderObserveValue = BeanProperties
				.value("directoryPath").observe(modelProvider);
		bindingContext.bindValue(observeTextTextObserveWidget,
				directoryPathModelProviderObserveValue, null, null);
		//
		return bindingContext;
	}
}
