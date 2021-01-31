package com.list.music.service;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jlefebure.spring.boot.minio.MinioException;

@Service
@Transactional
public class DataServerInitializer {

	@Autowired
	private AlbumService service;

	public void init() {
		System.out.println("Starting data images");

		System.out.println("Removendo imagens do servidor....");
		this.service.removeAllImages();

		System.out.println("Adicionando imagens no servidor....");
		for (File f : getResourceFolderFiles("images")) {
			if (!f.getName().contains(".")) {
				try {
					this.service.addImageInAlbum(f, f.getAbsoluteFile().getName());
				} catch (MinioException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

		System.out.println("Terminou");
	}

	private static File[] getResourceFolderFiles(String folder) {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		URL url = loader.getResource(folder);
		String path = url.getPath();
		return new File(path).listFiles();
	}
}
