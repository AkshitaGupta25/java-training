package day18;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.time.LocalDateTime;
import java.util.*;

@Component
@RestController
public class RestAPIExample {
    private Map<String, User> userProfile = new HashMap<>();
    private Map<String,List<Tweet>> tweets = new HashMap<>();
    private Map<String,List<String>> following = new HashMap<>();
    private UserDao userDao = new UserDaoImpl();
    private TweetDao tweetDao = new TweetDaoImpl();

    public RestAPIExample() {
        List<User> users = userDao.readAll();
        for (User user : users) {
            userProfile.put(user.getEmail(), user);
        }

        List<Object[]> list = tweetDao.readAll();
        for (int i = 0 ; i < list.size() ; i++){
            Object[] arr = list.get(i);
            Tweet tweet = new Tweet(arr[0].toString(), arr[1].toString(), arr[2].toString(),(LocalDateTime)arr[3]);
            String email = arr[1].toString();
            if(tweets.containsKey(email)){
                tweets.get(email).add(tweet);
            }
            else {
                List<Tweet> tweetList = new ArrayList<>();
                tweetList.add(tweet);
                tweets.put(arr[1].toString(),tweetList);
            }
        }
    }
    @GetMapping("/displayUserDetails")
    public ModelAndView getUserDetails() {
        ModelAndView modelAndView = new ModelAndView("users");
        if (userProfile.isEmpty())
            allAccDetails();
        List<User> users = new ArrayList<>();
        for(Map.Entry entry: userProfile.entrySet()){
            users.add((User)entry.getValue());
        }
        modelAndView.getModel().put("users", users);
        return modelAndView;
    }

    @GetMapping("/welcome")
    public ModelAndView welcome(){
        ModelAndView modelAndView = new ModelAndView("welcome");
        return modelAndView;
    }

    @GetMapping("/loginForm")
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }

    @GetMapping("/signUpForm")
    public ModelAndView signUp(){
        ModelAndView modelAndView = new ModelAndView("signup");
        return modelAndView;
    }

    @GetMapping("/getTweets")
    public ModelAndView fetchTweets(@RequestParam String email) {
        if (!tweets.containsKey(email))
            return errorMessageModelAndView("You haven't posted any tweet yet!");
        List<Tweet> tweetList = tweetDao.readByEmail(email);
        ModelAndView modelAndView = new ModelAndView("tweets");
        modelAndView.getModel().put("tweets",tweetList);
        modelAndView.getModel().put("name",tweetList.get(0).getName());

        return modelAndView;

    }

    @PostMapping(value = "/signUp", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView signUp(@RequestBody MultiValueMap<String, String> formData) {
        String name = formData.get("name").get(0);
        String email = formData.get("email").get(0);
        String password = formData.get("password").get(0);
        User user = new User(name,email,password);
        ResponseEntity<String> response = createUser(user);
        if (response.getStatusCode() == HttpStatus.OK) {
            ModelAndView modelAndView = new ModelAndView("login");
            return modelAndView;
        }
        else {
            return errorMessageModelAndView(response.getBody().toString());
        }
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView login(@RequestBody MultiValueMap<String, String> formData) {
        if(userProfile.isEmpty())
            allAccDetails();
        if (!isUserRegistered(formData)) {
            return errorMessageModelAndView("User is not registered");
        }
        if (!isUserValid(formData)) {
            return errorMessageModelAndView("Wrong credentials");
        }
        ModelAndView modelAndView = new ModelAndView("profile");
        String email = formData.get("email").get(0);
        String name = userProfile.get(email).getName();
        modelAndView.getModel().put("name", name);
        modelAndView.getModel().put("email", email);
        return modelAndView;
    }


    private ModelAndView errorMessageModelAndView(String message) {
        ModelAndView modelAndView = new ModelAndView("customError");
        if (message.isEmpty() || message.isBlank())
            message = "no message";
        modelAndView.getModel().put("message", message);
        return modelAndView;
    }

    private boolean isUserValid(MultiValueMap<String, String> map) {
        if (userProfile.isEmpty())
            allAccDetails();
        String email = map.get("email").get(0);
        String password = map.get("password").get(0);
        User user = userProfile.get(email);
        if (user.getPassword().equals(password))
            return true;
        return false;
    }
    private boolean isUserRegistered(MultiValueMap<String, String> map) {
        String email = map.get("email").get(0);
        if (userProfile.containsKey(email))
            return true;
        return false;
    }
    //    User can create an account  -->POST
    @PostMapping("/create")
    private ResponseEntity<String> createUser(@RequestBody User user) {
        ResponseEntity<String> responseEntity = null;
        if (userProfile.containsKey(user.getEmail())){
            responseEntity = new ResponseEntity<>("User already registered!",
                    HttpStatus.BAD_REQUEST);
        }
        else {
            String email = user.getEmail();
            userDao.create(user);
            userProfile.put(email, user);
            responseEntity = new ResponseEntity<>("User account created successfully!",HttpStatus.OK);
        }
        return responseEntity;
    }

    //    User can get all account details --> GET
    @GetMapping("/fetchUsers")
    Map<String, User> allAccDetails() {
        return userProfile;
    }

    //    User can fetch particular account details --> GET
    @GetMapping("/getDetails")
    private ResponseEntity<User> getAccDetails(@RequestParam String email, String password) {
        ResponseEntity<User> responseEntity = null;
        if(userProfile.containsKey(email)){
            if(userProfile.get(email).getPassword().equals(password) )
                responseEntity = new ResponseEntity<>(userProfile.get(email),HttpStatus.OK);
            else {
                responseEntity = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
                System.out.println("Wrong Password");
            }
        }
        else {
            responseEntity = new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
            System.out.println("User is not registered");
        }
        return responseEntity;
    }

    //    User can update account details  -->PUT
    @PutMapping("/update")
    private ResponseEntity<String> updateRecord(@RequestBody User user) {
        String email =user.getEmail();
        String updatedName = user.getName();
        String password = user.getPassword();
        ResponseEntity<String> responseEntity = null;
        if(password.equals(userProfile.get(email).getPassword())) {
            if (containsInvalidChars(updatedName)) {
                responseEntity = new ResponseEntity<>("name contains invalid characters",
                        HttpStatus.BAD_REQUEST);
            } else if (userProfile.containsKey(email)) {
                String currName = userProfile.get(email).getName();
                if (currName.equals(updatedName)) {
                    responseEntity = new ResponseEntity<>("No change rquired",
                            HttpStatus.OK);
                } else {
                    userProfile.get(email).setName(updatedName);
                    //write update query here
                    responseEntity = new ResponseEntity<>("update successful",
                            HttpStatus.OK);
                }
            } else {
                responseEntity = new ResponseEntity<>("User doesn't exist",
                        HttpStatus.NOT_FOUND);
            }
        }
        else {
            responseEntity = new ResponseEntity<>("Wrong password",
                    HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    private boolean containsInvalidChars(String name) {
        // name contains any numbers or +-
        return false;
    }


    //    User can delete account  -->DELETE
    @DeleteMapping("/delete")
    private ResponseEntity<String> deleteRecord(@RequestParam String email, String password) {
        ResponseEntity<String> responseEntity = null;
        if(userProfile.containsKey(email)){
            if(userProfile.get(email).getPassword().equals(password) ) {
                //write delete query here
                responseEntity = new ResponseEntity<>("User Deleted Successfully", HttpStatus.OK);
                userProfile.remove(email);
            }
            else {
                responseEntity = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
                System.out.println("Wrong Password");
            }
        }
        else {
            responseEntity = new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
            System.out.println("User is not registered");
        }
        return responseEntity;
    }

    //User can create tweet -->POST
    @PostMapping(value = "/createTweet",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ModelAndView createTweet(@RequestBody MultiValueMap<String, String> newTweet){
        ResponseEntity<String> responseEntity = null;
        String email = newTweet.get("email").get(0);
        String content = newTweet.get("content").get(0);
        LocalDateTime time = LocalDateTime.now();
        String name = userProfile.get(email).getName();
        Tweet t = new Tweet(name,email,content,time);
        if (userProfile.isEmpty())
            allAccDetails();
        if(userProfile.containsKey(email)){
                if(tweets.containsKey(email))
                    tweets.get(email).add(t);
                else {
                    List<Tweet> list = new ArrayList<>();
                    list.add(t);
                    tweets.put(email, list);
                }
                tweetDao.create(t);
                responseEntity = new ResponseEntity<>("Tweet added successfully",HttpStatus.OK);
                ModelAndView modelAndView = new ModelAndView("profile");
                modelAndView.getModel().put("name", name);
                modelAndView.getModel().put("email", email);
                return modelAndView;

        }
        else {
            responseEntity = new ResponseEntity<>("User is not registered",HttpStatus.BAD_REQUEST);
            errorMessageModelAndView("User is not registered");
        }
        return null;
    }

    //user can see all tweets
    @GetMapping("/fetchTweets")
    Map<String,List<Tweet>> fetchTweets(){
            return tweets;
    }

    //user can see tweets from a particular account
    @GetMapping("/fetchTweetsOfUser")
    List<Tweet> fetchTweetsOfUser(@RequestParam String email){
        return tweets.get(email);
    }

    //user can follow another user
    @PostMapping("/followUser")
    private ResponseEntity<String> followUsers(@RequestParam String email, String userEmail){
        ResponseEntity<String> responseEntity = null;
        if (!userProfile.containsKey(email)){
            responseEntity = new ResponseEntity<>("User doesn't exist",HttpStatus.BAD_REQUEST);
            return responseEntity;
        }
        if(userEmail.equals(email)){
            responseEntity = new ResponseEntity<>("You can't follow yourself",HttpStatus.BAD_REQUEST);
            return responseEntity;
        }
        if (userProfile.get(userEmail) != null) {
            if (following.containsKey(email)) {
                following.get(email).add(userEmail);
            } else {
                List<String> list = new ArrayList<>();
                list.add(userEmail);
                following.put(email, list);
            }
            responseEntity = new ResponseEntity<>("User " + userEmail + " followed successfully"
                                                    ,HttpStatus.OK);
        }
        else {
            responseEntity = new ResponseEntity<>("User you are following doesn't exist"
                                                    ,HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    //to view following of a user
    @GetMapping("/follow")
    private List<String> following(@RequestParam String email){
        if(following.containsKey(email))
            return following.get(email);
        else
            return Arrays.asList("User doesn't follow anyone");
    }
}