package com.example.gravity.devxplore.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.gravity.devxplore.data.model.User;
import com.example.gravity.devxplore.data.model.UserDetails;

import java.util.List;

/**
 * Created by gravity on 8/25/17.
 */
@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User... user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertDetail(UserDetails... detail);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllUsers(List<User> users);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllDetails(List<UserDetails> detailsList);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFavourite(User... favourite);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllFavourites(List<User> favourites);

    @Query("SELECT * FROM users")
    List<User> loadAllUsers();

    @Query("SELECT * FROM favourites")
    List<User> loadAllFavourites();

    @Query("SELECT * FROM users WHERE login = :login")
    User loadUser(String login);

    @Query("SELECT * FROM details WHERE login = :login")
    UserDetails loadDetail(String login);

    @Query("UPDATE users SET is_favourite = :isFavourite WHERE login = :login")
    void updateUser(String login, boolean isFavourite);

    @Query("UPDATE details SET is_favourite = :isFavourite WHERE login = :login")
    void updateDetail(String login, boolean isFavourite);

    @Query("UPDATE favourites SET is_favourite = :isFavourite WHERE login = :login")
    void updateFavourites(String login, boolean isFavourite);

    @Query("DELETE FROM favourites WHERE login = :login")
    void deleteFavourite(String login);

    @Query("DELETE FROM users")
    void deleteAllUsers();

    @Query("DELETE FROM details")
    void deleteAllDetails();

    @Query("DELETE FROM favourites")
    void deleteAllFavourites();

}
