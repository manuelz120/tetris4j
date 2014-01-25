package at.tetris4j.view.utils;

import org.jnativehook.GlobalScreen;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import at.tetris4j.view.View;

public class GlobalKeyListener implements NativeKeyListener {
	
	private View view;
	
	public GlobalKeyListener(View view){
		this.view = view;
	}
	
    @Override
	public void nativeKeyPressed(NativeKeyEvent e) {
            //System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));

            switch(e.getKeyCode()){
            case NativeKeyEvent.VK_LEFT:
            	view.setPosX(view.getPosX()-1);
            	break;
            case NativeKeyEvent.VK_RIGHT:
            	view.setPosX(view.getPosX()+1);
            	break;
            case NativeKeyEvent.VK_UP:
            	break;
            case NativeKeyEvent.VK_DOWN:
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
