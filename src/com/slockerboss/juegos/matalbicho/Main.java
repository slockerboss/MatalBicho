package com.slockerboss.juegos.matalbicho;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class Main extends Activity implements OnTouchListener {
	GameView gameView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		gameView = new GameView(this);
		setContentView(gameView);

		gameView.setOnTouchListener(this);

	}

	public boolean onTouch(View v, MotionEvent event) {
		float x = event.getX();
		float y = event.getY();

		// vistaJuego.detenerJuego();
		gameView.comunicateXtouch(x);
		gameView.comunicateTtouch(y);

		return false;
	}
}