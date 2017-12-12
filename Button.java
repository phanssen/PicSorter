import java.lang.reflect.Method;
import javax.swing.JButton;

public abstract class Button extends JButton{
	
	public Button( String text){
		super(text); //new JButton object 
	}
	
	public abstract void update(Object obj);


}
