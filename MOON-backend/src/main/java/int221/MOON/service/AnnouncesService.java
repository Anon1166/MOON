package int221.MOON.service;

import int221.MOON.Dto.AnnouncesDetailDto;
import int221.MOON.Dto.AnnouncesDto;
import int221.MOON.Dto.EditAnnouncesDTO;
import int221.MOON.ListMapper;
import int221.MOON.entities.Announces;
import int221.MOON.entities.Categories;
import int221.MOON.repository.AnnouncesRepository;
import int221.MOON.repository.CategoriesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class AnnouncesService {
    @Autowired
    private AnnouncesRepository announcesRepository;
    @Autowired
    private CategoriesRepository categoriesRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ListMapper listMapper;



    public AnnouncesDetailDto getAnnouncesById(Integer annId) {
        Announces m = announcesRepository.findById(annId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Announcement id " + annId + "does not exist !!!"));
        return modelMapper.map(m, AnnouncesDetailDto.class);


    }

    public List<AnnouncesDto> getAnnounces() {
        List<Announces> m = announcesRepository.findAll();
        m.sort((a,b) -> b.getId() - a.getId());
        return listMapper.mapList(m,AnnouncesDto.class,modelMapper);
    }


    public  Announces createAnnouncement(EditAnnouncesDTO announces) {
        Categories category = categoriesRepository.findById(announces.getCategoriesCategoryId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categories id " + announces.getCategoriesCategoryId() + "does not exist !!!"));
        Announces announcement = modelMapper.map(announces, Announces.class);
        announcement.setCategories(category);
//        Announces entity = new Announces()
        return announcesRepository.saveAndFlush(announcement);
    }


    public  Announces updateAnnouncement(EditAnnouncesDTO announces, Integer announcementId){
        Categories categories = categoriesRepository.findById(announces.getCategoriesCategoryId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categories id " + announces.getCategoriesCategoryId() + "does not exist !!!"));
        Announces announcement = modelMapper.map(announces, Announces.class);
        Announces a = announcesRepository.findById(announcementId).orElseThrow(
                () -> new RuntimeException("can not find id !!!"));
        a.setAnnouncementTitle(announcement.getAnnouncementTitle());
        a.setPublishDate(announcement.getPublishDate());
        a.setAnnouncementDescription(announcement.getAnnouncementDescription());
        a.setCloseDate(announcement.getCloseDate());
        a.setAnnouncementDisplay(announcement.getAnnouncementDisplay());
        a.setCategories(categories);
        return  announcesRepository.saveAndFlush(a);
    }

    public void deleteAnnouncement(Integer announcementId){
        Announces b = announcesRepository.findById(announcementId).orElseThrow(
                () -> new RuntimeException("can not find id !!!"));


        announcesRepository.delete(b);

    }



}
