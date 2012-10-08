package editors;

import java.awt.Toolkit;
import java.util.List;

import modelProvider.JudgmentsEditorModelProvider;

import org.apache.commons.lang.RandomStringUtils;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.greenapplesolutions.jtdbtool.domain.Judgement;

import dialogs.AddJudgementDialog;
import dialogs.EditJudgementDialog;
import editorInput.JudgmentEditorInput;

import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Group;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.wb.swt.ResourceManager;

import util.SelectedCourt;
import util.Util;

public class JudgmentsEditor extends EditorPart {
	private DataBindingContext m_bindingContext;
	private JudgmentsEditor editor;

	public JudgmentsEditor() {
		setTitleImage(ResourceManager.getPluginImage(
				"com.greenapplesolutions.jtDbTool",
				"icons/appIcons/16x16-1349202770_database_search.png"));
		this.editor = this;
	}

	public static final String ID = "com.greenapplesolutions.jtDbTool.judgementeditor";
	private TableViewer viewer;
	private JudgmentsEditorModelProvider modelProvider;
	private Label lblNewLabel;

	public void createPartControl(Composite parent) {

		parent.setLayout(new FormLayout());

		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new FormLayout());
		FormData fd_composite = new FormData();
		fd_composite.left = new FormAttachment(0, 10);
		fd_composite.bottom = new FormAttachment(100, -10);
		fd_composite.right = new FormAttachment(100, -10);
		composite.setLayoutData(fd_composite);
		createViewer(composite);

		Group group = new Group(parent, SWT.NONE);
		fd_composite.top = new FormAttachment(group, 6);
		group.setLayout(new FormLayout());
		FormData fd_group = new FormData();
		fd_group.left = new FormAttachment(0, 10);
		fd_group.right = new FormAttachment(100, -10);
		fd_group.top = new FormAttachment(0, 10);
		group.setLayoutData(fd_group);

		Button btnNewButton_1 = new Button(group, SWT.NONE);
		FormData fd_btnNewButton_1 = new FormData();
		btnNewButton_1.setLayoutData(fd_btnNewButton_1);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				int index = viewer.getTable().getSelectionIndex();
				if (modelProvider.getJudgements().size() < index || index == -1)
					MessageDialog.openInformation(new Shell(), "",
							"Choose a judgement to edit");
				else {
					EditJudgementDialog dialog = new EditJudgementDialog(
							new Shell(), modelProvider.getJudgements().get(
									index).Keycode, modelProvider
									.getJudgements().get(0).Court.trim()
									.toUpperCase(), editor);
					dialog.open();
				}
			}
		});
		btnNewButton_1.setText("Edit Judgement");
		lblNewLabel = new Label(group, SWT.NONE);
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.left = new FormAttachment(0, 22);
		fd_lblNewLabel.bottom = new FormAttachment(100, -3);
		lblNewLabel.setLayoutData(fd_lblNewLabel);

		Button btnNewButton = new Button(group, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Table table = viewer.getTable();
				int index = table.getSelectionIndex();
				if (modelProvider.getJudgements().size() < index || index == -1)
					MessageDialog.openInformation(new Shell(),
							"No Judgement Selected",
							"Select a judgement to delete");
				else {
					if (!modelProvider.deleteJudgement(modelProvider
							.getJudgements().get(index).Keycode))
						MessageDialog.openError(new Shell(), "Error",
								"Error in deleting judgement");
					else
						table.remove(index);
				}
			}
		});
		fd_btnNewButton_1.bottom = new FormAttachment(btnNewButton, 0,
				SWT.BOTTOM);
		fd_btnNewButton_1.right = new FormAttachment(btnNewButton, -4);
		FormData fd_btnNewButton = new FormData();
		fd_btnNewButton.top = new FormAttachment(0);
		fd_btnNewButton.right = new FormAttachment(100, -10);
		btnNewButton.setLayoutData(fd_btnNewButton);
		btnNewButton.setText("Delete");

		Button btnNewButton_2 = new Button(group, SWT.NONE);
		btnNewButton_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (modelProvider.getJudgements().size() == 0) {
					if (MessageDialog
							.openConfirm(new Shell(), "Verify",
									"Before adding Please verify is selected court is same as Result courts")) {
						AddJudgementDialog dialog = new AddJudgementDialog(
								new Shell(), RandomStringUtils
										.randomAlphanumeric(45).toUpperCase(),
								SelectedCourt.getInstance().getSelectedCourt());
						dialog.open();
					}

				} else {
					AddJudgementDialog dialog = new AddJudgementDialog(
							new Shell(), RandomStringUtils.randomAlphanumeric(
									45).toUpperCase(), modelProvider
									.getJudgements().get(0).Court.trim()
									.toUpperCase());
					dialog.open();

				}
			}
		});
		fd_lblNewLabel.right = new FormAttachment(btnNewButton_2, -138);
		FormData fd_btnNewButton_2 = new FormData();
		fd_btnNewButton_2.bottom = new FormAttachment(btnNewButton, 0,
				SWT.BOTTOM);
		fd_btnNewButton_2.right = new FormAttachment(btnNewButton_1, -6);
		btnNewButton_2.setLayoutData(fd_btnNewButton_2);
		btnNewButton_2.setText("Add Judgement");
		m_bindingContext = initDataBindings();
	}

	public void refresh() {
		viewer.refresh();
	}

	private void createViewer(Composite parent) {
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		createColumns(parent, viewer);
		final Table table = viewer.getTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				int index = table.getSelectionIndex();
				if (modelProvider.getJudgements().size() < index || index == -1)
					System.out.println("Void");
				else
					System.out
							.println(modelProvider.getJudgements().get(index).Appellant);
			}
		});
		FormData fd_table = new FormData();
		fd_table.bottom = new FormAttachment(100);
		fd_table.right = new FormAttachment(100);
		fd_table.top = new FormAttachment(0);
		fd_table.left = new FormAttachment(0, 5);
		table.setLayoutData(fd_table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		viewer.setContentProvider(new ArrayContentProvider());
		// Get the content for the viewer, setInput will call getElements in the
		// contentProvider
		viewer.setInput(modelProvider.getJudgements());
		// Make the selection available to other views
		getSite().setSelectionProvider(viewer);
		// Set the sorter for the table

	}

	public TableViewer getViewer() {
		return viewer;
	}

	// This will create the columns for the table
	private void createColumns(final Composite parent, final TableViewer viewer) {
		int xAxis = (int) Toolkit.getDefaultToolkit().getScreenSize().width;

		String[] titles = { "Appellant", "Respondant", "CaseNumber", "Judges",
				"Advocates", "Case Date" };
		int colWidth = xAxis / titles.length;
		int[] bounds = { colWidth, colWidth, colWidth, colWidth, colWidth,
				colWidth };

		// First column is for the first Appellant
		TableViewerColumn col = createTableViewerColumn(titles[0], bounds[0], 0);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Judgement p = (Judgement) element;
				return p.Appellant;
			}
		});

		// Second column is for the last Respondant
		col = createTableViewerColumn(titles[1], bounds[1], 1);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Judgement p = (Judgement) element;
				return p.Respondant;
			}
		});

		// Now the gender
		col = createTableViewerColumn(titles[2], bounds[2], 2);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Judgement p = (Judgement) element;
				return p.CaseNumber;
			}
		});

		// // Now the status judges
		col = createTableViewerColumn(titles[3], bounds[3], 3);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Judgement p = (Judgement) element;
				return p.Judges;
			}

		});
		col = createTableViewerColumn(titles[4], bounds[4], 4);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Judgement p = (Judgement) element;
				return p.Advocates;

			}

		});
		col = createTableViewerColumn(titles[5], bounds[5], 5);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Judgement p = (Judgement) element;
				return Util.getDateInString("dd-MM-yyyy", p.CaseDate);
			}

		});
	}

	private TableViewerColumn createTableViewerColumn(String title, int bound,
			final int colNumber) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(viewer,
				SWT.NONE);
		final TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);
		column.setResizable(true);
		column.setMoveable(true);
		return viewerColumn;
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */

	public void setFocus() {
		viewer.getControl().setFocus();
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
		JudgmentEditorInput jei = (JudgmentEditorInput) input;
		List<Judgement> judgements = jei.getJudgements();
		boolean isFileLoaded = jei.getIsFileLoaded();
		modelProvider = new JudgmentsEditorModelProvider(judgements,
				isFileLoaded);
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

	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue lblNewLabelObserveTextObserveWidget = SWTObservables
				.observeText(lblNewLabel);
		IObservableValue modelProviderSelectedCourtLabelObserveValue = BeansObservables
				.observeValue(modelProvider, "selectedCourtLabel");
		bindingContext.bindValue(lblNewLabelObserveTextObserveWidget,
				modelProviderSelectedCourtLabelObserveValue, null, null);
		//
		return bindingContext;
	}
}