package com.csci4050.movie.api.showing;

import com.csci4050.movie.api.model.Showing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShowingService {

    @Autowired
    private ShowingRepository showingRepository;

    public Optional<Showing> saveShowing(Showing showing) {
        showingRepository.save(showing);
        return Optional.empty();
    }

    public Optional<Showing> getConflictingShow(Showing showing) {
        // check for date and time conflict
        Optional<Showing> dateAndTime = showingRepository.findByDateAndTime(showing.getDate(), showing.getTime());
        if (dateAndTime.isPresent()) {
            return dateAndTime;
        }
        return Optional.empty();
    }
}
