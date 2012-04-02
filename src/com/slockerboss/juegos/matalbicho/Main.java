package com.slockerboss.juegos.matalbicho;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.TextView;

public class Main extends Activity implements OnTouchListener {
	GameView gameView;
	TextView textView;
	ImageView imageView;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tocaparaempezar);
		
		gameView = new GameView(this, 4);
		textView = (TextView) findViewById(R.id.TocaParaEmpezar);
		
		imageView = (ImageView) findViewById(R.id.MoteroFondo);
		imageView.setOnTouchListener(this);
//		imageView.setImageResource(R.drawable.motero_fondo2);
		
		imageView.setBackgroundResource(R.drawable.motero_fondo2);
		
		

	}

	public boolean onTouch(View v, MotionEvent event) {
		this.setContentView(gameView);
		return false;
	}
}