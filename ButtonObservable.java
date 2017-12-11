import java.util.ArrayList;

public class ButtonObservable{
	private ArrayList<Button> buttons;
	
	public ButtonObservable(){
		buttons = new ArrayList<Button>();
	}
	
	//add observer
	public void addObserver(Button but){
		buttons.add(but);
	}
	
	//remove observer
	public void removeObserver(Button but){
		buttons.remove(but);
	}
	
	//notify observer
	public void notifyObserver(Display obj){
		for(Button but: buttons){
			but.update(obj);
		}
	}

}
