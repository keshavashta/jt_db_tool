package dialogs;

import java.util.List;

import modelProvider.IndexProgressbarDialogModelProvider;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Text;

import com.greenapplesolutions.dbloader.domain.Judgement;
import com.greenapplesolutions.lawsearch.desktopapp.customEvents.PropertyChangeEvent;
import com.greenapplesolutions.lawsearch.desktopapp.customEvents.PropertyChangeListener;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.wb.swt.SWTResourceManager;

public class IndexProgressBarDialog extends Dialog {
	private DataBindingContext m_bindingContext;

	protected Object result;
	protected Shell shlIndexingJudgements;
	private Text text;
	private ProgressBar progressBar;
	private List<String> courts;
	private IndexProgressbarDialogModelProvider modelProvider;
	private Label lblNewLabel;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public IndexProgressBarDialog(Shell parent, List<String> courts,
			String filePath) {
		super(parent);
		setText("SWT Dialog");
		this.courts = courts;
		modelProvider = new IndexProgressbarDialogModelProvider(courts,
				filePath);
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlIndexingJudgements.open();
		shlIndexingJudgements.layout();
		Display display = getParent().getDisplay();
		while (!shlIndexingJudgements.isDisposed()) {
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
		shlIndexingJudgements = new Shell(getParent(),SWT.DIALOG_TRIM
				| SWT.APPLICATION_MODAL);
		shlIndexingJudgements.setSize(450, 300);
		shlIndexingJudgements.setText("Indexing Judgements");
		shlIndexingJudgements.setLayout(new FormLayout());

		lblNewLabel = new Label(shlIndexingJudgements, SWT.NONE);
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.right = new FormAttachment(0, 434);
		fd_lblNewLabel.top = new FormAttachment(0, 10);
		fd_lblNewLabel.left = new FormAttachment(0, 10);
		lblNewLabel.setLayoutData(fd_lblNewLabel);

		progressBar = new ProgressBar(shlIndexingJudgements, SWT.NONE);
		FormData fd_progressBar = new FormData();
		fd_progressBar.top = new FormAttachment(lblNewLabel, 3);
		fd_progressBar.left = new FormAttachment(lblNewLabel, 0, SWT.LEFT);
		fd_progressBar.right = new FormAttachment(0, 434);
		progressBar.setLayoutData(fd_progressBar);
		progressBar.setMaximum(courts.size());
		progressBar.setMinimum(0);
		text = new Text(shlIndexingJudgements, SWT.BORDER | SWT.READ_ONLY
				| SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		text.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		FormData fd_text = new FormData();
		fd_text.right = new FormAttachment(lblNewLabel, 0, SWT.RIGHT);
		fd_text.bottom = new FormAttachment(progressBar, 216, SWT.BOTTOM);
		fd_text.top = new FormAttachment(progressBar, 6);
		fd_text.left = new FormAttachment(lblNewLabel, 0, SWT.LEFT);
		text.setLayoutData(fd_text);
		modelProvider.addCustomEventListener(new PropertyChangeListener() {

			@Override
			public void propertyChanged(final PropertyChangeEvent evt) {
				Display.getDefault().asyncExec(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						progressBar.setSelection(evt.getProgressValue());

					}
				});

			}
		});
		m_bindingContext = initDataBindings();

	}

	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextLblNewLabelObserveWidget = WidgetProperties
				.text().observe(lblNewLabel);
		IObservableValue indexLabelMessageModelProviderObserveValue = BeanProperties
				.value("indexLabelMessage").observe(modelProvider);
		bindingContext.bindValue(observeTextLblNewLabelObserveWidget,
				indexLabelMessageModelProviderObserveValue, null, null);
		//
		IObservableValue observeTextTextObserveWidget = WidgetProperties.text(
				SWT.Modify).observe(text);
		IObservableValue logMessageModelProviderObserveValue = BeanProperties
				.value("logMessage").observe(modelProvider);
		bindingContext.bindValue(observeTextTextObserveWidget,
				logMessageModelProviderObserveValue, null, null);
		//
		return bindingContext;
	}
}
