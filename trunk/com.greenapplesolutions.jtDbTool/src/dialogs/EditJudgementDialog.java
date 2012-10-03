package dialogs;

import java.util.Date;

import modelProvider.EditJudgementdialogModelProvider;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.wb.swt.ResourceManager;

public class EditJudgementDialog extends Dialog {
	private DataBindingContext m_bindingContext;

	protected Object result;
	protected Shell shlVieweditJudgement;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private Text text_6;
	private Text text_7;
	private Text text_8;
	private Text text_9;
	private Text text_10;
	private Text text_11;
	private Text text_12;
	private Text text_13;
	private Text text_14;
	private Text text_15;
	private Text text_16;
	private Text text_17;
	private Text text_18;
	private Text text_19;
	private Text text_20;
	private Text text_21;
	private EditJudgementdialogModelProvider modelProvider;
	private Combo combo;
	private Combo combo_1;
	private Combo combo_2;
	private Combo combo_3;
	private Combo combo_4;
	private Combo combo_5;
	private DateTime dateTime;
	private Text text_22;
	private Text text_23;
	private Text text_24;
	private Text text_25;
	private Text text_26;
	private Text text_27;
	private Text text_28;
	private Text text_29;
	private Text text_30;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public EditJudgementDialog(Shell parent, String keycode) {
		super(parent);
		modelProvider = new EditJudgementdialogModelProvider(keycode);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlVieweditJudgement.open();
		shlVieweditJudgement.layout();
		Display display = getParent().getDisplay();
		while (!shlVieweditJudgement.isDisposed()) {
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
		shlVieweditJudgement = new Shell(getParent(), SWT.DIALOG_TRIM
				| SWT.APPLICATION_MODAL);
		shlVieweditJudgement.setImage(ResourceManager.getPluginImage(
				"com.greenapplesolutions.jtDbTool",
				"icons/appIcons/edit_txt32x32.png"));
		shlVieweditJudgement.setSize(1024, 670);
		shlVieweditJudgement.setText("View/Edit Judgement");
		shlVieweditJudgement.setLayout(new FormLayout());

		TabFolder tabFolder = new TabFolder(shlVieweditJudgement, SWT.NONE);
		FormData fd_tabFolder = new FormData();
		fd_tabFolder.top = new FormAttachment(0, 10);
		fd_tabFolder.left = new FormAttachment(0, 10);
		fd_tabFolder.bottom = new FormAttachment(0, 599);
		fd_tabFolder.right = new FormAttachment(0, 1008);
		tabFolder.setLayoutData(fd_tabFolder);

		TabItem tbtmJudgement = new TabItem(tabFolder, SWT.NONE);
		tbtmJudgement.setText("Judgement");

		Composite composite = new Composite(tabFolder, SWT.NONE);
		tbtmJudgement.setControl(composite);
		composite.setLayout(new FormLayout());

		Label lblNewLabel = new Label(composite, SWT.NONE);
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.top = new FormAttachment(0, 10);
		fd_lblNewLabel.left = new FormAttachment(0, 10);
		lblNewLabel.setLayoutData(fd_lblNewLabel);
		lblNewLabel.setText("Appellant");

		text = new Text(composite, SWT.BORDER);
		FormData fd_text = new FormData();
		fd_text.right = new FormAttachment(100, -10);
		fd_text.top = new FormAttachment(lblNewLabel, 6);
		fd_text.left = new FormAttachment(0, 10);
		text.setLayoutData(fd_text);

		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		FormData fd_lblNewLabel_1 = new FormData();
		fd_lblNewLabel_1.top = new FormAttachment(text, 8);
		fd_lblNewLabel_1.left = new FormAttachment(lblNewLabel, 0, SWT.LEFT);
		lblNewLabel_1.setLayoutData(fd_lblNewLabel_1);
		lblNewLabel_1.setText("Respondant");

		text_1 = new Text(composite, SWT.BORDER);
		FormData fd_text_1 = new FormData();
		fd_text_1.right = new FormAttachment(100, -10);
		fd_text_1.top = new FormAttachment(lblNewLabel_1, 6);
		fd_text_1.left = new FormAttachment(0, 10);
		text_1.setLayoutData(fd_text_1);

		Label lblNewLabel_2 = new Label(composite, SWT.NONE);
		FormData fd_lblNewLabel_2 = new FormData();
		fd_lblNewLabel_2.top = new FormAttachment(text_1, 13);
		fd_lblNewLabel_2.left = new FormAttachment(lblNewLabel, 0, SWT.LEFT);
		lblNewLabel_2.setLayoutData(fd_lblNewLabel_2);
		lblNewLabel_2.setText("Judges");

		text_2 = new Text(composite, SWT.BORDER);
		FormData fd_text_2 = new FormData();
		fd_text_2.right = new FormAttachment(100, -10);
		fd_text_2.left = new FormAttachment(0, 10);
		text_2.setLayoutData(fd_text_2);

		Label lblDate = new Label(composite, SWT.NONE);
		fd_text_2.bottom = new FormAttachment(lblDate, -7);
		FormData fd_lblDate = new FormData();
		fd_lblDate.top = new FormAttachment(0, 168);
		fd_lblDate.left = new FormAttachment(lblNewLabel, 0, SWT.LEFT);
		lblDate.setLayoutData(fd_lblDate);
		lblDate.setText("Date");

		dateTime = new DateTime(composite, SWT.BORDER);
		FormData fd_dateTime = new FormData();
		fd_dateTime.top = new FormAttachment(lblDate, 6);
		fd_dateTime.left = new FormAttachment(lblNewLabel, 0, SWT.LEFT);
		dateTime.setLayoutData(fd_dateTime);

		Label lblAdvocates = new Label(composite, SWT.NONE);
		FormData fd_lblAdvocates = new FormData();
		fd_lblAdvocates.top = new FormAttachment(dateTime, 6);
		fd_lblAdvocates.left = new FormAttachment(lblNewLabel, 0, SWT.LEFT);
		lblAdvocates.setLayoutData(fd_lblAdvocates);
		lblAdvocates.setText("Advocates");

		text_3 = new Text(composite, SWT.BORDER);
		FormData fd_text_3 = new FormData();
		fd_text_3.right = new FormAttachment(text, 0, SWT.RIGHT);
		fd_text_3.top = new FormAttachment(lblAdvocates, 6);
		fd_text_3.left = new FormAttachment(lblNewLabel, 0, SWT.LEFT);
		text_3.setLayoutData(fd_text_3);

		Label lblJudgement = new Label(composite, SWT.NONE);
		FormData fd_lblJudgement = new FormData();
		fd_lblJudgement.top = new FormAttachment(text_3, 6);
		fd_lblJudgement.left = new FormAttachment(lblNewLabel, 0, SWT.LEFT);
		lblJudgement.setLayoutData(fd_lblJudgement);
		lblJudgement.setText("Judgement");

		text_22 = new Text(composite, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL
				| SWT.MULTI);
		FormData fd_text_22 = new FormData();
		fd_text_22.right = new FormAttachment(text, 0, SWT.RIGHT);
		fd_text_22.bottom = new FormAttachment(lblJudgement, 269, SWT.BOTTOM);
		fd_text_22.top = new FormAttachment(lblJudgement, 6);
		fd_text_22.left = new FormAttachment(0, 10);
		text_22.setLayoutData(fd_text_22);

		TabItem tbtmCitation = new TabItem(tabFolder, SWT.NONE);
		tbtmCitation.setText("Citation");

		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		tbtmCitation.setControl(composite_1);

		Group grpCitation = new Group(composite_1, SWT.NONE);
		grpCitation.setText("Citation 1");
		grpCitation.setBounds(10, 10, 970, 82);

		Label lblJournal = new Label(grpCitation, SWT.NONE);
		lblJournal.setBounds(10, 20, 55, 15);
		lblJournal.setText("Journal");

		combo = new Combo(grpCitation, SWT.READ_ONLY);
		combo.setBounds(10, 49, 91, 23);

		Label lblNewLabel_3 = new Label(grpCitation, SWT.NONE);
		lblNewLabel_3.setBounds(118, 20, 55, 15);
		lblNewLabel_3.setText("Year");

		text_4 = new Text(grpCitation, SWT.BORDER);
		text_4.setBounds(107, 49, 76, 21);

		Label lblVolume = new Label(grpCitation, SWT.NONE);
		lblVolume.setBounds(206, 20, 55, 15);
		lblVolume.setText("Volume");

		text_5 = new Text(grpCitation, SWT.BORDER);
		text_5.setBounds(193, 49, 76, 21);

		Label lblPage = new Label(grpCitation, SWT.NONE);
		lblPage.setBounds(295, 20, 55, 15);
		lblPage.setText("Page");

		text_6 = new Text(grpCitation, SWT.BORDER);
		text_6.setBounds(284, 49, 76, 21);

		Group grpCitation_1 = new Group(composite_1, SWT.NONE);
		grpCitation_1.setText("Citation 2");
		grpCitation_1.setBounds(10, 98, 970, 82);

		Label label = new Label(grpCitation_1, SWT.NONE);
		label.setText("Journal");
		label.setBounds(10, 20, 55, 15);

		combo_1 = new Combo(grpCitation_1, SWT.READ_ONLY);
		combo_1.setBounds(10, 49, 91, 23);

		Label label_1 = new Label(grpCitation_1, SWT.NONE);
		label_1.setText("Year");
		label_1.setBounds(118, 20, 55, 15);

		text_7 = new Text(grpCitation_1, SWT.BORDER);
		text_7.setBounds(107, 49, 76, 21);

		Label label_2 = new Label(grpCitation_1, SWT.NONE);
		label_2.setText("Volume");
		label_2.setBounds(206, 20, 55, 15);

		text_8 = new Text(grpCitation_1, SWT.BORDER);
		text_8.setBounds(193, 49, 76, 21);

		Label label_3 = new Label(grpCitation_1, SWT.NONE);
		label_3.setText("Page");
		label_3.setBounds(295, 20, 55, 15);

		text_9 = new Text(grpCitation_1, SWT.BORDER);
		text_9.setBounds(284, 49, 76, 21);

		Group grpCitation_2 = new Group(composite_1, SWT.NONE);
		grpCitation_2.setText("Citation 3");
		grpCitation_2.setBounds(10, 188, 970, 82);

		Label label_4 = new Label(grpCitation_2, SWT.NONE);
		label_4.setText("Journal");
		label_4.setBounds(10, 20, 55, 15);

		combo_2 = new Combo(grpCitation_2, SWT.READ_ONLY);
		combo_2.setBounds(10, 49, 91, 23);

		Label label_5 = new Label(grpCitation_2, SWT.NONE);
		label_5.setText("Year");
		label_5.setBounds(118, 20, 55, 15);

		text_10 = new Text(grpCitation_2, SWT.BORDER);
		text_10.setBounds(107, 49, 76, 21);

		Label label_6 = new Label(grpCitation_2, SWT.NONE);
		label_6.setText("Volume");
		label_6.setBounds(206, 20, 55, 15);

		text_11 = new Text(grpCitation_2, SWT.BORDER);
		text_11.setBounds(193, 49, 76, 21);

		Label label_7 = new Label(grpCitation_2, SWT.NONE);
		label_7.setText("Page");
		label_7.setBounds(295, 20, 55, 15);

		text_12 = new Text(grpCitation_2, SWT.BORDER);
		text_12.setBounds(284, 49, 76, 21);

		Group grpCitation_3 = new Group(composite_1, SWT.NONE);
		grpCitation_3.setText("Citation 4");
		grpCitation_3.setBounds(10, 280, 970, 82);

		Label label_8 = new Label(grpCitation_3, SWT.NONE);
		label_8.setText("Journal");
		label_8.setBounds(10, 20, 55, 15);

		combo_3 = new Combo(grpCitation_3, SWT.READ_ONLY);
		combo_3.setBounds(10, 49, 91, 23);

		Label label_9 = new Label(grpCitation_3, SWT.NONE);
		label_9.setText("Year");
		label_9.setBounds(118, 20, 55, 15);

		text_13 = new Text(grpCitation_3, SWT.BORDER);
		text_13.setBounds(107, 49, 76, 21);

		Label label_10 = new Label(grpCitation_3, SWT.NONE);
		label_10.setText("Volume");
		label_10.setBounds(206, 20, 55, 15);

		text_14 = new Text(grpCitation_3, SWT.BORDER);
		text_14.setBounds(193, 49, 76, 21);

		Label label_11 = new Label(grpCitation_3, SWT.NONE);
		label_11.setText("Page");
		label_11.setBounds(295, 20, 55, 15);

		text_15 = new Text(grpCitation_3, SWT.BORDER);
		text_15.setBounds(284, 49, 76, 21);

		Group grpCitation_4 = new Group(composite_1, SWT.NONE);
		grpCitation_4.setText("Citation 5");
		grpCitation_4.setBounds(10, 368, 970, 82);

		Label label_12 = new Label(grpCitation_4, SWT.NONE);
		label_12.setText("Journal");
		label_12.setBounds(10, 20, 55, 15);

		combo_4 = new Combo(grpCitation_4, SWT.READ_ONLY);
		combo_4.setBounds(10, 49, 91, 23);

		Label label_13 = new Label(grpCitation_4, SWT.NONE);
		label_13.setText("Year");
		label_13.setBounds(118, 20, 55, 15);

		text_16 = new Text(grpCitation_4, SWT.BORDER);
		text_16.setBounds(107, 49, 76, 21);

		Label label_14 = new Label(grpCitation_4, SWT.NONE);
		label_14.setText("Volume");
		label_14.setBounds(206, 20, 55, 15);

		text_17 = new Text(grpCitation_4, SWT.BORDER);
		text_17.setBounds(193, 49, 76, 21);

		Label label_15 = new Label(grpCitation_4, SWT.NONE);
		label_15.setText("Page");
		label_15.setBounds(295, 20, 55, 15);

		text_18 = new Text(grpCitation_4, SWT.BORDER);
		text_18.setBounds(284, 49, 76, 21);

		Group grpCitation_5 = new Group(composite_1, SWT.NONE);
		grpCitation_5.setText("Citation 6");
		grpCitation_5.setBounds(10, 456, 970, 82);

		Label label_16 = new Label(grpCitation_5, SWT.NONE);
		label_16.setText("Journal");
		label_16.setBounds(10, 20, 55, 15);

		combo_5 = new Combo(grpCitation_5, SWT.READ_ONLY);
		combo_5.setBounds(10, 49, 91, 23);

		Label label_17 = new Label(grpCitation_5, SWT.NONE);
		label_17.setText("Year");
		label_17.setBounds(118, 20, 55, 15);

		text_19 = new Text(grpCitation_5, SWT.BORDER);
		text_19.setBounds(107, 49, 76, 21);

		Label label_18 = new Label(grpCitation_5, SWT.NONE);
		label_18.setText("Volume");
		label_18.setBounds(206, 20, 55, 15);

		text_20 = new Text(grpCitation_5, SWT.BORDER);
		text_20.setBounds(193, 49, 76, 21);

		Label label_19 = new Label(grpCitation_5, SWT.NONE);
		label_19.setText("Page");
		label_19.setBounds(295, 20, 55, 15);

		text_21 = new Text(grpCitation_5, SWT.BORDER);
		text_21.setBounds(284, 49, 76, 21);

		TabItem tbtmHeadnote = new TabItem(tabFolder, SWT.NONE);
		tbtmHeadnote.setText("Headnote");

		Composite composite_2 = new Composite(tabFolder, SWT.NONE);
		tbtmHeadnote.setControl(composite_2);

		Group grpHeadnoteAndHeld = new Group(composite_2, SWT.NONE);
		grpHeadnoteAndHeld.setText("Headnote And Held Number 1");
		grpHeadnoteAndHeld.setBounds(10, 10, 970, 132);

		Label lblHeld = new Label(grpHeadnoteAndHeld, SWT.NONE);
		lblHeld.setBounds(10, 21, 55, 15);
		lblHeld.setText("Held");

		Label label_20 = new Label(grpHeadnoteAndHeld, SWT.SEPARATOR
				| SWT.VERTICAL);
		label_20.setBounds(434, 21, 2, 101);

		Label lblHeadnote = new Label(grpHeadnoteAndHeld, SWT.NONE);
		lblHeadnote.setBounds(442, 21, 55, 15);
		lblHeadnote.setText("Headnote");

		text_23 = new Text(grpHeadnoteAndHeld, SWT.BORDER | SWT.WRAP
				| SWT.V_SCROLL | SWT.MULTI);
		text_23.setBounds(10, 42, 418, 80);

		text_24 = new Text(grpHeadnoteAndHeld, SWT.BORDER | SWT.WRAP
				| SWT.V_SCROLL | SWT.MULTI);
		text_24.setBounds(442, 42, 518, 80);

		Group group = new Group(composite_2, SWT.NONE);
		group.setText("Headnote And Held Number 1");
		group.setBounds(10, 148, 970, 132);

		Label label_21 = new Label(group, SWT.NONE);
		label_21.setText("Held");
		label_21.setBounds(10, 21, 55, 15);

		Label label_22 = new Label(group, SWT.SEPARATOR);
		label_22.setBounds(434, 21, 2, 101);

		Label label_23 = new Label(group, SWT.NONE);
		label_23.setText("Headnote");
		label_23.setBounds(442, 21, 55, 15);

		text_25 = new Text(group, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL
				| SWT.MULTI);
		text_25.setBounds(10, 42, 418, 80);

		text_26 = new Text(group, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL
				| SWT.MULTI);
		text_26.setBounds(442, 42, 518, 80);

		Group group_1 = new Group(composite_2, SWT.NONE);
		group_1.setText("Headnote And Held Number 1");
		group_1.setBounds(10, 289, 970, 132);

		Label label_24 = new Label(group_1, SWT.NONE);
		label_24.setText("Held");
		label_24.setBounds(10, 21, 55, 15);

		Label label_25 = new Label(group_1, SWT.SEPARATOR);
		label_25.setBounds(434, 21, 2, 101);

		Label label_26 = new Label(group_1, SWT.NONE);
		label_26.setText("Headnote");
		label_26.setBounds(442, 21, 55, 15);

		text_27 = new Text(group_1, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL
				| SWT.MULTI);
		text_27.setBounds(10, 42, 418, 80);

		text_28 = new Text(group_1, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL
				| SWT.MULTI);
		text_28.setBounds(442, 42, 518, 80);

		Group group_2 = new Group(composite_2, SWT.NONE);
		group_2.setText("Headnote And Held Number 1");
		group_2.setBounds(10, 427, 970, 132);

		Label label_27 = new Label(group_2, SWT.NONE);
		label_27.setText("Held");
		label_27.setBounds(10, 21, 55, 15);

		Label label_28 = new Label(group_2, SWT.SEPARATOR);
		label_28.setBounds(434, 21, 2, 101);

		Label label_29 = new Label(group_2, SWT.NONE);
		label_29.setText("Headnote");
		label_29.setBounds(442, 21, 55, 15);

		text_29 = new Text(group_2, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL
				| SWT.MULTI);
		text_29.setBounds(10, 42, 418, 80);

		text_30 = new Text(group_2, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL
				| SWT.MULTI);
		text_30.setBounds(442, 42, 518, 80);

		Button btnClose = new Button(shlVieweditJudgement, SWT.NONE);
		btnClose.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlVieweditJudgement.close();

			}
		});
		FormData fd_btnClose = new FormData();
		fd_btnClose.top = new FormAttachment(tabFolder, 8);
		fd_btnClose.right = new FormAttachment(100, -10);
		btnClose.setLayoutData(fd_btnClose);
		btnClose.setText("Close");

		Button btnUpdate = new Button(shlVieweditJudgement, SWT.NONE);
		btnUpdate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (modelProvider.updateJudgement())
					shlVieweditJudgement.close();
				else
					MessageDialog.openError(new Shell(), "Error",
							"Error in connecting with database.");
			}
		});
		FormData fd_btnUpdate = new FormData();
		fd_btnUpdate.bottom = new FormAttachment(btnClose, 0, SWT.BOTTOM);
		fd_btnUpdate.right = new FormAttachment(btnClose, -6);
		btnUpdate.setLayoutData(fd_btnUpdate);
		btnUpdate.setText("Update");
		m_bindingContext = initDataBindings();

	}

	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableList itemsComboObserveWidget = WidgetProperties.items()
				.observe(combo);
		IObservableList journalsModelProviderObserveList = BeanProperties.list(
				"journals").observe(modelProvider);
		bindingContext.bindList(itemsComboObserveWidget,
				journalsModelProviderObserveList, null, null);
		//
		IObservableList itemsCombo_1ObserveWidget = WidgetProperties.items()
				.observe(combo_1);
		bindingContext.bindList(itemsCombo_1ObserveWidget,
				journalsModelProviderObserveList, null, null);
		//
		IObservableList itemsCombo_2ObserveWidget = WidgetProperties.items()
				.observe(combo_2);
		bindingContext.bindList(itemsCombo_2ObserveWidget,
				journalsModelProviderObserveList, null, null);
		//
		IObservableList itemsCombo_3ObserveWidget = WidgetProperties.items()
				.observe(combo_3);
		bindingContext.bindList(itemsCombo_3ObserveWidget,
				journalsModelProviderObserveList, null, null);
		//
		IObservableList itemsCombo_4ObserveWidget = WidgetProperties.items()
				.observe(combo_4);
		bindingContext.bindList(itemsCombo_4ObserveWidget,
				journalsModelProviderObserveList, null, null);
		//
		IObservableList itemsCombo_5ObserveWidget = WidgetProperties.items()
				.observe(combo_5);
		bindingContext.bindList(itemsCombo_5ObserveWidget,
				journalsModelProviderObserveList, null, null);
		//
		IObservableValue observeTextText_4ObserveWidget = WidgetProperties
				.text(SWT.Modify).observe(text_4);
		IObservableValue citation1_yearModelProviderObserveValue = BeanProperties
				.value("citation1_year").observe(modelProvider);
		bindingContext.bindValue(observeTextText_4ObserveWidget,
				citation1_yearModelProviderObserveValue, null, null);
		//
		IObservableValue observeTextText_5ObserveWidget = WidgetProperties
				.text(SWT.Modify).observe(text_5);
		IObservableValue citation1_volumeModelProviderObserveValue = BeanProperties
				.value("citation1_volume").observe(modelProvider);
		bindingContext.bindValue(observeTextText_5ObserveWidget,
				citation1_volumeModelProviderObserveValue, null, null);
		//
		IObservableValue observeTextText_6ObserveWidget = WidgetProperties
				.text(SWT.Modify).observe(text_6);
		IObservableValue citation1_pageModelProviderObserveValue = BeanProperties
				.value("citation1_page").observe(modelProvider);
		bindingContext.bindValue(observeTextText_6ObserveWidget,
				citation1_pageModelProviderObserveValue, null, null);
		//
		IObservableValue observeTextText_7ObserveWidget = WidgetProperties
				.text(SWT.Modify).observe(text_7);
		IObservableValue citation2_yearModelProviderObserveValue = BeanProperties
				.value("citation2_year").observe(modelProvider);
		bindingContext.bindValue(observeTextText_7ObserveWidget,
				citation2_yearModelProviderObserveValue, null, null);
		//
		IObservableValue observeTextText_8ObserveWidget = WidgetProperties
				.text(SWT.Modify).observe(text_8);
		IObservableValue citation2_volumeModelProviderObserveValue = BeanProperties
				.value("citation2_volume").observe(modelProvider);
		bindingContext.bindValue(observeTextText_8ObserveWidget,
				citation2_volumeModelProviderObserveValue, null, null);
		//
		IObservableValue observeTextText_9ObserveWidget = WidgetProperties
				.text(SWT.Modify).observe(text_9);
		IObservableValue citation2_pageModelProviderObserveValue = BeanProperties
				.value("citation2_page").observe(modelProvider);
		bindingContext.bindValue(observeTextText_9ObserveWidget,
				citation2_pageModelProviderObserveValue, null, null);
		//
		IObservableValue observeTextText_10ObserveWidget = WidgetProperties
				.text(SWT.Modify).observe(text_10);
		IObservableValue citation3_yearModelProviderObserveValue = BeanProperties
				.value("citation3_year").observe(modelProvider);
		bindingContext.bindValue(observeTextText_10ObserveWidget,
				citation3_yearModelProviderObserveValue, null, null);
		//
		IObservableValue observeTextText_11ObserveWidget = WidgetProperties
				.text(SWT.Modify).observe(text_11);
		IObservableValue citation3_volumeModelProviderObserveValue = BeanProperties
				.value("citation3_volume").observe(modelProvider);
		bindingContext.bindValue(observeTextText_11ObserveWidget,
				citation3_volumeModelProviderObserveValue, null, null);
		//
		IObservableValue observeTextText_12ObserveWidget = WidgetProperties
				.text(SWT.Modify).observe(text_12);
		IObservableValue citation3_pageModelProviderObserveValue = BeanProperties
				.value("citation3_page").observe(modelProvider);
		bindingContext.bindValue(observeTextText_12ObserveWidget,
				citation3_pageModelProviderObserveValue, null, null);
		//
		IObservableValue observeTextText_13ObserveWidget = WidgetProperties
				.text(SWT.Modify).observe(text_13);
		IObservableValue citation4_yearModelProviderObserveValue = BeanProperties
				.value("citation4_year").observe(modelProvider);
		bindingContext.bindValue(observeTextText_13ObserveWidget,
				citation4_yearModelProviderObserveValue, null, null);
		//
		IObservableValue observeTextText_14ObserveWidget = WidgetProperties
				.text(SWT.Modify).observe(text_14);
		IObservableValue citation4_volumeModelProviderObserveValue = BeanProperties
				.value("citation4_volume").observe(modelProvider);
		bindingContext.bindValue(observeTextText_14ObserveWidget,
				citation4_volumeModelProviderObserveValue, null, null);
		//
		IObservableValue observeTextText_15ObserveWidget = WidgetProperties
				.text(SWT.Modify).observe(text_15);
		IObservableValue citation4_pageModelProviderObserveValue = BeanProperties
				.value("citation4_page").observe(modelProvider);
		bindingContext.bindValue(observeTextText_15ObserveWidget,
				citation4_pageModelProviderObserveValue, null, null);
		//
		IObservableValue observeTextText_16ObserveWidget = WidgetProperties
				.text(SWT.Modify).observe(text_16);
		IObservableValue citation5_yearModelProviderObserveValue = BeanProperties
				.value("citation5_year").observe(modelProvider);
		bindingContext.bindValue(observeTextText_16ObserveWidget,
				citation5_yearModelProviderObserveValue, null, null);
		//
		IObservableValue observeTextText_17ObserveWidget = WidgetProperties
				.text(SWT.Modify).observe(text_17);
		IObservableValue citation5_volumeModelProviderObserveValue = BeanProperties
				.value("citation5_volume").observe(modelProvider);
		bindingContext.bindValue(observeTextText_17ObserveWidget,
				citation5_volumeModelProviderObserveValue, null, null);
		//
		IObservableValue observeTextText_18ObserveWidget = WidgetProperties
				.text(SWT.Modify).observe(text_18);
		IObservableValue citation5_pageModelProviderObserveValue = BeanProperties
				.value("citation5_page").observe(modelProvider);
		bindingContext.bindValue(observeTextText_18ObserveWidget,
				citation5_pageModelProviderObserveValue, null, null);
		//
		IObservableValue observeTextText_19ObserveWidget = WidgetProperties
				.text(SWT.Modify).observe(text_19);
		IObservableValue citation6_yearModelProviderObserveValue = BeanProperties
				.value("citation6_year").observe(modelProvider);
		bindingContext.bindValue(observeTextText_19ObserveWidget,
				citation6_yearModelProviderObserveValue, null, null);
		//
		IObservableValue observeTextText_20ObserveWidget = WidgetProperties
				.text(SWT.Modify).observe(text_20);
		IObservableValue citation6_volumeModelProviderObserveValue = BeanProperties
				.value("citation6_volume").observe(modelProvider);
		bindingContext.bindValue(observeTextText_20ObserveWidget,
				citation6_volumeModelProviderObserveValue, null, null);
		//
		IObservableValue observeTextText_21ObserveWidget = WidgetProperties
				.text(SWT.Modify).observe(text_21);
		IObservableValue citation6_pageModelProviderObserveValue = BeanProperties
				.value("citation6_page").observe(modelProvider);
		bindingContext.bindValue(observeTextText_21ObserveWidget,
				citation6_pageModelProviderObserveValue, null, null);
		//
		IObservableValue observeSelectionComboObserveWidget = WidgetProperties
				.selection().observe(combo);
		IObservableValue citation1_journalModelProviderObserveValue = BeanProperties
				.value("citation1_journal").observe(modelProvider);
		bindingContext.bindValue(observeSelectionComboObserveWidget,
				citation1_journalModelProviderObserveValue, null, null);
		//
		IObservableValue observeSelectionCombo_1ObserveWidget = WidgetProperties
				.selection().observe(combo_1);
		IObservableValue citation2_journalModelProviderObserveValue = BeanProperties
				.value("citation2_journal").observe(modelProvider);
		bindingContext.bindValue(observeSelectionCombo_1ObserveWidget,
				citation2_journalModelProviderObserveValue, null, null);
		//
		IObservableValue observeSelectionCombo_2ObserveWidget = WidgetProperties
				.selection().observe(combo_2);
		IObservableValue citation3_journalModelProviderObserveValue = BeanProperties
				.value("citation3_journal").observe(modelProvider);
		bindingContext.bindValue(observeSelectionCombo_2ObserveWidget,
				citation3_journalModelProviderObserveValue, null, null);
		//
		IObservableValue observeSelectionCombo_3ObserveWidget = WidgetProperties
				.selection().observe(combo_3);
		IObservableValue citation4_journalModelProviderObserveValue = BeanProperties
				.value("citation4_journal").observe(modelProvider);
		bindingContext.bindValue(observeSelectionCombo_3ObserveWidget,
				citation4_journalModelProviderObserveValue, null, null);
		//
		IObservableValue observeSelectionCombo_4ObserveWidget = WidgetProperties
				.selection().observe(combo_4);
		IObservableValue citation5_journalModelProviderObserveValue = BeanProperties
				.value("citation5_journal").observe(modelProvider);
		bindingContext.bindValue(observeSelectionCombo_4ObserveWidget,
				citation5_journalModelProviderObserveValue, null, null);
		//
		IObservableValue observeSelectionCombo_5ObserveWidget = WidgetProperties
				.selection().observe(combo_5);
		IObservableValue citation6_journalModelProviderObserveValue = BeanProperties
				.value("citation6_journal").observe(modelProvider);
		bindingContext.bindValue(observeSelectionCombo_5ObserveWidget,
				citation6_journalModelProviderObserveValue, null, null);
		//
		IObservableValue observeSelectionDateTimeObserveWidget = WidgetProperties
				.selection().observe(dateTime);
		IObservableValue caseDateModelProviderObserveValue = BeanProperties
				.value("caseDate").observe(modelProvider);
		bindingContext.bindValue(observeSelectionDateTimeObserveWidget,
				caseDateModelProviderObserveValue, null, null);
		//
		IObservableValue observeTextTextObserveWidget = WidgetProperties.text(
				SWT.Modify).observe(text);
		IObservableValue appellantModelProviderObserveValue = BeanProperties
				.value("appellant").observe(modelProvider);
		bindingContext.bindValue(observeTextTextObserveWidget,
				appellantModelProviderObserveValue, null, null);
		//
		IObservableValue observeTextText_1ObserveWidget = WidgetProperties
				.text(SWT.Modify).observe(text_1);
		IObservableValue respondantModelProviderObserveValue = BeanProperties
				.value("respondant").observe(modelProvider);
		bindingContext.bindValue(observeTextText_1ObserveWidget,
				respondantModelProviderObserveValue, null, null);
		//
		IObservableValue observeTextText_2ObserveWidget = WidgetProperties
				.text(SWT.Modify).observe(text_2);
		IObservableValue judgesModelProviderObserveValue = BeanProperties
				.value("judges").observe(modelProvider);
		bindingContext.bindValue(observeTextText_2ObserveWidget,
				judgesModelProviderObserveValue, null, null);
		//
		IObservableValue observeTextText_3ObserveWidget = WidgetProperties
				.text(SWT.Modify).observe(text_3);
		IObservableValue advocatesModelProviderObserveValue = BeanProperties
				.value("advocates").observe(modelProvider);
		bindingContext.bindValue(observeTextText_3ObserveWidget,
				advocatesModelProviderObserveValue, null, null);
		//
		IObservableValue observeTextText_22ObserveWidget = WidgetProperties
				.text(SWT.Modify).observe(text_22);
		IObservableValue judgementTextModelProviderObserveValue = BeanProperties
				.value("judgementText").observe(modelProvider);
		bindingContext.bindValue(observeTextText_22ObserveWidget,
				judgementTextModelProviderObserveValue, null, null);
		//
		IObservableValue observeTextText_23ObserveWidget = WidgetProperties
				.text(SWT.Modify).observe(text_23);
		IObservableValue held1ModelProviderObserveValue = BeanProperties.value(
				"held1").observe(modelProvider);
		bindingContext.bindValue(observeTextText_23ObserveWidget,
				held1ModelProviderObserveValue, null, null);
		//
		IObservableValue observeTextText_24ObserveWidget = WidgetProperties
				.text(SWT.Modify).observe(text_24);
		IObservableValue headnote1ModelProviderObserveValue = BeanProperties
				.value("headnote1").observe(modelProvider);
		bindingContext.bindValue(observeTextText_24ObserveWidget,
				headnote1ModelProviderObserveValue, null, null);
		//
		IObservableValue observeTextText_25ObserveWidget = WidgetProperties
				.text(SWT.Modify).observe(text_25);
		IObservableValue held2ModelProviderObserveValue = BeanProperties.value(
				"held2").observe(modelProvider);
		bindingContext.bindValue(observeTextText_25ObserveWidget,
				held2ModelProviderObserveValue, null, null);
		//
		IObservableValue observeTextText_26ObserveWidget = WidgetProperties
				.text(SWT.Modify).observe(text_26);
		IObservableValue headnote2ModelProviderObserveValue = BeanProperties
				.value("headnote2").observe(modelProvider);
		bindingContext.bindValue(observeTextText_26ObserveWidget,
				headnote2ModelProviderObserveValue, null, null);
		//
		IObservableValue observeTextText_27ObserveWidget = WidgetProperties
				.text(SWT.Modify).observe(text_27);
		IObservableValue held3ModelProviderObserveValue = BeanProperties.value(
				"held3").observe(modelProvider);
		bindingContext.bindValue(observeTextText_27ObserveWidget,
				held3ModelProviderObserveValue, null, null);
		//
		IObservableValue observeTextText_28ObserveWidget = WidgetProperties
				.text(SWT.Modify).observe(text_28);
		IObservableValue headnote3ModelProviderObserveValue = BeanProperties
				.value("headnote3").observe(modelProvider);
		bindingContext.bindValue(observeTextText_28ObserveWidget,
				headnote3ModelProviderObserveValue, null, null);
		//
		IObservableValue observeTextText_29ObserveWidget = WidgetProperties
				.text(SWT.Modify).observe(text_29);
		IObservableValue held4ModelProviderObserveValue = BeanProperties.value(
				"held4").observe(modelProvider);
		bindingContext.bindValue(observeTextText_29ObserveWidget,
				held4ModelProviderObserveValue, null, null);
		//
		IObservableValue observeTextText_30ObserveWidget = WidgetProperties
				.text(SWT.Modify).observe(text_30);
		IObservableValue headnote4ModelProviderObserveValue = BeanProperties
				.value("headnote4").observe(modelProvider);
		bindingContext.bindValue(observeTextText_30ObserveWidget,
				headnote4ModelProviderObserveValue, null, null);
		//
		return bindingContext;
	}
}
