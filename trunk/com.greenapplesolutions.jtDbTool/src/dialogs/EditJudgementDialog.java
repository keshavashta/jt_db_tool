package dialogs;

import modelProvider.EditJudgementdialogModelProvider;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.GridData;

import util.Util;

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
	private Text text_31;
	private Text text_32;
	private Text text_33;
	private Text text_34;
	private Text text_35;
	private Text scHeadnote3;
	private Text scHeld3;
	private Text scHeadnote4;
	private Text scHeld4;
	private Text text_23;
	private Table resultsTable;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public EditJudgementDialog(Shell parent, String keycode, String courtName,
			Table table) {
		super(parent);
		resultsTable = table;
		modelProvider = new EditJudgementdialogModelProvider(keycode, courtName);
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
		int headnoteTextHeight = 200;
		shlVieweditJudgement = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.MAX
				| SWT.APPLICATION_MODAL);
		shlVieweditJudgement.setSize(532, 493);
		shlVieweditJudgement.setImage(ResourceManager.getPluginImage(
				"com.greenapplesolutions.jtDbTool",
				"icons/appIcons/edit_txt32x32.png"));

		shlVieweditJudgement.setBounds(Util.getFullScreenBounds());
		shlVieweditJudgement.setText("View/Edit Judgement");
		shlVieweditJudgement.setLayout(new FormLayout());

		TabFolder tabFolder = new TabFolder(shlVieweditJudgement, SWT.NONE);
		FormData fd_tabFolder = new FormData();
		fd_tabFolder.top = new FormAttachment(0, 10);
		fd_tabFolder.left = new FormAttachment(0, 10);

		fd_tabFolder.right = new FormAttachment(100, -10);
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

		dateTime = new DateTime(composite, SWT.BORDER | SWT.DROP_DOWN);
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
		fd_lblJudgement.left = new FormAttachment(lblNewLabel, 0, SWT.LEFT);
		fd_lblJudgement.right = new FormAttachment(0, 472);
		lblJudgement.setLayoutData(fd_lblJudgement);
		lblJudgement.setText("Judgement");

		text_22 = new Text(composite, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL
				| SWT.MULTI);
		FormData fd_text_22 = new FormData();
		fd_text_22.top = new FormAttachment(lblJudgement, 6);
		fd_text_22.right = new FormAttachment(text, 0, SWT.RIGHT);
		fd_text_22.left = new FormAttachment(0, 10);
		fd_text_22.bottom = new FormAttachment(100, -10);
		text_22.setLayoutData(fd_text_22);

		Label lblCasesReferred = new Label(composite, SWT.NONE);
		FormData fd_lblCasesReferred = new FormData();
		fd_lblCasesReferred.right = new FormAttachment(100, -1094);
		fd_lblCasesReferred.left = new FormAttachment(0, 10);
		lblCasesReferred.setLayoutData(fd_lblCasesReferred);
		lblCasesReferred.setText("Cases Referred");

		text_31 = new Text(composite, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL
				| SWT.MULTI);
		fd_lblJudgement.top = new FormAttachment(text_31, 6);
		FormData fd_text_31 = new FormData();
		fd_text_31.top = new FormAttachment(lblCasesReferred, 6);
		fd_text_31.right = new FormAttachment(text, 0, SWT.RIGHT);
		fd_text_31.left = new FormAttachment(lblNewLabel, 0, SWT.LEFT);
		fd_text_31.bottom = new FormAttachment(lblCasesReferred, 100);
		text_31.setLayoutData(fd_text_31);

		Label lblCaseNumber = new Label(composite, SWT.NONE);
		FormData fd_lblCaseNumber = new FormData();
		fd_lblCaseNumber.top = new FormAttachment(text_3, 7);
		fd_lblCaseNumber.left = new FormAttachment(lblNewLabel, 0, SWT.LEFT);
		lblCaseNumber.setLayoutData(fd_lblCaseNumber);
		lblCaseNumber.setText("Case Number");

		text_23 = new Text(composite, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
		fd_lblCasesReferred.top = new FormAttachment(text_23, 6);
		FormData fd_text_23 = new FormData();
		fd_text_23.bottom = new FormAttachment(lblCaseNumber, 48, SWT.BOTTOM);
		fd_text_23.right = new FormAttachment(100, -10);
		fd_text_23.top = new FormAttachment(lblCaseNumber, 3);
		fd_text_23.left = new FormAttachment(lblNewLabel, 0, SWT.LEFT);
		text_23.setLayoutData(fd_text_23);

		TabItem tbtmCitation = new TabItem(tabFolder, SWT.NONE);
		tbtmCitation.setText("Citation");

		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		tbtmCitation.setControl(composite_1);
		composite_1.setLayout(new FormLayout());

		Group grpCitation = new Group(composite_1, SWT.NONE);
		FormData fd_grpCitation = new FormData();
		fd_grpCitation.bottom = new FormAttachment(0, 92);
		fd_grpCitation.right = new FormAttachment(100, -10);
		fd_grpCitation.top = new FormAttachment(0, 10);
		fd_grpCitation.left = new FormAttachment(0, 10);
		grpCitation.setLayoutData(fd_grpCitation);
		grpCitation.setText("Citation 1");
		grpCitation.setLayout(new FormLayout());

		Label lblJournal = new Label(grpCitation, SWT.NONE);
		FormData fd_lblJournal = new FormData();
		fd_lblJournal.right = new FormAttachment(0, 62);
		fd_lblJournal.top = new FormAttachment(0, 5);
		fd_lblJournal.left = new FormAttachment(0, 7);
		lblJournal.setLayoutData(fd_lblJournal);
		lblJournal.setText("Journal");

		combo = new Combo(grpCitation, SWT.READ_ONLY);
		FormData fd_combo = new FormData();
		fd_combo.top = new FormAttachment(0, 34);
		combo.setLayoutData(fd_combo);

		Label lblNewLabel_3 = new Label(grpCitation, SWT.NONE);
		FormData fd_lblNewLabel_3 = new FormData();
		fd_lblNewLabel_3.right = new FormAttachment(0, 170);
		fd_lblNewLabel_3.top = new FormAttachment(0, 5);
		fd_lblNewLabel_3.left = new FormAttachment(0, 115);
		lblNewLabel_3.setLayoutData(fd_lblNewLabel_3);
		lblNewLabel_3.setText("Year");

		text_4 = new Text(grpCitation, SWT.BORDER);
		fd_combo.left = new FormAttachment(text_4, -96, SWT.LEFT);
		fd_combo.right = new FormAttachment(text_4, -6);
		FormData fd_text_4 = new FormData();
		fd_text_4.top = new FormAttachment(lblNewLabel_3, 14);
		fd_text_4.left = new FormAttachment(0, 103);
		text_4.setLayoutData(fd_text_4);

		Label lblVolume = new Label(grpCitation, SWT.NONE);
		FormData fd_lblVolume = new FormData();
		fd_lblVolume.right = new FormAttachment(0, 258);
		fd_lblVolume.top = new FormAttachment(0, 5);
		fd_lblVolume.left = new FormAttachment(0, 203);
		lblVolume.setLayoutData(fd_lblVolume);
		lblVolume.setText("Volume");

		text_5 = new Text(grpCitation, SWT.BORDER);
		fd_text_4.right = new FormAttachment(text_5, -6);
		FormData fd_text_5 = new FormData();
		fd_text_5.left = new FormAttachment(0, 185);
		fd_text_5.top = new FormAttachment(lblVolume, 14);
		text_5.setLayoutData(fd_text_5);

		Label lblPage = new Label(grpCitation, SWT.NONE);
		FormData fd_lblPage = new FormData();
		fd_lblPage.right = new FormAttachment(0, 347);
		fd_lblPage.top = new FormAttachment(0, 5);
		fd_lblPage.left = new FormAttachment(0, 292);
		lblPage.setLayoutData(fd_lblPage);
		lblPage.setText("Page");

		text_6 = new Text(grpCitation, SWT.BORDER);
		fd_text_5.right = new FormAttachment(text_6, -20);
		FormData fd_text_6 = new FormData();
		fd_text_6.top = new FormAttachment(0, 34);
		text_6.setLayoutData(fd_text_6);

		Button btnClear = new Button(grpCitation, SWT.NONE);
		fd_text_6.left = new FormAttachment(btnClear, -90, SWT.LEFT);
		fd_text_6.right = new FormAttachment(btnClear, -14);
		FormData fd_btnClear = new FormData();
		fd_btnClear.right = new FormAttachment(0, 446);
		fd_btnClear.top = new FormAttachment(0, 32);
		fd_btnClear.left = new FormAttachment(0, 371);
		btnClear.setLayoutData(fd_btnClear);
		btnClear.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				modelProvider.clearCitation1();
			}
		});
		btnClear.setText("Clear");

		Group grpCitation_1 = new Group(composite_1, SWT.NONE);
		FormData fd_grpCitation_1 = new FormData();
		fd_grpCitation_1.bottom = new FormAttachment(0, 180);
		fd_grpCitation_1.right = new FormAttachment(100, -10);
		fd_grpCitation_1.top = new FormAttachment(0, 98);
		fd_grpCitation_1.left = new FormAttachment(0, 10);
		grpCitation_1.setLayoutData(fd_grpCitation_1);
		grpCitation_1.setText("Citation 2");

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

		Button btnClear_1 = new Button(grpCitation_1, SWT.NONE);
		btnClear_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				modelProvider.clearCitation2();
			}
		});
		btnClear_1.setBounds(375, 47, 75, 25);
		btnClear_1.setText("Clear");

		Group grpCitation_2 = new Group(composite_1, SWT.NONE);
		FormData fd_grpCitation_2 = new FormData();
		fd_grpCitation_2.bottom = new FormAttachment(0, 270);
		fd_grpCitation_2.right = new FormAttachment(100, -10);
		fd_grpCitation_2.top = new FormAttachment(0, 188);
		fd_grpCitation_2.left = new FormAttachment(0, 10);
		grpCitation_2.setLayoutData(fd_grpCitation_2);
		grpCitation_2.setText("Citation 3");

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

		Button btnClear_2 = new Button(grpCitation_2, SWT.NONE);
		btnClear_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				modelProvider.clearCitation3();
			}
		});
		btnClear_2.setBounds(376, 47, 75, 25);
		btnClear_2.setText("Clear");

		Group grpCitation_3 = new Group(composite_1, SWT.NONE);
		FormData fd_grpCitation_3 = new FormData();
		fd_grpCitation_3.bottom = new FormAttachment(0, 362);
		fd_grpCitation_3.right = new FormAttachment(100, -10);
		fd_grpCitation_3.top = new FormAttachment(0, 280);
		fd_grpCitation_3.left = new FormAttachment(0, 10);
		grpCitation_3.setLayoutData(fd_grpCitation_3);
		grpCitation_3.setText("Citation 4");

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

		Button btnClear_3 = new Button(grpCitation_3, SWT.NONE);
		btnClear_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				modelProvider.clearCitation4();
			}
		});
		btnClear_3.setBounds(377, 45, 75, 25);
		btnClear_3.setText("Clear");

		Group grpCitation_4 = new Group(composite_1, SWT.NONE);
		FormData fd_grpCitation_4 = new FormData();
		fd_grpCitation_4.bottom = new FormAttachment(0, 450);
		fd_grpCitation_4.right = new FormAttachment(100, -10);
		fd_grpCitation_4.top = new FormAttachment(0, 368);
		fd_grpCitation_4.left = new FormAttachment(0, 10);
		grpCitation_4.setLayoutData(fd_grpCitation_4);
		grpCitation_4.setText("Citation 5");

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

		Button btnClear_4 = new Button(grpCitation_4, SWT.NONE);
		btnClear_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				modelProvider.clearCitation5();
			}
		});
		btnClear_4.setBounds(376, 47, 75, 25);
		btnClear_4.setText("Clear");

		Group grpCitation_5 = new Group(composite_1, SWT.NONE);
		FormData fd_grpCitation_5 = new FormData();
		fd_grpCitation_5.bottom = new FormAttachment(0, 538);
		fd_grpCitation_5.right = new FormAttachment(100, -10);
		fd_grpCitation_5.top = new FormAttachment(0, 456);
		fd_grpCitation_5.left = new FormAttachment(0, 10);
		grpCitation_5.setLayoutData(fd_grpCitation_5);
		grpCitation_5.setText("Citation 6");

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

		Button btnClear_5 = new Button(grpCitation_5, SWT.NONE);
		btnClear_5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				modelProvider.clearCitation6();
			}
		});
		btnClear_5.setBounds(375, 47, 75, 25);
		btnClear_5.setText("Clear");

		Button btnClose = new Button(shlVieweditJudgement, SWT.NONE);
		fd_tabFolder.bottom = new FormAttachment(btnClose, -18);

		TabItem tbtmNewItem = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem.setText("Headnote");

		ScrolledComposite scrolledComposite = new ScrolledComposite(tabFolder,
				SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		tbtmNewItem.setControl(scrolledComposite);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		Composite scrolledChildComposite = new Composite(scrolledComposite,
				SWT.NONE);
		scrolledChildComposite.setLayout(new GridLayout(1, true));

		Group group_3 = new Group(scrolledChildComposite, SWT.NONE);
		GridData gd_group_3 = new GridData(SWT.FILL, SWT.CENTER, false, false,
				1, 1);
		gd_group_3.heightHint = headnoteTextHeight;
		gd_group_3.grabExcessHorizontalSpace = true;
		group_3.setLayoutData(gd_group_3);
		group_3.setText("Headnote And Held Number 1");
		group_3.setLayout(new FormLayout());

		Label lblHeadnote_1 = new Label(group_3, SWT.NONE);
		FormData fd_lblHeadnote_1 = new FormData();
		fd_lblHeadnote_1.top = new FormAttachment(0, 10);
		fd_lblHeadnote_1.left = new FormAttachment(0, 10);
		lblHeadnote_1.setLayoutData(fd_lblHeadnote_1);
		lblHeadnote_1.setText("Headnote");

		text_32 = new Text(group_3, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL
				| SWT.MULTI);
		FormData fd_text_32 = new FormData();
		fd_text_32.right = new FormAttachment(0, 512);
		fd_text_32.top = new FormAttachment(lblHeadnote_1, 7);
		fd_text_32.left = new FormAttachment(0, 10);
		fd_text_32.bottom = new FormAttachment(100, -17);
		text_32.setLayoutData(fd_text_32);

		Label label_30 = new Label(group_3, SWT.SEPARATOR | SWT.VERTICAL);
		FormData fd_label_30 = new FormData();
		fd_label_30.left = new FormAttachment(text_32, 10);
		fd_label_30.top = new FormAttachment(0);
		fd_label_30.bottom = new FormAttachment(100);
		label_30.setLayoutData(fd_label_30);

		Label lblHeld_1 = new Label(group_3, SWT.NONE);
		FormData fd_lblHeld_1 = new FormData();
		fd_lblHeld_1.bottom = new FormAttachment(lblHeadnote_1, 0, SWT.BOTTOM);
		fd_lblHeld_1.left = new FormAttachment(label_30, 6);
		lblHeld_1.setLayoutData(fd_lblHeld_1);
		lblHeld_1.setText("Held");

		text_33 = new Text(group_3, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL
				| SWT.MULTI);
		FormData fd_text_33 = new FormData();
		fd_text_33.bottom = new FormAttachment(label_30, -17, SWT.BOTTOM);
		fd_text_33.top = new FormAttachment(text_32, 0, SWT.TOP);
		fd_text_33.right = new FormAttachment(100, -10);
		fd_text_33.left = new FormAttachment(label_30, 9);
		text_33.setLayoutData(fd_text_33);

		Group grpHeadnoteAndHeld_1 = new Group(scrolledChildComposite, SWT.NONE);
		GridData gd_grpHeadnoteAndHeld_1 = new GridData(SWT.FILL, SWT.CENTER,
				false, false, 1, 1);
		gd_grpHeadnoteAndHeld_1.heightHint = headnoteTextHeight;
		grpHeadnoteAndHeld_1.setLayoutData(gd_grpHeadnoteAndHeld_1);
		grpHeadnoteAndHeld_1.setText("Headnote And Held Number 2");
		grpHeadnoteAndHeld_1.setLayout(new FormLayout());

		Label label_31 = new Label(grpHeadnoteAndHeld_1, SWT.NONE);
		label_31.setText("Headnote");
		FormData fd_label_31 = new FormData();
		fd_label_31.top = new FormAttachment(0, 10);
		fd_label_31.left = new FormAttachment(0, 10);
		label_31.setLayoutData(fd_label_31);

		text_34 = new Text(grpHeadnoteAndHeld_1, SWT.BORDER | SWT.WRAP
				| SWT.V_SCROLL | SWT.MULTI);
		FormData fd_text_34 = new FormData();
		fd_text_34.bottom = new FormAttachment(100, -17);
		fd_text_34.top = new FormAttachment(label_31, 7);
		fd_text_34.right = new FormAttachment(0, 512);
		fd_text_34.left = new FormAttachment(0, 10);
		text_34.setLayoutData(fd_text_34);

		Label label_32 = new Label(grpHeadnoteAndHeld_1, SWT.SEPARATOR);
		FormData fd_label_32 = new FormData();
		fd_label_32.bottom = new FormAttachment(100);
		fd_label_32.top = new FormAttachment(0);
		fd_label_32.left = new FormAttachment(text_34, 10);
		label_32.setLayoutData(fd_label_32);

		Label label_33 = new Label(grpHeadnoteAndHeld_1, SWT.NONE);
		label_33.setText("Held");
		FormData fd_label_33 = new FormData();
		fd_label_33.bottom = new FormAttachment(label_31, 0, SWT.BOTTOM);
		fd_label_33.left = new FormAttachment(label_32, 6);
		label_33.setLayoutData(fd_label_33);

		text_35 = new Text(grpHeadnoteAndHeld_1, SWT.BORDER | SWT.WRAP
				| SWT.V_SCROLL | SWT.MULTI);
		FormData fd_text_35 = new FormData();
		fd_text_35.bottom = new FormAttachment(label_32, -17, SWT.BOTTOM);
		fd_text_35.top = new FormAttachment(text_34, 0, SWT.TOP);
		fd_text_35.right = new FormAttachment(100, -10);
		fd_text_35.left = new FormAttachment(label_32, 9);
		text_35.setLayoutData(fd_text_35);

		Group grpHeadnoteAndHeld_2 = new Group(scrolledChildComposite, SWT.NONE);
		GridData gd_grpHeadnoteAndHeld_2 = new GridData(SWT.FILL, SWT.CENTER,
				false, false, 1, 1);
		gd_grpHeadnoteAndHeld_2.heightHint = headnoteTextHeight;
		grpHeadnoteAndHeld_2.setLayoutData(gd_grpHeadnoteAndHeld_2);
		grpHeadnoteAndHeld_2.setText("Headnote And Held Number 3");
		grpHeadnoteAndHeld_2.setLayout(new FormLayout());

		Label label_34 = new Label(grpHeadnoteAndHeld_2, SWT.NONE);
		label_34.setText("Headnote");
		FormData fd_label_34 = new FormData();
		fd_label_34.top = new FormAttachment(0, 10);
		fd_label_34.left = new FormAttachment(0, 10);
		label_34.setLayoutData(fd_label_34);

		scHeadnote3 = new Text(grpHeadnoteAndHeld_2, SWT.BORDER | SWT.WRAP
				| SWT.V_SCROLL | SWT.MULTI);
		FormData fd_scHeadnote3 = new FormData();
		fd_scHeadnote3.bottom = new FormAttachment(100, -17);
		fd_scHeadnote3.top = new FormAttachment(label_34, 7);
		fd_scHeadnote3.right = new FormAttachment(0, 512);
		fd_scHeadnote3.left = new FormAttachment(0, 10);
		scHeadnote3.setLayoutData(fd_scHeadnote3);

		Label label_35 = new Label(grpHeadnoteAndHeld_2, SWT.SEPARATOR);
		FormData fd_label_35 = new FormData();
		fd_label_35.bottom = new FormAttachment(100);
		fd_label_35.top = new FormAttachment(0);
		fd_label_35.left = new FormAttachment(scHeadnote3, 10);
		label_35.setLayoutData(fd_label_35);

		Label label_36 = new Label(grpHeadnoteAndHeld_2, SWT.NONE);
		label_36.setText("Held");
		FormData fd_label_36 = new FormData();
		fd_label_36.bottom = new FormAttachment(label_34, 0, SWT.BOTTOM);
		fd_label_36.left = new FormAttachment(label_35, 6);
		label_36.setLayoutData(fd_label_36);

		scHeld3 = new Text(grpHeadnoteAndHeld_2, SWT.BORDER | SWT.WRAP
				| SWT.V_SCROLL | SWT.MULTI);
		FormData fd_scHeld3 = new FormData();
		fd_scHeld3.bottom = new FormAttachment(label_35, -17, SWT.BOTTOM);
		fd_scHeld3.top = new FormAttachment(scHeadnote3, 0, SWT.TOP);
		fd_scHeld3.right = new FormAttachment(100, -10);
		fd_scHeld3.left = new FormAttachment(label_35, 9);
		scHeld3.setLayoutData(fd_scHeld3);

		Group grpHeadnoteAndHeld_3 = new Group(scrolledChildComposite, SWT.NONE);
		GridData gd_grpHeadnoteAndHeld_3 = new GridData(SWT.FILL, SWT.CENTER,
				false, false, 1, 1);
		gd_grpHeadnoteAndHeld_3.heightHint = headnoteTextHeight;
		grpHeadnoteAndHeld_3.setLayoutData(gd_grpHeadnoteAndHeld_3);
		grpHeadnoteAndHeld_3.setText("Headnote And Held Number4");
		grpHeadnoteAndHeld_3.setLayout(new FormLayout());

		Label label_37 = new Label(grpHeadnoteAndHeld_3, SWT.NONE);
		label_37.setText("Headnote");
		FormData fd_label_37 = new FormData();
		fd_label_37.top = new FormAttachment(0, 10);
		fd_label_37.left = new FormAttachment(0, 10);
		label_37.setLayoutData(fd_label_37);

		scHeadnote4 = new Text(grpHeadnoteAndHeld_3, SWT.BORDER | SWT.WRAP
				| SWT.V_SCROLL | SWT.MULTI);
		FormData fd_scHeadnote4 = new FormData();
		fd_scHeadnote4.bottom = new FormAttachment(100, -17);
		fd_scHeadnote4.top = new FormAttachment(label_37, 7);
		fd_scHeadnote4.right = new FormAttachment(0, 512);
		fd_scHeadnote4.left = new FormAttachment(0, 10);
		scHeadnote4.setLayoutData(fd_scHeadnote4);

		Label label_38 = new Label(grpHeadnoteAndHeld_3, SWT.SEPARATOR);
		FormData fd_label_38 = new FormData();
		fd_label_38.bottom = new FormAttachment(100);
		fd_label_38.top = new FormAttachment(0);
		fd_label_38.left = new FormAttachment(scHeadnote4, 10);
		label_38.setLayoutData(fd_label_38);

		Label label_39 = new Label(grpHeadnoteAndHeld_3, SWT.NONE);
		label_39.setText("Held");
		FormData fd_label_39 = new FormData();
		fd_label_39.bottom = new FormAttachment(label_37, 0, SWT.BOTTOM);
		fd_label_39.left = new FormAttachment(label_38, 6);
		label_39.setLayoutData(fd_label_39);

		scHeld4 = new Text(grpHeadnoteAndHeld_3, SWT.BORDER | SWT.WRAP
				| SWT.V_SCROLL | SWT.MULTI);
		FormData fd_scHeld4 = new FormData();
		fd_scHeld4.bottom = new FormAttachment(label_38, -17, SWT.BOTTOM);
		fd_scHeld4.top = new FormAttachment(scHeadnote4, 0, SWT.TOP);
		fd_scHeld4.right = new FormAttachment(100, -10);
		fd_scHeld4.left = new FormAttachment(label_38, 9);
		scHeld4.setLayoutData(fd_scHeld4);
		scrolledComposite.setContent(scrolledChildComposite);
		scrolledComposite.setMinSize(scrolledChildComposite.computeSize(
				SWT.DEFAULT, SWT.DEFAULT));
		btnClose.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlVieweditJudgement.close();

			}
		});
		FormData fd_btnClose = new FormData();
		btnClose.setLayoutData(fd_btnClose);
		btnClose.setText("Close");
		Button btnUpdate = new Button(shlVieweditJudgement, SWT.NONE);
		fd_btnClose.top = new FormAttachment(btnUpdate, 0, SWT.TOP);
		fd_btnClose.left = new FormAttachment(btnUpdate, 6);
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
		fd_btnUpdate.right = new FormAttachment(100, -57);
		fd_btnUpdate.bottom = new FormAttachment(100, -10);
		btnUpdate.setLayoutData(fd_btnUpdate);
		btnUpdate.setText("Update");

		Button btnIsverified = new Button(shlVieweditJudgement, SWT.NONE);
		btnIsverified.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (modelProvider.judgementVerified()) {
					shlVieweditJudgement.close();
					resultsTable.remove(resultsTable.getSelectionIndex());
				} else
					MessageDialog.openError(new Shell(), "Error",
							"Error in connecting with database.");
			}
		});
		FormData fd_btnIsverified = new FormData();
		fd_btnIsverified.bottom = new FormAttachment(btnClose, 0, SWT.BOTTOM);
		fd_btnIsverified.right = new FormAttachment(btnUpdate, -6);
		btnIsverified.setLayoutData(fd_btnIsverified);
		btnIsverified.setText("IsVerified");
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
		IObservableValue text_31ObserveTextObserveWidget = SWTObservables
				.observeText(text_31, SWT.Modify);
		IObservableValue modelProviderCasesReferredObserveValue = BeansObservables
				.observeValue(modelProvider, "casesReferred");
		bindingContext.bindValue(text_31ObserveTextObserveWidget,
				modelProviderCasesReferredObserveValue, null, null);
		//
		IObservableValue observeTextText_32ObserveWidget = WidgetProperties
				.text(SWT.Modify).observe(text_32);
		IObservableValue headnote1ModelProviderObserveValue = BeanProperties
				.value("headnote1").observe(modelProvider);
		bindingContext.bindValue(observeTextText_32ObserveWidget,
				headnote1ModelProviderObserveValue, null, null);
		//
		IObservableValue observeTextText_33ObserveWidget = WidgetProperties
				.text(SWT.Modify).observe(text_33);
		IObservableValue held1ModelProviderObserveValue = BeanProperties.value(
				"held1").observe(modelProvider);
		bindingContext.bindValue(observeTextText_33ObserveWidget,
				held1ModelProviderObserveValue, null, null);
		//
		IObservableValue observeTextText_34ObserveWidget = WidgetProperties
				.text(SWT.Modify).observe(text_34);
		IObservableValue headnote2ModelProviderObserveValue = BeanProperties
				.value("headnote2").observe(modelProvider);
		bindingContext.bindValue(observeTextText_34ObserveWidget,
				headnote2ModelProviderObserveValue, null, null);
		//
		IObservableValue observeTextText_35ObserveWidget = WidgetProperties
				.text(SWT.Modify).observe(text_35);
		IObservableValue held2ModelProviderObserveValue = BeanProperties.value(
				"held2").observe(modelProvider);
		bindingContext.bindValue(observeTextText_35ObserveWidget,
				held2ModelProviderObserveValue, null, null);
		//
		IObservableValue observeTextScHeadnote3ObserveWidget = WidgetProperties
				.text(SWT.Modify).observe(scHeadnote3);
		IObservableValue headnote3ModelProviderObserveValue = BeanProperties
				.value("headnote3").observe(modelProvider);
		bindingContext.bindValue(observeTextScHeadnote3ObserveWidget,
				headnote3ModelProviderObserveValue, null, null);
		//
		IObservableValue observeTextScHeld3ObserveWidget = WidgetProperties
				.text(SWT.Modify).observe(scHeld3);
		IObservableValue held3ModelProviderObserveValue = BeanProperties.value(
				"held3").observe(modelProvider);
		bindingContext.bindValue(observeTextScHeld3ObserveWidget,
				held3ModelProviderObserveValue, null, null);
		//
		IObservableValue observeTextScHeadnote4ObserveWidget = WidgetProperties
				.text(SWT.Modify).observe(scHeadnote4);
		IObservableValue headnote4ModelProviderObserveValue = BeanProperties
				.value("headnote4").observe(modelProvider);
		bindingContext.bindValue(observeTextScHeadnote4ObserveWidget,
				headnote4ModelProviderObserveValue, null, null);
		//
		IObservableValue observeTextScHeld4ObserveWidget = WidgetProperties
				.text(SWT.Modify).observe(scHeld4);
		IObservableValue held4ModelProviderObserveValue = BeanProperties.value(
				"held4").observe(modelProvider);
		bindingContext.bindValue(observeTextScHeld4ObserveWidget,
				held4ModelProviderObserveValue, null, null);
		//
		IObservableValue text_23ObserveTextObserveWidget = SWTObservables
				.observeText(text_23, SWT.Modify);
		IObservableValue modelProviderCaseNumberObserveValue = BeansObservables
				.observeValue(modelProvider, "caseNumber");
		bindingContext.bindValue(text_23ObserveTextObserveWidget,
				modelProviderCaseNumberObserveValue, null, null);
		//
		return bindingContext;
	}
}
