package int221.MOON.controllers;

import int221.MOON.Dto.*;
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
    @PostMapping("")
    public EditAnnDto createAnnouncement(@RequestBody InputAnnouncesDTO announces){
        return  announcesService.createAnnouncement(announces);
    }
    @CrossOrigin
    @PutMapping("/{announcementId}")
    public UpdateDto updateAnnouncement(@PathVariable Integer announcementId , @RequestBody InputAnnouncesDTO announces){
        return  announcesService.updateAnnouncement(announces,announcementId);
    }
    @CrossOrigin
    @DeleteMapping("/{announcementId}")
    public void deleteAnnouncement(@PathVariable Integer announcementId ){
        announcesService.deleteAnnouncement(announcementId);
    }


}
