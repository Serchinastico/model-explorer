package com.etaoin.myopengltest.util.io;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class FileReader {

	public BufferedReader toBufferedReader(String path) throws FileNotFoundException {
		File directory = Environment.getExternalStorageDirectory();
		File file = new File(directory, path);
		InputStream inputStream = new FileInputStream(file);
		Reader reader = new InputStreamReader(inputStream);
		return new BufferedReader(reader);
	}
}
