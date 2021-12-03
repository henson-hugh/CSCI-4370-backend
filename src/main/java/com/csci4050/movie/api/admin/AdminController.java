package com.csci4050.movie.api.admin;

import com.csci4050.movie.api.EmailSenderService;
import com.csci4050.movie.api.customer.CustomerDto;
import com.csci4050.movie.api.customer.CustomerService;
import com.csci4050.movie.api.model.*;
import com.csci4050.movie.api.movie.MovieDto;
import com.csci4050.movie.api.movie.MovieService;
import com.csci4050.movie.api.promotion.PromotionDto;
import com.csci4050.movie.api.promotion.PromotionService;
import com.csci4050.movie.api.showing.ShowingDto;
import com.csci4050.movie.api.showing.ShowingService;
import com.csci4050.movie.api.user.UserDto;
import com.csci4050.movie.api.user.UserService;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private UserService userService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private ShowingService showingService;

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private AdminRepository adminRepository;



    @PostMapping("/movie/id/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> searchMoviesById(@PathVariable int id) {

        Optional<Movie> movie = movieService.getMovieById(id);
        if (movie.isPresent()) {
            Map<String, Object> map = new HashMap<String, Object>();

            List<Genre> genres = movieService.getGenreByMovieid(movie.get().getMid());
            List<Showing> showings = showingService.getShowingsByMovieid(movie.get().getMid());

            map.put("movie", movie.get());
            map.put("genres", genres);
            map.put("showings", showings);

            return new ResponseEntity<Object>(map, HttpStatus.ACCEPTED);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
    }

    // Add movie
    @PostMapping(value = "/movie/add")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<MovieDto> addMovie(@RequestBody MovieDto movieDto) {
        Movie movie = modelMapper.map(movieDto, Movie.class);

        // check if movie exists
        Optional<Movie> movieExist = movieService.getMovieByTitle(movie.getTitle());
        if (movieExist.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(modelMapper.map(movieExist.get(), MovieDto.class));
        }

        movieService.saveMovie(movie);
        movieDto.setMid(movieService.getMovieByTitle(movie.getTitle()).get().getMid());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(movieDto);
    }

    // Edit movie
    // Add movie
    @PostMapping(value = "/movie/edit")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<MovieDto> editMovie(@RequestBody MovieDto movieDto) {
        Movie movie = modelMapper.map(movieDto, Movie.class);

        // check if movie exists
        Optional<Movie> movieExist = movieService.getMovieById(movie.getMid());
        if (movieExist.isPresent()) {
            movieService.editMovie(movie);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(modelMapper.map(movieExist.get(), MovieDto.class));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(movieDto);
    }

    // Remove movie
    @PostMapping(value = "/movie/remove")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<MovieDto> removeMovie(@RequestBody MovieDto movieDto) {
        Movie movie = modelMapper.map(movieDto, Movie.class);

        // check if it exists
        Optional<Movie> movieExist = movieService.getMovieByTitle(movie.getTitle());
        if (movieExist.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(modelMapper.map(movieExist.get(), MovieDto.class));
        }
        movieService.removeMovie(movie);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(movieDto);
    }

    // Add Genres to movie
    @PostMapping(value = "/movie/{id}/addGenre")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<String> addGenres(@PathVariable("id") int mid, @RequestBody String genre) {

        genre = genre.toLowerCase(Locale.ROOT);

        // add genre-movie association if the association doesn't exist
        if (movieService.addGenre(genre, mid).isPresent()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(genre);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(genre);
    }

    // Add Casts to movie
    @PostMapping(value = "/movie/{id}/addCast")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<String> addCasts(@PathVariable("id") int mid, @RequestBody String name) {

        name = name.toLowerCase(Locale.ROOT);

        // add genre-movie association if the association doesn't exist
        if (movieService.addCast(name, mid).isPresent()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(name);
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(name);
    }

    // Schedule a movie (Create a showing)
    @PostMapping(value = "/movie/schedule")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<ShowingDto> addShowing(@RequestBody ShowingDto showingDto) {
        Showing showing = modelMapper.map(showingDto, Showing.class);

        // check to make sure there are no time conflicts
        Optional<Showing> conflict = showingService.getConflictingShow(showing);
        if (!conflict.isPresent()) {
            showingService.saveShowing(showing);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(showingDto);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(showingDto);
    }

    // Delete a movie (Remove a showing)
    @PostMapping(value = "/movie/remove-schedule")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<ShowingDto> removeShowing(@RequestBody ShowingDto showingDto) {
        Showing showing = modelMapper.map(showingDto, Showing.class);

        // check to make sure there are no time conflicts
        Optional<Showing> conflict = showingService.getConflictingShow(showing);
        if (!conflict.isPresent()) {
            showingService.removeShowing(showing);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(showingDto);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(showingDto);
    }

    // Create a promotion and send email
    @PostMapping(value = "/promotion/add")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<PromotionDto> addPromotion(@RequestBody PromotionDto promotionDto) {
        Promotion promotion = modelMapper.map(promotionDto, Promotion.class);

        promotionService.addPromotion(promotion);


        // find all users with promotions
        List<Customer> customers = customerService.getAllCustomersWithPromo();
        for (Customer c : customers) {
            User user = userService.getUserById(c.getUserid()).get();
            emailSenderService.sendPromoEmail(promotion, user);
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(promotionDto);
    }

    // Suspend a user
    @PostMapping(value = "/customer/suspend")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<CustomerDto> suspendUser(@RequestBody CustomerDto customerDto) {
        Customer customer = modelMapper.map(customerDto, Customer.class);

        // check if it exists
        Optional<Customer> customerExist = customerService.getCustomerById(customer.getCid());
        if (customerExist.isPresent()) {
            customerService.suspendCustomer(customer.getCid(), true);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(modelMapper.map(customerExist.get(), CustomerDto.class));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(customerDto);
    }


    // Remove user
    @PostMapping(value = "/customer/remove")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<CustomerDto> removeUser(@RequestBody CustomerDto customerDto) {
        Customer customer = modelMapper.map(customerDto, Customer.class);

        // check if it exists
        Optional<Customer> customerExist = customerService.getCustomerById(customer.getCid());
        if (customerExist.isPresent()) {
            customerService.deleteCustomer(customer.getCid());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(customerDto);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(customerDto);
    }

    @PostMapping(value = "/admin/create")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<AdminDto> addAdmin(@RequestBody UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        Admin admin = new Admin();
        admin.setUserid(user.getUid());
        adminRepository.save(admin);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(modelMapper.map(admin, AdminDto.class));
    }
}
