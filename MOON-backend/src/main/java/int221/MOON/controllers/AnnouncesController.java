package int221.MOON.controllers;

import int221.MOON.Dto.AnnouncesDetailDto;
import int221.MOON.Dto.AnnouncesDto;
import int221.MOON.entities.Announces;
import int221.MOON.execption.ErrorResponse;
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

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleCustomerNotFound(Exception ex, WebRequest request) {
        ErrorResponse er = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(), ex.getMessage(), request.getDescription(false).substring(4));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(er);
    }
}
