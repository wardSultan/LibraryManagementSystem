package com.example.LibraryManagementSystem.Patron;
import com.example.LibraryManagementSystem.Common.Exceptions.EntityNotFoundException;
import com.example.LibraryManagementSystem.Patron.Dto.CreatePatronDto;
import com.example.LibraryManagementSystem.Patron.Dto.PatronQueryFilterDto;
import com.example.LibraryManagementSystem.Patron.Dto.UpdatePatronDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PatronService {
    private final PatronRepository patronRepository;

    @Autowired
    public PatronService(PatronRepository patronRepository) {
        this.patronRepository = patronRepository;
    }

    public Page<Patron> getAllPatrons(@Valid PatronQueryFilterDto query) {

        Sort sort = query.getSortOrder().equalsIgnoreCase("asc") ?
                Sort.by(query.getSortField()).ascending() : Sort.by(query.getSortField()).descending();

        Pageable pageable = PageRequest.of(query.getPage(), query.getPageSize(), sort);

        return this.patronRepository.findAll(pageable);
    }

    public Patron getPatronById(Long id) {
        return patronRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("patron", id));
    }

    public Patron addPatron(CreatePatronDto body) {
        Patron patron = new Patron();
        patron.setName(body.getName());
        patron.setContactInfo(body.getContactInfo());


        return patronRepository.save(patron);
    }

    public Patron updatePatron(Long id, UpdatePatronDto body) {
        Patron patron = this.getPatronById(id);

        if (body.getName() != null) {
            patron.setName(body.getName());
        }
        if (body.getContactInfo()!= null) {
            patron.setContactInfo(body.getContactInfo());
        }

        return patronRepository.save(patron);
    }

    public void deletePatron(Long id) {

        if (!patronRepository.existsById(id)) {

            throw new EntityNotFoundException("Patron",id);
        }

        patronRepository.deleteById(id);
    }
}
