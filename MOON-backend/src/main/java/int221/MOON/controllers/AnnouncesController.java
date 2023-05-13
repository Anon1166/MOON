package int221.MOON.controllers;

import int221.MOON.Dto.*;
import int221.MOON.service.AnnouncesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = {"http://localhost:5173", "http://127.0.0.1:5173", "http://intproj22.sit.kmutt.ac.th/at2"})
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
    public EditAnnDto createAnnouncement(@Valid @RequestBody InputAnnouncesDTO announces){
        return  announcesService.createAnnouncement(announces);
    }
    @PutMapping("/{announcementId}")
    public UpdateDto updateAnnouncement(@PathVariable Integer announcementId ,@Valid @RequestBody InputAnnouncesDTO announces){
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
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "0") Integer category) {
       return announcesService.getAnnouncementPage(mode, page, size, category);
   }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(errors.toString());
    }
}
