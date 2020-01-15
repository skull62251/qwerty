package com.gotapi.ca.plain.server;

import java.util.logging.Logger;

import com.gotapi.ca.plain.server.github.UserModel;
import com.gotapi.ca.plain.server.github.dao.JdbcTemplateGit;

public class CheckUserPermission {
    private static Logger logger = Logger.getLogger(LoadProjectServlet.class.getName());

    public boolean checkUserPermission(String email, String uuid, String repositoryName) {
        logger.info("Requested: email:"+email+" uuid:"+uuid+ " repositoryName:"+repositoryName);

        UserModel user = JdbcTemplateGit.userPermission(email, "install");
        if( user != null ) {
            logger.info("Found: email:"+user.getEmail()+" uuid:"+uuid+ " url:"+user.getUrl());
        }

        boolean accessGranted = user != null
                && user.getUuid().equals(uuid) 
        		&& user.getEmail().equals(email) 
        		&& user.getUrl().toLowerCase().contains(repositoryName.toLowerCase());
        
        logger.info("Granted: "+accessGranted);
        return accessGranted;
    }

}
