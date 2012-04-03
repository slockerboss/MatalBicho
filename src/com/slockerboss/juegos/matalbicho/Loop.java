package com.slockerboss.juegos.matalbicho;

import android.graphics.Canvas;
import android.graphics.Color;

public class Loop extends Thread {
	private static final int FPS = 10;
	private GameView gameView;
	private boolean running = true;
	
	long ticksPS = 1000 / FPS;
    long startTime;
    long sleepTime;

	public Loop(GameView gv) {

		this.gameView = gv;
	}

	@Override
	public void run() {
		while (running) {

			Canvas c = null;
			startTime = System.currentTimeMillis();
			try {
				c = gameView.getHolder().lockCanvas();
				synchronized (gameView.getHolder()) {
					gameView.onDraw(c);
				}
			} finally {
				gameView.getHolder().unlockCanvasAndPost(c);
			}
			
			//esta parte del codigo es para que no vaya a trompicones
			sleepTime = ticksPS-(System.currentTimeMillis() - startTime);
			if (sleepTime > 0){
				try {
					sleep (sleepTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else
				try {
					sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		
	}

	public void setRunning(boolean b) {
		this.running = b;

	}

}
