package com.nmt.nmj.editor.action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;

import com.nmt.nmj.editor.Application;
import com.nmt.nmj.editor.ICommandIds;
import com.nmt.nmj.editor.backend.DatabaseServiceFactory;
import com.nmt.nmj.editor.backend.JukeboxDatabaseService;
import com.nmt.nmj.editor.exception.NmjEditorException;
import com.nmt.nmj.editor.nls.NlsMessages;
import com.nmt.nmj.editor.perspective.ListPerspective;
import com.nmt.nmj.editor.view.ListView;

public class OpenDatabaseAction extends Action {

    private final IWorkbenchWindow window;

    public OpenDatabaseAction(IWorkbenchWindow window, String label) {
        this.window = window;
        setText(label);
        setToolTipText(label);
        setId(com.nmt.nmj.editor.ICommandIds.CMD_OPEN_DATABASE);
        setActionDefinitionId(ICommandIds.CMD_OPEN_DATABASE);
    }

    public void run() {
        if (window != null) {
            FileDialog fileDialog = new FileDialog(window.getShell(), SWT.OPEN);
            fileDialog.setFilterPath(System.getProperty("user.home"));
            fileDialog.setText(NlsMessages.database_open_message);
            fileDialog.setFilterExtensions(new String[] { "*.db", "*.*" });
            fileDialog.setFilterNames(new String[] { NlsMessages.database_open_db_files,
                    NlsMessages.database_open_all_files });
            String selectedFile = fileDialog.open();
            if (selectedFile != null) {
                try {
                    JukeboxDatabaseService databaseService = DatabaseServiceFactory
                            .createSqliteJukeboxDatabaseService(selectedFile);
                    Application.setDatabaseService(databaseService);
                    databaseService.openConnection();
                    PlatformUI.getWorkbench().showPerspective(ListPerspective.ID, window);
                    ListView editorView = (ListView) window.getActivePage().showView(ListView.ID);
                    editorView.refresh();
                } catch (NmjEditorException e1) {
                    MessageDialog.openError(window.getShell(), NlsMessages.common_error, e1.getMessage());
                } catch (PartInitException e) {
                    MessageDialog.openError(window.getShell(), NlsMessages.common_error, "List view is missing");
                } catch (WorkbenchException e) {
                    MessageDialog.openError(window.getShell(), NlsMessages.common_error, "List perspective is missing");
                }
            }
        }
    }

}
