package pl.coderslab.charity.institution;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstitutionService {

    private final InstitutionRepository institutionRepository;

    public InstitutionService(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    public List<Institution> getAllInstitutions() {
        return institutionRepository.findAll();
    }

    public void createInstitution(Institution institution) {
        institutionRepository.save(institution);
    }

    public Institution getInstitutionById(Long id) {
        return institutionRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public void updateInstitution(Institution institution) {
        institutionRepository.save(institution);
    }
}
