package com.slockerboss.juegos.matalbicho;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView {
	private static final int NUMSPRITES = 4;
	private int numSprites;
	Loop loop;
	SurfaceHolder sfHolder;

	Bitmap miBitmap;
	Paint paint;

	float xtouch = 0;
	float ytouch = 0;

	private List<Sprite> sprites = new ArrayList<Sprite>();

	private Circle circulo;
	private boolean ultimo = false;

	/* ### CONSTRUCTORS ### */
	public GameView(Context context) {
		super(context);
		paint = new Paint();
		loop = new Loop(this);
		numSprites = NUMSPRITES;
		sfHolder = getHolder();
		sfHolder.addCallback(new SurfaceHolder.Callback() {

			public void surfaceCreated(SurfaceHolder holder) {
				crearSprites(NUMSPRITES);

				loop.setRunning(true);
				loop.start();

			}

			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {
				// TODO Auto-generated method stub

			}

			public void surfaceDestroyed(SurfaceHolder holder) {
				boolean retry = true;
				loop.setRunning(false);
				while (retry) {
					try {
						loop.join();
						retry = false;
					} catch (InterruptedException e) {
					}
				}

			}
		});

	}

	protected void crearOdestruir() {

		if (numSprites > 0 && !ultimo) {

			if (xtouch > this.getWidth() / 2) {
				eliminarSprites(1);
				numSprites--;
				if (numSprites == 1) {
					ultimo = true;
				}
			}
			// else crearSprites(1);

		} else if (xtouch < this.getWidth() / 2 && numSprites < 4 && xtouch !=0) {
			crearSprites(1);
			numSprites++;
			ultimo = false;
		}
		;
		xtouch = 0;
	}

	private void eliminarSprites(int i) {
		// implementar borrar varios
		sprites.remove(1);
	}

	private void crearSprites(int numsprites2) {
		miBitmap = BitmapFactory
				.decodeResource(getResources(), R.drawable.bad1);

		for (int i = 0; i < numsprites2; i++) {
			sprites.add(new Sprite(this, miBitmap));
		}

	}

	/* ### SETTERS/GETTERS ### */
	public void comunicateXtouch(float x) {
		this.xtouch = x;

	}

	public void comunicateTtouch(float y) {
		this.ytouch = y;

	}

	/* ### EL RESTO ### */
	@Override
	protected void onDraw(Canvas canvas) {
		circulo = new Circle(this, xtouch, ytouch, 25);
		canvas.drawColor(Color.BLACK);
		paint.setColor(Color.RED);
		canvas.drawLine(this.getWidth() / 2, 0, this.getWidth() / 2,
				this.getHeight(), paint);

		if (xtouch != 0) {
			circulo.dibuja(canvas);
		}
		crearOdestruir();
		for (Sprite sprite : sprites) {
			sprite.dibuja(canvas);
		}

	}

	public void detenerJuego() {
		loop.setRunning(false);

	}

	public void arrancarJuego() {
		loop.setRunning(true);

	}

}
