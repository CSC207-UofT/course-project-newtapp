package com.newts.newtapp.api.application.user;

import com.newts.newtapp.entities.User;

public class DeleteUser extends UserInteractor {
    private User user;
    private String password;


    @Override
    public void request(RequestModel request) {
        ResponseModel response = new ResponseModel();
        ConfigReader config = (ConfigReader) request.get(RequestField.CONFIG);

        int id = (int) request.get(RequestField.ID);

        if (DataBase.containsUserID(id)) {
            DataBase.deleteUser(id);
            response.fill(ResponseField.SUCCESS, config.get("deletedUser"));
        } else {
            response.fill(ResponseField.FAILURE, config.get("InvalidID"));
        }
        // send response through provided output boundary
        request.getOutput().respond(response);
    }
}