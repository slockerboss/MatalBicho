package com.slockerboss.juegos.matalbicho;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Circle {
	
	GameView gameView;
	float cx = 0;
	float cy = 0;
	float radio = 0;
	private Paint paint;
	

	public Circle(GameView gv, float cx, float cy, float radio) {
		this.gameView = gv;
		this.cx = cx;
		this.cy = cy;
		this.radio = radio;
		
		paint = new Paint();
	

	}


	public void dibuja(Canvas canvas){
		paint.setColor(Color.BLUE);
		canvas.drawCircle(cx, cy, radio, paint);
		
	}
}
