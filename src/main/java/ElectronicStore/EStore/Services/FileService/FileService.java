package ElectronicStore.EStore.Services.FileService;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface FileService {
    public String UploadImage(MultipartFile image,String path) throws IOException;
    InputStream getResource(String Path,String name) throws FileNotFoundException;
}
