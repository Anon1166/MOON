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

    @GetMapping("")
    public List<AnnouncesDto> getAnnounces(){
        return announcesService.getAnnounces();
    }

    @GetMapping("/{annId}")
    public AnnouncesDetailDto getAnnouncesById(@PathVariable Integer annId){
        return announcesService.getAnnouncesById(annId);
    }

<<<<<<< HEAD
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
=======
>>>>>>> ce7ec7f1de132f4b2a0ac8742726f9b8b2d24277
}
