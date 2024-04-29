package EntityLister;

import Entities.User;
import Exceptions.UserNotRegisteredException;
import com.google.gson.*;

import java.util.List;
import java.util.Set;

import java.lang.reflect.Type;

class UserSerializer implements JsonSerializer<User> {
    @Override
    public JsonElement serialize(User user, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("email", user.getEmail());
        jsonObject.addProperty("firstName", user.getFirstName());
        jsonObject.addProperty("lastName", user.getLastName());
        jsonObject.addProperty("address", user.getAddress());

        JsonArray jsonFriendsArray = new JsonArray();

        for (User friend : user.getFriends()) {
            jsonFriendsArray.add(friend.getEmail());
        }
        jsonObject.add("friends", jsonFriendsArray);

        return jsonObject;
    }
}

public class EntityLister {
    private final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .registerTypeAdapter(User.class, new UserSerializer())
            .create();

    public void listUser(String userEmail) throws UserNotRegisteredException {
        if (User.getUserByEmail(userEmail) == null)
            throw new UserNotRegisteredException("User with " + userEmail + " doesn't exist.");

        System.out.println(gson.toJson(User.getUserByEmail(userEmail)));
    }

    public void listPortfolio(String userEmail) throws UserNotRegisteredException {
        if (User.getUserByEmail(userEmail) == null)
            throw new UserNotRegisteredException("User with " + userEmail + " doesn't exist.");

        System.out.println(gson.toJson(User.getUserByEmail(userEmail).getPortfolio()));
    }

    public void listRecommendedStocks(Set<String> recommendedStocks) {
        System.out.println(gson.toJson(recommendedStocks));
    }

    public static void main(String[] args) {    // TODO delete test main
        User user = new User("alexx.trifu", "alex", "trifu", "nu se spune");
//        new EntityLister().listUser(user.getEmail());
//        new EntityLister().listPortfolio(user.getEmail());
    }
}
