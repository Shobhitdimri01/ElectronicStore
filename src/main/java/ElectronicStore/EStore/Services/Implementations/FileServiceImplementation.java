package ElectronicStore.EStore.Services.Implementations;

import ElectronicStore.EStore.Exceptions.BadAPIRequest;
import ElectronicStore.EStore.Services.FileService.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;


//Service to upload image and get image
@Service
public class FileServiceImplementation implements FileService {

    private Logger log = LoggerFactory.getLogger(FileServiceImplementation.class);
    @Override
    public String UploadImage(MultipartFile image, String path) throws IOException {
        String OriginalFilename = image.getOriginalFilename();
        log.info("Filename is : {}",OriginalFilename);
        String filename = UUID.randomUUID().toString();
        String getExtention = OriginalFilename.substring(OriginalFilename.lastIndexOf("."));
        String newFileName = filename+getExtention;
        String FileNameWithPath = path+ File.separator+newFileName;
        if (getExtention.equalsIgnoreCase(".jpeg")||getExtention.equalsIgnoreCase(".jpg")||getExtention.equalsIgnoreCase(".png")){

            File folder = new File(path);

            if(!folder.exists()) folder.mkdirs();

            Files.copy(image.getInputStream(), Paths.get(FileNameWithPath));

            return newFileName;


        }else{
            throw new BadAPIRequest("file with this extention "+getExtention+" not allowed");
        }
    }

    @Override
    public InputStream getResource(String Path, String name) throws FileNotFoundException {
        String FileNameWithPath = Path+File.separator+name;
        InputStream file = new FileInputStream(FileNameWithPath);
        return file;
    }
}
