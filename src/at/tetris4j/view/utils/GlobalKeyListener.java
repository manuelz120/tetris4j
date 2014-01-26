package at.tetris4j.view.utils;

import org.jnativehook.GlobalScreen;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import at.tetris4j.view.IView;

public class GlobalKeyListener implements NativeKeyListener {
	
	private IView view;
	
	public GlobalKeyListener(IView view){
		this.view = view;
	}
	
	private long delta;
	
    @Override
	public void nativeKeyPressed(NativeKeyEvent e) {
    		long now = System.currentTimeMillis();
    		if (now - delta < 300)	{
    			return;
    		}
    		
    		delta = System.currentTimeMillis();
    	
            switch(e.getKeyCode()){
            case NativeKeyEvent.VK_LEFT:
            	view.KeyPressed(TetrisKey.LEFT);
            	break;
            case NativeKeyEvent.VK_RIGHT:
            	view.KeyPressed(TetrisKey.RIGHT);
            	break;
            case NativeKeyEvent.VK_UP:
            	view.KeyPressed(TetrisKey.UP);
            	break;
            case NativeKeyEvent.VK_DOWN:
            	view.KeyPressed(TetrisKey.DOWN);
            	break;
            }
            
            if (e.getKeyCode() == NativeKeyEvent.VK_ESCAPE) {
                    GlobalScreen.unregisterNativeHook();
            }
    }

    @Override
	public void nativeKeyReleased(NativeKeyEvent e) {
            //System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
    }

    @Override
	public void nativeKeyTyped(NativeKeyEvent e) {
            //System.out.println("Key Typed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
    }
}