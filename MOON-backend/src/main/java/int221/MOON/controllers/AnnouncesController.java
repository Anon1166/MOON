package int221.MOON.controllers;

import int221.MOON.Dto.AnnouncesDetailDto;
import int221.MOON.Dto.AnnouncesDto;
import int221.MOON.Dto.EditAnnouncesDTO;
import int221.MOON.entities.Announces;
import int221.MOON.service.AnnouncesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/announcements")
public class AnnouncesController {
    @Autowired
    private AnnouncesService announcesService;

    @CrossOrigin
    @GetMapping("")
    public List<AnnouncesDto> getAnnounces(){
        return announcesService.getAnnounces();
    }
    @CrossOrigin
    @GetMapping("/{annId}")
    public AnnouncesDetailDto getAnnouncesById(@PathVariable Integer annId){
        return announcesService.getAnnouncesById(annId);
    }

    @CrossOrigin
    @PostMapping("/add")
    public Announces createAnnouncement(@RequestBody EditAnnouncesDTO announces){
        return  announcesService.createAnnouncement(announces);
    }
    @CrossOrigin
    @PutMapping("/{announcementId}")
    public Announces updateAnnouncement(@PathVariable Integer announcementId , @RequestBody EditAnnouncesDTO announces){
        return  announcesService.updateAnnouncement(announces,announcementId);
    }
    @CrossOrigin
    @DeleteMapping("/{announcementId}")
    public void deleteAnnouncement(@PathVariable Integer announcementId ){
        announcesService.deleteAnnouncement(announcementId);
    }


}
