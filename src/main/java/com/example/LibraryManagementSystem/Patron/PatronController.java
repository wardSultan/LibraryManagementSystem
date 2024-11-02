package com.example.LibraryManagementSystem.Patron;
import com.example.LibraryManagementSystem.Patron.Dto.CreatePatronDto;
import com.example.LibraryManagementSystem.Patron.Dto.PatronQueryFilterDto;
import com.example.LibraryManagementSystem.Patron.Dto.UpdatePatronDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/patrons")
@Validated
public class PatronController {

    private final PatronService patronService;

    @Autowired
    public PatronController(PatronService patronService) {
        this.patronService = patronService;
    }

    @GetMapping
    public Page<Patron> getAllPatrons(@Valid PatronQueryFilterDto query) {
        return new ResponseEntity<>(this.patronService.getAllPatrons(query), HttpStatus.OK).getBody();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Patron> getPatronById(@PathVariable Long id) {
        Patron patron = patronService.getPatronById(id);
        return new ResponseEntity<>(patron, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Patron> addPatron(@Valid @RequestBody CreatePatronDto body) {
        return new ResponseEntity<>(patronService.addPatron(body), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patron>  updatePatron(@PathVariable Long id,@Valid @RequestBody UpdatePatronDto body) {
        return new ResponseEntity<>(patronService.updatePatron(id, body), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatron(@PathVariable Long id) {
        patronService.deletePatron(id);
        return ResponseEntity.ok("patron with ID " + id + " has been deleted successfully.");
    }
}
