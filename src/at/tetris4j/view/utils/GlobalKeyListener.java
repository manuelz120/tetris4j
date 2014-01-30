package at.tetris4j.view.utils;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import at.tetris4j.view.IConsoleView;

public class GlobalKeyListener implements NativeKeyListener {
	
	private IConsoleView view;
	
	public GlobalKeyListener(IConsoleView view){
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
            	view.keyPressed(TetrisKey.LEFT);
            	break;
            case NativeKeyEvent.VK_RIGHT:
            	view.keyPressed(TetrisKey.RIGHT);
            	break;
            case NativeKeyEvent.VK_UP:
            	view.keyPressed(TetrisKey.UP);
            	break;
            case NativeKeyEvent.VK_DOWN:
            	view.keyPressed(TetrisKey.DOWN);
            	break;
            case NativeKeyEvent.VK_ESCAPE:
            	view.keyPressed(TetrisKey.STOP);
            	break;
            case NativeKeyEvent.VK_1:
            	view.keyPressed(TetrisKey.ONE);
            	break;
            case NativeKeyEvent.VK_2:
            	view.keyPressed(TetrisKey.TWO);
            	break;
            }
    }

    @Override
	public void nativeKeyReleased(NativeKeyEvent e) {}

    @Override
	public void nativeKeyTyped(NativeKeyEvent e) {}
}
