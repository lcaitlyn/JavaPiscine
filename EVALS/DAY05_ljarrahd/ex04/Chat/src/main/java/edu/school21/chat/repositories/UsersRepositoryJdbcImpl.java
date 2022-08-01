package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersRepositoryJdbcImpl implements UsersRepository {
    private final DataSource ds;

    public UsersRepositoryJdbcImpl(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public List<User> findAll(int page, int size) throws SQLException {
        String queryString = "WITH users_pag as (SELECT * FROM users OFFSET ? LIMIT ?) SELECT users.id as user_id, users.login as login, users.password as password, array_agg(DISTINCT room.id) as owner_room_id, array_agg(DISTINCT room.name) as owner_room_name, array_agg(DISTINCT rou.chat_room_id) as room_id, array_agg(DISTINCT r.name) as room_name FROM users_pag as users LEFT JOIN room ON users.id = room.owner LEFT JOIN rooms_of_users rou on users.id = rou.user_id LEFT JOIN room r on r.id = rou.chat_room_id group by users.id, users.login, users.password";
        PreparedStatement query = ds.getConnection().prepareStatement(queryString);
        query.setInt(1, page * size);
        query.setInt(2, size);
        ResultSet resultSet = query.executeQuery();
        ArrayList<User> users = new ArrayList<>();
        long id;
        String login;
        String password;
        User user;
        List<Chatroom> createdRooms;
        List<Chatroom> socializedRooms;
        while (resultSet.next()) {
            id = resultSet.getLong(1);
            login = resultSet.getString(2);
            password = resultSet.getString(3);
            createdRooms = getCreatedRooms(resultSet);
            socializedRooms = getSocializedRooms(resultSet);
            user = new User(id, login, password, createdRooms, socializedRooms);
            users.add(user);
        }
        return users;
    }

    List<Chatroom> getCreatedRooms(ResultSet rs) throws SQLException {
        ArrayList<Chatroom> createdRooms = new ArrayList<>();
        Long[] createdRoomsIds;
        String[] createdRoomsNames;
        createdRoomsIds = (Long[]) rs.getArray(4).getArray();
        createdRoomsNames = (String[]) rs.getArray(5).getArray();
        if (createdRoomsIds.length == 1 && createdRoomsIds[0] == null)
            return null;
        for (int i = 0; i < createdRoomsIds.length; i++) {
            if (createdRoomsIds[i] == null)
                System.out.println(createdRoomsNames[i]);
            createdRooms.add(new Chatroom(createdRoomsIds[i], createdRoomsNames[i]));
        }
        return createdRooms;
    }
    List<Chatroom> getSocializedRooms(ResultSet rs) throws SQLException {
        ArrayList<Chatroom> rooms = new ArrayList<>();
        Long[] roomsIds;
        String[] roomsNames;
        roomsIds = (Long[]) rs.getArray(6).getArray();
        roomsNames = (String[]) rs.getArray(7).getArray();
        if (roomsIds.length == 1 && roomsIds[0] == null)
            return null;
        for (int i = 0; i < roomsIds.length; i++) {
            rooms.add(new Chatroom(roomsIds[i], roomsNames[i]));
        }
        return rooms;
    }
}