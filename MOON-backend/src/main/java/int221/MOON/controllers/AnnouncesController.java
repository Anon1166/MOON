package int221.MOON.controllers;

import int221.MOON.Dto.*;
import int221.MOON.entities.Announces;
import int221.MOON.service.AnnouncesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/announcements")
public class AnnouncesController {
    @Autowired
    private AnnouncesService announcesService;

    @GetMapping("")
    public List<AnnouncesDto> getAnnounces(@RequestParam(defaultValue = "admin") String mode){
        return announcesService.getAnnounces(mode);
    }
    @GetMapping("/{annId}")
    public AnnouncesDetailDto getAnnouncesById(@PathVariable Integer annId){
        return announcesService.getAnnouncesById(annId);
    }

    @PostMapping("")
    public EditAnnDto createAnnouncement(@RequestBody InputAnnouncesDTO announces){
        return  announcesService.createAnnouncement(announces);
    }
    @PutMapping("/{announcementId}")
    public UpdateDto updateAnnouncement(@PathVariable Integer announcementId , @RequestBody InputAnnouncesDTO announces){
        return  announcesService.updateAnnouncement(announces,announcementId);
    }
    @DeleteMapping("/{announcementId}")
    public void deleteAnnouncement(@PathVariable Integer announcementId ){
        announcesService.deleteAnnouncement(announcementId);
    }

    //Page
   @GetMapping("/pages")
   public PageDTO<AnnouncesDto> getAnnouncesDtoPage(
            @RequestParam(defaultValue = "active") String mode,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size) {
       return announcesService.getAnnouncementPage(mode, page, size);
   }

}
