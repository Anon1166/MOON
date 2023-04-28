package int221.MOON.service;

import int221.MOON.Dto.AnnouncesDetailDto;
import int221.MOON.Dto.AnnouncesDto;
import int221.MOON.ListMapper;
import int221.MOON.entities.Announces;
import int221.MOON.repository.AnnouncesRepository;
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
    private ModelMapper modelMapper;
    @Autowired
    private ListMapper listMapper;



    public AnnouncesDetailDto getAnnouncesById(Integer annId) {
        Announces m = announcesRepository.findById(annId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Announcement id " + annId + "does not exist !!!"));
        return modelMapper.map(m, AnnouncesDetailDto.class);


    }

    public List<AnnouncesDto> getAnnounces() {
        List<Announces> m = announcesRepository.findAll();
        m.sort((a,b) -> b.getAnnouncementId() - a.getAnnouncementId());
        return listMapper.mapList(m,AnnouncesDto.class,modelMapper);
    }




}
