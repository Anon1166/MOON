package int221.MOON.service;

import int221.MOON.Dto.*;
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


    public EditAnnDto createAnnouncement(InputAnnouncesDTO announces) {
        Categories category = categoriesRepository.findById(announces.getCategoryId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categories id " + announces.getCategoryId() + "does not exist !!!"));
        Announces announcement = modelMapper.map(announces, Announces.class);
        announcement.setCategories(category);
//        Announces entity = new Announces()
        announcesRepository.saveAndFlush(announcement);
        return modelMapper.map(announcement, EditAnnDto.class);
    }


    public UpdateDto updateAnnouncement(InputAnnouncesDTO announces, Integer announcementId){
        Categories categories = categoriesRepository.findById(announces.getCategoryId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categories id " + announces.getCategoryId() + "does not exist !!!"));
        Announces announcement = modelMapper.map(announces, Announces.class);
        Announces a = announcesRepository.findById(announcementId).orElseThrow(
                () -> new RuntimeException("can not find id !!!"));
        a.setAnnouncementTitle(announcement.getAnnouncementTitle());
        a.setPublishDate(announcement.getPublishDate());
        a.setAnnouncementDescription(announcement.getAnnouncementDescription());
        a.setCloseDate(announcement.getCloseDate());
        a.setAnnouncementDisplay(announcement.getAnnouncementDisplay());
        a.setCategories(categories);
        announcesRepository.saveAndFlush(a);
        return modelMapper.map(a, UpdateDto.class);
    }

    public void deleteAnnouncement(Integer announcementId){
        Announces b = announcesRepository.findById(announcementId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "announcement id " + announcementId + "does not exist !!!"));


        announcesRepository.delete(b);

    }



}
