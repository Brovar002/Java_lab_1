import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener
{
    MenuBar menuBar;

    public KeyHandler(MenuBar menuBar)
    {
        this.menuBar = menuBar;
    }

    @Override
    public void keyPressed(KeyEvent keyEvent)
    {
        if ((keyEvent.getKeyCode() == KeyEvent.VK_S) &&
                ((keyEvent.getModifiers() & KeyEvent.CTRL_MASK) != 0))
        {
            menuBar.saveAsFile();
        }
        else if ((keyEvent.getKeyCode() == KeyEvent.VK_O) &&
                ((keyEvent.getModifiers() & KeyEvent.CTRL_MASK) != 0))
        {
            menuBar.openFile();
        }
        else if ((keyEvent.getKeyCode() == KeyEvent.VK_N) &&
                ((keyEvent.getModifiers() & KeyEvent.CTRL_MASK) != 0))
        {
            menuBar.createNewEditorWorkspace();
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent)
    {
    }

    @Override
    public void keyReleased(KeyEvent keyEvent)
    {
    }
}
