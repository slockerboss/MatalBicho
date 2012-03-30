package com.slockerboss.juegos.matalbicho;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Sprite {

	private static final int NUMCOLUM = 3;
	private static final int NUMFILAS = 4;
	Bitmap bmp;
	GameView gv;
	private boolean rightDirection = true;
	private boolean downDirection = true;
	private int x;
	private int y;
	private Random rnd;
	private int velInicialy = 0;
	private int velInicialx = 0;
	private Paint paint;

	private Rect rectangulo_fuente;
	private Rect rectangulo_destino;
	private int anchoSprite;
	private int altoSprite;
	private int currentFrame = 0;

	public Sprite(GameView gv, Bitmap bmp) {
		this.bmp = bmp;
		this.gv = gv;
		rnd = new Random();
		velInicialy = rnd.nextInt(7);
		velInicialx = rnd.nextInt(7);

		paint = new Paint();
		paint.setColor(Color.BLUE);

		this.anchoSprite = bmp.getWidth() / NUMCOLUM;
		this.altoSprite = bmp.getHeight() / NUMFILAS;

	}

	public void dibuja(Canvas canvas) {
		actualizarEstado();
		int srcX = currentFrame * this.altoSprite;
		rectangulo_fuente = new Rect(srcX, altoSprite, srcX + anchoSprite,
				2 * altoSprite);
		rectangulo_destino = new Rect(x, y, x + anchoSprite, y + altoSprite);
		// drawBitmap, en este caso pinto rectangulos, el rectangulo fuente,
		// agarra un rectangulo del bitmap y lo pinta en un rectangulo destino
		// del canvas
		
		canvas.drawBitmap(bmp, rectangulo_fuente, rectangulo_destino, null);

	}

	private void actualizarEstado() {

		// int srcY = 1 * this.altoSprite;

		if ((x < gv.getWidth() - this.altoSprite) && rightDirection) {
			x = x + velInicialx;
			rightDirection = true;

		} else {
			rightDirection = false;
			x = x - velInicialx;
			if (x < 1) {
				rightDirection = true;
			}

		}
		if ((y < gv.getHeight() - this.altoSprite) && downDirection) {
			y = y + velInicialy;
			downDirection = true;

		} else {
			downDirection = false;
			y = y - velInicialy;
			if (y < 1) {
				downDirection = true;
			}

		}
		currentFrame = ++currentFrame % NUMCOLUM; // recuerda, current frame, es
													// el muñequito q estas
													// mostrando cada frame
	}

	private void cambiarColor(int color) {
		paint.setColor(color);

	}

}
