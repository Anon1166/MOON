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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<AnnouncesDto> getAnnounces(String mode) {
        List<Announces> m = announcesRepository.findAll();
        ZonedDateTime nowDate = ZonedDateTime.now();
        m.sort((a, b) -> b.getId() - a.getId());
        if (mode.equals("admin")) {
            System.out.println("mode : "+mode);
            return listMapper.mapList(m, AnnouncesDto.class, modelMapper);
        } else if (mode.equals("close")) {


            List<Announces> list = m.stream()
                    .filter(a -> a.getAnnouncementDisplay().equals(Enum.Y) &&
                    a.getCloseDate() == null ||
                    (a.getCloseDate() != null && a.getCloseDate().toEpochSecond() < nowDate.toEpochSecond()))
                    .collect(Collectors.toList());
            return listMapper.mapList(list, AnnouncesDto.class, modelMapper);


        } else if (mode.equals("active")) {
            System.out.println("mode : "+mode);

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

    public PageDTO<AnnouncesDto> getAnnouncementPage(String mode, int page, int size) {
        Page<Announces> announcesListPage = announcesRepository.findAll(PageRequest.of(page, size));
        List<Announces> announcesList = announcesRepository.findAll();
        announcesList.sort((a, b) -> b.getId() - a.getId());
        ZonedDateTime nowDate = ZonedDateTime.now();

        System.out.println("mode : "+mode);
        if (mode.equals("active")) {
            System.out.println("active----------------");
            List<Announces> announcesContent = announcesList
                    .stream()
                    .filter(a -> a.getAnnouncementDisplay().equals("Y") & a.getCloseDate() == null || (a.getCloseDate() != null && a.getCloseDate().toEpochSecond() > nowDate.toEpochSecond()))
                    .collect(Collectors.toList());
            Pageable pageable = PageRequest.of(page, size);
            int start = (int) pageable.getOffset();
            int end = Math.min((start + pageable.getPageSize()), announcesContent.size());
            Page<Announces> announcesPage = new PageImpl<>(announcesContent.subList(start, end), PageRequest.of(page, size), announcesContent.size());
            return listMapper.toPageDTO(announcesPage, AnnouncesDto.class, modelMapper);


        } else if (mode.equals("close")) {
            System.out.println("close----------------");
            List<Announces> announcesContent = announcesList
                    .stream()
                    .filter(a -> a.getAnnouncementDisplay().equals("Y") & (a.getCloseDate() != null && a.getCloseDate().toEpochSecond() < nowDate.toEpochSecond()))
                    .collect(Collectors.toList());
            PageRequest pageRequest = PageRequest.of(page, size);
            int start = (int) pageRequest.getOffset();
            int end = Math.min((start + pageRequest.getPageSize()), announcesContent.size());
            Page<Announces> announcesPage = new PageImpl<>(announcesContent.subList(start, end), PageRequest.of(page, size), announcesContent.size());
            return listMapper.toPageDTO(announcesPage, AnnouncesDto.class, modelMapper);
        } else if (mode.equals("admin")) {
            System.out.println("admin----------------");
            return listMapper.toPageDTO(announcesListPage, AnnouncesDto.class, modelMapper);
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "cannot request invalid param");
    }


}
