package int221.MOON.controllers;

import int221.MOON.Dto.AnnouncesDetailDto;
import int221.MOON.Dto.AnnouncesDto;
import int221.MOON.Dto.EditAnnouncesDTO;
import int221.MOON.entities.Announces;
import int221.MOON.service.AnnouncesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/announcements")
public class AnnouncesController {
    @Autowired
    private AnnouncesService announcesService;

    @GetMapping("")
    public List<AnnouncesDto> getAnnounces(){
        return announcesService.getAnnounces();
    }

    @GetMapping("/{annId}")
    public AnnouncesDetailDto getAnnouncesById(@PathVariable Integer annId){
        return announcesService.getAnnouncesById(annId);
    }

    @PostMapping("/add")
    public Announces createAnnouncement(@RequestBody EditAnnouncesDTO announces){
        return  announcesService.createAnnouncement(announces);
    }

    @PutMapping("/{announcementId}")
    public Announces updateAnnouncement(@PathVariable Integer announcementId , @RequestBody EditAnnouncesDTO announces){
        return  announcesService.updateAnnouncement(announces,announcementId);
    }

    @DeleteMapping("/{announcementId}")
    public void deleteAnnouncement(@PathVariable Integer announcementId ){
        announcesService.deleteAnnouncement(announcementId);
    }
}
