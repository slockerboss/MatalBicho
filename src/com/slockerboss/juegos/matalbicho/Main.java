package com.slockerboss.juegos.matalbicho;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author Luis Slocker
 * @version 1.0
 * 
 *          <p>
 *          Main is the main class of this simple VideoGame which is designed to
 *          learn about Android Game (and no Game) development.
 *          </p>
 *          <p>
 *          The Goal of this Game is killing bad "bicho" smashing it
 *          </p>
 *          <p>
 *          So, in Main I create a new GameView, and set the main window of the
 *          game
 *          </p>
 * 
 */

public class Main extends Activity implements OnTouchListener {
	GameView gameView;
	TextView textView;
	ImageView imageView;

	/**
	 * Constructor of Main, that extends Activity. Here I set de current View
	 * and add a listener as trigger
	 * 
	 * @param Bundle
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tocaparaempezar);

		gameView = new GameView(this, 4);
		textView = (TextView) findViewById(R.id.TocaParaEmpezar);

		imageView = (ImageView) findViewById(R.id.MoteroFondo);
		imageView.setOnTouchListener(this);
		// imageView.setImageResource(R.drawable.motero_fondo2);

		imageView.setBackgroundResource(R.drawable.motero_fondo2);

	}

	/**
	 * Method of interface onTouchLister from android
	 * @param v
	 *            The view that receives the event.
	 * @param event
	 *            The event.
	 * @return boolean. False when you don't want the "up" action is registered (when you
	 *         up your finger)
	 */
	public boolean onTouch(View v, MotionEvent event) {
		this.setContentView(gameView);
		return false;
	}
}