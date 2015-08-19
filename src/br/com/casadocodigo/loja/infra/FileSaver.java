package br.com.casadocodigo.loja.infra;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileSaver {
	@Autowired
	private HttpServletRequest request;

	public String write(String baseFolder, MultipartFile file) {
		baseFolder = "";
		String realPath = request.getServletContext().getRealPath("/" + baseFolder);
		try {
			String path = realPath + file.getOriginalFilename();
			file.transferTo(new File(path));
			System.out.println(path);
			return baseFolder + "/" + file.getOriginalFilename();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
