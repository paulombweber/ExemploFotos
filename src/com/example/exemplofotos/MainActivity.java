package com.example.exemplofotos;

import java.io.File;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
	Button tirarFoto;
	Button abrirFoto;
	ImageView image;
	Uri uri;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tirarFoto = (Button) findViewById(R.id.buttonTirarFoto);
		abrirFoto = (Button) findViewById(R.id.buttonAbrirFoto);
		image = (ImageView) findViewById(R.id.imageView1);
//		uri = Uri.fromFile(new File(getCacheDir().getPath() 
//				+ "/" + new Date()));
//		
		setURI();
		
		tirarFoto.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				i.putExtra(MediaStore.EXTRA_OUTPUT, uri);
				
				startActivity(i);
			}
		});
		
		abrirFoto.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Bitmap img = BitmapFactory.decodeFile(uri.getPath());
				
				image.setImageBitmap(img);
				
			}
		});
		
		
	}

	private void setURI() {
		//Pega o diretorio de fotos
		File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		
		//nome do arquivo
		String name = dir.getPath() + "/" + System.currentTimeMillis() + ".jpg";
		
		uri = Uri.fromFile(new File(name));
	}

	
}
