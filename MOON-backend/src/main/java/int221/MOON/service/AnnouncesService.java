package int221.MOON.service;

import int221.MOON.Dto.*;
import int221.MOON.ListMapper;
import int221.MOON.Enum;
import int221.MOON.entities.Announces;
import int221.MOON.entities.Categories;
import int221.MOON.repository.AnnouncesRepository;
import int221.MOON.repository.CategoriesRepository;
import int221.MOON.validation.ValidDateValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.ZonedDateTime;
import java.util.ArrayList;
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



    public AnnouncesDetailDto getAnnouncesById(Integer annId, Boolean count) {
        Announces m = announcesRepository.findById(annId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Announcement id " + annId + "does not exist !!!"));
        if (count) {
            m.setCount(m.getCount()+1);
            announcesRepository.saveAndFlush(m);
        }
        return modelMapper.map(m, AnnouncesDetailDto.class);

    }

    public List<AnnouncesDto> getAnnounces(String mode) {
        try {
            List<Announces> m = announcesRepository.findAll();
            ZonedDateTime nowDate = ZonedDateTime.now();
            m.sort((a, b) -> b.getId() - a.getId());
            List<Announces> listReturn = new ArrayList<>();
            if (mode.equals("admin")) {
                listReturn = m;
            } else if (mode.equals("close")) {
                listReturn = m.stream()
                        .filter(a ->
                                a.getAnnouncementDisplay().equals(Enum.Y) &&
                                        (a.getCloseDate() != null && a.getCloseDate().isBefore(nowDate)))
                        .collect(Collectors.toList());
            } else if (mode.equals("active")) {
                listReturn = m.stream()
                        .filter(a ->
                                a.getAnnouncementDisplay().equals(Enum.Y) &&
                                        (a.getPublishDate() == null || a.getPublishDate().isBefore(nowDate)) &&
                                        (a.getCloseDate() == null || a.getCloseDate().isAfter(nowDate)))
                        .collect(Collectors.toList());
            }
            return listMapper.mapList(listReturn, AnnouncesDto.class, modelMapper);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "cannot request invalid param " + e.getMessage());
        }
    }


    public EditAnnDto createAnnouncement(InputAnnouncesDTO announces) {
        Categories category = categoriesRepository.findById(announces.getCategoryId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categories id " + announces.getCategoryId() + " does not exist !!!"));
        if (announces.getAnnouncementDisplay() == null) {
            announces.setAnnouncementDisplay("N");

        }

        Announces announcement = modelMapper.map(announces, Announces.class);
        announcement.setCategories(category);
        announcement.setCount(0);
        announcesRepository.saveAndFlush(announcement);
        return modelMapper.map(announcement, EditAnnDto.class);
    }


    public UpdateDto updateAnnouncement(InputAnnouncesDTO announces, Integer announcementId) {
        Categories categories = categoriesRepository.findById(announces.getCategoryId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categories id " + announces.getCategoryId() + "does not exist !!!"));
        if (announces.getAnnouncementDisplay() == null) {
            announces.setAnnouncementDisplay("N");
        }
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
    public PageDTO<AnnouncesDto> getAnnouncementPage(String mode, int page, int size, int category ) {
        try {
            List<AnnouncesDto> announcesList = getAnnounces(mode);
            List<AnnouncesDto> returnList;
            Pageable pageable = PageRequest.of(page, size);
            if (category == 0) {
                returnList = announcesList;
            } else {
                Categories categories = categoriesService.getIdCategories(category);
                returnList = announcesList.stream().filter(a -> a.getAnnouncementCategory() == categories.getAnnouncementCategory()).toList();
            }
            Page<AnnouncesDto> listPage = listMapper.convertToPage(returnList, pageable);
            return listMapper.toPageDTO(listPage, AnnouncesDto.class, modelMapper);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "cannot request invalid param "  + e.getMessage());
        }
    }


}
