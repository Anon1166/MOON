package int221.MOON.service;

import int221.MOON.Dto.*;
import int221.MOON.ListMapper;
import int221.MOON.Enum;
import int221.MOON.entities.Announces;
import int221.MOON.entities.Categories;
import int221.MOON.repository.AnnouncesRepository;
import int221.MOON.repository.CategoriesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnnouncesService {
    @Autowired
    private AnnouncesRepository announcesRepository;
    @Autowired
    private CategoriesRepository categoriesRepository;
    @Autowired
    private CategoriesService categoriesService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ListMapper listMapper;


    public AnnouncesDetailDto getAnnouncesById(Integer annId) {
        Announces m = announcesRepository.findById(annId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Announcement id " + annId + "does not exist !!!"));
        return modelMapper.map(m, AnnouncesDetailDto.class);


    }

    public List<AnnouncesDto> getAnnounces(String mode) {
        List<Announces> m = announcesRepository.findAll();
        ZonedDateTime nowDate = ZonedDateTime.now();
        m.sort((a, b) -> b.getId() - a.getId());
        if (mode.equals("admin")) {
            return listMapper.mapList(m, AnnouncesDto.class, modelMapper);

        } else if (mode.equals("close")) {
            List<Announces> list = m.stream()
                    .filter(a ->
                            a.getAnnouncementDisplay().equals(Enum.Y) &&
                                    (a.getCloseDate() == null ||
                                            (a.getCloseDate() != null && a.getCloseDate().toEpochSecond() < nowDate.toEpochSecond())))
                    .collect(Collectors.toList());
            return listMapper.mapList(list, AnnouncesDto.class, modelMapper);

        } else if (mode.equals("active")) {
            List<Announces> list = m.stream()
                    .filter(a -> a.getAnnouncementDisplay().equals(Enum.Y) &&
                            (a.getCloseDate() != null && a.getCloseDate().toEpochSecond() > nowDate.toEpochSecond()))
                    .collect(Collectors.toList());
            return listMapper.mapList(list, AnnouncesDto.class, modelMapper);

        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "cannot request invalid param");
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


    public UpdateDto updateAnnouncement(InputAnnouncesDTO announces, Integer announcementId) {
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

    public void deleteAnnouncement(Integer announcementId) {
        Announces b = announcesRepository.findById(announcementId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "announcement id " + announcementId + "does not exist !!!"));
        announcesRepository.delete(b);
    }


    //PAGE
    public PageDTO<AnnouncesDto> getAnnouncementPage(String mode, int page, int size, int category) {
        try {
            List<AnnouncesDto> announcesList = getAnnounces(mode);
            List<AnnouncesDto> returnList;
            Pageable pageable = PageRequest.of(page, size);
            System.out.println("try");
            if (category == 0) {
                System.out.println("cat == 0");
                returnList = announcesList;
            } else {
                Categories categories = categoriesService.getIdCategories(category);
                System.out.println("cat != 0");
                returnList = announcesList.stream().filter(a -> a.getAnnouncementCategory() == categories.getAnnouncementCategory()).toList();
            }
            Page<AnnouncesDto> listPage = listMapper.convertToPage(returnList, pageable);
            return listMapper.toPageDTO(listPage, AnnouncesDto.class, modelMapper);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "cannot request invalid param");
        }
    }


}
