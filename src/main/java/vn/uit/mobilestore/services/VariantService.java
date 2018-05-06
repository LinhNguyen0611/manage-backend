package vn.uit.mobilestore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.uit.mobilestore.constants.MessageCode;
import vn.uit.mobilestore.entities.ImageManager;
import vn.uit.mobilestore.entities.Item;
import vn.uit.mobilestore.entities.Variant;
import vn.uit.mobilestore.entities.Model;
import vn.uit.mobilestore.exceptions.ApplicationException;
import vn.uit.mobilestore.models.VariantModel;
import vn.uit.mobilestore.repositories.ImageManagerRepository;
import vn.uit.mobilestore.repositories.ModelRepository;
import vn.uit.mobilestore.repositories.VariantRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Linh Nguyen on 4/11/2018.
 */
@Service
public class VariantService extends BaseService <VariantRepository, Variant, Integer>{
    /**
     * Controller
     *
     * @param repository repository
     */
    @Autowired
    VariantService(VariantRepository repository, ModelRepository modelRepository, ImageManagerRepository imageManagerRepository) {
        super(repository);
        this.modelRepository = modelRepository;
        this.imageManagerRepository = imageManagerRepository;
    }

    private final ModelRepository modelRepository;

    private final ImageManagerRepository imageManagerRepository;

    @Transactional
    public Variant updateVariant(Integer id, VariantModel variantModel) {
        // Find item
        Variant variant = this.getById(id);
        //Validate ModelID
        Model model = modelRepository.findOne(variantModel.getModelID());
        if (model == null) {
            throw new ApplicationException(MessageCode.ERROR_MODEL_ID_NOT_FOUND);
        }
        updateImageList(variant, variantModel);
        // Update variant
        variant = variant.updateVariant(variantModel);
        variant.setImages(null);
        repository.save(variant);
        return variant;
    }

    private void updateImageList(Variant variant, VariantModel variantModel){
        // Update image
        List<ImageManager> oldImages = variant.getImages();
        List<String> newImages = variantModel.getImages();

        for (ImageManager oldImage : oldImages) {
            String imageURL = oldImage.getImageURL();
            // Delete if not existed in new list
            if (!newImages.contains(imageURL)) {
                imageManagerRepository.delete(oldImage);
            }
        }
        // Add new image manager if new image not found in old list
        for (String newImage : newImages) {
            boolean found = false;
            for (ImageManager oldImage : oldImages) {
                if (oldImage.getImageURL().equals(newImage)) {
                    found = true;
                }
            }
            if (!found) {
                ImageManager imageManager = new ImageManager();
                imageManager.setImageURL(newImage);
                imageManager.setVariantID(variant.getVariantId());
                imageManagerRepository.save(imageManager);
            }
        }
    }

    @Transactional
    public Variant saveVariant(VariantModel variantModel) {
        Variant variant = variantModel.toEntity();
        // Save variant
        Model model = modelRepository.findOne(variant.getModelID());
        if (model == null) {
            throw new ApplicationException(MessageCode.ERROR_MODEL_ID_NOT_FOUND);
        }
        variant = this.saveData(variant);
        // Save image
        List<String> images = variantModel.getImages();
        for (String imageUrl : images) {
            ImageManager imageManager = new ImageManager();
            imageManager.setImageURL(imageUrl);
            imageManager.setVariantID(variant.getVariantId());
            imageManagerRepository.saveAndFlush(imageManager);
        }
        return variant;
    }

    public Page<Item> listItemByVariantId(Integer id, Integer page, Integer size) {
        PageRequest pageRequest = new PageRequest(page, size);

        return repository.listItemByVariantId(id, pageRequest);
    }
}
