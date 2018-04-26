package vn.uit.mobilestore.aws;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.uit.mobilestore.constants.Const;
import vn.uit.mobilestore.constants.MessageCode;
import vn.uit.mobilestore.exceptions.ApplicationException;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * Created by Linh Nguyen on 4/25/2018.
 */
@Component
public class S3UploadService {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private final Environment environment;

    private static String bucketName = "mobilestoreuit";
    private AmazonS3 s3client;

    @Autowired
    public S3UploadService(Environment environment) {
        this.environment = environment;
        bucketName = environment.getProperty(Const.S3_BUCKET_KEY);
        AWSCredentialsProvider provider = new DefaultAWSCredentialsProviderChain();

        s3client = AmazonS3ClientBuilder.standard().withRegion(Regions.AP_SOUTHEAST_1)
                .withCredentials(provider).build();
    }

    public String uploadToS3(MultipartFile multipartFile) {
        String fileName = "";
        try {
            LOG.info("Uploading file {0} to S3", multipartFile.getOriginalFilename());
            File file = convertMultiPartToFile(multipartFile);
            fileName = generateFileName(multipartFile);
            PutObjectRequest request = new PutObjectRequest(bucketName, fileName, file);
            request.setCannedAcl(CannedAccessControlList.PublicRead);
            s3client.putObject(request);
            file.delete();
        } catch (AmazonClientException | IOException ace) {
            LOG.error("Error upload", ace);
            throw new ApplicationException(MessageCode.UPLOAD_FAILED);
        }
        return s3client.getUrl(bucketName, fileName).toString();
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    private String generateFileName(MultipartFile multiPart) {
        return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
    }
}
