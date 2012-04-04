package com.slockerboss.juegos.matalbicho;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * @author Luis Slocker
 * @version 1.0
 * 
 *          The GameView class is the custom view of the game, it extends
 *          SurfaceView, (the games often extends SurfaceView, and not View) @ *
 */
public class GameView extends SurfaceView {
	/** Description of numSprites */
	int numSprites = 4;
	/** Description of loop */
	Loop loop;
	SurfaceHolder sfHolder;

	Bitmap miBitmap;
	Bitmap miBitmap2;
	Bitmap sangre;
	Paint paint;

	int xtouch = 0;
	int ytouch = 0;
	
	List<TempSprite> tempSprites = new ArrayList<TempSprite>();
	List<Sprite> sprites = new ArrayList<Sprite>();

	Circle circulo;
	boolean ultimo = false;
	private int cont;
	

	/* ### CONSTRUCTORS ### */
	public GameView(Context context) {
		super(context);
	}

	public GameView(Context context, int numSprites) {
		super(context);
		this.numSprites = numSprites;
		paint = new Paint();
		loop = new Loop(this);

		sfHolder = getHolder();
	
		sfHolder.addCallback(new SurfaceHolder.Callback() {

			public void surfaceCreated(SurfaceHolder holder) {
				crearSprites(GameView.this.numSprites);
				
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
		
		this.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View gameView, MotionEvent event) {
				xtouch = (int) event.getX();
				ytouch = (int) event.getY();
				crearOdestruir();
				return false;

			}
		});

	}

	/* ### EL RESTO ### */
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		circulo = new Circle(this, xtouch, ytouch, 15);

		paint.setColor(Color.RED);
		canvas.drawLine(this.getWidth() / 2, 0, this.getWidth() / 2,
				this.getHeight(), paint);

		if (xtouch != 0) {
			circulo.dibuja(canvas);
		}

		xtouch = 0;

		for (Sprite sprite : sprites) {
			sprite.dibuja(canvas);
		}
		for (TempSprite tsprite : tempSprites) {
			tsprite.dibuja(canvas);
		}

	}

	protected void crearOdestruir() {

		if (xtouch > this.getWidth() / 2) {
			if (!ultimo) {
				synchronized (this.getHolder()) {

				}
				for (cont = sprites.size() - 1; cont >= 0; cont--) {
					Sprite spriteTemp = sprites.get(cont);
					if (spriteTemp.isColision(xtouch, ytouch)) {
						sprites.remove(cont);
						
						tempSprites.add(new TempSprite(this,tempSprites, sangre, xtouch, ytouch));
						numSprites--;
						break; // Este Break es para que no mate a dos bichos a
								// la vez
					}
				}

				if (numSprites == 1) {
					ultimo = true;

				}
			}

		} else if (numSprites < 8 && xtouch != 0) {
			crearSprites(1);
			numSprites++;
			ultimo = false;
		}

	}

	private void eliminarSprites(int numSprites) {
		for (int i = 0; i < numSprites; i++) {
			sprites.remove(1);
		}

	}

	private void crearSprites(int numSprites) {
		miBitmap2 = BitmapFactory.decodeResource(getResources(),
				R.drawable.malo01);
		miBitmap = BitmapFactory
				.decodeResource(getResources(), R.drawable.bad1);
		
		
		
		
		sangre = BitmapFactory.decodeResource(getResources(),
				R.drawable.sangre);
//		tempSprites.add(new TempSprite(GameView.this, sangre, xtouch, ytouch));
		
		
		
		
		int M = 4;
		int N = 1;

		int valorEntero = (int) Math.floor(Math.random() * (N - M + 1) + M); // Valor
																				// entre
																				// M
																				// y
																				// N,
																				// ambos
																				// incluidos.
		if (valorEntero % 2 == 1) {
			for (int i = 0; i < numSprites; i++) {
				sprites.add(new Sprite(this, miBitmap2));
			}
		} else {
			for (int i = 0; i < numSprites; i++) {
				sprites.add(new Sprite(this, miBitmap));
			}
		}

	}

	public void detenerJuego() {

	}

	public void detenerJuego(String string) {

		loop.setRunning(false);
		// this.mostrarAlerta(string);

	}

	private void mostrarAlerta(String string) {

	}

	public void arrancarJuego() {
		loop.setRunning(true);

	}

	/* ### SETTERS/GETTERS ### */
	public void comunicateXtouch(float x) {
		this.xtouch = (int) x;

	}

	public void comunicateTtouch(float y) {
		this.ytouch = (int) y;

	}

}
