package com.app.interview.domain.person.service;

import com.app.interview.domain.person.entity.Person;
import com.app.interview.domain.person.entity.PersonKnownTitle;
import com.app.interview.domain.person.repository.PersonKnownTitleRepository;
import com.app.interview.domain.person.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;
    private final PersonKnownTitleRepository knownTitleRepository;

    @Transactional
    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    @Transactional
    public void saveListOfPersonKnownTitle(List<PersonKnownTitle> personKnownTitleList) {
        knownTitleRepository.saveAll(personKnownTitleList);
    }

    public Person findPersonByExternalId(String externalId) {
        return personRepository.findByExternalId(externalId);
    }
}
