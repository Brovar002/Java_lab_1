import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


public class MenuBar extends JMenuBar implements ActionListener, Observer
{

    private static final long serialVersionUID = 1L;
    private FileManager fileManager;
    private JMenu fileMenu = null;
    private JMenu editMenu = null;

    public MenuBar(UserInterface ui)
    {
        this.fileManager = new FileManager();
        this.fileManager.addObserver(this);
        this.fileManager.addObserver(ui);
        createMenu();
        setVisible(true);
    }

    private void createMenu()
    {
        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");
        createMenuItems();
        this.add(fileMenu);
        this.add(editMenu);
    }

    private void createMenuItems()
    {
        String[] fileMenuOptions = { "New", "Open", "Save" };
        String[] editMenuOptions = { "Cut", "Copy", "Paste" };
        for (String option : fileMenuOptions)
        {
            fileMenu.add(createMenuItem(option));
        }
        for (String option : editMenuOptions)
        {
            editMenu.add(createMenuItem(option));
        }
    }

    private JMenuItem createMenuItem(String option)
    {
        JMenuItem newMenuItem = new JMenuItem(option);
        newMenuItem.addActionListener(this);
        return newMenuItem;
    }

    public void actionPerformed(ActionEvent e)
    {
        String eventPerformed = e.getActionCommand();
        switch (eventPerformed)
        {
            case "Open":
                openFile();
                break;
            case "Save":
                saveAsFile();
                break;
            case "New":
                createNewEditorWorkspace();
                break;
            case "Cut":
                cutOperation();
                break;
            case "Copy":
                copyOperation();
                break;
            case "Paste":
                pasteOperation();
                break;
        }
    }

    public void saveAsFile()
    {
        fileManager.saveAsFile(chooseFile());

    }

    public void openFile()
    {
        fileManager.loadFile(chooseFile());
    }

    public void cutOperation()
    {
        Model.getInstance().getTextArea().cut();
    }

    public void copyOperation()
    {
        Model.getInstance().getTextArea().copy();
    }

    public void pasteOperation()
    {
        Model.getInstance().getTextArea().paste();
    }

    public void createNewEditorWorkspace()
    {
        String text = Model.getInstance().getText();
        if (text == null || text.equals(""))
        { }
        else
            {
            int showConfirmDialog = JOptionPane.showConfirmDialog(null,
                    "Are you sure to clear current workspace? Any unsaved changes will be discarded");
            if (showConfirmDialog == 0)
            {
                Model.getInstance().setText("");
            }
        }
    }

    private String chooseFile()
    {
        JFileChooser chosenFile = new JFileChooser();
        int showOpenDialog = chosenFile.showOpenDialog(null);
        if (showOpenDialog == JFileChooser.APPROVE_OPTION)
        {
            return chosenFile.getSelectedFile().getAbsolutePath();
        }
        return "";
    }

    private void displayMessage(String message)
    {
        JOptionPane.showMessageDialog(null, message);
    }

    public void update(Observable o, Object arg)
    {
        if (arg == "display")
        {
            displayMessage(Model.getInstance().getDisplayMessage());
        }
    }

}
