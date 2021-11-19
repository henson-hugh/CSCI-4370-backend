package com.csci4050.movie.api.showing;

import com.csci4050.movie.api.model.Movie;
import com.csci4050.movie.api.model.Showing;
import com.csci4050.movie.api.movie.MovieRepository;
import com.csci4050.movie.api.movie.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class ShowingService {

    @Autowired
    private ShowingRepository showingRepository;

    @Autowired
    private MovieService movieService;

    public Optional<Showing> saveShowing(Showing showing) {
        Showing saveShowing = showing;
        saveShowing.setTime(showing.getTime());
        showingRepository.save(saveShowing);
        return Optional.empty();
    }

    public Optional<Showing> getConflictingShow(Showing showing) {
        // check for date and time conflict

        // optional return object
        Optional<Showing> returnShowing = Optional.empty();

        // find everything with same date
        List<Showing> showDate = showingRepository.findAllByDateAndRoomid(showing.getDate(), showing.getRoomid());
        // convert to showing object
        Showing[] showingDate = new Showing[showDate.size()];
        for(int i = 0; i < showDate.size(); i++) {
            showingDate[i] = showDate.get(i);
        }

        // if showing's start time or end time is within the duration of an existing showing, return the existing showing
        for(Showing s : showingDate) {
            Movie movie = movieService.getMovieById(showing.getMovieid()).get();
            Movie m = movieService.getMovieById(s.getMovieid()).get();
            //System.out.println(showing.getTime().compareTo(s.getTime()));
            // starting time check (if showing starts after start of another show, or before the end of another show)
            if (showing.getTime().compareTo(s.getTime()) >= 0 && showing.getTime().compareTo(s.getTime().plusMinutes(m.getDuration())) <= 0){ // operators might be wrong, It is hard to tell <= 0
                    //showing.getTime() >= s.getTime() && showing.getTime() <= s.getTime() + s.getDuration()) {
                returnShowing = Optional.of(s);
            }

            // ending time check (if show ends after the start of another show, or before the end of another show)
            if (showing.getTime().plusMinutes(movie.getDuration()).compareTo(s.getTime()) > 0 && showing.getTime().plusMinutes(movie.getDuration()).compareTo(s.getTime().plusMinutes(m.getDuration())) <= 0) {
                returnShowing = Optional.of(s);
            }
        }
        return returnShowing;
    }

    public Optional<Showing> removeShowing(Showing showing) {
        showingRepository.delete(showing);
        return Optional.of(showing);
    }

    public List<Showing> getShowingsByMovieid(int mid) {
        return showingRepository.findAllByMovieid(mid);
    }
}
