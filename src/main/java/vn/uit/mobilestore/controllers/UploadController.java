package vn.uit.mobilestore.controllers;

import org.hibernate.id.GUIDGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.uit.mobilestore.aws.S3UploadService;
import vn.uit.mobilestore.constants.Const;
import vn.uit.mobilestore.constants.URL;
import vn.uit.mobilestore.entities.Role;
import vn.uit.mobilestore.exceptions.ApplicationException;
import vn.uit.mobilestore.models.RoleModel;
import vn.uit.mobilestore.responses.ResponseModel;

import javax.validation.Valid;
import java.io.File;
import java.util.UUID;

/**
 * Created by Linh Nguyen on 4/25/2018.
 */
@RestController
@RequestMapping(URL.UPLOAD_CONTROLLER)
public class UploadController {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private S3UploadService uploadService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseModel<String> upload(@RequestParam("file") MultipartFile file) {
        ResponseModel<String> response = new ResponseModel<>();
        try {
            String storedUrl = uploadService.uploadToS3(file);
            response.setData(storedUrl);
            return response;
        } catch (ApplicationException ae) {
            LOG.error(Const.LOGGING_ERROR + " upload : {}", ae.getMessage());
            response.buildError(ae);
            return response;
        } finally {
            LOG.info(Const.LOGGING_CONTROLLER_END + " upload ");
        }
    }
}
